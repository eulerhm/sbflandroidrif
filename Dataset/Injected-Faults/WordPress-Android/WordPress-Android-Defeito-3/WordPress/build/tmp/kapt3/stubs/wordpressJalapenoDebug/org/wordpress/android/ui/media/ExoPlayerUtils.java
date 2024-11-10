package org.wordpress.android.ui.media;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u000e\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\rJ\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u0011R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lorg/wordpress/android/ui/media/ExoPlayerUtils;", "", "authenticationUtils", "Lorg/wordpress/android/ui/utils/AuthenticationUtils;", "appContext", "Landroid/content/Context;", "(Lorg/wordpress/android/ui/utils/AuthenticationUtils;Landroid/content/Context;)V", "httpDataSourceFactory", "Lcom/google/android/exoplayer2/upstream/DefaultHttpDataSourceFactory;", "buildDefaultDataSourceFactory", "Lcom/google/android/exoplayer2/upstream/DefaultDataSourceFactory;", "buildHttpDataSourceFactory", "url", "", "buildMediaSource", "Lcom/google/android/exoplayer2/source/MediaSource;", "uri", "Landroid/net/Uri;", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
@dagger.Reusable()
public final class ExoPlayerUtils {
    private final org.wordpress.android.ui.utils.AuthenticationUtils authenticationUtils = null;
    private final android.content.Context appContext = null;
    private com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory httpDataSourceFactory;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.media.ExoPlayerUtils.Companion Companion = null;
    private static final java.lang.String UNSUPPORTED_TYPE = "Unsupported type";
    
    @javax.inject.Inject()
    public ExoPlayerUtils(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.utils.AuthenticationUtils authenticationUtils, @org.jetbrains.annotations.NotNull()
    android.content.Context appContext) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory buildHttpDataSourceFactory(@org.jetbrains.annotations.NotNull()
    java.lang.String url) {
        return null;
    }
    
    private final com.google.android.exoplayer2.upstream.DefaultDataSourceFactory buildDefaultDataSourceFactory(com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory httpDataSourceFactory) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.google.android.exoplayer2.source.MediaSource buildMediaSource(@org.jetbrains.annotations.NotNull()
    android.net.Uri uri) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lorg/wordpress/android/ui/media/ExoPlayerUtils$Companion;", "", "()V", "UNSUPPORTED_TYPE", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}