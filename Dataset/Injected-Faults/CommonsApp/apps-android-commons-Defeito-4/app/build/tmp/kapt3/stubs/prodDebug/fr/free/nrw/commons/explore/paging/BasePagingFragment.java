package fr.free.nrw.commons.explore.paging;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b&\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0005\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020 H&J\b\u0010\"\u001a\u00020#H\u0016J\b\u0010$\u001a\u00020#H\u0016J\u001c\u0010%\u001a\u00020#2\u0012\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u001e0\u001dH\u0016J\u0010\u0010&\u001a\u00020#2\u0006\u0010\'\u001a\u00020(H\u0016J\u0010\u0010)\u001a\u00020#2\u0006\u0010*\u001a\u00020+H\u0016J,\u0010,\u001a\n .*\u0004\u0018\u00010-0-2\u0006\u0010/\u001a\u0002002\b\u00101\u001a\u0004\u0018\u0001022\b\u00103\u001a\u0004\u0018\u000104H\u0016J\b\u00105\u001a\u00020#H\u0016J\u000e\u00106\u001a\u00020#2\u0006\u0010!\u001a\u00020 J\u001a\u00107\u001a\u00020#2\u0006\u00108\u001a\u00020-2\b\u00103\u001a\u0004\u0018\u000104H\u0016J\u0010\u00109\u001a\u00020#2\u0006\u0010!\u001a\u00020 H\u0016J\b\u0010:\u001a\u00020#H\u0016J\b\u0010;\u001a\u00020#H\u0016R\u0012\u0010\u0005\u001a\u00020\u0006X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0018\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\nX\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u001b\u0010\r\u001a\u00020\u000e8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u000f\u0010\u0010R\u001b\u0010\u0013\u001a\u00020\u00148BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0017\u0010\u0012\u001a\u0004\b\u0015\u0010\u0016R\u001c\u0010\u0018\u001a\f\u0012\u0004\u0012\u00028\u0000\u0012\u0002\b\u00030\u0019X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u001c\u0010\u001c\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u001e\u0018\u00010\u001dX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006<"}, d2 = {"Lfr/free/nrw/commons/explore/paging/BasePagingFragment;", "T", "Lfr/free/nrw/commons/di/CommonsDaggerSupportFragment;", "Lfr/free/nrw/commons/explore/paging/PagingContract$View;", "()V", "errorTextId", "", "getErrorTextId", "()I", "injectedPresenter", "Lfr/free/nrw/commons/explore/paging/PagingContract$Presenter;", "getInjectedPresenter", "()Lfr/free/nrw/commons/explore/paging/PagingContract$Presenter;", "loadingAdapter", "Lfr/free/nrw/commons/explore/paging/FooterAdapter;", "getLoadingAdapter", "()Lfr/free/nrw/commons/explore/paging/FooterAdapter;", "loadingAdapter$delegate", "Lkotlin/Lazy;", "mergeAdapter", "Landroidx/recyclerview/widget/MergeAdapter;", "getMergeAdapter", "()Landroidx/recyclerview/widget/MergeAdapter;", "mergeAdapter$delegate", "pagedListAdapter", "Landroidx/paging/PagedListAdapter;", "getPagedListAdapter", "()Landroidx/paging/PagedListAdapter;", "searchResults", "Landroidx/lifecycle/LiveData;", "Landroidx/paging/PagedList;", "getEmptyText", "", "query", "hideEmptyText", "", "hideInitialLoadProgress", "observePagingResults", "onAttach", "context", "Landroid/content/Context;", "onConfigurationChanged", "newConfig", "Landroid/content/res/Configuration;", "onCreateView", "Landroid/view/View;", "kotlin.jvm.PlatformType", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDetach", "onQueryUpdated", "onViewCreated", "view", "showEmptyText", "showInitialLoadInProgress", "showSnackbar", "app-commons-v4.2.1-master_prodDebug"})
public abstract class BasePagingFragment<T extends java.lang.Object> extends fr.free.nrw.commons.di.CommonsDaggerSupportFragment implements fr.free.nrw.commons.explore.paging.PagingContract.View<T> {
    private final kotlin.Lazy loadingAdapter$delegate = null;
    private final kotlin.Lazy mergeAdapter$delegate = null;
    private androidx.lifecycle.LiveData<androidx.paging.PagedList<T>> searchResults;
    private java.util.HashMap _$_findViewCache;
    
    public BasePagingFragment() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public abstract androidx.paging.PagedListAdapter<T, ?> getPagedListAdapter();
    
    @org.jetbrains.annotations.NotNull
    public abstract fr.free.nrw.commons.explore.paging.PagingContract.Presenter<T> getInjectedPresenter();
    
    public abstract int getErrorTextId();
    
    private final fr.free.nrw.commons.explore.paging.FooterAdapter getLoadingAdapter() {
        return null;
    }
    
    private final androidx.recyclerview.widget.MergeAdapter getMergeAdapter() {
        return null;
    }
    
    @java.lang.Override
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override
    public void onViewCreated(@org.jetbrains.annotations.NotNull
    android.view.View view, @org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    /**
     * Called on configuration change, update the spanCount according to the orientation state.
     */
    @java.lang.Override
    public void onConfigurationChanged(@org.jetbrains.annotations.NotNull
    android.content.res.Configuration newConfig) {
    }
    
    @java.lang.Override
    public void observePagingResults(@org.jetbrains.annotations.NotNull
    androidx.lifecycle.LiveData<androidx.paging.PagedList<T>> searchResults) {
    }
    
    @java.lang.Override
    public void onAttach(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
    }
    
    @java.lang.Override
    public void onDetach() {
    }
    
    @java.lang.Override
    public void hideInitialLoadProgress() {
    }
    
    @java.lang.Override
    public void showInitialLoadInProgress() {
    }
    
    @java.lang.Override
    public void showSnackbar() {
    }
    
    public final void onQueryUpdated(@org.jetbrains.annotations.NotNull
    java.lang.String query) {
    }
    
    @java.lang.Override
    public void showEmptyText(@org.jetbrains.annotations.NotNull
    java.lang.String query) {
    }
    
    @org.jetbrains.annotations.NotNull
    public abstract java.lang.String getEmptyText(@org.jetbrains.annotations.NotNull
    java.lang.String query);
    
    @java.lang.Override
    public void hideEmptyText() {
    }
}