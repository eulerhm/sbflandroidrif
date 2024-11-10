package org.wordpress.android.usecase;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u0002*\u0004\b\u0002\u0010\u0003*\u0004\b\u0003\u0010\u0004*\u0004\b\u0004\u0010\u00052\u00020\u0006:\u0001\u0018B3\u0012$\u0010\u0007\u001a \u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u0003\u0012\u0004\u0012\u00028\u00040\b\u0012\u0006\u0010\t\u001a\u00028\u0000\u00a2\u0006\u0002\u0010\nJ\u0019\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00028\u0001H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0015J\u001e\u0010\u0016\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00028\u0003\u0012\u0004\u0012\u00028\u0004\u0012\u0004\u0012\u00028\u00020\r0\u0017R&\u0010\u000b\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00028\u0003\u0012\u0004\u0012\u00028\u0004\u0012\u0004\u0012\u00028\u00020\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R,\u0010\u000e\u001a \u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u0003\u0012\u0004\u0012\u00028\u00040\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0013\u0010\t\u001a\u00028\u0000\u00a2\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u000f\u0010\u0010\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0019"}, d2 = {"Lorg/wordpress/android/usecase/FlowFSMUseCase;", "RESOURCE_PROVIDER", "ACTION_TYPE", "DATA", "USE_CASE_TYPE", "ERROR", "", "initialState", "Lorg/wordpress/android/usecase/FlowFSMUseCase$StateInterface;", "resourceProvider", "(Lorg/wordpress/android/usecase/FlowFSMUseCase$StateInterface;Ljava/lang/Object;)V", "_flowChannel", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "Lorg/wordpress/android/usecase/UseCaseResult;", "_internalState", "getResourceProvider", "()Ljava/lang/Object;", "Ljava/lang/Object;", "manageAction", "", "action", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "subscribe", "Lkotlinx/coroutines/flow/SharedFlow;", "StateInterface", "org.wordpress.android_wordpressJalapenoDebug"})
public class FlowFSMUseCase<RESOURCE_PROVIDER extends java.lang.Object, ACTION_TYPE extends java.lang.Object, DATA extends java.lang.Object, USE_CASE_TYPE extends java.lang.Object, ERROR extends java.lang.Object> {
    private final RESOURCE_PROVIDER resourceProvider = null;
    private final kotlinx.coroutines.flow.MutableSharedFlow<org.wordpress.android.usecase.UseCaseResult<USE_CASE_TYPE, ERROR, DATA>> _flowChannel = null;
    private org.wordpress.android.usecase.FlowFSMUseCase.StateInterface<RESOURCE_PROVIDER, ACTION_TYPE, DATA, USE_CASE_TYPE, ERROR> _internalState;
    
    public FlowFSMUseCase(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.usecase.FlowFSMUseCase.StateInterface<RESOURCE_PROVIDER, ACTION_TYPE, DATA, USE_CASE_TYPE, ERROR> initialState, RESOURCE_PROVIDER resourceProvider) {
        super();
    }
    
    public final RESOURCE_PROVIDER getResourceProvider() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.SharedFlow<org.wordpress.android.usecase.UseCaseResult<USE_CASE_TYPE, ERROR, DATA>> subscribe() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object manageAction(ACTION_TYPE action, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u0000*\u0004\b\u0005\u0010\u0001*\u0004\b\u0006\u0010\u0002*\u0004\b\u0007\u0010\u0003*\u0004\b\b\u0010\u0004*\u0004\b\t\u0010\u00052\u00020\u0006J_\u0010\u0007\u001a \u0012\u0004\u0012\u00028\u0005\u0012\u0004\u0012\u00028\u0006\u0012\u0004\u0012\u00028\u0007\u0012\u0004\u0012\u00028\b\u0012\u0004\u0012\u00028\t0\u00002\u0006\u0010\b\u001a\u00028\u00052\u0006\u0010\t\u001a\u00028\u00062\u001e\u0010\n\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00028\b\u0012\u0004\u0012\u00028\t\u0012\u0004\u0012\u00028\u00070\f0\u000bH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\r\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u000e"}, d2 = {"Lorg/wordpress/android/usecase/FlowFSMUseCase$StateInterface;", "RESOURCE_PROVIDER", "TRANSITION_ACTION", "RESULT", "USE_CASE_TYPE", "ERROR", "", "runAction", "utilsProvider", "action", "flowChannel", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "Lorg/wordpress/android/usecase/UseCaseResult;", "(Ljava/lang/Object;Ljava/lang/Object;Lkotlinx/coroutines/flow/MutableSharedFlow;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract interface StateInterface<RESOURCE_PROVIDER extends java.lang.Object, TRANSITION_ACTION extends java.lang.Object, RESULT extends java.lang.Object, USE_CASE_TYPE extends java.lang.Object, ERROR extends java.lang.Object> {
        
        @org.jetbrains.annotations.Nullable()
        public abstract java.lang.Object runAction(RESOURCE_PROVIDER utilsProvider, TRANSITION_ACTION action, @org.jetbrains.annotations.NotNull()
        kotlinx.coroutines.flow.MutableSharedFlow<org.wordpress.android.usecase.UseCaseResult<USE_CASE_TYPE, ERROR, RESULT>> flowChannel, @org.jetbrains.annotations.NotNull()
        kotlin.coroutines.Continuation<? super org.wordpress.android.usecase.FlowFSMUseCase.StateInterface<RESOURCE_PROVIDER, TRANSITION_ACTION, RESULT, USE_CASE_TYPE, ERROR>> continuation);
    }
}