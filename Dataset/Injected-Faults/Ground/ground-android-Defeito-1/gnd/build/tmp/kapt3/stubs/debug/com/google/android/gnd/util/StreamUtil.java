package com.google.android.gnd.util;

import java.lang.System;

/**
 * Methods for working with and manipulating [java8.util.stream.Stream].
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\"\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0000\u0010\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0007H\u0007\u00a8\u0006\b"}, d2 = {"Lcom/google/android/gnd/util/StreamUtil;", "", "()V", "logErrorsAndSkip", "Ljava8/util/stream/Stream;", "R", "supplier", "Ljava8/util/function/Supplier;", "gnd_debug"})
public final class StreamUtil {
    @org.jetbrains.annotations.NotNull()
    public static final com.google.android.gnd.util.StreamUtil INSTANCE = null;
    
    private StreamUtil() {
        super();
    }
    
    /**
     * Executes the specified Supplier, writing throw Error exceptions to debug logs. If an error
     * occurs, an empty Stream is returned, otherwise a Stream with only the result of the Supplier is
     * returned.
     */
    @org.jetbrains.annotations.NotNull()
    @kotlin.jvm.JvmStatic()
    public static final <R extends java.lang.Object>java8.util.stream.Stream<R> logErrorsAndSkip(@org.jetbrains.annotations.NotNull()
    java8.util.function.Supplier<R> supplier) {
        return null;
    }
}