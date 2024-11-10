package org.wordpress.android.util.extensions;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 2, d1 = {"\u0000B\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0016\u0010\t\u001a\u00020\n*\u00020\u00022\b\b\u0001\u0010\u000b\u001a\u00020\nH\u0007\u001a\u0016\u0010\f\u001a\u00020\n*\u00020\u00022\b\b\u0001\u0010\u000b\u001a\u00020\nH\u0007\u001a\u0014\u0010\r\u001a\u00020\u000e*\u00020\u00022\b\b\u0001\u0010\u000b\u001a\u00020\n\u001a\u0014\u0010\u000f\u001a\u0004\u0018\u00010\u0010*\u00020\u00022\u0006\u0010\u0011\u001a\u00020\n\u001a\u0016\u0010\u0012\u001a\u00020\n*\u00020\u00022\b\b\u0001\u0010\u000b\u001a\u00020\nH\u0007\u001a\u0014\u0010\u0013\u001a\u0004\u0018\u00010\u0014*\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u0014\u001a6\u0010\u0016\u001a\u0004\u0018\u0001H\u0017\"\n\b\u0000\u0010\u0017\u0018\u0001*\u00020\u0018*\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u00142\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u00170\u001aH\u0086\b\u00a2\u0006\u0002\u0010\u001b\"\u0017\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00028F\u00a2\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0015\u0010\u0005\u001a\u00020\u0006*\u00020\u00028F\u00a2\u0006\u0006\u001a\u0004\b\u0007\u0010\b\u00a8\u0006\u001c"}, d2 = {"clipboardManager", "Landroid/content/ClipboardManager;", "Landroid/content/Context;", "getClipboardManager", "(Landroid/content/Context;)Landroid/content/ClipboardManager;", "currentLocale", "Ljava/util/Locale;", "getCurrentLocale", "(Landroid/content/Context;)Ljava/util/Locale;", "getColorFromAttribute", "", "attribute", "getColorResIdFromAttribute", "getColorStateListFromAttribute", "Landroid/content/res/ColorStateList;", "getDrawableFromAttribute", "Landroid/graphics/drawable/Drawable;", "attributeId", "getDrawableResIdFromAttribute", "getStringFromAsset", "", "assetFilename", "parseJsonFromAsset", "T", "", "modelClass", "Ljava/lang/Class;", "(Landroid/content/Context;Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;", "org.wordpress.android_wordpressJalapenoDebug"})
public final class ContextExtensionsKt {
    
    @androidx.annotation.ColorRes()
    public static final int getColorResIdFromAttribute(@org.jetbrains.annotations.NotNull()
    android.content.Context $this$getColorResIdFromAttribute, @androidx.annotation.AttrRes()
    int attribute) {
        return 0;
    }
    
    @androidx.annotation.ColorInt()
    public static final int getColorFromAttribute(@org.jetbrains.annotations.NotNull()
    android.content.Context $this$getColorFromAttribute, @androidx.annotation.AttrRes()
    int attribute) {
        return 0;
    }
    
    @androidx.annotation.DrawableRes()
    public static final int getDrawableResIdFromAttribute(@org.jetbrains.annotations.NotNull()
    android.content.Context $this$getDrawableResIdFromAttribute, @androidx.annotation.AttrRes()
    int attribute) {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final android.content.res.ColorStateList getColorStateListFromAttribute(@org.jetbrains.annotations.NotNull()
    android.content.Context $this$getColorStateListFromAttribute, @androidx.annotation.AttrRes()
    int attribute) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final java.util.Locale getCurrentLocale(@org.jetbrains.annotations.NotNull()
    android.content.Context $this$currentLocale) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public static final android.content.ClipboardManager getClipboardManager(@org.jetbrains.annotations.NotNull()
    android.content.Context $this$clipboardManager) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public static final android.graphics.drawable.Drawable getDrawableFromAttribute(@org.jetbrains.annotations.NotNull()
    android.content.Context $this$getDrawableFromAttribute, int attributeId) {
        return null;
    }
    
    /**
     * Reads an asset file as string
     * @param assetFilename the asset filename
     * @return the content of the asset file
     */
    @org.jetbrains.annotations.Nullable()
    public static final java.lang.String getStringFromAsset(@org.jetbrains.annotations.NotNull()
    android.content.Context $this$getStringFromAsset, @org.jetbrains.annotations.NotNull()
    java.lang.String assetFilename) {
        return null;
    }
}