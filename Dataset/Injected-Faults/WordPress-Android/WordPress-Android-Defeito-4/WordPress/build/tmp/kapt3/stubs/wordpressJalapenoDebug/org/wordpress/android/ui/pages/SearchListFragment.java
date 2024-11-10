package org.wordpress.android.ui.pages;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \'2\u00020\u0001:\u0001\'B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u001a\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0018H\u0016J\u0014\u0010\u001d\u001a\u00020\u0016*\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0002J\u0016\u0010!\u001a\u00020\u0016*\u00020\u001e2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0018H\u0002J\u001a\u0010\"\u001a\u00020\u0016*\u00020\u001e2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020%0$H\u0002J\f\u0010&\u001a\u00020\u0016*\u00020\u001eH\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0007\u001a\u00020\b8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u000f\u001a\u00020\u00108\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014\u00a8\u0006("}, d2 = {"Lorg/wordpress/android/ui/pages/SearchListFragment;", "Landroidx/fragment/app/Fragment;", "()V", "linearLayoutManager", "Landroidx/recyclerview/widget/LinearLayoutManager;", "listStateKey", "", "uiHelper", "Lorg/wordpress/android/ui/utils/UiHelpers;", "getUiHelper", "()Lorg/wordpress/android/ui/utils/UiHelpers;", "setUiHelper", "(Lorg/wordpress/android/ui/utils/UiHelpers;)V", "viewModel", "Lorg/wordpress/android/viewmodel/pages/SearchListViewModel;", "viewModelFactory", "Landroidx/lifecycle/ViewModelProvider$Factory;", "getViewModelFactory", "()Landroidx/lifecycle/ViewModelProvider$Factory;", "setViewModelFactory", "(Landroidx/lifecycle/ViewModelProvider$Factory;)V", "onSaveInstanceState", "", "outState", "Landroid/os/Bundle;", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "initializeViewModels", "Lorg/wordpress/android/databinding/PagesListFragmentBinding;", "activity", "Landroidx/fragment/app/FragmentActivity;", "initializeViews", "setSearchResult", "pages", "", "Lorg/wordpress/android/ui/pages/PageItem;", "setupObservers", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public final class SearchListFragment extends androidx.fragment.app.Fragment {
    @javax.inject.Inject()
    public androidx.lifecycle.ViewModelProvider.Factory viewModelFactory;
    private org.wordpress.android.viewmodel.pages.SearchListViewModel viewModel;
    private androidx.recyclerview.widget.LinearLayoutManager linearLayoutManager;
    @javax.inject.Inject()
    public org.wordpress.android.ui.utils.UiHelpers uiHelper;
    private final java.lang.String listStateKey = "list_state";
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.pages.SearchListFragment.Companion Companion = null;
    
    public SearchListFragment() {
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
    public final org.wordpress.android.ui.utils.UiHelpers getUiHelper() {
        return null;
    }
    
    public final void setUiHelper(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.utils.UiHelpers p0) {
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void onSaveInstanceState(@org.jetbrains.annotations.NotNull()
    android.os.Bundle outState) {
    }
    
    private final void initializeViewModels(org.wordpress.android.databinding.PagesListFragmentBinding $this$initializeViewModels, androidx.fragment.app.FragmentActivity activity) {
    }
    
    private final void initializeViews(org.wordpress.android.databinding.PagesListFragmentBinding $this$initializeViews, android.os.Bundle savedInstanceState) {
    }
    
    private final void setupObservers(org.wordpress.android.databinding.PagesListFragmentBinding $this$setupObservers) {
    }
    
    private final void setSearchResult(org.wordpress.android.databinding.PagesListFragmentBinding $this$setSearchResult, java.util.List<? extends org.wordpress.android.ui.pages.PageItem> pages) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004\u00a8\u0006\u0005"}, d2 = {"Lorg/wordpress/android/ui/pages/SearchListFragment$Companion;", "", "()V", "newInstance", "Lorg/wordpress/android/ui/pages/SearchListFragment;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.pages.SearchListFragment newInstance() {
            return null;
        }
    }
}