package org.wordpress.android.ui.posts;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u008a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 =2\u00020\u0001:\u0001=B\u0005\u00a2\u0006\u0002\u0010\u0002J\n\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0002J\b\u0010\u001f\u001a\u00020 H\u0002J\u0010\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$H\u0016J\u0012\u0010%\u001a\u00020\"2\b\u0010&\u001a\u0004\u0018\u00010\'H\u0016J\b\u0010(\u001a\u00020\"H\u0016J\b\u0010)\u001a\u00020\"H\u0016J\b\u0010*\u001a\u00020\"H\u0016J\b\u0010+\u001a\u00020\"H\u0016J\u0010\u0010,\u001a\u00020\"2\u0006\u0010-\u001a\u00020.H\u0007J\u0010\u0010/\u001a\u00020\"2\u0006\u0010-\u001a\u000200H\u0007J\u001a\u00101\u001a\u00020\"2\u0006\u00102\u001a\u0002032\b\u0010&\u001a\u0004\u0018\u00010\'H\u0016J\f\u00104\u001a\u00020\"*\u000205H\u0002J\f\u00106\u001a\u00020\"*\u000205H\u0002J\f\u00107\u001a\u00020\"*\u000208H\u0002J\f\u00109\u001a\u00020\"*\u000208H\u0002J\f\u0010:\u001a\u00020\"*\u00020;H\u0002J\f\u0010<\u001a\u00020\"*\u000208H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0007\u001a\u00020\b8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u000f\u001a\u00020\u00108\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0017\u001a\u00020\u00188\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001c\u00a8\u0006>"}, d2 = {"Lorg/wordpress/android/ui/posts/PrepublishingCategoriesFragment;", "Landroidx/fragment/app/Fragment;", "()V", "actionListener", "Lorg/wordpress/android/ui/posts/PrepublishingActionClickedListener;", "closeListener", "Lorg/wordpress/android/ui/posts/PrepublishingScreenClosedListener;", "dispatcher", "Lorg/wordpress/android/fluxc/Dispatcher;", "getDispatcher", "()Lorg/wordpress/android/fluxc/Dispatcher;", "setDispatcher", "(Lorg/wordpress/android/fluxc/Dispatcher;)V", "parentViewModel", "Lorg/wordpress/android/ui/posts/PrepublishingViewModel;", "uiHelpers", "Lorg/wordpress/android/ui/utils/UiHelpers;", "getUiHelpers", "()Lorg/wordpress/android/ui/utils/UiHelpers;", "setUiHelpers", "(Lorg/wordpress/android/ui/utils/UiHelpers;)V", "viewModel", "Lorg/wordpress/android/ui/posts/PrepublishingCategoriesViewModel;", "viewModelFactory", "Landroidx/lifecycle/ViewModelProvider$Factory;", "getViewModelFactory", "()Landroidx/lifecycle/ViewModelProvider$Factory;", "setViewModelFactory", "(Landroidx/lifecycle/ViewModelProvider$Factory;)V", "getEditPostActivityHook", "Lorg/wordpress/android/ui/posts/EditPostSettingsFragment$EditPostActivityHook;", "getEditPostRepository", "Lorg/wordpress/android/ui/posts/EditPostRepository;", "onAttach", "", "context", "Landroid/content/Context;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDetach", "onResume", "onStart", "onStop", "onTaxonomyChanged", "event", "Lorg/wordpress/android/fluxc/store/TaxonomyStore$OnTaxonomyChanged;", "onTermUploaded", "Lorg/wordpress/android/fluxc/store/TaxonomyStore$OnTermUploaded;", "onViewCreated", "view", "Landroid/view/View;", "initAddCategoryButton", "Lorg/wordpress/android/databinding/PrepublishingToolbarBinding;", "initBackButton", "initRecyclerView", "Lorg/wordpress/android/databinding/PrepublishingCategoriesFragmentBinding;", "initViewModel", "showToast", "Lorg/wordpress/android/ui/pages/SnackbarMessageHolder;", "startObserving", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public final class PrepublishingCategoriesFragment extends androidx.fragment.app.Fragment {
    private org.wordpress.android.ui.posts.PrepublishingScreenClosedListener closeListener;
    private org.wordpress.android.ui.posts.PrepublishingActionClickedListener actionListener;
    @javax.inject.Inject()
    public androidx.lifecycle.ViewModelProvider.Factory viewModelFactory;
    private org.wordpress.android.ui.posts.PrepublishingCategoriesViewModel viewModel;
    private org.wordpress.android.ui.posts.PrepublishingViewModel parentViewModel;
    @javax.inject.Inject()
    public org.wordpress.android.ui.utils.UiHelpers uiHelpers;
    @javax.inject.Inject()
    public org.wordpress.android.fluxc.Dispatcher dispatcher;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.posts.PrepublishingCategoriesFragment.Companion Companion = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String TAG = "prepublishing_categories_fragment_tag";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String NEEDS_REQUEST_LAYOUT = "prepublishing_categories_fragment_needs_request_layout";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ADD_CATEGORY_REQUEST = "prepublishing_add_category_request";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SELECTED_CATEGORY_IDS = "prepublishing_selected_category_ids";
    
    public PrepublishingCategoriesFragment() {
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
    public final org.wordpress.android.fluxc.Dispatcher getDispatcher() {
        return null;
    }
    
    public final void setDispatcher(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.Dispatcher p0) {
    }
    
    @java.lang.Override()
    public void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void onStart() {
    }
    
    @java.lang.Override()
    public void onStop() {
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
    
    private final void initAddCategoryButton(org.wordpress.android.databinding.PrepublishingToolbarBinding $this$initAddCategoryButton) {
    }
    
    private final void initRecyclerView(org.wordpress.android.databinding.PrepublishingCategoriesFragmentBinding $this$initRecyclerView) {
    }
    
    private final void initViewModel(org.wordpress.android.databinding.PrepublishingCategoriesFragmentBinding $this$initViewModel) {
    }
    
    private final void startObserving(org.wordpress.android.databinding.PrepublishingCategoriesFragmentBinding $this$startObserving) {
    }
    
    private final org.wordpress.android.ui.posts.EditPostRepository getEditPostRepository() {
        return null;
    }
    
    private final org.wordpress.android.ui.posts.EditPostSettingsFragment.EditPostActivityHook getEditPostActivityHook() {
        return null;
    }
    
    private final void showToast(org.wordpress.android.ui.pages.SnackbarMessageHolder $this$showToast) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @kotlin.jvm.JvmStatic()
    public static final org.wordpress.android.ui.posts.PrepublishingCategoriesFragment newInstance(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, boolean needsRequestLayout, @org.jetbrains.annotations.Nullable()
    android.os.Bundle bundle) {
        return null;
    }
    
    @java.lang.SuppressWarnings(value = {"unused"})
    @org.greenrobot.eventbus.Subscribe(threadMode = org.greenrobot.eventbus.ThreadMode.MAIN)
    public final void onTermUploaded(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.TaxonomyStore.OnTermUploaded event) {
    }
    
    @java.lang.SuppressWarnings(value = {"unused"})
    @org.greenrobot.eventbus.Subscribe(threadMode = org.greenrobot.eventbus.ThreadMode.MAIN)
    public final void onTaxonomyChanged(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.TaxonomyStore.OnTaxonomyChanged event) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J$\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lorg/wordpress/android/ui/posts/PrepublishingCategoriesFragment$Companion;", "", "()V", "ADD_CATEGORY_REQUEST", "", "NEEDS_REQUEST_LAYOUT", "SELECTED_CATEGORY_IDS", "TAG", "newInstance", "Lorg/wordpress/android/ui/posts/PrepublishingCategoriesFragment;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "needsRequestLayout", "", "bundle", "Landroid/os/Bundle;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        @kotlin.jvm.JvmStatic()
        public final org.wordpress.android.ui.posts.PrepublishingCategoriesFragment newInstance(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.SiteModel site, boolean needsRequestLayout, @org.jetbrains.annotations.Nullable()
        android.os.Bundle bundle) {
            return null;
        }
    }
}