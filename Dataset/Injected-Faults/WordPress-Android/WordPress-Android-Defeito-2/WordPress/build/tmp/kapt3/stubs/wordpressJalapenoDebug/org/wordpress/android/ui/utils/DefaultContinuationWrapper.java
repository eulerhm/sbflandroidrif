package org.wordpress.android.ui.utils;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\t\u001a\u00020\nH\u0016J\u0015\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00028\u0000H\u0016\u00a2\u0006\u0002\u0010\rJ+\u0010\u000e\u001a\u00028\u00002\u0018\u0010\u000f\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\u0004\u0012\u00020\n0\u0010H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0011R\u0016\u0010\u0004\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u00078VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0006\u0010\b\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0012"}, d2 = {"Lorg/wordpress/android/ui/utils/DefaultContinuationWrapper;", "T", "Lorg/wordpress/android/ui/utils/ContinuationWrapper;", "()V", "continuation", "Lkotlinx/coroutines/CancellableContinuation;", "isWaiting", "", "()Z", "cancel", "", "continueWith", "t", "(Ljava/lang/Object;)V", "suspendCoroutine", "block", "Lkotlin/Function1;", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "org.wordpress.android_wordpressJalapenoDebug"})
public final class DefaultContinuationWrapper<T extends java.lang.Object> implements org.wordpress.android.ui.utils.ContinuationWrapper<T> {
    private kotlinx.coroutines.CancellableContinuation<? super T> continuation;
    
    public DefaultContinuationWrapper() {
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