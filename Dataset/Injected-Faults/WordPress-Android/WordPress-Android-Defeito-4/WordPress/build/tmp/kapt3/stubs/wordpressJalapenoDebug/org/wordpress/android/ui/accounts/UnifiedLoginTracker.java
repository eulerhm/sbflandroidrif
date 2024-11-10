package org.wordpress.android.ui.accounts;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\b\b\u0007\u0018\u0000 !2\u00020\u0001:\u0005 !\"#$B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0014\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r0\fH\u0002J\b\u0010\u000e\u001a\u0004\u0018\u00010\u0006J\u0006\u0010\u000f\u001a\u00020\bJ\u0012\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\rH\u0002J\u0010\u0010\u0013\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\rJ\u0016\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0016\u001a\u00020\nJ\u000e\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\rJ\u000e\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0018\u001a\u00020\bJ\u000e\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\nJ\u001c\u0010\u001a\u001a\u00020\u00112\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0016\u001a\u00020\nH\u0007J\u000e\u0010\u001b\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u001dJ\u0010\u0010\u001e\u001a\u00020\u00112\b\u0010\u001f\u001a\u0004\u0018\u00010\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006%"}, d2 = {"Lorg/wordpress/android/ui/accounts/UnifiedLoginTracker;", "", "analyticsTracker", "Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;", "(Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;)V", "currentFlow", "Lorg/wordpress/android/ui/accounts/UnifiedLoginTracker$Flow;", "currentSource", "Lorg/wordpress/android/ui/accounts/UnifiedLoginTracker$Source;", "currentStep", "Lorg/wordpress/android/ui/accounts/UnifiedLoginTracker$Step;", "buildDefaultParams", "", "", "getFlow", "getSource", "handleMissingFlowOrStep", "", "value", "setFlow", "setFlowAndStep", "flow", "step", "setSource", "source", "setStep", "track", "trackClick", "click", "Lorg/wordpress/android/ui/accounts/UnifiedLoginTracker$Click;", "trackFailure", "error", "Click", "Companion", "Flow", "Source", "Step", "org.wordpress.android_wordpressJalapenoDebug"})
@javax.inject.Singleton()
public final class UnifiedLoginTracker {
    private final org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTracker = null;
    private org.wordpress.android.ui.accounts.UnifiedLoginTracker.Source currentSource = org.wordpress.android.ui.accounts.UnifiedLoginTracker.Source.DEFAULT;
    private org.wordpress.android.ui.accounts.UnifiedLoginTracker.Flow currentFlow;
    private org.wordpress.android.ui.accounts.UnifiedLoginTracker.Step currentStep;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.accounts.UnifiedLoginTracker.Companion Companion = null;
    private static final java.lang.String SOURCE = "source";
    private static final java.lang.String FLOW = "flow";
    private static final java.lang.String STEP = "step";
    private static final java.lang.String FAILURE = "failure";
    private static final java.lang.String CLICK = "click";
    
    @javax.inject.Inject()
    public UnifiedLoginTracker(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTracker) {
        super();
    }
    
    @kotlin.jvm.JvmOverloads()
    public final void track(@org.jetbrains.annotations.Nullable()
    org.wordpress.android.ui.accounts.UnifiedLoginTracker.Flow flow, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.accounts.UnifiedLoginTracker.Step step) {
    }
    
    @kotlin.jvm.JvmOverloads()
    public final void track(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.accounts.UnifiedLoginTracker.Step step) {
    }
    
    public final void trackFailure(@org.jetbrains.annotations.Nullable()
    java.lang.String error) {
    }
    
    public final void trackClick(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.accounts.UnifiedLoginTracker.Click click) {
    }
    
    private final java.util.Map<java.lang.String, java.lang.String> buildDefaultParams() {
        return null;
    }
    
    private final void handleMissingFlowOrStep(java.lang.String value) {
    }
    
    public final void setSource(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.accounts.UnifiedLoginTracker.Source source) {
    }
    
