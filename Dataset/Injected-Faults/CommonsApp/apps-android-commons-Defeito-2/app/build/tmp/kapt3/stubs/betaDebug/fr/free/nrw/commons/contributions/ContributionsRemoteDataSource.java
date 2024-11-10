package fr.free.nrw.commons.contributions;

import java.lang.System;

/**
 * Data-Source which acts as mediator for contributions-data from the API
 */
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0019\b\u0007\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0006\u0010\u0011\u001a\u00020\u0012J\u0016\u0010\u0013\u001a\u00020\u00122\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00030\u0015H\u0002J\u0015\u0010\u0016\u001a\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u0003H\u0016\u00a2\u0006\u0002\u0010\u0018J$\u0010\u0019\u001a\u00020\u00122\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00020\u001b2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00030\u0015H\u0016J$\u0010\u001c\u001a\u00020\u00122\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00020\u001b2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00030\u0015H\u0016J$\u0010\u001d\u001a\u00020\u00122\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00020\u001e2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00030\u001fH\u0016R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010\u00a8\u0006 "}, d2 = {"Lfr/free/nrw/commons/contributions/ContributionsRemoteDataSource;", "Landroidx/paging/ItemKeyedDataSource;", "", "Lfr/free/nrw/commons/contributions/Contribution;", "mediaClient", "Lfr/free/nrw/commons/media/MediaClient;", "ioThreadScheduler", "Lio/reactivex/Scheduler;", "(Lfr/free/nrw/commons/media/MediaClient;Lio/reactivex/Scheduler;)V", "compositeDisposable", "Lio/reactivex/disposables/CompositeDisposable;", "userName", "", "getUserName", "()Ljava/lang/String;", "setUserName", "(Ljava/lang/String;)V", "dispose", "", "fetchContributions", "callback", "Landroidx/paging/ItemKeyedDataSource$LoadCallback;", "getKey", "item", "(Lfr/free/nrw/commons/contributions/Contribution;)Ljava/lang/Integer;", "loadAfter", "params", "Landroidx/paging/ItemKeyedDataSource$LoadParams;", "loadBefore", "loadInitial", "Landroidx/paging/ItemKeyedDataSource$LoadInitialParams;", "Landroidx/paging/ItemKeyedDataSource$LoadInitialCallback;", "app-commons-v4.2.1-main_betaDebug"})
public final class ContributionsRemoteDataSource extends androidx.paging.ItemKeyedDataSource<java.lang.Integer, fr.free.nrw.commons.contributions.Contribution> {
    private final fr.free.nrw.commons.media.MediaClient mediaClient = null;
    private final io.reactivex.Scheduler ioThreadScheduler = null;
    private final io.reactivex.disposables.CompositeDisposable compositeDisposable = null;
    @org.jetbrains.annotations.Nullable
    private java.lang.String userName;
    
    @javax.inject.Inject
    public ContributionsRemoteDataSource(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.media.MediaClient mediaClient, @org.jetbrains.annotations.NotNull
    @javax.inject.Named(value = "io_thread")
    io.reactivex.Scheduler ioThreadScheduler) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getUserName() {
        return null;
    }
    
    public final void setUserName(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @java.lang.Override
    public void loadInitial(@org.jetbrains.annotations.NotNull
    androidx.paging.ItemKeyedDataSource.LoadInitialParams<java.lang.Integer> params, @org.jetbrains.annotations.NotNull
    androidx.paging.ItemKeyedDataSource.LoadInitialCallback<fr.free.nrw.commons.contributions.Contribution> callback) {
    }
    
    @java.lang.Override
    public void loadAfter(@org.jetbrains.annotations.NotNull
    androidx.paging.ItemKeyedDataSource.LoadParams<java.lang.Integer> params, @org.jetbrains.annotations.NotNull
    androidx.paging.ItemKeyedDataSource.LoadCallback<fr.free.nrw.commons.contributions.Contribution> callback) {
    }
    
    @java.lang.Override
    public void loadBefore(@org.jetbrains.annotations.NotNull
    androidx.paging.ItemKeyedDataSource.LoadParams<java.lang.Integer> params, @org.jetbrains.annotations.NotNull
    androidx.paging.ItemKeyedDataSource.LoadCallback<fr.free.nrw.commons.contributions.Contribution> callback) {
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public java.lang.Integer getKey(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.contributions.Contribution item) {
        return null;
    }
    
    /**
     * Fetches contributions using the MediaWiki API
     */
    private final void fetchContributions(androidx.paging.ItemKeyedDataSource.LoadCallback<fr.free.nrw.commons.contributions.Contribution> callback) {
    }
    
    public final void dispose() {
    }
}