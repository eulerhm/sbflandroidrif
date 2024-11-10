package org.wordpress.android.viewmodel.pages;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000  2\u00020\u0001:\u0002 !B\u0019\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0006\u0010\u0010\u001a\u00020\u0011J\u0010\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0014H\u0007J\u0010\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0016H\u0007J\u0019\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0018\u001a\u00020\u0019H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001aJ$\u0010\u001b\u001a\u0016\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u001c2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002R8\u0010\u0007\u001a,\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\t\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\n0\t0\f0\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\""}, d2 = {"Lorg/wordpress/android/viewmodel/pages/ActionPerformer;", "", "dispatcher", "Lorg/wordpress/android/fluxc/Dispatcher;", "defaultDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Lorg/wordpress/android/fluxc/Dispatcher;Lkotlinx/coroutines/CoroutineDispatcher;)V", "continuations", "", "Lkotlin/Pair;", "", "Lorg/wordpress/android/viewmodel/pages/ActionPerformer$PageAction$EventType;", "Lkotlin/coroutines/Continuation;", "", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "onCleanup", "", "onPostChange", "event", "Lorg/wordpress/android/fluxc/store/PostStore$OnPostChanged;", "onPostUploaded", "Lorg/wordpress/android/fluxc/store/PostStore$OnPostUploaded;", "performAction", "action", "Lorg/wordpress/android/viewmodel/pages/ActionPerformer$PageAction;", "(Lorg/wordpress/android/viewmodel/pages/ActionPerformer$PageAction;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "postCauseOfChangeToPostAction", "Lkotlin/Triple;", "", "postCauseOfChange", "Lorg/wordpress/android/fluxc/model/CauseOfOnPostChanged;", "Companion", "PageAction", "org.wordpress.android_wordpressJalapenoDebug"})
public final class ActionPerformer {
    private final org.wordpress.android.fluxc.Dispatcher dispatcher = null;
    private final kotlinx.coroutines.CoroutineDispatcher defaultDispatcher = null;
    private final kotlinx.coroutines.CoroutineScope coroutineScope = null;
    private java.util.Map<kotlin.Pair<java.lang.Long, org.wordpress.android.viewmodel.pages.ActionPerformer.PageAction.EventType>, kotlin.coroutines.Continuation<kotlin.Pair<java.lang.Boolean, java.lang.Long>>> continuations;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.viewmodel.pages.ActionPerformer.Companion Companion = null;
    private static final long ACTION_TIMEOUT = 10000L;
    
    @javax.inject.Inject()
    public ActionPerformer(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.Dispatcher dispatcher, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher defaultDispatcher) {
        super();
    }
    
    public final void onCleanup() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object performAction(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.pages.ActionPerformer.PageAction action, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @java.lang.SuppressWarnings(value = {"unused"})
    @org.greenrobot.eventbus.Subscribe(threadMode = org.greenrobot.eventbus.ThreadMode.MAIN)
    public final void onPostUploaded(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.PostStore.OnPostUploaded event) {
    }
    
    @java.lang.SuppressWarnings(value = {"unused"})
    @org.greenrobot.eventbus.Subscribe(threadMode = org.greenrobot.eventbus.ThreadMode.MAIN)
    public final void onPostChange(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.PostStore.OnPostChanged event) {
    }
    
    private final kotlin.Triple<java.lang.Long, java.lang.Integer, org.wordpress.android.viewmodel.pages.ActionPerformer.PageAction.EventType> postCauseOfChangeToPostAction(org.wordpress.android.fluxc.model.CauseOfOnPostChanged postCauseOfChange) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001:\u0001,B6\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u001c\u0010\u0006\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0007\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ\t\u0010 \u001a\u00020\u0003H\u00c6\u0003J\t\u0010!\u001a\u00020\u0005H\u00c6\u0003J\'\u0010\"\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0007H\u00c6\u0003\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0017JE\u0010#\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u001e\b\u0002\u0010\u0006\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0007H\u00c6\u0001\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010$J\u0013\u0010%\u001a\u00020&2\b\u0010\'\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010(\u001a\u00020)H\u00d6\u0001J\t\u0010*\u001a\u00020+H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\"\u0010\r\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u000eX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\"\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u000eX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0010\"\u0004\b\u0015\u0010\u0012R,\u0010\u0006\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0007\u00f8\u0001\u0000\u00a2\u0006\n\n\u0002\u0010\u0018\u001a\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\"\u0010\u001d\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u000eX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0010\"\u0004\b\u001f\u0010\u0012\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006-"}, d2 = {"Lorg/wordpress/android/viewmodel/pages/ActionPerformer$PageAction;", "", "remoteId", "", "event", "Lorg/wordpress/android/viewmodel/pages/ActionPerformer$PageAction$EventType;", "perform", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "", "(JLorg/wordpress/android/viewmodel/pages/ActionPerformer$PageAction$EventType;Lkotlin/jvm/functions/Function1;)V", "getEvent", "()Lorg/wordpress/android/viewmodel/pages/ActionPerformer$PageAction$EventType;", "onError", "Lkotlin/Function0;", "getOnError", "()Lkotlin/jvm/functions/Function0;", "setOnError", "(Lkotlin/jvm/functions/Function0;)V", "onSuccess", "getOnSuccess", "setOnSuccess", "getPerform", "()Lkotlin/jvm/functions/Function1;", "Lkotlin/jvm/functions/Function1;", "getRemoteId", "()J", "setRemoteId", "(J)V", "undo", "getUndo", "setUndo", "component1", "component2", "component3", "copy", "(JLorg/wordpress/android/viewmodel/pages/ActionPerformer$PageAction$EventType;Lkotlin/jvm/functions/Function1;)Lorg/wordpress/android/viewmodel/pages/ActionPerformer$PageAction;", "equals", "", "other", "hashCode", "", "toString", "", "EventType", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class PageAction {
        private long remoteId;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.viewmodel.pages.ActionPerformer.PageAction.EventType event = null;
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function1<kotlin.coroutines.Continuation<? super kotlin.Unit>, java.lang.Object> perform = null;
        @org.jetbrains.annotations.Nullable()
        private kotlin.jvm.functions.Function0<kotlin.Unit> onSuccess;
        @org.jetbrains.annotations.Nullable()
        private kotlin.jvm.functions.Function0<kotlin.Unit> onError;
        @org.jetbrains.annotations.Nullable()
        private kotlin.jvm.functions.Function0<kotlin.Unit> undo;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.viewmodel.pages.ActionPerformer.PageAction copy(long remoteId, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.viewmodel.pages.ActionPerformer.PageAction.EventType event, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super kotlin.coroutines.Continuation<? super kotlin.Unit>, ? extends java.lang.Object> perform) {
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
        
        public PageAction(long remoteId, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.viewmodel.pages.ActionPerformer.PageAction.EventType event, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super kotlin.coroutines.Continuation<? super kotlin.Unit>, ? extends java.lang.Object> perform) {
            super();
        }
        
        public final long component1() {
            return 0L;
        }
        
        public final long getRemoteId() {
            return 0L;
        }
        
        public final void setRemoteId(long p0) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.viewmodel.pages.ActionPerformer.PageAction.EventType component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.viewmodel.pages.ActionPerformer.PageAction.EventType getEvent() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function1<kotlin.coroutines.Continuation<? super kotlin.Unit>, java.lang.Object> component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function1<kotlin.coroutines.Continuation<? super kotlin.Unit>, java.lang.Object> getPerform() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final kotlin.jvm.functions.Function0<kotlin.Unit> getOnSuccess() {
            return null;
        }
        
        public final void setOnSuccess(@org.jetbrains.annotations.Nullable()
        kotlin.jvm.functions.Function0<kotlin.Unit> p0) {
        }
        
        @org.jetbrains.annotations.Nullable()
        public final kotlin.jvm.functions.Function0<kotlin.Unit> getOnError() {
            return null;
        }
        
        public final void setOnError(@org.jetbrains.annotations.Nullable()
        kotlin.jvm.functions.Function0<kotlin.Unit> p0) {
        }
        
        @org.jetbrains.annotations.Nullable()
        public final kotlin.jvm.functions.Function0<kotlin.Unit> getUndo() {
            return null;
        }
        
        public final void setUndo(@org.jetbrains.annotations.Nullable()
        kotlin.jvm.functions.Function0<kotlin.Unit> p0) {
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005\u00a8\u0006\u0006"}, d2 = {"Lorg/wordpress/android/viewmodel/pages/ActionPerformer$PageAction$EventType;", "", "(Ljava/lang/String;I)V", "UPLOAD", "UPDATE", "DELETE", "org.wordpress.android_wordpressJalapenoDebug"})
        public static enum EventType {
            /*public static final*/ UPLOAD /* = new UPLOAD() */,
            /*public static final*/ UPDATE /* = new UPDATE() */,
            /*public static final*/ DELETE /* = new DELETE() */;
            
            EventType() {
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lorg/wordpress/android/viewmodel/pages/ActionPerformer$Companion;", "", "()V", "ACTION_TIMEOUT", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}