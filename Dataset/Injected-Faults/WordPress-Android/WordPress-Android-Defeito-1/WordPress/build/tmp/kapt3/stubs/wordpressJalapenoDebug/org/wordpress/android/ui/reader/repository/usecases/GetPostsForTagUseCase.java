package org.wordpress.android.ui.reader.repository.usecases;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0019\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J-\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000eH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0010"}, d2 = {"Lorg/wordpress/android/ui/reader/repository/usecases/GetPostsForTagUseCase;", "", "ioDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "readerPostTableWrapper", "Lorg/wordpress/android/datasets/wrappers/ReaderPostTableWrapper;", "(Lkotlinx/coroutines/CoroutineDispatcher;Lorg/wordpress/android/datasets/wrappers/ReaderPostTableWrapper;)V", "get", "Lorg/wordpress/android/models/ReaderPostList;", "readerTag", "Lorg/wordpress/android/models/ReaderTag;", "maxRows", "", "excludeTextColumns", "", "(Lorg/wordpress/android/models/ReaderTag;IZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "org.wordpress.android_wordpressJalapenoDebug"})
public final class GetPostsForTagUseCase {
    private final kotlinx.coroutines.CoroutineDispatcher ioDispatcher = null;
    private final org.wordpress.android.datasets.wrappers.ReaderPostTableWrapper readerPostTableWrapper = null;
    
    @javax.inject.Inject()
    public GetPostsForTagUseCase(@org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "IO_THREAD")
    kotlinx.coroutines.CoroutineDispatcher ioDispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.datasets.wrappers.ReaderPostTableWrapper readerPostTableWrapper) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object get(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.models.ReaderTag readerTag, int maxRows, boolean excludeTextColumns, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.wordpress.android.models.ReaderPostList> continuation) {
        return null;
    }
}