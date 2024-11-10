package org.wordpress.android.util;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\b\b\u0001\u0010\u0005\u001a\u00020\u00042\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\u0007J$\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\b\b\u0001\u0010\f\u001a\u00020\u00042\b\b\u0001\u0010\r\u001a\u00020\u0004H\u0007J$\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\b\b\u0001\u0010\f\u001a\u00020\u00042\b\b\u0001\u0010\r\u001a\u00020\u0004H\u0007\u00a8\u0006\u0012"}, d2 = {"Lorg/wordpress/android/util/ColorUtils;", "", "()V", "applyEmphasisToColor", "", "color", "emphasisAlpha", "", "applyTintToDrawable", "Landroid/graphics/drawable/Drawable;", "context", "Landroid/content/Context;", "drawableResId", "colorResId", "setImageResourceWithTint", "", "imageView", "Landroid/widget/ImageView;", "org.wordpress.android_wordpressJalapenoDebug"})
public final class ColorUtils {
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.util.ColorUtils INSTANCE = null;
    
    private ColorUtils() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @kotlin.jvm.JvmStatic()
    public static final android.graphics.drawable.Drawable applyTintToDrawable(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @androidx.annotation.DrawableRes()
    int drawableResId, @androidx.annotation.ColorRes()
    int colorResId) {
        return null;
    }
    
    @kotlin.jvm.JvmStatic()
    public static final void setImageResourceWithTint(@org.jetbrains.annotations.NotNull()
    android.widget.ImageView imageView, @androidx.annotation.DrawableRes()
    int drawableResId, @androidx.annotation.ColorRes()
    int colorResId) {
    }
    
    @androidx.annotation.ColorInt()
    @kotlin.jvm.JvmStatic()
    public static final int applyEmphasisToColor(@androidx.annotation.ColorInt()
    int color, @androidx.annotation.FloatRange(from = 0.0, to = 1.0)
    float emphasisAlpha) {
        return 0;
    }
}