package org.wordpress.android.ui.layoutpicker;

import java.lang.System;

/**
 * Renders the Layout cards
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00022\u0006\u0010\u0016\u001a\u00020\u0012H\u0016J\u0018\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0012H\u0016J\u0014\u0010\u001b\u001a\u00020\u00142\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fR\u001e\u0010\b\u001a\u00020\t8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001d"}, d2 = {"Lorg/wordpress/android/ui/layoutpicker/LayoutsAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lorg/wordpress/android/ui/layoutpicker/LayoutViewHolder;", "context", "Landroid/content/Context;", "thumbDimensionProvider", "Lorg/wordpress/android/ui/layoutpicker/ThumbDimensionProvider;", "(Landroid/content/Context;Lorg/wordpress/android/ui/layoutpicker/ThumbDimensionProvider;)V", "imageManager", "Lorg/wordpress/android/util/image/ImageManager;", "getImageManager", "()Lorg/wordpress/android/util/image/ImageManager;", "setImageManager", "(Lorg/wordpress/android/util/image/ImageManager;)V", "layouts", "", "Lorg/wordpress/android/ui/layoutpicker/LayoutListItemUiState;", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "setData", "data", "org.wordpress.android_wordpressJalapenoDebug"})
public final class LayoutsAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<org.wordpress.android.ui.layoutpicker.LayoutViewHolder> {
    private final org.wordpress.android.ui.layoutpicker.ThumbDimensionProvider thumbDimensionProvider = null;
    @javax.inject.Inject()
    public org.wordpress.android.util.image.ImageManager imageManager;
    private java.util.List<org.wordpress.android.ui.layoutpicker.LayoutListItemUiState> layouts;
    
    public LayoutsAdapter(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.layoutpicker.ThumbDimensionProvider thumbDimensionProvider) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.util.image.ImageManager getImageManager() {
        return null;
    }
    
    public final void setImageManager(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.image.ImageManager p0) {
    }
    
    public final void setData(@org.jetbrains.annotations.NotNull()
    java.util.List<org.wordpress.android.ui.layoutpicker.LayoutListItemUiState> data) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public org.wordpress.android.ui.layoutpicker.LayoutViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.layoutpicker.LayoutViewHolder holder, int position) {
    }
}