    public final void setSource(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    public final void setFlow(@org.jetbrains.annotations.Nullable()
    java.lang.String value) {
    }
    
    public final void setStep(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.accounts.UnifiedLoginTracker.Step step) {
    }
    
    public final void setFlowAndStep(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.accounts.UnifiedLoginTracker.Flow flow, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.accounts.UnifiedLoginTracker.Step step) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.accounts.UnifiedLoginTracker.Source getSource() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final org.wordpress.android.ui.accounts.UnifiedLoginTracker.Flow getFlow() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\r\u00a8\u0006\u000e"}, d2 = {"Lorg/wordpress/android/ui/accounts/UnifiedLoginTracker$Source;", "", "value", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "JETPACK", "SHARE", "DEEPLINK", "REAUTHENTICATION", "SELF_HOSTED", "ADD_WORDPRESS_COM_ACCOUNT", "DEFAULT", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum Source {
        /*public static final*/ JETPACK /* = new JETPACK(null) */,
        /*public static final*/ SHARE /* = new SHARE(null) */,
        /*public static final*/ DEEPLINK /* = new DEEPLINK(null) */,
        /*public static final*/ REAUTHENTICATION /* = new REAUTHENTICATION(null) */,
        /*public static final*/ SELF_HOSTED /* = new SELF_HOSTED(null) */,
        /*public static final*/ ADD_WORDPRESS_COM_ACCOUNT /* = new ADD_WORDPRESS_COM_ACCOUNT(null) */,
        /*public static final*/ DEFAULT /* = new DEFAULT(null) */;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String value = null;
        
        Source(java.lang.String value) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getValue() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000f\u00a8\u0006\u0010"}, d2 = {"Lorg/wordpress/android/ui/accounts/UnifiedLoginTracker$Flow;", "", "value", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "PROLOGUE", "WORDPRESS_COM", "GOOGLE_LOGIN", "SMART_LOCK_LOGIN", "LOGIN_MAGIC_LINK", "LOGIN_PASSWORD", "LOGIN_SITE_ADDRESS", "SIGNUP", "GOOGLE_SIGNUP", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum Flow {
        /*public static final*/ PROLOGUE /* = new PROLOGUE(null) */,
        /*public static final*/ WORDPRESS_COM /* = new WORDPRESS_COM(null) */,
        /*public static final*/ GOOGLE_LOGIN /* = new GOOGLE_LOGIN(null) */,
        /*public static final*/ SMART_LOCK_LOGIN /* = new SMART_LOCK_LOGIN(null) */,
        /*public static final*/ LOGIN_MAGIC_LINK /* = new LOGIN_MAGIC_LINK(null) */,
        /*public static final*/ LOGIN_PASSWORD /* = new LOGIN_PASSWORD(null) */,
        /*public static final*/ LOGIN_SITE_ADDRESS /* = new LOGIN_SITE_ADDRESS(null) */,
        /*public static final*/ SIGNUP /* = new SIGNUP(null) */,
        /*public static final*/ GOOGLE_SIGNUP /* = new GOOGLE_SIGNUP(null) */;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String value = null;
        
        Flow(java.lang.String value) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getValue() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012\u00a8\u0006\u0013"}, d2 = {"Lorg/wordpress/android/ui/accounts/UnifiedLoginTracker$Step;", "", "value", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "PROLOGUE", "START", "MAGIC_LINK_REQUESTED", "EMAIL_OPENED", "USERNAME_PASSWORD", "SUCCESS", "HELP", "TWO_FACTOR_AUTHENTICATION", "SHOW_EMAIL_HINTS", "PASSWORD_CHALLENGE", "NOT_A_JETPACK_SITE", "NO_JETPACK_SITES", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum Step {
        /*public static final*/ PROLOGUE /* = new PROLOGUE(null) */,
        /*public static final*/ START /* = new START(null) */,
        /*public static final*/ MAGIC_LINK_REQUESTED /* = new MAGIC_LINK_REQUESTED(null) */,
        /*public static final*/ EMAIL_OPENED /* = new EMAIL_OPENED(null) */,
        /*public static final*/ USERNAME_PASSWORD /* = new USERNAME_PASSWORD(null) */,
        /*public static final*/ SUCCESS /* = new SUCCESS(null) */,
        /*public static final*/ HELP /* = new HELP(null) */,
        /*public static final*/ TWO_FACTOR_AUTHENTICATION /* = new TWO_FACTOR_AUTHENTICATION(null) */,
        /*public static final*/ SHOW_EMAIL_HINTS /* = new SHOW_EMAIL_HINTS(null) */,
        /*public static final*/ PASSWORD_CHALLENGE /* = new PASSWORD_CHALLENGE(null) */,
        /*public static final*/ NOT_A_JETPACK_SITE /* = new NOT_A_JETPACK_SITE(null) */,
        /*public static final*/ NO_JETPACK_SITES /* = new NO_JETPACK_SITES(null) */;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String value = null;
        
        Step(java.lang.String value) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getValue() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u001e\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001dj\u0002\b\u001ej\u0002\b\u001fj\u0002\b \u00a8\u0006!"}, d2 = {"Lorg/wordpress/android/ui/accounts/UnifiedLoginTracker$Click;", "", "value", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "SUBMIT", "CONTINUE", "DISMISS", "CONTINUE_WITH_WORDPRESS_COM", "LOGIN_WITH_SITE_ADDRESS", "LOGIN_WITH_GOOGLE", "FORGOTTEN_PASSWORD", "TERMS_OF_SERVICE_CLICKED", "SIGNUP_WITH_EMAIL", "SIGNUP_WITH_GOOGLE", "OPEN_EMAIL_CLIENT", "SHOW_HELP", "SEND_CODE_WITH_TEXT", "SUBMIT_2FA_CODE", "REQUEST_MAGIC_LINK", "LOGIN_WITH_PASSWORD", "CREATE_NEW_SITE", "ADD_SELF_HOSTED_SITE", "CONNECT_SITE", "SELECT_AVATAR", "EDIT_USERNAME", "HELP_FINDING_SITE_ADDRESS", "SELECT_EMAIL_FIELD", "PICK_EMAIL_FROM_HINT", "CREATE_ACCOUNT", "CHOOSE_SITE", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum Click {
        /*public static final*/ SUBMIT /* = new SUBMIT(null) */,
        /*public static final*/ CONTINUE /* = new CONTINUE(null) */,
        /*public static final*/ DISMISS /* = new DISMISS(null) */,
        /*public static final*/ CONTINUE_WITH_WORDPRESS_COM /* = new CONTINUE_WITH_WORDPRESS_COM(null) */,
        /*public static final*/ LOGIN_WITH_SITE_ADDRESS /* = new LOGIN_WITH_SITE_ADDRESS(null) */,
        /*public static final*/ LOGIN_WITH_GOOGLE /* = new LOGIN_WITH_GOOGLE(null) */,
        /*public static final*/ FORGOTTEN_PASSWORD /* = new FORGOTTEN_PASSWORD(null) */,
        /*public static final*/ TERMS_OF_SERVICE_CLICKED /* = new TERMS_OF_SERVICE_CLICKED(null) */,
        /*public static final*/ SIGNUP_WITH_EMAIL /* = new SIGNUP_WITH_EMAIL(null) */,
        /*public static final*/ SIGNUP_WITH_GOOGLE /* = new SIGNUP_WITH_GOOGLE(null) */,
        /*public static final*/ OPEN_EMAIL_CLIENT /* = new OPEN_EMAIL_CLIENT(null) */,
        /*public static final*/ SHOW_HELP /* = new SHOW_HELP(null) */,
        /*public static final*/ SEND_CODE_WITH_TEXT /* = new SEND_CODE_WITH_TEXT(null) */,
        /*public static final*/ SUBMIT_2FA_CODE /* = new SUBMIT_2FA_CODE(null) */,
        /*public static final*/ REQUEST_MAGIC_LINK /* = new REQUEST_MAGIC_LINK(null) */,
        /*public static final*/ LOGIN_WITH_PASSWORD /* = new LOGIN_WITH_PASSWORD(null) */,
        /*public static final*/ CREATE_NEW_SITE /* = new CREATE_NEW_SITE(null) */,
        /*public static final*/ ADD_SELF_HOSTED_SITE /* = new ADD_SELF_HOSTED_SITE(null) */,
        /*public static final*/ CONNECT_SITE /* = new CONNECT_SITE(null) */,
        /*public static final*/ SELECT_AVATAR /* = new SELECT_AVATAR(null) */,
        /*public static final*/ EDIT_USERNAME /* = new EDIT_USERNAME(null) */,
        /*public static final*/ HELP_FINDING_SITE_ADDRESS /* = new HELP_FINDING_SITE_ADDRESS(null) */,
        /*public static final*/ SELECT_EMAIL_FIELD /* = new SELECT_EMAIL_FIELD(null) */,
        /*public static final*/ PICK_EMAIL_FROM_HINT /* = new PICK_EMAIL_FROM_HINT(null) */,
        /*public static final*/ CREATE_ACCOUNT /* = new CREATE_ACCOUNT(null) */,
        /*public static final*/ CHOOSE_SITE /* = new CHOOSE_SITE(null) */;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String value = null;
        
        Click(java.lang.String value) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getValue() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lorg/wordpress/android/ui/accounts/UnifiedLoginTracker$Companion;", "", "()V", "CLICK", "", "FAILURE", "FLOW", "SOURCE", "STEP", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}