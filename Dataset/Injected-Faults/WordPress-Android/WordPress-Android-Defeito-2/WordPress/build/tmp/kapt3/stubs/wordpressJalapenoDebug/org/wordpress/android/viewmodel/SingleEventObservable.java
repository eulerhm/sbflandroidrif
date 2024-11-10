package org.wordpress.android.viewmodel;

import java.lang.System;

/**
 * A lifecycle-aware observable that sends only new updates after subscription, used for events like
 * navigation and SnackBar messages.
 *
 *
 * This avoids a common problem with events: on configuration change (like rotation) an update
 * can be emitted if the observer is active.
 *
 *
 * Note that only one observer can be subscribed.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0013\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u00a2\u0006\u0002\u0010\u0005J\u001c\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u0010J\u0014\u0010\u0011\u001a\u00020\f2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u0010R$\u0010\u0007\u001a\u0004\u0018\u00018\u00002\b\u0010\u0006\u001a\u0004\u0018\u00018\u0000@BX\u0086\u000e\u00a2\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\tR\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lorg/wordpress/android/viewmodel/SingleEventObservable;", "T", "", "sourceLiveData", "Landroidx/lifecycle/LiveData;", "(Landroidx/lifecycle/LiveData;)V", "<set-?>", "lastEvent", "getLastEvent", "()Ljava/lang/Object;", "Ljava/lang/Object;", "observe", "", "owner", "Landroidx/lifecycle/LifecycleOwner;", "observer", "Landroidx/lifecycle/Observer;", "observeForever", "org.wordpress.android_wordpressJalapenoDebug"})
public final class SingleEventObservable<T extends java.lang.Object> {
    private final androidx.lifecycle.LiveData<T> sourceLiveData = null;
    @org.jetbrains.annotations.Nullable()
    private T lastEvent;
    
    public SingleEventObservable(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LiveData<T> sourceLiveData) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final T getLastEvent() {
        return null;
    }
    
    public final void observe(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LifecycleOwner owner, @org.jetbrains.annotations.NotNull()
    androidx.lifecycle.Observer<T> observer) {
    }
    
    public final void observeForever(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.Observer<T> observer) {
    }
}