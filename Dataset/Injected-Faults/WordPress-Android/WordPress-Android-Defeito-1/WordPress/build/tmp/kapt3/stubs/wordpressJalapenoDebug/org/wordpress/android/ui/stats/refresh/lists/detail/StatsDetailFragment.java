package org.wordpress.android.ui.stats.refresh.lists.detail;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u0012\u0010\u0017\u001a\u00020\u00142\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016J\u001a\u0010\u001a\u001a\u00020\u00142\u0006\u0010\u001b\u001a\u00020\u001c2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016J\u0010\u0010\u001d\u001a\u00020\u00142\u0006\u0010\u000b\u001a\u00020\fH\u0002J\f\u0010\u001e\u001a\u00020\u0014*\u00020\u001fH\u0002R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\r\u001a\u00020\u000e8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012\u00a8\u0006 "}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/detail/StatsDetailFragment;", "Ldagger/android/support/DaggerFragment;", "()V", "statsSiteProvider", "Lorg/wordpress/android/ui/stats/refresh/utils/StatsSiteProvider;", "getStatsSiteProvider", "()Lorg/wordpress/android/ui/stats/refresh/utils/StatsSiteProvider;", "setStatsSiteProvider", "(Lorg/wordpress/android/ui/stats/refresh/utils/StatsSiteProvider;)V", "swipeToRefreshHelper", "Lorg/wordpress/android/util/helpers/SwipeToRefreshHelper;", "viewModel", "Lorg/wordpress/android/ui/stats/refresh/lists/detail/StatsDetailViewModel;", "viewModelFactory", "Landroidx/lifecycle/ViewModelProvider$Factory;", "getViewModelFactory", "()Landroidx/lifecycle/ViewModelProvider$Factory;", "setViewModelFactory", "(Landroidx/lifecycle/ViewModelProvider$Factory;)V", "initializeViewModels", "", "activity", "Landroidx/fragment/app/FragmentActivity;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "view", "Landroid/view/View;", "setupObservers", "initializeViews", "Lorg/wordpress/android/databinding/StatsDetailFragmentBinding;", "org.wordpress.android_wordpressJalapenoDebug"})
public final class StatsDetailFragment extends dagger.android.support.DaggerFragment {
    @javax.inject.Inject()
    public androidx.lifecycle.ViewModelProvider.Factory viewModelFactory;
    @javax.inject.Inject()
    public org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider statsSiteProvider;
    private org.wordpress.android.ui.stats.refresh.lists.detail.StatsDetailViewModel viewModel;
    private org.wordpress.android.util.helpers.SwipeToRefreshHelper swipeToRefreshHelper;
    
    public StatsDetailFragment() {
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
    public final org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider getStatsSiteProvider() {
        return null;
    }
    
    public final void setStatsSiteProvider(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider p0) {
    }
    
    @java.lang.Override()
    public void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void initializeViews(org.wordpress.android.databinding.StatsDetailFragmentBinding $this$initializeViews) {
    }
    
    private final void initializeViewModels(androidx.fragment.app.FragmentActivity activity) {
    }
    
    private final void setupObservers(org.wordpress.android.ui.stats.refresh.lists.detail.StatsDetailViewModel viewModel) {
    }
}