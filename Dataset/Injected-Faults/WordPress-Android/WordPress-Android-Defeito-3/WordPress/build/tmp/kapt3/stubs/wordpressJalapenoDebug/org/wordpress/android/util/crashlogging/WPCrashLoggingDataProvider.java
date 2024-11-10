package org.wordpress.android.util.crashlogging;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 22\u00020\u0001:\u00012B?\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u00a2\u0006\u0002\u0010\u0010J \u0010!\u001a\u0012\u0012\b\u0012\u00060\u0012j\u0002`#\u0012\u0004\u0012\u00020\u00120\"2\u0006\u0010$\u001a\u00020%H\u0002J\u0014\u0010&\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00120\"H\u0016J\b\u0010\'\u001a\u00020\u0016H\u0016J\u0012\u0010(\u001a\f\u0012\b\u0012\u00060\u0012j\u0002`#0)H\u0016J8\u0010*\u001a\u0012\u0012\b\u0012\u00060\u0012j\u0002`#\u0012\u0004\u0012\u00020\u00120\"2\u0016\u0010+\u001a\u0012\u0012\b\u0012\u00060\u0012j\u0002`#\u0012\u0004\u0012\u00020\u00120\"2\u0006\u0010$\u001a\u00020%H\u0016J \u0010,\u001a\u00020\u00162\u0006\u0010-\u001a\u00020\u00122\u0006\u0010.\u001a\u00020\u00122\u0006\u0010/\u001a\u00020\u0012H\u0016J\n\u00100\u001a\u0004\u0018\u000101H\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\u00020\u0012X\u0096D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0015\u001a\u00020\u0016X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0019\u001a\u00020\u001a8VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u001d\u001a\u00020\u0012X\u0096D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u001f\u001a\u00020\u0012X\u0096D\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u0014R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u00063"}, d2 = {"Lorg/wordpress/android/util/crashlogging/WPCrashLoggingDataProvider;", "Lcom/automattic/android/tracks/crashlogging/CrashLoggingDataProvider;", "sharedPreferences", "Landroid/content/SharedPreferences;", "resourceProvider", "Lorg/wordpress/android/viewmodel/ResourceProvider;", "accountStore", "Lorg/wordpress/android/fluxc/store/AccountStore;", "localeManager", "Lorg/wordpress/android/util/LocaleManagerWrapper;", "encryptedLogging", "Lorg/wordpress/android/util/EncryptedLogging;", "logFileProvider", "Lorg/wordpress/android/util/LogFileProviderWrapper;", "buildConfig", "Lorg/wordpress/android/util/BuildConfigWrapper;", "(Landroid/content/SharedPreferences;Lorg/wordpress/android/viewmodel/ResourceProvider;Lorg/wordpress/android/fluxc/store/AccountStore;Lorg/wordpress/android/util/LocaleManagerWrapper;Lorg/wordpress/android/util/EncryptedLogging;Lorg/wordpress/android/util/LogFileProviderWrapper;Lorg/wordpress/android/util/BuildConfigWrapper;)V", "buildType", "", "getBuildType", "()Ljava/lang/String;", "enableCrashLoggingLogs", "", "getEnableCrashLoggingLogs", "()Z", "locale", "Ljava/util/Locale;", "getLocale", "()Ljava/util/Locale;", "releaseName", "getReleaseName", "sentryDSN", "getSentryDSN", "appendEncryptedLogsUuid", "", "Lcom/automattic/android/tracks/crashlogging/ExtraKnownKey;", "eventLevel", "Lcom/automattic/android/tracks/crashlogging/EventLevel;", "applicationContextProvider", "crashLoggingEnabled", "extraKnownKeys", "", "provideExtrasForEvent", "currentExtras", "shouldDropWrappingException", "module", "type", "value", "userProvider", "Lcom/automattic/android/tracks/crashlogging/CrashLoggingUser;", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public final class WPCrashLoggingDataProvider implements com.automattic.android.tracks.crashlogging.CrashLoggingDataProvider {
    private final android.content.SharedPreferences sharedPreferences = null;
    private final org.wordpress.android.viewmodel.ResourceProvider resourceProvider = null;
    private final org.wordpress.android.fluxc.store.AccountStore accountStore = null;
    private final org.wordpress.android.util.LocaleManagerWrapper localeManager = null;
    private final org.wordpress.android.util.EncryptedLogging encryptedLogging = null;
    private final org.wordpress.android.util.LogFileProviderWrapper logFileProvider = null;
    private final org.wordpress.android.util.BuildConfigWrapper buildConfig = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String buildType = "debug";
    private final boolean enableCrashLoggingLogs = false;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String releaseName = "alpha-372";
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String sentryDSN = "https://00000000000000000000000000000000@sentry.io/00000000";
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.util.crashlogging.WPCrashLoggingDataProvider.Companion Companion = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_UUID = "uuid";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EVENT_BUS_MODULE = "org.greenrobot.eventbus";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EVENT_BUS_EXCEPTION = "EventBusException";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EVENT_BUS_INVOKING_SUBSCRIBER_FAILED_ERROR = "Invoking subscriber failed";
    
    @javax.inject.Inject()
    public WPCrashLoggingDataProvider(@org.jetbrains.annotations.NotNull()
    android.content.SharedPreferences sharedPreferences, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.ResourceProvider resourceProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.AccountStore accountStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.LocaleManagerWrapper localeManager, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.EncryptedLogging encryptedLogging, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.LogFileProviderWrapper logFileProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.BuildConfigWrapper buildConfig) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String getBuildType() {
        return null;
    }
    
    @java.lang.Override()
    public boolean getEnableCrashLoggingLogs() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.util.Locale getLocale() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String getReleaseName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String getSentryDSN() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.util.Map<java.lang.String, java.lang.String> applicationContextProvider() {
        return null;
    }
    
    @java.lang.Override()
    public boolean crashLoggingEnabled() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.util.List<java.lang.String> extraKnownKeys() {
        return null;
    }
    
    /**
     * If Sentry is unable to upload the event in its first attempt, it'll call the `setBeforeSend` callback
     * before trying to send it again. This can be easily reproduced by turning off network connectivity
     * and re-launching the app over and over again which will hit this callback each time.
     *
     * The problem with that is it'll keep queuing more and more logs to be uploaded to MC and more
     * importantly, it'll set the `uuid` of the Sentry event to the log file at the time of the successful
     * Sentry request. Since we are interested in the logs for when the crash happened, this would not be
     * correct for us.
     *
     * We can simply fix this issue by checking if the [EXTRA_UUID] field is already set.
     */
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.util.Map<java.lang.String, java.lang.String> provideExtrasForEvent(@org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, java.lang.String> currentExtras, @org.jetbrains.annotations.NotNull()
    com.automattic.android.tracks.crashlogging.EventLevel eventLevel) {
        return null;
    }
    
    private final java.util.Map<java.lang.String, java.lang.String> appendEncryptedLogsUuid(com.automattic.android.tracks.crashlogging.EventLevel eventLevel) {
        return null;
    }
    
    @java.lang.Override()
    public boolean shouldDropWrappingException(@org.jetbrains.annotations.NotNull()
    java.lang.String module, @org.jetbrains.annotations.NotNull()
    java.lang.String type, @org.jetbrains.annotations.NotNull()
    java.lang.String value) {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public com.automattic.android.tracks.crashlogging.CrashLoggingUser userProvider() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lorg/wordpress/android/util/crashlogging/WPCrashLoggingDataProvider$Companion;", "", "()V", "EVENT_BUS_EXCEPTION", "", "EVENT_BUS_INVOKING_SUBSCRIBER_FAILED_ERROR", "EVENT_BUS_MODULE", "EXTRA_UUID", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}