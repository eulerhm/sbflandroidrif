package org.wordpress.android.ui.people;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0019\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0019\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0017J\u0019\u0010\u0018\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0017J!\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u001bH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001cJ\u0010\u0010\u001d\u001a\u00020\u00142\u0006\u0010\u001e\u001a\u00020\tH\u0002R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\t0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u001f"}, d2 = {"Lorg/wordpress/android/ui/people/InviteLinksHandler;", "", "inviteLinksUseCase", "Lorg/wordpress/android/ui/people/InviteLinksUseCase;", "bgDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Lorg/wordpress/android/ui/people/InviteLinksUseCase;Lkotlinx/coroutines/CoroutineDispatcher;)V", "_inviteLinksState", "Landroidx/lifecycle/MediatorLiveData;", "Lorg/wordpress/android/ui/people/InviteLinksUseCase$InviteLinksState;", "_snackbarEvents", "Lorg/wordpress/android/viewmodel/Event;", "Lorg/wordpress/android/ui/pages/SnackbarMessageHolder;", "inviteLinksState", "Landroidx/lifecycle/LiveData;", "getInviteLinksState", "()Landroidx/lifecycle/LiveData;", "snackbarEvents", "getSnackbarEvents", "handleDisableLinks", "", "blogId", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "handleGenerateLinks", "handleInviteLinksStatusRequest", "scenarioContext", "Lorg/wordpress/android/ui/people/InviteLinksUseCase$UseCaseScenarioContext;", "(JLorg/wordpress/android/ui/people/InviteLinksUseCase$UseCaseScenarioContext;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "manageState", "state", "org.wordpress.android_wordpressJalapenoDebug"})
public final class InviteLinksHandler {
    private final org.wordpress.android.ui.people.InviteLinksUseCase inviteLinksUseCase = null;
    private final kotlinx.coroutines.CoroutineDispatcher bgDispatcher = null;
    private final androidx.lifecycle.MediatorLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.pages.SnackbarMessageHolder>> _snackbarEvents = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.pages.SnackbarMessageHolder>> snackbarEvents = null;
    private final androidx.lifecycle.MediatorLiveData<org.wordpress.android.ui.people.InviteLinksUseCase.InviteLinksState> _inviteLinksState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.ui.people.InviteLinksUseCase.InviteLinksState> inviteLinksState = null;
    
    @javax.inject.Inject()
    public InviteLinksHandler(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.people.InviteLinksUseCase inviteLinksUseCase, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher bgDispatcher) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.pages.SnackbarMessageHolder>> getSnackbarEvents() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.ui.people.InviteLinksUseCase.InviteLinksState> getInviteLinksState() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object handleInviteLinksStatusRequest(long blogId, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.people.InviteLinksUseCase.UseCaseScenarioContext scenarioContext, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object handleGenerateLinks(long blogId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object handleDisableLinks(long blogId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    private final void manageState(org.wordpress.android.ui.people.InviteLinksUseCase.InviteLinksState state) {
    }
}