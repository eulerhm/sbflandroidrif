package org.wordpress.android.workers.reminder.prompt;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u0000 @2\u00020\u0001:\u0001@BW\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u0015\u00a2\u0006\u0002\u0010\u0016J2\u0010)\u001a\n +*\u0004\u0018\u00010*0*2\u0006\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020/2\u0006\u00100\u001a\u00020/2\b\u00101\u001a\u0004\u0018\u000102H\u0002J(\u00103\u001a\n +*\u0004\u0018\u00010*0*2\u0006\u0010,\u001a\u00020-2\u0006\u00104\u001a\u00020/2\u0006\u00105\u001a\u000206H\u0002J(\u00107\u001a\n +*\u0004\u0018\u00010*0*2\u0006\u0010,\u001a\u00020-2\u0006\u00104\u001a\u00020/2\u0006\u00108\u001a\u000206H\u0002J2\u00109\u001a\n +*\u0004\u0018\u00010*0*2\u0006\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020/2\u0006\u00100\u001a\u00020/2\b\u00101\u001a\u0004\u0018\u000102H\u0002J\u0019\u0010:\u001a\u00020;2\u0006\u0010<\u001a\u00020/H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010=J\u0019\u0010>\u001a\u00020?2\u0006\u0010<\u001a\u00020/H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010=R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\f\u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u000e\u001a\u00020\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u0010\u001a\u00020\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010\u0012\u001a\u00020\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010(\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006A"}, d2 = {"Lorg/wordpress/android/workers/reminder/prompt/PromptReminderNotifier;", "", "contextProvider", "Lorg/wordpress/android/viewmodel/ContextProvider;", "resourceProvider", "Lorg/wordpress/android/viewmodel/ResourceProvider;", "siteStore", "Lorg/wordpress/android/fluxc/store/SiteStore;", "accountStore", "Lorg/wordpress/android/fluxc/store/AccountStore;", "reminderNotificationManager", "Lorg/wordpress/android/workers/reminder/ReminderNotificationManager;", "bloggingPromptsFeatureConfig", "Lorg/wordpress/android/util/config/BloggingPromptsFeatureConfig;", "bloggingPromptsStore", "Lorg/wordpress/android/fluxc/store/bloggingprompts/BloggingPromptsStore;", "bloggingRemindersAnalyticsTracker", "Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersAnalyticsTracker;", "htmlCompatWrapper", "Lorg/wordpress/android/util/HtmlCompatWrapper;", "bloggingRemindersStore", "Lorg/wordpress/android/fluxc/store/BloggingRemindersStore;", "(Lorg/wordpress/android/viewmodel/ContextProvider;Lorg/wordpress/android/viewmodel/ResourceProvider;Lorg/wordpress/android/fluxc/store/SiteStore;Lorg/wordpress/android/fluxc/store/AccountStore;Lorg/wordpress/android/workers/reminder/ReminderNotificationManager;Lorg/wordpress/android/util/config/BloggingPromptsFeatureConfig;Lorg/wordpress/android/fluxc/store/bloggingprompts/BloggingPromptsStore;Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersAnalyticsTracker;Lorg/wordpress/android/util/HtmlCompatWrapper;Lorg/wordpress/android/fluxc/store/BloggingRemindersStore;)V", "getAccountStore", "()Lorg/wordpress/android/fluxc/store/AccountStore;", "getBloggingPromptsFeatureConfig", "()Lorg/wordpress/android/util/config/BloggingPromptsFeatureConfig;", "getBloggingPromptsStore", "()Lorg/wordpress/android/fluxc/store/bloggingprompts/BloggingPromptsStore;", "getBloggingRemindersAnalyticsTracker", "()Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersAnalyticsTracker;", "getContextProvider", "()Lorg/wordpress/android/viewmodel/ContextProvider;", "getHtmlCompatWrapper", "()Lorg/wordpress/android/util/HtmlCompatWrapper;", "getReminderNotificationManager", "()Lorg/wordpress/android/workers/reminder/ReminderNotificationManager;", "getResourceProvider", "()Lorg/wordpress/android/viewmodel/ResourceProvider;", "getSiteStore", "()Lorg/wordpress/android/fluxc/store/SiteStore;", "createContentPendingIntent", "Landroid/app/PendingIntent;", "kotlin.jvm.PlatformType", "context", "Landroid/content/Context;", "openEditorRequestCode", "", "notificationId", "bloggingPrompt", "Lorg/wordpress/android/fluxc/model/bloggingprompts/BloggingPromptModel;", "createDismissNotificationButtonPendingIntent", "dismissNotificationRequestCode", "dismissButtonIntent", "Landroid/content/Intent;", "createDismissNotificationSwipePendingIntent", "dismissSwipeIntent", "createOpenEditorPendingIntent", "notify", "", "siteId", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "shouldNotify", "", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public final class PromptReminderNotifier {
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.viewmodel.ContextProvider contextProvider = null;
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.viewmodel.ResourceProvider resourceProvider = null;
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.fluxc.store.SiteStore siteStore = null;
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.fluxc.store.AccountStore accountStore = null;
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.workers.reminder.ReminderNotificationManager reminderNotificationManager = null;
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.util.config.BloggingPromptsFeatureConfig bloggingPromptsFeatureConfig = null;
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.fluxc.store.bloggingprompts.BloggingPromptsStore bloggingPromptsStore = null;
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.ui.bloggingreminders.BloggingRemindersAnalyticsTracker bloggingRemindersAnalyticsTracker = null;
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.util.HtmlCompatWrapper htmlCompatWrapper = null;
    private final org.wordpress.android.fluxc.store.BloggingRemindersStore bloggingRemindersStore = null;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.workers.reminder.prompt.PromptReminderNotifier.Companion Companion = null;
    public static final int NO_SITE_ID = -1;
    
    @javax.inject.Inject()
    public PromptReminderNotifier(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.ContextProvider contextProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.ResourceProvider resourceProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.SiteStore siteStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.AccountStore accountStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.workers.reminder.ReminderNotificationManager reminderNotificationManager, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.config.BloggingPromptsFeatureConfig bloggingPromptsFeatureConfig, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.bloggingprompts.BloggingPromptsStore bloggingPromptsStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.bloggingreminders.BloggingRemindersAnalyticsTracker bloggingRemindersAnalyticsTracker, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.HtmlCompatWrapper htmlCompatWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.BloggingRemindersStore bloggingRemindersStore) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.viewmodel.ContextProvider getContextProvider() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.viewmodel.ResourceProvider getResourceProvider() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.fluxc.store.SiteStore getSiteStore() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.fluxc.store.AccountStore getAccountStore() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.workers.reminder.ReminderNotificationManager getReminderNotificationManager() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.util.config.BloggingPromptsFeatureConfig getBloggingPromptsFeatureConfig() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.fluxc.store.bloggingprompts.BloggingPromptsStore getBloggingPromptsStore() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.bloggingreminders.BloggingRemindersAnalyticsTracker getBloggingRemindersAnalyticsTracker() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.util.HtmlCompatWrapper getHtmlCompatWrapper() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @kotlin.Suppress(names = {"MagicNumber"})
    public final java.lang.Object notify(int siteId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    private final android.app.PendingIntent createDismissNotificationButtonPendingIntent(android.content.Context context, int dismissNotificationRequestCode, android.content.Intent dismissButtonIntent) {
        return null;
    }
    
    private final android.app.PendingIntent createDismissNotificationSwipePendingIntent(android.content.Context context, int dismissNotificationRequestCode, android.content.Intent dismissSwipeIntent) {
        return null;
    }
    
    private final android.app.PendingIntent createOpenEditorPendingIntent(android.content.Context context, int openEditorRequestCode, int notificationId, org.wordpress.android.fluxc.model.bloggingprompts.BloggingPromptModel bloggingPrompt) {
        return null;
    }
    
    private final android.app.PendingIntent createContentPendingIntent(android.content.Context context, int openEditorRequestCode, int notificationId, org.wordpress.android.fluxc.model.bloggingprompts.BloggingPromptModel bloggingPrompt) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object shouldNotify(int siteId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> continuation) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lorg/wordpress/android/workers/reminder/prompt/PromptReminderNotifier$Companion;", "", "()V", "NO_SITE_ID", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}