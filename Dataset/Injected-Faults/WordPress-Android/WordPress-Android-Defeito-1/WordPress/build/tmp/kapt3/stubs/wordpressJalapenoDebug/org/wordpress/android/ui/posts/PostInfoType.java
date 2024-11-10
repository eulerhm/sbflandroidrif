package org.wordpress.android.ui.posts;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/posts/PostInfoType;", "", "()V", "PostInfo", "PostNoInfo", "Lorg/wordpress/android/ui/posts/PostInfoType$PostNoInfo;", "Lorg/wordpress/android/ui/posts/PostInfoType$PostInfo;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class PostInfoType {
    
    private PostInfoType() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/posts/PostInfoType$PostNoInfo;", "Lorg/wordpress/android/ui/posts/PostInfoType;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class PostNoInfo extends org.wordpress.android.ui.posts.PostInfoType {
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.ui.posts.PostInfoType.PostNoInfo INSTANCE = null;
        
        private PostNoInfo() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\f\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u000e\u001a\u00020\u00052\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u00d6\u0003J\t\u0010\u0011\u001a\u00020\u0012H\u00d6\u0001J\t\u0010\u0013\u001a\u00020\u0014H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0015"}, d2 = {"Lorg/wordpress/android/ui/posts/PostInfoType$PostInfo;", "Lorg/wordpress/android/ui/posts/PostInfoType;", "post", "Lorg/wordpress/android/fluxc/model/PostModel;", "hasError", "", "(Lorg/wordpress/android/fluxc/model/PostModel;Z)V", "getHasError", "()Z", "getPost", "()Lorg/wordpress/android/fluxc/model/PostModel;", "component1", "component2", "copy", "equals", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class PostInfo extends org.wordpress.android.ui.posts.PostInfoType {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.fluxc.model.PostModel post = null;
        private final boolean hasError = false;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.posts.PostInfoType.PostInfo copy(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.PostModel post, boolean hasError) {
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
        
        public PostInfo(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.PostModel post, boolean hasError) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.fluxc.model.PostModel component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.fluxc.model.PostModel getPost() {
            return null;
        }
        
        public final boolean component2() {
            return false;
        }
        
        public final boolean getHasError() {
            return false;
        }
    }
}