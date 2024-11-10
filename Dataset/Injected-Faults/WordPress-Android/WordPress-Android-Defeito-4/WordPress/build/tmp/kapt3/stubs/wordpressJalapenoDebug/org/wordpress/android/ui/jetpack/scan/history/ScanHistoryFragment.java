package org.wordpress.android.ui.jetpack.scan.history;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002:\u0001;B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0012\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0002J\b\u0010 \u001a\u00020!H\u0002J\b\u0010\"\u001a\u00020!H\u0016J\u0010\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&H\u0016J\u0010\u0010\'\u001a\u00020!2\u0006\u0010(\u001a\u00020\u001fH\u0016J\u0010\u0010)\u001a\u00020!2\u0006\u0010*\u001a\u00020+H\u0016J\u001a\u0010,\u001a\u00020!2\u0006\u0010-\u001a\u00020.2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016J\f\u0010/\u001a\u00020!*\u00020\u0005H\u0002J\u0014\u00100\u001a\u00020!*\u00020\u00052\u0006\u00101\u001a\u00020\u001dH\u0002J\f\u00102\u001a\u00020!*\u00020\u0005H\u0002J\u0014\u00103\u001a\u00020!*\u0002042\u0006\u00105\u001a\u000206H\u0002J\u001a\u00107\u001a\u00020!*\u00020\u00052\f\u00108\u001a\b\u0012\u0004\u0012\u00020:09H\u0002R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0006\u001a\u00020\u00078\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u000e\u001a\u00020\u000f8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0016\u001a\u00020\u00178\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001b\u00a8\u0006<"}, d2 = {"Lorg/wordpress/android/ui/jetpack/scan/history/ScanHistoryFragment;", "Landroidx/fragment/app/Fragment;", "Lorg/wordpress/android/ui/ScrollableViewInitializedListener;", "()V", "binding", "Lorg/wordpress/android/databinding/ScanHistoryFragmentBinding;", "localeManagerWrapper", "Lorg/wordpress/android/util/LocaleManagerWrapper;", "getLocaleManagerWrapper", "()Lorg/wordpress/android/util/LocaleManagerWrapper;", "setLocaleManagerWrapper", "(Lorg/wordpress/android/util/LocaleManagerWrapper;)V", "onTabSelectedListener", "Lcom/google/android/material/tabs/TabLayout$OnTabSelectedListener;", "uiHelpers", "Lorg/wordpress/android/ui/utils/UiHelpers;", "getUiHelpers", "()Lorg/wordpress/android/ui/utils/UiHelpers;", "setUiHelpers", "(Lorg/wordpress/android/ui/utils/UiHelpers;)V", "viewModel", "Lorg/wordpress/android/ui/jetpack/scan/history/ScanHistoryViewModel;", "viewModelFactory", "Landroidx/lifecycle/ViewModelProvider$Factory;", "getViewModelFactory", "()Landroidx/lifecycle/ViewModelProvider$Factory;", "setViewModelFactory", "(Landroidx/lifecycle/ViewModelProvider$Factory;)V", "getSite", "Lorg/wordpress/android/fluxc/model/SiteModel;", "savedInstanceState", "Landroid/os/Bundle;", "initDagger", "", "onDestroyView", "onOptionsItemSelected", "", "item", "Landroid/view/MenuItem;", "onSaveInstanceState", "outState", "onScrollableViewInitialized", "viewId", "", "onViewCreated", "view", "Landroid/view/View;", "initToolbar", "initViewModel", "site", "setupObservers", "updateErrorLayout", "Lorg/wordpress/android/databinding/FullscreenErrorWithRetryBinding;", "uiState", "Lorg/wordpress/android/ui/jetpack/scan/history/ScanHistoryViewModel$UiState$ErrorUiState;", "updateTabs", "list", "", "Lorg/wordpress/android/ui/jetpack/scan/history/ScanHistoryViewModel$TabUiState;", "ScanHistoryTabAdapter", "org.wordpress.android_wordpressJalapenoDebug"})
public final class ScanHistoryFragment extends androidx.fragment.app.Fragment implements org.wordpress.android.ui.ScrollableViewInitializedListener {
    @javax.inject.Inject()
    public androidx.lifecycle.ViewModelProvider.Factory viewModelFactory;
    @javax.inject.Inject()
    public org.wordpress.android.ui.utils.UiHelpers uiHelpers;
    @javax.inject.Inject()
    public org.wordpress.android.util.LocaleManagerWrapper localeManagerWrapper;
    private org.wordpress.android.ui.jetpack.scan.history.ScanHistoryViewModel viewModel;
    private org.wordpress.android.databinding.ScanHistoryFragmentBinding binding;
    private final com.google.android.material.tabs.TabLayout.OnTabSelectedListener onTabSelectedListener = null;
    
