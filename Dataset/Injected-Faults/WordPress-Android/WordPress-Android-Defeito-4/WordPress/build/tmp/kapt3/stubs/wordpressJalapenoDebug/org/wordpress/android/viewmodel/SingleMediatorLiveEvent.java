package org.wordpress.android.viewmodel;

import java.lang.System;

/**
 * A lifecycle-aware observable that sends only new updates after subscription, used for events like
 * navigation and Snackbar messages.
 *
 *
 * This avoids a common problem with events: on configuration change (like rotation) an update
 * can be emitted if the observer is active. This LiveData only calls the observable if there's an
 * explicit call to setValue() or call().
 *
 *
 * Note that only one observer is going to be notified of changes.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0016\u0018\u0000 \u0013*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002:\u0001\u0013B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0006\u0010\u0006\u001a\u00020\u0007J\b\u0010\b\u001a\u00020\u0007H\u0007J$\u0010\t\u001a\u00020\u00072\b\b\u0001\u0010\n\u001a\u00020\u000b2\u0010\b\u0001\u0010\f\u001a\n\u0012\u0006\b\u0000\u0012\u00028\u00000\rH\u0017J\u0015\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00028\u0000H\u0016\u00a2\u0006\u0002\u0010\u0010J\u0017\u0010\u0011\u001a\u00020\u00072\b\u0010\u0012\u001a\u0004\u0018\u00018\u0000H\u0017\u00a2\u0006\u0002\u0010\u0010R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lorg/wordpress/android/viewmodel/SingleMediatorLiveEvent;", "T", "Landroidx/lifecycle/MediatorLiveData;", "()V", "pending", "Ljava/util/concurrent/atomic/AtomicBoolean;", "asyncCall", "", "call", "observe", "owner", "Landroidx/lifecycle/LifecycleOwner;", "observer", "Landroidx/lifecycle/Observer;", "postValue", "value", "(Ljava/lang/Object;)V", "setValue", "t", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public class SingleMediatorLiveEvent<T extends java.lang.Object> extends androidx.lifecycle.MediatorLiveData<T> {
    private final java.util.concurrent.atomic.AtomicBoolean pending = null;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.viewmodel.SingleMediatorLiveEvent.Companion Companion = null;
    private static final java.lang.String TAG = "SingleLiveEvent";
    
    public SingleMediatorLiveEvent() {
        super();
    }
    
    @androidx.annotation.MainThread()
    @java.lang.Override()
    public void observe(@org.jetbrains.annotations.NotNull()
    @androidx.annotation.NonNull()
    androidx.lifecycle.LifecycleOwner owner, @org.jetbrains.annotations.NotNull()
    @androidx.annotation.NonNull()
    androidx.lifecycle.Observer<? super T> observer) {
    }
    
    @androidx.annotation.MainThread()
    @java.lang.Override()
    public void setValue(@org.jetbrains.annotations.Nullable()
    T t) {
    }
    
    @java.lang.Override()
    public void postValue(T value) {
    }
    
    /**
     * Used for cases where T is Void, to make calls cleaner.
     */
    @androidx.annotation.MainThread()
    public final void call() {
    }
    
    public final void asyncCall() {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lorg/wordpress/android/viewmodel/SingleMediatorLiveEvent$Companion;", "", "()V", "TAG", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}