package org.wordpress.android.util;

import java.lang.System;

/**
 * Injectable wrapper around MediaUtils, WPMediaUtils, FluxC's MediaUtils & EditorMediaUtils.
 *
 * MediaUtils, WPMediaUtils, FluxC's MediaUtils & EditorMediaUtils interfaces are consisted of static methods, which
 * makes the client code difficult to test/mock. Main purpose of this wrapper is to make testing easier.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\b\u0007\u0018\u0000 (2\u00020\u0001:\u0001(B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J(\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0016\b\u0002\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n\u0018\u00010\tJ\u0018\u0010\u000b\u001a\u0004\u0018\u00010\u00062\u0006\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000eJ\u0010\u0010\u000f\u001a\u00020\n2\b\u0010\u0010\u001a\u0004\u0018\u00010\nJ\u0006\u0010\u0011\u001a\u00020\u0006J\u0010\u0010\u0012\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0013\u001a\u00020\u0006J\u0018\u0010\u0014\u001a\u0004\u0018\u00010\u00062\u0006\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000eJ\u0010\u0010\u0015\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0016\u001a\u00020\u0006J\u0010\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aJ$\u0010\u001b\u001a\u0004\u0018\u00010\n2\u0006\u0010\u001c\u001a\u00020\n2\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\tJ\u000e\u0010\u001d\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u0006J\u0010\u0010\u001e\u001a\u00020\u000e2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0006J\u000e\u0010\u001f\u001a\u00020\u000e2\u0006\u0010 \u001a\u00020\nJ\u0018\u0010!\u001a\u00020\u000e2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u0010\u001a\u00020\nJ\u001e\u0010\"\u001a\u00020\u000e2\u0006\u0010#\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0013\u001a\u00020\u0006J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u0006J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010$\u001a\u00020\nJ\u000e\u0010%\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u0006J\u0010\u0010&\u001a\u00020\u000e2\b\u0010\u0010\u001a\u0004\u0018\u00010\nJ\u0006\u0010\'\u001a\u00020\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006)"}, d2 = {"Lorg/wordpress/android/util/MediaUtilsWrapper;", "", "appContext", "Landroid/content/Context;", "(Landroid/content/Context;)V", "copyFileToAppStorage", "Landroid/net/Uri;", "imageUri", "headers", "", "", "fixOrientationIssue", "path", "isVideo", "", "getExtensionForMimeType", "mimeType", "getLastRecordedVideoUri", "getMimeType", "uri", "getOptimizedMedia", "getRealPathFromURI", "mediaUri", "getSitePlanForMimeTypes", "Lorg/wordpress/android/fluxc/utils/MimeTypes$Plan;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "getVideoThumbnail", "videoPath", "isFile", "isInMediaStore", "isLocalFile", "uploadState", "isMimeTypeSupportedBySitePlan", "isProhibitedVideoDuration", "context", "mediaUriString", "isVideoFile", "isVideoMimeType", "shouldAdvertiseImageOptimization", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
@dagger.Reusable()
public final class MediaUtilsWrapper {
    private final android.content.Context appContext = null;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.util.MediaUtilsWrapper.Companion Companion = null;
    private static final long DURATION_5_MIN = 5L;
    
    @javax.inject.Inject()
    public MediaUtilsWrapper(@org.jetbrains.annotations.NotNull()
    android.content.Context appContext) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getRealPathFromURI(@org.jetbrains.annotations.NotNull()
    android.net.Uri mediaUri) {
        return null;
    }
    
    public final boolean isVideo(@org.jetbrains.annotations.NotNull()
    android.net.Uri mediaUri) {
        return false;
    }
    
    public final boolean isVideo(@org.jetbrains.annotations.NotNull()
    java.lang.String mediaUriString) {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.net.Uri getLastRecordedVideoUri() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final android.net.Uri getOptimizedMedia(@org.jetbrains.annotations.NotNull()
    java.lang.String path, boolean isVideo) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final android.net.Uri fixOrientationIssue(@org.jetbrains.annotations.NotNull()
    java.lang.String path, boolean isVideo) {
        return null;
    }
    
    public final boolean isVideoMimeType(@org.jetbrains.annotations.Nullable()
    java.lang.String mimeType) {
        return false;
    }
    
    public final boolean isInMediaStore(@org.jetbrains.annotations.Nullable()
    android.net.Uri mediaUri) {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final android.net.Uri copyFileToAppStorage(@org.jetbrains.annotations.NotNull()
    android.net.Uri imageUri, @org.jetbrains.annotations.Nullable()
    java.util.Map<java.lang.String, java.lang.String> headers) {
        return null;
    }
    
    public final boolean shouldAdvertiseImageOptimization() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getMimeType(@org.jetbrains.annotations.NotNull()
    android.net.Uri uri) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getVideoThumbnail(@org.jetbrains.annotations.NotNull()
    java.lang.String videoPath, @org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, java.lang.String> headers) {
        return null;
    }
    
    public final boolean isLocalFile(@org.jetbrains.annotations.NotNull()
    java.lang.String uploadState) {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getExtensionForMimeType(@org.jetbrains.annotations.Nullable()
    java.lang.String mimeType) {
        return null;
    }
    
    public final boolean isFile(@org.jetbrains.annotations.NotNull()
    android.net.Uri mediaUri) {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.fluxc.utils.MimeTypes.Plan getSitePlanForMimeTypes(@org.jetbrains.annotations.Nullable()
    org.wordpress.android.fluxc.model.SiteModel site) {
        return null;
    }
    
    public final boolean isMimeTypeSupportedBySitePlan(@org.jetbrains.annotations.Nullable()
    org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
    java.lang.String mimeType) {
        return false;
    }
    
    public final boolean isVideoFile(@org.jetbrains.annotations.NotNull()
    android.net.Uri mediaUri) {
        return false;
    }
    
    public final boolean isProhibitedVideoDuration(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
    android.net.Uri uri) {
        return false;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lorg/wordpress/android/util/MediaUtilsWrapper$Companion;", "", "()V", "DURATION_5_MIN", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}