package org.wordpress.android.util;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B+\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\b\u00a2\u0006\u0002\u0010\nJ\u0015\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00028\u0000H\u0016\u00a2\u0006\u0002\u0010\u0012R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0012\u0010\r\u001a\u0004\u0018\u00018\u0000X\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\u000e\u00a8\u0006\u0013"}, d2 = {"Lorg/wordpress/android/util/ThrottleLiveData;", "T", "Landroidx/lifecycle/MediatorLiveData;", "offset", "", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "backgroundDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "mainDispatcher", "(JLkotlinx/coroutines/CoroutineScope;Lkotlinx/coroutines/CoroutineDispatcher;Lkotlinx/coroutines/CoroutineDispatcher;)V", "currentJob", "Lkotlinx/coroutines/Job;", "tempValue", "Ljava/lang/Object;", "postValue", "", "value", "(Ljava/lang/Object;)V", "org.wordpress.android_wordpressJalapenoDebug"})
public final class ThrottleLiveData<T extends java.lang.Object> extends androidx.lifecycle.MediatorLiveData<T> {
    private final long offset = 0L;
    private final kotlinx.coroutines.CoroutineScope coroutineScope = null;
    private final kotlinx.coroutines.CoroutineDispatcher backgroundDispatcher = null;
    private final kotlinx.coroutines.CoroutineDispatcher mainDispatcher = null;
    private T tempValue;
    private kotlinx.coroutines.Job currentJob;
    
    public ThrottleLiveData(long offset, @org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.CoroutineScope coroutineScope, @org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.CoroutineDispatcher backgroundDispatcher, @org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.CoroutineDispatcher mainDispatcher) {
        super();
    }
    
    @java.lang.Override()
    public void postValue(T value) {
    }
}