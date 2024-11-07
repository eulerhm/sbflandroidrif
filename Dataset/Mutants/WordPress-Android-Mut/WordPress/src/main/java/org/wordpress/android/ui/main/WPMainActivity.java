package org.wordpress.android.ui.main;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.RemoteInput;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.wordpress.android.BuildConfig;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.analytics.AnalyticsTracker;
import org.wordpress.android.analytics.AnalyticsTracker.Stat;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.generated.AccountActionBuilder;
import org.wordpress.android.fluxc.generated.SiteActionBuilder;
import org.wordpress.android.fluxc.model.AccountModel;
import org.wordpress.android.fluxc.model.PostModel;
import org.wordpress.android.fluxc.model.SiteModel;
import org.wordpress.android.fluxc.network.rest.wpcom.site.PrivateAtomicCookie;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.fluxc.store.AccountStore.AuthenticationErrorType;
import org.wordpress.android.fluxc.store.AccountStore.OnAccountChanged;
import org.wordpress.android.fluxc.store.AccountStore.OnAuthenticationChanged;
import org.wordpress.android.fluxc.store.AccountStore.UpdateTokenPayload;
import org.wordpress.android.fluxc.store.PostStore;
import org.wordpress.android.fluxc.store.PostStore.OnPostUploaded;
import org.wordpress.android.fluxc.store.QuickStartStore;
import org.wordpress.android.fluxc.store.QuickStartStore.QuickStartExistingSiteTask;
import org.wordpress.android.fluxc.store.QuickStartStore.QuickStartNewSiteTask;
import org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.fluxc.store.SiteStore.CompleteQuickStartPayload;
import org.wordpress.android.fluxc.store.SiteStore.OnAllSitesMobileEditorChanged;
import org.wordpress.android.fluxc.store.SiteStore.OnQuickStartCompleted;
import org.wordpress.android.fluxc.store.SiteStore.OnSiteChanged;
import org.wordpress.android.fluxc.store.SiteStore.OnSiteEditorsChanged;
import org.wordpress.android.fluxc.store.SiteStore.OnSiteRemoved;
import org.wordpress.android.login.LoginAnalyticsListener;
import org.wordpress.android.networking.ConnectionChangeReceiver;
import org.wordpress.android.push.GCMMessageHandler;
import org.wordpress.android.push.GCMMessageService;
import org.wordpress.android.push.GCMRegistrationIntentService;
import org.wordpress.android.push.NativeNotificationsUtils;
import org.wordpress.android.push.NotificationType;
import org.wordpress.android.push.NotificationsProcessingService;
import org.wordpress.android.ui.ActivityId;
import org.wordpress.android.ui.ActivityLauncher;
import org.wordpress.android.ui.JetpackConnectionSource;
import org.wordpress.android.ui.JetpackConnectionWebViewActivity;
import org.wordpress.android.ui.LocaleAwareActivity;
import org.wordpress.android.ui.PagePostCreationSourcesDetail;
import org.wordpress.android.ui.RequestCodes;
import org.wordpress.android.ui.ShortcutsNavigator;
import org.wordpress.android.ui.WPTooltipView;
import org.wordpress.android.ui.accounts.LoginActivity;
import org.wordpress.android.ui.accounts.SignupEpilogueActivity;
import org.wordpress.android.ui.bloggingprompts.onboarding.BloggingPromptsOnboardingDialogFragment;
import org.wordpress.android.ui.bloggingprompts.onboarding.BloggingPromptsOnboardingDialogFragment.DialogType;
import org.wordpress.android.ui.bloggingprompts.onboarding.BloggingPromptsReminderSchedulerListener;
import org.wordpress.android.ui.bloggingreminders.BloggingReminderUtils;
import org.wordpress.android.ui.bloggingreminders.BloggingRemindersViewModel;
import org.wordpress.android.ui.main.WPMainNavigationView.OnPageListener;
import org.wordpress.android.ui.main.WPMainNavigationView.PageType;
import org.wordpress.android.ui.mlp.ModalLayoutPickerFragment;
import org.wordpress.android.ui.mysite.MySiteFragment;
import org.wordpress.android.ui.mysite.MySiteViewModel;
import org.wordpress.android.ui.mysite.SelectedSiteRepository;
import org.wordpress.android.ui.mysite.cards.quickstart.QuickStartRepository;
import org.wordpress.android.ui.mysite.tabs.BloggingPromptsOnboardingListener;
import org.wordpress.android.ui.notifications.NotificationEvents;
import org.wordpress.android.ui.notifications.NotificationsListFragment;
import org.wordpress.android.ui.notifications.SystemNotificationsTracker;
import org.wordpress.android.ui.notifications.adapters.NotesAdapter;
import org.wordpress.android.ui.notifications.receivers.NotificationsPendingDraftsReceiver;
import org.wordpress.android.ui.notifications.utils.NotificationsActions;
import org.wordpress.android.ui.notifications.utils.NotificationsUtils;
import org.wordpress.android.ui.notifications.utils.PendingDraftsNotificationsUtils;
import org.wordpress.android.ui.photopicker.MediaPickerLauncher;
import org.wordpress.android.ui.posts.BasicFragmentDialog.BasicDialogNegativeClickInterface;
import org.wordpress.android.ui.posts.BasicFragmentDialog.BasicDialogPositiveClickInterface;
import org.wordpress.android.ui.posts.EditPostActivity;
import org.wordpress.android.ui.posts.PostUtils.EntryPoint;
import org.wordpress.android.ui.posts.QuickStartPromptDialogFragment.QuickStartPromptClickInterface;
import org.wordpress.android.ui.prefs.AppPrefs;
import org.wordpress.android.ui.prefs.AppSettingsFragment;
import org.wordpress.android.ui.prefs.SiteSettingsFragment;
import org.wordpress.android.ui.quickstart.QuickStartMySitePrompts;
import org.wordpress.android.ui.quickstart.QuickStartTracker;
import org.wordpress.android.ui.reader.ReaderFragment;
import org.wordpress.android.ui.reader.services.update.ReaderUpdateLogic.UpdateTask;
import org.wordpress.android.ui.reader.services.update.ReaderUpdateServiceStarter;
import org.wordpress.android.ui.reader.tracker.ReaderTracker;
import org.wordpress.android.ui.sitecreation.misc.SiteCreationSource;
import org.wordpress.android.ui.stats.StatsTimeframe;
import org.wordpress.android.ui.stats.intro.StatsNewFeaturesIntroDialogFragment;
import org.wordpress.android.ui.stories.intro.StoriesIntroDialogFragment;
import org.wordpress.android.ui.uploads.UploadActionUseCase;
import org.wordpress.android.ui.uploads.UploadUtils;
import org.wordpress.android.ui.uploads.UploadUtilsWrapper;
import org.wordpress.android.ui.whatsnew.FeatureAnnouncementDialogFragment;
import org.wordpress.android.util.AniUtils;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.AppLog.T;
import org.wordpress.android.util.AuthenticationDialogUtils;
import org.wordpress.android.util.BuildConfigWrapper;
import org.wordpress.android.util.DeviceUtils;
import org.wordpress.android.util.FluxCUtils;
import org.wordpress.android.util.NetworkUtils;
import org.wordpress.android.util.ProfilingUtils;
import org.wordpress.android.util.QuickStartUtils;
import org.wordpress.android.util.QuickStartUtilsWrapper;
import org.wordpress.android.util.ShortcutUtils;
import org.wordpress.android.util.SiteUtils;
import org.wordpress.android.util.ToastUtils;
import org.wordpress.android.util.WPActivityUtils;
import org.wordpress.android.util.analytics.AnalyticsTrackerWrapper;
import org.wordpress.android.util.analytics.AnalyticsUtils;
import org.wordpress.android.util.analytics.service.InstallationReferrerServiceStarter;
import org.wordpress.android.util.config.MySiteDashboardTodaysStatsCardFeatureConfig;
import org.wordpress.android.util.config.StatsRevampV2FeatureConfig;
import org.wordpress.android.util.extensions.ViewExtensionsKt;
import org.wordpress.android.viewmodel.main.WPMainActivityViewModel;
import org.wordpress.android.viewmodel.main.WPMainActivityViewModel.FocusPointInfo;
import org.wordpress.android.viewmodel.mlp.ModalLayoutPickerViewModel;
import org.wordpress.android.widgets.AppRatingDialog;
import org.wordpress.android.workers.notification.createsite.CreateSiteNotificationScheduler;
import org.wordpress.android.workers.weeklyroundup.WeeklyRoundupScheduler;
import java.util.EnumSet;
import java.util.List;
import javax.inject.Inject;
import static androidx.lifecycle.Lifecycle.State.STARTED;
import static org.wordpress.android.WordPress.SITE;
import static org.wordpress.android.editor.gutenberg.GutenbergEditorFragment.ARG_STORY_BLOCK_ID;
import static org.wordpress.android.fluxc.store.SiteStore.CompleteQuickStartVariant.NEXT_STEPS;
import static org.wordpress.android.login.LoginAnalyticsListener.CreatedAccountSource.EMAIL;
import static org.wordpress.android.push.NotificationsProcessingService.ARG_NOTIFICATION_TYPE;
import static org.wordpress.android.ui.JetpackConnectionSource.NOTIFICATIONS;
import dagger.hilt.android.AndroidEntryPoint;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Main activity which hosts sites, reader, me and notifications pages
 */
@AndroidEntryPoint
public class WPMainActivity extends LocaleAwareActivity implements OnPageListener, BottomNavController, BasicDialogPositiveClickInterface, BasicDialogNegativeClickInterface, QuickStartPromptClickInterface, BloggingPromptsReminderSchedulerListener, BloggingPromptsOnboardingListener, UpdateSelectedSiteListener {

    public static final String ARG_CONTINUE_JETPACK_CONNECT = "ARG_CONTINUE_JETPACK_CONNECT";

    public static final String ARG_CREATE_SITE = "ARG_CREATE_SITE";

    public static final String ARG_DO_LOGIN_UPDATE = "ARG_DO_LOGIN_UPDATE";

    public static final String ARG_IS_MAGIC_LINK_LOGIN = "ARG_IS_MAGIC_LINK_LOGIN";

    public static final String ARG_IS_MAGIC_LINK_SIGNUP = "ARG_IS_MAGIC_LINK_SIGNUP";

    public static final String ARG_JETPACK_CONNECT_SOURCE = "ARG_JETPACK_CONNECT_SOURCE";

    public static final String ARG_OLD_SITES_IDS = "ARG_OLD_SITES_IDS";

    public static final String ARG_OPENED_FROM_PUSH = "opened_from_push";

    public static final String ARG_SHOW_LOGIN_EPILOGUE = "show_login_epilogue";

    public static final String ARG_SHOW_SIGNUP_EPILOGUE = "show_signup_epilogue";

    public static final String ARG_SHOW_SITE_CREATION = "show_site_creation";

    public static final String ARG_SITE_CREATION_SOURCE = "ARG_SITE_CREATION_SOURCE";

    public static final String ARG_WP_COM_SIGN_UP = "sign_up";

    public static final String ARG_OPEN_PAGE = "open_page";

    public static final String ARG_MY_SITE = "show_my_site";

    public static final String ARG_NOTIFICATIONS = "show_notifications";

    public static final String ARG_READER = "show_reader";

    public static final String ARG_READER_BOOKMARK_TAB = "show_reader_bookmark_tab";

    public static final String ARG_EDITOR = "show_editor";

    public static final String ARG_SHOW_ZENDESK_NOTIFICATIONS = "show_zendesk_notifications";

    public static final String ARG_STATS = "show_stats";

    public static final String ARG_STATS_TIMEFRAME = "stats_timeframe";

    public static final String ARG_PAGES = "show_pages";

    public static final String ARG_BLOGGING_PROMPTS_ONBOARDING = "show_blogging_prompts_onboarding";

    public static final String ARG_EDITOR_PROMPT_ID = "editor_prompt_id";

    public static final String ARG_DISMISS_NOTIFICATION = "dismiss_notification";

    public static final String ARG_OPEN_BLOGGING_REMINDERS = "show_blogging_reminders_flow";

    public static final String ARG_SELECTED_SITE = "SELECTED_SITE_ID";

    public static final String ARG_STAT_TO_TRACK = "stat_to_track";

    public static final String ARG_EDITOR_ORIGIN = "editor_origin";

    // Track the first `onResume` event for the current session so we can use it for Analytics tracking
    private static boolean mFirstResume = true;

    private WPMainNavigationView mBottomNav;

    private TextView mConnectionBar;

    private JetpackConnectionSource mJetpackConnectSource;

    private boolean mIsMagicLinkLogin;

    private boolean mIsMagicLinkSignup;

    private WPMainActivityViewModel mViewModel;

    private ModalLayoutPickerViewModel mMLPViewModel;

    private BloggingRemindersViewModel mBloggingRemindersViewModel;

    private FloatingActionButton mFloatingActionButton;

    private WPTooltipView mFabTooltip;

    private static final String MAIN_BOTTOM_SHEET_TAG = "MAIN_BOTTOM_SHEET_TAG";

    private static final String BLOGGING_REMINDERS_BOTTOM_SHEET_TAG = "BLOGGING_REMINDERS_BOTTOM_SHEET_TAG";

    private final Handler mHandler = new Handler();

    @Inject
    AccountStore mAccountStore;

    @Inject
    SiteStore mSiteStore;

    @Inject
    PostStore mPostStore;

    @Inject
    Dispatcher mDispatcher;

    @Inject
    protected LoginAnalyticsListener mLoginAnalyticsListener;

