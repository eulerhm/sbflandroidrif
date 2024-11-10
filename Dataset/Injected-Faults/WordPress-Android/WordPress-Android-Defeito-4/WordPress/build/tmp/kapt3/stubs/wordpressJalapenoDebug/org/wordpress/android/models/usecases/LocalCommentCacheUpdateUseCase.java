package org.wordpress.android.models.usecases;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002 \u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0001:\u0002\u0007\bB\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0006\u00a8\u0006\t"}, d2 = {"Lorg/wordpress/android/models/usecases/LocalCommentCacheUpdateUseCase;", "Lorg/wordpress/android/usecase/FlowFSMUseCase;", "", "Lorg/wordpress/android/models/usecases/LocalCommentCacheUpdateUseCase$PropagateCommentsUpdateAction;", "Lorg/wordpress/android/models/usecases/CommentsUseCaseType;", "Lorg/wordpress/android/fluxc/store/CommentStore$CommentError;", "()V", "CommentsUpdateState", "PropagateCommentsUpdateAction", "org.wordpress.android_wordpressJalapenoDebug"})
public final class LocalCommentCacheUpdateUseCase extends org.wordpress.android.usecase.FlowFSMUseCase<java.lang.Object, org.wordpress.android.models.usecases.LocalCommentCacheUpdateUseCase.PropagateCommentsUpdateAction, java.lang.Object, org.wordpress.android.models.usecases.CommentsUseCaseType, org.wordpress.android.fluxc.store.CommentStore.CommentError> {
    
    @javax.inject.Inject()
    public LocalCommentCacheUpdateUseCase() {
        super(null, null);
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002 \u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0001:\u0001\u0007B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0006\u0082\u0001\u0001\b\u00a8\u0006\t"}, d2 = {"Lorg/wordpress/android/models/usecases/LocalCommentCacheUpdateUseCase$CommentsUpdateState;", "Lorg/wordpress/android/usecase/FlowFSMUseCase$StateInterface;", "", "Lorg/wordpress/android/models/usecases/LocalCommentCacheUpdateUseCase$PropagateCommentsUpdateAction;", "Lorg/wordpress/android/models/usecases/CommentsUseCaseType;", "Lorg/wordpress/android/fluxc/store/CommentStore$CommentError;", "()V", "Idle", "Lorg/wordpress/android/models/usecases/LocalCommentCacheUpdateUseCase$CommentsUpdateState$Idle;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class CommentsUpdateState implements org.wordpress.android.usecase.FlowFSMUseCase.StateInterface<java.lang.Object, org.wordpress.android.models.usecases.LocalCommentCacheUpdateUseCase.PropagateCommentsUpdateAction, java.lang.Object, org.wordpress.android.models.usecases.CommentsUseCaseType, org.wordpress.android.fluxc.store.CommentStore.CommentError> {
        
        private CommentsUpdateState() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J_\u0010\u0003\u001a \u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u00042\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u00062\u001e\u0010\u000b\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00050\r0\fH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000e\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u000f"}, d2 = {"Lorg/wordpress/android/models/usecases/LocalCommentCacheUpdateUseCase$CommentsUpdateState$Idle;", "Lorg/wordpress/android/models/usecases/LocalCommentCacheUpdateUseCase$CommentsUpdateState;", "()V", "runAction", "Lorg/wordpress/android/usecase/FlowFSMUseCase$StateInterface;", "", "Lorg/wordpress/android/models/usecases/LocalCommentCacheUpdateUseCase$PropagateCommentsUpdateAction;", "Lorg/wordpress/android/models/usecases/CommentsUseCaseType;", "Lorg/wordpress/android/fluxc/store/CommentStore$CommentError;", "utilsProvider", "action", "flowChannel", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "Lorg/wordpress/android/usecase/UseCaseResult;", "(Ljava/lang/Object;Lorg/wordpress/android/models/usecases/LocalCommentCacheUpdateUseCase$PropagateCommentsUpdateAction;Lkotlinx/coroutines/flow/MutableSharedFlow;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Idle extends org.wordpress.android.models.usecases.LocalCommentCacheUpdateUseCase.CommentsUpdateState {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.models.usecases.LocalCommentCacheUpdateUseCase.CommentsUpdateState.Idle INSTANCE = null;
            
            private Idle() {
                super();
            }
            
            @org.jetbrains.annotations.Nullable()
            @java.lang.Override()
            public java.lang.Object runAction(@org.jetbrains.annotations.NotNull()
            java.lang.Object utilsProvider, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.models.usecases.LocalCommentCacheUpdateUseCase.PropagateCommentsUpdateAction action, @org.jetbrains.annotations.NotNull()
            kotlinx.coroutines.flow.MutableSharedFlow<org.wordpress.android.usecase.UseCaseResult<org.wordpress.android.models.usecases.CommentsUseCaseType, org.wordpress.android.fluxc.store.CommentStore.CommentError, java.lang.Object>> flowChannel, @org.jetbrains.annotations.NotNull()
            kotlin.coroutines.Continuation<? super org.wordpress.android.usecase.FlowFSMUseCase.StateInterface<java.lang.Object, org.wordpress.android.models.usecases.LocalCommentCacheUpdateUseCase.PropagateCommentsUpdateAction, java.lang.Object, org.wordpress.android.models.usecases.CommentsUseCaseType, org.wordpress.android.fluxc.store.CommentStore.CommentError>> continuation) {
                return null;
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0001\u0003B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0001\u0004\u00a8\u0006\u0005"}, d2 = {"Lorg/wordpress/android/models/usecases/LocalCommentCacheUpdateUseCase$PropagateCommentsUpdateAction;", "", "()V", "UpdatedComments", "Lorg/wordpress/android/models/usecases/LocalCommentCacheUpdateUseCase$PropagateCommentsUpdateAction$UpdatedComments;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class PropagateCommentsUpdateAction {
        
        private PropagateCommentsUpdateAction() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/models/usecases/LocalCommentCacheUpdateUseCase$PropagateCommentsUpdateAction$UpdatedComments;", "Lorg/wordpress/android/models/usecases/LocalCommentCacheUpdateUseCase$PropagateCommentsUpdateAction;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class UpdatedComments extends org.wordpress.android.models.usecases.LocalCommentCacheUpdateUseCase.PropagateCommentsUpdateAction {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.models.usecases.LocalCommentCacheUpdateUseCase.PropagateCommentsUpdateAction.UpdatedComments INSTANCE = null;
            
            private UpdatedComments() {
                super();
            }
        }
    }
}