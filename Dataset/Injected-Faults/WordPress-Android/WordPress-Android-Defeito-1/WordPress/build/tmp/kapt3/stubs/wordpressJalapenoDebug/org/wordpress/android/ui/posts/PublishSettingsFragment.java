package org.wordpress.android.ui.posts;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b&\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0015\u001a\u00020\u0016H%J\n\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0002J\n\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0002J\b\u0010\u001b\u001a\u00020\u001cH$J&\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\u0006\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\"2\b\u0010#\u001a\u0004\u0018\u00010$H\u0016J\u0018\u0010%\u001a\u00020&2\u0006\u0010\'\u001a\u00020\"2\u0006\u0010\t\u001a\u00020\nH$J\u0012\u0010(\u001a\u00020&2\b\u0010)\u001a\u0004\u0018\u00010*H\u0002J\b\u0010+\u001a\u00020&H\u0002J\b\u0010,\u001a\u00020&H\u0002J\b\u0010-\u001a\u00020&H\u0002R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001e\u0010\u000f\u001a\u00020\u00108\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014\u00a8\u0006."}, d2 = {"Lorg/wordpress/android/ui/posts/PublishSettingsFragment;", "Landroidx/fragment/app/Fragment;", "()V", "analyticsTrackerWrapper", "Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;", "getAnalyticsTrackerWrapper", "()Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;", "setAnalyticsTrackerWrapper", "(Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;)V", "viewModel", "Lorg/wordpress/android/ui/posts/PublishSettingsViewModel;", "getViewModel", "()Lorg/wordpress/android/ui/posts/PublishSettingsViewModel;", "setViewModel", "(Lorg/wordpress/android/ui/posts/PublishSettingsViewModel;)V", "viewModelFactory", "Landroidx/lifecycle/ViewModelProvider$Factory;", "getViewModelFactory", "()Landroidx/lifecycle/ViewModelProvider$Factory;", "setViewModelFactory", "(Landroidx/lifecycle/ViewModelProvider$Factory;)V", "getContentLayout", "", "getEditPostActivityHook", "Lorg/wordpress/android/ui/posts/EditPostSettingsFragment$EditPostActivityHook;", "getPostRepository", "Lorg/wordpress/android/ui/posts/EditPostRepository;", "getPublishSettingsFragmentType", "Lorg/wordpress/android/ui/posts/PublishSettingsFragmentType;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "setupContent", "", "rootView", "showNotificationTimeSelectionDialog", "schedulingReminderPeriod", "Lorg/wordpress/android/fluxc/store/PostSchedulingNotificationStore$SchedulingReminderModel$Period;", "showPostDateSelectionDialog", "showPostTimeSelectionDialog", "trackPostScheduled", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class PublishSettingsFragment extends androidx.fragment.app.Fragment {
    @javax.inject.Inject()
    public androidx.lifecycle.ViewModelProvider.Factory viewModelFactory;
    @javax.inject.Inject()
    public org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTrackerWrapper;
    public org.wordpress.android.ui.posts.PublishSettingsViewModel viewModel;
    
    public PublishSettingsFragment() {
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
    public final org.wordpress.android.util.analytics.AnalyticsTrackerWrapper getAnalyticsTrackerWrapper() {
        return null;
    }
    
    public final void setAnalyticsTrackerWrapper(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.AnalyticsTrackerWrapper p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.posts.PublishSettingsViewModel getViewModel() {
        return null;
    }
    
    public final void setViewModel(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.PublishSettingsViewModel p0) {
    }
    
    @androidx.annotation.LayoutRes()
    protected abstract int getContentLayout();
    
    @org.jetbrains.annotations.NotNull()
    protected abstract org.wordpress.android.ui.posts.PublishSettingsFragmentType getPublishSettingsFragmentType();
    
    protected abstract void setupContent(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup rootView, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.PublishSettingsViewModel viewModel);
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    private final void trackPostScheduled() {
    }
    
    private final void showPostDateSelectionDialog() {
    }
    
    private final void showPostTimeSelectionDialog() {
    }
    
    private final void showNotificationTimeSelectionDialog(org.wordpress.android.fluxc.store.PostSchedulingNotificationStore.SchedulingReminderModel.Period schedulingReminderPeriod) {
    }
    
    private final org.wordpress.android.ui.posts.EditPostRepository getPostRepository() {
        return null;
    }
    
    private final org.wordpress.android.ui.posts.EditPostSettingsFragment.EditPostActivityHook getEditPostActivityHook() {
        return null;
    }
}