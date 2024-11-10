package org.wordpress.android.ui.layoutpicker;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b&\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\b\u0010\u0017\u001a\u00020\u0018H&J\b\u0010\u0019\u001a\u00020\u000eH&J\b\u0010\u001a\u001a\u00020\u0016H\u0002J\b\u0010\u001b\u001a\u00020\u0016H\u0002J\u0010\u0010\u001c\u001a\u00020\u00162\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J&\u0010\u001f\u001a\u0004\u0018\u00010 2\u0006\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010$2\b\u0010%\u001a\u0004\u0018\u00010&H\u0016J\b\u0010\'\u001a\u00020\u0016H\u0016J\u001a\u0010(\u001a\u00020\u00162\u0006\u0010)\u001a\u00020 2\b\u0010%\u001a\u0004\u0018\u00010&H\u0017J\u0018\u0010*\u001a\u00020\u00162\u0006\u0010)\u001a\u00020 2\u0006\u0010+\u001a\u00020\u0018H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u000f\u001a\u00020\u00108\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014\u00a8\u0006,"}, d2 = {"Lorg/wordpress/android/ui/layoutpicker/LayoutPreviewFragment;", "Lorg/wordpress/android/ui/FullscreenBottomSheetDialogFragment;", "()V", "binding", "Lorg/wordpress/android/databinding/LayoutPickerPreviewFragmentBinding;", "displayUtilsWrapper", "Lorg/wordpress/android/util/DisplayUtilsWrapper;", "getDisplayUtilsWrapper", "()Lorg/wordpress/android/util/DisplayUtilsWrapper;", "setDisplayUtilsWrapper", "(Lorg/wordpress/android/util/DisplayUtilsWrapper;)V", "previewModeSelectorPopup", "Lorg/wordpress/android/ui/PreviewModeSelectorPopup;", "viewModel", "Lorg/wordpress/android/ui/layoutpicker/LayoutPickerViewModel;", "viewModelFactory", "Landroidx/lifecycle/ViewModelProvider$Factory;", "getViewModelFactory", "()Landroidx/lifecycle/ViewModelProvider$Factory;", "setViewModelFactory", "(Landroidx/lifecycle/ViewModelProvider$Factory;)V", "closeModal", "", "getChooseButtonText", "", "getViewModel", "initViewModel", "load", "onAttach", "context", "Landroid/content/Context;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onViewCreated", "view", "setWebViewWidth", "previewWidth", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class LayoutPreviewFragment extends org.wordpress.android.ui.FullscreenBottomSheetDialogFragment {
    @javax.inject.Inject()
    public org.wordpress.android.util.DisplayUtilsWrapper displayUtilsWrapper;
    @javax.inject.Inject()
    public androidx.lifecycle.ViewModelProvider.Factory viewModelFactory;
    private org.wordpress.android.ui.layoutpicker.LayoutPickerViewModel viewModel;
    private org.wordpress.android.ui.PreviewModeSelectorPopup previewModeSelectorPopup;
    private org.wordpress.android.databinding.LayoutPickerPreviewFragmentBinding binding;
    
    public LayoutPreviewFragment() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.util.DisplayUtilsWrapper getDisplayUtilsWrapper() {
        return null;
    }
    
    public final void setDisplayUtilsWrapper(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.DisplayUtilsWrapper p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.ViewModelProvider.Factory getViewModelFactory() {
        return null;
    }
    
    public final void setViewModelFactory(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.ViewModelProvider.Factory p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @android.annotation.SuppressLint(value = {"SetJavaScriptEnabled"})
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    public abstract int getChooseButtonText();
    
    @org.jetbrains.annotations.NotNull()
    public abstract org.wordpress.android.ui.layoutpicker.LayoutPickerViewModel getViewModel();
    
    private final void initViewModel() {
    }
    
    @java.lang.Override()
    public void closeModal() {
    }
    
    private final void load() {
    }
    
    private final void setWebViewWidth(android.view.View view, int previewWidth) {
    }
    
    @java.lang.Override()
    public void onAttach(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
}