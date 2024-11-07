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

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.format.DateUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.lang.ref.WeakReference;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.RejectedExecutionException;
import androidx.annotation.AnyThread;
import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LifecycleOwner;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import ch.threema.app.BuildFlavor;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.activities.wizard.WizardBaseActivity;
import ch.threema.app.activities.wizard.WizardStartActivity;
import ch.threema.app.archive.ArchiveActivity;
import ch.threema.app.dialogs.GenericAlertDialog;
import ch.threema.app.dialogs.GenericProgressDialog;
import ch.threema.app.dialogs.SMSVerificationDialog;
import ch.threema.app.dialogs.ShowOnceDialog;
import ch.threema.app.dialogs.SimpleStringAlertDialog;
import ch.threema.app.exceptions.EntryAlreadyExistsException;
import ch.threema.app.exceptions.FileSystemNotPresentException;
import ch.threema.app.fragments.ContactsSectionFragment;
import ch.threema.app.fragments.MessageSectionFragment;
import ch.threema.app.fragments.MyIDFragment;
import ch.threema.app.globalsearch.GlobalSearchActivity;
import ch.threema.app.listeners.AppIconListener;
import ch.threema.app.listeners.ContactCountListener;
import ch.threema.app.listeners.ConversationListener;
import ch.threema.app.listeners.MessageListener;
import ch.threema.app.listeners.ProfileListener;
import ch.threema.app.listeners.SMSVerificationListener;
import ch.threema.app.listeners.VoipCallListener;
import ch.threema.app.managers.ListenerManager;
import ch.threema.app.managers.ServiceManager;
import ch.threema.app.messagereceiver.MessageReceiver;
import ch.threema.app.preference.SettingsActivity;
import ch.threema.app.push.PushService;
import ch.threema.app.routines.CheckLicenseRoutine;
import ch.threema.app.services.ContactService;
import ch.threema.app.services.ConversationService;
import ch.threema.app.services.ConversationTagService;
import ch.threema.app.services.DeviceService;
import ch.threema.app.services.FileService;
import ch.threema.app.services.LifetimeService;
import ch.threema.app.services.LockAppService;
import ch.threema.app.services.MessageService;
import ch.threema.app.services.NotificationService;
import ch.threema.app.services.PassphraseService;
import ch.threema.app.services.PreferenceService;
import ch.threema.app.services.UpdateSystemService;
import ch.threema.app.services.UserService;
import ch.threema.app.services.license.LicenseService;
import ch.threema.app.threemasafe.ThreemaSafeMDMConfig;
import ch.threema.app.threemasafe.ThreemaSafeService;
import ch.threema.app.ui.IdentityPopup;
import ch.threema.app.ui.TooltipPopup;
import ch.threema.app.utils.AnimationUtil;
import ch.threema.app.utils.AppRestrictionUtil;
import ch.threema.app.utils.BitmapUtil;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.ConnectionIndicatorUtil;
import ch.threema.app.utils.DialogUtil;
import ch.threema.app.utils.IntentDataUtil;
import ch.threema.app.utils.RuntimeUtil;
import ch.threema.app.utils.StateBitmapUtil;
import ch.threema.app.utils.TestUtil;
import ch.threema.app.voip.activities.CallActivity;
import ch.threema.app.voip.services.VoipCallService;
import ch.threema.app.webclient.activities.SessionsActivity;
import ch.threema.client.ConnectionState;
import ch.threema.client.ConnectionStateListener;
import ch.threema.client.LinkMobileNoException;
import ch.threema.client.ThreemaConnection;
import ch.threema.localcrypto.MasterKey;
import ch.threema.storage.models.AbstractMessageModel;
import ch.threema.storage.models.ContactModel;
import ch.threema.storage.models.ConversationModel;
import static ch.threema.app.services.ConversationTagServiceImpl.FIXED_TAG_UNREAD;
import static ch.threema.app.voip.services.VoipCallService.ACTION_HANGUP;
import static ch.threema.app.voip.services.VoipCallService.EXTRA_ACTIVITY_MODE;
import static ch.threema.app.voip.services.VoipCallService.EXTRA_CONTACT_IDENTITY;
import static ch.threema.app.voip.services.VoipCallService.EXTRA_START_TIME;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class HomeActivity extends ThreemaAppCompatActivity implements SMSVerificationDialog.SMSVerificationDialogCallback, GenericAlertDialog.DialogClickListener, LifecycleOwner {

    private static final Logger logger = LoggerFactory.getLogger(HomeActivity.class);

    private static final String THREEMA_CHANNEL_IDENTITY = "*THREEMA";

    private static final String THREEMA_CHANNEL_INFO_COMMAND = "Info";

    private static final String THREEMA_CHANNEL_START_NEWS_COMMAND = "Start News";

    private static final String THREEMA_CHANNEL_START_ANDROID_COMMAND = "Start Android";

    private static final String THREEMA_CHANNEL_WORK_COMMAND = "Start Threema Work";

    private static final long PHONE_REQUEST_DELAY = 10 * DateUtils.MINUTE_IN_MILLIS;

    private static final String DIALOG_TAG_VERIFY_CODE = "vc";

    private static final String DIALOG_TAG_VERIFY_CODE_CONFIRM = "vcc";

    private static final String DIALOG_TAG_CANCEL_VERIFY = "cv";

    private static final String DIALOG_TAG_MASTERKEY_LOCKED = "mkl";

    private static final String DIALOG_TAG_SERIAL_LOCKED = "sll";

    private static final String DIALOG_TAG_ENABLE_POLLING = "enp";

    private static final String DIALOG_TAG_FINISH_UP = "fup";

    private static final String DIALOG_TAG_THREEMA_CHANNEL_VERIFY = "cvf";

    private static final String DIALOG_TAG_UPDATING = "updating";

    private static final String FRAGMENT_TAG_MESSAGES = "0";

    private static final String FRAGMENT_TAG_CONTACTS = "1";

    private static final String FRAGMENT_TAG_PROFILE = "2";

    private static final String BUNDLE_CURRENT_FRAGMENT_TAG = "currentFragmentTag";

    private static final int REQUEST_CODE_WHATSNEW = 41912;

    private static final String TOOLTIP_TAG = "tooltip_home_pref";

    public static final String EXTRA_SHOW_CONTACTS = "show_contacts";

    private ActionBar actionBar;

    private boolean isLicenseCheckStarted = false, isInitialized = false, isWhatsNewShown = false;

    private Toolbar toolbar;

    private View connectionIndicator;

    private LinearLayout noticeLayout, ongoingCallNoticeLayout;

    private ServiceManager serviceManager;

    private NotificationService notificationService;

    private UserService userService;

    private ContactService contactService;

    private LockAppService lockAppService;

    private PreferenceService preferenceService;

    private ConversationService conversationService;

    private final ArrayList<AbstractMessageModel> unsentMessages = new ArrayList<>();

    private BroadcastReceiver checkLicenseBroadcastReceiver = null;

    private final BroadcastReceiver currentCheckAppReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, final Intent intent) {
            if (!ListenerUtil.mutListener.listen(3233)) {
                RuntimeUtil.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        if (!ListenerUtil.mutListener.listen(3232)) {
                            if (intent.getAction().equals(IntentDataUtil.ACTION_LICENSE_NOT_ALLOWED)) {
                                if (!ListenerUtil.mutListener.listen(3231)) {
                                    if (Arrays.asList(BuildFlavor.LicenseType.SERIAL, BuildFlavor.LicenseType.GOOGLE_WORK, BuildFlavor.LicenseType.HMS_WORK).contains(BuildFlavor.getLicenseType())) {
                                        if (!ListenerUtil.mutListener.listen(3230)) {
                                            // show enter serial stuff
                                            startActivityForResult(new Intent(HomeActivity.this, EnterSerialActivity.class), ThreemaActivity.ACTIVITY_ID_ENTER_SERIAL);
                                        }
                                    } else {
                                        if (!ListenerUtil.mutListener.listen(3229)) {
                                            showErrorTextAndExit(IntentDataUtil.getMessage(intent));
                                        }
                                    }
                                }
                            } else if ((ListenerUtil.mutListener.listen(3221) ? ((ListenerUtil.mutListener.listen(3220) ? ((ListenerUtil.mutListener.listen(3219) ? (intent.getAction().equals(IntentDataUtil.ACTION_UPDATE_AVAILABLE) || !ConfigUtils.isWorkBuild()) : (intent.getAction().equals(IntentDataUtil.ACTION_UPDATE_AVAILABLE) && !ConfigUtils.isWorkBuild())) || userService != null) : ((ListenerUtil.mutListener.listen(3219) ? (intent.getAction().equals(IntentDataUtil.ACTION_UPDATE_AVAILABLE) || !ConfigUtils.isWorkBuild()) : (intent.getAction().equals(IntentDataUtil.ACTION_UPDATE_AVAILABLE) && !ConfigUtils.isWorkBuild())) && userService != null)) || userService.hasIdentity()) : ((ListenerUtil.mutListener.listen(3220) ? ((ListenerUtil.mutListener.listen(3219) ? (intent.getAction().equals(IntentDataUtil.ACTION_UPDATE_AVAILABLE) || !ConfigUtils.isWorkBuild()) : (intent.getAction().equals(IntentDataUtil.ACTION_UPDATE_AVAILABLE) && !ConfigUtils.isWorkBuild())) || userService != null) : ((ListenerUtil.mutListener.listen(3219) ? (intent.getAction().equals(IntentDataUtil.ACTION_UPDATE_AVAILABLE) || !ConfigUtils.isWorkBuild()) : (intent.getAction().equals(IntentDataUtil.ACTION_UPDATE_AVAILABLE) && !ConfigUtils.isWorkBuild())) && userService != null)) && userService.hasIdentity()))) {
                                if (!ListenerUtil.mutListener.listen(3228)) {
                                    new Handler().postDelayed(new Runnable() {

                                        @Override
                                        public void run() {
                                            Intent dialogIntent = new Intent(intent);
                                            if (!ListenerUtil.mutListener.listen(3222)) {
                                                dialogIntent.setClass(HomeActivity.this, DownloadApkActivity.class);
                                            }
                                            if (!ListenerUtil.mutListener.listen(3223)) {
                                                startActivity(dialogIntent);
                                            }
                                        }
                                    }, (ListenerUtil.mutListener.listen(3227) ? (DateUtils.SECOND_IN_MILLIS % 5) : (ListenerUtil.mutListener.listen(3226) ? (DateUtils.SECOND_IN_MILLIS / 5) : (ListenerUtil.mutListener.listen(3225) ? (DateUtils.SECOND_IN_MILLIS - 5) : (ListenerUtil.mutListener.listen(3224) ? (DateUtils.SECOND_IN_MILLIS + 5) : (DateUtils.SECOND_IN_MILLIS * 5))))));
                                }
                            }
                        }
                    }
                });
            }
        }
    };

    private BottomNavigationView bottomNavigationView;

    private View mainContent;

    private TooltipPopup tooltipPopup = null;

    private String currentFragmentTag;

    private static class UpdateBottomNavigationBadgeTask extends AsyncTask<Void, Void, Integer> {

        private ConversationTagService conversationTagService = null;

        private final WeakReference<Activity> activityWeakReference;

        UpdateBottomNavigationBadgeTask(Activity activity) {
            activityWeakReference = new WeakReference<>(activity);
            try {
                if (!ListenerUtil.mutListener.listen(3235)) {
                    conversationTagService = Objects.requireNonNull(ThreemaApplication.getServiceManager()).getConversationTagService();
                }
            } catch (Exception e) {
                if (!ListenerUtil.mutListener.listen(3234)) {
                    logger.error("UpdateBottomNav", e);
                }
            }
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            ConversationService conversationService;
            try {
                conversationService = ThreemaApplication.getServiceManager().getConversationService();
            } catch (Exception e) {
                return 0;
            }
            if (!ListenerUtil.mutListener.listen(3236)) {
                if (conversationService == null) {
                    return 0;
                }
            }
            List<ConversationModel> conversationModels = conversationService.getAll(false, new ConversationService.Filter() {

                @Override
                public boolean onlyUnread() {
                    return true;
                }

                @Override
                public boolean noDistributionLists() {
                    return false;
                }

                @Override
                public boolean noHiddenChats() {
                    return false;
                }

                @Override
                public boolean noInvalid() {
                    return false;
                }
            });
            int unread = 0;
            if (!ListenerUtil.mutListener.listen(3238)) {
                {
                    long _loopCounter19 = 0;
                    for (ConversationModel conversationModel : conversationModels) {
                        ListenerUtil.loopListener.listen("_loopCounter19", ++_loopCounter19);
                        if (!ListenerUtil.mutListener.listen(3237)) {
                            unread += conversationModel.getUnreadCount();
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(3240)) {
                if (conversationTagService != null) {
                    if (!ListenerUtil.mutListener.listen(3239)) {
                        unread += conversationTagService.getCount(conversationTagService.getTagModel(FIXED_TAG_UNREAD));
                    }
                }
            }
            return unread;
        }

        @Override
        protected void onPostExecute(Integer count) {
            if (!ListenerUtil.mutListener.listen(3251)) {
                if (activityWeakReference.get() != null) {
                    BottomNavigationView bottomNavigationView = activityWeakReference.get().findViewById(R.id.bottom_navigation);
                    if (!ListenerUtil.mutListener.listen(3250)) {
                        if (bottomNavigationView != null) {
                            BadgeDrawable badgeDrawable = bottomNavigationView.getOrCreateBadge(R.id.messages);
                            if (!ListenerUtil.mutListener.listen(3242)) {
                                if (badgeDrawable.getVerticalOffset() == 0) {
                                    if (!ListenerUtil.mutListener.listen(3241)) {
                                        badgeDrawable.setVerticalOffset(activityWeakReference.get().getResources().getDimensionPixelSize(R.dimen.bottom_nav_badge_offset_vertical));
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(3243)) {
                                badgeDrawable.setNumber(count);
                            }
                            if (!ListenerUtil.mutListener.listen(3249)) {
                                badgeDrawable.setVisible((ListenerUtil.mutListener.listen(3248) ? (count >= 0) : (ListenerUtil.mutListener.listen(3247) ? (count <= 0) : (ListenerUtil.mutListener.listen(3246) ? (count < 0) : (ListenerUtil.mutListener.listen(3245) ? (count != 0) : (ListenerUtil.mutListener.listen(3244) ? (count == 0) : (count > 0)))))));
                            }
                        }
                    }
                }
            }
        }
    }

    private final ConnectionStateListener connectionStateListener = new ConnectionStateListener() {

        @Override
        public void updateConnectionState(final ConnectionState connectionState, InetSocketAddress address) {
            if (!ListenerUtil.mutListener.listen(3252)) {
                updateConnectionIndicator(connectionState);
            }
        }
    };

    private void updateUnsentMessagesList(AbstractMessageModel modifiedMessageModel, boolean add) {
        int numCurrentUnsent = unsentMessages.size();
        synchronized (unsentMessages) {
            String uid = modifiedMessageModel.getUid();
            Iterator<AbstractMessageModel> iterator = unsentMessages.iterator();
            if (!ListenerUtil.mutListener.listen(3255)) {
                {
                    long _loopCounter20 = 0;
                    while (iterator.hasNext()) {
                        ListenerUtil.loopListener.listen("_loopCounter20", ++_loopCounter20);
                        AbstractMessageModel unsentMessage = iterator.next();
                        if (!ListenerUtil.mutListener.listen(3254)) {
                            if (TestUtil.compare(unsentMessage.getUid(), uid)) {
                                if (!ListenerUtil.mutListener.listen(3253)) {
                                    iterator.remove();
                                }
                            }
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(3257)) {
                if (add) {
                    if (!ListenerUtil.mutListener.listen(3256)) {
                        unsentMessages.add(modifiedMessageModel);
                    }
                }
            }
            int numNewUnsent = unsentMessages.size();
            if (!ListenerUtil.mutListener.listen(3271)) {
                if ((ListenerUtil.mutListener.listen(3269) ? (notificationService != null || !((ListenerUtil.mutListener.listen(3268) ? ((ListenerUtil.mutListener.listen(3262) ? (numCurrentUnsent >= 0) : (ListenerUtil.mutListener.listen(3261) ? (numCurrentUnsent <= 0) : (ListenerUtil.mutListener.listen(3260) ? (numCurrentUnsent > 0) : (ListenerUtil.mutListener.listen(3259) ? (numCurrentUnsent < 0) : (ListenerUtil.mutListener.listen(3258) ? (numCurrentUnsent != 0) : (numCurrentUnsent == 0)))))) || (ListenerUtil.mutListener.listen(3267) ? (numNewUnsent >= 0) : (ListenerUtil.mutListener.listen(3266) ? (numNewUnsent <= 0) : (ListenerUtil.mutListener.listen(3265) ? (numNewUnsent > 0) : (ListenerUtil.mutListener.listen(3264) ? (numNewUnsent < 0) : (ListenerUtil.mutListener.listen(3263) ? (numNewUnsent != 0) : (numNewUnsent == 0))))))) : ((ListenerUtil.mutListener.listen(3262) ? (numCurrentUnsent >= 0) : (ListenerUtil.mutListener.listen(3261) ? (numCurrentUnsent <= 0) : (ListenerUtil.mutListener.listen(3260) ? (numCurrentUnsent > 0) : (ListenerUtil.mutListener.listen(3259) ? (numCurrentUnsent < 0) : (ListenerUtil.mutListener.listen(3258) ? (numCurrentUnsent != 0) : (numCurrentUnsent == 0)))))) && (ListenerUtil.mutListener.listen(3267) ? (numNewUnsent >= 0) : (ListenerUtil.mutListener.listen(3266) ? (numNewUnsent <= 0) : (ListenerUtil.mutListener.listen(3265) ? (numNewUnsent > 0) : (ListenerUtil.mutListener.listen(3264) ? (numNewUnsent < 0) : (ListenerUtil.mutListener.listen(3263) ? (numNewUnsent != 0) : (numNewUnsent == 0)))))))))) : (notificationService != null && !((ListenerUtil.mutListener.listen(3268) ? ((ListenerUtil.mutListener.listen(3262) ? (numCurrentUnsent >= 0) : (ListenerUtil.mutListener.listen(3261) ? (numCurrentUnsent <= 0) : (ListenerUtil.mutListener.listen(3260) ? (numCurrentUnsent > 0) : (ListenerUtil.mutListener.listen(3259) ? (numCurrentUnsent < 0) : (ListenerUtil.mutListener.listen(3258) ? (numCurrentUnsent != 0) : (numCurrentUnsent == 0)))))) || (ListenerUtil.mutListener.listen(3267) ? (numNewUnsent >= 0) : (ListenerUtil.mutListener.listen(3266) ? (numNewUnsent <= 0) : (ListenerUtil.mutListener.listen(3265) ? (numNewUnsent > 0) : (ListenerUtil.mutListener.listen(3264) ? (numNewUnsent < 0) : (ListenerUtil.mutListener.listen(3263) ? (numNewUnsent != 0) : (numNewUnsent == 0))))))) : ((ListenerUtil.mutListener.listen(3262) ? (numCurrentUnsent >= 0) : (ListenerUtil.mutListener.listen(3261) ? (numCurrentUnsent <= 0) : (ListenerUtil.mutListener.listen(3260) ? (numCurrentUnsent > 0) : (ListenerUtil.mutListener.listen(3259) ? (numCurrentUnsent < 0) : (ListenerUtil.mutListener.listen(3258) ? (numCurrentUnsent != 0) : (numCurrentUnsent == 0)))))) && (ListenerUtil.mutListener.listen(3267) ? (numNewUnsent >= 0) : (ListenerUtil.mutListener.listen(3266) ? (numNewUnsent <= 0) : (ListenerUtil.mutListener.listen(3265) ? (numNewUnsent > 0) : (ListenerUtil.mutListener.listen(3264) ? (numNewUnsent < 0) : (ListenerUtil.mutListener.listen(3263) ? (numNewUnsent != 0) : (numNewUnsent == 0)))))))))))) {
                    if (!ListenerUtil.mutListener.listen(3270)) {
                        notificationService.showUnsentMessageNotification(unsentMessages);
                    }
                }
            }
        }
    }

    private final SMSVerificationListener smsVerificationListener = new SMSVerificationListener() {

        @Override
        public void onVerified() {
            if (!ListenerUtil.mutListener.listen(3274)) {
                RuntimeUtil.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        if (!ListenerUtil.mutListener.listen(3273)) {
                            if (noticeLayout != null) {
                                if (!ListenerUtil.mutListener.listen(3272)) {
                                    AnimationUtil.collapse(noticeLayout);
                                }
                            }
                        }
                    }
                });
            }
        }

        @Override
        public void onVerificationStarted() {
            if (!ListenerUtil.mutListener.listen(3277)) {
                RuntimeUtil.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        if (!ListenerUtil.mutListener.listen(3276)) {
                            if (noticeLayout != null) {
                                if (!ListenerUtil.mutListener.listen(3275)) {
                                    AnimationUtil.expand(noticeLayout);
                                }
                            }
                        }
                    }
                });
            }
        }
    };

    private void updateBottomNavigation() {
        if (!ListenerUtil.mutListener.listen(3278)) {
            RuntimeUtil.runOnUiThread(() -> {
                try {
                    new UpdateBottomNavigationBadgeTask(HomeActivity.this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                } catch (RejectedExecutionException e) {
                    try {
                        new UpdateBottomNavigationBadgeTask(HomeActivity.this).executeOnExecutor(AsyncTask.SERIAL_EXECUTOR);
                    } catch (RejectedExecutionException ignored) {
                    }
                }
            });
        }
    }

    private final ConversationListener conversationListener = new ConversationListener() {

        @Override
        public void onNew(ConversationModel conversationModel) {
            if (!ListenerUtil.mutListener.listen(3279)) {
                updateBottomNavigation();
            }
        }

        @Override
        public void onModified(ConversationModel modifiedConversationModel, Integer oldPosition) {
            if (!ListenerUtil.mutListener.listen(3280)) {
                updateBottomNavigation();
            }
        }

        @Override
        public void onRemoved(ConversationModel conversationModel) {
            if (!ListenerUtil.mutListener.listen(3281)) {
                updateBottomNavigation();
            }
        }

        @Override
        public void onModifiedAll() {
        }
    };

    private final MessageListener messageListener = new MessageListener() {

        @Override
        public void onNew(AbstractMessageModel newMessage) {
        }

        @Override
        public void onModified(List<AbstractMessageModel> modifiedMessageModels) {
            if (!ListenerUtil.mutListener.listen(3287)) {
                {
                    long _loopCounter21 = 0;
                    for (AbstractMessageModel modifiedMessageModel : modifiedMessageModels) {
                        ListenerUtil.loopListener.listen("_loopCounter21", ++_loopCounter21);
                        if (!ListenerUtil.mutListener.listen(3286)) {
                            if ((ListenerUtil.mutListener.listen(3282) ? (!modifiedMessageModel.isStatusMessage() || modifiedMessageModel.isOutbox()) : (!modifiedMessageModel.isStatusMessage() && modifiedMessageModel.isOutbox()))) {
                                if (!ListenerUtil.mutListener.listen(3285)) {
                                    switch(modifiedMessageModel.getState()) {
                                        case SENDFAILED:
                                            if (!ListenerUtil.mutListener.listen(3283)) {
                                                updateUnsentMessagesList(modifiedMessageModel, true);
                                            }
                                            break;
                                        default:
                                            if (!ListenerUtil.mutListener.listen(3284)) {
                                                updateUnsentMessagesList(modifiedMessageModel, false);
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

        @Override
        public void onRemoved(AbstractMessageModel removedMessageModel) {
            if (!ListenerUtil.mutListener.listen(3288)) {
                updateUnsentMessagesList(removedMessageModel, false);
            }
        }

        @Override
        public void onProgressChanged(AbstractMessageModel messageModel, int newProgress) {
        }
    };

    private final AppIconListener appIconListener = new AppIconListener() {

        @Override
        public void onChanged() {
            if (!ListenerUtil.mutListener.listen(3290)) {
                RuntimeUtil.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        if (!ListenerUtil.mutListener.listen(3289)) {
                            updateAppLogo();
                        }
                    }
                });
            }
        }
    };

    private final ProfileListener profileListener = new ProfileListener() {

        @Override
        public void onAvatarChanged() {
            if (!ListenerUtil.mutListener.listen(3291)) {
                RuntimeUtil.runOnUiThread(() -> updateDrawerImage());
            }
        }

        @Override
        public void onAvatarRemoved() {
            if (!ListenerUtil.mutListener.listen(3292)) {
                this.onAvatarChanged();
            }
        }

        @Override
        public void onNicknameChanged(String newNickname) {
        }
    };

    private final VoipCallListener voipCallListener = new VoipCallListener() {

        @Override
        public void onStart(String contact, long elpasedTimeMs) {
            if (!ListenerUtil.mutListener.listen(3293)) {
                RuntimeUtil.runOnUiThread(() -> {
                    initOngoingCallNotice();
                });
            }
        }

        @Override
        public void onEnd() {
            if (!ListenerUtil.mutListener.listen(3294)) {
                RuntimeUtil.runOnUiThread(() -> {
                    if (ongoingCallNoticeLayout != null) {
                        Chronometer chronometer = ongoingCallNoticeLayout.findViewById(R.id.call_duration);
                        chronometer.stop();
                        ongoingCallNoticeLayout.setVisibility(View.GONE);
                    }
                });
            }
        }
    };

    private final ContactCountListener contactCountListener = new ContactCountListener() {

        @Override
        public void onNewContactsCountUpdated(int last24hoursCount) {
            if (!ListenerUtil.mutListener.listen(3297)) {
                if ((ListenerUtil.mutListener.listen(3295) ? (preferenceService != null || preferenceService.getShowUnreadBadge()) : (preferenceService != null && preferenceService.getShowUnreadBadge()))) {
                    if (!ListenerUtil.mutListener.listen(3296)) {
                        RuntimeUtil.runOnUiThread(() -> {
                            if (!isFinishing() && !isDestroyed() && !isChangingConfigurations()) {
                                BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
                                if (bottomNavigationView != null) {
                                    BadgeDrawable badgeDrawable = bottomNavigationView.getOrCreateBadge(R.id.contacts);
                                    if (badgeDrawable.getVerticalOffset() == 0) {
                                        badgeDrawable.setVerticalOffset(getResources().getDimensionPixelSize(R.dimen.bottom_nav_badge_offset_vertical));
                                    }
                                    badgeDrawable.setVisible(last24hoursCount > 0);
                                }
                            }
                        });
                    }
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(3298)) {
            logger.debug("onCreate");
        }
        if (!ListenerUtil.mutListener.listen(3299)) {
            AnimationUtil.setupTransitions(this.getApplicationContext(), getWindow());
        }
        if (!ListenerUtil.mutListener.listen(3300)) {
            ConfigUtils.configureActivityTheme(this);
        }
        if (!ListenerUtil.mutListener.listen(3301)) {
            super.onCreate(savedInstanceState);
        }
        // check master key
        MasterKey masterKey = ThreemaApplication.getMasterKey();
        if (!ListenerUtil.mutListener.listen(3340)) {
            if ((ListenerUtil.mutListener.listen(3302) ? (masterKey != null || masterKey.isLocked()) : (masterKey != null && masterKey.isLocked()))) {
                if (!ListenerUtil.mutListener.listen(3339)) {
                    startActivityForResult(new Intent(this, UnlockMasterKeyActivity.class), ThreemaActivity.ACTIVITY_ID_UNLOCK_MASTER_KEY);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(3338)) {
                    if ((ListenerUtil.mutListener.listen(3303) ? (ConfigUtils.isSerialLicensed() || !ConfigUtils.isSerialLicenseValid()) : (ConfigUtils.isSerialLicensed() && !ConfigUtils.isSerialLicenseValid()))) {
                        if (!ListenerUtil.mutListener.listen(3336)) {
                            startActivityForResult(new Intent(this, EnterSerialActivity.class), ThreemaActivity.ACTIVITY_ID_ENTER_SERIAL);
                        }
                        if (!ListenerUtil.mutListener.listen(3337)) {
                            finish();
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(3304)) {
                            this.startMainActivity(savedInstanceState);
                        }
                        if (!ListenerUtil.mutListener.listen(3335)) {
                            // only execute this on first startup
                            if (savedInstanceState == null) {
                                if (!ListenerUtil.mutListener.listen(3334)) {
                                    if ((ListenerUtil.mutListener.listen(3306) ? ((ListenerUtil.mutListener.listen(3305) ? (preferenceService != null || userService != null) : (preferenceService != null && userService != null)) || userService.hasIdentity()) : ((ListenerUtil.mutListener.listen(3305) ? (preferenceService != null || userService != null) : (preferenceService != null && userService != null)) && userService.hasIdentity()))) {
                                        if (!ListenerUtil.mutListener.listen(3332)) {
                                            if (ConfigUtils.isWorkRestricted()) {
                                                // update configuration
                                                final ThreemaSafeMDMConfig newConfig = ThreemaSafeMDMConfig.getInstance();
                                                ThreemaSafeService threemaSafeService = null;
                                                try {
                                                    if (!ListenerUtil.mutListener.listen(3307)) {
                                                        threemaSafeService = serviceManager.getThreemaSafeService();
                                                    }
                                                } catch (Exception e) {
                                                }
                                                if (!ListenerUtil.mutListener.listen(3331)) {
                                                    if (threemaSafeService != null) {
                                                        if (!ListenerUtil.mutListener.listen(3329)) {
                                                            if (newConfig.hasChanged(preferenceService)) {
                                                                // dispose of old backup, if any
                                                                try {
                                                                    if (!ListenerUtil.mutListener.listen(3314)) {
                                                                        threemaSafeService.deleteBackup();
                                                                    }
                                                                    if (!ListenerUtil.mutListener.listen(3315)) {
                                                                        threemaSafeService.setEnabled(false);
                                                                    }
                                                                } catch (Exception e) {
                                                                }
                                                                if (!ListenerUtil.mutListener.listen(3316)) {
                                                                    preferenceService.setThreemaSafeServerInfo(newConfig.getServerInfo());
                                                                }
                                                                if (!ListenerUtil.mutListener.listen(3328)) {
                                                                    if (newConfig.isBackupForced()) {
                                                                        if (!ListenerUtil.mutListener.listen(3327)) {
                                                                            if (newConfig.isSkipBackupPasswordEntry()) {
                                                                                if (!ListenerUtil.mutListener.listen(3326)) {
                                                                                    // enable with given password
                                                                                    enableSafe(threemaSafeService, newConfig, null);
                                                                                }
                                                                            } else if ((ListenerUtil.mutListener.listen(3322) ? (threemaSafeService.getThreemaSafeMasterKey() != null || (ListenerUtil.mutListener.listen(3321) ? (threemaSafeService.getThreemaSafeMasterKey().length >= 0) : (ListenerUtil.mutListener.listen(3320) ? (threemaSafeService.getThreemaSafeMasterKey().length <= 0) : (ListenerUtil.mutListener.listen(3319) ? (threemaSafeService.getThreemaSafeMasterKey().length < 0) : (ListenerUtil.mutListener.listen(3318) ? (threemaSafeService.getThreemaSafeMasterKey().length != 0) : (ListenerUtil.mutListener.listen(3317) ? (threemaSafeService.getThreemaSafeMasterKey().length == 0) : (threemaSafeService.getThreemaSafeMasterKey().length > 0))))))) : (threemaSafeService.getThreemaSafeMasterKey() != null && (ListenerUtil.mutListener.listen(3321) ? (threemaSafeService.getThreemaSafeMasterKey().length >= 0) : (ListenerUtil.mutListener.listen(3320) ? (threemaSafeService.getThreemaSafeMasterKey().length <= 0) : (ListenerUtil.mutListener.listen(3319) ? (threemaSafeService.getThreemaSafeMasterKey().length < 0) : (ListenerUtil.mutListener.listen(3318) ? (threemaSafeService.getThreemaSafeMasterKey().length != 0) : (ListenerUtil.mutListener.listen(3317) ? (threemaSafeService.getThreemaSafeMasterKey().length == 0) : (threemaSafeService.getThreemaSafeMasterKey().length > 0))))))))) {
                                                                                if (!ListenerUtil.mutListener.listen(3325)) {
                                                                                    // -> create a new backup with existing password
                                                                                    enableSafe(threemaSafeService, newConfig, threemaSafeService.getThreemaSafeMasterKey());
                                                                                }
                                                                            } else {
                                                                                if (!ListenerUtil.mutListener.listen(3323)) {
                                                                                    threemaSafeService.launchForcedPasswordDialog(this);
                                                                                }
                                                                                if (!ListenerUtil.mutListener.listen(3324)) {
                                                                                    finish();
                                                                                }
                                                                                return;
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            } else {
                                                                if (!ListenerUtil.mutListener.listen(3313)) {
                                                                    if ((ListenerUtil.mutListener.listen(3308) ? (newConfig.isBackupForced() || !preferenceService.getThreemaSafeEnabled()) : (newConfig.isBackupForced() && !preferenceService.getThreemaSafeEnabled()))) {
                                                                        if (!ListenerUtil.mutListener.listen(3312)) {
                                                                            // config has not changed but safe is still not enabled. fix it.
                                                                            if (newConfig.isSkipBackupPasswordEntry()) {
                                                                                if (!ListenerUtil.mutListener.listen(3311)) {
                                                                                    // enable with given password
                                                                                    enableSafe(threemaSafeService, newConfig, null);
                                                                                }
                                                                            } else {
                                                                                if (!ListenerUtil.mutListener.listen(3309)) {
                                                                                    // ask user for a new password
                                                                                    threemaSafeService.launchForcedPasswordDialog(this);
                                                                                }
                                                                                if (!ListenerUtil.mutListener.listen(3310)) {
                                                                                    finish();
                                                                                }
                                                                                return;
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                        if (!ListenerUtil.mutListener.listen(3330)) {
                                                            // save current config as new reference
                                                            newConfig.saveConfig(preferenceService);
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        if (!ListenerUtil.mutListener.listen(3333)) {
                                            showWhatsNew();
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

    private void showMainContent() {
        if (!ListenerUtil.mutListener.listen(3343)) {
            if (mainContent != null) {
                if (!ListenerUtil.mutListener.listen(3342)) {
                    if (mainContent.getVisibility() != View.VISIBLE) {
                        if (!ListenerUtil.mutListener.listen(3341)) {
                            mainContent.setVisibility(View.VISIBLE);
                        }
                    }
                }
            }
        }
    }

    private void showWhatsNew() {
        // set this to false if you want to show a What's New screen
        final boolean skipWhatsNew = true;
        if (!ListenerUtil.mutListener.listen(3356)) {
            if (preferenceService != null) {
                if (!ListenerUtil.mutListener.listen(3355)) {
                    if (!preferenceService.isLatestVersion(this)) {
                        if (!ListenerUtil.mutListener.listen(3345)) {
                            if (preferenceService.getPrivacyPolicyAccepted() == null) {
                                if (!ListenerUtil.mutListener.listen(3344)) {
                                    preferenceService.setPrivacyPolicyAccepted(new Date(), PreferenceService.PRIVACY_POLICY_ACCEPT_UPDATE);
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(3354)) {
                            if ((ListenerUtil.mutListener.listen(3347) ? ((ListenerUtil.mutListener.listen(3346) ? (!ConfigUtils.isWorkBuild() || !RuntimeUtil.isInTest()) : (!ConfigUtils.isWorkBuild() && !RuntimeUtil.isInTest())) || !isFinishing()) : ((ListenerUtil.mutListener.listen(3346) ? (!ConfigUtils.isWorkBuild() || !RuntimeUtil.isInTest()) : (!ConfigUtils.isWorkBuild() && !RuntimeUtil.isInTest())) && !isFinishing()))) {
                                if (!ListenerUtil.mutListener.listen(3352)) {
                                    if (skipWhatsNew) {
                                        if (!ListenerUtil.mutListener.listen(3351)) {
                                            isWhatsNewShown = false;
                                        }
                                    } else {
                                        if (!ListenerUtil.mutListener.listen(3348)) {
                                            // make sure this is set to false if whatsnew is skipped - otherwise pin unlock will not be shown once
                                            isWhatsNewShown = true;
                                        }
                                        /*						int previous = preferenceService.getLatestVersion() % 1000;

						if (previous < 650) {
*/
                                        Intent intent = new Intent(this, WhatsNewActivity.class);
                                        if (!ListenerUtil.mutListener.listen(3349)) {
                                            startActivityForResult(intent, REQUEST_CODE_WHATSNEW);
                                        }
                                        if (!ListenerUtil.mutListener.listen(3350)) {
                                            overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(3353)) {
                                    preferenceService.setLatestVersion(this);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @SuppressLint("StaticFieldLeak")
    private void enableSafe(ThreemaSafeService threemaSafeService, ThreemaSafeMDMConfig mdmConfig, final byte[] masterkeyPreset) {
        if (!ListenerUtil.mutListener.listen(3364)) {
            new AsyncTask<Void, Void, byte[]>() {

                @Override
                protected byte[] doInBackground(Void... voids) {
                    if (!ListenerUtil.mutListener.listen(3357)) {
                        if (masterkeyPreset == null) {
                            return threemaSafeService.deriveMasterKey(mdmConfig.getPassword(), userService.getIdentity());
                        }
                    }
                    return masterkeyPreset;
                }

                @Override
                protected void onPostExecute(byte[] masterkey) {
                    if (!ListenerUtil.mutListener.listen(3363)) {
                        if (masterkey != null) {
                            if (!ListenerUtil.mutListener.listen(3359)) {
                                threemaSafeService.storeMasterKey(masterkey);
                            }
                            if (!ListenerUtil.mutListener.listen(3360)) {
                                preferenceService.setThreemaSafeServerInfo(mdmConfig.getServerInfo());
                            }
                            if (!ListenerUtil.mutListener.listen(3361)) {
                                threemaSafeService.setEnabled(true);
                            }
                            if (!ListenerUtil.mutListener.listen(3362)) {
                                threemaSafeService.uploadNow(HomeActivity.this, true);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(3358)) {
                                Toast.makeText(HomeActivity.this, R.string.safe_error_preparing, Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                }
            }.execute();
        }
    }

    private void showQRPopup() {
        int[] location = getMiniAvatarLocation();
        IdentityPopup identityPopup = new IdentityPopup(this);
        if (!ListenerUtil.mutListener.listen(3365)) {
            identityPopup.show(this, toolbar, location, () -> {
                // show profile fragment
                bottomNavigationView.post(() -> {
                    bottomNavigationView.findViewById(R.id.my_profile).performClick();
                });
            });
        }
    }

    private int[] getMiniAvatarLocation() {
        int[] location = new int[2];
        if (!ListenerUtil.mutListener.listen(3366)) {
            toolbar.getLocationInWindow(location);
        }
        if (!ListenerUtil.mutListener.listen(3371)) {
            location[0] += toolbar.getContentInsetLeft() + ((ListenerUtil.mutListener.listen(3370) ? ((getResources().getDimensionPixelSize(R.dimen.navigation_icon_padding) + getResources().getDimensionPixelSize(R.dimen.navigation_icon_size)) % 2) : (ListenerUtil.mutListener.listen(3369) ? ((getResources().getDimensionPixelSize(R.dimen.navigation_icon_padding) + getResources().getDimensionPixelSize(R.dimen.navigation_icon_size)) * 2) : (ListenerUtil.mutListener.listen(3368) ? ((getResources().getDimensionPixelSize(R.dimen.navigation_icon_padding) + getResources().getDimensionPixelSize(R.dimen.navigation_icon_size)) - 2) : (ListenerUtil.mutListener.listen(3367) ? ((getResources().getDimensionPixelSize(R.dimen.navigation_icon_padding) + getResources().getDimensionPixelSize(R.dimen.navigation_icon_size)) + 2) : ((getResources().getDimensionPixelSize(R.dimen.navigation_icon_padding) + getResources().getDimensionPixelSize(R.dimen.navigation_icon_size)) / 2))))));
        }
        if (!ListenerUtil.mutListener.listen(3376)) {
            location[1] += (ListenerUtil.mutListener.listen(3375) ? (toolbar.getHeight() % 2) : (ListenerUtil.mutListener.listen(3374) ? (toolbar.getHeight() * 2) : (ListenerUtil.mutListener.listen(3373) ? (toolbar.getHeight() - 2) : (ListenerUtil.mutListener.listen(3372) ? (toolbar.getHeight() + 2) : (toolbar.getHeight() / 2)))));
        }
        return location;
    }

    private void checkApp() {
        try {
            if (!ListenerUtil.mutListener.listen(3378)) {
                if (this.currentCheckAppReceiver != null) {
                    if (!ListenerUtil.mutListener.listen(3377)) {
                        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.currentCheckAppReceiver);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(3380)) {
                if (this.checkLicenseBroadcastReceiver != null) {
                    if (!ListenerUtil.mutListener.listen(3379)) {
                        this.unregisterReceiver(this.checkLicenseBroadcastReceiver);
                    }
                }
            }
        } catch (IllegalArgumentException r) {
        }
        // Register not licensed and update available broadcast
        IntentFilter filter = new IntentFilter();
        if (!ListenerUtil.mutListener.listen(3381)) {
            filter.addAction(IntentDataUtil.ACTION_LICENSE_NOT_ALLOWED);
        }
        if (!ListenerUtil.mutListener.listen(3382)) {
            filter.addAction(IntentDataUtil.ACTION_UPDATE_AVAILABLE);
        }
        if (!ListenerUtil.mutListener.listen(3383)) {
            LocalBroadcastManager.getInstance(this).registerReceiver(currentCheckAppReceiver, filter);
        }
        if (!ListenerUtil.mutListener.listen(3384)) {
            this.checkLicense();
        }
    }

    private boolean checkLicense() {
        if (!ListenerUtil.mutListener.listen(3385)) {
            if (this.isLicenseCheckStarted) {
                return true;
            }
        }
        if (!ListenerUtil.mutListener.listen(3400)) {
            if (serviceManager != null) {
                DeviceService deviceService = serviceManager.getDeviceService();
                if (!ListenerUtil.mutListener.listen(3399)) {
                    if ((ListenerUtil.mutListener.listen(3386) ? (deviceService != null || deviceService.isOnline()) : (deviceService != null && deviceService.isOnline()))) {
                        // start check directly
                        CheckLicenseRoutine check = null;
                        try {
                            if (!ListenerUtil.mutListener.listen(3393)) {
                                check = new CheckLicenseRoutine(this, serviceManager.getAPIConnector(), serviceManager.getUserService(), deviceService, serviceManager.getLicenseService(), serviceManager.getIdentityStore());
                            }
                        } catch (FileSystemNotPresentException e) {
                            if (!ListenerUtil.mutListener.listen(3392)) {
                                logger.error("Exception", e);
                            }
                            return false;
                        }
                        if (!ListenerUtil.mutListener.listen(3394)) {
                            new Thread(check).start();
                        }
                        if (!ListenerUtil.mutListener.listen(3395)) {
                            this.isLicenseCheckStarted = true;
                        }
                        if (!ListenerUtil.mutListener.listen(3398)) {
                            if (this.checkLicenseBroadcastReceiver != null) {
                                try {
                                    if (!ListenerUtil.mutListener.listen(3397)) {
                                        this.unregisterReceiver(this.checkLicenseBroadcastReceiver);
                                    }
                                } catch (IllegalArgumentException e) {
                                    if (!ListenerUtil.mutListener.listen(3396)) {
                                        logger.error("Exception", e);
                                    }
                                }
                            }
                        }
                        return true;
                    } else {
                        if (!ListenerUtil.mutListener.listen(3391)) {
                            if (this.checkLicenseBroadcastReceiver == null) {
                                if (!ListenerUtil.mutListener.listen(3389)) {
                                    this.checkLicenseBroadcastReceiver = new BroadcastReceiver() {

                                        @Override
                                        public void onReceive(Context context, Intent intent) {
                                            if (!ListenerUtil.mutListener.listen(3387)) {
                                                logger.debug("receive connectivity change in main activity to check license");
                                            }
                                            if (!ListenerUtil.mutListener.listen(3388)) {
                                                checkLicense();
                                            }
                                        }
                                    };
                                }
                                if (!ListenerUtil.mutListener.listen(3390)) {
                                    this.registerReceiver(this.checkLicenseBroadcastReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        if (!ListenerUtil.mutListener.listen(3401)) {
            logger.debug("onDestroy");
        }
        if (!ListenerUtil.mutListener.listen(3402)) {
            ThreemaApplication.activityDestroyed(this);
        }
        try {
            if (!ListenerUtil.mutListener.listen(3404)) {
                if (this.currentCheckAppReceiver != null) {
                    if (!ListenerUtil.mutListener.listen(3403)) {
                        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.currentCheckAppReceiver);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(3406)) {
                if (this.checkLicenseBroadcastReceiver != null) {
                    if (!ListenerUtil.mutListener.listen(3405)) {
                        this.unregisterReceiver(this.checkLicenseBroadcastReceiver);
                    }
                }
            }
        } catch (IllegalArgumentException r) {
        }
        if (!ListenerUtil.mutListener.listen(3407)) {
            // remove listeners to avoid memory leaks
            ListenerManager.messageListeners.remove(this.messageListener);
        }
        if (!ListenerUtil.mutListener.listen(3408)) {
            ListenerManager.smsVerificationListeners.remove(this.smsVerificationListener);
        }
        if (!ListenerUtil.mutListener.listen(3409)) {
            ListenerManager.appIconListeners.remove(this.appIconListener);
        }
        if (!ListenerUtil.mutListener.listen(3410)) {
            ListenerManager.profileListeners.remove(this.profileListener);
        }
        if (!ListenerUtil.mutListener.listen(3411)) {
            ListenerManager.voipCallListeners.remove(this.voipCallListener);
        }
        if (!ListenerUtil.mutListener.listen(3412)) {
            ListenerManager.conversationListeners.remove(this.conversationListener);
        }
        if (!ListenerUtil.mutListener.listen(3413)) {
            ListenerManager.contactCountListener.remove(this.contactCountListener);
        }
        if (!ListenerUtil.mutListener.listen(3416)) {
            if (serviceManager != null) {
                ThreemaConnection threemaConnection = serviceManager.getConnection();
                if (!ListenerUtil.mutListener.listen(3415)) {
                    if (threemaConnection != null) {
                        if (!ListenerUtil.mutListener.listen(3414)) {
                            threemaConnection.removeConnectionStateListener(connectionStateListener);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3417)) {
            super.onDestroy();
        }
    }

    private void showErrorTextAndExit(String text) {
        if (!ListenerUtil.mutListener.listen(3418)) {
            GenericAlertDialog.newInstance(R.string.error, text, R.string.finish, 0).show(getSupportFragmentManager(), DIALOG_TAG_FINISH_UP);
        }
    }

    private void runUpdates(final UpdateSystemService updateSystemService) {
        if (!ListenerUtil.mutListener.listen(3429)) {
            new AsyncTask<Void, Void, Void>() {

                @Override
                protected void onPreExecute() {
                    if (!ListenerUtil.mutListener.listen(3419)) {
                        GenericProgressDialog.newInstance(R.string.updating_system, R.string.please_wait).show(getSupportFragmentManager(), DIALOG_TAG_UPDATING);
                    }
                }

                @Override
                protected Void doInBackground(Void... voids) {
                    try {
                        if (!ListenerUtil.mutListener.listen(3425)) {
                            updateSystemService.update(new UpdateSystemService.OnSystemUpdateRun() {

                                @Override
                                public void onStart(final UpdateSystemService.SystemUpdate systemUpdate) {
                                    if (!ListenerUtil.mutListener.listen(3421)) {
                                        logger.info("Running update to " + systemUpdate.getText());
                                    }
                                }

                                @Override
                                public void onFinished(UpdateSystemService.SystemUpdate systemUpdate, boolean success) {
                                    if (!ListenerUtil.mutListener.listen(3424)) {
                                        if (success) {
                                            if (!ListenerUtil.mutListener.listen(3423)) {
                                                logger.info("System updated to " + systemUpdate.getText());
                                            }
                                        } else {
                                            if (!ListenerUtil.mutListener.listen(3422)) {
                                                logger.error("System update to " + systemUpdate.getText() + " failed!");
                                            }
                                        }
                                    }
                                }
                            });
                        }
                    } catch (final Exception e) {
                        if (!ListenerUtil.mutListener.listen(3420)) {
                            logger.error("Exception", e);
                        }
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    if (!ListenerUtil.mutListener.listen(3426)) {
                        DialogUtil.dismissDialog(getSupportFragmentManager(), DIALOG_TAG_UPDATING, true);
                    }
                    if (!ListenerUtil.mutListener.listen(3427)) {
                        HomeActivity.this.initMainActivity(null);
                    }
                    if (!ListenerUtil.mutListener.listen(3428)) {
                        showMainContent();
                    }
                }
            }.execute();
        }
    }

    private void startMainActivity(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(3430)) {
            // therefore Services are now available
            this.serviceManager = ThreemaApplication.getServiceManager();
        }
        if (!ListenerUtil.mutListener.listen(3431)) {
            if (this.isInitialized) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(3455)) {
            if (serviceManager != null) {
                if (!ListenerUtil.mutListener.listen(3433)) {
                    this.userService = this.serviceManager.getUserService();
                }
                if (!ListenerUtil.mutListener.listen(3434)) {
                    this.preferenceService = this.serviceManager.getPreferenceService();
                }
                if (!ListenerUtil.mutListener.listen(3435)) {
                    this.notificationService = serviceManager.getNotificationService();
                }
                if (!ListenerUtil.mutListener.listen(3436)) {
                    this.lockAppService = serviceManager.getLockAppService();
                }
                try {
                    if (!ListenerUtil.mutListener.listen(3437)) {
                        this.conversationService = serviceManager.getConversationService();
                    }
                    if (!ListenerUtil.mutListener.listen(3438)) {
                        this.contactService = serviceManager.getContactService();
                    }
                } catch (Exception e) {
                }
                if (!ListenerUtil.mutListener.listen(3442)) {
                    if ((ListenerUtil.mutListener.listen(3440) ? ((ListenerUtil.mutListener.listen(3439) ? (preferenceService == null && notificationService == null) : (preferenceService == null || notificationService == null)) && userService == null) : ((ListenerUtil.mutListener.listen(3439) ? (preferenceService == null && notificationService == null) : (preferenceService == null || notificationService == null)) || userService == null))) {
                        if (!ListenerUtil.mutListener.listen(3441)) {
                            finish();
                        }
                        return;
                    }
                }
                if (!ListenerUtil.mutListener.listen(3443)) {
                    // reset connectivity status
                    preferenceService.setLastOnlineStatus(serviceManager.getDeviceService().isOnline());
                }
                if (!ListenerUtil.mutListener.listen(3444)) {
                    // remove restart notification
                    notificationService.cancelRestartNotification();
                }
                UpdateSystemService updateSystemService = serviceManager.getUpdateSystemService();
                if (!ListenerUtil.mutListener.listen(3447)) {
                    if (updateSystemService.hasUpdates()) {
                        if (!ListenerUtil.mutListener.listen(3446)) {
                            // runASync updates FIRST!!
                            this.runUpdates(updateSystemService);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(3445)) {
                            this.initMainActivity(savedInstanceState);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(3448)) {
                    ListenerManager.smsVerificationListeners.add(this.smsVerificationListener);
                }
                if (!ListenerUtil.mutListener.listen(3449)) {
                    ListenerManager.messageListeners.add(this.messageListener);
                }
                if (!ListenerUtil.mutListener.listen(3450)) {
                    ListenerManager.appIconListeners.add(this.appIconListener);
                }
                if (!ListenerUtil.mutListener.listen(3451)) {
                    ListenerManager.profileListeners.add(this.profileListener);
                }
                if (!ListenerUtil.mutListener.listen(3452)) {
                    ListenerManager.voipCallListeners.add(this.voipCallListener);
                }
                if (!ListenerUtil.mutListener.listen(3453)) {
                    ListenerManager.conversationListeners.add(this.conversationListener);
                }
                if (!ListenerUtil.mutListener.listen(3454)) {
                    ListenerManager.contactCountListener.add(this.contactCountListener);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(3432)) {
                    RuntimeUtil.runOnUiThread(() -> showErrorTextAndExit(getString(R.string.service_manager_not_available)));
                }
            }
        }
    }

    @UiThread
    private void initMainActivity(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(3456)) {
            // refresh StateBitmapUtil
            StateBitmapUtil.getInstance().refresh();
        }
        if (!ListenerUtil.mutListener.listen(3457)) {
            // licensing
            checkApp();
        }
        if (!ListenerUtil.mutListener.listen(3464)) {
            // start wizard if necessary
            if ((ListenerUtil.mutListener.listen(3458) ? (preferenceService.getWizardRunning() && !userService.hasIdentity()) : (preferenceService.getWizardRunning() || !userService.hasIdentity()))) {
                if (!ListenerUtil.mutListener.listen(3459)) {
                    logger.debug("Missing identity. Wizard running? " + preferenceService.getWizardRunning());
                }
                if (!ListenerUtil.mutListener.listen(3462)) {
                    if (userService.hasIdentity()) {
                        if (!ListenerUtil.mutListener.listen(3461)) {
                            startActivity(new Intent(this, WizardBaseActivity.class));
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(3460)) {
                            startActivity(new Intent(this, WizardStartActivity.class));
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(3463)) {
                    finish();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(3465)) {
            // set custom locale
            ConfigUtils.setLocaleOverride(this, preferenceService);
        }
        if (!ListenerUtil.mutListener.listen(3466)) {
            // set up content
            setContentView(R.layout.activity_home);
        }
        if (!ListenerUtil.mutListener.listen(3468)) {
            // Write master key now if no passphrase has been set
            if (!ThreemaApplication.getMasterKey().isProtected()) {
                try {
                    if (!ListenerUtil.mutListener.listen(3467)) {
                        ThreemaApplication.getMasterKey().setPassphrase(null);
                    }
                } catch (Exception e) {
                    // better die if something went wrong as the master key may not have been saved
                    throw new RuntimeException(e);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3469)) {
            // Set up the action bar.
            initActionBar();
        }
        if (!ListenerUtil.mutListener.listen(3470)) {
            // init custom icon
            updateAppLogo();
        }
        if (!ListenerUtil.mutListener.listen(3471)) {
            // reset accent color
            ConfigUtils.resetAccentColor(this);
        }
        if (!ListenerUtil.mutListener.listen(3472)) {
            actionBar.setDisplayShowTitleEnabled(false);
        }
        if (!ListenerUtil.mutListener.listen(3473)) {
            actionBar.setDisplayUseLogoEnabled(false);
        }
        if (!ListenerUtil.mutListener.listen(3474)) {
            ConfigUtils.setScreenshotsAllowed(this, preferenceService, lockAppService);
        }
        if (!ListenerUtil.mutListener.listen(3475)) {
            // add connection state listener for displaying colored connection status line above toolbar
            new Thread(() -> {
                if (serviceManager != null) {
                    ThreemaConnection threemaConnection = serviceManager.getConnection();
                    if (threemaConnection != null) {
                        threemaConnection.addConnectionStateListener(connectionStateListener);
                        updateConnectionIndicator(threemaConnection.getConnectionState());
                    }
                }
            }).start();
        }
        if (!ListenerUtil.mutListener.listen(3476)) {
            // call onPrepareOptionsMenu
            this.invalidateOptionsMenu();
        }
        if (!ListenerUtil.mutListener.listen(3483)) {
            if (savedInstanceState == null) {
                if (!ListenerUtil.mutListener.listen(3482)) {
                    if (!PushService.servicesInstalled(this)) {
                        if (!ListenerUtil.mutListener.listen(3477)) {
                            enablePolling(serviceManager);
                        }
                        if (!ListenerUtil.mutListener.listen(3481)) {
                            if ((ListenerUtil.mutListener.listen(3479) ? ((ListenerUtil.mutListener.listen(3478) ? (!ConfigUtils.isBlackBerry() || !ConfigUtils.isAmazonDevice()) : (!ConfigUtils.isBlackBerry() && !ConfigUtils.isAmazonDevice())) || !ConfigUtils.isWorkBuild()) : ((ListenerUtil.mutListener.listen(3478) ? (!ConfigUtils.isBlackBerry() || !ConfigUtils.isAmazonDevice()) : (!ConfigUtils.isBlackBerry() && !ConfigUtils.isAmazonDevice())) && !ConfigUtils.isWorkBuild()))) {
                                if (!ListenerUtil.mutListener.listen(3480)) {
                                    RuntimeUtil.runOnUiThread(() -> ShowOnceDialog.newInstance(R.string.push_not_available_title, R.string.push_not_available_text).show(getSupportFragmentManager(), "nopush"));
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3484)) {
            this.mainContent = findViewById(R.id.main_content);
        }
        if (!ListenerUtil.mutListener.listen(3485)) {
            this.noticeLayout = findViewById(R.id.notice_layout);
        }
        if (!ListenerUtil.mutListener.listen(3486)) {
            findViewById(R.id.notice_button_enter_code).setOnClickListener(v -> SMSVerificationDialog.newInstance(userService.getLinkedMobile(true)).show(getSupportFragmentManager(), DIALOG_TAG_VERIFY_CODE));
        }
        if (!ListenerUtil.mutListener.listen(3487)) {
            findViewById(R.id.notice_button_cancel).setOnClickListener(v -> GenericAlertDialog.newInstance(R.string.verify_title, R.string.really_cancel_verify, R.string.yes, R.string.no).show(getSupportFragmentManager(), DIALOG_TAG_CANCEL_VERIFY));
        }
        if (!ListenerUtil.mutListener.listen(3488)) {
            this.noticeLayout.setVisibility(userService.getMobileLinkingState() == UserService.LinkingState_PENDING ? View.VISIBLE : View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(3489)) {
            this.ongoingCallNoticeLayout = findViewById(R.id.ongoing_call_layout);
        }
        if (!ListenerUtil.mutListener.listen(3490)) {
            findViewById(R.id.call_container).setOnClickListener(v -> {
                if (VoipCallService.isRunning()) {
                    final Intent openIntent = new Intent(HomeActivity.this, CallActivity.class);
                    openIntent.putExtra(EXTRA_ACTIVITY_MODE, CallActivity.MODE_ACTIVE_CALL);
                    openIntent.putExtra(EXTRA_CONTACT_IDENTITY, VoipCallService.getOtherPartysIdentity());
                    openIntent.putExtra(EXTRA_START_TIME, VoipCallService.getStartTime());
                    startActivity(openIntent);
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(3491)) {
            findViewById(R.id.call_hangup).setOnClickListener(v -> {
                final Intent hangupIntent = new Intent(HomeActivity.this, VoipCallService.class);
                hangupIntent.setAction(ACTION_HANGUP);
                startService(hangupIntent);
            });
        }
        if (!ListenerUtil.mutListener.listen(3492)) {
            initOngoingCallNotice();
        }
        /*
		 * setup fragments
		 */
        String initialFragmentTag = FRAGMENT_TAG_MESSAGES;
        final int initialItemId;
        final Fragment contactsFragment, messagesFragment, profileFragment;
        Intent intent = getIntent();
        if (!ListenerUtil.mutListener.listen(3496)) {
            if ((ListenerUtil.mutListener.listen(3493) ? (intent != null || intent.getBooleanExtra(EXTRA_SHOW_CONTACTS, false)) : (intent != null && intent.getBooleanExtra(EXTRA_SHOW_CONTACTS, false)))) {
                if (!ListenerUtil.mutListener.listen(3494)) {
                    initialFragmentTag = FRAGMENT_TAG_CONTACTS;
                }
                if (!ListenerUtil.mutListener.listen(3495)) {
                    intent.removeExtra(EXTRA_SHOW_CONTACTS);
                }
            }
        }
        if ((ListenerUtil.mutListener.listen(3497) ? (savedInstanceState != null || savedInstanceState.containsKey(BUNDLE_CURRENT_FRAGMENT_TAG)) : (savedInstanceState != null && savedInstanceState.containsKey(BUNDLE_CURRENT_FRAGMENT_TAG)))) {
            if (!ListenerUtil.mutListener.listen(3512)) {
                // restored session
                initialFragmentTag = savedInstanceState.getString(BUNDLE_CURRENT_FRAGMENT_TAG, initialFragmentTag);
            }
            contactsFragment = getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG_CONTACTS);
            messagesFragment = getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG_MESSAGES);
            profileFragment = getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG_PROFILE);
            if (!ListenerUtil.mutListener.listen(3513)) {
                currentFragmentTag = initialFragmentTag;
            }
            switch(initialFragmentTag) {
                case FRAGMENT_TAG_CONTACTS:
                    if (!ListenerUtil.mutListener.listen(3514)) {
                        getSupportFragmentManager().beginTransaction().hide(messagesFragment).hide(profileFragment).show(contactsFragment).commit();
                    }
                    initialItemId = R.id.contacts;
                    break;
                case FRAGMENT_TAG_MESSAGES:
                    if (!ListenerUtil.mutListener.listen(3515)) {
                        getSupportFragmentManager().beginTransaction().hide(contactsFragment).hide(profileFragment).show(messagesFragment).commit();
                    }
                    initialItemId = R.id.messages;
                    break;
                case FRAGMENT_TAG_PROFILE:
                    if (!ListenerUtil.mutListener.listen(3516)) {
                        getSupportFragmentManager().beginTransaction().hide(messagesFragment).hide(contactsFragment).show(profileFragment).commit();
                    }
                    initialItemId = R.id.my_profile;
                    break;
                default:
                    initialItemId = R.id.messages;
            }
        } else {
            if (!ListenerUtil.mutListener.listen(3500)) {
                // new session
                if ((ListenerUtil.mutListener.listen(3498) ? (conversationService == null && !conversationService.hasConversations()) : (conversationService == null || !conversationService.hasConversations()))) {
                    if (!ListenerUtil.mutListener.listen(3499)) {
                        initialFragmentTag = FRAGMENT_TAG_CONTACTS;
                    }
                }
            }
            contactsFragment = new ContactsSectionFragment();
            messagesFragment = new MessageSectionFragment();
            profileFragment = new MyIDFragment();
            FragmentTransaction messagesTransaction = getSupportFragmentManager().beginTransaction().add(R.id.home_container, messagesFragment, FRAGMENT_TAG_MESSAGES);
            FragmentTransaction contactsTransaction = getSupportFragmentManager().beginTransaction().add(R.id.home_container, contactsFragment, FRAGMENT_TAG_CONTACTS);
            FragmentTransaction profileTransaction = getSupportFragmentManager().beginTransaction().add(R.id.home_container, profileFragment, FRAGMENT_TAG_PROFILE);
            if (!ListenerUtil.mutListener.listen(3501)) {
                currentFragmentTag = initialFragmentTag;
            }
            switch(initialFragmentTag) {
                case FRAGMENT_TAG_CONTACTS:
                    initialItemId = R.id.contacts;
                    if (!ListenerUtil.mutListener.listen(3502)) {
                        messagesTransaction.hide(messagesFragment);
                    }
                    if (!ListenerUtil.mutListener.listen(3503)) {
                        messagesTransaction.hide(profileFragment);
                    }
                    break;
                case FRAGMENT_TAG_MESSAGES:
                    initialItemId = R.id.messages;
                    if (!ListenerUtil.mutListener.listen(3504)) {
                        messagesTransaction.hide(contactsFragment);
                    }
                    if (!ListenerUtil.mutListener.listen(3505)) {
                        messagesTransaction.hide(profileFragment);
                    }
                    break;
                case FRAGMENT_TAG_PROFILE:
                    initialItemId = R.id.my_profile;
                    if (!ListenerUtil.mutListener.listen(3506)) {
                        messagesTransaction.hide(messagesFragment);
                    }
                    if (!ListenerUtil.mutListener.listen(3507)) {
                        messagesTransaction.hide(contactsFragment);
                    }
                    break;
                default:
                    // should never happen
                    initialItemId = R.id.messages;
            }
            try {
                if (!ListenerUtil.mutListener.listen(3509)) {
                    messagesTransaction.commit();
                }
                if (!ListenerUtil.mutListener.listen(3510)) {
                    contactsTransaction.commit();
                }
                if (!ListenerUtil.mutListener.listen(3511)) {
                    profileTransaction.commit();
                }
            } catch (IllegalStateException e) {
                if (!ListenerUtil.mutListener.listen(3508)) {
                    logger.error("Exception", e);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3517)) {
            this.bottomNavigationView = findViewById(R.id.bottom_navigation);
        }
        if (!ListenerUtil.mutListener.listen(3518)) {
            this.bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
                if (tooltipPopup != null) {
                    tooltipPopup.dismiss();
                }
                Fragment currentFragment = getSupportFragmentManager().findFragmentByTag(currentFragmentTag);
                if (currentFragment != null) {
                    switch(item.getItemId()) {
                        case R.id.contacts:
                            if (!FRAGMENT_TAG_CONTACTS.equals(currentFragmentTag)) {
                                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fast_fade_in, R.anim.fast_fade_out, R.anim.fast_fade_in, R.anim.fast_fade_out).hide(currentFragment).show(contactsFragment).commit();
                                currentFragmentTag = FRAGMENT_TAG_CONTACTS;
                            }
                            return true;
                        case R.id.messages:
                            if (!FRAGMENT_TAG_MESSAGES.equals(currentFragmentTag)) {
                                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fast_fade_in, R.anim.fast_fade_out, R.anim.fast_fade_in, R.anim.fast_fade_out).hide(currentFragment).show(messagesFragment).commit();
                                currentFragmentTag = FRAGMENT_TAG_MESSAGES;
                            }
                            return true;
                        case R.id.my_profile:
                            if (!FRAGMENT_TAG_PROFILE.equals(currentFragmentTag)) {
                                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fast_fade_in, R.anim.fast_fade_out, R.anim.fast_fade_in, R.anim.fast_fade_out).hide(currentFragment).show(profileFragment).commit();
                                currentFragmentTag = FRAGMENT_TAG_PROFILE;
                            }
                            return true;
                    }
                }
                return false;
            });
        }
        if (!ListenerUtil.mutListener.listen(3519)) {
            this.bottomNavigationView.post(() -> {
                bottomNavigationView.setSelectedItemId(initialItemId);
            });
        }
        if (!ListenerUtil.mutListener.listen(3521)) {
            if (preferenceService.getShowUnreadBadge()) {
                if (!ListenerUtil.mutListener.listen(3520)) {
                    new UpdateBottomNavigationBadgeTask(this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3525)) {
            // restore sync adapter account if necessary
            if (preferenceService.isSyncContacts()) {
                if (!ListenerUtil.mutListener.listen(3523)) {
                    if (!userService.checkAccount()) {
                        if (!ListenerUtil.mutListener.listen(3522)) {
                            // create account
                            userService.getAccount(true);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(3524)) {
                    userService.enableAccountAutoSync(true);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3526)) {
            isInitialized = true;
        }
    }

    private void initOngoingCallNotice() {
        if (!ListenerUtil.mutListener.listen(3532)) {
            if (ongoingCallNoticeLayout != null) {
                if (!ListenerUtil.mutListener.listen(3531)) {
                    if (VoipCallService.isRunning()) {
                        Chronometer chronometer = ongoingCallNoticeLayout.findViewById(R.id.call_duration);
                        if (!ListenerUtil.mutListener.listen(3528)) {
                            chronometer.setBase(VoipCallService.getStartTime());
                        }
                        if (!ListenerUtil.mutListener.listen(3529)) {
                            chronometer.start();
                        }
                        if (!ListenerUtil.mutListener.listen(3530)) {
                            ongoingCallNoticeLayout.setVisibility(View.VISIBLE);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(3527)) {
                            ongoingCallNoticeLayout.setVisibility(View.GONE);
                        }
                    }
                }
            }
        }
    }

    private void enablePolling(ServiceManager serviceManager) {
        if (!ListenerUtil.mutListener.listen(3536)) {
            if (!preferenceService.isPolling()) {
                if (!ListenerUtil.mutListener.listen(3533)) {
                    preferenceService.setPolling(true);
                }
                LifetimeService lifetimeService = serviceManager.getLifetimeService();
                if (!ListenerUtil.mutListener.listen(3535)) {
                    if (lifetimeService != null) {
                        if (!ListenerUtil.mutListener.listen(3534)) {
                            lifetimeService.setPollingInterval(1);
                        }
                    }
                }
            }
        }
    }

    @SuppressLint("StaticFieldLeak")
    private void updateDrawerImage() {
        if (!ListenerUtil.mutListener.listen(3542)) {
            if (toolbar != null) {
                if (!ListenerUtil.mutListener.listen(3541)) {
                    new AsyncTask<Void, Void, Drawable>() {

                        @Override
                        protected Drawable doInBackground(Void... params) {
                            Bitmap bitmap = contactService.getAvatar(new ContactModel(userService.getIdentity(), null), false);
                            if (!ListenerUtil.mutListener.listen(3537)) {
                                if (bitmap != null) {
                                    int size = getResources().getDimensionPixelSize(R.dimen.navigation_icon_size);
                                    return new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, size, size, true));
                                }
                            }
                            return null;
                        }

                        @Override
                        protected void onPostExecute(Drawable drawable) {
                            if (!ListenerUtil.mutListener.listen(3540)) {
                                if (drawable != null) {
                                    if (!ListenerUtil.mutListener.listen(3538)) {
                                        toolbar.setNavigationIcon(drawable);
                                    }
                                    if (!ListenerUtil.mutListener.listen(3539)) {
                                        toolbar.setNavigationContentDescription(R.string.open_myid_popup);
                                    }
                                }
                            }
                        }
                    }.execute();
                }
            }
        }
    }

    @SuppressLint("StaticFieldLeak")
    private void reallyCancelVerify() {
        if (!ListenerUtil.mutListener.listen(3543)) {
            AnimationUtil.collapse(noticeLayout);
        }
        if (!ListenerUtil.mutListener.listen(3546)) {
            new AsyncTask<Void, Void, Void>() {

                @Override
                protected Void doInBackground(Void... params) {
                    try {
                        if (!ListenerUtil.mutListener.listen(3545)) {
                            userService.unlinkMobileNumber();
                        }
                    } catch (Exception e) {
                        if (!ListenerUtil.mutListener.listen(3544)) {
                            logger.error("Exception", e);
                        }
                    }
                    return null;
                }
            }.execute();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!ListenerUtil.mutListener.listen(3547)) {
            super.onCreateOptionsMenu(menu);
        }
        if (!ListenerUtil.mutListener.listen(3548)) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.activity_home, menu);
        }
        if (!ListenerUtil.mutListener.listen(3549)) {
            ConfigUtils.addIconsToOverflowMenu(this, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = null;
        if (!ListenerUtil.mutListener.listen(3564)) {
            switch(item.getItemId()) {
                case android.R.id.home:
                    if (!ListenerUtil.mutListener.listen(3551)) {
                        if (tooltipPopup != null) {
                            if (!ListenerUtil.mutListener.listen(3550)) {
                                tooltipPopup.dismiss();
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(3552)) {
                        showQRPopup();
                    }
                    return true;
                case R.id.menu_lock:
                    if (!ListenerUtil.mutListener.listen(3553)) {
                        lockAppService.lock();
                    }
                    return true;
                case R.id.menu_new_group:
                    if (!ListenerUtil.mutListener.listen(3554)) {
                        intent = new Intent(this, GroupAddActivity.class);
                    }
                    break;
                case R.id.menu_new_distribution_list:
                    if (!ListenerUtil.mutListener.listen(3555)) {
                        intent = new Intent(this, DistributionListAddActivity.class);
                    }
                    break;
                case R.id.my_backups:
                    if (!ListenerUtil.mutListener.listen(3556)) {
                        intent = new Intent(HomeActivity.this, BackupAdminActivity.class);
                    }
                    break;
                case R.id.webclient:
                    if (!ListenerUtil.mutListener.listen(3557)) {
                        intent = new Intent(HomeActivity.this, SessionsActivity.class);
                    }
                    break;
                case R.id.help:
                    if (!ListenerUtil.mutListener.listen(3558)) {
                        intent = new Intent(HomeActivity.this, SupportActivity.class);
                    }
                    break;
                case R.id.settings:
                    if (!ListenerUtil.mutListener.listen(3559)) {
                        AnimationUtil.startActivityForResult(this, null, new Intent(HomeActivity.this, SettingsActivity.class), ThreemaActivity.ACTIVITY_ID_SETTINGS);
                    }
                    break;
                case R.id.directory:
                    if (!ListenerUtil.mutListener.listen(3560)) {
                        intent = new Intent(HomeActivity.this, DirectoryActivity.class);
                    }
                    break;
                case R.id.threema_channel:
                    if (!ListenerUtil.mutListener.listen(3561)) {
                        confirmThreemaChannel();
                    }
                    break;
                case R.id.archived:
                    if (!ListenerUtil.mutListener.listen(3562)) {
                        intent = new Intent(HomeActivity.this, ArchiveActivity.class);
                    }
                    break;
                case R.id.globalsearch:
                    if (!ListenerUtil.mutListener.listen(3563)) {
                        intent = new Intent(HomeActivity.this, GlobalSearchActivity.class);
                    }
                default:
                    break;
            }
        }
        if (!ListenerUtil.mutListener.listen(3566)) {
            if (intent != null) {
                if (!ListenerUtil.mutListener.listen(3565)) {
                    AnimationUtil.startActivity(this, null, intent);
                }
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private int initActionBar() {
        if (!ListenerUtil.mutListener.listen(3567)) {
            toolbar = findViewById(R.id.main_toolbar);
        }
        if (!ListenerUtil.mutListener.listen(3568)) {
            setSupportActionBar(toolbar);
        }
        if (!ListenerUtil.mutListener.listen(3569)) {
            actionBar = getSupportActionBar();
        }
        AppCompatImageView toolbarLogoMain = toolbar.findViewById(R.id.toolbar_logo_main);
        ViewGroup.LayoutParams layoutParams = toolbarLogoMain.getLayoutParams();
        if (!ListenerUtil.mutListener.listen(3574)) {
            layoutParams.height = (ListenerUtil.mutListener.listen(3573) ? (ConfigUtils.getActionBarSize(this) % 3) : (ListenerUtil.mutListener.listen(3572) ? (ConfigUtils.getActionBarSize(this) * 3) : (ListenerUtil.mutListener.listen(3571) ? (ConfigUtils.getActionBarSize(this) - 3) : (ListenerUtil.mutListener.listen(3570) ? (ConfigUtils.getActionBarSize(this) + 3) : (ConfigUtils.getActionBarSize(this) / 3)))));
        }
        if (!ListenerUtil.mutListener.listen(3575)) {
            toolbarLogoMain.setLayoutParams(layoutParams);
        }
        if (!ListenerUtil.mutListener.listen(3576)) {
            toolbarLogoMain.setImageResource(R.drawable.logo_main);
        }
        if (!ListenerUtil.mutListener.listen(3577)) {
            toolbarLogoMain.setColorFilter(ConfigUtils.getColorFromAttribute(this, android.R.attr.textColorSecondary), PorterDuff.Mode.SRC_IN);
        }
        if (!ListenerUtil.mutListener.listen(3578)) {
            toolbarLogoMain.setContentDescription(getString(R.string.logo));
        }
        if (!ListenerUtil.mutListener.listen(3587)) {
            toolbarLogoMain.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(3586)) {
                        if (currentFragmentTag != null) {
                            Fragment currentFragment = getSupportFragmentManager().findFragmentByTag(currentFragmentTag);
                            if (!ListenerUtil.mutListener.listen(3585)) {
                                if ((ListenerUtil.mutListener.listen(3580) ? ((ListenerUtil.mutListener.listen(3579) ? (currentFragment != null || currentFragment.isAdded()) : (currentFragment != null && currentFragment.isAdded())) || !currentFragment.isHidden()) : ((ListenerUtil.mutListener.listen(3579) ? (currentFragment != null || currentFragment.isAdded()) : (currentFragment != null && currentFragment.isAdded())) && !currentFragment.isHidden()))) {
                                    if (!ListenerUtil.mutListener.listen(3584)) {
                                        if (currentFragment instanceof ContactsSectionFragment) {
                                            if (!ListenerUtil.mutListener.listen(3583)) {
                                                ((ContactsSectionFragment) currentFragment).onLogoClicked();
                                            }
                                        } else if (currentFragment instanceof MessageSectionFragment) {
                                            if (!ListenerUtil.mutListener.listen(3582)) {
                                                ((MessageSectionFragment) currentFragment).onLogoClicked();
                                            }
                                        } else if (currentFragment instanceof MyIDFragment) {
                                            if (!ListenerUtil.mutListener.listen(3581)) {
                                                ((MyIDFragment) currentFragment).onLogoClicked();
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
        if (!ListenerUtil.mutListener.listen(3588)) {
            updateDrawerImage();
        }
        return toolbar.getMinimumHeight();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (!ListenerUtil.mutListener.listen(3589)) {
            super.onPrepareOptionsMenu(menu);
        }
        if (!ListenerUtil.mutListener.listen(3617)) {
            if (serviceManager != null) {
                MenuItem lockMenuItem = menu.findItem(R.id.menu_lock);
                if (!ListenerUtil.mutListener.listen(3591)) {
                    if (lockMenuItem != null) {
                        if (!ListenerUtil.mutListener.listen(3590)) {
                            lockMenuItem.setVisible(lockAppService.isLockingEnabled());
                        }
                    }
                }
                MenuItem privateChatToggleMenuItem = menu.findItem(R.id.menu_toggle_private_chats);
                if (!ListenerUtil.mutListener.listen(3598)) {
                    if (privateChatToggleMenuItem != null) {
                        if (!ListenerUtil.mutListener.listen(3596)) {
                            if (preferenceService.isPrivateChatsHidden()) {
                                if (!ListenerUtil.mutListener.listen(3594)) {
                                    privateChatToggleMenuItem.setIcon(R.drawable.ic_outline_visibility);
                                }
                                if (!ListenerUtil.mutListener.listen(3595)) {
                                    privateChatToggleMenuItem.setTitle(R.string.title_show_private_chats);
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(3592)) {
                                    privateChatToggleMenuItem.setIcon(R.drawable.ic_outline_visibility_off);
                                }
                                if (!ListenerUtil.mutListener.listen(3593)) {
                                    privateChatToggleMenuItem.setTitle(R.string.title_hide_private_chats);
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(3597)) {
                            ConfigUtils.themeMenuItem(privateChatToggleMenuItem, ConfigUtils.getColorFromAttribute(this, R.attr.textColorSecondary));
                        }
                    }
                }
                Boolean addDisabled;
                boolean webDisabled = false;
                if (ConfigUtils.isWorkRestricted()) {
                    MenuItem backupsMenuItem = menu.findItem(R.id.my_backups);
                    if (!ListenerUtil.mutListener.listen(3604)) {
                        if (backupsMenuItem != null) {
                            if (!ListenerUtil.mutListener.listen(3603)) {
                                if ((ListenerUtil.mutListener.listen(3601) ? (AppRestrictionUtil.isBackupsDisabled(this) && ((ListenerUtil.mutListener.listen(3600) ? (AppRestrictionUtil.isDataBackupsDisabled(this) || ThreemaSafeMDMConfig.getInstance().isBackupDisabled()) : (AppRestrictionUtil.isDataBackupsDisabled(this) && ThreemaSafeMDMConfig.getInstance().isBackupDisabled())))) : (AppRestrictionUtil.isBackupsDisabled(this) || ((ListenerUtil.mutListener.listen(3600) ? (AppRestrictionUtil.isDataBackupsDisabled(this) || ThreemaSafeMDMConfig.getInstance().isBackupDisabled()) : (AppRestrictionUtil.isDataBackupsDisabled(this) && ThreemaSafeMDMConfig.getInstance().isBackupDisabled())))))) {
                                    if (!ListenerUtil.mutListener.listen(3602)) {
                                        backupsMenuItem.setVisible(false);
                                    }
                                }
                            }
                        }
                    }
                    addDisabled = AppRestrictionUtil.getBooleanRestriction(getString(R.string.restriction__disable_add_contact));
                    if (!ListenerUtil.mutListener.listen(3605)) {
                        webDisabled = AppRestrictionUtil.isWebDisabled(this);
                    }
                } else {
                    addDisabled = (ListenerUtil.mutListener.listen(3599) ? (this.contactService != null || this.contactService.getByIdentity(THREEMA_CHANNEL_IDENTITY) != null) : (this.contactService != null && this.contactService.getByIdentity(THREEMA_CHANNEL_IDENTITY) != null));
                }
                if (!ListenerUtil.mutListener.listen(3613)) {
                    if (ConfigUtils.isWorkBuild()) {
                        MenuItem menuItem = menu.findItem(R.id.directory);
                        if (!ListenerUtil.mutListener.listen(3609)) {
                            if (menuItem != null) {
                                if (!ListenerUtil.mutListener.listen(3608)) {
                                    menuItem.setVisible(ConfigUtils.isWorkDirectoryEnabled());
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(3610)) {
                            menuItem = menu.findItem(R.id.threema_channel);
                        }
                        if (!ListenerUtil.mutListener.listen(3612)) {
                            if (menuItem != null) {
                                if (!ListenerUtil.mutListener.listen(3611)) {
                                    menuItem.setVisible(false);
                                }
                            }
                        }
                    } else if ((ListenerUtil.mutListener.listen(3606) ? (addDisabled != null || addDisabled) : (addDisabled != null && addDisabled))) {
                        MenuItem menuItem = menu.findItem(R.id.threema_channel);
                        if (!ListenerUtil.mutListener.listen(3607)) {
                            menuItem.setVisible(false);
                        }
                    }
                }
                MenuItem webclientMenuItem = menu.findItem(R.id.webclient);
                if (!ListenerUtil.mutListener.listen(3616)) {
                    if (webclientMenuItem != null) {
                        if (!ListenerUtil.mutListener.listen(3615)) {
                            webclientMenuItem.setVisible(!((ListenerUtil.mutListener.listen(3614) ? (webDisabled && ConfigUtils.isBlackBerry()) : (webDisabled || ConfigUtils.isBlackBerry()))));
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }

    private void verifyPhoneCode(final String code) {
        if (!ListenerUtil.mutListener.listen(3625)) {
            new AsyncTask<Void, Void, String>() {

                @Override
                protected String doInBackground(Void... params) {
                    try {
                        if (!ListenerUtil.mutListener.listen(3620)) {
                            userService.verifyMobileNumber(code);
                        }
                    } catch (LinkMobileNoException e) {
                        if (!ListenerUtil.mutListener.listen(3618)) {
                            logger.error("Exception", e);
                        }
                        return getString(R.string.code_invalid);
                    } catch (Exception e) {
                        if (!ListenerUtil.mutListener.listen(3619)) {
                            logger.error("Exception", e);
                        }
                        return getString(R.string.verify_failed_summary);
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(String result) {
                    if (!ListenerUtil.mutListener.listen(3624)) {
                        if (result != null) {
                            if (!ListenerUtil.mutListener.listen(3623)) {
                                getSupportFragmentManager().beginTransaction().add(SimpleStringAlertDialog.newInstance(R.string.error, result), "ss").commitAllowingStateLoss();
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(3621)) {
                                Toast.makeText(HomeActivity.this, getString(R.string.verify_success_text), Toast.LENGTH_LONG).show();
                            }
                            if (!ListenerUtil.mutListener.listen(3622)) {
                                DialogUtil.dismissDialog(getSupportFragmentManager(), DIALOG_TAG_VERIFY_CODE, true);
                            }
                        }
                    }
                }
            }.execute();
        }
    }

    @Override
    public void onCallRequested(String tag) {
        if (!ListenerUtil.mutListener.listen(3633)) {
            if ((ListenerUtil.mutListener.listen(3630) ? (System.currentTimeMillis() >= userService.getMobileLinkingTime() + PHONE_REQUEST_DELAY) : (ListenerUtil.mutListener.listen(3629) ? (System.currentTimeMillis() <= userService.getMobileLinkingTime() + PHONE_REQUEST_DELAY) : (ListenerUtil.mutListener.listen(3628) ? (System.currentTimeMillis() > userService.getMobileLinkingTime() + PHONE_REQUEST_DELAY) : (ListenerUtil.mutListener.listen(3627) ? (System.currentTimeMillis() != userService.getMobileLinkingTime() + PHONE_REQUEST_DELAY) : (ListenerUtil.mutListener.listen(3626) ? (System.currentTimeMillis() == userService.getMobileLinkingTime() + PHONE_REQUEST_DELAY) : (System.currentTimeMillis() < userService.getMobileLinkingTime() + PHONE_REQUEST_DELAY))))))) {
                if (!ListenerUtil.mutListener.listen(3632)) {
                    SimpleStringAlertDialog.newInstance(R.string.verify_phonecall_text, getString(R.string.wait_one_minute)).show(getSupportFragmentManager(), "mi");
                }
            } else {
                if (!ListenerUtil.mutListener.listen(3631)) {
                    GenericAlertDialog.newInstance(R.string.verify_phonecall_text, R.string.prepare_call_message, R.string.ok, R.string.cancel).show(getSupportFragmentManager(), DIALOG_TAG_VERIFY_CODE_CONFIRM);
                }
            }
        }
    }

    private void reallyRequestCall() {
        if (!ListenerUtil.mutListener.listen(3639)) {
            new AsyncTask<Void, Void, String>() {

                @Override
                protected String doInBackground(Void... params) {
                    try {
                        if (!ListenerUtil.mutListener.listen(3636)) {
                            userService.makeMobileLinkCall();
                        }
                    } catch (LinkMobileNoException e) {
                        if (!ListenerUtil.mutListener.listen(3634)) {
                            logger.error("Exception", e);
                        }
                        return e.getMessage();
                    } catch (Exception e) {
                        if (!ListenerUtil.mutListener.listen(3635)) {
                            logger.error("Exception", e);
                        }
                        return getString(R.string.verify_failed_summary);
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(String result) {
                    if (!ListenerUtil.mutListener.listen(3638)) {
                        if (!TestUtil.empty(result)) {
                            if (!ListenerUtil.mutListener.listen(3637)) {
                                SimpleStringAlertDialog.newInstance(R.string.an_error_occurred, result).show(getSupportFragmentManager(), "le");
                            }
                        }
                    }
                }
            }.execute();
        }
    }

    @Override
    public void onYes(String tag, String code) {
        if (!ListenerUtil.mutListener.listen(3641)) {
            switch(tag) {
                case DIALOG_TAG_VERIFY_CODE:
                    if (!ListenerUtil.mutListener.listen(3640)) {
                        verifyPhoneCode(code);
                    }
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void onYes(String tag, Object data) {
        if (!ListenerUtil.mutListener.listen(3650)) {
            switch(tag) {
                case DIALOG_TAG_VERIFY_CODE_CONFIRM:
                    if (!ListenerUtil.mutListener.listen(3642)) {
                        reallyRequestCall();
                    }
                    break;
                case DIALOG_TAG_CANCEL_VERIFY:
                    if (!ListenerUtil.mutListener.listen(3643)) {
                        reallyCancelVerify();
                    }
                    break;
                case DIALOG_TAG_MASTERKEY_LOCKED:
                    if (!ListenerUtil.mutListener.listen(3644)) {
                        startActivityForResult(new Intent(HomeActivity.this, UnlockMasterKeyActivity.class), ThreemaActivity.ACTIVITY_ID_UNLOCK_MASTER_KEY);
                    }
                    break;
                case DIALOG_TAG_SERIAL_LOCKED:
                    if (!ListenerUtil.mutListener.listen(3645)) {
                        startActivityForResult(new Intent(HomeActivity.this, EnterSerialActivity.class), ThreemaActivity.ACTIVITY_ID_ENTER_SERIAL);
                    }
                    if (!ListenerUtil.mutListener.listen(3646)) {
                        finish();
                    }
                    break;
                case DIALOG_TAG_ENABLE_POLLING:
                    if (!ListenerUtil.mutListener.listen(3647)) {
                        enablePolling(serviceManager);
                    }
                    break;
                case DIALOG_TAG_FINISH_UP:
                    if (!ListenerUtil.mutListener.listen(3648)) {
                        System.exit(0);
                    }
                    break;
                case DIALOG_TAG_THREEMA_CHANNEL_VERIFY:
                    if (!ListenerUtil.mutListener.listen(3649)) {
                        addThreemaChannel();
                    }
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void onNo(String tag) {
    }

    @Override
    public void onNo(String tag, Object data) {
        if (!ListenerUtil.mutListener.listen(3654)) {
            switch(tag) {
                case DIALOG_TAG_MASTERKEY_LOCKED:
                    if (!ListenerUtil.mutListener.listen(3651)) {
                        finish();
                    }
                    break;
                case DIALOG_TAG_SERIAL_LOCKED:
                    if (!ListenerUtil.mutListener.listen(3652)) {
                        finish();
                    }
                    break;
                case DIALOG_TAG_ENABLE_POLLING:
                    if (!ListenerUtil.mutListener.listen(3653)) {
                        finish();
                    }
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void onResume() {
        if (!ListenerUtil.mutListener.listen(3655)) {
            logger.debug("onResume");
        }
        if (!ListenerUtil.mutListener.listen(3658)) {
            if (!isWhatsNewShown) {
                if (!ListenerUtil.mutListener.listen(3657)) {
                    ThreemaApplication.activityResumed(this);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(3656)) {
                    isWhatsNewShown = false;
                }
            }
        }
        MasterKey masterKey = ThreemaApplication.getMasterKey();
        if (!ListenerUtil.mutListener.listen(3662)) {
            if ((ListenerUtil.mutListener.listen(3659) ? (masterKey != null || masterKey.isProtected()) : (masterKey != null && masterKey.isProtected()))) {
                if (!ListenerUtil.mutListener.listen(3661)) {
                    if (!PassphraseService.isRunning()) {
                        if (!ListenerUtil.mutListener.listen(3660)) {
                            PassphraseService.start(this);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3667)) {
            if (serviceManager != null) {
                if (!ListenerUtil.mutListener.listen(3666)) {
                    new Thread(new Runnable() {

                        @Override
                        public void run() {
                            try {
                                FileService fileService = serviceManager.getFileService();
                                if (!ListenerUtil.mutListener.listen(3665)) {
                                    if (fileService != null) {
                                        if (!ListenerUtil.mutListener.listen(3664)) {
                                            fileService.cleanTempDirs();
                                        }
                                    }
                                }
                            } catch (FileSystemNotPresentException e) {
                                if (!ListenerUtil.mutListener.listen(3663)) {
                                    logger.error("Exception", e);
                                }
                            }
                        }
                    }).start();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3668)) {
            super.onResume();
        }
        if (!ListenerUtil.mutListener.listen(3669)) {
            showMainContent();
        }
    }

    @Override
    protected void onPause() {
        if (!ListenerUtil.mutListener.listen(3670)) {
            logger.debug("onPause");
        }
        if (!ListenerUtil.mutListener.listen(3671)) {
            super.onPause();
        }
        if (!ListenerUtil.mutListener.listen(3673)) {
            if (tooltipPopup != null) {
                if (!ListenerUtil.mutListener.listen(3672)) {
                    tooltipPopup.dismiss();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3674)) {
            ThreemaApplication.activityPaused(this);
        }
    }

    @Override
    public void onUserInteraction() {
        if (!ListenerUtil.mutListener.listen(3675)) {
            ThreemaApplication.activityUserInteract(this);
        }
        if (!ListenerUtil.mutListener.listen(3676)) {
            super.onUserInteraction();
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!ListenerUtil.mutListener.listen(3677)) {
            // http://www.androiddesignpatterns.com/2013/08/fragment-transaction-commit-state-loss.html
            super.onActivityResult(requestCode, resultCode, data);
        }
        if (!ListenerUtil.mutListener.listen(3702)) {
            switch(requestCode) {
                case ThreemaActivity.ACTIVITY_ID_WIZARDFIRST:
                    UserService userService = serviceManager.getUserService();
                    if (!ListenerUtil.mutListener.listen(3681)) {
                        if ((ListenerUtil.mutListener.listen(3678) ? (userService != null || userService.hasIdentity()) : (userService != null && userService.hasIdentity()))) {
                            if (!ListenerUtil.mutListener.listen(3680)) {
                                this.startMainActivity(null);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(3679)) {
                                finish();
                            }
                        }
                    }
                    break;
                case ThreemaActivity.ACTIVITY_ID_UNLOCK_MASTER_KEY:
                    MasterKey masterKey = ThreemaApplication.getMasterKey();
                    if (!ListenerUtil.mutListener.listen(3688)) {
                        if ((ListenerUtil.mutListener.listen(3682) ? (masterKey != null || masterKey.isLocked()) : (masterKey != null && masterKey.isLocked()))) {
                            if (!ListenerUtil.mutListener.listen(3687)) {
                                GenericAlertDialog.newInstance(R.string.master_key_locked, R.string.master_key_locked_want_exit, R.string.try_again, R.string.cancel).show(getSupportFragmentManager(), DIALOG_TAG_MASTERKEY_LOCKED);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(3684)) {
                                // hide after unlock
                                if (IntentDataUtil.hideAfterUnlock(this.getIntent())) {
                                    if (!ListenerUtil.mutListener.listen(3683)) {
                                        this.finish();
                                    }
                                    return;
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(3685)) {
                                this.startMainActivity(null);
                            }
                            if (!ListenerUtil.mutListener.listen(3686)) {
                                showWhatsNew();
                            }
                        }
                    }
                    break;
                case ThreemaActivity.ACTIVITY_ID_ENTER_SERIAL:
                    if (!ListenerUtil.mutListener.listen(3695)) {
                        if (serviceManager != null) {
                            LicenseService licenseService = null;
                            try {
                                if (!ListenerUtil.mutListener.listen(3690)) {
                                    licenseService = serviceManager.getLicenseService();
                                }
                            } catch (FileSystemNotPresentException e) {
                                if (!ListenerUtil.mutListener.listen(3689)) {
                                    logger.error("Exception", e);
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(3694)) {
                                if ((ListenerUtil.mutListener.listen(3691) ? (licenseService != null || !licenseService.isLicensed()) : (licenseService != null && !licenseService.isLicensed()))) {
                                    if (!ListenerUtil.mutListener.listen(3693)) {
                                        GenericAlertDialog.newInstance(R.string.enter_serial_title, R.string.serial_required_want_exit, R.string.try_again, R.string.cancel).show(getSupportFragmentManager(), DIALOG_TAG_SERIAL_LOCKED);
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(3692)) {
                                        this.startMainActivity(null);
                                    }
                                }
                            }
                        }
                    }
                    break;
                case ThreemaActivity.ACTIVITY_ID_SETTINGS:
                    if (!ListenerUtil.mutListener.listen(3696)) {
                        this.invalidateOptionsMenu();
                    }
                    break;
                case ThreemaActivity.ACTIVITY_ID_ID_SECTION:
                    if (!ListenerUtil.mutListener.listen(3699)) {
                        if (resultCode == ThreemaActivity.RESULT_RESTART) {
                            Intent i = getIntent();
                            if (!ListenerUtil.mutListener.listen(3697)) {
                                finish();
                            }
                            if (!ListenerUtil.mutListener.listen(3698)) {
                                startActivity(i);
                            }
                        }
                    }
                    break;
                case ThreemaActivity.ACTIVITY_ID_CONTACT_DETAIL:
                case ThreemaActivity.ACTIVITY_ID_GROUP_DETAIL:
                case ThreemaActivity.ACTIVITY_ID_COMPOSE_MESSAGE:
                    break;
                case REQUEST_CODE_WHATSNEW:
                    if (!ListenerUtil.mutListener.listen(3701)) {
                        if (!TooltipPopup.isDismissed(this, TOOLTIP_TAG)) {
                            if (!ListenerUtil.mutListener.listen(3700)) {
                                toolbar.postDelayed(() -> {
                                    if (isFinishing() || isDestroyed()) {
                                        return;
                                    }
                                    if (tooltipPopup != null) {
                                        tooltipPopup.dismiss();
                                    }
                                    int[] location = getMiniAvatarLocation();
                                    location[1] += toolbar.getHeight() / 2;
                                    tooltipPopup = new TooltipPopup(HomeActivity.this, TOOLTIP_TAG, R.layout.popup_tooltip_top_left);
                                    tooltipPopup.show(this, toolbar, getString(R.string.tooltip_identity_popup), TooltipPopup.ALIGN_BELOW_ANCHOR_ARROW_LEFT, location, 0);
                                }, 1000);
                            }
                        }
                    }
                    break;
                default:
                    break;
            }
        }
    }

    private void updateAppLogo() {
        if (!ListenerUtil.mutListener.listen(3703)) {
            // update app logo disabled on "not-work" builds
            if (!ConfigUtils.isWorkBuild()) {
                return;
            }
        }
        File customAppIcon = null;
        try {
            if (!ListenerUtil.mutListener.listen(3705)) {
                customAppIcon = serviceManager.getFileService().getAppLogo(ConfigUtils.getAppTheme(this));
            }
        } catch (FileSystemNotPresentException e) {
            if (!ListenerUtil.mutListener.listen(3704)) {
                logger.error("Exception", e);
            }
        }
        if (!ListenerUtil.mutListener.listen(3711)) {
            if ((ListenerUtil.mutListener.listen(3707) ? ((ListenerUtil.mutListener.listen(3706) ? (customAppIcon != null || customAppIcon.exists()) : (customAppIcon != null && customAppIcon.exists())) || this.toolbar != null) : ((ListenerUtil.mutListener.listen(3706) ? (customAppIcon != null || customAppIcon.exists()) : (customAppIcon != null && customAppIcon.exists())) && this.toolbar != null))) {
                // set the icon as app icon
                ImageView headerImageView = toolbar.findViewById(R.id.toolbar_logo_main);
                if (!ListenerUtil.mutListener.listen(3710)) {
                    if (headerImageView != null) {
                        if (!ListenerUtil.mutListener.listen(3708)) {
                            headerImageView.clearColorFilter();
                        }
                        if (!ListenerUtil.mutListener.listen(3709)) {
                            headerImageView.setImageBitmap(BitmapUtil.safeGetBitmapFromUri(this, Uri.fromFile(customAppIcon), ConfigUtils.getUsableWidth(getWindowManager()), false, false));
                        }
                    }
                }
            }
        }
    }

    public void addThreemaChannel() {
        final MessageService messageService;
        try {
            messageService = serviceManager.getMessageService();
        } catch (Exception e) {
            return;
        }
        if (!ListenerUtil.mutListener.listen(3731)) {
            new AsyncTask<Void, Void, Exception>() {

                ContactModel newContactModel;

                @Override
                protected void onPreExecute() {
                    if (!ListenerUtil.mutListener.listen(3712)) {
                        GenericProgressDialog.newInstance(R.string.threema_channel, R.string.please_wait).show(getSupportFragmentManager(), THREEMA_CHANNEL_IDENTITY);
                    }
                }

                @Override
                protected Exception doInBackground(Void... params) {
                    try {
                        if (!ListenerUtil.mutListener.listen(3713)) {
                            newContactModel = contactService.createContactByIdentity(THREEMA_CHANNEL_IDENTITY, true);
                        }
                    } catch (Exception e) {
                        return e;
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Exception exception) {
                    if (!ListenerUtil.mutListener.listen(3714)) {
                        DialogUtil.dismissDialog(getSupportFragmentManager(), THREEMA_CHANNEL_IDENTITY, true);
                    }
                    if (!ListenerUtil.mutListener.listen(3730)) {
                        if ((ListenerUtil.mutListener.listen(3715) ? (exception == null && exception instanceof EntryAlreadyExistsException) : (exception == null || exception instanceof EntryAlreadyExistsException))) {
                            if (!ListenerUtil.mutListener.listen(3717)) {
                                launchThreemaChannelChat();
                            }
                            if (!ListenerUtil.mutListener.listen(3729)) {
                                if (exception == null) {
                                    if (!ListenerUtil.mutListener.listen(3728)) {
                                        new Thread(new Runnable() {

                                            @Override
                                            public void run() {
                                                try {
                                                    MessageReceiver receiver = contactService.createReceiver(newContactModel);
                                                    if (!ListenerUtil.mutListener.listen(3721)) {
                                                        if (!getResources().getConfiguration().locale.getLanguage().startsWith("de")) {
                                                            if (!ListenerUtil.mutListener.listen(3718)) {
                                                                Thread.sleep(1000);
                                                            }
                                                            if (!ListenerUtil.mutListener.listen(3719)) {
                                                                messageService.sendText("en", receiver);
                                                            }
                                                            if (!ListenerUtil.mutListener.listen(3720)) {
                                                                Thread.sleep(500);
                                                            }
                                                        }
                                                    }
                                                    if (!ListenerUtil.mutListener.listen(3722)) {
                                                        Thread.sleep(1000);
                                                    }
                                                    if (!ListenerUtil.mutListener.listen(3723)) {
                                                        messageService.sendText(THREEMA_CHANNEL_START_NEWS_COMMAND, receiver);
                                                    }
                                                    if (!ListenerUtil.mutListener.listen(3724)) {
                                                        Thread.sleep(1500);
                                                    }
                                                    if (!ListenerUtil.mutListener.listen(3725)) {
                                                        messageService.sendText(ConfigUtils.isWorkBuild() ? THREEMA_CHANNEL_WORK_COMMAND : THREEMA_CHANNEL_START_ANDROID_COMMAND, receiver);
                                                    }
                                                    if (!ListenerUtil.mutListener.listen(3726)) {
                                                        Thread.sleep(1500);
                                                    }
                                                    if (!ListenerUtil.mutListener.listen(3727)) {
                                                        messageService.sendText(THREEMA_CHANNEL_INFO_COMMAND, receiver);
                                                    }
                                                } catch (Exception e) {
                                                }
                                            }
                                        }).start();
                                    }
                                }
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(3716)) {
                                Toast.makeText(HomeActivity.this, R.string.internet_connection_required, Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                }
            }.execute();
        }
    }

    private void launchThreemaChannelChat() {
        Intent intent = new Intent(getApplicationContext(), ComposeMessageActivity.class);
        if (!ListenerUtil.mutListener.listen(3732)) {
            IntentDataUtil.append(THREEMA_CHANNEL_IDENTITY, intent);
        }
        if (!ListenerUtil.mutListener.listen(3733)) {
            startActivity(intent);
        }
    }

    @AnyThread
    private void updateConnectionIndicator(final ConnectionState connectionState) {
        if (!ListenerUtil.mutListener.listen(3734)) {
            logger.debug("connectionState = " + connectionState);
        }
        if (!ListenerUtil.mutListener.listen(3735)) {
            RuntimeUtil.runOnUiThread(() -> {
                connectionIndicator = findViewById(R.id.connection_indicator);
                if (connectionIndicator != null) {
                    ConnectionIndicatorUtil.getInstance().updateConnectionIndicator(connectionIndicator, connectionState);
                    invalidateOptionsMenu();
                }
            });
        }
    }

    private void confirmThreemaChannel() {
        if (!ListenerUtil.mutListener.listen(3738)) {
            if (contactService.getByIdentity(THREEMA_CHANNEL_IDENTITY) == null) {
                if (!ListenerUtil.mutListener.listen(3737)) {
                    GenericAlertDialog.newInstance(R.string.threema_channel, R.string.threema_channel_intro, R.string.ok, R.string.cancel).show(getSupportFragmentManager(), DIALOG_TAG_THREEMA_CHANNEL_VERIFY);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(3736)) {
                    launchThreemaChannelChat();
                }
            }
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        if (!ListenerUtil.mutListener.listen(3739)) {
            super.onSaveInstanceState(outState);
        }
        if (!ListenerUtil.mutListener.listen(3741)) {
            if (currentFragmentTag != null) {
                if (!ListenerUtil.mutListener.listen(3740)) {
                    outState.putString(BUNDLE_CURRENT_FRAGMENT_TAG, currentFragmentTag);
                }
            }
        }
    }
}
