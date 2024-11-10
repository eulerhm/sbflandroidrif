package org.wordpress.android.util;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0002 !B\u0082\u0001\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012<\b\u0002\u0010\u0006\u001a6\u0012\u0015\u0012\u0013\u0018\u00010\b\u00a2\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u00110\f\u00a2\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0007\u0012\'\b\u0002\u0010\u000f\u001a!\u0012\u0015\u0012\u0013\u0018\u00010\b\u00a2\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0010\u00a2\u0006\u0002\u0010\u0011J\u0006\u0010\u001e\u001a\u00020\u001fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013RK\u0010\u0006\u001a<\u00128\u00126\u0012\u0015\u0012\u0013\u0018\u00010\b\u00a2\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u00110\f\u00a2\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u00070\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R6\u0010\u000f\u001a\'\u0012#\u0012!\u0012\u0015\u0012\u0013\u0018\u00010\b\u00a2\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u00100\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0016R\u0011\u0010\u001a\u001a\u00020\u001b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001d\u00a8\u0006\""}, d2 = {"Lorg/wordpress/android/util/SnackbarItem;", "", "info", "Lorg/wordpress/android/util/SnackbarItem$Info;", "action", "Lorg/wordpress/android/util/SnackbarItem$Action;", "dismissCallback", "Lkotlin/Function2;", "Lcom/google/android/material/snackbar/Snackbar;", "Lkotlin/ParameterName;", "name", "transientBottomBar", "", "event", "", "showCallback", "Lkotlin/Function1;", "(Lorg/wordpress/android/util/SnackbarItem$Info;Lorg/wordpress/android/util/SnackbarItem$Action;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function1;)V", "getAction", "()Lorg/wordpress/android/util/SnackbarItem$Action;", "Ljava/lang/ref/SoftReference;", "getDismissCallback", "()Ljava/lang/ref/SoftReference;", "getInfo", "()Lorg/wordpress/android/util/SnackbarItem$Info;", "getShowCallback", "snackbarCallback", "Lcom/google/android/material/snackbar/Snackbar$Callback;", "getSnackbarCallback", "()Lcom/google/android/material/snackbar/Snackbar$Callback;", "getSnackbarDurationMs", "", "Action", "Info", "org.wordpress.android_wordpressJalapenoDebug"})
public final class SnackbarItem {
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.util.SnackbarItem.Info info = null;
    @org.jetbrains.annotations.Nullable()
    private final org.wordpress.android.util.SnackbarItem.Action action = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.ref.SoftReference<kotlin.jvm.functions.Function2<com.google.android.material.snackbar.Snackbar, java.lang.Integer, kotlin.Unit>> dismissCallback = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.ref.SoftReference<kotlin.jvm.functions.Function1<com.google.android.material.snackbar.Snackbar, kotlin.Unit>> showCallback = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.android.material.snackbar.Snackbar.Callback snackbarCallback = null;
    
    @kotlin.jvm.JvmOverloads()
    public SnackbarItem(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.SnackbarItem.Info info) {
        super();
    }
    
    @kotlin.jvm.JvmOverloads()
    public SnackbarItem(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.SnackbarItem.Info info, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.util.SnackbarItem.Action action) {
        super();
    }
    
    @kotlin.jvm.JvmOverloads()
    public SnackbarItem(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.SnackbarItem.Info info, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.util.SnackbarItem.Action action, @org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function2<? super com.google.android.material.snackbar.Snackbar, ? super java.lang.Integer, kotlin.Unit> dismissCallback) {
        super();
    }
    
    @kotlin.jvm.JvmOverloads()
    public SnackbarItem(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.SnackbarItem.Info info, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.util.SnackbarItem.Action action, @org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function2<? super com.google.android.material.snackbar.Snackbar, ? super java.lang.Integer, kotlin.Unit> dismissCallback, @org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function1<? super com.google.android.material.snackbar.Snackbar, kotlin.Unit> showCallback) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.util.SnackbarItem.Info getInfo() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final org.wordpress.android.util.SnackbarItem.Action getAction() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.ref.SoftReference<kotlin.jvm.functions.Function2<com.google.android.material.snackbar.Snackbar, java.lang.Integer, kotlin.Unit>> getDismissCallback() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.ref.SoftReference<kotlin.jvm.functions.Function1<com.google.android.material.snackbar.Snackbar, kotlin.Unit>> getShowCallback() {
        return null;
    }
    
    public final long getSnackbarDurationMs() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.google.android.material.snackbar.Snackbar.Callback getSnackbarCallback() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B)\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001f\u0010\u0002\u001a\u0010\u0012\f\u0012\n \u0011*\u0004\u0018\u00010\u00030\u00030\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013\u00a8\u0006\u0014"}, d2 = {"Lorg/wordpress/android/util/SnackbarItem$Info;", "", "view", "Landroid/view/View;", "textRes", "Lorg/wordpress/android/ui/utils/UiString;", "duration", "", "isImportant", "", "(Landroid/view/View;Lorg/wordpress/android/ui/utils/UiString;IZ)V", "getDuration", "()I", "()Z", "getTextRes", "()Lorg/wordpress/android/ui/utils/UiString;", "Ljava/lang/ref/WeakReference;", "kotlin.jvm.PlatformType", "getView", "()Ljava/lang/ref/WeakReference;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Info {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.utils.UiString textRes = null;
        private final int duration = 0;
        private final boolean isImportant = false;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.ref.WeakReference<android.view.View> view = null;
        
        @kotlin.jvm.JvmOverloads()
        public Info(@org.jetbrains.annotations.NotNull()
        android.view.View view, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiString textRes, int duration) {
            super();
        }
        
        @kotlin.jvm.JvmOverloads()
        public Info(@org.jetbrains.annotations.NotNull()
        android.view.View view, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiString textRes, int duration, boolean isImportant) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.UiString getTextRes() {
            return null;
        }
        
        public final int getDuration() {
            return 0;
        }
        
        public final boolean isImportant() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.ref.WeakReference<android.view.View> getView() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006R\u001f\u0010\u0004\u001a\u0010\u0012\f\u0012\n \b*\u0004\u0018\u00010\u00050\u00050\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f\u00a8\u0006\r"}, d2 = {"Lorg/wordpress/android/util/SnackbarItem$Action;", "", "textRes", "Lorg/wordpress/android/ui/utils/UiString;", "clickListener", "Landroid/view/View$OnClickListener;", "(Lorg/wordpress/android/ui/utils/UiString;Landroid/view/View$OnClickListener;)V", "Ljava/lang/ref/SoftReference;", "kotlin.jvm.PlatformType", "getClickListener", "()Ljava/lang/ref/SoftReference;", "getTextRes", "()Lorg/wordpress/android/ui/utils/UiString;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Action {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.utils.UiString textRes = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.ref.SoftReference<android.view.View.OnClickListener> clickListener = null;
        
        public Action(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiString textRes, @org.jetbrains.annotations.NotNull()
        android.view.View.OnClickListener clickListener) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.UiString getTextRes() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.ref.SoftReference<android.view.View.OnClickListener> getClickListener() {
            return null;
        }
    }
}