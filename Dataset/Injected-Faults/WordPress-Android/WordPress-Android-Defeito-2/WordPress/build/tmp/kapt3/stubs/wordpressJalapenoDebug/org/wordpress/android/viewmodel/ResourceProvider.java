package org.wordpress.android.viewmodel;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\u0006J\u0010\u0010\b\u001a\u00020\t2\b\b\u0001\u0010\n\u001a\u00020\u0006J\u000e\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u0006J\u0010\u0010\r\u001a\u00020\u00062\b\b\u0001\u0010\n\u001a\u00020\u0006J\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u0006J.\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\b\u0001\u0010\u0013\u001a\u00020\u00062\b\b\u0001\u0010\u0014\u001a\u00020\u00062\b\b\u0001\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u0006J\u0010\u0010\u0017\u001a\u00020\u00122\b\b\u0001\u0010\u0007\u001a\u00020\u0006J)\u0010\u0017\u001a\u00020\u00122\b\b\u0001\u0010\u0007\u001a\u00020\u00062\u0012\u0010\u0018\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u0019\"\u00020\u0001\u00a2\u0006\u0002\u0010\u001aJ\u0019\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00120\u00192\u0006\u0010\f\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u001cR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001d"}, d2 = {"Lorg/wordpress/android/viewmodel/ResourceProvider;", "", "contextProvider", "Lorg/wordpress/android/viewmodel/ContextProvider;", "(Lorg/wordpress/android/viewmodel/ContextProvider;)V", "getColor", "", "resourceId", "getDimension", "", "dimen", "getDimensionPixelOffset", "id", "getDimensionPixelSize", "getDrawable", "Landroid/graphics/drawable/Drawable;", "iconId", "getQuantityString", "", "zero", "one", "other", "quantity", "getString", "formatArgs", "", "(I[Ljava/lang/Object;)Ljava/lang/String;", "getStringArray", "(I)[Ljava/lang/String;", "org.wordpress.android_wordpressJalapenoDebug"})
public final class ResourceProvider {
    private final org.wordpress.android.viewmodel.ContextProvider contextProvider = null;
    
    @javax.inject.Inject()
    public ResourceProvider(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.ContextProvider contextProvider) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getString(@androidx.annotation.StringRes()
    int resourceId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getString(@androidx.annotation.StringRes()
    int resourceId, @org.jetbrains.annotations.NotNull()
    java.lang.Object... formatArgs) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String[] getStringArray(int id) {
        return null;
    }
    
    public final int getColor(@androidx.annotation.ColorRes()
    int resourceId) {
        return 0;
    }
    
    public final int getDimensionPixelSize(@androidx.annotation.DimenRes()
    int dimen) {
        return 0;
    }
    
    public final float getDimension(@androidx.annotation.DimenRes()
    int dimen) {
        return 0.0F;
    }
    
    /**
     * Formats the string for the given quantity, using the given arguments.
     * We need this because our translation platform doesn't support Android plurals.
     *
     * @param zero The desired string identifier to get when quantity is exactly 0
     * @param one The desired string identifier to get when quantity is exactly 1
     * @param other The desired string identifier to get when quantity is not (0 or 1)
     * @param quantity The number used to get the correct string
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getQuantityString(@androidx.annotation.StringRes()
    int zero, @androidx.annotation.StringRes()
    int one, @androidx.annotation.StringRes()
    int other, int quantity) {
        return null;
    }
    
    public final int getDimensionPixelOffset(int id) {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final android.graphics.drawable.Drawable getDrawable(int iconId) {
        return null;
    }
}