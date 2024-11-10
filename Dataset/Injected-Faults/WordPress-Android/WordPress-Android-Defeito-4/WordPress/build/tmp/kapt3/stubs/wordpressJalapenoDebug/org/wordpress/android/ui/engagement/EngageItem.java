package org.wordpress.android.ui.engagement;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u0007\b\t\nB\u000f\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0003\u000b\f\r\u00a8\u0006\u000e"}, d2 = {"Lorg/wordpress/android/ui/engagement/EngageItem;", "", "type", "Lorg/wordpress/android/ui/engagement/EngageItem$EngageItemType;", "(Lorg/wordpress/android/ui/engagement/EngageItem$EngageItemType;)V", "getType", "()Lorg/wordpress/android/ui/engagement/EngageItem$EngageItemType;", "EngageItemType", "LikedItem", "Liker", "NextLikesPageLoader", "Lorg/wordpress/android/ui/engagement/EngageItem$LikedItem;", "Lorg/wordpress/android/ui/engagement/EngageItem$Liker;", "Lorg/wordpress/android/ui/engagement/EngageItem$NextLikesPageLoader;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class EngageItem {
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.ui.engagement.EngageItem.EngageItemType type = null;
    
    private EngageItem(org.wordpress.android.ui.engagement.EngageItem.EngageItemType type) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.engagement.EngageItem.EngageItemType getType() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b%\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u008c\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\u0007\u0012\u0006\u0010\f\u001a\u00020\t\u0012\u0006\u0010\r\u001a\u00020\t\u0012\u0006\u0010\u000e\u001a\u00020\t\u0012\u0006\u0010\u000f\u001a\u00020\u0007\u0012K\u0010\u0010\u001aG\u0012\u0013\u0012\u00110\t\u00a2\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0013\u0012\u00110\u0007\u00a2\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0015\u0012\u0013\u0012\u00110\u0007\u00a2\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\u00170\u0011\u0012\u0006\u0010\u0018\u001a\u00020\u0007\u0012`\u0010\u0019\u001a\\\u0012\u0013\u0012\u00110\t\u00a2\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0013\u0012\u00110\u0007\u00a2\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0015\u0012\u0013\u0012\u00110\t\u00a2\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u001b\u0012\u0013\u0012\u00110\t\u00a2\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u001c\u0012\u0004\u0012\u00020\u00170\u001a\u00a2\u0006\u0002\u0010\u001dJ\t\u00101\u001a\u00020\u0003H\u00c6\u0003J\t\u00102\u001a\u00020\u0007H\u00c6\u0003JN\u00103\u001aG\u0012\u0013\u0012\u00110\t\u00a2\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0013\u0012\u00110\u0007\u00a2\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0015\u0012\u0013\u0012\u00110\u0007\u00a2\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\u00170\u0011H\u00c6\u0003J\t\u00104\u001a\u00020\u0007H\u00c6\u0003Jc\u00105\u001a\\\u0012\u0013\u0012\u00110\t\u00a2\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0013\u0012\u00110\u0007\u00a2\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0015\u0012\u0013\u0012\u00110\t\u00a2\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u001b\u0012\u0013\u0012\u00110\t\u00a2\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u001c\u0012\u0004\u0012\u00020\u00170\u001aH\u00c6\u0003J\t\u00106\u001a\u00020\u0005H\u00c6\u0003J\t\u00107\u001a\u00020\u0007H\u00c6\u0003J\t\u00108\u001a\u00020\tH\u00c6\u0003J\t\u00109\u001a\u00020\tH\u00c6\u0003J\t\u0010:\u001a\u00020\u0007H\u00c6\u0003J\t\u0010;\u001a\u00020\tH\u00c6\u0003J\t\u0010<\u001a\u00020\tH\u00c6\u0003J\t\u0010=\u001a\u00020\tH\u00c6\u0003J\u00aa\u0002\u0010>\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\u00072\b\b\u0002\u0010\f\u001a\u00020\t2\b\b\u0002\u0010\r\u001a\u00020\t2\b\b\u0002\u0010\u000e\u001a\u00020\t2\b\b\u0002\u0010\u000f\u001a\u00020\u00072M\b\u0002\u0010\u0010\u001aG\u0012\u0013\u0012\u00110\t\u00a2\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0013\u0012\u00110\u0007\u00a2\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0015\u0012\u0013\u0012\u00110\u0007\u00a2\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\u00170\u00112\b\b\u0002\u0010\u0018\u001a\u00020\u00072b\b\u0002\u0010\u0019\u001a\\\u0012\u0013\u0012\u00110\t\u00a2\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0013\u0012\u00110\u0007\u00a2\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0015\u0012\u0013\u0012\u00110\t\u00a2\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u001b\u0012\u0013\u0012\u00110\t\u00a2\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u001c\u0012\u0004\u0012\u00020\u00170\u001aH\u00c6\u0001J\u0013\u0010?\u001a\u00020@2\b\u0010A\u001a\u0004\u0018\u00010BH\u00d6\u0003J\t\u0010C\u001a\u00020DH\u00d6\u0001J\t\u0010E\u001a\u00020\u0007H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0011\u0010\u000e\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0011\u0010\u000f\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010!R\u0011\u0010\r\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010#R\u0011\u0010\u0018\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010!R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010#R\u0011\u0010\f\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010#R\u0011\u0010\n\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010#R\u0011\u0010\u000b\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010!RV\u0010\u0010\u001aG\u0012\u0013\u0012\u00110\t\u00a2\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0013\u0012\u00110\u0007\u00a2\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0015\u0012\u0013\u0012\u00110\u0007\u00a2\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\u00170\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b+\u0010,Rk\u0010\u0019\u001a\\\u0012\u0013\u0012\u00110\t\u00a2\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0013\u0012\u00110\u0007\u00a2\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0015\u0012\u0013\u0012\u00110\t\u00a2\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u001b\u0012\u0013\u0012\u00110\t\u00a2\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u001c\u0012\u0004\u0012\u00020\u00170\u001a\u00a2\u0006\b\n\u0000\u001a\u0004\b-\u0010.R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b/\u00100\u00a8\u0006F"}, d2 = {"Lorg/wordpress/android/ui/engagement/EngageItem$LikedItem;", "Lorg/wordpress/android/ui/engagement/EngageItem;", "author", "Lorg/wordpress/android/ui/engagement/AuthorName;", "postOrCommentText", "", "authorAvatarUrl", "", "likedItemId", "", "likedItemSiteId", "likedItemSiteUrl", "likedItemPostId", "authorUserId", "authorPreferredSiteId", "authorPreferredSiteUrl", "onGravatarClick", "Lkotlin/Function3;", "Lkotlin/ParameterName;", "name", "siteId", "siteUrl", "source", "", "blogPreviewSource", "onHeaderClicked", "Lkotlin/Function4;", "postOrCommentId", "commentPostId", "(Lorg/wordpress/android/ui/engagement/AuthorName;Ljava/lang/CharSequence;Ljava/lang/String;JJLjava/lang/String;JJJLjava/lang/String;Lkotlin/jvm/functions/Function3;Ljava/lang/String;Lkotlin/jvm/functions/Function4;)V", "getAuthor", "()Lorg/wordpress/android/ui/engagement/AuthorName;", "getAuthorAvatarUrl", "()Ljava/lang/String;", "getAuthorPreferredSiteId", "()J", "getAuthorPreferredSiteUrl", "getAuthorUserId", "getBlogPreviewSource", "getLikedItemId", "getLikedItemPostId", "getLikedItemSiteId", "getLikedItemSiteUrl", "getOnGravatarClick", "()Lkotlin/jvm/functions/Function3;", "getOnHeaderClicked", "()Lkotlin/jvm/functions/Function4;", "getPostOrCommentText", "()Ljava/lang/CharSequence;", "component1", "component10", "component11", "component12", "component13", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class LikedItem extends org.wordpress.android.ui.engagement.EngageItem {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.engagement.AuthorName author = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.CharSequence postOrCommentText = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String authorAvatarUrl = null;
        private final long likedItemId = 0L;
        private final long likedItemSiteId = 0L;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String likedItemSiteUrl = null;
        private final long likedItemPostId = 0L;
        private final long authorUserId = 0L;
        private final long authorPreferredSiteId = 0L;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String authorPreferredSiteUrl = null;
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function3<java.lang.Long, java.lang.String, java.lang.String, kotlin.Unit> onGravatarClick = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String blogPreviewSource = null;
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function4<java.lang.Long, java.lang.String, java.lang.Long, java.lang.Long, kotlin.Unit> onHeaderClicked = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.engagement.EngageItem.LikedItem copy(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.engagement.AuthorName author, @org.jetbrains.annotations.NotNull()
        java.lang.CharSequence postOrCommentText, @org.jetbrains.annotations.NotNull()
        java.lang.String authorAvatarUrl, long likedItemId, long likedItemSiteId, @org.jetbrains.annotations.NotNull()
        java.lang.String likedItemSiteUrl, long likedItemPostId, long authorUserId, long authorPreferredSiteId, @org.jetbrains.annotations.NotNull()
        java.lang.String authorPreferredSiteUrl, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function3<? super java.lang.Long, ? super java.lang.String, ? super java.lang.String, kotlin.Unit> onGravatarClick, @org.jetbrains.annotations.NotNull()
        java.lang.String blogPreviewSource, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function4<? super java.lang.Long, ? super java.lang.String, ? super java.lang.Long, ? super java.lang.Long, kotlin.Unit> onHeaderClicked) {
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
        
        public LikedItem(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.engagement.AuthorName author, @org.jetbrains.annotations.NotNull()
        java.lang.CharSequence postOrCommentText, @org.jetbrains.annotations.NotNull()
        java.lang.String authorAvatarUrl, long likedItemId, long likedItemSiteId, @org.jetbrains.annotations.NotNull()
        java.lang.String likedItemSiteUrl, long likedItemPostId, long authorUserId, long authorPreferredSiteId, @org.jetbrains.annotations.NotNull()
        java.lang.String authorPreferredSiteUrl, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function3<? super java.lang.Long, ? super java.lang.String, ? super java.lang.String, kotlin.Unit> onGravatarClick, @org.jetbrains.annotations.NotNull()
        java.lang.String blogPreviewSource, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function4<? super java.lang.Long, ? super java.lang.String, ? super java.lang.Long, ? super java.lang.Long, kotlin.Unit> onHeaderClicked) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.engagement.AuthorName component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.engagement.AuthorName getAuthor() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.CharSequence component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.CharSequence getPostOrCommentText() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getAuthorAvatarUrl() {
            return null;
        }
        
        public final long component4() {
            return 0L;
        }
        
        public final long getLikedItemId() {
            return 0L;
        }
        
        public final long component5() {
            return 0L;
        }
        
        public final long getLikedItemSiteId() {
            return 0L;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component6() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getLikedItemSiteUrl() {
            return null;
        }
        
        public final long component7() {
            return 0L;
        }
        
        public final long getLikedItemPostId() {
            return 0L;
        }
        
        public final long component8() {
            return 0L;
        }
        
        public final long getAuthorUserId() {
            return 0L;
        }
        
        public final long component9() {
            return 0L;
        }
        
        public final long getAuthorPreferredSiteId() {
            return 0L;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component10() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getAuthorPreferredSiteUrl() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function3<java.lang.Long, java.lang.String, java.lang.String, kotlin.Unit> component11() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function3<java.lang.Long, java.lang.String, java.lang.String, kotlin.Unit> getOnGravatarClick() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component12() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getBlogPreviewSource() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function4<java.lang.Long, java.lang.String, java.lang.Long, java.lang.Long, kotlin.Unit> component13() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function4<java.lang.Long, java.lang.String, java.lang.Long, java.lang.Long, kotlin.Unit> getOnHeaderClicked() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b!\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u00a5\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0006\u0012\u0006\u0010\u000b\u001a\u00020\u0006\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u0003\u0012\u0006\u0010\u000e\u001a\u00020\u0003\u0012<\b\u0002\u0010\u000f\u001a6\u0012\u0013\u0012\u00110\u0011\u00a2\u0006\f\b\u0012\u0012\b\b\u0002\u0012\u0004\b\b(\u0013\u0012\u0015\u0012\u0013\u0018\u00010\u0014\u00a2\u0006\f\b\u0012\u0012\b\b\u0002\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0010\u0012\b\u0010\u0015\u001a\u0004\u0018\u00010\u0014\u00a2\u0006\u0002\u0010\u0017J\t\u0010)\u001a\u00020\u0003H\u00c6\u0003J\t\u0010*\u001a\u00020\u0003H\u00c6\u0003J\t\u0010+\u001a\u00020\u0003H\u00c6\u0003J=\u0010,\u001a6\u0012\u0013\u0012\u00110\u0011\u00a2\u0006\f\b\u0012\u0012\b\b\u0002\u0012\u0004\b\b(\u0013\u0012\u0015\u0012\u0013\u0018\u00010\u0014\u00a2\u0006\f\b\u0012\u0012\b\b\u0002\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0010H\u00c6\u0003J\u000b\u0010-\u001a\u0004\u0018\u00010\u0014H\u00c6\u0003J\t\u0010.\u001a\u00020\u0003H\u00c6\u0003J\t\u0010/\u001a\u00020\u0006H\u00c6\u0003J\t\u00100\u001a\u00020\u0003H\u00c6\u0003J\t\u00101\u001a\u00020\u0003H\u00c6\u0003J\t\u00102\u001a\u00020\u0003H\u00c6\u0003J\t\u00103\u001a\u00020\u0006H\u00c6\u0003J\t\u00104\u001a\u00020\u0006H\u00c6\u0003J\t\u00105\u001a\u00020\u0003H\u00c6\u0003J\u00c1\u0001\u00106\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00062\b\b\u0002\u0010\u000b\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u00032<\b\u0002\u0010\u000f\u001a6\u0012\u0013\u0012\u00110\u0011\u00a2\u0006\f\b\u0012\u0012\b\b\u0002\u0012\u0004\b\b(\u0013\u0012\u0015\u0012\u0013\u0018\u00010\u0014\u00a2\u0006\f\b\u0012\u0012\b\b\u0002\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u00102\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0014H\u00c6\u0001J\u0013\u00107\u001a\u0002082\b\u00109\u001a\u0004\u0018\u00010:H\u00d6\u0003J\t\u0010;\u001a\u00020<H\u00d6\u0001J\t\u0010=\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0019RE\u0010\u000f\u001a6\u0012\u0013\u0012\u00110\u0011\u00a2\u0006\f\b\u0012\u0012\b\b\u0002\u0012\u0004\b\b(\u0013\u0012\u0015\u0012\u0013\u0018\u00010\u0014\u00a2\u0006\f\b\u0012\u0012\b\b\u0002\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u000e\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0019R\u0011\u0010\u000b\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010\f\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u0019R\u0011\u0010\r\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0019R\u0013\u0010\u0015\u001a\u0004\u0018\u00010\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0011\u0010\b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0019R\u0011\u0010\t\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0019R\u0011\u0010\n\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001fR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010\u001fR\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010\u0019\u00a8\u0006>"}, d2 = {"Lorg/wordpress/android/ui/engagement/EngageItem$Liker;", "Lorg/wordpress/android/ui/engagement/EngageItem;", "name", "", "login", "userSiteId", "", "userSiteUrl", "userAvatarUrl", "userBio", "userId", "preferredBlogId", "preferredBlogName", "preferredBlogUrl", "preferredBlogBlavatar", "onClick", "Lkotlin/Function2;", "Lorg/wordpress/android/ui/engagement/EngagedListNavigationEvent$OpenUserProfileBottomSheet$UserProfile;", "Lkotlin/ParameterName;", "userProfile", "Lorg/wordpress/android/ui/engagement/EngagementNavigationSource;", "source", "", "(Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;JJLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function2;Lorg/wordpress/android/ui/engagement/EngagementNavigationSource;)V", "getLogin", "()Ljava/lang/String;", "getName", "getOnClick", "()Lkotlin/jvm/functions/Function2;", "getPreferredBlogBlavatar", "getPreferredBlogId", "()J", "getPreferredBlogName", "getPreferredBlogUrl", "getSource", "()Lorg/wordpress/android/ui/engagement/EngagementNavigationSource;", "getUserAvatarUrl", "getUserBio", "getUserId", "getUserSiteId", "getUserSiteUrl", "component1", "component10", "component11", "component12", "component13", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Liker extends org.wordpress.android.ui.engagement.EngageItem {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String name = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String login = null;
        private final long userSiteId = 0L;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String userSiteUrl = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String userAvatarUrl = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String userBio = null;
        private final long userId = 0L;
        private final long preferredBlogId = 0L;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String preferredBlogName = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String preferredBlogUrl = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String preferredBlogBlavatar = null;
        @org.jetbrains.annotations.Nullable()
        private final kotlin.jvm.functions.Function2<org.wordpress.android.ui.engagement.EngagedListNavigationEvent.OpenUserProfileBottomSheet.UserProfile, org.wordpress.android.ui.engagement.EngagementNavigationSource, kotlin.Unit> onClick = null;
        @org.jetbrains.annotations.Nullable()
        private final org.wordpress.android.ui.engagement.EngagementNavigationSource source = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.engagement.EngageItem.Liker copy(@org.jetbrains.annotations.NotNull()
        java.lang.String name, @org.jetbrains.annotations.NotNull()
        java.lang.String login, long userSiteId, @org.jetbrains.annotations.NotNull()
        java.lang.String userSiteUrl, @org.jetbrains.annotations.NotNull()
        java.lang.String userAvatarUrl, @org.jetbrains.annotations.NotNull()
        java.lang.String userBio, long userId, long preferredBlogId, @org.jetbrains.annotations.NotNull()
        java.lang.String preferredBlogName, @org.jetbrains.annotations.NotNull()
        java.lang.String preferredBlogUrl, @org.jetbrains.annotations.NotNull()
        java.lang.String preferredBlogBlavatar, @org.jetbrains.annotations.Nullable()
        kotlin.jvm.functions.Function2<? super org.wordpress.android.ui.engagement.EngagedListNavigationEvent.OpenUserProfileBottomSheet.UserProfile, ? super org.wordpress.android.ui.engagement.EngagementNavigationSource, kotlin.Unit> onClick, @org.jetbrains.annotations.Nullable()
        org.wordpress.android.ui.engagement.EngagementNavigationSource source) {
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
        
        public Liker(@org.jetbrains.annotations.NotNull()
        java.lang.String name, @org.jetbrains.annotations.NotNull()
        java.lang.String login, long userSiteId, @org.jetbrains.annotations.NotNull()
        java.lang.String userSiteUrl, @org.jetbrains.annotations.NotNull()
        java.lang.String userAvatarUrl, @org.jetbrains.annotations.NotNull()
        java.lang.String userBio, long userId, long preferredBlogId, @org.jetbrains.annotations.NotNull()
        java.lang.String preferredBlogName, @org.jetbrains.annotations.NotNull()
        java.lang.String preferredBlogUrl, @org.jetbrains.annotations.NotNull()
        java.lang.String preferredBlogBlavatar, @org.jetbrains.annotations.Nullable()
        kotlin.jvm.functions.Function2<? super org.wordpress.android.ui.engagement.EngagedListNavigationEvent.OpenUserProfileBottomSheet.UserProfile, ? super org.wordpress.android.ui.engagement.EngagementNavigationSource, kotlin.Unit> onClick, @org.jetbrains.annotations.Nullable()
        org.wordpress.android.ui.engagement.EngagementNavigationSource source) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getName() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getLogin() {
            return null;
        }
        
        public final long component3() {
            return 0L;
        }
        
        public final long getUserSiteId() {
            return 0L;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component4() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getUserSiteUrl() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component5() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getUserAvatarUrl() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component6() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getUserBio() {
            return null;
        }
        
        public final long component7() {
            return 0L;
        }
        
        public final long getUserId() {
            return 0L;
        }
        
        public final long component8() {
            return 0L;
        }
        
        public final long getPreferredBlogId() {
            return 0L;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component9() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getPreferredBlogName() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component10() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getPreferredBlogUrl() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component11() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getPreferredBlogBlavatar() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final kotlin.jvm.functions.Function2<org.wordpress.android.ui.engagement.EngagedListNavigationEvent.OpenUserProfileBottomSheet.UserProfile, org.wordpress.android.ui.engagement.EngagementNavigationSource, kotlin.Unit> component12() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final kotlin.jvm.functions.Function2<org.wordpress.android.ui.engagement.EngagedListNavigationEvent.OpenUserProfileBottomSheet.UserProfile, org.wordpress.android.ui.engagement.EngagementNavigationSource, kotlin.Unit> getOnClick() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final org.wordpress.android.ui.engagement.EngagementNavigationSource component13() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final org.wordpress.android.ui.engagement.EngagementNavigationSource getSource() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\u0002\u0010\u0007J\t\u0010\u000b\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0003J#\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0001J\u0013\u0010\u000e\u001a\u00020\u00032\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u00d6\u0003J\t\u0010\u0011\u001a\u00020\u0012H\u00d6\u0001J\t\u0010\u0013\u001a\u00020\u0014H\u00d6\u0001R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\n\u00a8\u0006\u0015"}, d2 = {"Lorg/wordpress/android/ui/engagement/EngageItem$NextLikesPageLoader;", "Lorg/wordpress/android/ui/engagement/EngageItem;", "isLoading", "", "action", "Lkotlin/Function0;", "", "(ZLkotlin/jvm/functions/Function0;)V", "getAction", "()Lkotlin/jvm/functions/Function0;", "()Z", "component1", "component2", "copy", "equals", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class NextLikesPageLoader extends org.wordpress.android.ui.engagement.EngageItem {
        private final boolean isLoading = false;
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function0<kotlin.Unit> action = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.engagement.EngageItem.NextLikesPageLoader copy(boolean isLoading, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function0<kotlin.Unit> action) {
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
        
        public NextLikesPageLoader(boolean isLoading, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function0<kotlin.Unit> action) {
            super(null);
        }
        
        public final boolean component1() {
            return false;
        }
        
        public final boolean isLoading() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function0<kotlin.Unit> component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function0<kotlin.Unit> getAction() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005\u00a8\u0006\u0006"}, d2 = {"Lorg/wordpress/android/ui/engagement/EngageItem$EngageItemType;", "", "(Ljava/lang/String;I)V", "LIKED_ITEM", "LIKER", "NEXT_LIKES_PAGE_LOADER", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum EngageItemType {
        /*public static final*/ LIKED_ITEM /* = new LIKED_ITEM() */,
        /*public static final*/ LIKER /* = new LIKER() */,
        /*public static final*/ NEXT_LIKES_PAGE_LOADER /* = new NEXT_LIKES_PAGE_LOADER() */;
        
        EngageItemType() {
        }
    }
}