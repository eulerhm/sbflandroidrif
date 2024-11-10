package org.wordpress.android.ui.stats.refresh.lists.sections.insights.management;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u001a\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016J#\u0010\u001a\u001a\u00020\u000e*\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0002\u00a2\u0006\u0002\u0010 J\f\u0010!\u001a\u00020\u000e*\u00020\u001bH\u0002J\f\u0010\"\u001a\u00020\u000e*\u00020\u001bH\u0002J\u001a\u0010#\u001a\u00020\u000e*\u00020\u001b2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020&0%H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0007\u001a\u00020\b8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f\u00a8\u0006\'"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/management/InsightsManagementFragment;", "Ldagger/android/support/DaggerFragment;", "()V", "menu", "Landroid/view/Menu;", "viewModel", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/management/InsightsManagementViewModel;", "viewModelFactory", "Landroidx/lifecycle/ViewModelProvider$Factory;", "getViewModelFactory", "()Landroidx/lifecycle/ViewModelProvider$Factory;", "setViewModelFactory", "(Landroidx/lifecycle/ViewModelProvider$Factory;)V", "onCreateOptionsMenu", "", "inflater", "Landroid/view/MenuInflater;", "onOptionsItemSelected", "", "item", "Landroid/view/MenuItem;", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "initializeViewModels", "Lorg/wordpress/android/databinding/InsightsManagementFragmentBinding;", "activity", "Landroidx/fragment/app/FragmentActivity;", "siteId", "", "(Lorg/wordpress/android/databinding/InsightsManagementFragmentBinding;Landroidx/fragment/app/FragmentActivity;Ljava/lang/Integer;)V", "initializeViews", "setupObservers", "updateAddedInsights", "insights", "", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/management/InsightsManagementViewModel$InsightListItem;", "org.wordpress.android_wordpressJalapenoDebug"})
public final class InsightsManagementFragment extends dagger.android.support.DaggerFragment {
    @javax.inject.Inject()
    public androidx.lifecycle.ViewModelProvider.Factory viewModelFactory;
    private org.wordpress.android.ui.stats.refresh.lists.sections.insights.management.InsightsManagementViewModel viewModel;
    private android.view.Menu menu;
    
    public InsightsManagementFragment() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.ViewModelProvider.Factory getViewModelFactory() {
        return null;
    }
    
    public final void setViewModelFactory(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.ViewModelProvider.Factory p0) {
    }
    
    @java.lang.Override()
    public void onCreateOptionsMenu(@org.jetbrains.annotations.NotNull()
    android.view.Menu menu, @org.jetbrains.annotations.NotNull()
    android.view.MenuInflater inflater) {
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public boolean onOptionsItemSelected(@org.jetbrains.annotations.NotNull()
    android.view.MenuItem item) {
        return false;
    }
    
    private final void initializeViews(org.wordpress.android.databinding.InsightsManagementFragmentBinding $this$initializeViews) {
    }
    
    private final void initializeViewModels(org.wordpress.android.databinding.InsightsManagementFragmentBinding $this$initializeViewModels, androidx.fragment.app.FragmentActivity activity, java.lang.Integer siteId) {
    }
    
    private final void setupObservers(org.wordpress.android.databinding.InsightsManagementFragmentBinding $this$setupObservers) {
    }
    
    private final void updateAddedInsights(org.wordpress.android.databinding.InsightsManagementFragmentBinding $this$updateAddedInsights, java.util.List<? extends org.wordpress.android.ui.stats.refresh.lists.sections.insights.management.InsightsManagementViewModel.InsightListItem> insights) {
    }
}