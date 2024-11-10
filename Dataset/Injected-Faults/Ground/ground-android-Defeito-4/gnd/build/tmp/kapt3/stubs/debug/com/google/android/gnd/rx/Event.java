package com.google.android.gnd.rx;

import java.lang.System;

/**
 * Wrapper for events passed through streams that should be handled at most once. This is used to
 * prevent events that trigger dialogs or other notifications from re-triggering when views are
 * restored on configuration change.
 *
 * @param <T> The event data.
 * </T>
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \n*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0001\nB\u000f\b\u0002\u0012\u0006\u0010\u0003\u001a\u00028\u0000\u00a2\u0006\u0002\u0010\u0004J\u0014\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\tR\u0010\u0010\u0003\u001a\u00028\u0000X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0005\u00a8\u0006\u000b"}, d2 = {"Lcom/google/android/gnd/rx/Event;", "T", "Lcom/google/android/gnd/rx/Action;", "data", "(Ljava/lang/Object;)V", "Ljava/lang/Object;", "ifUnhandled", "", "consumer", "Ljava8/util/function/Consumer;", "Companion", "gnd_debug"})
public final class Event<T extends java.lang.Object> extends com.google.android.gnd.rx.Action {
    private final T data = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.google.android.gnd.rx.Event.Companion Companion = null;
    
    private Event(T data) {
        super();
    }
    
    /**
     * Invokes the provided consumer if the value has not yet been handled.
     */
    @kotlin.jvm.Synchronized()
    public final synchronized void ifUnhandled(@org.jetbrains.annotations.NotNull()
    java8.util.function.Consumer<T> consumer) {
    }
    
    /**
     * Returns a new event with the specified event data.
     */
    @org.jetbrains.annotations.NotNull()
    @kotlin.jvm.JvmStatic()
    public static final <T extends java.lang.Object>com.google.android.gnd.rx.Event<T> create(T data) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J!\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0001\u0010\u00052\u0006\u0010\u0006\u001a\u0002H\u0005H\u0007\u00a2\u0006\u0002\u0010\u0007\u00a8\u0006\b"}, d2 = {"Lcom/google/android/gnd/rx/Event$Companion;", "", "()V", "create", "Lcom/google/android/gnd/rx/Event;", "T", "data", "(Ljava/lang/Object;)Lcom/google/android/gnd/rx/Event;", "gnd_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        /**
         * Returns a new event with the specified event data.
         */
        @org.jetbrains.annotations.NotNull()
        @kotlin.jvm.JvmStatic()
        public final <T extends java.lang.Object>com.google.android.gnd.rx.Event<T> create(T data) {
            return null;
        }
    }
}