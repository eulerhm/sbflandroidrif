package org.wordpress.android.ui.reader.usecases;

import java.lang.System;

/**
 * This class handles bookmark/saveForLater button click events.
 * It updates the post in the database, tracks events, initiates pre-load content and shows snackbar/dialog.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ/\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0014J(\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u0018\u0010\u0018\u001a\u00020\u00112\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001aH\u0002J;\u0010\u001c\u001a\u00020\u0016*\b\u0012\u0004\u0012\u00020\r0\u001d2\u0006\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001aH\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001eR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u001f"}, d2 = {"Lorg/wordpress/android/ui/reader/usecases/ReaderPostBookmarkUseCase;", "", "readerTracker", "Lorg/wordpress/android/ui/reader/tracker/ReaderTracker;", "networkUtilsWrapper", "Lorg/wordpress/android/util/NetworkUtilsWrapper;", "readerPostActionsWrapper", "Lorg/wordpress/android/ui/reader/actions/ReaderPostActionsWrapper;", "readerPostTableWrapper", "Lorg/wordpress/android/datasets/wrappers/ReaderPostTableWrapper;", "(Lorg/wordpress/android/ui/reader/tracker/ReaderTracker;Lorg/wordpress/android/util/NetworkUtilsWrapper;Lorg/wordpress/android/ui/reader/actions/ReaderPostActionsWrapper;Lorg/wordpress/android/datasets/wrappers/ReaderPostTableWrapper;)V", "toggleBookmark", "Lkotlinx/coroutines/flow/Flow;", "Lorg/wordpress/android/ui/reader/usecases/BookmarkPostState;", "post", "Lorg/wordpress/android/models/ReaderPost;", "isBookmarkList", "", "source", "", "(Lorg/wordpress/android/models/ReaderPost;ZLjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "trackEvent", "", "bookmarked", "updatePostInDb", "blogId", "", "postId", "preloadPostContentIfNecessary", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;ZZJJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "org.wordpress.android_wordpressJalapenoDebug"})
public final class ReaderPostBookmarkUseCase {
    private final org.wordpress.android.ui.reader.tracker.ReaderTracker readerTracker = null;
    private final org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper = null;
    private final org.wordpress.android.ui.reader.actions.ReaderPostActionsWrapper readerPostActionsWrapper = null;
    private final org.wordpress.android.datasets.wrappers.ReaderPostTableWrapper readerPostTableWrapper = null;
    
    @javax.inject.Inject()
    public ReaderPostBookmarkUseCase(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.tracker.ReaderTracker readerTracker, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.actions.ReaderPostActionsWrapper readerPostActionsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.datasets.wrappers.ReaderPostTableWrapper readerPostTableWrapper) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object toggleBookmark(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.models.ReaderPost post, boolean isBookmarkList, @org.jetbrains.annotations.NotNull()
    java.lang.String source, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<? extends org.wordpress.android.ui.reader.usecases.BookmarkPostState>> continuation) {
        return null;
    }
    
    private final java.lang.Object preloadPostContentIfNecessary(kotlinx.coroutines.flow.FlowCollector<? super org.wordpress.android.ui.reader.usecases.BookmarkPostState> $this$preloadPostContentIfNecessary, boolean bookmarked, boolean isBookmarkList, long blogId, long postId, kotlin.coroutines.Continuation<? super kotlin.Unit> p5) {
        return null;
    }
    
    private final boolean updatePostInDb(long blogId, long postId) {
        return false;
    }
    
    private final void trackEvent(boolean bookmarked, boolean isBookmarkList, org.wordpress.android.models.ReaderPost post, java.lang.String source) {
    }
}