package org.wordpress.android.ui.prefs.categories.list;

import java.lang.System;

@kotlin.Suppress(names = {"TooManyFunctions"})
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0002J\b\u0010\u0017\u001a\u00020\u0018H\u0002J\u0010\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\u0010\u0010\u001c\u001a\u00020\u00182\u0006\u0010\u001d\u001a\u00020\u0016H\u0016J\u001a\u0010\u001e\u001a\u00020\u00182\u0006\u0010\u001f\u001a\u00020 2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J\u0016\u0010!\u001a\u00020\u00182\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u001b0#H\u0002J\f\u0010$\u001a\u00020\u0018*\u00020%H\u0002J\f\u0010&\u001a\u00020\u0018*\u00020%H\u0002J\f\u0010\'\u001a\u00020\u0018*\u00020%H\u0002J\u0014\u0010(\u001a\u00020\u0018*\u00020%2\u0006\u0010)\u001a\u00020\u0014H\u0002J\f\u0010*\u001a\u00020\u0018*\u00020%H\u0002J\u0014\u0010+\u001a\u00020\u0018*\u00020%2\u0006\u0010,\u001a\u00020-H\u0002J\u0012\u0010.\u001a\u00020\u0018*\u00020 2\u0006\u0010/\u001a\u000200R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\r\u001a\u00020\u000e8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012\u00a8\u00061"}, d2 = {"Lorg/wordpress/android/ui/prefs/categories/list/CategoriesListFragment;", "Landroidx/fragment/app/Fragment;", "()V", "adapter", "Lorg/wordpress/android/ui/prefs/categories/list/SiteSettingsCategoriesAdapter;", "uiHelpers", "Lorg/wordpress/android/ui/utils/UiHelpers;", "getUiHelpers", "()Lorg/wordpress/android/ui/utils/UiHelpers;", "setUiHelpers", "(Lorg/wordpress/android/ui/utils/UiHelpers;)V", "viewModel", "Lorg/wordpress/android/ui/prefs/categories/list/CategoriesListViewModel;", "viewModelFactory", "Landroidx/lifecycle/ViewModelProvider$Factory;", "getViewModelFactory", "()Landroidx/lifecycle/ViewModelProvider$Factory;", "setViewModelFactory", "(Landroidx/lifecycle/ViewModelProvider$Factory;)V", "getSite", "Lorg/wordpress/android/fluxc/model/SiteModel;", "savedInstanceState", "Landroid/os/Bundle;", "initDagger", "", "onCategoryRowClicked", "categoryNode", "Lorg/wordpress/android/models/CategoryNode;", "onSaveInstanceState", "outState", "onViewCreated", "view", "Landroid/view/View;", "updateContentLayout", "list", "", "initEmptyView", "Lorg/wordpress/android/databinding/SiteSettingsCategoriesListFragmentBinding;", "initFabButton", "initRecyclerView", "initViewModel", "site", "setupObservers", "updateErrorContent", "error", "Lorg/wordpress/android/ui/prefs/categories/list/UiState$Error;", "updateVisibility", "visible", "", "org.wordpress.android_wordpressJalapenoDebug"})
public final class CategoriesListFragment extends androidx.fragment.app.Fragment {
    @javax.inject.Inject()
    public androidx.lifecycle.ViewModelProvider.Factory viewModelFactory;
    private org.wordpress.android.ui.prefs.categories.list.CategoriesListViewModel viewModel;
    @javax.inject.Inject()
    public org.wordpress.android.ui.utils.UiHelpers uiHelpers;
    private org.wordpress.android.ui.prefs.categories.list.SiteSettingsCategoriesAdapter adapter;
    
    public CategoriesListFragment() {
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
    
    private final void initViewModel(org.wordpress.android.databinding.SiteSettingsCategoriesListFragmentBinding $this$initViewModel, org.wordpress.android.fluxc.model.SiteModel site) {
    }
    
    private final void initRecyclerView(org.wordpress.android.databinding.SiteSettingsCategoriesListFragmentBinding $this$initRecyclerView) {
    }
    
    private final void initFabButton(org.wordpress.android.databinding.SiteSettingsCategoriesListFragmentBinding $this$initFabButton) {
    }
    
    private final void initEmptyView(org.wordpress.android.databinding.SiteSettingsCategoriesListFragmentBinding $this$initEmptyView) {
    }
    
    private final org.wordpress.android.fluxc.model.SiteModel getSite(android.os.Bundle savedInstanceState) {
        return null;
    }
    
    private final void setupObservers(org.wordpress.android.databinding.SiteSettingsCategoriesListFragmentBinding $this$setupObservers) {
    }
    
    private final void updateErrorContent(org.wordpress.android.databinding.SiteSettingsCategoriesListFragmentBinding $this$updateErrorContent, org.wordpress.android.ui.prefs.categories.list.UiState.Error error) {
    }
    
    private final void updateContentLayout(java.util.List<? extends org.wordpress.android.models.CategoryNode> list) {
    }
    
    private final void onCategoryRowClicked(org.wordpress.android.models.CategoryNode categoryNode) {
    }
    
    public final void updateVisibility(@org.jetbrains.annotations.NotNull()
    android.view.View $this$updateVisibility, boolean visible) {
    }
    
    @java.lang.Override()
    public void onSaveInstanceState(@org.jetbrains.annotations.NotNull()
    android.os.Bundle outState) {
    }
}