package org.wordpress.android.util.image.getters;

import java.lang.System;

/**
 * A class that we can load a remote resource into. Automatically displays placeholder while the remote img is
 * loading and displays an error image if the loading fails.
 *
 * We could probably subclass BaseTarget instead of ViewTarget, since we basically override most of its functionality.
 * However, we might want to use ViewTarget.clearOnDetach(..) when it becomes stable (it's experimental now).
 * It clears the View's Request when the View is detached from its Window and restarts the Request when the View is
 * re-attached from its Window.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001\u001fB\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\n\u0010\u000f\u001a\u0004\u0018\u00010\u000eH\u0016J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0017J\u0012\u0010\u0014\u001a\u00020\u00112\b\u0010\u0015\u001a\u0004\u0018\u00010\u0003H\u0016J\u0012\u0010\u0016\u001a\u00020\u00112\b\u0010\u0017\u001a\u0004\u0018\u00010\u0003H\u0016J\"\u0010\u0018\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u00032\u0010\u0010\u0019\u001a\f\u0012\u0006\b\u0000\u0012\u00020\u0003\u0018\u00010\u001aH\u0016J\u0018\u0010\u001b\u001a\u00020\u00112\u0006\u0010\b\u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00020\u001dH\u0002J\u0012\u0010\u001e\u001a\u00020\u00112\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016R\u0011\u0010\b\u001a\u00020\u00038F\u00a2\u0006\u0006\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006 "}, d2 = {"Lorg/wordpress/android/util/image/getters/WPRemoteResourceViewTarget;", "Lcom/bumptech/glide/request/target/ViewTarget;", "Landroid/widget/TextView;", "Landroid/graphics/drawable/Drawable;", "view", "maxSize", "", "(Landroid/widget/TextView;I)V", "drawable", "getDrawable", "()Landroid/graphics/drawable/Drawable;", "drawableWrapper", "Lorg/wordpress/android/util/image/getters/WPRemoteResourceViewTarget$RemoteDrawableWrapper;", "request", "Lcom/bumptech/glide/request/Request;", "getRequest", "getSize", "", "cb", "Lcom/bumptech/glide/request/target/SizeReadyCallback;", "onLoadFailed", "errorDrawable", "onLoadStarted", "res", "onResourceReady", "transition", "Lcom/bumptech/glide/request/transition/Transition;", "replaceDrawable", "bounds", "Landroid/graphics/Rect;", "setRequest", "RemoteDrawableWrapper", "org.wordpress.android_wordpressJalapenoDebug"})
public final class WPRemoteResourceViewTarget extends com.bumptech.glide.request.target.ViewTarget<android.widget.TextView, android.graphics.drawable.Drawable> {
    private final int maxSize = 0;
    private final org.wordpress.android.util.image.getters.WPRemoteResourceViewTarget.RemoteDrawableWrapper drawableWrapper = null;
    private com.bumptech.glide.request.Request request;
    
    public WPRemoteResourceViewTarget(@org.jetbrains.annotations.NotNull()
    android.widget.TextView view, int maxSize) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.graphics.drawable.Drawable getDrawable() {
        return null;
    }
    
    @java.lang.Override()
    public void onResourceReady(@org.jetbrains.annotations.NotNull()
    android.graphics.drawable.Drawable res, @org.jetbrains.annotations.Nullable()
    com.bumptech.glide.request.transition.Transition<? super android.graphics.drawable.Drawable> transition) {
    }
    
    @java.lang.Override()
    public void onLoadFailed(@org.jetbrains.annotations.Nullable()
    android.graphics.drawable.Drawable errorDrawable) {
    }
    
    @java.lang.Override()
    public void onLoadStarted(@org.jetbrains.annotations.Nullable()
    android.graphics.drawable.Drawable res) {
    }
    
    private final void replaceDrawable(android.graphics.drawable.Drawable drawable, android.graphics.Rect bounds) {
    }
    
    /**
     * Since this target can be used for loading multiple images into a single TextView, we can't use the default
     * implementation which supports only one request per view. On the other hand, by using field to store the request
     * we lose the ability to clear previous requests if the client creates new instance of the
     * WPRemoteResourceViewTarget for the new request on the same view. Canceling any previous requests for the same
     * View must be handled by the client (see WPCustomImageGetter.clear(..) methods for reference).
     */
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public com.bumptech.glide.request.Request getRequest() {
        return null;
    }
    
    @java.lang.Override()
    public void setRequest(@org.jetbrains.annotations.Nullable()
    com.bumptech.glide.request.Request request) {
    }
    
    /**
     * We don't want to call super, since it determines the size from the size of the View. But this target may be used
     * for loading multiple images into a single View.
     */
    @android.annotation.SuppressLint(value = {"MissingSuperCall"})
    @java.lang.Override()
    public void getSize(@org.jetbrains.annotations.NotNull()
    com.bumptech.glide.request.target.SizeReadyCallback cb) {
    }
    
    /**
     * Drawable wrapper so we can replace placeholder with remote/error resource, after the requests finishes.
     *
     * We need to synchronously return drawable in WPCustomImageGetter.getDrawable(...).
     * If we return regular drawable - let's say a placeholder, we won't be able to replace it with the actual image
     * ==> This wrapper just adds us ability to change the content of the drawable after the asynchronous call finishes.
     */
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\rH\u0016J\u0010\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\rH\u0016J\u0010\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0012\u0010\u0013\u001a\u00020\t2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J\u000e\u0010\u0016\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\u0001R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0001X\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007\u00a8\u0006\u0017"}, d2 = {"Lorg/wordpress/android/util/image/getters/WPRemoteResourceViewTarget$RemoteDrawableWrapper;", "Landroid/graphics/drawable/Drawable;", "()V", "drawable", "getDrawable$org_wordpress_android_wordpressJalapenoDebug", "()Landroid/graphics/drawable/Drawable;", "setDrawable$org_wordpress_android_wordpressJalapenoDebug", "(Landroid/graphics/drawable/Drawable;)V", "draw", "", "canvas", "Landroid/graphics/Canvas;", "getOpacity", "", "setAlpha", "alpha", "setBounds", "bounds", "Landroid/graphics/Rect;", "setColorFilter", "colorFilter", "Landroid/graphics/ColorFilter;", "setDrawable", "org.wordpress.android_wordpressJalapenoDebug"})
    static final class RemoteDrawableWrapper extends android.graphics.drawable.Drawable {
        @org.jetbrains.annotations.Nullable()
        private android.graphics.drawable.Drawable drawable;
        
        public RemoteDrawableWrapper() {
            super();
        }
        
        @org.jetbrains.annotations.Nullable()
        public final android.graphics.drawable.Drawable getDrawable$org_wordpress_android_wordpressJalapenoDebug() {
            return null;
        }
        
        public final void setDrawable$org_wordpress_android_wordpressJalapenoDebug(@org.jetbrains.annotations.Nullable()
        android.graphics.drawable.Drawable p0) {
        }
        
        public final void setDrawable(@org.jetbrains.annotations.NotNull()
        android.graphics.drawable.Drawable drawable) {
        }
        
        @java.lang.Override()
        public void draw(@org.jetbrains.annotations.NotNull()
        android.graphics.Canvas canvas) {
        }
        
        @java.lang.Override()
        public void setAlpha(int alpha) {
        }
        
        @java.lang.Override()
        public void setColorFilter(@org.jetbrains.annotations.Nullable()
        android.graphics.ColorFilter colorFilter) {
        }
        
        @java.lang.Override()
        public int getOpacity() {
            return 0;
        }
        
        @java.lang.Override()
        public void setBounds(@org.jetbrains.annotations.NotNull()
        android.graphics.Rect bounds) {
        }
    }
}