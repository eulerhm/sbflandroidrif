package org.wordpress.android.ui.mysite.cards.dashboard.posts;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003:\u0002\r\u000eB\u000f\b\u0004\u0012\u0006\u0010\u0004\u001a\u00028\u0000\u00a2\u0006\u0002\u0010\u0005J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH&R\u0016\u0010\u0004\u001a\u00028\u0000X\u0094\u0004\u00a2\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007\u0082\u0001\u0002\u000f\u0010\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/mysite/cards/dashboard/posts/PostCardViewHolder;", "T", "Landroidx/viewbinding/ViewBinding;", "Lorg/wordpress/android/ui/mysite/cards/dashboard/CardViewHolder;", "binding", "(Landroidx/viewbinding/ViewBinding;)V", "getBinding", "()Landroidx/viewbinding/ViewBinding;", "Landroidx/viewbinding/ViewBinding;", "bind", "", "card", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$DashboardCards$DashboardCard$PostCard;", "PostCardWithPostItemsViewHolder", "PostCardWithoutPostItemsViewHolder", "Lorg/wordpress/android/ui/mysite/cards/dashboard/posts/PostCardViewHolder$PostCardWithoutPostItemsViewHolder;", "Lorg/wordpress/android/ui/mysite/cards/dashboard/posts/PostCardViewHolder$PostCardWithPostItemsViewHolder;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class PostCardViewHolder<T extends androidx.viewbinding.ViewBinding> extends org.wordpress.android.ui.mysite.cards.dashboard.CardViewHolder<T> {
    @org.jetbrains.annotations.NotNull()
    private final T binding = null;
    
    private PostCardViewHolder(T binding) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    protected T getBinding() {
        return null;
    }
    
    public abstract void bind(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DashboardCards.DashboardCard.PostCard card);
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\tJ\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2 = {"Lorg/wordpress/android/ui/mysite/cards/dashboard/posts/PostCardViewHolder$PostCardWithoutPostItemsViewHolder;", "Lorg/wordpress/android/ui/mysite/cards/dashboard/posts/PostCardViewHolder;", "Lorg/wordpress/android/databinding/MySitePostCardWithoutPostItemsBinding;", "parent", "Landroid/view/ViewGroup;", "imageManager", "Lorg/wordpress/android/util/image/ImageManager;", "uiHelpers", "Lorg/wordpress/android/ui/utils/UiHelpers;", "(Landroid/view/ViewGroup;Lorg/wordpress/android/util/image/ImageManager;Lorg/wordpress/android/ui/utils/UiHelpers;)V", "bind", "", "card", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$DashboardCards$DashboardCard$PostCard;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class PostCardWithoutPostItemsViewHolder extends org.wordpress.android.ui.mysite.cards.dashboard.posts.PostCardViewHolder<org.wordpress.android.databinding.MySitePostCardWithoutPostItemsBinding> {
        private final org.wordpress.android.util.image.ImageManager imageManager = null;
        private final org.wordpress.android.ui.utils.UiHelpers uiHelpers = null;
        
        public PostCardWithoutPostItemsViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.ViewGroup parent, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.util.image.ImageManager imageManager, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiHelpers uiHelpers) {
            super(null);
        }
        
        @java.lang.Override()
        public void bind(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DashboardCards.DashboardCard.PostCard card) {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\tJ\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J\u0016\u0010\u000e\u001a\u00020\u000b*\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0002R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lorg/wordpress/android/ui/mysite/cards/dashboard/posts/PostCardViewHolder$PostCardWithPostItemsViewHolder;", "Lorg/wordpress/android/ui/mysite/cards/dashboard/posts/PostCardViewHolder;", "Lorg/wordpress/android/databinding/MySitePostCardWithPostItemsBinding;", "parent", "Landroid/view/ViewGroup;", "imageManager", "Lorg/wordpress/android/util/image/ImageManager;", "uiHelpers", "Lorg/wordpress/android/ui/utils/UiHelpers;", "(Landroid/view/ViewGroup;Lorg/wordpress/android/util/image/ImageManager;Lorg/wordpress/android/ui/utils/UiHelpers;)V", "bind", "", "card", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$DashboardCards$DashboardCard$PostCard;", "update", "Lorg/wordpress/android/databinding/MySiteCardToolbarBinding;", "title", "Lorg/wordpress/android/ui/utils/UiString;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class PostCardWithPostItemsViewHolder extends org.wordpress.android.ui.mysite.cards.dashboard.posts.PostCardViewHolder<org.wordpress.android.databinding.MySitePostCardWithPostItemsBinding> {
        private final org.wordpress.android.ui.utils.UiHelpers uiHelpers = null;
        
        public PostCardWithPostItemsViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.ViewGroup parent, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.util.image.ImageManager imageManager, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiHelpers uiHelpers) {
            super(null);
        }
        
        @java.lang.Override()
        public void bind(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DashboardCards.DashboardCard.PostCard card) {
        }
        
        private final void update(org.wordpress.android.databinding.MySiteCardToolbarBinding $this$update, org.wordpress.android.ui.utils.UiString title) {
        }
    }
}