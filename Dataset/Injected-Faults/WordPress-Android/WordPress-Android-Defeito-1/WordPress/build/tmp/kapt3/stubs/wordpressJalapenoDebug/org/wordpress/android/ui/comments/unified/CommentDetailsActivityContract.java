package org.wordpress.android.ui.comments.unified;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u00002\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0001:\u0002\u000e\u000fB\u0005\u00a2\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0002H\u0016J\u001c\u0010\n\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0006H\u0016\u00a8\u0006\u0010"}, d2 = {"Lorg/wordpress/android/ui/comments/unified/CommentDetailsActivityContract;", "Landroidx/activity/result/contract/ActivityResultContract;", "Lorg/wordpress/android/ui/comments/unified/CommentDetailsActivityContract$CommentDetailsActivityRequest;", "Lorg/wordpress/android/ui/comments/unified/CommentDetailsActivityContract$CommentDetailsActivityResponse;", "()V", "createIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "input", "parseResult", "resultCode", "", "intent", "CommentDetailsActivityRequest", "CommentDetailsActivityResponse", "org.wordpress.android_wordpressJalapenoDebug"})
public final class CommentDetailsActivityContract extends androidx.activity.result.contract.ActivityResultContract<org.wordpress.android.ui.comments.unified.CommentDetailsActivityContract.CommentDetailsActivityRequest, org.wordpress.android.ui.comments.unified.CommentDetailsActivityContract.CommentDetailsActivityResponse> {
    
    public CommentDetailsActivityContract() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public android.content.Intent createIntent(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.comments.unified.CommentDetailsActivityContract.CommentDetailsActivityRequest input) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public org.wordpress.android.ui.comments.unified.CommentDetailsActivityContract.CommentDetailsActivityResponse parseResult(int resultCode, @org.jetbrains.annotations.Nullable()
    android.content.Intent intent) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0010\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0011\u001a\u00020\u0007H\u00c6\u0003J\'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u00c6\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0016\u001a\u00020\u0017H\u00d6\u0001J\t\u0010\u0018\u001a\u00020\u0019H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e\u00a8\u0006\u001a"}, d2 = {"Lorg/wordpress/android/ui/comments/unified/CommentDetailsActivityContract$CommentDetailsActivityRequest;", "", "commentId", "", "commentStatus", "Lorg/wordpress/android/fluxc/model/CommentStatus;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "(JLorg/wordpress/android/fluxc/model/CommentStatus;Lorg/wordpress/android/fluxc/model/SiteModel;)V", "getCommentId", "()J", "getCommentStatus", "()Lorg/wordpress/android/fluxc/model/CommentStatus;", "getSite", "()Lorg/wordpress/android/fluxc/model/SiteModel;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class CommentDetailsActivityRequest {
        private final long commentId = 0L;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.fluxc.model.CommentStatus commentStatus = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.fluxc.model.SiteModel site = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.comments.unified.CommentDetailsActivityContract.CommentDetailsActivityRequest copy(long commentId, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.CommentStatus commentStatus, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.SiteModel site) {
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
        
        public CommentDetailsActivityRequest(long commentId, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.CommentStatus commentStatus, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.SiteModel site) {
            super();
        }
        
        public final long component1() {
            return 0L;
        }
        
        public final long getCommentId() {
            return 0L;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.fluxc.model.CommentStatus component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.fluxc.model.CommentStatus getCommentStatus() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.fluxc.model.SiteModel component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.fluxc.model.SiteModel getSite() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\f\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0011\u001a\u00020\u0012H\u00d6\u0001J\t\u0010\u0013\u001a\u00020\u0014H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0015"}, d2 = {"Lorg/wordpress/android/ui/comments/unified/CommentDetailsActivityContract$CommentDetailsActivityResponse;", "", "commentId", "", "commentStatus", "Lorg/wordpress/android/fluxc/model/CommentStatus;", "(JLorg/wordpress/android/fluxc/model/CommentStatus;)V", "getCommentId", "()J", "getCommentStatus", "()Lorg/wordpress/android/fluxc/model/CommentStatus;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class CommentDetailsActivityResponse {
        private final long commentId = 0L;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.fluxc.model.CommentStatus commentStatus = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.comments.unified.CommentDetailsActivityContract.CommentDetailsActivityResponse copy(long commentId, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.CommentStatus commentStatus) {
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
        
        public CommentDetailsActivityResponse(long commentId, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.CommentStatus commentStatus) {
            super();
        }
        
        public final long component1() {
            return 0L;
        }
        
        public final long getCommentId() {
            return 0L;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.fluxc.model.CommentStatus component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.fluxc.model.CommentStatus getCommentStatus() {
            return null;
        }
    }
}