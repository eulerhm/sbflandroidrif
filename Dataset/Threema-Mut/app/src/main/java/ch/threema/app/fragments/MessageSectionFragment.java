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
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.activities.ComposeMessageActivity;
import ch.threema.app.activities.ContactDetailActivity;
import ch.threema.app.activities.DistributionListAddActivity;
import ch.threema.app.activities.RecipientListBaseActivity;
import ch.threema.app.activities.ThreemaActivity;
import ch.threema.app.adapters.MessageListAdapter;
import ch.threema.app.archive.ArchiveActivity;
import ch.threema.app.asynctasks.DeleteDistributionListAsyncTask;
import ch.threema.app.asynctasks.DeleteGroupAsyncTask;
import ch.threema.app.asynctasks.DeleteMyGroupAsyncTask;
import ch.threema.app.asynctasks.EmptyChatAsyncTask;
import ch.threema.app.asynctasks.LeaveGroupAsyncTask;
import ch.threema.app.backuprestore.BackupChatService;
import ch.threema.app.collections.Functional;
import ch.threema.app.collections.IPredicateNonNull;
import ch.threema.app.dialogs.CancelableGenericProgressDialog;
import ch.threema.app.dialogs.GenericAlertDialog;
import ch.threema.app.dialogs.PasswordEntryDialog;
import ch.threema.app.dialogs.SelectorDialog;
import ch.threema.app.dialogs.SimpleStringAlertDialog;
import ch.threema.app.listeners.ChatListener;
import ch.threema.app.listeners.ContactListener;
import ch.threema.app.listeners.ContactSettingsListener;
import ch.threema.app.listeners.ConversationListener;
import ch.threema.app.listeners.GroupListener;
import ch.threema.app.listeners.SynchronizeContactsListener;
import ch.threema.app.managers.ListenerManager;
import ch.threema.app.managers.ServiceManager;
import ch.threema.app.messagereceiver.ContactMessageReceiver;
import ch.threema.app.messagereceiver.GroupMessageReceiver;
import ch.threema.app.messagereceiver.MessageReceiver;
import ch.threema.app.preference.SettingsActivity;
import ch.threema.app.preference.SettingsSecurityFragment;
import ch.threema.app.routines.SynchronizeContactsRoutine;
import ch.threema.app.services.ContactService;
import ch.threema.app.services.ConversationService;
import ch.threema.app.services.ConversationTagService;
import ch.threema.app.services.ConversationTagServiceImpl;
import ch.threema.app.services.DeadlineListService;
import ch.threema.app.services.DistributionListService;
import ch.threema.app.services.FileService;
import ch.threema.app.services.GroupService;
import ch.threema.app.services.LockAppService;
import ch.threema.app.services.MessageService;
import ch.threema.app.services.PreferenceService;
import ch.threema.app.services.RingtoneService;
import ch.threema.app.ui.EmptyRecyclerView;
import ch.threema.app.ui.EmptyView;
import ch.threema.app.ui.ResumePauseHandler;
import ch.threema.app.utils.AnimationUtil;
import ch.threema.app.utils.AppRestrictionUtil;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.DialogUtil;
import ch.threema.app.utils.FileUtil;
import ch.threema.app.utils.HiddenChatUtil;
import ch.threema.app.utils.IntentDataUtil;
import ch.threema.app.utils.LogUtil;
import ch.threema.app.utils.MimeUtil;
import ch.threema.app.utils.RuntimeUtil;
import ch.threema.app.utils.TestUtil;
import ch.threema.app.utils.ViewUtil;
import ch.threema.base.ThreemaException;
import ch.threema.localcrypto.MasterKeyLockedException;
import ch.threema.storage.models.ContactModel;
import ch.threema.storage.models.ConversationModel;
import ch.threema.storage.models.DistributionListModel;
import ch.threema.storage.models.GroupModel;
import ch.threema.storage.models.TagModel;
import static android.view.MenuItem.SHOW_AS_ACTION_ALWAYS;
import static android.view.MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW;
import static android.view.MenuItem.SHOW_AS_ACTION_NEVER;
import static ch.threema.app.ThreemaApplication.MAX_PW_LENGTH_BACKUP;
import static ch.threema.app.ThreemaApplication.MIN_PW_LENGTH_BACKUP;
import static ch.threema.app.managers.ListenerManager.conversationListeners;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class MessageSectionFragment extends MainFragment implements PasswordEntryDialog.PasswordEntryDialogClickListener, GenericAlertDialog.DialogClickListener, CancelableGenericProgressDialog.ProgressDialogClickListener, MessageListAdapter.ItemClickListener, SelectorDialog.SelectorDialogClickListener {

    private static final Logger logger = LoggerFactory.getLogger(MessageSectionFragment.class);

    private static final int PERMISSION_REQUEST_SHARE_THREAD = 1;

    private static final int ID_RETURN_FROM_SECURITY_SETTINGS = 33211;

    private static final int TEMP_MESSAGES_FILE_DELETE_WAIT_TIME = 2 * 60 * 1000;

    private static final String DIALOG_TAG_PREPARING_MESSAGES = "progressMsgs";

    private static final String DIALOG_TAG_SHARE_CHAT = "shareChat";

    private static final String DIALOG_TAG_REALLY_HIDE_THREAD = "lockC";

    private static final String DIALOG_TAG_HIDE_THREAD_EXPLAIN = "hideEx";

    private static final String DIALOG_TAG_SELECT_DELETE_ACTION = "sel";

    private static final String DIALOG_TAG_REALLY_LEAVE_GROUP = "rlg";

    private static final String DIALOG_TAG_REALLY_DELETE_MY_GROUP = "rdmg";

    private static final String DIALOG_TAG_REALLY_DELETE_GROUP = "rdgcc";

    private static final String DIALOG_TAG_REALLY_DELETE_DISTRIBUTION_LIST = "rddl";

    private static final String DIALOG_TAG_REALLY_EMPTY_CHAT = "rdec";

    private static final int ID_PRIVATE_TO_PUBLIC = 8111;

    private static final int TAG_EMPTY_CHAT = 1;

    private static final int TAG_DELETE_DISTRIBUTION_LIST = 2;

    private static final int TAG_LEAVE_GROUP = 3;

    private static final int TAG_DELETE_MY_GROUP = 4;

    private static final int TAG_DELETE_GROUP = 5;

    private static final int TAG_SET_PRIVATE = 7;

    private static final int TAG_UNSET_PRIVATE = 8;

    private static final int TAG_SHARE = 9;

    private static final int TAG_DELETE_LEFT_GROUP = 10;

    private static final int TAG_EDIT_GROUP = 11;

    private static final int TAG_MARK_READ = 12;

    private static final int TAG_MARK_UNREAD = 13;

    private static final String BUNDLE_FILTER_QUERY = "filterQuery";

    private static String highlightUid;

    private ServiceManager serviceManager;

    private ConversationService conversationService;

    private ContactService contactService;

    private GroupService groupService;

    private MessageService messageService;

    private DistributionListService distributionListService;

    private BackupChatService backupChatService;

    private DeadlineListService mutedChatsListService, mentionOnlyChatsListService, hiddenChatsListService;

    private ConversationTagService conversationTagService;

    private RingtoneService ringtoneService;

    private FileService fileService;

    private PreferenceService preferenceService;

    private LockAppService lockAppService;

    private Activity activity;

    private File tempMessagesFile;

    private MessageListAdapter messageListAdapter;

    private EmptyRecyclerView recyclerView;

    private View loadingView;

    private SearchView searchView;

    private WeakReference<MenuItem> searchMenuItemRef, toggleHiddenMenuItemRef;

    private ResumePauseHandler resumePauseHandler;

    private int currentFullSyncs = 0;

    private String filterQuery;

    private int cornerRadius;

    private TagModel unreadTagModel;

    private int archiveCount = 0;

    private Snackbar archiveSnackbar;

    private ConversationModel selectedConversation;

    private ExtendedFloatingActionButton floatingButtonView;

    private final Object messageListAdapterLock = new Object();

    private final SynchronizeContactsListener synchronizeContactsListener = new SynchronizeContactsListener() {

        @Override
        public void onStarted(SynchronizeContactsRoutine startedRoutine) {
            if (!ListenerUtil.mutListener.listen(27108)) {
                if (startedRoutine.fullSync()) {
                    if (!ListenerUtil.mutListener.listen(27107)) {
                        currentFullSyncs++;
                    }
                }
            }
        }

        @Override
        public void onFinished(SynchronizeContactsRoutine finishedRoutine) {
            if (!ListenerUtil.mutListener.listen(27112)) {
                if (finishedRoutine.fullSync()) {
                    if (!ListenerUtil.mutListener.listen(27109)) {
                        currentFullSyncs--;
                    }
                    if (!ListenerUtil.mutListener.listen(27110)) {
                        logger.debug("synchronizeContactsListener.onFinished");
                    }
                    if (!ListenerUtil.mutListener.listen(27111)) {
                        refreshListEvent();
                    }
                }
            }
        }

        @Override
        public void onError(SynchronizeContactsRoutine finishedRoutine) {
            if (!ListenerUtil.mutListener.listen(27116)) {
                if (finishedRoutine.fullSync()) {
                    if (!ListenerUtil.mutListener.listen(27113)) {
                        currentFullSyncs--;
                    }
                    if (!ListenerUtil.mutListener.listen(27114)) {
                        logger.debug("synchronizeContactsListener.onError");
                    }
                    if (!ListenerUtil.mutListener.listen(27115)) {
                        refreshListEvent();
                    }
                }
            }
        }
    };

    private final ConversationListener conversationListener = new ConversationListener() {

        @Override
        public void onNew(final ConversationModel conversationModel) {
            if (!ListenerUtil.mutListener.listen(27117)) {
                logger.debug("on new conversation");
            }
            if (!ListenerUtil.mutListener.listen(27120)) {
                if ((ListenerUtil.mutListener.listen(27118) ? (messageListAdapter != null || recyclerView != null) : (messageListAdapter != null && recyclerView != null))) {
                    if (!ListenerUtil.mutListener.listen(27119)) {
                        updateList(0, null, null);
                    }
                }
            }
        }

        @Override
        public void onModified(final ConversationModel modifiedConversationModel, final Integer oldPosition) {
            if (!ListenerUtil.mutListener.listen(27121)) {
                logger.debug("on modified conversation");
            }
            if (!ListenerUtil.mutListener.listen(27125)) {
                if ((ListenerUtil.mutListener.listen(27122) ? (messageListAdapter != null || recyclerView != null) : (messageListAdapter != null && recyclerView != null))) {
                    // scroll if position changed (to top)
                    List<ConversationModel> l = new ArrayList<>();
                    if (!ListenerUtil.mutListener.listen(27123)) {
                        l.add(modifiedConversationModel);
                    }
                    if (!ListenerUtil.mutListener.listen(27124)) {
                        updateList(oldPosition, l, null);
                    }
                }
            }
        }

        @Override
        public void onRemoved(final ConversationModel conversationModel) {
            if (!ListenerUtil.mutListener.listen(27129)) {
                if (isMultiPaneEnabled(activity)) {
                    if (!ListenerUtil.mutListener.listen(27128)) {
                        activity.finish();
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(27127)) {
                        if (messageListAdapter != null) {
                            if (!ListenerUtil.mutListener.listen(27126)) {
                                updateList();
                            }
                        }
                    }
                }
            }
        }

        @Override
        public void onModifiedAll() {
            if (!ListenerUtil.mutListener.listen(27130)) {
                logger.debug("on modified all");
            }
            if (!ListenerUtil.mutListener.listen(27135)) {
                if ((ListenerUtil.mutListener.listen(27131) ? (messageListAdapter != null || recyclerView != null) : (messageListAdapter != null && recyclerView != null))) {
                    if (!ListenerUtil.mutListener.listen(27134)) {
                        updateList(0, null, new Runnable() {

                            @Override
                            public void run() {
                                if (!ListenerUtil.mutListener.listen(27133)) {
                                    RuntimeUtil.runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            if (!ListenerUtil.mutListener.listen(27132)) {
                                                messageListAdapter.notifyDataSetChanged();
                                            }
                                        }
                                    });
                                }
                            }
                        });
                    }
                }
            }
        }
    };

    private final ChatListener chatListener = new ChatListener() {

        @Override
        public void onChatOpened(String conversationUid) {
            if (!ListenerUtil.mutListener.listen(27136)) {
                highlightUid = conversationUid;
            }
            if (!ListenerUtil.mutListener.listen(27140)) {
                if ((ListenerUtil.mutListener.listen(27137) ? (isMultiPaneEnabled(activity) || messageListAdapter != null) : (isMultiPaneEnabled(activity) && messageListAdapter != null))) {
                    if (!ListenerUtil.mutListener.listen(27138)) {
                        messageListAdapter.setHighlightItem(conversationUid);
                    }
                    if (!ListenerUtil.mutListener.listen(27139)) {
                        messageListAdapter.notifyDataSetChanged();
                    }
                }
            }
        }
    };

    private final ContactSettingsListener contactSettingsListener = new ContactSettingsListener() {

        @Override
        public void onSortingChanged() {
        }

        @Override
        public void onNameFormatChanged() {
            if (!ListenerUtil.mutListener.listen(27141)) {
                logger.debug("contactSettingsListener.onNameFormatChanged");
            }
            if (!ListenerUtil.mutListener.listen(27142)) {
                refreshListEvent();
            }
        }

        @Override
        public void onAvatarSettingChanged() {
            if (!ListenerUtil.mutListener.listen(27143)) {
                logger.debug("contactSettingsListener.onAvatarSettingChanged");
            }
            if (!ListenerUtil.mutListener.listen(27144)) {
                refreshListEvent();
            }
        }

        @Override
        public void onInactiveContactsSettingChanged() {
        }

        @Override
        public void onNotificationSettingChanged(String uid) {
            if (!ListenerUtil.mutListener.listen(27145)) {
                logger.debug("contactSettingsListener.onNotificationSettingChanged");
            }
            if (!ListenerUtil.mutListener.listen(27146)) {
                refreshListEvent();
            }
        }
    };

    private final ContactListener contactListener = new ContactListener() {

        @Override
        public void onModified(ContactModel modifiedContactModel) {
            if (!ListenerUtil.mutListener.listen(27147)) {
                logger.debug("contactListener.onModified [" + modifiedContactModel + "]");
            }
            if (!ListenerUtil.mutListener.listen(27148)) {
                refreshListEvent();
            }
        }

        @Override
        public void onAvatarChanged(ContactModel contactModel) {
            if (!ListenerUtil.mutListener.listen(27149)) {
                // TODO: Is this required?
                this.onModified(contactModel);
            }
        }

        @Override
        public void onNew(ContactModel createdContactModel) {
        }

        @Override
        public void onRemoved(ContactModel removedContactModel) {
        }

        @Override
        public boolean handle(String identity) {
            return (ListenerUtil.mutListener.listen(27154) ? (currentFullSyncs >= 0) : (ListenerUtil.mutListener.listen(27153) ? (currentFullSyncs > 0) : (ListenerUtil.mutListener.listen(27152) ? (currentFullSyncs < 0) : (ListenerUtil.mutListener.listen(27151) ? (currentFullSyncs != 0) : (ListenerUtil.mutListener.listen(27150) ? (currentFullSyncs == 0) : (currentFullSyncs <= 0))))));
        }
    };

    protected final boolean requiredInstances() {
        if (!ListenerUtil.mutListener.listen(27156)) {
            if (!this.checkInstances()) {
                if (!ListenerUtil.mutListener.listen(27155)) {
                    this.instantiate();
                }
            }
        }
        return this.checkInstances();
    }

    protected boolean checkInstances() {
        return TestUtil.required(this.serviceManager, this.contactListener, this.groupService, this.conversationService, this.distributionListService, this.fileService, this.backupChatService, this.mutedChatsListService, this.hiddenChatsListService, this.ringtoneService, this.preferenceService, this.lockAppService);
    }

    protected void instantiate() {
        if (!ListenerUtil.mutListener.listen(27157)) {
            this.serviceManager = ThreemaApplication.getServiceManager();
        }
        if (!ListenerUtil.mutListener.listen(27174)) {
            if (this.serviceManager != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(27160)) {
                        this.contactService = this.serviceManager.getContactService();
                    }
                    if (!ListenerUtil.mutListener.listen(27161)) {
                        this.groupService = this.serviceManager.getGroupService();
                    }
                    if (!ListenerUtil.mutListener.listen(27162)) {
                        this.messageService = this.serviceManager.getMessageService();
                    }
                    if (!ListenerUtil.mutListener.listen(27163)) {
                        this.conversationService = this.serviceManager.getConversationService();
                    }
                    if (!ListenerUtil.mutListener.listen(27164)) {
                        this.distributionListService = this.serviceManager.getDistributionListService();
                    }
                    if (!ListenerUtil.mutListener.listen(27165)) {
                        this.fileService = this.serviceManager.getFileService();
                    }
                    if (!ListenerUtil.mutListener.listen(27166)) {
                        this.backupChatService = this.serviceManager.getBackupChatService();
                    }
                    if (!ListenerUtil.mutListener.listen(27167)) {
                        this.mutedChatsListService = this.serviceManager.getMutedChatsListService();
                    }
                    if (!ListenerUtil.mutListener.listen(27168)) {
                        this.mentionOnlyChatsListService = this.serviceManager.getMentionOnlyChatsListService();
                    }
                    if (!ListenerUtil.mutListener.listen(27169)) {
                        this.hiddenChatsListService = this.serviceManager.getHiddenChatsListService();
                    }
                    if (!ListenerUtil.mutListener.listen(27170)) {
                        this.ringtoneService = this.serviceManager.getRingtoneService();
                    }
                    if (!ListenerUtil.mutListener.listen(27171)) {
                        this.preferenceService = this.serviceManager.getPreferenceService();
                    }
                    if (!ListenerUtil.mutListener.listen(27172)) {
                        this.conversationTagService = this.serviceManager.getConversationTagService();
                    }
                    if (!ListenerUtil.mutListener.listen(27173)) {
                        this.lockAppService = this.serviceManager.getLockAppService();
                    }
                } catch (MasterKeyLockedException e) {
                    if (!ListenerUtil.mutListener.listen(27158)) {
                        logger.debug("Master Key locked!");
                    }
                } catch (ThreemaException e) {
                    if (!ListenerUtil.mutListener.listen(27159)) {
                        logger.error("Exception", e);
                    }
                }
            }
        }
    }

    @Override
    public void onAttach(Activity activity) {
        if (!ListenerUtil.mutListener.listen(27175)) {
            super.onAttach(activity);
        }
        if (!ListenerUtil.mutListener.listen(27176)) {
            logger.debug("onAttach");
        }
        if (!ListenerUtil.mutListener.listen(27177)) {
            this.activity = activity;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(27178)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(27179)) {
            logger.debug("onCreate");
        }
        if (!ListenerUtil.mutListener.listen(27180)) {
            setRetainInstance(true);
        }
        if (!ListenerUtil.mutListener.listen(27181)) {
            setHasOptionsMenu(true);
        }
        if (!ListenerUtil.mutListener.listen(27182)) {
            setupListeners();
        }
        if (!ListenerUtil.mutListener.listen(27183)) {
            this.resumePauseHandler = ResumePauseHandler.getByActivity(this, this.activity);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(27184)) {
            super.onViewCreated(view, savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(27185)) {
            logger.debug("onViewCreated");
        }
        try {
            if (!ListenerUtil.mutListener.listen(27187)) {
                // show loading first
                ViewUtil.show(loadingView, true);
            }
            if (!ListenerUtil.mutListener.listen(27189)) {
                updateList(null, null, new Runnable() {

                    @Override
                    public void run() {
                        if (!ListenerUtil.mutListener.listen(27188)) {
                            // hide loading
                            ViewUtil.show(loadingView, false);
                        }
                    }
                }, true);
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(27186)) {
                LogUtil.exception(e, getActivity());
            }
        }
        if (!ListenerUtil.mutListener.listen(27192)) {
            if ((ListenerUtil.mutListener.listen(27190) ? (savedInstanceState != null || TestUtil.empty(filterQuery)) : (savedInstanceState != null && TestUtil.empty(filterQuery)))) {
                if (!ListenerUtil.mutListener.listen(27191)) {
                    filterQuery = savedInstanceState.getString(BUNDLE_FILTER_QUERY);
                }
            }
        }
    }

    @Override
    public void onDestroyView() {
        if (!ListenerUtil.mutListener.listen(27193)) {
            searchView = null;
        }
        if (!ListenerUtil.mutListener.listen(27196)) {
            if ((ListenerUtil.mutListener.listen(27194) ? (searchMenuItemRef != null || searchMenuItemRef.get() != null) : (searchMenuItemRef != null && searchMenuItemRef.get() != null))) {
                if (!ListenerUtil.mutListener.listen(27195)) {
                    searchMenuItemRef.clear();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(27197)) {
            messageListAdapter = null;
        }
        if (!ListenerUtil.mutListener.listen(27198)) {
            super.onDestroyView();
        }
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        if (!ListenerUtil.mutListener.listen(27199)) {
            super.onPrepareOptionsMenu(menu);
        }
        if (!ListenerUtil.mutListener.listen(27204)) {
            // move search item to popup if the lock item is visible
            if (this.searchMenuItemRef != null) {
                if (!ListenerUtil.mutListener.listen(27203)) {
                    if ((ListenerUtil.mutListener.listen(27200) ? (lockAppService != null || lockAppService.isLockingEnabled()) : (lockAppService != null && lockAppService.isLockingEnabled()))) {
                        if (!ListenerUtil.mutListener.listen(27202)) {
                            this.searchMenuItemRef.get().setShowAsAction(SHOW_AS_ACTION_NEVER | SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(27201)) {
                            this.searchMenuItemRef.get().setShowAsAction(SHOW_AS_ACTION_ALWAYS | SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        if (!ListenerUtil.mutListener.listen(27205)) {
            logger.debug("onCreateOptionsMenu");
        }
        if (!ListenerUtil.mutListener.listen(27230)) {
            if (activity != null) {
                if (!ListenerUtil.mutListener.listen(27229)) {
                    if (!isMultiPaneEnabled(activity)) {
                        MenuItem searchMenuItem = menu.findItem(R.id.menu_search_messages);
                        if (!ListenerUtil.mutListener.listen(27218)) {
                            if (searchMenuItem == null) {
                                if (!ListenerUtil.mutListener.listen(27206)) {
                                    inflater.inflate(R.menu.fragment_messages, menu);
                                }
                                if (!ListenerUtil.mutListener.listen(27217)) {
                                    if ((ListenerUtil.mutListener.listen(27207) ? (activity != null || this.isAdded()) : (activity != null && this.isAdded()))) {
                                        if (!ListenerUtil.mutListener.listen(27208)) {
                                            searchMenuItem = menu.findItem(R.id.menu_search_messages);
                                        }
                                        if (!ListenerUtil.mutListener.listen(27209)) {
                                            this.searchView = (SearchView) searchMenuItem.getActionView();
                                        }
                                        if (!ListenerUtil.mutListener.listen(27216)) {
                                            if (this.searchView != null) {
                                                if (!ListenerUtil.mutListener.listen(27213)) {
                                                    if (!TestUtil.empty(filterQuery)) {
                                                        if (!ListenerUtil.mutListener.listen(27210)) {
                                                            // restore filter
                                                            MenuItemCompat.expandActionView(searchMenuItem);
                                                        }
                                                        if (!ListenerUtil.mutListener.listen(27211)) {
                                                            searchView.setQuery(filterQuery, false);
                                                        }
                                                        if (!ListenerUtil.mutListener.listen(27212)) {
                                                            searchView.clearFocus();
                                                        }
                                                    }
                                                }
                                                if (!ListenerUtil.mutListener.listen(27214)) {
                                                    this.searchView.setQueryHint(getString(R.string.hint_filter_list));
                                                }
                                                if (!ListenerUtil.mutListener.listen(27215)) {
                                                    this.searchView.setOnQueryTextListener(queryTextListener);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(27219)) {
                            this.searchMenuItemRef = new WeakReference<>(searchMenuItem);
                        }
                        if (!ListenerUtil.mutListener.listen(27220)) {
                            toggleHiddenMenuItemRef = new WeakReference<>(menu.findItem(R.id.menu_toggle_private_chats));
                        }
                        if (!ListenerUtil.mutListener.listen(27228)) {
                            if (toggleHiddenMenuItemRef.get() != null) {
                                if (!ListenerUtil.mutListener.listen(27227)) {
                                    if (isAdded()) {
                                        if (!ListenerUtil.mutListener.listen(27225)) {
                                            toggleHiddenMenuItemRef.get().setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

                                                @Override
                                                public boolean onMenuItemClick(MenuItem item) {
                                                    if (!ListenerUtil.mutListener.listen(27224)) {
                                                        if (preferenceService.isPrivateChatsHidden()) {
                                                            if (!ListenerUtil.mutListener.listen(27223)) {
                                                                requestUnhideChats();
                                                            }
                                                        } else {
                                                            if (!ListenerUtil.mutListener.listen(27221)) {
                                                                preferenceService.setPrivateChatsHidden(true);
                                                            }
                                                            if (!ListenerUtil.mutListener.listen(27222)) {
                                                                updateList(null, null, new Thread(() -> fireSecretReceiverUpdate()));
                                                            }
                                                        }
                                                    }
                                                    return true;
                                                }
                                            });
                                        }
                                        if (!ListenerUtil.mutListener.listen(27226)) {
                                            updateHiddenMenuVisibility();
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(27231)) {
            super.onCreateOptionsMenu(menu, inflater);
        }
    }

    private void requestUnhideChats() {
        if (!ListenerUtil.mutListener.listen(27232)) {
            HiddenChatUtil.launchLockCheckDialog(this, preferenceService);
        }
    }

    final SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {

        @Override
        public boolean onQueryTextChange(String query) {
            if (!ListenerUtil.mutListener.listen(27233)) {
                filterQuery = query;
            }
            if (!ListenerUtil.mutListener.listen(27234)) {
                updateList(0, null, null);
            }
            return true;
        }

        @Override
        public boolean onQueryTextSubmit(String query) {
            return true;
        }
    };

    private void showConversation(ConversationModel conversationModel, View v) {
        if (!ListenerUtil.mutListener.listen(27235)) {
            conversationTagService.unTag(conversationModel, unreadTagModel);
        }
        Intent intent = IntentDataUtil.getShowConversationIntent(conversationModel, activity);
        if (!ListenerUtil.mutListener.listen(27236)) {
            if (intent == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(27242)) {
            if (isMultiPaneEnabled(activity)) {
                if (!ListenerUtil.mutListener.listen(27241)) {
                    if (this.isAdded()) {
                        if (!ListenerUtil.mutListener.listen(27238)) {
                            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        }
                        if (!ListenerUtil.mutListener.listen(27239)) {
                            startActivityForResult(intent, ThreemaActivity.ACTIVITY_ID_COMPOSE_MESSAGE);
                        }
                        if (!ListenerUtil.mutListener.listen(27240)) {
                            activity.overridePendingTransition(0, 0);
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(27237)) {
                    AnimationUtil.startActivityForResult(activity, ConfigUtils.isTabletLayout() ? null : v, intent, ThreemaActivity.ACTIVITY_ID_COMPOSE_MESSAGE);
                }
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!ListenerUtil.mutListener.listen(27261)) {
            switch(requestCode) {
                case ThreemaActivity.ACTIVITY_ID_SHARE_CHAT:
                    if (!ListenerUtil.mutListener.listen(27248)) {
                        if (tempMessagesFile != null) {
                            if (!ListenerUtil.mutListener.listen(27246)) {
                                /* We cannot delete the file immediately as some apps (e.g. Dropbox)
				   take some time until they read the file after the intent has been completed.
				   As we can't know for sure when they're done, we simply wait for one minute before
				   we delete the temporary file. */
                                new Thread() {

                                    final String tmpfilePath = tempMessagesFile.getAbsolutePath();

                                    @Override
                                    public void run() {
                                        try {
                                            if (!ListenerUtil.mutListener.listen(27245)) {
                                                Thread.sleep(TEMP_MESSAGES_FILE_DELETE_WAIT_TIME);
                                            }
                                        } catch (InterruptedException e) {
                                            if (!ListenerUtil.mutListener.listen(27243)) {
                                                logger.error("Exception", e);
                                            }
                                        } finally {
                                            if (!ListenerUtil.mutListener.listen(27244)) {
                                                FileUtil.deleteFileOrWarn(tmpfilePath, "tempMessagesFile", logger);
                                            }
                                        }
                                    }
                                }.start();
                            }
                            if (!ListenerUtil.mutListener.listen(27247)) {
                                tempMessagesFile = null;
                            }
                        }
                    }
                    break;
                case ThreemaActivity.ACTIVITY_ID_CHECK_LOCK:
                    if (!ListenerUtil.mutListener.listen(27252)) {
                        if (resultCode == Activity.RESULT_OK) {
                            if (!ListenerUtil.mutListener.listen(27249)) {
                                serviceManager.getScreenLockService().setAuthenticated(true);
                            }
                            if (!ListenerUtil.mutListener.listen(27250)) {
                                preferenceService.setPrivateChatsHidden(false);
                            }
                            if (!ListenerUtil.mutListener.listen(27251)) {
                                updateList(0, null, new Thread(() -> fireSecretReceiverUpdate()));
                            }
                        }
                    }
                    break;
                case ID_RETURN_FROM_SECURITY_SETTINGS:
                    if (!ListenerUtil.mutListener.listen(27254)) {
                        if (ConfigUtils.hasProtection(preferenceService)) {
                            if (!ListenerUtil.mutListener.listen(27253)) {
                                reallyHideChat(selectedConversation);
                            }
                        }
                    }
                    break;
                case ID_PRIVATE_TO_PUBLIC:
                    if (!ListenerUtil.mutListener.listen(27259)) {
                        if (resultCode == Activity.RESULT_OK) {
                            if (!ListenerUtil.mutListener.listen(27255)) {
                                ThreemaApplication.getServiceManager().getScreenLockService().setAuthenticated(true);
                            }
                            if (!ListenerUtil.mutListener.listen(27258)) {
                                if (selectedConversation != null) {
                                    MessageReceiver receiver = selectedConversation.getReceiver();
                                    if (!ListenerUtil.mutListener.listen(27257)) {
                                        if (receiver != null) {
                                            if (!ListenerUtil.mutListener.listen(27256)) {
                                                doUnhideChat(receiver);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                // fallthrough
                default:
                    if (!ListenerUtil.mutListener.listen(27260)) {
                        super.onActivityResult(requestCode, resultCode, data);
                    }
            }
        }
    }

    private void doUnhideChat(MessageReceiver receiver) {
        if (!ListenerUtil.mutListener.listen(27268)) {
            if ((ListenerUtil.mutListener.listen(27262) ? (receiver != null || hiddenChatsListService.has(receiver.getUniqueIdString())) : (receiver != null && hiddenChatsListService.has(receiver.getUniqueIdString())))) {
                if (!ListenerUtil.mutListener.listen(27263)) {
                    hiddenChatsListService.remove(receiver.getUniqueIdString());
                }
                if (!ListenerUtil.mutListener.listen(27265)) {
                    if (getView() != null) {
                        if (!ListenerUtil.mutListener.listen(27264)) {
                            Snackbar.make(getView(), R.string.chat_visible, Snackbar.LENGTH_SHORT).show();
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(27266)) {
                    this.fireReceiverUpdate(receiver);
                }
                if (!ListenerUtil.mutListener.listen(27267)) {
                    messageListAdapter.clearSelections();
                }
            }
        }
    }

    private void hideChat(ConversationModel conversationModel) {
        MessageReceiver receiver = conversationModel.getReceiver();
        if (!ListenerUtil.mutListener.listen(27280)) {
            if (hiddenChatsListService.has(receiver.getUniqueIdString())) {
                if (!ListenerUtil.mutListener.listen(27279)) {
                    if (ConfigUtils.hasProtection(preferenceService)) {
                        if (!ListenerUtil.mutListener.listen(27277)) {
                            // persist selection
                            selectedConversation = conversationModel;
                        }
                        if (!ListenerUtil.mutListener.listen(27278)) {
                            HiddenChatUtil.launchLockCheckDialog(null, this, preferenceService, ID_PRIVATE_TO_PUBLIC);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(27276)) {
                            doUnhideChat(receiver);
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(27275)) {
                    if (ConfigUtils.hasProtection(preferenceService)) {
                        GenericAlertDialog dialog = GenericAlertDialog.newInstance(R.string.hide_chat, R.string.really_hide_chat_message, R.string.ok, R.string.cancel);
                        if (!ListenerUtil.mutListener.listen(27272)) {
                            dialog.setTargetFragment(this, 0);
                        }
                        if (!ListenerUtil.mutListener.listen(27273)) {
                            dialog.setData(conversationModel);
                        }
                        if (!ListenerUtil.mutListener.listen(27274)) {
                            dialog.show(getFragmentManager(), DIALOG_TAG_REALLY_HIDE_THREAD);
                        }
                    } else {
                        GenericAlertDialog dialog = GenericAlertDialog.newInstance(R.string.hide_chat, R.string.hide_chat_message_explain, R.string.set_lock, R.string.cancel);
                        if (!ListenerUtil.mutListener.listen(27269)) {
                            dialog.setTargetFragment(this, 0);
                        }
                        if (!ListenerUtil.mutListener.listen(27270)) {
                            dialog.setData(conversationModel);
                        }
                        if (!ListenerUtil.mutListener.listen(27271)) {
                            dialog.show(getFragmentManager(), DIALOG_TAG_HIDE_THREAD_EXPLAIN);
                        }
                    }
                }
            }
        }
    }

    @SuppressLint("StaticFieldLeak")
    private void reallyHideChat(ConversationModel conversationModel) {
        if (!ListenerUtil.mutListener.listen(27294)) {
            new AsyncTask<Void, Void, Void>() {

                @Override
                protected void onPreExecute() {
                    if (!ListenerUtil.mutListener.listen(27282)) {
                        if (resumePauseHandler != null) {
                            if (!ListenerUtil.mutListener.listen(27281)) {
                                resumePauseHandler.onPause();
                            }
                        }
                    }
                }

                @Override
                protected Void doInBackground(Void... params) {
                    if (!ListenerUtil.mutListener.listen(27283)) {
                        hiddenChatsListService.add(conversationModel.getReceiver().getUniqueIdString(), DeadlineListService.DEADLINE_INDEFINITE);
                    }
                    if (!ListenerUtil.mutListener.listen(27284)) {
                        fireReceiverUpdate(conversationModel.getReceiver());
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    if (!ListenerUtil.mutListener.listen(27285)) {
                        messageListAdapter.clearSelections();
                    }
                    if (!ListenerUtil.mutListener.listen(27287)) {
                        if (getView() != null) {
                            if (!ListenerUtil.mutListener.listen(27286)) {
                                Snackbar.make(getView(), R.string.chat_hidden, Snackbar.LENGTH_SHORT).show();
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(27289)) {
                        if (resumePauseHandler != null) {
                            if (!ListenerUtil.mutListener.listen(27288)) {
                                resumePauseHandler.onResume();
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(27290)) {
                        updateHiddenMenuVisibility();
                    }
                    if (!ListenerUtil.mutListener.listen(27293)) {
                        if ((ListenerUtil.mutListener.listen(27291) ? (ConfigUtils.hasProtection(preferenceService) || preferenceService.isPrivateChatsHidden()) : (ConfigUtils.hasProtection(preferenceService) && preferenceService.isPrivateChatsHidden()))) {
                            if (!ListenerUtil.mutListener.listen(27292)) {
                                updateList(null, null, new Thread(() -> fireSecretReceiverUpdate()));
                            }
                        }
                    }
                }
            }.execute();
        }
    }

    private void shareChat(final ConversationModel conversationModel, final String password, final boolean includeMedia) {
        CancelableGenericProgressDialog progressDialog = CancelableGenericProgressDialog.newInstance(R.string.preparing_messages, 0, R.string.cancel);
        if (!ListenerUtil.mutListener.listen(27295)) {
            progressDialog.setTargetFragment(this, 0);
        }
        if (!ListenerUtil.mutListener.listen(27296)) {
            progressDialog.show(getFragmentManager(), DIALOG_TAG_PREPARING_MESSAGES);
        }
        if (!ListenerUtil.mutListener.listen(27315)) {
            new Thread(new Runnable() {

                @Override
                public void run() {
                    if (!ListenerUtil.mutListener.listen(27297)) {
                        tempMessagesFile = FileUtil.getUniqueFile(ConfigUtils.useContentUris() ? fileService.getTempPath().getPath() : fileService.getExtTmpPath().getPath(), "threema-chat.zip");
                    }
                    if (!ListenerUtil.mutListener.listen(27298)) {
                        FileUtil.deleteFileOrWarn(tempMessagesFile, "tempMessagesFile", logger);
                    }
                    if (!ListenerUtil.mutListener.listen(27314)) {
                        if (backupChatService.backupChatToZip(conversationModel, tempMessagesFile, password, includeMedia)) {
                            if (!ListenerUtil.mutListener.listen(27313)) {
                                if ((ListenerUtil.mutListener.listen(27306) ? ((ListenerUtil.mutListener.listen(27300) ? (tempMessagesFile != null || tempMessagesFile.exists()) : (tempMessagesFile != null && tempMessagesFile.exists())) || (ListenerUtil.mutListener.listen(27305) ? (tempMessagesFile.length() >= 0) : (ListenerUtil.mutListener.listen(27304) ? (tempMessagesFile.length() <= 0) : (ListenerUtil.mutListener.listen(27303) ? (tempMessagesFile.length() < 0) : (ListenerUtil.mutListener.listen(27302) ? (tempMessagesFile.length() != 0) : (ListenerUtil.mutListener.listen(27301) ? (tempMessagesFile.length() == 0) : (tempMessagesFile.length() > 0))))))) : ((ListenerUtil.mutListener.listen(27300) ? (tempMessagesFile != null || tempMessagesFile.exists()) : (tempMessagesFile != null && tempMessagesFile.exists())) && (ListenerUtil.mutListener.listen(27305) ? (tempMessagesFile.length() >= 0) : (ListenerUtil.mutListener.listen(27304) ? (tempMessagesFile.length() <= 0) : (ListenerUtil.mutListener.listen(27303) ? (tempMessagesFile.length() < 0) : (ListenerUtil.mutListener.listen(27302) ? (tempMessagesFile.length() != 0) : (ListenerUtil.mutListener.listen(27301) ? (tempMessagesFile.length() == 0) : (tempMessagesFile.length() > 0))))))))) {
                                    final Intent intent = new Intent(Intent.ACTION_SEND);
                                    if (!ListenerUtil.mutListener.listen(27307)) {
                                        intent.setType(MimeUtil.MIME_TYPE_ZIP);
                                    }
                                    if (!ListenerUtil.mutListener.listen(27308)) {
                                        intent.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.share_subject));
                                    }
                                    if (!ListenerUtil.mutListener.listen(27309)) {
                                        intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.chat_history_attached) + "\n\n" + getString(R.string.share_conversation_body));
                                    }
                                    if (!ListenerUtil.mutListener.listen(27310)) {
                                        intent.putExtra(Intent.EXTRA_STREAM, fileService.getShareFileUri(tempMessagesFile, null));
                                    }
                                    if (!ListenerUtil.mutListener.listen(27311)) {
                                        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                                    }
                                    if (!ListenerUtil.mutListener.listen(27312)) {
                                        RuntimeUtil.runOnUiThread(() -> {
                                            DialogUtil.dismissDialog(getFragmentManager(), DIALOG_TAG_PREPARING_MESSAGES, true);
                                            startActivityForResult(Intent.createChooser(intent, getString(R.string.share_via)), ThreemaActivity.ACTIVITY_ID_SHARE_CHAT);
                                        });
                                    }
                                }
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(27299)) {
                                RuntimeUtil.runOnUiThread(() -> {
                                    DialogUtil.dismissDialog(getFragmentManager(), DIALOG_TAG_PREPARING_MESSAGES, true);
                                    SimpleStringAlertDialog.newInstance(R.string.share_via, getString(R.string.an_error_occurred)).show(getFragmentManager(), "diskfull");
                                });
                            }
                        }
                    }
                }
            }).start();
        }
    }

    private void prepareShareChat(ConversationModel model) {
        PasswordEntryDialog dialogFragment = PasswordEntryDialog.newInstance(R.string.share_chat, R.string.enter_zip_password_body, R.string.password_hint, R.string.ok, R.string.cancel, MIN_PW_LENGTH_BACKUP, MAX_PW_LENGTH_BACKUP, R.string.backup_password_again_summary, 0, R.string.backup_data_media);
        if (!ListenerUtil.mutListener.listen(27316)) {
            dialogFragment.setTargetFragment(this, 0);
        }
        if (!ListenerUtil.mutListener.listen(27317)) {
            dialogFragment.setData(model);
        }
        if (!ListenerUtil.mutListener.listen(27318)) {
            dialogFragment.show(getFragmentManager(), DIALOG_TAG_SHARE_CHAT);
        }
    }

    private void refreshListEvent() {
        if (!ListenerUtil.mutListener.listen(27319)) {
            logger.debug("refreshListEvent reloadData");
        }
        if (!ListenerUtil.mutListener.listen(27323)) {
            if (this.resumePauseHandler != null) {
                if (!ListenerUtil.mutListener.listen(27322)) {
                    this.resumePauseHandler.runOnActive("refresh_list", new ResumePauseHandler.RunIfActive() {

                        @Override
                        public void runOnUiThread() {
                            if (!ListenerUtil.mutListener.listen(27320)) {
                                if (messageListAdapter == null) {
                                    return;
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(27321)) {
                                messageListAdapter.notifyDataSetChanged();
                            }
                        }
                    });
                }
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = getView();
        if (!ListenerUtil.mutListener.listen(27490)) {
            if (fragmentView == null) {
                if (!ListenerUtil.mutListener.listen(27324)) {
                    fragmentView = inflater.inflate(R.layout.fragment_messages, container, false);
                }
                final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                if (!ListenerUtil.mutListener.listen(27325)) {
                    this.recyclerView = fragmentView.findViewById(R.id.list);
                }
                if (!ListenerUtil.mutListener.listen(27326)) {
                    this.recyclerView.setHasFixedSize(true);
                }
                if (!ListenerUtil.mutListener.listen(27327)) {
                    this.recyclerView.setLayoutManager(linearLayoutManager);
                }
                if (!ListenerUtil.mutListener.listen(27328)) {
                    this.recyclerView.setItemAnimator(new DefaultItemAnimator());
                }
                if (!ListenerUtil.mutListener.listen(27329)) {
                    this.cornerRadius = getResources().getDimensionPixelSize(R.dimen.messagelist_card_corner_radius);
                }
                final ItemTouchHelper.SimpleCallback swipeCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {

                    private final VectorDrawableCompat pinIconDrawable = VectorDrawableCompat.create(getResources(), R.drawable.ic_pin, null);

                    private final VectorDrawableCompat unpinIconDrawable = VectorDrawableCompat.create(getResources(), R.drawable.ic_pin_outline, null);

                    private final VectorDrawableCompat archiveDrawable = VectorDrawableCompat.create(getResources(), R.drawable.ic_archive_outline, null);

                    @Override
                    public float getSwipeThreshold(@NonNull RecyclerView.ViewHolder viewHolder) {
                        return 0.7f;
                    }

                    @Override
                    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                        if (!ListenerUtil.mutListener.listen(27330)) {
                            // disable swiping and dragging for footer views
                            if (viewHolder.getItemViewType() == MessageListAdapter.TYPE_FOOTER) {
                                return makeMovementFlags(0, 0);
                            }
                        }
                        return super.getMovementFlags(recyclerView, viewHolder);
                    }

                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public int getSwipeDirs(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                        return super.getSwipeDirs(recyclerView, viewHolder);
                    }

                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                        if (!ListenerUtil.mutListener.listen(27331)) {
                            // required to clear swipe layout
                            messageListAdapter.notifyDataSetChanged();
                        }
                        final MessageListAdapter.MessageListViewHolder holder = (MessageListAdapter.MessageListViewHolder) viewHolder;
                        final int oldPosition = holder.getConversationModel().getPosition();
                        if (!ListenerUtil.mutListener.listen(27347)) {
                            if (direction == ItemTouchHelper.RIGHT) {
                                TagModel pinTagModel = conversationTagService.getTagModel(ConversationTagServiceImpl.FIXED_TAG_PIN);
                                if (!ListenerUtil.mutListener.listen(27343)) {
                                    conversationTagService.toggle(holder.getConversationModel(), pinTagModel, true);
                                }
                                ArrayList<ConversationModel> conversationModels = new ArrayList<>();
                                if (!ListenerUtil.mutListener.listen(27344)) {
                                    conversationModels.add(holder.getConversationModel());
                                }
                                if (!ListenerUtil.mutListener.listen(27346)) {
                                    updateList(null, conversationModels, new Runnable() {

                                        @Override
                                        public void run() {
                                            if (!ListenerUtil.mutListener.listen(27345)) {
                                                ListenerManager.conversationListeners.handle((ConversationListener listener) -> {
                                                    listener.onModified(holder.getConversationModel(), oldPosition);
                                                });
                                            }
                                        }
                                    });
                                }
                            } else if (direction == ItemTouchHelper.LEFT) {
                                if (!ListenerUtil.mutListener.listen(27332)) {
                                    archiveCount++;
                                }
                                if (!ListenerUtil.mutListener.listen(27333)) {
                                    conversationService.archive(holder.getConversationModel());
                                }
                                String snackText = String.format(getString(R.string.message_archived), archiveCount);
                                if (!ListenerUtil.mutListener.listen(27336)) {
                                    if ((ListenerUtil.mutListener.listen(27334) ? (archiveSnackbar != null || archiveSnackbar.isShown()) : (archiveSnackbar != null && archiveSnackbar.isShown()))) {
                                        if (!ListenerUtil.mutListener.listen(27335)) {
                                            archiveSnackbar.dismiss();
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(27342)) {
                                    if (getView() != null) {
                                        if (!ListenerUtil.mutListener.listen(27337)) {
                                            archiveSnackbar = Snackbar.make(getView(), snackText, Snackbar.LENGTH_LONG);
                                        }
                                        if (!ListenerUtil.mutListener.listen(27340)) {
                                            archiveSnackbar.addCallback(new Snackbar.Callback() {

                                                @Override
                                                public void onDismissed(Snackbar snackbar, int event) {
                                                    if (!ListenerUtil.mutListener.listen(27338)) {
                                                        super.onDismissed(snackbar, event);
                                                    }
                                                    if (!ListenerUtil.mutListener.listen(27339)) {
                                                        archiveCount = 0;
                                                    }
                                                }
                                            });
                                        }
                                        if (!ListenerUtil.mutListener.listen(27341)) {
                                            archiveSnackbar.show();
                                        }
                                    }
                                }
                            }
                        }
                    }

                    @Override
                    public void onChildDraw(@NonNull Canvas canvas, @NonNull RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                        View itemView = viewHolder.itemView;
                        if (!ListenerUtil.mutListener.listen(27445)) {
                            if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                                Paint paint = new Paint();
                                if (!ListenerUtil.mutListener.listen(27444)) {
                                    if ((ListenerUtil.mutListener.listen(27352) ? (dX >= 0) : (ListenerUtil.mutListener.listen(27351) ? (dX <= 0) : (ListenerUtil.mutListener.listen(27350) ? (dX < 0) : (ListenerUtil.mutListener.listen(27349) ? (dX != 0) : (ListenerUtil.mutListener.listen(27348) ? (dX == 0) : (dX > 0))))))) {
                                        MessageListAdapter.MessageListViewHolder holder = (MessageListAdapter.MessageListViewHolder) viewHolder;
                                        TagModel pinTagModel = conversationTagService.getTagModel(ConversationTagServiceImpl.FIXED_TAG_PIN);
                                        VectorDrawableCompat icon = conversationTagService.isTaggedWith(holder.getConversationModel(), pinTagModel) ? unpinIconDrawable : pinIconDrawable;
                                        if (!ListenerUtil.mutListener.listen(27413)) {
                                            icon.setBounds(0, 0, icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
                                        }
                                        String label = conversationTagService.isTaggedWith(holder.getConversationModel(), pinTagModel) ? getString(R.string.unpin) : getString(R.string.pin);
                                        if (!ListenerUtil.mutListener.listen(27414)) {
                                            paint.setColor(getResources().getColor(R.color.messagelist_pinned_color));
                                        }
                                        if (!ListenerUtil.mutListener.listen(27415)) {
                                            canvas.drawRect((float) itemView.getLeft(), (float) itemView.getTop(), dX + cornerRadius, (float) itemView.getBottom(), paint);
                                        }
                                        if (!ListenerUtil.mutListener.listen(27416)) {
                                            canvas.save();
                                        }
                                        if (!ListenerUtil.mutListener.listen(27429)) {
                                            canvas.translate((float) itemView.getLeft() + getResources().getDimension(R.dimen.swipe_icon_inset), (float) itemView.getTop() + (ListenerUtil.mutListener.listen(27428) ? (((ListenerUtil.mutListener.listen(27424) ? ((ListenerUtil.mutListener.listen(27420) ? ((float) itemView.getBottom() % (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27419) ? ((float) itemView.getBottom() / (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27418) ? ((float) itemView.getBottom() * (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27417) ? ((float) itemView.getBottom() + (float) itemView.getTop()) : ((float) itemView.getBottom() - (float) itemView.getTop()))))) % icon.getIntrinsicHeight()) : (ListenerUtil.mutListener.listen(27423) ? ((ListenerUtil.mutListener.listen(27420) ? ((float) itemView.getBottom() % (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27419) ? ((float) itemView.getBottom() / (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27418) ? ((float) itemView.getBottom() * (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27417) ? ((float) itemView.getBottom() + (float) itemView.getTop()) : ((float) itemView.getBottom() - (float) itemView.getTop()))))) / icon.getIntrinsicHeight()) : (ListenerUtil.mutListener.listen(27422) ? ((ListenerUtil.mutListener.listen(27420) ? ((float) itemView.getBottom() % (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27419) ? ((float) itemView.getBottom() / (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27418) ? ((float) itemView.getBottom() * (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27417) ? ((float) itemView.getBottom() + (float) itemView.getTop()) : ((float) itemView.getBottom() - (float) itemView.getTop()))))) * icon.getIntrinsicHeight()) : (ListenerUtil.mutListener.listen(27421) ? ((ListenerUtil.mutListener.listen(27420) ? ((float) itemView.getBottom() % (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27419) ? ((float) itemView.getBottom() / (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27418) ? ((float) itemView.getBottom() * (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27417) ? ((float) itemView.getBottom() + (float) itemView.getTop()) : ((float) itemView.getBottom() - (float) itemView.getTop()))))) + icon.getIntrinsicHeight()) : ((ListenerUtil.mutListener.listen(27420) ? ((float) itemView.getBottom() % (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27419) ? ((float) itemView.getBottom() / (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27418) ? ((float) itemView.getBottom() * (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27417) ? ((float) itemView.getBottom() + (float) itemView.getTop()) : ((float) itemView.getBottom() - (float) itemView.getTop()))))) - icon.getIntrinsicHeight())))))) % 2) : (ListenerUtil.mutListener.listen(27427) ? (((ListenerUtil.mutListener.listen(27424) ? ((ListenerUtil.mutListener.listen(27420) ? ((float) itemView.getBottom() % (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27419) ? ((float) itemView.getBottom() / (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27418) ? ((float) itemView.getBottom() * (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27417) ? ((float) itemView.getBottom() + (float) itemView.getTop()) : ((float) itemView.getBottom() - (float) itemView.getTop()))))) % icon.getIntrinsicHeight()) : (ListenerUtil.mutListener.listen(27423) ? ((ListenerUtil.mutListener.listen(27420) ? ((float) itemView.getBottom() % (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27419) ? ((float) itemView.getBottom() / (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27418) ? ((float) itemView.getBottom() * (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27417) ? ((float) itemView.getBottom() + (float) itemView.getTop()) : ((float) itemView.getBottom() - (float) itemView.getTop()))))) / icon.getIntrinsicHeight()) : (ListenerUtil.mutListener.listen(27422) ? ((ListenerUtil.mutListener.listen(27420) ? ((float) itemView.getBottom() % (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27419) ? ((float) itemView.getBottom() / (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27418) ? ((float) itemView.getBottom() * (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27417) ? ((float) itemView.getBottom() + (float) itemView.getTop()) : ((float) itemView.getBottom() - (float) itemView.getTop()))))) * icon.getIntrinsicHeight()) : (ListenerUtil.mutListener.listen(27421) ? ((ListenerUtil.mutListener.listen(27420) ? ((float) itemView.getBottom() % (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27419) ? ((float) itemView.getBottom() / (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27418) ? ((float) itemView.getBottom() * (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27417) ? ((float) itemView.getBottom() + (float) itemView.getTop()) : ((float) itemView.getBottom() - (float) itemView.getTop()))))) + icon.getIntrinsicHeight()) : ((ListenerUtil.mutListener.listen(27420) ? ((float) itemView.getBottom() % (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27419) ? ((float) itemView.getBottom() / (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27418) ? ((float) itemView.getBottom() * (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27417) ? ((float) itemView.getBottom() + (float) itemView.getTop()) : ((float) itemView.getBottom() - (float) itemView.getTop()))))) - icon.getIntrinsicHeight())))))) * 2) : (ListenerUtil.mutListener.listen(27426) ? (((ListenerUtil.mutListener.listen(27424) ? ((ListenerUtil.mutListener.listen(27420) ? ((float) itemView.getBottom() % (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27419) ? ((float) itemView.getBottom() / (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27418) ? ((float) itemView.getBottom() * (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27417) ? ((float) itemView.getBottom() + (float) itemView.getTop()) : ((float) itemView.getBottom() - (float) itemView.getTop()))))) % icon.getIntrinsicHeight()) : (ListenerUtil.mutListener.listen(27423) ? ((ListenerUtil.mutListener.listen(27420) ? ((float) itemView.getBottom() % (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27419) ? ((float) itemView.getBottom() / (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27418) ? ((float) itemView.getBottom() * (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27417) ? ((float) itemView.getBottom() + (float) itemView.getTop()) : ((float) itemView.getBottom() - (float) itemView.getTop()))))) / icon.getIntrinsicHeight()) : (ListenerUtil.mutListener.listen(27422) ? ((ListenerUtil.mutListener.listen(27420) ? ((float) itemView.getBottom() % (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27419) ? ((float) itemView.getBottom() / (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27418) ? ((float) itemView.getBottom() * (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27417) ? ((float) itemView.getBottom() + (float) itemView.getTop()) : ((float) itemView.getBottom() - (float) itemView.getTop()))))) * icon.getIntrinsicHeight()) : (ListenerUtil.mutListener.listen(27421) ? ((ListenerUtil.mutListener.listen(27420) ? ((float) itemView.getBottom() % (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27419) ? ((float) itemView.getBottom() / (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27418) ? ((float) itemView.getBottom() * (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27417) ? ((float) itemView.getBottom() + (float) itemView.getTop()) : ((float) itemView.getBottom() - (float) itemView.getTop()))))) + icon.getIntrinsicHeight()) : ((ListenerUtil.mutListener.listen(27420) ? ((float) itemView.getBottom() % (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27419) ? ((float) itemView.getBottom() / (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27418) ? ((float) itemView.getBottom() * (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27417) ? ((float) itemView.getBottom() + (float) itemView.getTop()) : ((float) itemView.getBottom() - (float) itemView.getTop()))))) - icon.getIntrinsicHeight())))))) - 2) : (ListenerUtil.mutListener.listen(27425) ? (((ListenerUtil.mutListener.listen(27424) ? ((ListenerUtil.mutListener.listen(27420) ? ((float) itemView.getBottom() % (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27419) ? ((float) itemView.getBottom() / (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27418) ? ((float) itemView.getBottom() * (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27417) ? ((float) itemView.getBottom() + (float) itemView.getTop()) : ((float) itemView.getBottom() - (float) itemView.getTop()))))) % icon.getIntrinsicHeight()) : (ListenerUtil.mutListener.listen(27423) ? ((ListenerUtil.mutListener.listen(27420) ? ((float) itemView.getBottom() % (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27419) ? ((float) itemView.getBottom() / (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27418) ? ((float) itemView.getBottom() * (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27417) ? ((float) itemView.getBottom() + (float) itemView.getTop()) : ((float) itemView.getBottom() - (float) itemView.getTop()))))) / icon.getIntrinsicHeight()) : (ListenerUtil.mutListener.listen(27422) ? ((ListenerUtil.mutListener.listen(27420) ? ((float) itemView.getBottom() % (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27419) ? ((float) itemView.getBottom() / (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27418) ? ((float) itemView.getBottom() * (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27417) ? ((float) itemView.getBottom() + (float) itemView.getTop()) : ((float) itemView.getBottom() - (float) itemView.getTop()))))) * icon.getIntrinsicHeight()) : (ListenerUtil.mutListener.listen(27421) ? ((ListenerUtil.mutListener.listen(27420) ? ((float) itemView.getBottom() % (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27419) ? ((float) itemView.getBottom() / (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27418) ? ((float) itemView.getBottom() * (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27417) ? ((float) itemView.getBottom() + (float) itemView.getTop()) : ((float) itemView.getBottom() - (float) itemView.getTop()))))) + icon.getIntrinsicHeight()) : ((ListenerUtil.mutListener.listen(27420) ? ((float) itemView.getBottom() % (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27419) ? ((float) itemView.getBottom() / (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27418) ? ((float) itemView.getBottom() * (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27417) ? ((float) itemView.getBottom() + (float) itemView.getTop()) : ((float) itemView.getBottom() - (float) itemView.getTop()))))) - icon.getIntrinsicHeight())))))) + 2) : (((ListenerUtil.mutListener.listen(27424) ? ((ListenerUtil.mutListener.listen(27420) ? ((float) itemView.getBottom() % (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27419) ? ((float) itemView.getBottom() / (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27418) ? ((float) itemView.getBottom() * (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27417) ? ((float) itemView.getBottom() + (float) itemView.getTop()) : ((float) itemView.getBottom() - (float) itemView.getTop()))))) % icon.getIntrinsicHeight()) : (ListenerUtil.mutListener.listen(27423) ? ((ListenerUtil.mutListener.listen(27420) ? ((float) itemView.getBottom() % (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27419) ? ((float) itemView.getBottom() / (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27418) ? ((float) itemView.getBottom() * (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27417) ? ((float) itemView.getBottom() + (float) itemView.getTop()) : ((float) itemView.getBottom() - (float) itemView.getTop()))))) / icon.getIntrinsicHeight()) : (ListenerUtil.mutListener.listen(27422) ? ((ListenerUtil.mutListener.listen(27420) ? ((float) itemView.getBottom() % (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27419) ? ((float) itemView.getBottom() / (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27418) ? ((float) itemView.getBottom() * (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27417) ? ((float) itemView.getBottom() + (float) itemView.getTop()) : ((float) itemView.getBottom() - (float) itemView.getTop()))))) * icon.getIntrinsicHeight()) : (ListenerUtil.mutListener.listen(27421) ? ((ListenerUtil.mutListener.listen(27420) ? ((float) itemView.getBottom() % (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27419) ? ((float) itemView.getBottom() / (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27418) ? ((float) itemView.getBottom() * (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27417) ? ((float) itemView.getBottom() + (float) itemView.getTop()) : ((float) itemView.getBottom() - (float) itemView.getTop()))))) + icon.getIntrinsicHeight()) : ((ListenerUtil.mutListener.listen(27420) ? ((float) itemView.getBottom() % (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27419) ? ((float) itemView.getBottom() / (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27418) ? ((float) itemView.getBottom() * (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27417) ? ((float) itemView.getBottom() + (float) itemView.getTop()) : ((float) itemView.getBottom() - (float) itemView.getTop()))))) - icon.getIntrinsicHeight())))))) / 2))))));
                                        }
                                        if (!ListenerUtil.mutListener.listen(27430)) {
                                            icon.draw(canvas);
                                        }
                                        if (!ListenerUtil.mutListener.listen(27431)) {
                                            canvas.restore();
                                        }
                                        Paint textPaint = new Paint();
                                        if (!ListenerUtil.mutListener.listen(27432)) {
                                            textPaint.setColor(Color.WHITE);
                                        }
                                        if (!ListenerUtil.mutListener.listen(27433)) {
                                            textPaint.setTextSize(getResources().getDimension(R.dimen.swipe_text_size));
                                        }
                                        Rect rect = new Rect();
                                        if (!ListenerUtil.mutListener.listen(27434)) {
                                            textPaint.getTextBounds(label, 0, label.length(), rect);
                                        }
                                        if (!ListenerUtil.mutListener.listen(27443)) {
                                            canvas.drawText(label, itemView.getLeft() + getResources().getDimension(R.dimen.swipe_text_inset), itemView.getTop() + (ListenerUtil.mutListener.listen(27442) ? (((ListenerUtil.mutListener.listen(27438) ? (itemView.getBottom() % itemView.getTop()) : (ListenerUtil.mutListener.listen(27437) ? (itemView.getBottom() / itemView.getTop()) : (ListenerUtil.mutListener.listen(27436) ? (itemView.getBottom() * itemView.getTop()) : (ListenerUtil.mutListener.listen(27435) ? (itemView.getBottom() + itemView.getTop()) : (itemView.getBottom() - itemView.getTop()))))) + rect.height()) % 2) : (ListenerUtil.mutListener.listen(27441) ? (((ListenerUtil.mutListener.listen(27438) ? (itemView.getBottom() % itemView.getTop()) : (ListenerUtil.mutListener.listen(27437) ? (itemView.getBottom() / itemView.getTop()) : (ListenerUtil.mutListener.listen(27436) ? (itemView.getBottom() * itemView.getTop()) : (ListenerUtil.mutListener.listen(27435) ? (itemView.getBottom() + itemView.getTop()) : (itemView.getBottom() - itemView.getTop()))))) + rect.height()) * 2) : (ListenerUtil.mutListener.listen(27440) ? (((ListenerUtil.mutListener.listen(27438) ? (itemView.getBottom() % itemView.getTop()) : (ListenerUtil.mutListener.listen(27437) ? (itemView.getBottom() / itemView.getTop()) : (ListenerUtil.mutListener.listen(27436) ? (itemView.getBottom() * itemView.getTop()) : (ListenerUtil.mutListener.listen(27435) ? (itemView.getBottom() + itemView.getTop()) : (itemView.getBottom() - itemView.getTop()))))) + rect.height()) - 2) : (ListenerUtil.mutListener.listen(27439) ? (((ListenerUtil.mutListener.listen(27438) ? (itemView.getBottom() % itemView.getTop()) : (ListenerUtil.mutListener.listen(27437) ? (itemView.getBottom() / itemView.getTop()) : (ListenerUtil.mutListener.listen(27436) ? (itemView.getBottom() * itemView.getTop()) : (ListenerUtil.mutListener.listen(27435) ? (itemView.getBottom() + itemView.getTop()) : (itemView.getBottom() - itemView.getTop()))))) + rect.height()) + 2) : (((ListenerUtil.mutListener.listen(27438) ? (itemView.getBottom() % itemView.getTop()) : (ListenerUtil.mutListener.listen(27437) ? (itemView.getBottom() / itemView.getTop()) : (ListenerUtil.mutListener.listen(27436) ? (itemView.getBottom() * itemView.getTop()) : (ListenerUtil.mutListener.listen(27435) ? (itemView.getBottom() + itemView.getTop()) : (itemView.getBottom() - itemView.getTop()))))) + rect.height()) / 2))))), textPaint);
                                        }
                                    } else if ((ListenerUtil.mutListener.listen(27357) ? (dX >= 0) : (ListenerUtil.mutListener.listen(27356) ? (dX <= 0) : (ListenerUtil.mutListener.listen(27355) ? (dX > 0) : (ListenerUtil.mutListener.listen(27354) ? (dX != 0) : (ListenerUtil.mutListener.listen(27353) ? (dX == 0) : (dX < 0))))))) {
                                        VectorDrawableCompat icon = archiveDrawable;
                                        if (!ListenerUtil.mutListener.listen(27358)) {
                                            icon.setBounds(0, 0, icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
                                        }
                                        if (!ListenerUtil.mutListener.listen(27359)) {
                                            icon.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
                                        }
                                        String label = getString(R.string.to_archive);
                                        if (!ListenerUtil.mutListener.listen(27360)) {
                                            paint.setColor(getResources().getColor(R.color.messagelist_archive_color));
                                        }
                                        if (!ListenerUtil.mutListener.listen(27361)) {
                                            canvas.drawRect(dX, (float) itemView.getTop(), (float) itemView.getRight(), (float) itemView.getBottom(), paint);
                                        }
                                        if (!ListenerUtil.mutListener.listen(27362)) {
                                            canvas.save();
                                        }
                                        if (!ListenerUtil.mutListener.listen(27383)) {
                                            canvas.translate((ListenerUtil.mutListener.listen(27370) ? ((ListenerUtil.mutListener.listen(27366) ? ((float) itemView.getRight() % getResources().getDimension(R.dimen.swipe_icon_inset)) : (ListenerUtil.mutListener.listen(27365) ? ((float) itemView.getRight() / getResources().getDimension(R.dimen.swipe_icon_inset)) : (ListenerUtil.mutListener.listen(27364) ? ((float) itemView.getRight() * getResources().getDimension(R.dimen.swipe_icon_inset)) : (ListenerUtil.mutListener.listen(27363) ? ((float) itemView.getRight() + getResources().getDimension(R.dimen.swipe_icon_inset)) : ((float) itemView.getRight() - getResources().getDimension(R.dimen.swipe_icon_inset)))))) % icon.getIntrinsicWidth()) : (ListenerUtil.mutListener.listen(27369) ? ((ListenerUtil.mutListener.listen(27366) ? ((float) itemView.getRight() % getResources().getDimension(R.dimen.swipe_icon_inset)) : (ListenerUtil.mutListener.listen(27365) ? ((float) itemView.getRight() / getResources().getDimension(R.dimen.swipe_icon_inset)) : (ListenerUtil.mutListener.listen(27364) ? ((float) itemView.getRight() * getResources().getDimension(R.dimen.swipe_icon_inset)) : (ListenerUtil.mutListener.listen(27363) ? ((float) itemView.getRight() + getResources().getDimension(R.dimen.swipe_icon_inset)) : ((float) itemView.getRight() - getResources().getDimension(R.dimen.swipe_icon_inset)))))) / icon.getIntrinsicWidth()) : (ListenerUtil.mutListener.listen(27368) ? ((ListenerUtil.mutListener.listen(27366) ? ((float) itemView.getRight() % getResources().getDimension(R.dimen.swipe_icon_inset)) : (ListenerUtil.mutListener.listen(27365) ? ((float) itemView.getRight() / getResources().getDimension(R.dimen.swipe_icon_inset)) : (ListenerUtil.mutListener.listen(27364) ? ((float) itemView.getRight() * getResources().getDimension(R.dimen.swipe_icon_inset)) : (ListenerUtil.mutListener.listen(27363) ? ((float) itemView.getRight() + getResources().getDimension(R.dimen.swipe_icon_inset)) : ((float) itemView.getRight() - getResources().getDimension(R.dimen.swipe_icon_inset)))))) * icon.getIntrinsicWidth()) : (ListenerUtil.mutListener.listen(27367) ? ((ListenerUtil.mutListener.listen(27366) ? ((float) itemView.getRight() % getResources().getDimension(R.dimen.swipe_icon_inset)) : (ListenerUtil.mutListener.listen(27365) ? ((float) itemView.getRight() / getResources().getDimension(R.dimen.swipe_icon_inset)) : (ListenerUtil.mutListener.listen(27364) ? ((float) itemView.getRight() * getResources().getDimension(R.dimen.swipe_icon_inset)) : (ListenerUtil.mutListener.listen(27363) ? ((float) itemView.getRight() + getResources().getDimension(R.dimen.swipe_icon_inset)) : ((float) itemView.getRight() - getResources().getDimension(R.dimen.swipe_icon_inset)))))) + icon.getIntrinsicWidth()) : ((ListenerUtil.mutListener.listen(27366) ? ((float) itemView.getRight() % getResources().getDimension(R.dimen.swipe_icon_inset)) : (ListenerUtil.mutListener.listen(27365) ? ((float) itemView.getRight() / getResources().getDimension(R.dimen.swipe_icon_inset)) : (ListenerUtil.mutListener.listen(27364) ? ((float) itemView.getRight() * getResources().getDimension(R.dimen.swipe_icon_inset)) : (ListenerUtil.mutListener.listen(27363) ? ((float) itemView.getRight() + getResources().getDimension(R.dimen.swipe_icon_inset)) : ((float) itemView.getRight() - getResources().getDimension(R.dimen.swipe_icon_inset)))))) - icon.getIntrinsicWidth()))))), (float) itemView.getTop() + (ListenerUtil.mutListener.listen(27382) ? (((ListenerUtil.mutListener.listen(27378) ? ((ListenerUtil.mutListener.listen(27374) ? ((float) itemView.getBottom() % (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27373) ? ((float) itemView.getBottom() / (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27372) ? ((float) itemView.getBottom() * (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27371) ? ((float) itemView.getBottom() + (float) itemView.getTop()) : ((float) itemView.getBottom() - (float) itemView.getTop()))))) % icon.getIntrinsicHeight()) : (ListenerUtil.mutListener.listen(27377) ? ((ListenerUtil.mutListener.listen(27374) ? ((float) itemView.getBottom() % (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27373) ? ((float) itemView.getBottom() / (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27372) ? ((float) itemView.getBottom() * (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27371) ? ((float) itemView.getBottom() + (float) itemView.getTop()) : ((float) itemView.getBottom() - (float) itemView.getTop()))))) / icon.getIntrinsicHeight()) : (ListenerUtil.mutListener.listen(27376) ? ((ListenerUtil.mutListener.listen(27374) ? ((float) itemView.getBottom() % (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27373) ? ((float) itemView.getBottom() / (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27372) ? ((float) itemView.getBottom() * (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27371) ? ((float) itemView.getBottom() + (float) itemView.getTop()) : ((float) itemView.getBottom() - (float) itemView.getTop()))))) * icon.getIntrinsicHeight()) : (ListenerUtil.mutListener.listen(27375) ? ((ListenerUtil.mutListener.listen(27374) ? ((float) itemView.getBottom() % (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27373) ? ((float) itemView.getBottom() / (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27372) ? ((float) itemView.getBottom() * (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27371) ? ((float) itemView.getBottom() + (float) itemView.getTop()) : ((float) itemView.getBottom() - (float) itemView.getTop()))))) + icon.getIntrinsicHeight()) : ((ListenerUtil.mutListener.listen(27374) ? ((float) itemView.getBottom() % (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27373) ? ((float) itemView.getBottom() / (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27372) ? ((float) itemView.getBottom() * (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27371) ? ((float) itemView.getBottom() + (float) itemView.getTop()) : ((float) itemView.getBottom() - (float) itemView.getTop()))))) - icon.getIntrinsicHeight())))))) % 2) : (ListenerUtil.mutListener.listen(27381) ? (((ListenerUtil.mutListener.listen(27378) ? ((ListenerUtil.mutListener.listen(27374) ? ((float) itemView.getBottom() % (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27373) ? ((float) itemView.getBottom() / (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27372) ? ((float) itemView.getBottom() * (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27371) ? ((float) itemView.getBottom() + (float) itemView.getTop()) : ((float) itemView.getBottom() - (float) itemView.getTop()))))) % icon.getIntrinsicHeight()) : (ListenerUtil.mutListener.listen(27377) ? ((ListenerUtil.mutListener.listen(27374) ? ((float) itemView.getBottom() % (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27373) ? ((float) itemView.getBottom() / (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27372) ? ((float) itemView.getBottom() * (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27371) ? ((float) itemView.getBottom() + (float) itemView.getTop()) : ((float) itemView.getBottom() - (float) itemView.getTop()))))) / icon.getIntrinsicHeight()) : (ListenerUtil.mutListener.listen(27376) ? ((ListenerUtil.mutListener.listen(27374) ? ((float) itemView.getBottom() % (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27373) ? ((float) itemView.getBottom() / (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27372) ? ((float) itemView.getBottom() * (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27371) ? ((float) itemView.getBottom() + (float) itemView.getTop()) : ((float) itemView.getBottom() - (float) itemView.getTop()))))) * icon.getIntrinsicHeight()) : (ListenerUtil.mutListener.listen(27375) ? ((ListenerUtil.mutListener.listen(27374) ? ((float) itemView.getBottom() % (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27373) ? ((float) itemView.getBottom() / (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27372) ? ((float) itemView.getBottom() * (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27371) ? ((float) itemView.getBottom() + (float) itemView.getTop()) : ((float) itemView.getBottom() - (float) itemView.getTop()))))) + icon.getIntrinsicHeight()) : ((ListenerUtil.mutListener.listen(27374) ? ((float) itemView.getBottom() % (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27373) ? ((float) itemView.getBottom() / (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27372) ? ((float) itemView.getBottom() * (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27371) ? ((float) itemView.getBottom() + (float) itemView.getTop()) : ((float) itemView.getBottom() - (float) itemView.getTop()))))) - icon.getIntrinsicHeight())))))) * 2) : (ListenerUtil.mutListener.listen(27380) ? (((ListenerUtil.mutListener.listen(27378) ? ((ListenerUtil.mutListener.listen(27374) ? ((float) itemView.getBottom() % (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27373) ? ((float) itemView.getBottom() / (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27372) ? ((float) itemView.getBottom() * (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27371) ? ((float) itemView.getBottom() + (float) itemView.getTop()) : ((float) itemView.getBottom() - (float) itemView.getTop()))))) % icon.getIntrinsicHeight()) : (ListenerUtil.mutListener.listen(27377) ? ((ListenerUtil.mutListener.listen(27374) ? ((float) itemView.getBottom() % (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27373) ? ((float) itemView.getBottom() / (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27372) ? ((float) itemView.getBottom() * (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27371) ? ((float) itemView.getBottom() + (float) itemView.getTop()) : ((float) itemView.getBottom() - (float) itemView.getTop()))))) / icon.getIntrinsicHeight()) : (ListenerUtil.mutListener.listen(27376) ? ((ListenerUtil.mutListener.listen(27374) ? ((float) itemView.getBottom() % (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27373) ? ((float) itemView.getBottom() / (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27372) ? ((float) itemView.getBottom() * (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27371) ? ((float) itemView.getBottom() + (float) itemView.getTop()) : ((float) itemView.getBottom() - (float) itemView.getTop()))))) * icon.getIntrinsicHeight()) : (ListenerUtil.mutListener.listen(27375) ? ((ListenerUtil.mutListener.listen(27374) ? ((float) itemView.getBottom() % (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27373) ? ((float) itemView.getBottom() / (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27372) ? ((float) itemView.getBottom() * (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27371) ? ((float) itemView.getBottom() + (float) itemView.getTop()) : ((float) itemView.getBottom() - (float) itemView.getTop()))))) + icon.getIntrinsicHeight()) : ((ListenerUtil.mutListener.listen(27374) ? ((float) itemView.getBottom() % (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27373) ? ((float) itemView.getBottom() / (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27372) ? ((float) itemView.getBottom() * (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27371) ? ((float) itemView.getBottom() + (float) itemView.getTop()) : ((float) itemView.getBottom() - (float) itemView.getTop()))))) - icon.getIntrinsicHeight())))))) - 2) : (ListenerUtil.mutListener.listen(27379) ? (((ListenerUtil.mutListener.listen(27378) ? ((ListenerUtil.mutListener.listen(27374) ? ((float) itemView.getBottom() % (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27373) ? ((float) itemView.getBottom() / (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27372) ? ((float) itemView.getBottom() * (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27371) ? ((float) itemView.getBottom() + (float) itemView.getTop()) : ((float) itemView.getBottom() - (float) itemView.getTop()))))) % icon.getIntrinsicHeight()) : (ListenerUtil.mutListener.listen(27377) ? ((ListenerUtil.mutListener.listen(27374) ? ((float) itemView.getBottom() % (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27373) ? ((float) itemView.getBottom() / (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27372) ? ((float) itemView.getBottom() * (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27371) ? ((float) itemView.getBottom() + (float) itemView.getTop()) : ((float) itemView.getBottom() - (float) itemView.getTop()))))) / icon.getIntrinsicHeight()) : (ListenerUtil.mutListener.listen(27376) ? ((ListenerUtil.mutListener.listen(27374) ? ((float) itemView.getBottom() % (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27373) ? ((float) itemView.getBottom() / (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27372) ? ((float) itemView.getBottom() * (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27371) ? ((float) itemView.getBottom() + (float) itemView.getTop()) : ((float) itemView.getBottom() - (float) itemView.getTop()))))) * icon.getIntrinsicHeight()) : (ListenerUtil.mutListener.listen(27375) ? ((ListenerUtil.mutListener.listen(27374) ? ((float) itemView.getBottom() % (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27373) ? ((float) itemView.getBottom() / (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27372) ? ((float) itemView.getBottom() * (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27371) ? ((float) itemView.getBottom() + (float) itemView.getTop()) : ((float) itemView.getBottom() - (float) itemView.getTop()))))) + icon.getIntrinsicHeight()) : ((ListenerUtil.mutListener.listen(27374) ? ((float) itemView.getBottom() % (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27373) ? ((float) itemView.getBottom() / (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27372) ? ((float) itemView.getBottom() * (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27371) ? ((float) itemView.getBottom() + (float) itemView.getTop()) : ((float) itemView.getBottom() - (float) itemView.getTop()))))) - icon.getIntrinsicHeight())))))) + 2) : (((ListenerUtil.mutListener.listen(27378) ? ((ListenerUtil.mutListener.listen(27374) ? ((float) itemView.getBottom() % (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27373) ? ((float) itemView.getBottom() / (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27372) ? ((float) itemView.getBottom() * (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27371) ? ((float) itemView.getBottom() + (float) itemView.getTop()) : ((float) itemView.getBottom() - (float) itemView.getTop()))))) % icon.getIntrinsicHeight()) : (ListenerUtil.mutListener.listen(27377) ? ((ListenerUtil.mutListener.listen(27374) ? ((float) itemView.getBottom() % (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27373) ? ((float) itemView.getBottom() / (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27372) ? ((float) itemView.getBottom() * (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27371) ? ((float) itemView.getBottom() + (float) itemView.getTop()) : ((float) itemView.getBottom() - (float) itemView.getTop()))))) / icon.getIntrinsicHeight()) : (ListenerUtil.mutListener.listen(27376) ? ((ListenerUtil.mutListener.listen(27374) ? ((float) itemView.getBottom() % (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27373) ? ((float) itemView.getBottom() / (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27372) ? ((float) itemView.getBottom() * (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27371) ? ((float) itemView.getBottom() + (float) itemView.getTop()) : ((float) itemView.getBottom() - (float) itemView.getTop()))))) * icon.getIntrinsicHeight()) : (ListenerUtil.mutListener.listen(27375) ? ((ListenerUtil.mutListener.listen(27374) ? ((float) itemView.getBottom() % (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27373) ? ((float) itemView.getBottom() / (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27372) ? ((float) itemView.getBottom() * (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27371) ? ((float) itemView.getBottom() + (float) itemView.getTop()) : ((float) itemView.getBottom() - (float) itemView.getTop()))))) + icon.getIntrinsicHeight()) : ((ListenerUtil.mutListener.listen(27374) ? ((float) itemView.getBottom() % (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27373) ? ((float) itemView.getBottom() / (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27372) ? ((float) itemView.getBottom() * (float) itemView.getTop()) : (ListenerUtil.mutListener.listen(27371) ? ((float) itemView.getBottom() + (float) itemView.getTop()) : ((float) itemView.getBottom() - (float) itemView.getTop()))))) - icon.getIntrinsicHeight())))))) / 2))))));
                                        }
                                        if (!ListenerUtil.mutListener.listen(27384)) {
                                            icon.draw(canvas);
                                        }
                                        if (!ListenerUtil.mutListener.listen(27385)) {
                                            canvas.restore();
                                        }
                                        Paint textPaint = new Paint();
                                        if (!ListenerUtil.mutListener.listen(27386)) {
                                            textPaint.setColor(Color.WHITE);
                                        }
                                        if (!ListenerUtil.mutListener.listen(27387)) {
                                            textPaint.setTextSize(getResources().getDimension(R.dimen.swipe_text_size));
                                        }
                                        Rect rect = new Rect();
                                        if (!ListenerUtil.mutListener.listen(27388)) {
                                            textPaint.getTextBounds(label, 0, label.length(), rect);
                                        }
                                        float textStartX = (ListenerUtil.mutListener.listen(27396) ? ((ListenerUtil.mutListener.listen(27392) ? (itemView.getRight() % getResources().getDimension(R.dimen.swipe_text_inset)) : (ListenerUtil.mutListener.listen(27391) ? (itemView.getRight() / getResources().getDimension(R.dimen.swipe_text_inset)) : (ListenerUtil.mutListener.listen(27390) ? (itemView.getRight() * getResources().getDimension(R.dimen.swipe_text_inset)) : (ListenerUtil.mutListener.listen(27389) ? (itemView.getRight() + getResources().getDimension(R.dimen.swipe_text_inset)) : (itemView.getRight() - getResources().getDimension(R.dimen.swipe_text_inset)))))) % rect.width()) : (ListenerUtil.mutListener.listen(27395) ? ((ListenerUtil.mutListener.listen(27392) ? (itemView.getRight() % getResources().getDimension(R.dimen.swipe_text_inset)) : (ListenerUtil.mutListener.listen(27391) ? (itemView.getRight() / getResources().getDimension(R.dimen.swipe_text_inset)) : (ListenerUtil.mutListener.listen(27390) ? (itemView.getRight() * getResources().getDimension(R.dimen.swipe_text_inset)) : (ListenerUtil.mutListener.listen(27389) ? (itemView.getRight() + getResources().getDimension(R.dimen.swipe_text_inset)) : (itemView.getRight() - getResources().getDimension(R.dimen.swipe_text_inset)))))) / rect.width()) : (ListenerUtil.mutListener.listen(27394) ? ((ListenerUtil.mutListener.listen(27392) ? (itemView.getRight() % getResources().getDimension(R.dimen.swipe_text_inset)) : (ListenerUtil.mutListener.listen(27391) ? (itemView.getRight() / getResources().getDimension(R.dimen.swipe_text_inset)) : (ListenerUtil.mutListener.listen(27390) ? (itemView.getRight() * getResources().getDimension(R.dimen.swipe_text_inset)) : (ListenerUtil.mutListener.listen(27389) ? (itemView.getRight() + getResources().getDimension(R.dimen.swipe_text_inset)) : (itemView.getRight() - getResources().getDimension(R.dimen.swipe_text_inset)))))) * rect.width()) : (ListenerUtil.mutListener.listen(27393) ? ((ListenerUtil.mutListener.listen(27392) ? (itemView.getRight() % getResources().getDimension(R.dimen.swipe_text_inset)) : (ListenerUtil.mutListener.listen(27391) ? (itemView.getRight() / getResources().getDimension(R.dimen.swipe_text_inset)) : (ListenerUtil.mutListener.listen(27390) ? (itemView.getRight() * getResources().getDimension(R.dimen.swipe_text_inset)) : (ListenerUtil.mutListener.listen(27389) ? (itemView.getRight() + getResources().getDimension(R.dimen.swipe_text_inset)) : (itemView.getRight() - getResources().getDimension(R.dimen.swipe_text_inset)))))) + rect.width()) : ((ListenerUtil.mutListener.listen(27392) ? (itemView.getRight() % getResources().getDimension(R.dimen.swipe_text_inset)) : (ListenerUtil.mutListener.listen(27391) ? (itemView.getRight() / getResources().getDimension(R.dimen.swipe_text_inset)) : (ListenerUtil.mutListener.listen(27390) ? (itemView.getRight() * getResources().getDimension(R.dimen.swipe_text_inset)) : (ListenerUtil.mutListener.listen(27389) ? (itemView.getRight() + getResources().getDimension(R.dimen.swipe_text_inset)) : (itemView.getRight() - getResources().getDimension(R.dimen.swipe_text_inset)))))) - rect.width())))));
                                        if (!ListenerUtil.mutListener.listen(27403)) {
                                            if ((ListenerUtil.mutListener.listen(27401) ? (textStartX >= 0) : (ListenerUtil.mutListener.listen(27400) ? (textStartX <= 0) : (ListenerUtil.mutListener.listen(27399) ? (textStartX > 0) : (ListenerUtil.mutListener.listen(27398) ? (textStartX != 0) : (ListenerUtil.mutListener.listen(27397) ? (textStartX == 0) : (textStartX < 0))))))) {
                                                if (!ListenerUtil.mutListener.listen(27402)) {
                                                    textStartX = 0;
                                                }
                                            }
                                        }
                                        if (!ListenerUtil.mutListener.listen(27412)) {
                                            canvas.drawText(label, textStartX, itemView.getTop() + (ListenerUtil.mutListener.listen(27411) ? (((ListenerUtil.mutListener.listen(27407) ? (itemView.getBottom() % itemView.getTop()) : (ListenerUtil.mutListener.listen(27406) ? (itemView.getBottom() / itemView.getTop()) : (ListenerUtil.mutListener.listen(27405) ? (itemView.getBottom() * itemView.getTop()) : (ListenerUtil.mutListener.listen(27404) ? (itemView.getBottom() + itemView.getTop()) : (itemView.getBottom() - itemView.getTop()))))) + rect.height()) % 2) : (ListenerUtil.mutListener.listen(27410) ? (((ListenerUtil.mutListener.listen(27407) ? (itemView.getBottom() % itemView.getTop()) : (ListenerUtil.mutListener.listen(27406) ? (itemView.getBottom() / itemView.getTop()) : (ListenerUtil.mutListener.listen(27405) ? (itemView.getBottom() * itemView.getTop()) : (ListenerUtil.mutListener.listen(27404) ? (itemView.getBottom() + itemView.getTop()) : (itemView.getBottom() - itemView.getTop()))))) + rect.height()) * 2) : (ListenerUtil.mutListener.listen(27409) ? (((ListenerUtil.mutListener.listen(27407) ? (itemView.getBottom() % itemView.getTop()) : (ListenerUtil.mutListener.listen(27406) ? (itemView.getBottom() / itemView.getTop()) : (ListenerUtil.mutListener.listen(27405) ? (itemView.getBottom() * itemView.getTop()) : (ListenerUtil.mutListener.listen(27404) ? (itemView.getBottom() + itemView.getTop()) : (itemView.getBottom() - itemView.getTop()))))) + rect.height()) - 2) : (ListenerUtil.mutListener.listen(27408) ? (((ListenerUtil.mutListener.listen(27407) ? (itemView.getBottom() % itemView.getTop()) : (ListenerUtil.mutListener.listen(27406) ? (itemView.getBottom() / itemView.getTop()) : (ListenerUtil.mutListener.listen(27405) ? (itemView.getBottom() * itemView.getTop()) : (ListenerUtil.mutListener.listen(27404) ? (itemView.getBottom() + itemView.getTop()) : (itemView.getBottom() - itemView.getTop()))))) + rect.height()) + 2) : (((ListenerUtil.mutListener.listen(27407) ? (itemView.getBottom() % itemView.getTop()) : (ListenerUtil.mutListener.listen(27406) ? (itemView.getBottom() / itemView.getTop()) : (ListenerUtil.mutListener.listen(27405) ? (itemView.getBottom() * itemView.getTop()) : (ListenerUtil.mutListener.listen(27404) ? (itemView.getBottom() + itemView.getTop()) : (itemView.getBottom() - itemView.getTop()))))) + rect.height()) / 2))))), textPaint);
                                        }
                                    }
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(27446)) {
                            super.onChildDraw(canvas, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
                        }
                    }

                    @Override
                    public float getSwipeEscapeVelocity(float defaultValue) {
                        return (ListenerUtil.mutListener.listen(27450) ? (defaultValue % 20) : (ListenerUtil.mutListener.listen(27449) ? (defaultValue / 20) : (ListenerUtil.mutListener.listen(27448) ? (defaultValue - 20) : (ListenerUtil.mutListener.listen(27447) ? (defaultValue + 20) : (defaultValue * 20)))));
                    }

                    @Override
                    public float getSwipeVelocityThreshold(float defaultValue) {
                        return (ListenerUtil.mutListener.listen(27454) ? (defaultValue % 5) : (ListenerUtil.mutListener.listen(27453) ? (defaultValue / 5) : (ListenerUtil.mutListener.listen(27452) ? (defaultValue - 5) : (ListenerUtil.mutListener.listen(27451) ? (defaultValue + 5) : (defaultValue * 5)))));
                    }
                };
                ItemTouchHelper itemTouchHelper = new ItemTouchHelper(swipeCallback);
                if (!ListenerUtil.mutListener.listen(27455)) {
                    itemTouchHelper.attachToRecyclerView(recyclerView);
                }
                if (!ListenerUtil.mutListener.listen(27456)) {
                    // disable change animation to avoid avatar flicker FX
                    ((SimpleItemAnimator) this.recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
                }
                if (!ListenerUtil.mutListener.listen(27457)) {
                    this.loadingView = fragmentView.findViewById(R.id.session_loading);
                }
                if (!ListenerUtil.mutListener.listen(27458)) {
                    ViewUtil.show(this.loadingView, true);
                }
                if (!ListenerUtil.mutListener.listen(27459)) {
                    this.floatingButtonView = fragmentView.findViewById(R.id.floating);
                }
                if (!ListenerUtil.mutListener.listen(27461)) {
                    this.floatingButtonView.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            if (!ListenerUtil.mutListener.listen(27460)) {
                                onFABClicked(v);
                            }
                        }
                    });
                }
                // add text view if contact list is empty
                EmptyView emptyView = new EmptyView(activity);
                if (!ListenerUtil.mutListener.listen(27462)) {
                    emptyView.setup(R.string.no_recent_conversations);
                }
                if (!ListenerUtil.mutListener.listen(27463)) {
                    ((ViewGroup) recyclerView.getParent()).addView(emptyView);
                }
                if (!ListenerUtil.mutListener.listen(27464)) {
                    recyclerView.setNumHeadersAndFooters(-1);
                }
                if (!ListenerUtil.mutListener.listen(27465)) {
                    recyclerView.setEmptyView(emptyView);
                }
                if (!ListenerUtil.mutListener.listen(27470)) {
                    recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

                        @Override
                        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                            if (!ListenerUtil.mutListener.listen(27466)) {
                                super.onScrolled(recyclerView, dx, dy);
                            }
                            if (!ListenerUtil.mutListener.listen(27469)) {
                                if (linearLayoutManager.findFirstVisibleItemPosition() == 0) {
                                    if (!ListenerUtil.mutListener.listen(27468)) {
                                        floatingButtonView.extend();
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(27467)) {
                                        floatingButtonView.shrink();
                                    }
                                }
                            }
                        }
                    });
                }
                if (!ListenerUtil.mutListener.listen(27486)) {
                    recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

                        private final int TOUCH_SAFE_AREA_PX = 5;

                        // ignore touches at the very left and right edge of the screen to prevent interference with UI gestures
                        @Override
                        public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                            int width = getResources().getDisplayMetrics().widthPixels;
                            int touchX = (int) e.getRawX();
                            return (ListenerUtil.mutListener.listen(27485) ? ((ListenerUtil.mutListener.listen(27475) ? (touchX >= TOUCH_SAFE_AREA_PX) : (ListenerUtil.mutListener.listen(27474) ? (touchX <= TOUCH_SAFE_AREA_PX) : (ListenerUtil.mutListener.listen(27473) ? (touchX > TOUCH_SAFE_AREA_PX) : (ListenerUtil.mutListener.listen(27472) ? (touchX != TOUCH_SAFE_AREA_PX) : (ListenerUtil.mutListener.listen(27471) ? (touchX == TOUCH_SAFE_AREA_PX) : (touchX < TOUCH_SAFE_AREA_PX)))))) && (ListenerUtil.mutListener.listen(27484) ? (touchX >= (ListenerUtil.mutListener.listen(27479) ? (width % TOUCH_SAFE_AREA_PX) : (ListenerUtil.mutListener.listen(27478) ? (width / TOUCH_SAFE_AREA_PX) : (ListenerUtil.mutListener.listen(27477) ? (width * TOUCH_SAFE_AREA_PX) : (ListenerUtil.mutListener.listen(27476) ? (width + TOUCH_SAFE_AREA_PX) : (width - TOUCH_SAFE_AREA_PX)))))) : (ListenerUtil.mutListener.listen(27483) ? (touchX <= (ListenerUtil.mutListener.listen(27479) ? (width % TOUCH_SAFE_AREA_PX) : (ListenerUtil.mutListener.listen(27478) ? (width / TOUCH_SAFE_AREA_PX) : (ListenerUtil.mutListener.listen(27477) ? (width * TOUCH_SAFE_AREA_PX) : (ListenerUtil.mutListener.listen(27476) ? (width + TOUCH_SAFE_AREA_PX) : (width - TOUCH_SAFE_AREA_PX)))))) : (ListenerUtil.mutListener.listen(27482) ? (touchX < (ListenerUtil.mutListener.listen(27479) ? (width % TOUCH_SAFE_AREA_PX) : (ListenerUtil.mutListener.listen(27478) ? (width / TOUCH_SAFE_AREA_PX) : (ListenerUtil.mutListener.listen(27477) ? (width * TOUCH_SAFE_AREA_PX) : (ListenerUtil.mutListener.listen(27476) ? (width + TOUCH_SAFE_AREA_PX) : (width - TOUCH_SAFE_AREA_PX)))))) : (ListenerUtil.mutListener.listen(27481) ? (touchX != (ListenerUtil.mutListener.listen(27479) ? (width % TOUCH_SAFE_AREA_PX) : (ListenerUtil.mutListener.listen(27478) ? (width / TOUCH_SAFE_AREA_PX) : (ListenerUtil.mutListener.listen(27477) ? (width * TOUCH_SAFE_AREA_PX) : (ListenerUtil.mutListener.listen(27476) ? (width + TOUCH_SAFE_AREA_PX) : (width - TOUCH_SAFE_AREA_PX)))))) : (ListenerUtil.mutListener.listen(27480) ? (touchX == (ListenerUtil.mutListener.listen(27479) ? (width % TOUCH_SAFE_AREA_PX) : (ListenerUtil.mutListener.listen(27478) ? (width / TOUCH_SAFE_AREA_PX) : (ListenerUtil.mutListener.listen(27477) ? (width * TOUCH_SAFE_AREA_PX) : (ListenerUtil.mutListener.listen(27476) ? (width + TOUCH_SAFE_AREA_PX) : (width - TOUCH_SAFE_AREA_PX)))))) : (touchX > (ListenerUtil.mutListener.listen(27479) ? (width % TOUCH_SAFE_AREA_PX) : (ListenerUtil.mutListener.listen(27478) ? (width / TOUCH_SAFE_AREA_PX) : (ListenerUtil.mutListener.listen(27477) ? (width * TOUCH_SAFE_AREA_PX) : (ListenerUtil.mutListener.listen(27476) ? (width + TOUCH_SAFE_AREA_PX) : (width - TOUCH_SAFE_AREA_PX)))))))))))) : ((ListenerUtil.mutListener.listen(27475) ? (touchX >= TOUCH_SAFE_AREA_PX) : (ListenerUtil.mutListener.listen(27474) ? (touchX <= TOUCH_SAFE_AREA_PX) : (ListenerUtil.mutListener.listen(27473) ? (touchX > TOUCH_SAFE_AREA_PX) : (ListenerUtil.mutListener.listen(27472) ? (touchX != TOUCH_SAFE_AREA_PX) : (ListenerUtil.mutListener.listen(27471) ? (touchX == TOUCH_SAFE_AREA_PX) : (touchX < TOUCH_SAFE_AREA_PX)))))) || (ListenerUtil.mutListener.listen(27484) ? (touchX >= (ListenerUtil.mutListener.listen(27479) ? (width % TOUCH_SAFE_AREA_PX) : (ListenerUtil.mutListener.listen(27478) ? (width / TOUCH_SAFE_AREA_PX) : (ListenerUtil.mutListener.listen(27477) ? (width * TOUCH_SAFE_AREA_PX) : (ListenerUtil.mutListener.listen(27476) ? (width + TOUCH_SAFE_AREA_PX) : (width - TOUCH_SAFE_AREA_PX)))))) : (ListenerUtil.mutListener.listen(27483) ? (touchX <= (ListenerUtil.mutListener.listen(27479) ? (width % TOUCH_SAFE_AREA_PX) : (ListenerUtil.mutListener.listen(27478) ? (width / TOUCH_SAFE_AREA_PX) : (ListenerUtil.mutListener.listen(27477) ? (width * TOUCH_SAFE_AREA_PX) : (ListenerUtil.mutListener.listen(27476) ? (width + TOUCH_SAFE_AREA_PX) : (width - TOUCH_SAFE_AREA_PX)))))) : (ListenerUtil.mutListener.listen(27482) ? (touchX < (ListenerUtil.mutListener.listen(27479) ? (width % TOUCH_SAFE_AREA_PX) : (ListenerUtil.mutListener.listen(27478) ? (width / TOUCH_SAFE_AREA_PX) : (ListenerUtil.mutListener.listen(27477) ? (width * TOUCH_SAFE_AREA_PX) : (ListenerUtil.mutListener.listen(27476) ? (width + TOUCH_SAFE_AREA_PX) : (width - TOUCH_SAFE_AREA_PX)))))) : (ListenerUtil.mutListener.listen(27481) ? (touchX != (ListenerUtil.mutListener.listen(27479) ? (width % TOUCH_SAFE_AREA_PX) : (ListenerUtil.mutListener.listen(27478) ? (width / TOUCH_SAFE_AREA_PX) : (ListenerUtil.mutListener.listen(27477) ? (width * TOUCH_SAFE_AREA_PX) : (ListenerUtil.mutListener.listen(27476) ? (width + TOUCH_SAFE_AREA_PX) : (width - TOUCH_SAFE_AREA_PX)))))) : (ListenerUtil.mutListener.listen(27480) ? (touchX == (ListenerUtil.mutListener.listen(27479) ? (width % TOUCH_SAFE_AREA_PX) : (ListenerUtil.mutListener.listen(27478) ? (width / TOUCH_SAFE_AREA_PX) : (ListenerUtil.mutListener.listen(27477) ? (width * TOUCH_SAFE_AREA_PX) : (ListenerUtil.mutListener.listen(27476) ? (width + TOUCH_SAFE_AREA_PX) : (width - TOUCH_SAFE_AREA_PX)))))) : (touchX > (ListenerUtil.mutListener.listen(27479) ? (width % TOUCH_SAFE_AREA_PX) : (ListenerUtil.mutListener.listen(27478) ? (width / TOUCH_SAFE_AREA_PX) : (ListenerUtil.mutListener.listen(27477) ? (width * TOUCH_SAFE_AREA_PX) : (ListenerUtil.mutListener.listen(27476) ? (width + TOUCH_SAFE_AREA_PX) : (width - TOUCH_SAFE_AREA_PX)))))))))))));
                        }

                        @Override
                        public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                        }

                        @Override
                        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
                        }
                    });
                }
                if (!ListenerUtil.mutListener.listen(27489)) {
                    // 
                    if (!this.requiredInstances()) {
                        if (!ListenerUtil.mutListener.listen(27488)) {
                            logger.error("could not instantiate required objects");
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(27487)) {
                            this.unreadTagModel = this.conversationTagService.getTagModel(ConversationTagServiceImpl.FIXED_TAG_UNREAD);
                        }
                    }
                }
            }
        }
        return fragmentView;
    }

    private void onFABClicked(View v) {
        if (!ListenerUtil.mutListener.listen(27491)) {
            // stop list fling to avoid crashes due to concurrent access to conversation data
            recyclerView.stopScroll();
        }
        Intent intent = new Intent(getContext(), RecipientListBaseActivity.class);
        if (!ListenerUtil.mutListener.listen(27492)) {
            intent.putExtra(ThreemaApplication.INTENT_DATA_HIDE_RECENTS, true);
        }
        if (!ListenerUtil.mutListener.listen(27493)) {
            intent.putExtra(RecipientListBaseActivity.INTENT_DATA_MULTISELECT, false);
        }
        if (!ListenerUtil.mutListener.listen(27494)) {
            AnimationUtil.startActivityForResult(this.getActivity(), v, intent, ThreemaActivity.ACTIVITY_ID_COMPOSE_MESSAGE);
        }
    }

    @Override
    public void onDestroy() {
        if (!ListenerUtil.mutListener.listen(27495)) {
            this.removeListeners();
        }
        if (!ListenerUtil.mutListener.listen(27497)) {
            if (this.resumePauseHandler != null) {
                if (!ListenerUtil.mutListener.listen(27496)) {
                    this.resumePauseHandler.onDestroy(this);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(27498)) {
            super.onDestroy();
        }
    }

    @Override
    public void onItemClick(View view, int position, ConversationModel model) {
        if (!ListenerUtil.mutListener.listen(27499)) {
            showConversation(model, view);
        }
    }

    @Override
    public void onAvatarClick(View view, int position, ConversationModel model) {
        Intent intent = null;
        if (!ListenerUtil.mutListener.listen(27506)) {
            if (model.isContactConversation()) {
                if (!ListenerUtil.mutListener.listen(27504)) {
                    intent = new Intent(getActivity(), ContactDetailActivity.class);
                }
                if (!ListenerUtil.mutListener.listen(27505)) {
                    intent.putExtra(ThreemaApplication.INTENT_DATA_CONTACT, model.getContact().getIdentity());
                }
            } else if ((ListenerUtil.mutListener.listen(27500) ? (model.isGroupConversation() || groupService.isGroupMember(model.getGroup())) : (model.isGroupConversation() && groupService.isGroupMember(model.getGroup())))) {
                if (!ListenerUtil.mutListener.listen(27503)) {
                    editGroup(model, view);
                }
            } else if (model.isDistributionListConversation()) {
                if (!ListenerUtil.mutListener.listen(27501)) {
                    intent = new Intent(getActivity(), DistributionListAddActivity.class);
                }
                if (!ListenerUtil.mutListener.listen(27502)) {
                    intent.putExtra(ThreemaApplication.INTENT_DATA_DISTRIBUTION_LIST, model.getDistributionList().getId());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(27508)) {
            if (intent != null) {
                if (!ListenerUtil.mutListener.listen(27507)) {
                    AnimationUtil.startActivityForResult(activity, view, intent, 0);
                }
            }
        }
    }

    @Override
    public void onFooterClick(View view) {
        Intent intent = new Intent(getActivity(), ArchiveActivity.class);
        if (!ListenerUtil.mutListener.listen(27509)) {
            intent.putExtra(ThreemaApplication.INTENT_DATA_ARCHIVE_FILTER, filterQuery);
        }
        if (!ListenerUtil.mutListener.listen(27510)) {
            AnimationUtil.startActivity(getActivity(), TestUtil.empty(filterQuery) ? view : null, intent);
        }
    }

    private void editGroup(ConversationModel model, View view) {
        Intent intent = groupService.getGroupEditIntent(model.getGroup(), activity);
        if (!ListenerUtil.mutListener.listen(27511)) {
            intent.putExtra(ThreemaApplication.INTENT_DATA_GROUP, model.getGroup().getId());
        }
        if (!ListenerUtil.mutListener.listen(27512)) {
            AnimationUtil.startActivityForResult(activity, view, intent, 0);
        }
    }

    @Override
    public boolean onItemLongClick(View view, int position, ConversationModel conversationModel) {
        if (!ListenerUtil.mutListener.listen(27515)) {
            if (!isMultiPaneEnabled(activity)) {
                if (!ListenerUtil.mutListener.listen(27513)) {
                    messageListAdapter.toggleItemChecked(conversationModel, position);
                }
                if (!ListenerUtil.mutListener.listen(27514)) {
                    showSelector();
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public void onProgressbarCanceled(String tag) {
        if (!ListenerUtil.mutListener.listen(27517)) {
            if (this.backupChatService != null) {
                if (!ListenerUtil.mutListener.listen(27516)) {
                    this.backupChatService.cancel();
                }
            }
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!ListenerUtil.mutListener.listen(27518)) {
            logger.debug("*** onHiddenChanged: " + hidden);
        }
        if (!ListenerUtil.mutListener.listen(27528)) {
            if (hidden) {
                if (!ListenerUtil.mutListener.listen(27525)) {
                    if ((ListenerUtil.mutListener.listen(27523) ? ((ListenerUtil.mutListener.listen(27522) ? ((ListenerUtil.mutListener.listen(27521) ? (this.searchView != null || this.searchView.isShown()) : (this.searchView != null && this.searchView.isShown())) || this.searchMenuItemRef != null) : ((ListenerUtil.mutListener.listen(27521) ? (this.searchView != null || this.searchView.isShown()) : (this.searchView != null && this.searchView.isShown())) && this.searchMenuItemRef != null)) || this.searchMenuItemRef.get() != null) : ((ListenerUtil.mutListener.listen(27522) ? ((ListenerUtil.mutListener.listen(27521) ? (this.searchView != null || this.searchView.isShown()) : (this.searchView != null && this.searchView.isShown())) || this.searchMenuItemRef != null) : ((ListenerUtil.mutListener.listen(27521) ? (this.searchView != null || this.searchView.isShown()) : (this.searchView != null && this.searchView.isShown())) && this.searchMenuItemRef != null)) && this.searchMenuItemRef.get() != null))) {
                        if (!ListenerUtil.mutListener.listen(27524)) {
                            this.searchMenuItemRef.get().collapseActionView();
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(27527)) {
                    if (this.resumePauseHandler != null) {
                        if (!ListenerUtil.mutListener.listen(27526)) {
                            this.resumePauseHandler.onPause();
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(27520)) {
                    if (this.resumePauseHandler != null) {
                        if (!ListenerUtil.mutListener.listen(27519)) {
                            this.resumePauseHandler.onResume();
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onPause() {
        if (!ListenerUtil.mutListener.listen(27529)) {
            super.onPause();
        }
        if (!ListenerUtil.mutListener.listen(27530)) {
            logger.debug("*** onPause");
        }
        if (!ListenerUtil.mutListener.listen(27532)) {
            if (this.resumePauseHandler != null) {
                if (!ListenerUtil.mutListener.listen(27531)) {
                    this.resumePauseHandler.onPause();
                }
            }
        }
    }

    @Override
    public void onResume() {
        if (!ListenerUtil.mutListener.listen(27533)) {
            logger.debug("*** onResume");
        }
        if (!ListenerUtil.mutListener.listen(27535)) {
            if (this.resumePauseHandler != null) {
                if (!ListenerUtil.mutListener.listen(27534)) {
                    this.resumePauseHandler.onResume();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(27549)) {
            if (this.preferenceService != null) {
                if (!ListenerUtil.mutListener.listen(27548)) {
                    if ((ListenerUtil.mutListener.listen(27541) ? ((ListenerUtil.mutListener.listen(27540) ? (android.os.Build.VERSION.SDK_INT <= android.os.Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(27539) ? (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(27538) ? (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(27537) ? (android.os.Build.VERSION.SDK_INT != android.os.Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(27536) ? (android.os.Build.VERSION.SDK_INT == android.os.Build.VERSION_CODES.M) : (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M)))))) || (PreferenceService.LockingMech_SYSTEM.equals(preferenceService.getLockMechanism()))) : ((ListenerUtil.mutListener.listen(27540) ? (android.os.Build.VERSION.SDK_INT <= android.os.Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(27539) ? (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(27538) ? (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(27537) ? (android.os.Build.VERSION.SDK_INT != android.os.Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(27536) ? (android.os.Build.VERSION.SDK_INT == android.os.Build.VERSION_CODES.M) : (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M)))))) && (PreferenceService.LockingMech_SYSTEM.equals(preferenceService.getLockMechanism()))))) {
                        KeyguardManager keyguardManager = (KeyguardManager) getActivity().getSystemService(Context.KEYGUARD_SERVICE);
                        if (!ListenerUtil.mutListener.listen(27547)) {
                            if (!keyguardManager.isDeviceSecure()) {
                                if (!ListenerUtil.mutListener.listen(27542)) {
                                    Toast.makeText(getActivity(), R.string.no_lockscreen_set, Toast.LENGTH_LONG).show();
                                }
                                if (!ListenerUtil.mutListener.listen(27543)) {
                                    preferenceService.setLockMechanism(PreferenceService.LockingMech_NONE);
                                }
                                if (!ListenerUtil.mutListener.listen(27544)) {
                                    preferenceService.setAppLockEnabled(false);
                                }
                                if (!ListenerUtil.mutListener.listen(27545)) {
                                    preferenceService.setPrivateChatsHidden(false);
                                }
                                if (!ListenerUtil.mutListener.listen(27546)) {
                                    updateList(0, null, null);
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(27550)) {
            updateHiddenMenuVisibility();
        }
        if (!ListenerUtil.mutListener.listen(27551)) {
            super.onResume();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (!ListenerUtil.mutListener.listen(27552)) {
            logger.info("saveInstance");
        }
        if (!ListenerUtil.mutListener.listen(27554)) {
            if (!TestUtil.empty(filterQuery)) {
                if (!ListenerUtil.mutListener.listen(27553)) {
                    outState.putString(BUNDLE_FILTER_QUERY, filterQuery);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(27555)) {
            super.onSaveInstanceState(outState);
        }
    }

    @Override
    public void onYes(String tag, String text, boolean isChecked, Object data) {
        if (!ListenerUtil.mutListener.listen(27556)) {
            shareChat((ConversationModel) data, text, isChecked);
        }
    }

    private void showSelector() {
        ArrayList<String> labels = new ArrayList<>();
        ArrayList<Integer> tags = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(27557)) {
            if (messageListAdapter.getCheckedItemCount() != 1) {
                return;
            }
        }
        ConversationModel conversationModel = messageListAdapter.getCheckedItems().get(0);
        if (!ListenerUtil.mutListener.listen(27558)) {
            if (conversationModel == null) {
                return;
            }
        }
        MessageReceiver receiver;
        try {
            receiver = conversationModel.getReceiver();
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(27559)) {
                logger.error("Exception", e);
            }
            return;
        }
        if (!ListenerUtil.mutListener.listen(27560)) {
            if (receiver == null) {
                return;
            }
        }
        boolean isPrivate = hiddenChatsListService.has(receiver.getUniqueIdString());
        if (!ListenerUtil.mutListener.listen(27566)) {
            if ((ListenerUtil.mutListener.listen(27561) ? (conversationModel.hasUnreadMessage() && conversationTagService.isTaggedWith(conversationModel, unreadTagModel)) : (conversationModel.hasUnreadMessage() || conversationTagService.isTaggedWith(conversationModel, unreadTagModel)))) {
                if (!ListenerUtil.mutListener.listen(27564)) {
                    labels.add(getString(R.string.mark_read));
                }
                if (!ListenerUtil.mutListener.listen(27565)) {
                    tags.add(TAG_MARK_READ);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(27562)) {
                    labels.add(getString(R.string.mark_unread));
                }
                if (!ListenerUtil.mutListener.listen(27563)) {
                    tags.add(TAG_MARK_UNREAD);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(27571)) {
            if (isPrivate) {
                if (!ListenerUtil.mutListener.listen(27569)) {
                    labels.add(getString(R.string.unset_private));
                }
                if (!ListenerUtil.mutListener.listen(27570)) {
                    tags.add(TAG_UNSET_PRIVATE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(27567)) {
                    labels.add(getString(R.string.set_private));
                }
                if (!ListenerUtil.mutListener.listen(27568)) {
                    tags.add(TAG_SET_PRIVATE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(27575)) {
            if ((ListenerUtil.mutListener.listen(27572) ? (!isPrivate || !AppRestrictionUtil.isExportDisabled(getActivity())) : (!isPrivate && !AppRestrictionUtil.isExportDisabled(getActivity())))) {
                if (!ListenerUtil.mutListener.listen(27573)) {
                    labels.add(getString(R.string.share_chat));
                }
                if (!ListenerUtil.mutListener.listen(27574)) {
                    tags.add(TAG_SHARE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(27583)) {
            if ((ListenerUtil.mutListener.listen(27580) ? (conversationModel.getMessageCount() >= 0) : (ListenerUtil.mutListener.listen(27579) ? (conversationModel.getMessageCount() <= 0) : (ListenerUtil.mutListener.listen(27578) ? (conversationModel.getMessageCount() < 0) : (ListenerUtil.mutListener.listen(27577) ? (conversationModel.getMessageCount() != 0) : (ListenerUtil.mutListener.listen(27576) ? (conversationModel.getMessageCount() == 0) : (conversationModel.getMessageCount() > 0))))))) {
                if (!ListenerUtil.mutListener.listen(27581)) {
                    labels.add(getString(R.string.empty_chat_title));
                }
                if (!ListenerUtil.mutListener.listen(27582)) {
                    tags.add(TAG_EMPTY_CHAT);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(27599)) {
            if (conversationModel.isDistributionListConversation()) {
                if (!ListenerUtil.mutListener.listen(27597)) {
                    // distribution lists
                    labels.add(getString(R.string.really_delete_distribution_list));
                }
                if (!ListenerUtil.mutListener.listen(27598)) {
                    tags.add(TAG_DELETE_DISTRIBUTION_LIST);
                }
            } else if (conversationModel.isGroupConversation()) {
                if (!ListenerUtil.mutListener.listen(27587)) {
                    // group chats
                    if ((ListenerUtil.mutListener.listen(27584) ? (groupService.isGroupOwner(conversationModel.getGroup()) || groupService.isGroupMember(conversationModel.getGroup())) : (groupService.isGroupOwner(conversationModel.getGroup()) && groupService.isGroupMember(conversationModel.getGroup())))) {
                        if (!ListenerUtil.mutListener.listen(27585)) {
                            labels.add(getString(R.string.group_edit_title));
                        }
                        if (!ListenerUtil.mutListener.listen(27586)) {
                            tags.add(TAG_EDIT_GROUP);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(27590)) {
                    if (groupService.isGroupMember(conversationModel.getGroup())) {
                        if (!ListenerUtil.mutListener.listen(27588)) {
                            labels.add(getString(R.string.action_leave_group));
                        }
                        if (!ListenerUtil.mutListener.listen(27589)) {
                            tags.add(TAG_LEAVE_GROUP);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(27591)) {
                    labels.add(getString(R.string.action_delete_group));
                }
                if (!ListenerUtil.mutListener.listen(27596)) {
                    if (groupService.isGroupMember(conversationModel.getGroup())) {
                        if (!ListenerUtil.mutListener.listen(27595)) {
                            if (groupService.isGroupOwner(conversationModel.getGroup())) {
                                if (!ListenerUtil.mutListener.listen(27594)) {
                                    tags.add(TAG_DELETE_MY_GROUP);
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(27593)) {
                                    tags.add(TAG_DELETE_GROUP);
                                }
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(27592)) {
                            tags.add(TAG_DELETE_LEFT_GROUP);
                        }
                    }
                }
            }
        }
        SelectorDialog selectorDialog = SelectorDialog.newInstance(receiver.getDisplayName(), labels, tags, getString(R.string.cancel));
        if (!ListenerUtil.mutListener.listen(27600)) {
            selectorDialog.setData(conversationModel);
        }
        if (!ListenerUtil.mutListener.listen(27601)) {
            selectorDialog.setTargetFragment(this, 0);
        }
        if (!ListenerUtil.mutListener.listen(27602)) {
            selectorDialog.show(getFragmentManager(), DIALOG_TAG_SELECT_DELETE_ACTION);
        }
    }

    @SuppressLint("StringFormatInvalid")
    @Override
    public void onClick(String tag, int which, Object data) {
        GenericAlertDialog dialog;
        if (!ListenerUtil.mutListener.listen(27603)) {
            messageListAdapter.clearSelections();
        }
        final ConversationModel conversationModel = (ConversationModel) data;
        switch(which) {
            case TAG_EMPTY_CHAT:
                dialog = GenericAlertDialog.newInstance(R.string.empty_chat_title, R.string.empty_chat_confirm, R.string.ok, R.string.cancel);
                if (!ListenerUtil.mutListener.listen(27604)) {
                    dialog.setData(conversationModel);
                }
                if (!ListenerUtil.mutListener.listen(27605)) {
                    dialog.setTargetFragment(this, 0);
                }
                if (!ListenerUtil.mutListener.listen(27606)) {
                    dialog.show(getFragmentManager(), DIALOG_TAG_REALLY_EMPTY_CHAT);
                }
                break;
            case TAG_DELETE_DISTRIBUTION_LIST:
                dialog = GenericAlertDialog.newInstance(R.string.really_delete_distribution_list, R.string.really_delete_distribution_list_message, R.string.ok, R.string.cancel);
                if (!ListenerUtil.mutListener.listen(27607)) {
                    dialog.setTargetFragment(this, 0);
                }
                if (!ListenerUtil.mutListener.listen(27608)) {
                    dialog.setData(conversationModel.getDistributionList());
                }
                if (!ListenerUtil.mutListener.listen(27609)) {
                    dialog.show(getFragmentManager(), DIALOG_TAG_REALLY_DELETE_DISTRIBUTION_LIST);
                }
                break;
            case TAG_LEAVE_GROUP:
                int leaveMessageRes = groupService.isGroupOwner(conversationModel.getGroup()) ? R.string.really_leave_group_admin_message : R.string.really_leave_group_message;
                dialog = GenericAlertDialog.newInstance(R.string.action_leave_group, Html.fromHtml(getString(leaveMessageRes)), R.string.ok, R.string.cancel);
                if (!ListenerUtil.mutListener.listen(27610)) {
                    dialog.setTargetFragment(this, 0);
                }
                if (!ListenerUtil.mutListener.listen(27611)) {
                    dialog.setData(conversationModel.getGroup());
                }
                if (!ListenerUtil.mutListener.listen(27612)) {
                    dialog.show(getFragmentManager(), DIALOG_TAG_REALLY_LEAVE_GROUP);
                }
                break;
            case TAG_EDIT_GROUP:
                if (!ListenerUtil.mutListener.listen(27613)) {
                    editGroup(conversationModel, null);
                }
                break;
            case TAG_DELETE_MY_GROUP:
                dialog = GenericAlertDialog.newInstance(R.string.action_delete_group, R.string.delete_my_group_message, R.string.ok, R.string.cancel);
                if (!ListenerUtil.mutListener.listen(27614)) {
                    dialog.setTargetFragment(this, 0);
                }
                if (!ListenerUtil.mutListener.listen(27615)) {
                    dialog.setData(conversationModel.getGroup());
                }
                if (!ListenerUtil.mutListener.listen(27616)) {
                    dialog.show(getFragmentManager(), DIALOG_TAG_REALLY_DELETE_MY_GROUP);
                }
                break;
            case TAG_DELETE_GROUP:
                dialog = GenericAlertDialog.newInstance(R.string.action_delete_group, String.format(getString(R.string.delete_group_message), 1), R.string.ok, R.string.cancel);
                if (!ListenerUtil.mutListener.listen(27617)) {
                    dialog.setTargetFragment(this, 0);
                }
                if (!ListenerUtil.mutListener.listen(27618)) {
                    dialog.setData(conversationModel.getGroup());
                }
                if (!ListenerUtil.mutListener.listen(27619)) {
                    dialog.show(getFragmentManager(), DIALOG_TAG_REALLY_DELETE_GROUP);
                }
                break;
            case TAG_DELETE_LEFT_GROUP:
                dialog = GenericAlertDialog.newInstance(R.string.action_delete_group, String.format(getString(R.string.delete_left_group_message), 1), R.string.ok, R.string.cancel);
                if (!ListenerUtil.mutListener.listen(27620)) {
                    dialog.setTargetFragment(this, 0);
                }
                if (!ListenerUtil.mutListener.listen(27621)) {
                    dialog.setData(conversationModel.getGroup());
                }
                if (!ListenerUtil.mutListener.listen(27622)) {
                    dialog.show(getFragmentManager(), DIALOG_TAG_REALLY_DELETE_GROUP);
                }
                break;
            case TAG_SET_PRIVATE:
            case TAG_UNSET_PRIVATE:
                if (!ListenerUtil.mutListener.listen(27623)) {
                    hideChat(conversationModel);
                }
                break;
            case TAG_SHARE:
                if (!ListenerUtil.mutListener.listen(27625)) {
                    if (ConfigUtils.requestStoragePermissions(activity, this, PERMISSION_REQUEST_SHARE_THREAD)) {
                        if (!ListenerUtil.mutListener.listen(27624)) {
                            prepareShareChat(conversationModel);
                        }
                    }
                }
                break;
            case TAG_MARK_READ:
                if (!ListenerUtil.mutListener.listen(27626)) {
                    conversationTagService.unTag(conversationModel, unreadTagModel);
                }
                if (!ListenerUtil.mutListener.listen(27628)) {
                    new Thread(new Runnable() {

                        @Override
                        public void run() {
                            if (!ListenerUtil.mutListener.listen(27627)) {
                                messageService.markConversationAsRead(conversationModel.getReceiver(), serviceManager.getNotificationService());
                            }
                        }
                    }).start();
                }
                break;
            case TAG_MARK_UNREAD:
                if (!ListenerUtil.mutListener.listen(27629)) {
                    conversationTagService.tag(conversationModel, unreadTagModel);
                }
                break;
        }
    }

    @Override
    public void onCancel(String tag) {
        if (!ListenerUtil.mutListener.listen(27630)) {
            messageListAdapter.clearSelections();
        }
    }

    @Override
    public void onNo(String tag) {
        if (!ListenerUtil.mutListener.listen(27632)) {
            if (DIALOG_TAG_SELECT_DELETE_ACTION.equals(tag)) {
                if (!ListenerUtil.mutListener.listen(27631)) {
                    messageListAdapter.clearSelections();
                }
            }
        }
    }

    @Override
    public void onYes(String tag, Object data) {
        if (!ListenerUtil.mutListener.listen(27644)) {
            switch(tag) {
                case DIALOG_TAG_REALLY_HIDE_THREAD:
                    if (!ListenerUtil.mutListener.listen(27633)) {
                        reallyHideChat((ConversationModel) data);
                    }
                    break;
                case DIALOG_TAG_HIDE_THREAD_EXPLAIN:
                    if (!ListenerUtil.mutListener.listen(27634)) {
                        selectedConversation = (ConversationModel) data;
                    }
                    Intent intent = new Intent(activity, SettingsActivity.class);
                    if (!ListenerUtil.mutListener.listen(27635)) {
                        intent.putExtra(PreferenceActivity.EXTRA_SHOW_FRAGMENT, SettingsSecurityFragment.class.getName());
                    }
                    if (!ListenerUtil.mutListener.listen(27636)) {
                        intent.putExtra(PreferenceActivity.EXTRA_NO_HEADERS, true);
                    }
                    if (!ListenerUtil.mutListener.listen(27637)) {
                        startActivityForResult(intent, ID_RETURN_FROM_SECURITY_SETTINGS);
                    }
                    break;
                case DIALOG_TAG_REALLY_DELETE_MY_GROUP:
                    if (!ListenerUtil.mutListener.listen(27638)) {
                        new DeleteMyGroupAsyncTask((GroupModel) data, groupService, null, this, null).execute();
                    }
                    break;
                case DIALOG_TAG_REALLY_LEAVE_GROUP:
                    if (!ListenerUtil.mutListener.listen(27639)) {
                        new LeaveGroupAsyncTask((GroupModel) data, groupService, null, this, null).execute();
                    }
                    break;
                case DIALOG_TAG_REALLY_DELETE_GROUP:
                    if (!ListenerUtil.mutListener.listen(27640)) {
                        new DeleteGroupAsyncTask((GroupModel) data, groupService, null, this, null).execute();
                    }
                    break;
                case DIALOG_TAG_REALLY_DELETE_DISTRIBUTION_LIST:
                    if (!ListenerUtil.mutListener.listen(27641)) {
                        new DeleteDistributionListAsyncTask((DistributionListModel) data, distributionListService, this, null).execute();
                    }
                    break;
                case DIALOG_TAG_REALLY_EMPTY_CHAT:
                    final ConversationModel conversationModel = (ConversationModel) data;
                    if (!ListenerUtil.mutListener.listen(27643)) {
                        new EmptyChatAsyncTask(new MessageReceiver[] { conversationModel.getReceiver() }, messageService, getFragmentManager(), false, new Runnable() {

                            @Override
                            public void run() {
                                if (!ListenerUtil.mutListener.listen(27642)) {
                                    conversationListeners.handle(listener -> {
                                        conversationService.clear(conversationModel);
                                        listener.onModified(conversationModel, null);
                                    });
                                }
                            }
                        }).execute();
                    }
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void onNo(String tag, Object data) {
    }

    @Override
    public boolean onBackPressed() {
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (!ListenerUtil.mutListener.listen(27654)) {
            switch(requestCode) {
                case PERMISSION_REQUEST_SHARE_THREAD:
                    if (!ListenerUtil.mutListener.listen(27653)) {
                        if ((ListenerUtil.mutListener.listen(27650) ? ((ListenerUtil.mutListener.listen(27649) ? (grantResults.length >= 0) : (ListenerUtil.mutListener.listen(27648) ? (grantResults.length <= 0) : (ListenerUtil.mutListener.listen(27647) ? (grantResults.length < 0) : (ListenerUtil.mutListener.listen(27646) ? (grantResults.length != 0) : (ListenerUtil.mutListener.listen(27645) ? (grantResults.length == 0) : (grantResults.length > 0)))))) || grantResults[0] == PackageManager.PERMISSION_GRANTED) : ((ListenerUtil.mutListener.listen(27649) ? (grantResults.length >= 0) : (ListenerUtil.mutListener.listen(27648) ? (grantResults.length <= 0) : (ListenerUtil.mutListener.listen(27647) ? (grantResults.length < 0) : (ListenerUtil.mutListener.listen(27646) ? (grantResults.length != 0) : (ListenerUtil.mutListener.listen(27645) ? (grantResults.length == 0) : (grantResults.length > 0)))))) && grantResults[0] == PackageManager.PERMISSION_GRANTED))) {
                            if (!ListenerUtil.mutListener.listen(27652)) {
                                prepareShareChat(selectedConversation);
                            }
                        } else if (!shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                            if (!ListenerUtil.mutListener.listen(27651)) {
                                ConfigUtils.showPermissionRationale(getContext(), getView(), R.string.permission_storage_required);
                            }
                        }
                    }
                    break;
            }
        }
    }

    private void setupListeners() {
        if (!ListenerUtil.mutListener.listen(27655)) {
            logger.debug("*** setup listeners");
        }
        if (!ListenerUtil.mutListener.listen(27656)) {
            // set listeners
            conversationListeners.add(this.conversationListener);
        }
        if (!ListenerUtil.mutListener.listen(27657)) {
            ListenerManager.contactListeners.add(this.contactListener);
        }
        if (!ListenerUtil.mutListener.listen(27658)) {
            ListenerManager.contactSettingsListeners.add(this.contactSettingsListener);
        }
        if (!ListenerUtil.mutListener.listen(27659)) {
            ListenerManager.synchronizeContactsListeners.add(this.synchronizeContactsListener);
        }
        if (!ListenerUtil.mutListener.listen(27660)) {
            ListenerManager.chatListener.add(this.chatListener);
        }
    }

    private void removeListeners() {
        if (!ListenerUtil.mutListener.listen(27661)) {
            logger.debug("*** remove listeners");
        }
        if (!ListenerUtil.mutListener.listen(27662)) {
            conversationListeners.remove(this.conversationListener);
        }
        if (!ListenerUtil.mutListener.listen(27663)) {
            ListenerManager.contactListeners.remove(this.contactListener);
        }
        if (!ListenerUtil.mutListener.listen(27664)) {
            ListenerManager.contactSettingsListeners.remove(this.contactSettingsListener);
        }
        if (!ListenerUtil.mutListener.listen(27665)) {
            ListenerManager.synchronizeContactsListeners.remove(this.synchronizeContactsListener);
        }
        if (!ListenerUtil.mutListener.listen(27666)) {
            ListenerManager.chatListener.remove(this.chatListener);
        }
    }

    private void updateList() {
        if (!ListenerUtil.mutListener.listen(27667)) {
            this.updateList(null, null, null);
        }
    }

    private void updateList(final Integer scrollToPosition, final List<ConversationModel> changedPositions, final Runnable runAfterSetData) {
        if (!ListenerUtil.mutListener.listen(27668)) {
            this.updateList(scrollToPosition, changedPositions, runAfterSetData, false);
        }
    }

    @SuppressLint("StaticFieldLeak")
    private void updateList(final Integer scrollToPosition, final List<ConversationModel> changedPositions, final Runnable runAfterSetData, boolean recreate) {
        if (!ListenerUtil.mutListener.listen(27670)) {
            // require
            if (!this.requiredInstances()) {
                if (!ListenerUtil.mutListener.listen(27669)) {
                    logger.error("could not instantiate required objects");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(27671)) {
            logger.debug("*** update list [" + scrollToPosition + ", " + (changedPositions != null ? changedPositions.size() : "0") + "]");
        }
        Thread updateListThread = new Thread(new Runnable() {

            @Override
            public void run() {
                List<ConversationModel> conversationModels;
                conversationModels = conversationService.getAll(false, new ConversationService.Filter() {

                    @Override
                    public boolean onlyUnread() {
                        return false;
                    }

                    @Override
                    public boolean noDistributionLists() {
                        return false;
                    }

                    @Override
                    public boolean noHiddenChats() {
                        return preferenceService.isPrivateChatsHidden();
                    }

                    @Override
                    public boolean noInvalid() {
                        return false;
                    }

                    @Override
                    public String filterQuery() {
                        return filterQuery;
                    }
                });
                if (!ListenerUtil.mutListener.listen(27695)) {
                    RuntimeUtil.runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            synchronized (messageListAdapterLock) {
                                if (!ListenerUtil.mutListener.listen(27675)) {
                                    if ((ListenerUtil.mutListener.listen(27672) ? (messageListAdapter == null && recreate) : (messageListAdapter == null || recreate))) {
                                        if (!ListenerUtil.mutListener.listen(27673)) {
                                            messageListAdapter = new MessageListAdapter(MessageSectionFragment.this.activity, contactService, groupService, distributionListService, conversationService, mutedChatsListService, mentionOnlyChatsListService, hiddenChatsListService, conversationTagService, ringtoneService, highlightUid, MessageSectionFragment.this);
                                        }
                                        if (!ListenerUtil.mutListener.listen(27674)) {
                                            recyclerView.setAdapter(messageListAdapter);
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(27678)) {
                                    if (messageListAdapter != null) {
                                        if (!ListenerUtil.mutListener.listen(27676)) {
                                            messageListAdapter.setData(conversationModels, changedPositions);
                                        }
                                        if (!ListenerUtil.mutListener.listen(27677)) {
                                            // make sure footer is refreshed
                                            messageListAdapter.refreshFooter();
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(27692)) {
                                    if (recyclerView != null) {
                                        if (!ListenerUtil.mutListener.listen(27691)) {
                                            if (scrollToPosition != null) {
                                                if (!ListenerUtil.mutListener.listen(27690)) {
                                                    if ((ListenerUtil.mutListener.listen(27679) ? (changedPositions != null || changedPositions.size() == 1) : (changedPositions != null && changedPositions.size() == 1))) {
                                                        ConversationModel changedModel = changedPositions.get(0);
                                                        if (!ListenerUtil.mutListener.listen(27689)) {
                                                            if (changedModel != null) {
                                                                final List<ConversationModel> copyOfModels = new ArrayList<>(conversationModels);
                                                                if (!ListenerUtil.mutListener.listen(27688)) {
                                                                    {
                                                                        long _loopCounter174 = 0;
                                                                        for (ConversationModel model : copyOfModels) {
                                                                            ListenerUtil.loopListener.listen("_loopCounter174", ++_loopCounter174);
                                                                            if (!ListenerUtil.mutListener.listen(27687)) {
                                                                                if (model.equals(changedModel)) {
                                                                                    if (!ListenerUtil.mutListener.listen(27686)) {
                                                                                        if ((ListenerUtil.mutListener.listen(27684) ? (scrollToPosition >= changedModel.getPosition()) : (ListenerUtil.mutListener.listen(27683) ? (scrollToPosition <= changedModel.getPosition()) : (ListenerUtil.mutListener.listen(27682) ? (scrollToPosition < changedModel.getPosition()) : (ListenerUtil.mutListener.listen(27681) ? (scrollToPosition != changedModel.getPosition()) : (ListenerUtil.mutListener.listen(27680) ? (scrollToPosition == changedModel.getPosition()) : (scrollToPosition > changedModel.getPosition()))))))) {
                                                                                            if (!ListenerUtil.mutListener.listen(27685)) {
                                                                                                recyclerView.scrollToPosition(changedModel.getPosition());
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                    break;
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
                            if (!ListenerUtil.mutListener.listen(27694)) {
                                if (runAfterSetData != null) {
                                    if (!ListenerUtil.mutListener.listen(27693)) {
                                        runAfterSetData.run();
                                    }
                                }
                            }
                        }
                    });
                }
            }
        });
        if (!ListenerUtil.mutListener.listen(27698)) {
            if (messageListAdapter == null) {
                if (!ListenerUtil.mutListener.listen(27697)) {
                    // hack: run synchronously when setting up the adapter for the first time to avoid showing an empty list
                    updateListThread.run();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(27696)) {
                    updateListThread.start();
                }
            }
        }
    }

    private void updateHiddenMenuVisibility() {
        if (!ListenerUtil.mutListener.listen(27710)) {
            if ((ListenerUtil.mutListener.listen(27700) ? ((ListenerUtil.mutListener.listen(27699) ? (isAdded() || toggleHiddenMenuItemRef != null) : (isAdded() && toggleHiddenMenuItemRef != null)) || toggleHiddenMenuItemRef.get() != null) : ((ListenerUtil.mutListener.listen(27699) ? (isAdded() || toggleHiddenMenuItemRef != null) : (isAdded() && toggleHiddenMenuItemRef != null)) && toggleHiddenMenuItemRef.get() != null))) {
                if (!ListenerUtil.mutListener.listen(27708)) {
                    if (hiddenChatsListService != null) {
                        if (!ListenerUtil.mutListener.listen(27707)) {
                            toggleHiddenMenuItemRef.get().setVisible((ListenerUtil.mutListener.listen(27706) ? ((ListenerUtil.mutListener.listen(27705) ? (hiddenChatsListService.getSize() >= 0) : (ListenerUtil.mutListener.listen(27704) ? (hiddenChatsListService.getSize() <= 0) : (ListenerUtil.mutListener.listen(27703) ? (hiddenChatsListService.getSize() < 0) : (ListenerUtil.mutListener.listen(27702) ? (hiddenChatsListService.getSize() != 0) : (ListenerUtil.mutListener.listen(27701) ? (hiddenChatsListService.getSize() == 0) : (hiddenChatsListService.getSize() > 0)))))) || ConfigUtils.hasProtection(preferenceService)) : ((ListenerUtil.mutListener.listen(27705) ? (hiddenChatsListService.getSize() >= 0) : (ListenerUtil.mutListener.listen(27704) ? (hiddenChatsListService.getSize() <= 0) : (ListenerUtil.mutListener.listen(27703) ? (hiddenChatsListService.getSize() < 0) : (ListenerUtil.mutListener.listen(27702) ? (hiddenChatsListService.getSize() != 0) : (ListenerUtil.mutListener.listen(27701) ? (hiddenChatsListService.getSize() == 0) : (hiddenChatsListService.getSize() > 0)))))) && ConfigUtils.hasProtection(preferenceService))));
                        }
                        return;
                    }
                }
                if (!ListenerUtil.mutListener.listen(27709)) {
                    toggleHiddenMenuItemRef.get().setVisible(false);
                }
            }
        }
    }

    private boolean isMultiPaneEnabled(Activity activity) {
        if (!ListenerUtil.mutListener.listen(27712)) {
            if (activity != null) {
                return (ListenerUtil.mutListener.listen(27711) ? (ConfigUtils.isTabletLayout() || activity instanceof ComposeMessageActivity) : (ConfigUtils.isTabletLayout() && activity instanceof ComposeMessageActivity));
            }
        }
        return false;
    }

    private void fireReceiverUpdate(final MessageReceiver receiver) {
        if (!ListenerUtil.mutListener.listen(27717)) {
            if (receiver instanceof GroupMessageReceiver) {
                if (!ListenerUtil.mutListener.listen(27716)) {
                    ListenerManager.groupListeners.handle(new ListenerManager.HandleListener<GroupListener>() {

                        @Override
                        public void handle(GroupListener listener) {
                            if (!ListenerUtil.mutListener.listen(27715)) {
                                listener.onUpdate(((GroupMessageReceiver) receiver).getGroup());
                            }
                        }
                    });
                }
            } else if (receiver instanceof ContactMessageReceiver) {
                if (!ListenerUtil.mutListener.listen(27714)) {
                    ListenerManager.contactListeners.handle(new ListenerManager.HandleListener<ContactListener>() {

                        @Override
                        public void handle(ContactListener listener) {
                            if (!ListenerUtil.mutListener.listen(27713)) {
                                listener.onModified(((ContactMessageReceiver) receiver).getContact());
                            }
                        }
                    });
                }
            }
        }
    }

    @WorkerThread
    private void fireSecretReceiverUpdate() {
        if (!ListenerUtil.mutListener.listen(27721)) {
            {
                long _loopCounter175 = 0;
                // fire a update for every secret receiver (to update webclient data)
                for (ConversationModel c : Functional.filter(this.conversationService.getAll(false, null), new IPredicateNonNull<ConversationModel>() {

                    @Override
                    public boolean apply(ConversationModel conversationModel) {
                        return (ListenerUtil.mutListener.listen(27720) ? (conversationModel != null || hiddenChatsListService.has(conversationModel.getReceiver().getUniqueIdString())) : (conversationModel != null && hiddenChatsListService.has(conversationModel.getReceiver().getUniqueIdString())));
                    }
                })) {
                    ListenerUtil.loopListener.listen("_loopCounter175", ++_loopCounter175);
                    if (!ListenerUtil.mutListener.listen(27719)) {
                        if (c != null) {
                            if (!ListenerUtil.mutListener.listen(27718)) {
                                this.fireReceiverUpdate(c.getReceiver());
                            }
                        }
                    }
                }
            }
        }
    }

    public void onLogoClicked() {
        if (!ListenerUtil.mutListener.listen(27724)) {
            if (this.recyclerView != null) {
                if (!ListenerUtil.mutListener.listen(27722)) {
                    this.recyclerView.stopScroll();
                }
                if (!ListenerUtil.mutListener.listen(27723)) {
                    this.recyclerView.scrollToPosition(0);
                }
            }
        }
    }
}
