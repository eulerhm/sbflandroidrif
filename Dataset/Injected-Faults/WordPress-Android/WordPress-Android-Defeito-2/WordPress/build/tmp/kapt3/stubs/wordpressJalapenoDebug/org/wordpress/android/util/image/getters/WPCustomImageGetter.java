package org.wordpress.android.util.image.getters;

import java.lang.System;

/**
 * ImageGetter for Html.fromHtml(). Retrieves images for HTML img tags using Glide library.
 *
 *
 * See {@link android.text.Html} for more details.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010#\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B!\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0007J\u001e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u0016H\u0002J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u0014\u0010\u0017\u001a\u0004\u0018\u00010\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016R\u001e\u0010\b\u001a\u00020\t8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00030\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001b"}, d2 = {"Lorg/wordpress/android/util/image/getters/WPCustomImageGetter;", "Landroid/text/Html$ImageGetter;", "textView", "Landroid/widget/TextView;", "maxWidth", "", "maxEmojiWidth", "(Landroid/widget/TextView;II)V", "imageManager", "Lorg/wordpress/android/util/image/ImageManager;", "getImageManager", "()Lorg/wordpress/android/util/image/ImageManager;", "setImageManager", "(Lorg/wordpress/android/util/image/ImageManager;)V", "targets", "Ljava/util/HashSet;", "Lorg/wordpress/android/util/image/getters/WPRemoteResourceViewTarget;", "Ljava/lang/ref/WeakReference;", "clear", "", "context", "Landroid/content/Context;", "", "getDrawable", "Landroid/graphics/drawable/Drawable;", "url", "", "org.wordpress.android_wordpressJalapenoDebug"})
public final class WPCustomImageGetter implements android.text.Html.ImageGetter {
    private final int maxWidth = 0;
    private final int maxEmojiWidth = 0;
    private final java.lang.ref.WeakReference<android.widget.TextView> textView = null;
    
    /**
     * We store all targets, so we can cancel any pending/ongoing requests when we want to load other content
     * into the TextView.
     */
    private final java.util.HashSet<org.wordpress.android.util.image.getters.WPRemoteResourceViewTarget> targets = null;
    @javax.inject.Inject()
    public org.wordpress.android.util.image.ImageManager imageManager;
    
    @kotlin.jvm.JvmOverloads()
    public WPCustomImageGetter(@org.jetbrains.annotations.NotNull()
    android.widget.TextView textView, int maxWidth) {
        super();
    }
    
    @kotlin.jvm.JvmOverloads()
    public WPCustomImageGetter(@org.jetbrains.annotations.NotNull()
    android.widget.TextView textView, int maxWidth, int maxEmojiWidth) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.util.image.ImageManager getImageManager() {
        return null;
    }
    
    public final void setImageManager(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.image.ImageManager p0) {
    }
    
    /**
     * Cancels all pending/ongoing requests.
     */
    private final void clear(android.widget.TextView textView) {
    }
    
    private final void clear(android.content.Context context, java.util.Set<org.wordpress.android.util.image.getters.WPRemoteResourceViewTarget> targets) {
    }
    
    /**
     * This method is called when the HTML parser encounters an
     * img tag.
     */
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public android.graphics.drawable.Drawable getDrawable(@org.jetbrains.annotations.Nullable()
    java.lang.String url) {
        return null;
    }
}