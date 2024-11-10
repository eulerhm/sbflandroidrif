package fr.free.nrw.commons.explore.paging;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b&\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0013\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\u0002\u0010\u0006J\u001e\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH$J\u001e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00000\u0014H\u0016J\u001e\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00162\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00000\u0017H\u0016J\u0016\u0010\u0018\u001a\u00020\t2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00100\bH\u0002J\u0006\u0010\u001a\u001a\u00020\u0010J\u0016\u0010\u001b\u001a\u00020\u00102\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0002R\u0016\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001c"}, d2 = {"Lfr/free/nrw/commons/explore/paging/PagingDataSource;", "T", "Landroidx/paging/PositionalDataSource;", "loadingStates", "Lio/reactivex/processors/PublishProcessor;", "Lfr/free/nrw/commons/explore/paging/LoadingState;", "(Lio/reactivex/processors/PublishProcessor;)V", "lastExecutedRequest", "Lkotlin/Function0;", "", "getItems", "", "loadSize", "", "startPosition", "loadInitial", "", "params", "Landroidx/paging/PositionalDataSource$LoadInitialParams;", "callback", "Landroidx/paging/PositionalDataSource$LoadInitialCallback;", "loadRange", "Landroidx/paging/PositionalDataSource$LoadRangeParams;", "Landroidx/paging/PositionalDataSource$LoadRangeCallback;", "performWithTryCatch", "function", "retryFailedRequest", "storeAndExecute", "app-commons-v4.2.1-master_prodDebug"})
public abstract class PagingDataSource<T extends java.lang.Object> extends androidx.paging.PositionalDataSource<T> {
    private final io.reactivex.processors.PublishProcessor<fr.free.nrw.commons.explore.paging.LoadingState> loadingStates = null;
    private kotlin.jvm.functions.Function0<java.lang.Boolean> lastExecutedRequest;
    
    public PagingDataSource(@org.jetbrains.annotations.NotNull
    io.reactivex.processors.PublishProcessor<fr.free.nrw.commons.explore.paging.LoadingState> loadingStates) {
        super();
    }
    
    private final void storeAndExecute(kotlin.jvm.functions.Function0<java.lang.Boolean> function) {
    }
    
    private final boolean performWithTryCatch(kotlin.jvm.functions.Function0<kotlin.Unit> function) {
        return false;
    }
    
    @java.lang.Override
    public void loadInitial(@org.jetbrains.annotations.NotNull
    androidx.paging.PositionalDataSource.LoadInitialParams params, @org.jetbrains.annotations.NotNull
    androidx.paging.PositionalDataSource.LoadInitialCallback<T> callback) {
    }
    
    @java.lang.Override
    public void loadRange(@org.jetbrains.annotations.NotNull
    androidx.paging.PositionalDataSource.LoadRangeParams params, @org.jetbrains.annotations.NotNull
    androidx.paging.PositionalDataSource.LoadRangeCallback<T> callback) {
    }
    
    @org.jetbrains.annotations.NotNull
    protected abstract java.util.List<T> getItems(int loadSize, int startPosition);
    
    public final void retryFailedRequest() {
    }
}