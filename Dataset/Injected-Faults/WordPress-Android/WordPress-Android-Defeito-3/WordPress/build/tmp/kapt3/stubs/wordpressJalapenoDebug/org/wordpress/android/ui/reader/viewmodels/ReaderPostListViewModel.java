package org.wordpress.android.ui.reader.viewmodels;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0096\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B;\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\b\u0001\u0010\n\u001a\u00020\u000b\u0012\b\b\u0001\u0010\f\u001a\u00020\u000b\u00a2\u0006\u0002\u0010\rJ\b\u0010-\u001a\u00020\u0015H\u0002J\u001e\u0010.\u001a\u00020\u00152\u0006\u0010/\u001a\u00020!2\u0006\u00100\u001a\u00020\u001b2\u0006\u00101\u001a\u000202J&\u00103\u001a\u00020\u00152\u0006\u00104\u001a\u0002052\u0006\u00106\u001a\u0002052\u0006\u00107\u001a\u00020\u001b2\u0006\u00101\u001a\u000202J\u000e\u00108\u001a\u00020\u00152\u0006\u00109\u001a\u00020:J\u000e\u0010;\u001a\u00020\u00152\u0006\u0010/\u001a\u00020!J\u001e\u0010<\u001a\u00020\u00152\u0006\u0010/\u001a\u00020!2\u0006\u00100\u001a\u00020\u001b2\u0006\u00101\u001a\u000202J\u001e\u0010=\u001a\u00020\u00152\u0006\u0010>\u001a\u00020\u001b2\u0006\u0010?\u001a\u00020\u001b2\u0006\u0010@\u001a\u00020\u001bJ(\u0010A\u001a\u00020\u00152\u0006\u0010>\u001a\u00020\u001b2\u0006\u0010?\u001a\u00020\u001b2\u0006\u0010@\u001a\u00020\u001b2\b\u0010B\u001a\u0004\u0018\u00010CJ\u001e\u0010D\u001a\u00020\u00152\u0006\u0010/\u001a\u00020!2\u0006\u00100\u001a\u00020\u001b2\u0006\u00101\u001a\u000202J\u001e\u0010E\u001a\u00020\u00152\u0006\u0010/\u001a\u00020!2\u0006\u00100\u001a\u00020\u001b2\u0006\u00101\u001a\u000202J\u000e\u0010F\u001a\u00020\u00152\u0006\u0010G\u001a\u00020HJ\u001e\u0010I\u001a\u00020\u00152\u0006\u0010/\u001a\u00020!2\u0006\u00100\u001a\u00020\u001b2\u0006\u00101\u001a\u000202J&\u0010J\u001a\u00020\u00152\u0006\u00104\u001a\u0002052\u0006\u00106\u001a\u0002052\u0006\u00107\u001a\u00020\u001b2\u0006\u00101\u001a\u000202J\u001e\u0010K\u001a\u00020\u00152\u0006\u0010/\u001a\u00020!2\u0006\u00100\u001a\u00020\u001b2\u0006\u00101\u001a\u000202J\u0010\u0010L\u001a\u00020\u00152\b\u0010%\u001a\u0004\u0018\u00010&R\u001a\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00100\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00100\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u00100\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u001d\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0010\u0010 \u001a\u0004\u0018\u00010!X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001d\u0010\"\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00100\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010%\u001a\u0004\u0018\u00010&X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\'\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00100\u001d\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010\u001fR\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010)\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u00100\u001d\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010\u001fR\u0017\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00190\u001d\u00a2\u0006\b\n\u0000\u001a\u0004\b,\u0010\u001f\u00a8\u0006M"}, d2 = {"Lorg/wordpress/android/ui/reader/viewmodels/ReaderPostListViewModel;", "Lorg/wordpress/android/viewmodel/ScopedViewModel;", "readerPostCardActionsHandler", "Lorg/wordpress/android/ui/reader/discover/ReaderPostCardActionsHandler;", "reblogUseCase", "Lorg/wordpress/android/ui/reader/reblog/ReblogUseCase;", "readerTracker", "Lorg/wordpress/android/ui/reader/tracker/ReaderTracker;", "seenStatusToggleUseCase", "Lorg/wordpress/android/ui/reader/usecases/ReaderSeenStatusToggleUseCase;", "mainDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "bgDispatcher", "(Lorg/wordpress/android/ui/reader/discover/ReaderPostCardActionsHandler;Lorg/wordpress/android/ui/reader/reblog/ReblogUseCase;Lorg/wordpress/android/ui/reader/tracker/ReaderTracker;Lorg/wordpress/android/ui/reader/usecases/ReaderSeenStatusToggleUseCase;Lkotlinx/coroutines/CoroutineDispatcher;Lkotlinx/coroutines/CoroutineDispatcher;)V", "_navigationEvents", "Landroidx/lifecycle/MediatorLiveData;", "Lorg/wordpress/android/viewmodel/Event;", "Lorg/wordpress/android/ui/reader/discover/ReaderNavigationEvents;", "_preloadPostEvents", "Lorg/wordpress/android/ui/reader/usecases/BookmarkPostState$PreLoadPostContent;", "_refreshPosts", "", "_snackbarEvents", "Lorg/wordpress/android/ui/pages/SnackbarMessageHolder;", "_updateFollowStatus", "Lorg/wordpress/android/ui/reader/usecases/ReaderSiteFollowUseCase$FollowSiteState$FollowStatusChanged;", "isStarted", "", "navigationEvents", "Landroidx/lifecycle/LiveData;", "getNavigationEvents", "()Landroidx/lifecycle/LiveData;", "pendingReblogPost", "Lorg/wordpress/android/models/ReaderPost;", "preloadPostEvents", "getPreloadPostEvents", "()Landroidx/lifecycle/MediatorLiveData;", "readerViewModel", "Lorg/wordpress/android/ui/reader/viewmodels/ReaderViewModel;", "refreshPosts", "getRefreshPosts", "snackbarEvents", "getSnackbarEvents", "updateFollowStatus", "getUpdateFollowStatus", "init", "onBlockSiteButtonClicked", "post", "bookmarksList", "source", "", "onBookmarkButtonClicked", "blogId", "", "postId", "isBookmarkList", "onEmptyStateButtonTapped", "tag", "Lorg/wordpress/android/models/ReaderTag;", "onExternalPostOpened", "onFollowSiteClicked", "onFragmentPause", "isTopLevelFragment", "isSearch", "isFilterable", "onFragmentResume", "subfilterListItem", "Lorg/wordpress/android/ui/reader/subfilter/SubfilterListItem;", "onLikeButtonClicked", "onReblogButtonClicked", "onReblogSiteSelected", "siteLocalId", "", "onReportPostButtonClicked", "onSiteNotificationMenuClicked", "onToggleSeenStatusClicked", "start", "org.wordpress.android_wordpressJalapenoDebug"})
public final class ReaderPostListViewModel extends org.wordpress.android.viewmodel.ScopedViewModel {
    private final org.wordpress.android.ui.reader.discover.ReaderPostCardActionsHandler readerPostCardActionsHandler = null;
    private final org.wordpress.android.ui.reader.reblog.ReblogUseCase reblogUseCase = null;
    private final org.wordpress.android.ui.reader.tracker.ReaderTracker readerTracker = null;
    private final org.wordpress.android.ui.reader.usecases.ReaderSeenStatusToggleUseCase seenStatusToggleUseCase = null;
    private final kotlinx.coroutines.CoroutineDispatcher mainDispatcher = null;
    private final kotlinx.coroutines.CoroutineDispatcher bgDispatcher = null;
    private boolean isStarted = false;
    private org.wordpress.android.ui.reader.viewmodels.ReaderViewModel readerViewModel;
    
    /**
     * Post which is about to be reblogged after the user selects a target site.
     */
    private org.wordpress.android.models.ReaderPost pendingReblogPost;
    private final androidx.lifecycle.MediatorLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.reader.discover.ReaderNavigationEvents>> _navigationEvents = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.reader.discover.ReaderNavigationEvents>> navigationEvents = null;
    private final androidx.lifecycle.MediatorLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.pages.SnackbarMessageHolder>> _snackbarEvents = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.pages.SnackbarMessageHolder>> snackbarEvents = null;
    private final androidx.lifecycle.MediatorLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.reader.usecases.BookmarkPostState.PreLoadPostContent>> _preloadPostEvents = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MediatorLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.reader.usecases.BookmarkPostState.PreLoadPostContent>> preloadPostEvents = null;
    private final androidx.lifecycle.MediatorLiveData<org.wordpress.android.viewmodel.Event<kotlin.Unit>> _refreshPosts = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<kotlin.Unit>> refreshPosts = null;
    private final androidx.lifecycle.MediatorLiveData<org.wordpress.android.ui.reader.usecases.ReaderSiteFollowUseCase.FollowSiteState.FollowStatusChanged> _updateFollowStatus = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.ui.reader.usecases.ReaderSiteFollowUseCase.FollowSiteState.FollowStatusChanged> updateFollowStatus = null;
    
    @javax.inject.Inject()
    public ReaderPostListViewModel(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.discover.ReaderPostCardActionsHandler readerPostCardActionsHandler, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.reblog.ReblogUseCase reblogUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.tracker.ReaderTracker readerTracker, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.usecases.ReaderSeenStatusToggleUseCase seenStatusToggleUseCase, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "UI_THREAD")
    kotlinx.coroutines.CoroutineDispatcher mainDispatcher, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher bgDispatcher) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.reader.discover.ReaderNavigationEvents>> getNavigationEvents() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.pages.SnackbarMessageHolder>> getSnackbarEvents() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MediatorLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.reader.usecases.BookmarkPostState.PreLoadPostContent>> getPreloadPostEvents() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<kotlin.Unit>> getRefreshPosts() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.ui.reader.usecases.ReaderSiteFollowUseCase.FollowSiteState.FollowStatusChanged> getUpdateFollowStatus() {
        return null;
    }
    
    public final void start(@org.jetbrains.annotations.Nullable()
    org.wordpress.android.ui.reader.viewmodels.ReaderViewModel readerViewModel) {
    }
    
    private final void init() {
    }
    
    /**
     * Handles reblog button action
     *
     * @param post post to reblog
     */
    public final void onReblogButtonClicked(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.models.ReaderPost post, boolean bookmarksList, @org.jetbrains.annotations.NotNull()
    java.lang.String source) {
    }
    
    public final void onBlockSiteButtonClicked(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.models.ReaderPost post, boolean bookmarksList, @org.jetbrains.annotations.NotNull()
    java.lang.String source) {
    }
    
    public final void onBookmarkButtonClicked(long blogId, long postId, boolean isBookmarkList, @org.jetbrains.annotations.NotNull()
    java.lang.String source) {
    }
    
    public final void onFollowSiteClicked(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.models.ReaderPost post, boolean bookmarksList, @org.jetbrains.annotations.NotNull()
    java.lang.String source) {
    }
    
    public final void onSiteNotificationMenuClicked(long blogId, long postId, boolean isBookmarkList, @org.jetbrains.annotations.NotNull()
    java.lang.String source) {
    }
    
    public final void onLikeButtonClicked(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.models.ReaderPost post, boolean bookmarksList, @org.jetbrains.annotations.NotNull()
    java.lang.String source) {
    }
    
    public final void onReportPostButtonClicked(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.models.ReaderPost post, boolean bookmarksList, @org.jetbrains.annotations.NotNull()
    java.lang.String source) {
    }
    
    public final void onToggleSeenStatusClicked(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.models.ReaderPost post, boolean bookmarksList, @org.jetbrains.annotations.NotNull()
    java.lang.String source) {
    }
    
    public final void onExternalPostOpened(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.models.ReaderPost post) {
    }
    
    /**
     * Handles site selection
     *
     * @param site selected site to reblog to
     */
    public final void onReblogSiteSelected(int siteLocalId) {
    }
    
    public final void onEmptyStateButtonTapped(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.models.ReaderTag tag) {
    }
    
    public final void onFragmentResume(boolean isTopLevelFragment, boolean isSearch, boolean isFilterable, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.ui.reader.subfilter.SubfilterListItem subfilterListItem) {
    }
    
    public final void onFragmentPause(boolean isTopLevelFragment, boolean isSearch, boolean isFilterable) {
    }
}