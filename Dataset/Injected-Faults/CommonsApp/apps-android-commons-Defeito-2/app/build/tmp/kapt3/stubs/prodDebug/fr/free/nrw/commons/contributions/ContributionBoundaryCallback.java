package fr.free.nrw.commons.contributions;

import java.lang.System;

/**
 * Class that extends PagedList.BoundaryCallback for contributions list It defines the action that
 * is triggered for various boundary conditions in the list
 */
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010 \n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B)\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\b\u0001\u0010\t\u001a\u00020\n\u00a2\u0006\u0002\u0010\u000bJ\u0006\u0010\u0014\u001a\u00020\u0015J\b\u0010\u0016\u001a\u00020\u0015H\u0002J\u0010\u0010\u0017\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u0002H\u0016J\u0010\u0010\u0019\u001a\u00020\u00152\u0006\u0010\u001a\u001a\u00020\u0002H\u0016J\b\u0010\u001b\u001a\u00020\u0015H\u0016J\u0016\u0010\u001c\u001a\u00020\u00152\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00020\u001eH\u0002R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013\u00a8\u0006\u001f"}, d2 = {"Lfr/free/nrw/commons/contributions/ContributionBoundaryCallback;", "Landroidx/paging/PagedList$BoundaryCallback;", "Lfr/free/nrw/commons/contributions/Contribution;", "repository", "Lfr/free/nrw/commons/contributions/ContributionsRepository;", "sessionManager", "Lfr/free/nrw/commons/auth/SessionManager;", "mediaClient", "Lfr/free/nrw/commons/media/MediaClient;", "ioThreadScheduler", "Lio/reactivex/Scheduler;", "(Lfr/free/nrw/commons/contributions/ContributionsRepository;Lfr/free/nrw/commons/auth/SessionManager;Lfr/free/nrw/commons/media/MediaClient;Lio/reactivex/Scheduler;)V", "compositeDisposable", "Lio/reactivex/disposables/CompositeDisposable;", "userName", "", "getUserName", "()Ljava/lang/String;", "setUserName", "(Ljava/lang/String;)V", "dispose", "", "fetchContributions", "onItemAtEndLoaded", "itemAtEnd", "onItemAtFrontLoaded", "itemAtFront", "onZeroItemsLoaded", "saveContributionsToDB", "contributions", "", "app-commons-v4.2.1-master_prodDebug"})
public final class ContributionBoundaryCallback extends androidx.paging.PagedList.BoundaryCallback<fr.free.nrw.commons.contributions.Contribution> {
    private final fr.free.nrw.commons.contributions.ContributionsRepository repository = null;
    private final fr.free.nrw.commons.auth.SessionManager sessionManager = null;
    private final fr.free.nrw.commons.media.MediaClient mediaClient = null;
    private final io.reactivex.Scheduler ioThreadScheduler = null;
    private final io.reactivex.disposables.CompositeDisposable compositeDisposable = null;
    @org.jetbrains.annotations.Nullable
    private java.lang.String userName;
    
    @javax.inject.Inject
    public ContributionBoundaryCallback(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.contributions.ContributionsRepository repository, @org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.auth.SessionManager sessionManager, @org.jetbrains.annotations.NotNull
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
    
    /**
     * It is triggered when the list has no items User's Contributions are then fetched from the
     * network
     */
    @java.lang.Override
    public void onZeroItemsLoaded() {
    }
    
    /**
     * It is triggered when the user scrolls to the top of the list
     */
    @java.lang.Override
    public void onItemAtFrontLoaded(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.contributions.Contribution itemAtFront) {
    }
    
    /**
     * It is triggered when the user scrolls to the end of the list. User's Contributions are then
     * fetched from the network
     */
    @java.lang.Override
    public void onItemAtEndLoaded(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.contributions.Contribution itemAtEnd) {
    }
    
    /**
     * Fetches contributions using the MediaWiki API
     */
    private final void fetchContributions() {
    }
    
    /**
     * Saves the contributions the the local DB
     */
    private final void saveContributionsToDB(java.util.List<fr.free.nrw.commons.contributions.Contribution> contributions) {
    }
    
    /**
     * Clean up
     */
    public final void dispose() {
    }
}