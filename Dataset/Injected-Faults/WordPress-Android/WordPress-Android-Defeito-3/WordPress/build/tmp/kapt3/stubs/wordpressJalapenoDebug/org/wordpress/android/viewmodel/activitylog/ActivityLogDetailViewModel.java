package org.wordpress.android.viewmodel.activitylog;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\r\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u0018\u0010=\u001a\u00020\u00152\u0006\u0010>\u001a\u00020?2\u0006\u0010@\u001a\u00020\u001bH\u0002J\b\u0010A\u001a\u00020\u0015H\u0002J\u000e\u0010B\u001a\u00020C2\u0006\u0010D\u001a\u00020\u0012J\u000e\u0010E\u001a\u00020C2\u0006\u0010F\u001a\u00020\u0010J\u000e\u0010G\u001a\u00020C2\u0006\u0010D\u001a\u00020\u0012J&\u0010H\u001a\u00020C2\u0006\u00107\u001a\u0002082\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010$\u001a\u00020\r2\u0006\u0010/\u001a\u00020\rJ\f\u0010I\u001a\u00020\r*\u00020JH\u0002R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\"\u0010\u0013\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\r\u0012\u0006\u0012\u0004\u0018\u00010\u00150\u00140\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u00170\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u001a\u001a\u00020\u001bX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u0017\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00120!8F\u00a2\u0006\u0006\u001a\u0004\b\"\u0010#R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010$\u001a\u00020\rX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b\'\u0010(R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u0017\u0010+\u001a\b\u0012\u0004\u0012\u00020\r0!8F\u00a2\u0006\u0006\u001a\u0004\b,\u0010#R\u0017\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00100!8F\u00a2\u0006\u0006\u001a\u0004\b.\u0010#R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010/\u001a\u00020\rX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b/\u0010&\"\u0004\b0\u0010(R%\u00101\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\r\u0012\u0006\u0012\u0004\u0018\u00010\u00150\u00140!8F\u00a2\u0006\u0006\u001a\u0004\b2\u0010#R\u001d\u00103\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u00170!8F\u00a2\u0006\u0006\u001a\u0004\b4\u0010#R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u00105\u001a\b\u0012\u0004\u0012\u00020\r0!8F\u00a2\u0006\u0006\u001a\u0004\b6\u0010#R\u001a\u00107\u001a\u000208X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b9\u0010:\"\u0004\b;\u0010<\u00a8\u0006K"}, d2 = {"Lorg/wordpress/android/viewmodel/activitylog/ActivityLogDetailViewModel;", "Landroidx/lifecycle/ViewModel;", "dispatcher", "Lorg/wordpress/android/fluxc/Dispatcher;", "activityLogStore", "Lorg/wordpress/android/fluxc/store/ActivityLogStore;", "resourceProvider", "Lorg/wordpress/android/viewmodel/ResourceProvider;", "htmlMessageUtils", "Lorg/wordpress/android/ui/utils/HtmlMessageUtils;", "(Lorg/wordpress/android/fluxc/Dispatcher;Lorg/wordpress/android/fluxc/store/ActivityLogStore;Lorg/wordpress/android/viewmodel/ResourceProvider;Lorg/wordpress/android/ui/utils/HtmlMessageUtils;)V", "_downloadBackupVisible", "Landroidx/lifecycle/MutableLiveData;", "", "_handleFormattableRangeClick", "Lorg/wordpress/android/viewmodel/SingleLiveEvent;", "Lorg/wordpress/android/fluxc/tools/FormattableRange;", "_item", "Lorg/wordpress/android/ui/activitylog/detail/ActivityLogDetailModel;", "_multisiteVisible", "Lkotlin/Pair;", "Landroid/text/SpannableString;", "_navigationEvents", "Lorg/wordpress/android/viewmodel/Event;", "Lorg/wordpress/android/ui/activitylog/detail/ActivityLogDetailNavigationEvents;", "_restoreVisible", "activityLogId", "", "getActivityLogId", "()Ljava/lang/String;", "setActivityLogId", "(Ljava/lang/String;)V", "activityLogItem", "Landroidx/lifecycle/LiveData;", "getActivityLogItem", "()Landroidx/lifecycle/LiveData;", "areButtonsVisible", "getAreButtonsVisible", "()Z", "setAreButtonsVisible", "(Z)V", "getDispatcher", "()Lorg/wordpress/android/fluxc/Dispatcher;", "downloadBackupVisible", "getDownloadBackupVisible", "handleFormattableRangeClick", "getHandleFormattableRangeClick", "isRestoreHidden", "setRestoreHidden", "multisiteVisible", "getMultisiteVisible", "navigationEvents", "getNavigationEvents", "restoreVisible", "getRestoreVisible", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "getSite", "()Lorg/wordpress/android/fluxc/model/SiteModel;", "setSite", "(Lorg/wordpress/android/fluxc/model/SiteModel;)V", "constructMultisiteSpan", "multisiteMessage", "", "clickableText", "getMultisiteMessage", "onDownloadBackupClicked", "", "model", "onRangeClicked", "range", "onRestoreClicked", "start", "showJetpackIcon", "Lorg/wordpress/android/fluxc/model/activity/ActivityLogModel$ActivityActor;", "org.wordpress.android_wordpressJalapenoDebug"})
public final class ActivityLogDetailViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.fluxc.Dispatcher dispatcher = null;
    private final org.wordpress.android.fluxc.store.ActivityLogStore activityLogStore = null;
    private final org.wordpress.android.viewmodel.ResourceProvider resourceProvider = null;
    private final org.wordpress.android.ui.utils.HtmlMessageUtils htmlMessageUtils = null;
    public org.wordpress.android.fluxc.model.SiteModel site;
    public java.lang.String activityLogId;
    private boolean areButtonsVisible = false;
    private boolean isRestoreHidden = false;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.activitylog.detail.ActivityLogDetailNavigationEvents>> _navigationEvents = null;
    private final org.wordpress.android.viewmodel.SingleLiveEvent<org.wordpress.android.fluxc.tools.FormattableRange> _handleFormattableRangeClick = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.ui.activitylog.detail.ActivityLogDetailModel> _item = null;
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> _restoreVisible = null;
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> _downloadBackupVisible = null;
    private final androidx.lifecycle.MutableLiveData<kotlin.Pair<java.lang.Boolean, android.text.SpannableString>> _multisiteVisible = null;
    
    @javax.inject.Inject()
    public ActivityLogDetailViewModel(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.Dispatcher dispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.ActivityLogStore activityLogStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.ResourceProvider resourceProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.utils.HtmlMessageUtils htmlMessageUtils) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.fluxc.Dispatcher getDispatcher() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.fluxc.model.SiteModel getSite() {
        return null;
    }
    
    public final void setSite(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getActivityLogId() {
        return null;
    }
    
    public final void setActivityLogId(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    public final boolean getAreButtonsVisible() {
        return false;
    }
    
    public final void setAreButtonsVisible(boolean p0) {
    }
    
    public final boolean isRestoreHidden() {
        return false;
    }
    
    public final void setRestoreHidden(boolean p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.activitylog.detail.ActivityLogDetailNavigationEvents>> getNavigationEvents() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.fluxc.tools.FormattableRange> getHandleFormattableRangeClick() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.ui.activitylog.detail.ActivityLogDetailModel> getActivityLogItem() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Boolean> getRestoreVisible() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Boolean> getDownloadBackupVisible() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<kotlin.Pair<java.lang.Boolean, android.text.SpannableString>> getMultisiteVisible() {
        return null;
    }
    
    public final void start(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
    java.lang.String activityLogId, boolean areButtonsVisible, boolean isRestoreHidden) {
    }
    
    private final android.text.SpannableString getMultisiteMessage() {
        return null;
    }
    
    private final android.text.SpannableString constructMultisiteSpan(java.lang.CharSequence multisiteMessage, java.lang.String clickableText) {
        return null;
    }
    
    public final void onRangeClicked(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.tools.FormattableRange range) {
    }
    
    public final void onRestoreClicked(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.activitylog.detail.ActivityLogDetailModel model) {
    }
    
    public final void onDownloadBackupClicked(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.activitylog.detail.ActivityLogDetailModel model) {
    }
    
    private final boolean showJetpackIcon(org.wordpress.android.fluxc.model.activity.ActivityLogModel.ActivityActor $this$showJetpackIcon) {
        return false;
    }
}