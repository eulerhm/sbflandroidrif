package org.wordpress.android.ui.accounts.login;

import org.jetbrains.annotations.NotNull;
import org.wordpress.android.analytics.AnalyticsTracker;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.login.LoginAnalyticsListener;
import org.wordpress.android.ui.accounts.UnifiedLoginTracker;
import org.wordpress.android.ui.accounts.UnifiedLoginTracker.Click;
import org.wordpress.android.ui.accounts.UnifiedLoginTracker.Flow;
import org.wordpress.android.ui.accounts.UnifiedLoginTracker.Step;
import org.wordpress.android.util.analytics.AnalyticsUtils;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Singleton;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@Singleton
public class LoginAnalyticsTracker implements LoginAnalyticsListener {

    private AccountStore mAccountStore;

    private SiteStore mSiteStore;

    private UnifiedLoginTracker mUnifiedLoginTracker;

    public LoginAnalyticsTracker(AccountStore accountStore, SiteStore siteStore, UnifiedLoginTracker unifiedLoginTracker) {
        if (!ListenerUtil.mutListener.listen(3314)) {
            this.mAccountStore = accountStore;
        }
        if (!ListenerUtil.mutListener.listen(3315)) {
            this.mSiteStore = siteStore;
        }
        if (!ListenerUtil.mutListener.listen(3316)) {
            mUnifiedLoginTracker = unifiedLoginTracker;
        }
    }

    @Override
    public void trackAnalyticsSignIn(boolean isWpcom) {
        if (!ListenerUtil.mutListener.listen(3317)) {
            AnalyticsUtils.trackAnalyticsSignIn(mAccountStore, mSiteStore, isWpcom);
        }
    }

    @Override
    public void trackCreatedAccount(String username, String email, CreatedAccountSource source) {
        if (!ListenerUtil.mutListener.listen(3318)) {
            AnalyticsUtils.trackAnalyticsAccountCreated(username, email, source.asPropertyMap());
        }
    }

    @Override
    public void trackEmailFormViewed() {
        if (!ListenerUtil.mutListener.listen(3319)) {
            AnalyticsTracker.track(AnalyticsTracker.Stat.LOGIN_EMAIL_FORM_VIEWED);
        }
        if (!ListenerUtil.mutListener.listen(3320)) {
            mUnifiedLoginTracker.track(Flow.WORDPRESS_COM, Step.START);
        }
    }

    @Override
    public void trackInsertedInvalidUrl() {
        if (!ListenerUtil.mutListener.listen(3321)) {
            AnalyticsTracker.track(AnalyticsTracker.Stat.LOGIN_INSERTED_INVALID_URL);
        }
    }

    @Override
    public void trackLoginAccessed() {
        if (!ListenerUtil.mutListener.listen(3322)) {
            AnalyticsTracker.track(AnalyticsTracker.Stat.LOGIN_ACCESSED);
        }
    }

    @Override
    public void trackLoginAutofillCredentialsFilled() {
        if (!ListenerUtil.mutListener.listen(3323)) {
            AnalyticsTracker.track(AnalyticsTracker.Stat.LOGIN_AUTOFILL_CREDENTIALS_FILLED);
        }
        if (!ListenerUtil.mutListener.listen(3324)) {
            mUnifiedLoginTracker.track(Flow.SMART_LOCK_LOGIN, Step.START);
        }
    }

    @Override
    public void trackLoginAutofillCredentialsUpdated() {
        if (!ListenerUtil.mutListener.listen(3325)) {
            AnalyticsTracker.track(AnalyticsTracker.Stat.LOGIN_AUTOFILL_CREDENTIALS_UPDATED);
        }
    }

    @Override
    public void trackLoginFailed(String errorContext, String errorType, String errorDescription) {
        if (!ListenerUtil.mutListener.listen(3326)) {
            AnalyticsTracker.track(AnalyticsTracker.Stat.LOGIN_FAILED, errorContext, errorType, errorDescription);
        }
    }

    @Override
    public void trackLoginForgotPasswordClicked() {
        if (!ListenerUtil.mutListener.listen(3327)) {
            AnalyticsTracker.track(AnalyticsTracker.Stat.LOGIN_FORGOT_PASSWORD_CLICKED);
        }
        if (!ListenerUtil.mutListener.listen(3328)) {
            mUnifiedLoginTracker.trackClick(Click.FORGOTTEN_PASSWORD);
        }
    }

    @Override
    public void trackLoginMagicLinkExited() {
        if (!ListenerUtil.mutListener.listen(3329)) {
            AnalyticsTracker.track(AnalyticsTracker.Stat.LOGIN_MAGIC_LINK_EXITED);
        }
    }

