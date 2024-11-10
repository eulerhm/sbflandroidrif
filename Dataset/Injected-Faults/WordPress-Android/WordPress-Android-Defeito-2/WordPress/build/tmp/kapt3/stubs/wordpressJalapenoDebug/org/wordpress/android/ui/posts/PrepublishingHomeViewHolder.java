package org.wordpress.android.ui.posts;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\r\u000e\u000fB\u0019\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH&R\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b\u0082\u0001\u0003\u0010\u0011\u0012\u00a8\u0006\u0013"}, d2 = {"Lorg/wordpress/android/ui/posts/PrepublishingHomeViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "parent", "Landroid/view/ViewGroup;", "layout", "", "(Landroid/view/ViewGroup;I)V", "getParent$org_wordpress_android_wordpressJalapenoDebug", "()Landroid/view/ViewGroup;", "onBind", "", "uiState", "Lorg/wordpress/android/ui/posts/PrepublishingHomeItemUiState;", "PrepublishingHeaderListItemViewHolder", "PrepublishingHomeListItemViewHolder", "PrepublishingSubmitButtonViewHolder", "Lorg/wordpress/android/ui/posts/PrepublishingHomeViewHolder$PrepublishingHomeListItemViewHolder;", "Lorg/wordpress/android/ui/posts/PrepublishingHomeViewHolder$PrepublishingHeaderListItemViewHolder;", "Lorg/wordpress/android/ui/posts/PrepublishingHomeViewHolder$PrepublishingSubmitButtonViewHolder;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class PrepublishingHomeViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
    @org.jetbrains.annotations.NotNull()
    private final android.view.ViewGroup parent = null;
    
    private PrepublishingHomeViewHolder(android.view.ViewGroup parent, @androidx.annotation.LayoutRes()
    int layout) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.view.ViewGroup getParent$org_wordpress_android_wordpressJalapenoDebug() {
        return null;
    }
    
    public abstract void onBind(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.PrepublishingHomeItemUiState uiState);
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r\u00a8\u0006\u0012"}, d2 = {"Lorg/wordpress/android/ui/posts/PrepublishingHomeViewHolder$PrepublishingHomeListItemViewHolder;", "Lorg/wordpress/android/ui/posts/PrepublishingHomeViewHolder;", "parentView", "Landroid/view/ViewGroup;", "uiHelpers", "Lorg/wordpress/android/ui/utils/UiHelpers;", "(Landroid/view/ViewGroup;Lorg/wordpress/android/ui/utils/UiHelpers;)V", "actionLayout", "Landroid/view/View;", "actionResult", "Landroid/widget/TextView;", "actionType", "getUiHelpers", "()Lorg/wordpress/android/ui/utils/UiHelpers;", "onBind", "", "uiState", "Lorg/wordpress/android/ui/posts/PrepublishingHomeItemUiState;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class PrepublishingHomeListItemViewHolder extends org.wordpress.android.ui.posts.PrepublishingHomeViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.utils.UiHelpers uiHelpers = null;
        private final android.widget.TextView actionType = null;
        private final android.widget.TextView actionResult = null;
        private final android.view.View actionLayout = null;
        
        public PrepublishingHomeListItemViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.ViewGroup parentView, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiHelpers uiHelpers) {
            super(null, 0);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.UiHelpers getUiHelpers() {
            return null;
        }
        
        @java.lang.Override()
        public void onBind(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.posts.PrepublishingHomeItemUiState uiState) {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010\u00a8\u0006\u0015"}, d2 = {"Lorg/wordpress/android/ui/posts/PrepublishingHomeViewHolder$PrepublishingHeaderListItemViewHolder;", "Lorg/wordpress/android/ui/posts/PrepublishingHomeViewHolder;", "parentView", "Landroid/view/ViewGroup;", "uiHelpers", "Lorg/wordpress/android/ui/utils/UiHelpers;", "imageManager", "Lorg/wordpress/android/util/image/ImageManager;", "(Landroid/view/ViewGroup;Lorg/wordpress/android/ui/utils/UiHelpers;Lorg/wordpress/android/util/image/ImageManager;)V", "getImageManager", "()Lorg/wordpress/android/util/image/ImageManager;", "siteIcon", "Landroid/widget/ImageView;", "siteName", "Landroid/widget/TextView;", "getUiHelpers", "()Lorg/wordpress/android/ui/utils/UiHelpers;", "onBind", "", "uiState", "Lorg/wordpress/android/ui/posts/PrepublishingHomeItemUiState;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class PrepublishingHeaderListItemViewHolder extends org.wordpress.android.ui.posts.PrepublishingHomeViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.utils.UiHelpers uiHelpers = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.util.image.ImageManager imageManager = null;
        private final android.widget.TextView siteName = null;
        private final android.widget.ImageView siteIcon = null;
        
        public PrepublishingHeaderListItemViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.ViewGroup parentView, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiHelpers uiHelpers, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.util.image.ImageManager imageManager) {
            super(null, 0);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.UiHelpers getUiHelpers() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.util.image.ImageManager getImageManager() {
            return null;
        }
        
        @java.lang.Override()
        public void onBind(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.posts.PrepublishingHomeItemUiState uiState) {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u000f"}, d2 = {"Lorg/wordpress/android/ui/posts/PrepublishingHomeViewHolder$PrepublishingSubmitButtonViewHolder;", "Lorg/wordpress/android/ui/posts/PrepublishingHomeViewHolder;", "parentView", "Landroid/view/ViewGroup;", "uiHelpers", "Lorg/wordpress/android/ui/utils/UiHelpers;", "(Landroid/view/ViewGroup;Lorg/wordpress/android/ui/utils/UiHelpers;)V", "button", "Landroid/widget/Button;", "getUiHelpers", "()Lorg/wordpress/android/ui/utils/UiHelpers;", "onBind", "", "uiState", "Lorg/wordpress/android/ui/posts/PrepublishingHomeItemUiState;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class PrepublishingSubmitButtonViewHolder extends org.wordpress.android.ui.posts.PrepublishingHomeViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.utils.UiHelpers uiHelpers = null;
        private final android.widget.Button button = null;
        
        public PrepublishingSubmitButtonViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.ViewGroup parentView, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiHelpers uiHelpers) {
            super(null, 0);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.UiHelpers getUiHelpers() {
            return null;
        }
        
        @java.lang.Override()
        public void onBind(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.posts.PrepublishingHomeItemUiState uiState) {
        }
    }
}