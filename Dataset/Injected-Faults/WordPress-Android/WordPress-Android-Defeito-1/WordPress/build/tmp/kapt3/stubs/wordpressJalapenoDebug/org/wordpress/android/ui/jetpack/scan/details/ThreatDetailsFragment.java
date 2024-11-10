package org.wordpress.android.ui.jetpack.scan.details;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0019\u001a\u00020\u001aH\u0002J\u0012\u0010\u001b\u001a\u00020\u001a2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J\b\u0010\u001e\u001a\u00020\u001aH\u0016J\u001a\u0010\u001f\u001a\u00020\u001a2\u0006\u0010 \u001a\u00020!2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J\u0010\u0010\"\u001a\u00020\u001a2\u0006\u0010#\u001a\u00020$H\u0002J\f\u0010%\u001a\u00020\u001a*\u00020&H\u0002J\f\u0010\'\u001a\u00020\u001a*\u00020&H\u0002J\u0014\u0010(\u001a\u00020\u001a*\u00020&2\u0006\u0010)\u001a\u00020*H\u0002J\f\u0010+\u001a\u00020\u001a*\u00020&H\u0002J\f\u0010,\u001a\u00020\u001a*\u00020-H\u0002R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u000b\u001a\u00020\f8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0013\u001a\u00020\u00148\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018\u00a8\u0006."}, d2 = {"Lorg/wordpress/android/ui/jetpack/scan/details/ThreatDetailsFragment;", "Landroidx/fragment/app/Fragment;", "()V", "imageManager", "Lorg/wordpress/android/util/image/ImageManager;", "getImageManager", "()Lorg/wordpress/android/util/image/ImageManager;", "setImageManager", "(Lorg/wordpress/android/util/image/ImageManager;)V", "threatActionDialog", "Landroidx/appcompat/app/AlertDialog;", "uiHelpers", "Lorg/wordpress/android/ui/utils/UiHelpers;", "getUiHelpers", "()Lorg/wordpress/android/ui/utils/UiHelpers;", "setUiHelpers", "(Lorg/wordpress/android/ui/utils/UiHelpers;)V", "viewModel", "Lorg/wordpress/android/ui/jetpack/scan/details/ThreatDetailsViewModel;", "viewModelFactory", "Landroidx/lifecycle/ViewModelProvider$Factory;", "getViewModelFactory", "()Landroidx/lifecycle/ViewModelProvider$Factory;", "setViewModelFactory", "(Landroidx/lifecycle/ViewModelProvider$Factory;)V", "initDagger", "", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "onPause", "onViewCreated", "view", "Landroid/view/View;", "showThreatActionDialog", "holder", "Lorg/wordpress/android/ui/jetpack/scan/details/ThreatDetailsNavigationEvents$OpenThreatActionDialog;", "initAdapter", "Lorg/wordpress/android/databinding/ThreatDetailsFragmentBinding;", "initViewModel", "refreshContentScreen", "content", "Lorg/wordpress/android/ui/jetpack/scan/details/ThreatDetailsViewModel$UiState$Content;", "setupObservers", "showSnackbar", "Lorg/wordpress/android/ui/pages/SnackbarMessageHolder;", "org.wordpress.android_wordpressJalapenoDebug"})
public final class ThreatDetailsFragment extends androidx.fragment.app.Fragment {
    @javax.inject.Inject()
    public org.wordpress.android.util.image.ImageManager imageManager;
    @javax.inject.Inject()
    public org.wordpress.android.ui.utils.UiHelpers uiHelpers;
    @javax.inject.Inject()
    public androidx.lifecycle.ViewModelProvider.Factory viewModelFactory;
    private org.wordpress.android.ui.jetpack.scan.details.ThreatDetailsViewModel viewModel;
    private androidx.appcompat.app.AlertDialog threatActionDialog;
    
    public ThreatDetailsFragment() {
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
    
    private final void initAdapter(org.wordpress.android.databinding.ThreatDetailsFragmentBinding $this$initAdapter) {
    }
    
    private final void initViewModel(org.wordpress.android.databinding.ThreatDetailsFragmentBinding $this$initViewModel) {
    }
    
    private final void setupObservers(org.wordpress.android.databinding.ThreatDetailsFragmentBinding $this$setupObservers) {
    }
    
    private final void refreshContentScreen(org.wordpress.android.databinding.ThreatDetailsFragmentBinding $this$refreshContentScreen, org.wordpress.android.ui.jetpack.scan.details.ThreatDetailsViewModel.UiState.Content content) {
    }
    
    private final void showSnackbar(org.wordpress.android.ui.pages.SnackbarMessageHolder $this$showSnackbar) {
    }
    
    private final void showThreatActionDialog(org.wordpress.android.ui.jetpack.scan.details.ThreatDetailsNavigationEvents.OpenThreatActionDialog holder) {
    }
    
    @java.lang.Override()
    public void onActivityCreated(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void onPause() {
    }
}