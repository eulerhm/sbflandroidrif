package org.wordpress.android.ui.posts.services;

import java.lang.System;

/**
 * Class which retrieves images from both local storage and remote url and informs the client about the progress
 * using Html.ImageGetter.Callbacks.
 *
 * Since Aztec doesn't allow directly setting an image into a View a custom Target which we load
 * the image into needs to be created.
 *
 * It's recommended to explicitly call clearTargets() method, so any resources may be cleared and all pending requests
 * canceled when the screen is not visible to the user anymore. Each target must be stored in a WeakReference,
 * so targets which are not used anymore don't leak.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0006\u0010\u0010\u001a\u00020\u0011J\u0006\u0010\u0012\u001a\u00020\u0013J \u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0013H\u0016J(\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\u0013H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0\r0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001b"}, d2 = {"Lorg/wordpress/android/ui/posts/services/AztecImageLoader;", "Lorg/wordpress/aztec/Html$ImageGetter;", "context", "Landroid/content/Context;", "imageManager", "Lorg/wordpress/android/util/image/ImageManager;", "loadingInProgress", "Landroid/graphics/drawable/Drawable;", "(Landroid/content/Context;Lorg/wordpress/android/util/image/ImageManager;Landroid/graphics/drawable/Drawable;)V", "mRequestsInProgress", "Ljava/util/ArrayList;", "", "targets", "Ljava/lang/ref/WeakReference;", "Lcom/bumptech/glide/request/target/BaseTarget;", "Landroid/graphics/Bitmap;", "clearTargets", "", "getNumberOfImagesBeingDownloaded", "", "loadImage", "url", "callbacks", "Lorg/wordpress/aztec/Html$ImageGetter$Callbacks;", "maxWidth", "maxSize", "minWidth", "org.wordpress.android_wordpressJalapenoDebug"})
public final class AztecImageLoader implements org.wordpress.aztec.Html.ImageGetter {
    private final android.content.Context context = null;
    private final org.wordpress.android.util.image.ImageManager imageManager = null;
    private final android.graphics.drawable.Drawable loadingInProgress = null;
    private final java.util.ArrayList<java.lang.ref.WeakReference<com.bumptech.glide.request.target.BaseTarget<android.graphics.Bitmap>>> targets = null;
    private final java.util.ArrayList<java.lang.String> mRequestsInProgress = null;
    
    public AztecImageLoader(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.image.ImageManager imageManager, @org.jetbrains.annotations.NotNull()
    android.graphics.drawable.Drawable loadingInProgress) {
        super();
    }
    
    @java.lang.Override()
    public void loadImage(@org.jetbrains.annotations.NotNull()
    java.lang.String url, @org.jetbrains.annotations.NotNull()
    org.wordpress.aztec.Html.ImageGetter.Callbacks callbacks, int maxWidth) {
    }
    
    @java.lang.Override()
    public void loadImage(@org.jetbrains.annotations.NotNull()
    java.lang.String url, @org.jetbrains.annotations.NotNull()
    org.wordpress.aztec.Html.ImageGetter.Callbacks callbacks, int maxSize, int minWidth) {
    }
    
    /**
     * Cancel any pending requests and free any resources so they may be reused.
     */
    public final void clearTargets() {
    }
    
    public final int getNumberOfImagesBeingDownloaded() {
        return 0;
    }
}