    @Override
    public void trackLoginMagicLinkOpened() {
        if (!ListenerUtil.mutListener.listen(3330)) {
            AnalyticsTracker.track(AnalyticsTracker.Stat.LOGIN_MAGIC_LINK_OPENED);
        }
    }

    @Override
    public void trackLoginMagicLinkOpenEmailClientClicked() {
        if (!ListenerUtil.mutListener.listen(3331)) {
            AnalyticsTracker.track(AnalyticsTracker.Stat.LOGIN_MAGIC_LINK_OPEN_EMAIL_CLIENT_CLICKED);
        }
        if (!ListenerUtil.mutListener.listen(3332)) {
            mUnifiedLoginTracker.track(Flow.LOGIN_MAGIC_LINK, Step.EMAIL_OPENED);
        }
    }

    @Override
    public void trackLoginMagicLinkSucceeded() {
        if (!ListenerUtil.mutListener.listen(3333)) {
            AnalyticsTracker.track(AnalyticsTracker.Stat.LOGIN_MAGIC_LINK_SUCCEEDED);
        }
    }

    @Override
    public void trackLoginSocial2faNeeded() {
        if (!ListenerUtil.mutListener.listen(3334)) {
            AnalyticsTracker.track(AnalyticsTracker.Stat.LOGIN_SOCIAL_2FA_NEEDED);
        }
    }

    @Override
    public void trackLoginSocialSuccess() {
        if (!ListenerUtil.mutListener.listen(3335)) {
            AnalyticsTracker.track(AnalyticsTracker.Stat.LOGIN_SOCIAL_SUCCESS);
        }
    }

    @Override
    public void trackMagicLinkFailed(Map<String, ?> properties) {
        if (!ListenerUtil.mutListener.listen(3336)) {
            AnalyticsTracker.track(AnalyticsTracker.Stat.LOGIN_MAGIC_LINK_FAILED, properties);
        }
    }

    @Override
    public void trackSignupMagicLinkOpenEmailClientViewed() {
        if (!ListenerUtil.mutListener.listen(3337)) {
            AnalyticsTracker.track(AnalyticsTracker.Stat.LOGIN_MAGIC_LINK_OPEN_EMAIL_CLIENT_VIEWED);
        }
        if (!ListenerUtil.mutListener.listen(3338)) {
            mUnifiedLoginTracker.track(Flow.SIGNUP, Step.MAGIC_LINK_REQUESTED);
        }
    }

    @Override
    public void trackLoginMagicLinkOpenEmailClientViewed() {
        if (!ListenerUtil.mutListener.listen(3339)) {
            AnalyticsTracker.track(AnalyticsTracker.Stat.LOGIN_MAGIC_LINK_OPEN_EMAIL_CLIENT_VIEWED);
        }
        if (!ListenerUtil.mutListener.listen(3340)) {
            mUnifiedLoginTracker.track(Flow.LOGIN_MAGIC_LINK, Step.MAGIC_LINK_REQUESTED);
        }
    }

    @Override
    public void trackMagicLinkRequested() {
        if (!ListenerUtil.mutListener.listen(3341)) {
            AnalyticsTracker.track(AnalyticsTracker.Stat.LOGIN_MAGIC_LINK_REQUESTED);
        }
    }

    @Override
    public void trackMagicLinkRequestFormViewed() {
        if (!ListenerUtil.mutListener.listen(3342)) {
            AnalyticsTracker.track(AnalyticsTracker.Stat.LOGIN_MAGIC_LINK_REQUEST_FORM_VIEWED);
        }
        if (!ListenerUtil.mutListener.listen(3343)) {
            mUnifiedLoginTracker.track(Flow.LOGIN_MAGIC_LINK, Step.START);
        }
    }

