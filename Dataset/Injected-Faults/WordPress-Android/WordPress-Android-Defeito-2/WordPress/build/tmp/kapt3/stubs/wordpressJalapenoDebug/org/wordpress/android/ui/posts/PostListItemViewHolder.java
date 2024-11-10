package org.wordpress.android.ui.posts;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0098\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 82\u00020\u0001:\u0003789B)\b\u0004\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH&J\u001e\u0010\u001f\u001a\u00020\u001c2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\"0!2\u0006\u0010#\u001a\u00020$H\u0004J\u0010\u0010%\u001a\u00020\u001c2\u0006\u0010&\u001a\u00020\'H\u0004J\u0012\u0010(\u001a\u00020\u001c2\b\u0010)\u001a\u0004\u0018\u00010\u0012H\u0002J \u0010*\u001a\u00020\u001c2\u0006\u0010+\u001a\u00020,2\u000e\u0010-\u001a\n\u0012\u0004\u0012\u00020.\u0018\u00010!H\u0002J\u0010\u0010/\u001a\u00020\u001c2\u0006\u00100\u001a\u000201H\u0002J7\u00102\u001a\u00020\u001c2\u0006\u0010+\u001a\u00020\u00142\f\u00103\u001a\b\u0012\u0004\u0012\u00020.0!2\u0006\u00104\u001a\u00020.2\n\b\u0001\u00105\u001a\u0004\u0018\u00010\u0003H\u0002\u00a2\u0006\u0002\u00106R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0001\u0002:;\u00a8\u0006<"}, d2 = {"Lorg/wordpress/android/ui/posts/PostListItemViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "layout", "", "parent", "Landroid/view/ViewGroup;", "imageManager", "Lorg/wordpress/android/util/image/ImageManager;", "uiHelpers", "Lorg/wordpress/android/ui/utils/UiHelpers;", "(ILandroid/view/ViewGroup;Lorg/wordpress/android/util/image/ImageManager;Lorg/wordpress/android/ui/utils/UiHelpers;)V", "container", "Landroidx/constraintlayout/widget/ConstraintLayout;", "disabledOverlay", "Landroid/widget/FrameLayout;", "featuredImageView", "Landroid/widget/ImageView;", "loadedFeaturedImgUrl", "", "postInfoTextView", "Lorg/wordpress/android/widgets/WPTextView;", "selectableBackground", "Landroid/graphics/drawable/Drawable;", "statusesTextView", "titleTextView", "uploadProgressBar", "Landroid/widget/ProgressBar;", "onBind", "", "item", "Lorg/wordpress/android/viewmodel/posts/PostListItemType$PostListItemUiState;", "onMoreClicked", "actions", "", "Lorg/wordpress/android/viewmodel/posts/PostListItemAction;", "v", "Landroid/view/View;", "setBasicValues", "data", "Lorg/wordpress/android/viewmodel/posts/PostListItemUiStateData;", "showFeaturedImage", "imageUrl", "updatePostInfoLabel", "view", "Landroid/widget/TextView;", "uiStrings", "Lorg/wordpress/android/ui/utils/UiString;", "updateProgressBarState", "progressBarUiState", "Lorg/wordpress/android/viewmodel/uistate/ProgressBarUiState;", "updateStatusesLabel", "statuses", "delimiter", "color", "(Lorg/wordpress/android/widgets/WPTextView;Ljava/util/List;Lorg/wordpress/android/ui/utils/UiString;Ljava/lang/Integer;)V", "Compact", "Companion", "Standard", "Lorg/wordpress/android/ui/posts/PostListItemViewHolder$Standard;", "Lorg/wordpress/android/ui/posts/PostListItemViewHolder$Compact;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class PostListItemViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
    private final org.wordpress.android.util.image.ImageManager imageManager = null;
    private final org.wordpress.android.ui.utils.UiHelpers uiHelpers = null;
    private final android.widget.ImageView featuredImageView = null;
    private final org.wordpress.android.widgets.WPTextView titleTextView = null;
    private final org.wordpress.android.widgets.WPTextView postInfoTextView = null;
    private final org.wordpress.android.widgets.WPTextView statusesTextView = null;
    private final android.widget.ProgressBar uploadProgressBar = null;
    private final android.widget.FrameLayout disabledOverlay = null;
    private final androidx.constraintlayout.widget.ConstraintLayout container = null;
    private final android.graphics.drawable.Drawable selectableBackground = null;
    
    /**
     * Url of an image loaded in the `featuredImageView`.
     */
    private java.lang.String loadedFeaturedImgUrl;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.posts.PostListItemViewHolder.Companion Companion = null;
    @org.jetbrains.annotations.NotNull()
    private static java.util.concurrent.atomic.AtomicBoolean isClickEnabled;
    
    private PostListItemViewHolder(@androidx.annotation.LayoutRes()
    int layout, android.view.ViewGroup parent, org.wordpress.android.util.image.ImageManager imageManager, org.wordpress.android.ui.utils.UiHelpers uiHelpers) {
        super(null);
    }
    
    public abstract void onBind(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.posts.PostListItemType.PostListItemUiState item);
    
    protected final void setBasicValues(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.posts.PostListItemUiStateData data) {
    }
    
    private final void updatePostInfoLabel(android.widget.TextView view, java.util.List<? extends org.wordpress.android.ui.utils.UiString> uiStrings) {
    }
    
    protected final void onMoreClicked(@org.jetbrains.annotations.NotNull()
    java.util.List<? extends org.wordpress.android.viewmodel.posts.PostListItemAction> actions, @org.jetbrains.annotations.NotNull()
    android.view.View v) {
    }
    
    private final void updateProgressBarState(org.wordpress.android.viewmodel.uistate.ProgressBarUiState progressBarUiState) {
    }
    
    private final void updateStatusesLabel(org.wordpress.android.widgets.WPTextView view, java.util.List<? extends org.wordpress.android.ui.utils.UiString> statuses, org.wordpress.android.ui.utils.UiString delimiter, @androidx.annotation.ColorRes()
    java.lang.Integer color) {
    }
    
    private final void showFeaturedImage(java.lang.String imageUrl) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u001a\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0002R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"}, d2 = {"Lorg/wordpress/android/ui/posts/PostListItemViewHolder$Standard;", "Lorg/wordpress/android/ui/posts/PostListItemViewHolder;", "parent", "Landroid/view/ViewGroup;", "imageManager", "Lorg/wordpress/android/util/image/ImageManager;", "uiHelpers", "Lorg/wordpress/android/ui/utils/UiHelpers;", "(Landroid/view/ViewGroup;Lorg/wordpress/android/util/image/ImageManager;Lorg/wordpress/android/ui/utils/UiHelpers;)V", "actionButtons", "", "Lorg/wordpress/android/widgets/PostListButton;", "excerptTextView", "Lorg/wordpress/android/widgets/WPTextView;", "isSafeClick", "", "view", "Landroid/view/View;", "onBind", "", "item", "Lorg/wordpress/android/viewmodel/posts/PostListItemType$PostListItemUiState;", "updateMenuItem", "postListButton", "action", "Lorg/wordpress/android/viewmodel/posts/PostListItemAction;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Standard extends org.wordpress.android.ui.posts.PostListItemViewHolder {
        private final org.wordpress.android.ui.utils.UiHelpers uiHelpers = null;
        private final org.wordpress.android.widgets.WPTextView excerptTextView = null;
        private final java.util.List<org.wordpress.android.widgets.PostListButton> actionButtons = null;
        
        public Standard(@org.jetbrains.annotations.NotNull()
        android.view.ViewGroup parent, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.util.image.ImageManager imageManager, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiHelpers uiHelpers) {
            super(0, null, null, null);
        }
        
        @java.lang.Override()
        public void onBind(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.viewmodel.posts.PostListItemType.PostListItemUiState item) {
        }
        
        private final void updateMenuItem(org.wordpress.android.widgets.PostListButton postListButton, org.wordpress.android.viewmodel.posts.PostListItemAction action) {
        }
        
        private final boolean isSafeClick(android.view.View view) {
            return false;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lorg/wordpress/android/ui/posts/PostListItemViewHolder$Compact;", "Lorg/wordpress/android/ui/posts/PostListItemViewHolder;", "parent", "Landroid/view/ViewGroup;", "imageManager", "Lorg/wordpress/android/util/image/ImageManager;", "uiHelpers", "Lorg/wordpress/android/ui/utils/UiHelpers;", "(Landroid/view/ViewGroup;Lorg/wordpress/android/util/image/ImageManager;Lorg/wordpress/android/ui/utils/UiHelpers;)V", "moreButton", "Landroid/widget/ImageButton;", "onBind", "", "item", "Lorg/wordpress/android/viewmodel/posts/PostListItemType$PostListItemUiState;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Compact extends org.wordpress.android.ui.posts.PostListItemViewHolder {
        private final org.wordpress.android.ui.utils.UiHelpers uiHelpers = null;
        private final android.widget.ImageButton moreButton = null;
        
        public Compact(@org.jetbrains.annotations.NotNull()
        android.view.ViewGroup parent, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.util.image.ImageManager imageManager, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiHelpers uiHelpers) {
            super(0, null, null, null);
        }
        
        @java.lang.Override()
        public void onBind(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.viewmodel.posts.PostListItemType.PostListItemUiState item) {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0003\u0010\u0005\"\u0004\b\u0006\u0010\u0007\u00a8\u0006\b"}, d2 = {"Lorg/wordpress/android/ui/posts/PostListItemViewHolder$Companion;", "", "()V", "isClickEnabled", "Ljava/util/concurrent/atomic/AtomicBoolean;", "()Ljava/util/concurrent/atomic/AtomicBoolean;", "setClickEnabled", "(Ljava/util/concurrent/atomic/AtomicBoolean;)V", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.concurrent.atomic.AtomicBoolean isClickEnabled() {
            return null;
        }
        
        public final void setClickEnabled(@org.jetbrains.annotations.NotNull()
        java.util.concurrent.atomic.AtomicBoolean p0) {
        }
    }
}