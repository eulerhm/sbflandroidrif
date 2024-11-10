package org.wordpress.android.ui.utils;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\n\u001a\u00020\u000bH\u0016J\u0015\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00028\u0000H\u0016\u00a2\u0006\u0002\u0010\u000eJ+\u0010\u000f\u001a\u00028\u00002\u0018\u0010\u0010\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0006\u0012\u0004\u0012\u00020\u000b0\u0011H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0012R\u001a\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\b8VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0007\u0010\t\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0013"}, d2 = {"Lorg/wordpress/android/ui/utils/ConcurrentContinuationWrapper;", "T", "Lorg/wordpress/android/ui/utils/ContinuationWrapper;", "()V", "continuationList", "Ljava/util/ArrayList;", "Lkotlinx/coroutines/CancellableContinuation;", "isWaiting", "", "()Z", "cancel", "", "continueWith", "t", "(Ljava/lang/Object;)V", "suspendCoroutine", "block", "Lkotlin/Function1;", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "org.wordpress.android_wordpressJalapenoDebug"})
public final class ConcurrentContinuationWrapper<T extends java.lang.Object> implements org.wordpress.android.ui.utils.ContinuationWrapper<T> {
    private final java.util.ArrayList<kotlinx.coroutines.CancellableContinuation<T>> continuationList = null;
    
    public ConcurrentContinuationWrapper() {
        super();
    }
    
    @java.lang.Override()
    public boolean isWaiting() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object suspendCoroutine(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super kotlinx.coroutines.CancellableContinuation<? super T>, kotlin.Unit> block, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super T> continuation) {
        return null;
    }
    
    @java.lang.Override()
    public void continueWith(T t) {
    }
    
    @java.lang.Override()
    public void cancel() {
    }
}