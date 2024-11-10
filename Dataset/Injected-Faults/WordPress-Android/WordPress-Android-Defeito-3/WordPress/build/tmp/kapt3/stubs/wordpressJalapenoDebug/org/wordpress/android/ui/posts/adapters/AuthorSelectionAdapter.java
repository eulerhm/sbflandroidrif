package org.wordpress.android.ui.posts.adapters;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0002+,B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\"\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00152\b\u0010\u0019\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\u0015\u0010\u001c\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u001d\u001a\u00020\u001e\u00a2\u0006\u0002\u0010\u001fJ\u0010\u0010 \u001a\u00020!2\u0006\u0010\u0018\u001a\u00020\u0015H\u0016J\u0010\u0010\"\u001a\u00020#2\u0006\u0010\u0018\u001a\u00020\u0015H\u0016J\"\u0010$\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00152\b\u0010\u0019\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\b\u0010%\u001a\u00020&H\u0016J\u0014\u0010\'\u001a\u00020(2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\r0*R\u001e\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u000e\u001a\u00020\u000f8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013\u00a8\u0006-"}, d2 = {"Lorg/wordpress/android/ui/posts/adapters/AuthorSelectionAdapter;", "Landroid/widget/BaseAdapter;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "imageManager", "Lorg/wordpress/android/util/image/ImageManager;", "getImageManager", "()Lorg/wordpress/android/util/image/ImageManager;", "setImageManager", "(Lorg/wordpress/android/util/image/ImageManager;)V", "items", "", "Lorg/wordpress/android/ui/posts/AuthorFilterListItemUIState;", "uiHelpers", "Lorg/wordpress/android/ui/utils/UiHelpers;", "getUiHelpers", "()Lorg/wordpress/android/ui/utils/UiHelpers;", "setUiHelpers", "(Lorg/wordpress/android/ui/utils/UiHelpers;)V", "getCount", "", "getDropDownView", "Landroid/view/View;", "position", "convertView", "parent", "Landroid/view/ViewGroup;", "getIndexOfSelection", "selection", "Lorg/wordpress/android/ui/posts/AuthorFilterSelection;", "(Lorg/wordpress/android/ui/posts/AuthorFilterSelection;)Ljava/lang/Integer;", "getItem", "", "getItemId", "", "getView", "hasStableIds", "", "updateItems", "", "newItems", "", "DropdownViewHolder", "NormalViewHolder", "org.wordpress.android_wordpressJalapenoDebug"})
public final class AuthorSelectionAdapter extends android.widget.BaseAdapter {
    @javax.inject.Inject()
    public org.wordpress.android.util.image.ImageManager imageManager;
    @javax.inject.Inject()
    public org.wordpress.android.ui.utils.UiHelpers uiHelpers;
    private final java.util.List<org.wordpress.android.ui.posts.AuthorFilterListItemUIState> items = null;
    
    public AuthorSelectionAdapter(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
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
    @java.lang.Override()
    public android.view.View getDropDownView(int position, @org.jetbrains.annotations.Nullable()
    android.view.View convertView, @org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent) {
        return null;
    }
    
    @java.lang.Override()
    public long getItemId(int position) {
        return 0L;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getIndexOfSelection(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.AuthorFilterSelection selection) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public android.view.View getView(int position, @org.jetbrains.annotations.Nullable()
    android.view.View convertView, @org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent) {
        return null;
    }
    
    @java.lang.Override()
    public boolean hasStableIds() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.Object getItem(int position) {
        return null;
    }
    
    @java.lang.Override()
    public int getCount() {
        return 0;
    }
    
    public final void updateItems(@org.jetbrains.annotations.NotNull()
    java.util.List<? extends org.wordpress.android.ui.posts.AuthorFilterListItemUIState> newItems) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0012\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J \u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0017R\u0014\u0010\u0005\u001a\u00020\u0006X\u0084\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u0002\u001a\u00020\u0003X\u0084\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0013"}, d2 = {"Lorg/wordpress/android/ui/posts/adapters/AuthorSelectionAdapter$NormalViewHolder;", "", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "image", "Landroidx/appcompat/widget/AppCompatImageView;", "getImage", "()Landroidx/appcompat/widget/AppCompatImageView;", "getItemView", "()Landroid/view/View;", "bind", "", "state", "Lorg/wordpress/android/ui/posts/AuthorFilterListItemUIState;", "imageManager", "Lorg/wordpress/android/util/image/ImageManager;", "uiHelpers", "Lorg/wordpress/android/ui/utils/UiHelpers;", "org.wordpress.android_wordpressJalapenoDebug"})
    static class NormalViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final android.view.View itemView = null;
        @org.jetbrains.annotations.NotNull()
        private final androidx.appcompat.widget.AppCompatImageView image = null;
        
        public NormalViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.View itemView) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        protected final android.view.View getItemView() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        protected final androidx.appcompat.widget.AppCompatImageView getImage() {
            return null;
        }
        
        @androidx.annotation.CallSuper()
        public void bind(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.posts.AuthorFilterListItemUIState state, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.util.image.ImageManager imageManager, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiHelpers uiHelpers) {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J \u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lorg/wordpress/android/ui/posts/adapters/AuthorSelectionAdapter$DropdownViewHolder;", "Lorg/wordpress/android/ui/posts/adapters/AuthorSelectionAdapter$NormalViewHolder;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "text", "Landroidx/appcompat/widget/AppCompatTextView;", "bind", "", "state", "Lorg/wordpress/android/ui/posts/AuthorFilterListItemUIState;", "imageManager", "Lorg/wordpress/android/util/image/ImageManager;", "uiHelpers", "Lorg/wordpress/android/ui/utils/UiHelpers;", "org.wordpress.android_wordpressJalapenoDebug"})
    static final class DropdownViewHolder extends org.wordpress.android.ui.posts.adapters.AuthorSelectionAdapter.NormalViewHolder {
        private final androidx.appcompat.widget.AppCompatTextView text = null;
        
        public DropdownViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.View itemView) {
            super(null);
        }
        
        @java.lang.Override()
        public void bind(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.posts.AuthorFilterListItemUIState state, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.util.image.ImageManager imageManager, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiHelpers uiHelpers) {
        }
    }
}