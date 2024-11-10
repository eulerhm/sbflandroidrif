package org.wordpress.android.ui.comments.unified;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 82\u0012\u0012\u0004\u0012\u00020\u0002\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00030\u0001:\u00018B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0010\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020,H\u0016J\u001c\u0010.\u001a\u00020/2\n\u00100\u001a\u0006\u0012\u0002\b\u00030\u00032\u0006\u0010-\u001a\u00020,H\u0016J*\u0010.\u001a\u00020/2\n\u00100\u001a\u0006\u0012\u0002\b\u00030\u00032\u0006\u0010-\u001a\u00020,2\f\u00101\u001a\b\u0012\u0004\u0012\u00020302H\u0016J\u001c\u00104\u001a\u0006\u0012\u0002\b\u00030\u00032\u0006\u00105\u001a\u0002062\u0006\u00107\u001a\u00020,H\u0016R\u001e\u0010\u0007\u001a\u00020\b8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001e\u0010\r\u001a\u00020\u000e8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001e\u0010\u0013\u001a\u00020\u00148\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001e\u0010\u0019\u001a\u00020\u001a8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001e\u0010\u001f\u001a\u00020 8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u001e\u0010%\u001a\u00020&8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\'\u0010(\"\u0004\b)\u0010*\u00a8\u00069"}, d2 = {"Lorg/wordpress/android/ui/comments/unified/UnifiedCommentListAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lorg/wordpress/android/ui/comments/unified/UnifiedCommentListItem;", "Lorg/wordpress/android/ui/comments/unified/UnifiedCommentListViewHolder;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "animationUtilsWrapper", "Lorg/wordpress/android/ui/utils/AnimationUtilsWrapper;", "getAnimationUtilsWrapper", "()Lorg/wordpress/android/ui/utils/AnimationUtilsWrapper;", "setAnimationUtilsWrapper", "(Lorg/wordpress/android/ui/utils/AnimationUtilsWrapper;)V", "commentListUiUtils", "Lorg/wordpress/android/ui/comments/unified/CommentListUiUtils;", "getCommentListUiUtils", "()Lorg/wordpress/android/ui/comments/unified/CommentListUiUtils;", "setCommentListUiUtils", "(Lorg/wordpress/android/ui/comments/unified/CommentListUiUtils;)V", "gravatarUtilsWrapper", "Lorg/wordpress/android/util/GravatarUtilsWrapper;", "getGravatarUtilsWrapper", "()Lorg/wordpress/android/util/GravatarUtilsWrapper;", "setGravatarUtilsWrapper", "(Lorg/wordpress/android/util/GravatarUtilsWrapper;)V", "imageManager", "Lorg/wordpress/android/util/image/ImageManager;", "getImageManager", "()Lorg/wordpress/android/util/image/ImageManager;", "setImageManager", "(Lorg/wordpress/android/util/image/ImageManager;)V", "resourceProvider", "Lorg/wordpress/android/viewmodel/ResourceProvider;", "getResourceProvider", "()Lorg/wordpress/android/viewmodel/ResourceProvider;", "setResourceProvider", "(Lorg/wordpress/android/viewmodel/ResourceProvider;)V", "uiHelpers", "Lorg/wordpress/android/ui/utils/UiHelpers;", "getUiHelpers", "()Lorg/wordpress/android/ui/utils/UiHelpers;", "setUiHelpers", "(Lorg/wordpress/android/ui/utils/UiHelpers;)V", "getItemViewType", "", "position", "onBindViewHolder", "", "holder", "payloads", "", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public final class UnifiedCommentListAdapter extends androidx.recyclerview.widget.ListAdapter<org.wordpress.android.ui.comments.unified.UnifiedCommentListItem, org.wordpress.android.ui.comments.unified.UnifiedCommentListViewHolder<?>> {
    @javax.inject.Inject()
    public org.wordpress.android.util.image.ImageManager imageManager;
    @javax.inject.Inject()
    public org.wordpress.android.ui.utils.UiHelpers uiHelpers;
    @javax.inject.Inject()
    public org.wordpress.android.ui.comments.unified.CommentListUiUtils commentListUiUtils;
    @javax.inject.Inject()
    public org.wordpress.android.viewmodel.ResourceProvider resourceProvider;
    @javax.inject.Inject()
    public org.wordpress.android.util.GravatarUtilsWrapper gravatarUtilsWrapper;
    @javax.inject.Inject()
    public org.wordpress.android.ui.utils.AnimationUtilsWrapper animationUtilsWrapper;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.comments.unified.UnifiedCommentListAdapter.Companion Companion = null;
    private static final org.wordpress.android.ui.comments.unified.UnifiedCommentsListDiffCallback diffCallback = null;
    
    public UnifiedCommentListAdapter(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.util.image.ImageManager getImageManager() {
        return null;
    }
    
    public final void setImageManager(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.image.ImageManager p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.utils.UiHelpers getUiHelpers() {
        return null;
    }
    
    public final void setUiHelpers(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.utils.UiHelpers p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.comments.unified.CommentListUiUtils getCommentListUiUtils() {
        return null;
    }
    
    public final void setCommentListUiUtils(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.comments.unified.CommentListUiUtils p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.viewmodel.ResourceProvider getResourceProvider() {
        return null;
    }
    
    public final void setResourceProvider(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.ResourceProvider p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.util.GravatarUtilsWrapper getGravatarUtilsWrapper() {
        return null;
    }
    
    public final void setGravatarUtilsWrapper(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.GravatarUtilsWrapper p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.utils.AnimationUtilsWrapper getAnimationUtilsWrapper() {
        return null;
    }
    
    public final void setAnimationUtilsWrapper(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.utils.AnimationUtilsWrapper p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public org.wordpress.android.ui.comments.unified.UnifiedCommentListViewHolder<?> onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.comments.unified.UnifiedCommentListViewHolder<?> holder, int position, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.Object> payloads) {
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.comments.unified.UnifiedCommentListViewHolder<?> holder, int position) {
    }
    
    @java.lang.Override()
    public int getItemViewType(int position) {
        return 0;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lorg/wordpress/android/ui/comments/unified/UnifiedCommentListAdapter$Companion;", "", "()V", "diffCallback", "Lorg/wordpress/android/ui/comments/unified/UnifiedCommentsListDiffCallback;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}