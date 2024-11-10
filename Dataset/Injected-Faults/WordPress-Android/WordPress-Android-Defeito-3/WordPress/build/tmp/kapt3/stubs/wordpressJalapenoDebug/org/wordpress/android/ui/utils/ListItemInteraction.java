package org.wordpress.android.ui.utils;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\bf\u0018\u0000 \u00042\u00020\u0001:\u0003\u0004\u0005\u0006J\b\u0010\u0002\u001a\u00020\u0003H&\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/utils/ListItemInteraction;", "", "click", "", "Companion", "NoParams", "OneParam", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract interface ListItemInteraction {
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.utils.ListItemInteraction.Companion Companion = null;
    
    public abstract void click();
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B!\u0012\u0006\u0010\u0003\u001a\u00028\u0000\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\u0002\u0010\u0007J\b\u0010\r\u001a\u00020\u0006H\u0016J\u000e\u0010\u000e\u001a\u00028\u0000H\u00c6\u0003\u00a2\u0006\u0002\u0010\u000bJ\u0015\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0003J4\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0003\u001a\u00028\u00002\u0014\b\u0002\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0001\u00a2\u0006\u0002\u0010\u0011J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u00d6\u0003J\t\u0010\u0016\u001a\u00020\u0017H\u00d6\u0001J\t\u0010\u0018\u001a\u00020\u0019H\u00d6\u0001R\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0003\u001a\u00028\u0000\u00a2\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u001a"}, d2 = {"Lorg/wordpress/android/ui/utils/ListItemInteraction$OneParam;", "T", "Lorg/wordpress/android/ui/utils/ListItemInteraction;", "data", "action", "Lkotlin/Function1;", "", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)V", "getAction", "()Lkotlin/jvm/functions/Function1;", "getData", "()Ljava/lang/Object;", "Ljava/lang/Object;", "click", "component1", "component2", "copy", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)Lorg/wordpress/android/ui/utils/ListItemInteraction$OneParam;", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class OneParam<T extends java.lang.Object> implements org.wordpress.android.ui.utils.ListItemInteraction {
        private final T data = null;
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function1<T, kotlin.Unit> action = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.ListItemInteraction.OneParam<T> copy(T data, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super T, kotlin.Unit> action) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.lang.String toString() {
            return null;
        }
        
        public OneParam(T data, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super T, kotlin.Unit> action) {
            super();
        }
        
        public final T component1() {
            return null;
        }
        
        public final T getData() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function1<T, kotlin.Unit> component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function1<T, kotlin.Unit> getAction() {
            return null;
        }
        
        @java.lang.Override()
        public void click() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\u0005J\b\u0010\b\u001a\u00020\u0004H\u0016J\u000f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\u0019\u0010\n\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u00d6\u0003J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001J\t\u0010\u0011\u001a\u00020\u0012H\u00d6\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\u0013"}, d2 = {"Lorg/wordpress/android/ui/utils/ListItemInteraction$NoParams;", "Lorg/wordpress/android/ui/utils/ListItemInteraction;", "action", "Lkotlin/Function0;", "", "(Lkotlin/jvm/functions/Function0;)V", "getAction", "()Lkotlin/jvm/functions/Function0;", "click", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class NoParams implements org.wordpress.android.ui.utils.ListItemInteraction {
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function0<kotlin.Unit> action = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.ListItemInteraction.NoParams copy(@org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function0<kotlin.Unit> action) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.lang.String toString() {
            return null;
        }
        
        public NoParams(@org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function0<kotlin.Unit> action) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function0<kotlin.Unit> component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function0<kotlin.Unit> getAction() {
            return null;
        }
        
        @java.lang.Override()
        public void click() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0014\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006J-\u0010\u0003\u001a\u00020\u0004\"\u0004\b\u0000\u0010\b2\u0006\u0010\t\u001a\u0002H\b2\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u00020\u00070\n\u00a2\u0006\u0002\u0010\u000b\u00a8\u0006\f"}, d2 = {"Lorg/wordpress/android/ui/utils/ListItemInteraction$Companion;", "", "()V", "create", "Lorg/wordpress/android/ui/utils/ListItemInteraction;", "action", "Lkotlin/Function0;", "", "T", "data", "Lkotlin/Function1;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)Lorg/wordpress/android/ui/utils/ListItemInteraction;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        /**
         * Use this creator only with function pointer. If you use it with a lambda, the created `ListItemInteraction`
         * equals method will always fail because two lambdas are never equal. If you need a parametrized function call,
         * use the other creator method.
         */
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.ListItemInteraction create(@org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function0<kotlin.Unit> action) {
            return null;
        }
        
        /**
         * Use this creator only with function pointer. If you use it with a lambda, the created `ListItemInteraction`
         * equals method will always fail because two lambdas are never equal.
         */
        @org.jetbrains.annotations.NotNull()
        public final <T extends java.lang.Object>org.wordpress.android.ui.utils.ListItemInteraction create(T data, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super T, kotlin.Unit> action) {
            return null;
        }
    }
}