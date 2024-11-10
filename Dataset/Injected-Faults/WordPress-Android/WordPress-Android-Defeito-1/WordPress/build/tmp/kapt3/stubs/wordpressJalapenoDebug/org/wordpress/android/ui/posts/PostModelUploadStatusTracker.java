package org.wordpress.android.ui.posts;

import java.lang.System;

/**
 * This is a temporary class to make the PostListViewModel more manageable. Please feel free to refactor it any way
 * you see fit.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0000\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\u0014\u0010\u0010\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\t0\u0013R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b8\u0002X\u0083\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lorg/wordpress/android/ui/posts/PostModelUploadStatusTracker;", "", "uploadStore", "Lorg/wordpress/android/fluxc/store/UploadStore;", "uploadActionUseCase", "Lorg/wordpress/android/ui/uploads/UploadActionUseCase;", "(Lorg/wordpress/android/fluxc/store/UploadStore;Lorg/wordpress/android/ui/uploads/UploadActionUseCase;)V", "uploadStatusMap", "Ljava/util/HashMap;", "", "Lorg/wordpress/android/viewmodel/posts/PostListItemUploadStatus;", "getUploadStatus", "post", "Lorg/wordpress/android/fluxc/model/PostModel;", "siteModel", "Lorg/wordpress/android/fluxc/model/SiteModel;", "invalidateUploadStatus", "", "localPostIds", "", "org.wordpress.android_wordpressJalapenoDebug"})
public final class PostModelUploadStatusTracker {
    private final org.wordpress.android.fluxc.store.UploadStore uploadStore = null;
    private final org.wordpress.android.ui.uploads.UploadActionUseCase uploadActionUseCase = null;
    @android.annotation.SuppressLint(value = {"UseSparseArrays"})
    private final java.util.HashMap<java.lang.Integer, org.wordpress.android.viewmodel.posts.PostListItemUploadStatus> uploadStatusMap = null;
    
    @javax.inject.Inject()
    public PostModelUploadStatusTracker(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.UploadStore uploadStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.uploads.UploadActionUseCase uploadActionUseCase) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.viewmodel.posts.PostListItemUploadStatus getUploadStatus(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.PostModel post, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel siteModel) {
        return null;
    }
    
    public final void invalidateUploadStatus(@org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.Integer> localPostIds) {
    }
}