    @Inject
    ShortcutsNavigator mShortcutsNavigator;

    @Inject
    ShortcutUtils mShortcutUtils;

    @Inject
    QuickStartStore mQuickStartStore;

    @Inject
    UploadActionUseCase mUploadActionUseCase;

    @Inject
    SystemNotificationsTracker mSystemNotificationsTracker;

    @Inject
    GCMMessageHandler mGCMMessageHandler;

    @Inject
    UploadUtilsWrapper mUploadUtilsWrapper;

    @Inject
    ViewModelProvider.Factory mViewModelFactory;

    @Inject
    PrivateAtomicCookie mPrivateAtomicCookie;

    @Inject
    ReaderTracker mReaderTracker;

    @Inject
    MediaPickerLauncher mMediaPickerLauncher;

    @Inject
    SelectedSiteRepository mSelectedSiteRepository;

    @Inject
    QuickStartRepository mQuickStartRepository;

    @Inject
    QuickStartUtilsWrapper mQuickStartUtilsWrapper;

    @Inject
    AnalyticsTrackerWrapper mAnalyticsTrackerWrapper;

    @Inject
    CreateSiteNotificationScheduler mCreateSiteNotificationScheduler;

    @Inject
    WeeklyRoundupScheduler mWeeklyRoundupScheduler;

    @Inject
    MySiteDashboardTodaysStatsCardFeatureConfig mTodaysStatsCardFeatureConfig;

    @Inject
    QuickStartTracker mQuickStartTracker;

    @Inject
    StatsRevampV2FeatureConfig mStatsRevampV2FeatureConfig;

    @Inject
    BuildConfigWrapper mBuildConfigWrapper;

    /*
     * fragments implement this if their contents can be scrolled, called when user
     * requests to scroll to the top
     */
    public interface OnScrollToTopListener {

        void onScrollToTop();
    }

    /*
     * fragments implement this and return true if the fragment handles the back button
     * and doesn't want the activity to handle it as well
     */
    public interface OnActivityBackPressedListener {

        boolean onActivityBackPressed();
    }

