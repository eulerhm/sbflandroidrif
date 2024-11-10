package org.wordpress.android.models.usecases;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0011\u0010\u0007\u001a\u00020\bH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\tJ\u001e\u0010\n\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00010\f0\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u000f"}, d2 = {"Lorg/wordpress/android/models/usecases/LocalCommentCacheUpdateHandler;", "", "localCommentCacheUpdateUseCase", "Lorg/wordpress/android/models/usecases/LocalCommentCacheUpdateUseCase;", "(Lorg/wordpress/android/models/usecases/LocalCommentCacheUpdateUseCase;)V", "useCases", "", "requestCommentsUpdate", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "subscribe", "Lkotlinx/coroutines/flow/Flow;", "Lorg/wordpress/android/usecase/UseCaseResult;", "Lorg/wordpress/android/models/usecases/CommentsUseCaseType;", "Lorg/wordpress/android/fluxc/store/CommentStore$CommentError;", "org.wordpress.android_wordpressJalapenoDebug"})
@javax.inject.Singleton()
public final class LocalCommentCacheUpdateHandler {
    private final org.wordpress.android.models.usecases.LocalCommentCacheUpdateUseCase localCommentCacheUpdateUseCase = null;
    private final java.util.List<org.wordpress.android.models.usecases.LocalCommentCacheUpdateUseCase> useCases = null;
    
    @javax.inject.Inject()
    public LocalCommentCacheUpdateHandler(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.models.usecases.LocalCommentCacheUpdateUseCase localCommentCacheUpdateUseCase) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<org.wordpress.android.usecase.UseCaseResult<org.wordpress.android.models.usecases.CommentsUseCaseType, org.wordpress.android.fluxc.store.CommentStore.CommentError, java.lang.Object>> subscribe() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object requestCommentsUpdate(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
}