package org.wordpress.android.viewmodel.posts;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0003\u0006\u0007\b\u00a8\u0006\t"}, d2 = {"Lorg/wordpress/android/viewmodel/posts/PostListItemIdentifier;", "", "()V", "EndListIndicatorIdentifier", "LocalPostId", "RemotePostId", "Lorg/wordpress/android/viewmodel/posts/PostListItemIdentifier$LocalPostId;", "Lorg/wordpress/android/viewmodel/posts/PostListItemIdentifier$RemotePostId;", "Lorg/wordpress/android/viewmodel/posts/PostListItemIdentifier$EndListIndicatorIdentifier;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class PostListItemIdentifier {
    
    private PostListItemIdentifier() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/viewmodel/posts/PostListItemIdentifier$LocalPostId;", "Lorg/wordpress/android/viewmodel/posts/PostListItemIdentifier;", "id", "Lorg/wordpress/android/fluxc/model/LocalOrRemoteId$LocalId;", "(Lorg/wordpress/android/fluxc/model/LocalOrRemoteId$LocalId;)V", "getId", "()Lorg/wordpress/android/fluxc/model/LocalOrRemoteId$LocalId;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class LocalPostId extends org.wordpress.android.viewmodel.posts.PostListItemIdentifier {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.fluxc.model.LocalOrRemoteId.LocalId id = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.viewmodel.posts.PostListItemIdentifier.LocalPostId copy(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.LocalOrRemoteId.LocalId id) {
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
        
        public LocalPostId(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.LocalOrRemoteId.LocalId id) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.fluxc.model.LocalOrRemoteId.LocalId component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.fluxc.model.LocalOrRemoteId.LocalId getId() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/viewmodel/posts/PostListItemIdentifier$RemotePostId;", "Lorg/wordpress/android/viewmodel/posts/PostListItemIdentifier;", "id", "Lorg/wordpress/android/fluxc/model/LocalOrRemoteId$RemoteId;", "(Lorg/wordpress/android/fluxc/model/LocalOrRemoteId$RemoteId;)V", "getId", "()Lorg/wordpress/android/fluxc/model/LocalOrRemoteId$RemoteId;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class RemotePostId extends org.wordpress.android.viewmodel.posts.PostListItemIdentifier {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.fluxc.model.LocalOrRemoteId.RemoteId id = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.viewmodel.posts.PostListItemIdentifier.RemotePostId copy(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.LocalOrRemoteId.RemoteId id) {
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
        
        public RemotePostId(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.LocalOrRemoteId.RemoteId id) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.fluxc.model.LocalOrRemoteId.RemoteId component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.fluxc.model.LocalOrRemoteId.RemoteId getId() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/viewmodel/posts/PostListItemIdentifier$EndListIndicatorIdentifier;", "Lorg/wordpress/android/viewmodel/posts/PostListItemIdentifier;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class EndListIndicatorIdentifier extends org.wordpress.android.viewmodel.posts.PostListItemIdentifier {
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.viewmodel.posts.PostListItemIdentifier.EndListIndicatorIdentifier INSTANCE = null;
        
        private EndListIndicatorIdentifier() {
            super();
        }
    }
}