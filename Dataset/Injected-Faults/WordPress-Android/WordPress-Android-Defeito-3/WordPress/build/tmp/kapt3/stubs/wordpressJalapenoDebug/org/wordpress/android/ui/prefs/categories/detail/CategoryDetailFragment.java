package org.wordpress.android.ui.prefs.categories.detail;

import java.lang.System;

@kotlin.Suppress(names = {"TooManyFunctions"})
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0017\u001a\u00020\u0018H\u0002J\b\u0010\u0019\u001a\u00020\u0018H\u0002J \u0010\u001a\u001a\u00020\u00182\u0016\u0010\u001b\u001a\u0012\u0012\u0004\u0012\u00020\u001d0\u001cj\b\u0012\u0004\u0012\u00020\u001d`\u001eH\u0002J\u001a\u0010\u001f\u001a\u00020\u00182\u0006\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0016J\u0010\u0010$\u001a\u00020\u00182\u0006\u0010%\u001a\u00020&H\u0002J\u0010\u0010\'\u001a\u00020\u00182\u0006\u0010%\u001a\u00020&H\u0002J\u0012\u0010(\u001a\u00020\u00182\b\b\u0001\u0010)\u001a\u00020*H\u0002J\u0010\u0010+\u001a\u00020\u00182\u0006\u0010,\u001a\u00020&H\u0002J\f\u0010-\u001a\u00020\u0018*\u00020.H\u0002J\f\u0010/\u001a\u00020\u0018*\u00020.H\u0002J\f\u00100\u001a\u00020\u0018*\u00020.H\u0002J\f\u00101\u001a\u00020\u0018*\u00020.H\u0002J\f\u00102\u001a\u00020\u0018*\u00020.H\u0002J\f\u00103\u001a\u00020\u0018*\u00020.H\u0002J\u0014\u00104\u001a\u00020\u0018*\u00020.2\u0006\u00105\u001a\u000206H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\t\u001a\u00020\n8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0011\u001a\u00020\u00128\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016\u00a8\u00067"}, d2 = {"Lorg/wordpress/android/ui/prefs/categories/detail/CategoryDetailFragment;", "Landroidx/fragment/app/Fragment;", "()V", "categoryAdapter", "Lorg/wordpress/android/ui/posts/ParentCategorySpinnerAdapter;", "progressDialog", "Landroid/app/ProgressDialog;", "spinnerTouched", "", "uiHelpers", "Lorg/wordpress/android/ui/utils/UiHelpers;", "getUiHelpers", "()Lorg/wordpress/android/ui/utils/UiHelpers;", "setUiHelpers", "(Lorg/wordpress/android/ui/utils/UiHelpers;)V", "viewModel", "Lorg/wordpress/android/ui/prefs/categories/detail/CategoryDetailViewModel;", "viewModelFactory", "Landroidx/lifecycle/ViewModelProvider$Factory;", "getViewModelFactory", "()Landroidx/lifecycle/ViewModelProvider$Factory;", "setViewModelFactory", "(Landroidx/lifecycle/ViewModelProvider$Factory;)V", "hideProgressDialog", "", "initDagger", "loadCategories", "categoryLevels", "Ljava/util/ArrayList;", "Lorg/wordpress/android/models/CategoryNode;", "Lkotlin/collections/ArrayList;", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "showPostError", "message", "Lorg/wordpress/android/ui/utils/UiString;", "showPostSuccess", "showProgressDialog", "messageId", "", "showToast", "uiString", "initAdapter", "Lorg/wordpress/android/databinding/CategoryDetailFragmentBinding;", "initInputText", "initSpinner", "initSubmitButton", "initViewModel", "startObserving", "updateSubmitButton", "submitButtonUiState", "Lorg/wordpress/android/ui/prefs/categories/detail/SubmitButtonUiState;", "org.wordpress.android_wordpressJalapenoDebug"})
public final class CategoryDetailFragment extends androidx.fragment.app.Fragment {
    @javax.inject.Inject()
    public androidx.lifecycle.ViewModelProvider.Factory viewModelFactory;
    private org.wordpress.android.ui.prefs.categories.detail.CategoryDetailViewModel viewModel;
    @javax.inject.Inject()
    public org.wordpress.android.ui.utils.UiHelpers uiHelpers;
    private org.wordpress.android.ui.posts.ParentCategorySpinnerAdapter categoryAdapter;
    private boolean spinnerTouched = false;
    private android.app.ProgressDialog progressDialog;
    
    public CategoryDetailFragment() {
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
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void initDagger() {
    }
    
    private final void initAdapter(org.wordpress.android.databinding.CategoryDetailFragmentBinding $this$initAdapter) {
    }
    
    private final void initSubmitButton(org.wordpress.android.databinding.CategoryDetailFragmentBinding $this$initSubmitButton) {
    }
    
    private final void initSpinner(org.wordpress.android.databinding.CategoryDetailFragmentBinding $this$initSpinner) {
    }
    
    @kotlin.Suppress(names = {"EmptyFunctionBlock"})
    private final void initInputText(org.wordpress.android.databinding.CategoryDetailFragmentBinding $this$initInputText) {
    }
    
    private final void initViewModel(org.wordpress.android.databinding.CategoryDetailFragmentBinding $this$initViewModel) {
    }
    
    private final void startObserving(org.wordpress.android.databinding.CategoryDetailFragmentBinding $this$startObserving) {
    }
    
    private final void updateSubmitButton(org.wordpress.android.databinding.CategoryDetailFragmentBinding $this$updateSubmitButton, org.wordpress.android.ui.prefs.categories.detail.SubmitButtonUiState submitButtonUiState) {
    }
    
    private final void loadCategories(java.util.ArrayList<org.wordpress.android.models.CategoryNode> categoryLevels) {
    }
    
    private final void showProgressDialog(@androidx.annotation.StringRes()
    int messageId) {
    }
    
    private final void hideProgressDialog() {
    }
    
    private final void showPostError(org.wordpress.android.ui.utils.UiString message) {
    }
    
    private final void showPostSuccess(org.wordpress.android.ui.utils.UiString message) {
    }
    
    private final void showToast(org.wordpress.android.ui.utils.UiString uiString) {
    }
}