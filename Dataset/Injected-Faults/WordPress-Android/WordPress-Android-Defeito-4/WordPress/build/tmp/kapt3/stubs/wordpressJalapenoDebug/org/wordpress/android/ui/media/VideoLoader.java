package org.wordpress.android.ui.media;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB;\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u00a2\u0006\u0002\u0010\rJ\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u0010\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J2\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00160\u001c2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00160\u001cR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001f"}, d2 = {"Lorg/wordpress/android/ui/media/VideoLoader;", "", "bgDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "mainDispatcher", "authenticationUtils", "Lorg/wordpress/android/ui/utils/AuthenticationUtils;", "appLogWrapper", "Lorg/wordpress/android/fluxc/utils/AppLogWrapper;", "mediaUtilsWrapper", "Lorg/wordpress/android/util/MediaUtilsWrapper;", "appContext", "Landroid/content/Context;", "(Lkotlinx/coroutines/CoroutineDispatcher;Lkotlinx/coroutines/CoroutineDispatcher;Lorg/wordpress/android/ui/utils/AuthenticationUtils;Lorg/wordpress/android/fluxc/utils/AppLogWrapper;Lorg/wordpress/android/util/MediaUtilsWrapper;Landroid/content/Context;)V", "getSizeFromContentUri", "", "contentUri", "Landroid/net/Uri;", "getSizeFromURL", "url", "Ljava/net/URL;", "runIfMediaNotTooBig", "", "scope", "Lkotlinx/coroutines/CoroutineScope;", "filePath", "", "loadAction", "Lkotlin/Function0;", "fallbackAction", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public final class VideoLoader {
    private final kotlinx.coroutines.CoroutineDispatcher bgDispatcher = null;
    private final kotlinx.coroutines.CoroutineDispatcher mainDispatcher = null;
    private final org.wordpress.android.ui.utils.AuthenticationUtils authenticationUtils = null;
    private final org.wordpress.android.fluxc.utils.AppLogWrapper appLogWrapper = null;
    private final org.wordpress.android.util.MediaUtilsWrapper mediaUtilsWrapper = null;
    private final android.content.Context appContext = null;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.media.VideoLoader.Companion Companion = null;
    private static final int MIN_SIZE = 0;
    private static final int SIZE_LIMIT_10_MB = 10485760;
    
    @javax.inject.Inject()
    public VideoLoader(@org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher bgDispatcher, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "UI_THREAD")
    kotlinx.coroutines.CoroutineDispatcher mainDispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.utils.AuthenticationUtils authenticationUtils, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.utils.AppLogWrapper appLogWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.MediaUtilsWrapper mediaUtilsWrapper, @org.jetbrains.annotations.NotNull()
    android.content.Context appContext) {
        super();
    }
    
    public final void runIfMediaNotTooBig(@org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.CoroutineScope scope, @org.jetbrains.annotations.NotNull()
    java.lang.String filePath, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> loadAction, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> fallbackAction) {
    }
    
    private final int getSizeFromContentUri(android.net.Uri contentUri) {
        return 0;
    }
    
    private final int getSizeFromURL(java.net.URL url) {
        return 0;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lorg/wordpress/android/ui/media/VideoLoader$Companion;", "", "()V", "MIN_SIZE", "", "SIZE_LIMIT_10_MB", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}