package org.wordpress.android.networking;

import java.lang.System;

/**
 * Implements a custom Glide [ModelLoader] that retries loading on http redirection (307)
 *
 * This is needed because the mshot backend service redirects to a loading gif image when the thumbnail is not ready.
 * This occurs when the thumbnail has not been accessed recently for the specific language and viewport size. When this
 * thumbnail is requested it is cached on the server and is readily available according to the server cache policy.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001\u0016B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J0\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0002H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0017"}, d2 = {"Lorg/wordpress/android/networking/GlideMShotsLoader;", "Lcom/bumptech/glide/load/model/ModelLoader;", "Lorg/wordpress/android/networking/MShot;", "Ljava/io/InputStream;", "noRedirectsRequestQueue", "Lcom/android/volley/RequestQueue;", "(Lcom/android/volley/RequestQueue;)V", "loader", "Lcom/bumptech/glide/integration/volley/VolleyUrlLoader;", "requestFactory", "Lcom/bumptech/glide/integration/volley/VolleyRequestFactory;", "buildLoadData", "Lcom/bumptech/glide/load/model/ModelLoader$LoadData;", "model", "width", "", "height", "options", "Lcom/bumptech/glide/load/Options;", "handles", "", "item", "Factory", "org.wordpress.android_wordpressJalapenoDebug"})
public final class GlideMShotsLoader implements com.bumptech.glide.load.model.ModelLoader<org.wordpress.android.networking.MShot, java.io.InputStream> {
    private final com.bumptech.glide.integration.volley.VolleyRequestFactory requestFactory = null;
    private final com.bumptech.glide.integration.volley.VolleyUrlLoader loader = null;
    
    public GlideMShotsLoader(@org.jetbrains.annotations.NotNull()
    com.android.volley.RequestQueue noRedirectsRequestQueue) {
        super();
    }
    
    @java.lang.Override()
    public boolean handles(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.networking.MShot item) {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public com.bumptech.glide.load.model.ModelLoader.LoadData<java.io.InputStream> buildLoadData(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.networking.MShot model, int width, int height, @org.jetbrains.annotations.NotNull()
    com.bumptech.glide.load.Options options) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u001c\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lorg/wordpress/android/networking/GlideMShotsLoader$Factory;", "Lcom/bumptech/glide/load/model/ModelLoaderFactory;", "Lorg/wordpress/android/networking/MShot;", "Ljava/io/InputStream;", "noRedirectsRequestQueue", "Lcom/android/volley/RequestQueue;", "(Lcom/android/volley/RequestQueue;)V", "build", "Lcom/bumptech/glide/load/model/ModelLoader;", "multiFactory", "Lcom/bumptech/glide/load/model/MultiModelLoaderFactory;", "teardown", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Factory implements com.bumptech.glide.load.model.ModelLoaderFactory<org.wordpress.android.networking.MShot, java.io.InputStream> {
        private final com.android.volley.RequestQueue noRedirectsRequestQueue = null;
        
        public Factory(@org.jetbrains.annotations.NotNull()
        com.android.volley.RequestQueue noRedirectsRequestQueue) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public com.bumptech.glide.load.model.ModelLoader<org.wordpress.android.networking.MShot, java.io.InputStream> build(@org.jetbrains.annotations.NotNull()
        com.bumptech.glide.load.model.MultiModelLoaderFactory multiFactory) {
            return null;
        }
        
        @java.lang.Override()
        public void teardown() {
        }
    }
}