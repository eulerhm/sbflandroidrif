package org.wordpress.android.ui.jetpack.scan.history;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u000b\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 32\u00020\u0001:\u00013B\u0005\u00a2\u0006\u0002\u0010\u0002J\n\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0016J\u0012\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0002J\b\u0010!\u001a\u00020\"H\u0002J\b\u0010#\u001a\u00020$H\u0002J\b\u0010%\u001a\u00020$H\u0016J\u0010\u0010&\u001a\u00020$2\u0006\u0010\'\u001a\u00020 H\u0016J\u001a\u0010(\u001a\u00020$2\u0006\u0010)\u001a\u00020\u001c2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016J\f\u0010*\u001a\u00020$*\u00020\u0004H\u0002J\u001c\u0010+\u001a\u00020$*\u00020\u00042\u0006\u0010,\u001a\u00020\u001e2\u0006\u0010-\u001a\u00020\"H\u0002J\u001a\u0010.\u001a\u00020$*\u00020\u00042\f\u0010/\u001a\b\u0012\u0004\u0012\u00020100H\u0002J\f\u00102\u001a\u00020$*\u00020\u0004H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\r\u001a\u00020\u000e8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0015\u001a\u00020\u00168\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001a\u00a8\u00064"}, d2 = {"Lorg/wordpress/android/ui/jetpack/scan/history/ScanHistoryListFragment;", "Lorg/wordpress/android/ui/ViewPagerFragment;", "()V", "binding", "Lorg/wordpress/android/databinding/ScanHistoryListFragmentBinding;", "imageManager", "Lorg/wordpress/android/util/image/ImageManager;", "getImageManager", "()Lorg/wordpress/android/util/image/ImageManager;", "setImageManager", "(Lorg/wordpress/android/util/image/ImageManager;)V", "parentViewModel", "Lorg/wordpress/android/ui/jetpack/scan/history/ScanHistoryViewModel;", "uiHelpers", "Lorg/wordpress/android/ui/utils/UiHelpers;", "getUiHelpers", "()Lorg/wordpress/android/ui/utils/UiHelpers;", "setUiHelpers", "(Lorg/wordpress/android/ui/utils/UiHelpers;)V", "viewModel", "Lorg/wordpress/android/ui/jetpack/scan/history/ScanHistoryListViewModel;", "viewModelFactory", "Landroidx/lifecycle/ViewModelProvider$Factory;", "getViewModelFactory", "()Landroidx/lifecycle/ViewModelProvider$Factory;", "setViewModelFactory", "(Landroidx/lifecycle/ViewModelProvider$Factory;)V", "getScrollableViewForUniqueIdProvision", "Landroid/view/View;", "getSite", "Lorg/wordpress/android/fluxc/model/SiteModel;", "savedInstanceState", "Landroid/os/Bundle;", "getTabType", "Lorg/wordpress/android/ui/jetpack/scan/history/ScanHistoryViewModel$ScanHistoryTabType;", "initDagger", "", "onDestroyView", "onSaveInstanceState", "outState", "onViewCreated", "view", "initRecyclerView", "initViewModel", "site", "tabType", "refreshContentScreen", "items", "", "Lorg/wordpress/android/ui/jetpack/scan/ScanListItemState;", "setupObservers", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public final class ScanHistoryListFragment extends org.wordpress.android.ui.ViewPagerFragment {
    @javax.inject.Inject()
    public androidx.lifecycle.ViewModelProvider.Factory viewModelFactory;
    @javax.inject.Inject()
    public org.wordpress.android.util.image.ImageManager imageManager;
    @javax.inject.Inject()
    public org.wordpress.android.ui.utils.UiHelpers uiHelpers;
    private org.wordpress.android.ui.jetpack.scan.history.ScanHistoryListViewModel viewModel;
    private org.wordpress.android.ui.jetpack.scan.history.ScanHistoryViewModel parentViewModel;
    private org.wordpress.android.databinding.ScanHistoryListFragmentBinding binding;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.jetpack.scan.history.ScanHistoryListFragment.Companion Companion = null;
    private static final java.lang.String ARG_TAB_TYPE = "arg_tab_type";
    
    public ScanHistoryListFragment() {
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
    public final org.wordpress.android.ui.utils.UiHelpers getUiHelpers() {
        return null;
    }
    
    public final void setUiHelpers(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.utils.UiHelpers p0) {
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
    
    private final void initDagger() {
    }
    
    private final void initRecyclerView(org.wordpress.android.databinding.ScanHistoryListFragmentBinding $this$initRecyclerView) {
    }
    
    private final void initViewModel(org.wordpress.android.databinding.ScanHistoryListFragmentBinding $this$initViewModel, org.wordpress.android.fluxc.model.SiteModel site, org.wordpress.android.ui.jetpack.scan.history.ScanHistoryViewModel.ScanHistoryTabType tabType) {
    }
    
    private final void setupObservers(org.wordpress.android.databinding.ScanHistoryListFragmentBinding $this$setupObservers) {
    }
    
    private final void refreshContentScreen(org.wordpress.android.databinding.ScanHistoryListFragmentBinding $this$refreshContentScreen, java.util.List<? extends org.wordpress.android.ui.jetpack.scan.ScanListItemState> items) {
    }
    
    private final org.wordpress.android.fluxc.model.SiteModel getSite(android.os.Bundle savedInstanceState) {
        return null;
    }
    
    private final org.wordpress.android.ui.jetpack.scan.history.ScanHistoryViewModel.ScanHistoryTabType getTabType() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public android.view.View getScrollableViewForUniqueIdProvision() {
        return null;
    }
    
    @java.lang.Override()
    public void onSaveInstanceState(@org.jetbrains.annotations.NotNull()
    android.os.Bundle outState) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lorg/wordpress/android/ui/jetpack/scan/history/ScanHistoryListFragment$Companion;", "", "()V", "ARG_TAB_TYPE", "", "newInstance", "Lorg/wordpress/android/ui/jetpack/scan/history/ScanHistoryListFragment;", "tabType", "Lorg/wordpress/android/ui/jetpack/scan/history/ScanHistoryViewModel$ScanHistoryTabType;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.jetpack.scan.history.ScanHistoryListFragment newInstance(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.jetpack.scan.history.ScanHistoryViewModel.ScanHistoryTabType tabType) {
            return null;
        }
    }
}