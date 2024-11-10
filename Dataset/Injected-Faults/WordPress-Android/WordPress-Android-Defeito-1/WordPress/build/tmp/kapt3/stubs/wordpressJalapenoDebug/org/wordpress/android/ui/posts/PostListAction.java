package org.wordpress.android.ui.posts;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\n\u0003\u0004\u0005\u0006\u0007\b\t\n\u000b\fB\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\n\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014\u0015\u0016\u00a8\u0006\u0017"}, d2 = {"Lorg/wordpress/android/ui/posts/PostListAction;", "", "()V", "CopyUrl", "DismissPendingNotification", "EditPost", "NewPost", "NewStoryPost", "PreviewPost", "RemotePreviewPost", "RetryUpload", "ViewPost", "ViewStats", "Lorg/wordpress/android/ui/posts/PostListAction$EditPost;", "Lorg/wordpress/android/ui/posts/PostListAction$NewPost;", "Lorg/wordpress/android/ui/posts/PostListAction$NewStoryPost;", "Lorg/wordpress/android/ui/posts/PostListAction$PreviewPost;", "Lorg/wordpress/android/ui/posts/PostListAction$RemotePreviewPost;", "Lorg/wordpress/android/ui/posts/PostListAction$RetryUpload;", "Lorg/wordpress/android/ui/posts/PostListAction$CopyUrl;", "Lorg/wordpress/android/ui/posts/PostListAction$ViewStats;", "Lorg/wordpress/android/ui/posts/PostListAction$ViewPost;", "Lorg/wordpress/android/ui/posts/PostListAction$DismissPendingNotification;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class PostListAction {
    
    private PostListAction() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e\u00a8\u0006\u000f"}, d2 = {"Lorg/wordpress/android/ui/posts/PostListAction$EditPost;", "Lorg/wordpress/android/ui/posts/PostListAction;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "post", "Lorg/wordpress/android/fluxc/model/PostModel;", "loadAutoSaveRevision", "", "(Lorg/wordpress/android/fluxc/model/SiteModel;Lorg/wordpress/android/fluxc/model/PostModel;Z)V", "getLoadAutoSaveRevision", "()Z", "getPost", "()Lorg/wordpress/android/fluxc/model/PostModel;", "getSite", "()Lorg/wordpress/android/fluxc/model/SiteModel;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class EditPost extends org.wordpress.android.ui.posts.PostListAction {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.fluxc.model.SiteModel site = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.fluxc.model.PostModel post = null;
        private final boolean loadAutoSaveRevision = false;
        
        public EditPost(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.PostModel post, boolean loadAutoSaveRevision) {
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
        
        public final boolean getLoadAutoSaveRevision() {
            return false;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t\u00a8\u0006\n"}, d2 = {"Lorg/wordpress/android/ui/posts/PostListAction$NewPost;", "Lorg/wordpress/android/ui/posts/PostListAction;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "isPromo", "", "(Lorg/wordpress/android/fluxc/model/SiteModel;Z)V", "()Z", "getSite", "()Lorg/wordpress/android/fluxc/model/SiteModel;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class NewPost extends org.wordpress.android.ui.posts.PostListAction {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.fluxc.model.SiteModel site = null;
        private final boolean isPromo = false;
        
        public NewPost(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.SiteModel site, boolean isPromo) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.fluxc.model.SiteModel getSite() {
            return null;
        }
        
        public final boolean isPromo() {
            return false;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/posts/PostListAction$NewStoryPost;", "Lorg/wordpress/android/ui/posts/PostListAction;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "(Lorg/wordpress/android/fluxc/model/SiteModel;)V", "getSite", "()Lorg/wordpress/android/fluxc/model/SiteModel;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class NewStoryPost extends org.wordpress.android.ui.posts.PostListAction {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.fluxc.model.SiteModel site = null;
        
        public NewStoryPost(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.SiteModel site) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.fluxc.model.SiteModel getSite() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u00002\u00020\u0001BK\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0018\u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\u0007\u0012\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\n0\f\u0012\u0006\u0010\u000e\u001a\u00020\r\u00a2\u0006\u0002\u0010\u000fR\u0011\u0010\u000e\u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u001d\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\n0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R#\u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019\u00a8\u0006\u001a"}, d2 = {"Lorg/wordpress/android/ui/posts/PostListAction$PreviewPost;", "Lorg/wordpress/android/ui/posts/PostListAction;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "post", "Lorg/wordpress/android/fluxc/model/PostModel;", "triggerPreviewStateUpdate", "Lkotlin/Function2;", "Lorg/wordpress/android/ui/posts/PostListRemotePreviewState;", "Lorg/wordpress/android/ui/posts/PostInfoType;", "", "showToast", "Lkotlin/Function1;", "Lorg/wordpress/android/viewmodel/helpers/ToastMessageHolder;", "messageMediaUploading", "(Lorg/wordpress/android/fluxc/model/SiteModel;Lorg/wordpress/android/fluxc/model/PostModel;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function1;Lorg/wordpress/android/viewmodel/helpers/ToastMessageHolder;)V", "getMessageMediaUploading", "()Lorg/wordpress/android/viewmodel/helpers/ToastMessageHolder;", "getPost", "()Lorg/wordpress/android/fluxc/model/PostModel;", "getShowToast", "()Lkotlin/jvm/functions/Function1;", "getSite", "()Lorg/wordpress/android/fluxc/model/SiteModel;", "getTriggerPreviewStateUpdate", "()Lkotlin/jvm/functions/Function2;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class PreviewPost extends org.wordpress.android.ui.posts.PostListAction {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.fluxc.model.SiteModel site = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.fluxc.model.PostModel post = null;
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function2<org.wordpress.android.ui.posts.PostListRemotePreviewState, org.wordpress.android.ui.posts.PostInfoType, kotlin.Unit> triggerPreviewStateUpdate = null;
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function1<org.wordpress.android.viewmodel.helpers.ToastMessageHolder, kotlin.Unit> showToast = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.viewmodel.helpers.ToastMessageHolder messageMediaUploading = null;
        
        public PreviewPost(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.PostModel post, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function2<? super org.wordpress.android.ui.posts.PostListRemotePreviewState, ? super org.wordpress.android.ui.posts.PostInfoType, kotlin.Unit> triggerPreviewStateUpdate, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super org.wordpress.android.viewmodel.helpers.ToastMessageHolder, kotlin.Unit> showToast, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.viewmodel.helpers.ToastMessageHolder messageMediaUploading) {
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
        public final kotlin.jvm.functions.Function2<org.wordpress.android.ui.posts.PostListRemotePreviewState, org.wordpress.android.ui.posts.PostInfoType, kotlin.Unit> getTriggerPreviewStateUpdate() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function1<org.wordpress.android.viewmodel.helpers.ToastMessageHolder, kotlin.Unit> getShowToast() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.viewmodel.helpers.ToastMessageHolder getMessageMediaUploading() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e\u00a8\u0006\u000f"}, d2 = {"Lorg/wordpress/android/ui/posts/PostListAction$RemotePreviewPost;", "Lorg/wordpress/android/ui/posts/PostListAction;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "post", "Lorg/wordpress/android/fluxc/model/PostModel;", "remotePreviewType", "Lorg/wordpress/android/ui/posts/RemotePreviewLogicHelper$RemotePreviewType;", "(Lorg/wordpress/android/fluxc/model/SiteModel;Lorg/wordpress/android/fluxc/model/PostModel;Lorg/wordpress/android/ui/posts/RemotePreviewLogicHelper$RemotePreviewType;)V", "getPost", "()Lorg/wordpress/android/fluxc/model/PostModel;", "getRemotePreviewType", "()Lorg/wordpress/android/ui/posts/RemotePreviewLogicHelper$RemotePreviewType;", "getSite", "()Lorg/wordpress/android/fluxc/model/SiteModel;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class RemotePreviewPost extends org.wordpress.android.ui.posts.PostListAction {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.fluxc.model.SiteModel site = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.fluxc.model.PostModel post = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.posts.RemotePreviewLogicHelper.RemotePreviewType remotePreviewType = null;
        
        public RemotePreviewPost(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.PostModel post, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.posts.RemotePreviewLogicHelper.RemotePreviewType remotePreviewType) {
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
        public final org.wordpress.android.ui.posts.RemotePreviewLogicHelper.RemotePreviewType getRemotePreviewType() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u000b"}, d2 = {"Lorg/wordpress/android/ui/posts/PostListAction$RetryUpload;", "Lorg/wordpress/android/ui/posts/PostListAction;", "post", "Lorg/wordpress/android/fluxc/model/PostModel;", "trackAnalytics", "", "(Lorg/wordpress/android/fluxc/model/PostModel;Z)V", "getPost", "()Lorg/wordpress/android/fluxc/model/PostModel;", "getTrackAnalytics", "()Z", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class RetryUpload extends org.wordpress.android.ui.posts.PostListAction {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.fluxc.model.PostModel post = null;
        private final boolean trackAnalytics = false;
        
        public RetryUpload(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.PostModel post, boolean trackAnalytics) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.fluxc.model.PostModel getPost() {
            return null;
        }
        
        public final boolean getTrackAnalytics() {
            return false;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\r\u0018\u00002\u00020\u0001B9\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007\u0012\u0006\u0010\n\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\b\u00a2\u0006\u0002\u0010\fR\u0011\u0010\u000b\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\n\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001d\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015\u00a8\u0006\u0016"}, d2 = {"Lorg/wordpress/android/ui/posts/PostListAction$CopyUrl;", "Lorg/wordpress/android/ui/posts/PostListAction;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "post", "Lorg/wordpress/android/fluxc/model/PostModel;", "showSnackbar", "Lkotlin/Function1;", "Lorg/wordpress/android/ui/pages/SnackbarMessageHolder;", "", "messageSuccess", "messageError", "(Lorg/wordpress/android/fluxc/model/SiteModel;Lorg/wordpress/android/fluxc/model/PostModel;Lkotlin/jvm/functions/Function1;Lorg/wordpress/android/ui/pages/SnackbarMessageHolder;Lorg/wordpress/android/ui/pages/SnackbarMessageHolder;)V", "getMessageError", "()Lorg/wordpress/android/ui/pages/SnackbarMessageHolder;", "getMessageSuccess", "getPost", "()Lorg/wordpress/android/fluxc/model/PostModel;", "getShowSnackbar", "()Lkotlin/jvm/functions/Function1;", "getSite", "()Lorg/wordpress/android/fluxc/model/SiteModel;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class CopyUrl extends org.wordpress.android.ui.posts.PostListAction {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.fluxc.model.SiteModel site = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.fluxc.model.PostModel post = null;
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function1<org.wordpress.android.ui.pages.SnackbarMessageHolder, kotlin.Unit> showSnackbar = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.pages.SnackbarMessageHolder messageSuccess = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.pages.SnackbarMessageHolder messageError = null;
        
        public CopyUrl(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.PostModel post, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super org.wordpress.android.ui.pages.SnackbarMessageHolder, kotlin.Unit> showSnackbar, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.pages.SnackbarMessageHolder messageSuccess, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.pages.SnackbarMessageHolder messageError) {
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
        public final kotlin.jvm.functions.Function1<org.wordpress.android.ui.pages.SnackbarMessageHolder, kotlin.Unit> getShowSnackbar() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.pages.SnackbarMessageHolder getMessageSuccess() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.pages.SnackbarMessageHolder getMessageError() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u000b"}, d2 = {"Lorg/wordpress/android/ui/posts/PostListAction$ViewStats;", "Lorg/wordpress/android/ui/posts/PostListAction;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "post", "Lorg/wordpress/android/fluxc/model/PostModel;", "(Lorg/wordpress/android/fluxc/model/SiteModel;Lorg/wordpress/android/fluxc/model/PostModel;)V", "getPost", "()Lorg/wordpress/android/fluxc/model/PostModel;", "getSite", "()Lorg/wordpress/android/fluxc/model/SiteModel;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class ViewStats extends org.wordpress.android.ui.posts.PostListAction {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.fluxc.model.SiteModel site = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.fluxc.model.PostModel post = null;
        
        public ViewStats(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.PostModel post) {
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
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u000b"}, d2 = {"Lorg/wordpress/android/ui/posts/PostListAction$ViewPost;", "Lorg/wordpress/android/ui/posts/PostListAction;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "post", "Lorg/wordpress/android/fluxc/model/PostModel;", "(Lorg/wordpress/android/fluxc/model/SiteModel;Lorg/wordpress/android/fluxc/model/PostModel;)V", "getPost", "()Lorg/wordpress/android/fluxc/model/PostModel;", "getSite", "()Lorg/wordpress/android/fluxc/model/SiteModel;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class ViewPost extends org.wordpress.android.ui.posts.PostListAction {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.fluxc.model.SiteModel site = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.fluxc.model.PostModel post = null;
        
        public ViewPost(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.PostModel post) {
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
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/posts/PostListAction$DismissPendingNotification;", "Lorg/wordpress/android/ui/posts/PostListAction;", "pushId", "", "(I)V", "getPushId", "()I", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class DismissPendingNotification extends org.wordpress.android.ui.posts.PostListAction {
        private final int pushId = 0;
        
        public DismissPendingNotification(int pushId) {
            super();
        }
        
        public final int getPushId() {
            return 0;
        }
    }
}