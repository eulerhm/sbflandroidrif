package org.wordpress.android.util;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B!\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0006\u0010\u0015\u001a\u00020\u0016J\u000e\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u0014J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\u001a\u0010\u001d\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u0018\u001a\u00020\u0014H\u0002J\u0011\u0010 \u001a\u00020\u0016H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010!R\u0014\u0010\t\u001a\u00020\n8VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0018\u0010\u000f\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0011\u0018\u00010\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\""}, d2 = {"Lorg/wordpress/android/util/SnackbarSequencer;", "Lkotlinx/coroutines/CoroutineScope;", "uiHelper", "Lorg/wordpress/android/ui/utils/UiHelpers;", "wpSnackbarWrapper", "Lorg/wordpress/android/widgets/WPSnackbarWrapper;", "mainDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Lorg/wordpress/android/ui/utils/UiHelpers;Lorg/wordpress/android/widgets/WPSnackbarWrapper;Lkotlinx/coroutines/CoroutineDispatcher;)V", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "job", "Lkotlinx/coroutines/Job;", "lastSnackBarReference", "Ljava/lang/ref/SoftReference;", "Lcom/google/android/material/snackbar/Snackbar;", "snackBarQueue", "Ljava/util/Queue;", "Lorg/wordpress/android/util/SnackbarItem;", "dismissLastSnackbar", "", "enqueue", "item", "isContextAlive", "", "activity", "Landroid/app/Activity;", "prepareSnackBar", "context", "Landroid/content/Context;", "start", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "org.wordpress.android_wordpressJalapenoDebug"})
@javax.inject.Singleton()
public final class SnackbarSequencer implements kotlinx.coroutines.CoroutineScope {
    private final org.wordpress.android.ui.utils.UiHelpers uiHelper = null;
    private final org.wordpress.android.widgets.WPSnackbarWrapper wpSnackbarWrapper = null;
    private final kotlinx.coroutines.CoroutineDispatcher mainDispatcher = null;
    private kotlinx.coroutines.Job job;
    private final java.util.Queue<org.wordpress.android.util.SnackbarItem> snackBarQueue = null;
    private java.lang.ref.SoftReference<com.google.android.material.snackbar.Snackbar> lastSnackBarReference;
    
    @javax.inject.Inject()
    public SnackbarSequencer(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.utils.UiHelpers uiHelper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.widgets.WPSnackbarWrapper wpSnackbarWrapper, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "UI_THREAD")
    kotlinx.coroutines.CoroutineDispatcher mainDispatcher) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public kotlin.coroutines.CoroutineContext getCoroutineContext() {
        return null;
    }
    
    public final void enqueue(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.SnackbarItem item) {
    }
    
    private final java.lang.Object start(kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    private final boolean isContextAlive(android.app.Activity activity) {
        return false;
    }
    
    private final com.google.android.material.snackbar.Snackbar prepareSnackBar(android.content.Context context, org.wordpress.android.util.SnackbarItem item) {
        return null;
    }
    
    public final void dismissLastSnackbar() {
    }
}