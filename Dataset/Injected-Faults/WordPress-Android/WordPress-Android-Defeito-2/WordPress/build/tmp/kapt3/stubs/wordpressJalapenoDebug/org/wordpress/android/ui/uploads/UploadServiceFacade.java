package org.wordpress.android.ui.uploads;

import java.lang.System;

/**
 * An injectable class built on top of [UploadService].
 *
 * The main purpose of this is to provide testability for classes that use [UploadService]. This should never
 * contain any static methods.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000bJ\u0010\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\rJ\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0010\u001a\u00020\rJ\u000e\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0007\u001a\u00020\bJ\u0014\u0010\u0014\u001a\u00020\u00062\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\r0\u0016J\u0014\u0010\u0017\u001a\u00020\u00062\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\r0\u0016J\u001e\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0012J\u001e\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u0012R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001f"}, d2 = {"Lorg/wordpress/android/ui/uploads/UploadServiceFacade;", "", "appContext", "Landroid/content/Context;", "(Landroid/content/Context;)V", "cancelFinalNotification", "", "post", "Lorg/wordpress/android/fluxc/model/PostImmutableModel;", "cancelFinalNotificationForMedia", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "getPendingOrInProgressFeaturedImageUploadForPost", "Lorg/wordpress/android/fluxc/model/MediaModel;", "getUploadProgressForMedia", "", "mediaModel", "isPendingOrInProgressMediaUpload", "", "isPostUploadingOrQueued", "uploadMedia", "mediaList", "Ljava/util/ArrayList;", "uploadMediaFromEditor", "uploadPost", "context", "postId", "", "isFirstTimePublish", "Lorg/wordpress/android/fluxc/model/PostModel;", "trackAnalytics", "org.wordpress.android_wordpressJalapenoDebug"})
public final class UploadServiceFacade {
    private final android.content.Context appContext = null;
    
    @javax.inject.Inject()
    public UploadServiceFacade(@org.jetbrains.annotations.NotNull()
    android.content.Context appContext) {
        super();
    }
    
    public final void uploadPost(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.PostModel post, boolean trackAnalytics) {
    }
    
    public final void uploadPost(@org.jetbrains.annotations.NotNull()
    android.content.Context context, int postId, boolean isFirstTimePublish) {
    }
    
    public final boolean isPostUploadingOrQueued(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.PostImmutableModel post) {
        return false;
    }
    
    public final void cancelFinalNotification(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.PostImmutableModel post) {
    }
    
    public final void cancelFinalNotificationForMedia(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site) {
    }
    
    public final void uploadMedia(@org.jetbrains.annotations.NotNull()
    java.util.ArrayList<org.wordpress.android.fluxc.model.MediaModel> mediaList) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final org.wordpress.android.fluxc.model.MediaModel getPendingOrInProgressFeaturedImageUploadForPost(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.PostImmutableModel post) {
        return null;
    }
    
    public final void uploadMediaFromEditor(@org.jetbrains.annotations.NotNull()
    java.util.ArrayList<org.wordpress.android.fluxc.model.MediaModel> mediaList) {
    }
    
    public final boolean isPendingOrInProgressMediaUpload(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.MediaModel mediaModel) {
        return false;
    }
    
    public final float getUploadProgressForMedia(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.MediaModel mediaModel) {
        return 0.0F;
    }
}