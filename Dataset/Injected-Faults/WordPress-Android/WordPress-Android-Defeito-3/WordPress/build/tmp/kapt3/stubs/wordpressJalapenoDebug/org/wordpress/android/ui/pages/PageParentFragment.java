package org.wordpress.android.ui.pages;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u008c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 A2\u00020\u00012\u00020\u0002:\u0001AB\u0005\u00a2\u0006\u0002\u0010\u0003J\u0012\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0016J\u0018\u0010$\u001a\u00020!2\u0006\u0010%\u001a\u00020&2\u0006\u0010\'\u001a\u00020(H\u0016J\b\u0010)\u001a\u00020!H\u0016J\b\u0010*\u001a\u00020!H\u0016J\u0010\u0010+\u001a\u00020\u00142\u0006\u0010,\u001a\u00020\u0016H\u0016J\u0010\u0010-\u001a\u00020!2\u0006\u0010.\u001a\u00020#H\u0016J\u001a\u0010/\u001a\u00020!2\u0006\u00100\u001a\u0002012\b\u0010\"\u001a\u0004\u0018\u00010#H\u0016J\b\u00102\u001a\u00020!H\u0002J\u0014\u00103\u001a\u00020!*\u00020\u00052\u0006\u00104\u001a\u00020\u0016H\u0002J\f\u00105\u001a\u00020!*\u00020\u0005H\u0002J$\u00106\u001a\u00020!*\u00020\u00052\u0006\u00107\u001a\u0002082\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u00109\u001a\u00020\u0014H\u0002J\u001e\u0010:\u001a\u00020!*\u00020\u00052\u0006\u00107\u001a\u0002082\b\u0010\"\u001a\u0004\u0018\u00010#H\u0002J\u001a\u0010;\u001a\u00020!*\u00020\u00052\f\u0010<\u001a\b\u0012\u0004\u0012\u00020>0=H\u0002J\f\u0010?\u001a\u00020!*\u00020\u0005H\u0002J\u0014\u0010@\u001a\u00020!*\u00020\u00052\u0006\u00104\u001a\u00020\u0016H\u0002R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u00078VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082D\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\u0012R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0016X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u001a\u001a\u00020\u001b8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001f\u00a8\u0006B"}, d2 = {"Lorg/wordpress/android/ui/pages/PageParentFragment;", "Landroidx/fragment/app/Fragment;", "Lkotlinx/coroutines/CoroutineScope;", "()V", "binding", "Lorg/wordpress/android/databinding/PageParentFragmentBinding;", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "job", "Lkotlinx/coroutines/Job;", "linearLayoutManager", "Landroidx/recyclerview/widget/LinearLayoutManager;", "listStateKey", "", "pageId", "", "Ljava/lang/Long;", "restorePreviousSearch", "", "saveButton", "Landroid/view/MenuItem;", "searchAction", "viewModel", "Lorg/wordpress/android/viewmodel/pages/PageParentViewModel;", "viewModelFactory", "Landroidx/lifecycle/ViewModelProvider$Factory;", "getViewModelFactory", "()Landroidx/lifecycle/ViewModelProvider$Factory;", "setViewModelFactory", "(Landroidx/lifecycle/ViewModelProvider$Factory;)V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onCreateOptionsMenu", "menu", "Landroid/view/Menu;", "inflater", "Landroid/view/MenuInflater;", "onDestroy", "onDestroyView", "onOptionsItemSelected", "item", "onSaveInstanceState", "outState", "onViewCreated", "view", "Landroid/view/View;", "returnParentChoiceAndExit", "hideSearchList", "myActionMenuItem", "initializeSearchView", "initializeViewModels", "activity", "Landroidx/fragment/app/FragmentActivity;", "isFirstStart", "initializeViews", "setPages", "pages", "", "Lorg/wordpress/android/ui/pages/PageItem;", "setupObservers", "showSearchList", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public final class PageParentFragment extends androidx.fragment.app.Fragment implements kotlinx.coroutines.CoroutineScope {
    private kotlinx.coroutines.Job job;
    @javax.inject.Inject()
    public androidx.lifecycle.ViewModelProvider.Factory viewModelFactory;
    private org.wordpress.android.viewmodel.pages.PageParentViewModel viewModel;
    private final java.lang.String listStateKey = "list_state";
    private androidx.recyclerview.widget.LinearLayoutManager linearLayoutManager;
    private android.view.MenuItem saveButton;
    private android.view.MenuItem searchAction;
    private java.lang.Long pageId;
    private boolean restorePreviousSearch = false;
    private org.wordpress.android.databinding.PageParentFragmentBinding binding;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.pages.PageParentFragment.Companion Companion = null;
    
    public PageParentFragment() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public kotlin.coroutines.CoroutineContext getCoroutineContext() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.ViewModelProvider.Factory getViewModelFactory() {
        return null;
    }
    
    public final void setViewModelFactory(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.ViewModelProvider.Factory p0) {
    }
    
    @java.lang.Override()
    public boolean onOptionsItemSelected(@org.jetbrains.annotations.NotNull()
    android.view.MenuItem item) {
        return false;
    }
    
    private final void returnParentChoiceAndExit() {
    }
    
    @java.lang.Override()
    public void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void onCreateOptionsMenu(@org.jetbrains.annotations.NotNull()
    android.view.Menu menu, @org.jetbrains.annotations.NotNull()
    android.view.MenuInflater inflater) {
    }
    
    private final void initializeSearchView(org.wordpress.android.databinding.PageParentFragmentBinding $this$initializeSearchView) {
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
    
    private final void initializeViews(org.wordpress.android.databinding.PageParentFragmentBinding $this$initializeViews, androidx.fragment.app.FragmentActivity activity, android.os.Bundle savedInstanceState) {
    }
    
    private final void initializeViewModels(org.wordpress.android.databinding.PageParentFragmentBinding $this$initializeViewModels, androidx.fragment.app.FragmentActivity activity, long pageId, boolean isFirstStart) {
    }
    
    private final void setupObservers(org.wordpress.android.databinding.PageParentFragmentBinding $this$setupObservers) {
    }
    
    @java.lang.Override()
    public void onSaveInstanceState(@org.jetbrains.annotations.NotNull()
    android.os.Bundle outState) {
    }
    
    private final void setPages(org.wordpress.android.databinding.PageParentFragmentBinding $this$setPages, java.util.List<? extends org.wordpress.android.ui.pages.PageItem> pages) {
    }
    
    private final void hideSearchList(org.wordpress.android.databinding.PageParentFragmentBinding $this$hideSearchList, android.view.MenuItem myActionMenuItem) {
    }
    
    private final void showSearchList(org.wordpress.android.databinding.PageParentFragmentBinding $this$showSearchList, android.view.MenuItem myActionMenuItem) {
    }
    
    @java.lang.Override()
    public void onDestroy() {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004\u00a8\u0006\u0005"}, d2 = {"Lorg/wordpress/android/ui/pages/PageParentFragment$Companion;", "", "()V", "newInstance", "Lorg/wordpress/android/ui/pages/PageParentFragment;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.pages.PageParentFragment newInstance() {
            return null;
        }
    }
}