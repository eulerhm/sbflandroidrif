package org.wordpress.android.ui.mediapicker.loader;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB\'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\b\u0010\u0012\u001a\u00020\u0013H\u0002J+\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\b\u0010\u0018\u001a\u0004\u0018\u00010\u000fH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0019J\f\u0010\u001a\u001a\u00020\r*\u00020\u001bH\u0002J\u0016\u0010\u001c\u001a\u0004\u0018\u00010\u000f*\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u000fH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u001f"}, d2 = {"Lorg/wordpress/android/ui/mediapicker/loader/GifMediaDataSource;", "Lorg/wordpress/android/ui/mediapicker/loader/MediaSource;", "context", "Landroid/content/Context;", "tenorClient", "Lorg/wordpress/android/ui/mediapicker/loader/TenorGifClient;", "uriUtilsWrapper", "Lorg/wordpress/android/util/UriUtilsWrapper;", "networkUtilsWrapper", "Lorg/wordpress/android/util/NetworkUtilsWrapper;", "(Landroid/content/Context;Lorg/wordpress/android/ui/mediapicker/loader/TenorGifClient;Lorg/wordpress/android/util/UriUtilsWrapper;Lorg/wordpress/android/util/NetworkUtilsWrapper;)V", "items", "", "Lorg/wordpress/android/ui/mediapicker/MediaItem;", "lastFilter", "", "nextPosition", "", "buildDefaultScreen", "Lorg/wordpress/android/ui/mediapicker/loader/MediaSource$MediaLoadingResult;", "load", "forced", "", "loadMore", "filter", "(ZZLjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toMediaItem", "Lcom/tenor/android/core/model/impl/Result;", "urlFromCollectionFormat", "format", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public final class GifMediaDataSource implements org.wordpress.android.ui.mediapicker.loader.MediaSource {
    private final android.content.Context context = null;
    private final org.wordpress.android.ui.mediapicker.loader.TenorGifClient tenorClient = null;
    private final org.wordpress.android.util.UriUtilsWrapper uriUtilsWrapper = null;
    private final org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper = null;
    private int nextPosition = 0;
    private final java.util.List<org.wordpress.android.ui.mediapicker.MediaItem> items = null;
    private java.lang.String lastFilter;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.mediapicker.loader.GifMediaDataSource.Companion Companion = null;
    private static final int PAGE_SIZE = 36;
    
    @javax.inject.Inject()
    public GifMediaDataSource(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mediapicker.loader.TenorGifClient tenorClient, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.UriUtilsWrapper uriUtilsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object load(boolean forced, boolean loadMore, @org.jetbrains.annotations.Nullable()
    java.lang.String filter, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.wordpress.android.ui.mediapicker.loader.MediaSource.MediaLoadingResult> continuation) {
        return null;
    }
    
    private final org.wordpress.android.ui.mediapicker.loader.MediaSource.MediaLoadingResult buildDefaultScreen() {
        return null;
    }
    
    private final org.wordpress.android.ui.mediapicker.MediaItem toMediaItem(com.tenor.android.core.model.impl.Result $this$toMediaItem) {
        return null;
    }
    
    private final java.lang.String urlFromCollectionFormat(com.tenor.android.core.model.impl.Result $this$urlFromCollectionFormat, java.lang.String format) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lorg/wordpress/android/ui/mediapicker/loader/GifMediaDataSource$Companion;", "", "()V", "PAGE_SIZE", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}