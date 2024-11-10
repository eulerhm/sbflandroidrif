package com.google.android.gnd.rx;

import java.lang.System;

/**
 * Wraps the state of an entity that can be loaded asynchronously. Based on `Resource` in
 * Android Guide to App Architecture: https://developer.android.com/jetpack/docs/guide#addendum
 *
 * @param <T> the type of data payload the resource contains.
 * </T>
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\u0018\u0000 \u000e*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002:\u0002\u000e\u000fB#\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00018\u0000\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\u0002\u0010\bR\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u000bR\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r\u00a8\u0006\u0010"}, d2 = {"Lcom/google/android/gnd/rx/Loadable;", "T", "Lcom/google/android/gnd/rx/ValueOrError;", "state", "Lcom/google/android/gnd/rx/Loadable$LoadState;", "data", "error", "", "(Lcom/google/android/gnd/rx/Loadable$LoadState;Ljava/lang/Object;Ljava/lang/Throwable;)V", "isLoaded", "", "()Z", "getState", "()Lcom/google/android/gnd/rx/Loadable$LoadState;", "Companion", "LoadState", "gnd_debug"})
public final class Loadable<T extends java.lang.Object> extends com.google.android.gnd.rx.ValueOrError<T> {
    @org.jetbrains.annotations.NotNull()
    private final com.google.android.gnd.rx.Loadable.LoadState state = null;
    private final boolean isLoaded = false;
    @org.jetbrains.annotations.NotNull()
    public static final com.google.android.gnd.rx.Loadable.Companion Companion = null;
    
    private Loadable(com.google.android.gnd.rx.Loadable.LoadState state, T data, java.lang.Throwable error) {
        super(null, null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.google.android.gnd.rx.Loadable.LoadState getState() {
        return null;
    }
    
    public final boolean isLoaded() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    @kotlin.jvm.JvmStatic()
    public static final <T extends java.lang.Object>com.google.android.gnd.rx.Loadable<T> notLoaded() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @kotlin.jvm.JvmStatic()
    public static final <T extends java.lang.Object>com.google.android.gnd.rx.Loadable<T> loaded(T data) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @kotlin.jvm.JvmStatic()
    public static final <T extends java.lang.Object>com.google.android.gnd.rx.Loadable<T> error(@org.jetbrains.annotations.NotNull()
    java.lang.Throwable t) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @kotlin.jvm.JvmStatic()
    public static final <T extends java.lang.Object>java8.util.Optional<T> getValue(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LiveData<com.google.android.gnd.rx.Loadable<T>> liveData) {
        return null;
    }
    
    /**
     * Modifies the provided stream to emit values instead of [Loadable] only when a value is
     * loaded (i.e., omitting intermediate loading and error states).
     */
    @org.jetbrains.annotations.NotNull()
    @kotlin.jvm.JvmStatic()
    public static final <V extends java.lang.Object>org.reactivestreams.Publisher<V> values(@org.jetbrains.annotations.NotNull()
    io.reactivex.Flowable<com.google.android.gnd.rx.Loadable<V>> stream) {
        return null;
    }
    
    /**
     * Returns a [Flowable] that first emits LOADING, then maps values emitted from the source
     * stream to `Loadable`s with a LOADED `Loadable`. Errors in the provided stream are
     * handled and wrapped in a `Loadable` with state ERROR. The returned stream itself should
     * never fail with an error.
     *
     * @param source the stream responsible for loading values.
     * @param <T> the type of entity being loaded
     *       </T>
     */
    @org.jetbrains.annotations.NotNull()
    @kotlin.jvm.JvmStatic()
    public static final <T extends java.lang.Object>io.reactivex.Flowable<com.google.android.gnd.rx.Loadable<T>> loadingOnceAndWrap(@org.jetbrains.annotations.NotNull()
    io.reactivex.Flowable<T> source) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007\u00a8\u0006\b"}, d2 = {"Lcom/google/android/gnd/rx/Loadable$LoadState;", "", "(Ljava/lang/String;I)V", "NOT_LOADED", "LOADING", "LOADED", "NOT_FOUND", "ERROR", "gnd_debug"})
    public static enum LoadState {
        /*public static final*/ NOT_LOADED /* = new NOT_LOADED() */,
        /*public static final*/ LOADING /* = new LOADING() */,
        /*public static final*/ LOADED /* = new LOADED() */,
        /*public static final*/ NOT_FOUND /* = new NOT_FOUND() */,
        /*public static final*/ ERROR /* = new ERROR() */;
        
        LoadState() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0001\u0010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007J.\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00050\t\"\u0004\b\u0001\u0010\u00052\u0016\u0010\n\u001a\u0012\u0012\u000e\u0012\f\u0012\u0006\u0012\u0004\u0018\u0001H\u0005\u0018\u00010\u00040\u000bH\u0007J!\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0001\u0010\u00052\u0006\u0010\r\u001a\u0002H\u0005H\u0007\u00a2\u0006\u0002\u0010\u000eJ\u0012\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0001\u0010\u0005J(\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00050\u00040\u0011\"\u0004\b\u0001\u0010\u00052\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0011H\u0007J\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0001\u0010\u0005H\u0007J*\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u00160\u0015\"\u0004\b\u0001\u0010\u00162\u0014\u0010\u0017\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00160\u00040\u0011H\u0007\u00a8\u0006\u0018"}, d2 = {"Lcom/google/android/gnd/rx/Loadable$Companion;", "", "()V", "error", "Lcom/google/android/gnd/rx/Loadable;", "T", "t", "", "getValue", "Ljava8/util/Optional;", "liveData", "Landroidx/lifecycle/LiveData;", "loaded", "data", "(Ljava/lang/Object;)Lcom/google/android/gnd/rx/Loadable;", "loading", "loadingOnceAndWrap", "Lio/reactivex/Flowable;", "source", "notLoaded", "values", "Lorg/reactivestreams/Publisher;", "V", "stream", "gnd_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        @kotlin.jvm.JvmStatic()
        public final <T extends java.lang.Object>com.google.android.gnd.rx.Loadable<T> notLoaded() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final <T extends java.lang.Object>com.google.android.gnd.rx.Loadable<T> loading() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @kotlin.jvm.JvmStatic()
        public final <T extends java.lang.Object>com.google.android.gnd.rx.Loadable<T> loaded(T data) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @kotlin.jvm.JvmStatic()
        public final <T extends java.lang.Object>com.google.android.gnd.rx.Loadable<T> error(@org.jetbrains.annotations.NotNull()
        java.lang.Throwable t) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @kotlin.jvm.JvmStatic()
        public final <T extends java.lang.Object>java8.util.Optional<T> getValue(@org.jetbrains.annotations.NotNull()
        androidx.lifecycle.LiveData<com.google.android.gnd.rx.Loadable<T>> liveData) {
            return null;
        }
        
        /**
         * Modifies the provided stream to emit values instead of [Loadable] only when a value is
         * loaded (i.e., omitting intermediate loading and error states).
         */
        @org.jetbrains.annotations.NotNull()
        @kotlin.jvm.JvmStatic()
        public final <V extends java.lang.Object>org.reactivestreams.Publisher<V> values(@org.jetbrains.annotations.NotNull()
        io.reactivex.Flowable<com.google.android.gnd.rx.Loadable<V>> stream) {
            return null;
        }
        
        /**
         * Returns a [Flowable] that first emits LOADING, then maps values emitted from the source
         * stream to `Loadable`s with a LOADED `Loadable`. Errors in the provided stream are
         * handled and wrapped in a `Loadable` with state ERROR. The returned stream itself should
         * never fail with an error.
         *
         * @param source the stream responsible for loading values.
         * @param <T> the type of entity being loaded
         *       </T>
         */
        @org.jetbrains.annotations.NotNull()
        @kotlin.jvm.JvmStatic()
        public final <T extends java.lang.Object>io.reactivex.Flowable<com.google.android.gnd.rx.Loadable<T>> loadingOnceAndWrap(@org.jetbrains.annotations.NotNull()
        io.reactivex.Flowable<T> source) {
            return null;
        }
    }
}