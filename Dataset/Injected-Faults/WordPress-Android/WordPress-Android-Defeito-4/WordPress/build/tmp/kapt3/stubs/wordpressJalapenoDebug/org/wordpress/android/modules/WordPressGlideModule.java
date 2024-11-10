package org.wordpress.android.modules;

import java.lang.System;

/**
 * Custom [AppGlideModule] that replaces Glide's default [RequestQueue] with FluxC's and sets custom GlideHeaderLoader
 * which adds support for custom authorization headers.
 */
@com.bumptech.glide.annotation.GlideModule()
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\b\u0010\u001a\u001a\u00020\u001bH\u0016J \u0010\u001c\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0016R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR$\u0010\t\u001a\u00020\n8\u0006@\u0006X\u0087.\u00a2\u0006\u0014\n\u0000\u0012\u0004\b\u000b\u0010\u0002\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR$\u0010\u0010\u001a\u00020\n8\u0006@\u0006X\u0087.\u00a2\u0006\u0014\n\u0000\u0012\u0004\b\u0011\u0010\u0002\u001a\u0004\b\u0012\u0010\r\"\u0004\b\u0013\u0010\u000f\u00a8\u0006!"}, d2 = {"Lorg/wordpress/android/modules/WordPressGlideModule;", "Lcom/bumptech/glide/module/AppGlideModule;", "()V", "glideRequestFactory", "Lorg/wordpress/android/networking/GlideRequestFactory;", "getGlideRequestFactory", "()Lorg/wordpress/android/networking/GlideRequestFactory;", "setGlideRequestFactory", "(Lorg/wordpress/android/networking/GlideRequestFactory;)V", "noRedirectsRequestQueue", "Lcom/android/volley/RequestQueue;", "getNoRedirectsRequestQueue$annotations", "getNoRedirectsRequestQueue", "()Lcom/android/volley/RequestQueue;", "setNoRedirectsRequestQueue", "(Lcom/android/volley/RequestQueue;)V", "requestQueue", "getRequestQueue$annotations", "getRequestQueue", "setRequestQueue", "applyOptions", "", "context", "Landroid/content/Context;", "builder", "Lcom/bumptech/glide/GlideBuilder;", "isManifestParsingEnabled", "", "registerComponents", "glide", "Lcom/bumptech/glide/Glide;", "registry", "Lcom/bumptech/glide/Registry;", "org.wordpress.android_wordpressJalapenoDebug"})
public final class WordPressGlideModule extends com.bumptech.glide.module.AppGlideModule {
    @javax.inject.Inject()
    public com.android.volley.RequestQueue requestQueue;
    @javax.inject.Inject()
    public com.android.volley.RequestQueue noRedirectsRequestQueue;
    @javax.inject.Inject()
    public org.wordpress.android.networking.GlideRequestFactory glideRequestFactory;
    
    public WordPressGlideModule() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.android.volley.RequestQueue getRequestQueue() {
        return null;
    }
    
    @javax.inject.Named(value = "custom-ssl")
    @java.lang.Deprecated()
    public static void getRequestQueue$annotations() {
    }
    
    public final void setRequestQueue(@org.jetbrains.annotations.NotNull()
    com.android.volley.RequestQueue p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.android.volley.RequestQueue getNoRedirectsRequestQueue() {
        return null;
    }
    
    @javax.inject.Named(value = "no-redirects")
    @java.lang.Deprecated()
    public static void getNoRedirectsRequestQueue$annotations() {
    }
    
    public final void setNoRedirectsRequestQueue(@org.jetbrains.annotations.NotNull()
    com.android.volley.RequestQueue p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.networking.GlideRequestFactory getGlideRequestFactory() {
        return null;
    }
    
    public final void setGlideRequestFactory(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.networking.GlideRequestFactory p0) {
    }
    
    @java.lang.Override()
    public void applyOptions(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    com.bumptech.glide.GlideBuilder builder) {
    }
    
    @java.lang.Override()
    public boolean isManifestParsingEnabled() {
        return false;
    }
    
    @java.lang.Override()
    public void registerComponents(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    com.bumptech.glide.Glide glide, @org.jetbrains.annotations.NotNull()
    com.bumptech.glide.Registry registry) {
    }
}