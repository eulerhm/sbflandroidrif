package org.wordpress.android.models.usecases;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002 \u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0001:\u0003\t\n\u000bB\u000f\b\u0007\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u00a2\u0006\u0002\u0010\b\u00a8\u0006\f"}, d2 = {"Lorg/wordpress/android/models/usecases/BatchModerateCommentsUseCase;", "Lorg/wordpress/android/usecase/FlowFSMUseCase;", "Lorg/wordpress/android/models/usecases/ModerateCommentsResourceProvider;", "Lorg/wordpress/android/models/usecases/BatchModerateCommentsUseCase$ModerateCommentsAction;", "Lorg/wordpress/android/fluxc/store/CommentsStore$CommentsData$DoNotCare;", "Lorg/wordpress/android/models/usecases/CommentsUseCaseType;", "Lorg/wordpress/android/fluxc/store/CommentStore$CommentError;", "moderateCommentsResourceProvider", "(Lorg/wordpress/android/models/usecases/ModerateCommentsResourceProvider;)V", "ModerateCommentsAction", "ModerateCommentsState", "Parameters", "org.wordpress.android_wordpressJalapenoDebug"})
public final class BatchModerateCommentsUseCase extends org.wordpress.android.usecase.FlowFSMUseCase<org.wordpress.android.models.usecases.ModerateCommentsResourceProvider, org.wordpress.android.models.usecases.BatchModerateCommentsUseCase.ModerateCommentsAction, org.wordpress.android.fluxc.store.CommentsStore.CommentsData.DoNotCare, org.wordpress.android.models.usecases.CommentsUseCaseType, org.wordpress.android.fluxc.store.CommentStore.CommentError> {
    
    @javax.inject.Inject()
    public BatchModerateCommentsUseCase(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.models.usecases.ModerateCommentsResourceProvider moderateCommentsResourceProvider) {
        super(null, null);
    }
    
