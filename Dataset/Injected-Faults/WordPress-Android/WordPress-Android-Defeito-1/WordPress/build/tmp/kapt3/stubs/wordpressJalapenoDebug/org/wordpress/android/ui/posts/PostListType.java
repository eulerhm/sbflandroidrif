package org.wordpress.android.ui.posts;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\t\b\u0086\u0001\u0018\u0000 \u00112\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0011B\u0015\b\u0002\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\u0005R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\t8F\u00a2\u0006\u0006\u001a\u0004\b\n\u0010\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010\u00a8\u0006\u0012"}, d2 = {"Lorg/wordpress/android/ui/posts/PostListType;", "", "postStatuses", "", "Lorg/wordpress/android/fluxc/model/post/PostStatus;", "(Ljava/lang/String;ILjava/util/List;)V", "getPostStatuses", "()Ljava/util/List;", "titleResId", "", "getTitleResId", "()I", "PUBLISHED", "DRAFTS", "SCHEDULED", "TRASHED", "SEARCH", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public enum PostListType {
    /*public static final*/ PUBLISHED /* = new PUBLISHED(null) */,
    /*public static final*/ DRAFTS /* = new DRAFTS(null) */,
    /*public static final*/ SCHEDULED /* = new SCHEDULED(null) */,
    /*public static final*/ TRASHED /* = new TRASHED(null) */,
    /*public static final*/ SEARCH /* = new SEARCH(null) */;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<org.wordpress.android.fluxc.model.post.PostStatus> postStatuses = null;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.posts.PostListType.Companion Companion = null;
    
    PostListType(java.util.List<? extends org.wordpress.android.fluxc.model.post.PostStatus> postStatuses) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<org.wordpress.android.fluxc.model.post.PostStatus> getPostStatuses() {
        return null;
    }
    
    public final int getTitleResId() {
        return 0;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/posts/PostListType$Companion;", "", "()V", "fromPostStatus", "Lorg/wordpress/android/ui/posts/PostListType;", "status", "Lorg/wordpress/android/fluxc/model/post/PostStatus;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.posts.PostListType fromPostStatus(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.post.PostStatus status) {
            return null;
        }
    }
}