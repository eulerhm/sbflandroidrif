package org.wordpress.android.ui.mediapicker.loader;

import java.lang.System;

@kotlin.Suppress(names = {"LongParameterList"})
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 12\u00020\u00012\u00020\u0002:\u000212BE\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0001\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010\u00a2\u0006\u0002\u0010\u0012J/\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001fJ\u0016\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\u0006\u0010!\u001a\u00020\u0011H\u0002J+\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020%2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\'J)\u0010(\u001a\u00020\u00172\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010&\u001a\u00020%2\u0006\u0010\u001d\u001a\u00020\u0015H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010)J\u0010\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020\u0017H\u0007J\u001e\u0010-\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\u0006\u0010!\u001a\u00020\u00112\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J \u0010.\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b*\b\u0012\u0004\u0012\u00020/0\u001b2\u0006\u0010!\u001a\u00020\u0011H\u0002J\f\u00100\u001a\u00020\u0015*\u00020\u0011H\u0002R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\u0013\u001a\u0014\u0012\u0004\u0012\u00020\u0015\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u00160\u0014X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u00063"}, d2 = {"Lorg/wordpress/android/ui/mediapicker/loader/MediaLibraryDataSource;", "Lorg/wordpress/android/ui/mediapicker/loader/MediaSource;", "Lorg/wordpress/android/ui/mediapicker/loader/MediaSourceWithTypes;", "mediaStore", "Lorg/wordpress/android/fluxc/store/MediaStore;", "dispatcher", "Lorg/wordpress/android/fluxc/Dispatcher;", "bgDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "networkUtilsWrapper", "Lorg/wordpress/android/util/NetworkUtilsWrapper;", "dateTimeUtilsWrapper", "Lorg/wordpress/android/util/DateTimeUtilsWrapper;", "siteModel", "Lorg/wordpress/android/fluxc/model/SiteModel;", "mediaTypes", "", "Lorg/wordpress/android/ui/mediapicker/MediaType;", "(Lorg/wordpress/android/fluxc/store/MediaStore;Lorg/wordpress/android/fluxc/Dispatcher;Lkotlinx/coroutines/CoroutineDispatcher;Lorg/wordpress/android/util/NetworkUtilsWrapper;Lorg/wordpress/android/util/DateTimeUtilsWrapper;Lorg/wordpress/android/fluxc/model/SiteModel;Ljava/util/Set;)V", "loadContinuations", "", "Lorg/wordpress/android/fluxc/utils/MimeType$Type;", "Lkotlin/coroutines/Continuation;", "Lorg/wordpress/android/fluxc/store/MediaStore$OnMediaListFetched;", "getMediaTypes", "()Ljava/util/Set;", "get", "", "Lorg/wordpress/android/ui/mediapicker/MediaItem;", "filter", "", "(Ljava/util/Set;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getFromDatabase", "mediaType", "load", "Lorg/wordpress/android/ui/mediapicker/loader/MediaSource$MediaLoadingResult;", "forced", "", "loadMore", "(ZZLjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "loadPage", "(Lorg/wordpress/android/fluxc/model/SiteModel;ZLorg/wordpress/android/fluxc/utils/MimeType$Type;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onMediaListFetched", "", "event", "searchInDatabase", "toMediaItems", "Lorg/wordpress/android/fluxc/model/MediaModel;", "toMimeType", "Companion", "MediaLibraryDataSourceFactory", "org.wordpress.android_wordpressJalapenoDebug"})
public final class MediaLibraryDataSource implements org.wordpress.android.ui.mediapicker.loader.MediaSource, org.wordpress.android.ui.mediapicker.loader.MediaSourceWithTypes {
    private final org.wordpress.android.fluxc.store.MediaStore mediaStore = null;
    private final org.wordpress.android.fluxc.Dispatcher dispatcher = null;
    private final kotlinx.coroutines.CoroutineDispatcher bgDispatcher = null;
    private final org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper = null;
    private final org.wordpress.android.util.DateTimeUtilsWrapper dateTimeUtilsWrapper = null;
    private final org.wordpress.android.fluxc.model.SiteModel siteModel = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.Set<org.wordpress.android.ui.mediapicker.MediaType> mediaTypes = null;
    private java.util.Map<org.wordpress.android.fluxc.utils.MimeType.Type, kotlin.coroutines.Continuation<org.wordpress.android.fluxc.store.MediaStore.OnMediaListFetched>> loadContinuations;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.mediapicker.loader.MediaLibraryDataSource.Companion Companion = null;
    public static final int NUM_MEDIA_PER_FETCH = 24;
    
