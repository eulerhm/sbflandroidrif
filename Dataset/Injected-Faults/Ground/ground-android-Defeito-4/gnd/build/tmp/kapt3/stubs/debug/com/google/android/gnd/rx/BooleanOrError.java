package com.google.android.gnd.rx;

import java.lang.System;

/**
 * The result of an operation that can return either true, false, or fail with an exception.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0005\u0018\u0000 \t2\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001:\u0001\tB\u001b\b\u0002\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0002\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b\u00a8\u0006\n"}, d2 = {"Lcom/google/android/gnd/rx/BooleanOrError;", "Lcom/google/android/gnd/rx/ValueOrError;", "", "value", "error", "", "(Ljava/lang/Boolean;Ljava/lang/Throwable;)V", "isTrue", "()Z", "Companion", "gnd_debug"})
public final class BooleanOrError extends com.google.android.gnd.rx.ValueOrError<java.lang.Boolean> {
    
    /**
     * Returns true if the operation succeeded with a result of `true`, or false otherwise. Note
     * that false is also returned if the operation failed in error.
     */
    private final boolean isTrue = false;
    @org.jetbrains.annotations.NotNull()
    public static final com.google.android.gnd.rx.BooleanOrError.Companion Companion = null;
    
    private BooleanOrError(java.lang.Boolean value, java.lang.Throwable error) {
        super(null, null);
    }
    
    public final boolean isTrue() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    @kotlin.jvm.JvmStatic()
    public static final com.google.android.gnd.rx.BooleanOrError trueValue() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @kotlin.jvm.JvmStatic()
    public static final com.google.android.gnd.rx.BooleanOrError falseValue() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @kotlin.jvm.JvmStatic()
    public static final com.google.android.gnd.rx.BooleanOrError error(@org.jetbrains.annotations.Nullable()
    java.lang.Throwable t) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007J\b\u0010\u0007\u001a\u00020\u0004H\u0007J\b\u0010\b\u001a\u00020\u0004H\u0007\u00a8\u0006\t"}, d2 = {"Lcom/google/android/gnd/rx/BooleanOrError$Companion;", "", "()V", "error", "Lcom/google/android/gnd/rx/BooleanOrError;", "t", "", "falseValue", "trueValue", "gnd_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        @kotlin.jvm.JvmStatic()
        public final com.google.android.gnd.rx.BooleanOrError trueValue() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @kotlin.jvm.JvmStatic()
        public final com.google.android.gnd.rx.BooleanOrError falseValue() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @kotlin.jvm.JvmStatic()
        public final com.google.android.gnd.rx.BooleanOrError error(@org.jetbrains.annotations.Nullable()
        java.lang.Throwable t) {
            return null;
        }
    }
}