package org.owntracks.android.support;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import org.owntracks.android.injection.qualifier.AppContext;
import javax.inject.Singleton;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u0011\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\t\u001a\u00020\nJ\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010J\u0016\u0010\u0011\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lorg/owntracks/android/support/RunThingsOnOtherThreads;", "", "appContext", "Landroid/content/Context;", "(Landroid/content/Context;)V", "backgroundHandler", "Landroid/os/Handler;", "mainHandler", "networkHandler", "getBackgroundLooper", "Landroid/os/Looper;", "postOnMainHandlerDelayed", "", "r", "Ljava/lang/Runnable;", "delayMilliseconds", "", "postOnNetworkHandlerDelayed", "Companion", "app_debug"})
@javax.inject.Singleton()
public final class RunThingsOnOtherThreads {
    private final android.os.Handler backgroundHandler = null;
    private final android.os.Handler mainHandler = null;
    private final android.os.Handler networkHandler = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String NETWORK_HANDLER_THREAD_NAME = "networkHandlerThread";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String BACKGROUND_HANDLER_THREAD_NAME = "backgroundHandlerThread";
    @org.jetbrains.annotations.NotNull()
    public static final org.owntracks.android.support.RunThingsOnOtherThreads.Companion Companion = null;
    
    @org.jetbrains.annotations.NotNull()
    public final android.os.Looper getBackgroundLooper() {
        return null;
    }
    
    public final void postOnMainHandlerDelayed(@org.jetbrains.annotations.NotNull()
    java.lang.Runnable r, long delayMilliseconds) {
    }
    
    public final void postOnNetworkHandlerDelayed(@org.jetbrains.annotations.NotNull()
    java.lang.Runnable r, long delayMilliseconds) {
    }
    
    @javax.inject.Inject()
    public RunThingsOnOtherThreads(@org.jetbrains.annotations.NotNull()
    @org.owntracks.android.injection.qualifier.AppContext()
    android.content.Context appContext) {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lorg/owntracks/android/support/RunThingsOnOtherThreads$Companion;", "", "()V", "BACKGROUND_HANDLER_THREAD_NAME", "", "NETWORK_HANDLER_THREAD_NAME", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}