package org.wordpress.android.models.usecases;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0019\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0012J\u0019\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0014H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0015J\u0019\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0017H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0018J\u0019\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u001aH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001bJ\u0019\u0010\u001c\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u001dH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001eJ\u001e\u0010\u001f\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00010!0 J\u0019\u0010\"\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0012R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R8\u0010\t\u001a,\u0012(\u0012&\u0012\u0006\b\u0001\u0012\u00020\u0001\u0012\u0006\b\u0001\u0012\u00020\u0001\u0012\u0006\b\u0001\u0012\u00020\u0001\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006#"}, d2 = {"Lorg/wordpress/android/models/usecases/UnifiedCommentsListHandler;", "", "paginateCommentsUseCase", "Lorg/wordpress/android/models/usecases/PaginateCommentsUseCase;", "batchModerationUseCase", "Lorg/wordpress/android/models/usecases/BatchModerateCommentsUseCase;", "moderationWithUndoUseCase", "Lorg/wordpress/android/models/usecases/ModerateCommentWithUndoUseCase;", "(Lorg/wordpress/android/models/usecases/PaginateCommentsUseCase;Lorg/wordpress/android/models/usecases/BatchModerateCommentsUseCase;Lorg/wordpress/android/models/usecases/ModerateCommentWithUndoUseCase;)V", "useCases", "", "Lorg/wordpress/android/usecase/FlowFSMUseCase;", "Lorg/wordpress/android/models/usecases/CommentsUseCaseType;", "Lorg/wordpress/android/fluxc/store/CommentStore$CommentError;", "moderateAfterUndo", "", "parameters", "Lorg/wordpress/android/models/usecases/ModerateCommentWithUndoUseCase$Parameters$ModerateWithFallbackParameters;", "(Lorg/wordpress/android/models/usecases/ModerateCommentWithUndoUseCase$Parameters$ModerateWithFallbackParameters;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "moderateComments", "Lorg/wordpress/android/models/usecases/BatchModerateCommentsUseCase$Parameters$ModerateCommentsParameters;", "(Lorg/wordpress/android/models/usecases/BatchModerateCommentsUseCase$Parameters$ModerateCommentsParameters;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "preModerateWithUndo", "Lorg/wordpress/android/models/usecases/ModerateCommentWithUndoUseCase$Parameters$ModerateCommentParameters;", "(Lorg/wordpress/android/models/usecases/ModerateCommentWithUndoUseCase$Parameters$ModerateCommentParameters;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "refreshFromCache", "Lorg/wordpress/android/models/usecases/PaginateCommentsUseCase$Parameters$ReloadFromCacheParameters;", "(Lorg/wordpress/android/models/usecases/PaginateCommentsUseCase$Parameters$ReloadFromCacheParameters;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "requestPage", "Lorg/wordpress/android/models/usecases/PaginateCommentsUseCase$Parameters$GetPageParameters;", "(Lorg/wordpress/android/models/usecases/PaginateCommentsUseCase$Parameters$GetPageParameters;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "subscribe", "Lkotlinx/coroutines/flow/Flow;", "Lorg/wordpress/android/usecase/UseCaseResult;", "undoCommentModeration", "org.wordpress.android_wordpressJalapenoDebug"})
public final class UnifiedCommentsListHandler {
    private final org.wordpress.android.models.usecases.PaginateCommentsUseCase paginateCommentsUseCase = null;
    private final org.wordpress.android.models.usecases.BatchModerateCommentsUseCase batchModerationUseCase = null;
    private final org.wordpress.android.models.usecases.ModerateCommentWithUndoUseCase moderationWithUndoUseCase = null;
    private final java.util.List<org.wordpress.android.usecase.FlowFSMUseCase<? extends java.lang.Object, ? extends java.lang.Object, ? extends java.lang.Object, org.wordpress.android.models.usecases.CommentsUseCaseType, org.wordpress.android.fluxc.store.CommentStore.CommentError>> useCases = null;
    
    @javax.inject.Inject()
    public UnifiedCommentsListHandler(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.models.usecases.PaginateCommentsUseCase paginateCommentsUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.models.usecases.BatchModerateCommentsUseCase batchModerationUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.models.usecases.ModerateCommentWithUndoUseCase moderationWithUndoUseCase) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<org.wordpress.android.usecase.UseCaseResult<org.wordpress.android.models.usecases.CommentsUseCaseType, org.wordpress.android.fluxc.store.CommentStore.CommentError, java.lang.Object>> subscribe() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object requestPage(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.models.usecases.PaginateCommentsUseCase.Parameters.GetPageParameters parameters, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object moderateComments(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.models.usecases.BatchModerateCommentsUseCase.Parameters.ModerateCommentsParameters parameters, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object preModerateWithUndo(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.models.usecases.ModerateCommentWithUndoUseCase.Parameters.ModerateCommentParameters parameters, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object moderateAfterUndo(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.models.usecases.ModerateCommentWithUndoUseCase.Parameters.ModerateWithFallbackParameters parameters, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object undoCommentModeration(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.models.usecases.ModerateCommentWithUndoUseCase.Parameters.ModerateWithFallbackParameters parameters, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object refreshFromCache(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.models.usecases.PaginateCommentsUseCase.Parameters.ReloadFromCacheParameters parameters, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
}