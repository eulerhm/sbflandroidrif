package org.wordpress.android.ui.engagement;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u0000  2\u00020\u0001:\u0001 B\u0019\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0006\u0010\u0013\u001a\u00020\u0014J+\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u001bH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001cJ+\u0010\u001d\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u001bH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001cJ\u0010\u0010\u001e\u001a\u00020\u00142\u0006\u0010\u001f\u001a\u00020\tH\u0002R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\t0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001d\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006!"}, d2 = {"Lorg/wordpress/android/ui/engagement/GetLikesHandler;", "", "getLikesUseCase", "Lorg/wordpress/android/ui/engagement/GetLikesUseCase;", "bgDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Lorg/wordpress/android/ui/engagement/GetLikesUseCase;Lkotlinx/coroutines/CoroutineDispatcher;)V", "_likesStatusUpdate", "Landroidx/lifecycle/MediatorLiveData;", "Lorg/wordpress/android/ui/engagement/GetLikesUseCase$GetLikesState;", "_snackbarEvents", "Lorg/wordpress/android/viewmodel/Event;", "Lorg/wordpress/android/ui/pages/SnackbarMessageHolder;", "likesStatusUpdate", "Landroidx/lifecycle/LiveData;", "getLikesStatusUpdate", "()Landroidx/lifecycle/LiveData;", "snackbarEvents", "getSnackbarEvents", "clear", "", "handleGetLikesForComment", "fingerPrint", "Lorg/wordpress/android/ui/engagement/GetLikesUseCase$LikeGroupFingerPrint;", "requestNextPage", "", "pageLength", "", "(Lorg/wordpress/android/ui/engagement/GetLikesUseCase$LikeGroupFingerPrint;ZILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "handleGetLikesForPost", "manageState", "state", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public final class GetLikesHandler {
    private final org.wordpress.android.ui.engagement.GetLikesUseCase getLikesUseCase = null;
    private final kotlinx.coroutines.CoroutineDispatcher bgDispatcher = null;
    private final androidx.lifecycle.MediatorLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.pages.SnackbarMessageHolder>> _snackbarEvents = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.pages.SnackbarMessageHolder>> snackbarEvents = null;
    private final androidx.lifecycle.MediatorLiveData<org.wordpress.android.ui.engagement.GetLikesUseCase.GetLikesState> _likesStatusUpdate = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.ui.engagement.GetLikesUseCase.GetLikesState> likesStatusUpdate = null;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.engagement.GetLikesHandler.Companion Companion = null;
    private static final int LIKES_PER_PAGE_DEFAULT = 20;
    
    @javax.inject.Inject()
    public GetLikesHandler(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.engagement.GetLikesUseCase getLikesUseCase, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher bgDispatcher) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.pages.SnackbarMessageHolder>> getSnackbarEvents() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.ui.engagement.GetLikesUseCase.GetLikesState> getLikesStatusUpdate() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object handleGetLikesForPost(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.engagement.GetLikesUseCase.LikeGroupFingerPrint fingerPrint, boolean requestNextPage, int pageLength, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object handleGetLikesForComment(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.engagement.GetLikesUseCase.LikeGroupFingerPrint fingerPrint, boolean requestNextPage, int pageLength, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    public final void clear() {
    }
    
    private final void manageState(org.wordpress.android.ui.engagement.GetLikesUseCase.GetLikesState state) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lorg/wordpress/android/ui/engagement/GetLikesHandler$Companion;", "", "()V", "LIKES_PER_PAGE_DEFAULT", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}