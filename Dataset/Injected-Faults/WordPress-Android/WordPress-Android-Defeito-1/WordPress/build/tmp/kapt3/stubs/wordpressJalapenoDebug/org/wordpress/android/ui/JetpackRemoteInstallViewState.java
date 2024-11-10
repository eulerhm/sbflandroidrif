package org.wordpress.android.ui;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0005\u001e\u001f !\"B_\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0007\u001a\u00020\u0005\u0012\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e\u00a2\u0006\u0002\u0010\u000fR\u0015\u0010\t\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0007\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0015\u0010\b\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0015\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0014R\u001a\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\r\u001a\u00020\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0014R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001d\u0082\u0001\u0004#$%&\u00a8\u0006\'"}, d2 = {"Lorg/wordpress/android/ui/JetpackRemoteInstallViewState;", "", "type", "Lorg/wordpress/android/ui/JetpackRemoteInstallViewState$Type;", "titleResource", "", "messageResource", "icon", "iconTint", "buttonResource", "onClick", "Lkotlin/Function0;", "", "progressBarVisible", "", "(Lorg/wordpress/android/ui/JetpackRemoteInstallViewState$Type;IIILjava/lang/Integer;Ljava/lang/Integer;Lkotlin/jvm/functions/Function0;Z)V", "getButtonResource", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getIcon", "()I", "getIconTint", "getMessageResource", "getOnClick", "()Lkotlin/jvm/functions/Function0;", "getProgressBarVisible", "()Z", "getTitleResource", "getType", "()Lorg/wordpress/android/ui/JetpackRemoteInstallViewState$Type;", "Error", "Installed", "Installing", "Start", "Type", "Lorg/wordpress/android/ui/JetpackRemoteInstallViewState$Start;", "Lorg/wordpress/android/ui/JetpackRemoteInstallViewState$Installing;", "Lorg/wordpress/android/ui/JetpackRemoteInstallViewState$Installed;", "Lorg/wordpress/android/ui/JetpackRemoteInstallViewState$Error;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class JetpackRemoteInstallViewState {
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.ui.JetpackRemoteInstallViewState.Type type = null;
    private final int titleResource = 0;
    private final int messageResource = 0;
    private final int icon = 0;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Integer iconTint = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Integer buttonResource = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function0<kotlin.Unit> onClick = null;
    private final boolean progressBarVisible = false;
    
    private JetpackRemoteInstallViewState(org.wordpress.android.ui.JetpackRemoteInstallViewState.Type type, @androidx.annotation.StringRes()
    int titleResource, @androidx.annotation.StringRes()
    int messageResource, @androidx.annotation.DrawableRes()
    int icon, @androidx.annotation.ColorRes()
    java.lang.Integer iconTint, @androidx.annotation.StringRes()
    java.lang.Integer buttonResource, kotlin.jvm.functions.Function0<kotlin.Unit> onClick, boolean progressBarVisible) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.JetpackRemoteInstallViewState.Type getType() {
        return null;
    }
    
    public final int getTitleResource() {
        return 0;
    }
    
    public final int getMessageResource() {
        return 0;
    }
    
    public final int getIcon() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getIconTint() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getButtonResource() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public kotlin.jvm.functions.Function0<kotlin.Unit> getOnClick() {
        return null;
    }
    
    public final boolean getProgressBarVisible() {
        return false;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\u0005J\u000f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\u0019\u0010\t\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u00d6\u0003J\t\u0010\u000e\u001a\u00020\u000fH\u00d6\u0001J\t\u0010\u0010\u001a\u00020\u0011H\u00d6\u0001R\u001a\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\u0012"}, d2 = {"Lorg/wordpress/android/ui/JetpackRemoteInstallViewState$Start;", "Lorg/wordpress/android/ui/JetpackRemoteInstallViewState;", "onClick", "Lkotlin/Function0;", "", "(Lkotlin/jvm/functions/Function0;)V", "getOnClick", "()Lkotlin/jvm/functions/Function0;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Start extends org.wordpress.android.ui.JetpackRemoteInstallViewState {
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function0<kotlin.Unit> onClick = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.JetpackRemoteInstallViewState.Start copy(@org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
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
        
        public Start(@org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
            super(null, 0, 0, 0, null, null, null, false);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function0<kotlin.Unit> component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public kotlin.jvm.functions.Function0<kotlin.Unit> getOnClick() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/JetpackRemoteInstallViewState$Installing;", "Lorg/wordpress/android/ui/JetpackRemoteInstallViewState;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Installing extends org.wordpress.android.ui.JetpackRemoteInstallViewState {
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.ui.JetpackRemoteInstallViewState.Installing INSTANCE = null;
        
        private Installing() {
            super(null, 0, 0, 0, null, null, null, false);
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\u0005J\u000f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\u0019\u0010\t\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u00d6\u0003J\t\u0010\u000e\u001a\u00020\u000fH\u00d6\u0001J\t\u0010\u0010\u001a\u00020\u0011H\u00d6\u0001R\u001a\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\u0012"}, d2 = {"Lorg/wordpress/android/ui/JetpackRemoteInstallViewState$Installed;", "Lorg/wordpress/android/ui/JetpackRemoteInstallViewState;", "onClick", "Lkotlin/Function0;", "", "(Lkotlin/jvm/functions/Function0;)V", "getOnClick", "()Lkotlin/jvm/functions/Function0;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Installed extends org.wordpress.android.ui.JetpackRemoteInstallViewState {
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function0<kotlin.Unit> onClick = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.JetpackRemoteInstallViewState.Installed copy(@org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
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
        
        public Installed(@org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
            super(null, 0, 0, 0, null, null, null, false);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function0<kotlin.Unit> component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public kotlin.jvm.functions.Function0<kotlin.Unit> getOnClick() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\u0005J\u000f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\u0019\u0010\t\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u00d6\u0003J\t\u0010\u000e\u001a\u00020\u000fH\u00d6\u0001J\t\u0010\u0010\u001a\u00020\u0011H\u00d6\u0001R\u001a\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\u0012"}, d2 = {"Lorg/wordpress/android/ui/JetpackRemoteInstallViewState$Error;", "Lorg/wordpress/android/ui/JetpackRemoteInstallViewState;", "onClick", "Lkotlin/Function0;", "", "(Lkotlin/jvm/functions/Function0;)V", "getOnClick", "()Lkotlin/jvm/functions/Function0;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Error extends org.wordpress.android.ui.JetpackRemoteInstallViewState {
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function0<kotlin.Unit> onClick = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.JetpackRemoteInstallViewState.Error copy(@org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
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
        
        public Error(@org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
            super(null, 0, 0, 0, null, null, null, false);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function0<kotlin.Unit> component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public kotlin.jvm.functions.Function0<kotlin.Unit> getOnClick() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/JetpackRemoteInstallViewState$Type;", "", "(Ljava/lang/String;I)V", "START", "INSTALLING", "INSTALLED", "ERROR", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum Type {
        /*public static final*/ START /* = new START() */,
        /*public static final*/ INSTALLING /* = new INSTALLING() */,
        /*public static final*/ INSTALLED /* = new INSTALLED() */,
        /*public static final*/ ERROR /* = new ERROR() */;
        
        Type() {
        }
    }
}