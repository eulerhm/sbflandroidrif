package org.wordpress.android.ui.comments.unified;

import java.lang.System;

@kotlin.Suppress(names = {"TooManyFunctions"})
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u00bc\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 P2\u00020\u0001:\u0007PQRSTUVB[\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\u0006\u0010\u0013\u001a\u00020\u0014\u00a2\u0006\u0002\u0010\u0015J\b\u0010,\u001a\u00020-H\u0002J\u0011\u0010.\u001a\u00020/H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u00100J\u0010\u00101\u001a\u0002022\u0006\u00103\u001a\u00020/H\u0002J\u0006\u00104\u001a\u00020-J\u0006\u00105\u001a\u00020-J\u0006\u00106\u001a\u00020-J\u0016\u00107\u001a\u00020-2\u0006\u00108\u001a\u0002092\u0006\u0010:\u001a\u00020;J\u0019\u0010<\u001a\u00020-2\u0006\u0010=\u001a\u00020>H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010?J\u0011\u0010@\u001a\u00020-H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u00100J\u0011\u0010A\u001a\u00020-H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u00100J\u0016\u0010B\u001a\u00020-2\u0006\u0010&\u001a\u00020\'2\u0006\u0010\u001e\u001a\u00020\u001fJ\u0019\u0010C\u001a\u00020-2\u0006\u0010D\u001a\u00020/H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010EJ!\u0010F\u001a\u00020!2\u0006\u0010G\u001a\u00020H2\u0006\u0010D\u001a\u00020/H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010IJ\u0011\u0010J\u001a\u00020-H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u00100J\u0019\u0010K\u001a\u00020-2\u0006\u00103\u001a\u00020/H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010EJ\f\u0010L\u001a\u00020!*\u00020MH\u0002J\f\u0010N\u001a\u00020O*\u00020\u001fH\u0002R\u001a\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u00180\u0017X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0\u00180\u0017X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001d0\u0017X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020!X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\"\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u00180#\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\'X\u0082.\u00a2\u0006\u0002\n\u0000R\u001d\u0010(\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0\u00180#\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010%R\u0017\u0010*\u001a\b\u0012\u0004\u0012\u00020\u001d0#\u00a2\u0006\b\n\u0000\u001a\u0004\b+\u0010%\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006W"}, d2 = {"Lorg/wordpress/android/ui/comments/unified/UnifiedCommentsEditViewModel;", "Lorg/wordpress/android/viewmodel/ScopedViewModel;", "mainDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "bgDispatcher", "commentsStore", "Lorg/wordpress/android/fluxc/store/CommentsStore;", "resourceProvider", "Lorg/wordpress/android/viewmodel/ResourceProvider;", "networkUtilsWrapper", "Lorg/wordpress/android/util/NetworkUtilsWrapper;", "localCommentCacheUpdateHandler", "Lorg/wordpress/android/models/usecases/LocalCommentCacheUpdateHandler;", "getCommentUseCase", "Lorg/wordpress/android/ui/comments/unified/usecase/GetCommentUseCase;", "notificationActionsWrapper", "Lorg/wordpress/android/ui/notifications/utils/NotificationsActionsWrapper;", "readerCommentTableWrapper", "Lorg/wordpress/android/datasets/wrappers/ReaderCommentTableWrapper;", "analyticsUtilsWrapper", "Lorg/wordpress/android/util/analytics/AnalyticsUtilsWrapper;", "(Lkotlinx/coroutines/CoroutineDispatcher;Lkotlinx/coroutines/CoroutineDispatcher;Lorg/wordpress/android/fluxc/store/CommentsStore;Lorg/wordpress/android/viewmodel/ResourceProvider;Lorg/wordpress/android/util/NetworkUtilsWrapper;Lorg/wordpress/android/models/usecases/LocalCommentCacheUpdateHandler;Lorg/wordpress/android/ui/comments/unified/usecase/GetCommentUseCase;Lorg/wordpress/android/ui/notifications/utils/NotificationsActionsWrapper;Lorg/wordpress/android/datasets/wrappers/ReaderCommentTableWrapper;Lorg/wordpress/android/util/analytics/AnalyticsUtilsWrapper;)V", "_onSnackbarMessage", "Landroidx/lifecycle/MutableLiveData;", "Lorg/wordpress/android/viewmodel/Event;", "Lorg/wordpress/android/ui/pages/SnackbarMessageHolder;", "_uiActionEvent", "Lorg/wordpress/android/ui/comments/unified/UnifiedCommentsEditViewModel$EditCommentActionEvent;", "_uiState", "Lorg/wordpress/android/ui/comments/unified/UnifiedCommentsEditViewModel$EditCommentUiState;", "commentIdentifier", "Lorg/wordpress/android/ui/comments/unified/CommentIdentifier;", "isStarted", "", "onSnackbarMessage", "Landroidx/lifecycle/LiveData;", "getOnSnackbarMessage", "()Landroidx/lifecycle/LiveData;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "uiActionEvent", "getUiActionEvent", "uiState", "getUiState", "initViews", "", "mapCommentEssentials", "Lorg/wordpress/android/ui/comments/unified/CommentEssentials;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "mapInputSettings", "Lorg/wordpress/android/ui/comments/unified/UnifiedCommentsEditViewModel$InputSettings;", "commentEssentials", "onActionMenuClicked", "onBackPressed", "onConfirmEditingDiscard", "onValidateField", "field", "", "fieldType", "Lorg/wordpress/android/ui/comments/unified/UnifiedCommentsEditViewModel$FieldType;", "setLoadingState", "state", "Lorg/wordpress/android/ui/comments/unified/UnifiedCommentsEditViewModel$ProgressState;", "(Lorg/wordpress/android/ui/comments/unified/UnifiedCommentsEditViewModel$ProgressState;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "showUpdateCommentError", "showUpdateNotificationError", "start", "updateComment", "editedCommentEssentials", "(Lorg/wordpress/android/ui/comments/unified/CommentEssentials;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateCommentEntity", "comment", "Lorg/wordpress/android/fluxc/persistence/comments/CommentsDao$CommentEntity;", "(Lorg/wordpress/android/fluxc/persistence/comments/CommentsDao$CommentEntity;Lorg/wordpress/android/ui/comments/unified/CommentEssentials;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateNotificationEntity", "updateReaderEntity", "hasError", "Lorg/wordpress/android/ui/comments/unified/UnifiedCommentsEditViewModel$EditErrorStrings;", "toCommentActionSource", "Lorg/wordpress/android/util/analytics/AnalyticsUtils$AnalyticsCommentActionSource;", "Companion", "EditCommentActionEvent", "EditCommentUiState", "EditErrorStrings", "FieldType", "InputSettings", "ProgressState", "org.wordpress.android_wordpressJalapenoDebug"})
public final class UnifiedCommentsEditViewModel extends org.wordpress.android.viewmodel.ScopedViewModel {
    private final kotlinx.coroutines.CoroutineDispatcher mainDispatcher = null;
    private final kotlinx.coroutines.CoroutineDispatcher bgDispatcher = null;
    private final org.wordpress.android.fluxc.store.CommentsStore commentsStore = null;
    private final org.wordpress.android.viewmodel.ResourceProvider resourceProvider = null;
    private final org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper = null;
    private final org.wordpress.android.models.usecases.LocalCommentCacheUpdateHandler localCommentCacheUpdateHandler = null;
    private final org.wordpress.android.ui.comments.unified.usecase.GetCommentUseCase getCommentUseCase = null;
    private final org.wordpress.android.ui.notifications.utils.NotificationsActionsWrapper notificationActionsWrapper = null;
    private final org.wordpress.android.datasets.wrappers.ReaderCommentTableWrapper readerCommentTableWrapper = null;
    private final org.wordpress.android.util.analytics.AnalyticsUtilsWrapper analyticsUtilsWrapper = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.ui.comments.unified.UnifiedCommentsEditViewModel.EditCommentUiState> _uiState = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.comments.unified.UnifiedCommentsEditViewModel.EditCommentActionEvent>> _uiActionEvent = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.pages.SnackbarMessageHolder>> _onSnackbarMessage = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.ui.comments.unified.UnifiedCommentsEditViewModel.EditCommentUiState> uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.comments.unified.UnifiedCommentsEditViewModel.EditCommentActionEvent>> uiActionEvent = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.pages.SnackbarMessageHolder>> onSnackbarMessage = null;
    private boolean isStarted = false;
    private org.wordpress.android.fluxc.model.SiteModel site;
    private org.wordpress.android.ui.comments.unified.CommentIdentifier commentIdentifier;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.comments.unified.UnifiedCommentsEditViewModel.Companion Companion = null;
    private static final long LOADING_DELAY_MS = 300L;
    
    @javax.inject.Inject()
    public UnifiedCommentsEditViewModel(@org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "UI_THREAD")
    kotlinx.coroutines.CoroutineDispatcher mainDispatcher, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher bgDispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.CommentsStore commentsStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.ResourceProvider resourceProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.models.usecases.LocalCommentCacheUpdateHandler localCommentCacheUpdateHandler, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.comments.unified.usecase.GetCommentUseCase getCommentUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.notifications.utils.NotificationsActionsWrapper notificationActionsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.datasets.wrappers.ReaderCommentTableWrapper readerCommentTableWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.AnalyticsUtilsWrapper analyticsUtilsWrapper) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.ui.comments.unified.UnifiedCommentsEditViewModel.EditCommentUiState> getUiState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.comments.unified.UnifiedCommentsEditViewModel.EditCommentActionEvent>> getUiActionEvent() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.pages.SnackbarMessageHolder>> getOnSnackbarMessage() {
        return null;
    }
    
    public final void start(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.comments.unified.CommentIdentifier commentIdentifier) {
    }
    
    private final java.lang.Object setLoadingState(org.wordpress.android.ui.comments.unified.UnifiedCommentsEditViewModel.ProgressState state, kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    public final void onActionMenuClicked() {
    }
    
    public final void onBackPressed() {
    }
    
    public final void onConfirmEditingDiscard() {
    }
    
    private final void initViews() {
    }
    
    private final java.lang.Object mapCommentEssentials(kotlin.coroutines.Continuation<? super org.wordpress.android.ui.comments.unified.CommentEssentials> continuation) {
        return null;
    }
    
    private final java.lang.Object updateComment(org.wordpress.android.ui.comments.unified.CommentEssentials editedCommentEssentials, kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    private final java.lang.Object updateCommentEntity(org.wordpress.android.fluxc.persistence.comments.CommentsDao.CommentEntity comment, org.wordpress.android.ui.comments.unified.CommentEssentials editedCommentEssentials, kotlin.coroutines.Continuation<? super java.lang.Boolean> continuation) {
        return null;
    }
    
    private final java.lang.Object updateNotificationEntity(kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    private final java.lang.Object updateReaderEntity(org.wordpress.android.ui.comments.unified.CommentEssentials commentEssentials, kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    private final java.lang.Object showUpdateCommentError(kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    private final java.lang.Object showUpdateNotificationError(kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    public final void onValidateField(@org.jetbrains.annotations.NotNull()
    java.lang.String field, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.comments.unified.UnifiedCommentsEditViewModel.FieldType fieldType) {
    }
    
    private final org.wordpress.android.ui.comments.unified.UnifiedCommentsEditViewModel.InputSettings mapInputSettings(org.wordpress.android.ui.comments.unified.CommentEssentials commentEssentials) {
        return null;
    }
    
    private final boolean hasError(org.wordpress.android.ui.comments.unified.UnifiedCommentsEditViewModel.EditErrorStrings $this$hasError) {
        return false;
    }
    
    private final org.wordpress.android.util.analytics.AnalyticsUtils.AnalyticsCommentActionSource toCommentActionSource(org.wordpress.android.ui.comments.unified.CommentIdentifier $this$toCommentActionSource) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B5\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u0007J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J9\u0010\u0011\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0015\u001a\u00020\u0016H\u00d6\u0001J\t\u0010\u0017\u001a\u00020\u0003H\u00d6\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t\u00a8\u0006\u0018"}, d2 = {"Lorg/wordpress/android/ui/comments/unified/UnifiedCommentsEditViewModel$EditErrorStrings;", "", "userNameError", "", "commentTextError", "userUrlError", "userEmailError", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getCommentTextError", "()Ljava/lang/String;", "getUserEmailError", "getUserNameError", "getUserUrlError", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class EditErrorStrings {
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String userNameError = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String commentTextError = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String userUrlError = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String userEmailError = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.comments.unified.UnifiedCommentsEditViewModel.EditErrorStrings copy(@org.jetbrains.annotations.Nullable()
        java.lang.String userNameError, @org.jetbrains.annotations.Nullable()
        java.lang.String commentTextError, @org.jetbrains.annotations.Nullable()
        java.lang.String userUrlError, @org.jetbrains.annotations.Nullable()
        java.lang.String userEmailError) {
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
        
        public EditErrorStrings() {
            super();
        }
        
        public EditErrorStrings(@org.jetbrains.annotations.Nullable()
        java.lang.String userNameError, @org.jetbrains.annotations.Nullable()
        java.lang.String commentTextError, @org.jetbrains.annotations.Nullable()
        java.lang.String userUrlError, @org.jetbrains.annotations.Nullable()
        java.lang.String userEmailError) {
            super();
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getUserNameError() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component2() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getCommentTextError() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component3() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getUserUrlError() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component4() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getUserEmailError() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BS\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u00a2\u0006\u0002\u0010\u0010J\t\u0010\u001f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010 \u001a\u00020\u0003H\u00c6\u0003J\t\u0010!\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\"\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010#\u001a\u0004\u0018\u00010\bH\u00c6\u0003J\t\u0010$\u001a\u00020\nH\u00c6\u0003J\t\u0010%\u001a\u00020\nH\u00c6\u0003J\t\u0010&\u001a\u00020\rH\u00c6\u0003J\t\u0010\'\u001a\u00020\u000fH\u00c6\u0003Je\u0010(\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000fH\u00c6\u0001J\u0013\u0010)\u001a\u00020\u00032\b\u0010*\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010+\u001a\u00020,H\u00d6\u0001J\t\u0010-\u001a\u00020.H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\f\u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u000b\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u000e\u001a\u00020\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0016R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0012R\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0012R\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0012\u00a8\u0006/"}, d2 = {"Lorg/wordpress/android/ui/comments/unified/UnifiedCommentsEditViewModel$EditCommentUiState;", "", "canSaveChanges", "", "shouldInitComment", "shouldInitWatchers", "showProgress", "progressText", "Lorg/wordpress/android/ui/utils/UiString;", "originalComment", "Lorg/wordpress/android/ui/comments/unified/CommentEssentials;", "editedComment", "editErrorStrings", "Lorg/wordpress/android/ui/comments/unified/UnifiedCommentsEditViewModel$EditErrorStrings;", "inputSettings", "Lorg/wordpress/android/ui/comments/unified/UnifiedCommentsEditViewModel$InputSettings;", "(ZZZZLorg/wordpress/android/ui/utils/UiString;Lorg/wordpress/android/ui/comments/unified/CommentEssentials;Lorg/wordpress/android/ui/comments/unified/CommentEssentials;Lorg/wordpress/android/ui/comments/unified/UnifiedCommentsEditViewModel$EditErrorStrings;Lorg/wordpress/android/ui/comments/unified/UnifiedCommentsEditViewModel$InputSettings;)V", "getCanSaveChanges", "()Z", "getEditErrorStrings", "()Lorg/wordpress/android/ui/comments/unified/UnifiedCommentsEditViewModel$EditErrorStrings;", "getEditedComment", "()Lorg/wordpress/android/ui/comments/unified/CommentEssentials;", "getInputSettings", "()Lorg/wordpress/android/ui/comments/unified/UnifiedCommentsEditViewModel$InputSettings;", "getOriginalComment", "getProgressText", "()Lorg/wordpress/android/ui/utils/UiString;", "getShouldInitComment", "getShouldInitWatchers", "getShowProgress", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class EditCommentUiState {
        private final boolean canSaveChanges = false;
        private final boolean shouldInitComment = false;
        private final boolean shouldInitWatchers = false;
        private final boolean showProgress = false;
        @org.jetbrains.annotations.Nullable()
        private final org.wordpress.android.ui.utils.UiString progressText = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.comments.unified.CommentEssentials originalComment = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.comments.unified.CommentEssentials editedComment = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.comments.unified.UnifiedCommentsEditViewModel.EditErrorStrings editErrorStrings = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.comments.unified.UnifiedCommentsEditViewModel.InputSettings inputSettings = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.comments.unified.UnifiedCommentsEditViewModel.EditCommentUiState copy(boolean canSaveChanges, boolean shouldInitComment, boolean shouldInitWatchers, boolean showProgress, @org.jetbrains.annotations.Nullable()
        org.wordpress.android.ui.utils.UiString progressText, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.comments.unified.CommentEssentials originalComment, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.comments.unified.CommentEssentials editedComment, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.comments.unified.UnifiedCommentsEditViewModel.EditErrorStrings editErrorStrings, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.comments.unified.UnifiedCommentsEditViewModel.InputSettings inputSettings) {
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
        
        public EditCommentUiState(boolean canSaveChanges, boolean shouldInitComment, boolean shouldInitWatchers, boolean showProgress, @org.jetbrains.annotations.Nullable()
        org.wordpress.android.ui.utils.UiString progressText, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.comments.unified.CommentEssentials originalComment, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.comments.unified.CommentEssentials editedComment, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.comments.unified.UnifiedCommentsEditViewModel.EditErrorStrings editErrorStrings, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.comments.unified.UnifiedCommentsEditViewModel.InputSettings inputSettings) {
            super();
        }
        
        public final boolean component1() {
            return false;
        }
        
        public final boolean getCanSaveChanges() {
            return false;
        }
        
        public final boolean component2() {
            return false;
        }
        
        public final boolean getShouldInitComment() {
            return false;
        }
        
        public final boolean component3() {
            return false;
        }
        
        public final boolean getShouldInitWatchers() {
            return false;
        }
        
        public final boolean component4() {
            return false;
        }
        
        public final boolean getShowProgress() {
            return false;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final org.wordpress.android.ui.utils.UiString component5() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final org.wordpress.android.ui.utils.UiString getProgressText() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.comments.unified.CommentEssentials component6() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.comments.unified.CommentEssentials getOriginalComment() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.comments.unified.CommentEssentials component7() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.comments.unified.CommentEssentials getEditedComment() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.comments.unified.UnifiedCommentsEditViewModel.EditErrorStrings component8() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.comments.unified.UnifiedCommentsEditViewModel.EditErrorStrings getEditErrorStrings() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.comments.unified.UnifiedCommentsEditViewModel.InputSettings component9() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.comments.unified.UnifiedCommentsEditViewModel.InputSettings getInputSettings() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0011\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0010\u001a\u00020\u0003H\u00c6\u0003J1\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\u0012\u001a\u00020\u00032\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0014\u001a\u00020\u0015H\u00d6\u0001J\t\u0010\u0016\u001a\u00020\u0017H\u00d6\u0001R\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t\u00a8\u0006\u0018"}, d2 = {"Lorg/wordpress/android/ui/comments/unified/UnifiedCommentsEditViewModel$InputSettings;", "", "enableEditName", "", "enableEditUrl", "enableEditEmail", "enableEditComment", "(ZZZZ)V", "getEnableEditComment", "()Z", "getEnableEditEmail", "getEnableEditName", "getEnableEditUrl", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class InputSettings {
        private final boolean enableEditName = false;
        private final boolean enableEditUrl = false;
        private final boolean enableEditEmail = false;
        private final boolean enableEditComment = false;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.comments.unified.UnifiedCommentsEditViewModel.InputSettings copy(boolean enableEditName, boolean enableEditUrl, boolean enableEditEmail, boolean enableEditComment) {
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
        
        public InputSettings(boolean enableEditName, boolean enableEditUrl, boolean enableEditEmail, boolean enableEditComment) {
            super();
        }
        
        public final boolean component1() {
            return false;
        }
        
        public final boolean getEnableEditName() {
            return false;
        }
        
        public final boolean component2() {
            return false;
        }
        
        public final boolean getEnableEditUrl() {
            return false;
        }
        
        public final boolean component3() {
            return false;
        }
        
        public final boolean getEnableEditEmail() {
            return false;
        }
        
        public final boolean component4() {
            return false;
        }
        
        public final boolean getEnableEditComment() {
            return false;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0019\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\u0006R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\r\u00a8\u0006\u000e"}, d2 = {"Lorg/wordpress/android/ui/comments/unified/UnifiedCommentsEditViewModel$ProgressState;", "", "show", "", "progressText", "Lorg/wordpress/android/ui/utils/UiString;", "(Ljava/lang/String;IZLorg/wordpress/android/ui/utils/UiString;)V", "getProgressText", "()Lorg/wordpress/android/ui/utils/UiString;", "getShow", "()Z", "NOT_VISIBLE", "LOADING", "SAVING", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum ProgressState {
        /*public static final*/ NOT_VISIBLE /* = new NOT_VISIBLE(false, null) */,
        /*public static final*/ LOADING /* = new LOADING(false, null) */,
        /*public static final*/ SAVING /* = new SAVING(false, null) */;
        private final boolean show = false;
        @org.jetbrains.annotations.Nullable()
        private final org.wordpress.android.ui.utils.UiString progressText = null;
        
        ProgressState(boolean show, org.wordpress.android.ui.utils.UiString progressText) {
        }
        
        public final boolean getShow() {
            return false;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final org.wordpress.android.ui.utils.UiString getProgressText() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u000b\n\u0002\b\f\b\u0086\u0001\u0018\u0000 \u00122\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0012B%\b\u0002\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005\u00a2\u0006\u0002\u0010\bJ\u000e\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u0000R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u000bj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011\u00a8\u0006\u0013"}, d2 = {"Lorg/wordpress/android/ui/comments/unified/UnifiedCommentsEditViewModel$FieldType;", "", "errorStringRes", "", "isValid", "Lkotlin/Function1;", "", "", "(Ljava/lang/String;IILkotlin/jvm/functions/Function1;)V", "getErrorStringRes", "()I", "()Lkotlin/jvm/functions/Function1;", "matches", "expectedField", "USER_NAME", "USER_EMAIL", "WEB_ADDRESS", "COMMENT", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum FieldType {
        /*public static final*/ USER_NAME /* = new USER_NAME(0, null) */,
        /*public static final*/ USER_EMAIL /* = new USER_EMAIL(0, null) */,
        /*public static final*/ WEB_ADDRESS /* = new WEB_ADDRESS(0, null) */,
        /*public static final*/ COMMENT /* = new COMMENT(0, null) */;
        private final int errorStringRes = 0;
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function1<java.lang.String, java.lang.Boolean> isValid = null;
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.ui.comments.unified.UnifiedCommentsEditViewModel.FieldType.Companion Companion = null;
        
        FieldType(@androidx.annotation.StringRes()
        int errorStringRes, kotlin.jvm.functions.Function1<? super java.lang.String, java.lang.Boolean> isValid) {
        }
        
        public final int getErrorStringRes() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function1<java.lang.String, java.lang.Boolean> isValid() {
            return null;
        }
        
        public final boolean matches(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.comments.unified.UnifiedCommentsEditViewModel.FieldType expectedField) {
            return false;
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0006H\u0002J\u0010\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u0006H\u0002J\u0010\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u0006H\u0002\u00a8\u0006\r"}, d2 = {"Lorg/wordpress/android/ui/comments/unified/UnifiedCommentsEditViewModel$FieldType$Companion;", "", "()V", "isValidComment", "", "comment", "", "isValidUserEmail", "email", "isValidUserName", "userName", "isValidWebAddress", "url", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Companion {
            
            private Companion() {
                super();
            }
            
            private final boolean isValidUserName(java.lang.String userName) {
                return false;
            }
            
            private final boolean isValidUserEmail(java.lang.String email) {
                return false;
            }
            
            private final boolean isValidWebAddress(java.lang.String url) {
                return false;
            }
            
            private final boolean isValidComment(java.lang.String comment) {
                return false;
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005\u00a8\u0006\u0006"}, d2 = {"Lorg/wordpress/android/ui/comments/unified/UnifiedCommentsEditViewModel$EditCommentActionEvent;", "", "(Ljava/lang/String;I)V", "CLOSE", "DONE", "CANCEL_EDIT_CONFIRM", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum EditCommentActionEvent {
        /*public static final*/ CLOSE /* = new CLOSE() */,
        /*public static final*/ DONE /* = new DONE() */,
        /*public static final*/ CANCEL_EDIT_CONFIRM /* = new CANCEL_EDIT_CONFIRM() */;
        
        EditCommentActionEvent() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lorg/wordpress/android/ui/comments/unified/UnifiedCommentsEditViewModel$Companion;", "", "()V", "LOADING_DELAY_MS", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}