package org.wordpress.android.networking;

import java.lang.System;

/**
 * RequestFactory which adds authorization headers to all Glide requests and makes sure requests to WPcom endpoints
 * use https.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J0\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\u00072\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\nH\u0002J\u0010\u0010\u000b\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0002JD\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r2\u0006\u0010\b\u001a\u00020\u00072\u000e\u0010\u000f\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00110\u00102\u0006\u0010\u0012\u001a\u00020\u00132\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\nH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lorg/wordpress/android/networking/GlideRequestFactory;", "Lcom/bumptech/glide/integration/volley/VolleyRequestFactory;", "authenticationUtils", "Lorg/wordpress/android/ui/utils/AuthenticationUtils;", "(Lorg/wordpress/android/ui/utils/AuthenticationUtils;)V", "addAuthHeaders", "", "", "url", "currentHeaders", "", "convertWPcomUrlToHttps", "create", "Lcom/android/volley/Request;", "", "callback", "Lcom/bumptech/glide/load/data/DataFetcher$DataCallback;", "Ljava/io/InputStream;", "priority", "Lcom/android/volley/Request$Priority;", "headers", "org.wordpress.android_wordpressJalapenoDebug"})
@javax.inject.Singleton()
public final class GlideRequestFactory implements com.bumptech.glide.integration.volley.VolleyRequestFactory {
    private final org.wordpress.android.ui.utils.AuthenticationUtils authenticationUtils = null;
    
    @javax.inject.Inject()
    public GlideRequestFactory(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.utils.AuthenticationUtils authenticationUtils) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public com.android.volley.Request<byte[]> create(@org.jetbrains.annotations.NotNull()
    java.lang.String url, @org.jetbrains.annotations.NotNull()
    com.bumptech.glide.load.data.DataFetcher.DataCallback<? super java.io.InputStream> callback, @org.jetbrains.annotations.NotNull()
    com.android.volley.Request.Priority priority, @org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, java.lang.String> headers) {
        return null;
    }
    
    private final java.lang.String convertWPcomUrlToHttps(java.lang.String url) {
        return null;
    }
    
    private final java.util.Map<java.lang.String, java.lang.String> addAuthHeaders(java.lang.String url, java.util.Map<java.lang.String, java.lang.String> currentHeaders) {
        return null;
    }
}