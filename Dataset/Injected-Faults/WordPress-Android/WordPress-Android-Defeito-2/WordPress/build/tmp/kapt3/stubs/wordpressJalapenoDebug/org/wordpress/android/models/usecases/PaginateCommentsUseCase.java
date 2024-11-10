package org.wordpress.android.models.usecases;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 \t2 \u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0001:\u0004\t\n\u000b\fB\u000f\b\u0007\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u00a2\u0006\u0002\u0010\b\u00a8\u0006\r"}, d2 = {"Lorg/wordpress/android/models/usecases/PaginateCommentsUseCase;", "Lorg/wordpress/android/usecase/FlowFSMUseCase;", "Lorg/wordpress/android/models/usecases/PaginateCommentsResourceProvider;", "Lorg/wordpress/android/models/usecases/PaginateCommentsUseCase$PaginateCommentsAction;", "Lorg/wordpress/android/fluxc/store/CommentsStore$CommentsData$PagingData;", "Lorg/wordpress/android/models/usecases/CommentsUseCaseType;", "Lorg/wordpress/android/fluxc/store/CommentStore$CommentError;", "paginateCommentsResourceProvider", "(Lorg/wordpress/android/models/usecases/PaginateCommentsResourceProvider;)V", "Companion", "PaginateCommentsAction", "PaginateCommentsState", "Parameters", "org.wordpress.android_wordpressJalapenoDebug"})
public final class PaginateCommentsUseCase extends org.wordpress.android.usecase.FlowFSMUseCase<org.wordpress.android.models.usecases.PaginateCommentsResourceProvider, org.wordpress.android.models.usecases.PaginateCommentsUseCase.PaginateCommentsAction, org.wordpress.android.fluxc.store.CommentsStore.CommentsData.PagingData, org.wordpress.android.models.usecases.CommentsUseCaseType, org.wordpress.android.fluxc.store.CommentStore.CommentError> {
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.models.usecases.PaginateCommentsUseCase.Companion Companion = null;
    public static final long LOADING_STATE_DELAY = 500L;
    
    @javax.inject.Inject()
    public PaginateCommentsUseCase(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.models.usecases.PaginateCommentsResourceProvider paginateCommentsResourceProvider) {
        super(null, null);
    }
    
