package org.wordpress.android.ui.reader.viewmodels;

import java.lang.System;

@kotlin.Suppress(names = {"TooManyFunctions"})
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u008e\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001GB!\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0010\u0010)\u001a\u00020\'2\u0006\u0010*\u001a\u00020\u0013H\u0002J\u0016\u0010+\u001a\b\u0012\u0004\u0012\u00020\u001a0\u000e2\u0006\u0010,\u001a\u00020\u000bH\u0002J\b\u0010-\u001a\u00020.H\u0002J\b\u0010/\u001a\u00020.H\u0002J\u0010\u00100\u001a\u00020.2\u0006\u00101\u001a\u00020\u001aH\u0002J \u00102\u001a\n\u0012\u0004\u0012\u00020.\u0018\u0001032\u0006\u00104\u001a\u00020\u001a2\u0006\u00105\u001a\u00020\u001aH\u0002J\b\u00106\u001a\u00020.H\u0002J\u0010\u00107\u001a\u0002082\u0006\u0010*\u001a\u00020\u0013H\u0002J\u0018\u00109\u001a\u00020.2\u0006\u0010:\u001a\u00020\u001a2\b\b\u0002\u00104\u001a\u00020\u001aJ\b\u0010;\u001a\u00020.H\u0014J\u0010\u0010<\u001a\u00020.2\u0006\u0010=\u001a\u00020\u001aH\u0002J\u0006\u0010>\u001a\u00020.J\u0006\u0010?\u001a\u00020.J\u0006\u0010@\u001a\u00020.J\u0006\u0010A\u001a\u00020.J\u0016\u0010B\u001a\u00020.2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\u0015J\u000e\u0010C\u001a\u00020.2\u0006\u0010D\u001a\u00020EJ\u001e\u0010F\u001a\u00020.2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\u00152\u0006\u0010$\u001a\u00020%R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u000e0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0017X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0015X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\u000e0\u001d\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010 \u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0\u001d\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001fR\u001d\u0010\"\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u000e0\u001d\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001fR\u000e\u0010$\u001a\u00020%X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0017\u0010&\u001a\b\u0012\u0004\u0012\u00020\'0\u001d\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010\u001f\u00a8\u0006H"}, d2 = {"Lorg/wordpress/android/ui/reader/viewmodels/ConversationNotificationsViewModel;", "Lorg/wordpress/android/viewmodel/ScopedViewModel;", "followCommentsHandler", "Lorg/wordpress/android/ui/reader/ReaderFollowCommentsHandler;", "readerPostTableWrapper", "Lorg/wordpress/android/datasets/wrappers/ReaderPostTableWrapper;", "bgDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Lorg/wordpress/android/ui/reader/ReaderFollowCommentsHandler;Lorg/wordpress/android/datasets/wrappers/ReaderPostTableWrapper;Lkotlinx/coroutines/CoroutineDispatcher;)V", "_pushNotificationsStatusUpdate", "Landroidx/lifecycle/MediatorLiveData;", "Lorg/wordpress/android/ui/reader/usecases/ReaderCommentsFollowUseCase$FollowCommentsState$FollowStateChanged;", "_showBottomSheetEvent", "Landroidx/lifecycle/MutableLiveData;", "Lorg/wordpress/android/viewmodel/Event;", "Lorg/wordpress/android/ui/reader/viewmodels/ConversationNotificationsViewModel$ShowBottomSheetData;", "_snackbarEvents", "Lorg/wordpress/android/ui/pages/SnackbarMessageHolder;", "_updateFollowStatus", "Lorg/wordpress/android/ui/reader/usecases/ReaderCommentsFollowUseCase$FollowCommentsState;", "blogId", "", "followStatusGetJob", "Lkotlinx/coroutines/Job;", "followStatusSetJob", "isStarted", "", "postId", "pushNotificationsStatusUpdate", "Landroidx/lifecycle/LiveData;", "getPushNotificationsStatusUpdate", "()Landroidx/lifecycle/LiveData;", "showBottomSheetEvent", "getShowBottomSheetEvent", "snackbarEvents", "getSnackbarEvents", "source", "Lorg/wordpress/android/ui/reader/comments/ThreadedCommentsActionSource;", "updateFollowUiState", "Lorg/wordpress/android/ui/reader/FollowConversationUiState;", "getUpdateFollowUiState", "buildFollowCommentsUiState", "followCommentsState", "buildPushNotificationsUiState", "followStateChanged", "disablePushNotificationsFromSnackbarAction", "", "enablePushNotificationsFromSnackbarAction", "getFollowConversationStatus", "isInit", "getSnackbarAction", "Lkotlin/Function0;", "fromSnackbar", "askingEnable", "init", "mapToStateType", "Lorg/wordpress/android/ui/reader/FollowCommentsUiStateType;", "onChangePushNotificationsRequest", "enable", "onCleared", "onFollowConversationClicked", "askSubscribe", "onFollowTapped", "onManageNotificationsTapped", "onRefresh", "onUnfollowTapped", "onUpdatePost", "onUserNavigateFromComments", "flags", "Lorg/wordpress/android/ui/reader/FollowConversationStatusFlags;", "start", "ShowBottomSheetData", "org.wordpress.android_wordpressJalapenoDebug"})
public final class ConversationNotificationsViewModel extends org.wordpress.android.viewmodel.ScopedViewModel {
    private final org.wordpress.android.ui.reader.ReaderFollowCommentsHandler followCommentsHandler = null;
    private final org.wordpress.android.datasets.wrappers.ReaderPostTableWrapper readerPostTableWrapper = null;
    private final kotlinx.coroutines.CoroutineDispatcher bgDispatcher = null;
    private boolean isStarted = false;
    private kotlinx.coroutines.Job followStatusGetJob;
    private kotlinx.coroutines.Job followStatusSetJob;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.reader.viewmodels.ConversationNotificationsViewModel.ShowBottomSheetData>> _showBottomSheetEvent = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.reader.viewmodels.ConversationNotificationsViewModel.ShowBottomSheetData>> showBottomSheetEvent = null;
    private final androidx.lifecycle.MediatorLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.pages.SnackbarMessageHolder>> _snackbarEvents = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.pages.SnackbarMessageHolder>> snackbarEvents = null;
    private final androidx.lifecycle.MediatorLiveData<org.wordpress.android.ui.reader.usecases.ReaderCommentsFollowUseCase.FollowCommentsState> _updateFollowStatus = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.ui.reader.FollowConversationUiState> updateFollowUiState = null;
    private final androidx.lifecycle.MediatorLiveData<org.wordpress.android.ui.reader.usecases.ReaderCommentsFollowUseCase.FollowCommentsState.FollowStateChanged> _pushNotificationsStatusUpdate = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<java.lang.Boolean>> pushNotificationsStatusUpdate = null;
    private long blogId = 0L;
    private long postId = 0L;
    private org.wordpress.android.ui.reader.comments.ThreadedCommentsActionSource source = org.wordpress.android.ui.reader.comments.ThreadedCommentsActionSource.UNKNOWN;
    
