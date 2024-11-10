package org.wordpress.android.ui.comments.unified;

import java.lang.System;

@kotlin.Suppress(names = {"TooManyFunctions"})
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u00ba\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\t\u0018\u0000 P2\u00020\u0001:\u0002PQBK\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\b\u0001\u0010\n\u001a\u00020\u000b\u0012\b\b\u0001\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u00a2\u0006\u0002\u0010\u0011J\u0006\u00107\u001a\u000208J\u0010\u00109\u001a\u0002082\u0006\u0010:\u001a\u00020;H\u0002J\b\u0010<\u001a\u000208H\u0002J\u0006\u0010=\u001a\u000208J\b\u0010>\u001a\u000208H\u0002J\u0010\u0010?\u001a\u0002082\u0006\u0010@\u001a\u00020AH\u0002J\u0006\u0010B\u001a\u000208J\u000e\u0010C\u001a\u0002082\u0006\u0010@\u001a\u00020AJ\u0016\u0010D\u001a\u0002082\u0006\u0010E\u001a\u00020\'2\u0006\u0010@\u001a\u00020AJ\u0006\u0010F\u001a\u000208J\u0010\u0010G\u001a\u0002082\u0006\u0010H\u001a\u00020IH\u0002J\b\u0010J\u001a\u000208H\u0002J\u000e\u0010K\u001a\u0002082\u0006\u0010L\u001a\u00020$J\u0018\u0010M\u001a\u0002082\u0006\u0010N\u001a\u00020\'2\u0006\u0010O\u001a\u00020AH\u0002R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000R&\u0010\u0015\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u001a0\u00170\u0016X\u0082\u0004\u00a2\u0006\u0002\n\u0000R&\u0010\u001b\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u001a0\u00170\u0016X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001dX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020 0\u001dX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010!\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001e0\"0\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020$X\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010%\u001a\b\u0012\u0004\u0012\u00020\'0&X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020)X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010*\u001a\b\u0012\u0004\u0012\u00020\u001e0+\u00a2\u0006\b\n\u0000\u001a\u0004\b,\u0010-R\u0017\u0010.\u001a\b\u0012\u0004\u0012\u00020 0+\u00a2\u0006\b\n\u0000\u001a\u0004\b/\u0010-R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R!\u00100\u001a\b\u0012\u0004\u0012\u000202018FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b5\u00106\u001a\u0004\b3\u00104R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006R"}, d2 = {"Lorg/wordpress/android/ui/comments/unified/UnifiedCommentListViewModel;", "Lorg/wordpress/android/viewmodel/ScopedViewModel;", "commentListUiModelHelper", "Lorg/wordpress/android/ui/comments/unified/CommentListUiModelHelper;", "selectedSiteRepository", "Lorg/wordpress/android/ui/mysite/SelectedSiteRepository;", "networkUtilsWrapper", "Lorg/wordpress/android/util/NetworkUtilsWrapper;", "analyticsTrackerWrapper", "Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;", "bgDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "mainDispatcher", "unifiedCommentsListHandler", "Lorg/wordpress/android/models/usecases/UnifiedCommentsListHandler;", "localCommentCacheUpdateHandler", "Lorg/wordpress/android/models/usecases/LocalCommentCacheUpdateHandler;", "(Lorg/wordpress/android/ui/comments/unified/CommentListUiModelHelper;Lorg/wordpress/android/ui/mysite/SelectedSiteRepository;Lorg/wordpress/android/util/NetworkUtilsWrapper;Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;Lkotlinx/coroutines/CoroutineDispatcher;Lkotlinx/coroutines/CoroutineDispatcher;Lorg/wordpress/android/models/usecases/UnifiedCommentsListHandler;Lorg/wordpress/android/models/usecases/LocalCommentCacheUpdateHandler;)V", "_batchModerationStatus", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lorg/wordpress/android/ui/comments/unified/CommentListUiModelHelper$BatchModerationStatus;", "_commentsProvider", "Lkotlinx/coroutines/flow/Flow;", "Lorg/wordpress/android/usecase/UseCaseResult;", "Lorg/wordpress/android/models/usecases/CommentsUseCaseType;", "Lorg/wordpress/android/fluxc/store/CommentStore$CommentError;", "", "_commentsUpdateListener", "_onCommentDetailsRequested", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "Lorg/wordpress/android/ui/comments/unified/UnifiedCommentListViewModel$SelectedComment;", "_onSnackbarMessage", "Lorg/wordpress/android/ui/pages/SnackbarMessageHolder;", "_selectedComments", "", "commentFilter", "Lorg/wordpress/android/ui/comments/unified/CommentFilter;", "commentInModeration", "Ljava/util/ArrayList;", "", "isStarted", "", "onCommentDetailsRequested", "Lkotlinx/coroutines/flow/SharedFlow;", "getOnCommentDetailsRequested", "()Lkotlinx/coroutines/flow/SharedFlow;", "onSnackbarMessage", "getOnSnackbarMessage", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "Lorg/wordpress/android/ui/comments/unified/CommentListUiModelHelper$CommentsUiModel;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "uiState$delegate", "Lkotlin/Lazy;", "clearActionModeSelection", "", "clickItem", "comment", "Lorg/wordpress/android/fluxc/persistence/comments/CommentsDao$CommentEntity;", "listenToLocalCacheUpdateRequests", "listenToModerateWithUndoSnackbarRequets", "listenToSnackBarRequests", "moderateSelectedComments", "newStatus", "Lorg/wordpress/android/fluxc/model/CommentStatus;", "onBatchModerationConfirmationCanceled", "performBatchModeration", "performSingleCommentModeration", "commentId", "reload", "requestNextPage", "offset", "", "requestsFirstPage", "start", "commentListFilter", "toggleItem", "remoteCommentId", "commentStatus", "Companion", "SelectedComment", "org.wordpress.android_wordpressJalapenoDebug"})
public final class UnifiedCommentListViewModel extends org.wordpress.android.viewmodel.ScopedViewModel {
    private final org.wordpress.android.ui.comments.unified.CommentListUiModelHelper commentListUiModelHelper = null;
    private final org.wordpress.android.ui.mysite.SelectedSiteRepository selectedSiteRepository = null;
    private final org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper = null;
    private final org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTrackerWrapper = null;
    private final kotlinx.coroutines.CoroutineDispatcher bgDispatcher = null;
    private final kotlinx.coroutines.CoroutineDispatcher mainDispatcher = null;
    private final org.wordpress.android.models.usecases.UnifiedCommentsListHandler unifiedCommentsListHandler = null;
    private boolean isStarted = false;
    private org.wordpress.android.ui.comments.unified.CommentFilter commentFilter;
    private final kotlinx.coroutines.flow.Flow<org.wordpress.android.usecase.UseCaseResult<org.wordpress.android.models.usecases.CommentsUseCaseType, org.wordpress.android.fluxc.store.CommentStore.CommentError, java.lang.Object>> _commentsUpdateListener = null;
    private final kotlinx.coroutines.flow.MutableSharedFlow<org.wordpress.android.ui.pages.SnackbarMessageHolder> _onSnackbarMessage = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<org.wordpress.android.ui.comments.unified.UnifiedCommentListViewModel.SelectedComment>> _selectedComments = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<org.wordpress.android.ui.comments.unified.CommentListUiModelHelper.BatchModerationStatus> _batchModerationStatus = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.SharedFlow<org.wordpress.android.ui.pages.SnackbarMessageHolder> onSnackbarMessage = null;
    private java.util.ArrayList<java.lang.Long> commentInModeration;
    private final kotlinx.coroutines.flow.MutableSharedFlow<org.wordpress.android.ui.comments.unified.UnifiedCommentListViewModel.SelectedComment> _onCommentDetailsRequested = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.SharedFlow<org.wordpress.android.ui.comments.unified.UnifiedCommentListViewModel.SelectedComment> onCommentDetailsRequested = null;
    private final kotlinx.coroutines.flow.Flow<org.wordpress.android.usecase.UseCaseResult<org.wordpress.android.models.usecases.CommentsUseCaseType, org.wordpress.android.fluxc.store.CommentStore.CommentError, java.lang.Object>> _commentsProvider = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy uiState$delegate = null;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.comments.unified.UnifiedCommentListViewModel.Companion Companion = null;
    private static final long UI_STATE_FLOW_TIMEOUT_MS = 5000L;
    private static final int COMMENT_PAGE_SIZE = 30;
    private static final int UNREPLIED_COMMENT_PAGE_SIZE = 100;
    
