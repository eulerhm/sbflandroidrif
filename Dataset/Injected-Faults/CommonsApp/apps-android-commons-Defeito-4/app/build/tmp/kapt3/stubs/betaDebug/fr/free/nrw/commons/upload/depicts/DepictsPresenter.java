package fr.free.nrw.commons.upload.depicts;

import java.lang.System;

/**
 * presenter for DepictsFragment
 */
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u0007\u0018\u0000 @2\u00020\u0001:\u0001@B#\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0007J\b\u0010\'\u001a\u00020(H\u0016J\u0014\u0010)\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120*H\u0016J\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00130,J\u0010\u0010-\u001a\u00020(2\u0006\u0010%\u001a\u00020&H\u0016J\u0018\u0010.\u001a\u00020(2\u0006\u0010%\u001a\u00020&2\u0006\u0010 \u001a\u00020!H\u0016J\u0010\u0010/\u001a\u00020(2\u0006\u00100\u001a\u00020\u0013H\u0016J\b\u00101\u001a\u00020(H\u0016J\b\u00102\u001a\u00020(H\u0016J\u0010\u00103\u001a\u00020(2\u0006\u00104\u001a\u00020$H\u0016J\u001c\u00105\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u0012062\u0006\u00107\u001a\u00020$H\u0002J(\u00108\u001a\u001a\u0012\u0016\u0012\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u0012\u0012\u0004\u0012\u00020$09062\u0006\u0010:\u001a\u00020$H\u0002J\u0016\u0010;\u001a\u00020(2\f\u0010<\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012H\u0002J\b\u0010=\u001a\u00020(H\u0016J\u0010\u0010>\u001a\u00020(2\u0006\u0010 \u001a\u00020!H\u0017J\b\u0010?\u001a\u00020(H\u0016R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001e\u0010\n\u001a\u00020\u000b8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0014\u001a\u00020\u00158\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001e\u0010\u001a\u001a\u00020\u001b8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010 \u001a\u0004\u0018\u00010!X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\"\u001a\b\u0012\u0004\u0012\u00020$0#X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020&X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006A"}, d2 = {"Lfr/free/nrw/commons/upload/depicts/DepictsPresenter;", "Lfr/free/nrw/commons/upload/depicts/DepictsContract$UserActionListener;", "repository", "Lfr/free/nrw/commons/repository/UploadRepository;", "ioScheduler", "Lio/reactivex/Scheduler;", "mainThreadScheduler", "(Lfr/free/nrw/commons/repository/UploadRepository;Lio/reactivex/Scheduler;Lio/reactivex/Scheduler;)V", "compositeDisposable", "Lio/reactivex/disposables/CompositeDisposable;", "controller", "Lfr/free/nrw/commons/bookmarks/items/BookmarkItemsController;", "getController", "()Lfr/free/nrw/commons/bookmarks/items/BookmarkItemsController;", "setController", "(Lfr/free/nrw/commons/bookmarks/items/BookmarkItemsController;)V", "depictedItems", "Landroidx/lifecycle/MutableLiveData;", "", "Lfr/free/nrw/commons/upload/structure/depictions/DepictedItem;", "depictsDao", "Lfr/free/nrw/commons/upload/depicts/DepictsDao;", "getDepictsDao", "()Lfr/free/nrw/commons/upload/depicts/DepictsDao;", "setDepictsDao", "(Lfr/free/nrw/commons/upload/depicts/DepictsDao;)V", "depictsHelper", "Lfr/free/nrw/commons/upload/depicts/DepictEditHelper;", "getDepictsHelper", "()Lfr/free/nrw/commons/upload/depicts/DepictEditHelper;", "setDepictsHelper", "(Lfr/free/nrw/commons/upload/depicts/DepictEditHelper;)V", "media", "Lfr/free/nrw/commons/Media;", "searchTerm", "Lio/reactivex/processors/PublishProcessor;", "", "view", "Lfr/free/nrw/commons/upload/depicts/DepictsContract$View;", "clearPreviousSelection", "", "getDepictedItems", "Landroidx/lifecycle/LiveData;", "getRecentDepictedItems", "", "onAttachView", "onAttachViewWithMedia", "onDepictItemClicked", "depictedItem", "onDetachView", "onPreviousButtonClicked", "searchForDepictions", "query", "searchResults", "Lio/reactivex/Flowable;", "querystring", "searchResultsWithTerm", "Lkotlin/Pair;", "term", "selectNewDepictions", "toSelect", "selectPlaceDepictions", "updateDepictions", "verifyDepictions", "Companion", "app-commons-v4.2.1-main_betaDebug"})
@javax.inject.Singleton
public final class DepictsPresenter implements fr.free.nrw.commons.upload.depicts.DepictsContract.UserActionListener {
    private final fr.free.nrw.commons.repository.UploadRepository repository = null;
    private final io.reactivex.Scheduler ioScheduler = null;
    private final io.reactivex.Scheduler mainThreadScheduler = null;
    @org.jetbrains.annotations.NotNull
    public static final fr.free.nrw.commons.upload.depicts.DepictsPresenter.Companion Companion = null;
    private static final fr.free.nrw.commons.upload.depicts.DepictsContract.View DUMMY = null;
    private fr.free.nrw.commons.upload.depicts.DepictsContract.View view;
    private final io.reactivex.disposables.CompositeDisposable compositeDisposable = null;
    private final io.reactivex.processors.PublishProcessor<java.lang.String> searchTerm = null;
    private final androidx.lifecycle.MutableLiveData<java.util.List<fr.free.nrw.commons.upload.structure.depictions.DepictedItem>> depictedItems = null;
    private fr.free.nrw.commons.Media media;
    @javax.inject.Inject
    public fr.free.nrw.commons.upload.depicts.DepictsDao depictsDao;
    
