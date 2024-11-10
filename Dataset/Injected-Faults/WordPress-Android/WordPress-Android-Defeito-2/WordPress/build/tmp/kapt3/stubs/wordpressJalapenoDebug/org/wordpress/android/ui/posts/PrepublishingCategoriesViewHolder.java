package org.wordpress.android.ui.posts;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003:\u0001\u000fB\u000f\b\u0004\u0012\u0006\u0010\u0004\u001a\u00028\u0000\u00a2\u0006\u0002\u0010\u0005J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH&R\u0016\u0010\u0004\u001a\u00028\u0000X\u0080\u0004\u00a2\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007\u0082\u0001\u0001\u0010\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/posts/PrepublishingCategoriesViewHolder;", "T", "Landroidx/viewbinding/ViewBinding;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "(Landroidx/viewbinding/ViewBinding;)V", "getBinding$org_wordpress_android_wordpressJalapenoDebug", "()Landroidx/viewbinding/ViewBinding;", "Landroidx/viewbinding/ViewBinding;", "onBind", "", "uiState", "Lorg/wordpress/android/ui/posts/PrepublishingCategoriesViewModel$PrepublishingCategoriesListItemUiState;", "position", "", "PrepublishingCategoriesListItemViewHolder", "Lorg/wordpress/android/ui/posts/PrepublishingCategoriesViewHolder$PrepublishingCategoriesListItemViewHolder;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class PrepublishingCategoriesViewHolder<T extends androidx.viewbinding.ViewBinding> extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
    @org.jetbrains.annotations.NotNull()
    private final T binding = null;
    
    private PrepublishingCategoriesViewHolder(T binding) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final T getBinding$org_wordpress_android_wordpressJalapenoDebug() {
        return null;
    }
    
    public abstract void onBind(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.PrepublishingCategoriesViewModel.PrepublishingCategoriesListItemUiState uiState, int position);
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2 = {"Lorg/wordpress/android/ui/posts/PrepublishingCategoriesViewHolder$PrepublishingCategoriesListItemViewHolder;", "Lorg/wordpress/android/ui/posts/PrepublishingCategoriesViewHolder;", "Lorg/wordpress/android/databinding/PrepublishingCategoriesRowBinding;", "parentView", "Landroid/view/ViewGroup;", "uiHelpers", "Lorg/wordpress/android/ui/utils/UiHelpers;", "(Landroid/view/ViewGroup;Lorg/wordpress/android/ui/utils/UiHelpers;)V", "onBind", "", "uiState", "Lorg/wordpress/android/ui/posts/PrepublishingCategoriesViewModel$PrepublishingCategoriesListItemUiState;", "position", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class PrepublishingCategoriesListItemViewHolder extends org.wordpress.android.ui.posts.PrepublishingCategoriesViewHolder<org.wordpress.android.databinding.PrepublishingCategoriesRowBinding> {
        private final org.wordpress.android.ui.utils.UiHelpers uiHelpers = null;
        
        public PrepublishingCategoriesListItemViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.ViewGroup parentView, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiHelpers uiHelpers) {
            super(null);
        }
        
        @java.lang.Override()
        public void onBind(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.posts.PrepublishingCategoriesViewModel.PrepublishingCategoriesListItemUiState uiState, int position) {
        }
    }
}