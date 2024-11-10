package org.wordpress.android.ui.posts.adapters;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0002\u001c\u001dB\u001d\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0016J\u0018\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u0010H\u0016J\u0018\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0010H\u0016J\u000e\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\fR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001e"}, d2 = {"Lorg/wordpress/android/ui/posts/adapters/PostListAdapter;", "Landroidx/paging/PagedListAdapter;", "Lorg/wordpress/android/viewmodel/posts/PostListItemType;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "context", "Landroid/content/Context;", "imageManager", "Lorg/wordpress/android/util/image/ImageManager;", "uiHelpers", "Lorg/wordpress/android/ui/utils/UiHelpers;", "(Landroid/content/Context;Lorg/wordpress/android/util/image/ImageManager;Lorg/wordpress/android/ui/utils/UiHelpers;)V", "itemLayoutType", "Lorg/wordpress/android/ui/posts/PostListViewLayoutType;", "layoutInflater", "Landroid/view/LayoutInflater;", "getItemViewType", "", "position", "onBindViewHolder", "", "holder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "updateItemLayoutType", "", "updatedItemLayoutType", "EndListViewHolder", "LoadingViewHolder", "org.wordpress.android_wordpressJalapenoDebug"})
public final class PostListAdapter extends androidx.paging.PagedListAdapter<org.wordpress.android.viewmodel.posts.PostListItemType, androidx.recyclerview.widget.RecyclerView.ViewHolder> {
    private final org.wordpress.android.util.image.ImageManager imageManager = null;
    private final org.wordpress.android.ui.utils.UiHelpers uiHelpers = null;
    private final android.view.LayoutInflater layoutInflater = null;
    private org.wordpress.android.ui.posts.PostListViewLayoutType itemLayoutType;
    
    public PostListAdapter(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.image.ImageManager imageManager, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.utils.UiHelpers uiHelpers) {
        super(null);
    }
    
    @java.lang.Override()
    public int getItemViewType(int position) {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public androidx.recyclerview.widget.RecyclerView.ViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    androidx.recyclerview.widget.RecyclerView.ViewHolder holder, int position) {
    }
    
    public final boolean updateItemLayoutType(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.PostListViewLayoutType updatedItemLayoutType) {
        return false;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0007R\u0013\u0010\f\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0007R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0007\u00a8\u0006\u0014"}, d2 = {"Lorg/wordpress/android/ui/posts/adapters/PostListAdapter$LoadingViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "(Landroid/view/View;)V", "buttonDeletePermanently", "getButtonDeletePermanently", "()Landroid/view/View;", "buttonMore", "getButtonMore", "buttonMoveToDraft", "getButtonMoveToDraft", "editButton", "getEditButton", "viewButton", "getViewButton", "onBind", "", "item", "Lorg/wordpress/android/viewmodel/posts/PostListItemType$LoadingItem;", "org.wordpress.android_wordpressJalapenoDebug"})
    static final class LoadingViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.Nullable()
        private final android.view.View editButton = null;
        @org.jetbrains.annotations.Nullable()
        private final android.view.View viewButton = null;
        @org.jetbrains.annotations.Nullable()
        private final android.view.View buttonMore = null;
        @org.jetbrains.annotations.Nullable()
        private final android.view.View buttonMoveToDraft = null;
        @org.jetbrains.annotations.Nullable()
        private final android.view.View buttonDeletePermanently = null;
        
        public LoadingViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.View view) {
            super(null);
        }
        
        @org.jetbrains.annotations.Nullable()
        public final android.view.View getEditButton() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final android.view.View getViewButton() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final android.view.View getButtonMore() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final android.view.View getButtonMoveToDraft() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final android.view.View getButtonDeletePermanently() {
            return null;
        }
        
        public final void onBind(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.viewmodel.posts.PostListItemType.LoadingItem item) {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004\u00a8\u0006\u0005"}, d2 = {"Lorg/wordpress/android/ui/posts/adapters/PostListAdapter$EndListViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "(Landroid/view/View;)V", "org.wordpress.android_wordpressJalapenoDebug"})
    static final class EndListViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        
        public EndListViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.View view) {
            super(null);
        }
    }
}