    /**
     * Helps to get all bookmarked items
     */
    @javax.inject.Inject
    public fr.free.nrw.commons.bookmarks.items.BookmarkItemsController controller;
    @javax.inject.Inject
    public fr.free.nrw.commons.upload.depicts.DepictEditHelper depictsHelper;
    
    @javax.inject.Inject
    public DepictsPresenter(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.repository.UploadRepository repository, @org.jetbrains.annotations.NotNull
    @javax.inject.Named(value = "io_thread")
    io.reactivex.Scheduler ioScheduler, @org.jetbrains.annotations.NotNull
    @javax.inject.Named(value = "main_thread")
    io.reactivex.Scheduler mainThreadScheduler) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final fr.free.nrw.commons.upload.depicts.DepictsDao getDepictsDao() {
        return null;
    }
    
    public final void setDepictsDao(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.upload.depicts.DepictsDao p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final fr.free.nrw.commons.bookmarks.items.BookmarkItemsController getController() {
        return null;
    }
    
    public final void setController(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.bookmarks.items.BookmarkItemsController p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final fr.free.nrw.commons.upload.depicts.DepictEditHelper getDepictsHelper() {
        return null;
    }
    
    public final void setDepictsHelper(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.upload.depicts.DepictEditHelper p0) {
    }
    
    @java.lang.Override
    public void onAttachView(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.upload.depicts.DepictsContract.View view) {
    }
    
    private final io.reactivex.Flowable<kotlin.Pair<java.util.List<fr.free.nrw.commons.upload.structure.depictions.DepictedItem>, java.lang.String>> searchResultsWithTerm(java.lang.String term) {
        return null;
    }
    
    private final io.reactivex.Flowable<java.util.List<fr.free.nrw.commons.upload.structure.depictions.DepictedItem>> searchResults(java.lang.String querystring) {
        return null;
    }
    
    @java.lang.Override
    public void onDetachView() {
    }
    
    /**
     * Selects the place depictions retrieved by the repository
     */
    @java.lang.Override
    public void selectPlaceDepictions() {
    }
    
    /**
     * Selects each [DepictedItem] in a given list as if they were clicked by the user by calling
     * [onDepictItemClicked] for each depiction and adding the depictions to [depictedItems]
     */
    private final void selectNewDepictions(java.util.List<fr.free.nrw.commons.upload.structure.depictions.DepictedItem> toSelect) {
    }
    
    @java.lang.Override
    public void clearPreviousSelection() {
    }
    
    @java.lang.Override
    public void onPreviousButtonClicked() {
    }
    
    @java.lang.Override
    public void onDepictItemClicked(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.upload.structure.depictions.DepictedItem depictedItem) {
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public androidx.lifecycle.LiveData<java.util.List<fr.free.nrw.commons.upload.structure.depictions.DepictedItem>> getDepictedItems() {
        return null;
    }
    
    /**
     * asks the repository to fetch depictions for the query
     * @param query
     */
    @java.lang.Override
    public void searchForDepictions(@org.jetbrains.annotations.NotNull
    java.lang.String query) {
    }
    
    /**
     * Check if depictions were selected
     * from the depiction list
     */
    @java.lang.Override
    public void verifyDepictions() {
    }
    
    /**
     * Gets the selected depicts and send them for posting to the server
     * and saves them in local storage
     */
    @android.annotation.SuppressLint(value = {"CheckResult"})
    @java.lang.Override
    public void updateDepictions(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.Media media) {
    }
    
    @java.lang.Override
    public void onAttachViewWithMedia(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.upload.depicts.DepictsContract.View view, @org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.Media media) {
    }
    
    /**
     * Get the depicts from DepictsRoomdataBase
     */
    @org.jetbrains.annotations.NotNull
    public final java.util.List<fr.free.nrw.commons.upload.structure.depictions.DepictedItem> getRecentDepictedItems() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lfr/free/nrw/commons/upload/depicts/DepictsPresenter$Companion;", "", "()V", "DUMMY", "Lfr/free/nrw/commons/upload/depicts/DepictsContract$View;", "app-commons-v4.2.1-main_betaDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}