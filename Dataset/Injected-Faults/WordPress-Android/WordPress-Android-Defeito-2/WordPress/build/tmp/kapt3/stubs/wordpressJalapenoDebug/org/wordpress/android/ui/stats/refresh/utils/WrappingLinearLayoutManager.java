package org.wordpress.android.ui.stats.refresh.utils;

import java.lang.System;

/**
 * This class should be used only when the RecyclerView's height is set to wrap_content.
 * In a normal recycler view when were decreasing the number of items, it first changes the RV height and then
 * animates the removal of items. This doesn't look good because the items first disappear only to slide back into
 * the view once their animation is finished. This layout manager reverts the order of animations and first animates
 * the removal of items and then decreases the height of the wrapped recycler view.
 * Solution is based on: https://stackoverflow.com/questions/40242011/custom-recyclerviews-layoutmanager-automeasuring-after-animation-finished-on-i
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u001f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0006\u0010\n\u001a\u00020\u000bJ\b\u0010\f\u001a\u00020\u0007H\u0016J\u0006\u0010\r\u001a\u00020\u000bJ \u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0005H\u0016J,\u0010\u0013\u001a\u00020\u000b2\n\u0010\u0014\u001a\u00060\u0015R\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u0005H\u0016J\b\u0010\u001a\u001a\u00020\u0007H\u0016R\u000e\u0010\t\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001b"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/utils/WrappingLinearLayoutManager;", "Landroidx/recyclerview/widget/LinearLayoutManager;", "context", "Landroid/content/Context;", "orientation", "", "reverseLayout", "", "(Landroid/content/Context;IZ)V", "enableAutoMeasure", "init", "", "isAutoMeasureEnabled", "onItemRangeRemoved", "onItemsRemoved", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "positionStart", "itemCount", "onMeasure", "recycler", "Landroidx/recyclerview/widget/RecyclerView$Recycler;", "state", "Landroidx/recyclerview/widget/RecyclerView$State;", "widthSpec", "heightSpec", "supportsPredictiveItemAnimations", "org.wordpress.android_wordpressJalapenoDebug"})
public final class WrappingLinearLayoutManager extends androidx.recyclerview.widget.LinearLayoutManager {
    private boolean enableAutoMeasure = true;
    
    public WrappingLinearLayoutManager(@org.jetbrains.annotations.Nullable()
    android.content.Context context, int orientation, boolean reverseLayout) {
        super(null);
    }
    
    public final void init() {
    }
    
    public final void onItemRangeRemoved() {
    }
    
    @java.lang.Override()
    public void onMeasure(@org.jetbrains.annotations.NotNull()
    androidx.recyclerview.widget.RecyclerView.Recycler recycler, @org.jetbrains.annotations.NotNull()
    androidx.recyclerview.widget.RecyclerView.State state, int widthSpec, int heightSpec) {
    }
    
    @java.lang.Override()
    public void onItemsRemoved(@org.jetbrains.annotations.NotNull()
    androidx.recyclerview.widget.RecyclerView recyclerView, int positionStart, int itemCount) {
    }
    
    @java.lang.Override()
    public boolean isAutoMeasureEnabled() {
        return false;
    }
    
    @java.lang.Override()
    public boolean supportsPredictiveItemAnimations() {
        return false;
    }
}