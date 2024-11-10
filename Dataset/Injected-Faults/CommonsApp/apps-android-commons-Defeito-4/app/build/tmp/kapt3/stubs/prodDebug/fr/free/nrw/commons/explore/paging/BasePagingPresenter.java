package fr.free.nrw.commons.explore.paging;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b&\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u001b\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006\u00a2\u0006\u0002\u0010\u0007J\u0016\u0010\u0017\u001a\u00020\u00182\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00028\u00000\tH\u0016J\b\u0010\u0019\u001a\u00020\u0018H\u0016J\u0010\u0010\u001a\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\u0010\u0010\u001d\u001a\u00020\u00182\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J\b\u0010 \u001a\u00020\u0018H\u0016R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0\rX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00028\u00000\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006!"}, d2 = {"Lfr/free/nrw/commons/explore/paging/BasePagingPresenter;", "T", "Lfr/free/nrw/commons/explore/paging/PagingContract$Presenter;", "mainThreadScheduler", "Lio/reactivex/Scheduler;", "pageableBaseDataSource", "Lfr/free/nrw/commons/explore/paging/PageableBaseDataSource;", "(Lio/reactivex/Scheduler;Lfr/free/nrw/commons/explore/paging/PageableBaseDataSource;)V", "DUMMY", "Lfr/free/nrw/commons/explore/paging/PagingContract$View;", "compositeDisposable", "Lio/reactivex/disposables/CompositeDisposable;", "listFooterData", "Landroidx/lifecycle/MutableLiveData;", "", "Lfr/free/nrw/commons/explore/paging/FooterItem;", "getListFooterData", "()Landroidx/lifecycle/MutableLiveData;", "getMainThreadScheduler", "()Lio/reactivex/Scheduler;", "getPageableBaseDataSource", "()Lfr/free/nrw/commons/explore/paging/PageableBaseDataSource;", "view", "onAttachView", "", "onDetachView", "onLoadingState", "it", "Lfr/free/nrw/commons/explore/paging/LoadingState;", "onQueryUpdated", "query", "", "retryFailedRequest", "app-commons-v4.2.1-master_prodDebug"})
public abstract class BasePagingPresenter<T extends java.lang.Object> implements fr.free.nrw.commons.explore.paging.PagingContract.Presenter<T> {
    @org.jetbrains.annotations.NotNull
    private final io.reactivex.Scheduler mainThreadScheduler = null;
    @org.jetbrains.annotations.NotNull
    private final fr.free.nrw.commons.explore.paging.PageableBaseDataSource<T> pageableBaseDataSource = null;
    private final fr.free.nrw.commons.explore.paging.PagingContract.View<T> DUMMY = null;
    private fr.free.nrw.commons.explore.paging.PagingContract.View<T> view;
    private final io.reactivex.disposables.CompositeDisposable compositeDisposable = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<java.util.List<fr.free.nrw.commons.explore.paging.FooterItem>> listFooterData = null;
    
    public BasePagingPresenter(@org.jetbrains.annotations.NotNull
    io.reactivex.Scheduler mainThreadScheduler, @org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.explore.paging.PageableBaseDataSource<T> pageableBaseDataSource) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final io.reactivex.Scheduler getMainThreadScheduler() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final fr.free.nrw.commons.explore.paging.PageableBaseDataSource<T> getPageableBaseDataSource() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public androidx.lifecycle.MutableLiveData<java.util.List<fr.free.nrw.commons.explore.paging.FooterItem>> getListFooterData() {
        return null;
    }
    
    @java.lang.Override
    public void onAttachView(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.explore.paging.PagingContract.View<T> view) {
    }
    
    private final void onLoadingState(fr.free.nrw.commons.explore.paging.LoadingState it) {
    }
    
    @java.lang.Override
    public void retryFailedRequest() {
    }
    
    @java.lang.Override
    public void onDetachView() {
    }
    
    @java.lang.Override
    public void onQueryUpdated(@org.jetbrains.annotations.NotNull
    java.lang.String query) {
    }
}