    @javax.inject.Inject()
    public UnifiedCommentListViewModel(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.comments.unified.CommentListUiModelHelper commentListUiModelHelper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.SelectedSiteRepository selectedSiteRepository, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTrackerWrapper, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher bgDispatcher, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "UI_THREAD")
    kotlinx.coroutines.CoroutineDispatcher mainDispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.models.usecases.UnifiedCommentsListHandler unifiedCommentsListHandler, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.models.usecases.LocalCommentCacheUpdateHandler localCommentCacheUpdateHandler) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.SharedFlow<org.wordpress.android.ui.pages.SnackbarMessageHolder> getOnSnackbarMessage() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.SharedFlow<org.wordpress.android.ui.comments.unified.UnifiedCommentListViewModel.SelectedComment> getOnCommentDetailsRequested() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<org.wordpress.android.ui.comments.unified.CommentListUiModelHelper.CommentsUiModel> getUiState() {
        return null;
    }
    
    public final void start(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.comments.unified.CommentFilter commentListFilter) {
    }
    
    private final void requestsFirstPage() {
    }
    
    private final void requestNextPage(int offset) {
    }
    
    private final void listenToLocalCacheUpdateRequests() {
    }
    
    private final void listenToSnackBarRequests() {
    }
    
    public final void listenToModerateWithUndoSnackbarRequets() {
    }
    
    public final void reload() {
    }
    
    private final void toggleItem(long remoteCommentId, org.wordpress.android.fluxc.model.CommentStatus commentStatus) {
    }
    
    private final void clickItem(org.wordpress.android.fluxc.persistence.comments.CommentsDao.CommentEntity comment) {
    }
    
    public final void clearActionModeSelection() {
    }
    
    public final void performSingleCommentModeration(long commentId, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.CommentStatus newStatus) {
    }
    
    public final void onBatchModerationConfirmationCanceled() {
    }
    
    public final void performBatchModeration(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.CommentStatus newStatus) {
    }
    
    private final void moderateSelectedComments(org.wordpress.android.fluxc.model.CommentStatus newStatus) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\f\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0011\u001a\u00020\u0012H\u00d6\u0001J\t\u0010\u0013\u001a\u00020\u0014H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0015"}, d2 = {"Lorg/wordpress/android/ui/comments/unified/UnifiedCommentListViewModel$SelectedComment;", "", "remoteCommentId", "", "status", "Lorg/wordpress/android/fluxc/model/CommentStatus;", "(JLorg/wordpress/android/fluxc/model/CommentStatus;)V", "getRemoteCommentId", "()J", "getStatus", "()Lorg/wordpress/android/fluxc/model/CommentStatus;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class SelectedComment {
        private final long remoteCommentId = 0L;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.fluxc.model.CommentStatus status = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.comments.unified.UnifiedCommentListViewModel.SelectedComment copy(long remoteCommentId, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.CommentStatus status) {
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
        
        public SelectedComment(long remoteCommentId, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.CommentStatus status) {
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
        public final org.wordpress.android.fluxc.model.CommentStatus getStatus() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lorg/wordpress/android/ui/comments/unified/UnifiedCommentListViewModel$Companion;", "", "()V", "COMMENT_PAGE_SIZE", "", "UI_STATE_FLOW_TIMEOUT_MS", "", "UNREPLIED_COMMENT_PAGE_SIZE", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}