package org.wordpress.android.viewmodel.pages;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u000bB\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n\u00a8\u0006\f"}, d2 = {"Lorg/wordpress/android/viewmodel/pages/PostModelUploadUiStateUseCase;", "", "()V", "createUploadUiState", "Lorg/wordpress/android/viewmodel/pages/PostModelUploadUiStateUseCase$PostUploadUiState;", "post", "Lorg/wordpress/android/fluxc/model/PostModel;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "uploadStatusTracker", "Lorg/wordpress/android/ui/posts/PostModelUploadStatusTracker;", "PostUploadUiState", "org.wordpress.android_wordpressJalapenoDebug"})
public final class PostModelUploadUiStateUseCase {
    
    @javax.inject.Inject()
    public PostModelUploadUiStateUseCase() {
        super();
    }
    
    /**
     * Copied from PostListItemUiStateHelper since the behavior is similar for the Page List UI State.
     */
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.viewmodel.pages.PostModelUploadUiStateUseCase.PostUploadUiState createUploadUiState(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.PostModel post, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.PostModelUploadStatusTracker uploadStatusTracker) {
        return null;
    }
    
    /**
     * Copied from PostListItemUiStateHelper since the behavior is similar for the Page List UI State.
     */
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0006\u0003\u0004\u0005\u0006\u0007\bB\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0006\t\n\u000b\f\r\u000e\u00a8\u0006\u000f"}, d2 = {"Lorg/wordpress/android/viewmodel/pages/PostModelUploadUiStateUseCase$PostUploadUiState;", "", "()V", "NothingToUpload", "UploadFailed", "UploadQueued", "UploadWaitingForConnection", "UploadingMedia", "UploadingPost", "Lorg/wordpress/android/viewmodel/pages/PostModelUploadUiStateUseCase$PostUploadUiState$UploadingMedia;", "Lorg/wordpress/android/viewmodel/pages/PostModelUploadUiStateUseCase$PostUploadUiState$UploadingPost;", "Lorg/wordpress/android/viewmodel/pages/PostModelUploadUiStateUseCase$PostUploadUiState$UploadFailed;", "Lorg/wordpress/android/viewmodel/pages/PostModelUploadUiStateUseCase$PostUploadUiState$UploadWaitingForConnection;", "Lorg/wordpress/android/viewmodel/pages/PostModelUploadUiStateUseCase$PostUploadUiState$UploadQueued;", "Lorg/wordpress/android/viewmodel/pages/PostModelUploadUiStateUseCase$PostUploadUiState$NothingToUpload;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class PostUploadUiState {
        
        private PostUploadUiState() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u000e\u001a\u00020\u000fH\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lorg/wordpress/android/viewmodel/pages/PostModelUploadUiStateUseCase$PostUploadUiState$UploadingMedia;", "Lorg/wordpress/android/viewmodel/pages/PostModelUploadUiStateUseCase$PostUploadUiState;", "progress", "", "(I)V", "getProgress", "()I", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class UploadingMedia extends org.wordpress.android.viewmodel.pages.PostModelUploadUiStateUseCase.PostUploadUiState {
            private final int progress = 0;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.viewmodel.pages.PostModelUploadUiStateUseCase.PostUploadUiState.UploadingMedia copy(int progress) {
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
            
            public UploadingMedia(int progress) {
                super();
            }
            
            public final int component1() {
                return 0;
            }
            
            public final int getProgress() {
                return 0;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0006\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\u0007\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\b\u001a\u00020\u00032\b\u0010\t\u001a\u0004\u0018\u00010\nH\u00d6\u0003J\t\u0010\u000b\u001a\u00020\fH\u00d6\u0001J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0005\u00a8\u0006\u000f"}, d2 = {"Lorg/wordpress/android/viewmodel/pages/PostModelUploadUiStateUseCase$PostUploadUiState$UploadingPost;", "Lorg/wordpress/android/viewmodel/pages/PostModelUploadUiStateUseCase$PostUploadUiState;", "isDraft", "", "(Z)V", "()Z", "component1", "copy", "equals", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class UploadingPost extends org.wordpress.android.viewmodel.pages.PostModelUploadUiStateUseCase.PostUploadUiState {
            private final boolean isDraft = false;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.viewmodel.pages.PostModelUploadUiStateUseCase.PostUploadUiState.UploadingPost copy(boolean isDraft) {
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
            
            public UploadingPost(boolean isDraft) {
                super();
            }
            
            public final boolean component1() {
                return false;
            }
            
            public final boolean isDraft() {
                return false;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0007J\t\u0010\f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\r\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u000e\u001a\u00020\u0005H\u00c6\u0003J\'\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u0010\u001a\u00020\u00052\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u00d6\u0003J\t\u0010\u0013\u001a\u00020\u0014H\u00d6\u0001J\t\u0010\u0015\u001a\u00020\u0016H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\n\u00a8\u0006\u0017"}, d2 = {"Lorg/wordpress/android/viewmodel/pages/PostModelUploadUiStateUseCase$PostUploadUiState$UploadFailed;", "Lorg/wordpress/android/viewmodel/pages/PostModelUploadUiStateUseCase$PostUploadUiState;", "error", "Lorg/wordpress/android/fluxc/store/UploadStore$UploadError;", "isEligibleForAutoUpload", "", "retryWillPushChanges", "(Lorg/wordpress/android/fluxc/store/UploadStore$UploadError;ZZ)V", "getError", "()Lorg/wordpress/android/fluxc/store/UploadStore$UploadError;", "()Z", "getRetryWillPushChanges", "component1", "component2", "component3", "copy", "equals", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class UploadFailed extends org.wordpress.android.viewmodel.pages.PostModelUploadUiStateUseCase.PostUploadUiState {
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.fluxc.store.UploadStore.UploadError error = null;
            private final boolean isEligibleForAutoUpload = false;
            private final boolean retryWillPushChanges = false;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.viewmodel.pages.PostModelUploadUiStateUseCase.PostUploadUiState.UploadFailed copy(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.fluxc.store.UploadStore.UploadError error, boolean isEligibleForAutoUpload, boolean retryWillPushChanges) {
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
            
            public UploadFailed(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.fluxc.store.UploadStore.UploadError error, boolean isEligibleForAutoUpload, boolean retryWillPushChanges) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.fluxc.store.UploadStore.UploadError component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.fluxc.store.UploadStore.UploadError getError() {
                return null;
            }
            
            public final boolean component2() {
                return false;
            }
            
            public final boolean isEligibleForAutoUpload() {
                return false;
            }
            
            public final boolean component3() {
                return false;
            }
            
            public final boolean getRetryWillPushChanges() {
                return false;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/viewmodel/pages/PostModelUploadUiStateUseCase$PostUploadUiState$UploadWaitingForConnection;", "Lorg/wordpress/android/viewmodel/pages/PostModelUploadUiStateUseCase$PostUploadUiState;", "postStatus", "Lorg/wordpress/android/fluxc/model/post/PostStatus;", "(Lorg/wordpress/android/fluxc/model/post/PostStatus;)V", "getPostStatus", "()Lorg/wordpress/android/fluxc/model/post/PostStatus;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class UploadWaitingForConnection extends org.wordpress.android.viewmodel.pages.PostModelUploadUiStateUseCase.PostUploadUiState {
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.fluxc.model.post.PostStatus postStatus = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.viewmodel.pages.PostModelUploadUiStateUseCase.PostUploadUiState.UploadWaitingForConnection copy(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.fluxc.model.post.PostStatus postStatus) {
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
            
            public UploadWaitingForConnection(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.fluxc.model.post.PostStatus postStatus) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.fluxc.model.post.PostStatus component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.fluxc.model.post.PostStatus getPostStatus() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/viewmodel/pages/PostModelUploadUiStateUseCase$PostUploadUiState$UploadQueued;", "Lorg/wordpress/android/viewmodel/pages/PostModelUploadUiStateUseCase$PostUploadUiState;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class UploadQueued extends org.wordpress.android.viewmodel.pages.PostModelUploadUiStateUseCase.PostUploadUiState {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.viewmodel.pages.PostModelUploadUiStateUseCase.PostUploadUiState.UploadQueued INSTANCE = null;
            
            private UploadQueued() {
                super();
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/viewmodel/pages/PostModelUploadUiStateUseCase$PostUploadUiState$NothingToUpload;", "Lorg/wordpress/android/viewmodel/pages/PostModelUploadUiStateUseCase$PostUploadUiState;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class NothingToUpload extends org.wordpress.android.viewmodel.pages.PostModelUploadUiStateUseCase.PostUploadUiState {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.viewmodel.pages.PostModelUploadUiStateUseCase.PostUploadUiState.NothingToUpload INSTANCE = null;
            
            private NothingToUpload() {
                super();
            }
        }
    }
}