package org.wordpress.android.ui.comments.unified;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 +2\u00020\u0001:\u0001+B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0016J\u0010\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\'H\u0016J\f\u0010(\u001a\u00020!*\u00020\nH\u0002J\f\u0010)\u001a\u00020!*\u00020\nH\u0003J\f\u0010*\u001a\u00020!*\u00020\nH\u0002R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0012\u001a\u00020\u00138\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u001a\u001a\u00020\u001b8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001f\u00a8\u0006,"}, d2 = {"Lorg/wordpress/android/ui/comments/unified/UnifiedCommentsActivity;", "Lorg/wordpress/android/ui/LocaleAwareActivity;", "()V", "analyticsTrackerWrapper", "Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;", "getAnalyticsTrackerWrapper", "()Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;", "setAnalyticsTrackerWrapper", "(Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;)V", "binding", "Lorg/wordpress/android/databinding/UnifiedCommentActivityBinding;", "commentListFilters", "", "Lorg/wordpress/android/ui/comments/unified/CommentFilter;", "disabledTabsOpacity", "", "pagerAdapter", "Lorg/wordpress/android/ui/comments/unified/UnifiedCommentListPagerAdapter;", "selectedSiteRepository", "Lorg/wordpress/android/ui/mysite/SelectedSiteRepository;", "getSelectedSiteRepository", "()Lorg/wordpress/android/ui/mysite/SelectedSiteRepository;", "setSelectedSiteRepository", "(Lorg/wordpress/android/ui/mysite/SelectedSiteRepository;)V", "viewModel", "Lorg/wordpress/android/ui/comments/unified/UnifiedCommentActivityViewModel;", "viewModelFactory", "Landroidx/lifecycle/ViewModelProvider$Factory;", "getViewModelFactory", "()Landroidx/lifecycle/ViewModelProvider$Factory;", "setViewModelFactory", "(Landroidx/lifecycle/ViewModelProvider$Factory;)V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onOptionsItemSelected", "", "item", "Landroid/view/MenuItem;", "setupActionBar", "setupContent", "setupObservers", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public final class UnifiedCommentsActivity extends org.wordpress.android.ui.LocaleAwareActivity {
    @javax.inject.Inject()
    public androidx.lifecycle.ViewModelProvider.Factory viewModelFactory;
    @javax.inject.Inject()
    public org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTrackerWrapper;
    @javax.inject.Inject()
    public org.wordpress.android.ui.mysite.SelectedSiteRepository selectedSiteRepository;
    private org.wordpress.android.ui.comments.unified.UnifiedCommentActivityViewModel viewModel;
    private final java.util.List<org.wordpress.android.ui.comments.unified.CommentFilter> commentListFilters = null;
    private float disabledTabsOpacity = 0.0F;
    private org.wordpress.android.ui.comments.unified.UnifiedCommentListPagerAdapter pagerAdapter;
    private org.wordpress.android.databinding.UnifiedCommentActivityBinding binding;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.comments.unified.UnifiedCommentsActivity.Companion Companion = null;
    private static final int VIEW_PAGER_OFFSCREEN_PAGE_LIMIT = 1;
    
    public UnifiedCommentsActivity() {
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
    public final org.wordpress.android.ui.mysite.SelectedSiteRepository getSelectedSiteRepository() {
        return null;
    }
    
    public final void setSelectedSiteRepository(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.SelectedSiteRepository p0) {
    }
    
    @java.lang.Override()
    public void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @android.annotation.SuppressLint(value = {"WrongConstant"})
    private final void setupContent(org.wordpress.android.databinding.UnifiedCommentActivityBinding $this$setupContent) {
    }
    
    private final void setupActionBar(org.wordpress.android.databinding.UnifiedCommentActivityBinding $this$setupActionBar) {
    }
    
    private final void setupObservers(org.wordpress.android.databinding.UnifiedCommentActivityBinding $this$setupObservers) {
    }
    
    @java.lang.Override()
    public boolean onOptionsItemSelected(@org.jetbrains.annotations.NotNull()
    android.view.MenuItem item) {
        return false;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lorg/wordpress/android/ui/comments/unified/UnifiedCommentsActivity$Companion;", "", "()V", "VIEW_PAGER_OFFSCREEN_PAGE_LIMIT", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}