package org.wordpress.android.ui.posts;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u008c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0018\b&\u0018\u00002\u00020\u0001:\u0003bcdB-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0002\u0010\fJ\u0010\u0010H\u001a\u00020!2\u0006\u0010I\u001a\u00020JH\u0002J\u0010\u0010K\u001a\u00020\u001a2\u0006\u0010L\u001a\u00020MH\u0002J\u000e\u00102\u001a\u00020\u00122\u0006\u0010L\u001a\u00020MJ\u001e\u0010N\u001a\u00020\u00122\u0006\u0010F\u001a\u00020\'2\u0006\u00100\u001a\u00020\'2\u0006\u0010O\u001a\u00020\'J\u0010\u0010P\u001a\u00020\u00122\b\u0010Q\u001a\u0004\u0018\u00010\u0016J\u0010\u0010<\u001a\u00020\u00122\b\u0010I\u001a\u0004\u0018\u00010JJ\u000e\u0010R\u001a\u00020\u00122\u0006\u0010I\u001a\u00020JJ\u0016\u0010S\u001a\u00020\u00122\u0006\u0010T\u001a\u00020\'2\u0006\u0010U\u001a\u00020\'J\u0006\u0010V\u001a\u00020\u0012J\u0016\u0010W\u001a\u00020\u00122\u0006\u0010L\u001a\u00020M2\u0006\u0010X\u001a\u00020\u0016J\u0012\u0010Y\u001a\u00020\u00122\b\u0010L\u001a\u0004\u0018\u00010MH\u0016J\u0010\u0010Z\u001a\u00020\u00122\u0006\u0010[\u001a\u00020\u001aH\u0002J\u001a\u0010\\\u001a\u00020\u00122\u0006\u0010L\u001a\u00020M2\b\b\u0002\u0010]\u001a\u00020\u0016H\u0002J\u0018\u0010^\u001a\u00020\u00122\u0006\u0010_\u001a\u00020\u001a2\b\u0010L\u001a\u0004\u0018\u00010MJ\u0010\u0010`\u001a\u00020\u00122\b\u0010I\u001a\u0004\u0018\u00010JJ\f\u0010a\u001a\u00020\'*\u00020\u0016H\u0002R\u001a\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u000f0\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u000f0\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0015\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00160\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\u000f0\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u001b\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00160\u000f0\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001d0\u000f0\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010 \u001a\u00020!X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R$\u0010(\u001a\u0004\u0018\u00010\'2\b\u0010&\u001a\u0004\u0018\u00010\'@BX\u0086\u000e\u00a2\u0006\n\n\u0002\u0010+\u001a\u0004\b)\u0010*R$\u0010,\u001a\u0004\u0018\u00010\'2\b\u0010&\u001a\u0004\u0018\u00010\'@BX\u0086\u000e\u00a2\u0006\n\n\u0002\u0010+\u001a\u0004\b-\u0010*R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R$\u0010.\u001a\u0004\u0018\u00010\'2\b\u0010&\u001a\u0004\u0018\u00010\'@BX\u0086\u000e\u00a2\u0006\n\n\u0002\u0010+\u001a\u0004\b/\u0010*R$\u00100\u001a\u0004\u0018\u00010\'2\b\u0010&\u001a\u0004\u0018\u00010\'@BX\u0086\u000e\u00a2\u0006\n\n\u0002\u0010+\u001a\u0004\b1\u0010*R\u001d\u00102\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f03\u00a2\u0006\b\n\u0000\u001a\u0004\b4\u00105R\u001d\u00106\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u000f03\u00a2\u0006\b\n\u0000\u001a\u0004\b7\u00105R\u001d\u00108\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u000f03\u00a2\u0006\b\n\u0000\u001a\u0004\b9\u00105R\u0019\u0010:\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001603\u00a2\u0006\b\n\u0000\u001a\u0004\b;\u00105R\u0017\u0010<\u001a\b\u0012\u0004\u0012\u00020\u001803\u00a2\u0006\b\n\u0000\u001a\u0004\b=\u00105R\u001d\u0010>\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\u000f03\u00a2\u0006\b\n\u0000\u001a\u0004\b?\u00105R\u001f\u0010@\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00160\u000f03\u00a2\u0006\b\n\u0000\u001a\u0004\bA\u00105R\u001d\u0010B\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001d0\u000f03\u00a2\u0006\b\n\u0000\u001a\u0004\bC\u00105R\u0017\u0010D\u001a\b\u0012\u0004\u0012\u00020\u001f03\u00a2\u0006\b\n\u0000\u001a\u0004\bE\u00105R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R$\u0010F\u001a\u0004\u0018\u00010\'2\b\u0010&\u001a\u0004\u0018\u00010\'@BX\u0086\u000e\u00a2\u0006\n\n\u0002\u0010+\u001a\u0004\bG\u0010*\u00a8\u0006e"}, d2 = {"Lorg/wordpress/android/ui/posts/PublishSettingsViewModel;", "Landroidx/lifecycle/ViewModel;", "resourceProvider", "Lorg/wordpress/android/viewmodel/ResourceProvider;", "postSettingsUtils", "Lorg/wordpress/android/ui/posts/PostSettingsUtils;", "localeManagerWrapper", "Lorg/wordpress/android/util/LocaleManagerWrapper;", "postSchedulingNotificationStore", "Lorg/wordpress/android/fluxc/store/PostSchedulingNotificationStore;", "siteStore", "Lorg/wordpress/android/fluxc/store/SiteStore;", "(Lorg/wordpress/android/viewmodel/ResourceProvider;Lorg/wordpress/android/ui/posts/PostSettingsUtils;Lorg/wordpress/android/util/LocaleManagerWrapper;Lorg/wordpress/android/fluxc/store/PostSchedulingNotificationStore;Lorg/wordpress/android/fluxc/store/SiteStore;)V", "_onAddToCalendar", "Landroidx/lifecycle/MutableLiveData;", "Lorg/wordpress/android/viewmodel/Event;", "Lorg/wordpress/android/ui/posts/PublishSettingsViewModel$CalendarEvent;", "_onDatePicked", "", "_onNotificationAdded", "Lorg/wordpress/android/ui/posts/PublishSettingsViewModel$Notification;", "_onNotificationTime", "Lorg/wordpress/android/fluxc/store/PostSchedulingNotificationStore$SchedulingReminderModel$Period;", "_onPostStatusChanged", "Lorg/wordpress/android/fluxc/model/post/PostStatus;", "_onPublishedDateChanged", "Ljava/util/Calendar;", "_onShowNotificationDialog", "_onToast", "", "_onUiModel", "Lorg/wordpress/android/ui/posts/PublishSettingsViewModel$PublishUiModel;", "canPublishImmediately", "", "getCanPublishImmediately", "()Z", "setCanPublishImmediately", "(Z)V", "<set-?>", "", "day", "getDay", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "hour", "getHour", "minute", "getMinute", "month", "getMonth", "onAddToCalendar", "Landroidx/lifecycle/LiveData;", "getOnAddToCalendar", "()Landroidx/lifecycle/LiveData;", "onDatePicked", "getOnDatePicked", "onNotificationAdded", "getOnNotificationAdded", "onNotificationTime", "getOnNotificationTime", "onPostStatusChanged", "getOnPostStatusChanged", "onPublishedDateChanged", "getOnPublishedDateChanged", "onShowNotificationDialog", "getOnShowNotificationDialog", "onToast", "getOnToast", "onUiModel", "getOnUiModel", "year", "getYear", "areNotificationsEnabled", "postModel", "Lorg/wordpress/android/fluxc/model/PostImmutableModel;", "getCurrentPublishDateAsCalendar", "postRepository", "Lorg/wordpress/android/ui/posts/EditPostRepository;", "onDateSelected", "dayOfMonth", "onNotificationCreated", "scheduleTime", "onShowDialog", "onTimeSelected", "selectedHour", "selectedMinute", "publishNow", "scheduleNotification", "notificationTime", "start", "updateDateAndTimeFromCalendar", "startCalendar", "updateNotifications", "schedulingReminderPeriod", "updatePost", "updatedDate", "updateUiModel", "toLabel", "CalendarEvent", "Notification", "PublishUiModel", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class PublishSettingsViewModel extends androidx.lifecycle.ViewModel {
    private final org.wordpress.android.viewmodel.ResourceProvider resourceProvider = null;
    private final org.wordpress.android.ui.posts.PostSettingsUtils postSettingsUtils = null;
    private final org.wordpress.android.util.LocaleManagerWrapper localeManagerWrapper = null;
    private final org.wordpress.android.fluxc.store.PostSchedulingNotificationStore postSchedulingNotificationStore = null;
    private final org.wordpress.android.fluxc.store.SiteStore siteStore = null;
    private boolean canPublishImmediately = false;
    @org.jetbrains.annotations.Nullable()
    private java.lang.Integer year;
    @org.jetbrains.annotations.Nullable()
    private java.lang.Integer month;
    @org.jetbrains.annotations.Nullable()
    private java.lang.Integer day;
    @org.jetbrains.annotations.Nullable()
    private java.lang.Integer hour;
    @org.jetbrains.annotations.Nullable()
    private java.lang.Integer minute;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<kotlin.Unit>> _onDatePicked = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<kotlin.Unit>> onDatePicked = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<java.util.Calendar>> _onPublishedDateChanged = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<java.util.Calendar>> onPublishedDateChanged = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.fluxc.model.post.PostStatus> _onPostStatusChanged = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.fluxc.model.post.PostStatus> onPostStatusChanged = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.ui.posts.PublishSettingsViewModel.PublishUiModel> _onUiModel = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.ui.posts.PublishSettingsViewModel.PublishUiModel> onUiModel = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<java.lang.String>> _onToast = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<java.lang.String>> onToast = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.fluxc.store.PostSchedulingNotificationStore.SchedulingReminderModel.Period>> _onShowNotificationDialog = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.fluxc.store.PostSchedulingNotificationStore.SchedulingReminderModel.Period>> onShowNotificationDialog = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.fluxc.store.PostSchedulingNotificationStore.SchedulingReminderModel.Period> _onNotificationTime = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.fluxc.store.PostSchedulingNotificationStore.SchedulingReminderModel.Period> onNotificationTime = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.posts.PublishSettingsViewModel.Notification>> _onNotificationAdded = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.posts.PublishSettingsViewModel.Notification>> onNotificationAdded = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.posts.PublishSettingsViewModel.CalendarEvent>> _onAddToCalendar = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.posts.PublishSettingsViewModel.CalendarEvent>> onAddToCalendar = null;
    
    public PublishSettingsViewModel(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.ResourceProvider resourceProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.PostSettingsUtils postSettingsUtils, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.LocaleManagerWrapper localeManagerWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.PostSchedulingNotificationStore postSchedulingNotificationStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.SiteStore siteStore) {
        super();
    }
    
    public final boolean getCanPublishImmediately() {
        return false;
    }
    
    public final void setCanPublishImmediately(boolean p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getYear() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getMonth() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getDay() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getHour() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getMinute() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<kotlin.Unit>> getOnDatePicked() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<java.util.Calendar>> getOnPublishedDateChanged() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.fluxc.model.post.PostStatus> getOnPostStatusChanged() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.ui.posts.PublishSettingsViewModel.PublishUiModel> getOnUiModel() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<java.lang.String>> getOnToast() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.fluxc.store.PostSchedulingNotificationStore.SchedulingReminderModel.Period>> getOnShowNotificationDialog() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.fluxc.store.PostSchedulingNotificationStore.SchedulingReminderModel.Period> getOnNotificationTime() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.posts.PublishSettingsViewModel.Notification>> getOnNotificationAdded() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.posts.PublishSettingsViewModel.CalendarEvent>> getOnAddToCalendar() {
        return null;
    }
    
    public void start(@org.jetbrains.annotations.Nullable()
    org.wordpress.android.ui.posts.EditPostRepository postRepository) {
    }
    
    public final void onPostStatusChanged(@org.jetbrains.annotations.Nullable()
    org.wordpress.android.fluxc.model.PostImmutableModel postModel) {
    }
    
    public final void publishNow() {
    }
    
    public final void onTimeSelected(int selectedHour, int selectedMinute) {
    }
    
    public final void onDateSelected(int year, int month, int dayOfMonth) {
    }
    
    public final void onShowDialog(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.PostImmutableModel postModel) {
    }
    
    public final void updatePost(@org.jetbrains.annotations.NotNull()
    java.util.Calendar updatedDate, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.ui.posts.EditPostRepository postRepository) {
    }
    
    public final void updateUiModel(@org.jetbrains.annotations.Nullable()
    org.wordpress.android.fluxc.model.PostImmutableModel postModel) {
    }
    
    public final void onNotificationCreated(@org.jetbrains.annotations.Nullable()
    org.wordpress.android.fluxc.store.PostSchedulingNotificationStore.SchedulingReminderModel.Period scheduleTime) {
    }
    
    public final void scheduleNotification(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.EditPostRepository postRepository, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.PostSchedulingNotificationStore.SchedulingReminderModel.Period notificationTime) {
    }
    
    public final void onAddToCalendar(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.EditPostRepository postRepository) {
    }
    
    private final java.util.Calendar getCurrentPublishDateAsCalendar(org.wordpress.android.ui.posts.EditPostRepository postRepository) {
        return null;
    }
    
    private final void updateNotifications(org.wordpress.android.ui.posts.EditPostRepository postRepository, org.wordpress.android.fluxc.store.PostSchedulingNotificationStore.SchedulingReminderModel.Period schedulingReminderPeriod) {
    }
    
    private final boolean areNotificationsEnabled(org.wordpress.android.fluxc.model.PostImmutableModel postModel) {
        return false;
    }
    
    private final int toLabel(org.wordpress.android.fluxc.store.PostSchedulingNotificationStore.SchedulingReminderModel.Period $this$toLabel) {
        return 0;
    }
    
    private final void updateDateAndTimeFromCalendar(java.util.Calendar startCalendar) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0013\b\u0086\b\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0012\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0013\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\u0014\u001a\u00020\u0007H\u00c6\u0003J1\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u0007H\u00c6\u0001J\u0013\u0010\u0016\u001a\u00020\u00072\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0018\u001a\u00020\u0005H\u00d6\u0001J\t\u0010\u0019\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\b\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010\u00a8\u0006\u001a"}, d2 = {"Lorg/wordpress/android/ui/posts/PublishSettingsViewModel$PublishUiModel;", "", "publishDateLabel", "", "notificationLabel", "", "notificationEnabled", "", "notificationVisible", "(Ljava/lang/String;IZZ)V", "getNotificationEnabled", "()Z", "getNotificationLabel", "()I", "getNotificationVisible", "getPublishDateLabel", "()Ljava/lang/String;", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class PublishUiModel {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String publishDateLabel = null;
        private final int notificationLabel = 0;
        private final boolean notificationEnabled = false;
        private final boolean notificationVisible = false;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.posts.PublishSettingsViewModel.PublishUiModel copy(@org.jetbrains.annotations.NotNull()
        java.lang.String publishDateLabel, int notificationLabel, boolean notificationEnabled, boolean notificationVisible) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.lang.String toString() {
            return null;
        }
        
        public PublishUiModel(@org.jetbrains.annotations.NotNull()
        java.lang.String publishDateLabel, int notificationLabel, boolean notificationEnabled, boolean notificationVisible) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getPublishDateLabel() {
            return null;
        }
        
        public final int component2() {
            return 0;
        }
        
        public final int getNotificationLabel() {
            return 0;
        }
        
        public final boolean component3() {
            return false;
        }
        
        public final boolean getNotificationEnabled() {
            return false;
        }
        
        public final boolean component4() {
            return false;
        }
        
        public final boolean getNotificationVisible() {
            return false;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\f\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0011\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u0012\u001a\u00020\u0013H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0014"}, d2 = {"Lorg/wordpress/android/ui/posts/PublishSettingsViewModel$Notification;", "", "id", "", "scheduledTime", "", "(IJ)V", "getId", "()I", "getScheduledTime", "()J", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Notification {
        private final int id = 0;
        private final long scheduledTime = 0L;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.posts.PublishSettingsViewModel.Notification copy(int id, long scheduledTime) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.lang.String toString() {
            return null;
        }
        
        public Notification(int id, long scheduledTime) {
            super();
        }
        
        public final int component1() {
            return 0;
        }
        
        public final int getId() {
            return 0;
        }
        
        public final long component2() {
            return 0L;
        }
        
        public final long getScheduledTime() {
            return 0L;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000f\u001a\u00020\u0006H\u00c6\u0003J\'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u00c6\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0014\u001a\u00020\u0015H\u00d6\u0001J\t\u0010\u0016\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t\u00a8\u0006\u0017"}, d2 = {"Lorg/wordpress/android/ui/posts/PublishSettingsViewModel$CalendarEvent;", "", "title", "", "description", "startTime", "", "(Ljava/lang/String;Ljava/lang/String;J)V", "getDescription", "()Ljava/lang/String;", "getStartTime", "()J", "getTitle", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class CalendarEvent {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String title = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String description = null;
        private final long startTime = 0L;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.posts.PublishSettingsViewModel.CalendarEvent copy(@org.jetbrains.annotations.NotNull()
        java.lang.String title, @org.jetbrains.annotations.NotNull()
        java.lang.String description, long startTime) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.lang.String toString() {
            return null;
        }
        
        public CalendarEvent(@org.jetbrains.annotations.NotNull()
        java.lang.String title, @org.jetbrains.annotations.NotNull()
        java.lang.String description, long startTime) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getTitle() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getDescription() {
            return null;
        }
        
        public final long component3() {
            return 0L;
        }
        
        public final long getStartTime() {
            return 0L;
        }
    }
}