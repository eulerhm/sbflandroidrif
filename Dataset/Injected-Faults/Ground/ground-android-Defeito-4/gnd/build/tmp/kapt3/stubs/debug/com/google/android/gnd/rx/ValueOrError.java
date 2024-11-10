package com.google.android.gnd.rx;

import java.lang.System;

/**
 * Represents the outcome of an operation that either succeeds with a value, or fails with an
 * exception.
 *
 * @param <T> the type of value held by instances of this `ValueOrError`.
 * </T>
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u0000 \f*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0001\fB\u001b\b\u0004\u0012\b\u0010\u0003\u001a\u0004\u0018\u00018\u0000\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\u0006J\u000e\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u000bJ\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u000bR\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\tR\u0012\u0010\u0003\u001a\u0004\u0018\u00018\u0000X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\n\u00a8\u0006\r"}, d2 = {"Lcom/google/android/gnd/rx/ValueOrError;", "T", "", "value", "error", "", "(Ljava/lang/Object;Ljava/lang/Throwable;)V", "isPresent", "", "()Z", "Ljava/lang/Object;", "Ljava8/util/Optional;", "Companion", "gnd_debug"})
public class ValueOrError<T extends java.lang.Object> {
    private final T value = null;
    private final java.lang.Throwable error = null;
    private final boolean isPresent = false;
    @org.jetbrains.annotations.NotNull()
    public static final com.google.android.gnd.rx.ValueOrError.Companion Companion = null;
    
    protected ValueOrError(@org.jetbrains.annotations.Nullable()
    T value, @org.jetbrains.annotations.Nullable()
    java.lang.Throwable error) {
        super();
    }
    
    public final boolean isPresent() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java8.util.Optional<T> value() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java8.util.Optional<java.lang.Throwable> error() {
        return null;
    }
    
    /**
     * Returns the value returned by the specified supplier, or an error if the supplier fails.
     */
    @org.jetbrains.annotations.NotNull()
    @kotlin.jvm.JvmStatic()
    public static final <T extends java.lang.Object>com.google.android.gnd.rx.ValueOrError<T> create(@org.jetbrains.annotations.NotNull()
    java8.util.function.Supplier<T> supplier) {
        return null;
    }
    
    /**
     * Modifies the specified stream to ignore errors, returning wrapped values.
     */
    @org.jetbrains.annotations.NotNull()
    @kotlin.jvm.JvmStatic()
    public static final <T extends java.lang.Object>io.reactivex.Observable<T> ignoreErrors(@org.jetbrains.annotations.NotNull()
    io.reactivex.Observable<com.google.android.gnd.rx.ValueOrError<T>> observable) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J$\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00050\u0004\"\u0004\b\u0001\u0010\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0007H\u0007J(\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00050\t\"\u0004\b\u0001\u0010\u00052\u0012\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00050\u00040\tH\u0007J \u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00050\u0004\"\u0004\b\u0001\u0010\u00052\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0002J#\u0010\u000e\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00050\u0004\"\u0004\b\u0001\u0010\u00052\u0006\u0010\u000f\u001a\u0002H\u0005H\u0002\u00a2\u0006\u0002\u0010\u0010\u00a8\u0006\u0011"}, d2 = {"Lcom/google/android/gnd/rx/ValueOrError$Companion;", "", "()V", "create", "Lcom/google/android/gnd/rx/ValueOrError;", "T", "supplier", "Ljava8/util/function/Supplier;", "ignoreErrors", "Lio/reactivex/Observable;", "observable", "newError", "t", "", "newValue", "value", "(Ljava/lang/Object;)Lcom/google/android/gnd/rx/ValueOrError;", "gnd_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        /**
         * Returns the value returned by the specified supplier, or an error if the supplier fails.
         */
        @org.jetbrains.annotations.NotNull()
        @kotlin.jvm.JvmStatic()
        public final <T extends java.lang.Object>com.google.android.gnd.rx.ValueOrError<T> create(@org.jetbrains.annotations.NotNull()
        java8.util.function.Supplier<T> supplier) {
            return null;
        }
        
        /**
         * Returns a new instance with the specified value.
         */
        private final <T extends java.lang.Object>com.google.android.gnd.rx.ValueOrError<T> newValue(T value) {
            return null;
        }
        
        /**
         * Returns a new instance with the specified error.
         */
        private final <T extends java.lang.Object>com.google.android.gnd.rx.ValueOrError<T> newError(java.lang.Throwable t) {
            return null;
        }
        
        /**
         * Modifies the specified stream to ignore errors, returning wrapped values.
         */
        @org.jetbrains.annotations.NotNull()
        @kotlin.jvm.JvmStatic()
        public final <T extends java.lang.Object>io.reactivex.Observable<T> ignoreErrors(@org.jetbrains.annotations.NotNull()
        io.reactivex.Observable<com.google.android.gnd.rx.ValueOrError<T>> observable) {
            return null;
        }
    }
}