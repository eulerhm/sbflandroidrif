package org.wordpress.android.ui.posts;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 ;2\u00020\u0001:\u0001;B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u0012\u0010\u001f\u001a\u00020\u001c2\b\u0010 \u001a\u0004\u0018\u00010!H\u0016J\b\u0010\"\u001a\u00020\u001cH\u0016J\b\u0010#\u001a\u00020\u001cH\u0016J\u001a\u0010$\u001a\u00020\u001c2\u0006\u0010%\u001a\u00020&2\b\u0010 \u001a\u0004\u0018\u00010!H\u0016J\f\u0010\'\u001a\u00020\u001c*\u00020(H\u0002J\f\u0010)\u001a\u00020\u001c*\u00020*H\u0002J\f\u0010+\u001a\u00020\u001c*\u00020(H\u0002J\f\u0010,\u001a\u00020\u001c*\u00020(H\u0002J\f\u0010-\u001a\u00020\u001c*\u00020(H\u0002J\f\u0010.\u001a\u00020\u001c*\u00020/H\u0002J$\u00100\u001a\u00020\u001c*\u00020(2\u0016\u00101\u001a\u0012\u0012\u0004\u0012\u00020302j\b\u0012\u0004\u0012\u000203`4H\u0002J\f\u00105\u001a\u00020\u001c*\u000206H\u0002J\f\u00107\u001a\u00020\u001c*\u00020/H\u0002J\u0014\u00108\u001a\u00020\u001c*\u00020(2\u0006\u00109\u001a\u00020:H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001e\u0010\r\u001a\u00020\u000e8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0015\u001a\u00020\u00168\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001a\u00a8\u0006<"}, d2 = {"Lorg/wordpress/android/ui/posts/PrepublishingAddCategoryFragment;", "Landroidx/fragment/app/Fragment;", "()V", "closeListener", "Lorg/wordpress/android/ui/posts/PrepublishingScreenClosedListener;", "parentViewModel", "Lorg/wordpress/android/ui/posts/PrepublishingViewModel;", "spinnerTouched", "", "getSpinnerTouched", "()Z", "setSpinnerTouched", "(Z)V", "uiHelpers", "Lorg/wordpress/android/ui/utils/UiHelpers;", "getUiHelpers", "()Lorg/wordpress/android/ui/utils/UiHelpers;", "setUiHelpers", "(Lorg/wordpress/android/ui/utils/UiHelpers;)V", "viewModel", "Lorg/wordpress/android/ui/posts/PrepublishingAddCategoryViewModel;", "viewModelFactory", "Landroidx/lifecycle/ViewModelProvider$Factory;", "getViewModelFactory", "()Landroidx/lifecycle/ViewModelProvider$Factory;", "setViewModelFactory", "(Landroidx/lifecycle/ViewModelProvider$Factory;)V", "onAttach", "", "context", "Landroid/content/Context;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDetach", "onResume", "onViewCreated", "view", "Landroid/view/View;", "initAdapter", "Lorg/wordpress/android/databinding/AddCategoryBinding;", "initBackButton", "Lorg/wordpress/android/databinding/PrepublishingToolbarBinding;", "initInputText", "initSpinner", "initSubmitButton", "initViewModel", "Lorg/wordpress/android/databinding/PrepublishingAddCategoryFragmentBinding;", "loadCategories", "categoryLevels", "Ljava/util/ArrayList;", "Lorg/wordpress/android/models/CategoryNode;", "Lkotlin/collections/ArrayList;", "showToast", "Lorg/wordpress/android/ui/pages/SnackbarMessageHolder;", "startObserving", "updateSubmitButton", "submitButtonUiState", "Lorg/wordpress/android/ui/posts/PrepublishingAddCategoryViewModel$SubmitButtonUiState;", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public final class PrepublishingAddCategoryFragment extends androidx.fragment.app.Fragment {
    private org.wordpress.android.ui.posts.PrepublishingScreenClosedListener closeListener;
    @javax.inject.Inject()
    public androidx.lifecycle.ViewModelProvider.Factory viewModelFactory;
    private org.wordpress.android.ui.posts.PrepublishingAddCategoryViewModel viewModel;
    private org.wordpress.android.ui.posts.PrepublishingViewModel parentViewModel;
    @javax.inject.Inject()
    public org.wordpress.android.ui.utils.UiHelpers uiHelpers;
    private boolean spinnerTouched = false;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.posts.PrepublishingAddCategoryFragment.Companion Companion = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String TAG = "prepublishing_add_category_fragment_tag";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String NEEDS_REQUEST_LAYOUT = "prepublishing_add_category_fragment_needs_request_layout";
    
    public PrepublishingAddCategoryFragment() {
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
    
    public final boolean getSpinnerTouched() {
        return false;
    }
    
    public final void setSpinnerTouched(boolean p0) {
    }
    
    @java.lang.Override()
    public void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void onAttach(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    @java.lang.Override()
    public void onDetach() {
    }
    
    @java.lang.Override()
    public void onResume() {
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void initBackButton(org.wordpress.android.databinding.PrepublishingToolbarBinding $this$initBackButton) {
    }
    
    private final void initSubmitButton(org.wordpress.android.databinding.AddCategoryBinding $this$initSubmitButton) {
    }
    
    private final void initInputText(org.wordpress.android.databinding.AddCategoryBinding $this$initInputText) {
    }
    
    private final void initAdapter(org.wordpress.android.databinding.AddCategoryBinding $this$initAdapter) {
    }
    
    private final void initSpinner(org.wordpress.android.databinding.AddCategoryBinding $this$initSpinner) {
    }
    
    private final void initViewModel(org.wordpress.android.databinding.PrepublishingAddCategoryFragmentBinding $this$initViewModel) {
    }
    
    private final void startObserving(org.wordpress.android.databinding.PrepublishingAddCategoryFragmentBinding $this$startObserving) {
    }
    
    private final void updateSubmitButton(org.wordpress.android.databinding.AddCategoryBinding $this$updateSubmitButton, org.wordpress.android.ui.posts.PrepublishingAddCategoryViewModel.SubmitButtonUiState submitButtonUiState) {
    }
    
    private final void loadCategories(org.wordpress.android.databinding.AddCategoryBinding $this$loadCategories, java.util.ArrayList<org.wordpress.android.models.CategoryNode> categoryLevels) {
    }
    
    private final void showToast(org.wordpress.android.ui.pages.SnackbarMessageHolder $this$showToast) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @kotlin.jvm.JvmStatic()
    public static final org.wordpress.android.ui.posts.PrepublishingAddCategoryFragment newInstance(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, boolean needsRequestLayout, @org.jetbrains.annotations.Nullable()
    android.os.Bundle bundle) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J$\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\rH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2 = {"Lorg/wordpress/android/ui/posts/PrepublishingAddCategoryFragment$Companion;", "", "()V", "NEEDS_REQUEST_LAYOUT", "", "TAG", "newInstance", "Lorg/wordpress/android/ui/posts/PrepublishingAddCategoryFragment;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "needsRequestLayout", "", "bundle", "Landroid/os/Bundle;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        @kotlin.jvm.JvmStatic()
        public final org.wordpress.android.ui.posts.PrepublishingAddCategoryFragment newInstance(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.SiteModel site, boolean needsRequestLayout, @org.jetbrains.annotations.Nullable()
        android.os.Bundle bundle) {
            return null;
        }
    }
}