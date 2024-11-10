package org.wordpress.android.ui.mediapicker.insert;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001#B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u00a2\u0006\u0002\u0010\u0010J(\u0010\u0015\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00170\u00162\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0004\u001a\u00020\u0005H\u0002J%\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001d2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020!0 H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\"R\u0014\u0010\u0011\u001a\u00020\u00128VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006$"}, d2 = {"Lorg/wordpress/android/ui/mediapicker/insert/GifMediaInsertUseCase;", "Lorg/wordpress/android/ui/mediapicker/insert/MediaInsertUseCase;", "context", "Landroid/content/Context;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "dispatcher", "Lorg/wordpress/android/fluxc/Dispatcher;", "ioDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "wpMediaUtilsWrapper", "Lorg/wordpress/android/util/WPMediaUtilsWrapper;", "fluxCUtilsWrapper", "Lorg/wordpress/android/util/FluxCUtilsWrapper;", "mimeTypeMapUtilsWrapper", "Lorg/wordpress/android/util/MimeTypeMapUtilsWrapper;", "(Landroid/content/Context;Lorg/wordpress/android/fluxc/model/SiteModel;Lorg/wordpress/android/fluxc/Dispatcher;Lkotlinx/coroutines/CoroutineDispatcher;Lorg/wordpress/android/util/WPMediaUtilsWrapper;Lorg/wordpress/android/util/FluxCUtilsWrapper;Lorg/wordpress/android/util/MimeTypeMapUtilsWrapper;)V", "actionTitle", "", "getActionTitle", "()I", "fetchAndSaveAsync", "Lkotlinx/coroutines/Deferred;", "Lorg/wordpress/android/ui/mediapicker/MediaItem$Identifier$LocalId;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "gifIdentifier", "Lorg/wordpress/android/ui/mediapicker/MediaItem$Identifier$GifMediaIdentifier;", "insert", "Lkotlinx/coroutines/flow/Flow;", "Lorg/wordpress/android/ui/mediapicker/insert/MediaInsertHandler$InsertModel;", "identifiers", "", "Lorg/wordpress/android/ui/mediapicker/MediaItem$Identifier;", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "GifMediaInsertUseCaseFactory", "org.wordpress.android_wordpressJalapenoDebug"})
public final class GifMediaInsertUseCase implements org.wordpress.android.ui.mediapicker.insert.MediaInsertUseCase {
    private final android.content.Context context = null;
    private final org.wordpress.android.fluxc.model.SiteModel site = null;
    private final org.wordpress.android.fluxc.Dispatcher dispatcher = null;
    private final kotlinx.coroutines.CoroutineDispatcher ioDispatcher = null;
    private final org.wordpress.android.util.WPMediaUtilsWrapper wpMediaUtilsWrapper = null;
    private final org.wordpress.android.util.FluxCUtilsWrapper fluxCUtilsWrapper = null;
    private final org.wordpress.android.util.MimeTypeMapUtilsWrapper mimeTypeMapUtilsWrapper = null;
    
    public GifMediaInsertUseCase(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.Dispatcher dispatcher, @org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.CoroutineDispatcher ioDispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.WPMediaUtilsWrapper wpMediaUtilsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.FluxCUtilsWrapper fluxCUtilsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.MimeTypeMapUtilsWrapper mimeTypeMapUtilsWrapper) {
        super();
    }
    
    @java.lang.Override()
    public int getActionTitle() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    java.util.List<? extends org.wordpress.android.ui.mediapicker.MediaItem.Identifier> identifiers, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<? extends org.wordpress.android.ui.mediapicker.insert.MediaInsertHandler.InsertModel>> continuation) {
        return null;
    }
    
    private final kotlinx.coroutines.Deferred<org.wordpress.android.ui.mediapicker.MediaItem.Identifier.LocalId> fetchAndSaveAsync(kotlinx.coroutines.CoroutineScope scope, org.wordpress.android.ui.mediapicker.MediaItem.Identifier.GifMediaIdentifier gifIdentifier, org.wordpress.android.fluxc.model.SiteModel site) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B9\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u00a2\u0006\u0002\u0010\u000eJ\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lorg/wordpress/android/ui/mediapicker/insert/GifMediaInsertUseCase$GifMediaInsertUseCaseFactory;", "", "context", "Landroid/content/Context;", "dispatcher", "Lorg/wordpress/android/fluxc/Dispatcher;", "ioDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "wpMediaUtilsWrapper", "Lorg/wordpress/android/util/WPMediaUtilsWrapper;", "fluxCUtilsWrapper", "Lorg/wordpress/android/util/FluxCUtilsWrapper;", "mimeTypeMapUtilsWrapper", "Lorg/wordpress/android/util/MimeTypeMapUtilsWrapper;", "(Landroid/content/Context;Lorg/wordpress/android/fluxc/Dispatcher;Lkotlinx/coroutines/CoroutineDispatcher;Lorg/wordpress/android/util/WPMediaUtilsWrapper;Lorg/wordpress/android/util/FluxCUtilsWrapper;Lorg/wordpress/android/util/MimeTypeMapUtilsWrapper;)V", "build", "Lorg/wordpress/android/ui/mediapicker/insert/GifMediaInsertUseCase;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class GifMediaInsertUseCaseFactory {
        private final android.content.Context context = null;
        private final org.wordpress.android.fluxc.Dispatcher dispatcher = null;
        private final kotlinx.coroutines.CoroutineDispatcher ioDispatcher = null;
        private final org.wordpress.android.util.WPMediaUtilsWrapper wpMediaUtilsWrapper = null;
        private final org.wordpress.android.util.FluxCUtilsWrapper fluxCUtilsWrapper = null;
        private final org.wordpress.android.util.MimeTypeMapUtilsWrapper mimeTypeMapUtilsWrapper = null;
        
        @javax.inject.Inject()
        public GifMediaInsertUseCaseFactory(@org.jetbrains.annotations.NotNull()
        android.content.Context context, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.Dispatcher dispatcher, @org.jetbrains.annotations.NotNull()
        @javax.inject.Named(value = "IO_THREAD")
        kotlinx.coroutines.CoroutineDispatcher ioDispatcher, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.util.WPMediaUtilsWrapper wpMediaUtilsWrapper, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.util.FluxCUtilsWrapper fluxCUtilsWrapper, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.util.MimeTypeMapUtilsWrapper mimeTypeMapUtilsWrapper) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mediapicker.insert.GifMediaInsertUseCase build(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.SiteModel site) {
            return null;
        }
    }
}