package org.wordpress.android.viewmodel.posts;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b*\b\u0086\b\u0018\u00002\u00020\u0001B}\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u0012\u000e\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\f\u0012\n\b\u0001\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00070\f\u0012\u0006\u0010\u0010\u001a\u00020\u0007\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\u0006\u0010\u0013\u001a\u00020\u0014\u0012\u0006\u0010\u0015\u001a\u00020\u0014\u00a2\u0006\u0002\u0010\u0016J\t\u0010,\u001a\u00020\u0003H\u00c6\u0003J\t\u0010-\u001a\u00020\u0012H\u00c6\u0003J\t\u0010.\u001a\u00020\u0014H\u00c6\u0003J\t\u0010/\u001a\u00020\u0014H\u00c6\u0003J\t\u00100\u001a\u00020\u0005H\u00c6\u0003J\u000b\u00101\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003J\u000b\u00102\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003J\u000b\u00103\u001a\u0004\u0018\u00010\nH\u00c6\u0003J\u0011\u00104\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\fH\u00c6\u0003J\u0010\u00105\u001a\u0004\u0018\u00010\u000eH\u00c6\u0003\u00a2\u0006\u0002\u0010(J\u000f\u00106\u001a\b\u0012\u0004\u0012\u00020\u00070\fH\u00c6\u0003J\t\u00107\u001a\u00020\u0007H\u00c6\u0003J\u009c\u0001\u00108\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\f2\n\b\u0003\u0010\r\u001a\u0004\u0018\u00010\u000e2\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00070\f2\b\b\u0002\u0010\u0010\u001a\u00020\u00072\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u0014H\u00c6\u0001\u00a2\u0006\u0002\u00109J\u0013\u0010:\u001a\u00020\u00142\b\u0010;\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010<\u001a\u00020\u000eH\u00d6\u0001J\t\u0010=\u001a\u00020\nH\u00d6\u0001R\u0011\u0010\u0015\u001a\u00020\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0013\u0010\t\u001a\u0004\u0018\u00010\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0019\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010\u0011\u001a\u00020\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0011\u0010\u0013\u001a\u00020\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0018R\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00070\f\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010 R\u0015\u0010\r\u001a\u0004\u0018\u00010\u000e\u00a2\u0006\n\n\u0002\u0010)\u001a\u0004\b\'\u0010(R\u0011\u0010\u0010\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010\u001aR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b+\u0010\u001a\u00a8\u0006>"}, d2 = {"Lorg/wordpress/android/viewmodel/posts/PostListItemUiStateData;", "", "remotePostId", "Lorg/wordpress/android/viewmodel/posts/PostListItemIdentifier$RemotePostId;", "localPostId", "Lorg/wordpress/android/viewmodel/posts/PostListItemIdentifier$LocalPostId;", "title", "Lorg/wordpress/android/ui/utils/UiString;", "excerpt", "imageUrl", "", "postInfo", "", "statusesColor", "", "statuses", "statusesDelimiter", "progressBarUiState", "Lorg/wordpress/android/viewmodel/uistate/ProgressBarUiState;", "showOverlay", "", "disableRippleEffect", "(Lorg/wordpress/android/viewmodel/posts/PostListItemIdentifier$RemotePostId;Lorg/wordpress/android/viewmodel/posts/PostListItemIdentifier$LocalPostId;Lorg/wordpress/android/ui/utils/UiString;Lorg/wordpress/android/ui/utils/UiString;Ljava/lang/String;Ljava/util/List;Ljava/lang/Integer;Ljava/util/List;Lorg/wordpress/android/ui/utils/UiString;Lorg/wordpress/android/viewmodel/uistate/ProgressBarUiState;ZZ)V", "getDisableRippleEffect", "()Z", "getExcerpt", "()Lorg/wordpress/android/ui/utils/UiString;", "getImageUrl", "()Ljava/lang/String;", "getLocalPostId", "()Lorg/wordpress/android/viewmodel/posts/PostListItemIdentifier$LocalPostId;", "getPostInfo", "()Ljava/util/List;", "getProgressBarUiState", "()Lorg/wordpress/android/viewmodel/uistate/ProgressBarUiState;", "getRemotePostId", "()Lorg/wordpress/android/viewmodel/posts/PostListItemIdentifier$RemotePostId;", "getShowOverlay", "getStatuses", "getStatusesColor", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getStatusesDelimiter", "getTitle", "component1", "component10", "component11", "component12", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Lorg/wordpress/android/viewmodel/posts/PostListItemIdentifier$RemotePostId;Lorg/wordpress/android/viewmodel/posts/PostListItemIdentifier$LocalPostId;Lorg/wordpress/android/ui/utils/UiString;Lorg/wordpress/android/ui/utils/UiString;Ljava/lang/String;Ljava/util/List;Ljava/lang/Integer;Ljava/util/List;Lorg/wordpress/android/ui/utils/UiString;Lorg/wordpress/android/viewmodel/uistate/ProgressBarUiState;ZZ)Lorg/wordpress/android/viewmodel/posts/PostListItemUiStateData;", "equals", "other", "hashCode", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
public final class PostListItemUiStateData {
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.viewmodel.posts.PostListItemIdentifier.RemotePostId remotePostId = null;
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.viewmodel.posts.PostListItemIdentifier.LocalPostId localPostId = null;
    @org.jetbrains.annotations.Nullable()
    private final org.wordpress.android.ui.utils.UiString title = null;
    @org.jetbrains.annotations.Nullable()
    private final org.wordpress.android.ui.utils.UiString excerpt = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String imageUrl = null;
    @org.jetbrains.annotations.Nullable()
    private final java.util.List<org.wordpress.android.ui.utils.UiString> postInfo = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Integer statusesColor = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<org.wordpress.android.ui.utils.UiString> statuses = null;
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.ui.utils.UiString statusesDelimiter = null;
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.viewmodel.uistate.ProgressBarUiState progressBarUiState = null;
    private final boolean showOverlay = false;
    private final boolean disableRippleEffect = false;
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.viewmodel.posts.PostListItemUiStateData copy(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.posts.PostListItemIdentifier.RemotePostId remotePostId, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.posts.PostListItemIdentifier.LocalPostId localPostId, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.ui.utils.UiString title, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.ui.utils.UiString excerpt, @org.jetbrains.annotations.Nullable()
    java.lang.String imageUrl, @org.jetbrains.annotations.Nullable()
    java.util.List<? extends org.wordpress.android.ui.utils.UiString> postInfo, @org.jetbrains.annotations.Nullable()
    @androidx.annotation.ColorRes()
    java.lang.Integer statusesColor, @org.jetbrains.annotations.NotNull()
    java.util.List<? extends org.wordpress.android.ui.utils.UiString> statuses, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.utils.UiString statusesDelimiter, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.uistate.ProgressBarUiState progressBarUiState, boolean showOverlay, boolean disableRippleEffect) {
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
    
    public PostListItemUiStateData(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.posts.PostListItemIdentifier.RemotePostId remotePostId, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.posts.PostListItemIdentifier.LocalPostId localPostId, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.ui.utils.UiString title, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.ui.utils.UiString excerpt, @org.jetbrains.annotations.Nullable()
    java.lang.String imageUrl, @org.jetbrains.annotations.Nullable()
    java.util.List<? extends org.wordpress.android.ui.utils.UiString> postInfo, @org.jetbrains.annotations.Nullable()
    @androidx.annotation.ColorRes()
    java.lang.Integer statusesColor, @org.jetbrains.annotations.NotNull()
    java.util.List<? extends org.wordpress.android.ui.utils.UiString> statuses, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.utils.UiString statusesDelimiter, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.uistate.ProgressBarUiState progressBarUiState, boolean showOverlay, boolean disableRippleEffect) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.viewmodel.posts.PostListItemIdentifier.RemotePostId component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.viewmodel.posts.PostListItemIdentifier.RemotePostId getRemotePostId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.viewmodel.posts.PostListItemIdentifier.LocalPostId component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.viewmodel.posts.PostListItemIdentifier.LocalPostId getLocalPostId() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final org.wordpress.android.ui.utils.UiString component3() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final org.wordpress.android.ui.utils.UiString getTitle() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final org.wordpress.android.ui.utils.UiString component4() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final org.wordpress.android.ui.utils.UiString getExcerpt() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getImageUrl() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.List<org.wordpress.android.ui.utils.UiString> component6() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.List<org.wordpress.android.ui.utils.UiString> getPostInfo() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer component7() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getStatusesColor() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<org.wordpress.android.ui.utils.UiString> component8() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<org.wordpress.android.ui.utils.UiString> getStatuses() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.utils.UiString component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.utils.UiString getStatusesDelimiter() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.viewmodel.uistate.ProgressBarUiState component10() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.viewmodel.uistate.ProgressBarUiState getProgressBarUiState() {
        return null;
    }
    
    public final boolean component11() {
        return false;
    }
    
    public final boolean getShowOverlay() {
        return false;
    }
    
    public final boolean component12() {
        return false;
    }
    
    public final boolean getDisableRippleEffect() {
        return false;
    }
}