    public ScanHistoryFragment() {
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
    public final org.wordpress.android.ui.utils.UiHelpers getUiHelpers() {
        return null;
    }
    
    public final void setUiHelpers(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.utils.UiHelpers p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.util.LocaleManagerWrapper getLocaleManagerWrapper() {
        return null;
    }
    
    public final void setLocaleManagerWrapper(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.LocaleManagerWrapper p0) {
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
    
    private final void initViewModel(org.wordpress.android.databinding.ScanHistoryFragmentBinding $this$initViewModel, org.wordpress.android.fluxc.model.SiteModel site) {
    }
    
    private final void setupObservers(org.wordpress.android.databinding.ScanHistoryFragmentBinding $this$setupObservers) {
    }
    
    private final void updateErrorLayout(org.wordpress.android.databinding.FullscreenErrorWithRetryBinding $this$updateErrorLayout, org.wordpress.android.ui.jetpack.scan.history.ScanHistoryViewModel.UiState.ErrorUiState uiState) {
    }
    
    private final void updateTabs(org.wordpress.android.databinding.ScanHistoryFragmentBinding $this$updateTabs, java.util.List<org.wordpress.android.ui.jetpack.scan.history.ScanHistoryViewModel.TabUiState> list) {
    }
    
    private final void initToolbar(org.wordpress.android.databinding.ScanHistoryFragmentBinding $this$initToolbar) {
    }
    
    private final org.wordpress.android.fluxc.model.SiteModel getSite(android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override()
    public void onSaveInstanceState(@org.jetbrains.annotations.NotNull()
    android.os.Bundle outState) {
    }
    
    @java.lang.Override()
    public boolean onOptionsItemSelected(@org.jetbrains.annotations.NotNull()
    android.view.MenuItem item) {
        return false;
    }
    
    @java.lang.Override()
    public void onScrollableViewInitialized(int viewId) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001b\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\u0010\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\nH\u0016R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lorg/wordpress/android/ui/jetpack/scan/history/ScanHistoryFragment$ScanHistoryTabAdapter;", "Landroidx/viewpager2/adapter/FragmentStateAdapter;", "items", "", "Lorg/wordpress/android/ui/jetpack/scan/history/ScanHistoryViewModel$TabUiState;", "parent", "Landroidx/fragment/app/Fragment;", "(Ljava/util/List;Landroidx/fragment/app/Fragment;)V", "createFragment", "position", "", "getItemCount", "org.wordpress.android_wordpressJalapenoDebug"})
    static final class ScanHistoryTabAdapter extends androidx.viewpager2.adapter.FragmentStateAdapter {
        private final java.util.List<org.wordpress.android.ui.jetpack.scan.history.ScanHistoryViewModel.TabUiState> items = null;
        
        public ScanHistoryTabAdapter(@org.jetbrains.annotations.NotNull()
        java.util.List<org.wordpress.android.ui.jetpack.scan.history.ScanHistoryViewModel.TabUiState> items, @org.jetbrains.annotations.NotNull()
        androidx.fragment.app.Fragment parent) {
            super(null);
        }
        
        @java.lang.Override()
        public int getItemCount() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public androidx.fragment.app.Fragment createFragment(int position) {
            return null;
        }
    }
}