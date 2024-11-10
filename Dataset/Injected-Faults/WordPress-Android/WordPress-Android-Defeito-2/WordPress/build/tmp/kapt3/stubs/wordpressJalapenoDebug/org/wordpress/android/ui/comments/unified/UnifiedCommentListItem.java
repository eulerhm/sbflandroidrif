package org.wordpress.android.ui.comments.unified;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0006\u000b\f\r\u000e\u000f\u0010B\u000f\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0012\u0010\u0005\u001a\u00020\u0006X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u0082\u0001\u0003\u0011\u0012\u0013\u00a8\u0006\u0014"}, d2 = {"Lorg/wordpress/android/ui/comments/unified/UnifiedCommentListItem;", "", "type", "Lorg/wordpress/android/ui/comments/unified/UnifiedCommentListItem$CommentListItemType;", "(Lorg/wordpress/android/ui/comments/unified/UnifiedCommentListItem$CommentListItemType;)V", "id", "", "getId", "()J", "getType", "()Lorg/wordpress/android/ui/comments/unified/UnifiedCommentListItem$CommentListItemType;", "ClickAction", "Comment", "CommentListItemType", "NextPageLoader", "SubHeader", "ToggleAction", "Lorg/wordpress/android/ui/comments/unified/UnifiedCommentListItem$SubHeader;", "Lorg/wordpress/android/ui/comments/unified/UnifiedCommentListItem$Comment;", "Lorg/wordpress/android/ui/comments/unified/UnifiedCommentListItem$NextPageLoader;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class UnifiedCommentListItem {
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.ui.comments.unified.UnifiedCommentListItem.CommentListItemType type = null;
    
    private UnifiedCommentListItem(org.wordpress.android.ui.comments.unified.UnifiedCommentListItem.CommentListItemType type) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.comments.unified.UnifiedCommentListItem.CommentListItemType getType() {
        return null;
    }
    
    public abstract long getId();
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\f\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u00d6\u0003J\t\u0010\u0012\u001a\u00020\u0013H\u00d6\u0001J\t\u0010\u0014\u001a\u00020\u0003H\u00d6\u0001R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0015"}, d2 = {"Lorg/wordpress/android/ui/comments/unified/UnifiedCommentListItem$SubHeader;", "Lorg/wordpress/android/ui/comments/unified/UnifiedCommentListItem;", "label", "", "id", "", "(Ljava/lang/String;J)V", "getId", "()J", "getLabel", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class SubHeader extends org.wordpress.android.ui.comments.unified.UnifiedCommentListItem {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String label = null;
        private final long id = 0L;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.comments.unified.UnifiedCommentListItem.SubHeader copy(@org.jetbrains.annotations.NotNull()
        java.lang.String label, long id) {
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
        
        public SubHeader(@org.jetbrains.annotations.NotNull()
        java.lang.String label, long id) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getLabel() {
            return null;
        }
        
        public final long component2() {
            return 0L;
        }
        
        @java.lang.Override()
        public long getId() {
            return 0L;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b!\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001Be\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u0005\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u00a2\u0006\u0002\u0010\u0013J\t\u0010%\u001a\u00020\u0003H\u00c6\u0003J\t\u0010&\u001a\u00020\rH\u00c6\u0003J\t\u0010\'\u001a\u00020\u0010H\u00c6\u0003J\t\u0010(\u001a\u00020\u0012H\u00c6\u0003J\t\u0010)\u001a\u00020\u0005H\u00c6\u0003J\t\u0010*\u001a\u00020\u0005H\u00c6\u0003J\t\u0010+\u001a\u00020\u0005H\u00c6\u0003J\t\u0010,\u001a\u00020\u0005H\u00c6\u0003J\t\u0010-\u001a\u00020\u0005H\u00c6\u0003J\t\u0010.\u001a\u00020\u0005H\u00c6\u0003J\t\u0010/\u001a\u00020\u0003H\u00c6\u0003J\t\u00100\u001a\u00020\rH\u00c6\u0003J\u0081\u0001\u00101\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u0012H\u00c6\u0001J\u0013\u00102\u001a\u00020\r2\b\u00103\u001a\u0004\u0018\u000104H\u00d6\u0003J\t\u00105\u001a\u000206H\u00d6\u0001J\t\u00107\u001a\u00020\u0005H\u00d6\u0001R\u0011\u0010\b\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0007\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0015R\u0011\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0015R\u0011\u0010\u0011\u001a\u00020\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\t\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0015R\u0014\u0010\u001b\u001a\u00020\u00038VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\f\u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u001eR\u0011\u0010\u000e\u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u001eR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0015R\u0011\u0010\n\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u0015R\u0011\u0010\u000b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001dR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001dR\u0011\u0010\u000f\u001a\u00020\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010$\u00a8\u00068"}, d2 = {"Lorg/wordpress/android/ui/comments/unified/UnifiedCommentListItem$Comment;", "Lorg/wordpress/android/ui/comments/unified/UnifiedCommentListItem;", "remoteCommentId", "", "postTitle", "", "authorName", "authorEmail", "authorAvatarUrl", "content", "publishedDate", "publishedTimestamp", "isPending", "", "isSelected", "toggleAction", "Lorg/wordpress/android/ui/comments/unified/UnifiedCommentListItem$ToggleAction;", "clickAction", "Lorg/wordpress/android/ui/comments/unified/UnifiedCommentListItem$ClickAction;", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JZZLorg/wordpress/android/ui/comments/unified/UnifiedCommentListItem$ToggleAction;Lorg/wordpress/android/ui/comments/unified/UnifiedCommentListItem$ClickAction;)V", "getAuthorAvatarUrl", "()Ljava/lang/String;", "getAuthorEmail", "getAuthorName", "getClickAction", "()Lorg/wordpress/android/ui/comments/unified/UnifiedCommentListItem$ClickAction;", "getContent", "id", "getId", "()J", "()Z", "getPostTitle", "getPublishedDate", "getPublishedTimestamp", "getRemoteCommentId", "getToggleAction", "()Lorg/wordpress/android/ui/comments/unified/UnifiedCommentListItem$ToggleAction;", "component1", "component10", "component11", "component12", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Comment extends org.wordpress.android.ui.comments.unified.UnifiedCommentListItem {
        private final long remoteCommentId = 0L;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String postTitle = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String authorName = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String authorEmail = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String authorAvatarUrl = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String content = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String publishedDate = null;
        private final long publishedTimestamp = 0L;
        private final boolean isPending = false;
        private final boolean isSelected = false;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.comments.unified.UnifiedCommentListItem.ToggleAction toggleAction = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.comments.unified.UnifiedCommentListItem.ClickAction clickAction = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.comments.unified.UnifiedCommentListItem.Comment copy(long remoteCommentId, @org.jetbrains.annotations.NotNull()
        java.lang.String postTitle, @org.jetbrains.annotations.NotNull()
        java.lang.String authorName, @org.jetbrains.annotations.NotNull()
        java.lang.String authorEmail, @org.jetbrains.annotations.NotNull()
        java.lang.String authorAvatarUrl, @org.jetbrains.annotations.NotNull()
        java.lang.String content, @org.jetbrains.annotations.NotNull()
        java.lang.String publishedDate, long publishedTimestamp, boolean isPending, boolean isSelected, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.comments.unified.UnifiedCommentListItem.ToggleAction toggleAction, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.comments.unified.UnifiedCommentListItem.ClickAction clickAction) {
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
        
        public Comment(long remoteCommentId, @org.jetbrains.annotations.NotNull()
        java.lang.String postTitle, @org.jetbrains.annotations.NotNull()
        java.lang.String authorName, @org.jetbrains.annotations.NotNull()
        java.lang.String authorEmail, @org.jetbrains.annotations.NotNull()
        java.lang.String authorAvatarUrl, @org.jetbrains.annotations.NotNull()
        java.lang.String content, @org.jetbrains.annotations.NotNull()
        java.lang.String publishedDate, long publishedTimestamp, boolean isPending, boolean isSelected, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.comments.unified.UnifiedCommentListItem.ToggleAction toggleAction, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.comments.unified.UnifiedCommentListItem.ClickAction clickAction) {
            super(null);
        }
        
        public final long component1() {
            return 0L;
        }
        
        public final long getRemoteCommentId() {
            return 0L;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getPostTitle() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getAuthorName() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component4() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getAuthorEmail() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component5() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getAuthorAvatarUrl() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component6() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getContent() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component7() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getPublishedDate() {
            return null;
        }
        
        public final long component8() {
            return 0L;
        }
        
        public final long getPublishedTimestamp() {
            return 0L;
        }
        
        public final boolean component9() {
            return false;
        }
        
        public final boolean isPending() {
            return false;
        }
        
        public final boolean component10() {
            return false;
        }
        
        public final boolean isSelected() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.comments.unified.UnifiedCommentListItem.ToggleAction component11() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.comments.unified.UnifiedCommentListItem.ToggleAction getToggleAction() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.comments.unified.UnifiedCommentListItem.ClickAction component12() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.comments.unified.UnifiedCommentListItem.ClickAction getClickAction() {
            return null;
        }
        
        @java.lang.Override()
        public long getId() {
            return 0L;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\f\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u00a2\u0006\u0002\u0010\tJ\t\u0010\u000f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0010\u001a\u00020\u0005H\u00c6\u0003J\u000f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u00c6\u0003J-\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u00c6\u0001J\u0013\u0010\u0013\u001a\u00020\u00032\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u00d6\u0003J\t\u0010\u0016\u001a\u00020\u0017H\u00d6\u0001J\t\u0010\u0018\u001a\u00020\u0019H\u00d6\u0001R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\fR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e\u00a8\u0006\u001a"}, d2 = {"Lorg/wordpress/android/ui/comments/unified/UnifiedCommentListItem$NextPageLoader;", "Lorg/wordpress/android/ui/comments/unified/UnifiedCommentListItem;", "isLoading", "", "id", "", "loadAction", "Lkotlin/Function0;", "", "(ZJLkotlin/jvm/functions/Function0;)V", "getId", "()J", "()Z", "getLoadAction", "()Lkotlin/jvm/functions/Function0;", "component1", "component2", "component3", "copy", "equals", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class NextPageLoader extends org.wordpress.android.ui.comments.unified.UnifiedCommentListItem {
        private final boolean isLoading = false;
        private final long id = 0L;
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function0<kotlin.Unit> loadAction = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.comments.unified.UnifiedCommentListItem.NextPageLoader copy(boolean isLoading, long id, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function0<kotlin.Unit> loadAction) {
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
        
        public NextPageLoader(boolean isLoading, long id, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function0<kotlin.Unit> loadAction) {
            super(null);
        }
        
        public final boolean component1() {
            return false;
        }
        
        public final boolean isLoading() {
            return false;
        }
        
        public final long component2() {
            return 0L;
        }
        
        @java.lang.Override()
        public long getId() {
            return 0L;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function0<kotlin.Unit> component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function0<kotlin.Unit> getLoadAction() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005\u00a8\u0006\u0006"}, d2 = {"Lorg/wordpress/android/ui/comments/unified/UnifiedCommentListItem$CommentListItemType;", "", "(Ljava/lang/String;I)V", "SUB_HEADER", "COMMENT", "NEXT_PAGE_LOADER", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum CommentListItemType {
        /*public static final*/ SUB_HEADER /* = new SUB_HEADER() */,
        /*public static final*/ COMMENT /* = new COMMENT() */,
        /*public static final*/ NEXT_PAGE_LOADER /* = new NEXT_PAGE_LOADER() */;
        
        CommentListItemType() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BE\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00126\u0010\u0004\u001a2\u0012\u0013\u0012\u00110\u0006\u00a2\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\n\u00a2\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f0\u0005\u00a2\u0006\u0002\u0010\rJ\t\u0010\u0010\u001a\u00020\u0003H\u00c6\u0003J9\u0010\u0011\u001a2\u0012\u0013\u0012\u00110\u0006\u00a2\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\n\u00a2\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f0\u0005H\u00c2\u0003JM\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u000328\b\u0002\u0010\u0004\u001a2\u0012\u0013\u0012\u00110\u0006\u00a2\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\n\u00a2\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f0\u0005H\u00c6\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0016\u001a\u00020\u0017H\u00d6\u0001J\u0006\u0010\u0018\u001a\u00020\fJ\t\u0010\u0019\u001a\u00020\u001aH\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR>\u0010\u0004\u001a2\u0012\u0013\u0012\u00110\u0006\u00a2\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\n\u00a2\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f0\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001b"}, d2 = {"Lorg/wordpress/android/ui/comments/unified/UnifiedCommentListItem$ToggleAction;", "", "comment", "Lorg/wordpress/android/fluxc/persistence/comments/CommentsDao$CommentEntity;", "toggleSelected", "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", "name", "remoteCommentId", "Lorg/wordpress/android/fluxc/model/CommentStatus;", "commentStatus", "", "(Lorg/wordpress/android/fluxc/persistence/comments/CommentsDao$CommentEntity;Lkotlin/jvm/functions/Function2;)V", "getComment", "()Lorg/wordpress/android/fluxc/persistence/comments/CommentsDao$CommentEntity;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "onToggle", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class ToggleAction {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.fluxc.persistence.comments.CommentsDao.CommentEntity comment = null;
        private final kotlin.jvm.functions.Function2<java.lang.Long, org.wordpress.android.fluxc.model.CommentStatus, kotlin.Unit> toggleSelected = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.comments.unified.UnifiedCommentListItem.ToggleAction copy(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.persistence.comments.CommentsDao.CommentEntity comment, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function2<? super java.lang.Long, ? super org.wordpress.android.fluxc.model.CommentStatus, kotlin.Unit> toggleSelected) {
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
        
        public ToggleAction(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.persistence.comments.CommentsDao.CommentEntity comment, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function2<? super java.lang.Long, ? super org.wordpress.android.fluxc.model.CommentStatus, kotlin.Unit> toggleSelected) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.fluxc.persistence.comments.CommentsDao.CommentEntity component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.fluxc.persistence.comments.CommentsDao.CommentEntity getComment() {
            return null;
        }
        
        private final kotlin.jvm.functions.Function2<java.lang.Long, org.wordpress.android.fluxc.model.CommentStatus, kotlin.Unit> component2() {
            return null;
        }
        
        public final void onToggle() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B0\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012!\u0010\u0004\u001a\u001d\u0012\u0013\u0012\u00110\u0003\u00a2\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u0002\u0012\u0004\u0012\u00020\b0\u0005\u00a2\u0006\u0002\u0010\tJ\t\u0010\f\u001a\u00020\u0003H\u00c6\u0003J$\u0010\r\u001a\u001d\u0012\u0013\u0012\u00110\u0003\u00a2\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u0002\u0012\u0004\u0012\u00020\b0\u0005H\u00c2\u0003J8\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032#\b\u0002\u0010\u0004\u001a\u001d\u0012\u0013\u0012\u00110\u0003\u00a2\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u0002\u0012\u0004\u0012\u00020\b0\u0005H\u00c6\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0012\u001a\u00020\u0013H\u00d6\u0001J\u0006\u0010\u0014\u001a\u00020\bJ\t\u0010\u0015\u001a\u00020\u0016H\u00d6\u0001R)\u0010\u0004\u001a\u001d\u0012\u0013\u0012\u00110\u0003\u00a2\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u0002\u0012\u0004\u0012\u00020\b0\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0017"}, d2 = {"Lorg/wordpress/android/ui/comments/unified/UnifiedCommentListItem$ClickAction;", "", "comment", "Lorg/wordpress/android/fluxc/persistence/comments/CommentsDao$CommentEntity;", "clickItem", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "", "(Lorg/wordpress/android/fluxc/persistence/comments/CommentsDao$CommentEntity;Lkotlin/jvm/functions/Function1;)V", "getComment", "()Lorg/wordpress/android/fluxc/persistence/comments/CommentsDao$CommentEntity;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "onClick", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class ClickAction {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.fluxc.persistence.comments.CommentsDao.CommentEntity comment = null;
        private final kotlin.jvm.functions.Function1<org.wordpress.android.fluxc.persistence.comments.CommentsDao.CommentEntity, kotlin.Unit> clickItem = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.comments.unified.UnifiedCommentListItem.ClickAction copy(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.persistence.comments.CommentsDao.CommentEntity comment, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super org.wordpress.android.fluxc.persistence.comments.CommentsDao.CommentEntity, kotlin.Unit> clickItem) {
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
        
        public ClickAction(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.persistence.comments.CommentsDao.CommentEntity comment, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super org.wordpress.android.fluxc.persistence.comments.CommentsDao.CommentEntity, kotlin.Unit> clickItem) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.fluxc.persistence.comments.CommentsDao.CommentEntity component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.fluxc.persistence.comments.CommentsDao.CommentEntity getComment() {
            return null;
        }
        
        private final kotlin.jvm.functions.Function1<org.wordpress.android.fluxc.persistence.comments.CommentsDao.CommentEntity, kotlin.Unit> component2() {
            return null;
        }
        
        public final void onClick() {
        }
    }
}