    @Override
    public void trackPasswordFormViewed(boolean isSocialChallenge) {
        if (!ListenerUtil.mutListener.listen(3344)) {
            AnalyticsTracker.track(AnalyticsTracker.Stat.LOGIN_PASSWORD_FORM_VIEWED);
        }
        if (!ListenerUtil.mutListener.listen(3347)) {
            if (isSocialChallenge) {
                if (!ListenerUtil.mutListener.listen(3346)) {
                    mUnifiedLoginTracker.track(Flow.GOOGLE_LOGIN, Step.PASSWORD_CHALLENGE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(3345)) {
                    mUnifiedLoginTracker.track(Flow.LOGIN_PASSWORD, Step.START);
                }
            }
        }
    }

    @Override
    public void trackSignupCanceled() {
        if (!ListenerUtil.mutListener.listen(3348)) {
            AnalyticsTracker.track(AnalyticsTracker.Stat.SIGNUP_CANCELED);
        }
    }

    @Override
    public void trackSignupEmailButtonTapped() {
        if (!ListenerUtil.mutListener.listen(3349)) {
            AnalyticsTracker.track(AnalyticsTracker.Stat.SIGNUP_EMAIL_BUTTON_TAPPED);
        }
    }

    @Override
    public void trackSignupEmailToLogin() {
        if (!ListenerUtil.mutListener.listen(3350)) {
            AnalyticsTracker.track(AnalyticsTracker.Stat.SIGNUP_EMAIL_TO_LOGIN);
        }
    }

    @Override
    public void trackSignupGoogleButtonTapped() {
        if (!ListenerUtil.mutListener.listen(3351)) {
            AnalyticsTracker.track(AnalyticsTracker.Stat.SIGNUP_SOCIAL_BUTTON_TAPPED);
        }
    }

    @Override
    public void trackSignupMagicLinkFailed() {
        if (!ListenerUtil.mutListener.listen(3352)) {
            AnalyticsTracker.track(AnalyticsTracker.Stat.SIGNUP_MAGIC_LINK_FAILED);
        }
    }

    @Override
    public void trackSignupMagicLinkOpened() {
        if (!ListenerUtil.mutListener.listen(3353)) {
            AnalyticsTracker.track(AnalyticsTracker.Stat.SIGNUP_MAGIC_LINK_OPENED);
        }
    }

    @Override
    public void trackSignupMagicLinkOpenEmailClientClicked() {
        if (!ListenerUtil.mutListener.listen(3354)) {
            AnalyticsTracker.track(AnalyticsTracker.Stat.SIGNUP_MAGIC_LINK_OPEN_EMAIL_CLIENT_CLICKED);
        }
        if (!ListenerUtil.mutListener.listen(3355)) {
            mUnifiedLoginTracker.track(Flow.SIGNUP, Step.EMAIL_OPENED);
        }
    }

    @Override
    public void trackSignupMagicLinkSent() {
        if (!ListenerUtil.mutListener.listen(3356)) {
            AnalyticsTracker.track(AnalyticsTracker.Stat.SIGNUP_MAGIC_LINK_SENT);
        }
    }

    @Override
    public void trackSignupMagicLinkSucceeded() {
        if (!ListenerUtil.mutListener.listen(3357)) {
            AnalyticsTracker.track(AnalyticsTracker.Stat.SIGNUP_MAGIC_LINK_SUCCEEDED);
        }
    }

    @Override
    public void trackSignupSocialAccountsNeedConnecting() {
        if (!ListenerUtil.mutListener.listen(3358)) {
            AnalyticsTracker.track(AnalyticsTracker.Stat.SIGNUP_SOCIAL_ACCOUNTS_NEED_CONNECTING);
        }
    }

    @Override
    public void trackSignupSocialButtonFailure() {
        if (!ListenerUtil.mutListener.listen(3359)) {
            AnalyticsTracker.track(AnalyticsTracker.Stat.SIGNUP_SOCIAL_BUTTON_FAILURE);
        }
    }

    @Override
    public void trackSignupSocialToLogin() {
        if (!ListenerUtil.mutListener.listen(3360)) {
            AnalyticsTracker.track(AnalyticsTracker.Stat.SIGNUP_SOCIAL_TO_LOGIN);
        }
    }

    @Override
    public void trackSignupTermsOfServiceTapped() {
        if (!ListenerUtil.mutListener.listen(3361)) {
            AnalyticsTracker.track(AnalyticsTracker.Stat.SIGNUP_TERMS_OF_SERVICE_TAPPED);
        }
    }

    @Override
    public void trackSocialButtonStart() {
        if (!ListenerUtil.mutListener.listen(3362)) {
            mUnifiedLoginTracker.track(Flow.GOOGLE_LOGIN, Step.START);
        }
    }

    @Override
    public void trackSocialAccountsNeedConnecting() {
        if (!ListenerUtil.mutListener.listen(3363)) {
            AnalyticsTracker.track(AnalyticsTracker.Stat.LOGIN_SOCIAL_ACCOUNTS_NEED_CONNECTING);
        }
    }

    @Override
    public void trackSocialButtonClick() {
        if (!ListenerUtil.mutListener.listen(3364)) {
            AnalyticsTracker.track(AnalyticsTracker.Stat.LOGIN_SOCIAL_BUTTON_CLICK);
        }
        if (!ListenerUtil.mutListener.listen(3365)) {
            mUnifiedLoginTracker.trackClick(Click.LOGIN_WITH_GOOGLE);
        }
    }

    @Override
    public void trackSocialButtonFailure() {
        if (!ListenerUtil.mutListener.listen(3366)) {
            AnalyticsTracker.track(AnalyticsTracker.Stat.LOGIN_SOCIAL_BUTTON_FAILURE);
        }
    }

    @Override
    public void trackSocialConnectFailure() {
        if (!ListenerUtil.mutListener.listen(3367)) {
            AnalyticsTracker.track(AnalyticsTracker.Stat.LOGIN_SOCIAL_CONNECT_FAILURE);
        }
    }

    @Override
    public void trackSocialConnectSuccess() {
        if (!ListenerUtil.mutListener.listen(3368)) {
            AnalyticsTracker.track(AnalyticsTracker.Stat.LOGIN_SOCIAL_CONNECT_SUCCESS);
        }
    }

    @Override
    public void trackSocialErrorUnknownUser() {
        if (!ListenerUtil.mutListener.listen(3369)) {
            AnalyticsTracker.track(AnalyticsTracker.Stat.LOGIN_SOCIAL_ERROR_UNKNOWN_USER);
        }
    }

    @Override
    public void trackSocialFailure(String errorContext, String errorType, String errorDescription) {
        if (!ListenerUtil.mutListener.listen(3370)) {
            AnalyticsTracker.track(AnalyticsTracker.Stat.LOGIN_SOCIAL_FAILURE, errorContext, errorType, errorDescription);
        }
    }

    @Override
    public void trackTwoFactorFormViewed() {
        if (!ListenerUtil.mutListener.listen(3371)) {
            AnalyticsTracker.track(AnalyticsTracker.Stat.LOGIN_TWO_FACTOR_FORM_VIEWED);
        }
        if (!ListenerUtil.mutListener.listen(3372)) {
            mUnifiedLoginTracker.track(Step.TWO_FACTOR_AUTHENTICATION);
        }
    }

    @Override
    public void trackUrlFormViewed() {
        if (!ListenerUtil.mutListener.listen(3373)) {
            AnalyticsTracker.track(AnalyticsTracker.Stat.LOGIN_URL_FORM_VIEWED);
        }
        if (!ListenerUtil.mutListener.listen(3374)) {
            mUnifiedLoginTracker.track(Flow.LOGIN_SITE_ADDRESS, Step.START);
        }
    }

    @Override
    public void trackUrlHelpScreenViewed() {
        if (!ListenerUtil.mutListener.listen(3375)) {
            AnalyticsTracker.track(AnalyticsTracker.Stat.LOGIN_URL_HELP_SCREEN_VIEWED);
        }
    }

    @Override
    public void trackUsernamePasswordFormViewed() {
        if (!ListenerUtil.mutListener.listen(3376)) {
            AnalyticsTracker.track(AnalyticsTracker.Stat.LOGIN_USERNAME_PASSWORD_FORM_VIEWED);
        }
        if (!ListenerUtil.mutListener.listen(3377)) {
            mUnifiedLoginTracker.track(Step.USERNAME_PASSWORD);
        }
    }

    @Override
    public void trackWpComBackgroundServiceUpdate(Map<String, ?> properties) {
        if (!ListenerUtil.mutListener.listen(3378)) {
            AnalyticsTracker.track(AnalyticsTracker.Stat.LOGIN_WPCOM_BACKGROUND_SERVICE_UPDATE, properties);
        }
    }

    @Override
    public void trackConnectedSiteInfoRequested(String url) {
        Map<String, String> properties = new HashMap<>();
        if (!ListenerUtil.mutListener.listen(3379)) {
            properties.put("url", url);
        }
        if (!ListenerUtil.mutListener.listen(3380)) {
            AnalyticsTracker.track(AnalyticsTracker.Stat.LOGIN_CONNECTED_SITE_INFO_REQUESTED, properties);
        }
    }

    @Override
    public void trackConnectedSiteInfoFailed(String url, String errorContext, String errorType, String errorDescription) {
        if (!ListenerUtil.mutListener.listen(3381)) {
            AnalyticsTracker.track(AnalyticsTracker.Stat.LOGIN_CONNECTED_SITE_INFO_FAILED, errorContext, errorType, errorDescription);
        }
    }

    @Override
    public void trackConnectedSiteInfoSucceeded(@NotNull Map<String, ?> properties) {
        if (!ListenerUtil.mutListener.listen(3382)) {
            AnalyticsTracker.track(AnalyticsTracker.Stat.LOGIN_CONNECTED_SITE_INFO_SUCCEEDED, properties);
        }
    }

    @Override
    public void trackFailure(String message) {
        if (!ListenerUtil.mutListener.listen(3383)) {
            mUnifiedLoginTracker.trackFailure(message);
        }
    }

    @Override
    public void trackSendCodeWithTextClicked() {
        if (!ListenerUtil.mutListener.listen(3384)) {
            mUnifiedLoginTracker.trackClick(Click.SEND_CODE_WITH_TEXT);
        }
    }

    @Override
    public void trackSubmit2faCodeClicked() {
        if (!ListenerUtil.mutListener.listen(3385)) {
            mUnifiedLoginTracker.trackClick(Click.SUBMIT_2FA_CODE);
        }
    }

    @Override
    public void trackSubmitClicked() {
        if (!ListenerUtil.mutListener.listen(3386)) {
            mUnifiedLoginTracker.trackClick(Click.SUBMIT);
        }
    }

    @Override
    public void trackRequestMagicLinkClick() {
        if (!ListenerUtil.mutListener.listen(3387)) {
            mUnifiedLoginTracker.trackClick(Click.REQUEST_MAGIC_LINK);
        }
    }

    @Override
    public void trackLoginWithPasswordClick() {
        if (!ListenerUtil.mutListener.listen(3388)) {
            mUnifiedLoginTracker.trackClick(Click.LOGIN_WITH_PASSWORD);
        }
    }

    @Override
    public void trackShowHelpClick() {
        if (!ListenerUtil.mutListener.listen(3389)) {
            mUnifiedLoginTracker.trackClick(Click.SHOW_HELP);
        }
        if (!ListenerUtil.mutListener.listen(3390)) {
            mUnifiedLoginTracker.track(Step.HELP);
        }
    }

    @Override
    public void trackDismissDialog() {
        if (!ListenerUtil.mutListener.listen(3391)) {
            mUnifiedLoginTracker.trackClick(Click.DISMISS);
        }
    }

    @Override
    public void trackSelectEmailField() {
        if (!ListenerUtil.mutListener.listen(3392)) {
            mUnifiedLoginTracker.trackClick(Click.SELECT_EMAIL_FIELD);
        }
    }

    @Override
    public void trackPickEmailFromHint() {
        if (!ListenerUtil.mutListener.listen(3393)) {
            mUnifiedLoginTracker.trackClick(Click.PICK_EMAIL_FROM_HINT);
        }
    }

    @Override
    public void trackShowEmailHints() {
        if (!ListenerUtil.mutListener.listen(3394)) {
            mUnifiedLoginTracker.track(Step.SHOW_EMAIL_HINTS);
        }
    }

    @Override
    public void emailFormScreenResumed() {
        if (!ListenerUtil.mutListener.listen(3395)) {
            mUnifiedLoginTracker.setFlowAndStep(Flow.WORDPRESS_COM, Step.START);
        }
    }

    @Override
    public void trackSocialSignupConfirmationViewed() {
        if (!ListenerUtil.mutListener.listen(3396)) {
            mUnifiedLoginTracker.track(Flow.GOOGLE_SIGNUP, Step.START);
        }
    }

    @Override
    public void trackCreateAccountClick() {
        if (!ListenerUtil.mutListener.listen(3397)) {
            mUnifiedLoginTracker.trackClick(Click.CREATE_ACCOUNT);
        }
    }

    @Override
    public void emailPasswordFormScreenResumed() {
        if (!ListenerUtil.mutListener.listen(3398)) {
            mUnifiedLoginTracker.setStep(Step.START);
        }
    }

    @Override
    public void siteAddressFormScreenResumed() {
        if (!ListenerUtil.mutListener.listen(3399)) {
            mUnifiedLoginTracker.setStep(Step.START);
        }
    }

    @Override
    public void magicLinkRequestScreenResumed() {
        if (!ListenerUtil.mutListener.listen(3400)) {
            mUnifiedLoginTracker.setStep(Step.START);
        }
    }

    @Override
    public void magicLinkSentScreenResumed() {
        if (!ListenerUtil.mutListener.listen(3401)) {
            mUnifiedLoginTracker.setStep(Step.MAGIC_LINK_REQUESTED);
        }
    }

    @Override
    public void usernamePasswordScreenResumed() {
        if (!ListenerUtil.mutListener.listen(3402)) {
            mUnifiedLoginTracker.setStep(Step.USERNAME_PASSWORD);
        }
    }
}
