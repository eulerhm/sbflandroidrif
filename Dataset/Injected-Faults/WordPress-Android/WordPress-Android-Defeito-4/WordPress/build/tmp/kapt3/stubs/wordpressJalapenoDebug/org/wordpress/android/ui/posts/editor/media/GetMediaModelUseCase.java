package org.wordpress.android.ui.posts.editor.media;

import java.lang.System;

/**
 * Helper class for retrieving/creating MediaModel from the provided data.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u001c\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001:\u0001,B9\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\b\b\u0001\u0010\f\u001a\u00020\r\u00a2\u0006\u0002\u0010\u000eJ!\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0015J\'\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00140\u0017H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0018J\u001a\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u0012\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J%\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00172\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00120\u001fH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010 J-\u0010!\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00172\u0006\u0010\"\u001a\u00020#2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020%0\u001fH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010&J\u0018\u0010\'\u001a\u00020(2\u0006\u0010)\u001a\u00020\u001a2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u0010\u0010*\u001a\u00020+2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006-"}, d2 = {"Lorg/wordpress/android/ui/posts/editor/media/GetMediaModelUseCase;", "", "fluxCUtilsWrapper", "Lorg/wordpress/android/util/FluxCUtilsWrapper;", "mediaUtilsWrapper", "Lorg/wordpress/android/util/MediaUtilsWrapper;", "mediaStore", "Lorg/wordpress/android/fluxc/store/MediaStore;", "fileProvider", "Lorg/wordpress/android/util/FileProvider;", "authenticationUtils", "Lorg/wordpress/android/ui/utils/AuthenticationUtils;", "bgDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Lorg/wordpress/android/util/FluxCUtilsWrapper;Lorg/wordpress/android/util/MediaUtilsWrapper;Lorg/wordpress/android/fluxc/store/MediaStore;Lorg/wordpress/android/util/FileProvider;Lorg/wordpress/android/ui/utils/AuthenticationUtils;Lkotlinx/coroutines/CoroutineDispatcher;)V", "createMediaModelFromUri", "Lorg/wordpress/android/ui/posts/editor/media/GetMediaModelUseCase$CreateMediaModelsResult;", "localSiteId", "", "uri", "Landroid/net/Uri;", "(ILandroid/net/Uri;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "uris", "", "(ILjava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createNewMediaModel", "Lorg/wordpress/android/fluxc/model/MediaModel;", "createVideoThumbnail", "", "loadMediaByLocalId", "mediaModelLocalIds", "", "(Ljava/lang/Iterable;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "loadMediaByRemoteId", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "mediaModelsRemoteIds", "", "(Lorg/wordpress/android/fluxc/model/SiteModel;Ljava/lang/Iterable;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setThumbnailIfAvailable", "", "media", "verifyFileExists", "", "CreateMediaModelsResult", "org.wordpress.android_wordpressJalapenoDebug"})
@dagger.Reusable()
public final class GetMediaModelUseCase {
    private final org.wordpress.android.util.FluxCUtilsWrapper fluxCUtilsWrapper = null;
    private final org.wordpress.android.util.MediaUtilsWrapper mediaUtilsWrapper = null;
    private final org.wordpress.android.fluxc.store.MediaStore mediaStore = null;
    private final org.wordpress.android.util.FileProvider fileProvider = null;
    private final org.wordpress.android.ui.utils.AuthenticationUtils authenticationUtils = null;
    private final kotlinx.coroutines.CoroutineDispatcher bgDispatcher = null;
    
    @javax.inject.Inject()
    public GetMediaModelUseCase(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.FluxCUtilsWrapper fluxCUtilsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.MediaUtilsWrapper mediaUtilsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.MediaStore mediaStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.FileProvider fileProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.utils.AuthenticationUtils authenticationUtils, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher bgDispatcher) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object loadMediaByLocalId(@org.jetbrains.annotations.NotNull()
    java.lang.Iterable<java.lang.Integer> mediaModelLocalIds, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<? extends org.wordpress.android.fluxc.model.MediaModel>> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object loadMediaByRemoteId(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
    java.lang.Iterable<java.lang.Long> mediaModelsRemoteIds, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<? extends org.wordpress.android.fluxc.model.MediaModel>> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object createMediaModelFromUri(int localSiteId, @org.jetbrains.annotations.NotNull()
    android.net.Uri uri, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.wordpress.android.ui.posts.editor.media.GetMediaModelUseCase.CreateMediaModelsResult> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object createMediaModelFromUri(int localSiteId, @org.jetbrains.annotations.NotNull()
    java.util.List<? extends android.net.Uri> uris, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.wordpress.android.ui.posts.editor.media.GetMediaModelUseCase.CreateMediaModelsResult> continuation) {
        return null;
    }
    
    private final org.wordpress.android.fluxc.model.MediaModel createNewMediaModel(int localSiteId, android.net.Uri uri) {
        return null;
    }
    
    private final void setThumbnailIfAvailable(org.wordpress.android.fluxc.model.MediaModel media, android.net.Uri uri) {
    }
    
    private final java.lang.String createVideoThumbnail(android.net.Uri uri) {
        return null;
    }
    
    private final boolean verifyFileExists(android.net.Uri uri) {
        return false;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\u000f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\t\u0010\r\u001a\u00020\u0006H\u00c6\u0003J#\u0010\u000e\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u00c6\u0001J\u0013\u0010\u000f\u001a\u00020\u00062\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0011\u001a\u00020\u0012H\u00d6\u0001J\t\u0010\u0013\u001a\u00020\u0014H\u00d6\u0001R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0015"}, d2 = {"Lorg/wordpress/android/ui/posts/editor/media/GetMediaModelUseCase$CreateMediaModelsResult;", "", "mediaModels", "", "Lorg/wordpress/android/fluxc/model/MediaModel;", "loadingSomeMediaFailed", "", "(Ljava/util/List;Z)V", "getLoadingSomeMediaFailed", "()Z", "getMediaModels", "()Ljava/util/List;", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class CreateMediaModelsResult {
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<org.wordpress.android.fluxc.model.MediaModel> mediaModels = null;
        private final boolean loadingSomeMediaFailed = false;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.posts.editor.media.GetMediaModelUseCase.CreateMediaModelsResult copy(@org.jetbrains.annotations.NotNull()
        java.util.List<? extends org.wordpress.android.fluxc.model.MediaModel> mediaModels, boolean loadingSomeMediaFailed) {
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
        
        public CreateMediaModelsResult(@org.jetbrains.annotations.NotNull()
        java.util.List<? extends org.wordpress.android.fluxc.model.MediaModel> mediaModels, boolean loadingSomeMediaFailed) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.fluxc.model.MediaModel> component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.fluxc.model.MediaModel> getMediaModels() {
            return null;
        }
        
        public final boolean component2() {
            return false;
        }
        
        public final boolean getLoadingSomeMediaFailed() {
            return false;
        }
    }
}