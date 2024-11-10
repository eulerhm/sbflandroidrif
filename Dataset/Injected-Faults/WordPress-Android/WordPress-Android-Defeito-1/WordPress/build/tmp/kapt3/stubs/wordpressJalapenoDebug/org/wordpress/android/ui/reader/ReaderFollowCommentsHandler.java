package org.wordpress.android.ui.reader;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0019\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006JC\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\u0010\b\u0002\u0010 \u001a\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010!H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\"JA\u0010#\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010$\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\u000e\u0010 \u001a\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010!H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\"J)\u0010%\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010&\u001a\u00020\u001dH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\'J\"\u0010(\u001a\u00020\u00182\u0006\u0010)\u001a\u00020\t2\u0010\b\u0002\u0010 \u001a\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010!H\u0002R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\t0\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0012\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006*"}, d2 = {"Lorg/wordpress/android/ui/reader/ReaderFollowCommentsHandler;", "", "readerCommentsFollowUseCase", "Lorg/wordpress/android/ui/reader/usecases/ReaderCommentsFollowUseCase;", "bgDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Lorg/wordpress/android/ui/reader/usecases/ReaderCommentsFollowUseCase;Lkotlinx/coroutines/CoroutineDispatcher;)V", "_followStatusUpdate", "Landroidx/lifecycle/MediatorLiveData;", "Lorg/wordpress/android/ui/reader/usecases/ReaderCommentsFollowUseCase$FollowCommentsState;", "_pushNotificationsStatusUpdate", "Lorg/wordpress/android/ui/reader/usecases/ReaderCommentsFollowUseCase$FollowCommentsState$FollowStateChanged;", "_snackbarEvents", "Lorg/wordpress/android/viewmodel/Event;", "Lorg/wordpress/android/ui/pages/SnackbarMessageHolder;", "followStatusUpdate", "Landroidx/lifecycle/LiveData;", "getFollowStatusUpdate", "()Landroidx/lifecycle/LiveData;", "pushNotificationsStatusUpdate", "getPushNotificationsStatusUpdate", "snackbarEvents", "getSnackbarEvents", "handleEnableByPushNotificationsClicked", "", "blogId", "", "postId", "askEnable", "", "source", "Lorg/wordpress/android/ui/reader/comments/ThreadedCommentsActionSource;", "onSuccessSnackbarAction", "Lkotlin/Function0;", "(JJZLorg/wordpress/android/ui/reader/comments/ThreadedCommentsActionSource;Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "handleFollowCommentsClicked", "askSubscribe", "handleFollowCommentsStatusRequest", "isInit", "(JJZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "manageState", "state", "org.wordpress.android_wordpressJalapenoDebug"})
public final class ReaderFollowCommentsHandler {
    private final org.wordpress.android.ui.reader.usecases.ReaderCommentsFollowUseCase readerCommentsFollowUseCase = null;
    private final kotlinx.coroutines.CoroutineDispatcher bgDispatcher = null;
    private final androidx.lifecycle.MediatorLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.pages.SnackbarMessageHolder>> _snackbarEvents = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.pages.SnackbarMessageHolder>> snackbarEvents = null;
    private final androidx.lifecycle.MediatorLiveData<org.wordpress.android.ui.reader.usecases.ReaderCommentsFollowUseCase.FollowCommentsState> _followStatusUpdate = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.ui.reader.usecases.ReaderCommentsFollowUseCase.FollowCommentsState> followStatusUpdate = null;
    private final androidx.lifecycle.MediatorLiveData<org.wordpress.android.ui.reader.usecases.ReaderCommentsFollowUseCase.FollowCommentsState.FollowStateChanged> _pushNotificationsStatusUpdate = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.ui.reader.usecases.ReaderCommentsFollowUseCase.FollowCommentsState.FollowStateChanged> pushNotificationsStatusUpdate = null;
    
    @javax.inject.Inject()
    public ReaderFollowCommentsHandler(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.usecases.ReaderCommentsFollowUseCase readerCommentsFollowUseCase, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher bgDispatcher) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.pages.SnackbarMessageHolder>> getSnackbarEvents() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.ui.reader.usecases.ReaderCommentsFollowUseCase.FollowCommentsState> getFollowStatusUpdate() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.ui.reader.usecases.ReaderCommentsFollowUseCase.FollowCommentsState.FollowStateChanged> getPushNotificationsStatusUpdate() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object handleFollowCommentsClicked(long blogId, long postId, boolean askSubscribe, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.comments.ThreadedCommentsActionSource source, @org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function0<kotlin.Unit> onSuccessSnackbarAction, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object handleFollowCommentsStatusRequest(long blogId, long postId, boolean isInit, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object handleEnableByPushNotificationsClicked(long blogId, long postId, boolean askEnable, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.comments.ThreadedCommentsActionSource source, @org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function0<kotlin.Unit> onSuccessSnackbarAction, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    private final void manageState(org.wordpress.android.ui.reader.usecases.ReaderCommentsFollowUseCase.FollowCommentsState state, kotlin.jvm.functions.Function0<kotlin.Unit> onSuccessSnackbarAction) {
    }
}