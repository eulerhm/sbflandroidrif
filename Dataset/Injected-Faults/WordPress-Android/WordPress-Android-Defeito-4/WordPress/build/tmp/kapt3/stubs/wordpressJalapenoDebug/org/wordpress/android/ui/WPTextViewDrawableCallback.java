package org.wordpress.android.ui;

import java.lang.System;

/**
 * Class extends Drawable.Callback, so we can support animated drawables (gifs).
 * Upon retrieving a drawable, call Drawable.setCallback(...).
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J \u0010\u000b\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0018\u0010\u0010\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\rH\u0016R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/WPTextViewDrawableCallback;", "Landroid/graphics/drawable/Drawable$Callback;", "textView", "Landroid/widget/TextView;", "(Landroid/widget/TextView;)V", "weakView", "Ljava/lang/ref/WeakReference;", "invalidateDrawable", "", "who", "Landroid/graphics/drawable/Drawable;", "scheduleDrawable", "what", "Ljava/lang/Runnable;", "when", "", "unscheduleDrawable", "org.wordpress.android_wordpressJalapenoDebug"})
public final class WPTextViewDrawableCallback implements android.graphics.drawable.Drawable.Callback {
    private final java.lang.ref.WeakReference<android.widget.TextView> weakView = null;
    
    public WPTextViewDrawableCallback(@org.jetbrains.annotations.NotNull()
    android.widget.TextView textView) {
        super();
    }
    
    @java.lang.Override()
    public void invalidateDrawable(@org.jetbrains.annotations.NotNull()
    android.graphics.drawable.Drawable who) {
    }
    
    @java.lang.Override()
    public void scheduleDrawable(@org.jetbrains.annotations.NotNull()
    android.graphics.drawable.Drawable who, @org.jetbrains.annotations.NotNull()
    java.lang.Runnable what, long when) {
    }
    
    @java.lang.Override()
    public void unscheduleDrawable(@org.jetbrains.annotations.NotNull()
    android.graphics.drawable.Drawable who, @org.jetbrains.annotations.NotNull()
    java.lang.Runnable what) {
    }
}