package org.wordpress.android.ui.reader.usecases;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0019\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J7\u0010\u0007\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\t\u0012\u0004\u0012\u00020\n0\b2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\nH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000fJ\u0012\u0010\u0010\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0011\u001a\u00020\tH\u0002J\u0012\u0010\u0012\u001a\u00020\n2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0015"}, d2 = {"Lorg/wordpress/android/ui/reader/usecases/ReaderGetPostUseCase;", "", "ioDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "readerPostTableWrapper", "Lorg/wordpress/android/datasets/wrappers/ReaderPostTableWrapper;", "(Lkotlinx/coroutines/CoroutineDispatcher;Lorg/wordpress/android/datasets/wrappers/ReaderPostTableWrapper;)V", "get", "Lkotlin/Pair;", "Lorg/wordpress/android/models/ReaderPost;", "", "blogId", "", "postId", "isFeed", "(JJZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getDiscoverEditorPickSourcePost", "post", "isDiscoverEditorPickPost", "discoverPostData", "Lorg/wordpress/android/models/ReaderPostDiscoverData;", "org.wordpress.android_wordpressJalapenoDebug"})
public final class ReaderGetPostUseCase {
    private final kotlinx.coroutines.CoroutineDispatcher ioDispatcher = null;
    private final org.wordpress.android.datasets.wrappers.ReaderPostTableWrapper readerPostTableWrapper = null;
    
    @javax.inject.Inject()
    public ReaderGetPostUseCase(@org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "IO_THREAD")
    kotlinx.coroutines.CoroutineDispatcher ioDispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.datasets.wrappers.ReaderPostTableWrapper readerPostTableWrapper) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object get(long blogId, long postId, boolean isFeed, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Pair<? extends org.wordpress.android.models.ReaderPost, java.lang.Boolean>> continuation) {
        return null;
    }
    
    private final org.wordpress.android.models.ReaderPost getDiscoverEditorPickSourcePost(org.wordpress.android.models.ReaderPost post) {
        return null;
    }
    
    private final boolean isDiscoverEditorPickPost(org.wordpress.android.models.ReaderPostDiscoverData discoverPostData) {
        return false;
    }
}