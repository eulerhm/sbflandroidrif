package org.wordpress.android.ui.mediapicker.loader;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB!\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\b\u0010\t\u001a\u00020\nH\u0002J\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eJ+\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0015JV\u0010\u0016\u001a\u0004\u0018\u0001H\u0017\"\u0004\b\u0000\u0010\u00172\b\u0010\u0013\u001a\u0004\u0018\u00010\u001421\u0010\u0018\u001a-\b\u0001\u0012\u0013\u0012\u00110\u0014\u00a2\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u0013\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00170\u001c\u0012\u0006\u0012\u0004\u0018\u00010\u001d0\u0019H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001eR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006 "}, d2 = {"Lorg/wordpress/android/ui/mediapicker/loader/StockMediaDataSource;", "Lorg/wordpress/android/ui/mediapicker/loader/MediaSource;", "stockMediaStore", "Lorg/wordpress/android/fluxc/store/StockMediaStore;", "bgDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "networkUtilsWrapper", "Lorg/wordpress/android/util/NetworkUtilsWrapper;", "(Lorg/wordpress/android/fluxc/store/StockMediaStore;Lkotlinx/coroutines/CoroutineDispatcher;Lorg/wordpress/android/util/NetworkUtilsWrapper;)V", "buildDefaultScreen", "Lorg/wordpress/android/ui/mediapicker/loader/MediaSource$MediaLoadingResult;", "get", "", "Lorg/wordpress/android/ui/mediapicker/MediaItem;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "load", "forced", "", "loadMore", "filter", "", "(ZZLjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "withValidFilter", "T", "action", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "Lkotlin/coroutines/Continuation;", "", "(Ljava/lang/String;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public final class StockMediaDataSource implements org.wordpress.android.ui.mediapicker.loader.MediaSource {
    private final org.wordpress.android.fluxc.store.StockMediaStore stockMediaStore = null;
    private final kotlinx.coroutines.CoroutineDispatcher bgDispatcher = null;
    private final org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper = null;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.mediapicker.loader.StockMediaDataSource.Companion Companion = null;
    private static final int MIN_SEARCH_QUERY_SIZE = 3;
    
    @javax.inject.Inject()
    public StockMediaDataSource(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.StockMediaStore stockMediaStore, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher bgDispatcher, @org.jetbrains.annotations.NotNull()
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
    
    private final java.lang.Object get(kotlin.coroutines.Continuation<? super java.util.List<org.wordpress.android.ui.mediapicker.MediaItem>> continuation) {
        return null;
    }
    
    private final <T extends java.lang.Object>java.lang.Object withValidFilter(java.lang.String filter, kotlin.jvm.functions.Function2<? super java.lang.String, ? super kotlin.coroutines.Continuation<? super T>, ? extends java.lang.Object> action, kotlin.coroutines.Continuation<? super T> continuation) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lorg/wordpress/android/ui/mediapicker/loader/StockMediaDataSource$Companion;", "", "()V", "MIN_SEARCH_QUERY_SIZE", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}