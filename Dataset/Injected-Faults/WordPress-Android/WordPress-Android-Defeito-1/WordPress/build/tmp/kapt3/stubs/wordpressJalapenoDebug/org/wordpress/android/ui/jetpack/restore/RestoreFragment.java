package org.wordpress.android.ui.jetpack.restore;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0017\u001a\u00020\u0018H\u0002J\b\u0010\u0019\u001a\u00020\u0018H\u0002J\b\u0010\u001a\u001a\u00020\u0018H\u0002J\u0010\u0010\u001b\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\u001a\u0010\u001e\u001a\u00020\u00182\u0006\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\u001dH\u0016J\u0010\u0010\"\u001a\u00020\u00182\u0006\u0010#\u001a\u00020$H\u0002J\f\u0010%\u001a\u00020\u0018*\u00020&H\u0002J\f\u0010\'\u001a\u00020\u0018*\u00020&H\u0002J\u0016\u0010(\u001a\u00020\u0018*\u00020&2\b\u0010!\u001a\u0004\u0018\u00010\u001dH\u0002J\f\u0010)\u001a\u00020\u0018*\u00020*H\u0002J\u0014\u0010+\u001a\u00020\u0018*\u00020&2\u0006\u0010,\u001a\u00020-H\u0002R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001e\u0010\t\u001a\u00020\n8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0011\u001a\u00020\u00128\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016\u00a8\u0006."}, d2 = {"Lorg/wordpress/android/ui/jetpack/restore/RestoreFragment;", "Landroidx/fragment/app/Fragment;", "()V", "imageManager", "Lorg/wordpress/android/util/image/ImageManager;", "getImageManager", "()Lorg/wordpress/android/util/image/ImageManager;", "setImageManager", "(Lorg/wordpress/android/util/image/ImageManager;)V", "uiHelpers", "Lorg/wordpress/android/ui/utils/UiHelpers;", "getUiHelpers", "()Lorg/wordpress/android/ui/utils/UiHelpers;", "setUiHelpers", "(Lorg/wordpress/android/ui/utils/UiHelpers;)V", "viewModel", "Lorg/wordpress/android/ui/jetpack/restore/RestoreViewModel;", "viewModelFactory", "Landroidx/lifecycle/ViewModelProvider$Factory;", "getViewModelFactory", "()Landroidx/lifecycle/ViewModelProvider$Factory;", "setViewModelFactory", "(Landroidx/lifecycle/ViewModelProvider$Factory;)V", "initBackPressHandler", "", "initDagger", "onBackPressed", "onSaveInstanceState", "outState", "Landroid/os/Bundle;", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "updateToolbar", "toolbarState", "Lorg/wordpress/android/ui/jetpack/restore/ToolbarState;", "initAdapter", "Lorg/wordpress/android/databinding/JetpackBackupRestoreFragmentBinding;", "initObservers", "initViewModel", "showSnackbar", "Lorg/wordpress/android/ui/pages/SnackbarMessageHolder;", "showView", "state", "Lorg/wordpress/android/ui/jetpack/restore/RestoreUiState;", "org.wordpress.android_wordpressJalapenoDebug"})
public final class RestoreFragment extends androidx.fragment.app.Fragment {
    @javax.inject.Inject()
    public androidx.lifecycle.ViewModelProvider.Factory viewModelFactory;
    @javax.inject.Inject()
    public org.wordpress.android.ui.utils.UiHelpers uiHelpers;
    @javax.inject.Inject()
    public org.wordpress.android.util.image.ImageManager imageManager;
    private org.wordpress.android.ui.jetpack.restore.RestoreViewModel viewModel;
    
    public RestoreFragment() {
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
    public final org.wordpress.android.util.image.ImageManager getImageManager() {
        return null;
    }
    
    public final void setImageManager(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.image.ImageManager p0) {
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void initDagger() {
    }
    
    private final void initBackPressHandler() {
    }
    
    private final void onBackPressed() {
    }
    
    private final void initAdapter(org.wordpress.android.databinding.JetpackBackupRestoreFragmentBinding $this$initAdapter) {
    }
    
    private final void initViewModel(org.wordpress.android.databinding.JetpackBackupRestoreFragmentBinding $this$initViewModel, android.os.Bundle savedInstanceState) {
    }
    
    private final void initObservers(org.wordpress.android.databinding.JetpackBackupRestoreFragmentBinding $this$initObservers) {
    }
    
    private final void showView(org.wordpress.android.databinding.JetpackBackupRestoreFragmentBinding $this$showView, org.wordpress.android.ui.jetpack.restore.RestoreUiState state) {
    }
    
    private final void updateToolbar(org.wordpress.android.ui.jetpack.restore.ToolbarState toolbarState) {
    }
    
    private final void showSnackbar(org.wordpress.android.ui.pages.SnackbarMessageHolder $this$showSnackbar) {
    }
    
    @java.lang.Override()
    public void onSaveInstanceState(@org.jetbrains.annotations.NotNull()
    android.os.Bundle outState) {
    }
}