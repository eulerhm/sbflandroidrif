package org.wordpress.android.ui.reader.utils;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u0010\u0010\t\u001a\u00020\n2\b\b\u0001\u0010\u000b\u001a\u00020\fJ\u0016\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/reader/utils/FeaturedImageUtils;", "", "photonUtilsWrapper", "Lorg/wordpress/android/util/PhotonUtilsWrapper;", "(Lorg/wordpress/android/util/PhotonUtilsWrapper;)V", "getLastIndexOfDashInUrl", "", "url", "Ljava/net/URL;", "shouldAddFeaturedImage", "", "readerPost", "Lorg/wordpress/android/models/ReaderPost;", "showFeaturedImage", "featuredImage", "", "postText", "org.wordpress.android_wordpressJalapenoDebug"})
public final class FeaturedImageUtils {
    private final org.wordpress.android.util.PhotonUtilsWrapper photonUtilsWrapper = null;
    
    @javax.inject.Inject()
    public FeaturedImageUtils(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.PhotonUtilsWrapper photonUtilsWrapper) {
        super();
    }
    
    public final boolean showFeaturedImage(@org.jetbrains.annotations.NotNull()
    java.lang.String featuredImage, @org.jetbrains.annotations.NotNull()
    java.lang.String postText) {
        return false;
    }
    
    private final int getLastIndexOfDashInUrl(java.net.URL url) {
        return 0;
    }
    
    public final boolean shouldAddFeaturedImage(@org.jetbrains.annotations.NotNull()
    @androidx.annotation.NonNull()
    org.wordpress.android.models.ReaderPost readerPost) {
        return false;
    }
}