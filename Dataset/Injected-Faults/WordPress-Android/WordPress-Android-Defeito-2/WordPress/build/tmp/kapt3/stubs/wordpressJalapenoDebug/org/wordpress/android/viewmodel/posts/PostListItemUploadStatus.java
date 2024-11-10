package org.wordpress.android.viewmodel.posts;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u001f\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BW\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\u0007\u0012\u0006\u0010\u000b\u001a\u00020\u0007\u0012\u0006\u0010\f\u001a\u00020\u0007\u0012\u0006\u0010\r\u001a\u00020\u0007\u0012\u0006\u0010\u000e\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\u000fJ\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010\u0019\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001b\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\u001c\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\u001e\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\u001f\u001a\u00020\u0007H\u00c6\u0003J\t\u0010 \u001a\u00020\u0007H\u00c6\u0003J\t\u0010!\u001a\u00020\u0007H\u00c6\u0003Jo\u0010\"\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\u00072\b\b\u0002\u0010\f\u001a\u00020\u00072\b\b\u0002\u0010\r\u001a\u00020\u00072\b\b\u0002\u0010\u000e\u001a\u00020\u0007H\u00c6\u0001J\u0013\u0010#\u001a\u00020\u00072\b\u0010$\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010%\u001a\u00020\u0005H\u00d6\u0001J\t\u0010&\u001a\u00020\'H\u00d6\u0001R\u0011\u0010\u000b\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\f\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\r\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0011R\u0011\u0010\t\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0011R\u0011\u0010\n\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0011R\u0011\u0010\b\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u000e\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0011\u00a8\u0006("}, d2 = {"Lorg/wordpress/android/viewmodel/posts/PostListItemUploadStatus;", "", "uploadError", "Lorg/wordpress/android/fluxc/store/UploadStore$UploadError;", "mediaUploadProgress", "", "isUploading", "", "isUploadingOrQueued", "isQueued", "isUploadFailed", "hasInProgressMediaUpload", "hasPendingMediaUpload", "isEligibleForAutoUpload", "uploadWillPushChanges", "(Lorg/wordpress/android/fluxc/store/UploadStore$UploadError;IZZZZZZZZ)V", "getHasInProgressMediaUpload", "()Z", "getHasPendingMediaUpload", "getMediaUploadProgress", "()I", "getUploadError", "()Lorg/wordpress/android/fluxc/store/UploadStore$UploadError;", "getUploadWillPushChanges", "component1", "component10", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
public final class PostListItemUploadStatus {
    @org.jetbrains.annotations.Nullable()
    private final org.wordpress.android.fluxc.store.UploadStore.UploadError uploadError = null;
    private final int mediaUploadProgress = 0;
    private final boolean isUploading = false;
    private final boolean isUploadingOrQueued = false;
    private final boolean isQueued = false;
    private final boolean isUploadFailed = false;
    private final boolean hasInProgressMediaUpload = false;
    private final boolean hasPendingMediaUpload = false;
    private final boolean isEligibleForAutoUpload = false;
    private final boolean uploadWillPushChanges = false;
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.viewmodel.posts.PostListItemUploadStatus copy(@org.jetbrains.annotations.Nullable()
    org.wordpress.android.fluxc.store.UploadStore.UploadError uploadError, int mediaUploadProgress, boolean isUploading, boolean isUploadingOrQueued, boolean isQueued, boolean isUploadFailed, boolean hasInProgressMediaUpload, boolean hasPendingMediaUpload, boolean isEligibleForAutoUpload, boolean uploadWillPushChanges) {
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
    
    public PostListItemUploadStatus(@org.jetbrains.annotations.Nullable()
    org.wordpress.android.fluxc.store.UploadStore.UploadError uploadError, int mediaUploadProgress, boolean isUploading, boolean isUploadingOrQueued, boolean isQueued, boolean isUploadFailed, boolean hasInProgressMediaUpload, boolean hasPendingMediaUpload, boolean isEligibleForAutoUpload, boolean uploadWillPushChanges) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final org.wordpress.android.fluxc.store.UploadStore.UploadError component1() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final org.wordpress.android.fluxc.store.UploadStore.UploadError getUploadError() {
        return null;
    }
    
    public final int component2() {
        return 0;
    }
    
    public final int getMediaUploadProgress() {
        return 0;
    }
    
    public final boolean component3() {
        return false;
    }
    
    public final boolean isUploading() {
        return false;
    }
    
    public final boolean component4() {
        return false;
    }
    
    public final boolean isUploadingOrQueued() {
        return false;
    }
    
    public final boolean component5() {
        return false;
    }
    
    public final boolean isQueued() {
        return false;
    }
    
    public final boolean component6() {
        return false;
    }
    
    public final boolean isUploadFailed() {
        return false;
    }
    
    public final boolean component7() {
        return false;
    }
    
    public final boolean getHasInProgressMediaUpload() {
        return false;
    }
    
    public final boolean component8() {
        return false;
    }
    
    public final boolean getHasPendingMediaUpload() {
        return false;
    }
    
    public final boolean component9() {
        return false;
    }
    
    public final boolean isEligibleForAutoUpload() {
        return false;
    }
    
    public final boolean component10() {
        return false;
    }
    
    public final boolean getUploadWillPushChanges() {
        return false;
    }
}