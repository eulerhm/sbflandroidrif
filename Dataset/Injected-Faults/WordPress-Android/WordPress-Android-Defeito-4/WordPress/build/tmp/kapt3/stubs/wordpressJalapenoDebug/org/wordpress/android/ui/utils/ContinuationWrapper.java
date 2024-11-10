package org.wordpress.android.ui.utils;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J\b\u0010\u0006\u001a\u00020\u0007H&J\u0015\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00028\u0000H&\u00a2\u0006\u0002\u0010\nJ+\u0010\u000b\u001a\u00028\u00002\u0018\u0010\f\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u000e\u0012\u0004\u0012\u00020\u00070\rH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000fR\u0012\u0010\u0003\u001a\u00020\u0004X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0003\u0010\u0005\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0010"}, d2 = {"Lorg/wordpress/android/ui/utils/ContinuationWrapper;", "T", "", "isWaiting", "", "()Z", "cancel", "", "continueWith", "t", "(Ljava/lang/Object;)V", "suspendCoroutine", "block", "Lkotlin/Function1;", "Lkotlinx/coroutines/CancellableContinuation;", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract interface ContinuationWrapper<T extends java.lang.Object> {
    
    public abstract boolean isWaiting();
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object suspendCoroutine(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super kotlinx.coroutines.CancellableContinuation<? super T>, kotlin.Unit> block, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super T> continuation);
    
    public abstract void continueWith(T t);
    
    public abstract void cancel();
}