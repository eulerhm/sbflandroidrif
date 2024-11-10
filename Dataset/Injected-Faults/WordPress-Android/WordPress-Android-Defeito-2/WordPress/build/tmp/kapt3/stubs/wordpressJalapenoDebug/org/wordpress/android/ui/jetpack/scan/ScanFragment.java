package org.wordpress.android.ui.jetpack.scan;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 92\u00020\u0001:\u00019B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0002J\b\u0010\u001d\u001a\u00020\u001eH\u0002J\u0010\u0010\u001f\u001a\u00020\u001e2\b\u0010 \u001a\u0004\u0018\u00010!J\b\u0010\"\u001a\u00020\u001eH\u0016J\u0010\u0010#\u001a\u00020\u001e2\u0006\u0010$\u001a\u00020\u001cH\u0016J\u001a\u0010%\u001a\u00020\u001e2\u0006\u0010&\u001a\u00020\'2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0016J\u0010\u0010(\u001a\u00020\u001e2\u0006\u0010)\u001a\u00020*H\u0002J\f\u0010+\u001a\u00020\u001e*\u00020,H\u0002J\f\u0010-\u001a\u00020\u001e*\u00020,H\u0002J\f\u0010.\u001a\u00020\u001e*\u00020,H\u0002J\u0014\u0010/\u001a\u00020\u001e*\u00020,2\u0006\u00100\u001a\u00020\u001aH\u0002J\f\u00101\u001a\u00020\u001e*\u00020,H\u0002J\f\u00102\u001a\u00020\u001e*\u000203H\u0002J\u0014\u00104\u001a\u00020\u001e*\u00020,2\u0006\u00105\u001a\u000206H\u0002J\u0014\u00107\u001a\u00020\u001e*\u00020,2\u0006\u00105\u001a\u000208H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001e\u0010\u000b\u001a\u00020\f8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0013\u001a\u00020\u00148\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018\u00a8\u0006:"}, d2 = {"Lorg/wordpress/android/ui/jetpack/scan/ScanFragment;", "Landroidx/fragment/app/Fragment;", "()V", "fixThreatsConfirmationDialog", "Landroidx/appcompat/app/AlertDialog;", "imageManager", "Lorg/wordpress/android/util/image/ImageManager;", "getImageManager", "()Lorg/wordpress/android/util/image/ImageManager;", "setImageManager", "(Lorg/wordpress/android/util/image/ImageManager;)V", "uiHelpers", "Lorg/wordpress/android/ui/utils/UiHelpers;", "getUiHelpers", "()Lorg/wordpress/android/ui/utils/UiHelpers;", "setUiHelpers", "(Lorg/wordpress/android/ui/utils/UiHelpers;)V", "viewModel", "Lorg/wordpress/android/ui/jetpack/scan/ScanViewModel;", "viewModelFactory", "Landroidx/lifecycle/ViewModelProvider$Factory;", "getViewModelFactory", "()Landroidx/lifecycle/ViewModelProvider$Factory;", "setViewModelFactory", "(Landroidx/lifecycle/ViewModelProvider$Factory;)V", "getSite", "Lorg/wordpress/android/fluxc/model/SiteModel;", "savedInstanceState", "Landroid/os/Bundle;", "initDagger", "", "onNewIntent", "intent", "Landroid/content/Intent;", "onPause", "onSaveInstanceState", "outState", "onViewCreated", "view", "Landroid/view/View;", "showFixThreatsConfirmationDialog", "holder", "Lorg/wordpress/android/ui/jetpack/scan/ScanNavigationEvents$OpenFixThreatsConfirmationDialog;", "initActionableEmptyView", "Lorg/wordpress/android/databinding/ScanFragmentBinding;", "initAdapter", "initRecyclerView", "initViewModel", "site", "setupObservers", "showSnackbar", "Lorg/wordpress/android/ui/pages/SnackbarMessageHolder;", "updateContentLayout", "state", "Lorg/wordpress/android/ui/jetpack/scan/ScanViewModel$UiState$ContentUiState;", "updateErrorLayout", "Lorg/wordpress/android/ui/jetpack/scan/ScanViewModel$UiState$ErrorUiState;", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public final class ScanFragment extends androidx.fragment.app.Fragment {
    @javax.inject.Inject()
    public org.wordpress.android.util.image.ImageManager imageManager;
    @javax.inject.Inject()
    public org.wordpress.android.ui.utils.UiHelpers uiHelpers;
    @javax.inject.Inject()
    public androidx.lifecycle.ViewModelProvider.Factory viewModelFactory;
    private org.wordpress.android.ui.jetpack.scan.ScanViewModel viewModel;
    private androidx.appcompat.app.AlertDialog fixThreatsConfirmationDialog;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.jetpack.scan.ScanFragment.Companion Companion = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ARG_THREAT_ID = "arg_threat_id";
    
    public ScanFragment() {
        super();
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
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.ViewModelProvider.Factory getViewModelFactory() {
        return null;
    }
    
    public final void setViewModelFactory(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.ViewModelProvider.Factory p0) {
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void initDagger() {
    }
    
    private final void initRecyclerView(org.wordpress.android.databinding.ScanFragmentBinding $this$initRecyclerView) {
    }
    
    private final void initActionableEmptyView(org.wordpress.android.databinding.ScanFragmentBinding $this$initActionableEmptyView) {
    }
    
    private final void initAdapter(org.wordpress.android.databinding.ScanFragmentBinding $this$initAdapter) {
    }
    
    private final void initViewModel(org.wordpress.android.databinding.ScanFragmentBinding $this$initViewModel, org.wordpress.android.fluxc.model.SiteModel site) {
    }
    
    private final void setupObservers(org.wordpress.android.databinding.ScanFragmentBinding $this$setupObservers) {
    }
    
    private final void updateContentLayout(org.wordpress.android.databinding.ScanFragmentBinding $this$updateContentLayout, org.wordpress.android.ui.jetpack.scan.ScanViewModel.UiState.ContentUiState state) {
    }
    
    private final void updateErrorLayout(org.wordpress.android.databinding.ScanFragmentBinding $this$updateErrorLayout, org.wordpress.android.ui.jetpack.scan.ScanViewModel.UiState.ErrorUiState state) {
    }
    
    private final void showSnackbar(org.wordpress.android.ui.pages.SnackbarMessageHolder $this$showSnackbar) {
    }
    
    private final void showFixThreatsConfirmationDialog(org.wordpress.android.ui.jetpack.scan.ScanNavigationEvents.OpenFixThreatsConfirmationDialog holder) {
    }
    
    @java.lang.Override()
    public void onPause() {
    }
    
    private final org.wordpress.android.fluxc.model.SiteModel getSite(android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override()
    public void onSaveInstanceState(@org.jetbrains.annotations.NotNull()
    android.os.Bundle outState) {
    }
    
    public final void onNewIntent(@org.jetbrains.annotations.Nullable()
    android.content.Intent intent) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lorg/wordpress/android/ui/jetpack/scan/ScanFragment$Companion;", "", "()V", "ARG_THREAT_ID", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}