    @kotlin.Suppress(names = {"LongMethod"})
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002 \u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0001:\u0001\bB\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0007\u0082\u0001\u0001\t\u00a8\u0006\n"}, d2 = {"Lorg/wordpress/android/models/usecases/BatchModerateCommentsUseCase$ModerateCommentsState;", "Lorg/wordpress/android/usecase/FlowFSMUseCase$StateInterface;", "Lorg/wordpress/android/models/usecases/ModerateCommentsResourceProvider;", "Lorg/wordpress/android/models/usecases/BatchModerateCommentsUseCase$ModerateCommentsAction;", "Lorg/wordpress/android/fluxc/store/CommentsStore$CommentsData$DoNotCare;", "Lorg/wordpress/android/models/usecases/CommentsUseCaseType;", "Lorg/wordpress/android/fluxc/store/CommentStore$CommentError;", "()V", "Idle", "Lorg/wordpress/android/models/usecases/BatchModerateCommentsUseCase$ModerateCommentsState$Idle;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class ModerateCommentsState implements org.wordpress.android.usecase.FlowFSMUseCase.StateInterface<org.wordpress.android.models.usecases.ModerateCommentsResourceProvider, org.wordpress.android.models.usecases.BatchModerateCommentsUseCase.ModerateCommentsAction, org.wordpress.android.fluxc.store.CommentsStore.CommentsData.DoNotCare, org.wordpress.android.models.usecases.CommentsUseCaseType, org.wordpress.android.fluxc.store.CommentStore.CommentError> {
        
        private ModerateCommentsState() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J_\u0010\u0003\u001a \u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u00042\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u00062\u001e\u0010\f\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00070\u000e0\rH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000f\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0010"}, d2 = {"Lorg/wordpress/android/models/usecases/BatchModerateCommentsUseCase$ModerateCommentsState$Idle;", "Lorg/wordpress/android/models/usecases/BatchModerateCommentsUseCase$ModerateCommentsState;", "()V", "runAction", "Lorg/wordpress/android/usecase/FlowFSMUseCase$StateInterface;", "Lorg/wordpress/android/models/usecases/ModerateCommentsResourceProvider;", "Lorg/wordpress/android/models/usecases/BatchModerateCommentsUseCase$ModerateCommentsAction;", "Lorg/wordpress/android/fluxc/store/CommentsStore$CommentsData$DoNotCare;", "Lorg/wordpress/android/models/usecases/CommentsUseCaseType;", "Lorg/wordpress/android/fluxc/store/CommentStore$CommentError;", "resourceProvider", "action", "flowChannel", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "Lorg/wordpress/android/usecase/UseCaseResult;", "(Lorg/wordpress/android/models/usecases/ModerateCommentsResourceProvider;Lorg/wordpress/android/models/usecases/BatchModerateCommentsUseCase$ModerateCommentsAction;Lkotlinx/coroutines/flow/MutableSharedFlow;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Idle extends org.wordpress.android.models.usecases.BatchModerateCommentsUseCase.ModerateCommentsState {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.models.usecases.BatchModerateCommentsUseCase.ModerateCommentsState.Idle INSTANCE = null;
            
            private Idle() {
                super();
            }
            
            @org.jetbrains.annotations.Nullable()
            @java.lang.Override()
            public java.lang.Object runAction(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.models.usecases.ModerateCommentsResourceProvider resourceProvider, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.models.usecases.BatchModerateCommentsUseCase.ModerateCommentsAction action, @org.jetbrains.annotations.NotNull()
            kotlinx.coroutines.flow.MutableSharedFlow<org.wordpress.android.usecase.UseCaseResult<org.wordpress.android.models.usecases.CommentsUseCaseType, org.wordpress.android.fluxc.store.CommentStore.CommentError, org.wordpress.android.fluxc.store.CommentsStore.CommentsData.DoNotCare>> flowChannel, @org.jetbrains.annotations.NotNull()
            kotlin.coroutines.Continuation<? super org.wordpress.android.usecase.FlowFSMUseCase.StateInterface<org.wordpress.android.models.usecases.ModerateCommentsResourceProvider, org.wordpress.android.models.usecases.BatchModerateCommentsUseCase.ModerateCommentsAction, org.wordpress.android.fluxc.store.CommentsStore.CommentsData.DoNotCare, org.wordpress.android.models.usecases.CommentsUseCaseType, org.wordpress.android.fluxc.store.CommentStore.CommentError>> continuation) {
                return null;
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0001\u0003B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0001\u0004\u00a8\u0006\u0005"}, d2 = {"Lorg/wordpress/android/models/usecases/BatchModerateCommentsUseCase$ModerateCommentsAction;", "", "()V", "OnModerateComments", "Lorg/wordpress/android/models/usecases/BatchModerateCommentsUseCase$ModerateCommentsAction$OnModerateComments;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class ModerateCommentsAction {
        
        private ModerateCommentsAction() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/models/usecases/BatchModerateCommentsUseCase$ModerateCommentsAction$OnModerateComments;", "Lorg/wordpress/android/models/usecases/BatchModerateCommentsUseCase$ModerateCommentsAction;", "parameters", "Lorg/wordpress/android/models/usecases/BatchModerateCommentsUseCase$Parameters$ModerateCommentsParameters;", "(Lorg/wordpress/android/models/usecases/BatchModerateCommentsUseCase$Parameters$ModerateCommentsParameters;)V", "getParameters", "()Lorg/wordpress/android/models/usecases/BatchModerateCommentsUseCase$Parameters$ModerateCommentsParameters;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class OnModerateComments extends org.wordpress.android.models.usecases.BatchModerateCommentsUseCase.ModerateCommentsAction {
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.models.usecases.BatchModerateCommentsUseCase.Parameters.ModerateCommentsParameters parameters = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.models.usecases.BatchModerateCommentsUseCase.ModerateCommentsAction.OnModerateComments copy(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.models.usecases.BatchModerateCommentsUseCase.Parameters.ModerateCommentsParameters parameters) {
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
            
            public OnModerateComments(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.models.usecases.BatchModerateCommentsUseCase.Parameters.ModerateCommentsParameters parameters) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.models.usecases.BatchModerateCommentsUseCase.Parameters.ModerateCommentsParameters component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.models.usecases.BatchModerateCommentsUseCase.Parameters.ModerateCommentsParameters getParameters() {
                return null;
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b6\u0018\u00002\u00020\u0001:\u0001\u0003B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0004"}, d2 = {"Lorg/wordpress/android/models/usecases/BatchModerateCommentsUseCase$Parameters;", "", "()V", "ModerateCommentsParameters", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class Parameters {
        
        private Parameters() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0003J\t\u0010\u0012\u001a\u00020\bH\u00c6\u0003J-\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\b\b\u0002\u0010\u0007\u001a\u00020\bH\u00c6\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0017\u001a\u00020\u0018H\u00d6\u0001J\t\u0010\u0019\u001a\u00020\u001aH\u00d6\u0001R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006\u001b"}, d2 = {"Lorg/wordpress/android/models/usecases/BatchModerateCommentsUseCase$Parameters$ModerateCommentsParameters;", "", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "remoteCommentIds", "", "", "newStatus", "Lorg/wordpress/android/fluxc/model/CommentStatus;", "(Lorg/wordpress/android/fluxc/model/SiteModel;Ljava/util/List;Lorg/wordpress/android/fluxc/model/CommentStatus;)V", "getNewStatus", "()Lorg/wordpress/android/fluxc/model/CommentStatus;", "getRemoteCommentIds", "()Ljava/util/List;", "getSite", "()Lorg/wordpress/android/fluxc/model/SiteModel;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class ModerateCommentsParameters {
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.fluxc.model.SiteModel site = null;
            @org.jetbrains.annotations.NotNull()
            private final java.util.List<java.lang.Long> remoteCommentIds = null;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.fluxc.model.CommentStatus newStatus = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.models.usecases.BatchModerateCommentsUseCase.Parameters.ModerateCommentsParameters copy(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
            java.util.List<java.lang.Long> remoteCommentIds, @org.jetbrains.annotations.NotNull()
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
            
            public ModerateCommentsParameters(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
            java.util.List<java.lang.Long> remoteCommentIds, @org.jetbrains.annotations.NotNull()
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
            
            @org.jetbrains.annotations.NotNull()
            public final java.util.List<java.lang.Long> component2() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.util.List<java.lang.Long> getRemoteCommentIds() {
                return null;
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
    }
}