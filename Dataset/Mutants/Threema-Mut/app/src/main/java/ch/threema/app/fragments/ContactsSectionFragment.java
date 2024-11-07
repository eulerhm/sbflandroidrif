/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2013-2021 Threema GmbH
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License, version 3,
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package ch.threema.app.fragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;
import com.google.android.material.chip.Chip;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.core.util.Pair;
import androidx.core.view.MenuItemCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.activities.AddContactActivity;
import ch.threema.app.activities.ComposeMessageActivity;
import ch.threema.app.activities.ContactDetailActivity;
import ch.threema.app.activities.ThreemaActivity;
import ch.threema.app.adapters.ContactListAdapter;
import ch.threema.app.asynctasks.DeleteContactAsyncTask;
import ch.threema.app.dialogs.BottomSheetAbstractDialog;
import ch.threema.app.dialogs.BottomSheetGridDialog;
import ch.threema.app.dialogs.GenericAlertDialog;
import ch.threema.app.emojis.EmojiTextView;
import ch.threema.app.exceptions.FileSystemNotPresentException;
import ch.threema.app.jobs.WorkSyncService;
import ch.threema.app.listeners.ContactListener;
import ch.threema.app.listeners.ContactSettingsListener;
import ch.threema.app.listeners.PreferenceListener;
import ch.threema.app.listeners.SynchronizeContactsListener;
import ch.threema.app.managers.ListenerManager;
import ch.threema.app.managers.ServiceManager;
import ch.threema.app.routines.SynchronizeContactsRoutine;
import ch.threema.app.services.AvatarCacheService;
import ch.threema.app.services.ContactService;
import ch.threema.app.services.LockAppService;
import ch.threema.app.services.PreferenceService;
import ch.threema.app.services.SynchronizeContactsService;
import ch.threema.app.services.UserService;
import ch.threema.app.ui.BottomSheetItem;
import ch.threema.app.ui.EmptyView;
import ch.threema.app.ui.LockingSwipeRefreshLayout;
import ch.threema.app.ui.ResumePauseHandler;
import ch.threema.app.utils.AnimationUtil;
import ch.threema.app.utils.BitmapUtil;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.IntentDataUtil;
import ch.threema.app.utils.RuntimeUtil;
import ch.threema.app.utils.ShareUtil;
import ch.threema.app.utils.TestUtil;
import ch.threema.app.workers.IdentityStatesWorker;
import ch.threema.base.ThreemaException;
import ch.threema.localcrypto.MasterKeyLockedException;
import ch.threema.storage.models.ContactModel;
import static android.view.MenuItem.SHOW_AS_ACTION_ALWAYS;
import static android.view.MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW;
import static android.view.MenuItem.SHOW_AS_ACTION_NEVER;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ContactsSectionFragment extends MainFragment implements SwipeRefreshLayout.OnRefreshListener, ListView.OnItemClickListener, ContactListAdapter.AvatarListener, GenericAlertDialog.DialogClickListener, BottomSheetAbstractDialog.BottomSheetDialogCallback {

    private static final Logger logger = LoggerFactory.getLogger(ContactsSectionFragment.class);

    private static final int PERMISSION_REQUEST_REFRESH_CONTACTS = 1;

    private static final String DIALOG_TAG_REALLY_DELETE_CONTACTS = "rdc";

    private static final String DIALOG_TAG_SHARE_WITH = "wsw";

    private static final String RUN_ON_ACTIVE_SHOW_LOADING = "show_loading";

    private static final String RUN_ON_ACTIVE_HIDE_LOADING = "hide_loading";

    private static final String RUN_ON_ACTIVE_UPDATE_LIST = "update_list";

    private static final String RUN_ON_ACTIVE_REFRESH_LIST = "refresh_list";

    private static final String RUN_ON_ACTIVE_REFRESH_PULL_TO_REFRESH = "pull_to_refresh";

    private static final String BUNDLE_FILTER_QUERY_C = "BundleFilterC";

    private static final String BUNDLE_SELECTED_TAB = "tabpos";

    private static final int TAB_ALL_CONTACTS = 0;

    private static final int TAB_WORK_ONLY = 1;

    private ResumePauseHandler resumePauseHandler;

    private ListView listView;

    private Chip contactsCounterChip;

    private LockingSwipeRefreshLayout swipeRefreshLayout;

    private ServiceManager serviceManager;

    private SearchView searchView;

    private MenuItem searchMenuItem;

    private ContactListAdapter contactListAdapter;

    private ActionMode actionMode = null;

    private ExtendedFloatingActionButton floatingButtonView;

    private EmojiTextView stickyInitialView;

    private FrameLayout stickyInitialLayout;

    private TabLayout workTabLayout;

    private SynchronizeContactsService synchronizeContactsService;

    private ContactService contactService;

    private PreferenceService preferenceService;

    private LockAppService lockAppService;

    private String filterQuery;

    @SuppressLint("StaticFieldLeak")
    private final TabLayout.OnTabSelectedListener onTabSelectedListener = new TabLayout.OnTabSelectedListener() {

        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            if (!ListenerUtil.mutListener.listen(26501)) {
                if ((ListenerUtil.mutListener.listen(26500) ? (swipeRefreshLayout != null || swipeRefreshLayout.isRefreshing()) : (swipeRefreshLayout != null && swipeRefreshLayout.isRefreshing()))) {
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(26507)) {
                new FetchContactsTask(contactService, false, tab.getPosition(), true) {

                    @Override
                    protected void onPostExecute(Pair<List<ContactModel>, FetchResults> result) {
                        final List<ContactModel> contactModels = result.first;
                        if (!ListenerUtil.mutListener.listen(26506)) {
                            if ((ListenerUtil.mutListener.listen(26502) ? (contactModels != null || contactListAdapter != null) : (contactModels != null && contactListAdapter != null))) {
                                if (!ListenerUtil.mutListener.listen(26503)) {
                                    contactListAdapter.updateData(contactModels);
                                }
                                if (!ListenerUtil.mutListener.listen(26505)) {
                                    if (!TestUtil.empty(filterQuery)) {
                                        if (!ListenerUtil.mutListener.listen(26504)) {
                                            contactListAdapter.getFilter().filter(filterQuery);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR);
            }
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {
        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {
        }
    };

    /**
     *  Simple POJO to hold the number of contacts that were added in the last 24h / 30d.
     */
    private static class FetchResults {

        int last24h = 0;

        int last30d = 0;

        int workCount = 0;
    }

    // Contacts changed receiver
    private final BroadcastReceiver contactsChangedReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (!ListenerUtil.mutListener.listen(26509)) {
                if (resumePauseHandler != null) {
                    if (!ListenerUtil.mutListener.listen(26508)) {
                        resumePauseHandler.runOnActive(RUN_ON_ACTIVE_REFRESH_LIST, runIfActiveUpdateList);
                    }
                }
            }
        }
    };

    private void startSwipeRefresh() {
        if (!ListenerUtil.mutListener.listen(26514)) {
            if (swipeRefreshLayout != null) {
                if (!ListenerUtil.mutListener.listen(26510)) {
                    swipeRefreshLayout.setRefreshing(true);
                }
                if (!ListenerUtil.mutListener.listen(26513)) {
                    if ((ListenerUtil.mutListener.listen(26511) ? (ConfigUtils.isWorkBuild() || workTabLayout != null) : (ConfigUtils.isWorkBuild() && workTabLayout != null))) {
                        if (!ListenerUtil.mutListener.listen(26512)) {
                            workTabLayout.selectTab(workTabLayout.getTabAt(TAB_ALL_CONTACTS), true);
                        }
                    }
                }
            }
        }
    }

    private void stopSwipeRefresh() {
        if (!ListenerUtil.mutListener.listen(26516)) {
            if (swipeRefreshLayout != null) {
                if (!ListenerUtil.mutListener.listen(26515)) {
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        }
    }

    private final ResumePauseHandler.RunIfActive runIfActiveShowLoading = new ResumePauseHandler.RunIfActive() {

        @Override
        public void runOnUiThread() {
        }
    };

    private final ResumePauseHandler.RunIfActive runIfActiveClearCacheAndRefresh = new ResumePauseHandler.RunIfActive() {

        @Override
        public void runOnUiThread() {
            if (!ListenerUtil.mutListener.listen(26524)) {
                if ((ListenerUtil.mutListener.listen(26517) ? (synchronizeContactsService != null || !synchronizeContactsService.isSynchronizationInProgress()) : (synchronizeContactsService != null && !synchronizeContactsService.isSynchronizationInProgress()))) {
                    if (!ListenerUtil.mutListener.listen(26518)) {
                        stopSwipeRefresh();
                    }
                    if (!ListenerUtil.mutListener.listen(26522)) {
                        if (serviceManager != null) {
                            try {
                                AvatarCacheService avatarCacheService = serviceManager.getAvatarCacheService();
                                if (!ListenerUtil.mutListener.listen(26521)) {
                                    if (avatarCacheService != null) {
                                        if (!ListenerUtil.mutListener.listen(26520)) {
                                            // clear the cache
                                            avatarCacheService.clear();
                                        }
                                    }
                                }
                            } catch (FileSystemNotPresentException e) {
                                if (!ListenerUtil.mutListener.listen(26519)) {
                                    logger.error("Exception", e);
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(26523)) {
                        updateList();
                    }
                }
            }
        }
    };

    private final ResumePauseHandler.RunIfActive runIfActiveUpdateList = new ResumePauseHandler.RunIfActive() {

        @Override
        public void runOnUiThread() {
            if (!ListenerUtil.mutListener.listen(26527)) {
                if ((ListenerUtil.mutListener.listen(26525) ? (synchronizeContactsService == null && !synchronizeContactsService.isSynchronizationInProgress()) : (synchronizeContactsService == null || !synchronizeContactsService.isSynchronizationInProgress()))) {
                    if (!ListenerUtil.mutListener.listen(26526)) {
                        updateList();
                    }
                }
            }
        }
    };

    private final ResumePauseHandler.RunIfActive runIfActiveUpdatePullToRefresh = new ResumePauseHandler.RunIfActive() {

        @Override
        public void runOnUiThread() {
            if (!ListenerUtil.mutListener.listen(26529)) {
                if (TestUtil.required(swipeRefreshLayout, preferenceService)) {
                    if (!ListenerUtil.mutListener.listen(26528)) {
                        swipeRefreshLayout.setEnabled(true);
                    }
                }
            }
        }
    };

    private final ResumePauseHandler.RunIfActive runIfActiveCreateList = new ResumePauseHandler.RunIfActive() {

        @Override
        public void runOnUiThread() {
            if (!ListenerUtil.mutListener.listen(26530)) {
                createListAdapter(null);
            }
        }
    };

    private final SynchronizeContactsListener synchronizeContactsListener = new SynchronizeContactsListener() {

        @Override
        public void onStarted(SynchronizeContactsRoutine startedRoutine) {
            if (!ListenerUtil.mutListener.listen(26534)) {
                // only show loading on "full sync"
                if ((ListenerUtil.mutListener.listen(26532) ? ((ListenerUtil.mutListener.listen(26531) ? (resumePauseHandler != null || swipeRefreshLayout != null) : (resumePauseHandler != null && swipeRefreshLayout != null)) || startedRoutine.fullSync()) : ((ListenerUtil.mutListener.listen(26531) ? (resumePauseHandler != null || swipeRefreshLayout != null) : (resumePauseHandler != null && swipeRefreshLayout != null)) && startedRoutine.fullSync()))) {
                    if (!ListenerUtil.mutListener.listen(26533)) {
                        resumePauseHandler.runOnActive(RUN_ON_ACTIVE_SHOW_LOADING, runIfActiveShowLoading);
                    }
                }
            }
        }

        @Override
        public void onFinished(SynchronizeContactsRoutine finishedRoutine) {
            if (!ListenerUtil.mutListener.listen(26537)) {
                if ((ListenerUtil.mutListener.listen(26535) ? (resumePauseHandler != null || swipeRefreshLayout != null) : (resumePauseHandler != null && swipeRefreshLayout != null))) {
                    if (!ListenerUtil.mutListener.listen(26536)) {
                        resumePauseHandler.runOnActive(RUN_ON_ACTIVE_HIDE_LOADING, runIfActiveClearCacheAndRefresh);
                    }
                }
            }
        }

        @Override
        public void onError(SynchronizeContactsRoutine finishedRoutine) {
            if (!ListenerUtil.mutListener.listen(26540)) {
                if ((ListenerUtil.mutListener.listen(26538) ? (resumePauseHandler != null || swipeRefreshLayout != null) : (resumePauseHandler != null && swipeRefreshLayout != null))) {
                    if (!ListenerUtil.mutListener.listen(26539)) {
                        resumePauseHandler.runOnActive(RUN_ON_ACTIVE_HIDE_LOADING, runIfActiveClearCacheAndRefresh);
                    }
                }
            }
        }
    };

    private final ContactSettingsListener contactSettingsListener = new ContactSettingsListener() {

        @Override
        public void onSortingChanged() {
            if (!ListenerUtil.mutListener.listen(26542)) {
                if (resumePauseHandler != null) {
                    if (!ListenerUtil.mutListener.listen(26541)) {
                        resumePauseHandler.runOnActive(RUN_ON_ACTIVE_REFRESH_LIST, runIfActiveCreateList);
                    }
                }
            }
        }

        @Override
        public void onNameFormatChanged() {
            if (!ListenerUtil.mutListener.listen(26544)) {
                if (resumePauseHandler != null) {
                    if (!ListenerUtil.mutListener.listen(26543)) {
                        resumePauseHandler.runOnActive(RUN_ON_ACTIVE_REFRESH_LIST, runIfActiveUpdateList);
                    }
                }
            }
        }

        @Override
        public void onAvatarSettingChanged() {
            if (!ListenerUtil.mutListener.listen(26546)) {
                if (resumePauseHandler != null) {
                    if (!ListenerUtil.mutListener.listen(26545)) {
                        resumePauseHandler.runOnActive(RUN_ON_ACTIVE_REFRESH_LIST, runIfActiveUpdateList);
                    }
                }
            }
        }

        @Override
        public void onInactiveContactsSettingChanged() {
            if (!ListenerUtil.mutListener.listen(26548)) {
                if (resumePauseHandler != null) {
                    if (!ListenerUtil.mutListener.listen(26547)) {
                        resumePauseHandler.runOnActive(RUN_ON_ACTIVE_REFRESH_LIST, runIfActiveUpdateList);
                    }
                }
            }
        }

        @Override
        public void onNotificationSettingChanged(String uid) {
        }
    };

    private final ContactListener contactListener = new ContactListener() {

        @Override
        public void onModified(ContactModel modifiedContactModel) {
            if (!ListenerUtil.mutListener.listen(26549)) {
                logger.debug("*** onModified " + modifiedContactModel.getIdentity());
            }
            if (!ListenerUtil.mutListener.listen(26551)) {
                if (resumePauseHandler != null) {
                    if (!ListenerUtil.mutListener.listen(26550)) {
                        resumePauseHandler.runOnActive(RUN_ON_ACTIVE_UPDATE_LIST, runIfActiveUpdateList);
                    }
                }
            }
        }

        @Override
        public void onAvatarChanged(ContactModel contactModel) {
            if (!ListenerUtil.mutListener.listen(26552)) {
                logger.debug("*** onAvatarChanged -> onModified " + contactModel.getIdentity());
            }
            if (!ListenerUtil.mutListener.listen(26553)) {
                this.onModified(contactModel);
            }
        }

        @Override
        public void onNew(final ContactModel createdContactModel) {
            if (!ListenerUtil.mutListener.listen(26555)) {
                if (resumePauseHandler != null) {
                    if (!ListenerUtil.mutListener.listen(26554)) {
                        resumePauseHandler.runOnActive(RUN_ON_ACTIVE_UPDATE_LIST, runIfActiveUpdateList);
                    }
                }
            }
        }

        @Override
        public void onRemoved(ContactModel removedContactModel) {
            if (!ListenerUtil.mutListener.listen(26561)) {
                RuntimeUtil.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        if (!ListenerUtil.mutListener.listen(26560)) {
                            if ((ListenerUtil.mutListener.listen(26557) ? ((ListenerUtil.mutListener.listen(26556) ? (searchView != null || searchMenuItem != null) : (searchView != null && searchMenuItem != null)) || searchMenuItem.isActionViewExpanded()) : ((ListenerUtil.mutListener.listen(26556) ? (searchView != null || searchMenuItem != null) : (searchView != null && searchMenuItem != null)) && searchMenuItem.isActionViewExpanded()))) {
                                if (!ListenerUtil.mutListener.listen(26558)) {
                                    filterQuery = null;
                                }
                                if (!ListenerUtil.mutListener.listen(26559)) {
                                    searchMenuItem.collapseActionView();
                                }
                            }
                        }
                    }
                });
            }
            if (!ListenerUtil.mutListener.listen(26563)) {
                if (resumePauseHandler != null) {
                    if (!ListenerUtil.mutListener.listen(26562)) {
                        resumePauseHandler.runOnActive(RUN_ON_ACTIVE_UPDATE_LIST, runIfActiveCreateList);
                    }
                }
            }
        }

        @Override
        public boolean handle(String identity) {
            return true;
        }
    };

    private final PreferenceListener preferenceListener = new PreferenceListener() {

        @Override
        public void onChanged(String key, Object value) {
            if (!ListenerUtil.mutListener.listen(26566)) {
                if (TestUtil.compare(key, getString(R.string.preferences__sync_contacts))) {
                    if (!ListenerUtil.mutListener.listen(26565)) {
                        if (resumePauseHandler != null) {
                            if (!ListenerUtil.mutListener.listen(26564)) {
                                resumePauseHandler.runOnActive(RUN_ON_ACTIVE_REFRESH_PULL_TO_REFRESH, runIfActiveUpdatePullToRefresh);
                            }
                        }
                    }
                }
            }
        }
    };

    /**
     *  An AsyncTask that fetches contacts and add counts in the background.
     */
    private static class FetchContactsTask extends AsyncTask<Void, Void, Pair<List<ContactModel>, FetchResults>> {

        ContactService contactService;

        boolean isOnLaunch, forceWork;

        int selectedTab;

        FetchContactsTask(ContactService contactService, boolean isOnLaunch, int selectedTab, boolean forceWork) {
            if (!ListenerUtil.mutListener.listen(26567)) {
                this.contactService = contactService;
            }
            if (!ListenerUtil.mutListener.listen(26568)) {
                this.isOnLaunch = isOnLaunch;
            }
            if (!ListenerUtil.mutListener.listen(26569)) {
                this.selectedTab = selectedTab;
            }
            if (!ListenerUtil.mutListener.listen(26570)) {
                this.forceWork = forceWork;
            }
        }

        @Override
        protected Pair<List<ContactModel>, FetchResults> doInBackground(Void... voids) {
            List<ContactModel> allContacts = null;
            // Count new contacts
            final FetchResults results = new FetchResults();
            if (!ListenerUtil.mutListener.listen(26586)) {
                if ((ListenerUtil.mutListener.listen(26576) ? (ConfigUtils.isWorkBuild() || (ListenerUtil.mutListener.listen(26575) ? (selectedTab >= TAB_WORK_ONLY) : (ListenerUtil.mutListener.listen(26574) ? (selectedTab <= TAB_WORK_ONLY) : (ListenerUtil.mutListener.listen(26573) ? (selectedTab > TAB_WORK_ONLY) : (ListenerUtil.mutListener.listen(26572) ? (selectedTab < TAB_WORK_ONLY) : (ListenerUtil.mutListener.listen(26571) ? (selectedTab != TAB_WORK_ONLY) : (selectedTab == TAB_WORK_ONLY))))))) : (ConfigUtils.isWorkBuild() && (ListenerUtil.mutListener.listen(26575) ? (selectedTab >= TAB_WORK_ONLY) : (ListenerUtil.mutListener.listen(26574) ? (selectedTab <= TAB_WORK_ONLY) : (ListenerUtil.mutListener.listen(26573) ? (selectedTab > TAB_WORK_ONLY) : (ListenerUtil.mutListener.listen(26572) ? (selectedTab < TAB_WORK_ONLY) : (ListenerUtil.mutListener.listen(26571) ? (selectedTab != TAB_WORK_ONLY) : (selectedTab == TAB_WORK_ONLY))))))))) {
                    if (!ListenerUtil.mutListener.listen(26577)) {
                        results.workCount = contactService.countIsWork();
                    }
                    if (!ListenerUtil.mutListener.listen(26585)) {
                        if ((ListenerUtil.mutListener.listen(26583) ? ((ListenerUtil.mutListener.listen(26582) ? (results.workCount >= 0) : (ListenerUtil.mutListener.listen(26581) ? (results.workCount <= 0) : (ListenerUtil.mutListener.listen(26580) ? (results.workCount < 0) : (ListenerUtil.mutListener.listen(26579) ? (results.workCount != 0) : (ListenerUtil.mutListener.listen(26578) ? (results.workCount == 0) : (results.workCount > 0)))))) && forceWork) : ((ListenerUtil.mutListener.listen(26582) ? (results.workCount >= 0) : (ListenerUtil.mutListener.listen(26581) ? (results.workCount <= 0) : (ListenerUtil.mutListener.listen(26580) ? (results.workCount < 0) : (ListenerUtil.mutListener.listen(26579) ? (results.workCount != 0) : (ListenerUtil.mutListener.listen(26578) ? (results.workCount == 0) : (results.workCount > 0)))))) || forceWork))) {
                            if (!ListenerUtil.mutListener.listen(26584)) {
                                allContacts = contactService.getIsWork();
                            }
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(26588)) {
                if (allContacts == null) {
                    if (!ListenerUtil.mutListener.listen(26587)) {
                        allContacts = contactService.getAll();
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(26625)) {
                if (!ConfigUtils.isWorkBuild()) {
                    long now = System.currentTimeMillis();
                    long delta24h = (ListenerUtil.mutListener.listen(26596) ? ((ListenerUtil.mutListener.listen(26592) ? (1000L % 3600) : (ListenerUtil.mutListener.listen(26591) ? (1000L / 3600) : (ListenerUtil.mutListener.listen(26590) ? (1000L - 3600) : (ListenerUtil.mutListener.listen(26589) ? (1000L + 3600) : (1000L * 3600))))) % 24) : (ListenerUtil.mutListener.listen(26595) ? ((ListenerUtil.mutListener.listen(26592) ? (1000L % 3600) : (ListenerUtil.mutListener.listen(26591) ? (1000L / 3600) : (ListenerUtil.mutListener.listen(26590) ? (1000L - 3600) : (ListenerUtil.mutListener.listen(26589) ? (1000L + 3600) : (1000L * 3600))))) / 24) : (ListenerUtil.mutListener.listen(26594) ? ((ListenerUtil.mutListener.listen(26592) ? (1000L % 3600) : (ListenerUtil.mutListener.listen(26591) ? (1000L / 3600) : (ListenerUtil.mutListener.listen(26590) ? (1000L - 3600) : (ListenerUtil.mutListener.listen(26589) ? (1000L + 3600) : (1000L * 3600))))) - 24) : (ListenerUtil.mutListener.listen(26593) ? ((ListenerUtil.mutListener.listen(26592) ? (1000L % 3600) : (ListenerUtil.mutListener.listen(26591) ? (1000L / 3600) : (ListenerUtil.mutListener.listen(26590) ? (1000L - 3600) : (ListenerUtil.mutListener.listen(26589) ? (1000L + 3600) : (1000L * 3600))))) + 24) : ((ListenerUtil.mutListener.listen(26592) ? (1000L % 3600) : (ListenerUtil.mutListener.listen(26591) ? (1000L / 3600) : (ListenerUtil.mutListener.listen(26590) ? (1000L - 3600) : (ListenerUtil.mutListener.listen(26589) ? (1000L + 3600) : (1000L * 3600))))) * 24)))));
                    long delta30d = (ListenerUtil.mutListener.listen(26600) ? (delta24h % 30) : (ListenerUtil.mutListener.listen(26599) ? (delta24h / 30) : (ListenerUtil.mutListener.listen(26598) ? (delta24h - 30) : (ListenerUtil.mutListener.listen(26597) ? (delta24h + 30) : (delta24h * 30)))));
                    if (!ListenerUtil.mutListener.listen(26624)) {
                        {
                            long _loopCounter172 = 0;
                            for (ContactModel contact : allContacts) {
                                ListenerUtil.loopListener.listen("_loopCounter172", ++_loopCounter172);
                                final Date dateCreated = contact.getDateCreated();
                                if (!ListenerUtil.mutListener.listen(26601)) {
                                    if (dateCreated == null) {
                                        continue;
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(26612)) {
                                    if ((ListenerUtil.mutListener.listen(26610) ? ((ListenerUtil.mutListener.listen(26605) ? (now % dateCreated.getTime()) : (ListenerUtil.mutListener.listen(26604) ? (now / dateCreated.getTime()) : (ListenerUtil.mutListener.listen(26603) ? (now * dateCreated.getTime()) : (ListenerUtil.mutListener.listen(26602) ? (now + dateCreated.getTime()) : (now - dateCreated.getTime()))))) >= delta24h) : (ListenerUtil.mutListener.listen(26609) ? ((ListenerUtil.mutListener.listen(26605) ? (now % dateCreated.getTime()) : (ListenerUtil.mutListener.listen(26604) ? (now / dateCreated.getTime()) : (ListenerUtil.mutListener.listen(26603) ? (now * dateCreated.getTime()) : (ListenerUtil.mutListener.listen(26602) ? (now + dateCreated.getTime()) : (now - dateCreated.getTime()))))) <= delta24h) : (ListenerUtil.mutListener.listen(26608) ? ((ListenerUtil.mutListener.listen(26605) ? (now % dateCreated.getTime()) : (ListenerUtil.mutListener.listen(26604) ? (now / dateCreated.getTime()) : (ListenerUtil.mutListener.listen(26603) ? (now * dateCreated.getTime()) : (ListenerUtil.mutListener.listen(26602) ? (now + dateCreated.getTime()) : (now - dateCreated.getTime()))))) > delta24h) : (ListenerUtil.mutListener.listen(26607) ? ((ListenerUtil.mutListener.listen(26605) ? (now % dateCreated.getTime()) : (ListenerUtil.mutListener.listen(26604) ? (now / dateCreated.getTime()) : (ListenerUtil.mutListener.listen(26603) ? (now * dateCreated.getTime()) : (ListenerUtil.mutListener.listen(26602) ? (now + dateCreated.getTime()) : (now - dateCreated.getTime()))))) != delta24h) : (ListenerUtil.mutListener.listen(26606) ? ((ListenerUtil.mutListener.listen(26605) ? (now % dateCreated.getTime()) : (ListenerUtil.mutListener.listen(26604) ? (now / dateCreated.getTime()) : (ListenerUtil.mutListener.listen(26603) ? (now * dateCreated.getTime()) : (ListenerUtil.mutListener.listen(26602) ? (now + dateCreated.getTime()) : (now - dateCreated.getTime()))))) == delta24h) : ((ListenerUtil.mutListener.listen(26605) ? (now % dateCreated.getTime()) : (ListenerUtil.mutListener.listen(26604) ? (now / dateCreated.getTime()) : (ListenerUtil.mutListener.listen(26603) ? (now * dateCreated.getTime()) : (ListenerUtil.mutListener.listen(26602) ? (now + dateCreated.getTime()) : (now - dateCreated.getTime()))))) < delta24h))))))) {
                                        if (!ListenerUtil.mutListener.listen(26611)) {
                                            results.last24h += 1;
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(26623)) {
                                    if ((ListenerUtil.mutListener.listen(26621) ? ((ListenerUtil.mutListener.listen(26616) ? (now % dateCreated.getTime()) : (ListenerUtil.mutListener.listen(26615) ? (now / dateCreated.getTime()) : (ListenerUtil.mutListener.listen(26614) ? (now * dateCreated.getTime()) : (ListenerUtil.mutListener.listen(26613) ? (now + dateCreated.getTime()) : (now - dateCreated.getTime()))))) >= delta30d) : (ListenerUtil.mutListener.listen(26620) ? ((ListenerUtil.mutListener.listen(26616) ? (now % dateCreated.getTime()) : (ListenerUtil.mutListener.listen(26615) ? (now / dateCreated.getTime()) : (ListenerUtil.mutListener.listen(26614) ? (now * dateCreated.getTime()) : (ListenerUtil.mutListener.listen(26613) ? (now + dateCreated.getTime()) : (now - dateCreated.getTime()))))) <= delta30d) : (ListenerUtil.mutListener.listen(26619) ? ((ListenerUtil.mutListener.listen(26616) ? (now % dateCreated.getTime()) : (ListenerUtil.mutListener.listen(26615) ? (now / dateCreated.getTime()) : (ListenerUtil.mutListener.listen(26614) ? (now * dateCreated.getTime()) : (ListenerUtil.mutListener.listen(26613) ? (now + dateCreated.getTime()) : (now - dateCreated.getTime()))))) > delta30d) : (ListenerUtil.mutListener.listen(26618) ? ((ListenerUtil.mutListener.listen(26616) ? (now % dateCreated.getTime()) : (ListenerUtil.mutListener.listen(26615) ? (now / dateCreated.getTime()) : (ListenerUtil.mutListener.listen(26614) ? (now * dateCreated.getTime()) : (ListenerUtil.mutListener.listen(26613) ? (now + dateCreated.getTime()) : (now - dateCreated.getTime()))))) != delta30d) : (ListenerUtil.mutListener.listen(26617) ? ((ListenerUtil.mutListener.listen(26616) ? (now % dateCreated.getTime()) : (ListenerUtil.mutListener.listen(26615) ? (now / dateCreated.getTime()) : (ListenerUtil.mutListener.listen(26614) ? (now * dateCreated.getTime()) : (ListenerUtil.mutListener.listen(26613) ? (now + dateCreated.getTime()) : (now - dateCreated.getTime()))))) == delta30d) : ((ListenerUtil.mutListener.listen(26616) ? (now % dateCreated.getTime()) : (ListenerUtil.mutListener.listen(26615) ? (now / dateCreated.getTime()) : (ListenerUtil.mutListener.listen(26614) ? (now * dateCreated.getTime()) : (ListenerUtil.mutListener.listen(26613) ? (now + dateCreated.getTime()) : (now - dateCreated.getTime()))))) < delta30d))))))) {
                                        if (!ListenerUtil.mutListener.listen(26622)) {
                                            results.last30d += 1;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return new Pair<>(allContacts, results);
        }
    }

    @Override
    public void onResume() {
        if (!ListenerUtil.mutListener.listen(26626)) {
            logger.debug("*** onResume");
        }
        if (!ListenerUtil.mutListener.listen(26628)) {
            if (this.resumePauseHandler != null) {
                if (!ListenerUtil.mutListener.listen(26627)) {
                    this.resumePauseHandler.onResume();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(26632)) {
            if (this.swipeRefreshLayout != null) {
                if (!ListenerUtil.mutListener.listen(26630)) {
                    this.swipeRefreshLayout.setEnabled((ListenerUtil.mutListener.listen(26629) ? (this.listView != null || this.listView.getFirstVisiblePosition() == 0) : (this.listView != null && this.listView.getFirstVisiblePosition() == 0)));
                }
                if (!ListenerUtil.mutListener.listen(26631)) {
                    stopSwipeRefresh();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(26633)) {
            super.onResume();
        }
    }

    @Override
    public void onPause() {
        if (!ListenerUtil.mutListener.listen(26634)) {
            super.onPause();
        }
        if (!ListenerUtil.mutListener.listen(26635)) {
            logger.debug("*** onPause");
        }
        if (!ListenerUtil.mutListener.listen(26637)) {
            if (this.resumePauseHandler != null) {
                if (!ListenerUtil.mutListener.listen(26636)) {
                    this.resumePauseHandler.onPause();
                }
            }
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(26638)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(26639)) {
            logger.debug("*** onCreate");
        }
        if (!ListenerUtil.mutListener.listen(26640)) {
            setRetainInstance(true);
        }
        if (!ListenerUtil.mutListener.listen(26641)) {
            setHasOptionsMenu(true);
        }
        if (!ListenerUtil.mutListener.listen(26642)) {
            setupListeners();
        }
        if (!ListenerUtil.mutListener.listen(26643)) {
            this.resumePauseHandler = ResumePauseHandler.getByActivity(this, this.getActivity());
        }
        if (!ListenerUtil.mutListener.listen(26645)) {
            if (this.resumePauseHandler != null) {
                if (!ListenerUtil.mutListener.listen(26644)) {
                    this.resumePauseHandler.runOnActive(RUN_ON_ACTIVE_REFRESH_PULL_TO_REFRESH, runIfActiveUpdatePullToRefresh);
                }
            }
        }
    }

    @Override
    public void onAttach(@NonNull Activity activity) {
        if (!ListenerUtil.mutListener.listen(26646)) {
            super.onAttach(activity);
        }
        if (!ListenerUtil.mutListener.listen(26647)) {
            logger.debug("*** onAttach");
        }
    }

    @Override
    public void onDestroy() {
        if (!ListenerUtil.mutListener.listen(26648)) {
            logger.debug("*** onDestroy");
        }
        if (!ListenerUtil.mutListener.listen(26649)) {
            removeListeners();
        }
        if (!ListenerUtil.mutListener.listen(26651)) {
            if (this.resumePauseHandler != null) {
                if (!ListenerUtil.mutListener.listen(26650)) {
                    this.resumePauseHandler.onDestroy(this);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(26652)) {
            super.onDestroy();
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!ListenerUtil.mutListener.listen(26653)) {
            logger.debug("*** onHiddenChanged: " + hidden);
        }
        if (!ListenerUtil.mutListener.listen(26664)) {
            if (hidden) {
                if (!ListenerUtil.mutListener.listen(26657)) {
                    if (actionMode != null) {
                        if (!ListenerUtil.mutListener.listen(26656)) {
                            actionMode.finish();
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(26661)) {
                    if ((ListenerUtil.mutListener.listen(26659) ? ((ListenerUtil.mutListener.listen(26658) ? (this.searchView != null || this.searchView.isShown()) : (this.searchView != null && this.searchView.isShown())) || this.searchMenuItem != null) : ((ListenerUtil.mutListener.listen(26658) ? (this.searchView != null || this.searchView.isShown()) : (this.searchView != null && this.searchView.isShown())) && this.searchMenuItem != null))) {
                        if (!ListenerUtil.mutListener.listen(26660)) {
                            this.searchMenuItem.collapseActionView();
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(26663)) {
                    if (this.resumePauseHandler != null) {
                        if (!ListenerUtil.mutListener.listen(26662)) {
                            this.resumePauseHandler.onPause();
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(26655)) {
                    if (this.resumePauseHandler != null) {
                        if (!ListenerUtil.mutListener.listen(26654)) {
                            this.resumePauseHandler.onResume();
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        if (!ListenerUtil.mutListener.listen(26665)) {
            super.onPrepareOptionsMenu(menu);
        }
        if (!ListenerUtil.mutListener.listen(26668)) {
            // move search item to popup if the lock item is visible
            if (lockAppService.isLockingEnabled()) {
                if (!ListenerUtil.mutListener.listen(26667)) {
                    this.searchMenuItem.setShowAsAction(SHOW_AS_ACTION_NEVER | SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(26666)) {
                    this.searchMenuItem.setShowAsAction(SHOW_AS_ACTION_ALWAYS | SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
                }
            }
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (!ListenerUtil.mutListener.listen(26669)) {
            logger.debug("*** onCreateOptionsMenu");
        }
        if (!ListenerUtil.mutListener.listen(26670)) {
            searchMenuItem = menu.findItem(R.id.menu_search_contacts);
        }
        if (!ListenerUtil.mutListener.listen(26684)) {
            if (searchMenuItem == null) {
                if (!ListenerUtil.mutListener.listen(26671)) {
                    inflater.inflate(R.menu.fragment_contacts, menu);
                }
                if (!ListenerUtil.mutListener.listen(26683)) {
                    if ((ListenerUtil.mutListener.listen(26672) ? (getActivity() != null || this.isAdded()) : (getActivity() != null && this.isAdded()))) {
                        if (!ListenerUtil.mutListener.listen(26673)) {
                            this.searchMenuItem = menu.findItem(R.id.menu_search_contacts);
                        }
                        if (!ListenerUtil.mutListener.listen(26674)) {
                            this.searchView = (SearchView) searchMenuItem.getActionView();
                        }
                        if (!ListenerUtil.mutListener.listen(26682)) {
                            if (this.searchView != null) {
                                if (!ListenerUtil.mutListener.listen(26679)) {
                                    if (!TestUtil.empty(filterQuery)) {
                                        if (!ListenerUtil.mutListener.listen(26675)) {
                                            // restore filter
                                            MenuItemCompat.expandActionView(searchMenuItem);
                                        }
                                        if (!ListenerUtil.mutListener.listen(26678)) {
                                            this.searchView.post(new Runnable() {

                                                @Override
                                                public void run() {
                                                    if (!ListenerUtil.mutListener.listen(26676)) {
                                                        searchView.setQuery(filterQuery, true);
                                                    }
                                                    if (!ListenerUtil.mutListener.listen(26677)) {
                                                        searchView.clearFocus();
                                                    }
                                                }
                                            });
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(26680)) {
                                    this.searchView.setQueryHint(getString(R.string.hint_filter_list));
                                }
                                if (!ListenerUtil.mutListener.listen(26681)) {
                                    this.searchView.setOnQueryTextListener(queryTextListener);
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(26685)) {
            super.onCreateOptionsMenu(menu, inflater);
        }
    }

    final SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {

        @Override
        public boolean onQueryTextChange(String query) {
            if (!ListenerUtil.mutListener.listen(26689)) {
                if ((ListenerUtil.mutListener.listen(26686) ? (contactListAdapter != null || contactListAdapter.getFilter() != null) : (contactListAdapter != null && contactListAdapter.getFilter() != null))) {
                    if (!ListenerUtil.mutListener.listen(26687)) {
                        filterQuery = query;
                    }
                    if (!ListenerUtil.mutListener.listen(26688)) {
                        contactListAdapter.getFilter().filter(query);
                    }
                }
            }
            return true;
        }

        @Override
        public boolean onQueryTextSubmit(String query) {
            return true;
        }
    };

    private int getDesiredWorkTab(boolean isOnFirstLaunch, Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(26692)) {
            if (ConfigUtils.isWorkBuild()) {
                if (!ListenerUtil.mutListener.listen(26691)) {
                    if (isOnFirstLaunch) {
                        // may be overridden later if there are no work contacts
                        return TAB_WORK_ONLY;
                    } else {
                        if (!ListenerUtil.mutListener.listen(26690)) {
                            if (savedInstanceState != null) {
                                return savedInstanceState.getInt(BUNDLE_SELECTED_TAB, TAB_ALL_CONTACTS);
                            } else if (workTabLayout != null) {
                                return workTabLayout.getSelectedTabPosition();
                            }
                        }
                    }
                }
            }
        }
        return TAB_ALL_CONTACTS;
    }

    @SuppressLint("StaticFieldLeak")
    protected void createListAdapter(final Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(26693)) {
            if (getActivity() == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(26694)) {
            if (!this.requiredInstances()) {
                return;
            }
        }
        final int[] desiredTabPosition = { getDesiredWorkTab(savedInstanceState == null, savedInstanceState) };
        if (!ListenerUtil.mutListener.listen(26717)) {
            new FetchContactsTask(contactService, savedInstanceState == null, desiredTabPosition[0], false) {

                @Override
                protected void onPostExecute(Pair<List<ContactModel>, FetchResults> result) {
                    final List<ContactModel> contactModels = result.first;
                    final FetchResults counts = result.second;
                    if (!ListenerUtil.mutListener.listen(26716)) {
                        if (contactModels != null) {
                            if (!ListenerUtil.mutListener.listen(26695)) {
                                updateContactsCounter(contactModels.size(), counts);
                            }
                            if (!ListenerUtil.mutListener.listen(26702)) {
                                if ((ListenerUtil.mutListener.listen(26700) ? (contactModels.size() >= 0) : (ListenerUtil.mutListener.listen(26699) ? (contactModels.size() <= 0) : (ListenerUtil.mutListener.listen(26698) ? (contactModels.size() < 0) : (ListenerUtil.mutListener.listen(26697) ? (contactModels.size() != 0) : (ListenerUtil.mutListener.listen(26696) ? (contactModels.size() == 0) : (contactModels.size() > 0))))))) {
                                    if (!ListenerUtil.mutListener.listen(26701)) {
                                        ((EmptyView) listView.getEmptyView()).setup(R.string.no_matching_contacts);
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(26706)) {
                                if ((ListenerUtil.mutListener.listen(26703) ? (isAdded() || getContext() != null) : (isAdded() && getContext() != null))) {
                                    if (!ListenerUtil.mutListener.listen(26704)) {
                                        contactListAdapter = new ContactListAdapter(getContext(), contactModels, contactService, serviceManager.getPreferenceService(), serviceManager.getBlackListService(), ContactsSectionFragment.this);
                                    }
                                    if (!ListenerUtil.mutListener.listen(26705)) {
                                        listView.setAdapter(contactListAdapter);
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(26715)) {
                                if (ConfigUtils.isWorkBuild()) {
                                    if (!ListenerUtil.mutListener.listen(26710)) {
                                        if ((ListenerUtil.mutListener.listen(26708) ? ((ListenerUtil.mutListener.listen(26707) ? (savedInstanceState == null || desiredTabPosition[0] == TAB_WORK_ONLY) : (savedInstanceState == null && desiredTabPosition[0] == TAB_WORK_ONLY)) || counts.workCount == 0) : ((ListenerUtil.mutListener.listen(26707) ? (savedInstanceState == null || desiredTabPosition[0] == TAB_WORK_ONLY) : (savedInstanceState == null && desiredTabPosition[0] == TAB_WORK_ONLY)) && counts.workCount == 0))) {
                                            if (!ListenerUtil.mutListener.listen(26709)) {
                                                // fix selected tab as there is now work contact
                                                desiredTabPosition[0] = TAB_ALL_CONTACTS;
                                            }
                                        }
                                    }
                                    if (!ListenerUtil.mutListener.listen(26714)) {
                                        if (desiredTabPosition[0] != workTabLayout.getSelectedTabPosition()) {
                                            if (!ListenerUtil.mutListener.listen(26711)) {
                                                workTabLayout.removeOnTabSelectedListener(onTabSelectedListener);
                                            }
                                            if (!ListenerUtil.mutListener.listen(26712)) {
                                                workTabLayout.selectTab(workTabLayout.getTabAt(selectedTab));
                                            }
                                            if (!ListenerUtil.mutListener.listen(26713)) {
                                                workTabLayout.addOnTabSelectedListener(onTabSelectedListener);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR);
        }
    }

    @SuppressLint("StaticFieldLeak")
    private void updateList() {
        if (!ListenerUtil.mutListener.listen(26719)) {
            if (!this.requiredInstances()) {
                if (!ListenerUtil.mutListener.listen(26718)) {
                    logger.error("could not instantiate required objects");
                }
                return;
            }
        }
        int desiredTab = getDesiredWorkTab(false, null);
        if (!ListenerUtil.mutListener.listen(26726)) {
            if (contactListAdapter != null) {
                if (!ListenerUtil.mutListener.listen(26725)) {
                    new FetchContactsTask(contactService, false, desiredTab, false) {

                        @Override
                        protected void onPostExecute(Pair<List<ContactModel>, FetchResults> result) {
                            final List<ContactModel> contactModels = result.first;
                            final FetchResults counts = result.second;
                            if (!ListenerUtil.mutListener.listen(26724)) {
                                if ((ListenerUtil.mutListener.listen(26721) ? ((ListenerUtil.mutListener.listen(26720) ? (contactModels != null || contactListAdapter != null) : (contactModels != null && contactListAdapter != null)) || isAdded()) : ((ListenerUtil.mutListener.listen(26720) ? (contactModels != null || contactListAdapter != null) : (contactModels != null && contactListAdapter != null)) && isAdded()))) {
                                    if (!ListenerUtil.mutListener.listen(26722)) {
                                        updateContactsCounter(contactModels.size(), counts);
                                    }
                                    if (!ListenerUtil.mutListener.listen(26723)) {
                                        contactListAdapter.updateData(contactModels);
                                    }
                                }
                            }
                        }
                    }.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR);
                }
            }
        }
    }

    private void updateContactsCounter(int numContacts, @Nullable FetchResults counts) {
        if (!ListenerUtil.mutListener.listen(26744)) {
            if ((ListenerUtil.mutListener.listen(26728) ? ((ListenerUtil.mutListener.listen(26727) ? (getActivity() != null || listView != null) : (getActivity() != null && listView != null)) || isAdded()) : ((ListenerUtil.mutListener.listen(26727) ? (getActivity() != null || listView != null) : (getActivity() != null && listView != null)) && isAdded()))) {
                if (!ListenerUtil.mutListener.listen(26743)) {
                    if (contactsCounterChip != null) {
                        if (!ListenerUtil.mutListener.listen(26730)) {
                            if (counts != null) {
                                if (!ListenerUtil.mutListener.listen(26729)) {
                                    ListenerManager.contactCountListener.handle(listener -> listener.onNewContactsCountUpdated(counts.last24h));
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(26742)) {
                            if ((ListenerUtil.mutListener.listen(26735) ? (numContacts >= 1) : (ListenerUtil.mutListener.listen(26734) ? (numContacts <= 1) : (ListenerUtil.mutListener.listen(26733) ? (numContacts < 1) : (ListenerUtil.mutListener.listen(26732) ? (numContacts != 1) : (ListenerUtil.mutListener.listen(26731) ? (numContacts == 1) : (numContacts > 1))))))) {
                                final StringBuilder builder = new StringBuilder();
                                if (!ListenerUtil.mutListener.listen(26737)) {
                                    builder.append(numContacts).append(" ").append(getString(R.string.title_section2));
                                }
                                if (!ListenerUtil.mutListener.listen(26739)) {
                                    if (counts != null) {
                                        if (!ListenerUtil.mutListener.listen(26738)) {
                                            builder.append(" (+").append(counts.last30d).append(" / ").append(getString(R.string.thirty_days_abbrev)).append(")");
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(26740)) {
                                    contactsCounterChip.setText(builder.toString());
                                }
                                if (!ListenerUtil.mutListener.listen(26741)) {
                                    contactsCounterChip.setVisibility(View.VISIBLE);
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(26736)) {
                                    contactsCounterChip.setVisibility(View.GONE);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    protected final boolean requiredInstances() {
        if (!ListenerUtil.mutListener.listen(26746)) {
            if (!this.checkInstances()) {
                if (!ListenerUtil.mutListener.listen(26745)) {
                    this.instantiate();
                }
            }
        }
        return this.checkInstances();
    }

    protected boolean checkInstances() {
        return TestUtil.required(this.serviceManager, this.contactListener, this.preferenceService, this.synchronizeContactsService, this.lockAppService);
    }

    protected void instantiate() {
        if (!ListenerUtil.mutListener.listen(26747)) {
            this.serviceManager = ThreemaApplication.getServiceManager();
        }
        if (!ListenerUtil.mutListener.listen(26754)) {
            if (this.serviceManager != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(26750)) {
                        this.contactService = this.serviceManager.getContactService();
                    }
                    if (!ListenerUtil.mutListener.listen(26751)) {
                        this.preferenceService = this.serviceManager.getPreferenceService();
                    }
                    if (!ListenerUtil.mutListener.listen(26752)) {
                        this.synchronizeContactsService = this.serviceManager.getSynchronizeContactsService();
                    }
                    if (!ListenerUtil.mutListener.listen(26753)) {
                        this.lockAppService = this.serviceManager.getLockAppService();
                    }
                } catch (MasterKeyLockedException e) {
                    if (!ListenerUtil.mutListener.listen(26748)) {
                        logger.debug("Master Key locked!");
                    }
                } catch (ThreemaException e) {
                    if (!ListenerUtil.mutListener.listen(26749)) {
                        logger.error("Exception", e);
                    }
                }
            }
        }
    }

    private void onFABClicked(View v) {
        Intent intent = new Intent(getActivity(), AddContactActivity.class);
        if (!ListenerUtil.mutListener.listen(26755)) {
            intent.putExtra(AddContactActivity.EXTRA_ADD_BY_ID, true);
        }
        if (!ListenerUtil.mutListener.listen(26756)) {
            startActivity(intent);
        }
        if (!ListenerUtil.mutListener.listen(26757)) {
            getActivity().overridePendingTransition(R.anim.fast_fade_in, R.anim.fast_fade_out);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View headerView, fragmentView = getView();
        if (!ListenerUtil.mutListener.listen(26758)) {
            logger.debug("*** onCreateView");
        }
        if (fragmentView == null) {
            if (!ListenerUtil.mutListener.listen(26759)) {
                fragmentView = inflater.inflate(R.layout.fragment_contacts, container, false);
            }
            if (!ListenerUtil.mutListener.listen(26761)) {
                if (!this.requiredInstances()) {
                    if (!ListenerUtil.mutListener.listen(26760)) {
                        logger.error("could not instantiate required objects");
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(26762)) {
                listView = fragmentView.findViewById(android.R.id.list);
            }
            if (!ListenerUtil.mutListener.listen(26763)) {
                listView.setOnItemClickListener(this);
            }
            if (!ListenerUtil.mutListener.listen(26764)) {
                listView.setDividerHeight(0);
            }
            if (!ListenerUtil.mutListener.listen(26765)) {
                listView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE_MODAL);
            }
            if (!ListenerUtil.mutListener.listen(26789)) {
                listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

                    MenuItem shareItem;

                    @Override
                    public void onItemCheckedStateChanged(android.view.ActionMode mode, int position, long id, boolean checked) {
                        if (!ListenerUtil.mutListener.listen(26779)) {
                            if (shareItem != null) {
                                final int count = listView.getCheckedItemCount();
                                if (!ListenerUtil.mutListener.listen(26778)) {
                                    if ((ListenerUtil.mutListener.listen(26770) ? (count >= 0) : (ListenerUtil.mutListener.listen(26769) ? (count <= 0) : (ListenerUtil.mutListener.listen(26768) ? (count < 0) : (ListenerUtil.mutListener.listen(26767) ? (count != 0) : (ListenerUtil.mutListener.listen(26766) ? (count == 0) : (count > 0))))))) {
                                        if (!ListenerUtil.mutListener.listen(26771)) {
                                            mode.setTitle(Integer.toString(count));
                                        }
                                        if (!ListenerUtil.mutListener.listen(26777)) {
                                            shareItem.setVisible((ListenerUtil.mutListener.listen(26776) ? (count >= 1) : (ListenerUtil.mutListener.listen(26775) ? (count <= 1) : (ListenerUtil.mutListener.listen(26774) ? (count > 1) : (ListenerUtil.mutListener.listen(26773) ? (count < 1) : (ListenerUtil.mutListener.listen(26772) ? (count != 1) : (count == 1)))))));
                                        }
                                    }
                                }
                            }
                        }
                    }

                    @Override
                    public boolean onCreateActionMode(android.view.ActionMode mode, Menu menu) {
                        if (!ListenerUtil.mutListener.listen(26780)) {
                            mode.getMenuInflater().inflate(R.menu.action_contacts_section, menu);
                        }
                        if (!ListenerUtil.mutListener.listen(26781)) {
                            actionMode = mode;
                        }
                        if (!ListenerUtil.mutListener.listen(26782)) {
                            ConfigUtils.themeMenu(menu, ConfigUtils.getColorFromAttribute(getContext(), R.attr.colorAccent));
                        }
                        return true;
                    }

                    @Override
                    public boolean onPrepareActionMode(android.view.ActionMode mode, Menu menu) {
                        if (!ListenerUtil.mutListener.listen(26783)) {
                            shareItem = menu.findItem(R.id.menu_contacts_share);
                        }
                        if (!ListenerUtil.mutListener.listen(26784)) {
                            mode.setTitle(Integer.toString(listView.getCheckedItemCount()));
                        }
                        return true;
                    }

                    @Override
                    public boolean onActionItemClicked(android.view.ActionMode mode, MenuItem item) {
                        switch(item.getItemId()) {
                            case R.id.menu_contacts_remove:
                                if (!ListenerUtil.mutListener.listen(26785)) {
                                    deleteSelectedContacts();
                                }
                                return true;
                            case R.id.menu_contacts_share:
                                HashSet<ContactModel> contactModels = contactListAdapter.getCheckedItems();
                                if (!ListenerUtil.mutListener.listen(26787)) {
                                    if (contactModels.size() == 1) {
                                        if (!ListenerUtil.mutListener.listen(26786)) {
                                            ShareUtil.shareContact(getActivity(), contactModels.iterator().next());
                                        }
                                    }
                                }
                                return true;
                            default:
                                return false;
                        }
                    }

                    @Override
                    public void onDestroyActionMode(android.view.ActionMode mode) {
                        if (!ListenerUtil.mutListener.listen(26788)) {
                            actionMode = null;
                        }
                    }
                });
            }
            if (!ConfigUtils.isWorkBuild()) {
                headerView = View.inflate(getActivity(), R.layout.header_contact_section, null);
                if (!ListenerUtil.mutListener.listen(26793)) {
                    listView.addHeaderView(headerView, null, false);
                }
                View footerView = View.inflate(getActivity(), R.layout.footer_contact_section, null);
                if (!ListenerUtil.mutListener.listen(26794)) {
                    this.contactsCounterChip = footerView.findViewById(R.id.contact_counter_text);
                }
                if (!ListenerUtil.mutListener.listen(26795)) {
                    listView.addFooterView(footerView, null, false);
                }
                if (!ListenerUtil.mutListener.listen(26797)) {
                    headerView.findViewById(R.id.share_container).setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            if (!ListenerUtil.mutListener.listen(26796)) {
                                shareInvite();
                            }
                        }
                    });
                }
            } else {
                headerView = View.inflate(getActivity(), R.layout.header_contact_section_work, null);
                if (!ListenerUtil.mutListener.listen(26790)) {
                    listView.addHeaderView(headerView, null, false);
                }
                if (!ListenerUtil.mutListener.listen(26791)) {
                    workTabLayout = ((TabLayout) headerView.findViewById(R.id.tab_layout));
                }
                if (!ListenerUtil.mutListener.listen(26792)) {
                    workTabLayout.addOnTabSelectedListener(onTabSelectedListener);
                }
            }
            if (!ListenerUtil.mutListener.listen(26798)) {
                this.swipeRefreshLayout = fragmentView.findViewById(R.id.swipe_container);
            }
            if (!ListenerUtil.mutListener.listen(26799)) {
                this.swipeRefreshLayout.setOnRefreshListener(this);
            }
            if (!ListenerUtil.mutListener.listen(26804)) {
                this.swipeRefreshLayout.setDistanceToTriggerSync((ListenerUtil.mutListener.listen(26803) ? (getResources().getConfiguration().screenHeightDp % 3) : (ListenerUtil.mutListener.listen(26802) ? (getResources().getConfiguration().screenHeightDp * 3) : (ListenerUtil.mutListener.listen(26801) ? (getResources().getConfiguration().screenHeightDp - 3) : (ListenerUtil.mutListener.listen(26800) ? (getResources().getConfiguration().screenHeightDp + 3) : (getResources().getConfiguration().screenHeightDp / 3))))));
            }
            if (!ListenerUtil.mutListener.listen(26805)) {
                this.swipeRefreshLayout.setColorSchemeResources(R.color.accent_light);
            }
            if (!ListenerUtil.mutListener.listen(26806)) {
                this.swipeRefreshLayout.setSize(SwipeRefreshLayout.LARGE);
            }
            if (!ListenerUtil.mutListener.listen(26807)) {
                this.floatingButtonView = fragmentView.findViewById(R.id.floating);
            }
            if (!ListenerUtil.mutListener.listen(26809)) {
                this.floatingButtonView.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        if (!ListenerUtil.mutListener.listen(26808)) {
                            onFABClicked(v);
                        }
                    }
                });
            }
            if (!ListenerUtil.mutListener.listen(26810)) {
                this.stickyInitialView = fragmentView.findViewById(R.id.initial_sticky);
            }
            if (!ListenerUtil.mutListener.listen(26811)) {
                this.stickyInitialLayout = fragmentView.findViewById(R.id.initial_sticky_layout);
            }
            if (!ListenerUtil.mutListener.listen(26812)) {
                this.stickyInitialLayout.setVisibility(View.GONE);
            }
        }
        return fragmentView;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(26813)) {
            super.onViewCreated(view, savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(26814)) {
            logger.debug("*** onViewCreated");
        }
        if (!ListenerUtil.mutListener.listen(26914)) {
            if ((ListenerUtil.mutListener.listen(26815) ? (getActivity() != null || listView != null) : (getActivity() != null && listView != null))) {
                // add text view if contact list is empty
                EmptyView emptyView = new EmptyView(getActivity());
                if (!ListenerUtil.mutListener.listen(26816)) {
                    emptyView.setup(R.string.no_contacts);
                }
                if (!ListenerUtil.mutListener.listen(26817)) {
                    ((ViewGroup) listView.getParent()).addView(emptyView);
                }
                if (!ListenerUtil.mutListener.listen(26818)) {
                    listView.setEmptyView(emptyView);
                }
                if (!ListenerUtil.mutListener.listen(26913)) {
                    listView.setOnScrollListener(new AbsListView.OnScrollListener() {

                        private int previousFirstVisibleItem = -1;

                        @Override
                        public void onScrollStateChanged(AbsListView view, int scrollState) {
                        }

                        @Override
                        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                            if (!ListenerUtil.mutListener.listen(26834)) {
                                if (swipeRefreshLayout != null) {
                                    if (!ListenerUtil.mutListener.listen(26833)) {
                                        if ((ListenerUtil.mutListener.listen(26824) ? (view != null || (ListenerUtil.mutListener.listen(26823) ? (view.getChildCount() >= 0) : (ListenerUtil.mutListener.listen(26822) ? (view.getChildCount() <= 0) : (ListenerUtil.mutListener.listen(26821) ? (view.getChildCount() < 0) : (ListenerUtil.mutListener.listen(26820) ? (view.getChildCount() != 0) : (ListenerUtil.mutListener.listen(26819) ? (view.getChildCount() == 0) : (view.getChildCount() > 0))))))) : (view != null && (ListenerUtil.mutListener.listen(26823) ? (view.getChildCount() >= 0) : (ListenerUtil.mutListener.listen(26822) ? (view.getChildCount() <= 0) : (ListenerUtil.mutListener.listen(26821) ? (view.getChildCount() < 0) : (ListenerUtil.mutListener.listen(26820) ? (view.getChildCount() != 0) : (ListenerUtil.mutListener.listen(26819) ? (view.getChildCount() == 0) : (view.getChildCount() > 0))))))))) {
                                            if (!ListenerUtil.mutListener.listen(26832)) {
                                                swipeRefreshLayout.setEnabled((ListenerUtil.mutListener.listen(26831) ? ((ListenerUtil.mutListener.listen(26830) ? (firstVisibleItem >= 0) : (ListenerUtil.mutListener.listen(26829) ? (firstVisibleItem <= 0) : (ListenerUtil.mutListener.listen(26828) ? (firstVisibleItem > 0) : (ListenerUtil.mutListener.listen(26827) ? (firstVisibleItem < 0) : (ListenerUtil.mutListener.listen(26826) ? (firstVisibleItem != 0) : (firstVisibleItem == 0)))))) || view.getChildAt(0).getTop() == 0) : ((ListenerUtil.mutListener.listen(26830) ? (firstVisibleItem >= 0) : (ListenerUtil.mutListener.listen(26829) ? (firstVisibleItem <= 0) : (ListenerUtil.mutListener.listen(26828) ? (firstVisibleItem > 0) : (ListenerUtil.mutListener.listen(26827) ? (firstVisibleItem < 0) : (ListenerUtil.mutListener.listen(26826) ? (firstVisibleItem != 0) : (firstVisibleItem == 0)))))) && view.getChildAt(0).getTop() == 0)));
                                            }
                                        } else {
                                            if (!ListenerUtil.mutListener.listen(26825)) {
                                                swipeRefreshLayout.setEnabled(false);
                                            }
                                        }
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(26912)) {
                                if (view != null) {
                                    if (!ListenerUtil.mutListener.listen(26911)) {
                                        if (contactListAdapter != null) {
                                            int direction = 0;
                                            if (!ListenerUtil.mutListener.listen(26843)) {
                                                if (floatingButtonView != null) {
                                                    if (!ListenerUtil.mutListener.listen(26842)) {
                                                        if ((ListenerUtil.mutListener.listen(26839) ? (firstVisibleItem >= 0) : (ListenerUtil.mutListener.listen(26838) ? (firstVisibleItem <= 0) : (ListenerUtil.mutListener.listen(26837) ? (firstVisibleItem > 0) : (ListenerUtil.mutListener.listen(26836) ? (firstVisibleItem < 0) : (ListenerUtil.mutListener.listen(26835) ? (firstVisibleItem != 0) : (firstVisibleItem == 0))))))) {
                                                            if (!ListenerUtil.mutListener.listen(26841)) {
                                                                floatingButtonView.extend();
                                                            }
                                                        } else {
                                                            if (!ListenerUtil.mutListener.listen(26840)) {
                                                                floatingButtonView.shrink();
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                            int headerCount = listView.getHeaderViewsCount();
                                            if (!ListenerUtil.mutListener.listen(26844)) {
                                                firstVisibleItem -= headerCount;
                                            }
                                            if (!ListenerUtil.mutListener.listen(26909)) {
                                                if (firstVisibleItem != previousFirstVisibleItem) {
                                                    if (!ListenerUtil.mutListener.listen(26908)) {
                                                        if ((ListenerUtil.mutListener.listen(26850) ? (previousFirstVisibleItem != -1 || (ListenerUtil.mutListener.listen(26849) ? (firstVisibleItem >= -1) : (ListenerUtil.mutListener.listen(26848) ? (firstVisibleItem <= -1) : (ListenerUtil.mutListener.listen(26847) ? (firstVisibleItem > -1) : (ListenerUtil.mutListener.listen(26846) ? (firstVisibleItem < -1) : (ListenerUtil.mutListener.listen(26845) ? (firstVisibleItem == -1) : (firstVisibleItem != -1))))))) : (previousFirstVisibleItem != -1 && (ListenerUtil.mutListener.listen(26849) ? (firstVisibleItem >= -1) : (ListenerUtil.mutListener.listen(26848) ? (firstVisibleItem <= -1) : (ListenerUtil.mutListener.listen(26847) ? (firstVisibleItem > -1) : (ListenerUtil.mutListener.listen(26846) ? (firstVisibleItem < -1) : (ListenerUtil.mutListener.listen(26845) ? (firstVisibleItem == -1) : (firstVisibleItem != -1))))))))) {
                                                            if (!ListenerUtil.mutListener.listen(26858)) {
                                                                if ((ListenerUtil.mutListener.listen(26856) ? (previousFirstVisibleItem >= firstVisibleItem) : (ListenerUtil.mutListener.listen(26855) ? (previousFirstVisibleItem <= firstVisibleItem) : (ListenerUtil.mutListener.listen(26854) ? (previousFirstVisibleItem > firstVisibleItem) : (ListenerUtil.mutListener.listen(26853) ? (previousFirstVisibleItem != firstVisibleItem) : (ListenerUtil.mutListener.listen(26852) ? (previousFirstVisibleItem == firstVisibleItem) : (previousFirstVisibleItem < firstVisibleItem))))))) {
                                                                    if (!ListenerUtil.mutListener.listen(26857)) {
                                                                        // Scroll Down
                                                                        direction = 1;
                                                                    }
                                                                }
                                                            }
                                                            if (!ListenerUtil.mutListener.listen(26865)) {
                                                                if ((ListenerUtil.mutListener.listen(26863) ? (previousFirstVisibleItem >= firstVisibleItem) : (ListenerUtil.mutListener.listen(26862) ? (previousFirstVisibleItem <= firstVisibleItem) : (ListenerUtil.mutListener.listen(26861) ? (previousFirstVisibleItem < firstVisibleItem) : (ListenerUtil.mutListener.listen(26860) ? (previousFirstVisibleItem != firstVisibleItem) : (ListenerUtil.mutListener.listen(26859) ? (previousFirstVisibleItem == firstVisibleItem) : (previousFirstVisibleItem > firstVisibleItem))))))) {
                                                                    if (!ListenerUtil.mutListener.listen(26864)) {
                                                                        // Scroll Up
                                                                        direction = -1;
                                                                    }
                                                                }
                                                            }
                                                            if (!ListenerUtil.mutListener.listen(26866)) {
                                                                stickyInitialView.setText(contactListAdapter.getInitial(firstVisibleItem));
                                                            }
                                                            String currentInitial = contactListAdapter.getInitial(firstVisibleItem);
                                                            String previousInitial = contactListAdapter.getInitial(previousFirstVisibleItem);
                                                            String nextInitial = "";
                                                            if (!ListenerUtil.mutListener.listen(26899)) {
                                                                if ((ListenerUtil.mutListener.listen(26877) ? ((ListenerUtil.mutListener.listen(26871) ? (direction >= 1) : (ListenerUtil.mutListener.listen(26870) ? (direction <= 1) : (ListenerUtil.mutListener.listen(26869) ? (direction > 1) : (ListenerUtil.mutListener.listen(26868) ? (direction < 1) : (ListenerUtil.mutListener.listen(26867) ? (direction != 1) : (direction == 1)))))) || (ListenerUtil.mutListener.listen(26876) ? (firstVisibleItem >= contactListAdapter.getCount()) : (ListenerUtil.mutListener.listen(26875) ? (firstVisibleItem <= contactListAdapter.getCount()) : (ListenerUtil.mutListener.listen(26874) ? (firstVisibleItem > contactListAdapter.getCount()) : (ListenerUtil.mutListener.listen(26873) ? (firstVisibleItem != contactListAdapter.getCount()) : (ListenerUtil.mutListener.listen(26872) ? (firstVisibleItem == contactListAdapter.getCount()) : (firstVisibleItem < contactListAdapter.getCount()))))))) : ((ListenerUtil.mutListener.listen(26871) ? (direction >= 1) : (ListenerUtil.mutListener.listen(26870) ? (direction <= 1) : (ListenerUtil.mutListener.listen(26869) ? (direction > 1) : (ListenerUtil.mutListener.listen(26868) ? (direction < 1) : (ListenerUtil.mutListener.listen(26867) ? (direction != 1) : (direction == 1)))))) && (ListenerUtil.mutListener.listen(26876) ? (firstVisibleItem >= contactListAdapter.getCount()) : (ListenerUtil.mutListener.listen(26875) ? (firstVisibleItem <= contactListAdapter.getCount()) : (ListenerUtil.mutListener.listen(26874) ? (firstVisibleItem > contactListAdapter.getCount()) : (ListenerUtil.mutListener.listen(26873) ? (firstVisibleItem != contactListAdapter.getCount()) : (ListenerUtil.mutListener.listen(26872) ? (firstVisibleItem == contactListAdapter.getCount()) : (firstVisibleItem < contactListAdapter.getCount()))))))))) {
                                                                    if (!ListenerUtil.mutListener.listen(26898)) {
                                                                        nextInitial = contactListAdapter.getInitial((ListenerUtil.mutListener.listen(26897) ? (firstVisibleItem % 1) : (ListenerUtil.mutListener.listen(26896) ? (firstVisibleItem / 1) : (ListenerUtil.mutListener.listen(26895) ? (firstVisibleItem * 1) : (ListenerUtil.mutListener.listen(26894) ? (firstVisibleItem - 1) : (firstVisibleItem + 1))))));
                                                                    }
                                                                } else if ((ListenerUtil.mutListener.listen(26888) ? ((ListenerUtil.mutListener.listen(26882) ? (direction >= -1) : (ListenerUtil.mutListener.listen(26881) ? (direction <= -1) : (ListenerUtil.mutListener.listen(26880) ? (direction > -1) : (ListenerUtil.mutListener.listen(26879) ? (direction < -1) : (ListenerUtil.mutListener.listen(26878) ? (direction != -1) : (direction == -1)))))) || (ListenerUtil.mutListener.listen(26887) ? (firstVisibleItem >= 0) : (ListenerUtil.mutListener.listen(26886) ? (firstVisibleItem <= 0) : (ListenerUtil.mutListener.listen(26885) ? (firstVisibleItem < 0) : (ListenerUtil.mutListener.listen(26884) ? (firstVisibleItem != 0) : (ListenerUtil.mutListener.listen(26883) ? (firstVisibleItem == 0) : (firstVisibleItem > 0))))))) : ((ListenerUtil.mutListener.listen(26882) ? (direction >= -1) : (ListenerUtil.mutListener.listen(26881) ? (direction <= -1) : (ListenerUtil.mutListener.listen(26880) ? (direction > -1) : (ListenerUtil.mutListener.listen(26879) ? (direction < -1) : (ListenerUtil.mutListener.listen(26878) ? (direction != -1) : (direction == -1)))))) && (ListenerUtil.mutListener.listen(26887) ? (firstVisibleItem >= 0) : (ListenerUtil.mutListener.listen(26886) ? (firstVisibleItem <= 0) : (ListenerUtil.mutListener.listen(26885) ? (firstVisibleItem < 0) : (ListenerUtil.mutListener.listen(26884) ? (firstVisibleItem != 0) : (ListenerUtil.mutListener.listen(26883) ? (firstVisibleItem == 0) : (firstVisibleItem > 0))))))))) {
                                                                    if (!ListenerUtil.mutListener.listen(26893)) {
                                                                        nextInitial = contactListAdapter.getInitial((ListenerUtil.mutListener.listen(26892) ? (firstVisibleItem % 1) : (ListenerUtil.mutListener.listen(26891) ? (firstVisibleItem / 1) : (ListenerUtil.mutListener.listen(26890) ? (firstVisibleItem * 1) : (ListenerUtil.mutListener.listen(26889) ? (firstVisibleItem + 1) : (firstVisibleItem - 1))))));
                                                                    }
                                                                }
                                                            }
                                                            if (!ListenerUtil.mutListener.listen(26907)) {
                                                                if ((ListenerUtil.mutListener.listen(26904) ? (direction >= 1) : (ListenerUtil.mutListener.listen(26903) ? (direction <= 1) : (ListenerUtil.mutListener.listen(26902) ? (direction > 1) : (ListenerUtil.mutListener.listen(26901) ? (direction < 1) : (ListenerUtil.mutListener.listen(26900) ? (direction != 1) : (direction == 1))))))) {
                                                                    if (!ListenerUtil.mutListener.listen(26906)) {
                                                                        stickyInitialLayout.setVisibility(nextInitial.equals(currentInitial) ? View.VISIBLE : View.GONE);
                                                                    }
                                                                } else {
                                                                    if (!ListenerUtil.mutListener.listen(26905)) {
                                                                        stickyInitialLayout.setVisibility(previousInitial.equals(currentInitial) ? View.VISIBLE : View.GONE);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            if (!ListenerUtil.mutListener.listen(26851)) {
                                                                stickyInitialLayout.setVisibility(View.GONE);
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                            if (!ListenerUtil.mutListener.listen(26910)) {
                                                previousFirstVisibleItem = firstVisibleItem;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    });
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(26917)) {
            if (savedInstanceState != null) {
                if (!ListenerUtil.mutListener.listen(26916)) {
                    if (TestUtil.empty(this.filterQuery)) {
                        if (!ListenerUtil.mutListener.listen(26915)) {
                            this.filterQuery = savedInstanceState.getString(BUNDLE_FILTER_QUERY_C);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(26918)) {
            // fill adapter with data
            createListAdapter(savedInstanceState);
        }
        // register a receiver that will receive info about changed contacts from contact sync
        IntentFilter filter = new IntentFilter();
        if (!ListenerUtil.mutListener.listen(26919)) {
            filter.addAction(IntentDataUtil.ACTION_CONTACTS_CHANGED);
        }
        if (!ListenerUtil.mutListener.listen(26920)) {
            LocalBroadcastManager.getInstance(getContext()).registerReceiver(contactsChangedReceiver, filter);
        }
    }

    @Override
    public void onDestroyView() {
        if (!ListenerUtil.mutListener.listen(26921)) {
            LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(contactsChangedReceiver);
        }
        if (!ListenerUtil.mutListener.listen(26922)) {
            searchView = null;
        }
        if (!ListenerUtil.mutListener.listen(26923)) {
            searchMenuItem = null;
        }
        if (!ListenerUtil.mutListener.listen(26924)) {
            contactListAdapter = null;
        }
        if (!ListenerUtil.mutListener.listen(26925)) {
            super.onDestroyView();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!ListenerUtil.mutListener.listen(26929)) {
            switch(requestCode) {
                case ThreemaActivity.ACTIVITY_ID_ADD_CONTACT:
                    if (!ListenerUtil.mutListener.listen(26927)) {
                        if (actionMode != null) {
                            if (!ListenerUtil.mutListener.listen(26926)) {
                                actionMode.finish();
                            }
                        }
                    }
                    break;
                case ThreemaActivity.ACTIVITY_ID_CONTACT_DETAIL:
                    break;
                default:
                    if (!ListenerUtil.mutListener.listen(26928)) {
                        super.onActivityResult(requestCode, resultCode, data);
                    }
            }
        }
    }

    @Override
    public void onRefresh() {
        if (!ListenerUtil.mutListener.listen(26931)) {
            if (actionMode != null) {
                if (!ListenerUtil.mutListener.listen(26930)) {
                    actionMode.finish();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(26932)) {
            startSwipeRefresh();
        }
        if (!ListenerUtil.mutListener.listen(26933)) {
            new Handler(Looper.getMainLooper()).postDelayed(this::stopSwipeRefresh, 2000);
        }
        try {
            if (!ListenerUtil.mutListener.listen(26934)) {
                WorkManager.getInstance(requireContext()).enqueue(new OneTimeWorkRequest.Builder(IdentityStatesWorker.class).build());
            }
        } catch (IllegalStateException ignored) {
        }
        if (!ListenerUtil.mutListener.listen(26938)) {
            if ((ListenerUtil.mutListener.listen(26935) ? (this.preferenceService.isSyncContacts() || ConfigUtils.requestContactPermissions(getActivity(), this, PERMISSION_REQUEST_REFRESH_CONTACTS)) : (this.preferenceService.isSyncContacts() && ConfigUtils.requestContactPermissions(getActivity(), this, PERMISSION_REQUEST_REFRESH_CONTACTS)))) {
                if (!ListenerUtil.mutListener.listen(26937)) {
                    if (this.synchronizeContactsService != null) {
                        if (!ListenerUtil.mutListener.listen(26936)) {
                            synchronizeContactsService.instantiateSynchronizationAndRun();
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(26940)) {
            if (ConfigUtils.isWorkBuild()) {
                if (!ListenerUtil.mutListener.listen(26939)) {
                    WorkSyncService.enqueueWork(getActivity(), new Intent(), true);
                }
            }
        }
    }

    private void openConversationForIdentity(View v, String identity) {
        Intent intent = new Intent(getActivity(), ComposeMessageActivity.class);
        if (!ListenerUtil.mutListener.listen(26941)) {
            intent.putExtra(ThreemaApplication.INTENT_DATA_CONTACT, identity);
        }
        if (!ListenerUtil.mutListener.listen(26942)) {
            intent.putExtra(ThreemaApplication.INTENT_DATA_EDITFOCUS, Boolean.TRUE);
        }
        if (!ListenerUtil.mutListener.listen(26943)) {
            AnimationUtil.startActivityForResult(getActivity(), v, intent, ThreemaActivity.ACTIVITY_ID_COMPOSE_MESSAGE);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (!ListenerUtil.mutListener.listen(26944)) {
            logger.info("saveInstance");
        }
        if (!ListenerUtil.mutListener.listen(26946)) {
            if (!TestUtil.empty(filterQuery)) {
                if (!ListenerUtil.mutListener.listen(26945)) {
                    outState.putString(BUNDLE_FILTER_QUERY_C, filterQuery);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(26949)) {
            if ((ListenerUtil.mutListener.listen(26947) ? (ConfigUtils.isWorkBuild() || workTabLayout != null) : (ConfigUtils.isWorkBuild() && workTabLayout != null))) {
                if (!ListenerUtil.mutListener.listen(26948)) {
                    outState.putInt(BUNDLE_SELECTED_TAB, workTabLayout.getSelectedTabPosition());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(26950)) {
            super.onSaveInstanceState(outState);
        }
    }

    @Override
    public boolean onBackPressed() {
        if (!ListenerUtil.mutListener.listen(26952)) {
            if (actionMode != null) {
                if (!ListenerUtil.mutListener.listen(26951)) {
                    actionMode.finish();
                }
                return true;
            }
        }
        if (!ListenerUtil.mutListener.listen(26956)) {
            if ((ListenerUtil.mutListener.listen(26954) ? ((ListenerUtil.mutListener.listen(26953) ? (this.searchView != null || this.searchView.isShown()) : (this.searchView != null && this.searchView.isShown())) || this.searchMenuItem != null) : ((ListenerUtil.mutListener.listen(26953) ? (this.searchView != null || this.searchView.isShown()) : (this.searchView != null && this.searchView.isShown())) && this.searchMenuItem != null))) {
                if (!ListenerUtil.mutListener.listen(26955)) {
                    MenuItemCompat.collapseActionView(this.searchMenuItem);
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        ContactModel contactModel = contactListAdapter.getClickedItem(v);
        if (!ListenerUtil.mutListener.listen(26959)) {
            if (contactModel != null) {
                String identity;
                identity = contactModel.getIdentity();
                if (!ListenerUtil.mutListener.listen(26958)) {
                    if (identity != null) {
                        if (!ListenerUtil.mutListener.listen(26957)) {
                            openConversationForIdentity(v, identity);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onAvatarClick(View view, int position) {
        if (!ListenerUtil.mutListener.listen(26960)) {
            if (contactListAdapter == null) {
                return;
            }
        }
        View listItemView = (View) view.getParent();
        if (!ListenerUtil.mutListener.listen(26968)) {
            if ((ListenerUtil.mutListener.listen(26965) ? (contactListAdapter.getCheckedItemCount() >= 0) : (ListenerUtil.mutListener.listen(26964) ? (contactListAdapter.getCheckedItemCount() <= 0) : (ListenerUtil.mutListener.listen(26963) ? (contactListAdapter.getCheckedItemCount() < 0) : (ListenerUtil.mutListener.listen(26962) ? (contactListAdapter.getCheckedItemCount() != 0) : (ListenerUtil.mutListener.listen(26961) ? (contactListAdapter.getCheckedItemCount() == 0) : (contactListAdapter.getCheckedItemCount() > 0))))))) {
                if (!ListenerUtil.mutListener.listen(26966)) {
                    // forward click on avatar to relevant list item
                    position += listView.getHeaderViewsCount();
                }
                if (!ListenerUtil.mutListener.listen(26967)) {
                    listView.setItemChecked(position, !listView.isItemChecked(position));
                }
                return;
            }
        }
        Intent intent = new Intent(getActivity(), ContactDetailActivity.class);
        if (!ListenerUtil.mutListener.listen(26969)) {
            intent.putExtra(ThreemaApplication.INTENT_DATA_CONTACT, contactListAdapter.getClickedItem(listItemView).getIdentity());
        }
        if (!ListenerUtil.mutListener.listen(26970)) {
            AnimationUtil.startActivityForResult(getActivity(), view, intent, ThreemaActivity.ACTIVITY_ID_CONTACT_DETAIL);
        }
    }

    @Override
    public boolean onAvatarLongClick(View view, int position) {
        /*
		if (contactListAdapter != null && contactListAdapter.getCheckedItemCount() == 0) {
			position += listView.getHeaderViewsCount();
			listView.setItemChecked(position, true);
		}
		*/
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (!ListenerUtil.mutListener.listen(26980)) {
            switch(requestCode) {
                case PERMISSION_REQUEST_REFRESH_CONTACTS:
                    if (!ListenerUtil.mutListener.listen(26979)) {
                        if ((ListenerUtil.mutListener.listen(26976) ? ((ListenerUtil.mutListener.listen(26975) ? (grantResults.length >= 0) : (ListenerUtil.mutListener.listen(26974) ? (grantResults.length <= 0) : (ListenerUtil.mutListener.listen(26973) ? (grantResults.length < 0) : (ListenerUtil.mutListener.listen(26972) ? (grantResults.length != 0) : (ListenerUtil.mutListener.listen(26971) ? (grantResults.length == 0) : (grantResults.length > 0)))))) || grantResults[0] == PackageManager.PERMISSION_GRANTED) : ((ListenerUtil.mutListener.listen(26975) ? (grantResults.length >= 0) : (ListenerUtil.mutListener.listen(26974) ? (grantResults.length <= 0) : (ListenerUtil.mutListener.listen(26973) ? (grantResults.length < 0) : (ListenerUtil.mutListener.listen(26972) ? (grantResults.length != 0) : (ListenerUtil.mutListener.listen(26971) ? (grantResults.length == 0) : (grantResults.length > 0)))))) && grantResults[0] == PackageManager.PERMISSION_GRANTED))) {
                            if (!ListenerUtil.mutListener.listen(26978)) {
                                this.onRefresh();
                            }
                        } else if (!shouldShowRequestPermissionRationale(Manifest.permission.READ_CONTACTS)) {
                            if (!ListenerUtil.mutListener.listen(26977)) {
                                ConfigUtils.showPermissionRationale(getContext(), getView(), R.string.permission_contacts_required);
                            }
                        }
                    }
            }
        }
    }

    private void setupListeners() {
        if (!ListenerUtil.mutListener.listen(26981)) {
            logger.debug("*** setup listeners");
        }
        if (!ListenerUtil.mutListener.listen(26982)) {
            // set listeners
            ListenerManager.contactListeners.add(this.contactListener);
        }
        if (!ListenerUtil.mutListener.listen(26983)) {
            ListenerManager.contactSettingsListeners.add(this.contactSettingsListener);
        }
        if (!ListenerUtil.mutListener.listen(26984)) {
            ListenerManager.synchronizeContactsListeners.add(this.synchronizeContactsListener);
        }
        if (!ListenerUtil.mutListener.listen(26985)) {
            ListenerManager.preferenceListeners.add(this.preferenceListener);
        }
    }

    private void removeListeners() {
        if (!ListenerUtil.mutListener.listen(26986)) {
            logger.debug("*** remove listeners");
        }
        if (!ListenerUtil.mutListener.listen(26987)) {
            ListenerManager.contactListeners.remove(this.contactListener);
        }
        if (!ListenerUtil.mutListener.listen(26988)) {
            ListenerManager.contactSettingsListeners.remove(this.contactSettingsListener);
        }
        if (!ListenerUtil.mutListener.listen(26989)) {
            ListenerManager.synchronizeContactsListeners.remove(this.synchronizeContactsListener);
        }
        if (!ListenerUtil.mutListener.listen(26990)) {
            ListenerManager.preferenceListeners.remove(this.preferenceListener);
        }
    }

    @SuppressLint("StringFormatInvalid")
    private void deleteSelectedContacts() {
        GenericAlertDialog dialog = GenericAlertDialog.newInstance(R.string.delete_contact_action, String.format(getString(R.string.really_delete_contacts_message), contactListAdapter.getCheckedItemCount()), R.string.ok, R.string.cancel);
        if (!ListenerUtil.mutListener.listen(26991)) {
            dialog.setTargetFragment(this, 0);
        }
        if (!ListenerUtil.mutListener.listen(26992)) {
            dialog.show(getFragmentManager(), DIALOG_TAG_REALLY_DELETE_CONTACTS);
        }
    }

    @Override
    public void onYes(String tag, Object data) {
        if (!ListenerUtil.mutListener.listen(26994)) {
            switch(tag) {
                case DIALOG_TAG_REALLY_DELETE_CONTACTS:
                    if (!ListenerUtil.mutListener.listen(26993)) {
                        reallyDeleteContacts();
                    }
                    break;
                default:
                    break;
            }
        }
    }

    @SuppressLint("StaticFieldLeak")
    private void reallyDeleteContacts() {
        if (!ListenerUtil.mutListener.listen(27006)) {
            new DeleteContactAsyncTask(getFragmentManager(), contactListAdapter.getCheckedItems(), contactService, new DeleteContactAsyncTask.DeleteContactsPostRunnable() {

                @Override
                public void run() {
                    if (!ListenerUtil.mutListener.listen(27003)) {
                        if (isAdded()) {
                            if (!ListenerUtil.mutListener.listen(27002)) {
                                if ((ListenerUtil.mutListener.listen(26999) ? (failed >= 0) : (ListenerUtil.mutListener.listen(26998) ? (failed <= 0) : (ListenerUtil.mutListener.listen(26997) ? (failed < 0) : (ListenerUtil.mutListener.listen(26996) ? (failed != 0) : (ListenerUtil.mutListener.listen(26995) ? (failed == 0) : (failed > 0))))))) {
                                    if (!ListenerUtil.mutListener.listen(27001)) {
                                        Toast.makeText(getActivity(), String.format(getString(R.string.some_contacts_not_deleted), failed), Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(27000)) {
                                        Toast.makeText(getActivity(), R.string.contacts_deleted, Toast.LENGTH_LONG).show();
                                    }
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(27005)) {
                        if (actionMode != null) {
                            if (!ListenerUtil.mutListener.listen(27004)) {
                                actionMode.finish();
                            }
                        }
                    }
                }
            }).execute();
        }
    }

    @Override
    public void onNo(String tag, Object data) {
    }

    @Override
    public void onSelected(String tag) {
        if (!ListenerUtil.mutListener.listen(27008)) {
            if (!TestUtil.empty(tag)) {
                if (!ListenerUtil.mutListener.listen(27007)) {
                    sendInvite(tag);
                }
            }
        }
    }

    public void shareInvite() {
        final PackageManager packageManager = getContext().getPackageManager();
        if (!ListenerUtil.mutListener.listen(27009)) {
            if (packageManager == null)
                return;
        }
        Intent messageIntent = new Intent(Intent.ACTION_SEND);
        if (!ListenerUtil.mutListener.listen(27010)) {
            messageIntent.setType("text/plain");
        }
        @SuppressLint({ "WrongConstant", "InlinedApi" })
        final List<ResolveInfo> messageApps = packageManager.queryIntentActivities(messageIntent, PackageManager.MATCH_ALL);
        if (!ListenerUtil.mutListener.listen(27024)) {
            if (!messageApps.isEmpty()) {
                ArrayList<BottomSheetItem> items = new ArrayList<>();
                if (!ListenerUtil.mutListener.listen(27021)) {
                    {
                        long _loopCounter173 = 0;
                        for (int i = 0; (ListenerUtil.mutListener.listen(27020) ? (i >= messageApps.size()) : (ListenerUtil.mutListener.listen(27019) ? (i <= messageApps.size()) : (ListenerUtil.mutListener.listen(27018) ? (i > messageApps.size()) : (ListenerUtil.mutListener.listen(27017) ? (i != messageApps.size()) : (ListenerUtil.mutListener.listen(27016) ? (i == messageApps.size()) : (i < messageApps.size())))))); i++) {
                            ListenerUtil.loopListener.listen("_loopCounter173", ++_loopCounter173);
                            ResolveInfo resolveInfo = messageApps.get(i);
                            if (!ListenerUtil.mutListener.listen(27015)) {
                                if (resolveInfo != null) {
                                    CharSequence label = resolveInfo.loadLabel(packageManager);
                                    Drawable icon = resolveInfo.loadIcon(packageManager);
                                    if (!ListenerUtil.mutListener.listen(27014)) {
                                        if ((ListenerUtil.mutListener.listen(27011) ? (label != null || icon != null) : (label != null && icon != null))) {
                                            Bitmap bitmap = BitmapUtil.getBitmapFromVectorDrawable(icon, null);
                                            if (!ListenerUtil.mutListener.listen(27013)) {
                                                if (bitmap != null) {
                                                    if (!ListenerUtil.mutListener.listen(27012)) {
                                                        items.add(new BottomSheetItem(bitmap, label.toString(), messageApps.get(i).activityInfo.packageName));
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                BottomSheetGridDialog dialog = BottomSheetGridDialog.newInstance(R.string.invite_via, items);
                if (!ListenerUtil.mutListener.listen(27022)) {
                    dialog.setTargetFragment(this, 0);
                }
                if (!ListenerUtil.mutListener.listen(27023)) {
                    dialog.show(getFragmentManager(), DIALOG_TAG_SHARE_WITH);
                }
            }
        }
    }

    private void sendInvite(String packageName) {
        // is this an SMS app? if it holds the SEND_SMS permission, it most probably is.
        boolean isShortMessage = ConfigUtils.checkManifestPermission(getContext(), packageName, "android.permission.SEND_SMS");
        if (!ListenerUtil.mutListener.listen(27026)) {
            if (packageName.contains("twitter")) {
                if (!ListenerUtil.mutListener.listen(27025)) {
                    isShortMessage = true;
                }
            }
        }
        Intent intent = new Intent(Intent.ACTION_SEND);
        if (!ListenerUtil.mutListener.listen(27027)) {
            intent.setType("text/plain");
        }
        if (!ListenerUtil.mutListener.listen(27028)) {
            intent.setPackage(packageName);
        }
        UserService userService = ThreemaApplication.getServiceManager().getUserService();
        if (!ListenerUtil.mutListener.listen(27032)) {
            if (isShortMessage) {
                /* short version */
                String messageBody = String.format(getString(R.string.invite_sms_body), getString(R.string.app_name), userService.getIdentity());
                if (!ListenerUtil.mutListener.listen(27031)) {
                    intent.putExtra(Intent.EXTRA_TEXT, messageBody);
                }
            } else {
                /* long version */
                String messageBody = String.format(getString(R.string.invite_email_body), getString(R.string.app_name), userService.getIdentity());
                if (!ListenerUtil.mutListener.listen(27029)) {
                    intent.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.invite_email_subject));
                }
                if (!ListenerUtil.mutListener.listen(27030)) {
                    intent.putExtra(Intent.EXTRA_TEXT, messageBody);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(27035)) {
            if (intent.resolveActivity(getContext().getPackageManager()) != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(27034)) {
                        startActivity(intent);
                    }
                } catch (SecurityException e) {
                    if (!ListenerUtil.mutListener.listen(27033)) {
                        logger.error("Exception", e);
                    }
                }
            }
        }
    }

    public void onLogoClicked() {
        if (!ListenerUtil.mutListener.listen(27038)) {
            if (this.listView != null) {
                if (!ListenerUtil.mutListener.listen(27036)) {
                    // this stops the fling
                    this.listView.smoothScrollBy(0, 0);
                }
                if (!ListenerUtil.mutListener.listen(27037)) {
                    this.listView.setSelection(0);
                }
            }
        }
    }
}
