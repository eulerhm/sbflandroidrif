package org.wordpress.android.ui.accounts;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.material.snackbar.Snackbar;
import org.jetbrains.annotations.NotNull;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.analytics.AnalyticsTracker;
import org.wordpress.android.fluxc.model.SiteModel;
import org.wordpress.android.fluxc.network.MemorizingTrustManager;
import org.wordpress.android.fluxc.store.AccountStore.AuthEmailPayloadScheme;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.fluxc.store.SiteStore.ConnectSiteInfoPayload;
import org.wordpress.android.login.AuthOptions;
import org.wordpress.android.login.GoogleFragment;
import org.wordpress.android.login.GoogleFragment.GoogleListener;
import org.wordpress.android.login.Login2FaFragment;
import org.wordpress.android.login.LoginAnalyticsListener;
import org.wordpress.android.login.LoginEmailFragment;
import org.wordpress.android.login.LoginEmailPasswordFragment;
import org.wordpress.android.login.LoginGoogleFragment;
import org.wordpress.android.login.LoginListener;
import org.wordpress.android.login.LoginMagicLinkRequestFragment;
import org.wordpress.android.login.LoginMagicLinkSentFragment;
import org.wordpress.android.login.LoginMode;
import org.wordpress.android.login.LoginSiteAddressFragment;
import org.wordpress.android.login.LoginUsernamePasswordFragment;
import org.wordpress.android.login.SignupConfirmationFragment;
import org.wordpress.android.login.SignupGoogleFragment;
import org.wordpress.android.login.SignupMagicLinkFragment;
import org.wordpress.android.support.ZendeskExtraTags;
import org.wordpress.android.support.ZendeskHelper;
import org.wordpress.android.ui.ActivityLauncher;
import org.wordpress.android.ui.JetpackConnectionSource;
import org.wordpress.android.ui.LocaleAwareActivity;
import org.wordpress.android.ui.RequestCodes;
import org.wordpress.android.ui.accounts.HelpActivity.Origin;
import org.wordpress.android.ui.accounts.LoginNavigationEvents.ShowNoJetpackSites;
import org.wordpress.android.ui.accounts.LoginNavigationEvents.ShowSiteAddressError;
import org.wordpress.android.ui.accounts.SmartLockHelper.Callback;
import org.wordpress.android.ui.accounts.UnifiedLoginTracker.Click;
import org.wordpress.android.ui.accounts.UnifiedLoginTracker.Flow;
import org.wordpress.android.ui.accounts.UnifiedLoginTracker.Source;
import org.wordpress.android.ui.accounts.login.LoginPrologueFragment;
import org.wordpress.android.ui.accounts.login.LoginPrologueListener;
import org.wordpress.android.ui.accounts.login.jetpack.LoginNoSitesFragment;
import org.wordpress.android.ui.accounts.login.jetpack.LoginSiteCheckErrorFragment;
import org.wordpress.android.ui.main.SitePickerActivity;
import org.wordpress.android.ui.notifications.services.NotificationsUpdateServiceStarter;
import org.wordpress.android.ui.posts.BasicFragmentDialog;
import org.wordpress.android.ui.posts.BasicFragmentDialog.BasicDialogPositiveClickInterface;
import org.wordpress.android.ui.prefs.AppPrefs;
import org.wordpress.android.ui.reader.services.update.ReaderUpdateLogic;
import org.wordpress.android.ui.reader.services.update.ReaderUpdateServiceStarter;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.AppLog.T;
import org.wordpress.android.util.BuildConfigWrapper;
import org.wordpress.android.util.SelfSignedSSLUtils;
import org.wordpress.android.util.StringUtils;
import org.wordpress.android.util.ToastUtils;
import org.wordpress.android.util.WPActivityUtils;
import org.wordpress.android.util.WPUrlUtils;
import org.wordpress.android.widgets.WPSnackbar;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import javax.inject.Inject;
import static org.wordpress.android.util.ActivityUtils.hideKeyboard;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class LoginActivity extends LocaleAwareActivity implements ConnectionCallbacks, OnConnectionFailedListener, Callback, LoginListener, GoogleListener, LoginPrologueListener, HasAndroidInjector, BasicDialogPositiveClickInterface {

    public static final String ARG_JETPACK_CONNECT_SOURCE = "ARG_JETPACK_CONNECT_SOURCE";

    public static final String MAGIC_LOGIN = "magic-login";

    public static final String TOKEN_PARAMETER = "token";

    private static final String KEY_SMARTLOCK_HELPER_STATE = "KEY_SMARTLOCK_HELPER_STATE";

    private static final String KEY_SIGNUP_FROM_LOGIN_ENABLED = "KEY_SIGNUP_FROM_LOGIN_ENABLED";

    private static final String KEY_SITE_LOGIN_AVAILABLE_FROM_PROLOGUE = "KEY_SITE_LOGIN_AVAILABLE_FROM_PROLOGUE";

    private static final String KEY_UNIFIED_TRACKER_SOURCE = "KEY_UNIFIED_TRACKER_SOURCE";

    private static final String KEY_UNIFIED_TRACKER_FLOW = "KEY_UNIFIED_TRACKER_FLOW";

    private static final String FORGOT_PASSWORD_URL_SUFFIX = "wp-login.php?action=lostpassword";

    private static final String GOOGLE_ERROR_DIALOG_TAG = "google_error_dialog_tag";

    private enum SmartLockHelperState {

        NOT_TRIGGERED, TRIGGER_FILL_IN_ON_CONNECT, FINISH_ON_CONNECT, FINISHED
    }

    private SmartLockHelper mSmartLockHelper;

    private SmartLockHelperState mSmartLockHelperState = SmartLockHelperState.NOT_TRIGGERED;

    private JetpackConnectionSource mJetpackConnectSource;

    private boolean mIsJetpackConnect;

    private boolean mIsSignupFromLoginEnabled;

    private boolean mIsSmartLockTriggeredFromPrologue;

    private boolean mIsSiteLoginAvailableFromPrologue;

    private LoginMode mLoginMode;

    private LoginViewModel mViewModel;

    @Inject
    DispatchingAndroidInjector<Object> mDispatchingAndroidInjector;

    @Inject
    protected LoginAnalyticsListener mLoginAnalyticsListener;

    @Inject
    ZendeskHelper mZendeskHelper;

    @Inject
    UnifiedLoginTracker mUnifiedLoginTracker;

    @Inject
    protected SiteStore mSiteStore;

    @Inject
    protected ViewModelProvider.Factory mViewModelFactory;

    @Inject
    BuildConfigWrapper mBuildConfigWrapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(3862)) {
            ((WordPress) getApplication()).component().inject(this);
        }
        if (!ListenerUtil.mutListener.listen(3863)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(3864)) {
            LoginFlowThemeHelper.injectMissingCustomAttributes(getTheme());
        }
        if (!ListenerUtil.mutListener.listen(3865)) {
            setContentView(R.layout.login_activity);
        }
        if (!ListenerUtil.mutListener.listen(3898)) {
            if (savedInstanceState == null) {
                if (!ListenerUtil.mutListener.listen(3875)) {
                    if (getIntent() != null) {
                        if (!ListenerUtil.mutListener.listen(3874)) {
                            mJetpackConnectSource = (JetpackConnectionSource) getIntent().getSerializableExtra(ARG_JETPACK_CONNECT_SOURCE);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(3876)) {
                    mLoginAnalyticsListener.trackLoginAccessed();
                }
                if (!ListenerUtil.mutListener.listen(3897)) {
                    switch(getLoginMode()) {
                        case FULL:
                            if (!ListenerUtil.mutListener.listen(3877)) {
                                mUnifiedLoginTracker.setSource(Source.DEFAULT);
                            }
                            if (!ListenerUtil.mutListener.listen(3878)) {
                                mIsSignupFromLoginEnabled = true;
                            }
                            if (!ListenerUtil.mutListener.listen(3879)) {
                                loginFromPrologue();
                            }
                            break;
                        case JETPACK_LOGIN_ONLY:
                            if (!ListenerUtil.mutListener.listen(3880)) {
                                mUnifiedLoginTracker.setSource(Source.DEFAULT);
                            }
                            if (!ListenerUtil.mutListener.listen(3881)) {
                                mIsSignupFromLoginEnabled = mBuildConfigWrapper.isSignupEnabled();
                            }
                            if (!ListenerUtil.mutListener.listen(3882)) {
                                loginFromPrologue();
                            }
                            break;
                        case WPCOM_LOGIN_ONLY:
                            if (!ListenerUtil.mutListener.listen(3883)) {
                                mUnifiedLoginTracker.setSource(Source.ADD_WORDPRESS_COM_ACCOUNT);
                            }
                            if (!ListenerUtil.mutListener.listen(3884)) {
                                mIsSignupFromLoginEnabled = true;
                            }
                            if (!ListenerUtil.mutListener.listen(3885)) {
                                checkSmartLockPasswordAndStartLogin();
                            }
                            break;
                        case SELFHOSTED_ONLY:
                            if (!ListenerUtil.mutListener.listen(3886)) {
                                mUnifiedLoginTracker.setSource(Source.SELF_HOSTED);
                            }
                            if (!ListenerUtil.mutListener.listen(3887)) {
                                showFragment(new LoginSiteAddressFragment(), LoginSiteAddressFragment.TAG);
                            }
                            break;
                        case JETPACK_STATS:
                            if (!ListenerUtil.mutListener.listen(3888)) {
                                mUnifiedLoginTracker.setSource(Source.JETPACK);
                            }
                            if (!ListenerUtil.mutListener.listen(3889)) {
                                mIsSignupFromLoginEnabled = true;
                            }
                            if (!ListenerUtil.mutListener.listen(3890)) {
                                checkSmartLockPasswordAndStartLogin();
                            }
                            break;
                        case WPCOM_LOGIN_DEEPLINK:
                            if (!ListenerUtil.mutListener.listen(3891)) {
                                mUnifiedLoginTracker.setSource(Source.DEEPLINK);
                            }
                            if (!ListenerUtil.mutListener.listen(3892)) {
                                checkSmartLockPasswordAndStartLogin();
                            }
                            break;
                        case WPCOM_REAUTHENTICATE:
                            if (!ListenerUtil.mutListener.listen(3893)) {
                                mUnifiedLoginTracker.setSource(Source.REAUTHENTICATION);
                            }
                            if (!ListenerUtil.mutListener.listen(3894)) {
                                checkSmartLockPasswordAndStartLogin();
                            }
                            break;
                        case SHARE_INTENT:
                            if (!ListenerUtil.mutListener.listen(3895)) {
                                mUnifiedLoginTracker.setSource(Source.SHARE);
                            }
                            if (!ListenerUtil.mutListener.listen(3896)) {
                                checkSmartLockPasswordAndStartLogin();
                            }
                            break;
                        case WOO_LOGIN_MODE:
                            break;
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(3866)) {
                    mSmartLockHelperState = SmartLockHelperState.valueOf(savedInstanceState.getString(KEY_SMARTLOCK_HELPER_STATE));
                }
                if (!ListenerUtil.mutListener.listen(3868)) {
                    if (mSmartLockHelperState != SmartLockHelperState.NOT_TRIGGERED) {
                        if (!ListenerUtil.mutListener.listen(3867)) {
                            // reconnect SmartLockHelper
                            initSmartLockHelperConnection();
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(3869)) {
                    mIsSignupFromLoginEnabled = savedInstanceState.getBoolean(KEY_SIGNUP_FROM_LOGIN_ENABLED);
                }
                if (!ListenerUtil.mutListener.listen(3870)) {
                    mIsSiteLoginAvailableFromPrologue = savedInstanceState.getBoolean(KEY_SITE_LOGIN_AVAILABLE_FROM_PROLOGUE);
                }
                String source = savedInstanceState.getString(KEY_UNIFIED_TRACKER_SOURCE);
                if (!ListenerUtil.mutListener.listen(3872)) {
                    if (source != null) {
                        if (!ListenerUtil.mutListener.listen(3871)) {
                            mUnifiedLoginTracker.setSource(source);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(3873)) {
                    mUnifiedLoginTracker.setFlow(savedInstanceState.getString(KEY_UNIFIED_TRACKER_FLOW));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3899)) {
            initViewModel();
        }
    }

    private void initViewModel() {
        if (!ListenerUtil.mutListener.listen(3900)) {
            mViewModel = new ViewModelProvider(this, mViewModelFactory).get(LoginViewModel.class);
        }
        if (!ListenerUtil.mutListener.listen(3901)) {
            // initObservers
            mViewModel.getNavigationEvents().observe(this, event -> {
                LoginNavigationEvents loginEvent = event.getContentIfNotHandled();
                if (loginEvent instanceof ShowSiteAddressError) {
                    showSiteAddressError((ShowSiteAddressError) loginEvent);
                } else if (loginEvent instanceof ShowNoJetpackSites) {
                    showNoJetpackSites();
                }
            });
        }
    }

    private void loginFromPrologue() {
        if (!ListenerUtil.mutListener.listen(3902)) {
            showFragment(new LoginPrologueFragment(), LoginPrologueFragment.TAG);
        }
        if (!ListenerUtil.mutListener.listen(3903)) {
            mIsSmartLockTriggeredFromPrologue = true;
        }
        if (!ListenerUtil.mutListener.listen(3904)) {
            mIsSiteLoginAvailableFromPrologue = true;
        }
        if (!ListenerUtil.mutListener.listen(3905)) {
            initSmartLockIfNotFinished(true);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (!ListenerUtil.mutListener.listen(3906)) {
            super.onSaveInstanceState(outState);
        }
        if (!ListenerUtil.mutListener.listen(3907)) {
            outState.putString(KEY_SMARTLOCK_HELPER_STATE, mSmartLockHelperState.name());
        }
        if (!ListenerUtil.mutListener.listen(3908)) {
            outState.putBoolean(KEY_SIGNUP_FROM_LOGIN_ENABLED, mIsSignupFromLoginEnabled);
        }
        if (!ListenerUtil.mutListener.listen(3909)) {
            outState.putBoolean(KEY_SITE_LOGIN_AVAILABLE_FROM_PROLOGUE, mIsSiteLoginAvailableFromPrologue);
        }
        if (!ListenerUtil.mutListener.listen(3910)) {
            outState.putString(KEY_UNIFIED_TRACKER_SOURCE, mUnifiedLoginTracker.getSource().getValue());
        }
        Flow flow = mUnifiedLoginTracker.getFlow();
        if (!ListenerUtil.mutListener.listen(3912)) {
            if (flow != null) {
                if (!ListenerUtil.mutListener.listen(3911)) {
                    outState.putString(KEY_UNIFIED_TRACKER_FLOW, flow.getValue());
                }
            }
        }
    }

    private void showFragment(Fragment fragment, String tag) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (!ListenerUtil.mutListener.listen(3913)) {
            fragmentTransaction.replace(R.id.fragment_container, fragment, tag);
        }
        if (!ListenerUtil.mutListener.listen(3914)) {
            fragmentTransaction.commit();
        }
    }

    private void slideInFragment(Fragment fragment, boolean shouldAddToBackStack, String tag) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (!ListenerUtil.mutListener.listen(3915)) {
            fragmentTransaction.setCustomAnimations(R.anim.activity_slide_in_from_right, R.anim.activity_slide_out_to_left, R.anim.activity_slide_in_from_left, R.anim.activity_slide_out_to_right);
        }
        if (!ListenerUtil.mutListener.listen(3916)) {
            fragmentTransaction.replace(R.id.fragment_container, fragment, tag);
        }
        if (!ListenerUtil.mutListener.listen(3918)) {
            if (shouldAddToBackStack) {
                if (!ListenerUtil.mutListener.listen(3917)) {
                    fragmentTransaction.addToBackStack(null);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3919)) {
            fragmentTransaction.commitAllowingStateLoss();
        }
    }

    private void addGoogleFragment(GoogleFragment googleFragment, String tag) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (!ListenerUtil.mutListener.listen(3920)) {
            googleFragment.setRetainInstance(true);
        }
        if (!ListenerUtil.mutListener.listen(3921)) {
            fragmentTransaction.add(googleFragment, tag);
        }
        if (!ListenerUtil.mutListener.listen(3922)) {
            fragmentTransaction.commit();
        }
    }

    private LoginPrologueFragment getLoginPrologueFragment() {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(LoginPrologueFragment.TAG);
        return fragment == null ? null : (LoginPrologueFragment) fragment;
    }

    private LoginEmailFragment getLoginEmailFragment() {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(LoginEmailFragment.TAG);
        return fragment == null ? null : (LoginEmailFragment) fragment;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!ListenerUtil.mutListener.listen(3924)) {
            if (item.getItemId() == android.R.id.home) {
                if (!ListenerUtil.mutListener.listen(3923)) {
                    onBackPressed();
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public LoginMode getLoginMode() {
        if (!ListenerUtil.mutListener.listen(3925)) {
            if (mLoginMode != null) {
                // returned the cached value
                return mLoginMode;
            }
        }
        if (!ListenerUtil.mutListener.listen(3926)) {
            // compute and cache the Login mode
            mLoginMode = LoginMode.fromIntent(getIntent());
        }
        return mLoginMode;
    }

    private void loggedInAndFinish(ArrayList<Integer> oldSitesIds, boolean doLoginUpdate) {
        if (!ListenerUtil.mutListener.listen(3963)) {
            switch(getLoginMode()) {
                case JETPACK_LOGIN_ONLY:
                    if (!ListenerUtil.mutListener.listen(3938)) {
                        if ((ListenerUtil.mutListener.listen(3927) ? (!mSiteStore.hasSite() || !mBuildConfigWrapper.isSiteCreationEnabled()) : (!mSiteStore.hasSite() && !mBuildConfigWrapper.isSiteCreationEnabled()))) {
                            if (!ListenerUtil.mutListener.listen(3937)) {
                                handleNoJetpackSites();
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(3934)) {
                                if ((ListenerUtil.mutListener.listen(3931) ? ((ListenerUtil.mutListener.listen(3930) ? ((ListenerUtil.mutListener.listen(3929) ? ((ListenerUtil.mutListener.listen(3928) ? (!mSiteStore.hasSite() || AppPrefs.shouldShowPostSignupInterstitial()) : (!mSiteStore.hasSite() && AppPrefs.shouldShowPostSignupInterstitial())) || !doLoginUpdate) : ((ListenerUtil.mutListener.listen(3928) ? (!mSiteStore.hasSite() || AppPrefs.shouldShowPostSignupInterstitial()) : (!mSiteStore.hasSite() && AppPrefs.shouldShowPostSignupInterstitial())) && !doLoginUpdate)) || mBuildConfigWrapper.isSiteCreationEnabled()) : ((ListenerUtil.mutListener.listen(3929) ? ((ListenerUtil.mutListener.listen(3928) ? (!mSiteStore.hasSite() || AppPrefs.shouldShowPostSignupInterstitial()) : (!mSiteStore.hasSite() && AppPrefs.shouldShowPostSignupInterstitial())) || !doLoginUpdate) : ((ListenerUtil.mutListener.listen(3928) ? (!mSiteStore.hasSite() || AppPrefs.shouldShowPostSignupInterstitial()) : (!mSiteStore.hasSite() && AppPrefs.shouldShowPostSignupInterstitial())) && !doLoginUpdate)) && mBuildConfigWrapper.isSiteCreationEnabled())) || mBuildConfigWrapper.isSignupEnabled()) : ((ListenerUtil.mutListener.listen(3930) ? ((ListenerUtil.mutListener.listen(3929) ? ((ListenerUtil.mutListener.listen(3928) ? (!mSiteStore.hasSite() || AppPrefs.shouldShowPostSignupInterstitial()) : (!mSiteStore.hasSite() && AppPrefs.shouldShowPostSignupInterstitial())) || !doLoginUpdate) : ((ListenerUtil.mutListener.listen(3928) ? (!mSiteStore.hasSite() || AppPrefs.shouldShowPostSignupInterstitial()) : (!mSiteStore.hasSite() && AppPrefs.shouldShowPostSignupInterstitial())) && !doLoginUpdate)) || mBuildConfigWrapper.isSiteCreationEnabled()) : ((ListenerUtil.mutListener.listen(3929) ? ((ListenerUtil.mutListener.listen(3928) ? (!mSiteStore.hasSite() || AppPrefs.shouldShowPostSignupInterstitial()) : (!mSiteStore.hasSite() && AppPrefs.shouldShowPostSignupInterstitial())) || !doLoginUpdate) : ((ListenerUtil.mutListener.listen(3928) ? (!mSiteStore.hasSite() || AppPrefs.shouldShowPostSignupInterstitial()) : (!mSiteStore.hasSite() && AppPrefs.shouldShowPostSignupInterstitial())) && !doLoginUpdate)) && mBuildConfigWrapper.isSiteCreationEnabled())) && mBuildConfigWrapper.isSignupEnabled()))) {
                                    if (!ListenerUtil.mutListener.listen(3933)) {
                                        ActivityLauncher.showPostSignupInterstitial(this);
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(3932)) {
                                        ActivityLauncher.showMainActivityAndLoginEpilogue(this, oldSitesIds, doLoginUpdate);
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(3935)) {
                                setResult(Activity.RESULT_OK);
                            }
                            if (!ListenerUtil.mutListener.listen(3936)) {
                                finish();
                            }
                        }
                    }
                    break;
                case FULL:
                case WPCOM_LOGIN_ONLY:
                    if (!ListenerUtil.mutListener.listen(3943)) {
                        if ((ListenerUtil.mutListener.listen(3940) ? ((ListenerUtil.mutListener.listen(3939) ? (!mSiteStore.hasSite() || AppPrefs.shouldShowPostSignupInterstitial()) : (!mSiteStore.hasSite() && AppPrefs.shouldShowPostSignupInterstitial())) || !doLoginUpdate) : ((ListenerUtil.mutListener.listen(3939) ? (!mSiteStore.hasSite() || AppPrefs.shouldShowPostSignupInterstitial()) : (!mSiteStore.hasSite() && AppPrefs.shouldShowPostSignupInterstitial())) && !doLoginUpdate))) {
                            if (!ListenerUtil.mutListener.listen(3942)) {
                                ActivityLauncher.showPostSignupInterstitial(this);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(3941)) {
                                ActivityLauncher.showMainActivityAndLoginEpilogue(this, oldSitesIds, doLoginUpdate);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(3944)) {
                        setResult(Activity.RESULT_OK);
                    }
                    if (!ListenerUtil.mutListener.listen(3945)) {
                        finish();
                    }
                    break;
                case JETPACK_STATS:
                    if (!ListenerUtil.mutListener.listen(3946)) {
                        ActivityLauncher.showLoginEpilogueForResult(this, oldSitesIds, true);
                    }
                    break;
                case WPCOM_LOGIN_DEEPLINK:
                case WPCOM_REAUTHENTICATE:
                    if (!ListenerUtil.mutListener.listen(3947)) {
                        ActivityLauncher.showLoginEpilogueForResult(this, oldSitesIds, false);
                    }
                    break;
                case SHARE_INTENT:
                case SELFHOSTED_ONLY:
                    // newly added self-hosted site's ID, so we can select it
                    ArrayList<Integer> newSitesIds = new ArrayList<>();
                    if (!ListenerUtil.mutListener.listen(3949)) {
                        {
                            long _loopCounter120 = 0;
                            for (SiteModel site : mSiteStore.getSites()) {
                                ListenerUtil.loopListener.listen("_loopCounter120", ++_loopCounter120);
                                if (!ListenerUtil.mutListener.listen(3948)) {
                                    newSitesIds.add(site.getId());
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(3950)) {
                        newSitesIds.removeAll(oldSitesIds);
                    }
                    if (!ListenerUtil.mutListener.listen(3961)) {
                        if ((ListenerUtil.mutListener.listen(3955) ? (newSitesIds.size() >= 0) : (ListenerUtil.mutListener.listen(3954) ? (newSitesIds.size() <= 0) : (ListenerUtil.mutListener.listen(3953) ? (newSitesIds.size() < 0) : (ListenerUtil.mutListener.listen(3952) ? (newSitesIds.size() != 0) : (ListenerUtil.mutListener.listen(3951) ? (newSitesIds.size() == 0) : (newSitesIds.size() > 0))))))) {
                            Intent intent = new Intent();
                            if (!ListenerUtil.mutListener.listen(3959)) {
                                intent.putExtra(SitePickerActivity.KEY_SITE_LOCAL_ID, newSitesIds.get(0));
                            }
                            if (!ListenerUtil.mutListener.listen(3960)) {
                                setResult(Activity.RESULT_OK, intent);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(3956)) {
                                AppLog.e(T.MAIN, "Couldn't detect newly added self-hosted site. " + "Expected at least 1 site ID but was 0.");
                            }
                            if (!ListenerUtil.mutListener.listen(3957)) {
                                ToastUtils.showToast(this, R.string.site_picker_failed_selecting_added_site);
                            }
                            if (!ListenerUtil.mutListener.listen(3958)) {
                                setResult(Activity.RESULT_OK);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(3962)) {
                        // skip the epilogue when only added a self-hosted site or sharing to WordPress
                        finish();
                    }
                    break;
                case WOO_LOGIN_MODE:
                    break;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!ListenerUtil.mutListener.listen(3964)) {
            AppLog.d(T.MAIN, "LoginActivity: onActivity Result - requestCode" + requestCode);
        }
        if (!ListenerUtil.mutListener.listen(3965)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
        if (!ListenerUtil.mutListener.listen(3977)) {
            switch(requestCode) {
                case RequestCodes.SHOW_LOGIN_EPILOGUE_AND_RETURN:
                case RequestCodes.SHOW_SIGNUP_EPILOGUE_AND_RETURN:
                    if (!ListenerUtil.mutListener.listen(3966)) {
                        // return to login caller now
                        setResult(RESULT_OK);
                    }
                    if (!ListenerUtil.mutListener.listen(3967)) {
                        finish();
                    }
                    break;
                case RequestCodes.SMART_LOCK_SAVE:
                    if (!ListenerUtil.mutListener.listen(3971)) {
                        if (resultCode == RESULT_OK) {
                            if (!ListenerUtil.mutListener.listen(3969)) {
                                mLoginAnalyticsListener.trackLoginAutofillCredentialsUpdated();
                            }
                            if (!ListenerUtil.mutListener.listen(3970)) {
                                AppLog.d(AppLog.T.NUX, "Credentials saved");
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(3968)) {
                                AppLog.d(AppLog.T.NUX, "Credentials save cancelled");
                            }
                        }
                    }
                    break;
                case RequestCodes.SMART_LOCK_READ:
                    if (!ListenerUtil.mutListener.listen(3976)) {
                        if (resultCode == RESULT_OK) {
                            if (!ListenerUtil.mutListener.listen(3974)) {
                                AppLog.d(AppLog.T.NUX, "Credentials retrieved");
                            }
                            Credential credential = data.getParcelableExtra(Credential.EXTRA_KEY);
                            if (!ListenerUtil.mutListener.listen(3975)) {
                                onCredentialRetrieved(credential);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(3972)) {
                                AppLog.e(AppLog.T.NUX, "Credential read failed");
                            }
                            if (!ListenerUtil.mutListener.listen(3973)) {
                                onCredentialsUnavailable();
                            }
                        }
                    }
                    break;
            }
        }
    }

    private void jumpToUsernamePassword(String username, String password) {
        LoginUsernamePasswordFragment loginUsernamePasswordFragment = LoginUsernamePasswordFragment.newInstance("wordpress.com", "wordpress.com", username, password, true);
        if (!ListenerUtil.mutListener.listen(3978)) {
            slideInFragment(loginUsernamePasswordFragment, true, LoginUsernamePasswordFragment.TAG);
        }
    }

    private boolean initSmartLockHelperConnection() {
        if (!ListenerUtil.mutListener.listen(3979)) {
            mSmartLockHelper = new SmartLockHelper(this);
        }
        return mSmartLockHelper.initSmartLockForPasswords();
    }

    private void checkSmartLockPasswordAndStartLogin() {
        if (!ListenerUtil.mutListener.listen(3980)) {
            initSmartLockIfNotFinished(true);
        }
        if (!ListenerUtil.mutListener.listen(3982)) {
            if (mSmartLockHelperState == SmartLockHelperState.FINISHED) {
                if (!ListenerUtil.mutListener.listen(3981)) {
                    startLogin();
                }
            }
        }
    }

    /**
     * @param triggerFillInOnConnect set to true, if you want to show an account chooser dialog when the user has
     *                               stored their credentials in the past. Set to false, if you just want to
     *                               initialize SmartLock eg. when you want to use it just to save users credentials.
     */
    private void initSmartLockIfNotFinished(boolean triggerFillInOnConnect) {
        if (!ListenerUtil.mutListener.listen(3988)) {
            if (mSmartLockHelperState == SmartLockHelperState.NOT_TRIGGERED) {
                if (!ListenerUtil.mutListener.listen(3987)) {
                    if (initSmartLockHelperConnection()) {
                        if (!ListenerUtil.mutListener.listen(3986)) {
                            if (triggerFillInOnConnect) {
                                if (!ListenerUtil.mutListener.listen(3985)) {
                                    mSmartLockHelperState = SmartLockHelperState.TRIGGER_FILL_IN_ON_CONNECT;
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(3984)) {
                                    mSmartLockHelperState = SmartLockHelperState.FINISH_ON_CONNECT;
                                }
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(3983)) {
                            // just shortcircuit the attempt to use SmartLockHelper
                            mSmartLockHelperState = SmartLockHelperState.FINISHED;
                        }
                    }
                }
            }
        }
    }

    private void startLogin() {
        if (!ListenerUtil.mutListener.listen(3989)) {
            if (getLoginEmailFragment() != null) {
                // email screen is already shown so, login has already started. Just bail.
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(3994)) {
            if (getLoginPrologueFragment() == null) {
                if (!ListenerUtil.mutListener.listen(3991)) {
                    // prologue fragment is not shown so, the email screen will be the initial screen on the fragment container
                    showFragment(LoginEmailFragment.newInstance(mIsSignupFromLoginEnabled), LoginEmailFragment.TAG);
                }
                if (!ListenerUtil.mutListener.listen(3993)) {
                    if (getLoginMode() == LoginMode.JETPACK_STATS) {
                        if (!ListenerUtil.mutListener.listen(3992)) {
                            mIsJetpackConnect = true;
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(3990)) {
                    // prologue fragment is shown so, slide in the email screen (and add to history)
                    slideInFragment(LoginEmailFragment.newInstance(mIsSignupFromLoginEnabled), true, LoginEmailFragment.TAG);
                }
            }
        }
    }

    @Override
    public void showEmailLoginScreen() {
        if (!ListenerUtil.mutListener.listen(3995)) {
            checkSmartLockPasswordAndStartLogin();
        }
    }

    @Override
    public void onTermsOfServiceClicked() {
        if (!ListenerUtil.mutListener.listen(3996)) {
            AnalyticsTracker.track(AnalyticsTracker.Stat.SIGNUP_TERMS_OF_SERVICE_TAPPED);
        }
        if (!ListenerUtil.mutListener.listen(3997)) {
            mUnifiedLoginTracker.trackClick(Click.TERMS_OF_SERVICE_CLICKED);
        }
        if (!ListenerUtil.mutListener.listen(3998)) {
            ActivityLauncher.openUrlExternal(this, WPUrlUtils.buildTermsOfServiceUrl(this));
        }
    }

    @Override
    public void gotWpcomEmail(String email, boolean verifyEmail, @Nullable AuthOptions authOptions) {
        if (!ListenerUtil.mutListener.listen(3999)) {
            initSmartLockIfNotFinished(false);
        }
        boolean isMagicLinkEnabled = (ListenerUtil.mutListener.listen(4000) ? (getLoginMode() != LoginMode.WPCOM_LOGIN_DEEPLINK || getLoginMode() != LoginMode.SHARE_INTENT) : (getLoginMode() != LoginMode.WPCOM_LOGIN_DEEPLINK && getLoginMode() != LoginMode.SHARE_INTENT));
        if (!ListenerUtil.mutListener.listen(4007)) {
            if (authOptions != null) {
                if (!ListenerUtil.mutListener.listen(4006)) {
                    if (authOptions.isPasswordless()) {
                        if (!ListenerUtil.mutListener.listen(4005)) {
                            showMagicLinkRequestScreen(email, verifyEmail, false, true);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(4004)) {
                            showEmailPasswordScreen(email, verifyEmail, isMagicLinkEnabled);
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4003)) {
                    if (isMagicLinkEnabled) {
                        if (!ListenerUtil.mutListener.listen(4002)) {
                            showMagicLinkRequestScreen(email, verifyEmail, true, false);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(4001)) {
                            showEmailPasswordScreen(email, verifyEmail, false);
                        }
                    }
                }
            }
        }
    }

    private void showEmailPasswordScreen(String email, boolean verifyEmail, boolean allowMagicLink) {
        LoginEmailPasswordFragment loginEmailPasswordFragment = LoginEmailPasswordFragment.newInstance(email, null, null, null, false, allowMagicLink, verifyEmail);
        if (!ListenerUtil.mutListener.listen(4008)) {
            slideInFragment(loginEmailPasswordFragment, true, LoginEmailPasswordFragment.TAG);
        }
    }

    private void showMagicLinkRequestScreen(String email, boolean verifyEmail, boolean allowPassword, boolean forceRequestAtStart) {
        AuthEmailPayloadScheme scheme = mViewModel.getMagicLinkScheme();
        String jetpackConnectionSource = mJetpackConnectSource != null ? mJetpackConnectSource.toString() : null;
        LoginMagicLinkRequestFragment loginMagicLinkRequestFragment = LoginMagicLinkRequestFragment.newInstance(email, scheme, mIsJetpackConnect, jetpackConnectionSource, verifyEmail, allowPassword, forceRequestAtStart);
        if (!ListenerUtil.mutListener.listen(4009)) {
            slideInFragment(loginMagicLinkRequestFragment, true, LoginMagicLinkRequestFragment.TAG);
        }
    }

    @Override
    public void gotUnregisteredEmail(String email) {
        if (!ListenerUtil.mutListener.listen(4010)) {
            showSignupMagicLink(email);
        }
    }

    @Override
    public void gotUnregisteredSocialAccount(String email, String displayName, String idToken, String photoUrl, String service) {
        SignupConfirmationFragment signupConfirmationFragment = SignupConfirmationFragment.newInstance(email, displayName, idToken, photoUrl, service);
        if (!ListenerUtil.mutListener.listen(4011)) {
            slideInFragment(signupConfirmationFragment, true, SignupConfirmationFragment.TAG);
        }
    }

    @Override
    public void loginViaSiteAddress() {
        LoginSiteAddressFragment loginSiteAddressFragment = new LoginSiteAddressFragment();
        if (!ListenerUtil.mutListener.listen(4012)) {
            slideInFragment(loginSiteAddressFragment, true, LoginSiteAddressFragment.TAG);
        }
    }

    @Override
    public void loginViaSocialAccount(String email, String idToken, String service, boolean isPasswordRequired) {
        LoginEmailPasswordFragment loginEmailPasswordFragment = LoginEmailPasswordFragment.newInstance(email, null, idToken, service, isPasswordRequired);
        if (!ListenerUtil.mutListener.listen(4013)) {
            slideInFragment(loginEmailPasswordFragment, true, LoginEmailPasswordFragment.TAG);
        }
    }

    @Override
    public void loggedInViaSocialAccount(ArrayList<Integer> oldSitesIds, boolean doLoginUpdate) {
        if (!ListenerUtil.mutListener.listen(4014)) {
            mLoginAnalyticsListener.trackLoginSocialSuccess();
        }
        if (!ListenerUtil.mutListener.listen(4015)) {
            loggedInAndFinish(oldSitesIds, doLoginUpdate);
        }
    }

    @Override
    public void loginViaWpcomUsernameInstead() {
        if (!ListenerUtil.mutListener.listen(4016)) {
            jumpToUsernamePassword(null, null);
        }
    }

    @Override
    public void showMagicLinkSentScreen(String email, boolean allowPassword) {
        LoginMagicLinkSentFragment loginMagicLinkSentFragment = LoginMagicLinkSentFragment.newInstance(email, allowPassword);
        if (!ListenerUtil.mutListener.listen(4017)) {
            slideInFragment(loginMagicLinkSentFragment, true, LoginMagicLinkSentFragment.TAG);
        }
    }

    @Override
    public void showSignupMagicLink(String email) {
        boolean isEmailClientAvailable = WPActivityUtils.isEmailClientAvailable(this);
        AuthEmailPayloadScheme scheme = mViewModel.getMagicLinkScheme();
        SignupMagicLinkFragment signupMagicLinkFragment = SignupMagicLinkFragment.newInstance(email, mIsJetpackConnect, mJetpackConnectSource != null ? mJetpackConnectSource.toString() : null, isEmailClientAvailable, scheme);
        if (!ListenerUtil.mutListener.listen(4018)) {
            slideInFragment(signupMagicLinkFragment, true, SignupMagicLinkFragment.TAG);
        }
    }

    @Override
    public void showSignupSocial(String email, String displayName, String idToken, String photoUrl, String service) {
        if (!ListenerUtil.mutListener.listen(4020)) {
            if (GoogleFragment.SERVICE_TYPE_GOOGLE.equals(service)) {
                if (!ListenerUtil.mutListener.listen(4019)) {
                    addGoogleFragment(SignupGoogleFragment.newInstance(email, displayName, idToken, photoUrl), SignupGoogleFragment.TAG);
                }
            }
        }
    }

    @Override
    public void openEmailClient(boolean isLogin) {
        if (!ListenerUtil.mutListener.listen(4021)) {
            mUnifiedLoginTracker.trackClick(Click.OPEN_EMAIL_CLIENT);
        }
        if (!ListenerUtil.mutListener.listen(4027)) {
            if (WPActivityUtils.isEmailClientAvailable(this)) {
                if (!ListenerUtil.mutListener.listen(4025)) {
                    if (isLogin) {
                        if (!ListenerUtil.mutListener.listen(4024)) {
                            mLoginAnalyticsListener.trackLoginMagicLinkOpenEmailClientClicked();
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(4023)) {
                            mLoginAnalyticsListener.trackSignupMagicLinkOpenEmailClientClicked();
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(4026)) {
                    WPActivityUtils.openEmailClientChooser(this, getString(R.string.login_select_email_client));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4022)) {
                    ToastUtils.showToast(this, R.string.login_email_client_not_found);
                }
            }
        }
    }

    @Override
    public void usePasswordInstead(String email) {
        if (!ListenerUtil.mutListener.listen(4028)) {
            mLoginAnalyticsListener.trackLoginMagicLinkExited();
        }
        LoginEmailPasswordFragment loginEmailPasswordFragment = LoginEmailPasswordFragment.newInstance(email, null, null, null, false);
        if (!ListenerUtil.mutListener.listen(4029)) {
            slideInFragment(loginEmailPasswordFragment, true, LoginEmailPasswordFragment.TAG);
        }
    }

    @Override
    public void forgotPassword(String url) {
        if (!ListenerUtil.mutListener.listen(4030)) {
            mLoginAnalyticsListener.trackLoginForgotPasswordClicked();
        }
        if (!ListenerUtil.mutListener.listen(4031)) {
            ActivityLauncher.openUrlExternal(this, url + FORGOT_PASSWORD_URL_SUFFIX);
        }
    }

    @Override
    public void useMagicLinkInstead(String email, boolean verifyEmail) {
        if (!ListenerUtil.mutListener.listen(4032)) {
            showMagicLinkRequestScreen(email, verifyEmail, false, true);
        }
    }

    @Override
    public void needs2fa(String email, String password) {
        Login2FaFragment login2FaFragment = Login2FaFragment.newInstance(email, password);
        if (!ListenerUtil.mutListener.listen(4033)) {
            slideInFragment(login2FaFragment, true, Login2FaFragment.TAG);
        }
    }

    @Override
    public void needs2faSocial(String email, String userId, String nonceAuthenticator, String nonceBackup, String nonceSms) {
        if (!ListenerUtil.mutListener.listen(4034)) {
            mLoginAnalyticsListener.trackLoginSocial2faNeeded();
        }
        Login2FaFragment login2FaFragment = Login2FaFragment.newInstanceSocial(email, userId, nonceAuthenticator, nonceBackup, nonceSms);
        if (!ListenerUtil.mutListener.listen(4035)) {
            slideInFragment(login2FaFragment, true, Login2FaFragment.TAG);
        }
    }

    @Override
    public void needs2faSocialConnect(String email, String password, String idToken, String service) {
        if (!ListenerUtil.mutListener.listen(4036)) {
            mLoginAnalyticsListener.trackLoginSocial2faNeeded();
        }
        Login2FaFragment login2FaFragment = Login2FaFragment.newInstanceSocialConnect(email, password, idToken, service);
        if (!ListenerUtil.mutListener.listen(4037)) {
            slideInFragment(login2FaFragment, true, Login2FaFragment.TAG);
        }
    }

    @Override
    public void loggedInViaPassword(ArrayList<Integer> oldSitesIds) {
        if (!ListenerUtil.mutListener.listen(4038)) {
            loggedInAndFinish(oldSitesIds, false);
        }
    }

    @Override
    public void alreadyLoggedInWpcom(ArrayList<Integer> oldSitesIds) {
        if (!ListenerUtil.mutListener.listen(4039)) {
            ToastUtils.showToast(this, R.string.already_logged_in_wpcom, ToastUtils.Duration.LONG);
        }
        if (!ListenerUtil.mutListener.listen(4040)) {
            loggedInAndFinish(oldSitesIds, false);
        }
    }

    @Override
    public void gotWpcomSiteInfo(String siteAddress) {
        LoginEmailFragment loginEmailFragment = LoginEmailFragment.newInstance(siteAddress);
        if (!ListenerUtil.mutListener.listen(4041)) {
            slideInFragment(loginEmailFragment, true, LoginEmailFragment.TAG);
        }
    }

    @Override
    public void gotXmlRpcEndpoint(String inputSiteAddress, String endpointAddress) {
        LoginUsernamePasswordFragment loginUsernamePasswordFragment = LoginUsernamePasswordFragment.newInstance(inputSiteAddress, endpointAddress, null, null, false);
        if (!ListenerUtil.mutListener.listen(4042)) {
            slideInFragment(loginUsernamePasswordFragment, true, LoginUsernamePasswordFragment.TAG);
        }
    }

    @Override
    public void handleSslCertificateError(MemorizingTrustManager memorizingTrustManager, final SelfSignedSSLCallback callback) {
        if (!ListenerUtil.mutListener.listen(4044)) {
            SelfSignedSSLUtils.showSSLWarningDialog(this, memorizingTrustManager, new SelfSignedSSLUtils.Callback() {

                @Override
                public void certificateTrusted() {
                    if (!ListenerUtil.mutListener.listen(4043)) {
                        callback.certificateTrusted();
                    }
                }
            });
        }
    }

    private void viewHelpAndSupport(Origin origin) {
        List<String> extraSupportTags = getLoginMode() == LoginMode.JETPACK_STATS ? Collections.singletonList(ZendeskExtraTags.connectingJetpack) : null;
        if (!ListenerUtil.mutListener.listen(4045)) {
            ActivityLauncher.viewHelpAndSupport(this, origin, null, extraSupportTags);
        }
    }

    @Override
    public void helpSiteAddress(String url) {
        if (!ListenerUtil.mutListener.listen(4046)) {
            viewHelpAndSupport(Origin.LOGIN_SITE_ADDRESS);
        }
    }

    @Override
    public void helpFindingSiteAddress(String username, SiteStore siteStore) {
        if (!ListenerUtil.mutListener.listen(4047)) {
            mUnifiedLoginTracker.trackClick(Click.HELP_FINDING_SITE_ADDRESS);
        }
        if (!ListenerUtil.mutListener.listen(4048)) {
            mZendeskHelper.createNewTicket(this, Origin.LOGIN_SITE_ADDRESS, null);
        }
    }

    @Override
    public void loggedInViaUsernamePassword(ArrayList<Integer> oldSitesIds) {
        if (!ListenerUtil.mutListener.listen(4049)) {
            loggedInAndFinish(oldSitesIds, false);
        }
    }

    @Override
    public void helpEmailScreen(String email) {
        if (!ListenerUtil.mutListener.listen(4050)) {
            viewHelpAndSupport(Origin.LOGIN_EMAIL);
        }
    }

    @Override
    public void helpSignupEmailScreen(String email) {
        if (!ListenerUtil.mutListener.listen(4051)) {
            viewHelpAndSupport(Origin.SIGNUP_EMAIL);
        }
    }

    @Override
    public void helpSignupMagicLinkScreen(String email) {
        if (!ListenerUtil.mutListener.listen(4052)) {
            viewHelpAndSupport(Origin.SIGNUP_MAGIC_LINK);
        }
    }

    @Override
    public void helpSignupConfirmationScreen(String email) {
        if (!ListenerUtil.mutListener.listen(4053)) {
            viewHelpAndSupport(Origin.SIGNUP_CONFIRMATION);
        }
    }

    @Override
    public void helpSocialEmailScreen(String email) {
        if (!ListenerUtil.mutListener.listen(4054)) {
            viewHelpAndSupport(Origin.LOGIN_SOCIAL);
        }
    }

    @Override
    public void addGoogleLoginFragment(boolean isSignupFromLoginEnabled) {
        if (!ListenerUtil.mutListener.listen(4055)) {
            addGoogleFragment(LoginGoogleFragment.newInstance(isSignupFromLoginEnabled), LoginGoogleFragment.TAG);
        }
    }

    @Override
    public void helpMagicLinkRequest(String email) {
        if (!ListenerUtil.mutListener.listen(4056)) {
            viewHelpAndSupport(Origin.LOGIN_MAGIC_LINK);
        }
    }

    @Override
    public void helpMagicLinkSent(String email) {
        if (!ListenerUtil.mutListener.listen(4057)) {
            viewHelpAndSupport(Origin.LOGIN_MAGIC_LINK);
        }
    }

    @Override
    public void helpEmailPasswordScreen(String email) {
        if (!ListenerUtil.mutListener.listen(4058)) {
            viewHelpAndSupport(Origin.LOGIN_EMAIL_PASSWORD);
        }
    }

    @Override
    public void help2FaScreen(String email) {
        if (!ListenerUtil.mutListener.listen(4059)) {
            viewHelpAndSupport(Origin.LOGIN_2FA);
        }
    }

    @Override
    public void startPostLoginServices() {
        if (!ListenerUtil.mutListener.listen(4060)) {
            // uses the application context since the activity is finished immediately below
            ReaderUpdateServiceStarter.startService(getApplicationContext(), EnumSet.of(ReaderUpdateLogic.UpdateTask.TAGS));
        }
        if (!ListenerUtil.mutListener.listen(4061)) {
            // Start Notification service
            NotificationsUpdateServiceStarter.startService(getApplicationContext());
        }
    }

    @Override
    public void helpUsernamePassword(String url, String username, boolean isWpcom) {
        if (!ListenerUtil.mutListener.listen(4062)) {
            viewHelpAndSupport(Origin.LOGIN_USERNAME_PASSWORD);
        }
    }

    @Override
    public void saveCredentialsInSmartLock(@Nullable final String username, @Nullable final String password, @NonNull final String displayName, @Nullable final Uri profilePicture) {
        if (!ListenerUtil.mutListener.listen(4063)) {
            if (getLoginMode() == LoginMode.SELFHOSTED_ONLY) {
                // originate from the site-picker.
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(4065)) {
            if (mSmartLockHelper == null) {
                // log some data to help us debug https://github.com/wordpress-mobile/WordPress-Android/issues/7182
                final String loginModeStr = "LoginMode: " + (getLoginMode() != null ? getLoginMode().name() : "null");
                if (!ListenerUtil.mutListener.listen(4064)) {
                    AppLog.w(AppLog.T.NUX, "Internal inconsistency error! mSmartLockHelper found null!" + loginModeStr);
                }
                // bail
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(4066)) {
            mSmartLockHelper.saveCredentialsInSmartLock(StringUtils.notNullStr(username), StringUtils.notNullStr(password), displayName, profilePicture);
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        if (!ListenerUtil.mutListener.listen(4067)) {
            AppLog.d(AppLog.T.NUX, "Connection result: " + connectionResult);
        }
        if (!ListenerUtil.mutListener.listen(4068)) {
            mSmartLockHelperState = SmartLockHelperState.FINISHED;
        }
    }

    @Override
    public void onConnected(Bundle bundle) {
        if (!ListenerUtil.mutListener.listen(4069)) {
            AppLog.d(AppLog.T.NUX, "Google API client connected");
        }
        if (!ListenerUtil.mutListener.listen(4074)) {
            switch(mSmartLockHelperState) {
                case NOT_TRIGGERED:
                    // should not reach this state here!
                    throw new RuntimeException("Internal inconsistency error!");
                case TRIGGER_FILL_IN_ON_CONNECT:
                    if (!ListenerUtil.mutListener.listen(4070)) {
                        mSmartLockHelperState = SmartLockHelperState.FINISHED;
                    }
                    if (!ListenerUtil.mutListener.listen(4071)) {
                        // force account chooser
                        mSmartLockHelper.disableAutoSignIn();
                    }
                    if (!ListenerUtil.mutListener.listen(4072)) {
                        mSmartLockHelper.smartLockAutoFill(this);
                    }
                    break;
                case FINISH_ON_CONNECT:
                    if (!ListenerUtil.mutListener.listen(4073)) {
                        mSmartLockHelperState = SmartLockHelperState.FINISHED;
                    }
                    break;
                case FINISHED:
                    // don't do anything special. We're reconnecting the GoogleApiClient on rotation.
                    break;
            }
        }
    }

    @Override
    public void onCredentialRetrieved(Credential credential) {
        if (!ListenerUtil.mutListener.listen(4075)) {
            mLoginAnalyticsListener.trackLoginAutofillCredentialsFilled();
        }
        if (!ListenerUtil.mutListener.listen(4076)) {
            mSmartLockHelperState = SmartLockHelperState.FINISHED;
        }
        final String username = credential.getId();
        final String password = credential.getPassword();
        if (!ListenerUtil.mutListener.listen(4077)) {
            jumpToUsernamePassword(username, password);
        }
    }

    @Override
    public void onCredentialsUnavailable() {
        if (!ListenerUtil.mutListener.listen(4078)) {
            mSmartLockHelperState = SmartLockHelperState.FINISHED;
        }
        if (!ListenerUtil.mutListener.listen(4079)) {
            if (mIsSmartLockTriggeredFromPrologue) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(4080)) {
            startLogin();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        if (!ListenerUtil.mutListener.listen(4081)) {
            AppLog.d(AppLog.T.NUX, "Google API client connection suspended");
        }
    }

    @Override
    public void showSignupToLoginMessage() {
        if (!ListenerUtil.mutListener.listen(4082)) {
            WPSnackbar.make(findViewById(R.id.main_view), R.string.signup_user_exists, Snackbar.LENGTH_LONG).show();
        }
    }

    @Override
    public void onGoogleEmailSelected(String email) {
        LoginEmailFragment loginEmailFragment = (LoginEmailFragment) getSupportFragmentManager().findFragmentByTag(LoginEmailFragment.TAG);
        if (!ListenerUtil.mutListener.listen(4084)) {
            if (loginEmailFragment != null) {
                if (!ListenerUtil.mutListener.listen(4083)) {
                    loginEmailFragment.setGoogleEmail(email);
                }
            }
        }
    }

    @Override
    public void onGoogleLoginFinished() {
        LoginEmailFragment loginEmailFragment = (LoginEmailFragment) getSupportFragmentManager().findFragmentByTag(LoginEmailFragment.TAG);
        if (!ListenerUtil.mutListener.listen(4086)) {
            if (loginEmailFragment != null) {
                if (!ListenerUtil.mutListener.listen(4085)) {
                    loginEmailFragment.finishLogin();
                }
            }
        }
    }

    @Override
    public void onGoogleSignupFinished(String name, String email, String photoUrl, String username) {
        if (!ListenerUtil.mutListener.listen(4087)) {
            AnalyticsTracker.track(AnalyticsTracker.Stat.SIGNUP_SOCIAL_SUCCESS);
        }
        if (!ListenerUtil.mutListener.listen(4090)) {
            if (mIsJetpackConnect) {
                if (!ListenerUtil.mutListener.listen(4089)) {
                    ActivityLauncher.showSignupEpilogueForResult(this, name, email, photoUrl, username, false);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4088)) {
                    ActivityLauncher.showMainActivityAndSignupEpilogue(this, name, email, photoUrl, username);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4091)) {
            setResult(Activity.RESULT_OK);
        }
        if (!ListenerUtil.mutListener.listen(4092)) {
            finish();
        }
    }

    @Override
    public void onGoogleSignupError(String msg) {
        if (!ListenerUtil.mutListener.listen(4093)) {
            mUnifiedLoginTracker.trackFailure(msg);
        }
        if (!ListenerUtil.mutListener.listen(4097)) {
            // Only show the error dialog if the activity is still active
            if (!getSupportFragmentManager().isStateSaved()) {
                BasicFragmentDialog dialog = new BasicFragmentDialog();
                if (!ListenerUtil.mutListener.listen(4095)) {
                    dialog.initialize(GOOGLE_ERROR_DIALOG_TAG, getString(R.string.error), msg, getString(org.wordpress.android.login.R.string.login_error_button), null, null);
                }
                if (!ListenerUtil.mutListener.listen(4096)) {
                    dialog.show(getSupportFragmentManager(), GOOGLE_ERROR_DIALOG_TAG);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4094)) {
                    AppLog.d(T.MAIN, "'Google sign up failed' dialog not shown, because the activity wasn't visible.");
                }
            }
        }
    }

    @Override
    public void onPositiveClicked(@NotNull String instanceTag) {
        if (!ListenerUtil.mutListener.listen(4098)) {
            switch(instanceTag) {
                case GOOGLE_ERROR_DIALOG_TAG:
                    // just dismiss the dialog
                    break;
            }
        }
    }

    @Override
    public AndroidInjector<Object> androidInjector() {
        return mDispatchingAndroidInjector;
    }

    @Override
    public void startOver() {
    }

    @Override
    public void showHelpFindingConnectedEmail() {
    }

    @Override
    public void gotConnectedSiteInfo(@NonNull String siteAddress, @Nullable String redirectUrl, boolean hasJetpack) {
    }

    @Override
    public void helpHandleDiscoveryError(String siteAddress, String endpointAddress, String username, String password, String userAvatarUrl, int errorMessage) {
    }

    @Override
    public void helpNoJetpackScreen(String siteAddress, String endpointAddress, String username, String password, String userAvatarUrl, Boolean checkJetpackAvailability) {
    }

    @Override
    public void loginViaSiteCredentials(String inputSiteAddress) {
    }

    @Override
    public void handleSiteAddressError(ConnectSiteInfoPayload siteInfo) {
        if (!ListenerUtil.mutListener.listen(4099)) {
            mViewModel.onHandleSiteAddressError(siteInfo);
        }
    }

    public void handleNoJetpackSites() {
        if (!ListenerUtil.mutListener.listen(4100)) {
            // hide keyboard if you can
            hideKeyboard(this);
        }
        if (!ListenerUtil.mutListener.listen(4101)) {
            mViewModel.onHandleNoJetpackSites();
        }
    }

    private void showSiteAddressError(ShowSiteAddressError event) {
        LoginSiteCheckErrorFragment fragment = LoginSiteCheckErrorFragment.Companion.newInstance(event.getUrl());
        if (!ListenerUtil.mutListener.listen(4102)) {
            slideInFragment(fragment, true, LoginSiteCheckErrorFragment.TAG);
        }
    }

    private void showNoJetpackSites() {
        LoginNoSitesFragment fragment = LoginNoSitesFragment.Companion.newInstance();
        if (!ListenerUtil.mutListener.listen(4103)) {
            slideInFragment(fragment, false, LoginNoSitesFragment.TAG);
        }
    }
}
