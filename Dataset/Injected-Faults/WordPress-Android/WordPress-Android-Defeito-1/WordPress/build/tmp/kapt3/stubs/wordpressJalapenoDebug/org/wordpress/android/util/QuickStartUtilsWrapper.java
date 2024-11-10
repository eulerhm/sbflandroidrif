package org.wordpress.android.util;

import java.lang.System;

@kotlin.Suppress(names = {"TooManyFunctions"})
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J6\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0007J\u0018\u0010\u0013\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u0015J\u000e\u0010\u0016\u001a\u00020\u00172\u0006\u0010\t\u001a\u00020\nJ\u0016\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0014\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cJ\u000e\u0010\u001d\u001a\u00020\u00192\u0006\u0010\u001e\u001a\u00020\fJ(\u0010\u001f\u001a\u00020\u00192\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J&\u0010 \u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\u001a2\u0006\u0010!\u001a\u00020\u00192\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\"\u001a\u00020#J\"\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u00102\u0006\u0010\'\u001a\u00020\u001a2\b\b\u0002\u0010(\u001a\u00020\u001aH\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006)"}, d2 = {"Lorg/wordpress/android/util/QuickStartUtilsWrapper;", "", "quickStartStore", "Lorg/wordpress/android/fluxc/store/QuickStartStore;", "dispatcher", "Lorg/wordpress/android/fluxc/Dispatcher;", "(Lorg/wordpress/android/fluxc/store/QuickStartStore;Lorg/wordpress/android/fluxc/Dispatcher;)V", "completeTaskAndRemindNextOne", "", "task", "Lorg/wordpress/android/fluxc/store/QuickStartStore$QuickStartTask;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "quickStartEvent", "Lorg/wordpress/android/ui/quickstart/QuickStartEvent;", "context", "Landroid/content/Context;", "quickStartType", "Lorg/wordpress/android/ui/quickstart/QuickStartType;", "getNextUncompletedQuickStartTask", "siteLocalId", "", "getTaskCompletedTracker", "Lorg/wordpress/android/analytics/AnalyticsTracker$Stat;", "isEveryQuickStartTaskDoneForType", "", "", "type", "Lorg/wordpress/android/fluxc/store/QuickStartStore$QuickStartTaskType;", "isQuickStartAvailableForTheSite", "siteModel", "shouldCancelCompleteAction", "startQuickStart", "isSiteTitleTaskCompleted", "quickStartTracker", "Lorg/wordpress/android/ui/quickstart/QuickStartTracker;", "stylizeQuickStartPrompt", "Landroid/text/Spannable;", "activityContext", "messageId", "iconId", "org.wordpress.android_wordpressJalapenoDebug"})
public final class QuickStartUtilsWrapper {
    private final org.wordpress.android.fluxc.store.QuickStartStore quickStartStore = null;
    private final org.wordpress.android.fluxc.Dispatcher dispatcher = null;
    
    @javax.inject.Inject()
    public QuickStartUtilsWrapper(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.QuickStartStore quickStartStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.Dispatcher dispatcher) {
        super();
    }
    
    public final boolean isQuickStartAvailableForTheSite(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel siteModel) {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    @kotlin.jvm.JvmOverloads()
    public final android.text.Spannable stylizeQuickStartPrompt(@org.jetbrains.annotations.NotNull()
    android.content.Context activityContext, int messageId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @kotlin.jvm.JvmOverloads()
    public final android.text.Spannable stylizeQuickStartPrompt(@org.jetbrains.annotations.NotNull()
    android.content.Context activityContext, int messageId, int iconId) {
        return null;
    }
    
    public final boolean isEveryQuickStartTaskDoneForType(int siteLocalId, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTaskType type) {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.analytics.AnalyticsTracker.Stat getTaskCompletedTracker(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask task) {
        return null;
    }
    
    @kotlin.jvm.JvmOverloads()
    public final void completeTaskAndRemindNextOne(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask task, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.Nullable()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.quickstart.QuickStartType quickStartType) {
    }
    
    @kotlin.jvm.JvmOverloads()
    public final void completeTaskAndRemindNextOne(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask task, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.ui.quickstart.QuickStartEvent quickStartEvent, @org.jetbrains.annotations.Nullable()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.quickstart.QuickStartType quickStartType) {
    }
    
    private final boolean shouldCancelCompleteAction(long siteLocalId, org.wordpress.android.fluxc.model.SiteModel site, org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask task, org.wordpress.android.ui.quickstart.QuickStartType quickStartType) {
        return false;
    }
    
    public final void startQuickStart(int siteLocalId, boolean isSiteTitleTaskCompleted, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.quickstart.QuickStartType quickStartType, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.quickstart.QuickStartTracker quickStartTracker) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask getNextUncompletedQuickStartTask(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.quickstart.QuickStartType quickStartType, long siteLocalId) {
        return null;
    }
}