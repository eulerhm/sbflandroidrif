package org.wordpress.android.ui.posts;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001:\u0003\u001a\u001b\u001cB\'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ&\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014J\u0018\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0018\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u0018H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001d"}, d2 = {"Lorg/wordpress/android/ui/posts/RemotePreviewLogicHelper;", "", "networkUtilsWrapper", "Lorg/wordpress/android/util/NetworkUtilsWrapper;", "activityLauncherWrapper", "Lorg/wordpress/android/ui/ActivityLauncherWrapper;", "postUtilsWrapper", "Lorg/wordpress/android/ui/posts/PostUtilsWrapper;", "uploadActionUseCase", "Lorg/wordpress/android/ui/uploads/UploadActionUseCase;", "(Lorg/wordpress/android/util/NetworkUtilsWrapper;Lorg/wordpress/android/ui/ActivityLauncherWrapper;Lorg/wordpress/android/ui/posts/PostUtilsWrapper;Lorg/wordpress/android/ui/uploads/UploadActionUseCase;)V", "runPostPreviewLogic", "Lorg/wordpress/android/ui/posts/RemotePreviewLogicHelper$PreviewLogicOperationResult;", "activity", "Landroid/app/Activity;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "post", "Lorg/wordpress/android/fluxc/model/PostImmutableModel;", "helperFunctions", "Lorg/wordpress/android/ui/posts/RemotePreviewLogicHelper$RemotePreviewHelperFunctions;", "shouldRemoteAutoSave", "", "action", "Lorg/wordpress/android/ui/uploads/UploadActionUseCase$UploadAction;", "shouldUpload", "PreviewLogicOperationResult", "RemotePreviewHelperFunctions", "RemotePreviewType", "org.wordpress.android_wordpressJalapenoDebug"})
@dagger.Reusable()
public final class RemotePreviewLogicHelper {
    private final org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper = null;
    private final org.wordpress.android.ui.ActivityLauncherWrapper activityLauncherWrapper = null;
    private final org.wordpress.android.ui.posts.PostUtilsWrapper postUtilsWrapper = null;
    private final org.wordpress.android.ui.uploads.UploadActionUseCase uploadActionUseCase = null;
    
    @javax.inject.Inject()
    public RemotePreviewLogicHelper(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.ActivityLauncherWrapper activityLauncherWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.PostUtilsWrapper postUtilsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.uploads.UploadActionUseCase uploadActionUseCase) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.posts.RemotePreviewLogicHelper.PreviewLogicOperationResult runPostPreviewLogic(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.PostImmutableModel post, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.RemotePreviewLogicHelper.RemotePreviewHelperFunctions helperFunctions) {
        return null;
    }
    
    private final boolean shouldUpload(org.wordpress.android.fluxc.model.PostImmutableModel post, org.wordpress.android.ui.uploads.UploadActionUseCase.UploadAction action) {
        return false;
    }
    
    private final boolean shouldRemoteAutoSave(org.wordpress.android.fluxc.model.PostImmutableModel post, org.wordpress.android.ui.uploads.UploadActionUseCase.UploadAction action) {
        return false;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005\u00a8\u0006\u0006"}, d2 = {"Lorg/wordpress/android/ui/posts/RemotePreviewLogicHelper$RemotePreviewType;", "", "(Ljava/lang/String;I)V", "NOT_A_REMOTE_PREVIEW", "REMOTE_PREVIEW", "REMOTE_PREVIEW_WITH_REMOTE_AUTO_SAVE", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum RemotePreviewType {
        /*public static final*/ NOT_A_REMOTE_PREVIEW /* = new NOT_A_REMOTE_PREVIEW() */,
        /*public static final*/ REMOTE_PREVIEW /* = new REMOTE_PREVIEW() */,
        /*public static final*/ REMOTE_PREVIEW_WITH_REMOTE_AUTO_SAVE /* = new REMOTE_PREVIEW_WITH_REMOTE_AUTO_SAVE() */;
        
        RemotePreviewType() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\t\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t\u00a8\u0006\n"}, d2 = {"Lorg/wordpress/android/ui/posts/RemotePreviewLogicHelper$PreviewLogicOperationResult;", "", "(Ljava/lang/String;I)V", "PREVIEW_NOT_AVAILABLE", "NETWORK_NOT_AVAILABLE", "MEDIA_UPLOAD_IN_PROGRESS", "CANNOT_SAVE_EMPTY_DRAFT", "GENERATING_PREVIEW", "OPENING_PREVIEW", "CANNOT_REMOTE_AUTO_SAVE_EMPTY_POST", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum PreviewLogicOperationResult {
        /*public static final*/ PREVIEW_NOT_AVAILABLE /* = new PREVIEW_NOT_AVAILABLE() */,
        /*public static final*/ NETWORK_NOT_AVAILABLE /* = new NETWORK_NOT_AVAILABLE() */,
        /*public static final*/ MEDIA_UPLOAD_IN_PROGRESS /* = new MEDIA_UPLOAD_IN_PROGRESS() */,
        /*public static final*/ CANNOT_SAVE_EMPTY_DRAFT /* = new CANNOT_SAVE_EMPTY_DRAFT() */,
        /*public static final*/ GENERATING_PREVIEW /* = new GENERATING_PREVIEW() */,
        /*public static final*/ OPENING_PREVIEW /* = new OPENING_PREVIEW() */,
        /*public static final*/ CANNOT_REMOTE_AUTO_SAVE_EMPTY_POST /* = new CANNOT_REMOTE_AUTO_SAVE_EMPTY_POST() */;
        
        PreviewLogicOperationResult() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\b\u0010\u0004\u001a\u00020\u0003H\u0016J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH&J\u0018\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH&\u00a8\u0006\u000b"}, d2 = {"Lorg/wordpress/android/ui/posts/RemotePreviewLogicHelper$RemotePreviewHelperFunctions;", "", "notifyEmptyDraft", "", "notifyEmptyPost", "notifyUploadInProgress", "", "post", "Lorg/wordpress/android/fluxc/model/PostImmutableModel;", "startUploading", "isRemoteAutoSave", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract interface RemotePreviewHelperFunctions {
        
        public abstract boolean notifyUploadInProgress(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.PostImmutableModel post);
        
        public abstract void notifyEmptyDraft();
        
        public abstract void startUploading(boolean isRemoteAutoSave, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.PostImmutableModel post);
        
        public abstract void notifyEmptyPost();
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 3)
        public static final class DefaultImpls {
            
            public static void notifyEmptyDraft(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.posts.RemotePreviewLogicHelper.RemotePreviewHelperFunctions $this) {
            }
            
            public static void notifyEmptyPost(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.posts.RemotePreviewLogicHelper.RemotePreviewHelperFunctions $this) {
            }
        }
    }
}