    @kotlin.Suppress(names = {"LongMethod"})
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002 \u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0001:\u0001\bB\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0007\u0082\u0001\u0001\t\u00a8\u0006\n"}, d2 = {"Lorg/wordpress/android/models/usecases/PaginateCommentsUseCase$PaginateCommentsState;", "Lorg/wordpress/android/usecase/FlowFSMUseCase$StateInterface;", "Lorg/wordpress/android/models/usecases/PaginateCommentsResourceProvider;", "Lorg/wordpress/android/models/usecases/PaginateCommentsUseCase$PaginateCommentsAction;", "Lorg/wordpress/android/fluxc/store/CommentsStore$CommentsData$PagingData;", "Lorg/wordpress/android/models/usecases/CommentsUseCaseType;", "Lorg/wordpress/android/fluxc/store/CommentStore$CommentError;", "()V", "Idle", "Lorg/wordpress/android/models/usecases/PaginateCommentsUseCase$PaginateCommentsState$Idle;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class PaginateCommentsState implements org.wordpress.android.usecase.FlowFSMUseCase.StateInterface<org.wordpress.android.models.usecases.PaginateCommentsResourceProvider, org.wordpress.android.models.usecases.PaginateCommentsUseCase.PaginateCommentsAction, org.wordpress.android.fluxc.store.CommentsStore.CommentsData.PagingData, org.wordpress.android.models.usecases.CommentsUseCaseType, org.wordpress.android.fluxc.store.CommentStore.CommentError> {
        
        private PaginateCommentsState() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J_\u0010\u0003\u001a \u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u00042\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u00062\u001e\u0010\f\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00070\u000e0\rH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000f\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0010"}, d2 = {"Lorg/wordpress/android/models/usecases/PaginateCommentsUseCase$PaginateCommentsState$Idle;", "Lorg/wordpress/android/models/usecases/PaginateCommentsUseCase$PaginateCommentsState;", "()V", "runAction", "Lorg/wordpress/android/usecase/FlowFSMUseCase$StateInterface;", "Lorg/wordpress/android/models/usecases/PaginateCommentsResourceProvider;", "Lorg/wordpress/android/models/usecases/PaginateCommentsUseCase$PaginateCommentsAction;", "Lorg/wordpress/android/fluxc/store/CommentsStore$CommentsData$PagingData;", "Lorg/wordpress/android/models/usecases/CommentsUseCaseType;", "Lorg/wordpress/android/fluxc/store/CommentStore$CommentError;", "utilsProvider", "action", "flowChannel", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "Lorg/wordpress/android/usecase/UseCaseResult;", "(Lorg/wordpress/android/models/usecases/PaginateCommentsResourceProvider;Lorg/wordpress/android/models/usecases/PaginateCommentsUseCase$PaginateCommentsAction;Lkotlinx/coroutines/flow/MutableSharedFlow;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Idle extends org.wordpress.android.models.usecases.PaginateCommentsUseCase.PaginateCommentsState {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.models.usecases.PaginateCommentsUseCase.PaginateCommentsState.Idle INSTANCE = null;
            
            private Idle() {
                super();
            }
            
            @org.jetbrains.annotations.Nullable()
            @java.lang.Override()
            public java.lang.Object runAction(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.models.usecases.PaginateCommentsResourceProvider utilsProvider, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.models.usecases.PaginateCommentsUseCase.PaginateCommentsAction action, @org.jetbrains.annotations.NotNull()
            kotlinx.coroutines.flow.MutableSharedFlow<org.wordpress.android.usecase.UseCaseResult<org.wordpress.android.models.usecases.CommentsUseCaseType, org.wordpress.android.fluxc.store.CommentStore.CommentError, org.wordpress.android.fluxc.store.CommentsStore.CommentsData.PagingData>> flowChannel, @org.jetbrains.annotations.NotNull()
            kotlin.coroutines.Continuation<? super org.wordpress.android.usecase.FlowFSMUseCase.StateInterface<org.wordpress.android.models.usecases.PaginateCommentsResourceProvider, org.wordpress.android.models.usecases.PaginateCommentsUseCase.PaginateCommentsAction, org.wordpress.android.fluxc.store.CommentsStore.CommentsData.PagingData, org.wordpress.android.models.usecases.CommentsUseCaseType, org.wordpress.android.fluxc.store.CommentStore.CommentError>> continuation) {
                return null;
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/models/usecases/PaginateCommentsUseCase$PaginateCommentsAction;", "", "()V", "OnGetPage", "OnReloadFromCache", "Lorg/wordpress/android/models/usecases/PaginateCommentsUseCase$PaginateCommentsAction$OnGetPage;", "Lorg/wordpress/android/models/usecases/PaginateCommentsUseCase$PaginateCommentsAction$OnReloadFromCache;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class PaginateCommentsAction {
        
        private PaginateCommentsAction() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/models/usecases/PaginateCommentsUseCase$PaginateCommentsAction$OnGetPage;", "Lorg/wordpress/android/models/usecases/PaginateCommentsUseCase$PaginateCommentsAction;", "parameters", "Lorg/wordpress/android/models/usecases/PaginateCommentsUseCase$Parameters$GetPageParameters;", "(Lorg/wordpress/android/models/usecases/PaginateCommentsUseCase$Parameters$GetPageParameters;)V", "getParameters", "()Lorg/wordpress/android/models/usecases/PaginateCommentsUseCase$Parameters$GetPageParameters;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class OnGetPage extends org.wordpress.android.models.usecases.PaginateCommentsUseCase.PaginateCommentsAction {
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.models.usecases.PaginateCommentsUseCase.Parameters.GetPageParameters parameters = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.models.usecases.PaginateCommentsUseCase.PaginateCommentsAction.OnGetPage copy(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.models.usecases.PaginateCommentsUseCase.Parameters.GetPageParameters parameters) {
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
            
            public OnGetPage(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.models.usecases.PaginateCommentsUseCase.Parameters.GetPageParameters parameters) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.models.usecases.PaginateCommentsUseCase.Parameters.GetPageParameters component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.models.usecases.PaginateCommentsUseCase.Parameters.GetPageParameters getParameters() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/models/usecases/PaginateCommentsUseCase$PaginateCommentsAction$OnReloadFromCache;", "Lorg/wordpress/android/models/usecases/PaginateCommentsUseCase$PaginateCommentsAction;", "parameters", "Lorg/wordpress/android/models/usecases/PaginateCommentsUseCase$Parameters$ReloadFromCacheParameters;", "(Lorg/wordpress/android/models/usecases/PaginateCommentsUseCase$Parameters$ReloadFromCacheParameters;)V", "getParameters", "()Lorg/wordpress/android/models/usecases/PaginateCommentsUseCase$Parameters$ReloadFromCacheParameters;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class OnReloadFromCache extends org.wordpress.android.models.usecases.PaginateCommentsUseCase.PaginateCommentsAction {
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.models.usecases.PaginateCommentsUseCase.Parameters.ReloadFromCacheParameters parameters = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.models.usecases.PaginateCommentsUseCase.PaginateCommentsAction.OnReloadFromCache copy(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.models.usecases.PaginateCommentsUseCase.Parameters.ReloadFromCacheParameters parameters) {
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
            
            public OnReloadFromCache(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.models.usecases.PaginateCommentsUseCase.Parameters.ReloadFromCacheParameters parameters) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.models.usecases.PaginateCommentsUseCase.Parameters.ReloadFromCacheParameters component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.models.usecases.PaginateCommentsUseCase.Parameters.ReloadFromCacheParameters getParameters() {
                return null;
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/models/usecases/PaginateCommentsUseCase$Parameters;", "", "()V", "GetPageParameters", "ReloadFromCacheParameters", "Lorg/wordpress/android/models/usecases/PaginateCommentsUseCase$Parameters$GetPageParameters;", "Lorg/wordpress/android/models/usecases/PaginateCommentsUseCase$Parameters$ReloadFromCacheParameters;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class Parameters {
        
        private Parameters() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0012\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0013\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0014\u001a\u00020\bH\u00c6\u0003J1\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\bH\u00c6\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u00d6\u0003J\t\u0010\u001a\u001a\u00020\u0005H\u00d6\u0001J\t\u0010\u001b\u001a\u00020\u001cH\u00d6\u0001R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010\u00a8\u0006\u001d"}, d2 = {"Lorg/wordpress/android/models/usecases/PaginateCommentsUseCase$Parameters$GetPageParameters;", "Lorg/wordpress/android/models/usecases/PaginateCommentsUseCase$Parameters;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "number", "", "offset", "commentFilter", "Lorg/wordpress/android/ui/comments/unified/CommentFilter;", "(Lorg/wordpress/android/fluxc/model/SiteModel;IILorg/wordpress/android/ui/comments/unified/CommentFilter;)V", "getCommentFilter", "()Lorg/wordpress/android/ui/comments/unified/CommentFilter;", "getNumber", "()I", "getOffset", "getSite", "()Lorg/wordpress/android/fluxc/model/SiteModel;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class GetPageParameters extends org.wordpress.android.models.usecases.PaginateCommentsUseCase.Parameters {
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.fluxc.model.SiteModel site = null;
            private final int number = 0;
            private final int offset = 0;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.comments.unified.CommentFilter commentFilter = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.models.usecases.PaginateCommentsUseCase.Parameters.GetPageParameters copy(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.fluxc.model.SiteModel site, int number, int offset, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.comments.unified.CommentFilter commentFilter) {
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
            
            public GetPageParameters(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.fluxc.model.SiteModel site, int number, int offset, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.comments.unified.CommentFilter commentFilter) {
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
            
            public final int component2() {
                return 0;
            }
            
            public final int getNumber() {
                return 0;
            }
            
            public final int component3() {
                return 0;
            }
            
            public final int getOffset() {
                return 0;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.comments.unified.CommentFilter component4() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.comments.unified.CommentFilter getCommentFilter() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\f\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u000e\u001a\u00020\u00052\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u00d6\u0003J\t\u0010\u0011\u001a\u00020\u0012H\u00d6\u0001J\t\u0010\u0013\u001a\u00020\u0014H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0015"}, d2 = {"Lorg/wordpress/android/models/usecases/PaginateCommentsUseCase$Parameters$ReloadFromCacheParameters;", "Lorg/wordpress/android/models/usecases/PaginateCommentsUseCase$Parameters;", "pagingParameters", "Lorg/wordpress/android/models/usecases/PaginateCommentsUseCase$Parameters$GetPageParameters;", "hasMore", "", "(Lorg/wordpress/android/models/usecases/PaginateCommentsUseCase$Parameters$GetPageParameters;Z)V", "getHasMore", "()Z", "getPagingParameters", "()Lorg/wordpress/android/models/usecases/PaginateCommentsUseCase$Parameters$GetPageParameters;", "component1", "component2", "copy", "equals", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class ReloadFromCacheParameters extends org.wordpress.android.models.usecases.PaginateCommentsUseCase.Parameters {
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.models.usecases.PaginateCommentsUseCase.Parameters.GetPageParameters pagingParameters = null;
            private final boolean hasMore = false;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.models.usecases.PaginateCommentsUseCase.Parameters.ReloadFromCacheParameters copy(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.models.usecases.PaginateCommentsUseCase.Parameters.GetPageParameters pagingParameters, boolean hasMore) {
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
            
            public ReloadFromCacheParameters(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.models.usecases.PaginateCommentsUseCase.Parameters.GetPageParameters pagingParameters, boolean hasMore) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.models.usecases.PaginateCommentsUseCase.Parameters.GetPageParameters component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.models.usecases.PaginateCommentsUseCase.Parameters.GetPageParameters getPagingParameters() {
                return null;
            }
            
            public final boolean component2() {
                return false;
            }
            
            public final boolean getHasMore() {
                return false;
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lorg/wordpress/android/models/usecases/PaginateCommentsUseCase$Companion;", "", "()V", "LOADING_STATE_DELAY", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}