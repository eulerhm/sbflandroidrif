package org.wordpress.android.models.usecases;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002 \u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0001:\u0004\t\n\u000b\fB\u000f\b\u0007\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u00a2\u0006\u0002\u0010\b\u00a8\u0006\r"}, d2 = {"Lorg/wordpress/android/models/usecases/ModerateCommentWithUndoUseCase;", "Lorg/wordpress/android/usecase/FlowFSMUseCase;", "Lorg/wordpress/android/models/usecases/ModerateCommentsResourceProvider;", "Lorg/wordpress/android/models/usecases/ModerateCommentWithUndoUseCase$ModerateCommentsAction;", "", "Lorg/wordpress/android/models/usecases/CommentsUseCaseType;", "Lorg/wordpress/android/fluxc/store/CommentStore$CommentError;", "moderateCommentsResourceProvider", "(Lorg/wordpress/android/models/usecases/ModerateCommentsResourceProvider;)V", "ModerateCommentsAction", "ModerateCommentsState", "Parameters", "SingleCommentModerationResult", "org.wordpress.android_wordpressJalapenoDebug"})
public final class ModerateCommentWithUndoUseCase extends org.wordpress.android.usecase.FlowFSMUseCase<org.wordpress.android.models.usecases.ModerateCommentsResourceProvider, org.wordpress.android.models.usecases.ModerateCommentWithUndoUseCase.ModerateCommentsAction, java.lang.Object, org.wordpress.android.models.usecases.CommentsUseCaseType, org.wordpress.android.fluxc.store.CommentStore.CommentError> {
    
    @javax.inject.Inject()
    public ModerateCommentWithUndoUseCase(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.models.usecases.ModerateCommentsResourceProvider moderateCommentsResourceProvider) {
        super(null, null);
    }
    
