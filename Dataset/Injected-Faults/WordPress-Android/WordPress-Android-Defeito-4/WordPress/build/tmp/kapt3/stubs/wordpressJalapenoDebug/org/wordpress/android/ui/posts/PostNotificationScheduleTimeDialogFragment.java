package org.wordpress.android.ui.posts;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0012\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\f\u0010\u0013\u001a\u00020\u0014*\u00020\u0015H\u0002J\n\u0010\u0016\u001a\u00020\u0015*\u00020\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n\u00a8\u0006\u0018"}, d2 = {"Lorg/wordpress/android/ui/posts/PostNotificationScheduleTimeDialogFragment;", "Landroidx/fragment/app/DialogFragment;", "()V", "viewModel", "Lorg/wordpress/android/ui/posts/PublishSettingsViewModel;", "viewModelFactory", "Landroidx/lifecycle/ViewModelProvider$Factory;", "getViewModelFactory", "()Landroidx/lifecycle/ViewModelProvider$Factory;", "setViewModelFactory", "(Landroidx/lifecycle/ViewModelProvider$Factory;)V", "onAttach", "", "context", "Landroid/content/Context;", "onCreateDialog", "Landroid/app/Dialog;", "savedInstanceState", "Landroid/os/Bundle;", "toDialogModel", "Lorg/wordpress/android/fluxc/store/PostSchedulingNotificationStore$SchedulingReminderModel$Period;", "", "toViewId", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public final class PostNotificationScheduleTimeDialogFragment extends androidx.fragment.app.DialogFragment {
    @javax.inject.Inject()
    public androidx.lifecycle.ViewModelProvider.Factory viewModelFactory;
    private org.wordpress.android.ui.posts.PublishSettingsViewModel viewModel;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.posts.PostNotificationScheduleTimeDialogFragment.Companion Companion = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String TAG = "post_notification_time_dialog_fragment";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ARG_NOTIFICATION_SCHEDULE_TIME = "notification_schedule_time";
    private static final java.lang.String ARG_PUBLISH_SETTINGS_FRAGMENT_TYPE = "publish_settings_fragment_type";
    
    public PostNotificationScheduleTimeDialogFragment() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.ViewModelProvider.Factory getViewModelFactory() {
        return null;
    }
    
    public final void setViewModelFactory(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.ViewModelProvider.Factory p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public android.app.Dialog onCreateDialog(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    private final org.wordpress.android.fluxc.store.PostSchedulingNotificationStore.SchedulingReminderModel.Period toDialogModel(int $this$toDialogModel) {
        return null;
    }
    
    public final int toViewId(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.PostSchedulingNotificationStore.SchedulingReminderModel.Period $this$toViewId) {
        return 0;
    }
    
    @java.lang.Override()
    public void onAttach(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lorg/wordpress/android/ui/posts/PostNotificationScheduleTimeDialogFragment$Companion;", "", "()V", "ARG_NOTIFICATION_SCHEDULE_TIME", "", "ARG_PUBLISH_SETTINGS_FRAGMENT_TYPE", "TAG", "newInstance", "Lorg/wordpress/android/ui/posts/PostNotificationScheduleTimeDialogFragment;", "period", "Lorg/wordpress/android/fluxc/store/PostSchedulingNotificationStore$SchedulingReminderModel$Period;", "publishSettingsFragmentType", "Lorg/wordpress/android/ui/posts/PublishSettingsFragmentType;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.posts.PostNotificationScheduleTimeDialogFragment newInstance(@org.jetbrains.annotations.Nullable()
        org.wordpress.android.fluxc.store.PostSchedulingNotificationStore.SchedulingReminderModel.Period period, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.posts.PublishSettingsFragmentType publishSettingsFragmentType) {
            return null;
        }
    }
}