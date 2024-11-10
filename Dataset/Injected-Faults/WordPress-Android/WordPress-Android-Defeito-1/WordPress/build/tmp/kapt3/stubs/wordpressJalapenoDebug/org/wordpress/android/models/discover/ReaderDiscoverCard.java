package org.wordpress.android.models.discover;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u0003\u0004\u0005\u0006B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0004\u0007\b\t\n\u00a8\u0006\u000b"}, d2 = {"Lorg/wordpress/android/models/discover/ReaderDiscoverCard;", "", "()V", "InterestsYouMayLikeCard", "ReaderPostCard", "ReaderRecommendedBlogsCard", "WelcomeBannerCard", "Lorg/wordpress/android/models/discover/ReaderDiscoverCard$WelcomeBannerCard;", "Lorg/wordpress/android/models/discover/ReaderDiscoverCard$InterestsYouMayLikeCard;", "Lorg/wordpress/android/models/discover/ReaderDiscoverCard$ReaderPostCard;", "Lorg/wordpress/android/models/discover/ReaderDiscoverCard$ReaderRecommendedBlogsCard;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class ReaderDiscoverCard {
    
    private ReaderDiscoverCard() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/models/discover/ReaderDiscoverCard$WelcomeBannerCard;", "Lorg/wordpress/android/models/discover/ReaderDiscoverCard;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class WelcomeBannerCard extends org.wordpress.android.models.discover.ReaderDiscoverCard {
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.models.discover.ReaderDiscoverCard.WelcomeBannerCard INSTANCE = null;
        
        private WelcomeBannerCard() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/models/discover/ReaderDiscoverCard$InterestsYouMayLikeCard;", "Lorg/wordpress/android/models/discover/ReaderDiscoverCard;", "interests", "Lorg/wordpress/android/models/ReaderTagList;", "(Lorg/wordpress/android/models/ReaderTagList;)V", "getInterests", "()Lorg/wordpress/android/models/ReaderTagList;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class InterestsYouMayLikeCard extends org.wordpress.android.models.discover.ReaderDiscoverCard {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.models.ReaderTagList interests = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.models.discover.ReaderDiscoverCard.InterestsYouMayLikeCard copy(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.models.ReaderTagList interests) {
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
        
        public InterestsYouMayLikeCard(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.models.ReaderTagList interests) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.models.ReaderTagList component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.models.ReaderTagList getInterests() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/models/discover/ReaderDiscoverCard$ReaderPostCard;", "Lorg/wordpress/android/models/discover/ReaderDiscoverCard;", "post", "Lorg/wordpress/android/models/ReaderPost;", "(Lorg/wordpress/android/models/ReaderPost;)V", "getPost", "()Lorg/wordpress/android/models/ReaderPost;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class ReaderPostCard extends org.wordpress.android.models.discover.ReaderDiscoverCard {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.models.ReaderPost post = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.models.discover.ReaderDiscoverCard.ReaderPostCard copy(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.models.ReaderPost post) {
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
        
        public ReaderPostCard(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.models.ReaderPost post) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.models.ReaderPost component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.models.ReaderPost getPost() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\u0005J\u000f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\u0019\u0010\t\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u00d6\u0003J\t\u0010\u000e\u001a\u00020\u000fH\u00d6\u0001J\t\u0010\u0010\u001a\u00020\u0011H\u00d6\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\u0012"}, d2 = {"Lorg/wordpress/android/models/discover/ReaderDiscoverCard$ReaderRecommendedBlogsCard;", "Lorg/wordpress/android/models/discover/ReaderDiscoverCard;", "blogs", "", "Lorg/wordpress/android/models/ReaderBlog;", "(Ljava/util/List;)V", "getBlogs", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class ReaderRecommendedBlogsCard extends org.wordpress.android.models.discover.ReaderDiscoverCard {
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<org.wordpress.android.models.ReaderBlog> blogs = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.models.discover.ReaderDiscoverCard.ReaderRecommendedBlogsCard copy(@org.jetbrains.annotations.NotNull()
        java.util.List<? extends org.wordpress.android.models.ReaderBlog> blogs) {
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
        
        public ReaderRecommendedBlogsCard(@org.jetbrains.annotations.NotNull()
        java.util.List<? extends org.wordpress.android.models.ReaderBlog> blogs) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.models.ReaderBlog> component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.models.ReaderBlog> getBlogs() {
            return null;
        }
    }
}