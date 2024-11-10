package org.wordpress.android.ui.utils;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J\"\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\b\u001a\u00020\tJ\u0016\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\u0016\u0010\u0010\u001a\u00020\u00112\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u0013J\u001f\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u00162\n\b\u0001\u0010\u0017\u001a\u0004\u0018\u00010\u000b\u00a2\u0006\u0002\u0010\u0018J\u0018\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0011J\u001f\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u001b2\n\b\u0001\u0010\u0017\u001a\u0004\u0018\u00010\u000b\u00a2\u0006\u0002\u0010\u001dJ\u0018\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u001b2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013J\u0016\u0010\u001e\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\t\u00a8\u0006 "}, d2 = {"Lorg/wordpress/android/ui/utils/UiHelpers;", "", "()V", "fadeInfadeOutViews", "", "firstView", "Landroid/view/View;", "secondView", "visible", "", "getPxOfUiDimen", "", "context", "Landroid/content/Context;", "uiDimen", "Lorg/wordpress/android/ui/utils/UiDimen;", "getTextOfUiString", "", "uiString", "Lorg/wordpress/android/ui/utils/UiString;", "setImageOrHide", "imageView", "Landroid/widget/ImageView;", "resId", "(Landroid/widget/ImageView;Ljava/lang/Integer;)V", "setTextOrHide", "view", "Landroid/widget/TextView;", "text", "(Landroid/widget/TextView;Ljava/lang/Integer;)V", "updateVisibility", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public final class UiHelpers {
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.utils.UiHelpers.Companion Companion = null;
    
    @javax.inject.Inject()
    public UiHelpers() {
        super();
    }
    
    public final int getPxOfUiDimen(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.utils.UiDimen uiDimen) {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.CharSequence getTextOfUiString(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.utils.UiString uiString) {
        return null;
    }
    
    public final void updateVisibility(@org.jetbrains.annotations.NotNull()
    android.view.View view, boolean visible) {
    }
    
    public final void setTextOrHide(@org.jetbrains.annotations.NotNull()
    android.widget.TextView view, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.ui.utils.UiString uiString) {
    }
    
    public final void setTextOrHide(@org.jetbrains.annotations.NotNull()
    android.widget.TextView view, @org.jetbrains.annotations.Nullable()
    @androidx.annotation.StringRes()
    java.lang.Integer resId) {
    }
    
    public final void setTextOrHide(@org.jetbrains.annotations.NotNull()
    android.widget.TextView view, @org.jetbrains.annotations.Nullable()
    java.lang.CharSequence text) {
    }
    
    public final void setImageOrHide(@org.jetbrains.annotations.NotNull()
    android.widget.ImageView imageView, @org.jetbrains.annotations.Nullable()
    @androidx.annotation.DrawableRes()
    java.lang.Integer resId) {
    }
    
    /**
     * Sets the [firstView] visible and the [secondView] invisible with a fade in/out animation and vice versa
     * @param visible if true the [firstView] is shown and the [secondView] is hidden else the other way round
     */
    public final void fadeInfadeOutViews(@org.jetbrains.annotations.Nullable()
    android.view.View firstView, @org.jetbrains.annotations.Nullable()
    android.view.View secondView, boolean visible) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/utils/UiHelpers$Companion;", "", "()V", "adjustDialogSize", "", "dialog", "Landroid/app/Dialog;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        public final void adjustDialogSize(@org.jetbrains.annotations.NotNull()
        android.app.Dialog dialog) {
        }
    }
}