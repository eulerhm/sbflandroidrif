package org.wordpress.android.ui.reader.discover;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0014\u0003\u0004\u0005\u0006\u0007\b\t\n\u000b\f\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014\u0015\u0016B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0014\u0017\u0018\u0019\u001a\u001b\u001c\u001d\u001e\u001f !\"#$%&\'()*\u00a8\u0006+"}, d2 = {"Lorg/wordpress/android/ui/reader/discover/ReaderNavigationEvents;", "", "()V", "OpenEditorForReblog", "OpenPost", "OpenUrl", "ReplaceRelatedPostDetailsWithHistory", "SharePost", "ShowBlogPreview", "ShowBookmarkedSavedOnlyLocallyDialog", "ShowBookmarkedTab", "ShowEngagedPeopleList", "ShowMediaPreview", "ShowNoSitesToReblog", "ShowPostDetail", "ShowPostInWebView", "ShowPostsByTag", "ShowReaderComments", "ShowReaderSubs", "ShowRelatedPostDetails", "ShowReportPost", "ShowSitePickerForResult", "ShowVideoViewer", "Lorg/wordpress/android/ui/reader/discover/ReaderNavigationEvents$ShowPostDetail;", "Lorg/wordpress/android/ui/reader/discover/ReaderNavigationEvents$SharePost;", "Lorg/wordpress/android/ui/reader/discover/ReaderNavigationEvents$OpenPost;", "Lorg/wordpress/android/ui/reader/discover/ReaderNavigationEvents$ShowPostsByTag;", "Lorg/wordpress/android/ui/reader/discover/ReaderNavigationEvents$ShowReaderComments;", "Lorg/wordpress/android/ui/reader/discover/ReaderNavigationEvents$ShowNoSitesToReblog;", "Lorg/wordpress/android/ui/reader/discover/ReaderNavigationEvents$ShowSitePickerForResult;", "Lorg/wordpress/android/ui/reader/discover/ReaderNavigationEvents$OpenEditorForReblog;", "Lorg/wordpress/android/ui/reader/discover/ReaderNavigationEvents$ShowBookmarkedTab;", "Lorg/wordpress/android/ui/reader/discover/ReaderNavigationEvents$ShowBookmarkedSavedOnlyLocallyDialog;", "Lorg/wordpress/android/ui/reader/discover/ReaderNavigationEvents$ShowVideoViewer;", "Lorg/wordpress/android/ui/reader/discover/ReaderNavigationEvents$ShowBlogPreview;", "Lorg/wordpress/android/ui/reader/discover/ReaderNavigationEvents$ShowReportPost;", "Lorg/wordpress/android/ui/reader/discover/ReaderNavigationEvents$ShowReaderSubs;", "Lorg/wordpress/android/ui/reader/discover/ReaderNavigationEvents$ShowRelatedPostDetails;", "Lorg/wordpress/android/ui/reader/discover/ReaderNavigationEvents$ReplaceRelatedPostDetailsWithHistory;", "Lorg/wordpress/android/ui/reader/discover/ReaderNavigationEvents$ShowMediaPreview;", "Lorg/wordpress/android/ui/reader/discover/ReaderNavigationEvents$OpenUrl;", "Lorg/wordpress/android/ui/reader/discover/ReaderNavigationEvents$ShowPostInWebView;", "Lorg/wordpress/android/ui/reader/discover/ReaderNavigationEvents$ShowEngagedPeopleList;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class ReaderNavigationEvents {
    
    private ReaderNavigationEvents() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/reader/discover/ReaderNavigationEvents$ShowPostDetail;", "Lorg/wordpress/android/ui/reader/discover/ReaderNavigationEvents;", "post", "Lorg/wordpress/android/models/ReaderPost;", "(Lorg/wordpress/android/models/ReaderPost;)V", "getPost", "()Lorg/wordpress/android/models/ReaderPost;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class ShowPostDetail extends org.wordpress.android.ui.reader.discover.ReaderNavigationEvents {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.models.ReaderPost post = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.reader.discover.ReaderNavigationEvents.ShowPostDetail copy(@org.jetbrains.annotations.NotNull()
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
        
        public ShowPostDetail(@org.jetbrains.annotations.NotNull()
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
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/reader/discover/ReaderNavigationEvents$SharePost;", "Lorg/wordpress/android/ui/reader/discover/ReaderNavigationEvents;", "post", "Lorg/wordpress/android/models/ReaderPost;", "(Lorg/wordpress/android/models/ReaderPost;)V", "getPost", "()Lorg/wordpress/android/models/ReaderPost;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class SharePost extends org.wordpress.android.ui.reader.discover.ReaderNavigationEvents {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.models.ReaderPost post = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.reader.discover.ReaderNavigationEvents.SharePost copy(@org.jetbrains.annotations.NotNull()
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
        
        public SharePost(@org.jetbrains.annotations.NotNull()
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
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/reader/discover/ReaderNavigationEvents$OpenPost;", "Lorg/wordpress/android/ui/reader/discover/ReaderNavigationEvents;", "post", "Lorg/wordpress/android/models/ReaderPost;", "(Lorg/wordpress/android/models/ReaderPost;)V", "getPost", "()Lorg/wordpress/android/models/ReaderPost;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class OpenPost extends org.wordpress.android.ui.reader.discover.ReaderNavigationEvents {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.models.ReaderPost post = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.reader.discover.ReaderNavigationEvents.OpenPost copy(@org.jetbrains.annotations.NotNull()
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
        
        public OpenPost(@org.jetbrains.annotations.NotNull()
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
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/reader/discover/ReaderNavigationEvents$ShowPostsByTag;", "Lorg/wordpress/android/ui/reader/discover/ReaderNavigationEvents;", "tag", "Lorg/wordpress/android/models/ReaderTag;", "(Lorg/wordpress/android/models/ReaderTag;)V", "getTag", "()Lorg/wordpress/android/models/ReaderTag;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class ShowPostsByTag extends org.wordpress.android.ui.reader.discover.ReaderNavigationEvents {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.models.ReaderTag tag = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.reader.discover.ReaderNavigationEvents.ShowPostsByTag copy(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.models.ReaderTag tag) {
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
        
        public ShowPostsByTag(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.models.ReaderTag tag) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.models.ReaderTag component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.models.ReaderTag getTag() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000f\u001a\u00020\u0006H\u00c6\u0003J\'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u00c6\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u00d6\u0003J\t\u0010\u0015\u001a\u00020\u0016H\u00d6\u0001J\t\u0010\u0017\u001a\u00020\u0018H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f\u00a8\u0006\u0019"}, d2 = {"Lorg/wordpress/android/ui/reader/discover/ReaderNavigationEvents$ShowReaderComments;", "Lorg/wordpress/android/ui/reader/discover/ReaderNavigationEvents;", "blogId", "", "postId", "source", "Lorg/wordpress/android/ui/reader/comments/ThreadedCommentsActionSource;", "(JJLorg/wordpress/android/ui/reader/comments/ThreadedCommentsActionSource;)V", "getBlogId", "()J", "getPostId", "getSource", "()Lorg/wordpress/android/ui/reader/comments/ThreadedCommentsActionSource;", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class ShowReaderComments extends org.wordpress.android.ui.reader.discover.ReaderNavigationEvents {
        private final long blogId = 0L;
        private final long postId = 0L;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.reader.comments.ThreadedCommentsActionSource source = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.reader.discover.ReaderNavigationEvents.ShowReaderComments copy(long blogId, long postId, @org.jetbrains.annotations.NotNull()
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
        
        public ShowReaderComments(long blogId, long postId, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.reader.comments.ThreadedCommentsActionSource source) {
            super();
        }
        
        public final long component1() {
            return 0L;
        }
        
        public final long getBlogId() {
            return 0L;
        }
        
        public final long component2() {
            return 0L;
        }
        
        public final long getPostId() {
            return 0L;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.reader.comments.ThreadedCommentsActionSource component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.reader.comments.ThreadedCommentsActionSource getSource() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/reader/discover/ReaderNavigationEvents$ShowNoSitesToReblog;", "Lorg/wordpress/android/ui/reader/discover/ReaderNavigationEvents;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class ShowNoSitesToReblog extends org.wordpress.android.ui.reader.discover.ReaderNavigationEvents {
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.ui.reader.discover.ReaderNavigationEvents.ShowNoSitesToReblog INSTANCE = null;
        
        private ShowNoSitesToReblog() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0010\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0011\u001a\u00020\u0007H\u00c6\u0003J\'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u00c6\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u00d6\u0003J\t\u0010\u0017\u001a\u00020\u0018H\u00d6\u0001J\t\u0010\u0019\u001a\u00020\u001aH\u00d6\u0001R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e\u00a8\u0006\u001b"}, d2 = {"Lorg/wordpress/android/ui/reader/discover/ReaderNavigationEvents$ShowSitePickerForResult;", "Lorg/wordpress/android/ui/reader/discover/ReaderNavigationEvents;", "preselectedSite", "Lorg/wordpress/android/fluxc/model/SiteModel;", "post", "Lorg/wordpress/android/models/ReaderPost;", "mode", "Lorg/wordpress/android/ui/main/SitePickerAdapter$SitePickerMode;", "(Lorg/wordpress/android/fluxc/model/SiteModel;Lorg/wordpress/android/models/ReaderPost;Lorg/wordpress/android/ui/main/SitePickerAdapter$SitePickerMode;)V", "getMode", "()Lorg/wordpress/android/ui/main/SitePickerAdapter$SitePickerMode;", "getPost", "()Lorg/wordpress/android/models/ReaderPost;", "getPreselectedSite", "()Lorg/wordpress/android/fluxc/model/SiteModel;", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class ShowSitePickerForResult extends org.wordpress.android.ui.reader.discover.ReaderNavigationEvents {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.fluxc.model.SiteModel preselectedSite = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.models.ReaderPost post = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.main.SitePickerAdapter.SitePickerMode mode = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.reader.discover.ReaderNavigationEvents.ShowSitePickerForResult copy(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.SiteModel preselectedSite, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.models.ReaderPost post, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.main.SitePickerAdapter.SitePickerMode mode) {
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
        
        public ShowSitePickerForResult(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.SiteModel preselectedSite, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.models.ReaderPost post, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.main.SitePickerAdapter.SitePickerMode mode) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.fluxc.model.SiteModel component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.fluxc.model.SiteModel getPreselectedSite() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.models.ReaderPost component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.models.ReaderPost getPost() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.main.SitePickerAdapter.SitePickerMode component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.main.SitePickerAdapter.SitePickerMode getMode() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0010\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0011\u001a\u00020\u0007H\u00c6\u0003J\'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u00c6\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u00d6\u0003J\t\u0010\u0017\u001a\u00020\u0018H\u00d6\u0001J\t\u0010\u0019\u001a\u00020\u001aH\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e\u00a8\u0006\u001b"}, d2 = {"Lorg/wordpress/android/ui/reader/discover/ReaderNavigationEvents$OpenEditorForReblog;", "Lorg/wordpress/android/ui/reader/discover/ReaderNavigationEvents;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "post", "Lorg/wordpress/android/models/ReaderPost;", "source", "Lorg/wordpress/android/ui/PagePostCreationSourcesDetail;", "(Lorg/wordpress/android/fluxc/model/SiteModel;Lorg/wordpress/android/models/ReaderPost;Lorg/wordpress/android/ui/PagePostCreationSourcesDetail;)V", "getPost", "()Lorg/wordpress/android/models/ReaderPost;", "getSite", "()Lorg/wordpress/android/fluxc/model/SiteModel;", "getSource", "()Lorg/wordpress/android/ui/PagePostCreationSourcesDetail;", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class OpenEditorForReblog extends org.wordpress.android.ui.reader.discover.ReaderNavigationEvents {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.fluxc.model.SiteModel site = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.models.ReaderPost post = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.PagePostCreationSourcesDetail source = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.reader.discover.ReaderNavigationEvents.OpenEditorForReblog copy(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.models.ReaderPost post, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.PagePostCreationSourcesDetail source) {
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
        
        public OpenEditorForReblog(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.models.ReaderPost post, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.PagePostCreationSourcesDetail source) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.fluxc.model.SiteModel component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.fluxc.model.SiteModel getSite() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.models.ReaderPost component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.models.ReaderPost getPost() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.PagePostCreationSourcesDetail component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.PagePostCreationSourcesDetail getSource() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/reader/discover/ReaderNavigationEvents$ShowBookmarkedTab;", "Lorg/wordpress/android/ui/reader/discover/ReaderNavigationEvents;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class ShowBookmarkedTab extends org.wordpress.android.ui.reader.discover.ReaderNavigationEvents {
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.ui.reader.discover.ReaderNavigationEvents.ShowBookmarkedTab INSTANCE = null;
        
        private ShowBookmarkedTab() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\u0005R\u0016\u0010\u0006\u001a\u00020\u00078\u0006X\u0087D\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0016\u0010\n\u001a\u00020\u00078\u0006X\u0087D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0016\u0010\u000e\u001a\u00020\u00078\u0006X\u0087D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\t\u00a8\u0006\u0010"}, d2 = {"Lorg/wordpress/android/ui/reader/discover/ReaderNavigationEvents$ShowBookmarkedSavedOnlyLocallyDialog;", "Lorg/wordpress/android/ui/reader/discover/ReaderNavigationEvents;", "okButtonAction", "Lkotlin/Function0;", "", "(Lkotlin/jvm/functions/Function0;)V", "buttonLabel", "", "getButtonLabel", "()I", "message", "getMessage", "getOkButtonAction", "()Lkotlin/jvm/functions/Function0;", "title", "getTitle", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class ShowBookmarkedSavedOnlyLocallyDialog extends org.wordpress.android.ui.reader.discover.ReaderNavigationEvents {
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function0<kotlin.Unit> okButtonAction = null;
        @androidx.annotation.StringRes()
        private final int title = org.wordpress.android.R.string.reader_save_posts_locally_dialog_title;
        @androidx.annotation.StringRes()
        private final int message = org.wordpress.android.R.string.reader_save_posts_locally_dialog_message;
        @androidx.annotation.StringRes()
        private final int buttonLabel = org.wordpress.android.R.string.dialog_button_ok;
        
        public ShowBookmarkedSavedOnlyLocallyDialog(@org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function0<kotlin.Unit> okButtonAction) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function0<kotlin.Unit> getOkButtonAction() {
            return null;
        }
        
        public final int getTitle() {
            return 0;
        }
        
        public final int getMessage() {
            return 0;
        }
        
        public final int getButtonLabel() {
            return 0;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lorg/wordpress/android/ui/reader/discover/ReaderNavigationEvents$ShowVideoViewer;", "Lorg/wordpress/android/ui/reader/discover/ReaderNavigationEvents;", "videoUrl", "", "(Ljava/lang/String;)V", "getVideoUrl", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class ShowVideoViewer extends org.wordpress.android.ui.reader.discover.ReaderNavigationEvents {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String videoUrl = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.reader.discover.ReaderNavigationEvents.ShowVideoViewer copy(@org.jetbrains.annotations.NotNull()
        java.lang.String videoUrl) {
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
        
        public ShowVideoViewer(@org.jetbrains.annotations.NotNull()
        java.lang.String videoUrl) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getVideoUrl() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\t\u0010\f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\r\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000e\u001a\u00020\u0006H\u00c6\u0003J\'\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u00c6\u0001J\u0013\u0010\u0010\u001a\u00020\u00062\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u00d6\u0003J\t\u0010\u0013\u001a\u00020\u0014H\u00d6\u0001J\t\u0010\u0015\u001a\u00020\u0016H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t\u00a8\u0006\u0017"}, d2 = {"Lorg/wordpress/android/ui/reader/discover/ReaderNavigationEvents$ShowBlogPreview;", "Lorg/wordpress/android/ui/reader/discover/ReaderNavigationEvents;", "siteId", "", "feedId", "isFollowed", "", "(JJZ)V", "getFeedId", "()J", "()Z", "getSiteId", "component1", "component2", "component3", "copy", "equals", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class ShowBlogPreview extends org.wordpress.android.ui.reader.discover.ReaderNavigationEvents {
        private final long siteId = 0L;
        private final long feedId = 0L;
        private final boolean isFollowed = false;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.reader.discover.ReaderNavigationEvents.ShowBlogPreview copy(long siteId, long feedId, boolean isFollowed) {
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
        
        public ShowBlogPreview(long siteId, long feedId, boolean isFollowed) {
            super();
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
        
        public final long getFeedId() {
            return 0L;
        }
        
        public final boolean component3() {
            return false;
        }
        
        public final boolean isFollowed() {
            return false;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lorg/wordpress/android/ui/reader/discover/ReaderNavigationEvents$ShowReportPost;", "Lorg/wordpress/android/ui/reader/discover/ReaderNavigationEvents;", "url", "", "(Ljava/lang/String;)V", "getUrl", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class ShowReportPost extends org.wordpress.android.ui.reader.discover.ReaderNavigationEvents {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String url = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.reader.discover.ReaderNavigationEvents.ShowReportPost copy(@org.jetbrains.annotations.NotNull()
        java.lang.String url) {
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
        
        public ShowReportPost(@org.jetbrains.annotations.NotNull()
        java.lang.String url) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getUrl() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/reader/discover/ReaderNavigationEvents$ShowReaderSubs;", "Lorg/wordpress/android/ui/reader/discover/ReaderNavigationEvents;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class ShowReaderSubs extends org.wordpress.android.ui.reader.discover.ReaderNavigationEvents {
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.ui.reader.discover.ReaderNavigationEvents.ShowReaderSubs INSTANCE = null;
        
        private ShowReaderSubs() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\n\u001a\u00020\u0003H\u00c6\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u00d6\u0003J\t\u0010\u0010\u001a\u00020\u0011H\u00d6\u0001J\t\u0010\u0012\u001a\u00020\u0013H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007\u00a8\u0006\u0014"}, d2 = {"Lorg/wordpress/android/ui/reader/discover/ReaderNavigationEvents$ShowRelatedPostDetails;", "Lorg/wordpress/android/ui/reader/discover/ReaderNavigationEvents;", "postId", "", "blogId", "(JJ)V", "getBlogId", "()J", "getPostId", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class ShowRelatedPostDetails extends org.wordpress.android.ui.reader.discover.ReaderNavigationEvents {
        private final long postId = 0L;
        private final long blogId = 0L;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.reader.discover.ReaderNavigationEvents.ShowRelatedPostDetails copy(long postId, long blogId) {
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
        
        public ShowRelatedPostDetails(long postId, long blogId) {
            super();
        }
        
        public final long component1() {
            return 0L;
        }
        
        public final long getPostId() {
            return 0L;
        }
        
        public final long component2() {
            return 0L;
        }
        
        public final long getBlogId() {
            return 0L;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\t\u0010\f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\r\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000e\u001a\u00020\u0006H\u00c6\u0003J\'\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u00c6\u0001J\u0013\u0010\u0010\u001a\u00020\u00062\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u00d6\u0003J\t\u0010\u0013\u001a\u00020\u0014H\u00d6\u0001J\t\u0010\u0015\u001a\u00020\u0016H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t\u00a8\u0006\u0017"}, d2 = {"Lorg/wordpress/android/ui/reader/discover/ReaderNavigationEvents$ReplaceRelatedPostDetailsWithHistory;", "Lorg/wordpress/android/ui/reader/discover/ReaderNavigationEvents;", "postId", "", "blogId", "isGlobal", "", "(JJZ)V", "getBlogId", "()J", "()Z", "getPostId", "component1", "component2", "component3", "copy", "equals", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class ReplaceRelatedPostDetailsWithHistory extends org.wordpress.android.ui.reader.discover.ReaderNavigationEvents {
        private final long postId = 0L;
        private final long blogId = 0L;
        private final boolean isGlobal = false;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.reader.discover.ReaderNavigationEvents.ReplaceRelatedPostDetailsWithHistory copy(long postId, long blogId, boolean isGlobal) {
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
        
        public ReplaceRelatedPostDetailsWithHistory(long postId, long blogId, boolean isGlobal) {
            super();
        }
        
        public final long component1() {
            return 0L;
        }
        
        public final long getPostId() {
            return 0L;
        }
        
        public final long component2() {
            return 0L;
        }
        
        public final long getBlogId() {
            return 0L;
        }
        
        public final boolean component3() {
            return false;
        }
        
        public final boolean isGlobal() {
            return false;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010\f\u001a\u00020\u0005H\u00c6\u0003J\u001f\u0010\r\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u00d6\u0003J\t\u0010\u0012\u001a\u00020\u0013H\u00d6\u0001J\t\u0010\u0014\u001a\u00020\u0005H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0015"}, d2 = {"Lorg/wordpress/android/ui/reader/discover/ReaderNavigationEvents$ShowMediaPreview;", "Lorg/wordpress/android/ui/reader/discover/ReaderNavigationEvents;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "featuredImage", "", "(Lorg/wordpress/android/fluxc/model/SiteModel;Ljava/lang/String;)V", "getFeaturedImage", "()Ljava/lang/String;", "getSite", "()Lorg/wordpress/android/fluxc/model/SiteModel;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class ShowMediaPreview extends org.wordpress.android.ui.reader.discover.ReaderNavigationEvents {
        @org.jetbrains.annotations.Nullable()
        private final org.wordpress.android.fluxc.model.SiteModel site = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String featuredImage = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.reader.discover.ReaderNavigationEvents.ShowMediaPreview copy(@org.jetbrains.annotations.Nullable()
        org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
        java.lang.String featuredImage) {
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
        
        public ShowMediaPreview(@org.jetbrains.annotations.Nullable()
        org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
        java.lang.String featuredImage) {
            super();
        }
        
        @org.jetbrains.annotations.Nullable()
        public final org.wordpress.android.fluxc.model.SiteModel component1() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final org.wordpress.android.fluxc.model.SiteModel getSite() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getFeaturedImage() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lorg/wordpress/android/ui/reader/discover/ReaderNavigationEvents$OpenUrl;", "Lorg/wordpress/android/ui/reader/discover/ReaderNavigationEvents;", "url", "", "(Ljava/lang/String;)V", "getUrl", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class OpenUrl extends org.wordpress.android.ui.reader.discover.ReaderNavigationEvents {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String url = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.reader.discover.ReaderNavigationEvents.OpenUrl copy(@org.jetbrains.annotations.NotNull()
        java.lang.String url) {
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
        
        public OpenUrl(@org.jetbrains.annotations.NotNull()
        java.lang.String url) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getUrl() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/reader/discover/ReaderNavigationEvents$ShowPostInWebView;", "Lorg/wordpress/android/ui/reader/discover/ReaderNavigationEvents;", "post", "Lorg/wordpress/android/models/ReaderPost;", "(Lorg/wordpress/android/models/ReaderPost;)V", "getPost", "()Lorg/wordpress/android/models/ReaderPost;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class ShowPostInWebView extends org.wordpress.android.ui.reader.discover.ReaderNavigationEvents {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.models.ReaderPost post = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.reader.discover.ReaderNavigationEvents.ShowPostInWebView copy(@org.jetbrains.annotations.NotNull()
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
        
        public ShowPostInWebView(@org.jetbrains.annotations.NotNull()
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
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000f\u001a\u00020\u0006H\u00c6\u0003J\'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u00c6\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u00d6\u0003J\t\u0010\u0015\u001a\u00020\u0016H\u00d6\u0001J\t\u0010\u0017\u001a\u00020\u0018H\u00d6\u0001R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b\u00a8\u0006\u0019"}, d2 = {"Lorg/wordpress/android/ui/reader/discover/ReaderNavigationEvents$ShowEngagedPeopleList;", "Lorg/wordpress/android/ui/reader/discover/ReaderNavigationEvents;", "siteId", "", "postId", "headerData", "Lorg/wordpress/android/ui/engagement/HeaderData;", "(JJLorg/wordpress/android/ui/engagement/HeaderData;)V", "getHeaderData", "()Lorg/wordpress/android/ui/engagement/HeaderData;", "getPostId", "()J", "getSiteId", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class ShowEngagedPeopleList extends org.wordpress.android.ui.reader.discover.ReaderNavigationEvents {
        private final long siteId = 0L;
        private final long postId = 0L;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.engagement.HeaderData headerData = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.reader.discover.ReaderNavigationEvents.ShowEngagedPeopleList copy(long siteId, long postId, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.engagement.HeaderData headerData) {
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
        
        public ShowEngagedPeopleList(long siteId, long postId, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.engagement.HeaderData headerData) {
            super();
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
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.engagement.HeaderData component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.engagement.HeaderData getHeaderData() {
            return null;
        }
    }
}