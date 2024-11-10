package org.wordpress.android.ui.pages;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\r\u000e\u000f\u0010B\u0019\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH&R\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b\u0082\u0001\u0004\u0011\u0012\u0013\u0014\u00a8\u0006\u0015"}, d2 = {"Lorg/wordpress/android/ui/pages/PageItemViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "parent", "Landroid/view/ViewGroup;", "layout", "", "(Landroid/view/ViewGroup;I)V", "getParent$org_wordpress_android_wordpressJalapenoDebug", "()Landroid/view/ViewGroup;", "onBind", "", "pageItem", "Lorg/wordpress/android/ui/pages/PageItem;", "EmptyViewHolder", "PageDividerViewHolder", "PageParentViewHolder", "PageViewHolder", "Lorg/wordpress/android/ui/pages/PageItemViewHolder$PageViewHolder;", "Lorg/wordpress/android/ui/pages/PageItemViewHolder$PageDividerViewHolder;", "Lorg/wordpress/android/ui/pages/PageItemViewHolder$PageParentViewHolder;", "Lorg/wordpress/android/ui/pages/PageItemViewHolder$EmptyViewHolder;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class PageItemViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
    @org.jetbrains.annotations.NotNull()
    private final android.view.ViewGroup parent = null;
    
    private PageItemViewHolder(android.view.ViewGroup parent, @androidx.annotation.LayoutRes()
    int layout) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.view.ViewGroup getParent$org_wordpress_android_wordpressJalapenoDebug() {
        return null;
    }
    
    public abstract void onBind(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.pages.PageItem pageItem);
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0096\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 <2\u00020\u0001:\u0001<Bc\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0018\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0005\u0012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u000b0\n\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\b\u0012\b\b\u0002\u0010\u000f\u001a\u00020\b\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u00a2\u0006\u0002\u0010\u0012J\u0018\u0010&\u001a\u00020\u000b2\u0006\u0010\'\u001a\u00020\u00072\u0006\u0010(\u001a\u00020)H\u0002J\u0010\u0010*\u001a\u00020\u000b2\u0006\u0010\'\u001a\u00020+H\u0016J\u0010\u0010,\u001a\u00020\u000b2\u0006\u0010-\u001a\u00020\bH\u0002J\u0012\u0010.\u001a\u00020\u000b2\b\u0010/\u001a\u0004\u0018\u000100H\u0002J3\u00101\u001a\u00020\u000b2\u0006\u00102\u001a\u0002032\b\u00104\u001a\u0004\u0018\u0001002\b\u00105\u001a\u0004\u0018\u0001062\b\u00107\u001a\u0004\u0018\u000106H\u0002\u00a2\u0006\u0002\u00108J\u0010\u00109\u001a\u00020\u000b2\u0006\u0010:\u001a\u00020;H\u0002R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0015\u001a\n \u0017*\u0004\u0018\u00010\u00160\u0016X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0018\u001a\n \u0017*\u0004\u0018\u00010\u00190\u0019X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u001a\u001a\n \u0017*\u0004\u0018\u00010\u00030\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u001b\u001a\n \u0017*\u0004\u0018\u00010\u00030\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u001c\u001a\n \u0017*\u0004\u0018\u00010\u001d0\u001dX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u001e\u001a\n \u0017*\u0004\u0018\u00010\u00190\u0019X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u001f\u001a\n \u0017*\u0004\u0018\u00010\u00160\u0016X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010 \u001a\n \u0017*\u0004\u0018\u00010\u00190\u0019X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010!\u001a\n \u0017*\u0004\u0018\u00010\u00190\u0019X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\"\u001a\u0004\u0018\u00010#X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020%X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006="}, d2 = {"Lorg/wordpress/android/ui/pages/PageItemViewHolder$PageViewHolder;", "Lorg/wordpress/android/ui/pages/PageItemViewHolder;", "parentView", "Landroid/view/ViewGroup;", "onMenuAction", "Lkotlin/Function2;", "Lorg/wordpress/android/ui/pages/PageItem$Action;", "Lorg/wordpress/android/ui/pages/PageItem$Page;", "", "onItemTapped", "Lkotlin/Function1;", "", "imageManager", "Lorg/wordpress/android/util/image/ImageManager;", "isSitePhotonCapable", "isPrivateAtSite", "uiHelper", "Lorg/wordpress/android/ui/utils/UiHelpers;", "(Landroid/view/ViewGroup;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function1;Lorg/wordpress/android/util/image/ImageManager;ZZLorg/wordpress/android/ui/utils/UiHelpers;)V", "disabledOverlay", "Landroid/widget/FrameLayout;", "featuredImage", "Landroid/widget/ImageView;", "kotlin.jvm.PlatformType", "labels", "Landroid/widget/TextView;", "pageItemContainer", "pageLayout", "pageMore", "Landroid/widget/ImageButton;", "pageSubtitle", "pageSubtitleIcon", "pageSubtitleSuffix", "pageTitle", "selectableBackground", "Landroid/graphics/drawable/Drawable;", "uploadProgressBar", "Landroid/widget/ProgressBar;", "moreClick", "pageItem", "v", "Landroid/view/View;", "onBind", "Lorg/wordpress/android/ui/pages/PageItem;", "setBackground", "tapActionEnabled", "showFeaturedImage", "imageUrl", "", "showSubtitle", "inputDate", "Ljava/util/Date;", "author", "subtitle", "", "icon", "(Ljava/util/Date;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;)V", "updateProgressBarState", "progressBarUiState", "Lorg/wordpress/android/viewmodel/uistate/ProgressBarUiState;", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class PageViewHolder extends org.wordpress.android.ui.pages.PageItemViewHolder {
        private final kotlin.jvm.functions.Function2<org.wordpress.android.ui.pages.PageItem.Action, org.wordpress.android.ui.pages.PageItem.Page, java.lang.Boolean> onMenuAction = null;
        private final kotlin.jvm.functions.Function1<org.wordpress.android.ui.pages.PageItem.Page, kotlin.Unit> onItemTapped = null;
        private final org.wordpress.android.util.image.ImageManager imageManager = null;
        private final boolean isSitePhotonCapable = false;
        private final boolean isPrivateAtSite = false;
        private final org.wordpress.android.ui.utils.UiHelpers uiHelper = null;
        private final android.widget.TextView pageTitle = null;
        private final android.widget.ImageButton pageMore = null;
        private final android.widget.TextView pageSubtitle = null;
        private final android.widget.ImageView pageSubtitleIcon = null;
        private final android.widget.TextView pageSubtitleSuffix = null;
        private final android.widget.TextView labels = null;
        private final android.widget.ImageView featuredImage = null;
        private final android.widget.ProgressBar uploadProgressBar = null;
        private final android.widget.FrameLayout disabledOverlay = null;
        private final android.view.ViewGroup pageItemContainer = null;
        private final android.view.ViewGroup pageLayout = null;
        private final android.graphics.drawable.Drawable selectableBackground = null;
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.ui.pages.PageItemViewHolder.PageViewHolder.Companion Companion = null;
        public static final int FEATURED_IMAGE_THUMBNAIL_SIZE_DP = 40;
        
        public PageViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.ViewGroup parentView, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function2<? super org.wordpress.android.ui.pages.PageItem.Action, ? super org.wordpress.android.ui.pages.PageItem.Page, java.lang.Boolean> onMenuAction, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super org.wordpress.android.ui.pages.PageItem.Page, kotlin.Unit> onItemTapped, @org.jetbrains.annotations.Nullable()
        org.wordpress.android.util.image.ImageManager imageManager, boolean isSitePhotonCapable, boolean isPrivateAtSite, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiHelpers uiHelper) {
            super(null, 0);
        }
        
        @java.lang.Override()
        public void onBind(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.pages.PageItem pageItem) {
        }
        
        private final void updateProgressBarState(org.wordpress.android.viewmodel.uistate.ProgressBarUiState progressBarUiState) {
        }
        
        private final void setBackground(boolean tapActionEnabled) {
        }
        
        private final void moreClick(org.wordpress.android.ui.pages.PageItem.Page pageItem, android.view.View v) {
        }
        
        private final void showFeaturedImage(java.lang.String imageUrl) {
        }
        
        private final void showSubtitle(java.util.Date inputDate, java.lang.String author, java.lang.Integer subtitle, java.lang.Integer icon) {
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lorg/wordpress/android/ui/pages/PageItemViewHolder$PageViewHolder$Companion;", "", "()V", "FEATURED_IMAGE_THUMBNAIL_SIZE_DP", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Companion {
            
            private Companion() {
                super();
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lorg/wordpress/android/ui/pages/PageItemViewHolder$PageDividerViewHolder;", "Lorg/wordpress/android/ui/pages/PageItemViewHolder;", "parentView", "Landroid/view/ViewGroup;", "(Landroid/view/ViewGroup;)V", "dividerTitle", "Landroid/widget/TextView;", "kotlin.jvm.PlatformType", "onBind", "", "pageItem", "Lorg/wordpress/android/ui/pages/PageItem;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class PageDividerViewHolder extends org.wordpress.android.ui.pages.PageItemViewHolder {
        private final android.widget.TextView dividerTitle = null;
        
        public PageDividerViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.ViewGroup parentView) {
            super(null, 0);
        }
        
        @java.lang.Override()
        public void onBind(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.pages.PageItem pageItem) {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005\u0012\b\b\u0001\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u0010\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u0012H\u0016R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\n \r*\u0004\u0018\u00010\f0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u000e\u001a\n \r*\u0004\u0018\u00010\u000f0\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lorg/wordpress/android/ui/pages/PageItemViewHolder$PageParentViewHolder;", "Lorg/wordpress/android/ui/pages/PageItemViewHolder;", "parentView", "Landroid/view/ViewGroup;", "onParentSelected", "Lkotlin/Function1;", "Lorg/wordpress/android/ui/pages/PageItem$ParentPage;", "", "layout", "", "(Landroid/view/ViewGroup;Lkotlin/jvm/functions/Function1;I)V", "pageTitle", "Landroid/widget/TextView;", "kotlin.jvm.PlatformType", "radioButton", "Landroid/widget/RadioButton;", "onBind", "pageItem", "Lorg/wordpress/android/ui/pages/PageItem;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class PageParentViewHolder extends org.wordpress.android.ui.pages.PageItemViewHolder {
        private final kotlin.jvm.functions.Function1<org.wordpress.android.ui.pages.PageItem.ParentPage, kotlin.Unit> onParentSelected = null;
        private final android.widget.TextView pageTitle = null;
        private final android.widget.RadioButton radioButton = null;
        
        public PageParentViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.ViewGroup parentView, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super org.wordpress.android.ui.pages.PageItem.ParentPage, kotlin.Unit> onParentSelected, @androidx.annotation.LayoutRes()
        int layout) {
            super(null, 0);
        }
        
        @java.lang.Override()
        public void onBind(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.pages.PageItem pageItem) {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\u0002\u0010\u0007J\u0010\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\rH\u0016R\u0016\u0010\b\u001a\n \n*\u0004\u0018\u00010\t0\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2 = {"Lorg/wordpress/android/ui/pages/PageItemViewHolder$EmptyViewHolder;", "Lorg/wordpress/android/ui/pages/PageItemViewHolder;", "parentView", "Landroid/view/ViewGroup;", "onActionButtonClicked", "Lkotlin/Function0;", "", "(Landroid/view/ViewGroup;Lkotlin/jvm/functions/Function0;)V", "emptyView", "Lorg/wordpress/android/ui/ActionableEmptyView;", "kotlin.jvm.PlatformType", "onBind", "pageItem", "Lorg/wordpress/android/ui/pages/PageItem;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class EmptyViewHolder extends org.wordpress.android.ui.pages.PageItemViewHolder {
        private final kotlin.jvm.functions.Function0<kotlin.Unit> onActionButtonClicked = null;
        private final org.wordpress.android.ui.ActionableEmptyView emptyView = null;
        
        public EmptyViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.ViewGroup parentView, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function0<kotlin.Unit> onActionButtonClicked) {
            super(null, 0);
        }
        
        @kotlin.Suppress(names = {"DEPRECATION"})
        @java.lang.Override()
        public void onBind(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.pages.PageItem pageItem) {
        }
    }
}