    public MediaLibraryDataSource(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.MediaStore mediaStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.Dispatcher dispatcher, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher bgDispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.DateTimeUtilsWrapper dateTimeUtilsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel siteModel, @org.jetbrains.annotations.NotNull()
    java.util.Set<? extends org.wordpress.android.ui.mediapicker.MediaType> mediaTypes) {
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
    
    private final java.lang.Object get(java.util.Set<? extends org.wordpress.android.ui.mediapicker.MediaType> mediaTypes, java.lang.String filter, kotlin.coroutines.Continuation<? super java.util.List<org.wordpress.android.ui.mediapicker.MediaItem>> continuation) {
        return null;
    }
    
    private final java.util.List<org.wordpress.android.ui.mediapicker.MediaItem> toMediaItems(java.util.List<? extends org.wordpress.android.fluxc.model.MediaModel> $this$toMediaItems, org.wordpress.android.ui.mediapicker.MediaType mediaType) {
        return null;
    }
    
    private final java.util.List<org.wordpress.android.ui.mediapicker.MediaItem> getFromDatabase(org.wordpress.android.ui.mediapicker.MediaType mediaType) {
        return null;
    }
    
    private final java.util.List<org.wordpress.android.ui.mediapicker.MediaItem> searchInDatabase(org.wordpress.android.ui.mediapicker.MediaType mediaType, java.lang.String filter) {
        return null;
    }
    
    private final java.lang.Object loadPage(org.wordpress.android.fluxc.model.SiteModel siteModel, boolean loadMore, org.wordpress.android.fluxc.utils.MimeType.Type filter, kotlin.coroutines.Continuation<? super org.wordpress.android.fluxc.store.MediaStore.OnMediaListFetched> continuation) {
        return null;
    }
    
    private final org.wordpress.android.fluxc.utils.MimeType.Type toMimeType(org.wordpress.android.ui.mediapicker.MediaType $this$toMimeType) {
        return null;
    }
    
    @org.greenrobot.eventbus.Subscribe(threadMode = org.greenrobot.eventbus.ThreadMode.MAIN)
    public final void onMediaListFetched(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.MediaStore.OnMediaListFetched event) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B1\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0002\u0010\fJ\u001c\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lorg/wordpress/android/ui/mediapicker/loader/MediaLibraryDataSource$MediaLibraryDataSourceFactory;", "", "mediaStore", "Lorg/wordpress/android/fluxc/store/MediaStore;", "dispatcher", "Lorg/wordpress/android/fluxc/Dispatcher;", "bgDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "networkUtilsWrapper", "Lorg/wordpress/android/util/NetworkUtilsWrapper;", "dateTimeUtilsWrapper", "Lorg/wordpress/android/util/DateTimeUtilsWrapper;", "(Lorg/wordpress/android/fluxc/store/MediaStore;Lorg/wordpress/android/fluxc/Dispatcher;Lkotlinx/coroutines/CoroutineDispatcher;Lorg/wordpress/android/util/NetworkUtilsWrapper;Lorg/wordpress/android/util/DateTimeUtilsWrapper;)V", "build", "Lorg/wordpress/android/ui/mediapicker/loader/MediaLibraryDataSource;", "siteModel", "Lorg/wordpress/android/fluxc/model/SiteModel;", "mediaTypes", "", "Lorg/wordpress/android/ui/mediapicker/MediaType;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class MediaLibraryDataSourceFactory {
        private final org.wordpress.android.fluxc.store.MediaStore mediaStore = null;
        private final org.wordpress.android.fluxc.Dispatcher dispatcher = null;
        private final kotlinx.coroutines.CoroutineDispatcher bgDispatcher = null;
        private final org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper = null;
        private final org.wordpress.android.util.DateTimeUtilsWrapper dateTimeUtilsWrapper = null;
        
        @javax.inject.Inject()
        public MediaLibraryDataSourceFactory(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.store.MediaStore mediaStore, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.Dispatcher dispatcher, @org.jetbrains.annotations.NotNull()
        @javax.inject.Named(value = "BG_THREAD")
        kotlinx.coroutines.CoroutineDispatcher bgDispatcher, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.util.DateTimeUtilsWrapper dateTimeUtilsWrapper) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mediapicker.loader.MediaLibraryDataSource build(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.SiteModel siteModel, @org.jetbrains.annotations.NotNull()
        java.util.Set<? extends org.wordpress.android.ui.mediapicker.MediaType> mediaTypes) {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lorg/wordpress/android/ui/mediapicker/loader/MediaLibraryDataSource$Companion;", "", "()V", "NUM_MEDIA_PER_FETCH", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}