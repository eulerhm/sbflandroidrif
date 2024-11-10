package org.wordpress.android.ui.main.utils;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nJB\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\n2\b\u0010\u0011\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0010\b\u0002\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0017R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0019"}, d2 = {"Lorg/wordpress/android/ui/main/utils/MeGravatarLoader;", "", "appPrefsWrapper", "Lorg/wordpress/android/ui/prefs/AppPrefsWrapper;", "imageManager", "Lorg/wordpress/android/util/image/ImageManager;", "resourseProvider", "Lorg/wordpress/android/viewmodel/ResourceProvider;", "(Lorg/wordpress/android/ui/prefs/AppPrefsWrapper;Lorg/wordpress/android/util/image/ImageManager;Lorg/wordpress/android/viewmodel/ResourceProvider;)V", "constructGravatarUrl", "", "rawAvatarUrl", "load", "", "newAvatarUploaded", "", "avatarUrl", "injectFilePath", "imageView", "Landroid/widget/ImageView;", "imageType", "Lorg/wordpress/android/util/image/ImageType;", "listener", "Lorg/wordpress/android/util/image/ImageManager$RequestListener;", "Landroid/graphics/drawable/Drawable;", "org.wordpress.android_wordpressJalapenoDebug"})
@javax.inject.Singleton()
public final class MeGravatarLoader {
    private final org.wordpress.android.ui.prefs.AppPrefsWrapper appPrefsWrapper = null;
    private final org.wordpress.android.util.image.ImageManager imageManager = null;
    private final org.wordpress.android.viewmodel.ResourceProvider resourseProvider = null;
    
    @javax.inject.Inject()
    public MeGravatarLoader(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.prefs.AppPrefsWrapper appPrefsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.image.ImageManager imageManager, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.ResourceProvider resourseProvider) {
        super();
    }
    
    public final void load(boolean newAvatarUploaded, @org.jetbrains.annotations.NotNull()
    java.lang.String avatarUrl, @org.jetbrains.annotations.Nullable()
    java.lang.String injectFilePath, @org.jetbrains.annotations.NotNull()
    android.widget.ImageView imageView, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.image.ImageType imageType, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.util.image.ImageManager.RequestListener<android.graphics.drawable.Drawable> listener) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String constructGravatarUrl(@org.jetbrains.annotations.NotNull()
    java.lang.String rawAvatarUrl) {
        return null;
    }
}