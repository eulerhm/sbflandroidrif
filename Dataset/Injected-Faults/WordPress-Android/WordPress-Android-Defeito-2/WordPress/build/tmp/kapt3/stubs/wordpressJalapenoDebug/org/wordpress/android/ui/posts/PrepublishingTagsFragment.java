package org.wordpress.android.ui.posts;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 32\u00020\u00012\u00020\u0002:\u00013B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\u001a\u001a\u00020\u001bH\u0014J\n\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0002J\b\u0010\u001e\u001a\u00020\u001fH\u0002J\n\u0010 \u001a\u0004\u0018\u00010!H\u0014J\u0010\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%H\u0016J\u0012\u0010&\u001a\u00020#2\b\u0010\'\u001a\u0004\u0018\u00010(H\u0016J\b\u0010)\u001a\u00020#H\u0016J\b\u0010*\u001a\u00020#H\u0016J\u0010\u0010+\u001a\u00020#2\u0006\u0010,\u001a\u00020!H\u0016J\u001a\u0010-\u001a\u00020#2\u0006\u0010.\u001a\u00020/2\b\u0010\'\u001a\u0004\u0018\u00010(H\u0016J\b\u00100\u001a\u00020#H\u0002J\f\u00101\u001a\u00020#*\u000202H\u0002R\u001e\u0010\u0004\u001a\u00020\u00058\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\f\u001a\u00020\r8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0014\u001a\u00020\u00158\u0000@\u0000X\u0081.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019\u00a8\u00064"}, d2 = {"Lorg/wordpress/android/ui/posts/PrepublishingTagsFragment;", "Lorg/wordpress/android/ui/posts/TagsFragment;", "Lorg/wordpress/android/ui/posts/TagsSelectedListener;", "()V", "analyticsTrackerWrapper", "Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;", "getAnalyticsTrackerWrapper", "()Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;", "setAnalyticsTrackerWrapper", "(Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;)V", "closeListener", "Lorg/wordpress/android/ui/posts/PrepublishingScreenClosedListener;", "uiHelpers", "Lorg/wordpress/android/ui/utils/UiHelpers;", "getUiHelpers", "()Lorg/wordpress/android/ui/utils/UiHelpers;", "setUiHelpers", "(Lorg/wordpress/android/ui/utils/UiHelpers;)V", "viewModel", "Lorg/wordpress/android/ui/posts/PrepublishingTagsViewModel;", "viewModelFactory", "Landroidx/lifecycle/ViewModelProvider$Factory;", "getViewModelFactory$org_wordpress_android_wordpressJalapenoDebug", "()Landroidx/lifecycle/ViewModelProvider$Factory;", "setViewModelFactory$org_wordpress_android_wordpressJalapenoDebug", "(Landroidx/lifecycle/ViewModelProvider$Factory;)V", "getContentLayout", "", "getEditPostActivityHook", "Lorg/wordpress/android/ui/posts/EditPostSettingsFragment$EditPostActivityHook;", "getEditPostRepository", "Lorg/wordpress/android/ui/posts/EditPostRepository;", "getTagsFromEditPostRepositoryOrArguments", "", "onAttach", "", "context", "Landroid/content/Context;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDetach", "onResume", "onTagsSelected", "selectedTags", "onViewCreated", "view", "Landroid/view/View;", "trackTagsChangedEvent", "initViewModel", "Lorg/wordpress/android/databinding/PrepublishingTagsFragmentBinding;", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public final class PrepublishingTagsFragment extends org.wordpress.android.ui.posts.TagsFragment implements org.wordpress.android.ui.posts.TagsSelectedListener {
    private org.wordpress.android.ui.posts.PrepublishingScreenClosedListener closeListener;
    @javax.inject.Inject()
    public androidx.lifecycle.ViewModelProvider.Factory viewModelFactory;
    @javax.inject.Inject()
    public org.wordpress.android.ui.utils.UiHelpers uiHelpers;
    @javax.inject.Inject()
    public org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTrackerWrapper;
    private org.wordpress.android.ui.posts.PrepublishingTagsViewModel viewModel;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.posts.PrepublishingTagsFragment.Companion Companion = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String TAG = "prepublishing_tags_fragment_tag";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String NEEDS_REQUEST_LAYOUT = "prepublishing_tags_fragment_needs_request_layout";
    
    public PrepublishingTagsFragment() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.ViewModelProvider.Factory getViewModelFactory$org_wordpress_android_wordpressJalapenoDebug() {
        return null;
    }
    
    public final void setViewModelFactory$org_wordpress_android_wordpressJalapenoDebug(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.ViewModelProvider.Factory p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.utils.UiHelpers getUiHelpers() {
        return null;
    }
    
    public final void setUiHelpers(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.utils.UiHelpers p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.util.analytics.AnalyticsTrackerWrapper getAnalyticsTrackerWrapper() {
        return null;
    }
    
    public final void setAnalyticsTrackerWrapper(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.AnalyticsTrackerWrapper p0) {
    }
    
    @java.lang.Override()
    protected int getContentLayout() {
        return 0;
    }
    
    @java.lang.Override()
    public void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void onAttach(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    @java.lang.Override()
    public void onDetach() {
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    protected java.lang.String getTagsFromEditPostRepositoryOrArguments() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @kotlin.jvm.JvmStatic()
    public static final org.wordpress.android.ui.posts.PrepublishingTagsFragment newInstance(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, boolean needsRequestLayout) {
        return null;
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void trackTagsChangedEvent() {
    }
    
    @java.lang.Override()
    public void onResume() {
    }
    
    private final void initViewModel(org.wordpress.android.databinding.PrepublishingTagsFragmentBinding $this$initViewModel) {
    }
    
    private final org.wordpress.android.ui.posts.EditPostRepository getEditPostRepository() {
        return null;
    }
    
    private final org.wordpress.android.ui.posts.EditPostSettingsFragment.EditPostActivityHook getEditPostActivityHook() {
        return null;
    }
    
    @java.lang.Override()
    public void onTagsSelected(@org.jetbrains.annotations.NotNull()
    java.lang.String selectedTags) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lorg/wordpress/android/ui/posts/PrepublishingTagsFragment$Companion;", "", "()V", "NEEDS_REQUEST_LAYOUT", "", "TAG", "newInstance", "Lorg/wordpress/android/ui/posts/PrepublishingTagsFragment;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "needsRequestLayout", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        @kotlin.jvm.JvmStatic()
        public final org.wordpress.android.ui.posts.PrepublishingTagsFragment newInstance(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.SiteModel site, boolean needsRequestLayout) {
            return null;
        }
    }
}