package org.wordpress.android.ui.engagement;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0005\u0007\b\t\n\u000bB\u0011\b\u0004\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0005\f\r\u000e\u000f\u0010\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/engagement/EngagedListNavigationEvent;", "", "closeUserProfileIfOpened", "", "(Z)V", "getCloseUserProfileIfOpened", "()Z", "OpenUserProfileBottomSheet", "PreviewCommentInReader", "PreviewPostInReader", "PreviewSiteById", "PreviewSiteByUrl", "Lorg/wordpress/android/ui/engagement/EngagedListNavigationEvent$PreviewSiteByUrl;", "Lorg/wordpress/android/ui/engagement/EngagedListNavigationEvent$PreviewSiteById;", "Lorg/wordpress/android/ui/engagement/EngagedListNavigationEvent$PreviewCommentInReader;", "Lorg/wordpress/android/ui/engagement/EngagedListNavigationEvent$PreviewPostInReader;", "Lorg/wordpress/android/ui/engagement/EngagedListNavigationEvent$OpenUserProfileBottomSheet;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class EngagedListNavigationEvent {
    private final boolean closeUserProfileIfOpened = false;
    
    private EngagedListNavigationEvent(boolean closeUserProfileIfOpened) {
        super();
    }
    
    public final boolean getCloseUserProfileIfOpened() {
        return false;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\n\u001a\u00020\u0003H\u00c6\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u00d6\u0003J\t\u0010\u0010\u001a\u00020\u0011H\u00d6\u0001J\t\u0010\u0012\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007\u00a8\u0006\u0013"}, d2 = {"Lorg/wordpress/android/ui/engagement/EngagedListNavigationEvent$PreviewSiteByUrl;", "Lorg/wordpress/android/ui/engagement/EngagedListNavigationEvent;", "siteUrl", "", "source", "(Ljava/lang/String;Ljava/lang/String;)V", "getSiteUrl", "()Ljava/lang/String;", "getSource", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class PreviewSiteByUrl extends org.wordpress.android.ui.engagement.EngagedListNavigationEvent {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String siteUrl = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String source = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.engagement.EngagedListNavigationEvent.PreviewSiteByUrl copy(@org.jetbrains.annotations.NotNull()
        java.lang.String siteUrl, @org.jetbrains.annotations.NotNull()
        java.lang.String source) {
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
        
        public PreviewSiteByUrl(@org.jetbrains.annotations.NotNull()
        java.lang.String siteUrl, @org.jetbrains.annotations.NotNull()
        java.lang.String source) {
            super(false);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getSiteUrl() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getSource() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\f\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u00d6\u0003J\t\u0010\u0012\u001a\u00020\u0013H\u00d6\u0001J\t\u0010\u0014\u001a\u00020\u0005H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0015"}, d2 = {"Lorg/wordpress/android/ui/engagement/EngagedListNavigationEvent$PreviewSiteById;", "Lorg/wordpress/android/ui/engagement/EngagedListNavigationEvent;", "siteId", "", "source", "", "(JLjava/lang/String;)V", "getSiteId", "()J", "getSource", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class PreviewSiteById extends org.wordpress.android.ui.engagement.EngagedListNavigationEvent {
        private final long siteId = 0L;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String source = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.engagement.EngagedListNavigationEvent.PreviewSiteById copy(long siteId, @org.jetbrains.annotations.NotNull()
        java.lang.String source) {
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
        
        public PreviewSiteById(long siteId, @org.jetbrains.annotations.NotNull()
        java.lang.String source) {
            super(false);
        }
        
        public final long component1() {
            return 0L;
        }
        
        public final long getSiteId() {
            return 0L;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getSource() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0010\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0011\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0012\u001a\u00020\u0007H\u00c6\u0003J1\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u00c6\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u00d6\u0003J\t\u0010\u0018\u001a\u00020\u0019H\u00d6\u0001J\t\u0010\u001a\u001a\u00020\u001bH\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e\u00a8\u0006\u001c"}, d2 = {"Lorg/wordpress/android/ui/engagement/EngagedListNavigationEvent$PreviewCommentInReader;", "Lorg/wordpress/android/ui/engagement/EngagedListNavigationEvent;", "siteId", "", "commentPostId", "postOrCommentId", "source", "Lorg/wordpress/android/ui/reader/comments/ThreadedCommentsActionSource;", "(JJJLorg/wordpress/android/ui/reader/comments/ThreadedCommentsActionSource;)V", "getCommentPostId", "()J", "getPostOrCommentId", "getSiteId", "getSource", "()Lorg/wordpress/android/ui/reader/comments/ThreadedCommentsActionSource;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class PreviewCommentInReader extends org.wordpress.android.ui.engagement.EngagedListNavigationEvent {
        private final long siteId = 0L;
        private final long commentPostId = 0L;
        private final long postOrCommentId = 0L;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.reader.comments.ThreadedCommentsActionSource source = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.engagement.EngagedListNavigationEvent.PreviewCommentInReader copy(long siteId, long commentPostId, long postOrCommentId, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.reader.comments.ThreadedCommentsActionSource source) {
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
        
        public PreviewCommentInReader(long siteId, long commentPostId, long postOrCommentId, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.reader.comments.ThreadedCommentsActionSource source) {
            super(false);
        }
        
        public final long component1() {
            return 0L;
        }
        
        public final long getSiteId() {
            return 0L;
        }
        
        public final long component2() {
            return 0L;
        }
        
        public final long getCommentPostId() {
            return 0L;
        }
        
        public final long component3() {
            return 0L;
        }
        
        public final long getPostOrCommentId() {
            return 0L;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.reader.comments.ThreadedCommentsActionSource component4() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.reader.comments.ThreadedCommentsActionSource getSource() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\n\u001a\u00020\u0003H\u00c6\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u00d6\u0003J\t\u0010\u0010\u001a\u00020\u0011H\u00d6\u0001J\t\u0010\u0012\u001a\u00020\u0013H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007\u00a8\u0006\u0014"}, d2 = {"Lorg/wordpress/android/ui/engagement/EngagedListNavigationEvent$PreviewPostInReader;", "Lorg/wordpress/android/ui/engagement/EngagedListNavigationEvent;", "siteId", "", "postId", "(JJ)V", "getPostId", "()J", "getSiteId", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class PreviewPostInReader extends org.wordpress.android.ui.engagement.EngagedListNavigationEvent {
        private final long siteId = 0L;
        private final long postId = 0L;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.engagement.EngagedListNavigationEvent.PreviewPostInReader copy(long siteId, long postId) {
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
        
        public PreviewPostInReader(long siteId, long postId) {
            super(false);
        }
        
        public final long component1() {
            return 0L;
        }
        
        public final long getSiteId() {
            return 0L;
        }
        
        public final long component2() {
            return 0L;
        }
        
        public final long getPostId() {
            return 0L;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001:\u0001!Bh\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012O\b\u0002\u0010\u0004\u001aI\u0012\u0013\u0012\u00110\u0006\u00a2\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\n\u00a2\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u00110\n\u00a2\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\r\u0018\u00010\u0005\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u000e\u00a2\u0006\u0002\u0010\u000fJ\t\u0010\u0016\u001a\u00020\u0003H\u00c6\u0003JP\u0010\u0017\u001aI\u0012\u0013\u0012\u00110\u0006\u00a2\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\n\u00a2\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u00110\n\u00a2\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\r\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u000eH\u00c6\u0003Jp\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032O\b\u0002\u0010\u0004\u001aI\u0012\u0013\u0012\u00110\u0006\u00a2\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\n\u00a2\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u00110\n\u00a2\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\r\u0018\u00010\u00052\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u000eH\u00c6\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u00d6\u0003J\t\u0010\u001e\u001a\u00020\u001fH\u00d6\u0001J\t\u0010 \u001a\u00020\nH\u00d6\u0001RX\u0010\u0004\u001aI\u0012\u0013\u0012\u00110\u0006\u00a2\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\n\u00a2\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u00110\n\u00a2\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\r\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0013\u0010\f\u001a\u0004\u0018\u00010\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015\u00a8\u0006\""}, d2 = {"Lorg/wordpress/android/ui/engagement/EngagedListNavigationEvent$OpenUserProfileBottomSheet;", "Lorg/wordpress/android/ui/engagement/EngagedListNavigationEvent;", "userProfile", "Lorg/wordpress/android/ui/engagement/EngagedListNavigationEvent$OpenUserProfileBottomSheet$UserProfile;", "onClick", "Lkotlin/Function3;", "", "Lkotlin/ParameterName;", "name", "siteId", "", "siteUrl", "source", "", "Lorg/wordpress/android/ui/engagement/EngagementNavigationSource;", "(Lorg/wordpress/android/ui/engagement/EngagedListNavigationEvent$OpenUserProfileBottomSheet$UserProfile;Lkotlin/jvm/functions/Function3;Lorg/wordpress/android/ui/engagement/EngagementNavigationSource;)V", "getOnClick", "()Lkotlin/jvm/functions/Function3;", "getSource", "()Lorg/wordpress/android/ui/engagement/EngagementNavigationSource;", "getUserProfile", "()Lorg/wordpress/android/ui/engagement/EngagedListNavigationEvent$OpenUserProfileBottomSheet$UserProfile;", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "UserProfile", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class OpenUserProfileBottomSheet extends org.wordpress.android.ui.engagement.EngagedListNavigationEvent {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.engagement.EngagedListNavigationEvent.OpenUserProfileBottomSheet.UserProfile userProfile = null;
        @org.jetbrains.annotations.Nullable()
        private final kotlin.jvm.functions.Function3<java.lang.Long, java.lang.String, java.lang.String, kotlin.Unit> onClick = null;
        @org.jetbrains.annotations.Nullable()
        private final org.wordpress.android.ui.engagement.EngagementNavigationSource source = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.engagement.EngagedListNavigationEvent.OpenUserProfileBottomSheet copy(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.engagement.EngagedListNavigationEvent.OpenUserProfileBottomSheet.UserProfile userProfile, @org.jetbrains.annotations.Nullable()
        kotlin.jvm.functions.Function3<? super java.lang.Long, ? super java.lang.String, ? super java.lang.String, kotlin.Unit> onClick, @org.jetbrains.annotations.Nullable()
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
        
        public OpenUserProfileBottomSheet(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.engagement.EngagedListNavigationEvent.OpenUserProfileBottomSheet.UserProfile userProfile, @org.jetbrains.annotations.Nullable()
        kotlin.jvm.functions.Function3<? super java.lang.Long, ? super java.lang.String, ? super java.lang.String, kotlin.Unit> onClick, @org.jetbrains.annotations.Nullable()
        org.wordpress.android.ui.engagement.EngagementNavigationSource source) {
            super(false);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.engagement.EngagedListNavigationEvent.OpenUserProfileBottomSheet.UserProfile component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.engagement.EngagedListNavigationEvent.OpenUserProfileBottomSheet.UserProfile getUserProfile() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final kotlin.jvm.functions.Function3<java.lang.Long, java.lang.String, java.lang.String, kotlin.Unit> component2() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final kotlin.jvm.functions.Function3<java.lang.Long, java.lang.String, java.lang.String, kotlin.Unit> getOnClick() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final org.wordpress.android.ui.engagement.EngagementNavigationSource component3() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final org.wordpress.android.ui.engagement.EngagementNavigationSource getSource() {
            return null;
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BE\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0002\u0010\fJ\t\u0010\u0017\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0018\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0019\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001c\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001e\u001a\u00020\u000bH\u00c6\u0003JY\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u000bH\u00c6\u0001J\u0013\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010#\u001a\u00020$H\u00d6\u0001J\t\u0010%\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000eR\u0011\u0010\t\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000eR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000e\u00a8\u0006&"}, d2 = {"Lorg/wordpress/android/ui/engagement/EngagedListNavigationEvent$OpenUserProfileBottomSheet$UserProfile;", "", "userAvatarUrl", "", "blavatarUrl", "userName", "userLogin", "userBio", "siteTitle", "siteUrl", "siteId", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;J)V", "getBlavatarUrl", "()Ljava/lang/String;", "getSiteId", "()J", "getSiteTitle", "getSiteUrl", "getUserAvatarUrl", "getUserBio", "getUserLogin", "getUserName", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", "other", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class UserProfile {
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String userAvatarUrl = null;
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String blavatarUrl = null;
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String userName = null;
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String userLogin = null;
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String userBio = null;
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String siteTitle = null;
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String siteUrl = null;
            private final long siteId = 0L;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.engagement.EngagedListNavigationEvent.OpenUserProfileBottomSheet.UserProfile copy(@org.jetbrains.annotations.NotNull()
            java.lang.String userAvatarUrl, @org.jetbrains.annotations.NotNull()
            java.lang.String blavatarUrl, @org.jetbrains.annotations.NotNull()
            java.lang.String userName, @org.jetbrains.annotations.NotNull()
            java.lang.String userLogin, @org.jetbrains.annotations.NotNull()
            java.lang.String userBio, @org.jetbrains.annotations.NotNull()
            java.lang.String siteTitle, @org.jetbrains.annotations.NotNull()
            java.lang.String siteUrl, long siteId) {
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
            
            public UserProfile(@org.jetbrains.annotations.NotNull()
            java.lang.String userAvatarUrl, @org.jetbrains.annotations.NotNull()
            java.lang.String blavatarUrl, @org.jetbrains.annotations.NotNull()
            java.lang.String userName, @org.jetbrains.annotations.NotNull()
            java.lang.String userLogin, @org.jetbrains.annotations.NotNull()
            java.lang.String userBio, @org.jetbrains.annotations.NotNull()
            java.lang.String siteTitle, @org.jetbrains.annotations.NotNull()
            java.lang.String siteUrl, long siteId) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getUserAvatarUrl() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String component2() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getBlavatarUrl() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String component3() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getUserName() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String component4() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getUserLogin() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String component5() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getUserBio() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String component6() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getSiteTitle() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String component7() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getSiteUrl() {
                return null;
            }
            
            public final long component8() {
                return 0L;
            }
            
            public final long getSiteId() {
                return 0L;
            }
        }
    }
}