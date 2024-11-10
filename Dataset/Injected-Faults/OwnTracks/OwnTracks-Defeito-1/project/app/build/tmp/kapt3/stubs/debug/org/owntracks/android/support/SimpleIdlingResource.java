package org.owntracks.android.support;

import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.IdlingResource.ResourceCallback;
import org.jetbrains.annotations.NotNull;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * A very simple implementation of [IdlingResource].
 *
 *
 * Consider using CountingIdlingResource from espresso-contrib package if you use this class from
 * multiple threads or need to keep a count of pending operations.
 */
@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u001a\u0012\u000b\u0010\u0002\u001a\u00070\u0003\u00a2\u0006\u0002\b\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\b\u0010\f\u001a\u00020\u0003H\u0016J\b\u0010\r\u001a\u00020\u0006H\u0016J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\tH\u0016J\u000e\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\r\u001a\u00020\u0006R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0013\u0010\u0002\u001a\u00070\u0003\u00a2\u0006\u0002\b\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lorg/owntracks/android/support/SimpleIdlingResource;", "Landroidx/test/espresso/IdlingResource;", "resourceName", "", "Lorg/jetbrains/annotations/NotNull;", "initialIdlingState", "", "(Ljava/lang/String;Z)V", "mCallback", "Landroidx/test/espresso/IdlingResource$ResourceCallback;", "mIsIdleNow", "Ljava/util/concurrent/atomic/AtomicBoolean;", "getName", "isIdleNow", "registerIdleTransitionCallback", "", "callback", "setIdleState", "app_debug"})
public final class SimpleIdlingResource implements androidx.test.espresso.IdlingResource {
    private volatile androidx.test.espresso.IdlingResource.ResourceCallback mCallback;
    private final java.util.concurrent.atomic.AtomicBoolean mIsIdleNow = null;
    private final java.lang.String resourceName = null;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String getName() {
        return null;
    }
    
    @java.lang.Override()
    public boolean isIdleNow() {
        return false;
    }
    
    @java.lang.Override()
    public void registerIdleTransitionCallback(@org.jetbrains.annotations.NotNull()
    androidx.test.espresso.IdlingResource.ResourceCallback callback) {
    }
    
    /**
     * Sets the new idle state, if isIdleNow is true, it pings the [ResourceCallback].
     * @param isIdleNow false if there are pending operations, true if idle.
     */
    public final void setIdleState(boolean isIdleNow) {
    }
    
    public SimpleIdlingResource(@org.jetbrains.annotations.NotNull()
    java.lang.String resourceName, boolean initialIdlingState) {
        super();
    }
}