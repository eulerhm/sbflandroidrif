package org.wordpress.android.ui.mlp;

import java.lang.System;

/**
 * Implements the Modal Layout Picker UI
 */
@kotlin.Suppress(names = {"TooManyFunctions"})
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\u0018\u0000 72\u00020\u0001:\u00017B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0019\u001a\u00020\u001aH\u0016J\u0010\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J&\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\u0006\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010#2\b\u0010$\u001a\u0004\u0018\u00010%H\u0016J\u0010\u0010&\u001a\u00020\u001a2\u0006\u0010\'\u001a\u00020%H\u0016J\u001a\u0010(\u001a\u00020\u001a2\u0006\u0010)\u001a\u00020\u001f2\b\u0010$\u001a\u0004\u0018\u00010%H\u0016J\u0014\u0010*\u001a\u00020\u001a*\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\u001c\u0010.\u001a\u00020\u001a*\u00020+2\u0006\u0010/\u001a\u0002002\u0006\u00101\u001a\u000200H\u0002J\u0014\u00102\u001a\u00020\u001a*\u00020+2\u0006\u00103\u001a\u000200H\u0002J\u0014\u00104\u001a\u00020\u001a*\u00020+2\u0006\u00103\u001a\u000200H\u0002J\f\u00105\u001a\u00020\u001a*\u00020+H\u0002J\u0016\u00106\u001a\u00020\u001a*\u00020+2\b\u0010$\u001a\u0004\u0018\u00010%H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001e\u0010\u000b\u001a\u00020\f8\u0000@\u0000X\u0081.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0013\u001a\u00020\u00148\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018\u00a8\u00068"}, d2 = {"Lorg/wordpress/android/ui/mlp/ModalLayoutPickerFragment;", "Lorg/wordpress/android/ui/FullscreenBottomSheetDialogFragment;", "()V", "previewModeSelectorPopup", "Lorg/wordpress/android/ui/PreviewModeSelectorPopup;", "thumbDimensionProvider", "Lorg/wordpress/android/ui/mlp/ModalLayoutPickerDimensionProvider;", "getThumbDimensionProvider", "()Lorg/wordpress/android/ui/mlp/ModalLayoutPickerDimensionProvider;", "setThumbDimensionProvider", "(Lorg/wordpress/android/ui/mlp/ModalLayoutPickerDimensionProvider;)V", "uiHelper", "Lorg/wordpress/android/ui/utils/UiHelpers;", "getUiHelper$org_wordpress_android_wordpressJalapenoDebug", "()Lorg/wordpress/android/ui/utils/UiHelpers;", "setUiHelper$org_wordpress_android_wordpressJalapenoDebug", "(Lorg/wordpress/android/ui/utils/UiHelpers;)V", "viewModel", "Lorg/wordpress/android/viewmodel/mlp/ModalLayoutPickerViewModel;", "viewModelFactory", "Landroidx/lifecycle/ViewModelProvider$Factory;", "getViewModelFactory", "()Landroidx/lifecycle/ViewModelProvider$Factory;", "setViewModelFactory", "(Landroidx/lifecycle/ViewModelProvider$Factory;)V", "closeModal", "", "onAttach", "context", "Landroid/content/Context;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onSaveInstanceState", "outState", "onViewCreated", "view", "setButtonsVisibility", "Lorg/wordpress/android/databinding/ModalLayoutPickerFragmentBinding;", "uiState", "Lorg/wordpress/android/ui/layoutpicker/ButtonsUiState;", "setContentVisibility", "skeleton", "", "error", "setDescriptionVisibility", "visible", "setHeaderVisibility", "setScrollListener", "setupViewModel", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public final class ModalLayoutPickerFragment extends org.wordpress.android.ui.FullscreenBottomSheetDialogFragment {
    @javax.inject.Inject()
    public org.wordpress.android.ui.utils.UiHelpers uiHelper;
    @javax.inject.Inject()
    public androidx.lifecycle.ViewModelProvider.Factory viewModelFactory;
    @javax.inject.Inject()
    public org.wordpress.android.ui.mlp.ModalLayoutPickerDimensionProvider thumbDimensionProvider;
    private org.wordpress.android.viewmodel.mlp.ModalLayoutPickerViewModel viewModel;
    private org.wordpress.android.ui.PreviewModeSelectorPopup previewModeSelectorPopup;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.mlp.ModalLayoutPickerFragment.Companion Companion = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String MODAL_LAYOUT_PICKER_TAG = "MODAL_LAYOUT_PICKER_TAG";
    
    public ModalLayoutPickerFragment() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.utils.UiHelpers getUiHelper$org_wordpress_android_wordpressJalapenoDebug() {
        return null;
    }
    
    public final void setUiHelper$org_wordpress_android_wordpressJalapenoDebug(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.utils.UiHelpers p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.ViewModelProvider.Factory getViewModelFactory() {
        return null;
    }
    
    public final void setViewModelFactory(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.ViewModelProvider.Factory p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.mlp.ModalLayoutPickerDimensionProvider getThumbDimensionProvider() {
        return null;
    }
    
    public final void setThumbDimensionProvider(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mlp.ModalLayoutPickerDimensionProvider p0) {
    }
    
    @java.lang.Override()
    public void onAttach(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setScrollListener(org.wordpress.android.databinding.ModalLayoutPickerFragmentBinding $this$setScrollListener) {
    }
    
    private final void setupViewModel(org.wordpress.android.databinding.ModalLayoutPickerFragmentBinding $this$setupViewModel, android.os.Bundle savedInstanceState) {
    }
    
    private final void setHeaderVisibility(org.wordpress.android.databinding.ModalLayoutPickerFragmentBinding $this$setHeaderVisibility, boolean visible) {
    }
    
    /**
     * Sets the header description visibility
     * @param visible if true the description is visible else invisible
     */
    private final void setDescriptionVisibility(org.wordpress.android.databinding.ModalLayoutPickerFragmentBinding $this$setDescriptionVisibility, boolean visible) {
    }
    
    private final void setButtonsVisibility(org.wordpress.android.databinding.ModalLayoutPickerFragmentBinding $this$setButtonsVisibility, org.wordpress.android.ui.layoutpicker.ButtonsUiState uiState) {
    }
    
    private final void setContentVisibility(org.wordpress.android.databinding.ModalLayoutPickerFragmentBinding $this$setContentVisibility, boolean skeleton, boolean error) {
    }
    
    @java.lang.Override()
    public void closeModal() {
    }
    
    @java.lang.Override()
    public void onSaveInstanceState(@org.jetbrains.annotations.NotNull()
    android.os.Bundle outState) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lorg/wordpress/android/ui/mlp/ModalLayoutPickerFragment$Companion;", "", "()V", "MODAL_LAYOUT_PICKER_TAG", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}