package org.wordpress.android.ui.stats.refresh.lists;

import java.lang.System;

@kotlin.Suppress(names = {"TooManyFunctions"})
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0096\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 E2\u00020\u0001:\u0001EB\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010%\u001a\u00020&H\u0016J\u0012\u0010\'\u001a\u00020(2\b\u0010)\u001a\u0004\u0018\u00010*H\u0016J\u0018\u0010+\u001a\u00020(2\u0006\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020/H\u0016J\b\u00100\u001a\u00020(H\u0016J\u0010\u00101\u001a\u0002022\u0006\u00103\u001a\u000204H\u0016J\u0010\u00105\u001a\u00020(2\u0006\u00106\u001a\u00020*H\u0016J\u001a\u00107\u001a\u00020(2\u0006\u00108\u001a\u00020&2\b\u0010)\u001a\u0004\u0018\u00010*H\u0016J\u0014\u00109\u001a\u00020(*\u00020\u00042\u0006\u0010:\u001a\u00020;H\u0002J\u0016\u0010<\u001a\u00020(*\u00020\u00042\b\u0010)\u001a\u0004\u0018\u00010*H\u0002J\u0014\u0010=\u001a\u00020(*\u00020\u00042\u0006\u0010:\u001a\u00020;H\u0002J\u0016\u0010>\u001a\u00020(*\u00020\u00042\b\u0010?\u001a\u0004\u0018\u00010@H\u0002J\u001a\u0010A\u001a\u00020(*\u00020\u00042\f\u0010B\u001a\b\u0012\u0004\u0012\u00020D0CH\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082D\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u000f\u001a\u00020\u00108\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001e\u0010\u0015\u001a\u00020\u00168\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u000e\u0010\u001b\u001a\u00020\u001cX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u001f\u001a\u00020 8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$\u00a8\u0006F"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/StatsListFragment;", "Lorg/wordpress/android/ui/ViewPagerFragment;", "()V", "binding", "Lorg/wordpress/android/databinding/StatsListFragmentBinding;", "imageManager", "Lorg/wordpress/android/util/image/ImageManager;", "getImageManager", "()Lorg/wordpress/android/util/image/ImageManager;", "setImageManager", "(Lorg/wordpress/android/util/image/ImageManager;)V", "layoutManager", "Landroidx/recyclerview/widget/RecyclerView$LayoutManager;", "listStateKey", "", "navigator", "Lorg/wordpress/android/ui/stats/refresh/utils/StatsNavigator;", "getNavigator", "()Lorg/wordpress/android/ui/stats/refresh/utils/StatsNavigator;", "setNavigator", "(Lorg/wordpress/android/ui/stats/refresh/utils/StatsNavigator;)V", "statsDateFormatter", "Lorg/wordpress/android/ui/stats/refresh/utils/StatsDateFormatter;", "getStatsDateFormatter", "()Lorg/wordpress/android/ui/stats/refresh/utils/StatsDateFormatter;", "setStatsDateFormatter", "(Lorg/wordpress/android/ui/stats/refresh/utils/StatsDateFormatter;)V", "statsSection", "Lorg/wordpress/android/ui/stats/refresh/lists/StatsListViewModel$StatsSection;", "viewModel", "Lorg/wordpress/android/ui/stats/refresh/lists/StatsListViewModel;", "viewModelFactory", "Landroidx/lifecycle/ViewModelProvider$Factory;", "getViewModelFactory", "()Landroidx/lifecycle/ViewModelProvider$Factory;", "setViewModelFactory", "(Landroidx/lifecycle/ViewModelProvider$Factory;)V", "getScrollableViewForUniqueIdProvision", "Landroid/view/View;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onCreateOptionsMenu", "menu", "Landroid/view/Menu;", "inflater", "Landroid/view/MenuInflater;", "onDestroyView", "onOptionsItemSelected", "", "item", "Landroid/view/MenuItem;", "onSaveInstanceState", "outState", "onViewCreated", "view", "initializeViewModels", "activity", "Landroidx/fragment/app/FragmentActivity;", "initializeViews", "setupObservers", "showUiModel", "it", "Lorg/wordpress/android/ui/stats/refresh/lists/StatsListViewModel$UiModel;", "updateInsights", "statsState", "", "Lorg/wordpress/android/ui/stats/refresh/lists/StatsBlock;", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public final class StatsListFragment extends org.wordpress.android.ui.ViewPagerFragment {
    @javax.inject.Inject()
    public androidx.lifecycle.ViewModelProvider.Factory viewModelFactory;
    @javax.inject.Inject()
    public org.wordpress.android.util.image.ImageManager imageManager;
    @javax.inject.Inject()
    public org.wordpress.android.ui.stats.refresh.utils.StatsDateFormatter statsDateFormatter;
    @javax.inject.Inject()
    public org.wordpress.android.ui.stats.refresh.utils.StatsNavigator navigator;
    private org.wordpress.android.ui.stats.refresh.lists.StatsListViewModel viewModel;
    private org.wordpress.android.ui.stats.refresh.lists.StatsListViewModel.StatsSection statsSection;
    private androidx.recyclerview.widget.RecyclerView.LayoutManager layoutManager;
    private org.wordpress.android.databinding.StatsListFragmentBinding binding;
    private final java.lang.String listStateKey = "list_state";
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.stats.refresh.lists.StatsListFragment.Companion Companion = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String LIST_TYPE = "type_key";
    
    public StatsListFragment() {
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
    public final org.wordpress.android.util.image.ImageManager getImageManager() {
        return null;
    }
    
    public final void setImageManager(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.image.ImageManager p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.stats.refresh.utils.StatsDateFormatter getStatsDateFormatter() {
        return null;
    }
    
    public final void setStatsDateFormatter(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.utils.StatsDateFormatter p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.stats.refresh.utils.StatsNavigator getNavigator() {
        return null;
    }
    
    public final void setNavigator(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.utils.StatsNavigator p0) {
    }
    
    @java.lang.Override()
    public void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void onSaveInstanceState(@org.jetbrains.annotations.NotNull()
    android.os.Bundle outState) {
    }
    
    @java.lang.Override()
    public void onCreateOptionsMenu(@org.jetbrains.annotations.NotNull()
    android.view.Menu menu, @org.jetbrains.annotations.NotNull()
    android.view.MenuInflater inflater) {
    }
    
    @java.lang.Override()
    public boolean onOptionsItemSelected(@org.jetbrains.annotations.NotNull()
    android.view.MenuItem item) {
        return false;
    }
    
    private final void initializeViews(org.wordpress.android.databinding.StatsListFragmentBinding $this$initializeViews, android.os.Bundle savedInstanceState) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public android.view.View getScrollableViewForUniqueIdProvision() {
        return null;
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
    
    private final void initializeViewModels(org.wordpress.android.databinding.StatsListFragmentBinding $this$initializeViewModels, androidx.fragment.app.FragmentActivity activity) {
    }
    
    private final void setupObservers(org.wordpress.android.databinding.StatsListFragmentBinding $this$setupObservers, androidx.fragment.app.FragmentActivity activity) {
    }
    
    private final void showUiModel(org.wordpress.android.databinding.StatsListFragmentBinding $this$showUiModel, org.wordpress.android.ui.stats.refresh.lists.StatsListViewModel.UiModel it) {
    }
    
    private final void updateInsights(org.wordpress.android.databinding.StatsListFragmentBinding $this$updateInsights, java.util.List<? extends org.wordpress.android.ui.stats.refresh.lists.StatsBlock> statsState) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/StatsListFragment$Companion;", "", "()V", "LIST_TYPE", "", "newInstance", "Lorg/wordpress/android/ui/stats/refresh/lists/StatsListFragment;", "section", "Lorg/wordpress/android/ui/stats/refresh/lists/StatsListViewModel$StatsSection;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.stats.refresh.lists.StatsListFragment newInstance(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.stats.refresh.lists.StatsListViewModel.StatsSection section) {
            return null;
        }
    }
}