    private final Runnable mShowFabFocusPoint = () -> {
        if (isFinishing()) {
            return;
        }
        boolean focusPointVisible = findViewById(R.id.fab_container).findViewById(R.id.quick_start_focus_point) != null;
        if (!focusPointVisible) {
            addOrRemoveQuickStartFocusPoint(QuickStartNewSiteTask.PUBLISH_POST, true);
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(5912)) {
            ProfilingUtils.split("WPMainActivity.onCreate");
        }
        if (!ListenerUtil.mutListener.listen(5913)) {
            ((WordPress) getApplication()).component().inject(this);
        }
        if (!ListenerUtil.mutListener.listen(5914)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(5915)) {
            setContentView(R.layout.main_activity);
        }
        if (!ListenerUtil.mutListener.listen(5916)) {
            mBottomNav = findViewById(R.id.bottom_navigation);
        }
        if (!ListenerUtil.mutListener.listen(5917)) {
            mBottomNav.init(getSupportFragmentManager(), this);
        }
        if (!ListenerUtil.mutListener.listen(5918)) {
            mConnectionBar = findViewById(R.id.connection_bar);
        }
        if (!ListenerUtil.mutListener.listen(5919)) {
            mConnectionBar.setOnClickListener(v -> {
                // slide out the bar on click, then re-check connection after a brief delay
                AniUtils.animateBottomBar(mConnectionBar, false);
                mHandler.postDelayed(() -> {
                    if (!isFinishing()) {
                        checkConnection();
                    }
                }, 2000);
            });
        }
        if (!ListenerUtil.mutListener.listen(5920)) {
            mIsMagicLinkLogin = getIntent().getBooleanExtra(ARG_IS_MAGIC_LINK_LOGIN, false);
        }
        if (!ListenerUtil.mutListener.listen(5921)) {
            mIsMagicLinkSignup = getIntent().getBooleanExtra(ARG_IS_MAGIC_LINK_SIGNUP, false);
        }
        if (!ListenerUtil.mutListener.listen(5922)) {
            mJetpackConnectSource = (JetpackConnectionSource) getIntent().getSerializableExtra(ARG_JETPACK_CONNECT_SOURCE);
        }
        String authTokenToSet = null;
        boolean canShowAppRatingPrompt = savedInstanceState != null;
        if (!ListenerUtil.mutListener.listen(5959)) {
            if (savedInstanceState == null) {
                if (!ListenerUtil.mutListener.listen(5924)) {
                    if (!AppPrefs.isInstallationReferrerObtained()) {
                        if (!ListenerUtil.mutListener.listen(5923)) {
                            InstallationReferrerServiceStarter.startService(this, null);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(5956)) {
                    if (FluxCUtils.isSignedInWPComOrHasWPOrgSite(mAccountStore, mSiteStore)) {
                        NotificationType notificationType = (NotificationType) getIntent().getSerializableExtra(ARG_NOTIFICATION_TYPE);
                        if (!ListenerUtil.mutListener.listen(5930)) {
                            if (notificationType != null) {
                                if (!ListenerUtil.mutListener.listen(5929)) {
                                    mSystemNotificationsTracker.trackTappedNotification(notificationType);
                                }
                            }
                        }
                        // open note detail if activity called from a push
                        boolean openedFromPush = ((ListenerUtil.mutListener.listen(5931) ? (getIntent() != null || getIntent().getBooleanExtra(ARG_OPENED_FROM_PUSH, false)) : (getIntent() != null && getIntent().getBooleanExtra(ARG_OPENED_FROM_PUSH, false))));
                        boolean openedFromShortcut = ((ListenerUtil.mutListener.listen(5932) ? (getIntent() != null || getIntent().getStringExtra(ShortcutsNavigator.ACTION_OPEN_SHORTCUT) != null) : (getIntent() != null && getIntent().getStringExtra(ShortcutsNavigator.ACTION_OPEN_SHORTCUT) != null)));
                        boolean openRequestedPage = ((ListenerUtil.mutListener.listen(5933) ? (getIntent() != null || getIntent().hasExtra(ARG_OPEN_PAGE)) : (getIntent() != null && getIntent().hasExtra(ARG_OPEN_PAGE))));
                        boolean isQuickStartRequestedFromPush = ((ListenerUtil.mutListener.listen(5934) ? (getIntent() != null || getIntent().getBooleanExtra(MySiteViewModel.ARG_QUICK_START_TASK, false)) : (getIntent() != null && getIntent().getBooleanExtra(MySiteViewModel.ARG_QUICK_START_TASK, false))));
                        boolean openZendeskTicketsFromPush = ((ListenerUtil.mutListener.listen(5935) ? (getIntent() != null || getIntent().getBooleanExtra(ARG_SHOW_ZENDESK_NOTIFICATIONS, false)) : (getIntent() != null && getIntent().getBooleanExtra(ARG_SHOW_ZENDESK_NOTIFICATIONS, false))));
                        if (!ListenerUtil.mutListener.listen(5955)) {
                            if (openZendeskTicketsFromPush) {
                                if (!ListenerUtil.mutListener.listen(5954)) {
                                    launchZendeskMyTickets();
                                }
                            } else if (openedFromPush) {
                                if (!ListenerUtil.mutListener.listen(5950)) {
                                    // open note detail if activity called from a push
                                    getIntent().putExtra(ARG_OPENED_FROM_PUSH, false);
                                }
                                if (!ListenerUtil.mutListener.listen(5953)) {
                                    if (getIntent().hasExtra(NotificationsPendingDraftsReceiver.POST_ID_EXTRA)) {
                                        if (!ListenerUtil.mutListener.listen(5952)) {
                                            launchWithPostId(getIntent().getIntExtra(NotificationsPendingDraftsReceiver.POST_ID_EXTRA, 0), getIntent().getBooleanExtra(NotificationsPendingDraftsReceiver.IS_PAGE_EXTRA, false));
                                        }
                                    } else {
                                        if (!ListenerUtil.mutListener.listen(5951)) {
                                            launchWithNoteId();
                                        }
                                    }
                                }
                            } else if (openedFromShortcut) {
                                if (!ListenerUtil.mutListener.listen(5948)) {
                                    initSelectedSite();
                                }
                                if (!ListenerUtil.mutListener.listen(5949)) {
                                    mShortcutsNavigator.showTargetScreen(getIntent().getStringExtra(ShortcutsNavigator.ACTION_OPEN_SHORTCUT), this, getSelectedSite());
                                }
                            } else if (openRequestedPage) {
                                if (!ListenerUtil.mutListener.listen(5947)) {
                                    handleOpenPageIntent(getIntent());
                                }
                            } else if (isQuickStartRequestedFromPush) {
                                if (!ListenerUtil.mutListener.listen(5945)) {
                                    // when app is opened from Quick Start reminder switch to MySite fragment
                                    mBottomNav.setCurrentSelectedPage(PageType.MY_SITE);
                                }
                                if (!ListenerUtil.mutListener.listen(5946)) {
                                    mQuickStartTracker.track(Stat.QUICK_START_NOTIFICATION_TAPPED);
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(5939)) {
                                    if (mIsMagicLinkLogin) {
                                        if (!ListenerUtil.mutListener.listen(5938)) {
                                            if (mAccountStore.hasAccessToken()) {
                                                if (!ListenerUtil.mutListener.listen(5937)) {
                                                    ToastUtils.showToast(this, R.string.login_already_logged_in_wpcom);
                                                }
                                            } else {
                                                if (!ListenerUtil.mutListener.listen(5936)) {
                                                    authTokenToSet = getAuthToken();
                                                }
                                            }
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(5944)) {
                                    // Continue Jetpack connect flow if coming from login/signup magic link.
                                    if ((ListenerUtil.mutListener.listen(5941) ? ((ListenerUtil.mutListener.listen(5940) ? (getIntent() != null || getIntent().getExtras() != null) : (getIntent() != null && getIntent().getExtras() != null)) || getIntent().getExtras().getBoolean(ARG_CONTINUE_JETPACK_CONNECT, false)) : ((ListenerUtil.mutListener.listen(5940) ? (getIntent() != null || getIntent().getExtras() != null) : (getIntent() != null && getIntent().getExtras() != null)) && getIntent().getExtras().getBoolean(ARG_CONTINUE_JETPACK_CONNECT, false)))) {
                                        if (!ListenerUtil.mutListener.listen(5943)) {
                                            JetpackConnectionWebViewActivity.startJetpackConnectionFlow(this, NOTIFICATIONS, (SiteModel) getIntent().getSerializableExtra(SITE), mAccountStore.hasAccessToken());
                                        }
                                    } else {
                                        if (!ListenerUtil.mutListener.listen(5942)) {
                                            canShowAppRatingPrompt = true;
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(5928)) {
                            if (mIsMagicLinkLogin) {
                                if (!ListenerUtil.mutListener.listen(5927)) {
                                    authTokenToSet = getAuthToken();
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(5925)) {
                                    showSignInForResultBasedOnIsJetpackAppBuildConfig(this);
                                }
                                if (!ListenerUtil.mutListener.listen(5926)) {
                                    finish();
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(5957)) {
                    checkDismissNotification();
                }
                if (!ListenerUtil.mutListener.listen(5958)) {
                    checkTrackAnalyticsEvent();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5960)) {
            // ensure the deep linking activity is enabled. It may have been disabled elsewhere and failed to get re-enabled
            WPActivityUtils.enableReaderDeeplinks(this);
        }
        if (!ListenerUtil.mutListener.listen(5961)) {
            // monitor whether we're not the default app
            trackDefaultApp();
        }
        if (!ListenerUtil.mutListener.listen(5962)) {
            // We need to register the dispatcher here otherwise it won't trigger if for example Site Picker is present
            mDispatcher.register(this);
        }
        if (!ListenerUtil.mutListener.listen(5963)) {
            EventBus.getDefault().register(this);
        }
        if (!ListenerUtil.mutListener.listen(5980)) {
            if (authTokenToSet != null) {
                // Save Token to the AccountStore. This will trigger a onAuthenticationChanged.
                UpdateTokenPayload payload = new UpdateTokenPayload(authTokenToSet);
                if (!ListenerUtil.mutListener.listen(5979)) {
                    mDispatcher.dispatch(AccountActionBuilder.newUpdateAccessTokenAction(payload));
                }
            } else if ((ListenerUtil.mutListener.listen(5964) ? (getIntent().getBooleanExtra(ARG_SHOW_LOGIN_EPILOGUE, false) || savedInstanceState == null) : (getIntent().getBooleanExtra(ARG_SHOW_LOGIN_EPILOGUE, false) && savedInstanceState == null))) {
                if (!ListenerUtil.mutListener.listen(5977)) {
                    canShowAppRatingPrompt = false;
                }
                if (!ListenerUtil.mutListener.listen(5978)) {
                    ActivityLauncher.showLoginEpilogue(this, getIntent().getBooleanExtra(ARG_DO_LOGIN_UPDATE, false), getIntent().getIntegerArrayListExtra(ARG_OLD_SITES_IDS), mBuildConfigWrapper.isSiteCreationEnabled());
                }
            } else if ((ListenerUtil.mutListener.listen(5965) ? (getIntent().getBooleanExtra(ARG_SHOW_SIGNUP_EPILOGUE, false) || savedInstanceState == null) : (getIntent().getBooleanExtra(ARG_SHOW_SIGNUP_EPILOGUE, false) && savedInstanceState == null))) {
                if (!ListenerUtil.mutListener.listen(5975)) {
                    canShowAppRatingPrompt = false;
                }
                if (!ListenerUtil.mutListener.listen(5976)) {
                    ActivityLauncher.showSignupEpilogue(this, getIntent().getStringExtra(SignupEpilogueActivity.EXTRA_SIGNUP_DISPLAY_NAME), getIntent().getStringExtra(SignupEpilogueActivity.EXTRA_SIGNUP_EMAIL_ADDRESS), getIntent().getStringExtra(SignupEpilogueActivity.EXTRA_SIGNUP_PHOTO_URL), getIntent().getStringExtra(SignupEpilogueActivity.EXTRA_SIGNUP_USERNAME), false);
                }
            } else if ((ListenerUtil.mutListener.listen(5966) ? (getIntent().getBooleanExtra(ARG_SHOW_SITE_CREATION, false) || savedInstanceState == null) : (getIntent().getBooleanExtra(ARG_SHOW_SITE_CREATION, false) && savedInstanceState == null))) {
                if (!ListenerUtil.mutListener.listen(5973)) {
                    canShowAppRatingPrompt = false;
                }
                if (!ListenerUtil.mutListener.listen(5974)) {
                    ActivityLauncher.newBlogForResult(this, SiteCreationSource.fromString(getIntent().getStringExtra(ARG_SITE_CREATION_SOURCE)));
                }
            } else if ((ListenerUtil.mutListener.listen(5967) ? (getIntent().getBooleanExtra(ARG_WP_COM_SIGN_UP, false) || savedInstanceState == null) : (getIntent().getBooleanExtra(ARG_WP_COM_SIGN_UP, false) && savedInstanceState == null))) {
                if (!ListenerUtil.mutListener.listen(5971)) {
                    canShowAppRatingPrompt = false;
                }
                if (!ListenerUtil.mutListener.listen(5972)) {
                    ActivityLauncher.showSignInForResultWpComOnly(this);
                }
            } else if ((ListenerUtil.mutListener.listen(5968) ? (getIntent().getBooleanExtra(ARG_BLOGGING_PROMPTS_ONBOARDING, false) || savedInstanceState == null) : (getIntent().getBooleanExtra(ARG_BLOGGING_PROMPTS_ONBOARDING, false) && savedInstanceState == null))) {
                if (!ListenerUtil.mutListener.listen(5969)) {
                    canShowAppRatingPrompt = false;
                }
                if (!ListenerUtil.mutListener.listen(5970)) {
                    showBloggingPromptsOnboarding();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5982)) {
            if (isGooglePlayServicesAvailable(this)) {
                if (!ListenerUtil.mutListener.listen(5981)) {
                    // Register for Cloud messaging
                    GCMRegistrationIntentService.enqueueWork(this, new Intent(this, GCMRegistrationIntentService.class));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5984)) {
            if (canShowAppRatingPrompt) {
                if (!ListenerUtil.mutListener.listen(5983)) {
                    AppRatingDialog.INSTANCE.showRateDialogIfNeeded(getFragmentManager());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5985)) {
            scheduleLocalNotifications();
        }
        if (!ListenerUtil.mutListener.listen(5986)) {
            initViewModel();
        }
        if (!ListenerUtil.mutListener.listen(5988)) {
            if (getIntent().getBooleanExtra(ARG_OPEN_BLOGGING_REMINDERS, false)) {
                if (!ListenerUtil.mutListener.listen(5987)) {
                    onSetPromptReminderClick(getIntent().getIntExtra(ARG_OPEN_BLOGGING_REMINDERS, 0));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5990)) {
            if (!mSelectedSiteRepository.hasSelectedSite()) {
                if (!ListenerUtil.mutListener.listen(5989)) {
                    initSelectedSite();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5995)) {
            if ((ListenerUtil.mutListener.listen(5993) ? ((ListenerUtil.mutListener.listen(5992) ? ((ListenerUtil.mutListener.listen(5991) ? (BuildConfig.IS_JETPACK_APP || mStatsRevampV2FeatureConfig.isEnabled()) : (BuildConfig.IS_JETPACK_APP && mStatsRevampV2FeatureConfig.isEnabled())) || AppPrefs.shouldDisplayStatsRevampFeatureAnnouncement()) : ((ListenerUtil.mutListener.listen(5991) ? (BuildConfig.IS_JETPACK_APP || mStatsRevampV2FeatureConfig.isEnabled()) : (BuildConfig.IS_JETPACK_APP && mStatsRevampV2FeatureConfig.isEnabled())) && AppPrefs.shouldDisplayStatsRevampFeatureAnnouncement())) || getSelectedSite() != null) : ((ListenerUtil.mutListener.listen(5992) ? ((ListenerUtil.mutListener.listen(5991) ? (BuildConfig.IS_JETPACK_APP || mStatsRevampV2FeatureConfig.isEnabled()) : (BuildConfig.IS_JETPACK_APP && mStatsRevampV2FeatureConfig.isEnabled())) || AppPrefs.shouldDisplayStatsRevampFeatureAnnouncement()) : ((ListenerUtil.mutListener.listen(5991) ? (BuildConfig.IS_JETPACK_APP || mStatsRevampV2FeatureConfig.isEnabled()) : (BuildConfig.IS_JETPACK_APP && mStatsRevampV2FeatureConfig.isEnabled())) && AppPrefs.shouldDisplayStatsRevampFeatureAnnouncement())) && getSelectedSite() != null))) {
                if (!ListenerUtil.mutListener.listen(5994)) {
                    StatsNewFeaturesIntroDialogFragment.newInstance().show(getSupportFragmentManager(), StatsNewFeaturesIntroDialogFragment.TAG);
                }
            }
        }
    }

    private void showBloggingPromptsOnboarding() {
        if (!ListenerUtil.mutListener.listen(5996)) {
            BloggingPromptsOnboardingDialogFragment.newInstance(DialogType.ONBOARDING).show(getSupportFragmentManager(), BloggingPromptsOnboardingDialogFragment.TAG);
        }
    }

    private void checkDismissNotification() {
        final Intent intent = getIntent();
        if (!ListenerUtil.mutListener.listen(5999)) {
            if ((ListenerUtil.mutListener.listen(5997) ? (intent != null || intent.hasExtra(ARG_DISMISS_NOTIFICATION)) : (intent != null && intent.hasExtra(ARG_DISMISS_NOTIFICATION)))) {
                final NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
                final int notificationId = intent.getIntExtra(ARG_DISMISS_NOTIFICATION, -1);
                if (!ListenerUtil.mutListener.listen(5998)) {
                    notificationManager.cancel(notificationId);
                }
            }
        }
    }

    private void checkTrackAnalyticsEvent() {
        final Intent intent = getIntent();
        if (!ListenerUtil.mutListener.listen(6003)) {
            if ((ListenerUtil.mutListener.listen(6000) ? (intent != null || intent.hasExtra(ARG_STAT_TO_TRACK)) : (intent != null && intent.hasExtra(ARG_STAT_TO_TRACK)))) {
                final Stat stat = (Stat) intent.getSerializableExtra(ARG_STAT_TO_TRACK);
                if (!ListenerUtil.mutListener.listen(6002)) {
                    if (stat != null) {
                        if (!ListenerUtil.mutListener.listen(6001)) {
                            mAnalyticsTrackerWrapper.track(stat);
                        }
                    }
                }
            }
        }
    }

    private void showSignInForResultBasedOnIsJetpackAppBuildConfig(Activity activity) {
        if (!ListenerUtil.mutListener.listen(6006)) {
            if (BuildConfig.IS_JETPACK_APP) {
                if (!ListenerUtil.mutListener.listen(6005)) {
                    ActivityLauncher.showSignInForResultJetpackOnly(activity);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(6004)) {
                    ActivityLauncher.showSignInForResult(activity);
                }
            }
        }
    }

    private boolean isGooglePlayServicesAvailable(Activity activity) {
        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        int connectionResult = googleApiAvailability.isGooglePlayServicesAvailable(activity);
        if (!ListenerUtil.mutListener.listen(6010)) {
            switch(connectionResult) {
                // Success: return true
                case ConnectionResult.SUCCESS:
                    return true;
                // Play Services unavailable, show an error dialog is the Play Services Lib needs an update
                case ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED:
                    Dialog dialog = googleApiAvailability.getErrorDialog(activity, connectionResult, 0);
                    if (!ListenerUtil.mutListener.listen(6008)) {
                        if (dialog != null) {
                            if (!ListenerUtil.mutListener.listen(6007)) {
                                dialog.show();
                            }
                        }
                    }
                // fall through
                default:
                case ConnectionResult.SERVICE_MISSING:
                case ConnectionResult.SERVICE_DISABLED:
                case ConnectionResult.SERVICE_INVALID:
                    if (!ListenerUtil.mutListener.listen(6009)) {
                        AppLog.w(T.NOTIFS, "Google Play Services unavailable, connection result: " + googleApiAvailability.getErrorString(connectionResult));
                    }
            }
        }
        return false;
    }

    private void scheduleLocalNotifications() {
        if (!ListenerUtil.mutListener.listen(6011)) {
            mCreateSiteNotificationScheduler.scheduleCreateSiteNotificationIfNeeded();
        }
        if (!ListenerUtil.mutListener.listen(6012)) {
            mWeeklyRoundupScheduler.schedule();
        }
    }

    private void initViewModel() {
        if (!ListenerUtil.mutListener.listen(6013)) {
            mFloatingActionButton = findViewById(R.id.fab_button);
        }
        if (!ListenerUtil.mutListener.listen(6014)) {
            mFabTooltip = findViewById(R.id.fab_tooltip);
        }
        if (!ListenerUtil.mutListener.listen(6015)) {
            mViewModel = new ViewModelProvider(this, mViewModelFactory).get(WPMainActivityViewModel.class);
        }
        if (!ListenerUtil.mutListener.listen(6016)) {
            mMLPViewModel = new ViewModelProvider(this, mViewModelFactory).get(ModalLayoutPickerViewModel.class);
        }
        if (!ListenerUtil.mutListener.listen(6017)) {
            mBloggingRemindersViewModel = new ViewModelProvider(this, mViewModelFactory).get(BloggingRemindersViewModel.class);
        }
        if (!ListenerUtil.mutListener.listen(6018)) {
            // Setup Observers
            mViewModel.getFabUiState().observe(this, fabUiState -> {
                String message = getResources().getString(fabUiState.getCreateContentMessageId());
                mFabTooltip.setMessage(message);
                if (fabUiState.isFabTooltipVisible()) {
                    mFabTooltip.show();
                } else {
                    mFabTooltip.hide();
                }
                mFloatingActionButton.setContentDescription(message);
                if (fabUiState.isFabVisible()) {
                    mFloatingActionButton.show();
                } else {
                    mFloatingActionButton.hide();
                }
                if (fabUiState.isFocusPointVisible()) {
                    mHandler.postDelayed(mShowFabFocusPoint, 200);
                } else if (!fabUiState.isFocusPointVisible()) {
                    mHandler.removeCallbacks(mShowFabFocusPoint);
                    mHandler.post(() -> addOrRemoveQuickStartFocusPoint(QuickStartNewSiteTask.PUBLISH_POST, false));
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(6019)) {
            mViewModel.getCreateAction().observe(this, createAction -> {
                switch(createAction) {
                    case CREATE_NEW_POST:
                        handleNewPostAction(PagePostCreationSourcesDetail.POST_FROM_MY_SITE, -1, null);
                        break;
                    case CREATE_NEW_PAGE:
                        if (mMLPViewModel.canShowModalLayoutPicker()) {
                            mMLPViewModel.createPageFlowTriggered();
                        } else {
                            handleNewPageAction("", "", null, PagePostCreationSourcesDetail.PAGE_FROM_MY_SITE);
                        }
                        break;
                    case CREATE_NEW_STORY:
                        handleNewStoryAction();
                        break;
                    case ANSWER_BLOGGING_PROMPT:
                    case NO_ACTION:
                        // noop - we handle ANSWER_BLOGGING_PROMPT through live data event
                        break;
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(6020)) {
            mViewModel.getOnFocusPointVisibilityChange().observe(this, event -> event.applyIfNotHandled(focusPointInfos -> {
                {
                    long _loopCounter142 = 0;
                    for (FocusPointInfo focusPointInfo : focusPointInfos) {
                        ListenerUtil.loopListener.listen("_loopCounter142", ++_loopCounter142);
                        addOrRemoveQuickStartFocusPoint(focusPointInfo.getTask(), focusPointInfo.isVisible());
                    }
                }
                return null;
            }));
        }
        if (!ListenerUtil.mutListener.listen(6021)) {
            mMLPViewModel.getOnCreateNewPageRequested().observe(this, request -> {
                handleNewPageAction(request.getTitle(), "", request.getTemplate(), PagePostCreationSourcesDetail.PAGE_FROM_MY_SITE);
            });
        }
        if (!ListenerUtil.mutListener.listen(6022)) {
            mViewModel.getOnFeatureAnnouncementRequested().observe(this, action -> {
                new FeatureAnnouncementDialogFragment().show(getSupportFragmentManager(), FeatureAnnouncementDialogFragment.TAG);
            });
        }
        if (!ListenerUtil.mutListener.listen(6023)) {
            mFloatingActionButton.setOnClickListener(v -> mViewModel.onFabClicked(getSelectedSite()));
        }
        if (!ListenerUtil.mutListener.listen(6024)) {
            mFloatingActionButton.setOnLongClickListener(v -> {
                if (v.isHapticFeedbackEnabled()) {
                    v.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);
                }
                mViewModel.onFabLongPressed(getSelectedSite());
                int messageId = mViewModel.getCreateContentMessageId(getSelectedSite());
                Toast.makeText(v.getContext(), messageId, Toast.LENGTH_SHORT).show();
                return true;
            });
        }
        if (!ListenerUtil.mutListener.listen(6025)) {
            ViewExtensionsKt.redirectContextClickToLongPressListener(mFloatingActionButton);
        }
        if (!ListenerUtil.mutListener.listen(6026)) {
            mFabTooltip.setOnClickListener(v -> {
                mViewModel.onTooltipTapped(getSelectedSite());
            });
        }
        if (!ListenerUtil.mutListener.listen(6027)) {
            mViewModel.isBottomSheetShowing().observe(this, event -> {
                event.applyIfNotHandled(isShowing -> {
                    FragmentManager fm = getSupportFragmentManager();
                    MainBottomSheetFragment bottomSheet = (MainBottomSheetFragment) fm.findFragmentByTag(MAIN_BOTTOM_SHEET_TAG);
                    if (isShowing && bottomSheet == null) {
                        bottomSheet = new MainBottomSheetFragment();
                        bottomSheet.show(getSupportFragmentManager(), MAIN_BOTTOM_SHEET_TAG);
                    } else if (!isShowing && bottomSheet != null) {
                        bottomSheet.dismiss();
                    }
                    return null;
                });
            });
        }
        if (!ListenerUtil.mutListener.listen(6028)) {
            BloggingReminderUtils.observeBottomSheet(mBloggingRemindersViewModel.isBottomSheetShowing(), this, BLOGGING_REMINDERS_BOTTOM_SHEET_TAG, this::getSupportFragmentManager);
        }
        if (!ListenerUtil.mutListener.listen(6029)) {
            mMLPViewModel.isModalLayoutPickerShowing().observe(this, event -> {
                event.applyIfNotHandled(isShowing -> {
                    FragmentManager fm = getSupportFragmentManager();
                    ModalLayoutPickerFragment mlpFragment = (ModalLayoutPickerFragment) fm.findFragmentByTag(ModalLayoutPickerFragment.MODAL_LAYOUT_PICKER_TAG);
                    if (isShowing && mlpFragment == null) {
                        mlpFragment = new ModalLayoutPickerFragment();
                        mlpFragment.show(getSupportFragmentManager(), ModalLayoutPickerFragment.MODAL_LAYOUT_PICKER_TAG);
                    } else if (!isShowing && mlpFragment != null) {
                        mlpFragment.dismiss();
                    }
                    return null;
                });
            });
        }
        if (!ListenerUtil.mutListener.listen(6030)) {
            mViewModel.getStartLoginFlow().observe(this, event -> {
                event.applyIfNotHandled(unit -> {
                    ActivityLauncher.viewMeActivityForResult(this);
                    return null;
                });
            });
        }
        if (!ListenerUtil.mutListener.listen(6031)) {
            mViewModel.getSwitchToMySite().observe(this, event -> {
                event.applyIfNotHandled(unit -> {
                    if (mBottomNav != null) {
                        mBottomNav.setCurrentSelectedPage(PageType.MY_SITE);
                    }
                    return null;
                });
            });
        }
        if (!ListenerUtil.mutListener.listen(6032)) {
            mViewModel.getCreatePostWithBloggingPrompt().observe(this, promptId -> {
                handleNewPostAction(PagePostCreationSourcesDetail.POST_FROM_MY_SITE, promptId, EntryPoint.ADD_NEW_SHEET_ANSWER_PROMPT);
            });
        }
        if (!ListenerUtil.mutListener.listen(6033)) {
            mViewModel.getOpenBloggingPromptsOnboarding().observe(this, action -> {
                showBloggingPromptsOnboarding();
            });
        }
        if (!ListenerUtil.mutListener.listen(6034)) {
            // It also means that the ViewModel must accept a nullable SiteModel.
            mViewModel.start(getSelectedSite());
        }
    }

    @Nullable
    private String getAuthToken() {
        Uri uri = getIntent().getData();
        return uri != null ? uri.getQueryParameter(LoginActivity.TOKEN_PARAMETER) : null;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        if (!ListenerUtil.mutListener.listen(6035)) {
            super.onNewIntent(intent);
        }
        if (!ListenerUtil.mutListener.listen(6036)) {
            setIntent(intent);
        }
        if (!ListenerUtil.mutListener.listen(6037)) {
            AppLog.i(T.MAIN, "main activity > new intent");
        }
        if (!ListenerUtil.mutListener.listen(6039)) {
            if (intent.hasExtra(NotificationsListFragment.NOTE_ID_EXTRA)) {
                if (!ListenerUtil.mutListener.listen(6038)) {
                    launchWithNoteId();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6041)) {
            if (intent.hasExtra(ARG_OPEN_PAGE)) {
                if (!ListenerUtil.mutListener.listen(6040)) {
                    handleOpenPageIntent(intent);
                }
            }
        }
    }

    private void handleOpenPageIntent(Intent intent) {
        String pagePosition = intent.getStringExtra(ARG_OPEN_PAGE);
        if (!ListenerUtil.mutListener.listen(6063)) {
            if (!TextUtils.isEmpty(pagePosition)) {
                if (!ListenerUtil.mutListener.listen(6062)) {
                    switch(pagePosition) {
                        case ARG_MY_SITE:
                            if (!ListenerUtil.mutListener.listen(6043)) {
                                mBottomNav.setCurrentSelectedPage(PageType.MY_SITE);
                            }
                            break;
                        case ARG_NOTIFICATIONS:
                            if (!ListenerUtil.mutListener.listen(6044)) {
                                mBottomNav.setCurrentSelectedPage(PageType.NOTIFS);
                            }
                            break;
                        case ARG_READER:
                            if (!ListenerUtil.mutListener.listen(6048)) {
                                if ((ListenerUtil.mutListener.listen(6045) ? (intent.getBooleanExtra(ARG_READER_BOOKMARK_TAB, false) || mBottomNav.getActiveFragment() instanceof ReaderFragment) : (intent.getBooleanExtra(ARG_READER_BOOKMARK_TAB, false) && mBottomNav.getActiveFragment() instanceof ReaderFragment))) {
                                    if (!ListenerUtil.mutListener.listen(6047)) {
                                        ((ReaderFragment) mBottomNav.getActiveFragment()).requestBookmarkTab();
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(6046)) {
                                        mBottomNav.setCurrentSelectedPage(PageType.READER);
                                    }
                                }
                            }
                            break;
                        case ARG_EDITOR:
                            if (!ListenerUtil.mutListener.listen(6050)) {
                                if (!mSelectedSiteRepository.hasSelectedSite()) {
                                    if (!ListenerUtil.mutListener.listen(6049)) {
                                        initSelectedSite();
                                    }
                                }
                            }
                            final int promptId = intent.getIntExtra(ARG_EDITOR_PROMPT_ID, -1);
                            final EntryPoint entryPoint = (EntryPoint) intent.getSerializableExtra(ARG_EDITOR_ORIGIN);
                            if (!ListenerUtil.mutListener.listen(6051)) {
                                onNewPostButtonClicked(promptId, entryPoint);
                            }
                            break;
                        case ARG_STATS:
                            if (!ListenerUtil.mutListener.listen(6053)) {
                                if (!mSelectedSiteRepository.hasSelectedSite()) {
                                    if (!ListenerUtil.mutListener.listen(6052)) {
                                        initSelectedSite();
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(6056)) {
                                if (intent.hasExtra(ARG_STATS_TIMEFRAME)) {
                                    if (!ListenerUtil.mutListener.listen(6055)) {
                                        ActivityLauncher.viewBlogStatsForTimeframe(this, getSelectedSite(), (StatsTimeframe) intent.getSerializableExtra(ARG_STATS_TIMEFRAME));
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(6054)) {
                                        ActivityLauncher.viewBlogStats(this, getSelectedSite());
                                    }
                                }
                            }
                            break;
                        case ARG_PAGES:
                            if (!ListenerUtil.mutListener.listen(6058)) {
                                if (!mSelectedSiteRepository.hasSelectedSite()) {
                                    if (!ListenerUtil.mutListener.listen(6057)) {
                                        initSelectedSite();
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(6059)) {
                                ActivityLauncher.viewCurrentBlogPages(this, getSelectedSite());
                            }
                            break;
                        case ARG_WP_COM_SIGN_UP:
                            if (!ListenerUtil.mutListener.listen(6060)) {
                                ActivityLauncher.showSignInForResultWpComOnly(this);
                            }
                            break;
                        case ARG_BLOGGING_PROMPTS_ONBOARDING:
                            if (!ListenerUtil.mutListener.listen(6061)) {
                                showBloggingPromptsOnboarding();
                            }
                            break;
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(6042)) {
                    AppLog.e(T.MAIN, "WPMainActivity.handleOpenIntent called with an invalid argument.");
                }
            }
        }
    }

    private void launchZendeskMyTickets() {
        if (!ListenerUtil.mutListener.listen(6064)) {
            if (isFinishing()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(6065)) {
            // Help&Support > HelpActivity the app is in the right section.
            mBottomNav.setCurrentSelectedPage(PageType.MY_SITE);
        }
        if (!ListenerUtil.mutListener.listen(6066)) {
            // init selected site, this is the same as in onResume
            initSelectedSite();
        }
        if (!ListenerUtil.mutListener.listen(6067)) {
            ActivityLauncher.viewZendeskTickets(this, getSelectedSite());
        }
    }

    /*
     * called when app is launched from a push notification, switches to the notification page
     * and opens the desired note detail
     */
    private void launchWithNoteId() {
        if (!ListenerUtil.mutListener.listen(6069)) {
            if ((ListenerUtil.mutListener.listen(6068) ? (isFinishing() && getIntent() == null) : (isFinishing() || getIntent() == null))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(6077)) {
            if (getIntent().hasExtra(NotificationsUtils.ARG_PUSH_AUTH_TOKEN)) {
                if (!ListenerUtil.mutListener.listen(6070)) {
                    mGCMMessageHandler.remove2FANotification(this);
                }
                if (!ListenerUtil.mutListener.listen(6076)) {
                    NotificationsUtils.validate2FAuthorizationTokenFromIntentExtras(getIntent(), new NotificationsUtils.TwoFactorAuthCallback() {

                        @Override
                        public void onTokenValid(String token, String title, String message) {
                            // for security).
                            String actionType = getIntent().getStringExtra(NotificationsProcessingService.ARG_ACTION_TYPE);
                            if (!ListenerUtil.mutListener.listen(6073)) {
                                if (NotificationsProcessingService.ARG_ACTION_AUTH_APPROVE.equals(actionType)) {
                                    if (!ListenerUtil.mutListener.listen(6072)) {
                                        // ping the push auth endpoint with the token, wp.com will take care of the rest!
                                        NotificationsUtils.sendTwoFactorAuthToken(token);
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(6071)) {
                                        NotificationsUtils.showPushAuthAlert(WPMainActivity.this, token, title, message);
                                    }
                                }
                            }
                        }

                        @Override
                        public void onTokenInvalid() {
                            if (!ListenerUtil.mutListener.listen(6074)) {
                                // Show a toast if the user took too long to open the notification
                                ToastUtils.showToast(WPMainActivity.this, R.string.push_auth_expired, ToastUtils.Duration.LONG);
                            }
                            if (!ListenerUtil.mutListener.listen(6075)) {
                                AnalyticsTracker.track(AnalyticsTracker.Stat.PUSH_AUTHENTICATION_EXPIRED);
                            }
                        }
                    });
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6078)) {
            // Then hit the server
            NotificationsActions.updateNotesSeenTimestamp();
        }
        if (!ListenerUtil.mutListener.listen(6079)) {
            mBottomNav.setCurrentSelectedPage(PageType.NOTIFS);
        }
        if (!ListenerUtil.mutListener.listen(6096)) {
            // here. It's ok to compare to <=1 as it could be zero then.
            if ((ListenerUtil.mutListener.listen(6084) ? (mGCMMessageHandler.getNotificationsCount() >= 1) : (ListenerUtil.mutListener.listen(6083) ? (mGCMMessageHandler.getNotificationsCount() > 1) : (ListenerUtil.mutListener.listen(6082) ? (mGCMMessageHandler.getNotificationsCount() < 1) : (ListenerUtil.mutListener.listen(6081) ? (mGCMMessageHandler.getNotificationsCount() != 1) : (ListenerUtil.mutListener.listen(6080) ? (mGCMMessageHandler.getNotificationsCount() == 1) : (mGCMMessageHandler.getNotificationsCount() <= 1))))))) {
                String noteId = getIntent().getStringExtra(NotificationsListFragment.NOTE_ID_EXTRA);
                if (!ListenerUtil.mutListener.listen(6095)) {
                    if (!TextUtils.isEmpty(noteId)) {
                        if (!ListenerUtil.mutListener.listen(6087)) {
                            mGCMMessageHandler.bumpPushNotificationsTappedAnalytics(noteId);
                        }
                        // extra EXTRA_VOICE_OR_INLINE_REPLY
                        String voiceReply = null;
                        Bundle remoteInput = RemoteInput.getResultsFromIntent(getIntent());
                        if (!ListenerUtil.mutListener.listen(6090)) {
                            if (remoteInput != null) {
                                CharSequence replyText = remoteInput.getCharSequence(GCMMessageService.EXTRA_VOICE_OR_INLINE_REPLY);
                                if (!ListenerUtil.mutListener.listen(6089)) {
                                    if (replyText != null) {
                                        if (!ListenerUtil.mutListener.listen(6088)) {
                                            voiceReply = replyText.toString();
                                        }
                                    }
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(6094)) {
                            if (voiceReply != null) {
                                if (!ListenerUtil.mutListener.listen(6092)) {
                                    NotificationsProcessingService.startServiceForReply(this, noteId, voiceReply);
                                }
                                if (!ListenerUtil.mutListener.listen(6093)) {
                                    finish();
                                }
                                // we processed the voice reply, so we exit this function immediately
                                return;
                            } else {
                                boolean shouldShowKeyboard = getIntent().getBooleanExtra(NotificationsListFragment.NOTE_INSTANT_REPLY_EXTRA, false);
                                if (!ListenerUtil.mutListener.listen(6091)) {
                                    NotificationsListFragment.openNoteForReply(this, noteId, shouldShowKeyboard, null, NotesAdapter.FILTERS.FILTER_ALL, true);
                                }
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(6086)) {
                            AppLog.e(T.NOTIFS, "app launched from a PN that doesn't have a note_id in it!!");
                        }
                        return;
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(6085)) {
                    // mark all tapped here
                    mGCMMessageHandler.bumpPushNotificationsTappedAllAnalytics();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6097)) {
            mGCMMessageHandler.removeAllNotifications(this);
        }
    }

    /**
     * called from an internal pending draft notification, so the user can land in the local draft and take action
     * such as finish editing and publish, or delete the post, etc.
     */
    private void launchWithPostId(int postId, boolean isPage) {
        if (!ListenerUtil.mutListener.listen(6099)) {
            if ((ListenerUtil.mutListener.listen(6098) ? (isFinishing() && getIntent() == null) : (isFinishing() || getIntent() == null))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(6100)) {
            AnalyticsTracker.track(AnalyticsTracker.Stat.NOTIFICATION_PENDING_DRAFTS_TAPPED);
        }
        if (!ListenerUtil.mutListener.listen(6101)) {
            NativeNotificationsUtils.dismissNotification(PendingDraftsNotificationsUtils.makePendingDraftNotificationId(postId), this);
        }
        // if no specific post id passed, show the list
        SiteModel selectedSite = getSelectedSite();
        if (!ListenerUtil.mutListener.listen(6103)) {
            if (selectedSite == null) {
                if (!ListenerUtil.mutListener.listen(6102)) {
                    ToastUtils.showToast(this, R.string.site_cannot_be_loaded);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(6113)) {
            if ((ListenerUtil.mutListener.listen(6108) ? (postId >= 0) : (ListenerUtil.mutListener.listen(6107) ? (postId <= 0) : (ListenerUtil.mutListener.listen(6106) ? (postId > 0) : (ListenerUtil.mutListener.listen(6105) ? (postId < 0) : (ListenerUtil.mutListener.listen(6104) ? (postId != 0) : (postId == 0))))))) {
                if (!ListenerUtil.mutListener.listen(6112)) {
                    // show list
                    if (isPage) {
                        if (!ListenerUtil.mutListener.listen(6111)) {
                            ActivityLauncher.viewCurrentBlogPages(this, selectedSite);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(6110)) {
                            ActivityLauncher.viewCurrentBlogPosts(this, selectedSite);
                        }
                    }
                }
            } else {
                PostModel post = mPostStore.getPostByLocalPostId(postId);
                if (!ListenerUtil.mutListener.listen(6109)) {
                    ActivityLauncher.editPostOrPageForResult(this, selectedSite, post);
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        if (!ListenerUtil.mutListener.listen(6114)) {
            EventBus.getDefault().unregister(this);
        }
        if (!ListenerUtil.mutListener.listen(6115)) {
            mDispatcher.unregister(this);
        }
        if (!ListenerUtil.mutListener.listen(6116)) {
            mHandler.removeCallbacksAndMessages(null);
        }
        if (!ListenerUtil.mutListener.listen(6117)) {
            super.onDestroy();
        }
    }

    @Override
    protected void onResume() {
        if (!ListenerUtil.mutListener.listen(6118)) {
            super.onResume();
        }
        if (!ListenerUtil.mutListener.listen(6119)) {
            // Load selected site
            initSelectedSite();
        }
        if (!ListenerUtil.mutListener.listen(6120)) {
            // viewing of a post
            WPActivityUtils.enableReaderDeeplinks(this);
        }
        // Ex: Notifications -> notifications detail -> back to notifications
        PageType currentPageType = mBottomNav.getCurrentSelectedPage();
        if (!ListenerUtil.mutListener.listen(6121)) {
            trackLastVisiblePage(currentPageType, mFirstResume);
        }
        if (!ListenerUtil.mutListener.listen(6123)) {
            if (currentPageType == PageType.NOTIFS) {
                if (!ListenerUtil.mutListener.listen(6122)) {
                    // notifications
                    mGCMMessageHandler.removeAllNotifications(this);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6124)) {
            announceTitleForAccessibility(currentPageType);
        }
        if (!ListenerUtil.mutListener.listen(6125)) {
            checkConnection();
        }
        if (!ListenerUtil.mutListener.listen(6126)) {
            checkQuickStartNotificationStatus();
        }
        if (!ListenerUtil.mutListener.listen(6128)) {
            // Update account to update the notification unseen status
            if (mAccountStore.hasAccessToken()) {
                if (!ListenerUtil.mutListener.listen(6127)) {
                    mDispatcher.dispatch(AccountActionBuilder.newFetchAccountAction());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6129)) {
            ProfilingUtils.split("WPMainActivity.onResume");
        }
        if (!ListenerUtil.mutListener.listen(6130)) {
            ProfilingUtils.dump();
        }
        if (!ListenerUtil.mutListener.listen(6131)) {
            ProfilingUtils.stop();
        }
        if (!ListenerUtil.mutListener.listen(6133)) {
            mViewModel.onResume(getSelectedSite(), (ListenerUtil.mutListener.listen(6132) ? (mSelectedSiteRepository.hasSelectedSite() || mBottomNav.getCurrentSelectedPage() == PageType.MY_SITE) : (mSelectedSiteRepository.hasSelectedSite() && mBottomNav.getCurrentSelectedPage() == PageType.MY_SITE)));
        }
        if (!ListenerUtil.mutListener.listen(6134)) {
            mFirstResume = false;
        }
    }

    private void checkQuickStartNotificationStatus() {
        SiteModel selectedSite = getSelectedSite();
        long selectedSiteLocalId = mSelectedSiteRepository.getSelectedSiteLocalId();
        if (!ListenerUtil.mutListener.listen(6141)) {
            if ((ListenerUtil.mutListener.listen(6137) ? ((ListenerUtil.mutListener.listen(6136) ? ((ListenerUtil.mutListener.listen(6135) ? (selectedSite != null || NetworkUtils.isNetworkAvailable(this)) : (selectedSite != null && NetworkUtils.isNetworkAvailable(this))) || mQuickStartRepository.getQuickStartType().isEveryQuickStartTaskDone(mQuickStartStore, selectedSiteLocalId)) : ((ListenerUtil.mutListener.listen(6135) ? (selectedSite != null || NetworkUtils.isNetworkAvailable(this)) : (selectedSite != null && NetworkUtils.isNetworkAvailable(this))) && mQuickStartRepository.getQuickStartType().isEveryQuickStartTaskDone(mQuickStartStore, selectedSiteLocalId))) || !mQuickStartStore.getQuickStartNotificationReceived(selectedSite.getId())) : ((ListenerUtil.mutListener.listen(6136) ? ((ListenerUtil.mutListener.listen(6135) ? (selectedSite != null || NetworkUtils.isNetworkAvailable(this)) : (selectedSite != null && NetworkUtils.isNetworkAvailable(this))) || mQuickStartRepository.getQuickStartType().isEveryQuickStartTaskDone(mQuickStartStore, selectedSiteLocalId)) : ((ListenerUtil.mutListener.listen(6135) ? (selectedSite != null || NetworkUtils.isNetworkAvailable(this)) : (selectedSite != null && NetworkUtils.isNetworkAvailable(this))) && mQuickStartRepository.getQuickStartType().isEveryQuickStartTaskDone(mQuickStartStore, selectedSiteLocalId))) && !mQuickStartStore.getQuickStartNotificationReceived(selectedSite.getId())))) {
                boolean isQuickStartCompleted = mQuickStartStore.getQuickStartCompleted(selectedSiteLocalId);
                if (!ListenerUtil.mutListener.listen(6139)) {
                    if (!isQuickStartCompleted) {
                        if (!ListenerUtil.mutListener.listen(6138)) {
                            mQuickStartStore.setQuickStartCompleted(selectedSiteLocalId, true);
                        }
                    }
                }
                CompleteQuickStartPayload payload = new CompleteQuickStartPayload(selectedSite, NEXT_STEPS.toString());
                if (!ListenerUtil.mutListener.listen(6140)) {
                    mDispatcher.dispatch(SiteActionBuilder.newCompleteQuickStartAction(payload));
                }
            }
        }
    }

    private void announceTitleForAccessibility(PageType pageType) {
        if (!ListenerUtil.mutListener.listen(6142)) {
            getWindow().getDecorView().announceForAccessibility(mBottomNav.getContentDescriptionForPageType(pageType));
        }
    }

    @Override
    public void onBackPressed() {
        // let the fragment handle the back button if it implements our OnParentBackPressedListener
        Fragment fragment = mBottomNav.getActiveFragment();
        if (!ListenerUtil.mutListener.listen(6144)) {
            if (fragment instanceof OnActivityBackPressedListener) {
                boolean handled = ((OnActivityBackPressedListener) fragment).onActivityBackPressed();
                if (!ListenerUtil.mutListener.listen(6143)) {
                    if (handled) {
                        return;
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6146)) {
            if ((ListenerUtil.mutListener.listen(6145) ? (isTaskRoot() || DeviceUtils.getInstance().isChromebook(this)) : (isTaskRoot() && DeviceUtils.getInstance().isChromebook(this)))) {
                // don't close app in Main Activity
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(6147)) {
            super.onBackPressed();
        }
    }

    @Override
    public void onRequestShowBottomNavigation() {
        if (!ListenerUtil.mutListener.listen(6148)) {
            showBottomNav(true);
        }
    }

    @Override
    public void onRequestHideBottomNavigation() {
        if (!ListenerUtil.mutListener.listen(6149)) {
            showBottomNav(false);
        }
    }

    private void showBottomNav(boolean show) {
        if (!ListenerUtil.mutListener.listen(6150)) {
            mBottomNav.setVisibility(show ? View.VISIBLE : View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(6151)) {
            findViewById(R.id.navbar_separator).setVisibility(show ? View.VISIBLE : View.GONE);
        }
    }

    // user switched pages in the bottom navbar
    @Override
    public void onPageChanged(int position) {
        if (!ListenerUtil.mutListener.listen(6152)) {
            mReaderTracker.onBottomNavigationTabChanged();
        }
        PageType pageType = WPMainNavigationView.getPageType(position);
        if (!ListenerUtil.mutListener.listen(6153)) {
            trackLastVisiblePage(pageType, true);
        }
        if (!ListenerUtil.mutListener.listen(6156)) {
            if (pageType == PageType.READER) {
                if (!ListenerUtil.mutListener.listen(6154)) {
                    // MySite fragment might not be attached to activity, so we need to remove focus point from here
                    QuickStartUtils.removeQuickStartFocusPoint(findViewById(R.id.root_view_main));
                }
                QuickStartTask followSiteTask = mQuickStartRepository.getQuickStartType().getTaskFromString(QuickStartStore.QUICK_START_FOLLOW_SITE_LABEL);
                if (!ListenerUtil.mutListener.listen(6155)) {
                    mQuickStartRepository.requestNextStepOfTask(followSiteTask);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6159)) {
            if (pageType == PageType.NOTIFS) {
                if (!ListenerUtil.mutListener.listen(6157)) {
                    // MySite fragment might not be attached to activity, so we need to remove focus point from here
                    QuickStartUtils.removeQuickStartFocusPoint(findViewById(R.id.root_view_main));
                }
                if (!ListenerUtil.mutListener.listen(6158)) {
                    mQuickStartRepository.completeTask(QuickStartExistingSiteTask.CHECK_NOTIFICATIONS);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6161)) {
            mViewModel.onPageChanged((ListenerUtil.mutListener.listen(6160) ? (mSiteStore.hasSite() || pageType == PageType.MY_SITE) : (mSiteStore.hasSite() && pageType == PageType.MY_SITE)), getSelectedSite());
        }
    }

    // user tapped the new post button in the bottom navbar
    @Override
    public void onNewPostButtonClicked(final int promptId, @NonNull final EntryPoint entryPoint) {
        if (!ListenerUtil.mutListener.listen(6162)) {
            handleNewPostAction(PagePostCreationSourcesDetail.POST_FROM_NAV_BAR, promptId, entryPoint);
        }
    }

    private void handleNewPageAction(String title, String content, String template, PagePostCreationSourcesDetail source) {
        if (!ListenerUtil.mutListener.listen(6164)) {
            if (!mSiteStore.hasSite()) {
                if (!ListenerUtil.mutListener.listen(6163)) {
                    // No site yet - Move to My Sites fragment that shows the create new site screen
                    mBottomNav.setCurrentSelectedPage(PageType.MY_SITE);
                }
                return;
            }
        }
        SiteModel selectedSite = getSelectedSite();
        if (!ListenerUtil.mutListener.listen(6166)) {
            if (selectedSite != null) {
                if (!ListenerUtil.mutListener.listen(6165)) {
                    // TODO: evaluate to include the QuickStart logic like in the handleNewPostAction
                    ActivityLauncher.addNewPageForResult(this, selectedSite, title, content, template, source);
                }
            }
        }
    }

    private void handleNewPostAction(PagePostCreationSourcesDetail source, final int promptId, final EntryPoint entryPoint) {
        if (!ListenerUtil.mutListener.listen(6168)) {
            if (!mSiteStore.hasSite()) {
                if (!ListenerUtil.mutListener.listen(6167)) {
                    // No site yet - Move to My Sites fragment that shows the create new site screen
                    mBottomNav.setCurrentSelectedPage(PageType.MY_SITE);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(6169)) {
            ActivityLauncher.addNewPostForResult(this, getSelectedSite(), false, source, promptId, entryPoint);
        }
    }

    private void handleNewStoryAction() {
        if (!ListenerUtil.mutListener.listen(6171)) {
            if (!mSiteStore.hasSite()) {
                if (!ListenerUtil.mutListener.listen(6170)) {
                    // No site yet - Move to My Sites fragment that shows the create new site screen
                    mBottomNav.setCurrentSelectedPage(PageType.MY_SITE);
                }
                return;
            }
        }
        SiteModel selectedSite = getSelectedSite();
        if (!ListenerUtil.mutListener.listen(6175)) {
            if (selectedSite != null) {
                if (!ListenerUtil.mutListener.listen(6174)) {
                    // TODO: evaluate to include the QuickStart logic like in the handleNewPostAction
                    if (AppPrefs.shouldShowStoriesIntro()) {
                        if (!ListenerUtil.mutListener.listen(6173)) {
                            StoriesIntroDialogFragment.newInstance(selectedSite).show(getSupportFragmentManager(), StoriesIntroDialogFragment.TAG);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(6172)) {
                            mMediaPickerLauncher.showStoriesPhotoPickerForResultAndTrack(this, selectedSite);
                        }
                    }
                }
            }
        }
    }

    private void trackLastVisiblePage(PageType pageType, boolean trackAnalytics) {
        if (!ListenerUtil.mutListener.listen(6185)) {
            switch(pageType) {
                case MY_SITE:
                    if (!ListenerUtil.mutListener.listen(6176)) {
                        ActivityId.trackLastActivity(ActivityId.MY_SITE);
                    }
                    if (!ListenerUtil.mutListener.listen(6178)) {
                        if (trackAnalytics) {
                            if (!ListenerUtil.mutListener.listen(6177)) {
                                // Added today's stats feature config to check if my site tab is accessed more often in AB testing
                                mAnalyticsTrackerWrapper.track(AnalyticsTracker.Stat.MY_SITE_ACCESSED, getSelectedSite(), mTodaysStatsCardFeatureConfig);
                            }
                        }
                    }
                    break;
                case READER:
                    if (!ListenerUtil.mutListener.listen(6179)) {
                        ActivityId.trackLastActivity(ActivityId.READER);
                    }
                    if (!ListenerUtil.mutListener.listen(6181)) {
                        if (trackAnalytics) {
                            if (!ListenerUtil.mutListener.listen(6180)) {
                                AnalyticsTracker.track(AnalyticsTracker.Stat.READER_ACCESSED);
                            }
                        }
                    }
                    break;
                case NOTIFS:
                    if (!ListenerUtil.mutListener.listen(6182)) {
                        ActivityId.trackLastActivity(ActivityId.NOTIFICATIONS);
                    }
                    if (!ListenerUtil.mutListener.listen(6184)) {
                        if (trackAnalytics) {
                            if (!ListenerUtil.mutListener.listen(6183)) {
                                AnalyticsTracker.track(AnalyticsTracker.Stat.NOTIFICATIONS_ACCESSED);
                            }
                        }
                    }
                    break;
                default:
                    break;
            }
        }
    }

    private void trackDefaultApp() {
        Intent wpcomIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.wordpresscom_sample_post)));
        ResolveInfo resolveInfo = getPackageManager().resolveActivity(wpcomIntent, PackageManager.MATCH_DEFAULT_ONLY);
        if (!ListenerUtil.mutListener.listen(6188)) {
            if ((ListenerUtil.mutListener.listen(6186) ? (resolveInfo != null || !getPackageName().equals(resolveInfo.activityInfo.name)) : (resolveInfo != null && !getPackageName().equals(resolveInfo.activityInfo.name)))) {
                if (!ListenerUtil.mutListener.listen(6187)) {
                    // not set as default handler so, track this to evaluate. Note, a resolver/chooser might be the default.
                    AnalyticsUtils.trackWithDefaultInterceptor(AnalyticsTracker.Stat.DEEP_LINK_NOT_DEFAULT_HANDLER, resolveInfo.activityInfo.name);
                }
            }
        }
    }

    public void setReaderPageActive() {
        if (!ListenerUtil.mutListener.listen(6189)) {
            mBottomNav.setCurrentSelectedPage(PageType.READER);
        }
    }

    private void setSite(Intent data) {
        if (!ListenerUtil.mutListener.listen(6192)) {
            if (data != null) {
                int siteLocalId = data.getIntExtra(SitePickerActivity.KEY_SITE_LOCAL_ID, SelectedSiteRepository.UNAVAILABLE);
                SiteModel site = mSiteStore.getSiteByLocalId(siteLocalId);
                if (!ListenerUtil.mutListener.listen(6191)) {
                    if (site != null) {
                        if (!ListenerUtil.mutListener.listen(6190)) {
                            setSelectedSite(site);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!ListenerUtil.mutListener.listen(6193)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
        if (!ListenerUtil.mutListener.listen(6195)) {
            if (!mSelectedSiteRepository.hasSelectedSite()) {
                if (!ListenerUtil.mutListener.listen(6194)) {
                    initSelectedSite();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6239)) {
            switch(requestCode) {
                case RequestCodes.EDIT_POST:
                case RequestCodes.EDIT_LANDING_PAGE:
                    if (!ListenerUtil.mutListener.listen(6198)) {
                        if ((ListenerUtil.mutListener.listen(6197) ? ((ListenerUtil.mutListener.listen(6196) ? (resultCode != Activity.RESULT_OK && data == null) : (resultCode != Activity.RESULT_OK || data == null)) && isFinishing()) : ((ListenerUtil.mutListener.listen(6196) ? (resultCode != Activity.RESULT_OK && data == null) : (resultCode != Activity.RESULT_OK || data == null)) || isFinishing()))) {
                            return;
                        }
                    }
                    int localId = data.getIntExtra(EditPostActivity.EXTRA_POST_LOCAL_ID, 0);
                    final SiteModel site = (SiteModel) data.getSerializableExtra(WordPress.SITE);
                    final PostModel post = mPostStore.getPostByLocalPostId(localId);
                    if (!ListenerUtil.mutListener.listen(6200)) {
                        if (EditPostActivity.checkToRestart(data)) {
                            if (!ListenerUtil.mutListener.listen(6199)) {
                                ActivityLauncher.editPostOrPageForResult(data, WPMainActivity.this, site, data.getIntExtra(EditPostActivity.EXTRA_POST_LOCAL_ID, 0));
                            }
                            // a restart will happen so, no need to continue here
                            break;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(6204)) {
                        if ((ListenerUtil.mutListener.listen(6201) ? (site != null || post != null) : (site != null && post != null))) {
                            if (!ListenerUtil.mutListener.listen(6203)) {
                                mUploadUtilsWrapper.handleEditPostResultSnackbars(this, findViewById(R.id.coordinator), data, post, site, mUploadActionUseCase.getUploadAction(post), new View.OnClickListener() {

                                    @Override
                                    public void onClick(View v) {
                                        if (!ListenerUtil.mutListener.listen(6202)) {
                                            UploadUtils.publishPost(WPMainActivity.this, post, site, mDispatcher);
                                        }
                                    }
                                }, isFirstTimePublishing -> mBloggingRemindersViewModel.onPublishingPost(site.getId(), isFirstTimePublishing));
                            }
                        }
                    }
                    break;
                case RequestCodes.CREATE_STORY:
                    SiteModel selectedSite = getSelectedSite();
                    if (!ListenerUtil.mutListener.listen(6207)) {
                        if (selectedSite != null) {
                            boolean isNewStory = (ListenerUtil.mutListener.listen(6205) ? (data == null && data.getStringExtra(ARG_STORY_BLOCK_ID) == null) : (data == null || data.getStringExtra(ARG_STORY_BLOCK_ID) == null));
                            if (!ListenerUtil.mutListener.listen(6206)) {
                                mBloggingRemindersViewModel.onPublishingPost(selectedSite.getId(), isNewStory);
                            }
                        }
                    }
                    break;
                case RequestCodes.CREATE_SITE:
                    if (!ListenerUtil.mutListener.listen(6208)) {
                        QuickStartUtils.cancelQuickStartReminder(this);
                    }
                    if (!ListenerUtil.mutListener.listen(6209)) {
                        AppPrefs.setQuickStartNoticeRequired(false);
                    }
                    if (!ListenerUtil.mutListener.listen(6210)) {
                        AppPrefs.setLastSkippedQuickStartTask(null);
                    }
                    if (!ListenerUtil.mutListener.listen(6212)) {
                        // Enable the block editor on sites created on mobile
                        if (data != null) {
                            int newSiteLocalID = data.getIntExtra(SitePickerActivity.KEY_SITE_LOCAL_ID, SelectedSiteRepository.UNAVAILABLE);
                            if (!ListenerUtil.mutListener.listen(6211)) {
                                SiteUtils.enableBlockEditorOnSiteCreation(mDispatcher, mSiteStore, newSiteLocalID);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(6213)) {
                        setSite(data);
                    }
                    if (!ListenerUtil.mutListener.listen(6214)) {
                        passOnActivityResultToMySiteFragment(requestCode, resultCode, data);
                    }
                    if (!ListenerUtil.mutListener.listen(6215)) {
                        mPrivateAtomicCookie.clearCookie();
                    }
                    break;
                case RequestCodes.ADD_ACCOUNT:
                    if (!ListenerUtil.mutListener.listen(6218)) {
                        if (resultCode == RESULT_OK) {
                            if (!ListenerUtil.mutListener.listen(6217)) {
                                // Register for Cloud messaging
                                startWithNewAccount();
                            }
                        } else if (!FluxCUtils.isSignedInWPComOrHasWPOrgSite(mAccountStore, mSiteStore)) {
                            if (!ListenerUtil.mutListener.listen(6216)) {
                                // can't do anything if user isn't signed in (either to wp.com or self-hosted)
                                finish();
                            }
                        }
                    }
                    break;
                case RequestCodes.REAUTHENTICATE:
                    if (!ListenerUtil.mutListener.listen(6220)) {
                        if (resultCode == RESULT_OK) {
                            if (!ListenerUtil.mutListener.listen(6219)) {
                                // Register for Cloud messaging
                                GCMRegistrationIntentService.enqueueWork(this, new Intent(this, GCMRegistrationIntentService.class));
                            }
                        }
                    }
                    break;
                case RequestCodes.LOGIN_EPILOGUE:
                    if (!ListenerUtil.mutListener.listen(6223)) {
                        if (resultCode == RESULT_OK) {
                            if (!ListenerUtil.mutListener.listen(6221)) {
                                setSite(data);
                            }
                            if (!ListenerUtil.mutListener.listen(6222)) {
                                passOnActivityResultToMySiteFragment(requestCode, resultCode, data);
                            }
                        }
                    }
                    break;
                case RequestCodes.SITE_PICKER:
                    boolean isSameSiteSelected = (ListenerUtil.mutListener.listen(6224) ? (data != null || data.getIntExtra(SitePickerActivity.KEY_SITE_LOCAL_ID, SelectedSiteRepository.UNAVAILABLE) == mSelectedSiteRepository.getSelectedSiteLocalId()) : (data != null && data.getIntExtra(SitePickerActivity.KEY_SITE_LOCAL_ID, SelectedSiteRepository.UNAVAILABLE) == mSelectedSiteRepository.getSelectedSiteLocalId()));
                    if (!ListenerUtil.mutListener.listen(6229)) {
                        if (!isSameSiteSelected) {
                            if (!ListenerUtil.mutListener.listen(6225)) {
                                QuickStartUtils.cancelQuickStartReminder(this);
                            }
                            if (!ListenerUtil.mutListener.listen(6226)) {
                                AppPrefs.setQuickStartNoticeRequired(false);
                            }
                            if (!ListenerUtil.mutListener.listen(6227)) {
                                AppPrefs.setLastSkippedQuickStartTask(null);
                            }
                            if (!ListenerUtil.mutListener.listen(6228)) {
                                mPrivateAtomicCookie.clearCookie();
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(6230)) {
                        setSite(data);
                    }
                    if (!ListenerUtil.mutListener.listen(6231)) {
                        passOnActivityResultToMySiteFragment(requestCode, resultCode, data);
                    }
                    break;
                case RequestCodes.SITE_SETTINGS:
                    if (!ListenerUtil.mutListener.listen(6233)) {
                        if (resultCode == SiteSettingsFragment.RESULT_BLOG_REMOVED) {
                            if (!ListenerUtil.mutListener.listen(6232)) {
                                handleSiteRemoved();
                            }
                        }
                    }
                    break;
                case RequestCodes.APP_SETTINGS:
                    if (!ListenerUtil.mutListener.listen(6235)) {
                        if (resultCode == AppSettingsFragment.LANGUAGE_CHANGED) {
                            if (!ListenerUtil.mutListener.listen(6234)) {
                                appLanguageChanged();
                            }
                        }
                    }
                    break;
                case RequestCodes.NOTE_DETAIL:
                    if (!ListenerUtil.mutListener.listen(6237)) {
                        if (getNotificationsListFragment() != null) {
                            if (!ListenerUtil.mutListener.listen(6236)) {
                                getNotificationsListFragment().onActivityResult(requestCode, resultCode, data);
                            }
                        }
                    }
                    break;
                case RequestCodes.STORIES_PHOTO_PICKER:
                case RequestCodes.PHOTO_PICKER:
                case RequestCodes.DOMAIN_REGISTRATION:
                    if (!ListenerUtil.mutListener.listen(6238)) {
                        passOnActivityResultToMySiteFragment(requestCode, resultCode, data);
                    }
                    break;
            }
        }
    }

    private void appLanguageChanged() {
        if (!ListenerUtil.mutListener.listen(6240)) {
            // onResume that is called right afterwards.
            new Handler(Looper.getMainLooper()).post(this::recreate);
        }
        if (!ListenerUtil.mutListener.listen(6241)) {
            // to get the ReaderTag.equals method recognize the equality based on the ReaderTag.getLabel method.
            AppPrefs.setReaderTag(null);
        }
    }

    private void startWithNewAccount() {
        if (!ListenerUtil.mutListener.listen(6242)) {
            GCMRegistrationIntentService.enqueueWork(this, new Intent(this, GCMRegistrationIntentService.class));
        }
        if (!ListenerUtil.mutListener.listen(6243)) {
            ReaderUpdateServiceStarter.startService(this, EnumSet.of(UpdateTask.TAGS, UpdateTask.FOLLOWED_BLOGS));
        }
    }

    private MySiteFragment getMySiteFragment() {
        Fragment fragment = mBottomNav.getFragment(PageType.MY_SITE);
        if (!ListenerUtil.mutListener.listen(6244)) {
            if (fragment instanceof MySiteFragment) {
                return (MySiteFragment) fragment;
            }
        }
        return null;
    }

    private void passOnActivityResultToMySiteFragment(int requestCode, int resultCode, Intent data) {
        Fragment fragment = mBottomNav.getFragment(PageType.MY_SITE);
        if (!ListenerUtil.mutListener.listen(6246)) {
            if (fragment != null) {
                if (!ListenerUtil.mutListener.listen(6245)) {
                    fragment.onActivityResult(requestCode, resultCode, data);
                }
            }
        }
    }

    private NotificationsListFragment getNotificationsListFragment() {
        Fragment fragment = mBottomNav.getFragment(PageType.NOTIFS);
        if (!ListenerUtil.mutListener.listen(6247)) {
            if (fragment instanceof NotificationsListFragment) {
                return (NotificationsListFragment) fragment;
            }
        }
        return null;
    }

    // We only do this for Quick Start focus points that need to be shown on the activity level.
    private void addOrRemoveQuickStartFocusPoint(QuickStartTask activeTask, boolean shouldAdd) {
        final QuickStartMySitePrompts prompts = QuickStartMySitePrompts.getPromptDetailsForTask(activeTask);
        if (!ListenerUtil.mutListener.listen(6248)) {
            if (prompts == null)
                return;
        }
        final ViewGroup parentView = findViewById(prompts.getParentContainerId());
        final View targetView = findViewById(prompts.getFocusedContainerId());
        if (!ListenerUtil.mutListener.listen(6270)) {
            if (parentView != null) {
                int size = getResources().getDimensionPixelOffset(R.dimen.quick_start_focus_point_size);
                int horizontalOffset;
                int verticalOffset;
                QuickStartTask followSiteTask = mQuickStartRepository.getQuickStartType().getTaskFromString(QuickStartStore.QUICK_START_FOLLOW_SITE_LABEL);
                if ((ListenerUtil.mutListener.listen(6249) ? (followSiteTask.equals(activeTask) && QuickStartExistingSiteTask.CHECK_NOTIFICATIONS.equals(activeTask)) : (followSiteTask.equals(activeTask) || QuickStartExistingSiteTask.CHECK_NOTIFICATIONS.equals(activeTask)))) {
                    horizontalOffset = targetView != null ? (((ListenerUtil.mutListener.listen(6265) ? ((ListenerUtil.mutListener.listen(6261) ? (targetView.getWidth() % 2) : (ListenerUtil.mutListener.listen(6260) ? (targetView.getWidth() * 2) : (ListenerUtil.mutListener.listen(6259) ? (targetView.getWidth() - 2) : (ListenerUtil.mutListener.listen(6258) ? (targetView.getWidth() + 2) : (targetView.getWidth() / 2))))) % size) : (ListenerUtil.mutListener.listen(6264) ? ((ListenerUtil.mutListener.listen(6261) ? (targetView.getWidth() % 2) : (ListenerUtil.mutListener.listen(6260) ? (targetView.getWidth() * 2) : (ListenerUtil.mutListener.listen(6259) ? (targetView.getWidth() - 2) : (ListenerUtil.mutListener.listen(6258) ? (targetView.getWidth() + 2) : (targetView.getWidth() / 2))))) / size) : (ListenerUtil.mutListener.listen(6263) ? ((ListenerUtil.mutListener.listen(6261) ? (targetView.getWidth() % 2) : (ListenerUtil.mutListener.listen(6260) ? (targetView.getWidth() * 2) : (ListenerUtil.mutListener.listen(6259) ? (targetView.getWidth() - 2) : (ListenerUtil.mutListener.listen(6258) ? (targetView.getWidth() + 2) : (targetView.getWidth() / 2))))) * size) : (ListenerUtil.mutListener.listen(6262) ? ((ListenerUtil.mutListener.listen(6261) ? (targetView.getWidth() % 2) : (ListenerUtil.mutListener.listen(6260) ? (targetView.getWidth() * 2) : (ListenerUtil.mutListener.listen(6259) ? (targetView.getWidth() - 2) : (ListenerUtil.mutListener.listen(6258) ? (targetView.getWidth() + 2) : (targetView.getWidth() / 2))))) + size) : ((ListenerUtil.mutListener.listen(6261) ? (targetView.getWidth() % 2) : (ListenerUtil.mutListener.listen(6260) ? (targetView.getWidth() * 2) : (ListenerUtil.mutListener.listen(6259) ? (targetView.getWidth() - 2) : (ListenerUtil.mutListener.listen(6258) ? (targetView.getWidth() + 2) : (targetView.getWidth() / 2))))) - size))))) + getResources().getDimensionPixelOffset(R.dimen.quick_start_focus_point_bottom_nav_offset))) : 0;
                    verticalOffset = 0;
                } else if (QuickStartNewSiteTask.PUBLISH_POST.equals(activeTask)) {
                    horizontalOffset = getResources().getDimensionPixelOffset(R.dimen.quick_start_focus_point_my_site_right_offset);
                    verticalOffset = targetView != null ? ((ListenerUtil.mutListener.listen(6257) ? (((ListenerUtil.mutListener.listen(6253) ? (targetView.getHeight() % size) : (ListenerUtil.mutListener.listen(6252) ? (targetView.getHeight() / size) : (ListenerUtil.mutListener.listen(6251) ? (targetView.getHeight() * size) : (ListenerUtil.mutListener.listen(6250) ? (targetView.getHeight() + size) : (targetView.getHeight() - size)))))) % 2) : (ListenerUtil.mutListener.listen(6256) ? (((ListenerUtil.mutListener.listen(6253) ? (targetView.getHeight() % size) : (ListenerUtil.mutListener.listen(6252) ? (targetView.getHeight() / size) : (ListenerUtil.mutListener.listen(6251) ? (targetView.getHeight() * size) : (ListenerUtil.mutListener.listen(6250) ? (targetView.getHeight() + size) : (targetView.getHeight() - size)))))) * 2) : (ListenerUtil.mutListener.listen(6255) ? (((ListenerUtil.mutListener.listen(6253) ? (targetView.getHeight() % size) : (ListenerUtil.mutListener.listen(6252) ? (targetView.getHeight() / size) : (ListenerUtil.mutListener.listen(6251) ? (targetView.getHeight() * size) : (ListenerUtil.mutListener.listen(6250) ? (targetView.getHeight() + size) : (targetView.getHeight() - size)))))) - 2) : (ListenerUtil.mutListener.listen(6254) ? (((ListenerUtil.mutListener.listen(6253) ? (targetView.getHeight() % size) : (ListenerUtil.mutListener.listen(6252) ? (targetView.getHeight() / size) : (ListenerUtil.mutListener.listen(6251) ? (targetView.getHeight() * size) : (ListenerUtil.mutListener.listen(6250) ? (targetView.getHeight() + size) : (targetView.getHeight() - size)))))) + 2) : (((ListenerUtil.mutListener.listen(6253) ? (targetView.getHeight() % size) : (ListenerUtil.mutListener.listen(6252) ? (targetView.getHeight() / size) : (ListenerUtil.mutListener.listen(6251) ? (targetView.getHeight() * size) : (ListenerUtil.mutListener.listen(6250) ? (targetView.getHeight() + size) : (targetView.getHeight() - size)))))) / 2)))))) : 0;
                } else {
                    horizontalOffset = 0;
                    verticalOffset = 0;
                }
                if (!ListenerUtil.mutListener.listen(6269)) {
                    if ((ListenerUtil.mutListener.listen(6266) ? (targetView != null || shouldAdd) : (targetView != null && shouldAdd))) {
                        if (!ListenerUtil.mutListener.listen(6268)) {
                            QuickStartUtils.addQuickStartFocusPointAboveTheView(parentView, targetView, horizontalOffset, verticalOffset);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(6267)) {
                            QuickStartUtils.removeQuickStartFocusPoint(parentView);
                        }
                    }
                }
            }
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onAuthenticationChanged(OnAuthenticationChanged event) {
        if (!ListenerUtil.mutListener.listen(6274)) {
            if (event.isError()) {
                if (!ListenerUtil.mutListener.listen(6273)) {
                    if ((ListenerUtil.mutListener.listen(6271) ? (mSelectedSiteRepository.hasSelectedSite() || event.error.type == AuthenticationErrorType.INVALID_TOKEN) : (mSelectedSiteRepository.hasSelectedSite() && event.error.type == AuthenticationErrorType.INVALID_TOKEN))) {
                        if (!ListenerUtil.mutListener.listen(6272)) {
                            AuthenticationDialogUtils.showAuthErrorView(this, mSiteStore, getSelectedSite());
                        }
                    }
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(6287)) {
            if (mAccountStore.hasAccessToken()) {
                if (!ListenerUtil.mutListener.listen(6286)) {
                    if (mIsMagicLinkLogin) {
                        if (!ListenerUtil.mutListener.listen(6285)) {
                            if (mIsMagicLinkSignup) {
                                if (!ListenerUtil.mutListener.listen(6279)) {
                                    // updated account info.
                                    AppPrefs.setShouldTrackMagicLinkSignup(true);
                                }
                                if (!ListenerUtil.mutListener.listen(6280)) {
                                    mViewModel.checkAndSetVariantForMySiteDefaultTabExperiment();
                                }
                                if (!ListenerUtil.mutListener.listen(6281)) {
                                    mDispatcher.dispatch(AccountActionBuilder.newFetchAccountAction());
                                }
                                if (!ListenerUtil.mutListener.listen(6284)) {
                                    if (mJetpackConnectSource != null) {
                                        if (!ListenerUtil.mutListener.listen(6283)) {
                                            ActivityLauncher.continueJetpackConnect(this, mJetpackConnectSource, getSelectedSite());
                                        }
                                    } else {
                                        if (!ListenerUtil.mutListener.listen(6282)) {
                                            ActivityLauncher.showSignupEpilogue(this, null, null, null, null, true);
                                        }
                                    }
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(6275)) {
                                    mLoginAnalyticsListener.trackLoginMagicLinkSucceeded();
                                }
                                if (!ListenerUtil.mutListener.listen(6278)) {
                                    if (mJetpackConnectSource != null) {
                                        if (!ListenerUtil.mutListener.listen(6277)) {
                                            ActivityLauncher.continueJetpackConnect(this, mJetpackConnectSource, getSelectedSite());
                                        }
                                    } else {
                                        if (!ListenerUtil.mutListener.listen(6276)) {
                                            ActivityLauncher.showLoginEpilogue(this, true, getIntent().getIntegerArrayListExtra(ARG_OLD_SITES_IDS), mBuildConfigWrapper.isSiteCreationEnabled());
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

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onQuickStartCompleted(OnQuickStartCompleted event) {
        if (!ListenerUtil.mutListener.listen(6290)) {
            if ((ListenerUtil.mutListener.listen(6288) ? (getSelectedSite() != null || !event.isError()) : (getSelectedSite() != null && !event.isError()))) {
                if (!ListenerUtil.mutListener.listen(6289)) {
                    // as long as we get any response that is not an error mark quick start notification as received
                    mQuickStartStore.setQuickStartNotificationReceived(event.site.getId(), true);
                }
            }
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onAccountChanged(OnAccountChanged event) {
        if (!ListenerUtil.mutListener.listen(6294)) {
            // Sign-out is handled in `handleSiteRemoved`, no need to show the signup flow here
            if (mAccountStore.hasAccessToken()) {
                if (!ListenerUtil.mutListener.listen(6291)) {
                    mBottomNav.showNoteBadge(mAccountStore.getAccount().getHasUnseenNotes());
                }
                if (!ListenerUtil.mutListener.listen(6293)) {
                    if (AppPrefs.getShouldTrackMagicLinkSignup()) {
                        if (!ListenerUtil.mutListener.listen(6292)) {
                            trackMagicLinkSignupIfNeeded();
                        }
                    }
                }
            }
        }
    }

    /**
     * Bumps stats related to a magic link sign up provided the account has been updated with
     * the username and email address needed to refresh analytics meta data.
     */
    private void trackMagicLinkSignupIfNeeded() {
        AccountModel account = mAccountStore.getAccount();
        if (!ListenerUtil.mutListener.listen(6300)) {
            if ((ListenerUtil.mutListener.listen(6295) ? (!TextUtils.isEmpty(account.getUserName()) || !TextUtils.isEmpty(account.getEmail())) : (!TextUtils.isEmpty(account.getUserName()) && !TextUtils.isEmpty(account.getEmail())))) {
                if (!ListenerUtil.mutListener.listen(6296)) {
                    mLoginAnalyticsListener.trackCreatedAccount(account.getUserName(), account.getEmail(), EMAIL);
                }
                if (!ListenerUtil.mutListener.listen(6297)) {
                    mLoginAnalyticsListener.trackSignupMagicLinkSucceeded();
                }
                if (!ListenerUtil.mutListener.listen(6298)) {
                    mLoginAnalyticsListener.trackAnalyticsSignIn(true);
                }
                if (!ListenerUtil.mutListener.listen(6299)) {
                    AppPrefs.removeShouldTrackMagicLinkSignup();
                }
            }
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(NotificationEvents.NotificationsChanged event) {
        if (!ListenerUtil.mutListener.listen(6301)) {
            mBottomNav.showNoteBadge(event.hasUnseenNotes);
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(NotificationEvents.NotificationsUnseenStatus event) {
        if (!ListenerUtil.mutListener.listen(6302)) {
            mBottomNav.showNoteBadge(event.hasUnseenNotes);
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(ConnectionChangeReceiver.ConnectionChangeEvent event) {
        if (!ListenerUtil.mutListener.listen(6303)) {
            updateConnectionBar(event.isConnected());
        }
    }

    private void checkConnection() {
        if (!ListenerUtil.mutListener.listen(6304)) {
            updateConnectionBar(NetworkUtils.isNetworkAvailable(this));
        }
    }

    private void updateConnectionBar(boolean isConnected) {
        if (!ListenerUtil.mutListener.listen(6309)) {
            if ((ListenerUtil.mutListener.listen(6305) ? (isConnected || mConnectionBar.getVisibility() == View.VISIBLE) : (isConnected && mConnectionBar.getVisibility() == View.VISIBLE))) {
                if (!ListenerUtil.mutListener.listen(6308)) {
                    AniUtils.animateBottomBar(mConnectionBar, false);
                }
            } else if ((ListenerUtil.mutListener.listen(6306) ? (!isConnected || mConnectionBar.getVisibility() != View.VISIBLE) : (!isConnected && mConnectionBar.getVisibility() != View.VISIBLE))) {
                if (!ListenerUtil.mutListener.listen(6307)) {
                    AniUtils.animateBottomBar(mConnectionBar, true);
                }
            }
        }
    }

    private void handleSiteRemoved() {
        if (!ListenerUtil.mutListener.listen(6310)) {
            mViewModel.handleSiteRemoved();
        }
        if (!ListenerUtil.mutListener.listen(6312)) {
            if (!mViewModel.isSignedInWPComOrHasWPOrgSite()) {
                if (!ListenerUtil.mutListener.listen(6311)) {
                    showSignInForResultBasedOnIsJetpackAppBuildConfig(this);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(6314)) {
            if (mViewModel.getHasMultipleSites()) {
                if (!ListenerUtil.mutListener.listen(6313)) {
                    ActivityLauncher.showSitePickerForResult(this, mViewModel.getFirstSite());
                }
            }
        }
    }

    /**
     * @return null if there is no site or if there is no selected site
     */
    @Nullable
    public SiteModel getSelectedSite() {
        return mSelectedSiteRepository.getSelectedSite();
    }

    private void setSelectedSite(@NonNull SiteModel selectedSite) {
        if (!ListenerUtil.mutListener.listen(6315)) {
            // Make selected site visible
            selectedSite.setIsVisible(true);
        }
        if (!ListenerUtil.mutListener.listen(6316)) {
            mSelectedSiteRepository.updateSite(selectedSite);
        }
        if (!ListenerUtil.mutListener.listen(6317)) {
            // When we select a site, we want to update its information or options
            mDispatcher.dispatch(SiteActionBuilder.newFetchSiteAction(selectedSite));
        }
    }

    /**
     * This should not be moved to a SiteUtils.getSelectedSite() or similar static method. We don't want
     * this to be used globally like WordPress.getCurrentBlog() was used. The state is maintained by this
     * Activity and the selected site parameter is passed along to other activities / fragments.
     */
    private void initSelectedSite() {
        int selectedSiteLocalId = mSelectedSiteRepository.getSelectedSiteLocalId(true);
        if (!ListenerUtil.mutListener.listen(6321)) {
            if (selectedSiteLocalId != SelectedSiteRepository.UNAVAILABLE) {
                // Site previously selected, use it
                SiteModel site = mSiteStore.getSiteByLocalId(selectedSiteLocalId);
                if (!ListenerUtil.mutListener.listen(6319)) {
                    if (site != null) {
                        if (!ListenerUtil.mutListener.listen(6318)) {
                            mSelectedSiteRepository.updateSite(site);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(6320)) {
                    // If saved site exist, then return, else (site has been removed?) try to select another site
                    if (mSelectedSiteRepository.hasSelectedSite()) {
                        return;
                    }
                }
            }
        }
        // Try to select the primary wpcom site
        long siteId = mAccountStore.getAccount().getPrimarySiteId();
        SiteModel primarySite = mSiteStore.getSiteBySiteId(siteId);
        if (!ListenerUtil.mutListener.listen(6323)) {
            // Primary site found, select it
            if (primarySite != null) {
                if (!ListenerUtil.mutListener.listen(6322)) {
                    setSelectedSite(primarySite);
                }
                return;
            }
        }
        // Else select the first visible site in the list
        List<SiteModel> sites = mSiteStore.getVisibleSites();
        if (!ListenerUtil.mutListener.listen(6325)) {
            if (sites.size() != 0) {
                if (!ListenerUtil.mutListener.listen(6324)) {
                    setSelectedSite(sites.get(0));
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(6326)) {
            // Else select the first in the list
            sites = mSiteStore.getSites();
        }
        if (!ListenerUtil.mutListener.listen(6328)) {
            if (sites.size() != 0) {
                if (!ListenerUtil.mutListener.listen(6327)) {
                    setSelectedSite(sites.get(0));
                }
            }
        }
    }

    // FluxC events
    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPostUploaded(OnPostUploaded event) {
        if (!ListenerUtil.mutListener.listen(6333)) {
            // it's visible. For more info see https://github.com/wordpress-mobile/WordPress-Android/issues/9604
            if (getLifecycle().getCurrentState().isAtLeast(STARTED)) {
                SiteModel selectedSite = getSelectedSite();
                if (!ListenerUtil.mutListener.listen(6332)) {
                    if ((ListenerUtil.mutListener.listen(6329) ? (selectedSite != null || event.post != null) : (selectedSite != null && event.post != null))) {
                        SiteModel targetSite;
                        if (event.post.getLocalSiteId() == selectedSite.getId()) {
                            targetSite = selectedSite;
                        } else {
                            SiteModel postSite = mSiteStore.getSiteByLocalId(event.post.getLocalSiteId());
                            if (postSite != null) {
                                targetSite = postSite;
                            } else {
                                if (!ListenerUtil.mutListener.listen(6330)) {
                                    AppLog.d(T.MAIN, "WPMainActivity >  onPostUploaded: got an event from a not found site [" + event.post.getLocalSiteId() + "].");
                                }
                                return;
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(6331)) {
                            mUploadUtilsWrapper.onPostUploadedSnackbarHandler(this, findViewById(R.id.coordinator), event.isError(), event.isFirstTimePublish, event.post, null, targetSite, isFirstTimePublishing -> mBloggingRemindersViewModel.onPublishingPost(targetSite.getId(), isFirstTimePublishing));
                        }
                    }
                }
            }
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSiteChanged(OnSiteChanged event) {
        if (!ListenerUtil.mutListener.listen(6336)) {
            // It would be better if the OnSiteChanged provided the list of changed sites.
            if ((ListenerUtil.mutListener.listen(6334) ? (getSelectedSite() == null || mSiteStore.hasSite()) : (getSelectedSite() == null && mSiteStore.hasSite()))) {
                if (!ListenerUtil.mutListener.listen(6335)) {
                    setSelectedSite(mSiteStore.getSites().get(0));
                }
            }
        }
        SiteModel selectedSite = getSelectedSite();
        if (!ListenerUtil.mutListener.listen(6339)) {
            if (selectedSite != null) {
                SiteModel site = mSiteStore.getSiteByLocalId(selectedSite.getId());
                if (!ListenerUtil.mutListener.listen(6338)) {
                    if (site != null) {
                        if (!ListenerUtil.mutListener.listen(6337)) {
                            mSelectedSiteRepository.updateSite(site);
                        }
                    }
                }
            }
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSiteEditorsChanged(OnSiteEditorsChanged event) {
        if (!ListenerUtil.mutListener.listen(6340)) {
            // When the site editor details are loaded from the remote backend, make sure to set a default if empty
            if (event.isError()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(6341)) {
            refreshCurrentSelectedSiteAfterEditorChanges(false, event.site.getId());
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onAllSitesMobileEditorChanged(OnAllSitesMobileEditorChanged event) {
        if (!ListenerUtil.mutListener.listen(6342)) {
            if (event.isError()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(6344)) {
            if (event.isNetworkResponse) {
                if (!ListenerUtil.mutListener.listen(6343)) {
                    // We can remove the global app setting now, since we're sure the migration ended with success.
                    AppPrefs.removeAppWideEditorPreference();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6345)) {
            refreshCurrentSelectedSiteAfterEditorChanges(true, -1);
        }
    }

    private void refreshCurrentSelectedSiteAfterEditorChanges(boolean alwaysRefreshUI, int siteLocalId) {
        if (!ListenerUtil.mutListener.listen(6346)) {
            // Need to update the user property about GB enabled on any of the sites
            AnalyticsUtils.refreshMetadata(mAccountStore, mSiteStore);
        }
        if (!ListenerUtil.mutListener.listen(6349)) {
            // It would be better if the OnSiteChanged provided the list of changed sites.
            if ((ListenerUtil.mutListener.listen(6347) ? (getSelectedSite() == null || mSiteStore.hasSite()) : (getSelectedSite() == null && mSiteStore.hasSite()))) {
                if (!ListenerUtil.mutListener.listen(6348)) {
                    setSelectedSite(mSiteStore.getSites().get(0));
                }
            }
        }
        SiteModel selectedSite = getSelectedSite();
        if (!ListenerUtil.mutListener.listen(6354)) {
            if (selectedSite != null) {
                if (!ListenerUtil.mutListener.listen(6351)) {
                    // When alwaysRefreshUI is `true` we need to refresh the UI regardless of the current site
                    if (!alwaysRefreshUI) {
                        if (!ListenerUtil.mutListener.listen(6350)) {
                            // we need to refresh the UI only when the site IDs matches
                            if (selectedSite.getId() != siteLocalId) {
                                // No need to refresh the UI, since the current selected site is another site
                                return;
                            }
                        }
                    }
                }
                SiteModel site = mSiteStore.getSiteByLocalId(selectedSite.getId());
                if (!ListenerUtil.mutListener.listen(6353)) {
                    if (site != null) {
                        if (!ListenerUtil.mutListener.listen(6352)) {
                            mSelectedSiteRepository.updateSite(site);
                        }
                    }
                }
            }
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSiteRemoved(OnSiteRemoved event) {
        if (!ListenerUtil.mutListener.listen(6355)) {
            handleSiteRemoved();
        }
    }

    @Override
    public void onPositiveClicked(@NonNull String instanceTag) {
        MySiteFragment mySiteFragment = getMySiteFragment();
        if (!ListenerUtil.mutListener.listen(6357)) {
            if (mySiteFragment != null) {
                if (!ListenerUtil.mutListener.listen(6356)) {
                    mySiteFragment.onPositiveClicked(instanceTag);
                }
            }
        }
    }

    @Override
    public void onNegativeClicked(@NonNull String instanceTag) {
        MySiteFragment mySiteFragment = getMySiteFragment();
        if (!ListenerUtil.mutListener.listen(6359)) {
            if (mySiteFragment != null) {
                if (!ListenerUtil.mutListener.listen(6358)) {
                    mySiteFragment.onNegativeClicked(instanceTag);
                }
            }
        }
    }

    @Override
    public void onSetPromptReminderClick(final int siteId) {
        if (!ListenerUtil.mutListener.listen(6360)) {
            mBloggingRemindersViewModel.onBloggingPromptSchedulingRequested(siteId);
        }
    }

    @Override
    public void onShowBloggingPromptsOnboarding() {
        if (!ListenerUtil.mutListener.listen(6361)) {
            showBloggingPromptsOnboarding();
        }
    }

    @Override
    public void onUpdateSelectedSiteResult(int resultCode, @Nullable Intent data) {
        if (!ListenerUtil.mutListener.listen(6362)) {
            onActivityResult(RequestCodes.SITE_PICKER, resultCode, data);
        }
    }

    // SnackBar sometimes do not appear when another SnackBar is still visible, even in other activities (weird)
    @Override
    protected void onPause() {
        if (!ListenerUtil.mutListener.listen(6363)) {
            super.onPause();
        }
        if (!ListenerUtil.mutListener.listen(6364)) {
            QuickStartUtils.removeQuickStartFocusPoint(findViewById(R.id.root_view_main));
        }
    }
}
