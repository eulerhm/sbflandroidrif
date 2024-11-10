package org.wordpress.android.ui.reader.repository.usecases;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\'\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0012J\u0019\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u0015H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0016J+\u0010\u0017\u001a\u00020\u0018*\b\u0012\u0004\u0012\u00020\u000e0\u00192\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001aR\u0016\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u001b"}, d2 = {"Lorg/wordpress/android/ui/reader/repository/usecases/BlockBlogUseCase;", "", "networkUtilsWrapper", "Lorg/wordpress/android/util/NetworkUtilsWrapper;", "readerTracker", "Lorg/wordpress/android/ui/reader/tracker/ReaderTracker;", "readerBlogActionsWrapper", "Lorg/wordpress/android/ui/reader/actions/ReaderBlogActionsWrapper;", "(Lorg/wordpress/android/util/NetworkUtilsWrapper;Lorg/wordpress/android/ui/reader/tracker/ReaderTracker;Lorg/wordpress/android/ui/reader/actions/ReaderBlogActionsWrapper;)V", "continuation", "Lkotlin/coroutines/Continuation;", "", "blockBlog", "Lkotlinx/coroutines/flow/Flow;", "Lorg/wordpress/android/ui/reader/repository/usecases/BlockSiteState;", "blogId", "", "feedId", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "blockBlogAndWaitForResult", "blockedBlogResult", "Lorg/wordpress/android/ui/reader/actions/ReaderBlogActions$BlockedBlogResult;", "(Lorg/wordpress/android/ui/reader/actions/ReaderBlogActions$BlockedBlogResult;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "performAction", "", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "org.wordpress.android_wordpressJalapenoDebug"})
public final class BlockBlogUseCase {
    private final org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper = null;
    private final org.wordpress.android.ui.reader.tracker.ReaderTracker readerTracker = null;
    private final org.wordpress.android.ui.reader.actions.ReaderBlogActionsWrapper readerBlogActionsWrapper = null;
    private kotlin.coroutines.Continuation<? super java.lang.Boolean> continuation;
    
    @javax.inject.Inject()
    public BlockBlogUseCase(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.tracker.ReaderTracker readerTracker, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.actions.ReaderBlogActionsWrapper readerBlogActionsWrapper) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object blockBlog(long blogId, long feedId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<? extends org.wordpress.android.ui.reader.repository.usecases.BlockSiteState>> continuation) {
        return null;
    }
    
    private final java.lang.Object performAction(kotlinx.coroutines.flow.FlowCollector<? super org.wordpress.android.ui.reader.repository.usecases.BlockSiteState> $this$performAction, long blogId, long feedId, kotlin.coroutines.Continuation<? super kotlin.Unit> p3) {
        return null;
    }
    
    private final java.lang.Object blockBlogAndWaitForResult(org.wordpress.android.ui.reader.actions.ReaderBlogActions.BlockedBlogResult blockedBlogResult, kotlin.coroutines.Continuation<? super java.lang.Boolean> continuation) {
        return null;
    }
}