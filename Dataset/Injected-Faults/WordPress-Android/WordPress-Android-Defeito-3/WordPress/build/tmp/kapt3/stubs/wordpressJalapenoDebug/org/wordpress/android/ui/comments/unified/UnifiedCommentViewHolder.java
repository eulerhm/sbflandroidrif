package org.wordpress.android.ui.comments.unified;

import java.lang.System;

@kotlin.Suppress(names = {"LongParameterList"})
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B=\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u00a2\u0006\u0002\u0010\u0011J\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0015H\u0002J\u000e\u0010\u0019\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\u001bJ\u000e\u0010\u001c\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001d"}, d2 = {"Lorg/wordpress/android/ui/comments/unified/UnifiedCommentViewHolder;", "Lorg/wordpress/android/ui/comments/unified/UnifiedCommentListViewHolder;", "Lorg/wordpress/android/databinding/CommentListItemBinding;", "parent", "Landroid/view/ViewGroup;", "imageManager", "Lorg/wordpress/android/util/image/ImageManager;", "uiHelpers", "Lorg/wordpress/android/ui/utils/UiHelpers;", "commentListUiUtils", "Lorg/wordpress/android/ui/comments/unified/CommentListUiUtils;", "resourceProvider", "Lorg/wordpress/android/viewmodel/ResourceProvider;", "gravatarUtilsWrapper", "Lorg/wordpress/android/util/GravatarUtilsWrapper;", "animationUtilsWrapper", "Lorg/wordpress/android/ui/utils/AnimationUtilsWrapper;", "(Landroid/view/ViewGroup;Lorg/wordpress/android/util/image/ImageManager;Lorg/wordpress/android/ui/utils/UiHelpers;Lorg/wordpress/android/ui/comments/unified/CommentListUiUtils;Lorg/wordpress/android/viewmodel/ResourceProvider;Lorg/wordpress/android/util/GravatarUtilsWrapper;Lorg/wordpress/android/ui/utils/AnimationUtilsWrapper;)V", "bind", "", "item", "Lorg/wordpress/android/ui/comments/unified/UnifiedCommentListItem$Comment;", "getGravatarUrl", "", "comment", "toggleSelected", "isSelected", "", "updateStateAndListeners", "org.wordpress.android_wordpressJalapenoDebug"})
public final class UnifiedCommentViewHolder extends org.wordpress.android.ui.comments.unified.UnifiedCommentListViewHolder<org.wordpress.android.databinding.CommentListItemBinding> {
    private final org.wordpress.android.util.image.ImageManager imageManager = null;
    private final org.wordpress.android.ui.utils.UiHelpers uiHelpers = null;
    private final org.wordpress.android.ui.comments.unified.CommentListUiUtils commentListUiUtils = null;
    private final org.wordpress.android.viewmodel.ResourceProvider resourceProvider = null;
    private final org.wordpress.android.util.GravatarUtilsWrapper gravatarUtilsWrapper = null;
    private final org.wordpress.android.ui.utils.AnimationUtilsWrapper animationUtilsWrapper = null;
    
    public UnifiedCommentViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.image.ImageManager imageManager, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.utils.UiHelpers uiHelpers, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.comments.unified.CommentListUiUtils commentListUiUtils, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.ResourceProvider resourceProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.GravatarUtilsWrapper gravatarUtilsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.utils.AnimationUtilsWrapper animationUtilsWrapper) {
        super(null);
    }
    
    public final void bind(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.comments.unified.UnifiedCommentListItem.Comment item) {
    }
    
    public final void toggleSelected(boolean isSelected) {
    }
    
    public final void updateStateAndListeners(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.comments.unified.UnifiedCommentListItem.Comment item) {
    }
    
    private final java.lang.String getGravatarUrl(org.wordpress.android.ui.comments.unified.UnifiedCommentListItem.Comment comment) {
        return null;
    }
}