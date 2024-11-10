package org.wordpress.android.util.image;

import java.lang.System;

/**
 * Singleton for asynchronous image fetching/loading with support for placeholders, transformations and more.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u009a\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0007\u0018\u0000 Q2\u00020\u0001:\u0002QRB\u0019\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\u0006J(\u0010\u0007\u001a\u00020\b\"\b\b\u0000\u0010\t*\u00020\u00012\u0006\u0010\n\u001a\u00020\u000b2\u000e\u0010\f\u001a\n\u0012\u0004\u0012\u0002H\t\u0018\u00010\rJ\u000e\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u0010J\"\u0010\u0011\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u0015H\u0007J\"\u0010\u0011\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u0014\u001a\u00020\u0015H\u0007J$\u0010\u0011\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u00102\b\b\u0001\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u0014\u001a\u00020\u0015H\u0007J,\u0010\u0011\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u0014\u001a\u00020\u0015H\u0007JQ\u0010\u0011\u001a\u00020\b2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u0014\u001a\u00020\u00152\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u0019H\u0007\u00a2\u0006\u0002\u0010\"J.\u0010#\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00130\r2\u0006\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u0014\u001a\u00020\u0015JC\u0010$\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010%\u001a\u00020\u001d2\u0010\b\u0002\u0010&\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\'2\n\b\u0002\u0010(\u001a\u0004\u0018\u00010\u0019H\u0007\u00a2\u0006\u0002\u0010)J:\u0010*\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010+\u001a\u00020\u00192\u0010\b\u0002\u0010&\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\'H\u0007JC\u0010,\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0010\b\u0002\u0010&\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\'2\n\b\u0002\u0010(\u001a\u0004\u0018\u00010\u0019H\u0007\u00a2\u0006\u0002\u0010)J*\u0010-\u001a\u00020\b2\u0012\u0010.\u001a\u000e\u0012\u0004\u0012\u000200\u0012\u0004\u0012\u00020\u00170/2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dJ\u001c\u00101\u001a\u00020\b2\u0006\u00102\u001a\u0002032\f\u0010&\u001a\b\u0012\u0004\u0012\u0002040\'J>\u00105\u001a\u00020\b2\u0006\u00106\u001a\u0002072\u0006\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u00108\u001a\u00020\u001d2\b\b\u0002\u0010\u0014\u001a\u00020\u00152\u0010\b\u0002\u0010&\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\'H\u0007J$\u00109\u001a\u00020\b2\u0006\u0010:\u001a\u00020\u00102\u0006\u0010;\u001a\u00020<2\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00170\'JB\u00109\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u00102\u001a\u0002032\b\b\u0002\u0010\u0014\u001a\u00020\u00152\n\b\u0002\u0010=\u001a\u0004\u0018\u00010\u001d2\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00170\'JB\u00109\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u0014\u001a\u00020\u00152\n\b\u0002\u0010=\u001a\u0004\u0018\u00010\u001d2\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00170\'J\u0016\u0010>\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010;\u001a\u00020<J#\u0010?\u001a\u00020@\"\b\b\u0000\u0010\t*\u00020\u0001*\u00020@2\u0006\u0010\u001a\u001a\u00020\u001bH\u0002\u00a2\u0006\u0002\u0010AJ#\u0010B\u001a\u00020@\"\b\b\u0000\u0010\t*\u00020\u0001*\u00020@2\u0006\u0010\u001a\u001a\u00020\u001bH\u0002\u00a2\u0006\u0002\u0010AJ%\u0010C\u001a\u00020@\"\b\b\u0000\u0010\t*\u00020\u0001*\u00020@2\b\u0010D\u001a\u0004\u0018\u00010\u0019H\u0002\u00a2\u0006\u0002\u0010EJ1\u0010F\u001a\u00020@*\u00020@2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010=\u001a\u0004\u0018\u00010\u001d2\f\u0010G\u001a\b\u0012\u0004\u0012\u00020\u00170\'H\u0002\u00a2\u0006\u0002\u0010HJ#\u0010I\u001a\u00020@\"\b\b\u0000\u0010\t*\u00020\u0001*\u00020@2\u0006\u0010\u0014\u001a\u00020\u0015H\u0002\u00a2\u0006\u0002\u0010JJ/\u0010K\u001a\u00020@\"\b\b\u0000\u0010\t*\u00020\u0001*\u00020@2\b\u0010 \u001a\u0004\u0018\u00010\u00192\b\u0010!\u001a\u0004\u0018\u00010\u0019H\u0002\u00a2\u0006\u0002\u0010LJ+\u0010M\u001a\u00020@\"\b\b\u0000\u0010\t*\u00020\u0001*\u00020@2\u000e\u0010&\u001a\n\u0012\u0004\u0012\u0002H\t\u0018\u00010\'H\u0002\u00a2\u0006\u0002\u0010NJ\u000e\u0010O\u001a\u00020P*\u0004\u0018\u00010\u000bH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006S"}, d2 = {"Lorg/wordpress/android/util/image/ImageManager;", "", "placeholderManager", "Lorg/wordpress/android/util/image/ImagePlaceholderManager;", "videoLoader", "Lorg/wordpress/android/ui/media/VideoLoader;", "(Lorg/wordpress/android/util/image/ImagePlaceholderManager;Lorg/wordpress/android/ui/media/VideoLoader;)V", "cancelRequest", "", "T", "context", "Landroid/content/Context;", "target", "Lcom/bumptech/glide/request/target/BaseTarget;", "cancelRequestAndClearImageView", "imageView", "Landroid/widget/ImageView;", "load", "bitmap", "Landroid/graphics/Bitmap;", "scaleType", "Landroid/widget/ImageView$ScaleType;", "drawable", "Landroid/graphics/drawable/Drawable;", "resourceId", "", "imageType", "Lorg/wordpress/android/util/image/ImageType;", "imgUrl", "", "awt", "Lcom/bumptech/glide/request/target/AppWidgetTarget;", "width", "height", "(Lcom/bumptech/glide/request/target/AppWidgetTarget;Landroid/content/Context;Lorg/wordpress/android/util/image/ImageType;Ljava/lang/String;Landroid/widget/ImageView$ScaleType;Ljava/lang/Integer;Ljava/lang/Integer;)V", "loadAsBitmapIntoCustomTarget", "loadBase64IntoCircle", "base64ImageData", "requestListener", "Lorg/wordpress/android/util/image/ImageManager$RequestListener;", "version", "(Landroid/widget/ImageView;Lorg/wordpress/android/util/image/ImageType;Ljava/lang/String;Lorg/wordpress/android/util/image/ImageManager$RequestListener;Ljava/lang/Integer;)V", "loadImageWithCorners", "cornerRadius", "loadIntoCircle", "loadIntoCustomTarget", "viewTarget", "Lcom/bumptech/glide/request/target/ViewTarget;", "Landroid/widget/TextView;", "loadIntoFileWithResultListener", "imgUri", "Landroid/net/Uri;", "Ljava/io/File;", "loadThumbnailFromVideoUrl", "scope", "Lkotlinx/coroutines/CoroutineScope;", "videoUrl", "loadWithResultListener", "view", "design", "Lorg/wordpress/android/networking/MShot;", "thumbnailUrl", "preload", "addFallback", "error/NonExistentClass", "(Lerror/NonExistentClass;Lorg/wordpress/android/util/image/ImageType;)Lerror/NonExistentClass;", "addPlaceholder", "addSignature", "signature", "(Lerror/NonExistentClass;Ljava/lang/Integer;)Lerror/NonExistentClass;", "addThumbnail", "listener", "(Lerror/NonExistentClass;Landroid/content/Context;Ljava/lang/String;Lorg/wordpress/android/util/image/ImageManager$RequestListener;)Lerror/NonExistentClass;", "applyScaleType", "(Lerror/NonExistentClass;Landroid/widget/ImageView$ScaleType;)Lerror/NonExistentClass;", "applySize", "(Lerror/NonExistentClass;Ljava/lang/Integer;Ljava/lang/Integer;)Lerror/NonExistentClass;", "attachRequestListener", "(Lerror/NonExistentClass;Lorg/wordpress/android/util/image/ImageManager$RequestListener;)Lerror/NonExistentClass;", "isAvailable", "", "Companion", "RequestListener", "org.wordpress.android_wordpressJalapenoDebug"})
@javax.inject.Singleton()
public final class ImageManager {
    private final org.wordpress.android.util.image.ImagePlaceholderManager placeholderManager = null;
    private final org.wordpress.android.ui.media.VideoLoader videoLoader = null;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.util.image.ImageManager.Companion Companion = null;
    @org.jetbrains.annotations.NotNull()
    @java.lang.Deprecated()
    private static final kotlin.Lazy instance$delegate = null;
    
    @javax.inject.Inject()
    public ImageManager(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.image.ImagePlaceholderManager placeholderManager, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.ui.media.VideoLoader videoLoader) {
        super();
    }
    
    /**
     * Return true if this [Context] is available.
     * Availability is defined as the following:
     * + [Context] is not null
     * + [Context] is not destroyed (tested with [FragmentActivity.isDestroyed] or [Activity.isDestroyed])
     */
    private final boolean isAvailable(android.content.Context $this$isAvailable) {
        return false;
    }
    
    /**
     * Loads an image from the "imgUrl" into the ImageView. Adds a placeholder and an error placeholder depending
     * on the ImageType.
     *
     * If no URL is provided, it only loads the placeholder
     */
    @kotlin.jvm.JvmOverloads()
    public final void load(@org.jetbrains.annotations.NotNull()
    android.widget.ImageView imageView, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.image.ImageType imageType) {
    }
    
    /**
     * Loads an image from the "imgUrl" into the ImageView. Adds a placeholder and an error placeholder depending
     * on the ImageType.
     *
     * If no URL is provided, it only loads the placeholder
     */
    @kotlin.jvm.JvmOverloads()
    public final void load(@org.jetbrains.annotations.NotNull()
    android.widget.ImageView imageView, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.image.ImageType imageType, @org.jetbrains.annotations.NotNull()
    java.lang.String imgUrl) {
    }
    
    /**
     * Loads an image from the "imgUrl" into the ImageView. Adds a placeholder and an error placeholder depending
     * on the ImageType.
     *
     * If no URL is provided, it only loads the placeholder
     */
    @kotlin.jvm.JvmOverloads()
    public final void load(@org.jetbrains.annotations.NotNull()
    android.widget.ImageView imageView, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.image.ImageType imageType, @org.jetbrains.annotations.NotNull()
    java.lang.String imgUrl, @org.jetbrains.annotations.NotNull()
    android.widget.ImageView.ScaleType scaleType) {
    }
    
    /**
     * Loads the first frame from the "videoUrl" as an image into the ImageView.
     * Adds a placeholder and an error placeholder depending on the ImageType.
     *
     * If no URL is provided, it only loads the placeholder
     */
    @kotlin.jvm.JvmOverloads()
    public final void loadThumbnailFromVideoUrl(@org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.CoroutineScope scope, @org.jetbrains.annotations.NotNull()
    android.widget.ImageView imageView) {
    }
    
    /**
     * Loads the first frame from the "videoUrl" as an image into the ImageView.
     * Adds a placeholder and an error placeholder depending on the ImageType.
     *
     * If no URL is provided, it only loads the placeholder
     */
    @kotlin.jvm.JvmOverloads()
    public final void loadThumbnailFromVideoUrl(@org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.CoroutineScope scope, @org.jetbrains.annotations.NotNull()
    android.widget.ImageView imageView, @org.jetbrains.annotations.NotNull()
    java.lang.String videoUrl) {
    }
    
    /**
     * Loads the first frame from the "videoUrl" as an image into the ImageView.
     * Adds a placeholder and an error placeholder depending on the ImageType.
     *
     * If no URL is provided, it only loads the placeholder
     */
    @kotlin.jvm.JvmOverloads()
    public final void loadThumbnailFromVideoUrl(@org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.CoroutineScope scope, @org.jetbrains.annotations.NotNull()
    android.widget.ImageView imageView, @org.jetbrains.annotations.NotNull()
    java.lang.String videoUrl, @org.jetbrains.annotations.NotNull()
    android.widget.ImageView.ScaleType scaleType) {
    }
    
    /**
     * Loads the first frame from the "videoUrl" as an image into the ImageView.
     * Adds a placeholder and an error placeholder depending on the ImageType.
     *
     * If no URL is provided, it only loads the placeholder
     */
    @kotlin.jvm.JvmOverloads()
    public final void loadThumbnailFromVideoUrl(@org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.CoroutineScope scope, @org.jetbrains.annotations.NotNull()
    android.widget.ImageView imageView, @org.jetbrains.annotations.NotNull()
    java.lang.String videoUrl, @org.jetbrains.annotations.NotNull()
    android.widget.ImageView.ScaleType scaleType, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.util.image.ImageManager.RequestListener<android.graphics.drawable.Drawable> requestListener) {
    }
    
    /**
     * Loads an image from the "imgUrl" into the AppWidgetTarget. Adds a placeholder and an error placeholder depending
     * on the ImageType.
     *
     * If no URL is provided, it only loads the placeholder
     */
    @kotlin.jvm.JvmOverloads()
    public final void load(@org.jetbrains.annotations.NotNull()
    com.bumptech.glide.request.target.AppWidgetTarget awt, @org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.image.ImageType imageType) {
    }
    
    /**
     * Loads an image from the "imgUrl" into the AppWidgetTarget. Adds a placeholder and an error placeholder depending
     * on the ImageType.
     *
     * If no URL is provided, it only loads the placeholder
     */
    @kotlin.jvm.JvmOverloads()
    public final void load(@org.jetbrains.annotations.NotNull()
    com.bumptech.glide.request.target.AppWidgetTarget awt, @org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.image.ImageType imageType, @org.jetbrains.annotations.NotNull()
    java.lang.String imgUrl) {
    }
    
    /**
     * Loads an image from the "imgUrl" into the AppWidgetTarget. Adds a placeholder and an error placeholder depending
     * on the ImageType.
     *
     * If no URL is provided, it only loads the placeholder
     */
    @kotlin.jvm.JvmOverloads()
    public final void load(@org.jetbrains.annotations.NotNull()
    com.bumptech.glide.request.target.AppWidgetTarget awt, @org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.image.ImageType imageType, @org.jetbrains.annotations.NotNull()
    java.lang.String imgUrl, @org.jetbrains.annotations.NotNull()
    android.widget.ImageView.ScaleType scaleType) {
    }
    
    /**
     * Loads an image from the "imgUrl" into the AppWidgetTarget. Adds a placeholder and an error placeholder depending
     * on the ImageType.
     *
     * If no URL is provided, it only loads the placeholder
     */
    @kotlin.jvm.JvmOverloads()
    public final void load(@org.jetbrains.annotations.NotNull()
    com.bumptech.glide.request.target.AppWidgetTarget awt, @org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.image.ImageType imageType, @org.jetbrains.annotations.NotNull()
    java.lang.String imgUrl, @org.jetbrains.annotations.NotNull()
    android.widget.ImageView.ScaleType scaleType, @org.jetbrains.annotations.Nullable()
    java.lang.Integer width) {
    }
    
    /**
     * Loads an image from the "imgUrl" into the AppWidgetTarget. Adds a placeholder and an error placeholder depending
     * on the ImageType.
     *
     * If no URL is provided, it only loads the placeholder
     */
    @kotlin.jvm.JvmOverloads()
    public final void load(@org.jetbrains.annotations.NotNull()
    com.bumptech.glide.request.target.AppWidgetTarget awt, @org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.image.ImageType imageType, @org.jetbrains.annotations.NotNull()
    java.lang.String imgUrl, @org.jetbrains.annotations.NotNull()
    android.widget.ImageView.ScaleType scaleType, @org.jetbrains.annotations.Nullable()
    java.lang.Integer width, @org.jetbrains.annotations.Nullable()
    java.lang.Integer height) {
    }
    
    /**
     * Loads an image from the "imgUrl" into the ImageView and applies circle transformation. Adds placeholder and
     * error placeholder depending on the ImageType.
     */
    @kotlin.jvm.JvmOverloads()
    public final void loadIntoCircle(@org.jetbrains.annotations.NotNull()
    android.widget.ImageView imageView, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.image.ImageType imageType, @org.jetbrains.annotations.NotNull()
    java.lang.String imgUrl) {
    }
    
    /**
     * Loads an image from the "imgUrl" into the ImageView and applies circle transformation. Adds placeholder and
     * error placeholder depending on the ImageType.
     */
    @kotlin.jvm.JvmOverloads()
    public final void loadIntoCircle(@org.jetbrains.annotations.NotNull()
    android.widget.ImageView imageView, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.image.ImageType imageType, @org.jetbrains.annotations.NotNull()
    java.lang.String imgUrl, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.util.image.ImageManager.RequestListener<android.graphics.drawable.Drawable> requestListener) {
    }
    
    /**
     * Loads an image from the "imgUrl" into the ImageView and applies circle transformation. Adds placeholder and
     * error placeholder depending on the ImageType.
     */
    @kotlin.jvm.JvmOverloads()
    public final void loadIntoCircle(@org.jetbrains.annotations.NotNull()
    android.widget.ImageView imageView, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.image.ImageType imageType, @org.jetbrains.annotations.NotNull()
    java.lang.String imgUrl, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.util.image.ImageManager.RequestListener<android.graphics.drawable.Drawable> requestListener, @org.jetbrains.annotations.Nullable()
    java.lang.Integer version) {
    }
    
    /**
     * Loads a base64 string without prefix (data:image/png;base64,) into the ImageView and applies circle
     * transformation. Adds placeholder and error placeholder depending on the ImageType.
     */
    @kotlin.jvm.JvmOverloads()
    public final void loadBase64IntoCircle(@org.jetbrains.annotations.NotNull()
    android.widget.ImageView imageView, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.image.ImageType imageType, @org.jetbrains.annotations.NotNull()
    java.lang.String base64ImageData) {
    }
    
    /**
     * Loads a base64 string without prefix (data:image/png;base64,) into the ImageView and applies circle
     * transformation. Adds placeholder and error placeholder depending on the ImageType.
     */
    @kotlin.jvm.JvmOverloads()
    public final void loadBase64IntoCircle(@org.jetbrains.annotations.NotNull()
    android.widget.ImageView imageView, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.image.ImageType imageType, @org.jetbrains.annotations.NotNull()
    java.lang.String base64ImageData, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.util.image.ImageManager.RequestListener<android.graphics.drawable.Drawable> requestListener) {
    }
    
    /**
     * Loads a base64 string without prefix (data:image/png;base64,) into the ImageView and applies circle
     * transformation. Adds placeholder and error placeholder depending on the ImageType.
     */
    @kotlin.jvm.JvmOverloads()
    public final void loadBase64IntoCircle(@org.jetbrains.annotations.NotNull()
    android.widget.ImageView imageView, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.image.ImageType imageType, @org.jetbrains.annotations.NotNull()
    java.lang.String base64ImageData, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.util.image.ImageManager.RequestListener<android.graphics.drawable.Drawable> requestListener, @org.jetbrains.annotations.Nullable()
    java.lang.Integer version) {
    }
    
    /**
     * Loads an image from the "imgUrl" into the ImageView with a corner radius. Adds placeholder and
     * error placeholder depending on the ImageType.
     */
    @kotlin.jvm.JvmOverloads()
    public final void loadImageWithCorners(@org.jetbrains.annotations.NotNull()
    android.widget.ImageView imageView, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.image.ImageType imageType, @org.jetbrains.annotations.NotNull()
    java.lang.String imgUrl, int cornerRadius) {
    }
    
    /**
     * Loads an image from the "imgUrl" into the ImageView with a corner radius. Adds placeholder and
     * error placeholder depending on the ImageType.
     */
    @kotlin.jvm.JvmOverloads()
    public final void loadImageWithCorners(@org.jetbrains.annotations.NotNull()
    android.widget.ImageView imageView, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.image.ImageType imageType, @org.jetbrains.annotations.NotNull()
    java.lang.String imgUrl, int cornerRadius, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.util.image.ImageManager.RequestListener<android.graphics.drawable.Drawable> requestListener) {
    }
    
    /**
     * Loads an image from the "imgUrl" into the ImageView. Adds a placeholder and an error placeholder depending
     * on the ImageType. Attaches the ResultListener so the client can manually show/hide progress and error
     * views or add a PhotoViewAttacher(adds support for pinch-to-zoom gesture). Optionally adds
     * thumbnailUrl - mostly used for loading low resolution images.
     *
     * Unless you necessarily need to react on the request result, preferred way is to use one of the load(...) methods.
     */
    public final void loadWithResultListener(@org.jetbrains.annotations.NotNull()
    android.widget.ImageView imageView, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.image.ImageType imageType, @org.jetbrains.annotations.NotNull()
    java.lang.String imgUrl, @org.jetbrains.annotations.NotNull()
    android.widget.ImageView.ScaleType scaleType, @org.jetbrains.annotations.Nullable()
    java.lang.String thumbnailUrl, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.image.ImageManager.RequestListener<android.graphics.drawable.Drawable> requestListener) {
    }
    
    /**
     * Preloads an [MShot].
     *
     * This is needed because the mshot service redirects to a loading gif image when the thumbnail is not ready.
     * The loading is handled by [org.wordpress.android.networking.GlideMShotsLoader]
     */
    public final void preload(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.networking.MShot design) {
    }
    
    /**
     * Loads an [MShot] into an [ImageView] and attaches a [RequestListener].
     *
     * This is needed because the mshot service redirects to a loading gif image when the thumbnail is not ready.
     * The loading is handled by [org.wordpress.android.networking.GlideMShotsLoader]
     */
    public final void loadWithResultListener(@org.jetbrains.annotations.NotNull()
    android.widget.ImageView view, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.networking.MShot design, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.image.ImageManager.RequestListener<android.graphics.drawable.Drawable> requestListener) {
    }
    
    /**
     * Loads an image from the "imgUri" into the ImageView. Doing this allows content and remote URIs to interchangeable.
     * Adds a placeholder and an error placeholder depending
     * on the ImageType. Attaches the ResultListener so the client can manually show/hide progress and error
     * views or add a PhotoViewAttacher(adds support for pinch-to-zoom gesture). Optionally adds
     * thumbnailUrl - mostly used for loading low resolution images.
     *
     * Unless you necessarily need to react on the request result, preferred way is to use one of the load(...) methods.
     */
    public final void loadWithResultListener(@org.jetbrains.annotations.NotNull()
    android.widget.ImageView imageView, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.image.ImageType imageType, @org.jetbrains.annotations.NotNull()
    android.net.Uri imgUri, @org.jetbrains.annotations.NotNull()
    android.widget.ImageView.ScaleType scaleType, @org.jetbrains.annotations.Nullable()
    java.lang.String thumbnailUrl, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.image.ImageManager.RequestListener<android.graphics.drawable.Drawable> requestListener) {
    }
    
    /**
     * Loads a File either using a file path obtained from the media store (for local images),
     * or using Glide's disk cache (for remote images). Using Uri allows content and remote URIs to be interchangeable.
     *
     * We can use asFile() asynchronously on the ui thread or synchronously on a background thread.
     * This function uses the asynchronous api which takes a Target argument to invoke asFile().
     */
    public final void loadIntoFileWithResultListener(@org.jetbrains.annotations.NotNull()
    android.net.Uri imgUri, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.image.ImageManager.RequestListener<java.io.File> requestListener) {
    }
    
    /**
     * Loads the Bitmap into the ImageView.
     */
    @kotlin.jvm.JvmOverloads()
    public final void load(@org.jetbrains.annotations.NotNull()
    android.widget.ImageView imageView, @org.jetbrains.annotations.NotNull()
    android.graphics.Bitmap bitmap) {
    }
    
    /**
     * Loads the Bitmap into the ImageView.
     */
    @kotlin.jvm.JvmOverloads()
    public final void load(@org.jetbrains.annotations.NotNull()
    android.widget.ImageView imageView, @org.jetbrains.annotations.NotNull()
    android.graphics.Bitmap bitmap, @org.jetbrains.annotations.NotNull()
    android.widget.ImageView.ScaleType scaleType) {
    }
    
    /**
     * Loads the Drawable into the ImageView.
     */
    @kotlin.jvm.JvmOverloads()
    public final void load(@org.jetbrains.annotations.NotNull()
    android.widget.ImageView imageView, @org.jetbrains.annotations.NotNull()
    android.graphics.drawable.Drawable drawable) {
    }
    
    /**
     * Loads the Drawable into the ImageView.
     */
    @kotlin.jvm.JvmOverloads()
    public final void load(@org.jetbrains.annotations.NotNull()
    android.widget.ImageView imageView, @org.jetbrains.annotations.NotNull()
    android.graphics.drawable.Drawable drawable, @org.jetbrains.annotations.NotNull()
    android.widget.ImageView.ScaleType scaleType) {
    }
    
    /**
     * Loads the DrawableResource into the ImageView.
     */
    @kotlin.jvm.JvmOverloads()
    public final void load(@org.jetbrains.annotations.NotNull()
    android.widget.ImageView imageView, @androidx.annotation.DrawableRes()
    int resourceId) {
    }
    
    /**
     * Loads the DrawableResource into the ImageView.
     */
    @kotlin.jvm.JvmOverloads()
    public final void load(@org.jetbrains.annotations.NotNull()
    android.widget.ImageView imageView, @androidx.annotation.DrawableRes()
    int resourceId, @org.jetbrains.annotations.NotNull()
    android.widget.ImageView.ScaleType scaleType) {
    }
    
    /**
     * Loads an image from the "imgUrl" into the ViewTarget. Adds a placeholder and an error placeholder depending
     * on the ImageType.
     *
     * Use this method with caution and only when you necessarily need it(in other words, don't use it
     * when you need to load an image into an ImageView).
     */
    public final void loadIntoCustomTarget(@org.jetbrains.annotations.NotNull()
    com.bumptech.glide.request.target.ViewTarget<android.widget.TextView, android.graphics.drawable.Drawable> viewTarget, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.image.ImageType imageType, @org.jetbrains.annotations.NotNull()
    java.lang.String imgUrl) {
    }
    
    /**
     * Loads an image from the "imgUrl" into the ViewTarget.
     *
     * Use this method with caution and only when you necessarily need it(in other words, don't use it
     * when you need to load an image into an ImageView).
     */
    public final void loadAsBitmapIntoCustomTarget(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    com.bumptech.glide.request.target.BaseTarget<android.graphics.Bitmap> target, @org.jetbrains.annotations.NotNull()
    java.lang.String imgUrl, @org.jetbrains.annotations.NotNull()
    android.widget.ImageView.ScaleType scaleType) {
    }
    
    /**
     * Cancel any pending requests and free any resources that may have been
     * loaded for the view.
     */
    public final void cancelRequestAndClearImageView(@org.jetbrains.annotations.NotNull()
    android.widget.ImageView imageView) {
    }
    
    /**
     * Cancel any pending requests and free any resources that may have been
     * loaded for the view.
     */
    public final <T extends java.lang.Object>void cancelRequest(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    com.bumptech.glide.request.target.BaseTarget<T> target) {
    }
    
    private final <T extends java.lang.Object>error.NonExistentClass applyScaleType(error.NonExistentClass $this$applyScaleType, android.widget.ImageView.ScaleType scaleType) {
        return null;
    }
    
    private final <T extends java.lang.Object>error.NonExistentClass applySize(error.NonExistentClass $this$applySize, java.lang.Integer width, java.lang.Integer height) {
        return null;
    }
    
    private final <T extends java.lang.Object>error.NonExistentClass addPlaceholder(error.NonExistentClass $this$addPlaceholder, org.wordpress.android.util.image.ImageType imageType) {
        return null;
    }
    
    private final <T extends java.lang.Object>error.NonExistentClass addFallback(error.NonExistentClass $this$addFallback, org.wordpress.android.util.image.ImageType imageType) {
        return null;
    }
    
    /**
     * Changing the signature invalidates cache.
     */
    private final <T extends java.lang.Object>error.NonExistentClass addSignature(error.NonExistentClass $this$addSignature, java.lang.Integer signature) {
        return null;
    }
    
    private final error.NonExistentClass addThumbnail(error.NonExistentClass $this$addThumbnail, android.content.Context context, java.lang.String thumbnailUrl, org.wordpress.android.util.image.ImageManager.RequestListener<android.graphics.drawable.Drawable> listener) {
        return null;
    }
    
    private final <T extends java.lang.Object>error.NonExistentClass attachRequestListener(error.NonExistentClass $this$attachRequestListener, org.wordpress.android.util.image.ImageManager.RequestListener<T> requestListener) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Deprecated()
    public static final org.wordpress.android.util.image.ImageManager getInstance() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J\"\u0010\u0003\u001a\u00020\u00042\u000e\u0010\u0005\u001a\n\u0018\u00010\u0006j\u0004\u0018\u0001`\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u0002H&J\u001f\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00028\u00002\b\u0010\b\u001a\u0004\u0018\u00010\u0002H&\u00a2\u0006\u0002\u0010\u000b\u00a8\u0006\f"}, d2 = {"Lorg/wordpress/android/util/image/ImageManager$RequestListener;", "T", "", "onLoadFailed", "", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "model", "onResourceReady", "resource", "(Ljava/lang/Object;Ljava/lang/Object;)V", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract interface RequestListener<T extends java.lang.Object> {
        
        /**
         * Called when an exception occurs during a load
         *
         * @param e The maybe {@code null} exception containing information about why the request failed.
         * @param model The model we were trying to load when the exception occurred.
         */
        public abstract void onLoadFailed(@org.jetbrains.annotations.Nullable()
        java.lang.Exception e, @org.jetbrains.annotations.Nullable()
        java.lang.Object model);
        
        /**
         * Called when a load completes successfully
         *
         * @param resource The resource that was loaded for the target.
         * @param model The specific model that was used to load the image.
         */
        public abstract void onResourceReady(T resource, @org.jetbrains.annotations.Nullable()
        java.lang.Object model);
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0087\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R!\u0010\u0003\u001a\u00020\u00048FX\u0087\u0084\u0002\u00a2\u0006\u0012\n\u0004\b\b\u0010\t\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\n"}, d2 = {"Lorg/wordpress/android/util/image/ImageManager$Companion;", "", "()V", "instance", "Lorg/wordpress/android/util/image/ImageManager;", "getInstance$annotations", "getInstance", "()Lorg/wordpress/android/util/image/ImageManager;", "instance$delegate", "Lkotlin/Lazy;", "org.wordpress.android_wordpressJalapenoDebug"})
    @java.lang.Deprecated()
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Deprecated()
        public final org.wordpress.android.util.image.ImageManager getInstance() {
            return null;
        }
        
        @kotlin.jvm.JvmStatic()
        @java.lang.Deprecated()
        public static void getInstance$annotations() {
        }
    }
}