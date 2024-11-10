package fr.free.nrw.commons.explore.paging;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\b&\u0018\u0000*\u0004\b\u0000\u0010\u00012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u0002H\u00010\u0002B\u0017\u0012\u0010\u0010\u0004\u001a\f\u0012\u0004\u0012\u00020\u00060\u0005j\u0002`\u0007\u00a2\u0006\u0002\u0010\bJ\u000e\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00000\nH\u0016J\u0006\u0010\u0014\u001a\u00020\u0015R\u0016\u0010\t\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R4\u0010\u000b\u001a$\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\r0\fj\b\u0012\u0004\u0012\u00028\u0000`\u000eX\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u001b\u0010\u0004\u001a\f\u0012\u0004\u0012\u00020\u00060\u0005j\u0002`\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012\u00a8\u0006\u0016"}, d2 = {"Lfr/free/nrw/commons/explore/paging/PagingDataSourceFactory;", "T", "Landroidx/paging/DataSource$Factory;", "", "loadingStates", "Lio/reactivex/processors/PublishProcessor;", "Lfr/free/nrw/commons/explore/paging/LoadingState;", "Lfr/free/nrw/commons/explore/depictions/search/LoadingStates;", "(Lio/reactivex/processors/PublishProcessor;)V", "currentDataSource", "Lfr/free/nrw/commons/explore/paging/PagingDataSource;", "loadFunction", "Lkotlin/Function2;", "", "Lfr/free/nrw/commons/explore/depictions/search/LoadFunction;", "getLoadFunction", "()Lkotlin/jvm/functions/Function2;", "getLoadingStates", "()Lio/reactivex/processors/PublishProcessor;", "create", "retryFailedRequest", "", "app-commons-v4.2.1-master_prodDebug"})
public abstract class PagingDataSourceFactory<T extends java.lang.Object> extends androidx.paging.DataSource.Factory<java.lang.Integer, T> {
    @org.jetbrains.annotations.NotNull
    private final io.reactivex.processors.PublishProcessor<fr.free.nrw.commons.explore.paging.LoadingState> loadingStates = null;
    private fr.free.nrw.commons.explore.paging.PagingDataSource<T> currentDataSource;
    
    public PagingDataSourceFactory(@org.jetbrains.annotations.NotNull
    io.reactivex.processors.PublishProcessor<fr.free.nrw.commons.explore.paging.LoadingState> loadingStates) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final io.reactivex.processors.PublishProcessor<fr.free.nrw.commons.explore.paging.LoadingState> getLoadingStates() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public abstract kotlin.jvm.functions.Function2<java.lang.Integer, java.lang.Integer, java.util.List<T>> getLoadFunction();
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public fr.free.nrw.commons.explore.paging.PagingDataSource<T> create() {
        return null;
    }
    
    public final void retryFailedRequest() {
    }
}