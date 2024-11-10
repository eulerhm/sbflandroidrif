package org.wordpress.android.ui.reader.views.uistates;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b \n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001:\u00014B]\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011\u00a2\u0006\u0002\u0010\u0012J\t\u0010$\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010%\u001a\u0004\u0018\u00010\u0011H\u00c6\u0003J\t\u0010&\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\'\u001a\u00020\u0006H\u00c6\u0003J\t\u0010(\u001a\u00020\bH\u00c6\u0003J\u000b\u0010)\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003J\u000b\u0010*\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003J\u000b\u0010+\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003J\t\u0010,\u001a\u00020\rH\u00c6\u0003J\t\u0010-\u001a\u00020\u000fH\u00c6\u0003Ju\u0010.\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u00c6\u0001J\u0013\u0010/\u001a\u00020\r2\b\u00100\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u00101\u001a\u000202H\u00d6\u0001J\t\u00103\u001a\u00020\u0006H\u00d6\u0001R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0011\u0010\u000e\u001a\u00020\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0013\u0010\t\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0014R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0014R\u0011\u0010 \u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0011\u0010\f\u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\"R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0019\u00a8\u00065"}, d2 = {"Lorg/wordpress/android/ui/reader/views/uistates/ReaderBlogSectionUiState;", "", "postId", "", "blogId", "dateLine", "", "blogName", "Lorg/wordpress/android/ui/utils/UiString;", "blogUrl", "avatarOrBlavatarUrl", "authorAvatarUrl", "isAuthorAvatarVisible", "", "blavatarType", "Lorg/wordpress/android/util/image/ImageType;", "blogSectionClickData", "Lorg/wordpress/android/ui/reader/views/uistates/ReaderBlogSectionUiState$ReaderBlogSectionClickData;", "(JJLjava/lang/String;Lorg/wordpress/android/ui/utils/UiString;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLorg/wordpress/android/util/image/ImageType;Lorg/wordpress/android/ui/reader/views/uistates/ReaderBlogSectionUiState$ReaderBlogSectionClickData;)V", "getAuthorAvatarUrl", "()Ljava/lang/String;", "getAvatarOrBlavatarUrl", "getBlavatarType", "()Lorg/wordpress/android/util/image/ImageType;", "getBlogId", "()J", "getBlogName", "()Lorg/wordpress/android/ui/utils/UiString;", "getBlogSectionClickData", "()Lorg/wordpress/android/ui/reader/views/uistates/ReaderBlogSectionUiState$ReaderBlogSectionClickData;", "getBlogUrl", "getDateLine", "dotSeparatorVisibility", "getDotSeparatorVisibility", "()Z", "getPostId", "component1", "component10", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "", "toString", "ReaderBlogSectionClickData", "org.wordpress.android_wordpressJalapenoDebug"})
public final class ReaderBlogSectionUiState {
    private final long postId = 0L;
    private final long blogId = 0L;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String dateLine = null;
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.ui.utils.UiString blogName = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String blogUrl = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String avatarOrBlavatarUrl = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String authorAvatarUrl = null;
    private final boolean isAuthorAvatarVisible = false;
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.util.image.ImageType blavatarType = null;
    @org.jetbrains.annotations.Nullable()
    private final org.wordpress.android.ui.reader.views.uistates.ReaderBlogSectionUiState.ReaderBlogSectionClickData blogSectionClickData = null;
    private final boolean dotSeparatorVisibility = false;
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.reader.views.uistates.ReaderBlogSectionUiState copy(long postId, long blogId, @org.jetbrains.annotations.NotNull()
    java.lang.String dateLine, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.utils.UiString blogName, @org.jetbrains.annotations.Nullable()
    java.lang.String blogUrl, @org.jetbrains.annotations.Nullable()
    java.lang.String avatarOrBlavatarUrl, @org.jetbrains.annotations.Nullable()
    java.lang.String authorAvatarUrl, boolean isAuthorAvatarVisible, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.image.ImageType blavatarType, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.ui.reader.views.uistates.ReaderBlogSectionUiState.ReaderBlogSectionClickData blogSectionClickData) {
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
    
    public ReaderBlogSectionUiState(long postId, long blogId, @org.jetbrains.annotations.NotNull()
    java.lang.String dateLine, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.utils.UiString blogName, @org.jetbrains.annotations.Nullable()
    java.lang.String blogUrl, @org.jetbrains.annotations.Nullable()
    java.lang.String avatarOrBlavatarUrl, @org.jetbrains.annotations.Nullable()
    java.lang.String authorAvatarUrl, boolean isAuthorAvatarVisible, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.image.ImageType blavatarType, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.ui.reader.views.uistates.ReaderBlogSectionUiState.ReaderBlogSectionClickData blogSectionClickData) {
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
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDateLine() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.utils.UiString component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.utils.UiString getBlogName() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getBlogUrl() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getAvatarOrBlavatarUrl() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component7() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getAuthorAvatarUrl() {
        return null;
    }
    
    public final boolean component8() {
        return false;
    }
    
    public final boolean isAuthorAvatarVisible() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.util.image.ImageType component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.util.image.ImageType getBlavatarType() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final org.wordpress.android.ui.reader.views.uistates.ReaderBlogSectionUiState.ReaderBlogSectionClickData component10() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final org.wordpress.android.ui.reader.views.uistates.ReaderBlogSectionUiState.ReaderBlogSectionClickData getBlogSectionClickData() {
        return null;
    }
    
    public final boolean getDotSeparatorVisibility() {
        return false;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B+\u0012\u001a\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0003\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u001d\u0010\r\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0003H\u00c6\u0003J\t\u0010\u000e\u001a\u00020\u0007H\u00c6\u0003J1\u0010\u000f\u001a\u00020\u00002\u001c\b\u0002\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00032\b\b\u0003\u0010\u0006\u001a\u00020\u0007H\u00c6\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0013\u001a\u00020\u0007H\u00d6\u0001J\t\u0010\u0014\u001a\u00020\u0015H\u00d6\u0001R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR%\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f\u00a8\u0006\u0016"}, d2 = {"Lorg/wordpress/android/ui/reader/views/uistates/ReaderBlogSectionUiState$ReaderBlogSectionClickData;", "", "onBlogSectionClicked", "Lkotlin/Function2;", "", "", "background", "", "(Lkotlin/jvm/functions/Function2;I)V", "getBackground", "()I", "getOnBlogSectionClicked", "()Lkotlin/jvm/functions/Function2;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class ReaderBlogSectionClickData {
        @org.jetbrains.annotations.Nullable()
        private final kotlin.jvm.functions.Function2<java.lang.Long, java.lang.Long, kotlin.Unit> onBlogSectionClicked = null;
        private final int background = 0;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.reader.views.uistates.ReaderBlogSectionUiState.ReaderBlogSectionClickData copy(@org.jetbrains.annotations.Nullable()
        kotlin.jvm.functions.Function2<? super java.lang.Long, ? super java.lang.Long, kotlin.Unit> onBlogSectionClicked, @androidx.annotation.AttrRes()
        int background) {
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
        
        public ReaderBlogSectionClickData(@org.jetbrains.annotations.Nullable()
        kotlin.jvm.functions.Function2<? super java.lang.Long, ? super java.lang.Long, kotlin.Unit> onBlogSectionClicked, @androidx.annotation.AttrRes()
        int background) {
            super();
        }
        
        @org.jetbrains.annotations.Nullable()
        public final kotlin.jvm.functions.Function2<java.lang.Long, java.lang.Long, kotlin.Unit> component1() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final kotlin.jvm.functions.Function2<java.lang.Long, java.lang.Long, kotlin.Unit> getOnBlogSectionClicked() {
            return null;
        }
        
        public final int component2() {
            return 0;
        }
        
        public final int getBackground() {
            return 0;
        }
    }
}