    @kotlin.Suppress(names = {"LongMethod"})
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002 \u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0001:\u0001\bB\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0007\u0082\u0001\u0001\t\u00a8\u0006\n"}, d2 = {"Lorg/wordpress/android/models/usecases/ModerateCommentWithUndoUseCase$ModerateCommentsState;", "Lorg/wordpress/android/usecase/FlowFSMUseCase$StateInterface;", "Lorg/wordpress/android/models/usecases/ModerateCommentsResourceProvider;", "Lorg/wordpress/android/models/usecases/ModerateCommentWithUndoUseCase$ModerateCommentsAction;", "", "Lorg/wordpress/android/models/usecases/CommentsUseCaseType;", "Lorg/wordpress/android/fluxc/store/CommentStore$CommentError;", "()V", "Idle", "Lorg/wordpress/android/models/usecases/ModerateCommentWithUndoUseCase$ModerateCommentsState$Idle;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class ModerateCommentsState implements org.wordpress.android.usecase.FlowFSMUseCase.StateInterface<org.wordpress.android.models.usecases.ModerateCommentsResourceProvider, org.wordpress.android.models.usecases.ModerateCommentWithUndoUseCase.ModerateCommentsAction, java.lang.Object, org.wordpress.android.models.usecases.CommentsUseCaseType, org.wordpress.android.fluxc.store.CommentStore.CommentError> {
        
        private ModerateCommentsState() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J_\u0010\u0003\u001a \u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u00042\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u00062\u001e\u0010\f\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00070\u000e0\rH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000f\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0010"}, d2 = {"Lorg/wordpress/android/models/usecases/ModerateCommentWithUndoUseCase$ModerateCommentsState$Idle;", "Lorg/wordpress/android/models/usecases/ModerateCommentWithUndoUseCase$ModerateCommentsState;", "()V", "runAction", "Lorg/wordpress/android/usecase/FlowFSMUseCase$StateInterface;", "Lorg/wordpress/android/models/usecases/ModerateCommentsResourceProvider;", "Lorg/wordpress/android/models/usecases/ModerateCommentWithUndoUseCase$ModerateCommentsAction;", "", "Lorg/wordpress/android/models/usecases/CommentsUseCaseType;", "Lorg/wordpress/android/fluxc/store/CommentStore$CommentError;", "resourceProvider", "action", "flowChannel", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "Lorg/wordpress/android/usecase/UseCaseResult;", "(Lorg/wordpress/android/models/usecases/ModerateCommentsResourceProvider;Lorg/wordpress/android/models/usecases/ModerateCommentWithUndoUseCase$ModerateCommentsAction;Lkotlinx/coroutines/flow/MutableSharedFlow;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Idle extends org.wordpress.android.models.usecases.ModerateCommentWithUndoUseCase.ModerateCommentsState {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.models.usecases.ModerateCommentWithUndoUseCase.ModerateCommentsState.Idle INSTANCE = null;
            
            private Idle() {
                super();
            }
            
            @org.jetbrains.annotations.Nullable()
            @java.lang.Override()
            public java.lang.Object runAction(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.models.usecases.ModerateCommentsResourceProvider resourceProvider, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.models.usecases.ModerateCommentWithUndoUseCase.ModerateCommentsAction action, @org.jetbrains.annotations.NotNull()
            kotlinx.coroutines.flow.MutableSharedFlow<org.wordpress.android.usecase.UseCaseResult<org.wordpress.android.models.usecases.CommentsUseCaseType, org.wordpress.android.fluxc.store.CommentStore.CommentError, java.lang.Object>> flowChannel, @org.jetbrains.annotations.NotNull()
            kotlin.coroutines.Continuation<? super org.wordpress.android.usecase.FlowFSMUseCase.StateInterface<org.wordpress.android.models.usecases.ModerateCommentsResourceProvider, org.wordpress.android.models.usecases.ModerateCommentWithUndoUseCase.ModerateCommentsAction, java.lang.Object, org.wordpress.android.models.usecases.CommentsUseCaseType, org.wordpress.android.fluxc.store.CommentStore.CommentError>> continuation) {
                return null;
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000e\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u000f\u001a\u00020\u0005H\u00c6\u0003J\'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0014\u001a\u00020\u0015H\u00d6\u0001J\t\u0010\u0016\u001a\u00020\u0017H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f\u00a8\u0006\u0018"}, d2 = {"Lorg/wordpress/android/models/usecases/ModerateCommentWithUndoUseCase$SingleCommentModerationResult;", "", "remoteCommentId", "", "newStatus", "Lorg/wordpress/android/fluxc/model/CommentStatus;", "oldStatus", "(JLorg/wordpress/android/fluxc/model/CommentStatus;Lorg/wordpress/android/fluxc/model/CommentStatus;)V", "getNewStatus", "()Lorg/wordpress/android/fluxc/model/CommentStatus;", "getOldStatus", "getRemoteCommentId", "()J", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class SingleCommentModerationResult {
        private final long remoteCommentId = 0L;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.fluxc.model.CommentStatus newStatus = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.fluxc.model.CommentStatus oldStatus = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.models.usecases.ModerateCommentWithUndoUseCase.SingleCommentModerationResult copy(long remoteCommentId, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.CommentStatus newStatus, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.CommentStatus oldStatus) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.lang.String toString() {
            return null;
        }
        
        public SingleCommentModerationResult(long remoteCommentId, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.CommentStatus newStatus, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.CommentStatus oldStatus) {
            super();
        }
        
        public final long component1() {
            return 0L;
        }
        
        public final long getRemoteCommentId() {
            return 0L;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.fluxc.model.CommentStatus component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.fluxc.model.CommentStatus getNewStatus() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.fluxc.model.CommentStatus component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.fluxc.model.CommentStatus getOldStatus() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0003\u0006\u0007\b\u00a8\u0006\t"}, d2 = {"Lorg/wordpress/android/models/usecases/ModerateCommentWithUndoUseCase$ModerateCommentsAction;", "", "()V", "OnModerateComment", "OnPushComment", "OnUndoModerateComment", "Lorg/wordpress/android/models/usecases/ModerateCommentWithUndoUseCase$ModerateCommentsAction$OnModerateComment;", "Lorg/wordpress/android/models/usecases/ModerateCommentWithUndoUseCase$ModerateCommentsAction$OnPushComment;", "Lorg/wordpress/android/models/usecases/ModerateCommentWithUndoUseCase$ModerateCommentsAction$OnUndoModerateComment;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class ModerateCommentsAction {
        
        private ModerateCommentsAction() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/models/usecases/ModerateCommentWithUndoUseCase$ModerateCommentsAction$OnModerateComment;", "Lorg/wordpress/android/models/usecases/ModerateCommentWithUndoUseCase$ModerateCommentsAction;", "parameters", "Lorg/wordpress/android/models/usecases/ModerateCommentWithUndoUseCase$Parameters$ModerateCommentParameters;", "(Lorg/wordpress/android/models/usecases/ModerateCommentWithUndoUseCase$Parameters$ModerateCommentParameters;)V", "getParameters", "()Lorg/wordpress/android/models/usecases/ModerateCommentWithUndoUseCase$Parameters$ModerateCommentParameters;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class OnModerateComment extends org.wordpress.android.models.usecases.ModerateCommentWithUndoUseCase.ModerateCommentsAction {
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.models.usecases.ModerateCommentWithUndoUseCase.Parameters.ModerateCommentParameters parameters = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.models.usecases.ModerateCommentWithUndoUseCase.ModerateCommentsAction.OnModerateComment copy(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.models.usecases.ModerateCommentWithUndoUseCase.Parameters.ModerateCommentParameters parameters) {
                return null;
            }
            
            @java.lang.Override()
            public boolean equals(@org.jetbrains.annotations.Nullable()
            java.lang.Object other) {
                return false;
            }
            
            @java.lang.Override()
            public int hashCode() {
                return 0;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public java.lang.String toString() {
                return null;
            }
            
            public OnModerateComment(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.models.usecases.ModerateCommentWithUndoUseCase.Parameters.ModerateCommentParameters parameters) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.models.usecases.ModerateCommentWithUndoUseCase.Parameters.ModerateCommentParameters component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.models.usecases.ModerateCommentWithUndoUseCase.Parameters.ModerateCommentParameters getParameters() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/models/usecases/ModerateCommentWithUndoUseCase$ModerateCommentsAction$OnPushComment;", "Lorg/wordpress/android/models/usecases/ModerateCommentWithUndoUseCase$ModerateCommentsAction;", "parameters", "Lorg/wordpress/android/models/usecases/ModerateCommentWithUndoUseCase$Parameters$ModerateWithFallbackParameters;", "(Lorg/wordpress/android/models/usecases/ModerateCommentWithUndoUseCase$Parameters$ModerateWithFallbackParameters;)V", "getParameters", "()Lorg/wordpress/android/models/usecases/ModerateCommentWithUndoUseCase$Parameters$ModerateWithFallbackParameters;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class OnPushComment extends org.wordpress.android.models.usecases.ModerateCommentWithUndoUseCase.ModerateCommentsAction {
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.models.usecases.ModerateCommentWithUndoUseCase.Parameters.ModerateWithFallbackParameters parameters = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.models.usecases.ModerateCommentWithUndoUseCase.ModerateCommentsAction.OnPushComment copy(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.models.usecases.ModerateCommentWithUndoUseCase.Parameters.ModerateWithFallbackParameters parameters) {
                return null;
            }
            
            @java.lang.Override()
            public boolean equals(@org.jetbrains.annotations.Nullable()
            java.lang.Object other) {
                return false;
            }
            
            @java.lang.Override()
            public int hashCode() {
                return 0;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public java.lang.String toString() {
                return null;
            }
            
            public OnPushComment(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.models.usecases.ModerateCommentWithUndoUseCase.Parameters.ModerateWithFallbackParameters parameters) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.models.usecases.ModerateCommentWithUndoUseCase.Parameters.ModerateWithFallbackParameters component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.models.usecases.ModerateCommentWithUndoUseCase.Parameters.ModerateWithFallbackParameters getParameters() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/models/usecases/ModerateCommentWithUndoUseCase$ModerateCommentsAction$OnUndoModerateComment;", "Lorg/wordpress/android/models/usecases/ModerateCommentWithUndoUseCase$ModerateCommentsAction;", "parameters", "Lorg/wordpress/android/models/usecases/ModerateCommentWithUndoUseCase$Parameters$ModerateWithFallbackParameters;", "(Lorg/wordpress/android/models/usecases/ModerateCommentWithUndoUseCase$Parameters$ModerateWithFallbackParameters;)V", "getParameters", "()Lorg/wordpress/android/models/usecases/ModerateCommentWithUndoUseCase$Parameters$ModerateWithFallbackParameters;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class OnUndoModerateComment extends org.wordpress.android.models.usecases.ModerateCommentWithUndoUseCase.ModerateCommentsAction {
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.models.usecases.ModerateCommentWithUndoUseCase.Parameters.ModerateWithFallbackParameters parameters = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.models.usecases.ModerateCommentWithUndoUseCase.ModerateCommentsAction.OnUndoModerateComment copy(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.models.usecases.ModerateCommentWithUndoUseCase.Parameters.ModerateWithFallbackParameters parameters) {
                return null;
            }
            
            @java.lang.Override()
            public boolean equals(@org.jetbrains.annotations.Nullable()
            java.lang.Object other) {
                return false;
            }
            
            @java.lang.Override()
            public int hashCode() {
                return 0;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public java.lang.String toString() {
                return null;
            }
            
            public OnUndoModerateComment(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.models.usecases.ModerateCommentWithUndoUseCase.Parameters.ModerateWithFallbackParameters parameters) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.models.usecases.ModerateCommentWithUndoUseCase.Parameters.ModerateWithFallbackParameters component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.models.usecases.ModerateCommentWithUndoUseCase.Parameters.ModerateWithFallbackParameters getParameters() {
                return null;
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0005"}, d2 = {"Lorg/wordpress/android/models/usecases/ModerateCommentWithUndoUseCase$Parameters;", "", "()V", "ModerateCommentParameters", "ModerateWithFallbackParameters", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class Parameters {
        
        private Parameters() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0010\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0011\u001a\u00020\u0007H\u00c6\u0003J\'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u00c6\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0016\u001a\u00020\u0017H\u00d6\u0001J\t\u0010\u0018\u001a\u00020\u0019H\u00d6\u0001R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e\u00a8\u0006\u001a"}, d2 = {"Lorg/wordpress/android/models/usecases/ModerateCommentWithUndoUseCase$Parameters$ModerateCommentParameters;", "", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "remoteCommentId", "", "newStatus", "Lorg/wordpress/android/fluxc/model/CommentStatus;", "(Lorg/wordpress/android/fluxc/model/SiteModel;JLorg/wordpress/android/fluxc/model/CommentStatus;)V", "getNewStatus", "()Lorg/wordpress/android/fluxc/model/CommentStatus;", "getRemoteCommentId", "()J", "getSite", "()Lorg/wordpress/android/fluxc/model/SiteModel;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class ModerateCommentParameters {
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.fluxc.model.SiteModel site = null;
            private final long remoteCommentId = 0L;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.fluxc.model.CommentStatus newStatus = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.models.usecases.ModerateCommentWithUndoUseCase.Parameters.ModerateCommentParameters copy(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.fluxc.model.SiteModel site, long remoteCommentId, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.fluxc.model.CommentStatus newStatus) {
                return null;
            }
            
            @java.lang.Override()
            public boolean equals(@org.jetbrains.annotations.Nullable()
            java.lang.Object other) {
                return false;
            }
            
            @java.lang.Override()
            public int hashCode() {
                return 0;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public java.lang.String toString() {
                return null;
            }
            
            public ModerateCommentParameters(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.fluxc.model.SiteModel site, long remoteCommentId, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.fluxc.model.CommentStatus newStatus) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.fluxc.model.SiteModel component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.fluxc.model.SiteModel getSite() {
                return null;
            }
            
            public final long component2() {
                return 0L;
            }
            
            public final long getRemoteCommentId() {
                return 0L;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.fluxc.model.CommentStatus component3() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.fluxc.model.CommentStatus getNewStatus() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0012\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0013\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\u0014\u001a\u00020\u0007H\u00c6\u0003J1\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u0007H\u00c6\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0019\u001a\u00020\u001aH\u00d6\u0001J\t\u0010\u001b\u001a\u00020\u001cH\u00d6\u0001R\u0011\u0010\b\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010\u00a8\u0006\u001d"}, d2 = {"Lorg/wordpress/android/models/usecases/ModerateCommentWithUndoUseCase$Parameters$ModerateWithFallbackParameters;", "", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "remoteCommentId", "", "newStatus", "Lorg/wordpress/android/fluxc/model/CommentStatus;", "fallbackStatus", "(Lorg/wordpress/android/fluxc/model/SiteModel;JLorg/wordpress/android/fluxc/model/CommentStatus;Lorg/wordpress/android/fluxc/model/CommentStatus;)V", "getFallbackStatus", "()Lorg/wordpress/android/fluxc/model/CommentStatus;", "getNewStatus", "getRemoteCommentId", "()J", "getSite", "()Lorg/wordpress/android/fluxc/model/SiteModel;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class ModerateWithFallbackParameters {
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.fluxc.model.SiteModel site = null;
            private final long remoteCommentId = 0L;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.fluxc.model.CommentStatus newStatus = null;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.fluxc.model.CommentStatus fallbackStatus = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.models.usecases.ModerateCommentWithUndoUseCase.Parameters.ModerateWithFallbackParameters copy(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.fluxc.model.SiteModel site, long remoteCommentId, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.fluxc.model.CommentStatus newStatus, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.fluxc.model.CommentStatus fallbackStatus) {
                return null;
            }
            
            @java.lang.Override()
            public boolean equals(@org.jetbrains.annotations.Nullable()
            java.lang.Object other) {
                return false;
            }
            
            @java.lang.Override()
            public int hashCode() {
                return 0;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public java.lang.String toString() {
                return null;
            }
            
            public ModerateWithFallbackParameters(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.fluxc.model.SiteModel site, long remoteCommentId, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.fluxc.model.CommentStatus newStatus, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.fluxc.model.CommentStatus fallbackStatus) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.fluxc.model.SiteModel component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.fluxc.model.SiteModel getSite() {
                return null;
            }
            
            public final long component2() {
                return 0L;
            }
            
            public final long getRemoteCommentId() {
                return 0L;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.fluxc.model.CommentStatus component3() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.fluxc.model.CommentStatus getNewStatus() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.fluxc.model.CommentStatus component4() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.fluxc.model.CommentStatus getFallbackStatus() {
                return null;
            }
        }
    }
}