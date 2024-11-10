package org.wordpress.android.ui.posts;

import java.lang.System;

/**
 * Helper class for separating logic related to FeaturedImage upload.
 *
 * This class is not testable at the moment, since it uses Static methods and Android dependencies.
 * However, it at least separates this piece of business logic from the view layer.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u008a\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u0000 02\u00020\u0001:\u000501234BO\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u00a2\u0006\u0002\u0010\u0014J\u001e\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cJ\u0016\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aJ\u0010\u0010\u001f\u001a\u0004\u0018\u00010 2\u0006\u0010\u0019\u001a\u00020\u001aJ\u0016\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020 J(\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010&\u001a\u00020\'2\b\u0010(\u001a\u0004\u0018\u00010)J\u0018\u0010*\u001a\u0004\u0018\u00010 2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aJ\u0010\u0010+\u001a\u00020\u00162\u0006\u0010%\u001a\u00020 H\u0002J\u0016\u0010,\u001a\u00020\u00162\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u00020$R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u00065"}, d2 = {"Lorg/wordpress/android/ui/posts/FeaturedImageHelper;", "", "uploadStore", "Lorg/wordpress/android/fluxc/store/UploadStore;", "mediaStore", "Lorg/wordpress/android/fluxc/store/MediaStore;", "uploadServiceFacade", "Lorg/wordpress/android/ui/uploads/UploadServiceFacade;", "resourceProvider", "Lorg/wordpress/android/viewmodel/ResourceProvider;", "readerUtilsWrapper", "Lorg/wordpress/android/ui/reader/utils/ReaderUtilsWrapper;", "fluxCUtilsWrapper", "Lorg/wordpress/android/util/FluxCUtilsWrapper;", "siteUtilsWrapper", "Lorg/wordpress/android/util/SiteUtilsWrapper;", "dispatcher", "Lorg/wordpress/android/fluxc/Dispatcher;", "analyticsTrackerWrapper", "Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;", "(Lorg/wordpress/android/fluxc/store/UploadStore;Lorg/wordpress/android/fluxc/store/MediaStore;Lorg/wordpress/android/ui/uploads/UploadServiceFacade;Lorg/wordpress/android/viewmodel/ResourceProvider;Lorg/wordpress/android/ui/reader/utils/ReaderUtilsWrapper;Lorg/wordpress/android/util/FluxCUtilsWrapper;Lorg/wordpress/android/util/SiteUtilsWrapper;Lorg/wordpress/android/fluxc/Dispatcher;Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;)V", "cancelFeaturedImageUpload", "", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "post", "Lorg/wordpress/android/fluxc/model/PostImmutableModel;", "cancelFailedOnly", "", "createCurrentFeaturedImageState", "Lorg/wordpress/android/ui/posts/FeaturedImageHelper$FeaturedImageData;", "getFailedFeaturedImageUpload", "Lorg/wordpress/android/fluxc/model/MediaModel;", "queueFeaturedImageForUpload", "Lorg/wordpress/android/ui/posts/FeaturedImageHelper$EnqueueFeaturedImageResult;", "localPostId", "", "media", "uri", "Landroid/net/Uri;", "mimeType", "", "retryFeaturedImageUpload", "startUploadService", "trackFeaturedImageEvent", "event", "Lorg/wordpress/android/ui/posts/FeaturedImageHelper$TrackableEvent;", "postId", "Companion", "EnqueueFeaturedImageResult", "FeaturedImageData", "FeaturedImageState", "TrackableEvent", "org.wordpress.android_wordpressJalapenoDebug"})
@dagger.Reusable()
public final class FeaturedImageHelper {
    private final org.wordpress.android.fluxc.store.UploadStore uploadStore = null;
    private final org.wordpress.android.fluxc.store.MediaStore mediaStore = null;
    private final org.wordpress.android.ui.uploads.UploadServiceFacade uploadServiceFacade = null;
    private final org.wordpress.android.viewmodel.ResourceProvider resourceProvider = null;
    private final org.wordpress.android.ui.reader.utils.ReaderUtilsWrapper readerUtilsWrapper = null;
    private final org.wordpress.android.util.FluxCUtilsWrapper fluxCUtilsWrapper = null;
    private final org.wordpress.android.util.SiteUtilsWrapper siteUtilsWrapper = null;
    private final org.wordpress.android.fluxc.Dispatcher dispatcher = null;
    private final org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTrackerWrapper = null;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.posts.FeaturedImageHelper.Companion Companion = null;
    private static final java.lang.String POST_ID_KEY = "post_id";
    
    @javax.inject.Inject()
    public FeaturedImageHelper(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.UploadStore uploadStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.MediaStore mediaStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.uploads.UploadServiceFacade uploadServiceFacade, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.ResourceProvider resourceProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.utils.ReaderUtilsWrapper readerUtilsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.FluxCUtilsWrapper fluxCUtilsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.SiteUtilsWrapper siteUtilsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.Dispatcher dispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTrackerWrapper) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final org.wordpress.android.fluxc.model.MediaModel getFailedFeaturedImageUpload(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.PostImmutableModel post) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final org.wordpress.android.fluxc.model.MediaModel retryFeaturedImageUpload(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.PostImmutableModel post) {
        return null;
    }
    
    private final void startUploadService(org.wordpress.android.fluxc.model.MediaModel media) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.posts.FeaturedImageHelper.EnqueueFeaturedImageResult queueFeaturedImageForUpload(int localPostId, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
    android.net.Uri uri, @org.jetbrains.annotations.Nullable()
    java.lang.String mimeType) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.posts.FeaturedImageHelper.EnqueueFeaturedImageResult queueFeaturedImageForUpload(int localPostId, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.MediaModel media) {
        return null;
    }
    
    public final void cancelFeaturedImageUpload(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.PostImmutableModel post, boolean cancelFailedOnly) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.posts.FeaturedImageHelper.FeaturedImageData createCurrentFeaturedImageState(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.PostImmutableModel post) {
        return null;
    }
    
    public final void trackFeaturedImageEvent(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.FeaturedImageHelper.TrackableEvent event, int postId) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u001f\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u00c6\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0011\u001a\u00020\u0012H\u00d6\u0001J\t\u0010\u0013\u001a\u00020\u0005H\u00d6\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0014"}, d2 = {"Lorg/wordpress/android/ui/posts/FeaturedImageHelper$FeaturedImageData;", "", "uiState", "Lorg/wordpress/android/ui/posts/FeaturedImageHelper$FeaturedImageState;", "mediaUri", "", "(Lorg/wordpress/android/ui/posts/FeaturedImageHelper$FeaturedImageState;Ljava/lang/String;)V", "getMediaUri", "()Ljava/lang/String;", "getUiState", "()Lorg/wordpress/android/ui/posts/FeaturedImageHelper$FeaturedImageState;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class FeaturedImageData {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.posts.FeaturedImageHelper.FeaturedImageState uiState = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String mediaUri = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.posts.FeaturedImageHelper.FeaturedImageData copy(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.posts.FeaturedImageHelper.FeaturedImageState uiState, @org.jetbrains.annotations.Nullable()
        java.lang.String mediaUri) {
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
        
        public FeaturedImageData(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.posts.FeaturedImageHelper.FeaturedImageState uiState, @org.jetbrains.annotations.Nullable()
        java.lang.String mediaUri) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.posts.FeaturedImageHelper.FeaturedImageState component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.posts.FeaturedImageHelper.FeaturedImageState getUiState() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component2() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getMediaUri() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0011\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B9\b\u0002\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\nR\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\nj\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013\u00a8\u0006\u0014"}, d2 = {"Lorg/wordpress/android/ui/posts/FeaturedImageHelper$FeaturedImageState;", "", "buttonVisible", "", "imageViewVisible", "localImageViewVisible", "progressOverlayVisible", "retryOverlayVisible", "(Ljava/lang/String;IZZZZZ)V", "getButtonVisible", "()Z", "getImageViewVisible", "getLocalImageViewVisible", "getProgressOverlayVisible", "getRetryOverlayVisible", "IMAGE_EMPTY", "REMOTE_IMAGE_LOADING", "REMOTE_IMAGE_SET", "IMAGE_UPLOAD_IN_PROGRESS", "IMAGE_UPLOAD_FAILED", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum FeaturedImageState {
        /*public static final*/ IMAGE_EMPTY /* = new IMAGE_EMPTY(false, false, false, false, false) */,
        /*public static final*/ REMOTE_IMAGE_LOADING /* = new REMOTE_IMAGE_LOADING(false, false, false, false, false) */,
        /*public static final*/ REMOTE_IMAGE_SET /* = new REMOTE_IMAGE_SET(false, false, false, false, false) */,
        /*public static final*/ IMAGE_UPLOAD_IN_PROGRESS /* = new IMAGE_UPLOAD_IN_PROGRESS(false, false, false, false, false) */,
        /*public static final*/ IMAGE_UPLOAD_FAILED /* = new IMAGE_UPLOAD_FAILED(false, false, false, false, false) */;
        private final boolean buttonVisible = false;
        private final boolean imageViewVisible = false;
        private final boolean localImageViewVisible = false;
        private final boolean progressOverlayVisible = false;
        private final boolean retryOverlayVisible = false;
        
        FeaturedImageState(boolean buttonVisible, boolean imageViewVisible, boolean localImageViewVisible, boolean progressOverlayVisible, boolean retryOverlayVisible) {
        }
        
        public final boolean getButtonVisible() {
            return false;
        }
        
        public final boolean getImageViewVisible() {
            return false;
        }
        
        public final boolean getLocalImageViewVisible() {
            return false;
        }
        
        public final boolean getProgressOverlayVisible() {
            return false;
        }
        
        public final boolean getRetryOverlayVisible() {
            return false;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005\u00a8\u0006\u0006"}, d2 = {"Lorg/wordpress/android/ui/posts/FeaturedImageHelper$EnqueueFeaturedImageResult;", "", "(Ljava/lang/String;I)V", "FILE_NOT_FOUND", "INVALID_POST_ID", "SUCCESS", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum EnqueueFeaturedImageResult {
        /*public static final*/ FILE_NOT_FOUND /* = new FILE_NOT_FOUND() */,
        /*public static final*/ INVALID_POST_ID /* = new INVALID_POST_ID() */,
        /*public static final*/ SUCCESS /* = new SUCCESS() */;
        
        EnqueueFeaturedImageResult() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\r\u00a8\u0006\u000e"}, d2 = {"Lorg/wordpress/android/ui/posts/FeaturedImageHelper$TrackableEvent;", "", "label", "Lorg/wordpress/android/analytics/AnalyticsTracker$Stat;", "(Ljava/lang/String;ILorg/wordpress/android/analytics/AnalyticsTracker$Stat;)V", "getLabel", "()Lorg/wordpress/android/analytics/AnalyticsTracker$Stat;", "IMAGE_SET_CLICKED", "IMAGE_PICKED_POST_SETTINGS", "IMAGE_PICKED_GUTENBERG_EDITOR", "IMAGE_REMOVED_GUTENBERG_EDITOR", "IMAGE_UPLOAD_CANCELED", "IMAGE_UPLOAD_RETRY_CLICKED", "IMAGE_REMOVE_CLICKED", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum TrackableEvent {
        /*public static final*/ IMAGE_SET_CLICKED /* = new IMAGE_SET_CLICKED(null) */,
        /*public static final*/ IMAGE_PICKED_POST_SETTINGS /* = new IMAGE_PICKED_POST_SETTINGS(null) */,
        /*public static final*/ IMAGE_PICKED_GUTENBERG_EDITOR /* = new IMAGE_PICKED_GUTENBERG_EDITOR(null) */,
        /*public static final*/ IMAGE_REMOVED_GUTENBERG_EDITOR /* = new IMAGE_REMOVED_GUTENBERG_EDITOR(null) */,
        /*public static final*/ IMAGE_UPLOAD_CANCELED /* = new IMAGE_UPLOAD_CANCELED(null) */,
        /*public static final*/ IMAGE_UPLOAD_RETRY_CLICKED /* = new IMAGE_UPLOAD_RETRY_CLICKED(null) */,
        /*public static final*/ IMAGE_REMOVE_CLICKED /* = new IMAGE_REMOVE_CLICKED(null) */;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.analytics.AnalyticsTracker.Stat label = null;
        
        TrackableEvent(org.wordpress.android.analytics.AnalyticsTracker.Stat label) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.analytics.AnalyticsTracker.Stat getLabel() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lorg/wordpress/android/ui/posts/FeaturedImageHelper$Companion;", "", "()V", "POST_ID_KEY", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}