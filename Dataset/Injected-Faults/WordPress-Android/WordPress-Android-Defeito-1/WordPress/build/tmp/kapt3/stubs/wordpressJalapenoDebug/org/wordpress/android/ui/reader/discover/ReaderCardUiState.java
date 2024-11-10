package org.wordpress.android.ui.reader.discover;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0005\u0003\u0004\u0005\u0006\u0007B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0004\b\t\n\u000b\u00a8\u0006\f"}, d2 = {"Lorg/wordpress/android/ui/reader/discover/ReaderCardUiState;", "", "()V", "ReaderInterestChipStyleColor", "ReaderInterestsCardUiState", "ReaderPostUiState", "ReaderRecommendedBlogsCardUiState", "ReaderWelcomeBannerCardUiState", "Lorg/wordpress/android/ui/reader/discover/ReaderCardUiState$ReaderWelcomeBannerCardUiState;", "Lorg/wordpress/android/ui/reader/discover/ReaderCardUiState$ReaderPostUiState;", "Lorg/wordpress/android/ui/reader/discover/ReaderCardUiState$ReaderInterestsCardUiState;", "Lorg/wordpress/android/ui/reader/discover/ReaderCardUiState$ReaderRecommendedBlogsCardUiState;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class ReaderCardUiState {
    
    private ReaderCardUiState() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u000e\u001a\u00020\u000fH\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lorg/wordpress/android/ui/reader/discover/ReaderCardUiState$ReaderWelcomeBannerCardUiState;", "Lorg/wordpress/android/ui/reader/discover/ReaderCardUiState;", "titleRes", "", "(I)V", "getTitleRes", "()I", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class ReaderWelcomeBannerCardUiState extends org.wordpress.android.ui.reader.discover.ReaderCardUiState {
        private final int titleRes = 0;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.reader.discover.ReaderCardUiState.ReaderWelcomeBannerCardUiState copy(@androidx.annotation.StringRes()
        int titleRes) {
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
        
        public ReaderWelcomeBannerCardUiState(@androidx.annotation.StringRes()
        int titleRes) {
            super();
        }
        
        public final int component1() {
            return 0;
        }
        
        public final int getTitleRes() {
            return 0;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\bN\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001:\u0002~\u007fB\u00db\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0014\u001a\u00020\u0015\u0012\b\u0010\u0016\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018\u0012\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a\u0012\u0006\u0010\u001b\u001a\u00020\t\u0012\u0006\u0010\u001c\u001a\u00020\t\u0012\u0006\u0010\u001d\u001a\u00020\t\u0012\u0006\u0010\u001e\u001a\u00020\t\u0012\u0006\u0010\u001f\u001a\u00020\t\u0012\u0006\u0010 \u001a\u00020!\u0012\u0006\u0010\"\u001a\u00020!\u0012\u0006\u0010#\u001a\u00020!\u0012\u0006\u0010$\u001a\u00020!\u0012\u0010\b\u0002\u0010%\u001a\n\u0012\u0004\u0012\u00020&\u0018\u00010\u0010\u0012\u0018\u0010\'\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020)0(\u0012\u0012\u0010*\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020)0+\u0012\u0012\u0010,\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020)0+\u0012\u0012\u0010-\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020)0+\u0012\u0018\u0010.\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020)0(\u00a2\u0006\u0002\u0010/J\t\u0010Y\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010Z\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010[\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010\\\u001a\u00020\u0015H\u00c6\u0003J\u000b\u0010]\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010^\u001a\u0004\u0018\u00010\u0018H\u00c6\u0003J\u000b\u0010_\u001a\u0004\u0018\u00010\u001aH\u00c6\u0003J\t\u0010`\u001a\u00020\tH\u00c6\u0003J\t\u0010a\u001a\u00020\tH\u00c6\u0003J\t\u0010b\u001a\u00020\tH\u00c6\u0003J\t\u0010c\u001a\u00020\tH\u00c6\u0003J\t\u0010d\u001a\u00020\u0005H\u00c6\u0003J\t\u0010e\u001a\u00020\tH\u00c6\u0003J\t\u0010f\u001a\u00020!H\u00c6\u0003J\t\u0010g\u001a\u00020!H\u00c6\u0003J\t\u0010h\u001a\u00020!H\u00c6\u0003J\t\u0010i\u001a\u00020!H\u00c6\u0003J\u0011\u0010j\u001a\n\u0012\u0004\u0012\u00020&\u0018\u00010\u0010H\u00c6\u0003J\u001b\u0010k\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020)0(H\u00c6\u0003J\u0015\u0010l\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020)0+H\u00c6\u0003J\u0015\u0010m\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020)0+H\u00c6\u0003J\u0015\u0010n\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020)0+H\u00c6\u0003J\t\u0010o\u001a\u00020\u0005H\u00c6\u0003J\u001b\u0010p\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020)0(H\u00c6\u0003J\t\u0010q\u001a\u00020\u0005H\u00c6\u0003J\t\u0010r\u001a\u00020\tH\u00c6\u0003J\t\u0010s\u001a\u00020\u000bH\u00c6\u0003J\u000b\u0010t\u001a\u0004\u0018\u00010\rH\u00c6\u0003J\u000b\u0010u\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000f\u0010v\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u00c6\u0003J\u0099\u0003\u0010w\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0014\u001a\u00020\u00152\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00182\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\b\b\u0002\u0010\u001b\u001a\u00020\t2\b\b\u0002\u0010\u001c\u001a\u00020\t2\b\b\u0002\u0010\u001d\u001a\u00020\t2\b\b\u0002\u0010\u001e\u001a\u00020\t2\b\b\u0002\u0010\u001f\u001a\u00020\t2\b\b\u0002\u0010 \u001a\u00020!2\b\b\u0002\u0010\"\u001a\u00020!2\b\b\u0002\u0010#\u001a\u00020!2\b\b\u0002\u0010$\u001a\u00020!2\u0010\b\u0002\u0010%\u001a\n\u0012\u0004\u0012\u00020&\u0018\u00010\u00102\u001a\b\u0002\u0010\'\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020)0(2\u0014\b\u0002\u0010*\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020)0+2\u0014\b\u0002\u0010,\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020)0+2\u0014\b\u0002\u0010-\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020)0+2\u001a\b\u0002\u0010.\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020)0(H\u00c6\u0001J\u0013\u0010x\u001a\u00020\t2\b\u0010y\u001a\u0004\u0018\u00010zH\u00d6\u0003J\t\u0010{\u001a\u00020|H\u00d6\u0001J\t\u0010}\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b0\u00101R\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b2\u00103R\u0011\u0010 \u001a\u00020!\u00a2\u0006\b\n\u0000\u001a\u0004\b4\u00105R\u0011\u0010$\u001a\u00020!\u00a2\u0006\b\n\u0000\u001a\u0004\b6\u00105R\u0013\u0010\u0019\u001a\u0004\u0018\u00010\u001a\u00a2\u0006\b\n\u0000\u001a\u0004\b7\u00108R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b9\u0010:R\u0011\u0010\u001b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b;\u0010<R\u0011\u0010\u0014\u001a\u00020\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b=\u0010>R\u0013\u0010\u0013\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b?\u0010:R\u0011\u0010\u001d\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b@\u0010<R\u0011\u0010\u0007\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\bA\u00101R\u0013\u0010\u0016\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\bB\u0010:R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010<R\u0011\u0010\"\u001a\u00020!\u00a2\u0006\b\n\u0000\u001a\u0004\bC\u00105R\u0019\u0010%\u001a\n\u0012\u0004\u0012\u00020&\u0018\u00010\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\bD\u0010ER\u0011\u0010\u001e\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\bF\u0010<R#\u0010\'\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020)0(\u00a2\u0006\b\n\u0000\u001a\u0004\bG\u0010HR\u001d\u0010*\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020)0+\u00a2\u0006\b\n\u0000\u001a\u0004\bI\u0010JR\u001d\u0010,\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020)0+\u00a2\u0006\b\n\u0000\u001a\u0004\bK\u0010JR\u001d\u0010-\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020)0+\u00a2\u0006\b\n\u0000\u001a\u0004\bL\u0010JR#\u0010.\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020)0(\u00a2\u0006\b\n\u0000\u001a\u0004\bM\u0010HR\u0011\u0010\u001f\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\bN\u0010<R\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\bO\u0010:R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\bP\u00101R\u0011\u0010#\u001a\u00020!\u00a2\u0006\b\n\u0000\u001a\u0004\bQ\u00105R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\bR\u0010:R\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\bS\u0010ER\u0013\u0010\u0017\u001a\u0004\u0018\u00010\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\bT\u0010UR\u0013\u0010\f\u001a\u0004\u0018\u00010\r\u00a2\u0006\b\n\u0000\u001a\u0004\bV\u0010WR\u0011\u0010\u001c\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\bX\u0010<\u00a8\u0006\u0080\u0001"}, d2 = {"Lorg/wordpress/android/ui/reader/discover/ReaderCardUiState$ReaderPostUiState;", "Lorg/wordpress/android/ui/reader/discover/ReaderCardUiState;", "source", "", "postId", "", "blogId", "feedId", "isFollowed", "", "blogSection", "Lorg/wordpress/android/ui/reader/views/uistates/ReaderBlogSectionUiState;", "title", "Lorg/wordpress/android/ui/utils/UiString;", "excerpt", "tagItems", "", "Lorg/wordpress/android/ui/reader/discover/interests/TagUiState;", "photoTitle", "featuredImageUrl", "featuredImageCornerRadius", "Lorg/wordpress/android/ui/utils/UiDimen;", "fullVideoUrl", "thumbnailStripSection", "Lorg/wordpress/android/ui/reader/discover/ReaderCardUiState$ReaderPostUiState$GalleryThumbnailStripData;", "discoverSection", "Lorg/wordpress/android/ui/reader/discover/ReaderCardUiState$ReaderPostUiState$DiscoverLayoutUiState;", "expandableTagsViewVisibility", "videoOverlayVisibility", "featuredImageVisibility", "moreMenuVisibility", "photoFrameVisibility", "bookmarkAction", "Lorg/wordpress/android/ui/reader/discover/ReaderPostCardAction$PrimaryAction;", "likeAction", "reblogAction", "commentsAction", "moreMenuItems", "Lorg/wordpress/android/ui/reader/discover/ReaderPostCardAction;", "onItemClicked", "Lkotlin/Function2;", "", "onItemRendered", "Lkotlin/Function1;", "onMoreButtonClicked", "onMoreDismissed", "onVideoOverlayClicked", "(Ljava/lang/String;JJJZLorg/wordpress/android/ui/reader/views/uistates/ReaderBlogSectionUiState;Lorg/wordpress/android/ui/utils/UiString;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Lorg/wordpress/android/ui/utils/UiDimen;Ljava/lang/String;Lorg/wordpress/android/ui/reader/discover/ReaderCardUiState$ReaderPostUiState$GalleryThumbnailStripData;Lorg/wordpress/android/ui/reader/discover/ReaderCardUiState$ReaderPostUiState$DiscoverLayoutUiState;ZZZZZLorg/wordpress/android/ui/reader/discover/ReaderPostCardAction$PrimaryAction;Lorg/wordpress/android/ui/reader/discover/ReaderPostCardAction$PrimaryAction;Lorg/wordpress/android/ui/reader/discover/ReaderPostCardAction$PrimaryAction;Lorg/wordpress/android/ui/reader/discover/ReaderPostCardAction$PrimaryAction;Ljava/util/List;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;)V", "getBlogId", "()J", "getBlogSection", "()Lorg/wordpress/android/ui/reader/views/uistates/ReaderBlogSectionUiState;", "getBookmarkAction", "()Lorg/wordpress/android/ui/reader/discover/ReaderPostCardAction$PrimaryAction;", "getCommentsAction", "getDiscoverSection", "()Lorg/wordpress/android/ui/reader/discover/ReaderCardUiState$ReaderPostUiState$DiscoverLayoutUiState;", "getExcerpt", "()Ljava/lang/String;", "getExpandableTagsViewVisibility", "()Z", "getFeaturedImageCornerRadius", "()Lorg/wordpress/android/ui/utils/UiDimen;", "getFeaturedImageUrl", "getFeaturedImageVisibility", "getFeedId", "getFullVideoUrl", "getLikeAction", "getMoreMenuItems", "()Ljava/util/List;", "getMoreMenuVisibility", "getOnItemClicked", "()Lkotlin/jvm/functions/Function2;", "getOnItemRendered", "()Lkotlin/jvm/functions/Function1;", "getOnMoreButtonClicked", "getOnMoreDismissed", "getOnVideoOverlayClicked", "getPhotoFrameVisibility", "getPhotoTitle", "getPostId", "getReblogAction", "getSource", "getTagItems", "getThumbnailStripSection", "()Lorg/wordpress/android/ui/reader/discover/ReaderCardUiState$ReaderPostUiState$GalleryThumbnailStripData;", "getTitle", "()Lorg/wordpress/android/ui/utils/UiString;", "getVideoOverlayVisibility", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component27", "component28", "component29", "component3", "component30", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "", "hashCode", "", "toString", "DiscoverLayoutUiState", "GalleryThumbnailStripData", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class ReaderPostUiState extends org.wordpress.android.ui.reader.discover.ReaderCardUiState {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String source = null;
        private final long postId = 0L;
        private final long blogId = 0L;
        private final long feedId = 0L;
        private final boolean isFollowed = false;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.reader.views.uistates.ReaderBlogSectionUiState blogSection = null;
        @org.jetbrains.annotations.Nullable()
        private final org.wordpress.android.ui.utils.UiString title = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String excerpt = null;
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<org.wordpress.android.ui.reader.discover.interests.TagUiState> tagItems = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String photoTitle = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String featuredImageUrl = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.utils.UiDimen featuredImageCornerRadius = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String fullVideoUrl = null;
        @org.jetbrains.annotations.Nullable()
        private final org.wordpress.android.ui.reader.discover.ReaderCardUiState.ReaderPostUiState.GalleryThumbnailStripData thumbnailStripSection = null;
        @org.jetbrains.annotations.Nullable()
        private final org.wordpress.android.ui.reader.discover.ReaderCardUiState.ReaderPostUiState.DiscoverLayoutUiState discoverSection = null;
        private final boolean expandableTagsViewVisibility = false;
        private final boolean videoOverlayVisibility = false;
        private final boolean featuredImageVisibility = false;
        private final boolean moreMenuVisibility = false;
        private final boolean photoFrameVisibility = false;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.reader.discover.ReaderPostCardAction.PrimaryAction bookmarkAction = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.reader.discover.ReaderPostCardAction.PrimaryAction likeAction = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.reader.discover.ReaderPostCardAction.PrimaryAction reblogAction = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.reader.discover.ReaderPostCardAction.PrimaryAction commentsAction = null;
        @org.jetbrains.annotations.Nullable()
        private final java.util.List<org.wordpress.android.ui.reader.discover.ReaderPostCardAction> moreMenuItems = null;
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function2<java.lang.Long, java.lang.Long, kotlin.Unit> onItemClicked = null;
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function1<org.wordpress.android.ui.reader.discover.ReaderCardUiState, kotlin.Unit> onItemRendered = null;
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function1<org.wordpress.android.ui.reader.discover.ReaderCardUiState.ReaderPostUiState, kotlin.Unit> onMoreButtonClicked = null;
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function1<org.wordpress.android.ui.reader.discover.ReaderCardUiState.ReaderPostUiState, kotlin.Unit> onMoreDismissed = null;
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function2<java.lang.Long, java.lang.Long, kotlin.Unit> onVideoOverlayClicked = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.reader.discover.ReaderCardUiState.ReaderPostUiState copy(@org.jetbrains.annotations.NotNull()
        java.lang.String source, long postId, long blogId, long feedId, boolean isFollowed, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.reader.views.uistates.ReaderBlogSectionUiState blogSection, @org.jetbrains.annotations.Nullable()
        org.wordpress.android.ui.utils.UiString title, @org.jetbrains.annotations.Nullable()
        java.lang.String excerpt, @org.jetbrains.annotations.NotNull()
        java.util.List<org.wordpress.android.ui.reader.discover.interests.TagUiState> tagItems, @org.jetbrains.annotations.Nullable()
        java.lang.String photoTitle, @org.jetbrains.annotations.Nullable()
        java.lang.String featuredImageUrl, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiDimen featuredImageCornerRadius, @org.jetbrains.annotations.Nullable()
        java.lang.String fullVideoUrl, @org.jetbrains.annotations.Nullable()
        org.wordpress.android.ui.reader.discover.ReaderCardUiState.ReaderPostUiState.GalleryThumbnailStripData thumbnailStripSection, @org.jetbrains.annotations.Nullable()
        org.wordpress.android.ui.reader.discover.ReaderCardUiState.ReaderPostUiState.DiscoverLayoutUiState discoverSection, boolean expandableTagsViewVisibility, boolean videoOverlayVisibility, boolean featuredImageVisibility, boolean moreMenuVisibility, boolean photoFrameVisibility, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.reader.discover.ReaderPostCardAction.PrimaryAction bookmarkAction, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.reader.discover.ReaderPostCardAction.PrimaryAction likeAction, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.reader.discover.ReaderPostCardAction.PrimaryAction reblogAction, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.reader.discover.ReaderPostCardAction.PrimaryAction commentsAction, @org.jetbrains.annotations.Nullable()
        java.util.List<? extends org.wordpress.android.ui.reader.discover.ReaderPostCardAction> moreMenuItems, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function2<? super java.lang.Long, ? super java.lang.Long, kotlin.Unit> onItemClicked, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super org.wordpress.android.ui.reader.discover.ReaderCardUiState, kotlin.Unit> onItemRendered, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super org.wordpress.android.ui.reader.discover.ReaderCardUiState.ReaderPostUiState, kotlin.Unit> onMoreButtonClicked, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super org.wordpress.android.ui.reader.discover.ReaderCardUiState.ReaderPostUiState, kotlin.Unit> onMoreDismissed, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function2<? super java.lang.Long, ? super java.lang.Long, kotlin.Unit> onVideoOverlayClicked) {
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
        
        public ReaderPostUiState(@org.jetbrains.annotations.NotNull()
        java.lang.String source, long postId, long blogId, long feedId, boolean isFollowed, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.reader.views.uistates.ReaderBlogSectionUiState blogSection, @org.jetbrains.annotations.Nullable()
        org.wordpress.android.ui.utils.UiString title, @org.jetbrains.annotations.Nullable()
        java.lang.String excerpt, @org.jetbrains.annotations.NotNull()
        java.util.List<org.wordpress.android.ui.reader.discover.interests.TagUiState> tagItems, @org.jetbrains.annotations.Nullable()
        java.lang.String photoTitle, @org.jetbrains.annotations.Nullable()
        java.lang.String featuredImageUrl, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiDimen featuredImageCornerRadius, @org.jetbrains.annotations.Nullable()
        java.lang.String fullVideoUrl, @org.jetbrains.annotations.Nullable()
        org.wordpress.android.ui.reader.discover.ReaderCardUiState.ReaderPostUiState.GalleryThumbnailStripData thumbnailStripSection, @org.jetbrains.annotations.Nullable()
        org.wordpress.android.ui.reader.discover.ReaderCardUiState.ReaderPostUiState.DiscoverLayoutUiState discoverSection, boolean expandableTagsViewVisibility, boolean videoOverlayVisibility, boolean featuredImageVisibility, boolean moreMenuVisibility, boolean photoFrameVisibility, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.reader.discover.ReaderPostCardAction.PrimaryAction bookmarkAction, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.reader.discover.ReaderPostCardAction.PrimaryAction likeAction, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.reader.discover.ReaderPostCardAction.PrimaryAction reblogAction, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.reader.discover.ReaderPostCardAction.PrimaryAction commentsAction, @org.jetbrains.annotations.Nullable()
        java.util.List<? extends org.wordpress.android.ui.reader.discover.ReaderPostCardAction> moreMenuItems, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function2<? super java.lang.Long, ? super java.lang.Long, kotlin.Unit> onItemClicked, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super org.wordpress.android.ui.reader.discover.ReaderCardUiState, kotlin.Unit> onItemRendered, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super org.wordpress.android.ui.reader.discover.ReaderCardUiState.ReaderPostUiState, kotlin.Unit> onMoreButtonClicked, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super org.wordpress.android.ui.reader.discover.ReaderCardUiState.ReaderPostUiState, kotlin.Unit> onMoreDismissed, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function2<? super java.lang.Long, ? super java.lang.Long, kotlin.Unit> onVideoOverlayClicked) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getSource() {
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
        
        public final long component4() {
            return 0L;
        }
        
        public final long getFeedId() {
            return 0L;
        }
        
        public final boolean component5() {
            return false;
        }
        
        public final boolean isFollowed() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.reader.views.uistates.ReaderBlogSectionUiState component6() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.reader.views.uistates.ReaderBlogSectionUiState getBlogSection() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final org.wordpress.android.ui.utils.UiString component7() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final org.wordpress.android.ui.utils.UiString getTitle() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component8() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getExcerpt() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.ui.reader.discover.interests.TagUiState> component9() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.ui.reader.discover.interests.TagUiState> getTagItems() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component10() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getPhotoTitle() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component11() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getFeaturedImageUrl() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.UiDimen component12() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.UiDimen getFeaturedImageCornerRadius() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component13() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getFullVideoUrl() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final org.wordpress.android.ui.reader.discover.ReaderCardUiState.ReaderPostUiState.GalleryThumbnailStripData component14() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final org.wordpress.android.ui.reader.discover.ReaderCardUiState.ReaderPostUiState.GalleryThumbnailStripData getThumbnailStripSection() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final org.wordpress.android.ui.reader.discover.ReaderCardUiState.ReaderPostUiState.DiscoverLayoutUiState component15() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final org.wordpress.android.ui.reader.discover.ReaderCardUiState.ReaderPostUiState.DiscoverLayoutUiState getDiscoverSection() {
            return null;
        }
        
        public final boolean component16() {
            return false;
        }
        
        public final boolean getExpandableTagsViewVisibility() {
            return false;
        }
        
        public final boolean component17() {
            return false;
        }
        
        public final boolean getVideoOverlayVisibility() {
            return false;
        }
        
        public final boolean component18() {
            return false;
        }
        
        public final boolean getFeaturedImageVisibility() {
            return false;
        }
        
        public final boolean component19() {
            return false;
        }
        
        public final boolean getMoreMenuVisibility() {
            return false;
        }
        
        public final boolean component20() {
            return false;
        }
        
        public final boolean getPhotoFrameVisibility() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.reader.discover.ReaderPostCardAction.PrimaryAction component21() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.reader.discover.ReaderPostCardAction.PrimaryAction getBookmarkAction() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.reader.discover.ReaderPostCardAction.PrimaryAction component22() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.reader.discover.ReaderPostCardAction.PrimaryAction getLikeAction() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.reader.discover.ReaderPostCardAction.PrimaryAction component23() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.reader.discover.ReaderPostCardAction.PrimaryAction getReblogAction() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.reader.discover.ReaderPostCardAction.PrimaryAction component24() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.reader.discover.ReaderPostCardAction.PrimaryAction getCommentsAction() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.util.List<org.wordpress.android.ui.reader.discover.ReaderPostCardAction> component25() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.util.List<org.wordpress.android.ui.reader.discover.ReaderPostCardAction> getMoreMenuItems() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function2<java.lang.Long, java.lang.Long, kotlin.Unit> component26() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function2<java.lang.Long, java.lang.Long, kotlin.Unit> getOnItemClicked() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function1<org.wordpress.android.ui.reader.discover.ReaderCardUiState, kotlin.Unit> component27() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function1<org.wordpress.android.ui.reader.discover.ReaderCardUiState, kotlin.Unit> getOnItemRendered() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function1<org.wordpress.android.ui.reader.discover.ReaderCardUiState.ReaderPostUiState, kotlin.Unit> component28() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function1<org.wordpress.android.ui.reader.discover.ReaderCardUiState.ReaderPostUiState, kotlin.Unit> getOnMoreButtonClicked() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function1<org.wordpress.android.ui.reader.discover.ReaderCardUiState.ReaderPostUiState, kotlin.Unit> component29() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function1<org.wordpress.android.ui.reader.discover.ReaderCardUiState.ReaderPostUiState, kotlin.Unit> getOnMoreDismissed() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function2<java.lang.Long, java.lang.Long, kotlin.Unit> component30() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function2<java.lang.Long, java.lang.Long, kotlin.Unit> getOnVideoOverlayClicked() {
            return null;
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000f\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0010\u001a\u00020\u0007H\u00c6\u0003J\'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u00c6\u0001J\u0013\u0010\u0012\u001a\u00020\u00052\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0014\u001a\u00020\u0015H\u00d6\u0001J\t\u0010\u0016\u001a\u00020\u0007H\u00d6\u0001R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\r\u00a8\u0006\u0017"}, d2 = {"Lorg/wordpress/android/ui/reader/discover/ReaderCardUiState$ReaderPostUiState$GalleryThumbnailStripData;", "", "images", "Lorg/wordpress/android/ui/reader/models/ReaderImageList;", "isPrivate", "", "content", "", "(Lorg/wordpress/android/ui/reader/models/ReaderImageList;ZLjava/lang/String;)V", "getContent", "()Ljava/lang/String;", "getImages", "()Lorg/wordpress/android/ui/reader/models/ReaderImageList;", "()Z", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class GalleryThumbnailStripData {
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.reader.models.ReaderImageList images = null;
            private final boolean isPrivate = false;
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String content = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.reader.discover.ReaderCardUiState.ReaderPostUiState.GalleryThumbnailStripData copy(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.reader.models.ReaderImageList images, boolean isPrivate, @org.jetbrains.annotations.NotNull()
            java.lang.String content) {
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
            
            public GalleryThumbnailStripData(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.reader.models.ReaderImageList images, boolean isPrivate, @org.jetbrains.annotations.NotNull()
            java.lang.String content) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.reader.models.ReaderImageList component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.reader.models.ReaderImageList getImages() {
                return null;
            }
            
            public final boolean component2() {
                return false;
            }
            
            public final boolean isPrivate() {
                return false;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String component3() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getContent() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0010\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0018\u0010\b\u001a\u0014\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\t\u00a2\u0006\u0002\u0010\fJ\t\u0010\u0015\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0016\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0017\u001a\u00020\u0007H\u00c6\u0003J\u001b\u0010\u0018\u001a\u0014\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\tH\u00c6\u0003JC\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u001a\b\u0002\u0010\b\u001a\u0014\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\tH\u00c6\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001d\u001a\u00020\u001eH\u00d6\u0001J\t\u0010\u001f\u001a\u00020\u0005H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R#\u0010\b\u001a\u0014\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014\u00a8\u0006 "}, d2 = {"Lorg/wordpress/android/ui/reader/discover/ReaderCardUiState$ReaderPostUiState$DiscoverLayoutUiState;", "", "discoverText", "Landroid/text/Spanned;", "discoverAvatarUrl", "", "imageType", "Lorg/wordpress/android/util/image/ImageType;", "onDiscoverClicked", "Lkotlin/Function2;", "", "", "(Landroid/text/Spanned;Ljava/lang/String;Lorg/wordpress/android/util/image/ImageType;Lkotlin/jvm/functions/Function2;)V", "getDiscoverAvatarUrl", "()Ljava/lang/String;", "getDiscoverText", "()Landroid/text/Spanned;", "getImageType", "()Lorg/wordpress/android/util/image/ImageType;", "getOnDiscoverClicked", "()Lkotlin/jvm/functions/Function2;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class DiscoverLayoutUiState {
            @org.jetbrains.annotations.NotNull()
            private final android.text.Spanned discoverText = null;
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String discoverAvatarUrl = null;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.util.image.ImageType imageType = null;
            @org.jetbrains.annotations.NotNull()
            private final kotlin.jvm.functions.Function2<java.lang.Long, java.lang.Long, kotlin.Unit> onDiscoverClicked = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.reader.discover.ReaderCardUiState.ReaderPostUiState.DiscoverLayoutUiState copy(@org.jetbrains.annotations.NotNull()
            android.text.Spanned discoverText, @org.jetbrains.annotations.NotNull()
            java.lang.String discoverAvatarUrl, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.util.image.ImageType imageType, @org.jetbrains.annotations.NotNull()
            kotlin.jvm.functions.Function2<? super java.lang.Long, ? super java.lang.Long, kotlin.Unit> onDiscoverClicked) {
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
            
            public DiscoverLayoutUiState(@org.jetbrains.annotations.NotNull()
            android.text.Spanned discoverText, @org.jetbrains.annotations.NotNull()
            java.lang.String discoverAvatarUrl, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.util.image.ImageType imageType, @org.jetbrains.annotations.NotNull()
            kotlin.jvm.functions.Function2<? super java.lang.Long, ? super java.lang.Long, kotlin.Unit> onDiscoverClicked) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final android.text.Spanned component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final android.text.Spanned getDiscoverText() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String component2() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getDiscoverAvatarUrl() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.util.image.ImageType component3() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.util.image.ImageType getImageType() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final kotlin.jvm.functions.Function2<java.lang.Long, java.lang.Long, kotlin.Unit> component4() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final kotlin.jvm.functions.Function2<java.lang.Long, java.lang.Long, kotlin.Unit> getOnDiscoverClicked() {
                return null;
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001:\u0002\u0012\u0013B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\u0005J\u000f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\u0019\u0010\t\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u00d6\u0003J\t\u0010\u000e\u001a\u00020\u000fH\u00d6\u0001J\t\u0010\u0010\u001a\u00020\u0011H\u00d6\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\u0014"}, d2 = {"Lorg/wordpress/android/ui/reader/discover/ReaderCardUiState$ReaderInterestsCardUiState;", "Lorg/wordpress/android/ui/reader/discover/ReaderCardUiState;", "interest", "", "Lorg/wordpress/android/ui/reader/discover/ReaderCardUiState$ReaderInterestsCardUiState$ReaderInterestUiState;", "(Ljava/util/List;)V", "getInterest", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "ChipStyle", "ReaderInterestUiState", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class ReaderInterestsCardUiState extends org.wordpress.android.ui.reader.discover.ReaderCardUiState {
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<org.wordpress.android.ui.reader.discover.ReaderCardUiState.ReaderInterestsCardUiState.ReaderInterestUiState> interest = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.reader.discover.ReaderCardUiState.ReaderInterestsCardUiState copy(@org.jetbrains.annotations.NotNull()
        java.util.List<org.wordpress.android.ui.reader.discover.ReaderCardUiState.ReaderInterestsCardUiState.ReaderInterestUiState> interest) {
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
        
        public ReaderInterestsCardUiState(@org.jetbrains.annotations.NotNull()
        java.util.List<org.wordpress.android.ui.reader.discover.ReaderCardUiState.ReaderInterestsCardUiState.ReaderInterestUiState> interest) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.ui.reader.discover.ReaderCardUiState.ReaderInterestsCardUiState.ReaderInterestUiState> component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.ui.reader.discover.ReaderCardUiState.ReaderInterestsCardUiState.ReaderInterestUiState> getInterest() {
            return null;
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003H\u00c6\u0003J\u0015\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0003J\t\u0010\u0012\u001a\u00020\bH\u00c6\u0003J3\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0014\b\u0002\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00060\u00052\b\b\u0002\u0010\u0007\u001a\u00020\bH\u00c6\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0017\u001a\u00020\u0018H\u00d6\u0001J\t\u0010\u0019\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006\u001a"}, d2 = {"Lorg/wordpress/android/ui/reader/discover/ReaderCardUiState$ReaderInterestsCardUiState$ReaderInterestUiState;", "", "interest", "", "onClicked", "Lkotlin/Function1;", "", "chipStyle", "Lorg/wordpress/android/ui/reader/discover/ReaderCardUiState$ReaderInterestsCardUiState$ChipStyle;", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lorg/wordpress/android/ui/reader/discover/ReaderCardUiState$ReaderInterestsCardUiState$ChipStyle;)V", "getChipStyle", "()Lorg/wordpress/android/ui/reader/discover/ReaderCardUiState$ReaderInterestsCardUiState$ChipStyle;", "getInterest", "()Ljava/lang/String;", "getOnClicked", "()Lkotlin/jvm/functions/Function1;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class ReaderInterestUiState {
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String interest = null;
            @org.jetbrains.annotations.NotNull()
            private final kotlin.jvm.functions.Function1<java.lang.String, kotlin.Unit> onClicked = null;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.reader.discover.ReaderCardUiState.ReaderInterestsCardUiState.ChipStyle chipStyle = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.reader.discover.ReaderCardUiState.ReaderInterestsCardUiState.ReaderInterestUiState copy(@org.jetbrains.annotations.NotNull()
            java.lang.String interest, @org.jetbrains.annotations.NotNull()
            kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onClicked, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.reader.discover.ReaderCardUiState.ReaderInterestsCardUiState.ChipStyle chipStyle) {
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
            
            public ReaderInterestUiState(@org.jetbrains.annotations.NotNull()
            java.lang.String interest, @org.jetbrains.annotations.NotNull()
            kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onClicked, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.reader.discover.ReaderCardUiState.ReaderInterestsCardUiState.ChipStyle chipStyle) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getInterest() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final kotlin.jvm.functions.Function1<java.lang.String, kotlin.Unit> component2() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final kotlin.jvm.functions.Function1<java.lang.String, kotlin.Unit> getOnClicked() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.reader.discover.ReaderCardUiState.ReaderInterestsCardUiState.ChipStyle component3() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.reader.discover.ReaderCardUiState.ReaderInterestsCardUiState.ChipStyle getChipStyle() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u000b\f\r\u000eB%\b\u0004\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0006R\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b\u0082\u0001\u0004\u000f\u0010\u0011\u0012\u00a8\u0006\u0013"}, d2 = {"Lorg/wordpress/android/ui/reader/discover/ReaderCardUiState$ReaderInterestsCardUiState$ChipStyle;", "", "chipStrokeColorResId", "", "chipFontColorResId", "chipFillColorResId", "(III)V", "getChipFillColorResId", "()I", "getChipFontColorResId", "getChipStrokeColorResId", "ChipStyleGreen", "ChipStyleOrange", "ChipStylePurple", "ChipStyleYellow", "Lorg/wordpress/android/ui/reader/discover/ReaderCardUiState$ReaderInterestsCardUiState$ChipStyle$ChipStyleGreen;", "Lorg/wordpress/android/ui/reader/discover/ReaderCardUiState$ReaderInterestsCardUiState$ChipStyle$ChipStylePurple;", "Lorg/wordpress/android/ui/reader/discover/ReaderCardUiState$ReaderInterestsCardUiState$ChipStyle$ChipStyleYellow;", "Lorg/wordpress/android/ui/reader/discover/ReaderCardUiState$ReaderInterestsCardUiState$ChipStyle$ChipStyleOrange;", "org.wordpress.android_wordpressJalapenoDebug"})
        public static abstract class ChipStyle {
            private final int chipStrokeColorResId = 0;
            private final int chipFontColorResId = 0;
            private final int chipFillColorResId = 0;
            
            private ChipStyle(@androidx.annotation.ColorRes()
            int chipStrokeColorResId, @androidx.annotation.ColorRes()
            int chipFontColorResId, @androidx.annotation.ColorRes()
            int chipFillColorResId) {
                super();
            }
            
            public final int getChipStrokeColorResId() {
                return 0;
            }
            
            public final int getChipFontColorResId() {
                return 0;
            }
            
            public final int getChipFillColorResId() {
                return 0;
            }
            
            @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/reader/discover/ReaderCardUiState$ReaderInterestsCardUiState$ChipStyle$ChipStyleGreen;", "Lorg/wordpress/android/ui/reader/discover/ReaderCardUiState$ReaderInterestsCardUiState$ChipStyle;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
            public static final class ChipStyleGreen extends org.wordpress.android.ui.reader.discover.ReaderCardUiState.ReaderInterestsCardUiState.ChipStyle {
                @org.jetbrains.annotations.NotNull()
                public static final org.wordpress.android.ui.reader.discover.ReaderCardUiState.ReaderInterestsCardUiState.ChipStyle.ChipStyleGreen INSTANCE = null;
                
                private ChipStyleGreen() {
                    super(0, 0, 0);
                }
            }
            
            @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/reader/discover/ReaderCardUiState$ReaderInterestsCardUiState$ChipStyle$ChipStylePurple;", "Lorg/wordpress/android/ui/reader/discover/ReaderCardUiState$ReaderInterestsCardUiState$ChipStyle;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
            public static final class ChipStylePurple extends org.wordpress.android.ui.reader.discover.ReaderCardUiState.ReaderInterestsCardUiState.ChipStyle {
                @org.jetbrains.annotations.NotNull()
                public static final org.wordpress.android.ui.reader.discover.ReaderCardUiState.ReaderInterestsCardUiState.ChipStyle.ChipStylePurple INSTANCE = null;
                
                private ChipStylePurple() {
                    super(0, 0, 0);
                }
            }
            
            @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/reader/discover/ReaderCardUiState$ReaderInterestsCardUiState$ChipStyle$ChipStyleYellow;", "Lorg/wordpress/android/ui/reader/discover/ReaderCardUiState$ReaderInterestsCardUiState$ChipStyle;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
            public static final class ChipStyleYellow extends org.wordpress.android.ui.reader.discover.ReaderCardUiState.ReaderInterestsCardUiState.ChipStyle {
                @org.jetbrains.annotations.NotNull()
                public static final org.wordpress.android.ui.reader.discover.ReaderCardUiState.ReaderInterestsCardUiState.ChipStyle.ChipStyleYellow INSTANCE = null;
                
                private ChipStyleYellow() {
                    super(0, 0, 0);
                }
            }
            
            @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/reader/discover/ReaderCardUiState$ReaderInterestsCardUiState$ChipStyle$ChipStyleOrange;", "Lorg/wordpress/android/ui/reader/discover/ReaderCardUiState$ReaderInterestsCardUiState$ChipStyle;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
            public static final class ChipStyleOrange extends org.wordpress.android.ui.reader.discover.ReaderCardUiState.ReaderInterestsCardUiState.ChipStyle {
                @org.jetbrains.annotations.NotNull()
                public static final org.wordpress.android.ui.reader.discover.ReaderCardUiState.ReaderInterestsCardUiState.ChipStyle.ChipStyleOrange INSTANCE = null;
                
                private ChipStyleOrange() {
                    super(0, 0, 0);
                }
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\b\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n\u00a8\u0006\u000b"}, d2 = {"Lorg/wordpress/android/ui/reader/discover/ReaderCardUiState$ReaderInterestChipStyleColor;", "", "id", "", "(Ljava/lang/String;II)V", "getId", "()I", "GREEN", "PURPLE", "YELLOW", "ORANGE", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum ReaderInterestChipStyleColor {
        /*public static final*/ GREEN /* = new GREEN(0) */,
        /*public static final*/ PURPLE /* = new PURPLE(0) */,
        /*public static final*/ YELLOW /* = new YELLOW(0) */,
        /*public static final*/ ORANGE /* = new ORANGE(0) */;
        private final int id = 0;
        
        ReaderInterestChipStyleColor(int id) {
        }
        
        public final int getId() {
            return 0;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001:\u0001\u0012B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\u0005J\u000f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\u0019\u0010\t\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u00d6\u0003J\t\u0010\u000e\u001a\u00020\u000fH\u00d6\u0001J\t\u0010\u0010\u001a\u00020\u0011H\u00d6\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\u0013"}, d2 = {"Lorg/wordpress/android/ui/reader/discover/ReaderCardUiState$ReaderRecommendedBlogsCardUiState;", "Lorg/wordpress/android/ui/reader/discover/ReaderCardUiState;", "blogs", "", "Lorg/wordpress/android/ui/reader/discover/ReaderCardUiState$ReaderRecommendedBlogsCardUiState$ReaderRecommendedBlogUiState;", "(Ljava/util/List;)V", "getBlogs", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "ReaderRecommendedBlogUiState", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class ReaderRecommendedBlogsCardUiState extends org.wordpress.android.ui.reader.discover.ReaderCardUiState {
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<org.wordpress.android.ui.reader.discover.ReaderCardUiState.ReaderRecommendedBlogsCardUiState.ReaderRecommendedBlogUiState> blogs = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.reader.discover.ReaderCardUiState.ReaderRecommendedBlogsCardUiState copy(@org.jetbrains.annotations.NotNull()
        java.util.List<org.wordpress.android.ui.reader.discover.ReaderCardUiState.ReaderRecommendedBlogsCardUiState.ReaderRecommendedBlogUiState> blogs) {
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
        
        public ReaderRecommendedBlogsCardUiState(@org.jetbrains.annotations.NotNull()
        java.util.List<org.wordpress.android.ui.reader.discover.ReaderCardUiState.ReaderRecommendedBlogsCardUiState.ReaderRecommendedBlogUiState> blogs) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.ui.reader.discover.ReaderCardUiState.ReaderRecommendedBlogsCardUiState.ReaderRecommendedBlogUiState> component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.ui.reader.discover.ReaderCardUiState.ReaderRecommendedBlogsCardUiState.ReaderRecommendedBlogUiState> getBlogs() {
            return null;
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001Bu\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u001e\u0010\f\u001a\u001a\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000e0\r\u0012\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u000e0\u0010\u00a2\u0006\u0002\u0010\u0011J\t\u0010%\u001a\u00020\u0003H\u00c6\u0003J\t\u0010&\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\'\u001a\u00020\u0006H\u00c6\u0003J\t\u0010(\u001a\u00020\u0006H\u00c6\u0003J\u000b\u0010)\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010*\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010+\u001a\u00020\u000bH\u00c6\u0003J!\u0010,\u001a\u001a\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000e0\rH\u00c6\u0003J\u0015\u0010-\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u000e0\u0010H\u00c6\u0003J\u008b\u0001\u0010.\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\n\u001a\u00020\u000b2 \b\u0002\u0010\f\u001a\u001a\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000e0\r2\u0014\b\u0002\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u000e0\u0010H\u00c6\u0001J\u0013\u0010/\u001a\u00020\u000b2\b\u00100\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u00101\u001a\u000202H\u00d6\u0001J\t\u00103\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0013R\u001b\u0010\u0017\u001a\u00020\u00188FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u0019\u0010\u001aR\u0013\u0010\t\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0015R\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u001eR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0015R\u001d\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u000e0\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010!R)\u0010\f\u001a\u001a\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000e0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0015\u00a8\u00064"}, d2 = {"Lorg/wordpress/android/ui/reader/discover/ReaderCardUiState$ReaderRecommendedBlogsCardUiState$ReaderRecommendedBlogUiState;", "", "name", "", "url", "blogId", "", "feedId", "description", "iconUrl", "isFollowed", "", "onItemClicked", "Lkotlin/Function3;", "", "onFollowClicked", "Lkotlin/Function1;", "(Ljava/lang/String;Ljava/lang/String;JJLjava/lang/String;Ljava/lang/String;ZLkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function1;)V", "getBlogId", "()J", "getDescription", "()Ljava/lang/String;", "getFeedId", "followContentDescription", "Lorg/wordpress/android/ui/utils/UiString$UiStringRes;", "getFollowContentDescription", "()Lorg/wordpress/android/ui/utils/UiString$UiStringRes;", "followContentDescription$delegate", "Lkotlin/Lazy;", "getIconUrl", "()Z", "getName", "getOnFollowClicked", "()Lkotlin/jvm/functions/Function1;", "getOnItemClicked", "()Lkotlin/jvm/functions/Function3;", "getUrl", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class ReaderRecommendedBlogUiState {
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String name = null;
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String url = null;
            private final long blogId = 0L;
            private final long feedId = 0L;
            @org.jetbrains.annotations.Nullable()
            private final java.lang.String description = null;
            @org.jetbrains.annotations.Nullable()
            private final java.lang.String iconUrl = null;
            private final boolean isFollowed = false;
            @org.jetbrains.annotations.NotNull()
            private final kotlin.jvm.functions.Function3<java.lang.Long, java.lang.Long, java.lang.Boolean, kotlin.Unit> onItemClicked = null;
            @org.jetbrains.annotations.NotNull()
            private final kotlin.jvm.functions.Function1<org.wordpress.android.ui.reader.discover.ReaderCardUiState.ReaderRecommendedBlogsCardUiState.ReaderRecommendedBlogUiState, kotlin.Unit> onFollowClicked = null;
            @org.jetbrains.annotations.NotNull()
            private final kotlin.Lazy followContentDescription$delegate = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.reader.discover.ReaderCardUiState.ReaderRecommendedBlogsCardUiState.ReaderRecommendedBlogUiState copy(@org.jetbrains.annotations.NotNull()
            java.lang.String name, @org.jetbrains.annotations.NotNull()
            java.lang.String url, long blogId, long feedId, @org.jetbrains.annotations.Nullable()
            java.lang.String description, @org.jetbrains.annotations.Nullable()
            java.lang.String iconUrl, boolean isFollowed, @org.jetbrains.annotations.NotNull()
            kotlin.jvm.functions.Function3<? super java.lang.Long, ? super java.lang.Long, ? super java.lang.Boolean, kotlin.Unit> onItemClicked, @org.jetbrains.annotations.NotNull()
            kotlin.jvm.functions.Function1<? super org.wordpress.android.ui.reader.discover.ReaderCardUiState.ReaderRecommendedBlogsCardUiState.ReaderRecommendedBlogUiState, kotlin.Unit> onFollowClicked) {
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
            
            public ReaderRecommendedBlogUiState(@org.jetbrains.annotations.NotNull()
            java.lang.String name, @org.jetbrains.annotations.NotNull()
            java.lang.String url, long blogId, long feedId, @org.jetbrains.annotations.Nullable()
            java.lang.String description, @org.jetbrains.annotations.Nullable()
            java.lang.String iconUrl, boolean isFollowed, @org.jetbrains.annotations.NotNull()
            kotlin.jvm.functions.Function3<? super java.lang.Long, ? super java.lang.Long, ? super java.lang.Boolean, kotlin.Unit> onItemClicked, @org.jetbrains.annotations.NotNull()
            kotlin.jvm.functions.Function1<? super org.wordpress.android.ui.reader.discover.ReaderCardUiState.ReaderRecommendedBlogsCardUiState.ReaderRecommendedBlogUiState, kotlin.Unit> onFollowClicked) {
                super();
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
            public final java.lang.String getUrl() {
                return null;
            }
            
            public final long component3() {
                return 0L;
            }
            
            public final long getBlogId() {
                return 0L;
            }
            
            public final long component4() {
                return 0L;
            }
            
            public final long getFeedId() {
                return 0L;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final java.lang.String component5() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final java.lang.String getDescription() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final java.lang.String component6() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final java.lang.String getIconUrl() {
                return null;
            }
            
            public final boolean component7() {
                return false;
            }
            
            public final boolean isFollowed() {
                return false;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final kotlin.jvm.functions.Function3<java.lang.Long, java.lang.Long, java.lang.Boolean, kotlin.Unit> component8() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final kotlin.jvm.functions.Function3<java.lang.Long, java.lang.Long, java.lang.Boolean, kotlin.Unit> getOnItemClicked() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final kotlin.jvm.functions.Function1<org.wordpress.android.ui.reader.discover.ReaderCardUiState.ReaderRecommendedBlogsCardUiState.ReaderRecommendedBlogUiState, kotlin.Unit> component9() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final kotlin.jvm.functions.Function1<org.wordpress.android.ui.reader.discover.ReaderCardUiState.ReaderRecommendedBlogsCardUiState.ReaderRecommendedBlogUiState, kotlin.Unit> getOnFollowClicked() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.utils.UiString.UiStringRes getFollowContentDescription() {
                return null;
            }
        }
    }
}