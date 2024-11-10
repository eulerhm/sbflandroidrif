package org.wordpress.android.ui.reader.utils;

import java.lang.System;

/**
 * Injectable wrapper around ReaderSeenStatusToggleUseCase.
 *
 * ReaderSeenStatusToggleUseCase uses suspended functions and flows, which makes calling if from Java clunky.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0019\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lorg/wordpress/android/ui/reader/utils/ReaderPostSeenStatusWrapper;", "Lkotlinx/coroutines/CoroutineScope;", "seenStatusToggleUseCase", "Lorg/wordpress/android/ui/reader/usecases/ReaderSeenStatusToggleUseCase;", "bgDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Lorg/wordpress/android/ui/reader/usecases/ReaderSeenStatusToggleUseCase;Lkotlinx/coroutines/CoroutineDispatcher;)V", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "markPostAsSeenSilently", "", "post", "Lorg/wordpress/android/models/ReaderPost;", "org.wordpress.android_wordpressJalapenoDebug"})
@dagger.Reusable()
public final class ReaderPostSeenStatusWrapper implements kotlinx.coroutines.CoroutineScope {
    private final org.wordpress.android.ui.reader.usecases.ReaderSeenStatusToggleUseCase seenStatusToggleUseCase = null;
    private final kotlinx.coroutines.CoroutineDispatcher bgDispatcher = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.coroutines.CoroutineContext coroutineContext = null;
    
    @javax.inject.Inject()
    public ReaderPostSeenStatusWrapper(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.usecases.ReaderSeenStatusToggleUseCase seenStatusToggleUseCase, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher bgDispatcher) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public kotlin.coroutines.CoroutineContext getCoroutineContext() {
        return null;
    }
    
    public final void markPostAsSeenSilently(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.models.ReaderPost post) {
    }
}