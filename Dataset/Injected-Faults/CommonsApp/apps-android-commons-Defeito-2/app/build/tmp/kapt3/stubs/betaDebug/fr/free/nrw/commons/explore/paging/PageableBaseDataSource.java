package fr.free.nrw.commons.explore.paging;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0002\n\u0002\b\u0002\b&\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0005J\u000e\u0010\'\u001a\u00020(2\u0006\u0010\"\u001a\u00020\u000bJ\u0006\u0010)\u001a\u00020(R\u001c\u0010\u0006\u001a\u0010\u0012\f\u0012\n \t*\u0004\u0018\u00010\b0\b0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\n\u001a\u0010\u0012\f\u0012\n \t*\u0004\u0018\u00010\u000b0\u000b0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R4\u0010\f\u001a(\u0012$\u0012\"\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u000e \t*\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u000e\u0018\u00010\r0\r0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u000f\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00100\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R4\u0010\u0013\u001a$\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u0015\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00160\u0014j\b\u0012\u0004\u0012\u00028\u0000`\u0017X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0017\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\b0\u001b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0017\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u000b0\u001b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001dR#\u0010 \u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u000e0\r0\u001b\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001dR\u001a\u0010\"\u001a\u00020\u000bX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&\u00a8\u0006*"}, d2 = {"Lfr/free/nrw/commons/explore/paging/PageableBaseDataSource;", "T", "", "liveDataConverter", "Lfr/free/nrw/commons/explore/paging/LiveDataConverter;", "(Lfr/free/nrw/commons/explore/paging/LiveDataConverter;)V", "_loadingStates", "Lio/reactivex/processors/PublishProcessor;", "Lfr/free/nrw/commons/explore/paging/LoadingState;", "kotlin.jvm.PlatformType", "_noItemsLoadedEvent", "", "_pagingResults", "Landroidx/lifecycle/LiveData;", "Landroidx/paging/PagedList;", "currentFactory", "Lfr/free/nrw/commons/explore/paging/PagingDataSourceFactory;", "dataSourceFactoryFactory", "Lkotlin/Function0;", "loadFunction", "Lkotlin/Function2;", "", "", "Lfr/free/nrw/commons/explore/depictions/search/LoadFunction;", "getLoadFunction", "()Lkotlin/jvm/functions/Function2;", "loadingStates", "Lio/reactivex/Flowable;", "getLoadingStates", "()Lio/reactivex/Flowable;", "noItemsLoadedQueries", "getNoItemsLoadedQueries", "pagingResults", "getPagingResults", "query", "getQuery", "()Ljava/lang/String;", "setQuery", "(Ljava/lang/String;)V", "onQueryUpdated", "", "retryFailedRequest", "app-commons-v4.2.1-main_betaDebug"})
public abstract class PageableBaseDataSource<T extends java.lang.Object> {
    private final fr.free.nrw.commons.explore.paging.LiveDataConverter liveDataConverter = null;
    public java.lang.String query;
    private final kotlin.jvm.functions.Function0<fr.free.nrw.commons.explore.paging.PagingDataSourceFactory<T>> dataSourceFactoryFactory = null;
    private final io.reactivex.processors.PublishProcessor<fr.free.nrw.commons.explore.paging.LoadingState> _loadingStates = null;
    @org.jetbrains.annotations.NotNull
    private final io.reactivex.Flowable<fr.free.nrw.commons.explore.paging.LoadingState> loadingStates = null;
    private final io.reactivex.processors.PublishProcessor<androidx.lifecycle.LiveData<androidx.paging.PagedList<T>>> _pagingResults = null;
    @org.jetbrains.annotations.NotNull
    private final io.reactivex.Flowable<androidx.lifecycle.LiveData<androidx.paging.PagedList<T>>> pagingResults = null;
    private final io.reactivex.processors.PublishProcessor<java.lang.String> _noItemsLoadedEvent = null;
    @org.jetbrains.annotations.NotNull
    private final io.reactivex.Flowable<java.lang.String> noItemsLoadedQueries = null;
    private fr.free.nrw.commons.explore.paging.PagingDataSourceFactory<T> currentFactory;
    
    public PageableBaseDataSource(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.explore.paging.LiveDataConverter liveDataConverter) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getQuery() {
        return null;
    }
    
    public final void setQuery(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final io.reactivex.Flowable<fr.free.nrw.commons.explore.paging.LoadingState> getLoadingStates() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final io.reactivex.Flowable<androidx.lifecycle.LiveData<androidx.paging.PagedList<T>>> getPagingResults() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final io.reactivex.Flowable<java.lang.String> getNoItemsLoadedQueries() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public abstract kotlin.jvm.functions.Function2<java.lang.Integer, java.lang.Integer, java.util.List<T>> getLoadFunction();
    
    public final void onQueryUpdated(@org.jetbrains.annotations.NotNull
    java.lang.String query) {
    }
    
    public final void retryFailedRequest() {
    }
}