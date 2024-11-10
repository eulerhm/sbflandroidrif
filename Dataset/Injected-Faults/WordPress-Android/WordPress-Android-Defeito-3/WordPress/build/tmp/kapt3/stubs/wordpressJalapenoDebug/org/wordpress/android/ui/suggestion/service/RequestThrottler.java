package org.wordpress.android.ui.suggestion.service;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u001f\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006\u00a2\u0006\u0002\u0010\u0007J\u0013\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00028\u0000\u00a2\u0006\u0002\u0010\rJ\u0013\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00028\u0000\u00a2\u0006\u0002\u0010\u0010R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00040\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/suggestion/service/RequestThrottler;", "K", "", "msBeforeRefresh", "", "currentTime", "Lkotlin/Function0;", "(JLkotlin/jvm/functions/Function0;)V", "timeSinceLastResponseMap", "", "areResultsStale", "", "key", "(Ljava/lang/Object;)Z", "onResponseReceived", "", "(Ljava/lang/Object;)V", "org.wordpress.android_wordpressJalapenoDebug"})
public final class RequestThrottler<K extends java.lang.Object> {
    private final long msBeforeRefresh = 0L;
    private final kotlin.jvm.functions.Function0<java.lang.Long> currentTime = null;
    private final java.util.Map<K, java.lang.Long> timeSinceLastResponseMap = null;
    
    public RequestThrottler() {
        super();
    }
    
    public RequestThrottler(long msBeforeRefresh, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<java.lang.Long> currentTime) {
        super();
    }
    
    public final boolean areResultsStale(K key) {
        return false;
    }
    
    public final void onResponseReceived(K key) {
    }
}