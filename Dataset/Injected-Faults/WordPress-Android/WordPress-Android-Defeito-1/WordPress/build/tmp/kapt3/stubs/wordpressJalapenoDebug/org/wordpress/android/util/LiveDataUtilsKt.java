package org.wordpress.android.util;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 2, d1 = {"\u0000\u0084\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u001c\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a>\u0010\u0000\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u00040\u00020\u0001\"\u0004\b\u0000\u0010\u0003\"\u0004\b\u0001\u0010\u00042\u0018\u0010\u0005\u001a\u0014\u0012\u0004\u0012\u0002H\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00040\u00060\u0002\u001a\u00b8\u0001\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\b0\u0006\"\u0004\b\u0000\u0010\t\"\u0004\b\u0001\u0010\n\"\u0004\b\u0002\u0010\u000b\"\u0004\b\u0003\u0010\f\"\u0004\b\u0004\u0010\r\"\u0004\b\u0005\u0010\b2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\t0\u00062\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\n0\u00062\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\u000b0\u00062\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\f0\u00062\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\r0\u00062\b\b\u0002\u0010\u0013\u001a\u00020\u001426\u0010\u0015\u001a2\u0012\u0006\u0012\u0004\u0018\u0001H\t\u0012\u0006\u0012\u0004\u0018\u0001H\n\u0012\u0006\u0012\u0004\u0018\u0001H\u000b\u0012\u0006\u0012\u0004\u0018\u0001H\f\u0012\u0006\u0012\u0004\u0018\u0001H\r\u0012\u0006\u0012\u0004\u0018\u0001H\b0\u0016\u001a\u009c\u0001\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\r0\u0006\"\u0004\b\u0000\u0010\t\"\u0004\b\u0001\u0010\n\"\u0004\b\u0002\u0010\u000b\"\u0004\b\u0003\u0010\f\"\u0004\b\u0004\u0010\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\t0\u00062\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\n0\u00062\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\u000b0\u00062\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\f0\u00062\b\b\u0002\u0010\u0013\u001a\u00020\u00142.\u0010\u0015\u001a*\u0012\u0006\u0012\u0004\u0018\u0001H\t\u0012\u0006\u0012\u0004\u0018\u0001H\n\u0012\u0006\u0012\u0004\u0018\u0001H\u000b\u0012\u0006\u0012\u0004\u0018\u0001H\f\u0012\u0006\u0012\u0004\u0018\u0001H\r0\u0017\u001a~\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\f0\u0001\"\u0004\b\u0000\u0010\t\"\u0004\b\u0001\u0010\n\"\u0004\b\u0002\u0010\u000b\"\u0004\b\u0003\u0010\f2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\t0\u00062\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\n0\u00062\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\u000b0\u00062\b\b\u0002\u0010\u0013\u001a\u00020\u00142$\u0010\u0015\u001a \u0012\u0006\u0012\u0004\u0018\u0001H\t\u0012\u0006\u0012\u0004\u0018\u0001H\n\u0012\u0006\u0012\u0004\u0018\u0001H\u000b\u0012\u0004\u0012\u0002H\f0\u0018\u001aZ\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\f0\u0006\"\u0004\b\u0000\u0010\n\"\u0004\b\u0001\u0010\u000b\"\u0004\b\u0002\u0010\f2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\n0\u00062\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u000b0\u00062\u001e\u0010\u0015\u001a\u001a\u0012\u0006\u0012\u0004\u0018\u0001H\n\u0012\u0006\u0012\u0004\u0018\u0001H\u000b\u0012\u0006\u0012\u0004\u0018\u0001H\f0\u0019\u001a;\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\n0\u0001\"\u0004\b\u0000\u0010\n2\"\u0010\u0005\u001a\u0012\u0012\u000e\b\u0001\u0012\n\u0012\u0004\u0012\u0002H\n\u0018\u00010\u00060\u001a\"\n\u0012\u0004\u0012\u0002H\n\u0018\u00010\u0006\u00a2\u0006\u0002\u0010\u001b\u001a~\u0010\u001c\u001a\b\u0012\u0004\u0012\u0002H\f0\u0006\"\u0004\b\u0000\u0010\n\"\u0004\b\u0001\u0010\u000b\"\u0004\b\u0002\u0010\f2\u0006\u0010\u001d\u001a\u00020\u001e2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\n0\u00062\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u000b0\u00062\b\b\u0002\u0010\u0013\u001a\u00020\u00142(\u0010\u0015\u001a$\b\u0001\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\f0\u001f\u0012\u0006\u0012\u0004\u0018\u00010 0\u0018\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010!\u001aK\u0010\"\u001a\b\u0012\u0004\u0012\u0002H\n0\u0001\"\u0004\b\u0000\u0010\n2\u001e\u0010\u0005\u001a\u0010\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u0002H\n0\u00060\u001a\"\b\u0012\u0004\u0012\u0002H\n0\u00062\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010#\u001a\u00020\u0014\u00a2\u0006\u0002\u0010$\u001a:\u0010\"\u001a\b\u0012\u0004\u0012\u0002H\n0\u0001\"\u0004\b\u0000\u0010\n2\u0012\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\n0\u00060%2\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010#\u001a\u00020\u0014\u001ai\u0010&\u001a\b\u0012\u0004\u0012\u0002H\u000b0\u0001\"\u0004\b\u0000\u0010\n\"\u0004\b\u0001\u0010\u000b2\u0006\u0010\'\u001a\u0002H\u000b2\u001e\u0010\u0005\u001a\u0010\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u0002H\n0\u00060\u001a\"\b\u0012\u0004\u0012\u0002H\n0\u00062\b\b\u0002\u0010\u0013\u001a\u00020\u00142\u0018\u0010(\u001a\u0014\u0012\u0004\u0012\u0002H\u000b\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\u000b0\u0019\u00a2\u0006\u0002\u0010)\u001a\u001c\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\n0\u0001\"\u0004\b\u0000\u0010\n*\b\u0012\u0004\u0012\u0002H\n0\u0006\u001a0\u0010*\u001a\b\u0012\u0004\u0012\u0002H\n0\u0006\"\u0004\b\u0000\u0010\n*\b\u0012\u0004\u0012\u0002H\n0\u00062\u0012\u0010+\u001a\u000e\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u00020\u00140,\u001aT\u0010-\u001a\b\u0012\u0004\u0012\u0002H\n0\u0001\"\u0004\b\u0000\u0010\n*\b\u0012\u0004\u0012\u0002H\n0\u000626\u0010.\u001a2\u0012\u0013\u0012\u0011H\n\u00a2\u0006\f\b/\u0012\b\b0\u0012\u0004\b\b(1\u0012\u0013\u0012\u0011H\n\u00a2\u0006\f\b/\u0012\b\b0\u0012\u0004\b\b(2\u0012\u0004\u0012\u0002H\n0\u0019\u001a8\u00103\u001a\b\u0012\u0004\u0012\u0002H\u000b0\u0001\"\u0004\b\u0000\u0010\n\"\u0004\b\u0001\u0010\u000b*\b\u0012\u0004\u0012\u0002H\n0\u00062\u0014\u00104\u001a\u0010\u0012\u0004\u0012\u0002H\n\u0012\u0006\u0012\u0004\u0018\u0001H\u000b0,\u001aZ\u00105\u001a\b\u0012\u0004\u0012\u0002H\u000b0\u0001\"\u0004\b\u0000\u0010\n\"\u0004\b\u0001\u0010\u000b*\b\u0012\u0004\u0012\u0002H\n0\u00062\u0006\u0010\u001d\u001a\u00020\u001e2$\u00104\u001a \b\u0001\u0012\u0004\u0012\u0002H\n\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u0001H\u000b0\u001f\u0012\u0006\u0012\u0004\u0018\u00010 0\u0019H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0002\u00106\u001a:\u00107\u001a\b\u0012\u0004\u0012\u0002H\u000b0\u0006\"\u0004\b\u0000\u0010\n\"\u0004\b\u0001\u0010\u000b*\b\u0012\u0004\u0012\u0002H\n0\u00062\u0016\u00104\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u0001H\n\u0012\u0006\u0012\u0004\u0018\u0001H\u000b0,\u001aA\u00108\u001a\b\u0012\u0004\u0012\u0002H\n0\u0006\"\u0004\b\u0000\u0010\n*\b\u0012\u0004\u0012\u0002H\n0\u00062#\u00109\u001a\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\n0\u0006\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u00020:0\u0019\u00a2\u0006\u0002\b;\u001a$\u0010<\u001a\b\u0012\u0004\u0012\u0002H\n0\u0006\"\u0004\b\u0000\u0010\n*\b\u0012\u0004\u0012\u0002H\n0\u00062\u0006\u0010=\u001a\u00020>\u001aL\u0010?\u001a\b\u0012\u0004\u0012\u0002H\n0@\"\u0004\b\u0000\u0010\n*\b\u0012\u0004\u0012\u0002H\n0\u00062\u0006\u0010A\u001a\u00020\u001e2\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010B\u001a\u00020C2\b\b\u0002\u0010D\u001a\u00020E2\b\b\u0002\u0010F\u001a\u00020E\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006G"}, d2 = {"combineMap", "Landroidx/lifecycle/MediatorLiveData;", "", "Key", "Value", "sources", "Landroidx/lifecycle/LiveData;", "merge", "X", "S", "T", "U", "V", "W", "sourceA", "sourceB", "sourceC", "sourceD", "sourceE", "distinct", "", "merger", "Lkotlin/Function5;", "Lkotlin/Function4;", "Lkotlin/Function3;", "Lkotlin/Function2;", "", "([Landroidx/lifecycle/LiveData;)Landroidx/lifecycle/MediatorLiveData;", "mergeAsyncNotNull", "scope", "Lkotlinx/coroutines/CoroutineScope;", "Lkotlin/coroutines/Continuation;", "", "(Lkotlinx/coroutines/CoroutineScope;Landroidx/lifecycle/LiveData;Landroidx/lifecycle/LiveData;ZLkotlin/jvm/functions/Function3;)Landroidx/lifecycle/LiveData;", "mergeNotNull", "singleEvent", "([Landroidx/lifecycle/LiveData;ZZ)Landroidx/lifecycle/MediatorLiveData;", "", "scan", "initialState", "scanFunction", "(Ljava/lang/Object;[Landroidx/lifecycle/LiveData;ZLkotlin/jvm/functions/Function2;)Landroidx/lifecycle/MediatorLiveData;", "filter", "predicate", "Lkotlin/Function1;", "fold", "action", "Lkotlin/ParameterName;", "name", "previous", "current", "map", "mapper", "mapAsync", "(Landroidx/lifecycle/LiveData;Lkotlinx/coroutines/CoroutineScope;Lkotlin/jvm/functions/Function2;)Landroidx/lifecycle/MediatorLiveData;", "mapNullable", "perform", "block", "", "Lkotlin/ExtensionFunctionType;", "skip", "times", "", "throttle", "Lorg/wordpress/android/util/ThrottleLiveData;", "coroutineScope", "offset", "", "backgroundDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "mainDispatcher", "org.wordpress.android_wordpressJalapenoDebug"})
public final class LiveDataUtilsKt {
    
    /**
     * A helper function that merges sources into a single LiveData object
     * @param sources producing an item of the same type
     * @param distinct true if all the emitted items should be distinct
     * @param singleEvent is true when each item should be shown only once
     * @return merged results from all the sources
     */
    @org.jetbrains.annotations.NotNull()
    public static final <T extends java.lang.Object>androidx.lifecycle.MediatorLiveData<T> mergeNotNull(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LiveData<T>[] sources, boolean distinct, boolean singleEvent) {
        return null;
    }
    
    /**
     * A helper function that merges sources into a single LiveData object
     * @param sources producing an item of the same type
     * @param distinct true if all the emitted items should be distinct
     * @param singleEvent is true when each item should be shown only once
     * @return merged results from all the sources
     */
    @org.jetbrains.annotations.NotNull()
    public static final <T extends java.lang.Object>androidx.lifecycle.MediatorLiveData<T> mergeNotNull(@org.jetbrains.annotations.NotNull()
    java.lang.Iterable<? extends androidx.lifecycle.LiveData<T>> sources, boolean distinct, boolean singleEvent) {
        return null;
    }
    
    /**
     * Merges two LiveData sources using a given function. The function returns an object of a new type.
     * @param bgDispatcher context to run the merger function in
     * @param sourceA first source
     * @param sourceB second source
     * @return new data source
     */
    @org.jetbrains.annotations.NotNull()
    public static final <T extends java.lang.Object, U extends java.lang.Object, V extends java.lang.Object>androidx.lifecycle.LiveData<V> mergeAsyncNotNull(@org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.CoroutineScope scope, @org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LiveData<T> sourceA, @org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LiveData<U> sourceB, boolean distinct, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function3<? super T, ? super U, ? super kotlin.coroutines.Continuation<? super V>, ? extends java.lang.Object> merger) {
        return null;
    }
    
    /**
     * Merges two LiveData sources using a given function. The function returns an object of a new type.
     * @param sourceA first source
     * @param sourceB second source
     * @return new data source
     */
    @org.jetbrains.annotations.NotNull()
    public static final <T extends java.lang.Object, U extends java.lang.Object, V extends java.lang.Object>androidx.lifecycle.LiveData<V> merge(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LiveData<T> sourceA, @org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LiveData<U> sourceB, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function2<? super T, ? super U, ? extends V> merger) {
        return null;
    }
    
    /**
     * Merges LiveData sources using a given function. The function returns an object of a new type.
     * @param sources all source
     * @return new data source
     */
    @org.jetbrains.annotations.NotNull()
    public static final <T extends java.lang.Object>androidx.lifecycle.MediatorLiveData<T> merge(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LiveData<T>... sources) {
        return null;
    }
    
    /**
     * Merges three LiveData sources using a given function. The function returns an object of a new type.
     * @param sourceA first source
     * @param sourceB second source
     * @param sourceC third source
     * @return new data source
     */
    @org.jetbrains.annotations.NotNull()
    public static final <S extends java.lang.Object, T extends java.lang.Object, U extends java.lang.Object, V extends java.lang.Object>androidx.lifecycle.MediatorLiveData<V> merge(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LiveData<S> sourceA, @org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LiveData<T> sourceB, @org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LiveData<U> sourceC, boolean distinct, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function3<? super S, ? super T, ? super U, ? extends V> merger) {
        return null;
    }
    
    /**
     * Merges four LiveData sources using a given function. The function returns an object of a new type.
     * @param sourceA first source
     * @param sourceB second source
     * @param sourceC third source
     * @param sourceD fourth source
     * @return new data source
     */
    @org.jetbrains.annotations.NotNull()
    public static final <S extends java.lang.Object, T extends java.lang.Object, U extends java.lang.Object, V extends java.lang.Object, W extends java.lang.Object>androidx.lifecycle.LiveData<W> merge(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LiveData<S> sourceA, @org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LiveData<T> sourceB, @org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LiveData<U> sourceC, @org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LiveData<V> sourceD, boolean distinct, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function4<? super S, ? super T, ? super U, ? super V, ? extends W> merger) {
        return null;
    }
    
    /**
     * Merges five LiveData sources using a given function. The function returns an object of a new type.
     * @param sourceA first source
     * @param sourceB second source
     * @param sourceC third source
     * @param sourceD fourth source
     * @param sourceE fifth source
     * @return new data source
     */
    @org.jetbrains.annotations.NotNull()
    public static final <S extends java.lang.Object, T extends java.lang.Object, U extends java.lang.Object, V extends java.lang.Object, W extends java.lang.Object, X extends java.lang.Object>androidx.lifecycle.LiveData<X> merge(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LiveData<S> sourceA, @org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LiveData<T> sourceB, @org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LiveData<U> sourceC, @org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LiveData<V> sourceD, @org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LiveData<W> sourceE, boolean distinct, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function5<? super S, ? super T, ? super U, ? super V, ? super W, ? extends X> merger) {
        return null;
    }
    
    /**
     * Combines all the LiveData values in the given Map into one LiveData with the map of values.
     * @param sources is a map of all the live data sources in a map by a given key
     * @return one livedata instance that combines all the values into one map
     */
    @org.jetbrains.annotations.NotNull()
    public static final <Key extends java.lang.Object, Value extends java.lang.Object>androidx.lifecycle.MediatorLiveData<java.util.Map<Key, Value>> combineMap(@org.jetbrains.annotations.NotNull()
    java.util.Map<Key, ? extends androidx.lifecycle.LiveData<Value>> sources) {
        return null;
    }
    
    /**
     * Simple wrapper of the map utility method that is null safe
     */
    @org.jetbrains.annotations.NotNull()
    public static final <T extends java.lang.Object, U extends java.lang.Object>androidx.lifecycle.MediatorLiveData<U> map(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LiveData<T> $this$map, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super T, ? extends U> mapper) {
        return null;
    }
    
    /**
     * A wrapper of the map utility method that is null safe and runs the mapping on a background thread
     * @param scope defines the scope to run mapping in
     */
    @org.jetbrains.annotations.NotNull()
    @android.annotation.SuppressLint(value = {"NullSafeMutableLiveData"})
    public static final <T extends java.lang.Object, U extends java.lang.Object>androidx.lifecycle.MediatorLiveData<U> mapAsync(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LiveData<T> $this$mapAsync, @org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.CoroutineScope scope, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function2<? super T, ? super kotlin.coroutines.Continuation<? super U>, ? extends java.lang.Object> mapper) {
        return null;
    }
    
    /**
     * Calls the specified function [block] with `this` value as its receiver and returns new instance of LiveData.
     */
    @org.jetbrains.annotations.NotNull()
    public static final <T extends java.lang.Object>androidx.lifecycle.LiveData<T> perform(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LiveData<T> $this$perform, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function2<? super androidx.lifecycle.LiveData<T>, ? super T, kotlin.Unit> block) {
        return null;
    }
    
    /**
     * Simple wrapper of the map utility method that is null safe
     */
    @org.jetbrains.annotations.NotNull()
    public static final <T extends java.lang.Object, U extends java.lang.Object>androidx.lifecycle.LiveData<U> mapNullable(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LiveData<T> $this$mapNullable, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super T, ? extends U> mapper) {
        return null;
    }
    
    /**
     * This method ensures that the LiveData instance doesn't emit the same item twice
     */
    @org.jetbrains.annotations.NotNull()
    public static final <T extends java.lang.Object>androidx.lifecycle.MediatorLiveData<T> distinct(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LiveData<T> $this$distinct) {
        return null;
    }
    
    /**
     * This method folds previous and updated value in a result
     */
    @org.jetbrains.annotations.NotNull()
    public static final <T extends java.lang.Object>androidx.lifecycle.MediatorLiveData<T> fold(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LiveData<T> $this$fold, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function2<? super T, ? super T, ? extends T> action) {
        return null;
    }
    
    /**
     * Call this method if you want to throttle the LiveData emissions.
     * The default implementation takes only the last emitted result after 100ms.
     */
    @org.jetbrains.annotations.NotNull()
    public static final <T extends java.lang.Object>org.wordpress.android.util.ThrottleLiveData<T> throttle(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LiveData<T> $this$throttle, @org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.CoroutineScope coroutineScope, boolean distinct, long offset, @org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.CoroutineDispatcher backgroundDispatcher, @org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.CoroutineDispatcher mainDispatcher) {
        return null;
    }
    
    /**
     * A helper function that filters data and only emits what fits the predicate
     * @param predicate
     * @return filtered result
     */
    @org.jetbrains.annotations.NotNull()
    public static final <T extends java.lang.Object>androidx.lifecycle.LiveData<T> filter(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LiveData<T> $this$filter, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super T, java.lang.Boolean> predicate) {
        return null;
    }
    
    /**
     * Suppresses the first n items by this [LiveData].
     *
     * Consider this for example:
     *
     * ```
     * val connectionStatusLiveData = getConnectionStatusLiveData()
     * connectionStatusLiveData.skip(1).observe(this, Observer {
     *    refresh()
     * })
     * ```
     *
     * The first value emitted by `connectionStatusLiveData` would be ignored and [Observer] will not be called.
     */
    @org.jetbrains.annotations.NotNull()
    public static final <T extends java.lang.Object>androidx.lifecycle.LiveData<T> skip(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LiveData<T> $this$skip, int times) {
        return null;
    }
    
    /**
     * A helper function that scans sources into a single state
     * @param initialState the initial state passed into the scan function
     * @param sources producing partial states to be merged into a single state
     * @param distinct true if all the emitted items should be distinct
     * @param scanFunction merges the partial state into the single state
     * @return merged partial states into the single state
     */
    @org.jetbrains.annotations.NotNull()
    public static final <T extends java.lang.Object, U extends java.lang.Object>androidx.lifecycle.MediatorLiveData<U> scan(U initialState, @org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LiveData<T>[] sources, boolean distinct, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function2<? super U, ? super T, ? extends U> scanFunction) {
        return null;
    }
}