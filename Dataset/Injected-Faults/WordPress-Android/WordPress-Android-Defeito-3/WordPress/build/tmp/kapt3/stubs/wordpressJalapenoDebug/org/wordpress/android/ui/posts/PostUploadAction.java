package org.wordpress.android.ui.posts;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0006\u0003\u0004\u0005\u0006\u0007\bB\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0006\t\n\u000b\f\r\u000e\u00a8\u0006\u000f"}, d2 = {"Lorg/wordpress/android/ui/posts/PostUploadAction;", "", "()V", "CancelPostAndMediaUpload", "EditPostResult", "MediaUploadedSnackbar", "PostRemotePreviewSnackbarError", "PostUploadedSnackbar", "PublishPost", "Lorg/wordpress/android/ui/posts/PostUploadAction$EditPostResult;", "Lorg/wordpress/android/ui/posts/PostUploadAction$PublishPost;", "Lorg/wordpress/android/ui/posts/PostUploadAction$PostUploadedSnackbar;", "Lorg/wordpress/android/ui/posts/PostUploadAction$MediaUploadedSnackbar;", "Lorg/wordpress/android/ui/posts/PostUploadAction$PostRemotePreviewSnackbarError;", "Lorg/wordpress/android/ui/posts/PostUploadAction$CancelPostAndMediaUpload;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class PostUploadAction {
    
    private PostUploadAction() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u00a2\u0006\u0002\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013\u00a8\u0006\u0014"}, d2 = {"Lorg/wordpress/android/ui/posts/PostUploadAction$EditPostResult;", "Lorg/wordpress/android/ui/posts/PostUploadAction;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "post", "Lorg/wordpress/android/fluxc/model/PostModel;", "data", "Landroid/content/Intent;", "publishAction", "Lkotlin/Function0;", "", "(Lorg/wordpress/android/fluxc/model/SiteModel;Lorg/wordpress/android/fluxc/model/PostModel;Landroid/content/Intent;Lkotlin/jvm/functions/Function0;)V", "getData", "()Landroid/content/Intent;", "getPost", "()Lorg/wordpress/android/fluxc/model/PostModel;", "getPublishAction", "()Lkotlin/jvm/functions/Function0;", "getSite", "()Lorg/wordpress/android/fluxc/model/SiteModel;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class EditPostResult extends org.wordpress.android.ui.posts.PostUploadAction {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.fluxc.model.SiteModel site = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.fluxc.model.PostModel post = null;
        @org.jetbrains.annotations.NotNull()
        private final android.content.Intent data = null;
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function0<kotlin.Unit> publishAction = null;
        
        public EditPostResult(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.PostModel post, @org.jetbrains.annotations.NotNull()
        android.content.Intent data, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function0<kotlin.Unit> publishAction) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.fluxc.model.SiteModel getSite() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.fluxc.model.PostModel getPost() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.content.Intent getData() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function0<kotlin.Unit> getPublishAction() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e\u00a8\u0006\u000f"}, d2 = {"Lorg/wordpress/android/ui/posts/PostUploadAction$PublishPost;", "Lorg/wordpress/android/ui/posts/PostUploadAction;", "dispatcher", "Lorg/wordpress/android/fluxc/Dispatcher;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "post", "Lorg/wordpress/android/fluxc/model/PostModel;", "(Lorg/wordpress/android/fluxc/Dispatcher;Lorg/wordpress/android/fluxc/model/SiteModel;Lorg/wordpress/android/fluxc/model/PostModel;)V", "getDispatcher", "()Lorg/wordpress/android/fluxc/Dispatcher;", "getPost", "()Lorg/wordpress/android/fluxc/model/PostModel;", "getSite", "()Lorg/wordpress/android/fluxc/model/SiteModel;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class PublishPost extends org.wordpress.android.ui.posts.PostUploadAction {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.fluxc.Dispatcher dispatcher = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.fluxc.model.SiteModel site = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.fluxc.model.PostModel post = null;
        
        public PublishPost(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.Dispatcher dispatcher, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.PostModel post) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.fluxc.Dispatcher getDispatcher() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.fluxc.model.SiteModel getSite() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.fluxc.model.PostModel getPost() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f\u00a2\u0006\u0002\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u000b\u001a\u0004\u0018\u00010\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0012R\u0011\u0010\n\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0012R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016\u00a8\u0006\u0017"}, d2 = {"Lorg/wordpress/android/ui/posts/PostUploadAction$PostUploadedSnackbar;", "Lorg/wordpress/android/ui/posts/PostUploadAction;", "dispatcher", "Lorg/wordpress/android/fluxc/Dispatcher;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "post", "Lorg/wordpress/android/fluxc/model/PostModel;", "isError", "", "isFirstTimePublish", "errorMessage", "", "(Lorg/wordpress/android/fluxc/Dispatcher;Lorg/wordpress/android/fluxc/model/SiteModel;Lorg/wordpress/android/fluxc/model/PostModel;ZZLjava/lang/String;)V", "getDispatcher", "()Lorg/wordpress/android/fluxc/Dispatcher;", "getErrorMessage", "()Ljava/lang/String;", "()Z", "getPost", "()Lorg/wordpress/android/fluxc/model/PostModel;", "getSite", "()Lorg/wordpress/android/fluxc/model/SiteModel;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class PostUploadedSnackbar extends org.wordpress.android.ui.posts.PostUploadAction {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.fluxc.Dispatcher dispatcher = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.fluxc.model.SiteModel site = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.fluxc.model.PostModel post = null;
        private final boolean isError = false;
        private final boolean isFirstTimePublish = false;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String errorMessage = null;
        
        public PostUploadedSnackbar(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.Dispatcher dispatcher, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.PostModel post, boolean isError, boolean isFirstTimePublish, @org.jetbrains.annotations.Nullable()
        java.lang.String errorMessage) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.fluxc.Dispatcher getDispatcher() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.fluxc.model.SiteModel getSite() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.fluxc.model.PostModel getPost() {
            return null;
        }
        
        public final boolean isError() {
            return false;
        }
        
        public final boolean isFirstTimePublish() {
            return false;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getErrorMessage() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u00a2\u0006\u0002\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\fR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\t\u001a\u0004\u0018\u00010\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012\u00a8\u0006\u0013"}, d2 = {"Lorg/wordpress/android/ui/posts/PostUploadAction$MediaUploadedSnackbar;", "Lorg/wordpress/android/ui/posts/PostUploadAction;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "mediaList", "", "Lorg/wordpress/android/fluxc/model/MediaModel;", "isError", "", "message", "", "(Lorg/wordpress/android/fluxc/model/SiteModel;Ljava/util/List;ZLjava/lang/String;)V", "()Z", "getMediaList", "()Ljava/util/List;", "getMessage", "()Ljava/lang/String;", "getSite", "()Lorg/wordpress/android/fluxc/model/SiteModel;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class MediaUploadedSnackbar extends org.wordpress.android.ui.posts.PostUploadAction {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.fluxc.model.SiteModel site = null;
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<org.wordpress.android.fluxc.model.MediaModel> mediaList = null;
        private final boolean isError = false;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String message = null;
        
        public MediaUploadedSnackbar(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
        java.util.List<? extends org.wordpress.android.fluxc.model.MediaModel> mediaList, boolean isError, @org.jetbrains.annotations.Nullable()
        java.lang.String message) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.fluxc.model.SiteModel getSite() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.fluxc.model.MediaModel> getMediaList() {
            return null;
        }
        
        public final boolean isError() {
            return false;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getMessage() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/posts/PostUploadAction$PostRemotePreviewSnackbarError;", "Lorg/wordpress/android/ui/posts/PostUploadAction;", "messageResId", "", "(I)V", "getMessageResId", "()I", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class PostRemotePreviewSnackbarError extends org.wordpress.android.ui.posts.PostUploadAction {
        private final int messageResId = 0;
        
        public PostRemotePreviewSnackbarError(int messageResId) {
            super();
        }
        
        public final int getMessageResId() {
            return 0;
        }
    }
    
    /**
     * Cancel all post and media uploads related to this post
     */
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/posts/PostUploadAction$CancelPostAndMediaUpload;", "Lorg/wordpress/android/ui/posts/PostUploadAction;", "post", "Lorg/wordpress/android/fluxc/model/PostModel;", "(Lorg/wordpress/android/fluxc/model/PostModel;)V", "getPost", "()Lorg/wordpress/android/fluxc/model/PostModel;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class CancelPostAndMediaUpload extends org.wordpress.android.ui.posts.PostUploadAction {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.fluxc.model.PostModel post = null;
        
        public CancelPostAndMediaUpload(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.PostModel post) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.fluxc.model.PostModel getPost() {
            return null;
        }
    }
}