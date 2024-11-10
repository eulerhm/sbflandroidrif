package org.wordpress.android.ui.reader.repository.usecases;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0019\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J!\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\rR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u000e"}, d2 = {"Lorg/wordpress/android/ui/reader/repository/usecases/UndoBlockBlogUseCase;", "", "readerTracker", "Lorg/wordpress/android/ui/reader/tracker/ReaderTracker;", "bgDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Lorg/wordpress/android/ui/reader/tracker/ReaderTracker;Lkotlinx/coroutines/CoroutineDispatcher;)V", "undoBlockBlog", "", "blockedBlogData", "Lorg/wordpress/android/ui/reader/actions/ReaderBlogActions$BlockedBlogResult;", "source", "", "(Lorg/wordpress/android/ui/reader/actions/ReaderBlogActions$BlockedBlogResult;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "org.wordpress.android_wordpressJalapenoDebug"})
public final class UndoBlockBlogUseCase {
    private final org.wordpress.android.ui.reader.tracker.ReaderTracker readerTracker = null;
    private final kotlinx.coroutines.CoroutineDispatcher bgDispatcher = null;
    
    @javax.inject.Inject()
    public UndoBlockBlogUseCase(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.tracker.ReaderTracker readerTracker, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher bgDispatcher) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object undoBlockBlog(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.actions.ReaderBlogActions.BlockedBlogResult blockedBlogData, @org.jetbrains.annotations.NotNull()
    java.lang.String source, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
}