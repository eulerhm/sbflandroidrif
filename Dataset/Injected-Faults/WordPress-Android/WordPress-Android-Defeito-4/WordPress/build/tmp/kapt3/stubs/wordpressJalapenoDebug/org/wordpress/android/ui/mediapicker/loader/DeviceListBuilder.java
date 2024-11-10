package org.wordpress.android.ui.mediapicker.loader;

import java.lang.System;

@kotlin.Suppress(names = {"LongParameterList"})
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\u0018\u00002\u00020\u00012\u00020\u0002:\u0002/0BG\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u0012\b\b\u0001\u0010\u000b\u001a\u00020\f\u0012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u00a2\u0006\u0002\u0010\u0012J-\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u000f2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001e2\b\u0010 \u001a\u0004\u0018\u00010!H\u0002\u00a2\u0006\u0002\u0010\"J+\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&2\u0006\u0010\'\u001a\u00020&2\b\u0010(\u001a\u0004\u0018\u00010)H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010*J\u001d\u0010+\u001a\u0004\u0018\u00010\u00152\b\u0010(\u001a\u0004\u0018\u00010)H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010,J\u001c\u0010-\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u001c\u001a\u00020\u000f2\b\u0010(\u001a\u0004\u0018\u00010)H\u0002J\u000e\u0010.\u001a\u00020&*\u0004\u0018\u00010\u0015H\u0002R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00150\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u00061"}, d2 = {"Lorg/wordpress/android/ui/mediapicker/loader/DeviceListBuilder;", "Lorg/wordpress/android/ui/mediapicker/loader/MediaSource;", "Lorg/wordpress/android/ui/mediapicker/loader/MediaSourceWithTypes;", "localeManagerWrapper", "Lorg/wordpress/android/util/LocaleManagerWrapper;", "deviceMediaLoader", "Lorg/wordpress/android/ui/mediapicker/loader/DeviceMediaLoader;", "mediaUtilsWrapper", "Lorg/wordpress/android/util/MediaUtilsWrapper;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "bgDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "mediaTypes", "", "Lorg/wordpress/android/ui/mediapicker/MediaType;", "pageSize", "", "(Lorg/wordpress/android/util/LocaleManagerWrapper;Lorg/wordpress/android/ui/mediapicker/loader/DeviceMediaLoader;Lorg/wordpress/android/util/MediaUtilsWrapper;Lorg/wordpress/android/fluxc/model/SiteModel;Lkotlinx/coroutines/CoroutineDispatcher;Ljava/util/Set;I)V", "cache", "", "Lorg/wordpress/android/ui/mediapicker/loader/DeviceListBuilder$Result;", "getMediaTypes", "()Ljava/util/Set;", "mimeTypes", "Lorg/wordpress/android/fluxc/utils/MimeTypes;", "addPage", "", "mediaType", "page", "", "Lorg/wordpress/android/ui/mediapicker/MediaItem;", "nextTimestamp", "", "(Lorg/wordpress/android/ui/mediapicker/MediaType;Ljava/util/List;Ljava/lang/Long;)V", "load", "Lorg/wordpress/android/ui/mediapicker/loader/MediaSource$MediaLoadingResult;", "forced", "", "loadMore", "filter", "", "(ZZLjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "loadDownloads", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "loadMedia", "shouldLoadMoreData", "DeviceListBuilderFactory", "Result", "org.wordpress.android_wordpressJalapenoDebug"})
public final class DeviceListBuilder implements org.wordpress.android.ui.mediapicker.loader.MediaSource, org.wordpress.android.ui.mediapicker.loader.MediaSourceWithTypes {
    private final org.wordpress.android.util.LocaleManagerWrapper localeManagerWrapper = null;
    private final org.wordpress.android.ui.mediapicker.loader.DeviceMediaLoader deviceMediaLoader = null;
    private final org.wordpress.android.util.MediaUtilsWrapper mediaUtilsWrapper = null;
    private final org.wordpress.android.fluxc.model.SiteModel site = null;
    private final kotlinx.coroutines.CoroutineDispatcher bgDispatcher = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.Set<org.wordpress.android.ui.mediapicker.MediaType> mediaTypes = null;
    private final int pageSize = 0;
    private final org.wordpress.android.fluxc.utils.MimeTypes mimeTypes = null;
    private final java.util.Map<org.wordpress.android.ui.mediapicker.MediaType, org.wordpress.android.ui.mediapicker.loader.DeviceListBuilder.Result> cache = null;
    
    public DeviceListBuilder(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.LocaleManagerWrapper localeManagerWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mediapicker.loader.DeviceMediaLoader deviceMediaLoader, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.MediaUtilsWrapper mediaUtilsWrapper, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher bgDispatcher, @org.jetbrains.annotations.NotNull()
    java.util.Set<? extends org.wordpress.android.ui.mediapicker.MediaType> mediaTypes, int pageSize) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.util.Set<org.wordpress.android.ui.mediapicker.MediaType> getMediaTypes() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object load(boolean forced, boolean loadMore, @org.jetbrains.annotations.Nullable()
    java.lang.String filter, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.wordpress.android.ui.mediapicker.loader.MediaSource.MediaLoadingResult> continuation) {
        return null;
    }
    
    private final org.wordpress.android.ui.mediapicker.loader.DeviceListBuilder.Result loadMedia(org.wordpress.android.ui.mediapicker.MediaType mediaType, java.lang.String filter) {
        return null;
    }
    
    private final java.lang.Object loadDownloads(java.lang.String filter, kotlin.coroutines.Continuation<? super org.wordpress.android.ui.mediapicker.loader.DeviceListBuilder.Result> continuation) {
        return null;
    }
    
    private final void addPage(org.wordpress.android.ui.mediapicker.MediaType mediaType, java.util.List<org.wordpress.android.ui.mediapicker.MediaItem> page, java.lang.Long nextTimestamp) {
    }
    
    private final boolean shouldLoadMoreData(org.wordpress.android.ui.mediapicker.loader.DeviceListBuilder.Result $this$shouldLoadMoreData) {
        return false;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B)\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\tJ\u000f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003\u00a2\u0006\u0002\u0010\rJ\t\u0010\u0013\u001a\u00020\bH\u00c6\u0003J4\u0010\u0014\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bH\u00c6\u0001\u00a2\u0006\u0002\u0010\u0015J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0019\u001a\u00020\bH\u00d6\u0001J\t\u0010\u001a\u001a\u00020\u001bH\u00d6\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010\u00a8\u0006\u001c"}, d2 = {"Lorg/wordpress/android/ui/mediapicker/loader/DeviceListBuilder$Result;", "", "items", "", "Lorg/wordpress/android/ui/mediapicker/MediaItem;", "nextTimestamp", "", "visibleItems", "", "(Ljava/util/List;Ljava/lang/Long;I)V", "getItems", "()Ljava/util/List;", "getNextTimestamp", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getVisibleItems", "()I", "component1", "component2", "component3", "copy", "(Ljava/util/List;Ljava/lang/Long;I)Lorg/wordpress/android/ui/mediapicker/loader/DeviceListBuilder$Result;", "equals", "", "other", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Result {
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<org.wordpress.android.ui.mediapicker.MediaItem> items = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Long nextTimestamp = null;
        private final int visibleItems = 0;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mediapicker.loader.DeviceListBuilder.Result copy(@org.jetbrains.annotations.NotNull()
        java.util.List<org.wordpress.android.ui.mediapicker.MediaItem> items, @org.jetbrains.annotations.Nullable()
        java.lang.Long nextTimestamp, int visibleItems) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.lang.String toString() {
            return null;
        }
        
        public Result(@org.jetbrains.annotations.NotNull()
        java.util.List<org.wordpress.android.ui.mediapicker.MediaItem> items, @org.jetbrains.annotations.Nullable()
        java.lang.Long nextTimestamp, int visibleItems) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.ui.mediapicker.MediaItem> component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.ui.mediapicker.MediaItem> getItems() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Long component2() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Long getNextTimestamp() {
            return null;
        }
        
        public final int component3() {
            return 0;
        }
        
        public final int getVisibleItems() {
            return 0;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B)\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0001\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u001e\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lorg/wordpress/android/ui/mediapicker/loader/DeviceListBuilder$DeviceListBuilderFactory;", "", "localeManagerWrapper", "Lorg/wordpress/android/util/LocaleManagerWrapper;", "deviceMediaLoader", "Lorg/wordpress/android/ui/mediapicker/loader/DeviceMediaLoader;", "mediaUtilsWrapper", "Lorg/wordpress/android/util/MediaUtilsWrapper;", "bgDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Lorg/wordpress/android/util/LocaleManagerWrapper;Lorg/wordpress/android/ui/mediapicker/loader/DeviceMediaLoader;Lorg/wordpress/android/util/MediaUtilsWrapper;Lkotlinx/coroutines/CoroutineDispatcher;)V", "build", "Lorg/wordpress/android/ui/mediapicker/loader/DeviceListBuilder;", "mediaTypes", "", "Lorg/wordpress/android/ui/mediapicker/MediaType;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class DeviceListBuilderFactory {
        private final org.wordpress.android.util.LocaleManagerWrapper localeManagerWrapper = null;
        private final org.wordpress.android.ui.mediapicker.loader.DeviceMediaLoader deviceMediaLoader = null;
        private final org.wordpress.android.util.MediaUtilsWrapper mediaUtilsWrapper = null;
        private final kotlinx.coroutines.CoroutineDispatcher bgDispatcher = null;
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.ui.mediapicker.loader.DeviceListBuilder.DeviceListBuilderFactory.Companion Companion = null;
        private static final int PAGE_SIZE = 32;
        
        @javax.inject.Inject()
        public DeviceListBuilderFactory(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.util.LocaleManagerWrapper localeManagerWrapper, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.mediapicker.loader.DeviceMediaLoader deviceMediaLoader, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.util.MediaUtilsWrapper mediaUtilsWrapper, @org.jetbrains.annotations.NotNull()
        @javax.inject.Named(value = "BG_THREAD")
        kotlinx.coroutines.CoroutineDispatcher bgDispatcher) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mediapicker.loader.DeviceListBuilder build(@org.jetbrains.annotations.NotNull()
        java.util.Set<? extends org.wordpress.android.ui.mediapicker.MediaType> mediaTypes, @org.jetbrains.annotations.Nullable()
        org.wordpress.android.fluxc.model.SiteModel site) {
            return null;
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lorg/wordpress/android/ui/mediapicker/loader/DeviceListBuilder$DeviceListBuilderFactory$Companion;", "", "()V", "PAGE_SIZE", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Companion {
            
            private Companion() {
                super();
            }
        }
    }
}