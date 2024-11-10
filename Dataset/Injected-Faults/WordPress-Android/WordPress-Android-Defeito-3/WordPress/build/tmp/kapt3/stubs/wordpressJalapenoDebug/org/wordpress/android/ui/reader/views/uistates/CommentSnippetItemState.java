package org.wordpress.android.ui.reader.views.uistates;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u0007\b\t\nB\u000f\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0004\u000b\f\r\u000e\u00a8\u0006\u000f"}, d2 = {"Lorg/wordpress/android/ui/reader/views/uistates/CommentSnippetItemState;", "", "type", "Lorg/wordpress/android/ui/reader/views/uistates/CommentItemType;", "(Lorg/wordpress/android/ui/reader/views/uistates/CommentItemType;)V", "getType", "()Lorg/wordpress/android/ui/reader/views/uistates/CommentItemType;", "ButtonState", "CommentState", "LoadingState", "TextMessage", "Lorg/wordpress/android/ui/reader/views/uistates/CommentSnippetItemState$LoadingState;", "Lorg/wordpress/android/ui/reader/views/uistates/CommentSnippetItemState$CommentState;", "Lorg/wordpress/android/ui/reader/views/uistates/CommentSnippetItemState$ButtonState;", "Lorg/wordpress/android/ui/reader/views/uistates/CommentSnippetItemState$TextMessage;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class CommentSnippetItemState {
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.ui.reader.views.uistates.CommentItemType type = null;
    
    private CommentSnippetItemState(org.wordpress.android.ui.reader.views.uistates.CommentItemType type) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public org.wordpress.android.ui.reader.views.uistates.CommentItemType getType() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/reader/views/uistates/CommentSnippetItemState$LoadingState;", "Lorg/wordpress/android/ui/reader/views/uistates/CommentSnippetItemState;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class LoadingState extends org.wordpress.android.ui.reader.views.uistates.CommentSnippetItemState {
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.ui.reader.views.uistates.CommentSnippetItemState.LoadingState INSTANCE = null;
        
        private LoadingState() {
            super(null);
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u001a\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BM\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\r\u001a\u00020\u000b\u00a2\u0006\u0002\u0010\u000eJ\t\u0010\u001a\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001c\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\u001e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001f\u001a\u00020\u0007H\u00c6\u0003J\t\u0010 \u001a\u00020\u000bH\u00c6\u0003J\t\u0010!\u001a\u00020\u000bH\u00c6\u0003J\t\u0010\"\u001a\u00020\u000bH\u00c6\u0003Jc\u0010#\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u000bH\u00c6\u0001J\u0013\u0010$\u001a\u00020\u00072\b\u0010%\u001a\u0004\u0018\u00010&H\u00d6\u0003J\t\u0010\'\u001a\u00020(H\u00d6\u0001J\t\u0010)\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\r\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0011\u0010\b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0010R\u0011\u0010\t\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0017R\u0011\u0010\f\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0013R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0017\u00a8\u0006*"}, d2 = {"Lorg/wordpress/android/ui/reader/views/uistates/CommentSnippetItemState$CommentState;", "Lorg/wordpress/android/ui/reader/views/uistates/CommentSnippetItemState;", "authorName", "", "datePublished", "avatarUrl", "showAuthorBadge", "", "commentText", "isPrivatePost", "blogId", "", "postId", "commentId", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;ZJJJ)V", "getAuthorName", "()Ljava/lang/String;", "getAvatarUrl", "getBlogId", "()J", "getCommentId", "getCommentText", "getDatePublished", "()Z", "getPostId", "getShowAuthorBadge", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class CommentState extends org.wordpress.android.ui.reader.views.uistates.CommentSnippetItemState {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String authorName = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String datePublished = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String avatarUrl = null;
        private final boolean showAuthorBadge = false;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String commentText = null;
        private final boolean isPrivatePost = false;
        private final long blogId = 0L;
        private final long postId = 0L;
        private final long commentId = 0L;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.reader.views.uistates.CommentSnippetItemState.CommentState copy(@org.jetbrains.annotations.NotNull()
        java.lang.String authorName, @org.jetbrains.annotations.NotNull()
        java.lang.String datePublished, @org.jetbrains.annotations.NotNull()
        java.lang.String avatarUrl, boolean showAuthorBadge, @org.jetbrains.annotations.NotNull()
        java.lang.String commentText, boolean isPrivatePost, long blogId, long postId, long commentId) {
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
        
        public CommentState(@org.jetbrains.annotations.NotNull()
        java.lang.String authorName, @org.jetbrains.annotations.NotNull()
        java.lang.String datePublished, @org.jetbrains.annotations.NotNull()
        java.lang.String avatarUrl, boolean showAuthorBadge, @org.jetbrains.annotations.NotNull()
        java.lang.String commentText, boolean isPrivatePost, long blogId, long postId, long commentId) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getAuthorName() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getDatePublished() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getAvatarUrl() {
            return null;
        }
        
        public final boolean component4() {
            return false;
        }
        
        public final boolean getShowAuthorBadge() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component5() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getCommentText() {
            return null;
        }
        
        public final boolean component6() {
            return false;
        }
        
        public final boolean isPrivatePost() {
            return false;
        }
        
        public final long component7() {
            return 0L;
        }
        
        public final long getBlogId() {
            return 0L;
        }
        
        public final long component8() {
            return 0L;
        }
        
        public final long getPostId() {
            return 0L;
        }
        
        public final long component9() {
            return 0L;
        }
        
        public final long getCommentId() {
            return 0L;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0018\u0010\u0007\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\t0\b\u00a2\u0006\u0002\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0013\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0014\u001a\u00020\u0005H\u00c6\u0003J\u001b\u0010\u0015\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\t0\bH\u00c6\u0003JC\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\u001a\b\u0002\u0010\u0007\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\t0\bH\u00c6\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u00d6\u0003J\t\u0010\u001b\u001a\u00020\u001cH\u00d6\u0001J\t\u0010\u001d\u001a\u00020\u001eH\u00d6\u0001R\u0011\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR#\u0010\u0007\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\t0\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\f\u00a8\u0006\u001f"}, d2 = {"Lorg/wordpress/android/ui/reader/views/uistates/CommentSnippetItemState$ButtonState;", "Lorg/wordpress/android/ui/reader/views/uistates/CommentSnippetItemState;", "buttonText", "Lorg/wordpress/android/ui/utils/UiString;", "postId", "", "blogId", "onCommentSnippetClicked", "Lkotlin/Function2;", "", "(Lorg/wordpress/android/ui/utils/UiString;JJLkotlin/jvm/functions/Function2;)V", "getBlogId", "()J", "getButtonText", "()Lorg/wordpress/android/ui/utils/UiString;", "getOnCommentSnippetClicked", "()Lkotlin/jvm/functions/Function2;", "getPostId", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class ButtonState extends org.wordpress.android.ui.reader.views.uistates.CommentSnippetItemState {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.utils.UiString buttonText = null;
        private final long postId = 0L;
        private final long blogId = 0L;
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function2<java.lang.Long, java.lang.Long, kotlin.Unit> onCommentSnippetClicked = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.reader.views.uistates.CommentSnippetItemState.ButtonState copy(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiString buttonText, long postId, long blogId, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function2<? super java.lang.Long, ? super java.lang.Long, kotlin.Unit> onCommentSnippetClicked) {
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
        
        public ButtonState(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiString buttonText, long postId, long blogId, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function2<? super java.lang.Long, ? super java.lang.Long, kotlin.Unit> onCommentSnippetClicked) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.UiString component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.UiString getButtonText() {
            return null;
        }
        
        public final long component2() {
            return 0L;
        }
        
        public final long getPostId() {
            return 0L;
        }
        
        public final long component3() {
            return 0L;
        }
        
        public final long getBlogId() {
            return 0L;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function2<java.lang.Long, java.lang.Long, kotlin.Unit> component4() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function2<java.lang.Long, java.lang.Long, kotlin.Unit> getOnCommentSnippetClicked() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/reader/views/uistates/CommentSnippetItemState$TextMessage;", "Lorg/wordpress/android/ui/reader/views/uistates/CommentSnippetItemState;", "message", "Lorg/wordpress/android/ui/utils/UiString;", "(Lorg/wordpress/android/ui/utils/UiString;)V", "getMessage", "()Lorg/wordpress/android/ui/utils/UiString;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class TextMessage extends org.wordpress.android.ui.reader.views.uistates.CommentSnippetItemState {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.utils.UiString message = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.reader.views.uistates.CommentSnippetItemState.TextMessage copy(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiString message) {
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
        
        public TextMessage(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiString message) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.UiString component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.UiString getMessage() {
            return null;
        }
    }
}