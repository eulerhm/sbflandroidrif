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
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.os.Vibrator;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.format.DateUtils;
import android.util.DisplayMetrics;
import android.util.Pair;
import android.view.Gravity;
import android.view.HapticFeedbackConstants;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.view.inputmethod.EditorInfo;
import android.widget.AbsListView;
import android.widget.Filter;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetView;
import com.google.android.material.snackbar.Snackbar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.regex.Pattern;
import androidx.annotation.AnyThread;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import androidx.annotation.WorkerThread;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.appcompat.widget.SearchView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.core.content.pm.ShortcutManagerCompat;
import androidx.core.graphics.ColorUtils;
import androidx.core.view.MenuItemCompat;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.transition.Slide;
import androidx.transition.Transition;
import androidx.transition.TransitionManager;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.actions.SendAction;
import ch.threema.app.actions.TextMessageSendAction;
import ch.threema.app.activities.ComposeMessageActivity;
import ch.threema.app.activities.ContactDetailActivity;
import ch.threema.app.activities.ContactNotificationsActivity;
import ch.threema.app.activities.DistributionListAddActivity;
import ch.threema.app.activities.GroupNotificationsActivity;
import ch.threema.app.activities.HomeActivity;
import ch.threema.app.activities.MediaGalleryActivity;
import ch.threema.app.activities.RecipientListBaseActivity;
import ch.threema.app.activities.SendMediaActivity;
import ch.threema.app.activities.TextChatBubbleActivity;
import ch.threema.app.activities.ThreemaActivity;
import ch.threema.app.activities.ThreemaToolbarActivity;
import ch.threema.app.activities.WorkExplainActivity;
import ch.threema.app.activities.ballot.BallotOverviewActivity;
import ch.threema.app.adapters.ComposeMessageAdapter;
import ch.threema.app.adapters.decorators.ChatAdapterDecorator;
import ch.threema.app.asynctasks.EmptyChatAsyncTask;
import ch.threema.app.cache.ThumbnailCache;
import ch.threema.app.dialogs.ExpandableTextEntryDialog;
import ch.threema.app.dialogs.GenericAlertDialog;
import ch.threema.app.dialogs.GenericProgressDialog;
import ch.threema.app.dialogs.MessageDetailDialog;
import ch.threema.app.dialogs.SelectorDialog;
import ch.threema.app.emojis.EmojiButton;
import ch.threema.app.emojis.EmojiMarkupUtil;
import ch.threema.app.emojis.EmojiPicker;
import ch.threema.app.emojis.EmojiTextView;
import ch.threema.app.listeners.BallotListener;
import ch.threema.app.listeners.ContactListener;
import ch.threema.app.listeners.ContactTypingListener;
import ch.threema.app.listeners.ConversationListener;
import ch.threema.app.listeners.GroupListener;
import ch.threema.app.listeners.MessageListener;
import ch.threema.app.listeners.MessagePlayerListener;
import ch.threema.app.listeners.QRCodeScanListener;
import ch.threema.app.managers.ListenerManager;
import ch.threema.app.managers.ServiceManager;
import ch.threema.app.mediaattacher.MediaAttachActivity;
import ch.threema.app.mediaattacher.MediaFilterQuery;
import ch.threema.app.messagereceiver.ContactMessageReceiver;
import ch.threema.app.messagereceiver.MessageReceiver;
import ch.threema.app.routines.ReadMessagesRoutine;
import ch.threema.app.services.ContactService;
import ch.threema.app.services.ConversationService;
import ch.threema.app.services.DeadlineListService;
import ch.threema.app.services.DeviceService;
import ch.threema.app.services.DistributionListService;
import ch.threema.app.services.DownloadService;
import ch.threema.app.services.FileService;
import ch.threema.app.services.GroupService;
import ch.threema.app.services.IdListService;
import ch.threema.app.services.MessageService;
import ch.threema.app.services.NotificationService;
import ch.threema.app.services.PreferenceService;
import ch.threema.app.services.RingtoneService;
import ch.threema.app.services.ShortcutService;
import ch.threema.app.services.UserService;
import ch.threema.app.services.WallpaperService;
import ch.threema.app.services.ballot.BallotService;
import ch.threema.app.services.license.LicenseService;
import ch.threema.app.services.messageplayer.MessagePlayerService;
import ch.threema.app.ui.AvatarView;
import ch.threema.app.ui.ContentCommitComposeEditText;
import ch.threema.app.ui.ConversationListView;
import ch.threema.app.ui.DebouncedOnClickListener;
import ch.threema.app.ui.ListViewSwipeListener;
import ch.threema.app.ui.MentionSelectorPopup;
import ch.threema.app.ui.OpenBallotNoticeView;
import ch.threema.app.ui.QRCodePopup;
import ch.threema.app.ui.SendButton;
import ch.threema.app.ui.SingleToast;
import ch.threema.app.ui.TooltipPopup;
import ch.threema.app.ui.TypingIndicatorTextWatcher;
import ch.threema.app.ui.VerificationLevelImageView;
import ch.threema.app.ui.listitemholder.ComposeMessageHolder;
import ch.threema.app.utils.AnimationUtil;
import ch.threema.app.utils.AppRestrictionUtil;
import ch.threema.app.utils.BallotUtil;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.ContactUtil;
import ch.threema.app.utils.ConversationUtil;
import ch.threema.app.utils.DialogUtil;
import ch.threema.app.utils.EditTextUtil;
import ch.threema.app.utils.FileUtil;
import ch.threema.app.utils.IntentDataUtil;
import ch.threema.app.utils.LinkifyUtil;
import ch.threema.app.utils.LocaleUtil;
import ch.threema.app.utils.LogUtil;
import ch.threema.app.utils.MediaPlayerStateWrapper;
import ch.threema.app.utils.MessageUtil;
import ch.threema.app.utils.NameUtil;
import ch.threema.app.utils.NavigationUtil;
import ch.threema.app.utils.QuoteUtil;
import ch.threema.app.utils.RuntimeUtil;
import ch.threema.app.utils.TestUtil;
import ch.threema.app.utils.ToolbarUtil;
import ch.threema.app.voicemessage.VoiceRecorderActivity;
import ch.threema.app.voip.listeners.VoipCallEventListener;
import ch.threema.app.voip.managers.VoipListenerManager;
import ch.threema.app.voip.services.VoipStateService;
import ch.threema.app.voip.util.VoipUtil;
import ch.threema.client.IdentityType;
import ch.threema.client.file.FileData;
import ch.threema.storage.models.AbstractMessageModel;
import ch.threema.storage.models.ContactModel;
import ch.threema.storage.models.ConversationModel;
import ch.threema.storage.models.DateSeparatorMessageModel;
import ch.threema.storage.models.DistributionListMessageModel;
import ch.threema.storage.models.DistributionListModel;
import ch.threema.storage.models.FirstUnreadMessageModel;
import ch.threema.storage.models.GroupMessageModel;
import ch.threema.storage.models.GroupModel;
import ch.threema.storage.models.MessageState;
import ch.threema.storage.models.MessageType;
import ch.threema.storage.models.ballot.BallotModel;
import static android.view.WindowManager.LayoutParams.FLAG_SECURE;
import static ch.threema.app.ThreemaApplication.getAppContext;
import static ch.threema.app.services.messageplayer.MessagePlayer.SOURCE_AUDIORECORDER;
import static ch.threema.app.services.messageplayer.MessagePlayer.SOURCE_LIFECYCLE;
import static ch.threema.app.services.messageplayer.MessagePlayer.SOURCE_VOIP;
import static ch.threema.app.utils.LinkifyUtil.DIALOG_TAG_CONFIRM_LINK;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ComposeMessageFragment extends Fragment implements LifecycleOwner, SwipeRefreshLayout.OnRefreshListener, GenericAlertDialog.DialogClickListener, ChatAdapterDecorator.ActionModeStatus, SelectorDialog.SelectorDialogClickListener, EmojiPicker.EmojiPickerListener, MentionSelectorPopup.MentionSelectorListener, OpenBallotNoticeView.VisibilityListener, ThreemaToolbarActivity.OnSoftKeyboardChangedListener, ExpandableTextEntryDialog.ExpandableTextEntryDialogClickListener {

    private static final Logger logger = LoggerFactory.getLogger(ComposeMessageFragment.class);

    private static final String CONFIRM_TAG_DELETE_DISTRIBUTION_LIST = "deleteDistributionList";

    public static final String DIALOG_TAG_CONFIRM_CALL = "dtcc";

    private static final String DIALOG_TAG_CHOOSE_SHORTCUT_TYPE = "st";

    private static final String DIALOG_TAG_EMPTY_CHAT = "ccc";

    private static final String DIALOG_TAG_CONFIRM_BLOCK = "block";

    private static final String DIALOG_TAG_DECRYPTING_MESSAGES = "dcr";

    private static final String DIALOG_TAG_SEARCHING = "src";

    private static final String DIALOG_TAG_LOADING_MESSAGES = "loadm";

    public static final String EXTRA_API_MESSAGE_ID = "apimsgid";

    public static final String EXTRA_SEARCH_QUERY = "searchQuery";

    public static final String EXTRA_LAST_MEDIA_SEARCH_QUERY = "searchMediaQuery";

    public static final String EXTRA_LAST_MEDIA_TYPE_QUERY = "searchMediaType";

    private static final int PERMISSION_REQUEST_SAVE_MESSAGE = 2;

    private static final int PERMISSION_REQUEST_ATTACH_VOICE_MESSAGE = 7;

    private static final int PERMISSION_REQUEST_ATTACH_CAMERA = 8;

    private static final int PERMISSION_REQUEST_ATTACH_CAMERA_VIDEO = 11;

    private static final int ACTIVITY_ID_VOICE_RECORDER = 9731;

    public static final long VIBRATION_MSEC = 300;

    private static final long MESSAGE_PAGE_SIZE = 100;

    public static final int SCROLLBUTTON_VIEW_TIMEOUT = 3000;

    private static final int SMOOTHSCROLL_THRESHOLD = 10;

    // may not be larger than MESSAGE_PAGE_SIZE
    private static final int MAX_SELECTED_ITEMS = 100;

    private static final int MAX_FORWARDABLE_ITEMS = 50;

    private static final int CONTEXT_MENU_BOLD = 700;

    private static final int CONTEXT_MENU_ITALIC = 701;

    private static final int CONTEXT_MENU_STRIKETHRU = 702;

    private static final int CONTEXT_MENU_GROUP = 22100;

    private static final String CAMERA_URI = "camera_uri";

    private ContentCommitComposeEditText messageText;

    private SendButton sendButton;

    private ImageButton attachButton, cameraButton;

    private ContactModel contactModel;

    private MessageReceiver messageReceiver;

    private AudioManager audioManager;

    private ConversationListView convListView;

    private ComposeMessageAdapter composeMessageAdapter;

    private View isTypingView;

    private MenuItem mutedMenuItem = null, blockMenuItem = null, deleteDistributionListItem = null, callItem = null, shortCutItem = null, showOpenBallotWindowMenuItem = null, showBallotsMenuItem = null;

    private TextView dateTextView;

    private ActionMode actionMode = null;

    private ActionMode searchActionMode = null;

    private ImageView quickscrollDownView = null, quickscrollUpView = null;

    private FrameLayout dateView = null;

    private FrameLayout bottomPanel = null;

    private String identity;

    private Integer groupId = 0, distributionListId = 0;

    private Uri cameraUri;

    private long intentTimestamp = 0L;

    private int longClickItem = AbsListView.INVALID_POSITION;

    private int listViewTop = 0, lastFirstVisibleItem = -1;

    private Snackbar deleteSnackbar;

    private TypingIndicatorTextWatcher typingIndicatorTextWatcher;

    private Map<String, Integer> identityColors;

    private MediaFilterQuery lastMediaFilter;

    private PreferenceService preferenceService;

    private ContactService contactService;

    private MessageService messageService;

    private NotificationService notificationService;

    private IdListService blackListIdentityService;

    private ConversationService conversationService;

    private DeviceService deviceService;

    private WallpaperService wallpaperService;

    private DeadlineListService mutedChatsListService, mentionOnlyChatsListService, hiddenChatsListService;

    private RingtoneService ringtoneService;

    private UserService userService;

    private FileService fileService;

    private VoipStateService voipStateService;

    private ShortcutService shortcutService;

    private DownloadService downloadService;

    private LicenseService licenseService;

    private boolean listUpdateInProgress = false, isPaused = false;

    private final List<AbstractMessageModel> unreadMessages = new ArrayList<>();

    private final List<AbstractMessageModel> messageValues = new ArrayList<>();

    private final List<AbstractMessageModel> selectedMessages = new ArrayList<>(1);

    private final List<Pair<AbstractMessageModel, Integer>> deleteableMessages = new ArrayList<>(1);

    private EmojiMarkupUtil emojiMarkupUtil;

    private EmojiPicker emojiPicker;

    private EmojiButton emojiButton;

    private SwipeRefreshLayout swipeRefreshLayout;

    private Integer currentPageReferenceId = null;

    private EmojiTextView actionBarTitleTextView;

    private TextView actionBarSubtitleTextView;

    private VerificationLevelImageView actionBarSubtitleImageView;

    private AvatarView actionBarAvatarView;

    private ImageView wallpaperView;

    private ActionBar actionBar;

    private MentionSelectorPopup mentionPopup;

    private TooltipPopup workTooltipPopup;

    private OpenBallotNoticeView openBallotNoticeView;

    private ComposeMessageActivity activity;

    private View fragmentView;

    private CoordinatorLayout coordinatorLayout;

    private BallotService ballotService;

    private LayoutInflater layoutInflater;

    private ListViewSwipeListener listViewSwipeListener;

    private GroupService groupService;

    private boolean isGroupChat = false;

    private GroupModel groupModel;

    private Date listInitializedAt;

    private boolean isDistributionListChat = false;

    private DistributionListService distributionListService;

    private DistributionListModel distributionListModel;

    private MessagePlayerService messagePlayerService;

    private int listInstancePosition = AbsListView.INVALID_POSITION;

    private int listInstanceTop = 0;

    private String listInstanceReceiverId = null;

    private int unreadCount = 0;

    private boolean hasFocus = false;

    private final QuoteInfo quoteInfo = new QuoteInfo();

    private TextView searchCounter;

    private ProgressBar searchProgress;

    private ImageView searchNextButton, searchPreviousButton;

    @SuppressLint("SimpleDateFormat")
    private final SimpleDateFormat dayFormatter = new SimpleDateFormat("yyyyMMdd");

    private ThumbnailCache<?> thumbnailCache = null;

    private static class QuoteInfo {

        private FrameLayout quotePanel;

        private TextView quoteTextView, quoteIdentityView;

        private String quoteText, quoteIdentity;

        private View quoteBar;

        private ImageView quoteThumbnail;

        private ImageView quoteTypeImage;

        private AbstractMessageModel messageModel;
    }

    @Override
    public boolean getActionModeEnabled() {
        return actionMode != null;
    }

    private final MessageService.MessageFilter nextMessageFilter = new MessageService.MessageFilter() {

        @Override
        public long getPageSize() {
            return MESSAGE_PAGE_SIZE;
        }

        @Override
        public Integer getPageReferenceId() {
            return getCurrentPageReferenceId();
        }

        @Override
        public boolean withStatusMessages() {
            return true;
        }

        @Override
        public boolean withUnsaved() {
            return false;
        }

        @Override
        public boolean onlyUnread() {
            return false;
        }

        @Override
        public boolean onlyDownloaded() {
            return false;
        }

        @Override
        public MessageType[] types() {
            return null;
        }

        @Override
        public int[] contentTypes() {
            return null;
        }
    };

    // handler to remove dateview button after a certain time
    private final Handler dateViewHandler = new Handler();

    private final Runnable dateViewTask = () -> RuntimeUtil.runOnUiThread(() -> {
        if (dateView != null && dateView.getVisibility() == View.VISIBLE) {
            AnimationUtil.slideOutAnimation(dateView, false, 1f, null);
            AnimationUtil.setFadingVisibility(quickscrollUpView, View.GONE);
            AnimationUtil.setFadingVisibility(quickscrollDownView, View.GONE);
        }
    });

    // Listeners
    private final VoipCallEventListener voipCallEventListener = new VoipCallEventListener() {

        @Override
        public void onRinging(String peerIdentity) {
        }

        @Override
        public void onStarted(String peerIdentity, boolean outgoing) {
            if (!ListenerUtil.mutListener.listen(24437)) {
                logger.debug("VoipCallEventListener onStarted");
            }
            if (!ListenerUtil.mutListener.listen(24438)) {
                updateVoipCallMenuItem(false);
            }
            if (!ListenerUtil.mutListener.listen(24440)) {
                if (messagePlayerService != null) {
                    if (!ListenerUtil.mutListener.listen(24439)) {
                        messagePlayerService.pauseAll(SOURCE_VOIP);
                    }
                }
            }
        }

        @Override
        public void onFinished(@NonNull String peerIdentity, boolean outgoing, int duration) {
            if (!ListenerUtil.mutListener.listen(24441)) {
                logger.debug("VoipCallEventListener onFinished");
            }
            if (!ListenerUtil.mutListener.listen(24442)) {
                updateVoipCallMenuItem(true);
            }
        }

        @Override
        public void onRejected(String peerIdentity, boolean outgoing, byte reason) {
            if (!ListenerUtil.mutListener.listen(24443)) {
                logger.debug("VoipCallEventListener onRejected");
            }
            if (!ListenerUtil.mutListener.listen(24444)) {
                updateVoipCallMenuItem(true);
            }
        }

        @Override
        public void onMissed(String peerIdentity, boolean accepted) {
            if (!ListenerUtil.mutListener.listen(24445)) {
                logger.debug("VoipCallEventListener onMissed");
            }
            if (!ListenerUtil.mutListener.listen(24446)) {
                updateVoipCallMenuItem(true);
            }
        }

        @Override
        public void onAborted(String peerIdentity) {
            if (!ListenerUtil.mutListener.listen(24447)) {
                logger.debug("VoipCallEventListener onAborted");
            }
            if (!ListenerUtil.mutListener.listen(24448)) {
                updateVoipCallMenuItem(true);
            }
        }
    };

    private final MessageListener messageListener = new MessageListener() {

        @Override
        public void onNew(final AbstractMessageModel newMessage) {
            if (!ListenerUtil.mutListener.listen(24450)) {
                if (newMessage != null) {
                    if (!ListenerUtil.mutListener.listen(24449)) {
                        RuntimeUtil.runOnUiThread(() -> {
                            if (newMessage.isOutbox()) {
                                if (addMessageToList(newMessage)) {
                                    if (!newMessage.isStatusMessage() && (newMessage.getType() != MessageType.VOIP_STATUS)) {
                                        playSentSound();
                                    }
                                }
                            } else {
                                if (addMessageToList(newMessage) && !isPaused) {
                                    if (!newMessage.isStatusMessage() && (newMessage.getType() != MessageType.VOIP_STATUS)) {
                                        playReceivedSound();
                                    }
                                }
                            }
                        });
                    }
                }
            }
        }

        @Override
        public void onModified(final List<AbstractMessageModel> modifiedMessageModels) {
            // replace model
            synchronized (messageValues) {
                if (!ListenerUtil.mutListener.listen(24462)) {
                    {
                        long _loopCounter163 = 0;
                        for (final AbstractMessageModel modifiedMessageModel : modifiedMessageModels) {
                            ListenerUtil.loopListener.listen("_loopCounter163", ++_loopCounter163);
                            if (!ListenerUtil.mutListener.listen(24461)) {
                                if (modifiedMessageModel.getId() != 0) {
                                    if (!ListenerUtil.mutListener.listen(24460)) {
                                        {
                                            long _loopCounter162 = 0;
                                            for (int n = 0; (ListenerUtil.mutListener.listen(24459) ? (n >= messageValues.size()) : (ListenerUtil.mutListener.listen(24458) ? (n <= messageValues.size()) : (ListenerUtil.mutListener.listen(24457) ? (n > messageValues.size()) : (ListenerUtil.mutListener.listen(24456) ? (n != messageValues.size()) : (ListenerUtil.mutListener.listen(24455) ? (n == messageValues.size()) : (n < messageValues.size())))))); n++) {
                                                ListenerUtil.loopListener.listen("_loopCounter162", ++_loopCounter162);
                                                AbstractMessageModel listModel = messageValues.get(n);
                                                if (!ListenerUtil.mutListener.listen(24454)) {
                                                    if ((ListenerUtil.mutListener.listen(24451) ? (listModel != null || listModel.getId() == modifiedMessageModel.getId()) : (listModel != null && listModel.getId() == modifiedMessageModel.getId()))) {
                                                        if (!ListenerUtil.mutListener.listen(24453)) {
                                                            // if the changed message is different to the created
                                                            if (modifiedMessageModel != listModel) {
                                                                if (!ListenerUtil.mutListener.listen(24452)) {
                                                                    // replace item
                                                                    messageValues.set(n, modifiedMessageModel);
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
            if (!ListenerUtil.mutListener.listen(24463)) {
                RuntimeUtil.runOnUiThread(() -> {
                    if (composeMessageAdapter != null) {
                        composeMessageAdapter.notifyDataSetChanged();
                    }
                });
            }
        }

        @Override
        public void onRemoved(final AbstractMessageModel removedMessageModel) {
            if (!ListenerUtil.mutListener.listen(24464)) {
                RuntimeUtil.runOnUiThread(() -> {
                    if (TestUtil.required(composeMessageAdapter, removedMessageModel)) {
                        composeMessageAdapter.remove(removedMessageModel);
                        composeMessageAdapter.notifyDataSetChanged();
                    }
                });
            }
        }

        @Override
        public void onProgressChanged(AbstractMessageModel messageModel, int newProgress) {
            if (!ListenerUtil.mutListener.listen(24465)) {
                // ignore
                RuntimeUtil.runOnUiThread(() -> {
                    if (composeMessageAdapter != null) {
                        composeMessageAdapter.notifyDataSetChanged();
                    }
                });
            }
        }
    };

    private final GroupListener groupListener = new GroupListener() {

        @Override
        public void onCreate(GroupModel newGroupModel) {
        }

        @Override
        public void onRename(GroupModel groupModel) {
            if (!ListenerUtil.mutListener.listen(24466)) {
                updateToolBarTitleInUIThread();
            }
        }

        @Override
        public void onUpdatePhoto(GroupModel groupModel) {
            if (!ListenerUtil.mutListener.listen(24467)) {
                updateToolBarTitleInUIThread();
            }
        }

        @Override
        public void onRemove(GroupModel groupModel) {
            if (!ListenerUtil.mutListener.listen(24473)) {
                if ((ListenerUtil.mutListener.listen(24469) ? ((ListenerUtil.mutListener.listen(24468) ? (isGroupChat || groupId != null) : (isGroupChat && groupId != null)) || groupId == groupModel.getId()) : ((ListenerUtil.mutListener.listen(24468) ? (isGroupChat || groupId != null) : (isGroupChat && groupId != null)) && groupId == groupModel.getId()))) {
                    if (!ListenerUtil.mutListener.listen(24472)) {
                        RuntimeUtil.runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                if (!ListenerUtil.mutListener.listen(24471)) {
                                    if (activity != null) {
                                        if (!ListenerUtil.mutListener.listen(24470)) {
                                            activity.finish();
                                        }
                                    }
                                }
                            }
                        });
                    }
                }
            }
        }

        @Override
        public void onNewMember(GroupModel group, String newIdentity, int previousMemberCount) {
            if (!ListenerUtil.mutListener.listen(24474)) {
                updateToolBarTitleInUIThread();
            }
        }

        @Override
        public void onMemberLeave(GroupModel group, String identity, int previousMemberCount) {
            if (!ListenerUtil.mutListener.listen(24475)) {
                updateToolBarTitleInUIThread();
            }
        }

        @Override
        public void onMemberKicked(GroupModel group, String identity, int previousMemberCount) {
            if (!ListenerUtil.mutListener.listen(24476)) {
                updateToolBarTitleInUIThread();
            }
        }

        @Override
        public void onUpdate(GroupModel groupModel) {
            if (!ListenerUtil.mutListener.listen(24477)) {
                updateToolBarTitleInUIThread();
            }
        }

        @Override
        public void onLeave(GroupModel groupModel) {
            if (!ListenerUtil.mutListener.listen(24483)) {
                if ((ListenerUtil.mutListener.listen(24479) ? ((ListenerUtil.mutListener.listen(24478) ? (isGroupChat || groupId != null) : (isGroupChat && groupId != null)) || groupId == groupModel.getId()) : ((ListenerUtil.mutListener.listen(24478) ? (isGroupChat || groupId != null) : (isGroupChat && groupId != null)) && groupId == groupModel.getId()))) {
                    if (!ListenerUtil.mutListener.listen(24482)) {
                        RuntimeUtil.runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                if (!ListenerUtil.mutListener.listen(24481)) {
                                    if (activity != null) {
                                        if (!ListenerUtil.mutListener.listen(24480)) {
                                            activity.finish();
                                        }
                                    }
                                }
                            }
                        });
                    }
                }
            }
        }
    };

    private final ContactListener contactListener = new ContactListener() {

        @Override
        public void onModified(final ContactModel modifiedContactModel) {
            if (!ListenerUtil.mutListener.listen(24484)) {
                RuntimeUtil.runOnUiThread(() -> updateContactModelData(modifiedContactModel));
            }
        }

        @Override
        public void onAvatarChanged(ContactModel contactModel) {
            if (!ListenerUtil.mutListener.listen(24485)) {
                updateToolBarTitleInUIThread();
            }
        }

        @Override
        public void onRemoved(ContactModel removedContactModel) {
            if (!ListenerUtil.mutListener.listen(24488)) {
                if ((ListenerUtil.mutListener.listen(24486) ? (contactModel != null || contactModel.equals(removedContactModel)) : (contactModel != null && contactModel.equals(removedContactModel)))) {
                    if (!ListenerUtil.mutListener.listen(24487)) {
                        // our contact has been removed. finish activity.
                        RuntimeUtil.runOnUiThread(() -> {
                            if (activity != null) {
                                activity.finish();
                            }
                        });
                    }
                }
            }
        }

        @Override
        public boolean handle(String handleIdentity) {
            // 2. a contact as group member
            return true;
        }
    };

    private final ContactTypingListener contactTypingListener = new ContactTypingListener() {

        @Override
        public void onContactIsTyping(final ContactModel fromContact, final boolean isTyping) {
            if (!ListenerUtil.mutListener.listen(24489)) {
                RuntimeUtil.runOnUiThread(() -> {
                    if (contactModel != null && fromContact.getIdentity().equals(contactModel.getIdentity())) {
                        contactTypingStateChanged(isTyping);
                    }
                });
            }
        }
    };

    private final ConversationListener conversationListener = new ConversationListener() {

        @Override
        public void onNew(ConversationModel conversationModel) {
        }

        @Override
        public void onModified(ConversationModel modifiedConversationModel, Integer oldPosition) {
        }

        @Override
        public void onRemoved(ConversationModel conversationModel) {
            if (!ListenerUtil.mutListener.listen(24499)) {
                if (conversationModel != null) {
                    boolean itsMyConversation = false;
                    if (!ListenerUtil.mutListener.listen(24496)) {
                        if (contactModel != null) {
                            if (!ListenerUtil.mutListener.listen(24495)) {
                                itsMyConversation = ((ListenerUtil.mutListener.listen(24494) ? (conversationModel.getContact() != null || TestUtil.compare(conversationModel.getContact().getIdentity(), contactModel.getIdentity())) : (conversationModel.getContact() != null && TestUtil.compare(conversationModel.getContact().getIdentity(), contactModel.getIdentity()))));
                            }
                        } else if (distributionListModel != null) {
                            if (!ListenerUtil.mutListener.listen(24493)) {
                                itsMyConversation = (ListenerUtil.mutListener.listen(24492) ? (conversationModel.getDistributionList() != null || TestUtil.compare(conversationModel.getDistributionList().getId(), distributionListModel.getId())) : (conversationModel.getDistributionList() != null && TestUtil.compare(conversationModel.getDistributionList().getId(), distributionListModel.getId())));
                            }
                        } else if (groupModel != null) {
                            if (!ListenerUtil.mutListener.listen(24491)) {
                                itsMyConversation = (ListenerUtil.mutListener.listen(24490) ? (conversationModel.getGroup() != null || TestUtil.compare(conversationModel.getGroup().getId(), groupModel.getId())) : (conversationModel.getGroup() != null && TestUtil.compare(conversationModel.getGroup().getId(), groupModel.getId())));
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(24498)) {
                        if (itsMyConversation) {
                            if (!ListenerUtil.mutListener.listen(24497)) {
                                RuntimeUtil.runOnUiThread(() -> {
                                    if (getActivity() != null) {
                                        getActivity().finish();
                                    }
                                });
                            }
                        }
                    }
                }
            }
        }

        @Override
        public void onModifiedAll() {
        }
    };

    private final MessagePlayerListener messagePlayerListener = new MessagePlayerListener() {

        @Override
        public void onAudioStreamChanged(int newStreamType) {
        }

        @Override
        public void onAudioPlayEnded(AbstractMessageModel messageModel) {
            if (!ListenerUtil.mutListener.listen(24500)) {
                // Play next audio message, if any
                RuntimeUtil.runOnUiThread(() -> {
                    if (composeMessageAdapter != null) {
                        int index = composeMessageAdapter.getNextVoiceMessage(messageModel);
                        if (index != AbsListView.INVALID_POSITION) {
                            View view = composeMessageAdapter.getView(index, null, null);
                            ComposeMessageHolder holder = (ComposeMessageHolder) view.getTag();
                            if (holder.messagePlayer != null) {
                                holder.messagePlayer.open();
                                composeMessageAdapter.notifyDataSetChanged();
                            }
                        }
                    }
                });
            }
        }
    };

    private final QRCodeScanListener qrCodeScanListener = new QRCodeScanListener() {

        @Override
        public void onScanCompleted(String scanResult) {
            if (!ListenerUtil.mutListener.listen(24511)) {
                if ((ListenerUtil.mutListener.listen(24506) ? (scanResult != null || (ListenerUtil.mutListener.listen(24505) ? (scanResult.length() >= 0) : (ListenerUtil.mutListener.listen(24504) ? (scanResult.length() <= 0) : (ListenerUtil.mutListener.listen(24503) ? (scanResult.length() < 0) : (ListenerUtil.mutListener.listen(24502) ? (scanResult.length() != 0) : (ListenerUtil.mutListener.listen(24501) ? (scanResult.length() == 0) : (scanResult.length() > 0))))))) : (scanResult != null && (ListenerUtil.mutListener.listen(24505) ? (scanResult.length() >= 0) : (ListenerUtil.mutListener.listen(24504) ? (scanResult.length() <= 0) : (ListenerUtil.mutListener.listen(24503) ? (scanResult.length() < 0) : (ListenerUtil.mutListener.listen(24502) ? (scanResult.length() != 0) : (ListenerUtil.mutListener.listen(24501) ? (scanResult.length() == 0) : (scanResult.length() > 0))))))))) {
                    if (!ListenerUtil.mutListener.listen(24510)) {
                        RuntimeUtil.runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                if (!ListenerUtil.mutListener.listen(24509)) {
                                    if (messageText != null) {
                                        if (!ListenerUtil.mutListener.listen(24507)) {
                                            messageText.setText(scanResult);
                                        }
                                        if (!ListenerUtil.mutListener.listen(24508)) {
                                            messageText.setSelection(messageText.length());
                                        }
                                    }
                                }
                            }
                        });
                    }
                }
            }
        }
    };

    private final BallotListener ballotListener = new BallotListener() {

        @Override
        public void onClosed(BallotModel ballotModel) {
        }

        @Override
        public void onModified(BallotModel ballotModel) {
        }

        @Override
        public void onCreated(BallotModel ballotModel) {
            try {
                if (!ListenerUtil.mutListener.listen(24513)) {
                    BallotUtil.openDefaultActivity(getContext(), getFragmentManager(), ballotService.get(ballotModel.getId()), userService.getIdentity());
                }
            } catch (Exception e) {
                if (!ListenerUtil.mutListener.listen(24512)) {
                    logger.error("Exception", e);
                }
            }
        }

        @Override
        public void onRemoved(BallotModel ballotModel) {
        }

        @Override
        public boolean handle(BallotModel ballotModel) {
            return (ListenerUtil.mutListener.listen(24514) ? (ballotModel != null || userService.getIdentity().equals(ballotModel.getCreatorIdentity())) : (ballotModel != null && userService.getIdentity().equals(ballotModel.getCreatorIdentity())));
        }
    };

    @SuppressLint("StaticFieldLeak")
    @Override
    public void onRefresh() {
        if (!ListenerUtil.mutListener.listen(24515)) {
            logger.debug("onRefresh");
        }
        if (!ListenerUtil.mutListener.listen(24518)) {
            if ((ListenerUtil.mutListener.listen(24516) ? (actionMode != null && searchActionMode != null) : (actionMode != null || searchActionMode != null))) {
                if (!ListenerUtil.mutListener.listen(24517)) {
                    swipeRefreshLayout.setRefreshing(false);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(24537)) {
            new AsyncTask<Void, Void, Boolean>() {

                private List<AbstractMessageModel> messageModels;

                @Override
                protected Boolean doInBackground(Void... params) {
                    if (!ListenerUtil.mutListener.listen(24519)) {
                        messageModels = getNextRecords();
                    }
                    if (!ListenerUtil.mutListener.listen(24525)) {
                        if (messageModels != null) {
                            return (ListenerUtil.mutListener.listen(24524) ? (messageModels.size() <= nextMessageFilter.getPageSize()) : (ListenerUtil.mutListener.listen(24523) ? (messageModels.size() > nextMessageFilter.getPageSize()) : (ListenerUtil.mutListener.listen(24522) ? (messageModels.size() < nextMessageFilter.getPageSize()) : (ListenerUtil.mutListener.listen(24521) ? (messageModels.size() != nextMessageFilter.getPageSize()) : (ListenerUtil.mutListener.listen(24520) ? (messageModels.size() == nextMessageFilter.getPageSize()) : (messageModels.size() >= nextMessageFilter.getPageSize()))))));
                        }
                    }
                    return false;
                }

                @Override
                protected void onPostExecute(Boolean hasMoreRecords) {
                    if (!ListenerUtil.mutListener.listen(24534)) {
                        if (messageModels != null) {
                            int numberOfInsertedRecords = insertToList(messageModels, false, true, true);
                            if (!ListenerUtil.mutListener.listen(24533)) {
                                if ((ListenerUtil.mutListener.listen(24531) ? (numberOfInsertedRecords >= 0) : (ListenerUtil.mutListener.listen(24530) ? (numberOfInsertedRecords <= 0) : (ListenerUtil.mutListener.listen(24529) ? (numberOfInsertedRecords < 0) : (ListenerUtil.mutListener.listen(24528) ? (numberOfInsertedRecords != 0) : (ListenerUtil.mutListener.listen(24527) ? (numberOfInsertedRecords == 0) : (numberOfInsertedRecords > 0))))))) {
                                    if (!ListenerUtil.mutListener.listen(24532)) {
                                        convListView.setSelection(convListView.getSelectedItemPosition() + numberOfInsertedRecords + 1);
                                    }
                                }
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(24526)) {
                                composeMessageAdapter.notifyDataSetChanged();
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(24535)) {
                        // Notify PullToRefreshAttacher that the refresh has activity.finished
                        swipeRefreshLayout.setRefreshing(false);
                    }
                    if (!ListenerUtil.mutListener.listen(24536)) {
                        swipeRefreshLayout.setEnabled(hasMoreRecords);
                    }
                }
            }.execute();
        }
    }

    @Override
    public void onAttach(@NonNull Activity activity) {
        if (!ListenerUtil.mutListener.listen(24538)) {
            logger.debug("onAttach");
        }
        if (!ListenerUtil.mutListener.listen(24539)) {
            super.onAttach(activity);
        }
        if (!ListenerUtil.mutListener.listen(24540)) {
            setHasOptionsMenu(true);
        }
        if (!ListenerUtil.mutListener.listen(24541)) {
            this.activity = (ComposeMessageActivity) activity;
        }
        if (!ListenerUtil.mutListener.listen(24542)) {
            this.audioManager = (AudioManager) activity.getSystemService(Context.AUDIO_SERVICE);
        }
        if (!ListenerUtil.mutListener.listen(24544)) {
            if (bottomPanel != null) {
                if (!ListenerUtil.mutListener.listen(24543)) {
                    bottomPanel.setVisibility(View.VISIBLE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(24546)) {
            if (this.emojiPicker != null) {
                if (!ListenerUtil.mutListener.listen(24545)) {
                    this.emojiPicker.init(activity);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(24547)) {
            // resolution and layout may have changed after being attached to a new activity
            ConfigUtils.getPreferredThumbnailWidth(activity, true);
        }
        if (!ListenerUtil.mutListener.listen(24548)) {
            ConfigUtils.getPreferredAudioMessageWidth(activity, true);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(24549)) {
            logger.debug("onCreate");
        }
        if (!ListenerUtil.mutListener.listen(24551)) {
            if (getActivity() != null) {
                if (!ListenerUtil.mutListener.listen(24550)) {
                    getActivity().supportPostponeEnterTransition();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(24552)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(24553)) {
            setRetainInstance(true);
        }
        if (!ListenerUtil.mutListener.listen(24554)) {
            ListenerManager.contactTypingListeners.add(this.contactTypingListener);
        }
        if (!ListenerUtil.mutListener.listen(24555)) {
            ListenerManager.messageListeners.add(this.messageListener, true);
        }
        if (!ListenerUtil.mutListener.listen(24556)) {
            ListenerManager.groupListeners.add(this.groupListener);
        }
        if (!ListenerUtil.mutListener.listen(24557)) {
            ListenerManager.contactListeners.add(this.contactListener);
        }
        if (!ListenerUtil.mutListener.listen(24558)) {
            ListenerManager.conversationListeners.add(this.conversationListener);
        }
        if (!ListenerUtil.mutListener.listen(24559)) {
            ListenerManager.messagePlayerListener.add(this.messagePlayerListener);
        }
        if (!ListenerUtil.mutListener.listen(24560)) {
            ListenerManager.qrCodeScanListener.add(this.qrCodeScanListener);
        }
        if (!ListenerUtil.mutListener.listen(24561)) {
            ListenerManager.ballotListeners.add(this.ballotListener);
        }
        if (!ListenerUtil.mutListener.listen(24562)) {
            VoipListenerManager.callEventListener.add(this.voipCallEventListener);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(24563)) {
            logger.debug("onCreateView");
        }
        if (!ListenerUtil.mutListener.listen(24565)) {
            if (!requiredInstances()) {
                if (!ListenerUtil.mutListener.listen(24564)) {
                    activity.finish();
                }
                return this.fragmentView;
            }
        }
        if (!ListenerUtil.mutListener.listen(24566)) {
            this.layoutInflater = inflater;
        }
        if (!ListenerUtil.mutListener.listen(24637)) {
            if (this.fragmentView == null) {
                if (!ListenerUtil.mutListener.listen(24567)) {
                    // set font size
                    activity.getTheme().applyStyle(preferenceService.getFontStyle(), true);
                }
                if (!ListenerUtil.mutListener.listen(24568)) {
                    this.fragmentView = inflater.inflate(R.layout.fragment_compose_message, container, false);
                }
                ScrollView sv = fragmentView.findViewById(R.id.wallpaper_scroll);
                if (!ListenerUtil.mutListener.listen(24569)) {
                    sv.setEnabled(false);
                }
                if (!ListenerUtil.mutListener.listen(24570)) {
                    sv.setOnTouchListener(null);
                }
                if (!ListenerUtil.mutListener.listen(24571)) {
                    sv.setOnClickListener(null);
                }
                if (!ListenerUtil.mutListener.listen(24572)) {
                    this.convListView = fragmentView.findViewById(R.id.history);
                }
                if (!ListenerUtil.mutListener.listen(24573)) {
                    ViewCompat.setNestedScrollingEnabled(this.convListView, true);
                }
                if (!ListenerUtil.mutListener.listen(24574)) {
                    this.convListView.setDivider(null);
                }
                if (!ListenerUtil.mutListener.listen(24575)) {
                    this.convListView.setClipToPadding(false);
                }
                if (!ListenerUtil.mutListener.listen(24577)) {
                    if (ConfigUtils.isTabletLayout()) {
                        if (!ListenerUtil.mutListener.listen(24576)) {
                            this.convListView.setPadding(0, 0, 0, 0);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(24578)) {
                    this.listViewTop = this.convListView.getPaddingTop();
                }
                if (!ListenerUtil.mutListener.listen(24579)) {
                    this.swipeRefreshLayout = fragmentView.findViewById(R.id.ptr_layout);
                }
                if (!ListenerUtil.mutListener.listen(24580)) {
                    this.swipeRefreshLayout.setOnRefreshListener(this);
                }
                if (!ListenerUtil.mutListener.listen(24581)) {
                    this.swipeRefreshLayout.setColorSchemeResources(R.color.accent_light);
                }
                if (!ListenerUtil.mutListener.listen(24582)) {
                    this.swipeRefreshLayout.setSize(SwipeRefreshLayout.LARGE);
                }
                if (!ListenerUtil.mutListener.listen(24583)) {
                    this.coordinatorLayout = fragmentView.findViewById(R.id.coordinator);
                }
                if (!ListenerUtil.mutListener.listen(24584)) {
                    this.messageText = fragmentView.findViewById(R.id.embedded_text_editor);
                }
                if (!ListenerUtil.mutListener.listen(24591)) {
                    if ((ListenerUtil.mutListener.listen(24589) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(24588) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(24587) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(24586) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(24585) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M))))))) {
                        if (!ListenerUtil.mutListener.listen(24590)) {
                            // do not add on lollipop or lower due to this bug: https://issuetracker.google.com/issues/36937508
                            this.messageText.setCustomSelectionActionModeCallback(textSelectionCallback);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(24592)) {
                    this.sendButton = this.fragmentView.findViewById(R.id.send_button);
                }
                if (!ListenerUtil.mutListener.listen(24593)) {
                    this.attachButton = this.fragmentView.findViewById(R.id.attach_button);
                }
                if (!ListenerUtil.mutListener.listen(24594)) {
                    this.cameraButton = this.fragmentView.findViewById(R.id.camera_button);
                }
                if (!ListenerUtil.mutListener.listen(24595)) {
                    this.cameraButton.setOnClickListener(v -> {
                        if (actionMode != null) {
                            actionMode.finish();
                        }
                        closeQuoteMode();
                        if (!validateSendingPermission()) {
                            return;
                        }
                        if (ConfigUtils.requestCameraPermissions(activity, this, PERMISSION_REQUEST_ATTACH_CAMERA)) {
                            attachCamera();
                        }
                    });
                }
                if (!ListenerUtil.mutListener.listen(24596)) {
                    updateCameraButton();
                }
                if (!ListenerUtil.mutListener.listen(24597)) {
                    this.emojiButton = this.fragmentView.findViewById(R.id.emoji_button);
                }
                if (!ListenerUtil.mutListener.listen(24618)) {
                    this.emojiButton.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            if (!ListenerUtil.mutListener.listen(24598)) {
                                logger.info("Emoji button clicked");
                            }
                            if (!ListenerUtil.mutListener.listen(24617)) {
                                if (activity.isSoftKeyboardOpen()) {
                                    if (!ListenerUtil.mutListener.listen(24611)) {
                                        logger.info("Show emoji picker after keyboard close");
                                    }
                                    if (!ListenerUtil.mutListener.listen(24614)) {
                                        activity.runOnSoftKeyboardClose(new Runnable() {

                                            @Override
                                            public void run() {
                                                if (!ListenerUtil.mutListener.listen(24613)) {
                                                    if (emojiPicker != null) {
                                                        if (!ListenerUtil.mutListener.listen(24612)) {
                                                            emojiPicker.show(activity.loadStoredSoftKeyboardHeight());
                                                        }
                                                    }
                                                }
                                            }
                                        });
                                    }
                                    if (!ListenerUtil.mutListener.listen(24616)) {
                                        messageText.post(new Runnable() {

                                            @Override
                                            public void run() {
                                                if (!ListenerUtil.mutListener.listen(24615)) {
                                                    EditTextUtil.hideSoftKeyboard(messageText);
                                                }
                                            }
                                        });
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(24610)) {
                                        if (emojiPicker != null) {
                                            if (!ListenerUtil.mutListener.listen(24609)) {
                                                if (emojiPicker.isShown()) {
                                                    if (!ListenerUtil.mutListener.listen(24601)) {
                                                        logger.info("EmojPicker currently shown. Closing.");
                                                    }
                                                    if (!ListenerUtil.mutListener.listen(24608)) {
                                                        if ((ListenerUtil.mutListener.listen(24603) ? ((ListenerUtil.mutListener.listen(24602) ? (ConfigUtils.isLandscape(activity) || !ConfigUtils.isTabletLayout()) : (ConfigUtils.isLandscape(activity) && !ConfigUtils.isTabletLayout())) || preferenceService.isFullscreenIme()) : ((ListenerUtil.mutListener.listen(24602) ? (ConfigUtils.isLandscape(activity) || !ConfigUtils.isTabletLayout()) : (ConfigUtils.isLandscape(activity) && !ConfigUtils.isTabletLayout())) && preferenceService.isFullscreenIme()))) {
                                                            if (!ListenerUtil.mutListener.listen(24607)) {
                                                                emojiPicker.hide();
                                                            }
                                                        } else {
                                                            if (!ListenerUtil.mutListener.listen(24604)) {
                                                                activity.openSoftKeyboard(emojiPicker, messageText);
                                                            }
                                                            if (!ListenerUtil.mutListener.listen(24606)) {
                                                                if (activity.getResources().getConfiguration().keyboard == Configuration.KEYBOARD_QWERTY) {
                                                                    if (!ListenerUtil.mutListener.listen(24605)) {
                                                                        emojiPicker.hide();
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    if (!ListenerUtil.mutListener.listen(24599)) {
                                                        logger.info("Show emoji picker immediately");
                                                    }
                                                    if (!ListenerUtil.mutListener.listen(24600)) {
                                                        emojiPicker.show(activity.loadStoredSoftKeyboardHeight());
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    });
                }
                if (!ListenerUtil.mutListener.listen(24619)) {
                    this.emojiMarkupUtil = EmojiMarkupUtil.getInstance();
                }
                if (!ListenerUtil.mutListener.listen(24620)) {
                    this.wallpaperView = this.fragmentView.findViewById(R.id.wallpaper_view);
                }
                if (!ListenerUtil.mutListener.listen(24621)) {
                    this.quickscrollUpView = this.fragmentView.findViewById(R.id.quickscroll_top);
                }
                if (!ListenerUtil.mutListener.listen(24622)) {
                    this.quickscrollDownView = this.fragmentView.findViewById(R.id.quickscroll_bottom);
                }
                if (!ListenerUtil.mutListener.listen(24623)) {
                    this.dateView = this.fragmentView.findViewById(R.id.date_separator_container);
                }
                if (!ListenerUtil.mutListener.listen(24624)) {
                    this.dateTextView = this.fragmentView.findViewById(R.id.text_view);
                }
                if (!ListenerUtil.mutListener.listen(24625)) {
                    quoteInfo.quotePanel = this.fragmentView.findViewById(R.id.quote_panel);
                }
                if (!ListenerUtil.mutListener.listen(24626)) {
                    quoteInfo.quoteTextView = this.fragmentView.findViewById(R.id.quote_text_view);
                }
                if (!ListenerUtil.mutListener.listen(24627)) {
                    quoteInfo.quoteIdentityView = this.fragmentView.findViewById(R.id.quote_id_view);
                }
                if (!ListenerUtil.mutListener.listen(24628)) {
                    quoteInfo.quoteBar = this.fragmentView.findViewById(R.id.quote_bar);
                }
                if (!ListenerUtil.mutListener.listen(24629)) {
                    quoteInfo.quoteThumbnail = this.fragmentView.findViewById(R.id.quote_thumbnail);
                }
                if (!ListenerUtil.mutListener.listen(24630)) {
                    quoteInfo.quoteTypeImage = this.fragmentView.findViewById(R.id.quote_type_image);
                }
                ImageView quoteCloseButton = this.fragmentView.findViewById(R.id.quote_panel_close_button);
                if (!ListenerUtil.mutListener.listen(24631)) {
                    quoteCloseButton.setOnClickListener(v -> closeQuoteMode());
                }
                if (!ListenerUtil.mutListener.listen(24632)) {
                    this.bottomPanel = this.fragmentView.findViewById(R.id.bottom_panel);
                }
                if (!ListenerUtil.mutListener.listen(24633)) {
                    this.openBallotNoticeView = this.fragmentView.findViewById(R.id.open_ballots_layout);
                }
                if (!ListenerUtil.mutListener.listen(24634)) {
                    this.getValuesFromBundle(savedInstanceState);
                }
                if (!ListenerUtil.mutListener.listen(24635)) {
                    this.handleIntent(activity.getIntent());
                }
                if (!ListenerUtil.mutListener.listen(24636)) {
                    this.setupListeners();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(24649)) {
            if (preferenceService.getEmojiStyle() == PreferenceService.EmojiStyle_ANDROID) {
                if (!ListenerUtil.mutListener.listen(24647)) {
                    // remove emoji button
                    this.emojiButton.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(24648)) {
                    this.messageText.setPadding(getResources().getDimensionPixelSize(R.dimen.no_emoji_button_padding_left), this.messageText.getPaddingTop(), this.messageText.getPaddingRight(), this.messageText.getPaddingBottom());
                }
            } else {
                try {
                    if (!ListenerUtil.mutListener.listen(24640)) {
                        this.emojiPicker = (EmojiPicker) ((ViewStub) this.activity.findViewById(R.id.emoji_stub)).inflate();
                    }
                    if (!ListenerUtil.mutListener.listen(24641)) {
                        this.emojiPicker.init(activity);
                    }
                    if (!ListenerUtil.mutListener.listen(24642)) {
                        this.emojiButton.attach(this.emojiPicker, preferenceService.isFullscreenIme());
                    }
                    if (!ListenerUtil.mutListener.listen(24645)) {
                        this.emojiPicker.setEmojiKeyListener(new EmojiPicker.EmojiKeyListener() {

                            @Override
                            public void onBackspaceClick() {
                                if (!ListenerUtil.mutListener.listen(24643)) {
                                    messageText.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL));
                                }
                            }

                            @Override
                            public void onEmojiClick(String emojiCodeString) {
                                if (!ListenerUtil.mutListener.listen(24644)) {
                                    RuntimeUtil.runOnUiThread(() -> messageText.addEmoji(emojiCodeString));
                                }
                            }
                        });
                    }
                    if (!ListenerUtil.mutListener.listen(24646)) {
                        this.emojiPicker.addEmojiPickerListener(this);
                    }
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(24638)) {
                        logger.error("Exception", e);
                    }
                    if (!ListenerUtil.mutListener.listen(24639)) {
                        activity.finish();
                    }
                }
            }
        }
        return this.fragmentView;
    }

    private final android.view.ActionMode.Callback textSelectionCallback = new android.view.ActionMode.Callback() {

        private final Pattern pattern = Pattern.compile("\\B");

        @Override
        public boolean onCreateActionMode(android.view.ActionMode mode, Menu menu) {
            return true;
        }

        @Override
        public boolean onPrepareActionMode(android.view.ActionMode mode, Menu menu) {
            if (!ListenerUtil.mutListener.listen(24650)) {
                menu.removeGroup(CONTEXT_MENU_GROUP);
            }
            if (!ListenerUtil.mutListener.listen(24684)) {
                if ((ListenerUtil.mutListener.listen(24651) ? (messageText != null || messageText.getText() != null) : (messageText != null && messageText.getText() != null))) {
                    String text = messageText.getText().toString();
                    if (!ListenerUtil.mutListener.listen(24683)) {
                        if ((ListenerUtil.mutListener.listen(24656) ? (text.length() >= 1) : (ListenerUtil.mutListener.listen(24655) ? (text.length() <= 1) : (ListenerUtil.mutListener.listen(24654) ? (text.length() < 1) : (ListenerUtil.mutListener.listen(24653) ? (text.length() != 1) : (ListenerUtil.mutListener.listen(24652) ? (text.length() == 1) : (text.length() > 1))))))) {
                            int start = messageText.getSelectionStart();
                            int end = messageText.getSelectionEnd();
                            try {
                                if (!ListenerUtil.mutListener.listen(24682)) {
                                    if ((ListenerUtil.mutListener.listen(24678) ? ((ListenerUtil.mutListener.listen(24677) ? (((ListenerUtil.mutListener.listen(24666) ? ((ListenerUtil.mutListener.listen(24661) ? (start >= 0) : (ListenerUtil.mutListener.listen(24660) ? (start > 0) : (ListenerUtil.mutListener.listen(24659) ? (start < 0) : (ListenerUtil.mutListener.listen(24658) ? (start != 0) : (ListenerUtil.mutListener.listen(24657) ? (start == 0) : (start <= 0)))))) && pattern.matcher(text.substring((ListenerUtil.mutListener.listen(24665) ? (start % 1) : (ListenerUtil.mutListener.listen(24664) ? (start / 1) : (ListenerUtil.mutListener.listen(24663) ? (start * 1) : (ListenerUtil.mutListener.listen(24662) ? (start + 1) : (start - 1))))), start)).find()) : ((ListenerUtil.mutListener.listen(24661) ? (start >= 0) : (ListenerUtil.mutListener.listen(24660) ? (start > 0) : (ListenerUtil.mutListener.listen(24659) ? (start < 0) : (ListenerUtil.mutListener.listen(24658) ? (start != 0) : (ListenerUtil.mutListener.listen(24657) ? (start == 0) : (start <= 0)))))) || pattern.matcher(text.substring((ListenerUtil.mutListener.listen(24665) ? (start % 1) : (ListenerUtil.mutListener.listen(24664) ? (start / 1) : (ListenerUtil.mutListener.listen(24663) ? (start * 1) : (ListenerUtil.mutListener.listen(24662) ? (start + 1) : (start - 1))))), start)).find()))) || ((ListenerUtil.mutListener.listen(24676) ? ((ListenerUtil.mutListener.listen(24671) ? (end <= text.length()) : (ListenerUtil.mutListener.listen(24670) ? (end > text.length()) : (ListenerUtil.mutListener.listen(24669) ? (end < text.length()) : (ListenerUtil.mutListener.listen(24668) ? (end != text.length()) : (ListenerUtil.mutListener.listen(24667) ? (end == text.length()) : (end >= text.length())))))) && pattern.matcher(text.substring(end, (ListenerUtil.mutListener.listen(24675) ? (end % 1) : (ListenerUtil.mutListener.listen(24674) ? (end / 1) : (ListenerUtil.mutListener.listen(24673) ? (end * 1) : (ListenerUtil.mutListener.listen(24672) ? (end - 1) : (end + 1))))))).find()) : ((ListenerUtil.mutListener.listen(24671) ? (end <= text.length()) : (ListenerUtil.mutListener.listen(24670) ? (end > text.length()) : (ListenerUtil.mutListener.listen(24669) ? (end < text.length()) : (ListenerUtil.mutListener.listen(24668) ? (end != text.length()) : (ListenerUtil.mutListener.listen(24667) ? (end == text.length()) : (end >= text.length())))))) || pattern.matcher(text.substring(end, (ListenerUtil.mutListener.listen(24675) ? (end % 1) : (ListenerUtil.mutListener.listen(24674) ? (end / 1) : (ListenerUtil.mutListener.listen(24673) ? (end * 1) : (ListenerUtil.mutListener.listen(24672) ? (end - 1) : (end + 1))))))).find())))) : (((ListenerUtil.mutListener.listen(24666) ? ((ListenerUtil.mutListener.listen(24661) ? (start >= 0) : (ListenerUtil.mutListener.listen(24660) ? (start > 0) : (ListenerUtil.mutListener.listen(24659) ? (start < 0) : (ListenerUtil.mutListener.listen(24658) ? (start != 0) : (ListenerUtil.mutListener.listen(24657) ? (start == 0) : (start <= 0)))))) && pattern.matcher(text.substring((ListenerUtil.mutListener.listen(24665) ? (start % 1) : (ListenerUtil.mutListener.listen(24664) ? (start / 1) : (ListenerUtil.mutListener.listen(24663) ? (start * 1) : (ListenerUtil.mutListener.listen(24662) ? (start + 1) : (start - 1))))), start)).find()) : ((ListenerUtil.mutListener.listen(24661) ? (start >= 0) : (ListenerUtil.mutListener.listen(24660) ? (start > 0) : (ListenerUtil.mutListener.listen(24659) ? (start < 0) : (ListenerUtil.mutListener.listen(24658) ? (start != 0) : (ListenerUtil.mutListener.listen(24657) ? (start == 0) : (start <= 0)))))) || pattern.matcher(text.substring((ListenerUtil.mutListener.listen(24665) ? (start % 1) : (ListenerUtil.mutListener.listen(24664) ? (start / 1) : (ListenerUtil.mutListener.listen(24663) ? (start * 1) : (ListenerUtil.mutListener.listen(24662) ? (start + 1) : (start - 1))))), start)).find()))) && ((ListenerUtil.mutListener.listen(24676) ? ((ListenerUtil.mutListener.listen(24671) ? (end <= text.length()) : (ListenerUtil.mutListener.listen(24670) ? (end > text.length()) : (ListenerUtil.mutListener.listen(24669) ? (end < text.length()) : (ListenerUtil.mutListener.listen(24668) ? (end != text.length()) : (ListenerUtil.mutListener.listen(24667) ? (end == text.length()) : (end >= text.length())))))) && pattern.matcher(text.substring(end, (ListenerUtil.mutListener.listen(24675) ? (end % 1) : (ListenerUtil.mutListener.listen(24674) ? (end / 1) : (ListenerUtil.mutListener.listen(24673) ? (end * 1) : (ListenerUtil.mutListener.listen(24672) ? (end - 1) : (end + 1))))))).find()) : ((ListenerUtil.mutListener.listen(24671) ? (end <= text.length()) : (ListenerUtil.mutListener.listen(24670) ? (end > text.length()) : (ListenerUtil.mutListener.listen(24669) ? (end < text.length()) : (ListenerUtil.mutListener.listen(24668) ? (end != text.length()) : (ListenerUtil.mutListener.listen(24667) ? (end == text.length()) : (end >= text.length())))))) || pattern.matcher(text.substring(end, (ListenerUtil.mutListener.listen(24675) ? (end % 1) : (ListenerUtil.mutListener.listen(24674) ? (end / 1) : (ListenerUtil.mutListener.listen(24673) ? (end * 1) : (ListenerUtil.mutListener.listen(24672) ? (end - 1) : (end + 1))))))).find()))))) || !text.substring(start, end).contains("\n")) : ((ListenerUtil.mutListener.listen(24677) ? (((ListenerUtil.mutListener.listen(24666) ? ((ListenerUtil.mutListener.listen(24661) ? (start >= 0) : (ListenerUtil.mutListener.listen(24660) ? (start > 0) : (ListenerUtil.mutListener.listen(24659) ? (start < 0) : (ListenerUtil.mutListener.listen(24658) ? (start != 0) : (ListenerUtil.mutListener.listen(24657) ? (start == 0) : (start <= 0)))))) && pattern.matcher(text.substring((ListenerUtil.mutListener.listen(24665) ? (start % 1) : (ListenerUtil.mutListener.listen(24664) ? (start / 1) : (ListenerUtil.mutListener.listen(24663) ? (start * 1) : (ListenerUtil.mutListener.listen(24662) ? (start + 1) : (start - 1))))), start)).find()) : ((ListenerUtil.mutListener.listen(24661) ? (start >= 0) : (ListenerUtil.mutListener.listen(24660) ? (start > 0) : (ListenerUtil.mutListener.listen(24659) ? (start < 0) : (ListenerUtil.mutListener.listen(24658) ? (start != 0) : (ListenerUtil.mutListener.listen(24657) ? (start == 0) : (start <= 0)))))) || pattern.matcher(text.substring((ListenerUtil.mutListener.listen(24665) ? (start % 1) : (ListenerUtil.mutListener.listen(24664) ? (start / 1) : (ListenerUtil.mutListener.listen(24663) ? (start * 1) : (ListenerUtil.mutListener.listen(24662) ? (start + 1) : (start - 1))))), start)).find()))) || ((ListenerUtil.mutListener.listen(24676) ? ((ListenerUtil.mutListener.listen(24671) ? (end <= text.length()) : (ListenerUtil.mutListener.listen(24670) ? (end > text.length()) : (ListenerUtil.mutListener.listen(24669) ? (end < text.length()) : (ListenerUtil.mutListener.listen(24668) ? (end != text.length()) : (ListenerUtil.mutListener.listen(24667) ? (end == text.length()) : (end >= text.length())))))) && pattern.matcher(text.substring(end, (ListenerUtil.mutListener.listen(24675) ? (end % 1) : (ListenerUtil.mutListener.listen(24674) ? (end / 1) : (ListenerUtil.mutListener.listen(24673) ? (end * 1) : (ListenerUtil.mutListener.listen(24672) ? (end - 1) : (end + 1))))))).find()) : ((ListenerUtil.mutListener.listen(24671) ? (end <= text.length()) : (ListenerUtil.mutListener.listen(24670) ? (end > text.length()) : (ListenerUtil.mutListener.listen(24669) ? (end < text.length()) : (ListenerUtil.mutListener.listen(24668) ? (end != text.length()) : (ListenerUtil.mutListener.listen(24667) ? (end == text.length()) : (end >= text.length())))))) || pattern.matcher(text.substring(end, (ListenerUtil.mutListener.listen(24675) ? (end % 1) : (ListenerUtil.mutListener.listen(24674) ? (end / 1) : (ListenerUtil.mutListener.listen(24673) ? (end * 1) : (ListenerUtil.mutListener.listen(24672) ? (end - 1) : (end + 1))))))).find())))) : (((ListenerUtil.mutListener.listen(24666) ? ((ListenerUtil.mutListener.listen(24661) ? (start >= 0) : (ListenerUtil.mutListener.listen(24660) ? (start > 0) : (ListenerUtil.mutListener.listen(24659) ? (start < 0) : (ListenerUtil.mutListener.listen(24658) ? (start != 0) : (ListenerUtil.mutListener.listen(24657) ? (start == 0) : (start <= 0)))))) && pattern.matcher(text.substring((ListenerUtil.mutListener.listen(24665) ? (start % 1) : (ListenerUtil.mutListener.listen(24664) ? (start / 1) : (ListenerUtil.mutListener.listen(24663) ? (start * 1) : (ListenerUtil.mutListener.listen(24662) ? (start + 1) : (start - 1))))), start)).find()) : ((ListenerUtil.mutListener.listen(24661) ? (start >= 0) : (ListenerUtil.mutListener.listen(24660) ? (start > 0) : (ListenerUtil.mutListener.listen(24659) ? (start < 0) : (ListenerUtil.mutListener.listen(24658) ? (start != 0) : (ListenerUtil.mutListener.listen(24657) ? (start == 0) : (start <= 0)))))) || pattern.matcher(text.substring((ListenerUtil.mutListener.listen(24665) ? (start % 1) : (ListenerUtil.mutListener.listen(24664) ? (start / 1) : (ListenerUtil.mutListener.listen(24663) ? (start * 1) : (ListenerUtil.mutListener.listen(24662) ? (start + 1) : (start - 1))))), start)).find()))) && ((ListenerUtil.mutListener.listen(24676) ? ((ListenerUtil.mutListener.listen(24671) ? (end <= text.length()) : (ListenerUtil.mutListener.listen(24670) ? (end > text.length()) : (ListenerUtil.mutListener.listen(24669) ? (end < text.length()) : (ListenerUtil.mutListener.listen(24668) ? (end != text.length()) : (ListenerUtil.mutListener.listen(24667) ? (end == text.length()) : (end >= text.length())))))) && pattern.matcher(text.substring(end, (ListenerUtil.mutListener.listen(24675) ? (end % 1) : (ListenerUtil.mutListener.listen(24674) ? (end / 1) : (ListenerUtil.mutListener.listen(24673) ? (end * 1) : (ListenerUtil.mutListener.listen(24672) ? (end - 1) : (end + 1))))))).find()) : ((ListenerUtil.mutListener.listen(24671) ? (end <= text.length()) : (ListenerUtil.mutListener.listen(24670) ? (end > text.length()) : (ListenerUtil.mutListener.listen(24669) ? (end < text.length()) : (ListenerUtil.mutListener.listen(24668) ? (end != text.length()) : (ListenerUtil.mutListener.listen(24667) ? (end == text.length()) : (end >= text.length())))))) || pattern.matcher(text.substring(end, (ListenerUtil.mutListener.listen(24675) ? (end % 1) : (ListenerUtil.mutListener.listen(24674) ? (end / 1) : (ListenerUtil.mutListener.listen(24673) ? (end * 1) : (ListenerUtil.mutListener.listen(24672) ? (end - 1) : (end + 1))))))).find()))))) && !text.substring(start, end).contains("\n")))) {
                                        if (!ListenerUtil.mutListener.listen(24679)) {
                                            menu.add(CONTEXT_MENU_GROUP, CONTEXT_MENU_BOLD, 200, R.string.bold);
                                        }
                                        if (!ListenerUtil.mutListener.listen(24680)) {
                                            menu.add(CONTEXT_MENU_GROUP, CONTEXT_MENU_ITALIC, 201, R.string.italic);
                                        }
                                        if (!ListenerUtil.mutListener.listen(24681)) {
                                            menu.add(CONTEXT_MENU_GROUP, CONTEXT_MENU_STRIKETHRU, 203, R.string.strikethrough);
                                        }
                                    }
                                }
                            } catch (StringIndexOutOfBoundsException e) {
                            }
                        }
                    }
                }
            }
            return true;
        }

        @Override
        public boolean onActionItemClicked(android.view.ActionMode mode, MenuItem item) {
            if (!ListenerUtil.mutListener.listen(24688)) {
                switch(item.getItemId()) {
                    case CONTEXT_MENU_BOLD:
                        if (!ListenerUtil.mutListener.listen(24685)) {
                            addMarkup("*");
                        }
                        break;
                    case CONTEXT_MENU_ITALIC:
                        if (!ListenerUtil.mutListener.listen(24686)) {
                            addMarkup("_");
                        }
                        break;
                    case CONTEXT_MENU_STRIKETHRU:
                        if (!ListenerUtil.mutListener.listen(24687)) {
                            addMarkup("~");
                        }
                        break;
                    default:
                        return false;
                }
            }
            return true;
        }

        @Override
        public void onDestroyActionMode(android.view.ActionMode mode) {
        }
    };

    private void addMarkup(String string) {
        Editable editable = messageText.getText();
        if (!ListenerUtil.mutListener.listen(24696)) {
            if ((ListenerUtil.mutListener.listen(24693) ? (editable.length() >= 0) : (ListenerUtil.mutListener.listen(24692) ? (editable.length() <= 0) : (ListenerUtil.mutListener.listen(24691) ? (editable.length() < 0) : (ListenerUtil.mutListener.listen(24690) ? (editable.length() != 0) : (ListenerUtil.mutListener.listen(24689) ? (editable.length() == 0) : (editable.length() > 0))))))) {
                int start = messageText.getSelectionStart();
                int end = messageText.getSelectionEnd();
                if (!ListenerUtil.mutListener.listen(24694)) {
                    editable.insert(end, string);
                }
                if (!ListenerUtil.mutListener.listen(24695)) {
                    editable.insert(start, string);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(24697)) {
            messageText.invalidate();
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(24698)) {
            logger.debug("onActivityCreated");
        }
        if (!ListenerUtil.mutListener.listen(24699)) {
            super.onActivityCreated(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(24700)) {
            /*
		 * This callback tells the fragment when it is fully associated with the new activity instance. This is called after onCreateView(LayoutInflater, ViewGroup, Bundle) and before onViewStateRestored(Bundle).
		 */
            activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        }
        if (!ListenerUtil.mutListener.listen(24701)) {
            if (preferenceService == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(24757)) {
            if (preferenceService.getEmojiStyle() != PreferenceService.EmojiStyle_ANDROID) {
                if (!ListenerUtil.mutListener.listen(24755)) {
                    if ((ListenerUtil.mutListener.listen(24706) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(24705) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(24704) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(24703) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(24702) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP))))))) {
                        if (!ListenerUtil.mutListener.listen(24754)) {
                            activity.findViewById(R.id.compose_activity_parent).getRootView().getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

                                @Override
                                public void onGlobalLayout() {
                                    DisplayMetrics metrics = new DisplayMetrics();
                                    if (!ListenerUtil.mutListener.listen(24733)) {
                                        // get dimensions of usable display space with decorations (status bar / navigation bar) subtracted
                                        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
                                    }
                                    int usableHeight = metrics.heightPixels;
                                    int statusBarHeight = ConfigUtils.getStatusBarHeight(getContext());
                                    int rootViewHeight = activity.findViewById(R.id.compose_activity_parent).getHeight();
                                    if (!ListenerUtil.mutListener.listen(24753)) {
                                        if ((ListenerUtil.mutListener.listen(24742) ? ((ListenerUtil.mutListener.listen(24737) ? (rootViewHeight % statusBarHeight) : (ListenerUtil.mutListener.listen(24736) ? (rootViewHeight / statusBarHeight) : (ListenerUtil.mutListener.listen(24735) ? (rootViewHeight * statusBarHeight) : (ListenerUtil.mutListener.listen(24734) ? (rootViewHeight - statusBarHeight) : (rootViewHeight + statusBarHeight))))) >= usableHeight) : (ListenerUtil.mutListener.listen(24741) ? ((ListenerUtil.mutListener.listen(24737) ? (rootViewHeight % statusBarHeight) : (ListenerUtil.mutListener.listen(24736) ? (rootViewHeight / statusBarHeight) : (ListenerUtil.mutListener.listen(24735) ? (rootViewHeight * statusBarHeight) : (ListenerUtil.mutListener.listen(24734) ? (rootViewHeight - statusBarHeight) : (rootViewHeight + statusBarHeight))))) <= usableHeight) : (ListenerUtil.mutListener.listen(24740) ? ((ListenerUtil.mutListener.listen(24737) ? (rootViewHeight % statusBarHeight) : (ListenerUtil.mutListener.listen(24736) ? (rootViewHeight / statusBarHeight) : (ListenerUtil.mutListener.listen(24735) ? (rootViewHeight * statusBarHeight) : (ListenerUtil.mutListener.listen(24734) ? (rootViewHeight - statusBarHeight) : (rootViewHeight + statusBarHeight))))) > usableHeight) : (ListenerUtil.mutListener.listen(24739) ? ((ListenerUtil.mutListener.listen(24737) ? (rootViewHeight % statusBarHeight) : (ListenerUtil.mutListener.listen(24736) ? (rootViewHeight / statusBarHeight) : (ListenerUtil.mutListener.listen(24735) ? (rootViewHeight * statusBarHeight) : (ListenerUtil.mutListener.listen(24734) ? (rootViewHeight - statusBarHeight) : (rootViewHeight + statusBarHeight))))) < usableHeight) : (ListenerUtil.mutListener.listen(24738) ? ((ListenerUtil.mutListener.listen(24737) ? (rootViewHeight % statusBarHeight) : (ListenerUtil.mutListener.listen(24736) ? (rootViewHeight / statusBarHeight) : (ListenerUtil.mutListener.listen(24735) ? (rootViewHeight * statusBarHeight) : (ListenerUtil.mutListener.listen(24734) ? (rootViewHeight - statusBarHeight) : (rootViewHeight + statusBarHeight))))) != usableHeight) : ((ListenerUtil.mutListener.listen(24737) ? (rootViewHeight % statusBarHeight) : (ListenerUtil.mutListener.listen(24736) ? (rootViewHeight / statusBarHeight) : (ListenerUtil.mutListener.listen(24735) ? (rootViewHeight * statusBarHeight) : (ListenerUtil.mutListener.listen(24734) ? (rootViewHeight - statusBarHeight) : (rootViewHeight + statusBarHeight))))) == usableHeight))))))) {
                                            if (!ListenerUtil.mutListener.listen(24752)) {
                                                activity.onSoftKeyboardClosed();
                                            }
                                        } else {
                                            if (!ListenerUtil.mutListener.listen(24751)) {
                                                activity.onSoftKeyboardOpened((ListenerUtil.mutListener.listen(24750) ? ((ListenerUtil.mutListener.listen(24746) ? (usableHeight % statusBarHeight) : (ListenerUtil.mutListener.listen(24745) ? (usableHeight / statusBarHeight) : (ListenerUtil.mutListener.listen(24744) ? (usableHeight * statusBarHeight) : (ListenerUtil.mutListener.listen(24743) ? (usableHeight + statusBarHeight) : (usableHeight - statusBarHeight))))) % rootViewHeight) : (ListenerUtil.mutListener.listen(24749) ? ((ListenerUtil.mutListener.listen(24746) ? (usableHeight % statusBarHeight) : (ListenerUtil.mutListener.listen(24745) ? (usableHeight / statusBarHeight) : (ListenerUtil.mutListener.listen(24744) ? (usableHeight * statusBarHeight) : (ListenerUtil.mutListener.listen(24743) ? (usableHeight + statusBarHeight) : (usableHeight - statusBarHeight))))) / rootViewHeight) : (ListenerUtil.mutListener.listen(24748) ? ((ListenerUtil.mutListener.listen(24746) ? (usableHeight % statusBarHeight) : (ListenerUtil.mutListener.listen(24745) ? (usableHeight / statusBarHeight) : (ListenerUtil.mutListener.listen(24744) ? (usableHeight * statusBarHeight) : (ListenerUtil.mutListener.listen(24743) ? (usableHeight + statusBarHeight) : (usableHeight - statusBarHeight))))) * rootViewHeight) : (ListenerUtil.mutListener.listen(24747) ? ((ListenerUtil.mutListener.listen(24746) ? (usableHeight % statusBarHeight) : (ListenerUtil.mutListener.listen(24745) ? (usableHeight / statusBarHeight) : (ListenerUtil.mutListener.listen(24744) ? (usableHeight * statusBarHeight) : (ListenerUtil.mutListener.listen(24743) ? (usableHeight + statusBarHeight) : (usableHeight - statusBarHeight))))) + rootViewHeight) : ((ListenerUtil.mutListener.listen(24746) ? (usableHeight % statusBarHeight) : (ListenerUtil.mutListener.listen(24745) ? (usableHeight / statusBarHeight) : (ListenerUtil.mutListener.listen(24744) ? (usableHeight * statusBarHeight) : (ListenerUtil.mutListener.listen(24743) ? (usableHeight + statusBarHeight) : (usableHeight - statusBarHeight))))) - rootViewHeight))))));
                                            }
                                        }
                                    }
                                }
                            });
                        }
                    } else {
                        try {
                            View rootView = activity.getWindow().getDecorView().getRootView();
                            if (!ListenerUtil.mutListener.listen(24717)) {
                                if ((ListenerUtil.mutListener.listen(24712) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(24711) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(24710) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(24709) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(24708) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) : (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M))))))) {
                                    try {
                                        ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();
                                        if (!ListenerUtil.mutListener.listen(24716)) {
                                            if ((ListenerUtil.mutListener.listen(24714) ? (decorView.getChildCount() == 1 || decorView.getChildAt(0) instanceof LinearLayout) : (decorView.getChildCount() == 1 && decorView.getChildAt(0) instanceof LinearLayout))) {
                                                if (!ListenerUtil.mutListener.listen(24715)) {
                                                    rootView = decorView.getChildAt(0);
                                                }
                                            }
                                        }
                                    } catch (Exception e) {
                                        if (!ListenerUtil.mutListener.listen(24713)) {
                                            logger.error("Exception", e);
                                        }
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(24732)) {
                                ViewCompat.setOnApplyWindowInsetsListener(rootView, new OnApplyWindowInsetsListener() {

                                    @Override
                                    public WindowInsetsCompat onApplyWindowInsets(View v, WindowInsetsCompat insets) {
                                        if (!ListenerUtil.mutListener.listen(24718)) {
                                            logger.info("%%% system window top " + insets.getSystemWindowInsetTop() + " bottom " + insets.getSystemWindowInsetBottom());
                                        }
                                        if (!ListenerUtil.mutListener.listen(24719)) {
                                            logger.info("%%% stable insets top " + insets.getStableInsetTop() + " bottom " + insets.getStableInsetBottom());
                                        }
                                        if (!ListenerUtil.mutListener.listen(24731)) {
                                            if ((ListenerUtil.mutListener.listen(24724) ? (insets.getSystemWindowInsetBottom() >= insets.getStableInsetBottom()) : (ListenerUtil.mutListener.listen(24723) ? (insets.getSystemWindowInsetBottom() > insets.getStableInsetBottom()) : (ListenerUtil.mutListener.listen(24722) ? (insets.getSystemWindowInsetBottom() < insets.getStableInsetBottom()) : (ListenerUtil.mutListener.listen(24721) ? (insets.getSystemWindowInsetBottom() != insets.getStableInsetBottom()) : (ListenerUtil.mutListener.listen(24720) ? (insets.getSystemWindowInsetBottom() == insets.getStableInsetBottom()) : (insets.getSystemWindowInsetBottom() <= insets.getStableInsetBottom()))))))) {
                                                if (!ListenerUtil.mutListener.listen(24730)) {
                                                    activity.onSoftKeyboardClosed();
                                                }
                                            } else {
                                                if (!ListenerUtil.mutListener.listen(24729)) {
                                                    activity.onSoftKeyboardOpened((ListenerUtil.mutListener.listen(24728) ? (insets.getSystemWindowInsetBottom() % insets.getStableInsetBottom()) : (ListenerUtil.mutListener.listen(24727) ? (insets.getSystemWindowInsetBottom() / insets.getStableInsetBottom()) : (ListenerUtil.mutListener.listen(24726) ? (insets.getSystemWindowInsetBottom() * insets.getStableInsetBottom()) : (ListenerUtil.mutListener.listen(24725) ? (insets.getSystemWindowInsetBottom() + insets.getStableInsetBottom()) : (insets.getSystemWindowInsetBottom() - insets.getStableInsetBottom()))))));
                                                }
                                            }
                                        }
                                        return insets;
                                    }
                                });
                            }
                        } catch (NullPointerException e) {
                            if (!ListenerUtil.mutListener.listen(24707)) {
                                logger.error("Exception", e);
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(24756)) {
                    activity.addOnSoftKeyboardChangedListener(this);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(24766)) {
            // restore action mode after rotate if the activity was detached
            if ((ListenerUtil.mutListener.listen(24764) ? ((ListenerUtil.mutListener.listen(24763) ? (convListView != null || (ListenerUtil.mutListener.listen(24762) ? (convListView.getCheckedItemCount() >= 0) : (ListenerUtil.mutListener.listen(24761) ? (convListView.getCheckedItemCount() <= 0) : (ListenerUtil.mutListener.listen(24760) ? (convListView.getCheckedItemCount() < 0) : (ListenerUtil.mutListener.listen(24759) ? (convListView.getCheckedItemCount() != 0) : (ListenerUtil.mutListener.listen(24758) ? (convListView.getCheckedItemCount() == 0) : (convListView.getCheckedItemCount() > 0))))))) : (convListView != null && (ListenerUtil.mutListener.listen(24762) ? (convListView.getCheckedItemCount() >= 0) : (ListenerUtil.mutListener.listen(24761) ? (convListView.getCheckedItemCount() <= 0) : (ListenerUtil.mutListener.listen(24760) ? (convListView.getCheckedItemCount() < 0) : (ListenerUtil.mutListener.listen(24759) ? (convListView.getCheckedItemCount() != 0) : (ListenerUtil.mutListener.listen(24758) ? (convListView.getCheckedItemCount() == 0) : (convListView.getCheckedItemCount() > 0)))))))) || actionMode != null) : ((ListenerUtil.mutListener.listen(24763) ? (convListView != null || (ListenerUtil.mutListener.listen(24762) ? (convListView.getCheckedItemCount() >= 0) : (ListenerUtil.mutListener.listen(24761) ? (convListView.getCheckedItemCount() <= 0) : (ListenerUtil.mutListener.listen(24760) ? (convListView.getCheckedItemCount() < 0) : (ListenerUtil.mutListener.listen(24759) ? (convListView.getCheckedItemCount() != 0) : (ListenerUtil.mutListener.listen(24758) ? (convListView.getCheckedItemCount() == 0) : (convListView.getCheckedItemCount() > 0))))))) : (convListView != null && (ListenerUtil.mutListener.listen(24762) ? (convListView.getCheckedItemCount() >= 0) : (ListenerUtil.mutListener.listen(24761) ? (convListView.getCheckedItemCount() <= 0) : (ListenerUtil.mutListener.listen(24760) ? (convListView.getCheckedItemCount() < 0) : (ListenerUtil.mutListener.listen(24759) ? (convListView.getCheckedItemCount() != 0) : (ListenerUtil.mutListener.listen(24758) ? (convListView.getCheckedItemCount() == 0) : (convListView.getCheckedItemCount() > 0)))))))) && actionMode != null))) {
                if (!ListenerUtil.mutListener.listen(24765)) {
                    actionMode = ((AppCompatActivity) getActivity()).startSupportActionMode(new ComposeMessageAction(this.longClickItem));
                }
            }
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(24767)) {
            super.onViewCreated(view, savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(24768)) {
            activity.supportStartPostponedEnterTransition();
        }
    }

    public void onWindowFocusChanged(boolean hasFocus) {
        if (!ListenerUtil.mutListener.listen(24769)) {
            logger.debug("onWindowFocusChanged " + hasFocus);
        }
        if (!ListenerUtil.mutListener.listen(24775)) {
            // see: http://stackoverflow.com/questions/35318649/android-proximity-sensor-issue-only-in-samsung-devices
            if (hasFocus) {
                if (!ListenerUtil.mutListener.listen(24774)) {
                    if (!this.hasFocus) {
                        if (!ListenerUtil.mutListener.listen(24772)) {
                            reallyOnResume();
                        }
                        if (!ListenerUtil.mutListener.listen(24773)) {
                            this.hasFocus = true;
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(24770)) {
                    reallyOnPause();
                }
                if (!ListenerUtil.mutListener.listen(24771)) {
                    this.hasFocus = false;
                }
            }
        }
    }

    @Override
    public void onResume() {
        if (!ListenerUtil.mutListener.listen(24776)) {
            logger.debug("onResume");
        }
        if (!ListenerUtil.mutListener.listen(24777)) {
            super.onResume();
        }
        if (!ListenerUtil.mutListener.listen(24780)) {
            if ((ListenerUtil.mutListener.listen(24778) ? (!ConfigUtils.isSamsungDevice() && ConfigUtils.isTabletLayout()) : (!ConfigUtils.isSamsungDevice() || ConfigUtils.isTabletLayout()))) {
                if (!ListenerUtil.mutListener.listen(24779)) {
                    reallyOnResume();
                }
            }
        }
    }

    private void reallyOnResume() {
        if (!ListenerUtil.mutListener.listen(24781)) {
            logger.debug("reallyOnResume");
        }
        if (!ListenerUtil.mutListener.listen(24810)) {
            // set visible receiver
            if (this.messageReceiver != null) {
                if (!ListenerUtil.mutListener.listen(24782)) {
                    this.notificationService.setVisibleReceiver(this.messageReceiver);
                }
                if (!ListenerUtil.mutListener.listen(24783)) {
                    isPaused = false;
                }
                if (!ListenerUtil.mutListener.listen(24784)) {
                    logger.debug("markAllRead");
                }
                if (!ListenerUtil.mutListener.listen(24794)) {
                    // mark all unread messages
                    if ((ListenerUtil.mutListener.listen(24789) ? (this.unreadMessages.size() >= 0) : (ListenerUtil.mutListener.listen(24788) ? (this.unreadMessages.size() <= 0) : (ListenerUtil.mutListener.listen(24787) ? (this.unreadMessages.size() < 0) : (ListenerUtil.mutListener.listen(24786) ? (this.unreadMessages.size() != 0) : (ListenerUtil.mutListener.listen(24785) ? (this.unreadMessages.size() == 0) : (this.unreadMessages.size() > 0))))))) {
                        ReadMessagesRoutine r = new ReadMessagesRoutine(this.unreadMessages, this.messageService, this.notificationService);
                        if (!ListenerUtil.mutListener.listen(24792)) {
                            r.addOnFinished(new ReadMessagesRoutine.OnFinished() {

                                @Override
                                public void finished(boolean success) {
                                    if (!ListenerUtil.mutListener.listen(24791)) {
                                        if (success) {
                                            if (!ListenerUtil.mutListener.listen(24790)) {
                                                unreadMessages.clear();
                                            }
                                        }
                                    }
                                }
                            });
                        }
                        if (!ListenerUtil.mutListener.listen(24793)) {
                            new Thread(r).start();
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(24795)) {
                    // update menus
                    updateMuteMenu();
                }
                if (!ListenerUtil.mutListener.listen(24796)) {
                    // start media players again
                    this.messagePlayerService.resumeAll(getActivity(), this.messageReceiver, SOURCE_LIFECYCLE);
                }
                if (!ListenerUtil.mutListener.listen(24809)) {
                    // restore scroll position after orientation change
                    if (getActivity() != null) {
                        Intent intent = getActivity().getIntent();
                        if (!ListenerUtil.mutListener.listen(24808)) {
                            if ((ListenerUtil.mutListener.listen(24798) ? ((ListenerUtil.mutListener.listen(24797) ? (intent != null || !intent.hasExtra(EXTRA_API_MESSAGE_ID)) : (intent != null && !intent.hasExtra(EXTRA_API_MESSAGE_ID))) || !intent.hasExtra(EXTRA_SEARCH_QUERY)) : ((ListenerUtil.mutListener.listen(24797) ? (intent != null || !intent.hasExtra(EXTRA_API_MESSAGE_ID)) : (intent != null && !intent.hasExtra(EXTRA_API_MESSAGE_ID))) && !intent.hasExtra(EXTRA_SEARCH_QUERY)))) {
                                if (!ListenerUtil.mutListener.listen(24807)) {
                                    convListView.post(new Runnable() {

                                        @Override
                                        public void run() {
                                            if (!ListenerUtil.mutListener.listen(24804)) {
                                                if ((ListenerUtil.mutListener.listen(24800) ? ((ListenerUtil.mutListener.listen(24799) ? (listInstancePosition != AbsListView.INVALID_POSITION || messageReceiver != null) : (listInstancePosition != AbsListView.INVALID_POSITION && messageReceiver != null)) || messageReceiver.getUniqueIdString().equals(listInstanceReceiverId)) : ((ListenerUtil.mutListener.listen(24799) ? (listInstancePosition != AbsListView.INVALID_POSITION || messageReceiver != null) : (listInstancePosition != AbsListView.INVALID_POSITION && messageReceiver != null)) && messageReceiver.getUniqueIdString().equals(listInstanceReceiverId)))) {
                                                    if (!ListenerUtil.mutListener.listen(24802)) {
                                                        logger.debug("restoring position " + listInstancePosition);
                                                    }
                                                    if (!ListenerUtil.mutListener.listen(24803)) {
                                                        convListView.setSelectionFromTop(listInstancePosition, listInstanceTop);
                                                    }
                                                } else {
                                                    if (!ListenerUtil.mutListener.listen(24801)) {
                                                        jumpToFirstUnreadMessage();
                                                    }
                                                }
                                            }
                                            if (!ListenerUtil.mutListener.listen(24805)) {
                                                // make sure it's not restored twice
                                                listInstancePosition = AbsListView.INVALID_POSITION;
                                            }
                                            if (!ListenerUtil.mutListener.listen(24806)) {
                                                listInstanceReceiverId = null;
                                            }
                                        }
                                    });
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onStart() {
        if (!ListenerUtil.mutListener.listen(24811)) {
            logger.debug("onStart");
        }
        if (!ListenerUtil.mutListener.listen(24812)) {
            super.onStart();
        }
    }

    @Override
    public void onPause() {
        if (!ListenerUtil.mutListener.listen(24813)) {
            logger.debug("onPause");
        }
        if (!ListenerUtil.mutListener.listen(24816)) {
            if ((ListenerUtil.mutListener.listen(24814) ? (!ConfigUtils.isSamsungDevice() && ConfigUtils.isTabletLayout()) : (!ConfigUtils.isSamsungDevice() || ConfigUtils.isTabletLayout()))) {
                if (!ListenerUtil.mutListener.listen(24815)) {
                    reallyOnPause();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(24817)) {
            super.onPause();
        }
    }

    private void reallyOnPause() {
        if (!ListenerUtil.mutListener.listen(24818)) {
            logger.debug("reallyOnPause");
        }
        if (!ListenerUtil.mutListener.listen(24819)) {
            isPaused = true;
        }
        if (!ListenerUtil.mutListener.listen(24820)) {
            onEmojiPickerClose();
        }
        if (!ListenerUtil.mutListener.listen(24822)) {
            if (this.notificationService != null) {
                if (!ListenerUtil.mutListener.listen(24821)) {
                    this.notificationService.setVisibleReceiver(null);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(24824)) {
            // stop all playing audio messages (incoming call?)
            if (this.messagePlayerService != null) {
                if (!ListenerUtil.mutListener.listen(24823)) {
                    this.messagePlayerService.pauseAll(SOURCE_LIFECYCLE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(24825)) {
            // save unfinished text
            saveMessageDraft();
        }
        if (!ListenerUtil.mutListener.listen(24827)) {
            if (this.typingIndicatorTextWatcher != null) {
                if (!ListenerUtil.mutListener.listen(24826)) {
                    this.typingIndicatorTextWatcher.stopTyping();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(24828)) {
            preserveListInstanceValues();
        }
    }

    @Override
    public void onStop() {
        if (!ListenerUtil.mutListener.listen(24829)) {
            logger.debug("onStop");
        }
        if (!ListenerUtil.mutListener.listen(24831)) {
            if (this.typingIndicatorTextWatcher != null) {
                if (!ListenerUtil.mutListener.listen(24830)) {
                    this.typingIndicatorTextWatcher.stopTyping();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(24839)) {
            if ((ListenerUtil.mutListener.listen(24836) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) : (ListenerUtil.mutListener.listen(24835) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q) : (ListenerUtil.mutListener.listen(24834) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) : (ListenerUtil.mutListener.listen(24833) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.Q) : (ListenerUtil.mutListener.listen(24832) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.Q) : (Build.VERSION.SDK_INT > Build.VERSION_CODES.Q))))))) {
                if (!ListenerUtil.mutListener.listen(24838)) {
                    // close keyboard to prevent layout corruption after unlocking phone
                    if (this.messageText != null) {
                        if (!ListenerUtil.mutListener.listen(24837)) {
                            EditTextUtil.hideSoftKeyboard(this.messageText);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(24840)) {
            super.onStop();
        }
    }

    @Override
    public void onDetach() {
        if (!ListenerUtil.mutListener.listen(24841)) {
            logger.debug("onDetach");
        }
        if (!ListenerUtil.mutListener.listen(24844)) {
            if ((ListenerUtil.mutListener.listen(24842) ? (this.emojiPicker != null || this.emojiPicker.isShown()) : (this.emojiPicker != null && this.emojiPicker.isShown()))) {
                if (!ListenerUtil.mutListener.listen(24843)) {
                    this.emojiPicker.hide();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(24845)) {
            dismissMentionPopup();
        }
        if (!ListenerUtil.mutListener.listen(24846)) {
            this.activity = null;
        }
        if (!ListenerUtil.mutListener.listen(24847)) {
            super.onDetach();
        }
    }

    @Override
    public void onDestroy() {
        if (!ListenerUtil.mutListener.listen(24848)) {
            logger.debug("onDestroy");
        }
        try {
            if (!ListenerUtil.mutListener.listen(24850)) {
                ListenerManager.contactTypingListeners.remove(this.contactTypingListener);
            }
            if (!ListenerUtil.mutListener.listen(24851)) {
                ListenerManager.groupListeners.remove(this.groupListener);
            }
            if (!ListenerUtil.mutListener.listen(24852)) {
                ListenerManager.messageListeners.remove(this.messageListener);
            }
            if (!ListenerUtil.mutListener.listen(24853)) {
                ListenerManager.contactListeners.remove(this.contactListener);
            }
            if (!ListenerUtil.mutListener.listen(24854)) {
                ListenerManager.conversationListeners.remove(this.conversationListener);
            }
            if (!ListenerUtil.mutListener.listen(24855)) {
                ListenerManager.messagePlayerListener.remove(this.messagePlayerListener);
            }
            if (!ListenerUtil.mutListener.listen(24856)) {
                ListenerManager.qrCodeScanListener.remove(this.qrCodeScanListener);
            }
            if (!ListenerUtil.mutListener.listen(24857)) {
                ListenerManager.ballotListeners.remove(this.ballotListener);
            }
            if (!ListenerUtil.mutListener.listen(24858)) {
                VoipListenerManager.callEventListener.remove(this.voipCallEventListener);
            }
            if (!ListenerUtil.mutListener.listen(24859)) {
                dismissTooltipPopup(workTooltipPopup, true);
            }
            if (!ListenerUtil.mutListener.listen(24860)) {
                workTooltipPopup = null;
            }
            if (!ListenerUtil.mutListener.listen(24861)) {
                dismissMentionPopup();
            }
            if (!ListenerUtil.mutListener.listen(24863)) {
                if (this.emojiButton != null) {
                    if (!ListenerUtil.mutListener.listen(24862)) {
                        this.emojiButton.detach(this.emojiPicker);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(24865)) {
                if (this.emojiPicker != null) {
                    if (!ListenerUtil.mutListener.listen(24864)) {
                        this.emojiPicker.removeEmojiPickerListener(this);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(24867)) {
                if (!requiredInstances()) {
                    if (!ListenerUtil.mutListener.listen(24866)) {
                        super.onDestroy();
                    }
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(24869)) {
                // release all players!
                if (this.messagePlayerService != null) {
                    if (!ListenerUtil.mutListener.listen(24868)) {
                        this.messagePlayerService.release();
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(24871)) {
                if (this.messageService != null) {
                    if (!ListenerUtil.mutListener.listen(24870)) {
                        this.messageService.saveMessageQueueAsync();
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(24873)) {
                if (this.thumbnailCache != null) {
                    if (!ListenerUtil.mutListener.listen(24872)) {
                        this.thumbnailCache.flush();
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(24877)) {
                if (this.messageText != null) {
                    if (!ListenerUtil.mutListener.listen(24875)) {
                        // remove typing change listener
                        if (this.typingIndicatorTextWatcher != null) {
                            if (!ListenerUtil.mutListener.listen(24874)) {
                                this.messageText.removeTextChangedListener(this.typingIndicatorTextWatcher);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(24876)) {
                        // http://stackoverflow.com/questions/18348049/android-edittext-memory-leak
                        this.messageText.setText(null);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(24878)) {
                // remove wallpaper
                this.wallpaperView.setImageBitmap(null);
            }
            if (!ListenerUtil.mutListener.listen(24879)) {
                // delete pending deleteable messages
                deleteDeleteableMessages();
            }
            if (!ListenerUtil.mutListener.listen(24882)) {
                if ((ListenerUtil.mutListener.listen(24880) ? (this.deleteSnackbar != null || this.deleteSnackbar.isShownOrQueued()) : (this.deleteSnackbar != null && this.deleteSnackbar.isShownOrQueued()))) {
                    if (!ListenerUtil.mutListener.listen(24881)) {
                        this.deleteSnackbar.dismiss();
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(24883)) {
                removeIsTypingFooter();
            }
            if (!ListenerUtil.mutListener.listen(24884)) {
                this.isTypingView = null;
            }
            if (!ListenerUtil.mutListener.listen(24887)) {
                // clear all records to remove all references
                if (this.composeMessageAdapter != null) {
                    if (!ListenerUtil.mutListener.listen(24885)) {
                        this.composeMessageAdapter.clear();
                    }
                    if (!ListenerUtil.mutListener.listen(24886)) {
                        this.composeMessageAdapter = null;
                    }
                }
            }
        } catch (Exception x) {
            if (!ListenerUtil.mutListener.listen(24849)) {
                logger.error("Exception", x);
            }
        }
        if (!ListenerUtil.mutListener.listen(24888)) {
            super.onDestroy();
        }
    }

    private void removeScrollButtons() {
        if (!ListenerUtil.mutListener.listen(24889)) {
            logger.debug("removeScrollButtons");
        }
        if (!ListenerUtil.mutListener.listen(24892)) {
            if ((ListenerUtil.mutListener.listen(24890) ? (dateView != null || dateView.getVisibility() == View.VISIBLE) : (dateView != null && dateView.getVisibility() == View.VISIBLE))) {
                if (!ListenerUtil.mutListener.listen(24891)) {
                    AnimationUtil.slideOutAnimation(dateView, false, 1f, null);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(24894)) {
            if (actionMode != null) {
                if (!ListenerUtil.mutListener.listen(24893)) {
                    actionMode.finish();
                }
            }
        }
    }

    private void setupListeners() {
        if (!ListenerUtil.mutListener.listen(24945)) {
            // we don't look for swipes or pulldowns
            this.convListView.setOnScrollListener(new AbsListView.OnScrollListener() {

                @Override
                public void onScrollStateChanged(AbsListView absListView, int scrollState) {
                    if (!ListenerUtil.mutListener.listen(24896)) {
                        if (listViewSwipeListener != null) {
                            if (!ListenerUtil.mutListener.listen(24895)) {
                                listViewSwipeListener.setEnabled(scrollState != AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(24898)) {
                        if (!absListView.canScrollList(View.SCROLL_AXIS_VERTICAL)) {
                            if (!ListenerUtil.mutListener.listen(24897)) {
                                AnimationUtil.setFadingVisibility(quickscrollDownView, View.GONE);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(24900)) {
                        if (!absListView.canScrollList(-View.SCROLL_AXIS_VERTICAL)) {
                            if (!ListenerUtil.mutListener.listen(24899)) {
                                AnimationUtil.setFadingVisibility(quickscrollUpView, View.GONE);
                            }
                        }
                    }
                }

                @Override
                public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                    if (!ListenerUtil.mutListener.listen(24944)) {
                        if ((ListenerUtil.mutListener.listen(24906) ? (view != null || (ListenerUtil.mutListener.listen(24905) ? (view.getChildCount() >= 0) : (ListenerUtil.mutListener.listen(24904) ? (view.getChildCount() <= 0) : (ListenerUtil.mutListener.listen(24903) ? (view.getChildCount() < 0) : (ListenerUtil.mutListener.listen(24902) ? (view.getChildCount() != 0) : (ListenerUtil.mutListener.listen(24901) ? (view.getChildCount() == 0) : (view.getChildCount() > 0))))))) : (view != null && (ListenerUtil.mutListener.listen(24905) ? (view.getChildCount() >= 0) : (ListenerUtil.mutListener.listen(24904) ? (view.getChildCount() <= 0) : (ListenerUtil.mutListener.listen(24903) ? (view.getChildCount() < 0) : (ListenerUtil.mutListener.listen(24902) ? (view.getChildCount() != 0) : (ListenerUtil.mutListener.listen(24901) ? (view.getChildCount() == 0) : (view.getChildCount() > 0))))))))) {
                            View itemView = view.getChildAt(0);
                            boolean onTop = (ListenerUtil.mutListener.listen(24913) ? ((ListenerUtil.mutListener.listen(24912) ? (firstVisibleItem >= 0) : (ListenerUtil.mutListener.listen(24911) ? (firstVisibleItem <= 0) : (ListenerUtil.mutListener.listen(24910) ? (firstVisibleItem > 0) : (ListenerUtil.mutListener.listen(24909) ? (firstVisibleItem < 0) : (ListenerUtil.mutListener.listen(24908) ? (firstVisibleItem != 0) : (firstVisibleItem == 0)))))) || itemView.getTop() == listViewTop) : ((ListenerUtil.mutListener.listen(24912) ? (firstVisibleItem >= 0) : (ListenerUtil.mutListener.listen(24911) ? (firstVisibleItem <= 0) : (ListenerUtil.mutListener.listen(24910) ? (firstVisibleItem > 0) : (ListenerUtil.mutListener.listen(24909) ? (firstVisibleItem < 0) : (ListenerUtil.mutListener.listen(24908) ? (firstVisibleItem != 0) : (firstVisibleItem == 0)))))) && itemView.getTop() == listViewTop));
                            if (!ListenerUtil.mutListener.listen(24914)) {
                                swipeRefreshLayout.setEnabled(onTop);
                            }
                            if (!ListenerUtil.mutListener.listen(24943)) {
                                if (firstVisibleItem != lastFirstVisibleItem) {
                                    if (!ListenerUtil.mutListener.listen(24926)) {
                                        if ((ListenerUtil.mutListener.listen(24919) ? (lastFirstVisibleItem >= firstVisibleItem) : (ListenerUtil.mutListener.listen(24918) ? (lastFirstVisibleItem <= firstVisibleItem) : (ListenerUtil.mutListener.listen(24917) ? (lastFirstVisibleItem > firstVisibleItem) : (ListenerUtil.mutListener.listen(24916) ? (lastFirstVisibleItem != firstVisibleItem) : (ListenerUtil.mutListener.listen(24915) ? (lastFirstVisibleItem == firstVisibleItem) : (lastFirstVisibleItem < firstVisibleItem))))))) {
                                            if (!ListenerUtil.mutListener.listen(24923)) {
                                                // scrolling down
                                                AnimationUtil.setFadingVisibility(quickscrollUpView, View.GONE);
                                            }
                                            if (!ListenerUtil.mutListener.listen(24925)) {
                                                if (view.canScrollList(View.SCROLL_AXIS_VERTICAL)) {
                                                    if (!ListenerUtil.mutListener.listen(24924)) {
                                                        AnimationUtil.setFadingVisibility(quickscrollDownView, View.VISIBLE);
                                                    }
                                                }
                                            }
                                        } else {
                                            if (!ListenerUtil.mutListener.listen(24920)) {
                                                // scrolling up
                                                AnimationUtil.setFadingVisibility(quickscrollDownView, View.GONE);
                                            }
                                            if (!ListenerUtil.mutListener.listen(24922)) {
                                                if (view.canScrollList(-View.SCROLL_AXIS_VERTICAL)) {
                                                    if (!ListenerUtil.mutListener.listen(24921)) {
                                                        AnimationUtil.setFadingVisibility(quickscrollUpView, View.VISIBLE);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    if (!ListenerUtil.mutListener.listen(24935)) {
                                        if ((ListenerUtil.mutListener.listen(24933) ? ((ListenerUtil.mutListener.listen(24927) ? (dateView.getVisibility() != View.VISIBLE || composeMessageAdapter != null) : (dateView.getVisibility() != View.VISIBLE && composeMessageAdapter != null)) || (ListenerUtil.mutListener.listen(24932) ? (composeMessageAdapter.getCount() >= 0) : (ListenerUtil.mutListener.listen(24931) ? (composeMessageAdapter.getCount() <= 0) : (ListenerUtil.mutListener.listen(24930) ? (composeMessageAdapter.getCount() < 0) : (ListenerUtil.mutListener.listen(24929) ? (composeMessageAdapter.getCount() != 0) : (ListenerUtil.mutListener.listen(24928) ? (composeMessageAdapter.getCount() == 0) : (composeMessageAdapter.getCount() > 0))))))) : ((ListenerUtil.mutListener.listen(24927) ? (dateView.getVisibility() != View.VISIBLE || composeMessageAdapter != null) : (dateView.getVisibility() != View.VISIBLE && composeMessageAdapter != null)) && (ListenerUtil.mutListener.listen(24932) ? (composeMessageAdapter.getCount() >= 0) : (ListenerUtil.mutListener.listen(24931) ? (composeMessageAdapter.getCount() <= 0) : (ListenerUtil.mutListener.listen(24930) ? (composeMessageAdapter.getCount() < 0) : (ListenerUtil.mutListener.listen(24929) ? (composeMessageAdapter.getCount() != 0) : (ListenerUtil.mutListener.listen(24928) ? (composeMessageAdapter.getCount() == 0) : (composeMessageAdapter.getCount() > 0))))))))) {
                                            if (!ListenerUtil.mutListener.listen(24934)) {
                                                AnimationUtil.slideInAnimation(dateView, false, 200);
                                            }
                                        }
                                    }
                                    if (!ListenerUtil.mutListener.listen(24936)) {
                                        dateViewHandler.removeCallbacks(dateViewTask);
                                    }
                                    if (!ListenerUtil.mutListener.listen(24937)) {
                                        dateViewHandler.postDelayed(dateViewTask, SCROLLBUTTON_VIEW_TIMEOUT);
                                    }
                                    if (!ListenerUtil.mutListener.listen(24938)) {
                                        lastFirstVisibleItem = firstVisibleItem;
                                    }
                                    if (!ListenerUtil.mutListener.listen(24942)) {
                                        if (composeMessageAdapter != null) {
                                            AbstractMessageModel abstractMessageModel = composeMessageAdapter.getItem(firstVisibleItem);
                                            if (!ListenerUtil.mutListener.listen(24941)) {
                                                if (abstractMessageModel != null) {
                                                    Date createdAt = abstractMessageModel.getCreatedAt();
                                                    if (!ListenerUtil.mutListener.listen(24940)) {
                                                        if (createdAt != null) {
                                                            if (!ListenerUtil.mutListener.listen(24939)) {
                                                                dateView.post(() -> {
                                                                    dateTextView.setText(LocaleUtil.formatDateRelative(getActivity(), createdAt.getTime()));
                                                                });
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(24907)) {
                                swipeRefreshLayout.setEnabled(false);
                            }
                        }
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(24967)) {
            listViewSwipeListener = new ListViewSwipeListener(this.convListView, new ListViewSwipeListener.DismissCallbacks() {

                @Override
                public boolean canSwipe(int position) {
                    if (!ListenerUtil.mutListener.listen(24946)) {
                        if (actionMode != null) {
                            return false;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(24948)) {
                        if ((ListenerUtil.mutListener.listen(24947) ? (messageReceiver == null && !messageReceiver.validateSendingPermission(null)) : (messageReceiver == null || !messageReceiver.validateSendingPermission(null)))) {
                            return false;
                        }
                    }
                    int viewType = composeMessageAdapter.getItemViewType(position);
                    if (!ListenerUtil.mutListener.listen(24951)) {
                        if ((ListenerUtil.mutListener.listen(24950) ? ((ListenerUtil.mutListener.listen(24949) ? (viewType == ComposeMessageAdapter.TYPE_STATUS && viewType == ComposeMessageAdapter.TYPE_FIRST_UNREAD) : (viewType == ComposeMessageAdapter.TYPE_STATUS || viewType == ComposeMessageAdapter.TYPE_FIRST_UNREAD)) && viewType == ComposeMessageAdapter.TYPE_DATE_SEPARATOR) : ((ListenerUtil.mutListener.listen(24949) ? (viewType == ComposeMessageAdapter.TYPE_STATUS && viewType == ComposeMessageAdapter.TYPE_FIRST_UNREAD) : (viewType == ComposeMessageAdapter.TYPE_STATUS || viewType == ComposeMessageAdapter.TYPE_FIRST_UNREAD)) || viewType == ComposeMessageAdapter.TYPE_DATE_SEPARATOR))) {
                            return false;
                        }
                    }
                    AbstractMessageModel messageModel = composeMessageAdapter.getItem(position);
                    if (!ListenerUtil.mutListener.listen(24952)) {
                        if (messageModel == null) {
                            return false;
                        }
                    }
                    return QuoteUtil.isQuoteable(messageModel);
                }

                @Override
                public void onSwiped(int position) {
                    AbstractMessageModel abstractMessageModel = composeMessageAdapter.getItem(position);
                    if (!ListenerUtil.mutListener.listen(24959)) {
                        if (preferenceService.isInAppVibrate()) {
                            if (!ListenerUtil.mutListener.listen(24958)) {
                                if ((ListenerUtil.mutListener.listen(24954) ? ((ListenerUtil.mutListener.listen(24953) ? (isAdded() || !isDetached()) : (isAdded() && !isDetached())) || activity != null) : ((ListenerUtil.mutListener.listen(24953) ? (isAdded() || !isDetached()) : (isAdded() && !isDetached())) && activity != null))) {
                                    Vibrator vibrator = (Vibrator) activity.getSystemService(Context.VIBRATOR_SERVICE);
                                    if (!ListenerUtil.mutListener.listen(24957)) {
                                        if ((ListenerUtil.mutListener.listen(24955) ? (vibrator != null || vibrator.hasVibrator()) : (vibrator != null && vibrator.hasVibrator()))) {
                                            if (!ListenerUtil.mutListener.listen(24956)) {
                                                vibrator.vibrate(100);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(24966)) {
                        if (abstractMessageModel != null) {
                            if (!ListenerUtil.mutListener.listen(24965)) {
                                if ((ListenerUtil.mutListener.listen(24960) ? (isQuotePanelShown() || abstractMessageModel.equals(quoteInfo.messageModel)) : (isQuotePanelShown() && abstractMessageModel.equals(quoteInfo.messageModel)))) {
                                    if (!ListenerUtil.mutListener.listen(24964)) {
                                        closeQuoteMode();
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(24963)) {
                                        startQuoteMode(abstractMessageModel, new Runnable() {

                                            @Override
                                            public void run() {
                                                if (!ListenerUtil.mutListener.listen(24962)) {
                                                    RuntimeUtil.runOnUiThread(new Runnable() {

                                                        @Override
                                                        public void run() {
                                                            if (!ListenerUtil.mutListener.listen(24961)) {
                                                                EditTextUtil.showSoftKeyboard(messageText);
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
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(24970)) {
            this.quickscrollDownView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(24968)) {
                        removeScrollButtons();
                    }
                    if (!ListenerUtil.mutListener.listen(24969)) {
                        scrollList(Integer.MAX_VALUE);
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(24973)) {
            this.quickscrollUpView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(24971)) {
                        removeScrollButtons();
                    }
                    if (!ListenerUtil.mutListener.listen(24972)) {
                        scrollList(0);
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(24976)) {
            if (sendButton != null) {
                if (!ListenerUtil.mutListener.listen(24975)) {
                    sendButton.setOnClickListener(new DebouncedOnClickListener(500) {

                        @Override
                        public void onDebouncedClick(View v) {
                            if (!ListenerUtil.mutListener.listen(24974)) {
                                sendMessage();
                            }
                        }
                    });
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(24987)) {
            if (attachButton != null) {
                if (!ListenerUtil.mutListener.listen(24986)) {
                    attachButton.setOnClickListener(new DebouncedOnClickListener(1000) {

                        @Override
                        public void onDebouncedClick(View v) {
                            if (!ListenerUtil.mutListener.listen(24985)) {
                                if (validateSendingPermission()) {
                                    if (!ListenerUtil.mutListener.listen(24978)) {
                                        if (actionMode != null) {
                                            if (!ListenerUtil.mutListener.listen(24977)) {
                                                actionMode.finish();
                                            }
                                        }
                                    }
                                    if (!ListenerUtil.mutListener.listen(24979)) {
                                        closeQuoteMode();
                                    }
                                    Intent intent = new Intent(activity, MediaAttachActivity.class);
                                    if (!ListenerUtil.mutListener.listen(24980)) {
                                        IntentDataUtil.addMessageReceiverToIntent(intent, messageReceiver);
                                    }
                                    if (!ListenerUtil.mutListener.listen(24982)) {
                                        if (ComposeMessageFragment.this.lastMediaFilter != null) {
                                            if (!ListenerUtil.mutListener.listen(24981)) {
                                                intent = IntentDataUtil.addLastMediaFilterToIntent(intent, ComposeMessageFragment.this.lastMediaFilter);
                                            }
                                        }
                                    }
                                    if (!ListenerUtil.mutListener.listen(24983)) {
                                        activity.startActivityForResult(intent, ThreemaActivity.ACTIVITY_ID_ATTACH_MEDIA);
                                    }
                                    if (!ListenerUtil.mutListener.listen(24984)) {
                                        activity.overridePendingTransition(R.anim.fast_fade_in, R.anim.fast_fade_out);
                                    }
                                }
                            }
                        }
                    });
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(24994)) {
            this.messageText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (!ListenerUtil.mutListener.listen(24993)) {
                        if ((ListenerUtil.mutListener.listen(24991) ? ((actionId == EditorInfo.IME_ACTION_SEND) && ((ListenerUtil.mutListener.listen(24990) ? ((ListenerUtil.mutListener.listen(24989) ? ((ListenerUtil.mutListener.listen(24988) ? (event != null || event.getAction() == KeyEvent.ACTION_DOWN) : (event != null && event.getAction() == KeyEvent.ACTION_DOWN)) || event.getKeyCode() == KeyEvent.KEYCODE_ENTER) : ((ListenerUtil.mutListener.listen(24988) ? (event != null || event.getAction() == KeyEvent.ACTION_DOWN) : (event != null && event.getAction() == KeyEvent.ACTION_DOWN)) && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || preferenceService.isEnterToSend()) : ((ListenerUtil.mutListener.listen(24989) ? ((ListenerUtil.mutListener.listen(24988) ? (event != null || event.getAction() == KeyEvent.ACTION_DOWN) : (event != null && event.getAction() == KeyEvent.ACTION_DOWN)) || event.getKeyCode() == KeyEvent.KEYCODE_ENTER) : ((ListenerUtil.mutListener.listen(24988) ? (event != null || event.getAction() == KeyEvent.ACTION_DOWN) : (event != null && event.getAction() == KeyEvent.ACTION_DOWN)) && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) && preferenceService.isEnterToSend())))) : ((actionId == EditorInfo.IME_ACTION_SEND) || ((ListenerUtil.mutListener.listen(24990) ? ((ListenerUtil.mutListener.listen(24989) ? ((ListenerUtil.mutListener.listen(24988) ? (event != null || event.getAction() == KeyEvent.ACTION_DOWN) : (event != null && event.getAction() == KeyEvent.ACTION_DOWN)) || event.getKeyCode() == KeyEvent.KEYCODE_ENTER) : ((ListenerUtil.mutListener.listen(24988) ? (event != null || event.getAction() == KeyEvent.ACTION_DOWN) : (event != null && event.getAction() == KeyEvent.ACTION_DOWN)) && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || preferenceService.isEnterToSend()) : ((ListenerUtil.mutListener.listen(24989) ? ((ListenerUtil.mutListener.listen(24988) ? (event != null || event.getAction() == KeyEvent.ACTION_DOWN) : (event != null && event.getAction() == KeyEvent.ACTION_DOWN)) || event.getKeyCode() == KeyEvent.KEYCODE_ENTER) : ((ListenerUtil.mutListener.listen(24988) ? (event != null || event.getAction() == KeyEvent.ACTION_DOWN) : (event != null && event.getAction() == KeyEvent.ACTION_DOWN)) && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) && preferenceService.isEnterToSend())))))) {
                            if (!ListenerUtil.mutListener.listen(24992)) {
                                sendMessage();
                            }
                            return true;
                        }
                    }
                    return false;
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(25003)) {
            if (ConfigUtils.isDefaultEmojiStyle()) {
                if (!ListenerUtil.mutListener.listen(25002)) {
                    this.messageText.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            if (!ListenerUtil.mutListener.listen(25001)) {
                                if (emojiPicker != null) {
                                    if (!ListenerUtil.mutListener.listen(25000)) {
                                        if (emojiPicker.isShown()) {
                                            if (!ListenerUtil.mutListener.listen(24999)) {
                                                if ((ListenerUtil.mutListener.listen(24996) ? ((ListenerUtil.mutListener.listen(24995) ? (ConfigUtils.isLandscape(activity) || !ConfigUtils.isTabletLayout()) : (ConfigUtils.isLandscape(activity) && !ConfigUtils.isTabletLayout())) || preferenceService.isFullscreenIme()) : ((ListenerUtil.mutListener.listen(24995) ? (ConfigUtils.isLandscape(activity) || !ConfigUtils.isTabletLayout()) : (ConfigUtils.isLandscape(activity) && !ConfigUtils.isTabletLayout())) && preferenceService.isFullscreenIme()))) {
                                                    if (!ListenerUtil.mutListener.listen(24998)) {
                                                        emojiPicker.hide();
                                                    }
                                                } else {
                                                    if (!ListenerUtil.mutListener.listen(24997)) {
                                                        activity.openSoftKeyboard(emojiPicker, messageText);
                                                    }
                                                }
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
        if (!ListenerUtil.mutListener.listen(25010)) {
            this.messageText.addTextChangedListener(new TextWatcher() {

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (!ListenerUtil.mutListener.listen(25004)) {
                        ThreemaApplication.activityUserInteract(activity);
                    }
                    if (!ListenerUtil.mutListener.listen(25005)) {
                        updateSendButton(s);
                    }
                    if (!ListenerUtil.mutListener.listen(25008)) {
                        if ((ListenerUtil.mutListener.listen(25006) ? (getActivity() != null || getActivity().getCurrentFocus() == messageText) : (getActivity() != null && getActivity().getCurrentFocus() == messageText))) {
                            if (!ListenerUtil.mutListener.listen(25007)) {
                                checkPossibleMention(s, start, before, count);
                            }
                        }
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (!ListenerUtil.mutListener.listen(25009)) {
                        updateCameraButton();
                    }
                }
            });
        }
    }

    private void updateCameraButton() {
        if (!ListenerUtil.mutListener.listen(25012)) {
            if ((ListenerUtil.mutListener.listen(25011) ? (cameraButton == null && messageText == null) : (cameraButton == null || messageText == null))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(25023)) {
            if ((ListenerUtil.mutListener.listen(25018) ? ((ListenerUtil.mutListener.listen(25017) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(25016) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(25015) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(25014) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(25013) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)))))) || ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) : ((ListenerUtil.mutListener.listen(25017) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(25016) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(25015) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(25014) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(25013) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)))))) && ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED))) {
                if (!ListenerUtil.mutListener.listen(25022)) {
                    // we hide the camera button only in case a)
                    if ((ListenerUtil.mutListener.listen(25019) ? (!shouldShowRequestPermissionRationale(Manifest.permission.CAMERA) || preferenceService.getCameraPermissionRequestShown()) : (!shouldShowRequestPermissionRationale(Manifest.permission.CAMERA) && preferenceService.getCameraPermissionRequestShown()))) {
                        if (!ListenerUtil.mutListener.listen(25020)) {
                            cameraButton.setVisibility(View.GONE);
                        }
                        if (!ListenerUtil.mutListener.listen(25021)) {
                            fixMessageTextPadding(View.GONE);
                        }
                        return;
                    }
                }
            }
        }
        int visibility = (ListenerUtil.mutListener.listen(25024) ? (messageText.getText() == null && messageText.getText().length() == 0) : (messageText.getText() == null || messageText.getText().length() == 0)) ? View.VISIBLE : View.GONE;
        if (!ListenerUtil.mutListener.listen(25031)) {
            if (cameraButton.getVisibility() != visibility) {
                Transition transition = new Slide(Gravity.RIGHT);
                if (!ListenerUtil.mutListener.listen(25025)) {
                    transition.setDuration(150);
                }
                if (!ListenerUtil.mutListener.listen(25026)) {
                    transition.setInterpolator(new LinearInterpolator());
                }
                if (!ListenerUtil.mutListener.listen(25027)) {
                    transition.addTarget(R.id.camera_button);
                }
                if (!ListenerUtil.mutListener.listen(25028)) {
                    TransitionManager.beginDelayedTransition((ViewGroup) cameraButton.getParent(), transition);
                }
                if (!ListenerUtil.mutListener.listen(25029)) {
                    cameraButton.setVisibility(visibility);
                }
                if (!ListenerUtil.mutListener.listen(25030)) {
                    fixMessageTextPadding(visibility);
                }
            }
        }
    }

    private void fixMessageTextPadding(int visibility) {
        int marginRight = getResources().getDimensionPixelSize(visibility == View.VISIBLE ? R.dimen.emoji_and_photo_button_width : R.dimen.emoji_button_width);
        if (!ListenerUtil.mutListener.listen(25032)) {
            messageText.setPadding(messageText.getPaddingLeft(), messageText.getPaddingTop(), marginRight, messageText.getPaddingBottom());
        }
    }

    private void checkPossibleMention(CharSequence s, int start, int before, int count) {
        if (!ListenerUtil.mutListener.listen(25099)) {
            if ((ListenerUtil.mutListener.listen(25044) ? ((ListenerUtil.mutListener.listen(25038) ? (isGroupChat || (ListenerUtil.mutListener.listen(25037) ? (count >= 1) : (ListenerUtil.mutListener.listen(25036) ? (count <= 1) : (ListenerUtil.mutListener.listen(25035) ? (count > 1) : (ListenerUtil.mutListener.listen(25034) ? (count < 1) : (ListenerUtil.mutListener.listen(25033) ? (count != 1) : (count == 1))))))) : (isGroupChat && (ListenerUtil.mutListener.listen(25037) ? (count >= 1) : (ListenerUtil.mutListener.listen(25036) ? (count <= 1) : (ListenerUtil.mutListener.listen(25035) ? (count > 1) : (ListenerUtil.mutListener.listen(25034) ? (count < 1) : (ListenerUtil.mutListener.listen(25033) ? (count != 1) : (count == 1)))))))) || (ListenerUtil.mutListener.listen(25043) ? (before >= count) : (ListenerUtil.mutListener.listen(25042) ? (before <= count) : (ListenerUtil.mutListener.listen(25041) ? (before > count) : (ListenerUtil.mutListener.listen(25040) ? (before < count) : (ListenerUtil.mutListener.listen(25039) ? (before == count) : (before != count))))))) : ((ListenerUtil.mutListener.listen(25038) ? (isGroupChat || (ListenerUtil.mutListener.listen(25037) ? (count >= 1) : (ListenerUtil.mutListener.listen(25036) ? (count <= 1) : (ListenerUtil.mutListener.listen(25035) ? (count > 1) : (ListenerUtil.mutListener.listen(25034) ? (count < 1) : (ListenerUtil.mutListener.listen(25033) ? (count != 1) : (count == 1))))))) : (isGroupChat && (ListenerUtil.mutListener.listen(25037) ? (count >= 1) : (ListenerUtil.mutListener.listen(25036) ? (count <= 1) : (ListenerUtil.mutListener.listen(25035) ? (count > 1) : (ListenerUtil.mutListener.listen(25034) ? (count < 1) : (ListenerUtil.mutListener.listen(25033) ? (count != 1) : (count == 1)))))))) && (ListenerUtil.mutListener.listen(25043) ? (before >= count) : (ListenerUtil.mutListener.listen(25042) ? (before <= count) : (ListenerUtil.mutListener.listen(25041) ? (before > count) : (ListenerUtil.mutListener.listen(25040) ? (before < count) : (ListenerUtil.mutListener.listen(25039) ? (before == count) : (before != count))))))))) {
                if (!ListenerUtil.mutListener.listen(25098)) {
                    if ((ListenerUtil.mutListener.listen(25055) ? ((ListenerUtil.mutListener.listen(25049) ? (s.length() >= 0) : (ListenerUtil.mutListener.listen(25048) ? (s.length() <= 0) : (ListenerUtil.mutListener.listen(25047) ? (s.length() < 0) : (ListenerUtil.mutListener.listen(25046) ? (s.length() != 0) : (ListenerUtil.mutListener.listen(25045) ? (s.length() == 0) : (s.length() > 0)))))) || (ListenerUtil.mutListener.listen(25054) ? (start >= s.length()) : (ListenerUtil.mutListener.listen(25053) ? (start <= s.length()) : (ListenerUtil.mutListener.listen(25052) ? (start > s.length()) : (ListenerUtil.mutListener.listen(25051) ? (start != s.length()) : (ListenerUtil.mutListener.listen(25050) ? (start == s.length()) : (start < s.length()))))))) : ((ListenerUtil.mutListener.listen(25049) ? (s.length() >= 0) : (ListenerUtil.mutListener.listen(25048) ? (s.length() <= 0) : (ListenerUtil.mutListener.listen(25047) ? (s.length() < 0) : (ListenerUtil.mutListener.listen(25046) ? (s.length() != 0) : (ListenerUtil.mutListener.listen(25045) ? (s.length() == 0) : (s.length() > 0)))))) && (ListenerUtil.mutListener.listen(25054) ? (start >= s.length()) : (ListenerUtil.mutListener.listen(25053) ? (start <= s.length()) : (ListenerUtil.mutListener.listen(25052) ? (start > s.length()) : (ListenerUtil.mutListener.listen(25051) ? (start != s.length()) : (ListenerUtil.mutListener.listen(25050) ? (start == s.length()) : (start < s.length()))))))))) {
                        if (!ListenerUtil.mutListener.listen(25097)) {
                            if (s.charAt(start) == '@') {
                                if (!ListenerUtil.mutListener.listen(25096)) {
                                    if ((ListenerUtil.mutListener.listen(25070) ? ((ListenerUtil.mutListener.listen(25065) ? ((ListenerUtil.mutListener.listen(25060) ? (start >= 0) : (ListenerUtil.mutListener.listen(25059) ? (start <= 0) : (ListenerUtil.mutListener.listen(25058) ? (start > 0) : (ListenerUtil.mutListener.listen(25057) ? (start < 0) : (ListenerUtil.mutListener.listen(25056) ? (start != 0) : (start == 0)))))) && s.charAt((ListenerUtil.mutListener.listen(25064) ? (start % 1) : (ListenerUtil.mutListener.listen(25063) ? (start / 1) : (ListenerUtil.mutListener.listen(25062) ? (start * 1) : (ListenerUtil.mutListener.listen(25061) ? (start + 1) : (start - 1)))))) == ' ') : ((ListenerUtil.mutListener.listen(25060) ? (start >= 0) : (ListenerUtil.mutListener.listen(25059) ? (start <= 0) : (ListenerUtil.mutListener.listen(25058) ? (start > 0) : (ListenerUtil.mutListener.listen(25057) ? (start < 0) : (ListenerUtil.mutListener.listen(25056) ? (start != 0) : (start == 0)))))) || s.charAt((ListenerUtil.mutListener.listen(25064) ? (start % 1) : (ListenerUtil.mutListener.listen(25063) ? (start / 1) : (ListenerUtil.mutListener.listen(25062) ? (start * 1) : (ListenerUtil.mutListener.listen(25061) ? (start + 1) : (start - 1)))))) == ' ')) && s.charAt((ListenerUtil.mutListener.listen(25069) ? (start % 1) : (ListenerUtil.mutListener.listen(25068) ? (start / 1) : (ListenerUtil.mutListener.listen(25067) ? (start * 1) : (ListenerUtil.mutListener.listen(25066) ? (start + 1) : (start - 1)))))) == '\n') : ((ListenerUtil.mutListener.listen(25065) ? ((ListenerUtil.mutListener.listen(25060) ? (start >= 0) : (ListenerUtil.mutListener.listen(25059) ? (start <= 0) : (ListenerUtil.mutListener.listen(25058) ? (start > 0) : (ListenerUtil.mutListener.listen(25057) ? (start < 0) : (ListenerUtil.mutListener.listen(25056) ? (start != 0) : (start == 0)))))) && s.charAt((ListenerUtil.mutListener.listen(25064) ? (start % 1) : (ListenerUtil.mutListener.listen(25063) ? (start / 1) : (ListenerUtil.mutListener.listen(25062) ? (start * 1) : (ListenerUtil.mutListener.listen(25061) ? (start + 1) : (start - 1)))))) == ' ') : ((ListenerUtil.mutListener.listen(25060) ? (start >= 0) : (ListenerUtil.mutListener.listen(25059) ? (start <= 0) : (ListenerUtil.mutListener.listen(25058) ? (start > 0) : (ListenerUtil.mutListener.listen(25057) ? (start < 0) : (ListenerUtil.mutListener.listen(25056) ? (start != 0) : (start == 0)))))) || s.charAt((ListenerUtil.mutListener.listen(25064) ? (start % 1) : (ListenerUtil.mutListener.listen(25063) ? (start / 1) : (ListenerUtil.mutListener.listen(25062) ? (start * 1) : (ListenerUtil.mutListener.listen(25061) ? (start + 1) : (start - 1)))))) == ' ')) || s.charAt((ListenerUtil.mutListener.listen(25069) ? (start % 1) : (ListenerUtil.mutListener.listen(25068) ? (start / 1) : (ListenerUtil.mutListener.listen(25067) ? (start * 1) : (ListenerUtil.mutListener.listen(25066) ? (start + 1) : (start - 1)))))) == '\n'))) {
                                        if (!ListenerUtil.mutListener.listen(25095)) {
                                            if ((ListenerUtil.mutListener.listen(25089) ? ((ListenerUtil.mutListener.listen(25084) ? ((ListenerUtil.mutListener.listen(25079) ? (s.length() >= (ListenerUtil.mutListener.listen(25074) ? (start % 1) : (ListenerUtil.mutListener.listen(25073) ? (start / 1) : (ListenerUtil.mutListener.listen(25072) ? (start * 1) : (ListenerUtil.mutListener.listen(25071) ? (start - 1) : (start + 1)))))) : (ListenerUtil.mutListener.listen(25078) ? (s.length() > (ListenerUtil.mutListener.listen(25074) ? (start % 1) : (ListenerUtil.mutListener.listen(25073) ? (start / 1) : (ListenerUtil.mutListener.listen(25072) ? (start * 1) : (ListenerUtil.mutListener.listen(25071) ? (start - 1) : (start + 1)))))) : (ListenerUtil.mutListener.listen(25077) ? (s.length() < (ListenerUtil.mutListener.listen(25074) ? (start % 1) : (ListenerUtil.mutListener.listen(25073) ? (start / 1) : (ListenerUtil.mutListener.listen(25072) ? (start * 1) : (ListenerUtil.mutListener.listen(25071) ? (start - 1) : (start + 1)))))) : (ListenerUtil.mutListener.listen(25076) ? (s.length() != (ListenerUtil.mutListener.listen(25074) ? (start % 1) : (ListenerUtil.mutListener.listen(25073) ? (start / 1) : (ListenerUtil.mutListener.listen(25072) ? (start * 1) : (ListenerUtil.mutListener.listen(25071) ? (start - 1) : (start + 1)))))) : (ListenerUtil.mutListener.listen(25075) ? (s.length() == (ListenerUtil.mutListener.listen(25074) ? (start % 1) : (ListenerUtil.mutListener.listen(25073) ? (start / 1) : (ListenerUtil.mutListener.listen(25072) ? (start * 1) : (ListenerUtil.mutListener.listen(25071) ? (start - 1) : (start + 1)))))) : (s.length() <= (ListenerUtil.mutListener.listen(25074) ? (start % 1) : (ListenerUtil.mutListener.listen(25073) ? (start / 1) : (ListenerUtil.mutListener.listen(25072) ? (start * 1) : (ListenerUtil.mutListener.listen(25071) ? (start - 1) : (start + 1))))))))))) && s.charAt((ListenerUtil.mutListener.listen(25083) ? (start % 1) : (ListenerUtil.mutListener.listen(25082) ? (start / 1) : (ListenerUtil.mutListener.listen(25081) ? (start * 1) : (ListenerUtil.mutListener.listen(25080) ? (start - 1) : (start + 1)))))) == ' ') : ((ListenerUtil.mutListener.listen(25079) ? (s.length() >= (ListenerUtil.mutListener.listen(25074) ? (start % 1) : (ListenerUtil.mutListener.listen(25073) ? (start / 1) : (ListenerUtil.mutListener.listen(25072) ? (start * 1) : (ListenerUtil.mutListener.listen(25071) ? (start - 1) : (start + 1)))))) : (ListenerUtil.mutListener.listen(25078) ? (s.length() > (ListenerUtil.mutListener.listen(25074) ? (start % 1) : (ListenerUtil.mutListener.listen(25073) ? (start / 1) : (ListenerUtil.mutListener.listen(25072) ? (start * 1) : (ListenerUtil.mutListener.listen(25071) ? (start - 1) : (start + 1)))))) : (ListenerUtil.mutListener.listen(25077) ? (s.length() < (ListenerUtil.mutListener.listen(25074) ? (start % 1) : (ListenerUtil.mutListener.listen(25073) ? (start / 1) : (ListenerUtil.mutListener.listen(25072) ? (start * 1) : (ListenerUtil.mutListener.listen(25071) ? (start - 1) : (start + 1)))))) : (ListenerUtil.mutListener.listen(25076) ? (s.length() != (ListenerUtil.mutListener.listen(25074) ? (start % 1) : (ListenerUtil.mutListener.listen(25073) ? (start / 1) : (ListenerUtil.mutListener.listen(25072) ? (start * 1) : (ListenerUtil.mutListener.listen(25071) ? (start - 1) : (start + 1)))))) : (ListenerUtil.mutListener.listen(25075) ? (s.length() == (ListenerUtil.mutListener.listen(25074) ? (start % 1) : (ListenerUtil.mutListener.listen(25073) ? (start / 1) : (ListenerUtil.mutListener.listen(25072) ? (start * 1) : (ListenerUtil.mutListener.listen(25071) ? (start - 1) : (start + 1)))))) : (s.length() <= (ListenerUtil.mutListener.listen(25074) ? (start % 1) : (ListenerUtil.mutListener.listen(25073) ? (start / 1) : (ListenerUtil.mutListener.listen(25072) ? (start * 1) : (ListenerUtil.mutListener.listen(25071) ? (start - 1) : (start + 1))))))))))) || s.charAt((ListenerUtil.mutListener.listen(25083) ? (start % 1) : (ListenerUtil.mutListener.listen(25082) ? (start / 1) : (ListenerUtil.mutListener.listen(25081) ? (start * 1) : (ListenerUtil.mutListener.listen(25080) ? (start - 1) : (start + 1)))))) == ' ')) && s.charAt((ListenerUtil.mutListener.listen(25088) ? (start % 1) : (ListenerUtil.mutListener.listen(25087) ? (start / 1) : (ListenerUtil.mutListener.listen(25086) ? (start * 1) : (ListenerUtil.mutListener.listen(25085) ? (start - 1) : (start + 1)))))) == '\n') : ((ListenerUtil.mutListener.listen(25084) ? ((ListenerUtil.mutListener.listen(25079) ? (s.length() >= (ListenerUtil.mutListener.listen(25074) ? (start % 1) : (ListenerUtil.mutListener.listen(25073) ? (start / 1) : (ListenerUtil.mutListener.listen(25072) ? (start * 1) : (ListenerUtil.mutListener.listen(25071) ? (start - 1) : (start + 1)))))) : (ListenerUtil.mutListener.listen(25078) ? (s.length() > (ListenerUtil.mutListener.listen(25074) ? (start % 1) : (ListenerUtil.mutListener.listen(25073) ? (start / 1) : (ListenerUtil.mutListener.listen(25072) ? (start * 1) : (ListenerUtil.mutListener.listen(25071) ? (start - 1) : (start + 1)))))) : (ListenerUtil.mutListener.listen(25077) ? (s.length() < (ListenerUtil.mutListener.listen(25074) ? (start % 1) : (ListenerUtil.mutListener.listen(25073) ? (start / 1) : (ListenerUtil.mutListener.listen(25072) ? (start * 1) : (ListenerUtil.mutListener.listen(25071) ? (start - 1) : (start + 1)))))) : (ListenerUtil.mutListener.listen(25076) ? (s.length() != (ListenerUtil.mutListener.listen(25074) ? (start % 1) : (ListenerUtil.mutListener.listen(25073) ? (start / 1) : (ListenerUtil.mutListener.listen(25072) ? (start * 1) : (ListenerUtil.mutListener.listen(25071) ? (start - 1) : (start + 1)))))) : (ListenerUtil.mutListener.listen(25075) ? (s.length() == (ListenerUtil.mutListener.listen(25074) ? (start % 1) : (ListenerUtil.mutListener.listen(25073) ? (start / 1) : (ListenerUtil.mutListener.listen(25072) ? (start * 1) : (ListenerUtil.mutListener.listen(25071) ? (start - 1) : (start + 1)))))) : (s.length() <= (ListenerUtil.mutListener.listen(25074) ? (start % 1) : (ListenerUtil.mutListener.listen(25073) ? (start / 1) : (ListenerUtil.mutListener.listen(25072) ? (start * 1) : (ListenerUtil.mutListener.listen(25071) ? (start - 1) : (start + 1))))))))))) && s.charAt((ListenerUtil.mutListener.listen(25083) ? (start % 1) : (ListenerUtil.mutListener.listen(25082) ? (start / 1) : (ListenerUtil.mutListener.listen(25081) ? (start * 1) : (ListenerUtil.mutListener.listen(25080) ? (start - 1) : (start + 1)))))) == ' ') : ((ListenerUtil.mutListener.listen(25079) ? (s.length() >= (ListenerUtil.mutListener.listen(25074) ? (start % 1) : (ListenerUtil.mutListener.listen(25073) ? (start / 1) : (ListenerUtil.mutListener.listen(25072) ? (start * 1) : (ListenerUtil.mutListener.listen(25071) ? (start - 1) : (start + 1)))))) : (ListenerUtil.mutListener.listen(25078) ? (s.length() > (ListenerUtil.mutListener.listen(25074) ? (start % 1) : (ListenerUtil.mutListener.listen(25073) ? (start / 1) : (ListenerUtil.mutListener.listen(25072) ? (start * 1) : (ListenerUtil.mutListener.listen(25071) ? (start - 1) : (start + 1)))))) : (ListenerUtil.mutListener.listen(25077) ? (s.length() < (ListenerUtil.mutListener.listen(25074) ? (start % 1) : (ListenerUtil.mutListener.listen(25073) ? (start / 1) : (ListenerUtil.mutListener.listen(25072) ? (start * 1) : (ListenerUtil.mutListener.listen(25071) ? (start - 1) : (start + 1)))))) : (ListenerUtil.mutListener.listen(25076) ? (s.length() != (ListenerUtil.mutListener.listen(25074) ? (start % 1) : (ListenerUtil.mutListener.listen(25073) ? (start / 1) : (ListenerUtil.mutListener.listen(25072) ? (start * 1) : (ListenerUtil.mutListener.listen(25071) ? (start - 1) : (start + 1)))))) : (ListenerUtil.mutListener.listen(25075) ? (s.length() == (ListenerUtil.mutListener.listen(25074) ? (start % 1) : (ListenerUtil.mutListener.listen(25073) ? (start / 1) : (ListenerUtil.mutListener.listen(25072) ? (start * 1) : (ListenerUtil.mutListener.listen(25071) ? (start - 1) : (start + 1)))))) : (s.length() <= (ListenerUtil.mutListener.listen(25074) ? (start % 1) : (ListenerUtil.mutListener.listen(25073) ? (start / 1) : (ListenerUtil.mutListener.listen(25072) ? (start * 1) : (ListenerUtil.mutListener.listen(25071) ? (start - 1) : (start + 1))))))))))) || s.charAt((ListenerUtil.mutListener.listen(25083) ? (start % 1) : (ListenerUtil.mutListener.listen(25082) ? (start / 1) : (ListenerUtil.mutListener.listen(25081) ? (start * 1) : (ListenerUtil.mutListener.listen(25080) ? (start - 1) : (start + 1)))))) == ' ')) || s.charAt((ListenerUtil.mutListener.listen(25088) ? (start % 1) : (ListenerUtil.mutListener.listen(25087) ? (start / 1) : (ListenerUtil.mutListener.listen(25086) ? (start * 1) : (ListenerUtil.mutListener.listen(25085) ? (start - 1) : (start + 1)))))) == '\n'))) {
                                                if (!ListenerUtil.mutListener.listen(25090)) {
                                                    dismissTooltipPopup(workTooltipPopup, true);
                                                }
                                                if (!ListenerUtil.mutListener.listen(25091)) {
                                                    workTooltipPopup = null;
                                                }
                                                if (!ListenerUtil.mutListener.listen(25092)) {
                                                    dismissMentionPopup();
                                                }
                                                if (!ListenerUtil.mutListener.listen(25093)) {
                                                    mentionPopup = new MentionSelectorPopup(getActivity(), this, groupService, this.contactService, this.userService, this.preferenceService, groupModel);
                                                }
                                                if (!ListenerUtil.mutListener.listen(25094)) {
                                                    mentionPopup.show(getActivity(), this.messageText, emojiButton.getWidth());
                                                }
                                                ;
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

    private void updateSendButton(CharSequence s) {
        if (!ListenerUtil.mutListener.listen(25109)) {
            if (isQuotePanelShown()) {
                if (!ListenerUtil.mutListener.listen(25108)) {
                    if (TestUtil.empty(s)) {
                        if (!ListenerUtil.mutListener.listen(25107)) {
                            sendButton.setEnabled(false);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(25105)) {
                            sendButton.setSend();
                        }
                        if (!ListenerUtil.mutListener.listen(25106)) {
                            sendButton.setEnabled(true);
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(25104)) {
                    if (TestUtil.empty(s)) {
                        if (!ListenerUtil.mutListener.listen(25102)) {
                            sendButton.setRecord();
                        }
                        if (!ListenerUtil.mutListener.listen(25103)) {
                            sendButton.setEnabled(true);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(25100)) {
                            sendButton.setSend();
                        }
                        if (!ListenerUtil.mutListener.listen(25101)) {
                            sendButton.setEnabled(true);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(25111)) {
            if (emojiButton != null)
                if (!ListenerUtil.mutListener.listen(25110)) {
                    emojiButton.setVisibility(preferenceService.getEmojiStyle() != PreferenceService.EmojiStyle_ANDROID ? View.VISIBLE : View.GONE);
                }
        }
        if (!ListenerUtil.mutListener.listen(25113)) {
            if (messageText != null)
                if (!ListenerUtil.mutListener.listen(25112)) {
                    messageText.setVisibility(View.VISIBLE);
                }
        }
    }

    private void setBackgroundWallpaper() {
        if (!ListenerUtil.mutListener.listen(25116)) {
            if ((ListenerUtil.mutListener.listen(25114) ? (isAdded() || this.wallpaperView != null) : (isAdded() && this.wallpaperView != null))) {
                if (!ListenerUtil.mutListener.listen(25115)) {
                    wallpaperService.setupWallpaperBitmap(this.messageReceiver, this.wallpaperView, ConfigUtils.isLandscape(activity));
                }
            }
        }
    }

    private void resetDefaultValues() {
        if (!ListenerUtil.mutListener.listen(25117)) {
            this.distributionListId = 0;
        }
        if (!ListenerUtil.mutListener.listen(25118)) {
            this.groupId = 0;
        }
        if (!ListenerUtil.mutListener.listen(25119)) {
            this.identity = null;
        }
        if (!ListenerUtil.mutListener.listen(25120)) {
            this.groupModel = null;
        }
        if (!ListenerUtil.mutListener.listen(25121)) {
            this.distributionListModel = null;
        }
        if (!ListenerUtil.mutListener.listen(25122)) {
            this.contactModel = null;
        }
        if (!ListenerUtil.mutListener.listen(25123)) {
            this.messageReceiver = null;
        }
        if (!ListenerUtil.mutListener.listen(25124)) {
            this.listInstancePosition = AbsListView.INVALID_POSITION;
        }
        if (!ListenerUtil.mutListener.listen(25125)) {
            this.listInstanceReceiverId = null;
        }
        if (!ListenerUtil.mutListener.listen(25131)) {
            if (ConfigUtils.isTabletLayout()) {
                if (!ListenerUtil.mutListener.listen(25126)) {
                    // apply pending deletes upon reentering a chat through onNewIntent() in multi-frame environment
                    deleteDeleteableMessages();
                }
                if (!ListenerUtil.mutListener.listen(25129)) {
                    if ((ListenerUtil.mutListener.listen(25127) ? (this.deleteSnackbar != null || this.deleteSnackbar.isShownOrQueued()) : (this.deleteSnackbar != null && this.deleteSnackbar.isShownOrQueued()))) {
                        if (!ListenerUtil.mutListener.listen(25128)) {
                            this.deleteSnackbar.dismiss();
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(25130)) {
                    closeQuoteMode();
                }
            }
        }
    }

    private void getValuesFromBundle(Bundle bundle) {
        if (!ListenerUtil.mutListener.listen(25137)) {
            if (bundle != null) {
                if (!ListenerUtil.mutListener.listen(25132)) {
                    this.groupId = bundle.getInt(ThreemaApplication.INTENT_DATA_GROUP, 0);
                }
                if (!ListenerUtil.mutListener.listen(25133)) {
                    this.distributionListId = bundle.getInt(ThreemaApplication.INTENT_DATA_DISTRIBUTION_LIST, 0);
                }
                if (!ListenerUtil.mutListener.listen(25134)) {
                    this.identity = bundle.getString(ThreemaApplication.INTENT_DATA_CONTACT);
                }
                if (!ListenerUtil.mutListener.listen(25135)) {
                    this.intentTimestamp = bundle.getLong(ThreemaApplication.INTENT_DATA_TIMESTAMP, 0L);
                }
                if (!ListenerUtil.mutListener.listen(25136)) {
                    this.cameraUri = bundle.getParcelable(CAMERA_URI);
                }
            }
        }
    }

    public void onNewIntent(Intent intent) {
        if (!ListenerUtil.mutListener.listen(25138)) {
            logger.debug("onNewIntent");
        }
        if (!ListenerUtil.mutListener.listen(25139)) {
            if (!requiredInstances()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(25140)) {
            resetDefaultValues();
        }
        if (!ListenerUtil.mutListener.listen(25141)) {
            handleIntent(intent);
        }
        if (!ListenerUtil.mutListener.listen(25143)) {
            // initialize various toolbar items
            if (actionMode != null) {
                if (!ListenerUtil.mutListener.listen(25142)) {
                    actionMode.finish();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(25145)) {
            if (searchActionMode != null) {
                if (!ListenerUtil.mutListener.listen(25144)) {
                    searchActionMode.finish();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(25146)) {
            this.closeQuoteMode();
        }
        if (!ListenerUtil.mutListener.listen(25147)) {
            this.updateToolbarTitle();
        }
        if (!ListenerUtil.mutListener.listen(25148)) {
            this.updateMenus();
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setupToolbar() {
        View actionBarTitleView = layoutInflater.inflate(R.layout.actionbar_compose_title, null);
        if (!ListenerUtil.mutListener.listen(25172)) {
            if (actionBarTitleView != null) {
                if (!ListenerUtil.mutListener.listen(25149)) {
                    this.actionBarTitleTextView = actionBarTitleView.findViewById(R.id.title);
                }
                if (!ListenerUtil.mutListener.listen(25150)) {
                    this.actionBarSubtitleImageView = actionBarTitleView.findViewById(R.id.subtitle_image);
                }
                if (!ListenerUtil.mutListener.listen(25151)) {
                    this.actionBarSubtitleTextView = actionBarTitleView.findViewById(R.id.subtitle_text);
                }
                if (!ListenerUtil.mutListener.listen(25152)) {
                    this.actionBarAvatarView = actionBarTitleView.findViewById(R.id.avatar_view);
                }
                final RelativeLayout actionBarTitleContainer = actionBarTitleView.findViewById(R.id.title_container);
                if (!ListenerUtil.mutListener.listen(25162)) {
                    actionBarTitleContainer.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            Intent intent = null;
                            if (!ListenerUtil.mutListener.listen(25158)) {
                                if (isGroupChat) {
                                    if (!ListenerUtil.mutListener.listen(25157)) {
                                        if (groupService.isGroupMember(groupModel)) {
                                            if (!ListenerUtil.mutListener.listen(25156)) {
                                                intent = groupService.getGroupEditIntent(groupModel, activity);
                                            }
                                        }
                                    }
                                } else if (isDistributionListChat) {
                                    if (!ListenerUtil.mutListener.listen(25155)) {
                                        intent = new Intent(activity, DistributionListAddActivity.class);
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(25153)) {
                                        intent = new Intent(activity, ContactDetailActivity.class);
                                    }
                                    if (!ListenerUtil.mutListener.listen(25154)) {
                                        intent.putExtra(ThreemaApplication.INTENT_DATA_CONTACT_READONLY, true);
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(25161)) {
                                if (intent != null) {
                                    if (!ListenerUtil.mutListener.listen(25159)) {
                                        intent = addExtrasToIntent(intent, messageReceiver);
                                    }
                                    if (!ListenerUtil.mutListener.listen(25160)) {
                                        activity.startActivityForResult(intent, 0);
                                    }
                                }
                            }
                        }
                    });
                }
                if (!ListenerUtil.mutListener.listen(25171)) {
                    if (contactModel != null) {
                        if (!ListenerUtil.mutListener.listen(25170)) {
                            if (contactModel.getType() == IdentityType.WORK) {
                                if (!ListenerUtil.mutListener.listen(25169)) {
                                    if (!ConfigUtils.isWorkBuild()) {
                                        if (!ListenerUtil.mutListener.listen(25168)) {
                                            if (!preferenceService.getIsWorkHintTooltipShown()) {
                                                if (!ListenerUtil.mutListener.listen(25167)) {
                                                    actionBarTitleTextView.postDelayed(() -> {
                                                        if (getActivity() != null && isAdded()) {
                                                            dismissTooltipPopup(workTooltipPopup, true);
                                                            int[] location = new int[2];
                                                            actionBarAvatarView.getLocationOnScreen(location);
                                                            location[0] += actionBarAvatarView.getWidth() / 2;
                                                            location[1] += actionBarAvatarView.getHeight();
                                                            workTooltipPopup = new TooltipPopup(getActivity(), R.string.preferences__tooltip_work_hint_shown, R.layout.popup_tooltip_top_left_work, this, new Intent(getActivity(), WorkExplainActivity.class));
                                                            workTooltipPopup.show(getActivity(), actionBarAvatarView, getString(R.string.tooltip_work_hint), TooltipPopup.ALIGN_BELOW_ANCHOR_ARROW_LEFT, location, 4000);
                                                        }
                                                    }, 1000);
                                                }
                                            }
                                        }
                                    }
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(25166)) {
                                    if (!preferenceService.getIsVideoCallTooltipShown()) {
                                        if (!ListenerUtil.mutListener.listen(25165)) {
                                            if ((ListenerUtil.mutListener.listen(25163) ? (ContactUtil.canReceiveVoipMessages(contactModel, blackListIdentityService) || ConfigUtils.isCallsEnabled(getContext(), preferenceService, licenseService)) : (ContactUtil.canReceiveVoipMessages(contactModel, blackListIdentityService) && ConfigUtils.isCallsEnabled(getContext(), preferenceService, licenseService)))) {
                                                View toolbar = ((ThreemaToolbarActivity) getActivity()).getToolbar();
                                                if (!ListenerUtil.mutListener.listen(25164)) {
                                                    toolbar.postDelayed(() -> {
                                                        if (getActivity() != null && isAdded()) {
                                                            int[] location = new int[2];
                                                            View itemView = toolbar.findViewById(R.id.menu_threema_call);
                                                            if (itemView != null) {
                                                                itemView.getLocationInWindow(location);
                                                                if (ConfigUtils.isVideoCallsEnabled()) {
                                                                    try {
                                                                        TapTargetView.showFor(getActivity(), TapTarget.forView(itemView, getString(R.string.video_calls_new), getString(R.string.tooltip_video_call)).outerCircleColor(// Specify a color for the outer circle
                                                                        ConfigUtils.getAppTheme(getActivity()) == ConfigUtils.THEME_DARK ? R.color.accent_dark : R.color.accent_light).outerCircleAlpha(// Specify the alpha amount for the outer circle
                                                                        0.96f).targetCircleColor(// Specify a color for the target circle
                                                                        android.R.color.white).titleTextSize(// Specify the size (in sp) of the title text
                                                                        24).titleTextColor(// Specify the color of the title text
                                                                        android.R.color.white).descriptionTextSize(// Specify the size (in sp) of the description text
                                                                        18).descriptionTextColor(// Specify the color of the description text
                                                                        android.R.color.white).textColor(// Specify a color for both the title and description text
                                                                        android.R.color.white).textTypeface(// Specify a typeface for the text
                                                                        Typeface.SANS_SERIF).dimColor(// If set, will dim behind the view with 30% opacity of the given color
                                                                        android.R.color.black).drawShadow(// Whether to draw a drop shadow or not
                                                                        true).cancelable(// Whether tapping outside the outer circle dismisses the view
                                                                        true).tintTarget(// Whether to tint the target view's color
                                                                        true).transparentTarget(// Specify whether the target is transparent (displays the content underneath)
                                                                        false).targetRadius(// Specify the target radius (in dp)
                                                                        50), new // The listener can listen for regular clicks, long clicks or cancels
                                                                        TapTargetView.Listener() {

                                                                            @Override
                                                                            public void onTargetClick(TapTargetView view) {
                                                                                super.onTargetClick(view);
                                                                                String name = NameUtil.getDisplayNameOrNickname(contactModel, false);
                                                                                GenericAlertDialog dialog = GenericAlertDialog.newInstance(R.string.threema_call, String.format(getContext().getString(R.string.voip_call_confirm), name), R.string.ok, R.string.cancel);
                                                                                dialog.setTargetFragment(ComposeMessageFragment.this, 0);
                                                                                dialog.show(getFragmentManager(), ComposeMessageFragment.DIALOG_TAG_CONFIRM_CALL);
                                                                            }
                                                                        });
                                                                        preferenceService.setVideoCallTooltipShown(true);
                                                                    } catch (Exception ignore) {
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }, 1000);
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
        if (!ListenerUtil.mutListener.listen(25174)) {
            if (activity == null) {
                if (!ListenerUtil.mutListener.listen(25173)) {
                    activity = (ComposeMessageActivity) getActivity();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(25179)) {
            if (activity != null) {
                if (!ListenerUtil.mutListener.listen(25175)) {
                    this.actionBar = activity.getSupportActionBar();
                }
                if (!ListenerUtil.mutListener.listen(25178)) {
                    if (actionBar != null) {
                        if (!ListenerUtil.mutListener.listen(25176)) {
                            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_HOME_AS_UP);
                        }
                        if (!ListenerUtil.mutListener.listen(25177)) {
                            actionBar.setCustomView(actionBarTitleView);
                        }
                    }
                }
            }
        }
    }

    @UiThread
    private void handleIntent(Intent intent) {
        if (!ListenerUtil.mutListener.listen(25180)) {
            logger.debug("handleIntent");
        }
        String conversationUid;
        if (!ListenerUtil.mutListener.listen(25181)) {
            this.isGroupChat = false;
        }
        if (!ListenerUtil.mutListener.listen(25182)) {
            this.isDistributionListChat = false;
        }
        if (!ListenerUtil.mutListener.listen(25183)) {
            this.currentPageReferenceId = null;
        }
        if (!ListenerUtil.mutListener.listen(25186)) {
            // fix ANDR-432
            if (this.typingIndicatorTextWatcher != null) {
                if (!ListenerUtil.mutListener.listen(25185)) {
                    if (this.messageText != null) {
                        if (!ListenerUtil.mutListener.listen(25184)) {
                            this.messageText.removeTextChangedListener(this.typingIndicatorTextWatcher);
                        }
                    }
                }
            }
        }
        if ((ListenerUtil.mutListener.listen(25192) ? (intent.hasExtra(ThreemaApplication.INTENT_DATA_GROUP) && (ListenerUtil.mutListener.listen(25191) ? (this.groupId >= 0) : (ListenerUtil.mutListener.listen(25190) ? (this.groupId <= 0) : (ListenerUtil.mutListener.listen(25189) ? (this.groupId > 0) : (ListenerUtil.mutListener.listen(25188) ? (this.groupId < 0) : (ListenerUtil.mutListener.listen(25187) ? (this.groupId == 0) : (this.groupId != 0))))))) : (intent.hasExtra(ThreemaApplication.INTENT_DATA_GROUP) || (ListenerUtil.mutListener.listen(25191) ? (this.groupId >= 0) : (ListenerUtil.mutListener.listen(25190) ? (this.groupId <= 0) : (ListenerUtil.mutListener.listen(25189) ? (this.groupId > 0) : (ListenerUtil.mutListener.listen(25188) ? (this.groupId < 0) : (ListenerUtil.mutListener.listen(25187) ? (this.groupId == 0) : (this.groupId != 0))))))))) {
            if (!ListenerUtil.mutListener.listen(25247)) {
                this.isGroupChat = true;
            }
            if (!ListenerUtil.mutListener.listen(25254)) {
                if ((ListenerUtil.mutListener.listen(25252) ? (this.groupId >= 0) : (ListenerUtil.mutListener.listen(25251) ? (this.groupId <= 0) : (ListenerUtil.mutListener.listen(25250) ? (this.groupId > 0) : (ListenerUtil.mutListener.listen(25249) ? (this.groupId < 0) : (ListenerUtil.mutListener.listen(25248) ? (this.groupId != 0) : (this.groupId == 0))))))) {
                    if (!ListenerUtil.mutListener.listen(25253)) {
                        this.groupId = intent.getIntExtra(ThreemaApplication.INTENT_DATA_GROUP, 0);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(25255)) {
                this.groupModel = this.groupService.getById(this.groupId);
            }
            if (!ListenerUtil.mutListener.listen(25259)) {
                if ((ListenerUtil.mutListener.listen(25256) ? (this.groupModel == null && this.groupModel.isDeleted()) : (this.groupModel == null || this.groupModel.isDeleted()))) {
                    if (!ListenerUtil.mutListener.listen(25258)) {
                        logger.error(activity.getString(R.string.group_not_found), activity, new Runnable() {

                            @Override
                            public void run() {
                                if (!ListenerUtil.mutListener.listen(25257)) {
                                    activity.finish();
                                }
                            }
                        });
                    }
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(25260)) {
                // if (!ConfigUtils.isTabletLayout()) {
                intent.removeExtra(ThreemaApplication.INTENT_DATA_GROUP);
            }
            if (!ListenerUtil.mutListener.listen(25261)) {
                // }
                this.messageReceiver = this.groupService.createReceiver(this.groupModel);
            }
            conversationUid = ConversationUtil.getGroupConversationUid(this.groupId);
        } else if ((ListenerUtil.mutListener.listen(25198) ? (intent.hasExtra(ThreemaApplication.INTENT_DATA_DISTRIBUTION_LIST) && (ListenerUtil.mutListener.listen(25197) ? (this.distributionListId >= 0) : (ListenerUtil.mutListener.listen(25196) ? (this.distributionListId <= 0) : (ListenerUtil.mutListener.listen(25195) ? (this.distributionListId > 0) : (ListenerUtil.mutListener.listen(25194) ? (this.distributionListId < 0) : (ListenerUtil.mutListener.listen(25193) ? (this.distributionListId == 0) : (this.distributionListId != 0))))))) : (intent.hasExtra(ThreemaApplication.INTENT_DATA_DISTRIBUTION_LIST) || (ListenerUtil.mutListener.listen(25197) ? (this.distributionListId >= 0) : (ListenerUtil.mutListener.listen(25196) ? (this.distributionListId <= 0) : (ListenerUtil.mutListener.listen(25195) ? (this.distributionListId > 0) : (ListenerUtil.mutListener.listen(25194) ? (this.distributionListId < 0) : (ListenerUtil.mutListener.listen(25193) ? (this.distributionListId == 0) : (this.distributionListId != 0))))))))) {
            if (!ListenerUtil.mutListener.listen(25232)) {
                this.isDistributionListChat = true;
            }
            try {
                if (!ListenerUtil.mutListener.listen(25240)) {
                    if ((ListenerUtil.mutListener.listen(25238) ? (this.distributionListId >= 0) : (ListenerUtil.mutListener.listen(25237) ? (this.distributionListId <= 0) : (ListenerUtil.mutListener.listen(25236) ? (this.distributionListId > 0) : (ListenerUtil.mutListener.listen(25235) ? (this.distributionListId < 0) : (ListenerUtil.mutListener.listen(25234) ? (this.distributionListId != 0) : (this.distributionListId == 0))))))) {
                        if (!ListenerUtil.mutListener.listen(25239)) {
                            this.distributionListId = intent.getIntExtra(ThreemaApplication.INTENT_DATA_DISTRIBUTION_LIST, 0);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(25241)) {
                    this.distributionListModel = distributionListService.getById(this.distributionListId);
                }
                if (!ListenerUtil.mutListener.listen(25244)) {
                    if (this.distributionListModel == null) {
                        if (!ListenerUtil.mutListener.listen(25243)) {
                            logger.error("Invalid distribution list", activity, new Runnable() {

                                @Override
                                public void run() {
                                    if (!ListenerUtil.mutListener.listen(25242)) {
                                        activity.finish();
                                    }
                                }
                            });
                        }
                        return;
                    }
                }
                if (!ListenerUtil.mutListener.listen(25245)) {
                    intent.removeExtra(ThreemaApplication.INTENT_DATA_DISTRIBUTION_LIST);
                }
                if (!ListenerUtil.mutListener.listen(25246)) {
                    this.messageReceiver = distributionListService.createReceiver(this.distributionListModel);
                }
            } catch (Exception e) {
                if (!ListenerUtil.mutListener.listen(25233)) {
                    logger.error("Exception", e);
                }
                return;
            }
            conversationUid = ConversationUtil.getDistributionListConversationUid(this.distributionListId);
        } else {
            if (!ListenerUtil.mutListener.listen(25200)) {
                if (TestUtil.empty(this.identity)) {
                    if (!ListenerUtil.mutListener.listen(25199)) {
                        this.identity = intent.getStringExtra(ThreemaApplication.INTENT_DATA_CONTACT);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(25211)) {
                if (this.identity == null) {
                    if (!ListenerUtil.mutListener.listen(25210)) {
                        if (intent.getData() != null) {
                            if (!ListenerUtil.mutListener.listen(25209)) {
                                if ((ListenerUtil.mutListener.listen(25206) ? ((ListenerUtil.mutListener.listen(25205) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(25204) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(25203) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(25202) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(25201) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) : (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)))))) && ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) : ((ListenerUtil.mutListener.listen(25205) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(25204) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(25203) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(25202) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(25201) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) : (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)))))) || ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED))) {
                                    if (!ListenerUtil.mutListener.listen(25208)) {
                                        this.identity = ContactUtil.getIdentityFromViewIntent(activity, intent);
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(25207)) {
                                        Toast.makeText(activity, R.string.permission_contacts_required, Toast.LENGTH_LONG).show();
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(25212)) {
                // if (!ConfigUtils.isTabletLayout()) {
                intent.removeExtra(ThreemaApplication.INTENT_DATA_CONTACT);
            }
            if (!ListenerUtil.mutListener.listen(25222)) {
                if ((ListenerUtil.mutListener.listen(25219) ? ((ListenerUtil.mutListener.listen(25218) ? (this.identity == null && (ListenerUtil.mutListener.listen(25217) ? (this.identity.length() >= 0) : (ListenerUtil.mutListener.listen(25216) ? (this.identity.length() <= 0) : (ListenerUtil.mutListener.listen(25215) ? (this.identity.length() > 0) : (ListenerUtil.mutListener.listen(25214) ? (this.identity.length() < 0) : (ListenerUtil.mutListener.listen(25213) ? (this.identity.length() != 0) : (this.identity.length() == 0))))))) : (this.identity == null || (ListenerUtil.mutListener.listen(25217) ? (this.identity.length() >= 0) : (ListenerUtil.mutListener.listen(25216) ? (this.identity.length() <= 0) : (ListenerUtil.mutListener.listen(25215) ? (this.identity.length() > 0) : (ListenerUtil.mutListener.listen(25214) ? (this.identity.length() < 0) : (ListenerUtil.mutListener.listen(25213) ? (this.identity.length() != 0) : (this.identity.length() == 0)))))))) && this.identity.equals(this.userService.getIdentity())) : ((ListenerUtil.mutListener.listen(25218) ? (this.identity == null && (ListenerUtil.mutListener.listen(25217) ? (this.identity.length() >= 0) : (ListenerUtil.mutListener.listen(25216) ? (this.identity.length() <= 0) : (ListenerUtil.mutListener.listen(25215) ? (this.identity.length() > 0) : (ListenerUtil.mutListener.listen(25214) ? (this.identity.length() < 0) : (ListenerUtil.mutListener.listen(25213) ? (this.identity.length() != 0) : (this.identity.length() == 0))))))) : (this.identity == null || (ListenerUtil.mutListener.listen(25217) ? (this.identity.length() >= 0) : (ListenerUtil.mutListener.listen(25216) ? (this.identity.length() <= 0) : (ListenerUtil.mutListener.listen(25215) ? (this.identity.length() > 0) : (ListenerUtil.mutListener.listen(25214) ? (this.identity.length() < 0) : (ListenerUtil.mutListener.listen(25213) ? (this.identity.length() != 0) : (this.identity.length() == 0)))))))) || this.identity.equals(this.userService.getIdentity())))) {
                    if (!ListenerUtil.mutListener.listen(25220)) {
                        logger.error("no identity found");
                    }
                    if (!ListenerUtil.mutListener.listen(25221)) {
                        activity.finish();
                    }
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(25223)) {
                this.contactModel = this.contactService.getByIdentity(this.identity);
            }
            if (!ListenerUtil.mutListener.listen(25229)) {
                if (this.contactModel == null) {
                    if (!ListenerUtil.mutListener.listen(25224)) {
                        Toast.makeText(getContext(), getString(R.string.contact_not_found) + ": " + this.identity, Toast.LENGTH_LONG).show();
                    }
                    Intent homeIntent = new Intent(activity, HomeActivity.class);
                    if (!ListenerUtil.mutListener.listen(25225)) {
                        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    }
                    if (!ListenerUtil.mutListener.listen(25226)) {
                        startActivity(homeIntent);
                    }
                    if (!ListenerUtil.mutListener.listen(25227)) {
                        activity.overridePendingTransition(0, 0);
                    }
                    if (!ListenerUtil.mutListener.listen(25228)) {
                        activity.finish();
                    }
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(25230)) {
                this.messageReceiver = this.contactService.createReceiver(this.contactModel);
            }
            if (!ListenerUtil.mutListener.listen(25231)) {
                this.typingIndicatorTextWatcher = new TypingIndicatorTextWatcher(this.userService, contactModel);
            }
            conversationUid = ConversationUtil.getIdentityConversationUid(this.identity);
        }
        if (!ListenerUtil.mutListener.listen(25264)) {
            if (this.messageReceiver == null) {
                if (!ListenerUtil.mutListener.listen(25263)) {
                    logger.error("invalid receiver", activity, new Runnable() {

                        @Override
                        public void run() {
                            if (!ListenerUtil.mutListener.listen(25262)) {
                                activity.finish();
                            }
                        }
                    });
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(25266)) {
            // hide chat from view and prevent screenshots - may not work on some devices
            if (this.hiddenChatsListService.has(this.messageReceiver.getUniqueIdString())) {
                try {
                    if (!ListenerUtil.mutListener.listen(25265)) {
                        getActivity().getWindow().addFlags(FLAG_SECURE);
                    }
                } catch (Exception ignored) {
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(25267)) {
            // set wallpaper based on message receiver
            this.setBackgroundWallpaper();
        }
        if (!ListenerUtil.mutListener.listen(25269)) {
            this.initConversationList((ListenerUtil.mutListener.listen(25268) ? (intent.hasExtra(EXTRA_API_MESSAGE_ID) || intent.hasExtra(EXTRA_SEARCH_QUERY)) : (intent.hasExtra(EXTRA_API_MESSAGE_ID) && intent.hasExtra(EXTRA_SEARCH_QUERY))) ? (Runnable) () -> {
                String apiMessageId = intent.getStringExtra(EXTRA_API_MESSAGE_ID);
                String searchQuery = intent.getStringExtra(EXTRA_SEARCH_QUERY);
                AbstractMessageModel targetMessageModel = messageService.getMessageModelByApiMessageId(apiMessageId, messageReceiver.getType());
                if (targetMessageModel != null && !TestUtil.empty(apiMessageId) && !TestUtil.empty(searchQuery)) {
                    String identity;
                    if (targetMessageModel instanceof GroupMessageModel) {
                        identity = targetMessageModel.isOutbox() ? contactService.getMe().getIdentity() : targetMessageModel.getIdentity();
                    } else {
                        identity = targetMessageModel.getIdentity();
                    }
                    QuoteUtil.QuoteContent quoteContent = QuoteUtil.QuoteContent.createV2(identity, searchQuery, searchQuery, apiMessageId, targetMessageModel, messageReceiver.getType(), null, null);
                    if (composeMessageAdapter != null) {
                        ComposeMessageAdapter.ConversationListFilter filter = (ComposeMessageAdapter.ConversationListFilter) composeMessageAdapter.getQuoteFilter(quoteContent);
                        searchV2Quote(apiMessageId, filter);
                        intent.removeExtra(EXTRA_API_MESSAGE_ID);
                    }
                } else {
                    Toast.makeText(ThreemaApplication.getAppContext(), R.string.message_not_found, Toast.LENGTH_SHORT).show();
                }
            } : null);
        }
        // any previously handled intent
        long newTimestamp = 0L;
        try {
            if (!ListenerUtil.mutListener.listen(25271)) {
                newTimestamp = intent.getLongExtra(ThreemaApplication.INTENT_DATA_TIMESTAMP, 0L);
            }
            if (!ListenerUtil.mutListener.listen(25283)) {
                if ((ListenerUtil.mutListener.listen(25282) ? ((ListenerUtil.mutListener.listen(25276) ? (newTimestamp >= 0L) : (ListenerUtil.mutListener.listen(25275) ? (newTimestamp <= 0L) : (ListenerUtil.mutListener.listen(25274) ? (newTimestamp > 0L) : (ListenerUtil.mutListener.listen(25273) ? (newTimestamp < 0L) : (ListenerUtil.mutListener.listen(25272) ? (newTimestamp == 0L) : (newTimestamp != 0L)))))) || (ListenerUtil.mutListener.listen(25281) ? (newTimestamp >= this.intentTimestamp) : (ListenerUtil.mutListener.listen(25280) ? (newTimestamp > this.intentTimestamp) : (ListenerUtil.mutListener.listen(25279) ? (newTimestamp < this.intentTimestamp) : (ListenerUtil.mutListener.listen(25278) ? (newTimestamp != this.intentTimestamp) : (ListenerUtil.mutListener.listen(25277) ? (newTimestamp == this.intentTimestamp) : (newTimestamp <= this.intentTimestamp))))))) : ((ListenerUtil.mutListener.listen(25276) ? (newTimestamp >= 0L) : (ListenerUtil.mutListener.listen(25275) ? (newTimestamp <= 0L) : (ListenerUtil.mutListener.listen(25274) ? (newTimestamp > 0L) : (ListenerUtil.mutListener.listen(25273) ? (newTimestamp < 0L) : (ListenerUtil.mutListener.listen(25272) ? (newTimestamp == 0L) : (newTimestamp != 0L)))))) && (ListenerUtil.mutListener.listen(25281) ? (newTimestamp >= this.intentTimestamp) : (ListenerUtil.mutListener.listen(25280) ? (newTimestamp > this.intentTimestamp) : (ListenerUtil.mutListener.listen(25279) ? (newTimestamp < this.intentTimestamp) : (ListenerUtil.mutListener.listen(25278) ? (newTimestamp != this.intentTimestamp) : (ListenerUtil.mutListener.listen(25277) ? (newTimestamp == this.intentTimestamp) : (newTimestamp <= this.intentTimestamp))))))))) {
                    return;
                }
            }
        } finally {
            if (!ListenerUtil.mutListener.listen(25270)) {
                this.intentTimestamp = newTimestamp;
            }
        }
        if (!ListenerUtil.mutListener.listen(25284)) {
            this.messageText.setText("");
        }
        if (!ListenerUtil.mutListener.listen(25285)) {
            this.messageText.setMessageReceiver(this.messageReceiver);
        }
        if (!ListenerUtil.mutListener.listen(25286)) {
            this.openBallotNoticeView.setMessageReceiver(this.messageReceiver);
        }
        if (!ListenerUtil.mutListener.listen(25287)) {
            this.openBallotNoticeView.setVisibilityListener(this);
        }
        if (!ListenerUtil.mutListener.listen(25288)) {
            // restore draft before setting predefined text
            restoreMessageDraft();
        }
        String defaultText = intent.getStringExtra(ThreemaApplication.INTENT_DATA_TEXT);
        if (!ListenerUtil.mutListener.listen(25291)) {
            if (!TestUtil.empty(defaultText)) {
                if (!ListenerUtil.mutListener.listen(25289)) {
                    this.messageText.setText(null);
                }
                if (!ListenerUtil.mutListener.listen(25290)) {
                    this.messageText.append(defaultText);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(25292)) {
            updateSendButton(this.messageText.getText());
        }
        if (!ListenerUtil.mutListener.listen(25293)) {
            updateCameraButton();
        }
        boolean editFocus = intent.getBooleanExtra(ThreemaApplication.INTENT_DATA_EDITFOCUS, false);
        if (!ListenerUtil.mutListener.listen(25302)) {
            if ((ListenerUtil.mutListener.listen(25299) ? (editFocus && (ListenerUtil.mutListener.listen(25298) ? (this.unreadCount >= 0) : (ListenerUtil.mutListener.listen(25297) ? (this.unreadCount > 0) : (ListenerUtil.mutListener.listen(25296) ? (this.unreadCount < 0) : (ListenerUtil.mutListener.listen(25295) ? (this.unreadCount != 0) : (ListenerUtil.mutListener.listen(25294) ? (this.unreadCount == 0) : (this.unreadCount <= 0))))))) : (editFocus || (ListenerUtil.mutListener.listen(25298) ? (this.unreadCount >= 0) : (ListenerUtil.mutListener.listen(25297) ? (this.unreadCount > 0) : (ListenerUtil.mutListener.listen(25296) ? (this.unreadCount < 0) : (ListenerUtil.mutListener.listen(25295) ? (this.unreadCount != 0) : (ListenerUtil.mutListener.listen(25294) ? (this.unreadCount == 0) : (this.unreadCount <= 0))))))))) {
                if (!ListenerUtil.mutListener.listen(25300)) {
                    messageText.setSelected(true);
                }
                if (!ListenerUtil.mutListener.listen(25301)) {
                    messageText.requestFocus();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(25303)) {
            this.notificationService.setVisibleReceiver(this.messageReceiver);
        }
        if (!ListenerUtil.mutListener.listen(25306)) {
            if ((ListenerUtil.mutListener.listen(25304) ? (!this.isGroupChat || !this.isDistributionListChat) : (!this.isGroupChat && !this.isDistributionListChat))) {
                if (!ListenerUtil.mutListener.listen(25305)) {
                    this.messageText.addTextChangedListener(this.typingIndicatorTextWatcher);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(25307)) {
            ListenerManager.chatListener.handle(listener -> listener.onChatOpened(conversationUid));
        }
    }

    private boolean validateSendingPermission() {
        return (ListenerUtil.mutListener.listen(25309) ? (this.messageReceiver != null || this.messageReceiver.validateSendingPermission(new MessageReceiver.OnSendingPermissionDenied() {

            @Override
            public void denied(final int errorResId) {
                if (!ListenerUtil.mutListener.listen(25308)) {
                    RuntimeUtil.runOnUiThread(() -> SingleToast.getInstance().showLongText(getString(errorResId)));
                }
            }
        })) : (this.messageReceiver != null && this.messageReceiver.validateSendingPermission(new MessageReceiver.OnSendingPermissionDenied() {

            @Override
            public void denied(final int errorResId) {
                if (!ListenerUtil.mutListener.listen(25308)) {
                    RuntimeUtil.runOnUiThread(() -> SingleToast.getInstance().showLongText(getString(errorResId)));
                }
            }
        })));
    }

    private void deleteSelectedMessages() {
        int deleteableMessagesCount = 0;
        if (!ListenerUtil.mutListener.listen(25388)) {
            if ((ListenerUtil.mutListener.listen(25315) ? (selectedMessages != null || (ListenerUtil.mutListener.listen(25314) ? (selectedMessages.size() >= 0) : (ListenerUtil.mutListener.listen(25313) ? (selectedMessages.size() <= 0) : (ListenerUtil.mutListener.listen(25312) ? (selectedMessages.size() < 0) : (ListenerUtil.mutListener.listen(25311) ? (selectedMessages.size() != 0) : (ListenerUtil.mutListener.listen(25310) ? (selectedMessages.size() == 0) : (selectedMessages.size() > 0))))))) : (selectedMessages != null && (ListenerUtil.mutListener.listen(25314) ? (selectedMessages.size() >= 0) : (ListenerUtil.mutListener.listen(25313) ? (selectedMessages.size() <= 0) : (ListenerUtil.mutListener.listen(25312) ? (selectedMessages.size() < 0) : (ListenerUtil.mutListener.listen(25311) ? (selectedMessages.size() != 0) : (ListenerUtil.mutListener.listen(25310) ? (selectedMessages.size() == 0) : (selectedMessages.size() > 0))))))))) {
                if (!ListenerUtil.mutListener.listen(25322)) {
                    // sort highest first for removal
                    Collections.sort(selectedMessages, new Comparator<AbstractMessageModel>() {

                        @Override
                        public int compare(AbstractMessageModel lhs, AbstractMessageModel rhs) {
                            return (ListenerUtil.mutListener.listen(25321) ? (rhs.getId() % lhs.getId()) : (ListenerUtil.mutListener.listen(25320) ? (rhs.getId() / lhs.getId()) : (ListenerUtil.mutListener.listen(25319) ? (rhs.getId() * lhs.getId()) : (ListenerUtil.mutListener.listen(25318) ? (rhs.getId() + lhs.getId()) : (rhs.getId() - lhs.getId())))));
                        }
                    });
                }
                synchronized (deleteableMessages) {
                    if (!ListenerUtil.mutListener.listen(25363)) {
                        {
                            long _loopCounter164 = 0;
                            for (AbstractMessageModel messageModel : selectedMessages) {
                                ListenerUtil.loopListener.listen("_loopCounter164", ++_loopCounter164);
                                if (!ListenerUtil.mutListener.listen(25362)) {
                                    if (messageModel != null) {
                                        // remove from adapter but not from database
                                        int position = composeMessageAdapter.getPosition(messageModel);
                                        AbstractMessageModel previousMessage = null;
                                        if (!ListenerUtil.mutListener.listen(25333)) {
                                            if ((ListenerUtil.mutListener.listen(25327) ? (position >= 0) : (ListenerUtil.mutListener.listen(25326) ? (position <= 0) : (ListenerUtil.mutListener.listen(25325) ? (position < 0) : (ListenerUtil.mutListener.listen(25324) ? (position != 0) : (ListenerUtil.mutListener.listen(25323) ? (position == 0) : (position > 0))))))) {
                                                if (!ListenerUtil.mutListener.listen(25332)) {
                                                    previousMessage = composeMessageAdapter.getItem((ListenerUtil.mutListener.listen(25331) ? (position % 1) : (ListenerUtil.mutListener.listen(25330) ? (position / 1) : (ListenerUtil.mutListener.listen(25329) ? (position * 1) : (ListenerUtil.mutListener.listen(25328) ? (position + 1) : (position - 1))))));
                                                }
                                            }
                                        }
                                        if (!ListenerUtil.mutListener.listen(25358)) {
                                            if ((ListenerUtil.mutListener.listen(25334) ? (previousMessage != null || previousMessage instanceof DateSeparatorMessageModel) : (previousMessage != null && previousMessage instanceof DateSeparatorMessageModel))) {
                                                AbstractMessageModel nextMessage = null;
                                                if (!ListenerUtil.mutListener.listen(25349)) {
                                                    if ((ListenerUtil.mutListener.listen(25343) ? (position >= ((ListenerUtil.mutListener.listen(25338) ? (composeMessageAdapter.getCount() % 1) : (ListenerUtil.mutListener.listen(25337) ? (composeMessageAdapter.getCount() / 1) : (ListenerUtil.mutListener.listen(25336) ? (composeMessageAdapter.getCount() * 1) : (ListenerUtil.mutListener.listen(25335) ? (composeMessageAdapter.getCount() + 1) : (composeMessageAdapter.getCount() - 1))))))) : (ListenerUtil.mutListener.listen(25342) ? (position <= ((ListenerUtil.mutListener.listen(25338) ? (composeMessageAdapter.getCount() % 1) : (ListenerUtil.mutListener.listen(25337) ? (composeMessageAdapter.getCount() / 1) : (ListenerUtil.mutListener.listen(25336) ? (composeMessageAdapter.getCount() * 1) : (ListenerUtil.mutListener.listen(25335) ? (composeMessageAdapter.getCount() + 1) : (composeMessageAdapter.getCount() - 1))))))) : (ListenerUtil.mutListener.listen(25341) ? (position > ((ListenerUtil.mutListener.listen(25338) ? (composeMessageAdapter.getCount() % 1) : (ListenerUtil.mutListener.listen(25337) ? (composeMessageAdapter.getCount() / 1) : (ListenerUtil.mutListener.listen(25336) ? (composeMessageAdapter.getCount() * 1) : (ListenerUtil.mutListener.listen(25335) ? (composeMessageAdapter.getCount() + 1) : (composeMessageAdapter.getCount() - 1))))))) : (ListenerUtil.mutListener.listen(25340) ? (position != ((ListenerUtil.mutListener.listen(25338) ? (composeMessageAdapter.getCount() % 1) : (ListenerUtil.mutListener.listen(25337) ? (composeMessageAdapter.getCount() / 1) : (ListenerUtil.mutListener.listen(25336) ? (composeMessageAdapter.getCount() * 1) : (ListenerUtil.mutListener.listen(25335) ? (composeMessageAdapter.getCount() + 1) : (composeMessageAdapter.getCount() - 1))))))) : (ListenerUtil.mutListener.listen(25339) ? (position == ((ListenerUtil.mutListener.listen(25338) ? (composeMessageAdapter.getCount() % 1) : (ListenerUtil.mutListener.listen(25337) ? (composeMessageAdapter.getCount() / 1) : (ListenerUtil.mutListener.listen(25336) ? (composeMessageAdapter.getCount() * 1) : (ListenerUtil.mutListener.listen(25335) ? (composeMessageAdapter.getCount() + 1) : (composeMessageAdapter.getCount() - 1))))))) : (position < ((ListenerUtil.mutListener.listen(25338) ? (composeMessageAdapter.getCount() % 1) : (ListenerUtil.mutListener.listen(25337) ? (composeMessageAdapter.getCount() / 1) : (ListenerUtil.mutListener.listen(25336) ? (composeMessageAdapter.getCount() * 1) : (ListenerUtil.mutListener.listen(25335) ? (composeMessageAdapter.getCount() + 1) : (composeMessageAdapter.getCount() - 1))))))))))))) {
                                                        if (!ListenerUtil.mutListener.listen(25348)) {
                                                            nextMessage = composeMessageAdapter.getItem((ListenerUtil.mutListener.listen(25347) ? (position % 1) : (ListenerUtil.mutListener.listen(25346) ? (position / 1) : (ListenerUtil.mutListener.listen(25345) ? (position * 1) : (ListenerUtil.mutListener.listen(25344) ? (position - 1) : (position + 1))))));
                                                        }
                                                    }
                                                }
                                                if (!ListenerUtil.mutListener.listen(25357)) {
                                                    if ((ListenerUtil.mutListener.listen(25350) ? (nextMessage == null && !dayFormatter.format(messageModel.getCreatedAt()).equals(dayFormatter.format(nextMessage.getCreatedAt()))) : (nextMessage == null || !dayFormatter.format(messageModel.getCreatedAt()).equals(dayFormatter.format(nextMessage.getCreatedAt()))))) {
                                                        if (!ListenerUtil.mutListener.listen(25355)) {
                                                            deleteableMessages.add(new Pair<>(previousMessage, (ListenerUtil.mutListener.listen(25354) ? (position % 1) : (ListenerUtil.mutListener.listen(25353) ? (position / 1) : (ListenerUtil.mutListener.listen(25352) ? (position * 1) : (ListenerUtil.mutListener.listen(25351) ? (position + 1) : (position - 1)))))));
                                                        }
                                                        if (!ListenerUtil.mutListener.listen(25356)) {
                                                            composeMessageAdapter.remove(previousMessage);
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        if (!ListenerUtil.mutListener.listen(25359)) {
                                            deleteableMessages.add(new Pair<>(messageModel, position));
                                        }
                                        if (!ListenerUtil.mutListener.listen(25360)) {
                                            deleteableMessagesCount++;
                                        }
                                        if (!ListenerUtil.mutListener.listen(25361)) {
                                            composeMessageAdapter.remove(messageModel);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(25364)) {
                        composeMessageAdapter.notifyDataSetChanged();
                    }
                    if (!ListenerUtil.mutListener.listen(25369)) {
                        // sort lowest first for insertion
                        Collections.sort(deleteableMessages, new Comparator<Pair<AbstractMessageModel, Integer>>() {

                            @Override
                            public int compare(Pair<AbstractMessageModel, Integer> lhs, Pair<AbstractMessageModel, Integer> rhs) {
                                return (ListenerUtil.mutListener.listen(25368) ? (lhs.second % rhs.second) : (ListenerUtil.mutListener.listen(25367) ? (lhs.second / rhs.second) : (ListenerUtil.mutListener.listen(25366) ? (lhs.second * rhs.second) : (ListenerUtil.mutListener.listen(25365) ? (lhs.second + rhs.second) : (lhs.second - rhs.second)))));
                            }
                        });
                    }
                }
                if (!ListenerUtil.mutListener.listen(25370)) {
                    selectedMessages.clear();
                }
                if (!ListenerUtil.mutListener.listen(25372)) {
                    if (actionMode != null) {
                        if (!ListenerUtil.mutListener.listen(25371)) {
                            actionMode.finish();
                        }
                    }
                }
                try {
                    if (!ListenerUtil.mutListener.listen(25379)) {
                        deleteSnackbar = Snackbar.make(coordinatorLayout, deleteableMessagesCount + " " + getString(R.string.message_deleted), (ListenerUtil.mutListener.listen(25378) ? (7 % (int) DateUtils.SECOND_IN_MILLIS) : (ListenerUtil.mutListener.listen(25377) ? (7 / (int) DateUtils.SECOND_IN_MILLIS) : (ListenerUtil.mutListener.listen(25376) ? (7 - (int) DateUtils.SECOND_IN_MILLIS) : (ListenerUtil.mutListener.listen(25375) ? (7 + (int) DateUtils.SECOND_IN_MILLIS) : (7 * (int) DateUtils.SECOND_IN_MILLIS))))));
                    }
                    if (!ListenerUtil.mutListener.listen(25380)) {
                        deleteSnackbar.setAction(R.string.message_delete_undo, v -> RuntimeUtil.runOnUiThread(this::undoDeleteMessages));
                    }
                    if (!ListenerUtil.mutListener.listen(25386)) {
                        deleteSnackbar.setCallback(new Snackbar.Callback() {

                            @Override
                            public void onDismissed(Snackbar snackbar, int event) {
                                if (!ListenerUtil.mutListener.listen(25381)) {
                                    super.onDismissed(snackbar, event);
                                }
                                if (!ListenerUtil.mutListener.listen(25385)) {
                                    if ((ListenerUtil.mutListener.listen(25383) ? ((ListenerUtil.mutListener.listen(25382) ? (event != DISMISS_EVENT_ACTION || event != DISMISS_EVENT_CONSECUTIVE) : (event != DISMISS_EVENT_ACTION && event != DISMISS_EVENT_CONSECUTIVE)) || event != DISMISS_EVENT_MANUAL) : ((ListenerUtil.mutListener.listen(25382) ? (event != DISMISS_EVENT_ACTION || event != DISMISS_EVENT_CONSECUTIVE) : (event != DISMISS_EVENT_ACTION && event != DISMISS_EVENT_CONSECUTIVE)) && event != DISMISS_EVENT_MANUAL))) {
                                        if (!ListenerUtil.mutListener.listen(25384)) {
                                            RuntimeUtil.runOnUiThread(() -> deleteDeleteableMessages());
                                        }
                                    }
                                }
                            }
                        });
                    }
                    if (!ListenerUtil.mutListener.listen(25387)) {
                        deleteSnackbar.show();
                    }
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(25373)) {
                        logger.debug("https://issuetracker.google.com/issues/63793040");
                    }
                    if (!ListenerUtil.mutListener.listen(25374)) {
                        RuntimeUtil.runOnUiThread(this::undoDeleteMessages);
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(25317)) {
                    if (actionMode != null) {
                        if (!ListenerUtil.mutListener.listen(25316)) {
                            actionMode.finish();
                        }
                    }
                }
            }
        }
    }

    private void undoDeleteMessages() {
        synchronized (deleteableMessages) {
            if (!ListenerUtil.mutListener.listen(25390)) {
                {
                    long _loopCounter165 = 0;
                    for (Pair<AbstractMessageModel, Integer> m : deleteableMessages) {
                        ListenerUtil.loopListener.listen("_loopCounter165", ++_loopCounter165);
                        if (!ListenerUtil.mutListener.listen(25389)) {
                            composeMessageAdapter.insert(m.first, m.second);
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(25391)) {
                deleteableMessages.clear();
            }
        }
        if (!ListenerUtil.mutListener.listen(25392)) {
            composeMessageAdapter.notifyDataSetChanged();
        }
    }

    private synchronized void deleteDeleteableMessages() {
        if (!ListenerUtil.mutListener.listen(25412)) {
            if ((ListenerUtil.mutListener.listen(25397) ? (deleteableMessages.size() >= 0) : (ListenerUtil.mutListener.listen(25396) ? (deleteableMessages.size() <= 0) : (ListenerUtil.mutListener.listen(25395) ? (deleteableMessages.size() < 0) : (ListenerUtil.mutListener.listen(25394) ? (deleteableMessages.size() != 0) : (ListenerUtil.mutListener.listen(25393) ? (deleteableMessages.size() == 0) : (deleteableMessages.size() > 0))))))) {
                synchronized (deleteableMessages) {
                    if (!ListenerUtil.mutListener.listen(25400)) {
                        {
                            long _loopCounter166 = 0;
                            for (Pair<AbstractMessageModel, Integer> m : deleteableMessages) {
                                ListenerUtil.loopListener.listen("_loopCounter166", ++_loopCounter166);
                                if (!ListenerUtil.mutListener.listen(25399)) {
                                    if (m != null) {
                                        if (!ListenerUtil.mutListener.listen(25398)) {
                                            messageService.remove(m.first);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(25401)) {
                        deleteableMessages.clear();
                    }
                    if (!ListenerUtil.mutListener.listen(25411)) {
                        if (messageReceiver != null) {
                            if (!ListenerUtil.mutListener.listen(25410)) {
                                if ((ListenerUtil.mutListener.listen(25407) ? ((ListenerUtil.mutListener.listen(25406) ? (messageReceiver.getMessagesCount() >= 0) : (ListenerUtil.mutListener.listen(25405) ? (messageReceiver.getMessagesCount() > 0) : (ListenerUtil.mutListener.listen(25404) ? (messageReceiver.getMessagesCount() < 0) : (ListenerUtil.mutListener.listen(25403) ? (messageReceiver.getMessagesCount() != 0) : (ListenerUtil.mutListener.listen(25402) ? (messageReceiver.getMessagesCount() == 0) : (messageReceiver.getMessagesCount() <= 0)))))) || messageReceiver instanceof ContactMessageReceiver) : ((ListenerUtil.mutListener.listen(25406) ? (messageReceiver.getMessagesCount() >= 0) : (ListenerUtil.mutListener.listen(25405) ? (messageReceiver.getMessagesCount() > 0) : (ListenerUtil.mutListener.listen(25404) ? (messageReceiver.getMessagesCount() < 0) : (ListenerUtil.mutListener.listen(25403) ? (messageReceiver.getMessagesCount() != 0) : (ListenerUtil.mutListener.listen(25402) ? (messageReceiver.getMessagesCount() == 0) : (messageReceiver.getMessagesCount() <= 0)))))) && messageReceiver instanceof ContactMessageReceiver))) {
                                    if (!ListenerUtil.mutListener.listen(25409)) {
                                        conversationService.clear(messageReceiver);
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(25408)) {
                                        conversationService.refresh(messageReceiver);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @UiThread
    private void contactTypingStateChanged(boolean isTyping) {
        if (!ListenerUtil.mutListener.listen(25420)) {
            RuntimeUtil.runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    if (!ListenerUtil.mutListener.listen(25419)) {
                        if (isTypingView != null) {
                            if (!ListenerUtil.mutListener.listen(25413)) {
                                logger.debug("is typing " + isTyping + " footer view count " + convListView.getFooterViewsCount());
                            }
                            if (!ListenerUtil.mutListener.listen(25418)) {
                                if (isTyping) {
                                    if (!ListenerUtil.mutListener.listen(25417)) {
                                        // remove if the the another footer element added
                                        if (convListView.getFooterViewsCount() == 0) {
                                            if (!ListenerUtil.mutListener.listen(25415)) {
                                                isTypingView.setVisibility(View.VISIBLE);
                                            }
                                            if (!ListenerUtil.mutListener.listen(25416)) {
                                                convListView.addFooterView(isTypingView, null, false);
                                            }
                                        }
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(25414)) {
                                        removeIsTypingFooter();
                                    }
                                }
                            }
                        }
                    }
                }
            });
        }
    }

    private void removeIsTypingFooter() {
        if (!ListenerUtil.mutListener.listen(25430)) {
            if (isTypingView != null) {
                if (!ListenerUtil.mutListener.listen(25421)) {
                    isTypingView.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(25429)) {
                    if ((ListenerUtil.mutListener.listen(25427) ? (convListView != null || (ListenerUtil.mutListener.listen(25426) ? (convListView.getFooterViewsCount() >= 0) : (ListenerUtil.mutListener.listen(25425) ? (convListView.getFooterViewsCount() <= 0) : (ListenerUtil.mutListener.listen(25424) ? (convListView.getFooterViewsCount() < 0) : (ListenerUtil.mutListener.listen(25423) ? (convListView.getFooterViewsCount() != 0) : (ListenerUtil.mutListener.listen(25422) ? (convListView.getFooterViewsCount() == 0) : (convListView.getFooterViewsCount() > 0))))))) : (convListView != null && (ListenerUtil.mutListener.listen(25426) ? (convListView.getFooterViewsCount() >= 0) : (ListenerUtil.mutListener.listen(25425) ? (convListView.getFooterViewsCount() <= 0) : (ListenerUtil.mutListener.listen(25424) ? (convListView.getFooterViewsCount() < 0) : (ListenerUtil.mutListener.listen(25423) ? (convListView.getFooterViewsCount() != 0) : (ListenerUtil.mutListener.listen(25422) ? (convListView.getFooterViewsCount() == 0) : (convListView.getFooterViewsCount() > 0))))))))) {
                        if (!ListenerUtil.mutListener.listen(25428)) {
                            convListView.removeFooterView(isTypingView);
                        }
                    }
                }
            }
        }
    }

    @UiThread
    private boolean addMessageToList(AbstractMessageModel message) {
        if (!ListenerUtil.mutListener.listen(25433)) {
            if ((ListenerUtil.mutListener.listen(25432) ? ((ListenerUtil.mutListener.listen(25431) ? (message == null && this.messageReceiver == null) : (message == null || this.messageReceiver == null)) && this.composeMessageAdapter == null) : ((ListenerUtil.mutListener.listen(25431) ? (message == null && this.messageReceiver == null) : (message == null || this.messageReceiver == null)) || this.composeMessageAdapter == null))) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(25435)) {
            // check if the message already added
            if ((ListenerUtil.mutListener.listen(25434) ? (this.listInitializedAt != null || message.getCreatedAt().before(this.listInitializedAt)) : (this.listInitializedAt != null && message.getCreatedAt().before(this.listInitializedAt)))) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(25436)) {
            if (!this.messageReceiver.isMessageBelongsToMe(message)) {
                // do nothing, not my thread
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(25437)) {
            logger.debug("addMessageToList: started");
        }
        if (!ListenerUtil.mutListener.listen(25438)) {
            this.composeMessageAdapter.removeFirstUnreadPosition();
        }
        // if previous message is from another date, add a date separator
        synchronized (this.messageValues) {
            int size = this.messageValues.size();
            Date date = new Date();
            Date createdAt = (ListenerUtil.mutListener.listen(25443) ? (size >= 0) : (ListenerUtil.mutListener.listen(25442) ? (size <= 0) : (ListenerUtil.mutListener.listen(25441) ? (size < 0) : (ListenerUtil.mutListener.listen(25440) ? (size != 0) : (ListenerUtil.mutListener.listen(25439) ? (size == 0) : (size > 0)))))) ? this.messageValues.get((ListenerUtil.mutListener.listen(25447) ? (size % 1) : (ListenerUtil.mutListener.listen(25446) ? (size / 1) : (ListenerUtil.mutListener.listen(25445) ? (size * 1) : (ListenerUtil.mutListener.listen(25444) ? (size + 1) : (size - 1)))))).getCreatedAt() : new Date(0L);
            if (!ListenerUtil.mutListener.listen(25450)) {
                if (!dayFormatter.format(createdAt).equals(dayFormatter.format(date))) {
                    final DateSeparatorMessageModel dateSeparatorMessageModel = new DateSeparatorMessageModel();
                    if (!ListenerUtil.mutListener.listen(25448)) {
                        dateSeparatorMessageModel.setCreatedAt(date);
                    }
                    if (!ListenerUtil.mutListener.listen(25449)) {
                        this.messageValues.add(size, dateSeparatorMessageModel);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(25451)) {
            this.composeMessageAdapter.add(message);
        }
        if (!ListenerUtil.mutListener.listen(25454)) {
            if (!this.isPaused) {
                if (!ListenerUtil.mutListener.listen(25453)) {
                    new Thread(new ReadMessagesRoutine(Arrays.asList(message), this.messageService, this.notificationService)).start();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(25452)) {
                    this.unreadMessages.add(message);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(25456)) {
            if (message.isOutbox()) {
                if (!ListenerUtil.mutListener.listen(25455)) {
                    // scroll to bottom on outgoing message
                    scrollList(Integer.MAX_VALUE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(25457)) {
            logger.debug("addMessageToList: finished");
        }
        return true;
    }

    @UiThread
    private void scrollList(final int targetPosition) {
        if (!ListenerUtil.mutListener.listen(25458)) {
            logger.debug("scrollList " + targetPosition);
        }
        if (!ListenerUtil.mutListener.listen(25460)) {
            if (this.listUpdateInProgress) {
                if (!ListenerUtil.mutListener.listen(25459)) {
                    logger.debug("Update in progress");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(25461)) {
            if (this.composeMessageAdapter == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(25462)) {
            this.composeMessageAdapter.notifyDataSetChanged();
        }
        if (!ListenerUtil.mutListener.listen(25511)) {
            this.convListView.post(new Runnable() {

                @Override
                public void run() {
                    int topEntry = convListView.getFirstVisiblePosition();
                    if (!ListenerUtil.mutListener.listen(25510)) {
                        // update only if really necessary
                        if ((ListenerUtil.mutListener.listen(25467) ? (targetPosition >= topEntry) : (ListenerUtil.mutListener.listen(25466) ? (targetPosition <= topEntry) : (ListenerUtil.mutListener.listen(25465) ? (targetPosition > topEntry) : (ListenerUtil.mutListener.listen(25464) ? (targetPosition < topEntry) : (ListenerUtil.mutListener.listen(25463) ? (targetPosition == topEntry) : (targetPosition != topEntry))))))) {
                            if (!ListenerUtil.mutListener.listen(25468)) {
                                listUpdateInProgress = true;
                            }
                            int listEntryCount = convListView.getCount();
                            if (!ListenerUtil.mutListener.listen(25508)) {
                                if ((ListenerUtil.mutListener.listen(25473) ? (topEntry >= targetPosition) : (ListenerUtil.mutListener.listen(25472) ? (topEntry <= targetPosition) : (ListenerUtil.mutListener.listen(25471) ? (topEntry < targetPosition) : (ListenerUtil.mutListener.listen(25470) ? (topEntry != targetPosition) : (ListenerUtil.mutListener.listen(25469) ? (topEntry == targetPosition) : (topEntry > targetPosition))))))) {
                                    // scroll up
                                    int startPosition = (ListenerUtil.mutListener.listen(25499) ? (targetPosition % SMOOTHSCROLL_THRESHOLD) : (ListenerUtil.mutListener.listen(25498) ? (targetPosition / SMOOTHSCROLL_THRESHOLD) : (ListenerUtil.mutListener.listen(25497) ? (targetPosition * SMOOTHSCROLL_THRESHOLD) : (ListenerUtil.mutListener.listen(25496) ? (targetPosition - SMOOTHSCROLL_THRESHOLD) : (targetPosition + SMOOTHSCROLL_THRESHOLD)))));
                                    if (!ListenerUtil.mutListener.listen(25507)) {
                                        if ((ListenerUtil.mutListener.listen(25504) ? (startPosition >= listEntryCount) : (ListenerUtil.mutListener.listen(25503) ? (startPosition <= listEntryCount) : (ListenerUtil.mutListener.listen(25502) ? (startPosition > listEntryCount) : (ListenerUtil.mutListener.listen(25501) ? (startPosition != listEntryCount) : (ListenerUtil.mutListener.listen(25500) ? (startPosition == listEntryCount) : (startPosition < listEntryCount))))))) {
                                            if (!ListenerUtil.mutListener.listen(25506)) {
                                                convListView.setSelection(targetPosition);
                                            }
                                        } else {
                                            if (!ListenerUtil.mutListener.listen(25505)) {
                                                convListView.smoothScrollToPosition(targetPosition);
                                            }
                                        }
                                    }
                                } else {
                                    // scroll down
                                    int startPosition = (ListenerUtil.mutListener.listen(25477) ? (listEntryCount % SMOOTHSCROLL_THRESHOLD) : (ListenerUtil.mutListener.listen(25476) ? (listEntryCount / SMOOTHSCROLL_THRESHOLD) : (ListenerUtil.mutListener.listen(25475) ? (listEntryCount * SMOOTHSCROLL_THRESHOLD) : (ListenerUtil.mutListener.listen(25474) ? (listEntryCount + SMOOTHSCROLL_THRESHOLD) : (listEntryCount - SMOOTHSCROLL_THRESHOLD)))));
                                    if (!ListenerUtil.mutListener.listen(25495)) {
                                        if ((ListenerUtil.mutListener.listen(25492) ? ((ListenerUtil.mutListener.listen(25486) ? ((ListenerUtil.mutListener.listen(25481) ? (listEntryCount % convListView.getLastVisiblePosition()) : (ListenerUtil.mutListener.listen(25480) ? (listEntryCount / convListView.getLastVisiblePosition()) : (ListenerUtil.mutListener.listen(25479) ? (listEntryCount * convListView.getLastVisiblePosition()) : (ListenerUtil.mutListener.listen(25478) ? (listEntryCount + convListView.getLastVisiblePosition()) : (listEntryCount - convListView.getLastVisiblePosition()))))) >= SMOOTHSCROLL_THRESHOLD) : (ListenerUtil.mutListener.listen(25485) ? ((ListenerUtil.mutListener.listen(25481) ? (listEntryCount % convListView.getLastVisiblePosition()) : (ListenerUtil.mutListener.listen(25480) ? (listEntryCount / convListView.getLastVisiblePosition()) : (ListenerUtil.mutListener.listen(25479) ? (listEntryCount * convListView.getLastVisiblePosition()) : (ListenerUtil.mutListener.listen(25478) ? (listEntryCount + convListView.getLastVisiblePosition()) : (listEntryCount - convListView.getLastVisiblePosition()))))) <= SMOOTHSCROLL_THRESHOLD) : (ListenerUtil.mutListener.listen(25484) ? ((ListenerUtil.mutListener.listen(25481) ? (listEntryCount % convListView.getLastVisiblePosition()) : (ListenerUtil.mutListener.listen(25480) ? (listEntryCount / convListView.getLastVisiblePosition()) : (ListenerUtil.mutListener.listen(25479) ? (listEntryCount * convListView.getLastVisiblePosition()) : (ListenerUtil.mutListener.listen(25478) ? (listEntryCount + convListView.getLastVisiblePosition()) : (listEntryCount - convListView.getLastVisiblePosition()))))) < SMOOTHSCROLL_THRESHOLD) : (ListenerUtil.mutListener.listen(25483) ? ((ListenerUtil.mutListener.listen(25481) ? (listEntryCount % convListView.getLastVisiblePosition()) : (ListenerUtil.mutListener.listen(25480) ? (listEntryCount / convListView.getLastVisiblePosition()) : (ListenerUtil.mutListener.listen(25479) ? (listEntryCount * convListView.getLastVisiblePosition()) : (ListenerUtil.mutListener.listen(25478) ? (listEntryCount + convListView.getLastVisiblePosition()) : (listEntryCount - convListView.getLastVisiblePosition()))))) != SMOOTHSCROLL_THRESHOLD) : (ListenerUtil.mutListener.listen(25482) ? ((ListenerUtil.mutListener.listen(25481) ? (listEntryCount % convListView.getLastVisiblePosition()) : (ListenerUtil.mutListener.listen(25480) ? (listEntryCount / convListView.getLastVisiblePosition()) : (ListenerUtil.mutListener.listen(25479) ? (listEntryCount * convListView.getLastVisiblePosition()) : (ListenerUtil.mutListener.listen(25478) ? (listEntryCount + convListView.getLastVisiblePosition()) : (listEntryCount - convListView.getLastVisiblePosition()))))) == SMOOTHSCROLL_THRESHOLD) : ((ListenerUtil.mutListener.listen(25481) ? (listEntryCount % convListView.getLastVisiblePosition()) : (ListenerUtil.mutListener.listen(25480) ? (listEntryCount / convListView.getLastVisiblePosition()) : (ListenerUtil.mutListener.listen(25479) ? (listEntryCount * convListView.getLastVisiblePosition()) : (ListenerUtil.mutListener.listen(25478) ? (listEntryCount + convListView.getLastVisiblePosition()) : (listEntryCount - convListView.getLastVisiblePosition()))))) > SMOOTHSCROLL_THRESHOLD)))))) || (ListenerUtil.mutListener.listen(25491) ? (startPosition >= 0) : (ListenerUtil.mutListener.listen(25490) ? (startPosition <= 0) : (ListenerUtil.mutListener.listen(25489) ? (startPosition < 0) : (ListenerUtil.mutListener.listen(25488) ? (startPosition != 0) : (ListenerUtil.mutListener.listen(25487) ? (startPosition == 0) : (startPosition > 0))))))) : ((ListenerUtil.mutListener.listen(25486) ? ((ListenerUtil.mutListener.listen(25481) ? (listEntryCount % convListView.getLastVisiblePosition()) : (ListenerUtil.mutListener.listen(25480) ? (listEntryCount / convListView.getLastVisiblePosition()) : (ListenerUtil.mutListener.listen(25479) ? (listEntryCount * convListView.getLastVisiblePosition()) : (ListenerUtil.mutListener.listen(25478) ? (listEntryCount + convListView.getLastVisiblePosition()) : (listEntryCount - convListView.getLastVisiblePosition()))))) >= SMOOTHSCROLL_THRESHOLD) : (ListenerUtil.mutListener.listen(25485) ? ((ListenerUtil.mutListener.listen(25481) ? (listEntryCount % convListView.getLastVisiblePosition()) : (ListenerUtil.mutListener.listen(25480) ? (listEntryCount / convListView.getLastVisiblePosition()) : (ListenerUtil.mutListener.listen(25479) ? (listEntryCount * convListView.getLastVisiblePosition()) : (ListenerUtil.mutListener.listen(25478) ? (listEntryCount + convListView.getLastVisiblePosition()) : (listEntryCount - convListView.getLastVisiblePosition()))))) <= SMOOTHSCROLL_THRESHOLD) : (ListenerUtil.mutListener.listen(25484) ? ((ListenerUtil.mutListener.listen(25481) ? (listEntryCount % convListView.getLastVisiblePosition()) : (ListenerUtil.mutListener.listen(25480) ? (listEntryCount / convListView.getLastVisiblePosition()) : (ListenerUtil.mutListener.listen(25479) ? (listEntryCount * convListView.getLastVisiblePosition()) : (ListenerUtil.mutListener.listen(25478) ? (listEntryCount + convListView.getLastVisiblePosition()) : (listEntryCount - convListView.getLastVisiblePosition()))))) < SMOOTHSCROLL_THRESHOLD) : (ListenerUtil.mutListener.listen(25483) ? ((ListenerUtil.mutListener.listen(25481) ? (listEntryCount % convListView.getLastVisiblePosition()) : (ListenerUtil.mutListener.listen(25480) ? (listEntryCount / convListView.getLastVisiblePosition()) : (ListenerUtil.mutListener.listen(25479) ? (listEntryCount * convListView.getLastVisiblePosition()) : (ListenerUtil.mutListener.listen(25478) ? (listEntryCount + convListView.getLastVisiblePosition()) : (listEntryCount - convListView.getLastVisiblePosition()))))) != SMOOTHSCROLL_THRESHOLD) : (ListenerUtil.mutListener.listen(25482) ? ((ListenerUtil.mutListener.listen(25481) ? (listEntryCount % convListView.getLastVisiblePosition()) : (ListenerUtil.mutListener.listen(25480) ? (listEntryCount / convListView.getLastVisiblePosition()) : (ListenerUtil.mutListener.listen(25479) ? (listEntryCount * convListView.getLastVisiblePosition()) : (ListenerUtil.mutListener.listen(25478) ? (listEntryCount + convListView.getLastVisiblePosition()) : (listEntryCount - convListView.getLastVisiblePosition()))))) == SMOOTHSCROLL_THRESHOLD) : ((ListenerUtil.mutListener.listen(25481) ? (listEntryCount % convListView.getLastVisiblePosition()) : (ListenerUtil.mutListener.listen(25480) ? (listEntryCount / convListView.getLastVisiblePosition()) : (ListenerUtil.mutListener.listen(25479) ? (listEntryCount * convListView.getLastVisiblePosition()) : (ListenerUtil.mutListener.listen(25478) ? (listEntryCount + convListView.getLastVisiblePosition()) : (listEntryCount - convListView.getLastVisiblePosition()))))) > SMOOTHSCROLL_THRESHOLD)))))) && (ListenerUtil.mutListener.listen(25491) ? (startPosition >= 0) : (ListenerUtil.mutListener.listen(25490) ? (startPosition <= 0) : (ListenerUtil.mutListener.listen(25489) ? (startPosition < 0) : (ListenerUtil.mutListener.listen(25488) ? (startPosition != 0) : (ListenerUtil.mutListener.listen(25487) ? (startPosition == 0) : (startPosition > 0))))))))) {
                                            if (!ListenerUtil.mutListener.listen(25494)) {
                                                convListView.setSelection(targetPosition);
                                            }
                                        } else {
                                            if (!ListenerUtil.mutListener.listen(25493)) {
                                                convListView.smoothScrollToPosition(targetPosition);
                                            }
                                        }
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(25509)) {
                                listUpdateInProgress = false;
                            }
                        }
                    }
                }
            });
        }
    }

    /**
     *  Loading the next records for the listview
     */
    @WorkerThread
    private List<AbstractMessageModel> getNextRecords() {
        List<AbstractMessageModel> messageModels = this.messageService.getMessagesForReceiver(this.messageReceiver, this.nextMessageFilter);
        if (!ListenerUtil.mutListener.listen(25512)) {
            this.valuesLoaded(messageModels);
        }
        return messageModels;
    }

    @WorkerThread
    private List<AbstractMessageModel> getAllRecords() {
        List<AbstractMessageModel> messageModels = this.messageService.getMessagesForReceiver(this.messageReceiver);
        if (!ListenerUtil.mutListener.listen(25513)) {
            this.valuesLoaded(messageModels);
        }
        return messageModels;
    }

    /**
     *  Append records to the list, adding date separators if necessary
     *  Locks list by calling setNotifyOnChange(false) on the adapter to speed up list ctrl
     *  Don't forget to call notifyDataSetChanged() on the adapter in the UI thread after inserting
     *  @param values MessageModels to insert
     *  @param clear Whether previous list entries should be cleared before appending
     *  @param markasread Whether chat should be marked as read
     *  @return Number of items that have been added to the list INCLUDING date separators and other decoration
     */
    @UiThread
    private int insertToList(final List<AbstractMessageModel> values, boolean clear, boolean markasread, boolean notify) {
        int insertedSize = 0;
        if (!ListenerUtil.mutListener.listen(25514)) {
            this.composeMessageAdapter.setNotifyOnChange(false);
        }
        synchronized (this.messageValues) {
            int initialSize = this.messageValues.size();
            Date date = new Date();
            if (!ListenerUtil.mutListener.listen(25527)) {
                if (clear) {
                    if (!ListenerUtil.mutListener.listen(25526)) {
                        this.messageValues.clear();
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(25525)) {
                        // prevent duplicate date separators when adding messages to an existing chat (e.g. after pull-to-refresh)
                        if ((ListenerUtil.mutListener.listen(25519) ? (this.messageValues.size() >= 0) : (ListenerUtil.mutListener.listen(25518) ? (this.messageValues.size() <= 0) : (ListenerUtil.mutListener.listen(25517) ? (this.messageValues.size() < 0) : (ListenerUtil.mutListener.listen(25516) ? (this.messageValues.size() != 0) : (ListenerUtil.mutListener.listen(25515) ? (this.messageValues.size() == 0) : (this.messageValues.size() > 0))))))) {
                            if (!ListenerUtil.mutListener.listen(25521)) {
                                if (this.messageValues.get(0) instanceof DateSeparatorMessageModel) {
                                    if (!ListenerUtil.mutListener.listen(25520)) {
                                        this.messageValues.remove(0);
                                    }
                                }
                            }
                            AbstractMessageModel topmostMessage = this.messageValues.get(0);
                            if (!ListenerUtil.mutListener.listen(25524)) {
                                if (topmostMessage != null) {
                                    Date topmostDate = topmostMessage.getCreatedAt();
                                    if (!ListenerUtil.mutListener.listen(25523)) {
                                        if (topmostDate != null) {
                                            if (!ListenerUtil.mutListener.listen(25522)) {
                                                date = topmostDate;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(25535)) {
                {
                    long _loopCounter167 = 0;
                    for (AbstractMessageModel m : values) {
                        ListenerUtil.loopListener.listen("_loopCounter167", ++_loopCounter167);
                        Date createdAt = m.getCreatedAt();
                        if (!ListenerUtil.mutListener.listen(25533)) {
                            if (createdAt != null) {
                                if (!ListenerUtil.mutListener.listen(25532)) {
                                    if (!dayFormatter.format(createdAt).equals(dayFormatter.format(date))) {
                                        if (!ListenerUtil.mutListener.listen(25530)) {
                                            if (!this.messageValues.isEmpty()) {
                                                final DateSeparatorMessageModel dateSeparatorMessageModel = new DateSeparatorMessageModel();
                                                if (!ListenerUtil.mutListener.listen(25528)) {
                                                    dateSeparatorMessageModel.setCreatedAt(this.messageValues.get(0).getCreatedAt());
                                                }
                                                if (!ListenerUtil.mutListener.listen(25529)) {
                                                    this.messageValues.add(0, dateSeparatorMessageModel);
                                                }
                                            }
                                        }
                                        if (!ListenerUtil.mutListener.listen(25531)) {
                                            date = createdAt;
                                        }
                                    }
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(25534)) {
                            this.messageValues.add(0, m);
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(25539)) {
                if ((ListenerUtil.mutListener.listen(25536) ? (!this.messageValues.isEmpty() || !(this.messageValues.get(0) instanceof DateSeparatorMessageModel)) : (!this.messageValues.isEmpty() && !(this.messageValues.get(0) instanceof DateSeparatorMessageModel)))) {
                    // add topmost date separator
                    final DateSeparatorMessageModel dateSeparatorMessageModel = new DateSeparatorMessageModel();
                    if (!ListenerUtil.mutListener.listen(25537)) {
                        dateSeparatorMessageModel.setCreatedAt(this.messageValues.get(0).getCreatedAt());
                    }
                    if (!ListenerUtil.mutListener.listen(25538)) {
                        this.messageValues.add(0, dateSeparatorMessageModel);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(25540)) {
                this.listInitializedAt = new Date();
            }
            if (!ListenerUtil.mutListener.listen(25545)) {
                insertedSize = (ListenerUtil.mutListener.listen(25544) ? (this.messageValues.size() % initialSize) : (ListenerUtil.mutListener.listen(25543) ? (this.messageValues.size() / initialSize) : (ListenerUtil.mutListener.listen(25542) ? (this.messageValues.size() * initialSize) : (ListenerUtil.mutListener.listen(25541) ? (this.messageValues.size() + initialSize) : (this.messageValues.size() - initialSize)))));
            }
        }
        if (!ListenerUtil.mutListener.listen(25551)) {
            if (clear) {
                if (!ListenerUtil.mutListener.listen(25549)) {
                    composeMessageAdapter.setNotifyOnChange(true);
                }
                if (!ListenerUtil.mutListener.listen(25550)) {
                    composeMessageAdapter.notifyDataSetInvalidated();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(25548)) {
                    if (notify) {
                        if (!ListenerUtil.mutListener.listen(25547)) {
                            composeMessageAdapter.notifyDataSetChanged();
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(25546)) {
                            composeMessageAdapter.setNotifyOnChange(true);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(25553)) {
            if (markasread) {
                if (!ListenerUtil.mutListener.listen(25552)) {
                    markAsRead();
                }
            }
        }
        return insertedSize;
    }

    private void valuesLoaded(List<AbstractMessageModel> values) {
        if (!ListenerUtil.mutListener.listen(25565)) {
            if ((ListenerUtil.mutListener.listen(25559) ? (values != null || (ListenerUtil.mutListener.listen(25558) ? (values.size() >= 0) : (ListenerUtil.mutListener.listen(25557) ? (values.size() <= 0) : (ListenerUtil.mutListener.listen(25556) ? (values.size() < 0) : (ListenerUtil.mutListener.listen(25555) ? (values.size() != 0) : (ListenerUtil.mutListener.listen(25554) ? (values.size() == 0) : (values.size() > 0))))))) : (values != null && (ListenerUtil.mutListener.listen(25558) ? (values.size() >= 0) : (ListenerUtil.mutListener.listen(25557) ? (values.size() <= 0) : (ListenerUtil.mutListener.listen(25556) ? (values.size() < 0) : (ListenerUtil.mutListener.listen(25555) ? (values.size() != 0) : (ListenerUtil.mutListener.listen(25554) ? (values.size() == 0) : (values.size() > 0))))))))) {
                if (!ListenerUtil.mutListener.listen(25564)) {
                    this.currentPageReferenceId = values.get((ListenerUtil.mutListener.listen(25563) ? (values.size() % 1) : (ListenerUtil.mutListener.listen(25562) ? (values.size() / 1) : (ListenerUtil.mutListener.listen(25561) ? (values.size() * 1) : (ListenerUtil.mutListener.listen(25560) ? (values.size() + 1) : (values.size() - 1)))))).getId();
                }
            }
        }
    }

    /**
     *  initialize conversation list and set the unread message count
     *  @return number of unread messages
     */
    @SuppressLint({ "StaticFieldLeak", "WrongThread" })
    @UiThread
    private void initConversationList(@Nullable Runnable runAfter) {
        if (!ListenerUtil.mutListener.listen(25566)) {
            this.unreadCount = (int) this.messageReceiver.getUnreadMessagesCount();
        }
        if (!ListenerUtil.mutListener.listen(25582)) {
            if ((ListenerUtil.mutListener.listen(25571) ? (this.unreadCount >= MESSAGE_PAGE_SIZE) : (ListenerUtil.mutListener.listen(25570) ? (this.unreadCount <= MESSAGE_PAGE_SIZE) : (ListenerUtil.mutListener.listen(25569) ? (this.unreadCount < MESSAGE_PAGE_SIZE) : (ListenerUtil.mutListener.listen(25568) ? (this.unreadCount != MESSAGE_PAGE_SIZE) : (ListenerUtil.mutListener.listen(25567) ? (this.unreadCount == MESSAGE_PAGE_SIZE) : (this.unreadCount > MESSAGE_PAGE_SIZE))))))) {
                if (!ListenerUtil.mutListener.listen(25581)) {
                    new AsyncTask<Void, Void, List<AbstractMessageModel>>() {

                        @Override
                        protected void onPreExecute() {
                            if (!ListenerUtil.mutListener.listen(25575)) {
                                GenericProgressDialog.newInstance(-1, R.string.please_wait).show(getParentFragmentManager(), DIALOG_TAG_LOADING_MESSAGES);
                            }
                        }

                        @Override
                        protected List<AbstractMessageModel> doInBackground(Void... voids) {
                            return messageService.getMessagesForReceiver(messageReceiver, new MessageService.MessageFilter() {

                                @Override
                                public long getPageSize() {
                                    return -1;
                                }

                                @Override
                                public Integer getPageReferenceId() {
                                    return null;
                                }

                                @Override
                                public boolean withStatusMessages() {
                                    return false;
                                }

                                @Override
                                public boolean withUnsaved() {
                                    return false;
                                }

                                @Override
                                public boolean onlyUnread() {
                                    return false;
                                }

                                @Override
                                public boolean onlyDownloaded() {
                                    return false;
                                }

                                @Override
                                public MessageType[] types() {
                                    return new MessageType[0];
                                }

                                @Override
                                public int[] contentTypes() {
                                    return null;
                                }
                            });
                        }

                        @Override
                        protected void onPostExecute(List<AbstractMessageModel> values) {
                            if (!ListenerUtil.mutListener.listen(25576)) {
                                valuesLoaded(values);
                            }
                            if (!ListenerUtil.mutListener.listen(25577)) {
                                populateList(values);
                            }
                            if (!ListenerUtil.mutListener.listen(25578)) {
                                DialogUtil.dismissDialog(getParentFragmentManager(), DIALOG_TAG_LOADING_MESSAGES, true);
                            }
                            if (!ListenerUtil.mutListener.listen(25580)) {
                                if (runAfter != null) {
                                    if (!ListenerUtil.mutListener.listen(25579)) {
                                        runAfter.run();
                                    }
                                }
                            }
                        }
                    }.execute();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(25572)) {
                    populateList(getNextRecords());
                }
                if (!ListenerUtil.mutListener.listen(25574)) {
                    if (runAfter != null) {
                        if (!ListenerUtil.mutListener.listen(25573)) {
                            runAfter.run();
                        }
                    }
                }
            }
        }
    }

    /**
     *  Populate ListView with provided message models
     *  @param values
     */
    @UiThread
    private void populateList(List<AbstractMessageModel> values) {
        if (!ListenerUtil.mutListener.listen(25628)) {
            if (composeMessageAdapter != null) {
                if (!ListenerUtil.mutListener.listen(25621)) {
                    // re-use existing adapter (for example on tablets)
                    composeMessageAdapter.clear();
                }
                if (!ListenerUtil.mutListener.listen(25622)) {
                    composeMessageAdapter.setThumbnailWidth(ConfigUtils.getPreferredThumbnailWidth(getContext(), false));
                }
                if (!ListenerUtil.mutListener.listen(25623)) {
                    composeMessageAdapter.setGroupId(groupId);
                }
                if (!ListenerUtil.mutListener.listen(25624)) {
                    composeMessageAdapter.setMessageReceiver(messageReceiver);
                }
                if (!ListenerUtil.mutListener.listen(25625)) {
                    composeMessageAdapter.setUnreadMessagesCount(unreadCount);
                }
                if (!ListenerUtil.mutListener.listen(25626)) {
                    insertToList(values, true, true, true);
                }
                if (!ListenerUtil.mutListener.listen(25627)) {
                    updateToolbarTitle();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(25583)) {
                    thumbnailCache = new ThumbnailCache<Integer>(null);
                }
                if (!ListenerUtil.mutListener.listen(25584)) {
                    composeMessageAdapter = new ComposeMessageAdapter(activity, messagePlayerService, messageValues, userService, contactService, fileService, messageService, ballotService, preferenceService, downloadService, licenseService, messageReceiver, convListView, thumbnailCache, ConfigUtils.getPreferredThumbnailWidth(getContext(), false), ComposeMessageFragment.this, unreadCount);
                }
                if (!ListenerUtil.mutListener.listen(25589)) {
                    // adding footer before setting the list adapter (android < 4.4)
                    if ((ListenerUtil.mutListener.listen(25586) ? ((ListenerUtil.mutListener.listen(25585) ? (null != convListView || !isGroupChat) : (null != convListView && !isGroupChat)) || !isDistributionListChat) : ((ListenerUtil.mutListener.listen(25585) ? (null != convListView || !isGroupChat) : (null != convListView && !isGroupChat)) && !isDistributionListChat))) {
                        if (!ListenerUtil.mutListener.listen(25587)) {
                            // create the istyping instance for later use
                            isTypingView = layoutInflater.inflate(R.layout.conversation_list_item_typing, null);
                        }
                        if (!ListenerUtil.mutListener.listen(25588)) {
                            convListView.addFooterView(isTypingView, null, false);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(25590)) {
                    composeMessageAdapter.setGroupId(groupId);
                }
                if (!ListenerUtil.mutListener.listen(25616)) {
                    composeMessageAdapter.setOnClickListener(new ComposeMessageAdapter.OnClickListener() {

                        @Override
                        public void resend(AbstractMessageModel messageModel) {
                            if (!ListenerUtil.mutListener.listen(25596)) {
                                if ((ListenerUtil.mutListener.listen(25592) ? ((ListenerUtil.mutListener.listen(25591) ? (messageModel.isOutbox() || messageModel.getState() == MessageState.SENDFAILED) : (messageModel.isOutbox() && messageModel.getState() == MessageState.SENDFAILED)) || messageReceiver.isMessageBelongsToMe(messageModel)) : ((ListenerUtil.mutListener.listen(25591) ? (messageModel.isOutbox() || messageModel.getState() == MessageState.SENDFAILED) : (messageModel.isOutbox() && messageModel.getState() == MessageState.SENDFAILED)) && messageReceiver.isMessageBelongsToMe(messageModel)))) {
                                    try {
                                        if (!ListenerUtil.mutListener.listen(25595)) {
                                            messageService.resendMessage(messageModel, messageReceiver, null);
                                        }
                                    } catch (Exception e) {
                                        if (!ListenerUtil.mutListener.listen(25594)) {
                                            RuntimeUtil.runOnUiThread(new Runnable() {

                                                @Override
                                                public void run() {
                                                    if (!ListenerUtil.mutListener.listen(25593)) {
                                                        Toast.makeText(getContext(), R.string.original_file_no_longer_avilable, Toast.LENGTH_LONG).show();
                                                    }
                                                }
                                            });
                                        }
                                    }
                                }
                            }
                        }

                        @Override
                        public void click(View view, int position, AbstractMessageModel messageModel) {
                            if (!ListenerUtil.mutListener.listen(25598)) {
                                if (searchActionMode == null) {
                                    if (!ListenerUtil.mutListener.listen(25597)) {
                                        onListItemClick(view, position, messageModel);
                                    }
                                }
                            }
                        }

                        @Override
                        public void longClick(View view, int position, AbstractMessageModel messageModel) {
                            if (!ListenerUtil.mutListener.listen(25600)) {
                                if (searchActionMode == null) {
                                    if (!ListenerUtil.mutListener.listen(25599)) {
                                        onListItemLongClick(view, position);
                                    }
                                }
                            }
                        }

                        @Override
                        public boolean touch(View view, MotionEvent motionEvent, AbstractMessageModel messageModel) {
                            if (!ListenerUtil.mutListener.listen(25602)) {
                                if ((ListenerUtil.mutListener.listen(25601) ? (listViewSwipeListener != null || searchActionMode == null) : (listViewSwipeListener != null && searchActionMode == null))) {
                                    return listViewSwipeListener.onTouch(view, motionEvent);
                                }
                            }
                            return false;
                        }

                        @Override
                        public void avatarClick(View view, int position, AbstractMessageModel messageModel) {
                            if (!ListenerUtil.mutListener.listen(25613)) {
                                if ((ListenerUtil.mutListener.listen(25603) ? (messageModel != null || messageModel.getIdentity() != null) : (messageModel != null && messageModel.getIdentity() != null))) {
                                    ContactModel contactModel = contactService.getByIdentity(messageModel.getIdentity());
                                    if (!ListenerUtil.mutListener.listen(25612)) {
                                        if (contactModel != null) {
                                            Intent intent;
                                            if ((ListenerUtil.mutListener.listen(25604) ? (messageModel instanceof GroupMessageModel && messageModel instanceof DistributionListMessageModel) : (messageModel instanceof GroupMessageModel || messageModel instanceof DistributionListMessageModel))) {
                                                intent = new Intent(getActivity(), ComposeMessageActivity.class);
                                                if (!ListenerUtil.mutListener.listen(25607)) {
                                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                                }
                                                if (!ListenerUtil.mutListener.listen(25608)) {
                                                    intent.setData((Uri.parse("foobar://" + SystemClock.elapsedRealtime())));
                                                }
                                                if (!ListenerUtil.mutListener.listen(25609)) {
                                                    IntentDataUtil.append(contactModel, intent);
                                                }
                                                if (!ListenerUtil.mutListener.listen(25610)) {
                                                    getActivity().finish();
                                                }
                                            } else {
                                                intent = new Intent(getActivity(), ContactDetailActivity.class);
                                                if (!ListenerUtil.mutListener.listen(25605)) {
                                                    intent.putExtra(ThreemaApplication.INTENT_DATA_CONTACT_READONLY, true);
                                                }
                                                if (!ListenerUtil.mutListener.listen(25606)) {
                                                    IntentDataUtil.append(contactModel, intent);
                                                }
                                            }
                                            if (!ListenerUtil.mutListener.listen(25611)) {
                                                AnimationUtil.startActivityForResult(getActivity(), view, intent, 0);
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        @SuppressLint("DefaultLocale")
                        @Override
                        public void onSearchResultsUpdate(int searchResultsIndex, int searchResultsSize) {
                            if (!ListenerUtil.mutListener.listen(25614)) {
                                RuntimeUtil.runOnUiThread(() -> {
                                    if (searchCounter != null) {
                                        try {
                                            searchCounter.setText(String.format("%d / %d", searchResultsIndex, searchResultsSize));
                                        } catch (Exception e) {
                                        }
                                    }
                                });
                            }
                        }

                        @Override
                        public void onSearchInProgress(boolean inProgress) {
                            if (!ListenerUtil.mutListener.listen(25615)) {
                                RuntimeUtil.runOnUiThread(() -> {
                                    if (searchNextButton != null && searchPreviousButton != null) {
                                        try {
                                            searchPreviousButton.setVisibility(inProgress ? View.INVISIBLE : View.VISIBLE);
                                            searchNextButton.setVisibility(inProgress ? View.INVISIBLE : View.VISIBLE);
                                            searchProgress.setVisibility(inProgress ? View.VISIBLE : View.INVISIBLE);
                                        } catch (Exception e) {
                                        }
                                    }
                                });
                            }
                        }
                    });
                }
                if (!ListenerUtil.mutListener.listen(25617)) {
                    insertToList(values, false, !hiddenChatsListService.has(messageReceiver.getUniqueIdString()), false);
                }
                if (!ListenerUtil.mutListener.listen(25618)) {
                    convListView.setAdapter(composeMessageAdapter);
                }
                if (!ListenerUtil.mutListener.listen(25619)) {
                    convListView.setItemsCanFocus(false);
                }
                if (!ListenerUtil.mutListener.listen(25620)) {
                    convListView.setVisibility(View.VISIBLE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(25629)) {
            setIdentityColors();
        }
        if (!ListenerUtil.mutListener.listen(25630)) {
            removeIsTypingFooter();
        }
    }

    /**
     *  Jump to first unread message keeping in account shift caused by date separators and other decorations
     *  Currently depends on various globals...
     */
    @UiThread
    private void jumpToFirstUnreadMessage() {
        if (!ListenerUtil.mutListener.listen(25662)) {
            if ((ListenerUtil.mutListener.listen(25635) ? (unreadCount >= 0) : (ListenerUtil.mutListener.listen(25634) ? (unreadCount <= 0) : (ListenerUtil.mutListener.listen(25633) ? (unreadCount < 0) : (ListenerUtil.mutListener.listen(25632) ? (unreadCount != 0) : (ListenerUtil.mutListener.listen(25631) ? (unreadCount == 0) : (unreadCount > 0))))))) {
                synchronized (this.messageValues) {
                    int position = Math.min((ListenerUtil.mutListener.listen(25639) ? (convListView.getCount() % unreadCount) : (ListenerUtil.mutListener.listen(25638) ? (convListView.getCount() / unreadCount) : (ListenerUtil.mutListener.listen(25637) ? (convListView.getCount() * unreadCount) : (ListenerUtil.mutListener.listen(25636) ? (convListView.getCount() + unreadCount) : (convListView.getCount() - unreadCount))))), (ListenerUtil.mutListener.listen(25643) ? (this.messageValues.size() % 1) : (ListenerUtil.mutListener.listen(25642) ? (this.messageValues.size() / 1) : (ListenerUtil.mutListener.listen(25641) ? (this.messageValues.size() * 1) : (ListenerUtil.mutListener.listen(25640) ? (this.messageValues.size() + 1) : (this.messageValues.size() - 1))))));
                    if (!ListenerUtil.mutListener.listen(25651)) {
                        {
                            long _loopCounter168 = 0;
                            while ((ListenerUtil.mutListener.listen(25650) ? (position <= 0) : (ListenerUtil.mutListener.listen(25649) ? (position > 0) : (ListenerUtil.mutListener.listen(25648) ? (position < 0) : (ListenerUtil.mutListener.listen(25647) ? (position != 0) : (ListenerUtil.mutListener.listen(25646) ? (position == 0) : (position >= 0))))))) {
                                ListenerUtil.loopListener.listen("_loopCounter168", ++_loopCounter168);
                                if (!ListenerUtil.mutListener.listen(25644)) {
                                    if (this.messageValues.get(position) instanceof FirstUnreadMessageModel) {
                                        break;
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(25645)) {
                                    position--;
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(25652)) {
                        unreadCount = 0;
                    }
                    if (!ListenerUtil.mutListener.listen(25661)) {
                        if ((ListenerUtil.mutListener.listen(25657) ? (position >= 0) : (ListenerUtil.mutListener.listen(25656) ? (position <= 0) : (ListenerUtil.mutListener.listen(25655) ? (position < 0) : (ListenerUtil.mutListener.listen(25654) ? (position != 0) : (ListenerUtil.mutListener.listen(25653) ? (position == 0) : (position > 0))))))) {
                            final int finalPosition = position;
                            if (!ListenerUtil.mutListener.listen(25658)) {
                                logger.debug("jump to initial position " + finalPosition);
                            }
                            if (!ListenerUtil.mutListener.listen(25659)) {
                                convListView.setSelection(finalPosition);
                            }
                            if (!ListenerUtil.mutListener.listen(25660)) {
                                convListView.postDelayed(() -> {
                                    convListView.setSelection(finalPosition);
                                }, 750);
                            }
                            return;
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(25663)) {
            convListView.setSelection(Integer.MAX_VALUE);
        }
    }

    private void setIdentityColors() {
        if (!ListenerUtil.mutListener.listen(25664)) {
            logger.debug("setIdentityColors");
        }
        if (!ListenerUtil.mutListener.listen(25692)) {
            if (this.isGroupChat) {
                Map<String, Integer> colors = this.groupService.getGroupMemberColors(this.groupModel);
                if (!ListenerUtil.mutListener.listen(25691)) {
                    if (ConfigUtils.getAppTheme(activity) == ConfigUtils.THEME_DARK) {
                        Map<String, Integer> darkColors = new HashMap<>();
                        @ColorInt
                        final int bubbleColorRecv = activity.getResources().getColor(R.color.dark_bubble_recv);
                        if (!ListenerUtil.mutListener.listen(25689)) {
                            {
                                long _loopCounter169 = 0;
                                // lighten up some colors to ensure better visibility if dark theme is enabled
                                for (Map.Entry<String, Integer> entry : colors.entrySet()) {
                                    ListenerUtil.loopListener.listen("_loopCounter169", ++_loopCounter169);
                                    @ColorInt
                                    int newColor;
                                    try {
                                        newColor = entry.getValue();
                                        if ((ListenerUtil.mutListener.listen(25672) ? (ColorUtils.calculateContrast(newColor, bubbleColorRecv) >= 1.7) : (ListenerUtil.mutListener.listen(25671) ? (ColorUtils.calculateContrast(newColor, bubbleColorRecv) > 1.7) : (ListenerUtil.mutListener.listen(25670) ? (ColorUtils.calculateContrast(newColor, bubbleColorRecv) < 1.7) : (ListenerUtil.mutListener.listen(25669) ? (ColorUtils.calculateContrast(newColor, bubbleColorRecv) != 1.7) : (ListenerUtil.mutListener.listen(25668) ? (ColorUtils.calculateContrast(newColor, bubbleColorRecv) == 1.7) : (ColorUtils.calculateContrast(newColor, bubbleColorRecv) <= 1.7))))))) {
                                            float[] hsl = new float[3];
                                            if (!ListenerUtil.mutListener.listen(25673)) {
                                                ColorUtils.colorToHSL(entry.getValue(), hsl);
                                            }
                                            if (!ListenerUtil.mutListener.listen(25680)) {
                                                if ((ListenerUtil.mutListener.listen(25678) ? (hsl[2] >= 0.7f) : (ListenerUtil.mutListener.listen(25677) ? (hsl[2] <= 0.7f) : (ListenerUtil.mutListener.listen(25676) ? (hsl[2] > 0.7f) : (ListenerUtil.mutListener.listen(25675) ? (hsl[2] != 0.7f) : (ListenerUtil.mutListener.listen(25674) ? (hsl[2] == 0.7f) : (hsl[2] < 0.7f))))))) {
                                                    if (!ListenerUtil.mutListener.listen(25679)) {
                                                        // pull up luminance
                                                        hsl[2] = 0.7f;
                                                    }
                                                }
                                            }
                                            if (!ListenerUtil.mutListener.listen(25687)) {
                                                if ((ListenerUtil.mutListener.listen(25685) ? (hsl[1] >= 0.6f) : (ListenerUtil.mutListener.listen(25684) ? (hsl[1] <= 0.6f) : (ListenerUtil.mutListener.listen(25683) ? (hsl[1] < 0.6f) : (ListenerUtil.mutListener.listen(25682) ? (hsl[1] != 0.6f) : (ListenerUtil.mutListener.listen(25681) ? (hsl[1] == 0.6f) : (hsl[1] > 0.6f))))))) {
                                                    if (!ListenerUtil.mutListener.listen(25686)) {
                                                        // tone down saturation
                                                        hsl[1] = 0.6f;
                                                    }
                                                }
                                            }
                                            newColor = ColorUtils.HSLToColor(hsl);
                                        }
                                        if (!ListenerUtil.mutListener.listen(25688)) {
                                            darkColors.put(entry.getKey(), newColor);
                                        }
                                    } catch (Exception e) {
                                    }
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(25690)) {
                            this.identityColors = darkColors;
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(25667)) {
                            this.identityColors = colors;
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(25666)) {
                    if (this.identityColors != null) {
                        if (!ListenerUtil.mutListener.listen(25665)) {
                            this.identityColors.clear();
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(25693)) {
            this.composeMessageAdapter.setIdentityColors(this.identityColors);
        }
    }

    private void onListItemClick(View view, int position, AbstractMessageModel messageModel) {
        if (!ListenerUtil.mutListener.listen(25694)) {
            if (view == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(25730)) {
            if (actionMode != null) {
                if (!ListenerUtil.mutListener.listen(25721)) {
                    if (selectedMessages.contains(messageModel)) {
                        if (!ListenerUtil.mutListener.listen(25719)) {
                            // remove from selection
                            selectedMessages.remove(messageModel);
                        }
                        if (!ListenerUtil.mutListener.listen(25720)) {
                            convListView.setItemChecked(position, false);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(25718)) {
                            if ((ListenerUtil.mutListener.listen(25714) ? (convListView.getCheckedItemCount() >= MAX_SELECTED_ITEMS) : (ListenerUtil.mutListener.listen(25713) ? (convListView.getCheckedItemCount() <= MAX_SELECTED_ITEMS) : (ListenerUtil.mutListener.listen(25712) ? (convListView.getCheckedItemCount() > MAX_SELECTED_ITEMS) : (ListenerUtil.mutListener.listen(25711) ? (convListView.getCheckedItemCount() != MAX_SELECTED_ITEMS) : (ListenerUtil.mutListener.listen(25710) ? (convListView.getCheckedItemCount() == MAX_SELECTED_ITEMS) : (convListView.getCheckedItemCount() < MAX_SELECTED_ITEMS))))))) {
                                if (!ListenerUtil.mutListener.listen(25716)) {
                                    // add this to selection
                                    selectedMessages.add(messageModel);
                                }
                                if (!ListenerUtil.mutListener.listen(25717)) {
                                    convListView.setItemChecked(position, true);
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(25715)) {
                                    convListView.setItemChecked(position, false);
                                }
                            }
                        }
                    }
                }
                final int checked = convListView.getCheckedItemCount();
                if (!ListenerUtil.mutListener.listen(25729)) {
                    if ((ListenerUtil.mutListener.listen(25726) ? (checked >= 0) : (ListenerUtil.mutListener.listen(25725) ? (checked <= 0) : (ListenerUtil.mutListener.listen(25724) ? (checked < 0) : (ListenerUtil.mutListener.listen(25723) ? (checked != 0) : (ListenerUtil.mutListener.listen(25722) ? (checked == 0) : (checked > 0))))))) {
                        if (!ListenerUtil.mutListener.listen(25728)) {
                            // invalidate menu to update display => onPrepareActionMode()
                            actionMode.invalidate();
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(25727)) {
                            actionMode.finish();
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(25696)) {
                    if (view.isSelected()) {
                        if (!ListenerUtil.mutListener.listen(25695)) {
                            view.setSelected(false);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(25698)) {
                    if (convListView.isItemChecked(position)) {
                        if (!ListenerUtil.mutListener.listen(25697)) {
                            convListView.setItemChecked(position, false);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(25709)) {
                    // check if item is a quote
                    if (QuoteUtil.isQuoteV1(messageModel.getBody())) {
                        QuoteUtil.QuoteContent quoteContent = QuoteUtil.getQuoteContent(messageModel, messageReceiver.getType(), false, thumbnailCache, getContext(), this.messageService, this.userService, this.fileService);
                        if (!ListenerUtil.mutListener.listen(25708)) {
                            if (quoteContent != null) {
                                if (!ListenerUtil.mutListener.listen(25706)) {
                                    if (searchActionMode != null) {
                                        if (!ListenerUtil.mutListener.listen(25705)) {
                                            searchActionMode.finish();
                                        }
                                    }
                                }
                                ComposeMessageAdapter.ConversationListFilter filter = (ComposeMessageAdapter.ConversationListFilter) composeMessageAdapter.getQuoteFilter(quoteContent);
                                if (!ListenerUtil.mutListener.listen(25707)) {
                                    // search for quoted text
                                    filter.filter(quoteContent.quotedText, count -> {
                                        if (count == 0) {
                                            SingleToast.getInstance().showShortText(getString(R.string.quote_not_found));
                                        }
                                    });
                                }
                            }
                        }
                    } else if (messageModel.getQuotedMessageId() != null) {
                        QuoteUtil.QuoteContent quoteContent = QuoteUtil.getQuoteContent(messageModel, messageReceiver.getType(), false, thumbnailCache, getContext(), this.messageService, this.userService, this.fileService);
                        if (!ListenerUtil.mutListener.listen(25704)) {
                            if (quoteContent != null) {
                                if (!ListenerUtil.mutListener.listen(25700)) {
                                    if (searchActionMode != null) {
                                        if (!ListenerUtil.mutListener.listen(25699)) {
                                            searchActionMode.finish();
                                        }
                                    }
                                }
                                AbstractMessageModel quotedMessageModel = messageService.getMessageModelByApiMessageId(messageModel.getQuotedMessageId(), messageReceiver.getType());
                                if (!ListenerUtil.mutListener.listen(25703)) {
                                    if (quotedMessageModel != null) {
                                        ComposeMessageAdapter.ConversationListFilter filter = (ComposeMessageAdapter.ConversationListFilter) composeMessageAdapter.getQuoteFilter(quoteContent);
                                        if (!ListenerUtil.mutListener.listen(25702)) {
                                            searchV2Quote(quotedMessageModel.getApiMessageId(), filter);
                                        }
                                    } else {
                                        if (!ListenerUtil.mutListener.listen(25701)) {
                                            Toast.makeText(getContext().getApplicationContext(), R.string.quoted_message_deleted, Toast.LENGTH_SHORT).show();
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

    /**
     *  Recursively search for message with provided apiMessageId in chat and gradually load more records to Adapter until matching message is found by provided Filter
     *  TODO: we should provide a static version of this that does not rely on globals
     *  @param apiMessageId to search for
     *  @param filter Filter to use for this search
     */
    @UiThread
    private synchronized void searchV2Quote(final String apiMessageId, final ComposeMessageAdapter.ConversationListFilter filter) {
        if (!ListenerUtil.mutListener.listen(25756)) {
            filter.filter("#" + apiMessageId, new Filter.FilterListener() {

                @SuppressLint("StaticFieldLeak")
                @Override
                public void onFilterComplete(int count) {
                    if (!ListenerUtil.mutListener.listen(25755)) {
                        if ((ListenerUtil.mutListener.listen(25735) ? (count >= 0) : (ListenerUtil.mutListener.listen(25734) ? (count <= 0) : (ListenerUtil.mutListener.listen(25733) ? (count > 0) : (ListenerUtil.mutListener.listen(25732) ? (count < 0) : (ListenerUtil.mutListener.listen(25731) ? (count != 0) : (count == 0))))))) {
                            if (!ListenerUtil.mutListener.listen(25754)) {
                                new AsyncTask<Void, Void, Integer>() {

                                    List<AbstractMessageModel> messageModels;

                                    @Override
                                    protected Integer doInBackground(Void... params) {
                                        if (!ListenerUtil.mutListener.listen(25737)) {
                                            messageModels = getNextRecords();
                                        }
                                        if (!ListenerUtil.mutListener.listen(25738)) {
                                            if (messageModels != null) {
                                                return messageModels.size();
                                            }
                                        }
                                        return null;
                                    }

                                    @Override
                                    protected void onPostExecute(Integer result) {
                                        if (!ListenerUtil.mutListener.listen(25753)) {
                                            if (getContext() != null) {
                                                if (!ListenerUtil.mutListener.listen(25752)) {
                                                    if ((ListenerUtil.mutListener.listen(25744) ? (result != null || (ListenerUtil.mutListener.listen(25743) ? (result >= 0) : (ListenerUtil.mutListener.listen(25742) ? (result <= 0) : (ListenerUtil.mutListener.listen(25741) ? (result < 0) : (ListenerUtil.mutListener.listen(25740) ? (result != 0) : (ListenerUtil.mutListener.listen(25739) ? (result == 0) : (result > 0))))))) : (result != null && (ListenerUtil.mutListener.listen(25743) ? (result >= 0) : (ListenerUtil.mutListener.listen(25742) ? (result <= 0) : (ListenerUtil.mutListener.listen(25741) ? (result < 0) : (ListenerUtil.mutListener.listen(25740) ? (result != 0) : (ListenerUtil.mutListener.listen(25739) ? (result == 0) : (result > 0))))))))) {
                                                        if (!ListenerUtil.mutListener.listen(25747)) {
                                                            insertToList(messageModels, false, false, true);
                                                        }
                                                        if (!ListenerUtil.mutListener.listen(25751)) {
                                                            if (getFragmentManager() != null) {
                                                                if (!ListenerUtil.mutListener.listen(25749)) {
                                                                    if (getFragmentManager().findFragmentByTag(DIALOG_TAG_SEARCHING) == null) {
                                                                        if (!ListenerUtil.mutListener.listen(25748)) {
                                                                            GenericProgressDialog.newInstance(R.string.searching, R.string.please_wait).show(getFragmentManager(), DIALOG_TAG_SEARCHING);
                                                                        }
                                                                    }
                                                                }
                                                                if (!ListenerUtil.mutListener.listen(25750)) {
                                                                    searchV2Quote(apiMessageId, filter);
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        if (!ListenerUtil.mutListener.listen(25745)) {
                                                            SingleToast.getInstance().showShortText(getString(R.string.quote_not_found));
                                                        }
                                                        if (!ListenerUtil.mutListener.listen(25746)) {
                                                            swipeRefreshLayout.setEnabled(false);
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }.execute();
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(25736)) {
                                DialogUtil.dismissDialog(getFragmentManager(), DIALOG_TAG_SEARCHING, true);
                            }
                        }
                    }
                }
            });
        }
    }

    @UiThread
    private void onListItemLongClick(@NonNull View view, final int position) {
        int viewType = composeMessageAdapter.getItemViewType(position);
        if (!ListenerUtil.mutListener.listen(25758)) {
            if ((ListenerUtil.mutListener.listen(25757) ? (viewType == ComposeMessageAdapter.TYPE_FIRST_UNREAD && viewType == ComposeMessageAdapter.TYPE_DATE_SEPARATOR) : (viewType == ComposeMessageAdapter.TYPE_FIRST_UNREAD || viewType == ComposeMessageAdapter.TYPE_DATE_SEPARATOR))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(25759)) {
            selectedMessages.clear();
        }
        if (!ListenerUtil.mutListener.listen(25760)) {
            selectedMessages.add(composeMessageAdapter.getItem(position));
        }
        if (!ListenerUtil.mutListener.listen(25768)) {
            if (actionMode != null) {
                if (!ListenerUtil.mutListener.listen(25765)) {
                    convListView.clearChoices();
                }
                if (!ListenerUtil.mutListener.listen(25766)) {
                    convListView.setItemChecked(position, true);
                }
                if (!ListenerUtil.mutListener.listen(25767)) {
                    actionMode.invalidate();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(25761)) {
                    convListView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
                }
                if (!ListenerUtil.mutListener.listen(25762)) {
                    convListView.setItemChecked(position, true);
                }
                if (!ListenerUtil.mutListener.listen(25763)) {
                    view.setSelected(true);
                }
                if (!ListenerUtil.mutListener.listen(25764)) {
                    actionMode = activity.startSupportActionMode(new ComposeMessageAction(position));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(25769)) {
            view.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);
        }
        if (!ListenerUtil.mutListener.listen(25770)) {
            // see: http://stackoverflow.com/questions/16047215/android-how-to-stop-linkify-on-long-press
            longClickItem = position;
        }
    }

    private boolean isMuted() {
        if (!ListenerUtil.mutListener.listen(25773)) {
            if ((ListenerUtil.mutListener.listen(25771) ? (messageReceiver != null || mutedChatsListService != null) : (messageReceiver != null && mutedChatsListService != null))) {
                String uniqueId = messageReceiver.getUniqueIdString();
                return (ListenerUtil.mutListener.listen(25772) ? (!TestUtil.empty(uniqueId) || mutedChatsListService.has(uniqueId)) : (!TestUtil.empty(uniqueId) && mutedChatsListService.has(uniqueId)));
            }
        }
        return false;
    }

    private boolean isMentionsOnly() {
        if (!ListenerUtil.mutListener.listen(25776)) {
            if ((ListenerUtil.mutListener.listen(25774) ? (messageReceiver != null || mentionOnlyChatsListService != null) : (messageReceiver != null && mentionOnlyChatsListService != null))) {
                String uniqueId = messageReceiver.getUniqueIdString();
                return (ListenerUtil.mutListener.listen(25775) ? (!TestUtil.empty(uniqueId) || mentionOnlyChatsListService.has(uniqueId)) : (!TestUtil.empty(uniqueId) && mentionOnlyChatsListService.has(uniqueId)));
            }
        }
        return false;
    }

    private boolean isSilent() {
        if (!ListenerUtil.mutListener.listen(25780)) {
            if ((ListenerUtil.mutListener.listen(25777) ? (messageReceiver != null || ringtoneService != null) : (messageReceiver != null && ringtoneService != null))) {
                String uniqueId = messageReceiver.getUniqueIdString();
                return (ListenerUtil.mutListener.listen(25779) ? ((ListenerUtil.mutListener.listen(25778) ? (!TestUtil.empty(uniqueId) || ringtoneService.hasCustomRingtone(uniqueId)) : (!TestUtil.empty(uniqueId) && ringtoneService.hasCustomRingtone(uniqueId))) || ringtoneService.isSilent(uniqueId, isGroupChat)) : ((ListenerUtil.mutListener.listen(25778) ? (!TestUtil.empty(uniqueId) || ringtoneService.hasCustomRingtone(uniqueId)) : (!TestUtil.empty(uniqueId) && ringtoneService.hasCustomRingtone(uniqueId))) && ringtoneService.isSilent(uniqueId, isGroupChat)));
            }
        }
        return false;
    }

    private void playInAppSound(final int resId, final boolean isVibrate) {
        if (!ListenerUtil.mutListener.listen(25782)) {
            if ((ListenerUtil.mutListener.listen(25781) ? (this.isMuted() && this.isSilent()) : (this.isMuted() || this.isSilent()))) {
                // do not play
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(25810)) {
            RuntimeUtil.runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    int ringerMode = audioManager.getRingerMode();
                    boolean isSilent = ((ListenerUtil.mutListener.listen(25783) ? (ringerMode == AudioManager.RINGER_MODE_SILENT && ringerMode == AudioManager.RINGER_MODE_VIBRATE) : (ringerMode == AudioManager.RINGER_MODE_SILENT || ringerMode == AudioManager.RINGER_MODE_VIBRATE)));
                    if (!ListenerUtil.mutListener.listen(25804)) {
                        if ((ListenerUtil.mutListener.listen(25784) ? (preferenceService.isInAppSounds() || !isSilent) : (preferenceService.isInAppSounds() && !isSilent))) {
                            MediaPlayerStateWrapper mediaPlayer = new MediaPlayerStateWrapper();
                            if (!ListenerUtil.mutListener.listen(25793)) {
                                if ((ListenerUtil.mutListener.listen(25789) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(25788) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(25787) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(25786) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(25785) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP))))))) {
                                    if (!ListenerUtil.mutListener.listen(25791)) {
                                        mediaPlayer.setAudioAttributes(new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION).setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION).build());
                                    }
                                    if (!ListenerUtil.mutListener.listen(25792)) {
                                        mediaPlayer.setVolume(0.3f, 0.3f);
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(25790)) {
                                        mediaPlayer.setAudioStreamType(AudioManager.STREAM_NOTIFICATION);
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(25798)) {
                                mediaPlayer.setStateListener(new MediaPlayerStateWrapper.StateListener() {

                                    @Override
                                    public void onCompletion(MediaPlayer mp) {
                                        if (!ListenerUtil.mutListener.listen(25795)) {
                                            if (mp.isPlaying()) {
                                                if (!ListenerUtil.mutListener.listen(25794)) {
                                                    mp.stop();
                                                }
                                            }
                                        }
                                        if (!ListenerUtil.mutListener.listen(25796)) {
                                            mp.reset();
                                        }
                                        if (!ListenerUtil.mutListener.listen(25797)) {
                                            mp.release();
                                        }
                                    }

                                    @Override
                                    public void onPrepared(MediaPlayer mp) {
                                    }
                                });
                            }
                            try (AssetFileDescriptor afd = ComposeMessageFragment.this.getResources().openRawResourceFd(resId)) {
                                if (!ListenerUtil.mutListener.listen(25801)) {
                                    mediaPlayer.setDataSource(afd);
                                }
                                if (!ListenerUtil.mutListener.listen(25802)) {
                                    mediaPlayer.prepare();
                                }
                                if (!ListenerUtil.mutListener.listen(25803)) {
                                    mediaPlayer.start();
                                }
                            } catch (Exception e) {
                                if (!ListenerUtil.mutListener.listen(25799)) {
                                    logger.debug("could not play in-app sound.");
                                }
                                if (!ListenerUtil.mutListener.listen(25800)) {
                                    mediaPlayer.release();
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(25809)) {
                        if ((ListenerUtil.mutListener.listen(25805) ? (preferenceService.isInAppVibrate() || isVibrate) : (preferenceService.isInAppVibrate() && isVibrate))) {
                            Vibrator vibrator = (Vibrator) activity.getSystemService(Context.VIBRATOR_SERVICE);
                            if (!ListenerUtil.mutListener.listen(25808)) {
                                if (vibrator != null) {
                                    if (!ListenerUtil.mutListener.listen(25807)) {
                                        switch(ringerMode) {
                                            case AudioManager.RINGER_MODE_VIBRATE:
                                            case AudioManager.RINGER_MODE_NORMAL:
                                                if (!ListenerUtil.mutListener.listen(25806)) {
                                                    vibrator.vibrate(VIBRATION_MSEC);
                                                }
                                                break;
                                            default:
                                                break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            });
        }
    }

    private void playSentSound() {
        if (!ListenerUtil.mutListener.listen(25811)) {
            playInAppSound(R.raw.sent_message, false);
        }
    }

    private void playReceivedSound() {
        if (!ListenerUtil.mutListener.listen(25812)) {
            playInAppSound(R.raw.received_message, true);
        }
    }

    private void sendTextMessage() {
        if (!ListenerUtil.mutListener.listen(25813)) {
            if (!this.validateSendingPermission()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(25832)) {
            if (!TestUtil.empty(this.messageText.getText())) {
                final CharSequence message;
                if (isQuotePanelShown()) {
                    message = QuoteUtil.quote(this.messageText.getText().toString(), quoteInfo.quoteIdentity, quoteInfo.quoteText, quoteInfo.messageModel);
                    if (!ListenerUtil.mutListener.listen(25816)) {
                        closeQuoteMode();
                    }
                } else {
                    message = this.messageText.getText();
                }
                if (!ListenerUtil.mutListener.listen(25831)) {
                    if (!TestUtil.empty(message)) {
                        if (!ListenerUtil.mutListener.listen(25817)) {
                            // block send button to avoid double posting
                            this.messageText.setText("");
                        }
                        if (!ListenerUtil.mutListener.listen(25819)) {
                            if (typingIndicatorTextWatcher != null) {
                                if (!ListenerUtil.mutListener.listen(25818)) {
                                    messageText.removeTextChangedListener(typingIndicatorTextWatcher);
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(25821)) {
                            if (typingIndicatorTextWatcher != null) {
                                if (!ListenerUtil.mutListener.listen(25820)) {
                                    messageText.addTextChangedListener(typingIndicatorTextWatcher);
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(25823)) {
                            // send stopped typing message
                            if (typingIndicatorTextWatcher != null) {
                                if (!ListenerUtil.mutListener.listen(25822)) {
                                    typingIndicatorTextWatcher.stopTyping();
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(25830)) {
                            new Thread(new Runnable() {

                                @Override
                                public void run() {
                                    if (!ListenerUtil.mutListener.listen(25829)) {
                                        TextMessageSendAction.getInstance().sendTextMessage(new MessageReceiver[] { messageReceiver }, message.toString(), new SendAction.ActionHandler() {

                                            @Override
                                            public void onError(final String errorMessage) {
                                                if (!ListenerUtil.mutListener.listen(25824)) {
                                                    RuntimeUtil.runOnUiThread(() -> {
                                                        Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_LONG).show();
                                                        if (!TestUtil.empty(message)) {
                                                            messageText.setText(message);
                                                            messageText.setSelection(messageText.length());
                                                        }
                                                    });
                                                }
                                            }

                                            @Override
                                            public void onWarning(String warning, boolean continueAction) {
                                            }

                                            @Override
                                            public void onProgress(final int progress, final int total) {
                                            }

                                            @Override
                                            public void onCompleted() {
                                                if (!ListenerUtil.mutListener.listen(25828)) {
                                                    RuntimeUtil.runOnUiThread(new Runnable() {

                                                        @Override
                                                        public void run() {
                                                            if (!ListenerUtil.mutListener.listen(25825)) {
                                                                scrollList(Integer.MAX_VALUE);
                                                            }
                                                            if (!ListenerUtil.mutListener.listen(25827)) {
                                                                if (ConfigUtils.isTabletLayout()) {
                                                                    if (!ListenerUtil.mutListener.listen(25826)) {
                                                                        // remove draft right now to make sure conversations pane is updated
                                                                        ThreemaApplication.putMessageDraft(messageReceiver.getUniqueIdString(), "");
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    });
                                                }
                                            }
                                        });
                                    }
                                }
                            }).start();
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(25815)) {
                    if (ConfigUtils.requestAudioPermissions(getActivity(), this, PERMISSION_REQUEST_ATTACH_VOICE_MESSAGE)) {
                        if (!ListenerUtil.mutListener.listen(25814)) {
                            attachVoiceMessage();
                        }
                    }
                }
            }
        }
    }

    private void attachVoiceMessage() {
        if (!ListenerUtil.mutListener.listen(25833)) {
            closeQuoteMode();
        }
        if (!ListenerUtil.mutListener.listen(25835)) {
            // stop all message players
            if (this.messagePlayerService != null) {
                if (!ListenerUtil.mutListener.listen(25834)) {
                    this.messagePlayerService.pauseAll(SOURCE_AUDIORECORDER);
                }
            }
        }
        Intent intent = new Intent(activity, VoiceRecorderActivity.class);
        if (!ListenerUtil.mutListener.listen(25836)) {
            IntentDataUtil.addMessageReceiverToIntent(intent, messageReceiver);
        }
        if (!ListenerUtil.mutListener.listen(25837)) {
            activity.startActivityForResult(intent, ACTIVITY_ID_VOICE_RECORDER);
        }
        if (!ListenerUtil.mutListener.listen(25838)) {
            activity.overridePendingTransition(R.anim.slide_in_left_short, 0);
        }
    }

    private void copySelectedMessagesToClipboard() {
        AbstractMessageModel messageModel = selectedMessages.get(0);
        if (!ListenerUtil.mutListener.listen(25840)) {
            if (messageModel == null) {
                if (!ListenerUtil.mutListener.listen(25839)) {
                    logger.error("no message model", activity);
                }
                return;
            }
        }
        String body = "";
        if (!ListenerUtil.mutListener.listen(25849)) {
            {
                long _loopCounter170 = 0;
                for (AbstractMessageModel message : selectedMessages) {
                    ListenerUtil.loopListener.listen("_loopCounter170", ++_loopCounter170);
                    if (!ListenerUtil.mutListener.listen(25847)) {
                        if ((ListenerUtil.mutListener.listen(25845) ? (body.length() >= 0) : (ListenerUtil.mutListener.listen(25844) ? (body.length() <= 0) : (ListenerUtil.mutListener.listen(25843) ? (body.length() < 0) : (ListenerUtil.mutListener.listen(25842) ? (body.length() != 0) : (ListenerUtil.mutListener.listen(25841) ? (body.length() == 0) : (body.length() > 0))))))) {
                            if (!ListenerUtil.mutListener.listen(25846)) {
                                body += "\n";
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(25848)) {
                        body += message.getType() == MessageType.TEXT ? QuoteUtil.getMessageBody(message, false) : message.getCaption();
                    }
                }
            }
        }
        try {
            ClipboardManager clipboard = (ClipboardManager) activity.getSystemService(Context.CLIPBOARD_SERVICE);
            if (!ListenerUtil.mutListener.listen(25854)) {
                if (clipboard != null) {
                    ClipData clipData = ClipData.newPlainText(null, body);
                    if (!ListenerUtil.mutListener.listen(25853)) {
                        if (clipData != null) {
                            if (!ListenerUtil.mutListener.listen(25851)) {
                                clipboard.setPrimaryClip(clipData);
                            }
                            if (!ListenerUtil.mutListener.listen(25852)) {
                                Snackbar.make(coordinatorLayout, R.string.message_copied, Snackbar.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(25850)) {
                // see https://code.google.com/p/android/issues/detail?id=58043
                logger.error("Exception", e);
            }
        }
    }

    @SuppressLint("StaticFieldLeak")
    private void shareMessages() {
        if (!ListenerUtil.mutListener.listen(25874)) {
            if ((ListenerUtil.mutListener.listen(25859) ? (selectedMessages.size() >= 1) : (ListenerUtil.mutListener.listen(25858) ? (selectedMessages.size() <= 1) : (ListenerUtil.mutListener.listen(25857) ? (selectedMessages.size() < 1) : (ListenerUtil.mutListener.listen(25856) ? (selectedMessages.size() != 1) : (ListenerUtil.mutListener.listen(25855) ? (selectedMessages.size() == 1) : (selectedMessages.size() > 1))))))) {
                if (!ListenerUtil.mutListener.listen(25873)) {
                    new AsyncTask<Void, Void, Void>() {

                        @Override
                        protected void onPreExecute() {
                            if (!ListenerUtil.mutListener.listen(25868)) {
                                GenericProgressDialog.newInstance(R.string.decoding_message, R.string.please_wait).show(getFragmentManager(), DIALOG_TAG_DECRYPTING_MESSAGES);
                            }
                        }

                        @Override
                        protected Void doInBackground(Void... voids) {
                            if (!ListenerUtil.mutListener.listen(25871)) {
                                fileService.loadDecryptedMessageFiles(selectedMessages, new FileService.OnDecryptedFilesComplete() {

                                    @Override
                                    public void complete(ArrayList<Uri> uris) {
                                        if (!ListenerUtil.mutListener.listen(25869)) {
                                            shareMediaMessages(uris);
                                        }
                                    }

                                    @Override
                                    public void error(String message) {
                                        if (!ListenerUtil.mutListener.listen(25870)) {
                                            RuntimeUtil.runOnUiThread(() -> Toast.makeText(activity, message, Toast.LENGTH_LONG).show());
                                        }
                                    }
                                });
                            }
                            return null;
                        }

                        @Override
                        protected void onPostExecute(Void aVoid) {
                            if (!ListenerUtil.mutListener.listen(25872)) {
                                DialogUtil.dismissDialog(getFragmentManager(), DIALOG_TAG_DECRYPTING_MESSAGES, true);
                            }
                        }
                    }.execute();
                }
            } else {
                final AbstractMessageModel messageModel = selectedMessages.get(0);
                if (!ListenerUtil.mutListener.listen(25867)) {
                    if (messageModel != null) {
                        if (!ListenerUtil.mutListener.listen(25866)) {
                            fileService.loadDecryptedMessageFile(messageModel, new FileService.OnDecryptedFileComplete() {

                                @Override
                                public void complete(File decryptedFile) {
                                    if (!ListenerUtil.mutListener.listen(25864)) {
                                        if (decryptedFile != null) {
                                            String filename = null;
                                            if (!ListenerUtil.mutListener.listen(25862)) {
                                                if (messageModel.getType() == MessageType.FILE) {
                                                    if (!ListenerUtil.mutListener.listen(25861)) {
                                                        filename = messageModel.getFileData().getFileName();
                                                    }
                                                }
                                            }
                                            if (!ListenerUtil.mutListener.listen(25863)) {
                                                shareMediaMessages(Collections.singletonList(fileService.getShareFileUri(decryptedFile, filename)));
                                            }
                                        } else {
                                            if (!ListenerUtil.mutListener.listen(25860)) {
                                                messageService.shareTextMessage(activity, messageModel);
                                            }
                                        }
                                    }
                                }

                                @Override
                                public void error(final String message) {
                                    if (!ListenerUtil.mutListener.listen(25865)) {
                                        RuntimeUtil.runOnUiThread(() -> Toast.makeText(activity, message, Toast.LENGTH_LONG).show());
                                    }
                                }
                            });
                        }
                    }
                }
            }
        }
    }

    private void shareMediaMessages(List<Uri> uris) {
        if (!ListenerUtil.mutListener.listen(25879)) {
            if (selectedMessages.size() == 1) {
                ExpandableTextEntryDialog alertDialog = ExpandableTextEntryDialog.newInstance(getString(R.string.share_media), R.string.add_caption_hint, selectedMessages.get(0).getCaption(), R.string.label_continue, R.string.cancel, true);
                if (!ListenerUtil.mutListener.listen(25876)) {
                    alertDialog.setData(uris);
                }
                if (!ListenerUtil.mutListener.listen(25877)) {
                    alertDialog.setTargetFragment(this, 0);
                }
                if (!ListenerUtil.mutListener.listen(25878)) {
                    alertDialog.show(getParentFragmentManager(), null);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(25875)) {
                    messageService.shareMediaMessages(activity, new ArrayList<>(selectedMessages), new ArrayList<>(uris), null);
                }
            }
        }
    }

    @Override
    public void onYes(String tag, Object data, String text) {
        List<Uri> uris = (List<Uri>) data;
        if (!ListenerUtil.mutListener.listen(25880)) {
            messageService.shareMediaMessages(activity, new ArrayList<>(selectedMessages), new ArrayList<>(uris), text);
        }
    }

    @Override
    public void onContactSelected(String identity, int length, int insertPosition) {
        Editable editable = this.messageText.getText();
        if (!ListenerUtil.mutListener.listen(25898)) {
            if ((ListenerUtil.mutListener.listen(25891) ? ((ListenerUtil.mutListener.listen(25885) ? (insertPosition <= 0) : (ListenerUtil.mutListener.listen(25884) ? (insertPosition > 0) : (ListenerUtil.mutListener.listen(25883) ? (insertPosition < 0) : (ListenerUtil.mutListener.listen(25882) ? (insertPosition != 0) : (ListenerUtil.mutListener.listen(25881) ? (insertPosition == 0) : (insertPosition >= 0)))))) || (ListenerUtil.mutListener.listen(25890) ? (insertPosition >= editable.length()) : (ListenerUtil.mutListener.listen(25889) ? (insertPosition > editable.length()) : (ListenerUtil.mutListener.listen(25888) ? (insertPosition < editable.length()) : (ListenerUtil.mutListener.listen(25887) ? (insertPosition != editable.length()) : (ListenerUtil.mutListener.listen(25886) ? (insertPosition == editable.length()) : (insertPosition <= editable.length()))))))) : ((ListenerUtil.mutListener.listen(25885) ? (insertPosition <= 0) : (ListenerUtil.mutListener.listen(25884) ? (insertPosition > 0) : (ListenerUtil.mutListener.listen(25883) ? (insertPosition < 0) : (ListenerUtil.mutListener.listen(25882) ? (insertPosition != 0) : (ListenerUtil.mutListener.listen(25881) ? (insertPosition == 0) : (insertPosition >= 0)))))) && (ListenerUtil.mutListener.listen(25890) ? (insertPosition >= editable.length()) : (ListenerUtil.mutListener.listen(25889) ? (insertPosition > editable.length()) : (ListenerUtil.mutListener.listen(25888) ? (insertPosition < editable.length()) : (ListenerUtil.mutListener.listen(25887) ? (insertPosition != editable.length()) : (ListenerUtil.mutListener.listen(25886) ? (insertPosition == editable.length()) : (insertPosition <= editable.length()))))))))) {
                if (!ListenerUtil.mutListener.listen(25896)) {
                    editable.delete(insertPosition, (ListenerUtil.mutListener.listen(25895) ? (insertPosition % length) : (ListenerUtil.mutListener.listen(25894) ? (insertPosition / length) : (ListenerUtil.mutListener.listen(25893) ? (insertPosition * length) : (ListenerUtil.mutListener.listen(25892) ? (insertPosition - length) : (insertPosition + length))))));
                }
                if (!ListenerUtil.mutListener.listen(25897)) {
                    this.messageText.addMention(identity);
                }
            }
        }
    }

    private void startQuoteMode(AbstractMessageModel messageModel, Runnable onFinishRunnable) {
        if (!ListenerUtil.mutListener.listen(25900)) {
            if (messageModel == null) {
                if (!ListenerUtil.mutListener.listen(25899)) {
                    messageModel = selectedMessages.get(0);
                }
            }
        }
        String body = QuoteUtil.getMessageBody(messageModel, true);
        if (!ListenerUtil.mutListener.listen(25927)) {
            if ((ListenerUtil.mutListener.listen(25901) ? (!TestUtil.empty(body) && ConfigUtils.canCreateV2Quotes()) : (!TestUtil.empty(body) || ConfigUtils.canCreateV2Quotes()))) {
                if (!ListenerUtil.mutListener.listen(25903)) {
                    sendButton.setEnabled((ListenerUtil.mutListener.listen(25902) ? (messageText != null || !TestUtil.empty(messageText.getText())) : (messageText != null && !TestUtil.empty(messageText.getText()))));
                }
                if (!ListenerUtil.mutListener.listen(25904)) {
                    quoteInfo.quoteIdentity = messageModel.isOutbox() ? userService.getIdentity() : messageModel.getIdentity();
                }
                if (!ListenerUtil.mutListener.listen(25905)) {
                    quoteInfo.quoteIdentityView.setText(NameUtil.getQuoteName(quoteInfo.quoteIdentity, this.contactService, this.userService));
                }
                int color = ConfigUtils.getAccentColor(activity);
                if (!ListenerUtil.mutListener.listen(25912)) {
                    if (!messageModel.isOutbox()) {
                        if (!ListenerUtil.mutListener.listen(25911)) {
                            if (isGroupChat) {
                                if (!ListenerUtil.mutListener.listen(25910)) {
                                    if ((ListenerUtil.mutListener.listen(25908) ? (identityColors != null || identityColors.containsKey(quoteInfo.quoteIdentity)) : (identityColors != null && identityColors.containsKey(quoteInfo.quoteIdentity)))) {
                                        if (!ListenerUtil.mutListener.listen(25909)) {
                                            color = identityColors.get(quoteInfo.quoteIdentity);
                                        }
                                    }
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(25907)) {
                                    if (contactModel != null) {
                                        if (!ListenerUtil.mutListener.listen(25906)) {
                                            color = contactModel.getColor();
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(25913)) {
                    quoteInfo.quoteBar.setBackgroundColor(color);
                }
                if (!ListenerUtil.mutListener.listen(25914)) {
                    quoteInfo.quoteTextView.setText(emojiMarkupUtil.addTextSpans(activity, body, quoteInfo.quoteTextView, false, false));
                }
                if (!ListenerUtil.mutListener.listen(25915)) {
                    quoteInfo.quoteText = body;
                }
                if (!ListenerUtil.mutListener.listen(25916)) {
                    quoteInfo.messageModel = messageModel;
                }
                if (!ListenerUtil.mutListener.listen(25917)) {
                    quoteInfo.quoteThumbnail.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(25918)) {
                    quoteInfo.quoteTypeImage.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(25925)) {
                    if (ConfigUtils.canCreateV2Quotes()) {
                        try {
                            Bitmap thumbnail = fileService.getMessageThumbnailBitmap(messageModel, thumbnailCache);
                            if (!ListenerUtil.mutListener.listen(25921)) {
                                if (thumbnail != null) {
                                    if (!ListenerUtil.mutListener.listen(25919)) {
                                        quoteInfo.quoteThumbnail.setImageBitmap(thumbnail);
                                    }
                                    if (!ListenerUtil.mutListener.listen(25920)) {
                                        quoteInfo.quoteThumbnail.setVisibility(View.VISIBLE);
                                    }
                                }
                            }
                        } catch (Exception ignore) {
                        }
                        MessageUtil.MessageViewElement messageViewElement = MessageUtil.getViewElement(getContext(), messageModel);
                        if (!ListenerUtil.mutListener.listen(25924)) {
                            if (messageViewElement.icon != null) {
                                if (!ListenerUtil.mutListener.listen(25922)) {
                                    quoteInfo.quoteTypeImage.setImageResource(messageViewElement.icon);
                                }
                                if (!ListenerUtil.mutListener.listen(25923)) {
                                    quoteInfo.quoteTypeImage.setVisibility(View.VISIBLE);
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(25926)) {
                    AnimationUtil.expand(quoteInfo.quotePanel, onFinishRunnable);
                }
            }
        }
    }

    private void closeQuoteMode() {
        if (!ListenerUtil.mutListener.listen(25928)) {
            quoteInfo.quoteIdentityView.setText("");
        }
        if (!ListenerUtil.mutListener.listen(25929)) {
            quoteInfo.quoteTextView.setText("");
        }
        if (!ListenerUtil.mutListener.listen(25931)) {
            if (isQuotePanelShown()) {
                if (!ListenerUtil.mutListener.listen(25930)) {
                    AnimationUtil.collapse(quoteInfo.quotePanel, () -> updateSendButton(messageText.getText()));
                }
            }
        }
    }

    private boolean isQuotePanelShown() {
        return (ListenerUtil.mutListener.listen(25932) ? (quoteInfo.quotePanel != null || quoteInfo.quotePanel.getVisibility() == View.VISIBLE) : (quoteInfo.quotePanel != null && quoteInfo.quotePanel.getVisibility() == View.VISIBLE));
    }

    private void startForwardMessage() {
        if (!ListenerUtil.mutListener.listen(25946)) {
            if ((ListenerUtil.mutListener.listen(25937) ? (selectedMessages.size() >= 0) : (ListenerUtil.mutListener.listen(25936) ? (selectedMessages.size() <= 0) : (ListenerUtil.mutListener.listen(25935) ? (selectedMessages.size() < 0) : (ListenerUtil.mutListener.listen(25934) ? (selectedMessages.size() != 0) : (ListenerUtil.mutListener.listen(25933) ? (selectedMessages.size() == 0) : (selectedMessages.size() > 0))))))) {
                if (!ListenerUtil.mutListener.listen(25944)) {
                    if (selectedMessages.size() == 1) {
                        final AbstractMessageModel messageModel = selectedMessages.get(0);
                        if (!ListenerUtil.mutListener.listen(25943)) {
                            if (messageModel.getType() == MessageType.TEXT) {
                                // allow editing before sending if it's a single text message
                                String body = QuoteUtil.getMessageBody(messageModel, false);
                                Intent intent = new Intent(activity, RecipientListBaseActivity.class);
                                if (!ListenerUtil.mutListener.listen(25938)) {
                                    intent.setType("text/plain");
                                }
                                if (!ListenerUtil.mutListener.listen(25939)) {
                                    intent.setAction(Intent.ACTION_SEND);
                                }
                                if (!ListenerUtil.mutListener.listen(25940)) {
                                    intent.putExtra(Intent.EXTRA_TEXT, body);
                                }
                                if (!ListenerUtil.mutListener.listen(25941)) {
                                    intent.putExtra(ThreemaApplication.INTENT_DATA_IS_FORWARD, true);
                                }
                                if (!ListenerUtil.mutListener.listen(25942)) {
                                    activity.startActivity(intent);
                                }
                                return;
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(25945)) {
                    FileUtil.forwardMessages(activity, RecipientListBaseActivity.class, selectedMessages);
                }
            }
        }
    }

    private void showMessageLog() {
        AbstractMessageModel messageModel = selectedMessages.get(0);
        if (!ListenerUtil.mutListener.listen(25947)) {
            if (messageModel == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(25948)) {
            MessageDetailDialog.newInstance(R.string.message_log_title, messageModel.getId(), messageModel.getClass().toString()).show(getFragmentManager(), "messageLog");
        }
    }

    private void updateToolbarTitle() {
        if (!ListenerUtil.mutListener.listen(25950)) {
            if ((ListenerUtil.mutListener.listen(25949) ? (!TestUtil.required(this.actionBar, this.actionBarSubtitleImageView, this.actionBarSubtitleTextView, this.actionBarTitleTextView, this.emojiMarkupUtil, this.messageReceiver) && !requiredInstances()) : (!TestUtil.required(this.actionBar, this.actionBarSubtitleImageView, this.actionBarSubtitleTextView, this.actionBarTitleTextView, this.emojiMarkupUtil, this.messageReceiver) || !requiredInstances()))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(25951)) {
            this.actionBarSubtitleTextView.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(25952)) {
            this.actionBarSubtitleImageView.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(25953)) {
            this.actionBarTitleTextView.setText(this.messageReceiver.getDisplayName());
        }
        if (!ListenerUtil.mutListener.listen(25954)) {
            this.actionBarTitleTextView.setPaintFlags(this.actionBarTitleTextView.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
        }
        if (!ListenerUtil.mutListener.listen(25970)) {
            if (this.isGroupChat) {
                if (!ListenerUtil.mutListener.listen(25965)) {
                    if (!groupService.isGroupMember(this.groupModel)) {
                        if (!ListenerUtil.mutListener.listen(25964)) {
                            this.actionBarTitleTextView.setPaintFlags(this.actionBarTitleTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(25966)) {
                    actionBarSubtitleTextView.setText(groupService.getMembersString(groupModel));
                }
                if (!ListenerUtil.mutListener.listen(25967)) {
                    actionBarSubtitleTextView.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(25968)) {
                    actionBarAvatarView.setImageBitmap(groupService.getAvatar(groupModel, false));
                }
                if (!ListenerUtil.mutListener.listen(25969)) {
                    actionBarAvatarView.setBadgeVisible(false);
                }
            } else if (this.isDistributionListChat) {
                if (!ListenerUtil.mutListener.listen(25960)) {
                    actionBarSubtitleTextView.setText(this.distributionListService.getMembersString(this.distributionListModel));
                }
                if (!ListenerUtil.mutListener.listen(25961)) {
                    actionBarSubtitleTextView.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(25962)) {
                    actionBarAvatarView.setImageBitmap(distributionListService.getAvatar(distributionListModel, false));
                }
                if (!ListenerUtil.mutListener.listen(25963)) {
                    actionBarAvatarView.setBadgeVisible(false);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(25959)) {
                    if (contactModel != null) {
                        if (!ListenerUtil.mutListener.listen(25955)) {
                            this.actionBarSubtitleImageView.setContactModel(contactModel);
                        }
                        if (!ListenerUtil.mutListener.listen(25956)) {
                            this.actionBarSubtitleImageView.setVisibility(View.VISIBLE);
                        }
                        if (!ListenerUtil.mutListener.listen(25957)) {
                            this.actionBarAvatarView.setImageBitmap(contactService.getAvatar(contactModel, false, true));
                        }
                        if (!ListenerUtil.mutListener.listen(25958)) {
                            this.actionBarAvatarView.setBadgeVisible(contactService.showBadge(contactModel));
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(25971)) {
            this.actionBarTitleTextView.invalidate();
        }
        if (!ListenerUtil.mutListener.listen(25972)) {
            this.actionBarSubtitleTextView.invalidate();
        }
        if (!ListenerUtil.mutListener.listen(25973)) {
            this.actionBarSubtitleImageView.invalidate();
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        if (!ListenerUtil.mutListener.listen(25974)) {
            inflater.inflate(R.menu.fragment_compose_message, menu);
        }
        if (!ListenerUtil.mutListener.listen(25975)) {
            this.setupToolbar();
        }
        if (!ListenerUtil.mutListener.listen(25976)) {
            super.onCreateOptionsMenu(menu, inflater);
        }
        if (!ListenerUtil.mutListener.listen(25977)) {
            ConfigUtils.addIconsToOverflowMenu(getContext(), menu);
        }
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        if (!ListenerUtil.mutListener.listen(25978)) {
            this.callItem = menu.findItem(R.id.menu_threema_call);
        }
        if (!ListenerUtil.mutListener.listen(25979)) {
            this.deleteDistributionListItem = menu.findItem(R.id.menu_delete_distribution_list);
        }
        if (!ListenerUtil.mutListener.listen(25980)) {
            this.shortCutItem = menu.findItem(R.id.menu_shortcut);
        }
        if (!ListenerUtil.mutListener.listen(25981)) {
            this.mutedMenuItem = menu.findItem(R.id.menu_muted);
        }
        if (!ListenerUtil.mutListener.listen(25982)) {
            this.blockMenuItem = menu.findItem(R.id.menu_block_contact);
        }
        if (!ListenerUtil.mutListener.listen(25983)) {
            this.showOpenBallotWindowMenuItem = menu.findItem(R.id.menu_ballot_window_show);
        }
        if (!ListenerUtil.mutListener.listen(25984)) {
            this.showBallotsMenuItem = menu.findItem(R.id.menu_ballot_show_all);
        }
        if (!ListenerUtil.mutListener.listen(25985)) {
            // initialize menus
            updateMenus();
        }
        if (!ListenerUtil.mutListener.listen(25986)) {
            // initialize various toolbar items
            this.updateToolbarTitle();
        }
    }

    @SuppressLint("StaticFieldLeak")
    private void updateMenus() {
        if (!ListenerUtil.mutListener.listen(25987)) {
            logger.debug("updateMenus");
        }
        if (!ListenerUtil.mutListener.listen(25988)) {
            if (!TestUtil.required(this.callItem, this.deleteDistributionListItem, this.shortCutItem, this.mutedMenuItem, this.blockMenuItem, this.showOpenBallotWindowMenuItem, isAdded())) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(25989)) {
            this.deleteDistributionListItem.setVisible(this.isDistributionListChat);
        }
        if (!ListenerUtil.mutListener.listen(25990)) {
            this.shortCutItem.setVisible(ShortcutManagerCompat.isRequestPinShortcutSupported(getAppContext()));
        }
        if (!ListenerUtil.mutListener.listen(25993)) {
            this.mutedMenuItem.setVisible((ListenerUtil.mutListener.listen(25992) ? (!this.isDistributionListChat || !((ListenerUtil.mutListener.listen(25991) ? (isGroupChat || groupService.isNotesGroup(groupModel)) : (isGroupChat && groupService.isNotesGroup(groupModel))))) : (!this.isDistributionListChat && !((ListenerUtil.mutListener.listen(25991) ? (isGroupChat || groupService.isNotesGroup(groupModel)) : (isGroupChat && groupService.isNotesGroup(groupModel)))))));
        }
        if (!ListenerUtil.mutListener.listen(25994)) {
            updateMuteMenu();
        }
        if (!ListenerUtil.mutListener.listen(25999)) {
            if (contactModel != null) {
                if (!ListenerUtil.mutListener.listen(25996)) {
                    this.blockMenuItem.setVisible(true);
                }
                if (!ListenerUtil.mutListener.listen(25997)) {
                    updateBlockMenu();
                }
                if (!ListenerUtil.mutListener.listen(25998)) {
                    contactTypingStateChanged(contactService.isTyping(contactModel.getIdentity()));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(25995)) {
                    this.blockMenuItem.setVisible(false);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(26013)) {
            new AsyncTask<Void, Void, Long>() {

                @Override
                protected Long doInBackground(Void... voids) {
                    return ballotService.countBallots(new BallotService.BallotFilter() {

                        @Override
                        public MessageReceiver getReceiver() {
                            return messageReceiver;
                        }

                        @Override
                        public BallotModel.State[] getStates() {
                            return new BallotModel.State[] { BallotModel.State.OPEN };
                        }

                        @Override
                        public String createdOrNotVotedByIdentity() {
                            return userService.getIdentity();
                        }

                        @Override
                        public boolean filter(BallotModel ballotModel) {
                            return true;
                        }
                    });
                }

                @Override
                protected void onPostExecute(Long openBallots) {
                    if (!ListenerUtil.mutListener.listen(26005)) {
                        showOpenBallotWindowMenuItem.setVisible((ListenerUtil.mutListener.listen(26004) ? (openBallots >= 0L) : (ListenerUtil.mutListener.listen(26003) ? (openBallots <= 0L) : (ListenerUtil.mutListener.listen(26002) ? (openBallots < 0L) : (ListenerUtil.mutListener.listen(26001) ? (openBallots != 0L) : (ListenerUtil.mutListener.listen(26000) ? (openBallots == 0L) : (openBallots > 0L)))))));
                    }
                    if (!ListenerUtil.mutListener.listen(26010)) {
                        if (preferenceService.getBallotOverviewHidden()) {
                            if (!ListenerUtil.mutListener.listen(26008)) {
                                showOpenBallotWindowMenuItem.setIcon(R.drawable.ic_outline_visibility);
                            }
                            if (!ListenerUtil.mutListener.listen(26009)) {
                                showOpenBallotWindowMenuItem.setTitle(R.string.ballot_window_show);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(26006)) {
                                showOpenBallotWindowMenuItem.setIcon(R.drawable.ic_outline_visibility_off);
                            }
                            if (!ListenerUtil.mutListener.listen(26007)) {
                                showOpenBallotWindowMenuItem.setTitle(R.string.ballot_window_hide);
                            }
                        }
                    }
                    Context context = getContext();
                    if (!ListenerUtil.mutListener.listen(26012)) {
                        if (context != null) {
                            if (!ListenerUtil.mutListener.listen(26011)) {
                                ConfigUtils.themeMenuItem(showOpenBallotWindowMenuItem, ConfigUtils.getColorFromAttribute(context, R.attr.textColorSecondary));
                            }
                        }
                    }
                }
            }.execute();
        }
        if (!ListenerUtil.mutListener.listen(26020)) {
            new AsyncTask<Void, Void, Long>() {

                @Override
                protected Long doInBackground(Void... voids) {
                    return ballotService.countBallots(new BallotService.BallotFilter() {

                        @Override
                        public MessageReceiver getReceiver() {
                            return messageReceiver;
                        }

                        @Override
                        public BallotModel.State[] getStates() {
                            return new BallotModel.State[] { BallotModel.State.OPEN, BallotModel.State.CLOSED };
                        }

                        @Override
                        public boolean filter(BallotModel ballotModel) {
                            return true;
                        }
                    });
                }

                @Override
                protected void onPostExecute(Long hasBallots) {
                    if (!ListenerUtil.mutListener.listen(26019)) {
                        showBallotsMenuItem.setVisible((ListenerUtil.mutListener.listen(26018) ? (hasBallots >= 0L) : (ListenerUtil.mutListener.listen(26017) ? (hasBallots <= 0L) : (ListenerUtil.mutListener.listen(26016) ? (hasBallots < 0L) : (ListenerUtil.mutListener.listen(26015) ? (hasBallots != 0L) : (ListenerUtil.mutListener.listen(26014) ? (hasBallots == 0L) : (hasBallots > 0L)))))));
                    }
                }
            }.execute();
        }
        if (!ListenerUtil.mutListener.listen(26021)) {
            updateVoipCallMenuItem(null);
        }
    }

    private void updateMuteMenu() {
        if (!ListenerUtil.mutListener.listen(26023)) {
            if ((ListenerUtil.mutListener.listen(26022) ? (!isAdded() && this.mutedMenuItem == null) : (!isAdded() || this.mutedMenuItem == null))) {
                // do not update if no longer attached to activity
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(26032)) {
            if (isMuted()) {
                if (!ListenerUtil.mutListener.listen(26030)) {
                    this.mutedMenuItem.setIcon(R.drawable.ic_dnd_total_silence_grey600_24dp);
                }
                if (!ListenerUtil.mutListener.listen(26031)) {
                    this.mutedMenuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
                }
            } else if (isMentionsOnly()) {
                if (!ListenerUtil.mutListener.listen(26028)) {
                    this.mutedMenuItem.setIcon(R.drawable.ic_dnd_mention_grey600_24dp);
                }
                if (!ListenerUtil.mutListener.listen(26029)) {
                    this.mutedMenuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
                }
            } else if (isSilent()) {
                if (!ListenerUtil.mutListener.listen(26026)) {
                    this.mutedMenuItem.setIcon(R.drawable.ic_notifications_off_outline);
                }
                if (!ListenerUtil.mutListener.listen(26027)) {
                    this.mutedMenuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(26024)) {
                    this.mutedMenuItem.setIcon(R.drawable.ic_notifications_active_outline);
                }
                if (!ListenerUtil.mutListener.listen(26025)) {
                    this.mutedMenuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
                }
            }
        }
    }

    private void updateBlockMenu() {
        if (!ListenerUtil.mutListener.listen(26033)) {
            if (!isAdded()) {
                // do not update if no longer attached to activity
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(26040)) {
            if (TestUtil.required(this.blockMenuItem, this.blackListIdentityService, this.contactModel)) {
                boolean state = this.blackListIdentityService.has(this.contactModel.getIdentity());
                if (!ListenerUtil.mutListener.listen(26034)) {
                    this.blockMenuItem.setTitle(state ? getString(R.string.unblock_contact) : getString(R.string.block_contact));
                }
                if (!ListenerUtil.mutListener.listen(26035)) {
                    this.blockMenuItem.setShowAsAction(state ? MenuItem.SHOW_AS_ACTION_ALWAYS : MenuItem.SHOW_AS_ACTION_NEVER);
                }
                if (!ListenerUtil.mutListener.listen(26036)) {
                    this.mutedMenuItem.setShowAsAction(state ? MenuItem.SHOW_AS_ACTION_NEVER : MenuItem.SHOW_AS_ACTION_IF_ROOM);
                }
                if (!ListenerUtil.mutListener.listen(26037)) {
                    this.mutedMenuItem.setVisible(!state);
                }
                if (!ListenerUtil.mutListener.listen(26038)) {
                    this.callItem.setShowAsAction(state ? MenuItem.SHOW_AS_ACTION_NEVER : MenuItem.SHOW_AS_ACTION_ALWAYS);
                }
                if (!ListenerUtil.mutListener.listen(26039)) {
                    updateVoipCallMenuItem(!state);
                }
            }
        }
    }

    @AnyThread
    private void updateVoipCallMenuItem(final Boolean newState) {
        if (!ListenerUtil.mutListener.listen(26047)) {
            RuntimeUtil.runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    if (!ListenerUtil.mutListener.listen(26046)) {
                        if (callItem != null) {
                            if (!ListenerUtil.mutListener.listen(26045)) {
                                if ((ListenerUtil.mutListener.listen(26041) ? (ContactUtil.canReceiveVoipMessages(contactModel, blackListIdentityService) || ConfigUtils.isCallsEnabled(getContext(), preferenceService, licenseService)) : (ContactUtil.canReceiveVoipMessages(contactModel, blackListIdentityService) && ConfigUtils.isCallsEnabled(getContext(), preferenceService, licenseService)))) {
                                    if (!ListenerUtil.mutListener.listen(26043)) {
                                        logger.debug("updateVoipMenu newState " + newState);
                                    }
                                    if (!ListenerUtil.mutListener.listen(26044)) {
                                        callItem.setVisible(newState != null ? newState : voipStateService.getCallState().isIdle());
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(26042)) {
                                        callItem.setVisible(false);
                                    }
                                }
                            }
                        }
                    }
                }
            });
        }
    }

    private Intent addExtrasToIntent(Intent intent, MessageReceiver receiver) {
        if (!ListenerUtil.mutListener.listen(26051)) {
            switch(receiver.getType()) {
                case MessageReceiver.Type_GROUP:
                    if (!ListenerUtil.mutListener.listen(26048)) {
                        intent.putExtra(ThreemaApplication.INTENT_DATA_GROUP, groupId);
                    }
                    break;
                case MessageReceiver.Type_DISTRIBUTION_LIST:
                    if (!ListenerUtil.mutListener.listen(26049)) {
                        intent.putExtra(ThreemaApplication.INTENT_DATA_DISTRIBUTION_LIST, distributionListModel.getId());
                    }
                    break;
                case MessageReceiver.Type_CONTACT:
                default:
                    if (!ListenerUtil.mutListener.listen(26050)) {
                        intent.putExtra(ThreemaApplication.INTENT_DATA_CONTACT, identity);
                    }
            }
        }
        return intent;
    }

    private void attachCamera() {
        Intent previewIntent = IntentDataUtil.addMessageReceiversToIntent(new Intent(activity, SendMediaActivity.class), new MessageReceiver[] { this.messageReceiver });
        if (!ListenerUtil.mutListener.listen(26054)) {
            if ((ListenerUtil.mutListener.listen(26052) ? (this.actionBarTitleTextView != null || this.actionBarTitleTextView.getText() != null) : (this.actionBarTitleTextView != null && this.actionBarTitleTextView.getText() != null))) {
                if (!ListenerUtil.mutListener.listen(26053)) {
                    previewIntent.putExtra(ThreemaApplication.INTENT_DATA_TEXT, this.actionBarTitleTextView.getText().toString());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(26055)) {
            previewIntent.putExtra(ThreemaApplication.INTENT_DATA_PICK_FROM_CAMERA, true);
        }
        if (!ListenerUtil.mutListener.listen(26056)) {
            AnimationUtil.startActivityForResult(activity, null, previewIntent, ThreemaActivity.ACTIVITY_ID_SEND_MEDIA);
        }
    }

    private void showPermissionRationale(int stringResource) {
        if (!ListenerUtil.mutListener.listen(26057)) {
            ConfigUtils.showPermissionRationale(getContext(), coordinatorLayout, stringResource);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!ListenerUtil.mutListener.listen(26086)) {
            switch(item.getItemId()) {
                case android.R.id.home:
                    if (!ListenerUtil.mutListener.listen(26058)) {
                        NavigationUtil.navigateUpToHome(activity);
                    }
                    break;
                case R.id.menu_search_messages:
                    if (!ListenerUtil.mutListener.listen(26059)) {
                        searchActionMode = activity.startSupportActionMode(new SearchActionMode());
                    }
                    break;
                case R.id.menu_gallery:
                    Intent mediaGalleryIntent = new Intent(activity, MediaGalleryActivity.class);
                    if (!ListenerUtil.mutListener.listen(26060)) {
                        activity.startActivity(addExtrasToIntent(mediaGalleryIntent, this.messageReceiver));
                    }
                    break;
                case R.id.menu_threema_call:
                    if (!ListenerUtil.mutListener.listen(26061)) {
                        VoipUtil.initiateCall(activity, contactModel, false, null);
                    }
                    break;
                case R.id.menu_wallpaper:
                    if (!ListenerUtil.mutListener.listen(26064)) {
                        wallpaperService.selectWallpaper(this, this.messageReceiver, new Runnable() {

                            @Override
                            public void run() {
                                if (!ListenerUtil.mutListener.listen(26063)) {
                                    RuntimeUtil.runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            if (!ListenerUtil.mutListener.listen(26062)) {
                                                setBackgroundWallpaper();
                                            }
                                        }
                                    });
                                }
                            }
                        });
                    }
                    if (!ListenerUtil.mutListener.listen(26065)) {
                        activity.overridePendingTransition(R.anim.fast_fade_in, R.anim.fast_fade_out);
                    }
                    break;
                case R.id.menu_muted:
                    if (!ListenerUtil.mutListener.listen(26071)) {
                        if (!isDistributionListChat) {
                            Intent intent;
                            int[] location = new int[2];
                            if (isGroupChat) {
                                intent = new Intent(activity, GroupNotificationsActivity.class);
                                if (!ListenerUtil.mutListener.listen(26067)) {
                                    intent.putExtra(ThreemaApplication.INTENT_DATA_GROUP, this.groupId);
                                }
                            } else {
                                intent = new Intent(activity, ContactNotificationsActivity.class);
                                if (!ListenerUtil.mutListener.listen(26066)) {
                                    intent.putExtra(ThreemaApplication.INTENT_DATA_CONTACT, this.identity);
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(26069)) {
                                if (ToolbarUtil.getMenuItemCenterPosition(((ThreemaToolbarActivity) activity).getToolbar(), R.id.menu_muted, location)) {
                                    if (!ListenerUtil.mutListener.listen(26068)) {
                                        intent.putExtra((ThreemaApplication.INTENT_DATA_ANIM_CENTER), location);
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(26070)) {
                                activity.startActivity(intent);
                            }
                        }
                    }
                    break;
                case R.id.menu_block_contact:
                    if (!ListenerUtil.mutListener.listen(26075)) {
                        if (this.blackListIdentityService.has(contactModel.getIdentity())) {
                            if (!ListenerUtil.mutListener.listen(26073)) {
                                this.blackListIdentityService.toggle(activity, contactModel);
                            }
                            if (!ListenerUtil.mutListener.listen(26074)) {
                                updateBlockMenu();
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(26072)) {
                                GenericAlertDialog.newInstance(R.string.block_contact, R.string.really_block_contact, R.string.yes, R.string.no).setTargetFragment(this).show(getFragmentManager(), DIALOG_TAG_CONFIRM_BLOCK);
                            }
                        }
                    }
                    break;
                case R.id.menu_delete_distribution_list:
                    if (!ListenerUtil.mutListener.listen(26076)) {
                        GenericAlertDialog.newInstance(R.string.really_delete_distribution_list, R.string.really_delete_distribution_list_message, R.string.ok, R.string.cancel).setTargetFragment(this).setData(distributionListModel).show(getFragmentManager(), CONFIRM_TAG_DELETE_DISTRIBUTION_LIST);
                    }
                    break;
                case R.id.menu_shortcut:
                    if (!ListenerUtil.mutListener.listen(26077)) {
                        createShortcut();
                    }
                    break;
                case R.id.menu_empty_chat:
                    if (!ListenerUtil.mutListener.listen(26078)) {
                        GenericAlertDialog.newInstance(R.string.empty_chat_title, R.string.empty_chat_confirm, R.string.ok, R.string.cancel).setTargetFragment(this).show(getFragmentManager(), DIALOG_TAG_EMPTY_CHAT);
                    }
                    break;
                case R.id.menu_ballot_window_show:
                    if (!ListenerUtil.mutListener.listen(26083)) {
                        if (openBallotNoticeView.isShown()) {
                            if (!ListenerUtil.mutListener.listen(26081)) {
                                preferenceService.setBallotOverviewHidden(true);
                            }
                            if (!ListenerUtil.mutListener.listen(26082)) {
                                openBallotNoticeView.hide(true);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(26079)) {
                                preferenceService.setBallotOverviewHidden(false);
                            }
                            if (!ListenerUtil.mutListener.listen(26080)) {
                                openBallotNoticeView.show(true);
                            }
                        }
                    }
                    break;
                case R.id.menu_ballot_show_all:
                    Intent intent = new Intent(getContext(), BallotOverviewActivity.class);
                    if (!ListenerUtil.mutListener.listen(26084)) {
                        IntentDataUtil.addMessageReceiverToIntent(intent, messageReceiver);
                    }
                    if (!ListenerUtil.mutListener.listen(26085)) {
                        startActivity(intent);
                    }
                    break;
            }
        }
        return false;
    }

    private void emptyChat() {
        if (!ListenerUtil.mutListener.listen(26095)) {
            new EmptyChatAsyncTask(new MessageReceiver[] { messageReceiver }, messageService, getFragmentManager(), false, new Runnable() {

                @Override
                public void run() {
                    if (!ListenerUtil.mutListener.listen(26094)) {
                        if (isAdded()) {
                            synchronized (messageValues) {
                                if (!ListenerUtil.mutListener.listen(26087)) {
                                    messageValues.clear();
                                }
                                if (!ListenerUtil.mutListener.listen(26088)) {
                                    composeMessageAdapter.notifyDataSetChanged();
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(26089)) {
                                // empty draft
                                ThreemaApplication.putMessageDraft(messageReceiver.getUniqueIdString(), "");
                            }
                            if (!ListenerUtil.mutListener.listen(26090)) {
                                messageText.setText(null);
                            }
                            if (!ListenerUtil.mutListener.listen(26091)) {
                                // clear conversations cache
                                conversationService.reset();
                            }
                            if (!ListenerUtil.mutListener.listen(26093)) {
                                ListenerManager.conversationListeners.handle(new ListenerManager.HandleListener<ConversationListener>() {

                                    @Override
                                    public void handle(ConversationListener listener) {
                                        if (!ListenerUtil.mutListener.listen(26092)) {
                                            listener.onModifiedAll();
                                        }
                                    }
                                });
                            }
                        }
                    }
                }
            }).execute();
        }
    }

    private void createShortcut() {
        if (!ListenerUtil.mutListener.listen(26105)) {
            if (this.isGroupChat) {
                if (!ListenerUtil.mutListener.listen(26104)) {
                    this.shortcutService.createShortcut(groupModel);
                }
            } else if (this.isDistributionListChat) {
                if (!ListenerUtil.mutListener.listen(26103)) {
                    this.shortcutService.createShortcut(distributionListModel);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(26102)) {
                    if ((ListenerUtil.mutListener.listen(26096) ? (ContactUtil.canReceiveVoipMessages(contactModel, blackListIdentityService) || ConfigUtils.isCallsEnabled(getContext(), preferenceService, licenseService)) : (ContactUtil.canReceiveVoipMessages(contactModel, blackListIdentityService) && ConfigUtils.isCallsEnabled(getContext(), preferenceService, licenseService)))) {
                        ArrayList<String> items = new ArrayList<String>();
                        if (!ListenerUtil.mutListener.listen(26098)) {
                            items.add(getString(R.string.prefs_header_chat));
                        }
                        if (!ListenerUtil.mutListener.listen(26099)) {
                            items.add(getString(R.string.threema_call));
                        }
                        SelectorDialog selectorDialog = SelectorDialog.newInstance(getString(R.string.shortcut_choice_title), items, getString(R.string.cancel));
                        if (!ListenerUtil.mutListener.listen(26100)) {
                            selectorDialog.setTargetFragment(this, 0);
                        }
                        if (!ListenerUtil.mutListener.listen(26101)) {
                            selectorDialog.show(getFragmentManager(), DIALOG_TAG_CHOOSE_SHORTCUT_TYPE);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(26097)) {
                            this.shortcutService.createShortcut(contactModel, ShortcutService.TYPE_CHAT);
                        }
                    }
                }
            }
        }
    }

    private void sendMessage() {
        if (!ListenerUtil.mutListener.listen(26107)) {
            if (typingIndicatorTextWatcher != null) {
                if (!ListenerUtil.mutListener.listen(26106)) {
                    typingIndicatorTextWatcher.killEvents();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(26108)) {
            this.sendTextMessage();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, final Intent intent) {
        if (!ListenerUtil.mutListener.listen(26111)) {
            if ((ListenerUtil.mutListener.listen(26109) ? (wallpaperService != null || wallpaperService.handleActivityResult(this, requestCode, resultCode, intent, this.messageReceiver)) : (wallpaperService != null && wallpaperService.handleActivityResult(this, requestCode, resultCode, intent, this.messageReceiver)))) {
                if (!ListenerUtil.mutListener.listen(26110)) {
                    setBackgroundWallpaper();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(26119)) {
            if ((ListenerUtil.mutListener.listen(26116) ? (requestCode >= ACTIVITY_ID_VOICE_RECORDER) : (ListenerUtil.mutListener.listen(26115) ? (requestCode <= ACTIVITY_ID_VOICE_RECORDER) : (ListenerUtil.mutListener.listen(26114) ? (requestCode > ACTIVITY_ID_VOICE_RECORDER) : (ListenerUtil.mutListener.listen(26113) ? (requestCode < ACTIVITY_ID_VOICE_RECORDER) : (ListenerUtil.mutListener.listen(26112) ? (requestCode != ACTIVITY_ID_VOICE_RECORDER) : (requestCode == ACTIVITY_ID_VOICE_RECORDER))))))) {
                if (!ListenerUtil.mutListener.listen(26118)) {
                    if (this.messagePlayerService != null) {
                        if (!ListenerUtil.mutListener.listen(26117)) {
                            this.messagePlayerService.resumeAll(getActivity(), messageReceiver, SOURCE_AUDIORECORDER);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(26122)) {
            if ((ListenerUtil.mutListener.listen(26120) ? (requestCode == ThreemaActivity.ACTIVITY_ID_ATTACH_MEDIA || resultCode == Activity.RESULT_OK) : (requestCode == ThreemaActivity.ACTIVITY_ID_ATTACH_MEDIA && resultCode == Activity.RESULT_OK))) {
                if (!ListenerUtil.mutListener.listen(26121)) {
                    this.lastMediaFilter = IntentDataUtil.getLastMediaFilterFromIntent(intent);
                }
            }
        }
    }

    private final SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {

        // listener for search bar on top
        @Override
        public boolean onQueryTextChange(String newText) {
            if (!ListenerUtil.mutListener.listen(26123)) {
                composeMessageAdapter.getFilter().filter(newText);
            }
            return true;
        }

        @Override
        public boolean onQueryTextSubmit(String query) {
            if (!ListenerUtil.mutListener.listen(26124)) {
                composeMessageAdapter.nextMatchPosition();
            }
            return true;
        }
    };

    @Override
    public void onClick(String tag, int which, Object data) {
        if (!ListenerUtil.mutListener.listen(26130)) {
            if (DIALOG_TAG_CHOOSE_SHORTCUT_TYPE.equals(tag)) {
                if (!ListenerUtil.mutListener.listen(26129)) {
                    this.shortcutService.createShortcut(contactModel, (ListenerUtil.mutListener.listen(26128) ? (which % 1) : (ListenerUtil.mutListener.listen(26127) ? (which / 1) : (ListenerUtil.mutListener.listen(26126) ? (which * 1) : (ListenerUtil.mutListener.listen(26125) ? (which - 1) : (which + 1))))));
                }
            }
        }
    }

    @Override
    public void onCancel(String tag) {
    }

    @Override
    public void onNo(String tag) {
    }

    public class ComposeMessageAction implements ActionMode.Callback {

        private final int position;

        private MenuItem ackItem, decItem, quoteItem, logItem, discardItem, forwardItem, saveItem, copyItem, qrItem, shareItem, showText;

        ComposeMessageAction(int position) {
            this.position = position;
            if (!ListenerUtil.mutListener.listen(26131)) {
                longClickItem = position;
            }
        }

        private void updateActionMenu() {
            if (!ListenerUtil.mutListener.listen(26132)) {
                // workaround for support library bug, see https://code.google.com/p/android/issues/detail?id=81192
                MenuItemCompat.setShowAsAction(ackItem, MenuItemCompat.SHOW_AS_ACTION_ALWAYS);
            }
            if (!ListenerUtil.mutListener.listen(26133)) {
                MenuItemCompat.setShowAsAction(decItem, MenuItemCompat.SHOW_AS_ACTION_ALWAYS);
            }
            if (!ListenerUtil.mutListener.listen(26134)) {
                MenuItemCompat.setShowAsAction(quoteItem, MenuItemCompat.SHOW_AS_ACTION_ALWAYS);
            }
            if (!ListenerUtil.mutListener.listen(26135)) {
                MenuItemCompat.setShowAsAction(logItem, MenuItemCompat.SHOW_AS_ACTION_IF_ROOM);
            }
            if (!ListenerUtil.mutListener.listen(26136)) {
                MenuItemCompat.setShowAsAction(discardItem, MenuItemCompat.SHOW_AS_ACTION_IF_ROOM);
            }
            if (!ListenerUtil.mutListener.listen(26137)) {
                MenuItemCompat.setShowAsAction(saveItem, MenuItemCompat.SHOW_AS_ACTION_IF_ROOM);
            }
            if (!ListenerUtil.mutListener.listen(26138)) {
                MenuItemCompat.setShowAsAction(copyItem, MenuItemCompat.SHOW_AS_ACTION_ALWAYS);
            }
            if (!ListenerUtil.mutListener.listen(26139)) {
                MenuItemCompat.setShowAsAction(forwardItem, MenuItemCompat.SHOW_AS_ACTION_ALWAYS);
            }
            if (!ListenerUtil.mutListener.listen(26140)) {
                MenuItemCompat.setShowAsAction(qrItem, MenuItemCompat.SHOW_AS_ACTION_IF_ROOM);
            }
            if (!ListenerUtil.mutListener.listen(26141)) {
                MenuItemCompat.setShowAsAction(shareItem, MenuItemCompat.SHOW_AS_ACTION_IF_ROOM);
            }
            if (!ListenerUtil.mutListener.listen(26142)) {
                decItem.setVisible(false);
            }
            if (!ListenerUtil.mutListener.listen(26143)) {
                ackItem.setVisible(false);
            }
            if (!ListenerUtil.mutListener.listen(26144)) {
                quoteItem.setVisible(false);
            }
            if (!ListenerUtil.mutListener.listen(26145)) {
                qrItem.setVisible(false);
            }
            if (!ListenerUtil.mutListener.listen(26146)) {
                copyItem.setVisible(false);
            }
            if (!ListenerUtil.mutListener.listen(26147)) {
                logItem.setVisible(false);
            }
            if (!ListenerUtil.mutListener.listen(26148)) {
                saveItem.setVisible(false);
            }
            if (!ListenerUtil.mutListener.listen(26149)) {
                shareItem.setVisible(false);
            }
            if (!ListenerUtil.mutListener.listen(26150)) {
                showText.setVisible(false);
            }
            if (!ListenerUtil.mutListener.listen(26232)) {
                if ((ListenerUtil.mutListener.listen(26155) ? (selectedMessages.size() >= 1) : (ListenerUtil.mutListener.listen(26154) ? (selectedMessages.size() <= 1) : (ListenerUtil.mutListener.listen(26153) ? (selectedMessages.size() < 1) : (ListenerUtil.mutListener.listen(26152) ? (selectedMessages.size() != 1) : (ListenerUtil.mutListener.listen(26151) ? (selectedMessages.size() == 1) : (selectedMessages.size() > 1))))))) {
                    boolean isForwardable = (ListenerUtil.mutListener.listen(26205) ? (selectedMessages.size() >= MAX_FORWARDABLE_ITEMS) : (ListenerUtil.mutListener.listen(26204) ? (selectedMessages.size() > MAX_FORWARDABLE_ITEMS) : (ListenerUtil.mutListener.listen(26203) ? (selectedMessages.size() < MAX_FORWARDABLE_ITEMS) : (ListenerUtil.mutListener.listen(26202) ? (selectedMessages.size() != MAX_FORWARDABLE_ITEMS) : (ListenerUtil.mutListener.listen(26201) ? (selectedMessages.size() == MAX_FORWARDABLE_ITEMS) : (selectedMessages.size() <= MAX_FORWARDABLE_ITEMS))))));
                    boolean isMedia = true;
                    boolean isTextOnly = true;
                    boolean isShareable = true;
                    if (!ListenerUtil.mutListener.listen(26227)) {
                        {
                            long _loopCounter171 = 0;
                            for (AbstractMessageModel message : selectedMessages) {
                                ListenerUtil.loopListener.listen("_loopCounter171", ++_loopCounter171);
                                if (!ListenerUtil.mutListener.listen(26211)) {
                                    if ((ListenerUtil.mutListener.listen(26209) ? (isForwardable || ((ListenerUtil.mutListener.listen(26208) ? (// if the media is not downloaded
                                    (ListenerUtil.mutListener.listen(26207) ? ((ListenerUtil.mutListener.listen(26206) ? (!message.isAvailable() && // or the message is status message (unread or status)
                                    message.isStatusMessage()) : (!message.isAvailable() || // or the message is status message (unread or status)
                                    message.isStatusMessage())) && // or a ballot
                                    message.getType() == MessageType.BALLOT) : ((ListenerUtil.mutListener.listen(26206) ? (!message.isAvailable() && // or the message is status message (unread or status)
                                    message.isStatusMessage()) : (!message.isAvailable() || // or the message is status message (unread or status)
                                    message.isStatusMessage())) || // or a ballot
                                    message.getType() == MessageType.BALLOT)) && // or a voip status
                                    message.getType() == MessageType.VOIP_STATUS) : (// if the media is not downloaded
                                    (ListenerUtil.mutListener.listen(26207) ? ((ListenerUtil.mutListener.listen(26206) ? (!message.isAvailable() && // or the message is status message (unread or status)
                                    message.isStatusMessage()) : (!message.isAvailable() || // or the message is status message (unread or status)
                                    message.isStatusMessage())) && // or a ballot
                                    message.getType() == MessageType.BALLOT) : ((ListenerUtil.mutListener.listen(26206) ? (!message.isAvailable() && // or the message is status message (unread or status)
                                    message.isStatusMessage()) : (!message.isAvailable() || // or the message is status message (unread or status)
                                    message.isStatusMessage())) || // or a ballot
                                    message.getType() == MessageType.BALLOT)) || // or a voip status
                                    message.getType() == MessageType.VOIP_STATUS)))) : (isForwardable && ((ListenerUtil.mutListener.listen(26208) ? (// if the media is not downloaded
                                    (ListenerUtil.mutListener.listen(26207) ? ((ListenerUtil.mutListener.listen(26206) ? (!message.isAvailable() && // or the message is status message (unread or status)
                                    message.isStatusMessage()) : (!message.isAvailable() || // or the message is status message (unread or status)
                                    message.isStatusMessage())) && // or a ballot
                                    message.getType() == MessageType.BALLOT) : ((ListenerUtil.mutListener.listen(26206) ? (!message.isAvailable() && // or the message is status message (unread or status)
                                    message.isStatusMessage()) : (!message.isAvailable() || // or the message is status message (unread or status)
                                    message.isStatusMessage())) || // or a ballot
                                    message.getType() == MessageType.BALLOT)) && // or a voip status
                                    message.getType() == MessageType.VOIP_STATUS) : (// if the media is not downloaded
                                    (ListenerUtil.mutListener.listen(26207) ? ((ListenerUtil.mutListener.listen(26206) ? (!message.isAvailable() && // or the message is status message (unread or status)
                                    message.isStatusMessage()) : (!message.isAvailable() || // or the message is status message (unread or status)
                                    message.isStatusMessage())) && // or a ballot
                                    message.getType() == MessageType.BALLOT) : ((ListenerUtil.mutListener.listen(26206) ? (!message.isAvailable() && // or the message is status message (unread or status)
                                    message.isStatusMessage()) : (!message.isAvailable() || // or the message is status message (unread or status)
                                    message.isStatusMessage())) || // or a ballot
                                    message.getType() == MessageType.BALLOT)) || // or a voip status
                                    message.getType() == MessageType.VOIP_STATUS)))))) {
                                        if (!ListenerUtil.mutListener.listen(26210)) {
                                            isForwardable = false;
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(26218)) {
                                    if ((ListenerUtil.mutListener.listen(26216) ? ((ListenerUtil.mutListener.listen(26212) ? (isMedia || !message.isAvailable()) : (isMedia && !message.isAvailable())) && ((ListenerUtil.mutListener.listen(26215) ? ((ListenerUtil.mutListener.listen(26214) ? ((ListenerUtil.mutListener.listen(26213) ? (message.getType() != MessageType.IMAGE || message.getType() != MessageType.VOICEMESSAGE) : (message.getType() != MessageType.IMAGE && message.getType() != MessageType.VOICEMESSAGE)) || message.getType() != MessageType.VIDEO) : ((ListenerUtil.mutListener.listen(26213) ? (message.getType() != MessageType.IMAGE || message.getType() != MessageType.VOICEMESSAGE) : (message.getType() != MessageType.IMAGE && message.getType() != MessageType.VOICEMESSAGE)) && message.getType() != MessageType.VIDEO)) || message.getType() != MessageType.FILE) : ((ListenerUtil.mutListener.listen(26214) ? ((ListenerUtil.mutListener.listen(26213) ? (message.getType() != MessageType.IMAGE || message.getType() != MessageType.VOICEMESSAGE) : (message.getType() != MessageType.IMAGE && message.getType() != MessageType.VOICEMESSAGE)) || message.getType() != MessageType.VIDEO) : ((ListenerUtil.mutListener.listen(26213) ? (message.getType() != MessageType.IMAGE || message.getType() != MessageType.VOICEMESSAGE) : (message.getType() != MessageType.IMAGE && message.getType() != MessageType.VOICEMESSAGE)) && message.getType() != MessageType.VIDEO)) && message.getType() != MessageType.FILE)))) : ((ListenerUtil.mutListener.listen(26212) ? (isMedia || !message.isAvailable()) : (isMedia && !message.isAvailable())) || ((ListenerUtil.mutListener.listen(26215) ? ((ListenerUtil.mutListener.listen(26214) ? ((ListenerUtil.mutListener.listen(26213) ? (message.getType() != MessageType.IMAGE || message.getType() != MessageType.VOICEMESSAGE) : (message.getType() != MessageType.IMAGE && message.getType() != MessageType.VOICEMESSAGE)) || message.getType() != MessageType.VIDEO) : ((ListenerUtil.mutListener.listen(26213) ? (message.getType() != MessageType.IMAGE || message.getType() != MessageType.VOICEMESSAGE) : (message.getType() != MessageType.IMAGE && message.getType() != MessageType.VOICEMESSAGE)) && message.getType() != MessageType.VIDEO)) || message.getType() != MessageType.FILE) : ((ListenerUtil.mutListener.listen(26214) ? ((ListenerUtil.mutListener.listen(26213) ? (message.getType() != MessageType.IMAGE || message.getType() != MessageType.VOICEMESSAGE) : (message.getType() != MessageType.IMAGE && message.getType() != MessageType.VOICEMESSAGE)) || message.getType() != MessageType.VIDEO) : ((ListenerUtil.mutListener.listen(26213) ? (message.getType() != MessageType.IMAGE || message.getType() != MessageType.VOICEMESSAGE) : (message.getType() != MessageType.IMAGE && message.getType() != MessageType.VOICEMESSAGE)) && message.getType() != MessageType.VIDEO)) && message.getType() != MessageType.FILE)))))) {
                                        if (!ListenerUtil.mutListener.listen(26217)) {
                                            isMedia = false;
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(26221)) {
                                    if ((ListenerUtil.mutListener.listen(26219) ? (isTextOnly || message.getType() != MessageType.TEXT) : (isTextOnly && message.getType() != MessageType.TEXT))) {
                                        if (!ListenerUtil.mutListener.listen(26220)) {
                                            isTextOnly = false;
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(26226)) {
                                    if (isShareable) {
                                        if (!ListenerUtil.mutListener.listen(26225)) {
                                            if ((ListenerUtil.mutListener.listen(26223) ? ((ListenerUtil.mutListener.listen(26222) ? (message.getType() != MessageType.IMAGE || message.getType() != MessageType.VIDEO) : (message.getType() != MessageType.IMAGE && message.getType() != MessageType.VIDEO)) || message.getType() != MessageType.FILE) : ((ListenerUtil.mutListener.listen(26222) ? (message.getType() != MessageType.IMAGE || message.getType() != MessageType.VIDEO) : (message.getType() != MessageType.IMAGE && message.getType() != MessageType.VIDEO)) && message.getType() != MessageType.FILE))) {
                                                if (!ListenerUtil.mutListener.listen(26224)) {
                                                    isShareable = false;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(26228)) {
                        forwardItem.setVisible(isForwardable);
                    }
                    if (!ListenerUtil.mutListener.listen(26229)) {
                        saveItem.setVisible(isMedia);
                    }
                    if (!ListenerUtil.mutListener.listen(26230)) {
                        copyItem.setVisible(isTextOnly);
                    }
                    if (!ListenerUtil.mutListener.listen(26231)) {
                        shareItem.setVisible(isShareable);
                    }
                } else if (selectedMessages.size() == 1) {
                    AbstractMessageModel selectedMessage = selectedMessages.get(0);
                    if (!ListenerUtil.mutListener.listen(26200)) {
                        if (selectedMessage.isStatusMessage()) {
                            if (!ListenerUtil.mutListener.listen(26197)) {
                                forwardItem.setVisible(false);
                            }
                            if (!ListenerUtil.mutListener.listen(26198)) {
                                copyItem.setVisible(true);
                            }
                            if (!ListenerUtil.mutListener.listen(26199)) {
                                logItem.setVisible(true);
                            }
                        } else {
                            boolean isValidReceiver = messageReceiver.validateSendingPermission(null);
                            if (!ListenerUtil.mutListener.listen(26157)) {
                                quoteItem.setVisible((ListenerUtil.mutListener.listen(26156) ? (isValidReceiver || QuoteUtil.isQuoteable(selectedMessage)) : (isValidReceiver && QuoteUtil.isQuoteable(selectedMessage))));
                            }
                            if (!ListenerUtil.mutListener.listen(26159)) {
                                decItem.setVisible((ListenerUtil.mutListener.listen(26158) ? (MessageUtil.canSendUserDecline(selectedMessage) || isValidReceiver) : (MessageUtil.canSendUserDecline(selectedMessage) && isValidReceiver)));
                            }
                            if (!ListenerUtil.mutListener.listen(26161)) {
                                ackItem.setVisible((ListenerUtil.mutListener.listen(26160) ? (MessageUtil.canSendUserAcknowledge(selectedMessage) || isValidReceiver) : (MessageUtil.canSendUserAcknowledge(selectedMessage) && isValidReceiver)));
                            }
                            if (!ListenerUtil.mutListener.listen(26162)) {
                                logItem.setVisible(true);
                            }
                            if (!ListenerUtil.mutListener.listen(26196)) {
                                switch(selectedMessage.getType()) {
                                    case IMAGE:
                                        if (!ListenerUtil.mutListener.listen(26163)) {
                                            saveItem.setVisible(true);
                                        }
                                        if (!ListenerUtil.mutListener.listen(26164)) {
                                            forwardItem.setVisible(true);
                                        }
                                        if (!ListenerUtil.mutListener.listen(26165)) {
                                            shareItem.setVisible(true);
                                        }
                                        if (!ListenerUtil.mutListener.listen(26167)) {
                                            if (!TestUtil.empty(selectedMessage.getCaption())) {
                                                if (!ListenerUtil.mutListener.listen(26166)) {
                                                    copyItem.setVisible(true);
                                                }
                                            }
                                        }
                                        break;
                                    case VIDEO:
                                        if (!ListenerUtil.mutListener.listen(26168)) {
                                            saveItem.setVisible(selectedMessage.isAvailable());
                                        }
                                        if (!ListenerUtil.mutListener.listen(26169)) {
                                            forwardItem.setVisible(selectedMessage.isAvailable());
                                        }
                                        if (!ListenerUtil.mutListener.listen(26170)) {
                                            shareItem.setVisible(selectedMessage.isAvailable());
                                        }
                                        break;
                                    case VOICEMESSAGE:
                                        if (!ListenerUtil.mutListener.listen(26171)) {
                                            saveItem.setVisible(selectedMessage.isAvailable());
                                        }
                                        if (!ListenerUtil.mutListener.listen(26172)) {
                                            forwardItem.setVisible(selectedMessage.isAvailable());
                                        }
                                        break;
                                    case FILE:
                                        if (!ListenerUtil.mutListener.listen(26175)) {
                                            if (selectedMessage.getFileData().getRenderingType() == FileData.RENDERING_DEFAULT) {
                                                if (!ListenerUtil.mutListener.listen(26173)) {
                                                    MenuItemCompat.setShowAsAction(saveItem, MenuItemCompat.SHOW_AS_ACTION_ALWAYS);
                                                }
                                                if (!ListenerUtil.mutListener.listen(26174)) {
                                                    MenuItemCompat.setShowAsAction(forwardItem, MenuItemCompat.SHOW_AS_ACTION_IF_ROOM);
                                                }
                                            }
                                        }
                                        if (!ListenerUtil.mutListener.listen(26176)) {
                                            saveItem.setVisible(selectedMessage.isAvailable());
                                        }
                                        if (!ListenerUtil.mutListener.listen(26177)) {
                                            shareItem.setVisible(selectedMessage.isAvailable());
                                        }
                                        if (!ListenerUtil.mutListener.listen(26178)) {
                                            forwardItem.setVisible(selectedMessage.isAvailable());
                                        }
                                        if (!ListenerUtil.mutListener.listen(26180)) {
                                            if (!TestUtil.empty(selectedMessage.getCaption())) {
                                                if (!ListenerUtil.mutListener.listen(26179)) {
                                                    copyItem.setVisible(true);
                                                }
                                            }
                                        }
                                        break;
                                    case BALLOT:
                                        if (!ListenerUtil.mutListener.listen(26181)) {
                                            saveItem.setVisible(false);
                                        }
                                        if (!ListenerUtil.mutListener.listen(26182)) {
                                            forwardItem.setVisible(false);
                                        }
                                        break;
                                    case TEXT:
                                        if (!ListenerUtil.mutListener.listen(26183)) {
                                            saveItem.setVisible(false);
                                        }
                                        if (!ListenerUtil.mutListener.listen(26184)) {
                                            forwardItem.setVisible(true);
                                        }
                                        if (!ListenerUtil.mutListener.listen(26185)) {
                                            copyItem.setVisible(true);
                                        }
                                        if (!ListenerUtil.mutListener.listen(26186)) {
                                            qrItem.setVisible(true);
                                        }
                                        if (!ListenerUtil.mutListener.listen(26187)) {
                                            shareItem.setVisible(true);
                                        }
                                        if (!ListenerUtil.mutListener.listen(26188)) {
                                            showText.setVisible(true);
                                        }
                                        break;
                                    case VOIP_STATUS:
                                        if (!ListenerUtil.mutListener.listen(26189)) {
                                            saveItem.setVisible(false);
                                        }
                                        if (!ListenerUtil.mutListener.listen(26190)) {
                                            forwardItem.setVisible(false);
                                        }
                                        if (!ListenerUtil.mutListener.listen(26191)) {
                                            copyItem.setVisible(false);
                                        }
                                        if (!ListenerUtil.mutListener.listen(26192)) {
                                            qrItem.setVisible(false);
                                        }
                                        if (!ListenerUtil.mutListener.listen(26193)) {
                                            shareItem.setVisible(false);
                                        }
                                        if (!ListenerUtil.mutListener.listen(26194)) {
                                            logItem.setVisible(false);
                                        }
                                        break;
                                    case LOCATION:
                                        if (!ListenerUtil.mutListener.listen(26195)) {
                                            shareItem.setVisible(true);
                                        }
                                        break;
                                    default:
                                        break;
                                }
                            }
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(26235)) {
                if (AppRestrictionUtil.isShareMediaDisabled(getContext())) {
                    if (!ListenerUtil.mutListener.listen(26233)) {
                        shareItem.setVisible(false);
                    }
                    if (!ListenerUtil.mutListener.listen(26234)) {
                        saveItem.setVisible(false);
                    }
                }
            }
        }

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            if (!ListenerUtil.mutListener.listen(26236)) {
                if (this.position == AbsListView.INVALID_POSITION) {
                    return false;
                }
            }
            if (!ListenerUtil.mutListener.listen(26242)) {
                if ((ListenerUtil.mutListener.listen(26241) ? (convListView.getCheckedItemCount() >= 1) : (ListenerUtil.mutListener.listen(26240) ? (convListView.getCheckedItemCount() <= 1) : (ListenerUtil.mutListener.listen(26239) ? (convListView.getCheckedItemCount() > 1) : (ListenerUtil.mutListener.listen(26238) ? (convListView.getCheckedItemCount() != 1) : (ListenerUtil.mutListener.listen(26237) ? (convListView.getCheckedItemCount() == 1) : (convListView.getCheckedItemCount() < 1))))))) {
                    return false;
                }
            }
            MenuInflater inflater = mode.getMenuInflater();
            if (!ListenerUtil.mutListener.listen(26244)) {
                if (inflater != null) {
                    if (!ListenerUtil.mutListener.listen(26243)) {
                        inflater.inflate(R.menu.action_compose_message, menu);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(26245)) {
                ConfigUtils.addIconsToOverflowMenu(null, menu);
            }
            if (!ListenerUtil.mutListener.listen(26246)) {
                decItem = menu.findItem(R.id.menu_message_dec);
            }
            if (!ListenerUtil.mutListener.listen(26247)) {
                ackItem = menu.findItem(R.id.menu_message_ack);
            }
            if (!ListenerUtil.mutListener.listen(26248)) {
                logItem = menu.findItem(R.id.menu_message_log);
            }
            if (!ListenerUtil.mutListener.listen(26249)) {
                discardItem = menu.findItem(R.id.menu_message_discard);
            }
            if (!ListenerUtil.mutListener.listen(26250)) {
                forwardItem = menu.findItem(R.id.menu_message_forward);
            }
            if (!ListenerUtil.mutListener.listen(26251)) {
                saveItem = menu.findItem(R.id.menu_message_save);
            }
            if (!ListenerUtil.mutListener.listen(26252)) {
                copyItem = menu.findItem(R.id.menu_message_copy);
            }
            if (!ListenerUtil.mutListener.listen(26253)) {
                qrItem = menu.findItem(R.id.menu_message_qrcode);
            }
            if (!ListenerUtil.mutListener.listen(26254)) {
                shareItem = menu.findItem(R.id.menu_share);
            }
            if (!ListenerUtil.mutListener.listen(26255)) {
                quoteItem = menu.findItem(R.id.menu_message_quote);
            }
            if (!ListenerUtil.mutListener.listen(26256)) {
                showText = menu.findItem(R.id.menu_show_text);
            }
            if (!ListenerUtil.mutListener.listen(26257)) {
                updateActionMenu();
            }
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            final int checked = convListView.getCheckedItemCount();
            if (!ListenerUtil.mutListener.listen(26258)) {
                mode.setTitle(Integer.toString(checked));
            }
            if (!ListenerUtil.mutListener.listen(26259)) {
                updateActionMenu();
            }
            return true;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            if (!ListenerUtil.mutListener.listen(26267)) {
                if ((ListenerUtil.mutListener.listen(26265) ? (selectedMessages == null && (ListenerUtil.mutListener.listen(26264) ? (selectedMessages.size() >= 1) : (ListenerUtil.mutListener.listen(26263) ? (selectedMessages.size() <= 1) : (ListenerUtil.mutListener.listen(26262) ? (selectedMessages.size() > 1) : (ListenerUtil.mutListener.listen(26261) ? (selectedMessages.size() != 1) : (ListenerUtil.mutListener.listen(26260) ? (selectedMessages.size() == 1) : (selectedMessages.size() < 1))))))) : (selectedMessages == null || (ListenerUtil.mutListener.listen(26264) ? (selectedMessages.size() >= 1) : (ListenerUtil.mutListener.listen(26263) ? (selectedMessages.size() <= 1) : (ListenerUtil.mutListener.listen(26262) ? (selectedMessages.size() > 1) : (ListenerUtil.mutListener.listen(26261) ? (selectedMessages.size() != 1) : (ListenerUtil.mutListener.listen(26260) ? (selectedMessages.size() == 1) : (selectedMessages.size() < 1))))))))) {
                    if (!ListenerUtil.mutListener.listen(26266)) {
                        mode.finish();
                    }
                    return true;
                }
            }
            if (!ListenerUtil.mutListener.listen(26290)) {
                switch(item.getItemId()) {
                    case R.id.menu_message_copy:
                        if (!ListenerUtil.mutListener.listen(26268)) {
                            copySelectedMessagesToClipboard();
                        }
                        if (!ListenerUtil.mutListener.listen(26269)) {
                            mode.finish();
                        }
                        break;
                    case R.id.menu_message_discard:
                        if (!ListenerUtil.mutListener.listen(26270)) {
                            deleteSelectedMessages();
                        }
                        break;
                    case R.id.menu_message_forward:
                        if (!ListenerUtil.mutListener.listen(26271)) {
                            startForwardMessage();
                        }
                        if (!ListenerUtil.mutListener.listen(26272)) {
                            mode.finish();
                        }
                        break;
                    case R.id.menu_message_ack:
                        if (!ListenerUtil.mutListener.listen(26273)) {
                            sendUserAck();
                        }
                        if (!ListenerUtil.mutListener.listen(26274)) {
                            mode.finish();
                        }
                        break;
                    case R.id.menu_message_dec:
                        if (!ListenerUtil.mutListener.listen(26275)) {
                            sendUserDec();
                        }
                        if (!ListenerUtil.mutListener.listen(26276)) {
                            mode.finish();
                        }
                        break;
                    case R.id.menu_message_save:
                        if (!ListenerUtil.mutListener.listen(26278)) {
                            if (ConfigUtils.requestStoragePermissions(activity, ComposeMessageFragment.this, PERMISSION_REQUEST_SAVE_MESSAGE)) {
                                if (!ListenerUtil.mutListener.listen(26277)) {
                                    fileService.saveMedia(activity, coordinatorLayout, new CopyOnWriteArrayList<>(selectedMessages), false);
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(26279)) {
                            mode.finish();
                        }
                        break;
                    case R.id.menu_message_log:
                        if (!ListenerUtil.mutListener.listen(26280)) {
                            showMessageLog();
                        }
                        if (!ListenerUtil.mutListener.listen(26281)) {
                            mode.finish();
                        }
                        break;
                    case R.id.menu_message_qrcode:
                        if (!ListenerUtil.mutListener.listen(26282)) {
                            showAsQrCode(((ThreemaToolbarActivity) activity).getToolbar());
                        }
                        if (!ListenerUtil.mutListener.listen(26283)) {
                            mode.finish();
                        }
                        break;
                    case R.id.menu_share:
                        if (!ListenerUtil.mutListener.listen(26284)) {
                            shareMessages();
                        }
                        if (!ListenerUtil.mutListener.listen(26285)) {
                            mode.finish();
                        }
                        break;
                    case R.id.menu_message_quote:
                        if (!ListenerUtil.mutListener.listen(26286)) {
                            startQuoteMode(null, null);
                        }
                        if (!ListenerUtil.mutListener.listen(26287)) {
                            mode.finish();
                        }
                        break;
                    case R.id.menu_show_text:
                        if (!ListenerUtil.mutListener.listen(26288)) {
                            showTextChatBubble();
                        }
                        if (!ListenerUtil.mutListener.listen(26289)) {
                            mode.finish();
                        }
                        break;
                    default:
                        return false;
                }
            }
            return true;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            if (!ListenerUtil.mutListener.listen(26291)) {
                actionMode = null;
            }
            if (!ListenerUtil.mutListener.listen(26292)) {
                longClickItem = AbsListView.INVALID_POSITION;
            }
            if (!ListenerUtil.mutListener.listen(26293)) {
                // handle done button
                convListView.clearChoices();
            }
            if (!ListenerUtil.mutListener.listen(26294)) {
                convListView.requestLayout();
            }
            if (!ListenerUtil.mutListener.listen(26296)) {
                convListView.post(new Runnable() {

                    @Override
                    public void run() {
                        if (!ListenerUtil.mutListener.listen(26295)) {
                            convListView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
                        }
                    }
                });
            }
        }
    }

    private void showTextChatBubble() {
        AbstractMessageModel messageModel = selectedMessages.get(0);
        Intent intent = new Intent(getContext(), TextChatBubbleActivity.class);
        if (!ListenerUtil.mutListener.listen(26297)) {
            IntentDataUtil.append(messageModel, intent);
        }
        if (!ListenerUtil.mutListener.listen(26298)) {
            activity.startActivity(intent);
        }
        if (!ListenerUtil.mutListener.listen(26299)) {
            activity.overridePendingTransition(R.anim.fast_fade_in, R.anim.fast_fade_out);
        }
    }

    private void showAsQrCode(View v) {
        AbstractMessageModel messageModel = selectedMessages.get(0);
        if (!ListenerUtil.mutListener.listen(26302)) {
            if ((ListenerUtil.mutListener.listen(26300) ? (messageModel != null || messageModel.getType() == MessageType.TEXT) : (messageModel != null && messageModel.getType() == MessageType.TEXT))) {
                if (!ListenerUtil.mutListener.listen(26301)) {
                    new QRCodePopup(getContext(), getActivity().getWindow().getDecorView(), getActivity()).show(v, messageModel.getBody());
                }
            }
        }
    }

    private void sendUserAck() {
        if (!ListenerUtil.mutListener.listen(26303)) {
            messageService.sendUserAcknowledgement(selectedMessages.get(0));
        }
        if (!ListenerUtil.mutListener.listen(26304)) {
            Toast.makeText(getActivity(), R.string.message_acknowledged, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     *  Send a Decline Message
     */
    private void sendUserDec() {
        if (!ListenerUtil.mutListener.listen(26305)) {
            messageService.sendUserDecline(selectedMessages.get(0));
        }
        if (!ListenerUtil.mutListener.listen(26306)) {
            Toast.makeText(getActivity(), R.string.message_declined, Toast.LENGTH_SHORT).show();
        }
    }

    public boolean onBackPressed() {
        if (!ListenerUtil.mutListener.listen(26307)) {
            logger.debug("onBackPressed");
        }
        // dismiss emoji keyboard if it's showing instead of leaving activity
        if ((ListenerUtil.mutListener.listen(26308) ? (emojiPicker != null || emojiPicker.isShown()) : (emojiPicker != null && emojiPicker.isShown()))) {
            if (!ListenerUtil.mutListener.listen(26316)) {
                emojiPicker.hide();
            }
            return true;
        } else {
            if ((ListenerUtil.mutListener.listen(26309) ? (mentionPopup != null || mentionPopup.isShowing()) : (mentionPopup != null && mentionPopup.isShowing()))) {
                if (!ListenerUtil.mutListener.listen(26310)) {
                    dismissMentionPopup();
                }
                return true;
            }
            if (searchActionMode != null) {
                if (!ListenerUtil.mutListener.listen(26311)) {
                    searchActionMode.finish();
                }
                return true;
            }
            if (actionMode != null) {
                if (!ListenerUtil.mutListener.listen(26315)) {
                    actionMode.finish();
                }
                return true;
            } else if (ConfigUtils.isTabletLayout()) {
                if (!ListenerUtil.mutListener.listen(26314)) {
                    if (actionBar != null) {
                        if (!ListenerUtil.mutListener.listen(26312)) {
                            actionBar.setDisplayUseLogoEnabled(true);
                        }
                        if (!ListenerUtil.mutListener.listen(26313)) {
                            actionBar.setDisplayShowCustomEnabled(false);
                        }
                    }
                }
            }
            return false;
        }
    }

    private void preserveListInstanceValues() {
        if (!ListenerUtil.mutListener.listen(26317)) {
            // so we don't put it into a bundle in onSaveInstanceState
            listInstancePosition = AbsListView.INVALID_POSITION;
        }
        if (!ListenerUtil.mutListener.listen(26332)) {
            if ((ListenerUtil.mutListener.listen(26318) ? (convListView != null || composeMessageAdapter != null) : (convListView != null && composeMessageAdapter != null))) {
                if (!ListenerUtil.mutListener.listen(26331)) {
                    if (convListView.getLastVisiblePosition() != (ListenerUtil.mutListener.listen(26322) ? (composeMessageAdapter.getCount() % 1) : (ListenerUtil.mutListener.listen(26321) ? (composeMessageAdapter.getCount() / 1) : (ListenerUtil.mutListener.listen(26320) ? (composeMessageAdapter.getCount() * 1) : (ListenerUtil.mutListener.listen(26319) ? (composeMessageAdapter.getCount() + 1) : (composeMessageAdapter.getCount() - 1)))))) {
                        if (!ListenerUtil.mutListener.listen(26323)) {
                            listInstancePosition = convListView.getFirstVisiblePosition();
                        }
                        View v = convListView.getChildAt(0);
                        if (!ListenerUtil.mutListener.listen(26328)) {
                            listInstanceTop = (v == null) ? 0 : ((ListenerUtil.mutListener.listen(26327) ? (v.getTop() % convListView.getPaddingTop()) : (ListenerUtil.mutListener.listen(26326) ? (v.getTop() / convListView.getPaddingTop()) : (ListenerUtil.mutListener.listen(26325) ? (v.getTop() * convListView.getPaddingTop()) : (ListenerUtil.mutListener.listen(26324) ? (v.getTop() + convListView.getPaddingTop()) : (v.getTop() - convListView.getPaddingTop()))))));
                        }
                        if (!ListenerUtil.mutListener.listen(26330)) {
                            if (messageReceiver != null) {
                                if (!ListenerUtil.mutListener.listen(26329)) {
                                    listInstanceReceiverId = messageReceiver.getUniqueIdString();
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (!ListenerUtil.mutListener.listen(26333)) {
            logger.debug("onSaveInstanceState");
        }
        if (!ListenerUtil.mutListener.listen(26334)) {
            // some phones destroy the retained fragment upon going in background so we have to persist some data
            outState.putParcelable(CAMERA_URI, cameraUri);
        }
        if (!ListenerUtil.mutListener.listen(26335)) {
            outState.putInt(ThreemaApplication.INTENT_DATA_GROUP, this.groupId);
        }
        if (!ListenerUtil.mutListener.listen(26336)) {
            outState.putInt(ThreemaApplication.INTENT_DATA_DISTRIBUTION_LIST, this.distributionListId);
        }
        if (!ListenerUtil.mutListener.listen(26337)) {
            outState.putString(ThreemaApplication.INTENT_DATA_CONTACT, this.identity);
        }
        if (!ListenerUtil.mutListener.listen(26338)) {
            super.onSaveInstanceState(outState);
        }
    }

    private Integer getCurrentPageReferenceId() {
        return this.currentPageReferenceId;
    }

    private void configureSearchWidget(final MenuItem menuItem) {
        SearchView searchView = (SearchView) menuItem.getActionView();
        if (!ListenerUtil.mutListener.listen(26360)) {
            if (searchView != null) {
                if (!ListenerUtil.mutListener.listen(26339)) {
                    searchView.setOnQueryTextListener(queryTextListener);
                }
                if (!ListenerUtil.mutListener.listen(26340)) {
                    searchView.setQueryHint(getString(R.string.hint_search_keyword));
                }
                if (!ListenerUtil.mutListener.listen(26341)) {
                    searchView.setIconified(false);
                }
                if (!ListenerUtil.mutListener.listen(26342)) {
                    searchView.setOnCloseListener(() -> {
                        if (searchActionMode != null) {
                            searchActionMode.finish();
                        }
                        return false;
                    });
                }
                LinearLayout linearLayoutOfSearchView = (LinearLayout) searchView.getChildAt(0);
                if (!ListenerUtil.mutListener.listen(26359)) {
                    if (linearLayoutOfSearchView != null) {
                        if (!ListenerUtil.mutListener.listen(26343)) {
                            linearLayoutOfSearchView.setGravity(Gravity.CENTER_VERTICAL);
                        }
                        if (!ListenerUtil.mutListener.listen(26344)) {
                            linearLayoutOfSearchView.setPadding(0, 0, 0, 0);
                        }
                        if (!ListenerUtil.mutListener.listen(26345)) {
                            searchCounter = (TextView) layoutInflater.inflate(R.layout.textview_search_action, null);
                        }
                        if (!ListenerUtil.mutListener.listen(26346)) {
                            linearLayoutOfSearchView.addView(searchCounter);
                        }
                        FrameLayout searchPreviousLayout = (FrameLayout) layoutInflater.inflate(R.layout.button_search_action, null);
                        if (!ListenerUtil.mutListener.listen(26347)) {
                            searchPreviousButton = searchPreviousLayout.findViewById(R.id.search_button);
                        }
                        if (!ListenerUtil.mutListener.listen(26348)) {
                            searchPreviousButton.setImageDrawable(ConfigUtils.getThemedDrawable(activity, R.drawable.ic_keyboard_arrow_down_outline));
                        }
                        if (!ListenerUtil.mutListener.listen(26349)) {
                            searchPreviousButton.setScaleY(-1);
                        }
                        if (!ListenerUtil.mutListener.listen(26351)) {
                            searchPreviousButton.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View v) {
                                    if (!ListenerUtil.mutListener.listen(26350)) {
                                        composeMessageAdapter.previousMatchPosition();
                                    }
                                }
                            });
                        }
                        if (!ListenerUtil.mutListener.listen(26352)) {
                            linearLayoutOfSearchView.addView(searchPreviousLayout);
                        }
                        FrameLayout searchNextLayout = (FrameLayout) layoutInflater.inflate(R.layout.button_search_action, null);
                        if (!ListenerUtil.mutListener.listen(26353)) {
                            searchNextButton = searchNextLayout.findViewById(R.id.search_button);
                        }
                        if (!ListenerUtil.mutListener.listen(26354)) {
                            searchProgress = searchNextLayout.findViewById(R.id.next_progress);
                        }
                        if (!ListenerUtil.mutListener.listen(26355)) {
                            searchNextButton.setImageDrawable(ConfigUtils.getThemedDrawable(activity, R.drawable.ic_keyboard_arrow_down_outline));
                        }
                        if (!ListenerUtil.mutListener.listen(26357)) {
                            searchNextButton.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View v) {
                                    if (!ListenerUtil.mutListener.listen(26356)) {
                                        composeMessageAdapter.nextMatchPosition();
                                    }
                                }
                            });
                        }
                        if (!ListenerUtil.mutListener.listen(26358)) {
                            linearLayoutOfSearchView.addView(searchNextLayout);
                        }
                    }
                }
            }
        }
    }

    private class SearchActionMode implements ActionMode.Callback {

        @SuppressLint("StaticFieldLeak")
        @Override
        public boolean onCreateActionMode(ActionMode mode, final Menu menu) {
            if (!ListenerUtil.mutListener.listen(26361)) {
                composeMessageAdapter.clearFilter();
            }
            if (!ListenerUtil.mutListener.listen(26362)) {
                activity.getMenuInflater().inflate(R.menu.action_compose_message_search, menu);
            }
            final MenuItem item = menu.findItem(R.id.menu_action_search);
            final View actionView = item.getActionView();
            if (!ListenerUtil.mutListener.listen(26363)) {
                item.setActionView(R.layout.item_progress);
            }
            if (!ListenerUtil.mutListener.listen(26364)) {
                item.expandActionView();
            }
            if (!ListenerUtil.mutListener.listen(26366)) {
                if (bottomPanel != null) {
                    if (!ListenerUtil.mutListener.listen(26365)) {
                        bottomPanel.setVisibility(View.GONE);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(26369)) {
                if ((ListenerUtil.mutListener.listen(26367) ? (emojiPicker != null || emojiPicker.isShown()) : (emojiPicker != null && emojiPicker.isShown()))) {
                    if (!ListenerUtil.mutListener.listen(26368)) {
                        emojiPicker.hide();
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(26370)) {
                dismissMentionPopup();
            }
            if (!ListenerUtil.mutListener.listen(26379)) {
                // load all records
                new AsyncTask<Void, Void, Void>() {

                    List<AbstractMessageModel> messageModels;

                    @Override
                    protected Void doInBackground(Void... params) {
                        if (!ListenerUtil.mutListener.listen(26371)) {
                            messageModels = getAllRecords();
                        }
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void result) {
                        if (!ListenerUtil.mutListener.listen(26378)) {
                            if ((ListenerUtil.mutListener.listen(26372) ? (messageModels != null || isAdded()) : (messageModels != null && isAdded()))) {
                                if (!ListenerUtil.mutListener.listen(26373)) {
                                    item.collapseActionView();
                                }
                                if (!ListenerUtil.mutListener.listen(26374)) {
                                    item.setActionView(actionView);
                                }
                                if (!ListenerUtil.mutListener.listen(26375)) {
                                    configureSearchWidget(menu.findItem(R.id.menu_action_search));
                                }
                                if (!ListenerUtil.mutListener.listen(26376)) {
                                    insertToList(messageModels, true, true, true);
                                }
                                if (!ListenerUtil.mutListener.listen(26377)) {
                                    convListView.setSelection(Integer.MAX_VALUE);
                                }
                            }
                        }
                    }
                }.execute();
            }
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            if (!ListenerUtil.mutListener.listen(26380)) {
                searchCounter = null;
            }
            if (!ListenerUtil.mutListener.listen(26381)) {
                searchActionMode = null;
            }
            if (!ListenerUtil.mutListener.listen(26383)) {
                if (composeMessageAdapter != null) {
                    if (!ListenerUtil.mutListener.listen(26382)) {
                        composeMessageAdapter.clearFilter();
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(26385)) {
                if (bottomPanel != null) {
                    if (!ListenerUtil.mutListener.listen(26384)) {
                        bottomPanel.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
    }

    private void updateToolBarTitleInUIThread() {
        if (!ListenerUtil.mutListener.listen(26386)) {
            RuntimeUtil.runOnUiThread(this::updateToolbarTitle);
        }
    }

    @UiThread
    private void updateContactModelData(final ContactModel contactModel) {
        if (!ListenerUtil.mutListener.listen(26388)) {
            // update header
            if (contactModel.getIdentity().equals(identity)) {
                if (!ListenerUtil.mutListener.listen(26387)) {
                    updateToolbarTitle();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(26390)) {
            if (composeMessageAdapter != null) {
                if (!ListenerUtil.mutListener.listen(26389)) {
                    composeMessageAdapter.resetCachedContactModelData(contactModel);
                }
            }
        }
    }

    protected final boolean requiredInstances() {
        if (!ListenerUtil.mutListener.listen(26392)) {
            if (!this.checkInstances()) {
                if (!ListenerUtil.mutListener.listen(26391)) {
                    this.instantiate();
                }
            }
        }
        return this.checkInstances();
    }

    protected boolean checkInstances() {
        return TestUtil.required(this.preferenceService, this.userService, this.contactService, this.groupService, this.messageService, this.fileService, this.notificationService, this.distributionListService, this.messagePlayerService, this.blackListIdentityService, this.ballotService, this.conversationService, this.deviceService, this.wallpaperService, this.mutedChatsListService, this.ringtoneService, this.voipStateService, this.shortcutService);
    }

    protected void instantiate() {
        ServiceManager serviceManager = ThreemaApplication.getServiceManager();
        if (!ListenerUtil.mutListener.listen(26416)) {
            if (serviceManager != null) {
                if (!ListenerUtil.mutListener.listen(26393)) {
                    this.preferenceService = serviceManager.getPreferenceService();
                }
                try {
                    if (!ListenerUtil.mutListener.listen(26395)) {
                        this.userService = serviceManager.getUserService();
                    }
                    if (!ListenerUtil.mutListener.listen(26396)) {
                        this.contactService = serviceManager.getContactService();
                    }
                    if (!ListenerUtil.mutListener.listen(26397)) {
                        this.groupService = serviceManager.getGroupService();
                    }
                    if (!ListenerUtil.mutListener.listen(26398)) {
                        this.messageService = serviceManager.getMessageService();
                    }
                    if (!ListenerUtil.mutListener.listen(26399)) {
                        this.fileService = serviceManager.getFileService();
                    }
                    if (!ListenerUtil.mutListener.listen(26400)) {
                        this.notificationService = serviceManager.getNotificationService();
                    }
                    if (!ListenerUtil.mutListener.listen(26401)) {
                        this.distributionListService = serviceManager.getDistributionListService();
                    }
                    if (!ListenerUtil.mutListener.listen(26402)) {
                        this.messagePlayerService = serviceManager.getMessagePlayerService();
                    }
                    if (!ListenerUtil.mutListener.listen(26403)) {
                        this.blackListIdentityService = serviceManager.getBlackListService();
                    }
                    if (!ListenerUtil.mutListener.listen(26404)) {
                        this.ballotService = serviceManager.getBallotService();
                    }
                    if (!ListenerUtil.mutListener.listen(26405)) {
                        this.conversationService = serviceManager.getConversationService();
                    }
                    if (!ListenerUtil.mutListener.listen(26406)) {
                        this.deviceService = serviceManager.getDeviceService();
                    }
                    if (!ListenerUtil.mutListener.listen(26407)) {
                        this.wallpaperService = serviceManager.getWallpaperService();
                    }
                    if (!ListenerUtil.mutListener.listen(26408)) {
                        this.mutedChatsListService = serviceManager.getMutedChatsListService();
                    }
                    if (!ListenerUtil.mutListener.listen(26409)) {
                        this.mentionOnlyChatsListService = serviceManager.getMentionOnlyChatsListService();
                    }
                    if (!ListenerUtil.mutListener.listen(26410)) {
                        this.hiddenChatsListService = serviceManager.getHiddenChatsListService();
                    }
                    if (!ListenerUtil.mutListener.listen(26411)) {
                        this.ringtoneService = serviceManager.getRingtoneService();
                    }
                    if (!ListenerUtil.mutListener.listen(26412)) {
                        this.voipStateService = serviceManager.getVoipStateService();
                    }
                    if (!ListenerUtil.mutListener.listen(26413)) {
                        this.shortcutService = serviceManager.getShortcutService();
                    }
                    if (!ListenerUtil.mutListener.listen(26414)) {
                        this.downloadService = serviceManager.getDownloadService();
                    }
                    if (!ListenerUtil.mutListener.listen(26415)) {
                        this.licenseService = serviceManager.getLicenseService();
                    }
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(26394)) {
                        LogUtil.exception(e, activity);
                    }
                }
            }
        }
    }

    // Dialog callbacks
    @Override
    public void onYes(String tag, Object data) {
        if (!ListenerUtil.mutListener.listen(26427)) {
            switch(tag) {
                case CONFIRM_TAG_DELETE_DISTRIBUTION_LIST:
                    final DistributionListModel dmodel = (DistributionListModel) data;
                    if (!ListenerUtil.mutListener.listen(26420)) {
                        if (dmodel != null) {
                            if (!ListenerUtil.mutListener.listen(26419)) {
                                new Thread(new Runnable() {

                                    @Override
                                    public void run() {
                                        if (!ListenerUtil.mutListener.listen(26417)) {
                                            distributionListService.remove(dmodel);
                                        }
                                        if (!ListenerUtil.mutListener.listen(26418)) {
                                            RuntimeUtil.runOnUiThread(() -> activity.finish());
                                        }
                                    }
                                }).start();
                            }
                        }
                    }
                    break;
                case ThreemaApplication.CONFIRM_TAG_CLOSE_BALLOT:
                    if (!ListenerUtil.mutListener.listen(26421)) {
                        BallotUtil.closeBallot((AppCompatActivity) getActivity(), (BallotModel) data, ballotService);
                    }
                    break;
                case DIALOG_TAG_CONFIRM_CALL:
                    if (!ListenerUtil.mutListener.listen(26422)) {
                        VoipUtil.initiateCall((AppCompatActivity) getActivity(), contactModel, false, null);
                    }
                    break;
                case DIALOG_TAG_EMPTY_CHAT:
                    if (!ListenerUtil.mutListener.listen(26423)) {
                        emptyChat();
                    }
                    break;
                case DIALOG_TAG_CONFIRM_BLOCK:
                    if (!ListenerUtil.mutListener.listen(26424)) {
                        blackListIdentityService.toggle(activity, contactModel);
                    }
                    if (!ListenerUtil.mutListener.listen(26425)) {
                        updateBlockMenu();
                    }
                    break;
                case DIALOG_TAG_CONFIRM_LINK:
                    Uri uri = (Uri) data;
                    if (!ListenerUtil.mutListener.listen(26426)) {
                        LinkifyUtil.getInstance().openLink(getContext(), uri);
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
    public void onEmojiPickerOpen() {
    }

    @Override
    public void onEmojiPickerClose() {
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (!ListenerUtil.mutListener.listen(26448)) {
            if ((ListenerUtil.mutListener.listen(26433) ? ((ListenerUtil.mutListener.listen(26432) ? (grantResults.length >= 0) : (ListenerUtil.mutListener.listen(26431) ? (grantResults.length <= 0) : (ListenerUtil.mutListener.listen(26430) ? (grantResults.length < 0) : (ListenerUtil.mutListener.listen(26429) ? (grantResults.length != 0) : (ListenerUtil.mutListener.listen(26428) ? (grantResults.length == 0) : (grantResults.length > 0)))))) || grantResults[0] == PackageManager.PERMISSION_GRANTED) : ((ListenerUtil.mutListener.listen(26432) ? (grantResults.length >= 0) : (ListenerUtil.mutListener.listen(26431) ? (grantResults.length <= 0) : (ListenerUtil.mutListener.listen(26430) ? (grantResults.length < 0) : (ListenerUtil.mutListener.listen(26429) ? (grantResults.length != 0) : (ListenerUtil.mutListener.listen(26428) ? (grantResults.length == 0) : (grantResults.length > 0)))))) && grantResults[0] == PackageManager.PERMISSION_GRANTED))) {
                if (!ListenerUtil.mutListener.listen(26447)) {
                    switch(requestCode) {
                        case PERMISSION_REQUEST_SAVE_MESSAGE:
                            if (!ListenerUtil.mutListener.listen(26443)) {
                                fileService.saveMedia(activity, coordinatorLayout, new CopyOnWriteArrayList<>(selectedMessages), false);
                            }
                            break;
                        case PERMISSION_REQUEST_ATTACH_VOICE_MESSAGE:
                            if (!ListenerUtil.mutListener.listen(26444)) {
                                attachVoiceMessage();
                            }
                            break;
                        case PERMISSION_REQUEST_ATTACH_CAMERA:
                            if (!ListenerUtil.mutListener.listen(26445)) {
                                updateCameraButton();
                            }
                            if (!ListenerUtil.mutListener.listen(26446)) {
                                attachCamera();
                            }
                            break;
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(26442)) {
                    switch(requestCode) {
                        case PERMISSION_REQUEST_SAVE_MESSAGE:
                            if (!ListenerUtil.mutListener.listen(26435)) {
                                if (!shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                                    if (!ListenerUtil.mutListener.listen(26434)) {
                                        showPermissionRationale(R.string.permission_storage_required);
                                    }
                                }
                            }
                            break;
                        case PERMISSION_REQUEST_ATTACH_VOICE_MESSAGE:
                            if (!ListenerUtil.mutListener.listen(26437)) {
                                if (!shouldShowRequestPermissionRationale(Manifest.permission.RECORD_AUDIO)) {
                                    if (!ListenerUtil.mutListener.listen(26436)) {
                                        showPermissionRationale(R.string.permission_record_audio_required);
                                    }
                                }
                            }
                            break;
                        case PERMISSION_REQUEST_ATTACH_CAMERA:
                        case PERMISSION_REQUEST_ATTACH_CAMERA_VIDEO:
                            if (!ListenerUtil.mutListener.listen(26438)) {
                                preferenceService.setCameraPermissionRequestShown(true);
                            }
                            if (!ListenerUtil.mutListener.listen(26440)) {
                                if (!shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
                                    if (!ListenerUtil.mutListener.listen(26439)) {
                                        showPermissionRationale(R.string.permission_camera_photo_required);
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(26441)) {
                                updateCameraButton();
                            }
                            break;
                    }
                }
            }
        }
    }

    private void dismissMentionPopup() {
        if (!ListenerUtil.mutListener.listen(26451)) {
            if (this.mentionPopup != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(26450)) {
                        this.mentionPopup.dismiss();
                    }
                } catch (final IllegalArgumentException e) {
                } finally {
                    if (!ListenerUtil.mutListener.listen(26449)) {
                        this.mentionPopup = null;
                    }
                }
            }
        }
    }

    private void dismissTooltipPopup(TooltipPopup tooltipPopup, boolean immediate) {
        try {
            if (!ListenerUtil.mutListener.listen(26453)) {
                if (tooltipPopup != null) {
                    if (!ListenerUtil.mutListener.listen(26452)) {
                        tooltipPopup.dismiss(immediate);
                    }
                }
            }
        } catch (final IllegalArgumentException e) {
        }
    }

    public void markAsRead() {
        if (!ListenerUtil.mutListener.listen(26463)) {
            if (messageReceiver != null) {
                try {
                    List<AbstractMessageModel> unreadMessages = messageReceiver.getUnreadMessages();
                    if (!ListenerUtil.mutListener.listen(26462)) {
                        if ((ListenerUtil.mutListener.listen(26460) ? (unreadMessages != null || (ListenerUtil.mutListener.listen(26459) ? (unreadMessages.size() >= 0) : (ListenerUtil.mutListener.listen(26458) ? (unreadMessages.size() <= 0) : (ListenerUtil.mutListener.listen(26457) ? (unreadMessages.size() < 0) : (ListenerUtil.mutListener.listen(26456) ? (unreadMessages.size() != 0) : (ListenerUtil.mutListener.listen(26455) ? (unreadMessages.size() == 0) : (unreadMessages.size() > 0))))))) : (unreadMessages != null && (ListenerUtil.mutListener.listen(26459) ? (unreadMessages.size() >= 0) : (ListenerUtil.mutListener.listen(26458) ? (unreadMessages.size() <= 0) : (ListenerUtil.mutListener.listen(26457) ? (unreadMessages.size() < 0) : (ListenerUtil.mutListener.listen(26456) ? (unreadMessages.size() != 0) : (ListenerUtil.mutListener.listen(26455) ? (unreadMessages.size() == 0) : (unreadMessages.size() > 0))))))))) {
                            if (!ListenerUtil.mutListener.listen(26461)) {
                                new Thread(new ReadMessagesRoutine(unreadMessages, this.messageService, this.notificationService)).start();
                            }
                        }
                    }
                } catch (SQLException e) {
                    if (!ListenerUtil.mutListener.listen(26454)) {
                        logger.error("Exception", e);
                    }
                }
            }
        }
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        if (!ListenerUtil.mutListener.listen(26464)) {
            super.onConfigurationChanged(newConfig);
        }
        if (!ListenerUtil.mutListener.listen(26467)) {
            if ((ListenerUtil.mutListener.listen(26465) ? (this.emojiPicker != null || this.emojiPicker.isShown()) : (this.emojiPicker != null && this.emojiPicker.isShown()))) {
                if (!ListenerUtil.mutListener.listen(26466)) {
                    this.emojiPicker.hide();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(26468)) {
            EditTextUtil.hideSoftKeyboard(this.messageText);
        }
        if (!ListenerUtil.mutListener.listen(26469)) {
            dismissMentionPopup();
        }
        if (!ListenerUtil.mutListener.listen(26470)) {
            dismissTooltipPopup(workTooltipPopup, true);
        }
        if (!ListenerUtil.mutListener.listen(26471)) {
            workTooltipPopup = null;
        }
        if (!ListenerUtil.mutListener.listen(26476)) {
            if (ConfigUtils.isTabletLayout()) {
                if (!ListenerUtil.mutListener.listen(26474)) {
                    // make sure layout changes after rotate are reflected in thumbnail size etc.
                    saveMessageDraft();
                }
                if (!ListenerUtil.mutListener.listen(26475)) {
                    this.handleIntent(activity.getIntent());
                }
            } else {
                if (!ListenerUtil.mutListener.listen(26473)) {
                    if (isAdded()) {
                        if (!ListenerUtil.mutListener.listen(26472)) {
                            // refresh wallpaper to reflect orientation change
                            this.wallpaperService.setupWallpaperBitmap(this.messageReceiver, this.wallpaperView, ConfigUtils.isLandscape(activity));
                        }
                    }
                }
            }
        }
    }

    private void restoreMessageDraft() {
        if (!ListenerUtil.mutListener.listen(26483)) {
            if ((ListenerUtil.mutListener.listen(26478) ? ((ListenerUtil.mutListener.listen(26477) ? (this.messageReceiver != null || this.messageText != null) : (this.messageReceiver != null && this.messageText != null)) || TestUtil.empty(this.messageText.getText())) : ((ListenerUtil.mutListener.listen(26477) ? (this.messageReceiver != null || this.messageText != null) : (this.messageReceiver != null && this.messageText != null)) && TestUtil.empty(this.messageText.getText())))) {
                String messageDraft = ThreemaApplication.getMessageDraft(this.messageReceiver.getUniqueIdString());
                if (!ListenerUtil.mutListener.listen(26482)) {
                    if (!TextUtils.isEmpty(messageDraft)) {
                        if (!ListenerUtil.mutListener.listen(26480)) {
                            this.messageText.setText("");
                        }
                        if (!ListenerUtil.mutListener.listen(26481)) {
                            this.messageText.append(messageDraft);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(26479)) {
                            this.messageText.setText("");
                        }
                    }
                }
            }
        }
    }

    private void saveMessageDraft() {
        if (!ListenerUtil.mutListener.listen(26490)) {
            if (this.messageReceiver != null) {
                String draft = ThreemaApplication.getMessageDraft(this.messageReceiver.getUniqueIdString());
                if (!ListenerUtil.mutListener.listen(26485)) {
                    if (this.messageText.getText() != null) {
                        if (!ListenerUtil.mutListener.listen(26484)) {
                            ThreemaApplication.putMessageDraft(this.messageReceiver.getUniqueIdString(), this.messageText.getText().toString());
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(26489)) {
                    if ((ListenerUtil.mutListener.listen(26486) ? (!TestUtil.empty(this.messageText.getText()) && !TestUtil.empty(draft)) : (!TestUtil.empty(this.messageText.getText()) || !TestUtil.empty(draft)))) {
                        if (!ListenerUtil.mutListener.listen(26488)) {
                            ListenerManager.conversationListeners.handle(new ListenerManager.HandleListener<ConversationListener>() {

                                @Override
                                public void handle(ConversationListener listener) {
                                    if (!ListenerUtil.mutListener.listen(26487)) {
                                        listener.onModifiedAll();
                                    }
                                }
                            });
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onDismissed() {
        if (!ListenerUtil.mutListener.listen(26491)) {
            updateMenus();
        }
    }

    @Override
    public void onKeyboardHidden() {
        if (!ListenerUtil.mutListener.listen(26496)) {
            if ((ListenerUtil.mutListener.listen(26492) ? (getActivity() != null || isAdded()) : (getActivity() != null && isAdded()))) {
                if (!ListenerUtil.mutListener.listen(26493)) {
                    dismissMentionPopup();
                }
                if (!ListenerUtil.mutListener.listen(26494)) {
                    dismissTooltipPopup(workTooltipPopup, false);
                }
                if (!ListenerUtil.mutListener.listen(26495)) {
                    workTooltipPopup = null;
                }
            }
        }
    }

    @Override
    public void onKeyboardShown() {
        if (!ListenerUtil.mutListener.listen(26499)) {
            if ((ListenerUtil.mutListener.listen(26497) ? (emojiPicker != null || emojiPicker.isShown()) : (emojiPicker != null && emojiPicker.isShown()))) {
                if (!ListenerUtil.mutListener.listen(26498)) {
                    emojiPicker.hide();
                }
            }
        }
    }
}
