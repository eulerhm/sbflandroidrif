package org.wordpress.android.viewmodel;

import java.lang.System;

/**
 * ReactiveMutableLiveData is a simple extension of MutableLiveData.
 * It's purpose is to allow users to monitor onActive and onInactive
 * situations because those methods are unreachable using straight up LiveData
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u000b*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002:\u0001\u000bB\'\b\u0016\u0012\u000e\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\u0002\u0010\u0007B\r\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\b\u0010\u0003\u001a\u00020\u0005H\u0014J\b\u0010\u0006\u001a\u00020\u0005H\u0014R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lorg/wordpress/android/viewmodel/ReactiveMutableLiveData;", "T", "Landroidx/lifecycle/MutableLiveData;", "onActive", "Lkotlin/Function0;", "", "onInactive", "(Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;)V", "onReactiveListener", "Lorg/wordpress/android/viewmodel/OnReactiveListener;", "(Lorg/wordpress/android/viewmodel/OnReactiveListener;)V", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public final class ReactiveMutableLiveData<T extends java.lang.Object> extends androidx.lifecycle.MutableLiveData<T> {
    private final org.wordpress.android.viewmodel.OnReactiveListener onReactiveListener = null;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.viewmodel.ReactiveMutableLiveData.Companion Companion = null;
    
    public ReactiveMutableLiveData(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.OnReactiveListener onReactiveListener) {
        super(null);
    }
    
    public ReactiveMutableLiveData(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onActive, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onInactive) {
        super(null);
    }
    
    @java.lang.Override()
    protected void onActive() {
    }
    
    @java.lang.Override()
    protected void onInactive() {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\"\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u00a8\u0006\t"}, d2 = {"Lorg/wordpress/android/viewmodel/ReactiveMutableLiveData$Companion;", "", "()V", "setReactiveListener", "Lorg/wordpress/android/viewmodel/OnReactiveListener;", "onActive", "Lkotlin/Function0;", "", "onInactive", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        /**
         * Creates a OnReactiveListener that can be passed into the constructor
         */
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.viewmodel.OnReactiveListener setReactiveListener(@org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function0<kotlin.Unit> onActive, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function0<kotlin.Unit> onInactive) {
            return null;
        }
    }
}