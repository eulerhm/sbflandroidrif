package org.wordpress.android.models.usecases;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001B!\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e\u00a8\u0006\u000f"}, d2 = {"Lorg/wordpress/android/models/usecases/ModerateCommentsResourceProvider;", "", "commentsStore", "Lorg/wordpress/android/fluxc/store/CommentsStore;", "localCommentCacheUpdateHandler", "Lorg/wordpress/android/models/usecases/LocalCommentCacheUpdateHandler;", "bgDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Lorg/wordpress/android/fluxc/store/CommentsStore;Lorg/wordpress/android/models/usecases/LocalCommentCacheUpdateHandler;Lkotlinx/coroutines/CoroutineDispatcher;)V", "getBgDispatcher", "()Lkotlinx/coroutines/CoroutineDispatcher;", "getCommentsStore", "()Lorg/wordpress/android/fluxc/store/CommentsStore;", "getLocalCommentCacheUpdateHandler", "()Lorg/wordpress/android/models/usecases/LocalCommentCacheUpdateHandler;", "org.wordpress.android_wordpressJalapenoDebug"})
@dagger.Reusable()
public final class ModerateCommentsResourceProvider {
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.fluxc.store.CommentsStore commentsStore = null;
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.models.usecases.LocalCommentCacheUpdateHandler localCommentCacheUpdateHandler = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.CoroutineDispatcher bgDispatcher = null;
    
    @javax.inject.Inject()
    public ModerateCommentsResourceProvider(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.CommentsStore commentsStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.models.usecases.LocalCommentCacheUpdateHandler localCommentCacheUpdateHandler, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher bgDispatcher) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.fluxc.store.CommentsStore getCommentsStore() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.models.usecases.LocalCommentCacheUpdateHandler getLocalCommentCacheUpdateHandler() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.CoroutineDispatcher getBgDispatcher() {
        return null;
    }
}