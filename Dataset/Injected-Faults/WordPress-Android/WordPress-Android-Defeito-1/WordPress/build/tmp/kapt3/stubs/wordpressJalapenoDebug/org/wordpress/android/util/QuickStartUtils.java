package org.wordpress.android.util;

import java.lang.System;

@kotlin.Suppress(names = {"TooManyFunctions"})
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J(\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0004H\u0007J\u0010\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u0013H\u0007J\"\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0006H\u0007J*\u0010\u001b\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0018\u001a\u00020\u0019H\u0007J\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u0015H\u0007J\u0010\u0010!\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u0015H\u0007J\u000e\u0010\"\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u0015J\u0010\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&H\u0007J\u0010\u0010\'\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0007J\u0016\u0010(\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010)\u001a\u00020\u0015J\"\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020\u00132\u0006\u0010-\u001a\u00020\u00042\b\b\u0002\u0010.\u001a\u00020\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006/"}, d2 = {"Lorg/wordpress/android/util/QuickStartUtils;", "", "()V", "ICON_NOT_SET", "", "QUICK_START_REMINDER_INTERVAL", "", "themeBrowserUtils", "Lorg/wordpress/android/ui/themes/ThemeBrowserUtils;", "addQuickStartFocusPointAboveTheView", "", "topLevelParent", "Landroid/view/ViewGroup;", "targetedView", "Landroid/view/View;", "rightOffset", "topOffset", "cancelQuickStartReminder", "context", "Landroid/content/Context;", "getNextUncompletedQuickStartTask", "Lorg/wordpress/android/fluxc/store/QuickStartStore$QuickStartTask;", "quickStartStore", "Lorg/wordpress/android/fluxc/store/QuickStartStore;", "quickStartType", "Lorg/wordpress/android/ui/quickstart/QuickStartType;", "siteLocalId", "getNextUncompletedQuickStartTaskForReminderNotification", "taskType", "Lorg/wordpress/android/fluxc/store/QuickStartStore$QuickStartTaskType;", "getQuickStartListSkippedTracker", "Lorg/wordpress/android/analytics/AnalyticsTracker$Stat;", "task", "getQuickStartListTappedTracker", "getTaskCompletedTracker", "isQuickStartAvailableForTheSite", "", "siteModel", "Lorg/wordpress/android/fluxc/model/SiteModel;", "removeQuickStartFocusPoint", "startQuickStartReminderTimer", "quickStartTask", "stylizeQuickStartPrompt", "Landroid/text/Spannable;", "activityContext", "messageId", "iconId", "org.wordpress.android_wordpressJalapenoDebug"})
public final class QuickStartUtils {
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.util.QuickStartUtils INSTANCE = null;
    private static final org.wordpress.android.ui.themes.ThemeBrowserUtils themeBrowserUtils = null;
    private static final long QUICK_START_REMINDER_INTERVAL = 172800000L;
    public static final int ICON_NOT_SET = -1;
    
    private QuickStartUtils() {
        super();
    }
    
    /**
     * Formats the string, to highlight text between %1$s and %2$s with specified color, and add an icon
     * in front of it if necessary
     *
     * @param context Context used to access resources
     * @param messageId resources id of the message to display. If string contains basic HTML tags inside
     * <![CDATA[ ]]>, they will be converted to Spans.
     * @param iconId resource if of the icon that goes before the highlighted area
     */
    @org.jetbrains.annotations.NotNull()
    @kotlin.jvm.JvmOverloads()
    @kotlin.jvm.JvmStatic()
    public static final android.text.Spannable stylizeQuickStartPrompt(@org.jetbrains.annotations.NotNull()
    android.content.Context activityContext, int messageId) {
        return null;
    }
    
    /**
     * Formats the string, to highlight text between %1$s and %2$s with specified color, and add an icon
     * in front of it if necessary
     *
     * @param context Context used to access resources
     * @param messageId resources id of the message to display. If string contains basic HTML tags inside
     * <![CDATA[ ]]>, they will be converted to Spans.
     * @param iconId resource if of the icon that goes before the highlighted area
     */
    @org.jetbrains.annotations.NotNull()
    @kotlin.jvm.JvmOverloads()
    @kotlin.jvm.JvmStatic()
    public static final android.text.Spannable stylizeQuickStartPrompt(@org.jetbrains.annotations.NotNull()
    android.content.Context activityContext, int messageId, int iconId) {
        return null;
    }
    
    /**
     * Adds animated quick start focus point targetedView to the top level parent,
     * and places it in the top-right corner of the specified targetedView.
     *
     * @param topLevelParent Parent where quick start focus targetedView will be added.
     * Usually Relative or Frame layout
     * @param targetedView View in top-right corner of which the quick start focus view will be placed. Child of
     * topLevelParent
     * @param rightOffset specifies in px how much should we move view to the left from the right
     * @param topOffset specifies in px how much should we move view to the bottom from the top
     */
    @kotlin.jvm.JvmStatic()
    public static final void addQuickStartFocusPointAboveTheView(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup topLevelParent, @org.jetbrains.annotations.NotNull()
    android.view.View targetedView, int rightOffset, int topOffset) {
    }
    
    @kotlin.jvm.JvmStatic()
    public static final void removeQuickStartFocusPoint(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup topLevelParent) {
    }
    
    @kotlin.jvm.JvmStatic()
    public static final boolean isQuickStartAvailableForTheSite(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel siteModel) {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    @kotlin.Suppress(names = {"ComplexMethod"})
    @kotlin.jvm.JvmStatic()
    public static final org.wordpress.android.analytics.AnalyticsTracker.Stat getQuickStartListTappedTracker(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask task) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @kotlin.Suppress(names = {"ComplexMethod"})
    @kotlin.jvm.JvmStatic()
    public static final org.wordpress.android.analytics.AnalyticsTracker.Stat getQuickStartListSkippedTracker(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask task) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @kotlin.Suppress(names = {"ComplexMethod"})
    public final org.wordpress.android.analytics.AnalyticsTracker.Stat getTaskCompletedTracker(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask task) {
        return null;
    }
    
    public final void startQuickStartReminderTimer(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask quickStartTask) {
    }
    
    @kotlin.jvm.JvmStatic()
    public static final void cancelQuickStartReminder(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    /**
     * This method tries to return the next uncompleted task of taskType
     * if no uncompleted task of taskType remain it tries to find and return uncompleted task of other task type
     */
    @org.jetbrains.annotations.Nullable()
    @kotlin.jvm.JvmStatic()
    public static final org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask getNextUncompletedQuickStartTaskForReminderNotification(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.QuickStartStore quickStartStore, long siteLocalId, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTaskType taskType, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.quickstart.QuickStartType quickStartType) {
        return null;
    }
    
    /**
     * This method tries to return the next uncompleted task from complete tasks pool
     */
    @org.jetbrains.annotations.Nullable()
    @kotlin.Suppress(names = {"ReturnCount"})
    @kotlin.jvm.JvmStatic()
    public static final org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask getNextUncompletedQuickStartTask(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.QuickStartStore quickStartStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.quickstart.QuickStartType quickStartType, long siteLocalId) {
        return null;
    }
}