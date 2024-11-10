package org.wordpress.android.ui.reader;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J&\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016J\u001a\u0010\u001a\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u00132\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n\u00a8\u0006\u001d"}, d2 = {"Lorg/wordpress/android/ui/reader/SubfilterBottomSheetFragment;", "Lcom/google/android/material/bottomsheet/BottomSheetDialogFragment;", "()V", "viewModel", "Lorg/wordpress/android/ui/reader/subfilter/SubFilterViewModel;", "viewModelFactory", "Landroidx/lifecycle/ViewModelProvider$Factory;", "getViewModelFactory", "()Landroidx/lifecycle/ViewModelProvider$Factory;", "setViewModelFactory", "(Landroidx/lifecycle/ViewModelProvider$Factory;)V", "onAttach", "", "context", "Landroid/content/Context;", "onCancel", "dialog", "Landroid/content/DialogInterface;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "view", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public final class SubfilterBottomSheetFragment extends com.google.android.material.bottomsheet.BottomSheetDialogFragment {
    @javax.inject.Inject()
    public androidx.lifecycle.ViewModelProvider.Factory viewModelFactory;
    private org.wordpress.android.ui.reader.subfilter.SubFilterViewModel viewModel;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.reader.SubfilterBottomSheetFragment.Companion Companion = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SUBFILTER_VIEW_MODEL_KEY = "subfilter_view_model_key";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SUBFILTER_TITLE_KEY = "subfilter_title_key";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SUBFILTER_CATEGORIES_KEY = "subfilter_categories_key";
    
    public SubfilterBottomSheetFragment() {
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
    @kotlin.jvm.JvmStatic()
    public static final org.wordpress.android.ui.reader.SubfilterBottomSheetFragment newInstance(@org.jetbrains.annotations.NotNull()
    java.lang.String subfilterViewModelKey, @org.jetbrains.annotations.NotNull()
    java.util.List<? extends org.wordpress.android.ui.reader.subfilter.SubfilterCategory> categories, @org.jetbrains.annotations.NotNull()
    java.lang.CharSequence title) {
        return null;
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
    
    @java.lang.Override()
    public void onAttach(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    @java.lang.Override()
    public void onCancel(@org.jetbrains.annotations.NotNull()
    android.content.DialogInterface dialog) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J&\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00042\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\r\u001a\u00020\u000eH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lorg/wordpress/android/ui/reader/SubfilterBottomSheetFragment$Companion;", "", "()V", "SUBFILTER_CATEGORIES_KEY", "", "SUBFILTER_TITLE_KEY", "SUBFILTER_VIEW_MODEL_KEY", "newInstance", "Lorg/wordpress/android/ui/reader/SubfilterBottomSheetFragment;", "subfilterViewModelKey", "categories", "", "Lorg/wordpress/android/ui/reader/subfilter/SubfilterCategory;", "title", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        @kotlin.jvm.JvmStatic()
        public final org.wordpress.android.ui.reader.SubfilterBottomSheetFragment newInstance(@org.jetbrains.annotations.NotNull()
        java.lang.String subfilterViewModelKey, @org.jetbrains.annotations.NotNull()
        java.util.List<? extends org.wordpress.android.ui.reader.subfilter.SubfilterCategory> categories, @org.jetbrains.annotations.NotNull()
        java.lang.CharSequence title) {
            return null;
        }
    }
}