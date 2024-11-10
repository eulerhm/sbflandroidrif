package org.wordpress.android.ui.layoutpicker;

import java.lang.System;

/**
 * Renders the layout categories
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001BA\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\t\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f\u00a2\u0006\u0002\u0010\rJ\b\u0010\u0015\u001a\u00020\fH\u0016J\u0010\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\fH\u0016J\u0018\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00022\u0006\u0010\u0017\u001a\u00020\fH\u0016J\u0018\u0010\u001b\u001a\u00020\u00022\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\fH\u0016J\u000e\u0010\u001f\u001a\u00020\u00192\u0006\u0010 \u001a\u00020\u0004J\u0006\u0010!\u001a\u00020\u0004J\u0010\u0010\"\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0002H\u0016J\u0014\u0010#\u001a\u00020\u00192\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010R\u0012\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u000eR\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\u00020\t8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006%"}, d2 = {"Lorg/wordpress/android/ui/layoutpicker/LayoutCategoryAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lorg/wordpress/android/ui/layoutpicker/LayoutsRowViewHolder;", "nestedScrollStates", "Landroid/os/Bundle;", "thumbDimensionProvider", "Lorg/wordpress/android/ui/layoutpicker/ThumbDimensionProvider;", "recommendedDimensionProvider", "showRowDividers", "", "useLargeCategoryHeading", "footerLayoutResId", "", "(Landroid/os/Bundle;Lorg/wordpress/android/ui/layoutpicker/ThumbDimensionProvider;Lorg/wordpress/android/ui/layoutpicker/ThumbDimensionProvider;ZZLjava/lang/Integer;)V", "Ljava/lang/Integer;", "items", "", "Lorg/wordpress/android/ui/layoutpicker/LayoutCategoryUiState;", "shouldShowFooter", "getShouldShowFooter", "()Z", "getItemCount", "getItemViewType", "position", "onBindViewHolder", "", "holder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "onRestoreInstanceState", "savedInstanceState", "onSaveInstanceState", "onViewRecycled", "update", "newItems", "org.wordpress.android_wordpressJalapenoDebug"})
public final class LayoutCategoryAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<org.wordpress.android.ui.layoutpicker.LayoutsRowViewHolder> {
    private android.os.Bundle nestedScrollStates;
    private final org.wordpress.android.ui.layoutpicker.ThumbDimensionProvider thumbDimensionProvider = null;
    private final org.wordpress.android.ui.layoutpicker.ThumbDimensionProvider recommendedDimensionProvider = null;
    private final boolean showRowDividers = false;
    private final boolean useLargeCategoryHeading = false;
    private final java.lang.Integer footerLayoutResId = null;
    private java.util.List<org.wordpress.android.ui.layoutpicker.LayoutCategoryUiState> items;
    
    public LayoutCategoryAdapter(@org.jetbrains.annotations.NotNull()
    android.os.Bundle nestedScrollStates, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.layoutpicker.ThumbDimensionProvider thumbDimensionProvider, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.ui.layoutpicker.ThumbDimensionProvider recommendedDimensionProvider, boolean showRowDividers, boolean useLargeCategoryHeading, @org.jetbrains.annotations.Nullable()
    java.lang.Integer footerLayoutResId) {
        super();
    }
    
    private final boolean getShouldShowFooter() {
        return false;
    }
    
    public final void update(@org.jetbrains.annotations.NotNull()
    java.util.List<org.wordpress.android.ui.layoutpicker.LayoutCategoryUiState> newItems) {
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.layoutpicker.LayoutsRowViewHolder holder, int position) {
    }
    
    @java.lang.Override()
    public int getItemViewType(int position) {
        return 0;
    }
    
    @java.lang.Override()
    public void onViewRecycled(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.layoutpicker.LayoutsRowViewHolder holder) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public org.wordpress.android.ui.layoutpicker.LayoutsRowViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    public final void onRestoreInstanceState(@org.jetbrains.annotations.NotNull()
    android.os.Bundle savedInstanceState) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.os.Bundle onSaveInstanceState() {
        return null;
    }
}