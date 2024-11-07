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
package ch.threema.app.activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Location;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.BadParcelableException;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.DocumentsContract;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executors;
import androidx.annotation.AnyThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import androidx.annotation.WorkerThread;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.core.app.TaskStackBuilder;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import ch.threema.app.BuildConfig;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.actions.LocationMessageSendAction;
import ch.threema.app.actions.SendAction;
import ch.threema.app.actions.TextMessageSendAction;
import ch.threema.app.adapters.FilterableListAdapter;
import ch.threema.app.dialogs.CancelableHorizontalProgressDialog;
import ch.threema.app.dialogs.ExpandableTextEntryDialog;
import ch.threema.app.dialogs.GenericProgressDialog;
import ch.threema.app.dialogs.TextWithCheckboxDialog;
import ch.threema.app.dialogs.ThreemaDialogFragment;
import ch.threema.app.fragments.DistributionListFragment;
import ch.threema.app.fragments.GroupListFragment;
import ch.threema.app.fragments.RecentListFragment;
import ch.threema.app.fragments.RecipientListFragment;
import ch.threema.app.fragments.UserListFragment;
import ch.threema.app.fragments.WorkUserListFragment;
import ch.threema.app.managers.ServiceManager;
import ch.threema.app.messagereceiver.MessageReceiver;
import ch.threema.app.services.ContactService;
import ch.threema.app.services.ConversationService;
import ch.threema.app.services.DistributionListService;
import ch.threema.app.services.FileService;
import ch.threema.app.services.GroupService;
import ch.threema.app.services.MessageService;
import ch.threema.app.services.PreferenceService;
import ch.threema.app.services.UserService;
import ch.threema.app.ui.MediaItem;
import ch.threema.app.ui.SingleToast;
import ch.threema.app.ui.ThreemaSearchView;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.ContactLookupUtil;
import ch.threema.app.utils.DialogUtil;
import ch.threema.app.utils.FileUtil;
import ch.threema.app.utils.IntentDataUtil;
import ch.threema.app.utils.MimeUtil;
import ch.threema.app.utils.NameUtil;
import ch.threema.app.utils.NavigationUtil;
import ch.threema.app.utils.RuntimeUtil;
import ch.threema.app.utils.TestUtil;
import ch.threema.client.file.FileData;
import ch.threema.storage.models.AbstractMessageModel;
import ch.threema.storage.models.ContactModel;
import ch.threema.storage.models.DistributionListModel;
import ch.threema.storage.models.GroupModel;
import ch.threema.storage.models.MessageType;
import ch.threema.storage.models.data.LocationDataModel;
import java8.util.concurrent.CompletableFuture;
import static ch.threema.app.activities.SendMediaActivity.MAX_SELECTABLE_IMAGES;
import static ch.threema.app.ui.MediaItem.TYPE_TEXT;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class RecipientListBaseActivity extends ThreemaToolbarActivity implements CancelableHorizontalProgressDialog.ProgressDialogClickListener, ExpandableTextEntryDialog.ExpandableTextEntryDialogClickListener, TextWithCheckboxDialog.TextWithCheckboxDialogClickListener, SearchView.OnQueryTextListener {

    private static final Logger logger = LoggerFactory.getLogger(RecipientListBaseActivity.class);

    private static final int FRAGMENT_RECENT = 0;

    private static final int FRAGMENT_USERS = 1;

    private static final int FRAGMENT_GROUPS = 2;

    private static final int FRAGMENT_DISTRIBUTION_LIST = 3;

    private static final int FRAGMENT_WORK_USERS = 4;

    private static final int NUM_FRAGMENTS = 5;

    private static final String DIALOG_TAG_MULTISEND = "multisend";

    private static final String DIALOG_TAG_FILECOPY = "filecopy";

    public static final String INTENT_DATA_MULTISELECT = "ms";

    private static final int REQUEST_READ_EXTERNAL_STORAGE = 1;

    private ViewPager viewPager;

    private UserGroupPagerAdapter userGroupPagerAdapter;

    private MenuItem searchMenuItem;

    private ThreemaSearchView searchView;

    private boolean hideUi, hideRecents, multiSelect;

    private String captionText;

    private final List<MediaItem> mediaItems = new ArrayList<>();

    private final List<MessageReceiver> recipientMessageReceivers = new ArrayList<>();

    private final List<AbstractMessageModel> originalMessageModels = new ArrayList<>();

    private final List<Integer> tabs = new ArrayList<>(NUM_FRAGMENTS);

    private GroupService groupService;

    private ContactService contactService;

    private ConversationService conversationService;

    private DistributionListService distributionListService;

    private MessageService messageService;

    private FileService fileService;

    private final Runnable copyFilesRunnable = new Runnable() {

        @Override
        public void run() {
            if (!ListenerUtil.mutListener.listen(5421)) {
                {
                    long _loopCounter38 = 0;
                    for (int i = 0; (ListenerUtil.mutListener.listen(5420) ? (i >= mediaItems.size()) : (ListenerUtil.mutListener.listen(5419) ? (i <= mediaItems.size()) : (ListenerUtil.mutListener.listen(5418) ? (i > mediaItems.size()) : (ListenerUtil.mutListener.listen(5417) ? (i != mediaItems.size()) : (ListenerUtil.mutListener.listen(5416) ? (i == mediaItems.size()) : (i < mediaItems.size())))))); i++) {
                        ListenerUtil.loopListener.listen("_loopCounter38", ++_loopCounter38);
                        MediaItem mediaItem = mediaItems.get(i);
                        if (!ListenerUtil.mutListener.listen(5410)) {
                            if (TestUtil.empty(mediaItem.getFilename())) {
                                if (!ListenerUtil.mutListener.listen(5409)) {
                                    mediaItem.setFilename(FileUtil.getFilenameFromUri(getContentResolver(), mediaItem));
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(5415)) {
                            if (ContentResolver.SCHEME_CONTENT.equalsIgnoreCase(mediaItem.getUri().getScheme())) {
                                try {
                                    File file = fileService.createTempFile("rcpt", null);
                                    if (!ListenerUtil.mutListener.listen(5412)) {
                                        FileUtil.copyFile(mediaItem.getUri(), file, getContentResolver());
                                    }
                                    if (!ListenerUtil.mutListener.listen(5413)) {
                                        mediaItem.setUri(Uri.fromFile(file));
                                    }
                                    if (!ListenerUtil.mutListener.listen(5414)) {
                                        mediaItem.setDeleteAfterUse(true);
                                    }
                                } catch (IOException e) {
                                    if (!ListenerUtil.mutListener.listen(5411)) {
                                        logger.error("Unable to copy to tmp dir", e);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    };

    @Override
    public boolean onQueryTextSubmit(String query) {
        // Do something
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        int currentItem = viewPager.getCurrentItem();
        Fragment fragment = userGroupPagerAdapter.getRegisteredFragment(currentItem);
        if (!ListenerUtil.mutListener.listen(5424)) {
            if (fragment != null) {
                FilterableListAdapter listAdapter = ((RecipientListFragment) fragment).getAdapter();
                if (!ListenerUtil.mutListener.listen(5422)) {
                    // adapter can be null if it has not been initialized yet (runs in different thread)
                    if (listAdapter == null)
                        return false;
                }
                if (!ListenerUtil.mutListener.listen(5423)) {
                    listAdapter.getFilter().filter(newText);
                }
            }
        }
        return true;
    }

    public int getLayoutResource() {
        return R.layout.activity_recipientlist;
    }

    private boolean validateSendingPermission(MessageReceiver messageReceiver) {
        return (ListenerUtil.mutListener.listen(5425) ? (messageReceiver != null || messageReceiver.validateSendingPermission(errorResId -> RuntimeUtil.runOnUiThread(() -> SingleToast.getInstance().showLongText(getString(errorResId))))) : (messageReceiver != null && messageReceiver.validateSendingPermission(errorResId -> RuntimeUtil.runOnUiThread(() -> SingleToast.getInstance().showLongText(getString(errorResId))))));
    }

    private void resetValues() {
        if (!ListenerUtil.mutListener.listen(5426)) {
            hideUi = hideRecents = false;
        }
        if (!ListenerUtil.mutListener.listen(5427)) {
            mediaItems.clear();
        }
        if (!ListenerUtil.mutListener.listen(5428)) {
            originalMessageModels.clear();
        }
        if (!ListenerUtil.mutListener.listen(5429)) {
            tabs.clear();
        }
        if (!ListenerUtil.mutListener.listen(5430)) {
            captionText = null;
        }
    }

    @Override
    protected boolean initActivity(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(5431)) {
            if (!super.initActivity(savedInstanceState)) {
                return false;
            }
        }
        ;
        final ServiceManager serviceManager = ThreemaApplication.getServiceManager();
        UserService userService;
        try {
            if (!ListenerUtil.mutListener.listen(5433)) {
                this.contactService = serviceManager.getContactService();
            }
            if (!ListenerUtil.mutListener.listen(5434)) {
                this.conversationService = serviceManager.getConversationService();
            }
            if (!ListenerUtil.mutListener.listen(5435)) {
                this.groupService = serviceManager.getGroupService();
            }
            if (!ListenerUtil.mutListener.listen(5436)) {
                this.distributionListService = serviceManager.getDistributionListService();
            }
            if (!ListenerUtil.mutListener.listen(5437)) {
                this.messageService = serviceManager.getMessageService();
            }
            if (!ListenerUtil.mutListener.listen(5438)) {
                this.fileService = serviceManager.getFileService();
            }
            userService = serviceManager.getUserService();
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(5432)) {
                logger.error("Exception", e);
            }
            return false;
        }
        if (!ListenerUtil.mutListener.listen(5440)) {
            if (!userService.hasIdentity()) {
                if (!ListenerUtil.mutListener.listen(5439)) {
                    ConfigUtils.recreateActivity(this);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5441)) {
            onNewIntent(getIntent());
        }
        return true;
    }

    private void setupUI() {
        final TabLayout tabLayout = findViewById(R.id.sliding_tabs);
        final ActionBar actionBar = getSupportActionBar();
        final ProgressBar progressBar = findViewById(R.id.progress_sending);
        if (!ListenerUtil.mutListener.listen(5442)) {
            viewPager = findViewById(R.id.pager);
        }
        if (!ListenerUtil.mutListener.listen(5445)) {
            if ((ListenerUtil.mutListener.listen(5443) ? (viewPager == null && tabLayout == null) : (viewPager == null || tabLayout == null))) {
                if (!ListenerUtil.mutListener.listen(5444)) {
                    finish();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(5446)) {
            tabLayout.clearOnTabSelectedListeners();
        }
        if (!ListenerUtil.mutListener.listen(5447)) {
            tabLayout.removeAllTabs();
        }
        if (!ListenerUtil.mutListener.listen(5448)) {
            viewPager.clearOnPageChangeListeners();
        }
        if (!ListenerUtil.mutListener.listen(5449)) {
            viewPager.setAdapter(null);
        }
        if (!ListenerUtil.mutListener.listen(5450)) {
            viewPager.removeAllViews();
        }
        if (!ListenerUtil.mutListener.listen(5507)) {
            if (!hideUi) {
                boolean hasMedia = false;
                if (!ListenerUtil.mutListener.listen(5464)) {
                    {
                        long _loopCounter39 = 0;
                        for (MediaItem mediaItem : mediaItems) {
                            ListenerUtil.loopListener.listen("_loopCounter39", ++_loopCounter39);
                            String mimeType = mediaItem.getMimeType();
                            if (!ListenerUtil.mutListener.listen(5463)) {
                                if (mimeType != null) {
                                    if (!ListenerUtil.mutListener.listen(5462)) {
                                        if ((ListenerUtil.mutListener.listen(5460) ? (!hasMedia || !mimeType.startsWith("text/")) : (!hasMedia && !mimeType.startsWith("text/")))) {
                                            if (!ListenerUtil.mutListener.listen(5461)) {
                                                hasMedia = true;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(5466)) {
                    if (hasMedia) {
                        if (!ListenerUtil.mutListener.listen(5465)) {
                            if (!ConfigUtils.requestStoragePermissions(this, null, REQUEST_READ_EXTERNAL_STORAGE)) {
                                return;
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(5469)) {
                    if (!hideRecents) {
                        if (!ListenerUtil.mutListener.listen(5467)) {
                            tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_history_outline).setContentDescription(R.string.title_tab_recent));
                        }
                        if (!ListenerUtil.mutListener.listen(5468)) {
                            tabs.add(FRAGMENT_RECENT);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(5472)) {
                    if (ConfigUtils.isWorkBuild()) {
                        if (!ListenerUtil.mutListener.listen(5470)) {
                            tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_work_outline).setContentDescription(R.string.title_tab_work_users));
                        }
                        if (!ListenerUtil.mutListener.listen(5471)) {
                            tabs.add(FRAGMENT_WORK_USERS);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(5473)) {
                    tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_person_outline).setContentDescription(R.string.title_tab_users));
                }
                if (!ListenerUtil.mutListener.listen(5474)) {
                    tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_group_outline).setContentDescription(R.string.title_tab_groups));
                }
                if (!ListenerUtil.mutListener.listen(5475)) {
                    tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_bullhorn_outline).setContentDescription(R.string.title_tab_distribution_list));
                }
                if (!ListenerUtil.mutListener.listen(5476)) {
                    tabs.add(FRAGMENT_USERS);
                }
                if (!ListenerUtil.mutListener.listen(5477)) {
                    tabs.add(FRAGMENT_GROUPS);
                }
                if (!ListenerUtil.mutListener.listen(5478)) {
                    tabs.add(FRAGMENT_DISTRIBUTION_LIST);
                }
                if (!ListenerUtil.mutListener.listen(5480)) {
                    if (progressBar != null) {
                        if (!ListenerUtil.mutListener.listen(5479)) {
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(5481)) {
                    // keeps inactive tabs from being destroyed causing all kinds of problems with lingering AsyncTasks on the the adapter
                    viewPager.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(5486)) {
                    viewPager.setOffscreenPageLimit((ListenerUtil.mutListener.listen(5485) ? (tabLayout.getTabCount() % 1) : (ListenerUtil.mutListener.listen(5484) ? (tabLayout.getTabCount() / 1) : (ListenerUtil.mutListener.listen(5483) ? (tabLayout.getTabCount() * 1) : (ListenerUtil.mutListener.listen(5482) ? (tabLayout.getTabCount() + 1) : (tabLayout.getTabCount() - 1))))));
                }
                if (!ListenerUtil.mutListener.listen(5487)) {
                    tabLayout.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(5488)) {
                    tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
                }
                if (!ListenerUtil.mutListener.listen(5489)) {
                    userGroupPagerAdapter = new UserGroupPagerAdapter(getSupportFragmentManager());
                }
                if (!ListenerUtil.mutListener.listen(5490)) {
                    viewPager.setAdapter(userGroupPagerAdapter);
                }
                if (!ListenerUtil.mutListener.listen(5491)) {
                    viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
                }
                if (!ListenerUtil.mutListener.listen(5492)) {
                    tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
                }
                if (!ListenerUtil.mutListener.listen(5498)) {
                    viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {

                        @Override
                        public void onPageSelected(int position) {
                            if (!ListenerUtil.mutListener.listen(5496)) {
                                if (searchMenuItem != null) {
                                    if (!ListenerUtil.mutListener.listen(5493)) {
                                        searchMenuItem.collapseActionView();
                                    }
                                    if (!ListenerUtil.mutListener.listen(5495)) {
                                        if (searchView != null) {
                                            if (!ListenerUtil.mutListener.listen(5494)) {
                                                searchView.setQuery("", false);
                                            }
                                        }
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(5497)) {
                                invalidateOptionsMenu();
                            }
                        }
                    });
                }
                if (!ListenerUtil.mutListener.listen(5501)) {
                    if (actionBar != null) {
                        if (!ListenerUtil.mutListener.listen(5499)) {
                            actionBar.setDisplayHomeAsUpEnabled(true);
                        }
                        if (!ListenerUtil.mutListener.listen(5500)) {
                            actionBar.setTitle(R.string.title_choose_recipient);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(5504)) {
                    if ((ListenerUtil.mutListener.listen(5502) ? (!hideRecents || !conversationService.hasConversations()) : (!hideRecents && !conversationService.hasConversations()))) {
                        if (!ListenerUtil.mutListener.listen(5503)) {
                            // no conversation? show users tab as default
                            this.viewPager.setCurrentItem(tabs.indexOf(FRAGMENT_USERS), true);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(5506)) {
                    if (searchMenuItem != null) {
                        if (!ListenerUtil.mutListener.listen(5505)) {
                            searchMenuItem.setVisible(true);
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(5453)) {
                    if (actionBar != null) {
                        if (!ListenerUtil.mutListener.listen(5451)) {
                            actionBar.setDisplayHomeAsUpEnabled(false);
                        }
                        if (!ListenerUtil.mutListener.listen(5452)) {
                            actionBar.setTitle(R.string.please_wait);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(5455)) {
                    if (tabLayout != null) {
                        if (!ListenerUtil.mutListener.listen(5454)) {
                            tabLayout.setVisibility(View.GONE);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(5457)) {
                    if (progressBar != null) {
                        if (!ListenerUtil.mutListener.listen(5456)) {
                            progressBar.setVisibility(View.VISIBLE);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(5459)) {
                    if (searchMenuItem != null) {
                        if (!ListenerUtil.mutListener.listen(5458)) {
                            searchMenuItem.setVisible(false);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5508)) {
            findViewById(R.id.main_content).setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        if (!ListenerUtil.mutListener.listen(5509)) {
            logger.debug("onNewIntent");
        }
        if (!ListenerUtil.mutListener.listen(5510)) {
            super.onNewIntent(intent);
        }
        if (!ListenerUtil.mutListener.listen(5511)) {
            resetValues();
        }
        if (!ListenerUtil.mutListener.listen(5652)) {
            if (intent != null) {
                if (!ListenerUtil.mutListener.listen(5512)) {
                    setIntent(intent);
                }
                try {
                    if (!ListenerUtil.mutListener.listen(5514)) {
                        this.hideRecents = intent.getBooleanExtra(ThreemaApplication.INTENT_DATA_HIDE_RECENTS, false);
                    }
                    if (!ListenerUtil.mutListener.listen(5515)) {
                        this.multiSelect = intent.getBooleanExtra(INTENT_DATA_MULTISELECT, true);
                    }
                } catch (BadParcelableException e) {
                    if (!ListenerUtil.mutListener.listen(5513)) {
                        logger.error("Exception", e);
                    }
                }
                String identity = IntentDataUtil.getIdentity(intent);
                if (!ListenerUtil.mutListener.listen(5517)) {
                    if (!TestUtil.empty(identity)) {
                        if (!ListenerUtil.mutListener.listen(5516)) {
                            hideUi = true;
                        }
                    }
                }
                int groupId = IntentDataUtil.getGroupId(intent);
                if (!ListenerUtil.mutListener.listen(5524)) {
                    if ((ListenerUtil.mutListener.listen(5522) ? (groupId >= 0) : (ListenerUtil.mutListener.listen(5521) ? (groupId <= 0) : (ListenerUtil.mutListener.listen(5520) ? (groupId < 0) : (ListenerUtil.mutListener.listen(5519) ? (groupId != 0) : (ListenerUtil.mutListener.listen(5518) ? (groupId == 0) : (groupId > 0))))))) {
                        if (!ListenerUtil.mutListener.listen(5523)) {
                            hideUi = true;
                        }
                    }
                }
                String action = intent.getAction();
                if (!ListenerUtil.mutListener.listen(5651)) {
                    if (action != null) {
                        if (!ListenerUtil.mutListener.listen(5650)) {
                            // called from other app via regular send intent
                            if (action.equals(Intent.ACTION_SEND)) {
                                String type = intent.getType();
                                Uri uri = null;
                                Parcelable parcelable = intent.getParcelableExtra(Intent.EXTRA_STREAM);
                                if (!ListenerUtil.mutListener.listen(5594)) {
                                    if (parcelable != null) {
                                        if (!ListenerUtil.mutListener.listen(5592)) {
                                            if (!(parcelable instanceof Uri)) {
                                                if (!ListenerUtil.mutListener.listen(5591)) {
                                                    parcelable = Uri.parse(parcelable.toString());
                                                }
                                            }
                                        }
                                        if (!ListenerUtil.mutListener.listen(5593)) {
                                            uri = (Uri) parcelable;
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(5640)) {
                                    if ((ListenerUtil.mutListener.listen(5596) ? (type != null || ((ListenerUtil.mutListener.listen(5595) ? (uri != null && MimeUtil.isTextFile(type)) : (uri != null || MimeUtil.isTextFile(type))))) : (type != null && ((ListenerUtil.mutListener.listen(5595) ? (uri != null && MimeUtil.isTextFile(type)) : (uri != null || MimeUtil.isTextFile(type))))))) {
                                        if (!ListenerUtil.mutListener.listen(5622)) {
                                            if (type.equals("message/rfc822")) {
                                                // extract file type from uri path
                                                String mimeType = FileUtil.getMimeTypeFromUri(this, uri);
                                                if (!ListenerUtil.mutListener.listen(5617)) {
                                                    if (!TestUtil.empty(mimeType)) {
                                                        if (!ListenerUtil.mutListener.listen(5616)) {
                                                            type = mimeType;
                                                        }
                                                    }
                                                }
                                                // email body text - can be null
                                                CharSequence charSequence = intent.getCharSequenceExtra(Intent.EXTRA_TEXT);
                                                if (!ListenerUtil.mutListener.listen(5621)) {
                                                    if (charSequence != null) {
                                                        String textIntent = charSequence.toString();
                                                        if (!ListenerUtil.mutListener.listen(5620)) {
                                                            if (!((ListenerUtil.mutListener.listen(5618) ? (textIntent.contains("---") || textIntent.contains("WhatsApp")) : (textIntent.contains("---") && textIntent.contains("WhatsApp"))))) {
                                                                if (!ListenerUtil.mutListener.listen(5619)) {
                                                                    // strip this footer
                                                                    mediaItems.add(new MediaItem(uri, TYPE_TEXT, MimeUtil.MIME_TYPE_TEXT, textIntent));
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        if (!ListenerUtil.mutListener.listen(5639)) {
                                            if (type.equals("text/plain")) {
                                                String textIntent = getTextFromIntent(intent);
                                                if (!ListenerUtil.mutListener.listen(5638)) {
                                                    if (uri != null) {
                                                        if (!ListenerUtil.mutListener.listen(5632)) {
                                                            // default to sending text as file
                                                            type = "x-text/plain";
                                                        }
                                                        String guessedType = getMimeTypeFromContentUri(uri);
                                                        if (!ListenerUtil.mutListener.listen(5634)) {
                                                            if (guessedType != null) {
                                                                if (!ListenerUtil.mutListener.listen(5633)) {
                                                                    type = guessedType;
                                                                }
                                                            }
                                                        }
                                                        if (!ListenerUtil.mutListener.listen(5635)) {
                                                            addMediaItem(type, uri, textIntent);
                                                        }
                                                        if (!ListenerUtil.mutListener.listen(5637)) {
                                                            if (textIntent != null) {
                                                                if (!ListenerUtil.mutListener.listen(5636)) {
                                                                    captionText = textIntent;
                                                                }
                                                            }
                                                        }
                                                    } else if (textIntent != null) {
                                                        if (!ListenerUtil.mutListener.listen(5631)) {
                                                            mediaItems.add(new MediaItem(uri, TYPE_TEXT, MimeUtil.MIME_TYPE_TEXT, textIntent));
                                                        }
                                                    }
                                                }
                                            } else {
                                                if (!ListenerUtil.mutListener.listen(5630)) {
                                                    if (uri != null) {
                                                        // guess the correct mime type as ACTION_SEND may have been called with a generic mime type such as "image/*" which should be overridden
                                                        String guessedType = getMimeTypeFromContentUri(uri);
                                                        if (!ListenerUtil.mutListener.listen(5624)) {
                                                            if (guessedType != null) {
                                                                if (!ListenerUtil.mutListener.listen(5623)) {
                                                                    type = guessedType;
                                                                }
                                                            }
                                                        }
                                                        String textIntent = getTextFromIntent(intent);
                                                        if (!ListenerUtil.mutListener.listen(5629)) {
                                                            // don't add fixed caption to media item because we want it to be editable when sending a zip file (share chat)
                                                            if ((ListenerUtil.mutListener.listen(5625) ? (type.equals("application/zip") || textIntent != null) : (type.equals("application/zip") && textIntent != null))) {
                                                                if (!ListenerUtil.mutListener.listen(5627)) {
                                                                    captionText = textIntent;
                                                                }
                                                                if (!ListenerUtil.mutListener.listen(5628)) {
                                                                    mediaItems.add(new MediaItem(uri, MediaItem.TYPE_FILE, MimeUtil.MIME_TYPE_ZIP, textIntent));
                                                                }
                                                            } else {
                                                                if (!ListenerUtil.mutListener.listen(5626)) {
                                                                    // if text was shared along with the media item, add that too
                                                                    addMediaItem(type, uri, textIntent);
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    } else {
                                        // try ClipData
                                        ClipData clipData = intent.getClipData();
                                        if (!ListenerUtil.mutListener.listen(5612)) {
                                            if ((ListenerUtil.mutListener.listen(5602) ? (clipData != null || (ListenerUtil.mutListener.listen(5601) ? (clipData.getItemCount() >= 0) : (ListenerUtil.mutListener.listen(5600) ? (clipData.getItemCount() <= 0) : (ListenerUtil.mutListener.listen(5599) ? (clipData.getItemCount() < 0) : (ListenerUtil.mutListener.listen(5598) ? (clipData.getItemCount() != 0) : (ListenerUtil.mutListener.listen(5597) ? (clipData.getItemCount() == 0) : (clipData.getItemCount() > 0))))))) : (clipData != null && (ListenerUtil.mutListener.listen(5601) ? (clipData.getItemCount() >= 0) : (ListenerUtil.mutListener.listen(5600) ? (clipData.getItemCount() <= 0) : (ListenerUtil.mutListener.listen(5599) ? (clipData.getItemCount() < 0) : (ListenerUtil.mutListener.listen(5598) ? (clipData.getItemCount() != 0) : (ListenerUtil.mutListener.listen(5597) ? (clipData.getItemCount() == 0) : (clipData.getItemCount() > 0))))))))) {
                                                if (!ListenerUtil.mutListener.listen(5611)) {
                                                    {
                                                        long _loopCounter42 = 0;
                                                        for (int i = 0; (ListenerUtil.mutListener.listen(5610) ? (i >= clipData.getItemCount()) : (ListenerUtil.mutListener.listen(5609) ? (i <= clipData.getItemCount()) : (ListenerUtil.mutListener.listen(5608) ? (i > clipData.getItemCount()) : (ListenerUtil.mutListener.listen(5607) ? (i != clipData.getItemCount()) : (ListenerUtil.mutListener.listen(5606) ? (i == clipData.getItemCount()) : (i < clipData.getItemCount())))))); i++) {
                                                            ListenerUtil.loopListener.listen("_loopCounter42", ++_loopCounter42);
                                                            Uri uri1 = clipData.getItemAt(i).getUri();
                                                            CharSequence text = clipData.getItemAt(i).getText();
                                                            if (!ListenerUtil.mutListener.listen(5605)) {
                                                                if (uri1 != null) {
                                                                    if (!ListenerUtil.mutListener.listen(5604)) {
                                                                        addMediaItem(type, uri1, null);
                                                                    }
                                                                } else if (!TestUtil.empty(text)) {
                                                                    if (!ListenerUtil.mutListener.listen(5603)) {
                                                                        mediaItems.add(new MediaItem(uri, TYPE_TEXT, MimeUtil.MIME_TYPE_TEXT, text.toString()));
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        if (!ListenerUtil.mutListener.listen(5615)) {
                                            if (mediaItems.size() == 0) {
                                                if (!ListenerUtil.mutListener.listen(5613)) {
                                                    Toast.makeText(this, getString(R.string.invalid_data), Toast.LENGTH_LONG).show();
                                                }
                                                if (!ListenerUtil.mutListener.listen(5614)) {
                                                    finish();
                                                }
                                            }
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(5642)) {
                                    if (!TestUtil.empty(identity)) {
                                        if (!ListenerUtil.mutListener.listen(5641)) {
                                            prepareForwardingOrSharing(new ArrayList<>(Collections.singletonList(contactService.getByIdentity(identity))));
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(5649)) {
                                    if ((ListenerUtil.mutListener.listen(5647) ? (groupId >= 0) : (ListenerUtil.mutListener.listen(5646) ? (groupId <= 0) : (ListenerUtil.mutListener.listen(5645) ? (groupId < 0) : (ListenerUtil.mutListener.listen(5644) ? (groupId != 0) : (ListenerUtil.mutListener.listen(5643) ? (groupId == 0) : (groupId > 0))))))) {
                                        if (!ListenerUtil.mutListener.listen(5648)) {
                                            prepareForwardingOrSharing(new ArrayList<>(Collections.singletonList(groupService.getById(groupId))));
                                        }
                                    }
                                }
                            } else if (action.equals(Intent.ACTION_SENDTO)) {
                                if (!ListenerUtil.mutListener.listen(5584)) {
                                    // called from contact app or quickcontactbadge
                                    if ((ListenerUtil.mutListener.listen(5582) ? (lockAppService != null || lockAppService.isLocked()) : (lockAppService != null && lockAppService.isLocked()))) {
                                        if (!ListenerUtil.mutListener.listen(5583)) {
                                            finish();
                                        }
                                        return;
                                    }
                                }
                                // try to extract identity from intent data
                                Uri uri = intent.getData();
                                if (!ListenerUtil.mutListener.listen(5590)) {
                                    // skip user selection if recipient is already known
                                    if ((ListenerUtil.mutListener.listen(5585) ? (uri != null || "smsto".equals(uri.getScheme())) : (uri != null && "smsto".equals(uri.getScheme())))) {
                                        if (!ListenerUtil.mutListener.listen(5586)) {
                                            mediaItems.add(new MediaItem(uri, TYPE_TEXT, MimeUtil.MIME_TYPE_TEXT, intent.getStringExtra("sms_body")));
                                        }
                                        final ContactModel contactModel = ContactLookupUtil.phoneNumberToContact(this, contactService, uri.getSchemeSpecificPart());
                                        if (!ListenerUtil.mutListener.listen(5589)) {
                                            if (contactModel != null) {
                                                if (!ListenerUtil.mutListener.listen(5588)) {
                                                    prepareComposeIntent(new ArrayList<>(Collections.singletonList(contactModel)), false);
                                                }
                                                return;
                                            } else {
                                                if (!ListenerUtil.mutListener.listen(5587)) {
                                                    finish();
                                                }
                                                return;
                                            }
                                        }
                                    }
                                }
                            } else if (action.equals(Intent.ACTION_VIEW)) {
                                if (!ListenerUtil.mutListener.listen(5567)) {
                                    // called from action URL
                                    if ((ListenerUtil.mutListener.listen(5565) ? (lockAppService != null || lockAppService.isLocked()) : (lockAppService != null && lockAppService.isLocked()))) {
                                        if (!ListenerUtil.mutListener.listen(5566)) {
                                            finish();
                                        }
                                        return;
                                    }
                                }
                                Uri dataUri = intent.getData();
                                if (!ListenerUtil.mutListener.listen(5581)) {
                                    if (TestUtil.required(dataUri)) {
                                        String scheme = dataUri.getScheme();
                                        String host = dataUri.getHost();
                                        if (!ListenerUtil.mutListener.listen(5580)) {
                                            if ((ListenerUtil.mutListener.listen(5568) ? (scheme != null || host != null) : (scheme != null && host != null))) {
                                                if (!ListenerUtil.mutListener.listen(5579)) {
                                                    if ((ListenerUtil.mutListener.listen(5573) ? (((ListenerUtil.mutListener.listen(5569) ? (BuildConfig.uriScheme.equals(scheme) || "compose".equals(host)) : (BuildConfig.uriScheme.equals(scheme) && "compose".equals(host)))) && ((ListenerUtil.mutListener.listen(5572) ? ((ListenerUtil.mutListener.listen(5571) ? ("https".equals(scheme) || ((ListenerUtil.mutListener.listen(5570) ? (BuildConfig.actionUrl.equals(host) && BuildConfig.contactActionUrl.equals(host)) : (BuildConfig.actionUrl.equals(host) || BuildConfig.contactActionUrl.equals(host))))) : ("https".equals(scheme) && ((ListenerUtil.mutListener.listen(5570) ? (BuildConfig.actionUrl.equals(host) && BuildConfig.contactActionUrl.equals(host)) : (BuildConfig.actionUrl.equals(host) || BuildConfig.contactActionUrl.equals(host)))))) || "/compose".equals(dataUri.getPath())) : ((ListenerUtil.mutListener.listen(5571) ? ("https".equals(scheme) || ((ListenerUtil.mutListener.listen(5570) ? (BuildConfig.actionUrl.equals(host) && BuildConfig.contactActionUrl.equals(host)) : (BuildConfig.actionUrl.equals(host) || BuildConfig.contactActionUrl.equals(host))))) : ("https".equals(scheme) && ((ListenerUtil.mutListener.listen(5570) ? (BuildConfig.actionUrl.equals(host) && BuildConfig.contactActionUrl.equals(host)) : (BuildConfig.actionUrl.equals(host) || BuildConfig.contactActionUrl.equals(host)))))) && "/compose".equals(dataUri.getPath()))))) : (((ListenerUtil.mutListener.listen(5569) ? (BuildConfig.uriScheme.equals(scheme) || "compose".equals(host)) : (BuildConfig.uriScheme.equals(scheme) && "compose".equals(host)))) || ((ListenerUtil.mutListener.listen(5572) ? ((ListenerUtil.mutListener.listen(5571) ? ("https".equals(scheme) || ((ListenerUtil.mutListener.listen(5570) ? (BuildConfig.actionUrl.equals(host) && BuildConfig.contactActionUrl.equals(host)) : (BuildConfig.actionUrl.equals(host) || BuildConfig.contactActionUrl.equals(host))))) : ("https".equals(scheme) && ((ListenerUtil.mutListener.listen(5570) ? (BuildConfig.actionUrl.equals(host) && BuildConfig.contactActionUrl.equals(host)) : (BuildConfig.actionUrl.equals(host) || BuildConfig.contactActionUrl.equals(host)))))) || "/compose".equals(dataUri.getPath())) : ((ListenerUtil.mutListener.listen(5571) ? ("https".equals(scheme) || ((ListenerUtil.mutListener.listen(5570) ? (BuildConfig.actionUrl.equals(host) && BuildConfig.contactActionUrl.equals(host)) : (BuildConfig.actionUrl.equals(host) || BuildConfig.contactActionUrl.equals(host))))) : ("https".equals(scheme) && ((ListenerUtil.mutListener.listen(5570) ? (BuildConfig.actionUrl.equals(host) && BuildConfig.contactActionUrl.equals(host)) : (BuildConfig.actionUrl.equals(host) || BuildConfig.contactActionUrl.equals(host)))))) && "/compose".equals(dataUri.getPath()))))))) {
                                                        String text = dataUri.getQueryParameter("text");
                                                        if (!ListenerUtil.mutListener.listen(5575)) {
                                                            if (!TestUtil.empty(text)) {
                                                                if (!ListenerUtil.mutListener.listen(5574)) {
                                                                    mediaItems.add(new MediaItem(dataUri, TYPE_TEXT, MimeUtil.MIME_TYPE_TEXT, text));
                                                                }
                                                            }
                                                        }
                                                        String targetIdentity;
                                                        String queryParameter = dataUri.getQueryParameter("id");
                                                        if (queryParameter != null) {
                                                            targetIdentity = queryParameter.toUpperCase();
                                                            ContactModel contactModel = contactService.getByIdentity(targetIdentity);
                                                            if (!ListenerUtil.mutListener.listen(5578)) {
                                                                if (contactModel == null) {
                                                                    if (!ListenerUtil.mutListener.listen(5577)) {
                                                                        addNewContact(targetIdentity);
                                                                    }
                                                                } else {
                                                                    if (!ListenerUtil.mutListener.listen(5576)) {
                                                                        prepareComposeIntent(new ArrayList<>(Collections.singletonList(contactModel)), false);
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
                            } else if ((ListenerUtil.mutListener.listen(5525) ? (action.equals(Intent.ACTION_SEND_MULTIPLE) || intent.hasExtra(Intent.EXTRA_STREAM)) : (action.equals(Intent.ACTION_SEND_MULTIPLE) && intent.hasExtra(Intent.EXTRA_STREAM)))) {
                                // called from other app with multiple media payload
                                String type = intent.getType();
                                ArrayList<Uri> uris = intent.getParcelableArrayListExtra(Intent.EXTRA_STREAM);
                                if (!ListenerUtil.mutListener.listen(5564)) {
                                    if (uris != null) {
                                        if (!ListenerUtil.mutListener.listen(5554)) {
                                            {
                                                long _loopCounter41 = 0;
                                                for (int i = 0; (ListenerUtil.mutListener.listen(5553) ? (i >= uris.size()) : (ListenerUtil.mutListener.listen(5552) ? (i <= uris.size()) : (ListenerUtil.mutListener.listen(5551) ? (i > uris.size()) : (ListenerUtil.mutListener.listen(5550) ? (i != uris.size()) : (ListenerUtil.mutListener.listen(5549) ? (i == uris.size()) : (i < uris.size())))))); i++) {
                                                    ListenerUtil.loopListener.listen("_loopCounter41", ++_loopCounter41);
                                                    if (!ListenerUtil.mutListener.listen(5548)) {
                                                        if ((ListenerUtil.mutListener.listen(5542) ? (i >= MAX_SELECTABLE_IMAGES) : (ListenerUtil.mutListener.listen(5541) ? (i <= MAX_SELECTABLE_IMAGES) : (ListenerUtil.mutListener.listen(5540) ? (i > MAX_SELECTABLE_IMAGES) : (ListenerUtil.mutListener.listen(5539) ? (i != MAX_SELECTABLE_IMAGES) : (ListenerUtil.mutListener.listen(5538) ? (i == MAX_SELECTABLE_IMAGES) : (i < MAX_SELECTABLE_IMAGES))))))) {
                                                            Uri uri = uris.get(i);
                                                            if (!ListenerUtil.mutListener.listen(5547)) {
                                                                if (uri != null) {
                                                                    String mimeType = FileUtil.getMimeTypeFromUri(this, uri);
                                                                    if (!ListenerUtil.mutListener.listen(5545)) {
                                                                        if (mimeType == null) {
                                                                            if (!ListenerUtil.mutListener.listen(5544)) {
                                                                                mimeType = type;
                                                                            }
                                                                        }
                                                                    }
                                                                    if (!ListenerUtil.mutListener.listen(5546)) {
                                                                        addMediaItem(mimeType, uri, null);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            if (!ListenerUtil.mutListener.listen(5543)) {
                                                                Toast.makeText(getApplicationContext(), getString(R.string.max_selectable_media_exceeded, MAX_SELECTABLE_IMAGES), Toast.LENGTH_LONG).show();
                                                            }
                                                            break;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        if (!ListenerUtil.mutListener.listen(5556)) {
                                            if (!TestUtil.empty(identity)) {
                                                if (!ListenerUtil.mutListener.listen(5555)) {
                                                    prepareForwardingOrSharing(new ArrayList<>(Collections.singletonList(contactService.getByIdentity(identity))));
                                                }
                                            }
                                        }
                                        if (!ListenerUtil.mutListener.listen(5563)) {
                                            if ((ListenerUtil.mutListener.listen(5561) ? (groupId >= 0) : (ListenerUtil.mutListener.listen(5560) ? (groupId <= 0) : (ListenerUtil.mutListener.listen(5559) ? (groupId < 0) : (ListenerUtil.mutListener.listen(5558) ? (groupId != 0) : (ListenerUtil.mutListener.listen(5557) ? (groupId == 0) : (groupId > 0))))))) {
                                                if (!ListenerUtil.mutListener.listen(5562)) {
                                                    prepareForwardingOrSharing(new ArrayList<>(Collections.singletonList(groupService.getById(groupId))));
                                                }
                                            }
                                        }
                                    } else {
                                        if (!ListenerUtil.mutListener.listen(5537)) {
                                            finish();
                                        }
                                        return;
                                    }
                                }
                            } else if (action.equals(ThreemaApplication.INTENT_ACTION_FORWARD)) {
                                // internal forward using message id instead of media URI
                                ArrayList<Integer> messageIds = IntentDataUtil.getAbstractMessageIds(intent);
                                String originalMessageType = IntentDataUtil.getAbstractMessageType(intent);
                                if (!ListenerUtil.mutListener.listen(5536)) {
                                    if ((ListenerUtil.mutListener.listen(5531) ? (messageIds != null || (ListenerUtil.mutListener.listen(5530) ? (messageIds.size() >= 0) : (ListenerUtil.mutListener.listen(5529) ? (messageIds.size() <= 0) : (ListenerUtil.mutListener.listen(5528) ? (messageIds.size() < 0) : (ListenerUtil.mutListener.listen(5527) ? (messageIds.size() != 0) : (ListenerUtil.mutListener.listen(5526) ? (messageIds.size() == 0) : (messageIds.size() > 0))))))) : (messageIds != null && (ListenerUtil.mutListener.listen(5530) ? (messageIds.size() >= 0) : (ListenerUtil.mutListener.listen(5529) ? (messageIds.size() <= 0) : (ListenerUtil.mutListener.listen(5528) ? (messageIds.size() < 0) : (ListenerUtil.mutListener.listen(5527) ? (messageIds.size() != 0) : (ListenerUtil.mutListener.listen(5526) ? (messageIds.size() == 0) : (messageIds.size() > 0))))))))) {
                                        if (!ListenerUtil.mutListener.listen(5535)) {
                                            {
                                                long _loopCounter40 = 0;
                                                for (int messageId : messageIds) {
                                                    ListenerUtil.loopListener.listen("_loopCounter40", ++_loopCounter40);
                                                    AbstractMessageModel model = messageService.getMessageModelFromId(messageId, originalMessageType);
                                                    if (!ListenerUtil.mutListener.listen(5534)) {
                                                        if ((ListenerUtil.mutListener.listen(5532) ? (model != null || model.getType() != MessageType.BALLOT) : (model != null && model.getType() != MessageType.BALLOT))) {
                                                            if (!ListenerUtil.mutListener.listen(5533)) {
                                                                originalMessageModels.add(model);
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
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5653)) {
            setupUI();
        }
    }

    @Nullable
    private String getMimeTypeFromContentUri(@NonNull Uri uri) {
        if (!ListenerUtil.mutListener.listen(5659)) {
            if (ContentResolver.SCHEME_CONTENT.equalsIgnoreCase(uri.getScheme())) {
                // query database for correct mime type as ACTION_SEND may have been called with a generic mime type such as "image/*"
                String[] proj = { DocumentsContract.Document.COLUMN_MIME_TYPE };
                try (Cursor cursor = getContentResolver().query(uri, proj, null, null, null)) {
                    if (!ListenerUtil.mutListener.listen(5656)) {
                        if ((ListenerUtil.mutListener.listen(5654) ? (cursor != null || cursor.moveToFirst()) : (cursor != null && cursor.moveToFirst()))) {
                            String mimeType = cursor.getString(cursor.getColumnIndex(DocumentsContract.Document.COLUMN_MIME_TYPE));
                            if (!ListenerUtil.mutListener.listen(5655)) {
                                if (!TestUtil.empty(mimeType)) {
                                    return mimeType;
                                }
                            }
                        }
                    }
                } catch (Exception ignored) {
                }
                String filemame = FileUtil.getFilenameFromUri(getContentResolver(), uri);
                if (!ListenerUtil.mutListener.listen(5658)) {
                    if (!TestUtil.empty(filemame)) {
                        String mimeType = FileUtil.getMimeTypeFromPath(filemame);
                        if (!ListenerUtil.mutListener.listen(5657)) {
                            if (!TestUtil.empty(mimeType)) {
                                return mimeType;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    private String getTextFromIntent(Intent intent) {
        String subject = intent.getStringExtra(Intent.EXTRA_SUBJECT);
        String text = intent.getStringExtra(Intent.EXTRA_TEXT);
        String textIntent;
        if ((ListenerUtil.mutListener.listen(5666) ? ((ListenerUtil.mutListener.listen(5665) ? (subject != null || (ListenerUtil.mutListener.listen(5664) ? (subject.length() >= 0) : (ListenerUtil.mutListener.listen(5663) ? (subject.length() <= 0) : (ListenerUtil.mutListener.listen(5662) ? (subject.length() < 0) : (ListenerUtil.mutListener.listen(5661) ? (subject.length() != 0) : (ListenerUtil.mutListener.listen(5660) ? (subject.length() == 0) : (subject.length() > 0))))))) : (subject != null && (ListenerUtil.mutListener.listen(5664) ? (subject.length() >= 0) : (ListenerUtil.mutListener.listen(5663) ? (subject.length() <= 0) : (ListenerUtil.mutListener.listen(5662) ? (subject.length() < 0) : (ListenerUtil.mutListener.listen(5661) ? (subject.length() != 0) : (ListenerUtil.mutListener.listen(5660) ? (subject.length() == 0) : (subject.length() > 0)))))))) || !subject.equals(text)) : ((ListenerUtil.mutListener.listen(5665) ? (subject != null || (ListenerUtil.mutListener.listen(5664) ? (subject.length() >= 0) : (ListenerUtil.mutListener.listen(5663) ? (subject.length() <= 0) : (ListenerUtil.mutListener.listen(5662) ? (subject.length() < 0) : (ListenerUtil.mutListener.listen(5661) ? (subject.length() != 0) : (ListenerUtil.mutListener.listen(5660) ? (subject.length() == 0) : (subject.length() > 0))))))) : (subject != null && (ListenerUtil.mutListener.listen(5664) ? (subject.length() >= 0) : (ListenerUtil.mutListener.listen(5663) ? (subject.length() <= 0) : (ListenerUtil.mutListener.listen(5662) ? (subject.length() < 0) : (ListenerUtil.mutListener.listen(5661) ? (subject.length() != 0) : (ListenerUtil.mutListener.listen(5660) ? (subject.length() == 0) : (subject.length() > 0)))))))) && !subject.equals(text)))) {
            textIntent = subject;
            if (!ListenerUtil.mutListener.listen(5668)) {
                if (!TextUtils.isEmpty(text)) {
                    if (!ListenerUtil.mutListener.listen(5667)) {
                        textIntent += " - " + text;
                    }
                }
            }
        } else {
            textIntent = text;
        }
        return textIntent;
    }

    private void addMediaItem(String mimeType, @NonNull Uri uri, @Nullable String caption) {
        if (!ListenerUtil.mutListener.listen(5677)) {
            if (ContentResolver.SCHEME_FILE.equalsIgnoreCase(uri.getScheme())) {
                String path = uri.getPath();
                File applicationDir = new File(getApplicationInfo().dataDir);
                if (!ListenerUtil.mutListener.listen(5676)) {
                    if (path != null) {
                        try {
                            String inputPath = new File(path).getCanonicalPath();
                            if (!ListenerUtil.mutListener.listen(5675)) {
                                if (inputPath.startsWith(applicationDir.getCanonicalPath())) {
                                    if (!ListenerUtil.mutListener.listen(5674)) {
                                        Toast.makeText(this, "Illegal path", Toast.LENGTH_SHORT).show();
                                    }
                                    return;
                                }
                            }
                        } catch (IOException e) {
                            if (!ListenerUtil.mutListener.listen(5672)) {
                                logger.error("Exception", e);
                            }
                            if (!ListenerUtil.mutListener.listen(5673)) {
                                Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                            return;
                        }
                    }
                }
            } else if (ContentResolver.SCHEME_CONTENT.equalsIgnoreCase(uri.getScheme())) {
                try {
                    if (!ListenerUtil.mutListener.listen(5671)) {
                        getContentResolver().takePersistableUriPermission(uri, Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    }
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(5669)) {
                        logger.info("Unable to take persistable uri permission");
                    }
                    if (!ListenerUtil.mutListener.listen(5670)) {
                        uri = FileUtil.getFileUri(uri);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5678)) {
            mediaItems.add(new MediaItem(uri, mimeType, caption));
        }
    }

    @SuppressLint("StaticFieldLeak")
    private void addNewContact(final String identity) {
        final ContactModel contactModel = contactService.getByIdentity(identity);
        if (!ListenerUtil.mutListener.listen(5688)) {
            if (contactModel == null) {
                if (!ListenerUtil.mutListener.listen(5680)) {
                    GenericProgressDialog.newInstance(R.string.creating_contact, R.string.please_wait).show(getSupportFragmentManager(), "pro");
                }
                if (!ListenerUtil.mutListener.listen(5687)) {
                    new AsyncTask<Void, Void, Void>() {

                        boolean fail = false;

                        ContactModel newContactModel = null;

                        @Override
                        protected Void doInBackground(Void... params) {
                            try {
                                if (!ListenerUtil.mutListener.listen(5682)) {
                                    newContactModel = contactService.createContactByIdentity(identity, false);
                                }
                            } catch (Exception e) {
                                if (!ListenerUtil.mutListener.listen(5681)) {
                                    fail = true;
                                }
                            }
                            return null;
                        }

                        @Override
                        protected void onPostExecute(Void result) {
                            if (!ListenerUtil.mutListener.listen(5683)) {
                                DialogUtil.dismissDialog(getSupportFragmentManager(), "pro", true);
                            }
                            if (!ListenerUtil.mutListener.listen(5686)) {
                                if (fail) {
                                    View rootView = getWindow().getDecorView().findViewById(android.R.id.content);
                                    if (!ListenerUtil.mutListener.listen(5685)) {
                                        Snackbar.make(rootView, R.string.contact_not_found, Snackbar.LENGTH_LONG).show();
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(5684)) {
                                        prepareComposeIntent(new ArrayList<>(Collections.singletonList(newContactModel)), false);
                                    }
                                }
                            }
                        }
                    }.execute();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(5679)) {
                    prepareComposeIntent(new ArrayList<>(Collections.singletonList(contactModel)), false);
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!ListenerUtil.mutListener.listen(5689)) {
            super.onCreateOptionsMenu(menu);
        }
        if (!ListenerUtil.mutListener.listen(5690)) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.activity_recipientlist, menu);
        }
        if (!ListenerUtil.mutListener.listen(5691)) {
            this.searchMenuItem = menu.findItem(R.id.menu_search_messages);
        }
        if (!ListenerUtil.mutListener.listen(5692)) {
            this.searchView = (ThreemaSearchView) this.searchMenuItem.getActionView();
        }
        if (!ListenerUtil.mutListener.listen(5698)) {
            if (this.searchView != null) {
                if (!ListenerUtil.mutListener.listen(5694)) {
                    this.searchView.setQueryHint(getString(R.string.hint_filter_list));
                }
                if (!ListenerUtil.mutListener.listen(5695)) {
                    this.searchView.setOnQueryTextListener(this);
                }
                if (!ListenerUtil.mutListener.listen(5697)) {
                    if (hideUi) {
                        if (!ListenerUtil.mutListener.listen(5696)) {
                            this.searchMenuItem.setVisible(false);
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(5693)) {
                    this.searchMenuItem.setVisible(false);
                }
            }
        }
        return true;
    }

    private MessageReceiver getMessageReceiver(Object model) {
        MessageReceiver messageReceiver = null;
        if (!ListenerUtil.mutListener.listen(5702)) {
            if (model instanceof ContactModel) {
                if (!ListenerUtil.mutListener.listen(5701)) {
                    messageReceiver = contactService.createReceiver((ContactModel) model);
                }
            } else if (model instanceof GroupModel) {
                if (!ListenerUtil.mutListener.listen(5700)) {
                    messageReceiver = groupService.createReceiver((GroupModel) model);
                }
            } else if (model instanceof DistributionListModel) {
                if (!ListenerUtil.mutListener.listen(5699)) {
                    messageReceiver = distributionListService.createReceiver((DistributionListModel) model);
                }
            }
        }
        return messageReceiver;
    }

    private void prepareComposeIntent(ArrayList<Object> recipients, boolean keepOriginalCaptions) {
        Intent intent = null;
        MessageReceiver messageReceiver = null;
        ArrayList<MessageReceiver> messageReceivers = new ArrayList<>(recipients.size());
        if (!ListenerUtil.mutListener.listen(5706)) {
            {
                long _loopCounter43 = 0;
                for (Object model : recipients) {
                    ListenerUtil.loopListener.listen("_loopCounter43", ++_loopCounter43);
                    if (!ListenerUtil.mutListener.listen(5703)) {
                        messageReceiver = getMessageReceiver(model);
                    }
                    if (!ListenerUtil.mutListener.listen(5705)) {
                        if (validateSendingPermission(messageReceiver)) {
                            if (!ListenerUtil.mutListener.listen(5704)) {
                                messageReceivers.add(messageReceiver);
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5707)) {
            intent = IntentDataUtil.getComposeIntentForReceivers(this, messageReceivers);
        }
        if (!ListenerUtil.mutListener.listen(5715)) {
            if ((ListenerUtil.mutListener.listen(5712) ? (originalMessageModels.size() >= 0) : (ListenerUtil.mutListener.listen(5711) ? (originalMessageModels.size() <= 0) : (ListenerUtil.mutListener.listen(5710) ? (originalMessageModels.size() < 0) : (ListenerUtil.mutListener.listen(5709) ? (originalMessageModels.size() != 0) : (ListenerUtil.mutListener.listen(5708) ? (originalMessageModels.size() == 0) : (originalMessageModels.size() > 0))))))) {
                if (!ListenerUtil.mutListener.listen(5714)) {
                    this.forwardMessages(messageReceivers.toArray(new MessageReceiver[0]), intent, keepOriginalCaptions);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(5713)) {
                    this.sendSharedMedia(messageReceivers.toArray(new MessageReceiver[0]), intent);
                }
            }
        }
    }

    private void sendSharedMedia(final MessageReceiver[] messageReceivers, final Intent intent) {
        if (!ListenerUtil.mutListener.listen(5734)) {
            if ((ListenerUtil.mutListener.listen(5717) ? ((ListenerUtil.mutListener.listen(5716) ? (messageReceivers.length == 1 || mediaItems.size() == 1) : (messageReceivers.length == 1 && mediaItems.size() == 1)) || TYPE_TEXT == mediaItems.get(0).getType()) : ((ListenerUtil.mutListener.listen(5716) ? (messageReceivers.length == 1 || mediaItems.size() == 1) : (messageReceivers.length == 1 && mediaItems.size() == 1)) && TYPE_TEXT == mediaItems.get(0).getType()))) {
                if (!ListenerUtil.mutListener.listen(5732)) {
                    intent.putExtra(ThreemaApplication.INTENT_DATA_TEXT, mediaItems.get(0).getCaption());
                }
                if (!ListenerUtil.mutListener.listen(5733)) {
                    startComposeActivity(intent);
                }
            } else if ((ListenerUtil.mutListener.listen(5728) ? ((ListenerUtil.mutListener.listen(5722) ? (messageReceivers.length >= 1) : (ListenerUtil.mutListener.listen(5721) ? (messageReceivers.length <= 1) : (ListenerUtil.mutListener.listen(5720) ? (messageReceivers.length < 1) : (ListenerUtil.mutListener.listen(5719) ? (messageReceivers.length != 1) : (ListenerUtil.mutListener.listen(5718) ? (messageReceivers.length == 1) : (messageReceivers.length > 1)))))) && (ListenerUtil.mutListener.listen(5727) ? (mediaItems.size() >= 0) : (ListenerUtil.mutListener.listen(5726) ? (mediaItems.size() <= 0) : (ListenerUtil.mutListener.listen(5725) ? (mediaItems.size() < 0) : (ListenerUtil.mutListener.listen(5724) ? (mediaItems.size() != 0) : (ListenerUtil.mutListener.listen(5723) ? (mediaItems.size() == 0) : (mediaItems.size() > 0))))))) : ((ListenerUtil.mutListener.listen(5722) ? (messageReceivers.length >= 1) : (ListenerUtil.mutListener.listen(5721) ? (messageReceivers.length <= 1) : (ListenerUtil.mutListener.listen(5720) ? (messageReceivers.length < 1) : (ListenerUtil.mutListener.listen(5719) ? (messageReceivers.length != 1) : (ListenerUtil.mutListener.listen(5718) ? (messageReceivers.length == 1) : (messageReceivers.length > 1)))))) || (ListenerUtil.mutListener.listen(5727) ? (mediaItems.size() >= 0) : (ListenerUtil.mutListener.listen(5726) ? (mediaItems.size() <= 0) : (ListenerUtil.mutListener.listen(5725) ? (mediaItems.size() < 0) : (ListenerUtil.mutListener.listen(5724) ? (mediaItems.size() != 0) : (ListenerUtil.mutListener.listen(5723) ? (mediaItems.size() == 0) : (mediaItems.size() > 0))))))))) {
                if (!ListenerUtil.mutListener.listen(5730)) {
                    messageService.sendMediaSingleThread(mediaItems, Arrays.asList(messageReceivers));
                }
                if (!ListenerUtil.mutListener.listen(5731)) {
                    startComposeActivity(intent);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(5729)) {
                    startComposeActivity(intent);
                }
            }
        }
    }

    void forwardSingleMessage(final MessageReceiver[] messageReceivers, final int i, final Intent intent, final boolean keepOriginalCaptions) {
        final AbstractMessageModel messageModel = originalMessageModels.get(i);
        if (!ListenerUtil.mutListener.listen(5762)) {
            fileService.loadDecryptedMessageFile(messageModel, new FileService.OnDecryptedFileComplete() {

                @Override
                public void complete(File decryptedFile) {
                    if (!ListenerUtil.mutListener.listen(5735)) {
                        RuntimeUtil.runOnUiThread(() -> DialogUtil.updateProgress(getSupportFragmentManager(), DIALOG_TAG_MULTISEND, i));
                    }
                    if (!ListenerUtil.mutListener.listen(5747)) {
                        if (messageModel.isAvailable()) {
                            Uri uri = null;
                            if (!ListenerUtil.mutListener.listen(5737)) {
                                if (decryptedFile != null) {
                                    if (!ListenerUtil.mutListener.listen(5736)) {
                                        uri = Uri.fromFile(decryptedFile);
                                    }
                                }
                            }
                            String caption = keepOriginalCaptions ? messageModel.getCaption() : captionText;
                            if (!ListenerUtil.mutListener.listen(5746)) {
                                switch(messageModel.getType()) {
                                    case IMAGE:
                                        if (!ListenerUtil.mutListener.listen(5738)) {
                                            sendForwardedMedia(messageReceivers, uri, caption, MediaItem.TYPE_IMAGE, null, FileData.RENDERING_MEDIA, null);
                                        }
                                        break;
                                    case VIDEO:
                                        if (!ListenerUtil.mutListener.listen(5739)) {
                                            sendForwardedMedia(messageReceivers, uri, caption, MediaItem.TYPE_VIDEO, null, FileData.RENDERING_MEDIA, null);
                                        }
                                        break;
                                    case VOICEMESSAGE:
                                        if (!ListenerUtil.mutListener.listen(5740)) {
                                            sendForwardedMedia(messageReceivers, uri, caption, MediaItem.TYPE_VOICEMESSAGE, MimeUtil.MIME_TYPE_AUDIO_AAC, FileData.RENDERING_MEDIA, null);
                                        }
                                        break;
                                    case FILE:
                                        int mediaType = MediaItem.TYPE_FILE;
                                        String mimeType = messageModel.getFileData().getMimeType();
                                        int renderingType = messageModel.getFileData().getRenderingType();
                                        if (!ListenerUtil.mutListener.listen(5742)) {
                                            if (messageModel.getFileData().getRenderingType() != FileData.RENDERING_DEFAULT) {
                                                if (!ListenerUtil.mutListener.listen(5741)) {
                                                    mediaType = MimeUtil.getMediaTypeFromMimeType(mimeType);
                                                }
                                            }
                                        }
                                        if (!ListenerUtil.mutListener.listen(5743)) {
                                            sendForwardedMedia(messageReceivers, uri, caption, mediaType, mimeType, renderingType, messageModel.getFileData().getFileName());
                                        }
                                        break;
                                    case LOCATION:
                                        if (!ListenerUtil.mutListener.listen(5744)) {
                                            sendLocationMessage(messageReceivers, messageModel.getLocationData());
                                        }
                                        break;
                                    case TEXT:
                                        if (!ListenerUtil.mutListener.listen(5745)) {
                                            sendTextMessage(messageReceivers, messageModel.getBody());
                                        }
                                        break;
                                    default:
                                        // unsupported message type
                                        break;
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(5760)) {
                        if ((ListenerUtil.mutListener.listen(5756) ? (i >= (ListenerUtil.mutListener.listen(5751) ? (originalMessageModels.size() % 1) : (ListenerUtil.mutListener.listen(5750) ? (originalMessageModels.size() / 1) : (ListenerUtil.mutListener.listen(5749) ? (originalMessageModels.size() * 1) : (ListenerUtil.mutListener.listen(5748) ? (originalMessageModels.size() + 1) : (originalMessageModels.size() - 1)))))) : (ListenerUtil.mutListener.listen(5755) ? (i <= (ListenerUtil.mutListener.listen(5751) ? (originalMessageModels.size() % 1) : (ListenerUtil.mutListener.listen(5750) ? (originalMessageModels.size() / 1) : (ListenerUtil.mutListener.listen(5749) ? (originalMessageModels.size() * 1) : (ListenerUtil.mutListener.listen(5748) ? (originalMessageModels.size() + 1) : (originalMessageModels.size() - 1)))))) : (ListenerUtil.mutListener.listen(5754) ? (i > (ListenerUtil.mutListener.listen(5751) ? (originalMessageModels.size() % 1) : (ListenerUtil.mutListener.listen(5750) ? (originalMessageModels.size() / 1) : (ListenerUtil.mutListener.listen(5749) ? (originalMessageModels.size() * 1) : (ListenerUtil.mutListener.listen(5748) ? (originalMessageModels.size() + 1) : (originalMessageModels.size() - 1)))))) : (ListenerUtil.mutListener.listen(5753) ? (i != (ListenerUtil.mutListener.listen(5751) ? (originalMessageModels.size() % 1) : (ListenerUtil.mutListener.listen(5750) ? (originalMessageModels.size() / 1) : (ListenerUtil.mutListener.listen(5749) ? (originalMessageModels.size() * 1) : (ListenerUtil.mutListener.listen(5748) ? (originalMessageModels.size() + 1) : (originalMessageModels.size() - 1)))))) : (ListenerUtil.mutListener.listen(5752) ? (i == (ListenerUtil.mutListener.listen(5751) ? (originalMessageModels.size() % 1) : (ListenerUtil.mutListener.listen(5750) ? (originalMessageModels.size() / 1) : (ListenerUtil.mutListener.listen(5749) ? (originalMessageModels.size() * 1) : (ListenerUtil.mutListener.listen(5748) ? (originalMessageModels.size() + 1) : (originalMessageModels.size() - 1)))))) : (i < (ListenerUtil.mutListener.listen(5751) ? (originalMessageModels.size() % 1) : (ListenerUtil.mutListener.listen(5750) ? (originalMessageModels.size() / 1) : (ListenerUtil.mutListener.listen(5749) ? (originalMessageModels.size() * 1) : (ListenerUtil.mutListener.listen(5748) ? (originalMessageModels.size() + 1) : (originalMessageModels.size() - 1)))))))))))) {
                            if (!ListenerUtil.mutListener.listen(5759)) {
                                forwardSingleMessage(messageReceivers, i + 1, intent, keepOriginalCaptions);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(5757)) {
                                DialogUtil.dismissDialog(getSupportFragmentManager(), DIALOG_TAG_MULTISEND, true);
                            }
                            if (!ListenerUtil.mutListener.listen(5758)) {
                                startComposeActivity(intent);
                            }
                        }
                    }
                }

                @Override
                public void error(String message) {
                    if (!ListenerUtil.mutListener.listen(5761)) {
                        RuntimeUtil.runOnUiThread(() -> SingleToast.getInstance().showLongText(getString(R.string.an_error_occurred_during_send)));
                    }
                }
            });
        }
    }

    @UiThread
    private void forwardMessages(final MessageReceiver[] messageReceivers, final Intent intent, boolean keepOriginalCaptions) {
        if (!ListenerUtil.mutListener.listen(5763)) {
            CancelableHorizontalProgressDialog.newInstance(R.string.sending_messages, 0, 0, originalMessageModels.size()).show(getSupportFragmentManager(), DIALOG_TAG_MULTISEND);
        }
        if (!ListenerUtil.mutListener.listen(5764)) {
            forwardSingleMessage(messageReceivers, 0, intent, keepOriginalCaptions);
        }
    }

    private void startComposeActivityAsync(final Intent intent) {
        if (!ListenerUtil.mutListener.listen(5765)) {
            if (intent == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(5766)) {
            RuntimeUtil.runOnUiThread(() -> startComposeActivity(intent));
        }
    }

    @UiThread
    private void startComposeActivity(Intent intent) {
        if (!ListenerUtil.mutListener.listen(5771)) {
            if (isCalledFromExternalApp()) {
                if (!ListenerUtil.mutListener.listen(5769)) {
                    startActivity(intent);
                }
                if (!ListenerUtil.mutListener.listen(5770)) {
                    finishAffinity();
                }
            } else {
                // we have to clear the backstack to prevent users from coming back here with the return key
                Intent upIntent = new Intent(this, HomeActivity.class);
                if (!ListenerUtil.mutListener.listen(5767)) {
                    TaskStackBuilder.create(this).addNextIntent(upIntent).addNextIntent(intent).startActivities();
                }
                if (!ListenerUtil.mutListener.listen(5768)) {
                    finish();
                }
            }
        }
    }

    public void prepareForwardingOrSharing(final ArrayList<Object> recipients) {
        if (!ListenerUtil.mutListener.listen(5822)) {
            if ((ListenerUtil.mutListener.listen(5782) ? ((ListenerUtil.mutListener.listen(5776) ? (mediaItems.size() >= 0) : (ListenerUtil.mutListener.listen(5775) ? (mediaItems.size() <= 0) : (ListenerUtil.mutListener.listen(5774) ? (mediaItems.size() < 0) : (ListenerUtil.mutListener.listen(5773) ? (mediaItems.size() != 0) : (ListenerUtil.mutListener.listen(5772) ? (mediaItems.size() == 0) : (mediaItems.size() > 0)))))) && (ListenerUtil.mutListener.listen(5781) ? (originalMessageModels.size() >= 0) : (ListenerUtil.mutListener.listen(5780) ? (originalMessageModels.size() <= 0) : (ListenerUtil.mutListener.listen(5779) ? (originalMessageModels.size() < 0) : (ListenerUtil.mutListener.listen(5778) ? (originalMessageModels.size() != 0) : (ListenerUtil.mutListener.listen(5777) ? (originalMessageModels.size() == 0) : (originalMessageModels.size() > 0))))))) : ((ListenerUtil.mutListener.listen(5776) ? (mediaItems.size() >= 0) : (ListenerUtil.mutListener.listen(5775) ? (mediaItems.size() <= 0) : (ListenerUtil.mutListener.listen(5774) ? (mediaItems.size() < 0) : (ListenerUtil.mutListener.listen(5773) ? (mediaItems.size() != 0) : (ListenerUtil.mutListener.listen(5772) ? (mediaItems.size() == 0) : (mediaItems.size() > 0)))))) || (ListenerUtil.mutListener.listen(5781) ? (originalMessageModels.size() >= 0) : (ListenerUtil.mutListener.listen(5780) ? (originalMessageModels.size() <= 0) : (ListenerUtil.mutListener.listen(5779) ? (originalMessageModels.size() < 0) : (ListenerUtil.mutListener.listen(5778) ? (originalMessageModels.size() != 0) : (ListenerUtil.mutListener.listen(5777) ? (originalMessageModels.size() == 0) : (originalMessageModels.size() > 0))))))))) {
                String recipientName = "";
                if (!ListenerUtil.mutListener.listen(5821)) {
                    if (!((ListenerUtil.mutListener.listen(5785) ? (((ListenerUtil.mutListener.listen(5783) ? (mediaItems.size() == 1 || MimeUtil.isTextFile(mediaItems.get(0).getMimeType())) : (mediaItems.size() == 1 && MimeUtil.isTextFile(mediaItems.get(0).getMimeType())))) && ((ListenerUtil.mutListener.listen(5784) ? (originalMessageModels.size() == 1 || originalMessageModels.get(0).getType() == MessageType.TEXT) : (originalMessageModels.size() == 1 && originalMessageModels.get(0).getType() == MessageType.TEXT)))) : (((ListenerUtil.mutListener.listen(5783) ? (mediaItems.size() == 1 || MimeUtil.isTextFile(mediaItems.get(0).getMimeType())) : (mediaItems.size() == 1 && MimeUtil.isTextFile(mediaItems.get(0).getMimeType())))) || ((ListenerUtil.mutListener.listen(5784) ? (originalMessageModels.size() == 1 || originalMessageModels.get(0).getType() == MessageType.TEXT) : (originalMessageModels.size() == 1 && originalMessageModels.get(0).getType() == MessageType.TEXT))))))) {
                        if (!ListenerUtil.mutListener.listen(5797)) {
                            {
                                long _loopCounter44 = 0;
                                for (Object model : recipients) {
                                    ListenerUtil.loopListener.listen("_loopCounter44", ++_loopCounter44);
                                    if (!ListenerUtil.mutListener.listen(5792)) {
                                        if ((ListenerUtil.mutListener.listen(5790) ? (recipientName.length() >= 0) : (ListenerUtil.mutListener.listen(5789) ? (recipientName.length() <= 0) : (ListenerUtil.mutListener.listen(5788) ? (recipientName.length() < 0) : (ListenerUtil.mutListener.listen(5787) ? (recipientName.length() != 0) : (ListenerUtil.mutListener.listen(5786) ? (recipientName.length() == 0) : (recipientName.length() > 0))))))) {
                                            if (!ListenerUtil.mutListener.listen(5791)) {
                                                recipientName += ", ";
                                            }
                                        }
                                    }
                                    if (!ListenerUtil.mutListener.listen(5796)) {
                                        if (model instanceof ContactModel) {
                                            if (!ListenerUtil.mutListener.listen(5795)) {
                                                recipientName += NameUtil.getDisplayName((ContactModel) model);
                                            }
                                        } else if (model instanceof GroupModel) {
                                            if (!ListenerUtil.mutListener.listen(5794)) {
                                                recipientName += NameUtil.getDisplayName((GroupModel) model, this.groupService);
                                            }
                                        } else if (model instanceof DistributionListModel) {
                                            if (!ListenerUtil.mutListener.listen(5793)) {
                                                recipientName += NameUtil.getDisplayName((DistributionListModel) model, this.distributionListService);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(5820)) {
                            if ((ListenerUtil.mutListener.listen(5802) ? (originalMessageModels.size() >= 0) : (ListenerUtil.mutListener.listen(5801) ? (originalMessageModels.size() <= 0) : (ListenerUtil.mutListener.listen(5800) ? (originalMessageModels.size() < 0) : (ListenerUtil.mutListener.listen(5799) ? (originalMessageModels.size() != 0) : (ListenerUtil.mutListener.listen(5798) ? (originalMessageModels.size() == 0) : (originalMessageModels.size() > 0))))))) {
                                // forwarded content of any type
                                String presetCaption = null;
                                boolean expandable = false;
                                boolean hasCaptions = false;
                                if (!ListenerUtil.mutListener.listen(5817)) {
                                    if (originalMessageModels.size() == 1) {
                                        if (!ListenerUtil.mutListener.listen(5811)) {
                                            presetCaption = originalMessageModels.get(0).getCaption();
                                        }
                                        if (!ListenerUtil.mutListener.listen(5816)) {
                                            if ((ListenerUtil.mutListener.listen(5814) ? ((ListenerUtil.mutListener.listen(5813) ? ((ListenerUtil.mutListener.listen(5812) ? (originalMessageModels.get(0).getType() == MessageType.VIDEO && originalMessageModels.get(0).getType() == MessageType.IMAGE) : (originalMessageModels.get(0).getType() == MessageType.VIDEO || originalMessageModels.get(0).getType() == MessageType.IMAGE)) && originalMessageModels.get(0).getType() == MessageType.VOICEMESSAGE) : ((ListenerUtil.mutListener.listen(5812) ? (originalMessageModels.get(0).getType() == MessageType.VIDEO && originalMessageModels.get(0).getType() == MessageType.IMAGE) : (originalMessageModels.get(0).getType() == MessageType.VIDEO || originalMessageModels.get(0).getType() == MessageType.IMAGE)) || originalMessageModels.get(0).getType() == MessageType.VOICEMESSAGE)) && originalMessageModels.get(0).getType() == MessageType.FILE) : ((ListenerUtil.mutListener.listen(5813) ? ((ListenerUtil.mutListener.listen(5812) ? (originalMessageModels.get(0).getType() == MessageType.VIDEO && originalMessageModels.get(0).getType() == MessageType.IMAGE) : (originalMessageModels.get(0).getType() == MessageType.VIDEO || originalMessageModels.get(0).getType() == MessageType.IMAGE)) && originalMessageModels.get(0).getType() == MessageType.VOICEMESSAGE) : ((ListenerUtil.mutListener.listen(5812) ? (originalMessageModels.get(0).getType() == MessageType.VIDEO && originalMessageModels.get(0).getType() == MessageType.IMAGE) : (originalMessageModels.get(0).getType() == MessageType.VIDEO || originalMessageModels.get(0).getType() == MessageType.IMAGE)) || originalMessageModels.get(0).getType() == MessageType.VOICEMESSAGE)) || originalMessageModels.get(0).getType() == MessageType.FILE))) {
                                                if (!ListenerUtil.mutListener.listen(5815)) {
                                                    expandable = true;
                                                }
                                            }
                                        }
                                    } else {
                                        if (!ListenerUtil.mutListener.listen(5810)) {
                                            {
                                                long _loopCounter47 = 0;
                                                for (AbstractMessageModel messageModel : originalMessageModels) {
                                                    ListenerUtil.loopListener.listen("_loopCounter47", ++_loopCounter47);
                                                    if (!ListenerUtil.mutListener.listen(5809)) {
                                                        if ((ListenerUtil.mutListener.listen(5807) ? (messageModel.getCaption() != null || !TextUtils.isEmpty(messageModel.getCaption())) : (messageModel.getCaption() != null && !TextUtils.isEmpty(messageModel.getCaption())))) {
                                                            if (!ListenerUtil.mutListener.listen(5808)) {
                                                                hasCaptions = true;
                                                            }
                                                            break;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                ThreemaDialogFragment alertDialog;
                                if (!expandable) {
                                    alertDialog = TextWithCheckboxDialog.newInstance(getString(R.string.really_forward, recipientName), hasCaptions ? R.string.forward_captions : 0, R.string.send, R.string.cancel);
                                } else {
                                    alertDialog = ExpandableTextEntryDialog.newInstance(getString(R.string.really_forward, recipientName), R.string.add_caption_hint, presetCaption, R.string.send, R.string.cancel, expandable);
                                }
                                if (!ListenerUtil.mutListener.listen(5818)) {
                                    alertDialog.setData(recipients);
                                }
                                if (!ListenerUtil.mutListener.listen(5819)) {
                                    alertDialog.show(getSupportFragmentManager(), null);
                                }
                            } else {
                                // content shared by external apps may be referred to by content URIs which will not survive this activity. so in order to be able to use them later we have to copy these files to a local directory first
                                String finalRecipientName = recipientName;
                                if (!ListenerUtil.mutListener.listen(5803)) {
                                    GenericProgressDialog.newInstance(R.string.importing_files, R.string.please_wait).show(getSupportFragmentManager(), DIALOG_TAG_FILECOPY);
                                }
                                try {
                                    if (!ListenerUtil.mutListener.listen(5806)) {
                                        CompletableFuture.runAsync(copyFilesRunnable, Executors.newSingleThreadExecutor()).thenRunAsync(() -> {
                                            int numEditableMedia = 0;
                                            {
                                                long _loopCounter45 = 0;
                                                for (MediaItem mediaItem : mediaItems) {
                                                    ListenerUtil.loopListener.listen("_loopCounter45", ++_loopCounter45);
                                                    String mimeType = mediaItem.getMimeType();
                                                    if (MimeUtil.isImageFile(mimeType) || MimeUtil.isVideoFile(mimeType)) {
                                                        numEditableMedia++;
                                                    }
                                                }
                                            }
                                            DialogUtil.dismissDialog(getSupportFragmentManager(), DIALOG_TAG_FILECOPY, true);
                                            if (numEditableMedia == mediaItems.size()) {
                                                // all files are either images or videos => redirect to SendMediaActivity
                                                recipientMessageReceivers.clear();
                                                {
                                                    long _loopCounter46 = 0;
                                                    for (Object model : recipients) {
                                                        ListenerUtil.loopListener.listen("_loopCounter46", ++_loopCounter46);
                                                        MessageReceiver messageReceiver = getMessageReceiver(model);
                                                        if (validateSendingPermission(messageReceiver)) {
                                                            recipientMessageReceivers.add(messageReceiver);
                                                        }
                                                    }
                                                }
                                                if (recipientMessageReceivers.size() > 0) {
                                                    Intent intent = IntentDataUtil.addMessageReceiversToIntent(new Intent(RecipientListBaseActivity.this, SendMediaActivity.class), recipientMessageReceivers.toArray(new MessageReceiver[0]));
                                                    intent.putExtra(SendMediaActivity.EXTRA_MEDIA_ITEMS, (ArrayList<MediaItem>) mediaItems);
                                                    intent.putExtra(ThreemaApplication.INTENT_DATA_TEXT, finalRecipientName);
                                                    startActivityForResult(intent, ThreemaActivity.ACTIVITY_ID_SEND_MEDIA);
                                                }
                                            } else {
                                                // mixed media
                                                ExpandableTextEntryDialog alertDialog = ExpandableTextEntryDialog.newInstance(getString(R.string.really_send, finalRecipientName), R.string.add_caption_hint, captionText, R.string.send, R.string.cancel, mediaItems.size() == 1);
                                                alertDialog.setData(recipients);
                                                alertDialog.show(getSupportFragmentManager(), null);
                                            }
                                        }, ContextCompat.getMainExecutor(getApplicationContext()));
                                    }
                                } catch (Exception e) {
                                    if (!ListenerUtil.mutListener.listen(5804)) {
                                        logger.error("Exception", e);
                                    }
                                    if (!ListenerUtil.mutListener.listen(5805)) {
                                        finish();
                                    }
                                    return;
                                }
                            }
                        }
                        return;
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5823)) {
            // fallback to starting new chat
            prepareComposeIntent(recipients, false);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!ListenerUtil.mutListener.listen(5827)) {
            switch(item.getItemId()) {
                case android.R.id.home:
                    if (!ListenerUtil.mutListener.listen(5826)) {
                        if (isCalledFromExternalApp()) {
                            if (!ListenerUtil.mutListener.listen(5825)) {
                                finish();
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(5824)) {
                                NavigationUtil.navigateUpToHome(this);
                            }
                        }
                    }
                    return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        if (!ListenerUtil.mutListener.listen(5828)) {
            logger.debug("onResume");
        }
        if (!ListenerUtil.mutListener.listen(5829)) {
            super.onResume();
        }
    }

    @Override
    protected void onPause() {
        if (!ListenerUtil.mutListener.listen(5830)) {
            logger.debug("onPause");
        }
        if (!ListenerUtil.mutListener.listen(5831)) {
            super.onPause();
        }
    }

    @Override
    public void onUserInteraction() {
        if (!ListenerUtil.mutListener.listen(5832)) {
            logger.debug("onUserInteraction");
        }
        if (!ListenerUtil.mutListener.listen(5833)) {
            super.onUserInteraction();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!ListenerUtil.mutListener.listen(5838)) {
            switch(requestCode) {
                case ACTIVITY_ID_SEND_MEDIA:
                    if (!ListenerUtil.mutListener.listen(5836)) {
                        if (resultCode == RESULT_OK) {
                            if (!ListenerUtil.mutListener.listen(5835)) {
                                startComposeActivityAsync(IntentDataUtil.getComposeIntentForReceivers(this, (ArrayList<MessageReceiver>) recipientMessageReceivers));
                            }
                        } else if (hideUi) {
                            if (!ListenerUtil.mutListener.listen(5834)) {
                                finish();
                            }
                        }
                    }
                    break;
                default:
                    if (!ListenerUtil.mutListener.listen(5837)) {
                        super.onActivityResult(requestCode, resultCode, data);
                    }
            }
        }
    }

    public class UserGroupPagerAdapter extends FragmentPagerAdapter {

        // these globals are not persistent across orientation changes (at least in Android <= 4.1)!
        SparseArray<Fragment> registeredFragments = new SparseArray<Fragment>();

        public UserGroupPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            if (!ListenerUtil.mutListener.listen(5844)) {
                switch(tabs.get(position)) {
                    case FRAGMENT_USERS:
                        if (!ListenerUtil.mutListener.listen(5839)) {
                            fragment = new UserListFragment();
                        }
                        break;
                    case FRAGMENT_GROUPS:
                        if (!ListenerUtil.mutListener.listen(5840)) {
                            fragment = new GroupListFragment();
                        }
                        break;
                    case FRAGMENT_RECENT:
                        if (!ListenerUtil.mutListener.listen(5841)) {
                            fragment = new RecentListFragment();
                        }
                        break;
                    case FRAGMENT_DISTRIBUTION_LIST:
                        if (!ListenerUtil.mutListener.listen(5842)) {
                            fragment = new DistributionListFragment();
                        }
                        break;
                    case FRAGMENT_WORK_USERS:
                        if (!ListenerUtil.mutListener.listen(5843)) {
                            fragment = new WorkUserListFragment();
                        }
                        break;
                }
            }
            if (!ListenerUtil.mutListener.listen(5847)) {
                if (fragment != null) {
                    Bundle args = new Bundle();
                    if (!ListenerUtil.mutListener.listen(5845)) {
                        args.putBoolean(RecipientListFragment.ARGUMENT_MULTI_SELECT, multiSelect);
                    }
                    if (!ListenerUtil.mutListener.listen(5846)) {
                        fragment.setArguments(args);
                    }
                }
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return tabs.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (!ListenerUtil.mutListener.listen(5848)) {
                switch(tabs.get(position)) {
                    case FRAGMENT_USERS:
                        return getString(R.string.title_tab_users).toUpperCase();
                    case FRAGMENT_GROUPS:
                        return getString(R.string.title_tab_groups).toUpperCase();
                    case FRAGMENT_RECENT:
                        return getString(R.string.title_tab_recent).toUpperCase();
                    case FRAGMENT_DISTRIBUTION_LIST:
                        return getString(R.string.title_tab_distribution_list).toUpperCase();
                    case FRAGMENT_WORK_USERS:
                        return getString(R.string.title_tab_work_users).toUpperCase();
                }
            }
            return null;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            Fragment fragment = (Fragment) super.instantiateItem(container, position);
            if (!ListenerUtil.mutListener.listen(5849)) {
                registeredFragments.put(position, fragment);
            }
            return fragment;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            if (!ListenerUtil.mutListener.listen(5850)) {
                registeredFragments.remove(position);
            }
            if (!ListenerUtil.mutListener.listen(5851)) {
                super.destroyItem(container, position, object);
            }
        }

        public Fragment getRegisteredFragment(int position) {
            return registeredFragments.get(position);
        }
    }

    public boolean getShowDistributionLists() {
        return tabs.contains(FRAGMENT_RECENT);
    }

    @AnyThread
    private void sendForwardedMedia(final MessageReceiver[] messageReceivers, final Uri uri, final String caption, final int type, @Nullable final String mimeType, @FileData.RenderingType final int renderingType, final String filename) {
        final MediaItem mediaItem = new MediaItem(uri, type);
        if (!ListenerUtil.mutListener.listen(5853)) {
            if (mimeType != null) {
                if (!ListenerUtil.mutListener.listen(5852)) {
                    mediaItem.setMimeType(mimeType);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5860)) {
            if ((ListenerUtil.mutListener.listen(5858) ? (renderingType >= -1) : (ListenerUtil.mutListener.listen(5857) ? (renderingType <= -1) : (ListenerUtil.mutListener.listen(5856) ? (renderingType > -1) : (ListenerUtil.mutListener.listen(5855) ? (renderingType < -1) : (ListenerUtil.mutListener.listen(5854) ? (renderingType == -1) : (renderingType != -1))))))) {
                if (!ListenerUtil.mutListener.listen(5859)) {
                    mediaItem.setRenderingType(renderingType);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5861)) {
            mediaItem.setCaption(caption);
        }
        if (!ListenerUtil.mutListener.listen(5863)) {
            if (!TestUtil.empty(filename)) {
                if (!ListenerUtil.mutListener.listen(5862)) {
                    mediaItem.setFilename(filename);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5867)) {
            if (renderingType == FileData.RENDERING_MEDIA) {
                if (!ListenerUtil.mutListener.listen(5866)) {
                    if (type == MediaItem.TYPE_VIDEO) {
                        if (!ListenerUtil.mutListener.listen(5865)) {
                            // do not re-transcode forwarded videos
                            mediaItem.setVideoSize(PreferenceService.VideoSize_ORIGINAL);
                        }
                    } else if (type == MediaItem.TYPE_IMAGE) {
                        if (!ListenerUtil.mutListener.listen(5864)) {
                            // do not scale forwarded images
                            mediaItem.setImageScale(PreferenceService.ImageScale_ORIGINAL);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5868)) {
            messageService.sendMediaSingleThread(Collections.singletonList(mediaItem), Arrays.asList(messageReceivers));
        }
    }

    @WorkerThread
    private void sendLocationMessage(final MessageReceiver[] messageReceivers, LocationDataModel locationData) {
        final Location location = new Location("");
        if (!ListenerUtil.mutListener.listen(5869)) {
            location.setLatitude(locationData.getLatitude());
        }
        if (!ListenerUtil.mutListener.listen(5870)) {
            location.setLongitude(locationData.getLongitude());
        }
        if (!ListenerUtil.mutListener.listen(5871)) {
            location.setAccuracy(locationData.getAccuracy());
        }
        final String poiName = locationData.getPoi();
        if (!ListenerUtil.mutListener.listen(5876)) {
            LocationMessageSendAction.getInstance().sendLocationMessage(messageReceivers, location, poiName, new SendAction.ActionHandler() {

                @Override
                public void onError(final String errorMessage) {
                    if (!ListenerUtil.mutListener.listen(5872)) {
                        RuntimeUtil.runOnUiThread(() -> Toast.makeText(RecipientListBaseActivity.this, errorMessage, Toast.LENGTH_SHORT).show());
                    }
                    if (!ListenerUtil.mutListener.listen(5874)) {
                        if (hideUi) {
                            if (!ListenerUtil.mutListener.listen(5873)) {
                                finish();
                            }
                        }
                    }
                }

                @Override
                public void onWarning(String warning, boolean continueAction) {
                }

                @Override
                public void onProgress(int progress, int total) {
                }

                @Override
                public void onCompleted() {
                    if (!ListenerUtil.mutListener.listen(5875)) {
                        startComposeActivityAsync(null);
                    }
                }
            });
        }
    }

    @WorkerThread
    private void sendTextMessage(final MessageReceiver[] messageReceivers, final String text) {
        if (!ListenerUtil.mutListener.listen(5877)) {
            logger.debug("sendTextMessage");
        }
        if (!ListenerUtil.mutListener.listen(5881)) {
            TextMessageSendAction.getInstance().sendTextMessage(messageReceivers, text, new SendAction.ActionHandler() {

                @Override
                public void onError(final String errorMessage) {
                    if (!ListenerUtil.mutListener.listen(5878)) {
                        RuntimeUtil.runOnUiThread(() -> Toast.makeText(RecipientListBaseActivity.this, errorMessage, Toast.LENGTH_SHORT).show());
                    }
                    if (!ListenerUtil.mutListener.listen(5880)) {
                        if (hideUi) {
                            if (!ListenerUtil.mutListener.listen(5879)) {
                                finish();
                            }
                        }
                    }
                }

                @Override
                public void onWarning(String warning, boolean continueAction) {
                }

                @Override
                public void onProgress(int progress, int total) {
                }

                @Override
                public void onCompleted() {
                }
            });
        }
    }

    @Override
    public void onCancel(String tag, Object object) {
        if (!ListenerUtil.mutListener.listen(5883)) {
            if (hideUi) {
                if (!ListenerUtil.mutListener.listen(5882)) {
                    finish();
                }
            }
        }
    }

    // return from ExpandableTextEntryDialog
    @Override
    public void onYes(String tag, Object data, String text) {
        if (!ListenerUtil.mutListener.listen(5884)) {
            this.captionText = text;
        }
        if (!ListenerUtil.mutListener.listen(5889)) {
            if (data instanceof ArrayList) {
                if (!ListenerUtil.mutListener.listen(5887)) {
                    if (!TestUtil.empty(text)) {
                        if (!ListenerUtil.mutListener.listen(5886)) {
                            {
                                long _loopCounter48 = 0;
                                for (MediaItem mediaItem : mediaItems) {
                                    ListenerUtil.loopListener.listen("_loopCounter48", ++_loopCounter48);
                                    if (!ListenerUtil.mutListener.listen(5885)) {
                                        mediaItem.setCaption(text);
                                    }
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(5888)) {
                    prepareComposeIntent((ArrayList<Object>) data, false);
                }
            }
        }
    }

    // return from TextWithCheckboxDialog
    @Override
    public void onYes(String tag, Object data, boolean checked) {
        if (!ListenerUtil.mutListener.listen(5891)) {
            if (data instanceof ArrayList) {
                if (!ListenerUtil.mutListener.listen(5890)) {
                    prepareComposeIntent((ArrayList<Object>) data, checked);
                }
            }
        }
    }

    @Override
    public void onNo(String tag) {
        if (!ListenerUtil.mutListener.listen(5893)) {
            if (hideUi) {
                if (!ListenerUtil.mutListener.listen(5892)) {
                    finish();
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (!ListenerUtil.mutListener.listen(5894)) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
        if (!ListenerUtil.mutListener.listen(5907)) {
            if ((ListenerUtil.mutListener.listen(5900) ? ((ListenerUtil.mutListener.listen(5899) ? (grantResults.length >= 0) : (ListenerUtil.mutListener.listen(5898) ? (grantResults.length <= 0) : (ListenerUtil.mutListener.listen(5897) ? (grantResults.length < 0) : (ListenerUtil.mutListener.listen(5896) ? (grantResults.length != 0) : (ListenerUtil.mutListener.listen(5895) ? (grantResults.length == 0) : (grantResults.length > 0)))))) || grantResults[0] == PackageManager.PERMISSION_GRANTED) : ((ListenerUtil.mutListener.listen(5899) ? (grantResults.length >= 0) : (ListenerUtil.mutListener.listen(5898) ? (grantResults.length <= 0) : (ListenerUtil.mutListener.listen(5897) ? (grantResults.length < 0) : (ListenerUtil.mutListener.listen(5896) ? (grantResults.length != 0) : (ListenerUtil.mutListener.listen(5895) ? (grantResults.length == 0) : (grantResults.length > 0)))))) && grantResults[0] == PackageManager.PERMISSION_GRANTED))) {
                if (!ListenerUtil.mutListener.listen(5906)) {
                    switch(requestCode) {
                        case REQUEST_READ_EXTERNAL_STORAGE:
                            if (!ListenerUtil.mutListener.listen(5905)) {
                                setupUI();
                            }
                            break;
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(5904)) {
                    switch(requestCode) {
                        case REQUEST_READ_EXTERNAL_STORAGE:
                            if (!ListenerUtil.mutListener.listen(5902)) {
                                if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                                    if (!ListenerUtil.mutListener.listen(5901)) {
                                        Toast.makeText(this, R.string.permission_storage_required, Toast.LENGTH_LONG).show();
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(5903)) {
                                finish();
                            }
                            break;
                    }
                }
            }
        }
    }

    public boolean isCalledFromExternalApp() {
        return false;
    }
}
