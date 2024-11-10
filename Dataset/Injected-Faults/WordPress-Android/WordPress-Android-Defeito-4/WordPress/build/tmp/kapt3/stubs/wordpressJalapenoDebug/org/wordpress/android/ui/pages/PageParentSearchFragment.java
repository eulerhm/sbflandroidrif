package org.wordpress.android.ui.pages;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 -2\u00020\u00012\u00020\u0002:\u0001-B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\u001a\u001a\u00020\u001bH\u0016J\u0010\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u001a\u0010\u001f\u001a\u00020\u001b2\u0006\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010\u001eH\u0016J\u0014\u0010#\u001a\u00020\u001b*\u00020$2\u0006\u0010%\u001a\u00020&H\u0002J\u0016\u0010\'\u001a\u00020\u001b*\u00020$2\b\u0010\"\u001a\u0004\u0018\u00010\u001eH\u0002J\u001a\u0010(\u001a\u00020\u001b*\u00020$2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020+0*H\u0002J\f\u0010,\u001a\u00020\u001b*\u00020$H\u0002R\u0014\u0010\u0004\u001a\u00020\u00058VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\u00020\tX\u0084\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0014\u001a\u00020\u00158\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019\u00a8\u0006."}, d2 = {"Lorg/wordpress/android/ui/pages/PageParentSearchFragment;", "Landroidx/fragment/app/Fragment;", "Lkotlinx/coroutines/CoroutineScope;", "()V", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "job", "Lkotlinx/coroutines/Job;", "getJob", "()Lkotlinx/coroutines/Job;", "setJob", "(Lkotlinx/coroutines/Job;)V", "linearLayoutManager", "Landroidx/recyclerview/widget/LinearLayoutManager;", "listStateKey", "", "viewModel", "Lorg/wordpress/android/viewmodel/pages/PageParentSearchViewModel;", "viewModelFactory", "Landroidx/lifecycle/ViewModelProvider$Factory;", "getViewModelFactory", "()Landroidx/lifecycle/ViewModelProvider$Factory;", "setViewModelFactory", "(Landroidx/lifecycle/ViewModelProvider$Factory;)V", "onDestroy", "", "onSaveInstanceState", "outState", "Landroid/os/Bundle;", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "initializeViewModels", "Lorg/wordpress/android/databinding/PagesListFragmentBinding;", "activity", "Landroidx/fragment/app/FragmentActivity;", "initializeViews", "setSearchResult", "pages", "", "Lorg/wordpress/android/ui/pages/PageItem;", "setupObservers", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public final class PageParentSearchFragment extends androidx.fragment.app.Fragment implements kotlinx.coroutines.CoroutineScope {
    @org.jetbrains.annotations.NotNull()
    private kotlinx.coroutines.Job job;
    @javax.inject.Inject()
    public androidx.lifecycle.ViewModelProvider.Factory viewModelFactory;
    private org.wordpress.android.viewmodel.pages.PageParentSearchViewModel viewModel;
    private androidx.recyclerview.widget.LinearLayoutManager linearLayoutManager;
    private final java.lang.String listStateKey = "list_state";
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.pages.PageParentSearchFragment.Companion Companion = null;
    
    public PageParentSearchFragment() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    protected final kotlinx.coroutines.Job getJob() {
        return null;
    }
    
    protected final void setJob(@org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.Job p0) {
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
    
    @java.lang.Override()
    public void onDestroy() {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004\u00a8\u0006\u0005"}, d2 = {"Lorg/wordpress/android/ui/pages/PageParentSearchFragment$Companion;", "", "()V", "newInstance", "Lorg/wordpress/android/ui/pages/PageParentSearchFragment;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.pages.PageParentSearchFragment newInstance() {
            return null;
        }
    }
}