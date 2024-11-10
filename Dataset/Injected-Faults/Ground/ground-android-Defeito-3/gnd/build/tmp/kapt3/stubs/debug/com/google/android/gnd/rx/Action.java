package com.google.android.gnd.rx;

import java.lang.System;

/**
 * Wrapper for actions passed through streams that should be handled at most once. This is used to
 * prevent actions that trigger dialogs or other notifications from retriggering when views are
 * restored on configuration change.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/google/android/gnd/rx/Action;", "", "()V", "handled", "", "ifUnhandled", "", "handler", "Ljava/lang/Runnable;", "gnd_debug"})
public class Action {
    private boolean handled = false;
    
    protected Action() {
        super();
    }
    
    /**
     * Invokes the provided handler if the value has not yet been handled.
     */
    @kotlin.jvm.Synchronized()
    public final synchronized void ifUnhandled(@org.jetbrains.annotations.NotNull()
    java.lang.Runnable handler) {
    }
}