    @javax.inject.Inject()
    public ConversationNotificationsViewModel(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.ReaderFollowCommentsHandler followCommentsHandler, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.datasets.wrappers.ReaderPostTableWrapper readerPostTableWrapper, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher bgDispatcher) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.reader.viewmodels.ConversationNotificationsViewModel.ShowBottomSheetData>> getShowBottomSheetEvent() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.pages.SnackbarMessageHolder>> getSnackbarEvents() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.ui.reader.FollowConversationUiState> getUpdateFollowUiState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<java.lang.Boolean>> getPushNotificationsStatusUpdate() {
        return null;
    }
    
    public final void start(long blogId, long postId, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.comments.ThreadedCommentsActionSource source) {
    }
    
    public final void onRefresh() {
    }
    
    public final void onFollowTapped() {
    }
    
    public final void onUnfollowTapped() {
    }
    
    public final void onChangePushNotificationsRequest(boolean enable, boolean fromSnackbar) {
    }
    
    public final void onManageNotificationsTapped() {
    }
    
    public final void onUpdatePost(long blogId, long postId) {
    }
    
    public final void onUserNavigateFromComments(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.FollowConversationStatusFlags flags) {
    }
    
    private final kotlin.jvm.functions.Function0<kotlin.Unit> getSnackbarAction(boolean fromSnackbar, boolean askingEnable) {
        return null;
    }
    
    private final void enablePushNotificationsFromSnackbarAction() {
    }
    
    private final void disablePushNotificationsFromSnackbarAction() {
    }
    
    private final void init() {
    }
    
    private final void onFollowConversationClicked(boolean askSubscribe) {
    }
    
    private final void getFollowConversationStatus(boolean isInit) {
    }
    
    private final org.wordpress.android.ui.reader.FollowCommentsUiStateType mapToStateType(org.wordpress.android.ui.reader.usecases.ReaderCommentsFollowUseCase.FollowCommentsState followCommentsState) {
        return null;
    }
    
    private final org.wordpress.android.ui.reader.FollowConversationUiState buildFollowCommentsUiState(org.wordpress.android.ui.reader.usecases.ReaderCommentsFollowUseCase.FollowCommentsState followCommentsState) {
        return null;
    }
    
    private final org.wordpress.android.viewmodel.Event<java.lang.Boolean> buildPushNotificationsUiState(org.wordpress.android.ui.reader.usecases.ReaderCommentsFollowUseCase.FollowCommentsState.FollowStateChanged followStateChanged) {
        return null;
    }
    
    @java.lang.Override()
    protected void onCleared() {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0005J\t\u0010\b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\t\u001a\u00020\u0003H\u00c6\u0003J\u001d\u0010\n\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\u000b\u001a\u00020\u00032\b\u0010\f\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/reader/viewmodels/ConversationNotificationsViewModel$ShowBottomSheetData;", "", "show", "", "isReceivingNotifications", "(ZZ)V", "()Z", "getShow", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class ShowBottomSheetData {
        private final boolean show = false;
        private final boolean isReceivingNotifications = false;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.reader.viewmodels.ConversationNotificationsViewModel.ShowBottomSheetData copy(boolean show, boolean isReceivingNotifications) {
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
        
        public ShowBottomSheetData(boolean show, boolean isReceivingNotifications) {
            super();
        }
        
        public final boolean component1() {
            return false;
        }
        
        public final boolean getShow() {
            return false;
        }
        
        public final boolean component2() {
            return false;
        }
        
        public final boolean isReceivingNotifications() {
            return false;
        }
    }
}