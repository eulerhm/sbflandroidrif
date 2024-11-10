package org.wordpress.android.ui.stats.refresh.lists.viewholders;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J \u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0017J\b\u0010\u000e\u001a\u00020\bH\u0002\u00a8\u0006\u000f"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/viewholders/BaseStatsViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "parent", "Landroid/view/ViewGroup;", "layout", "", "(Landroid/view/ViewGroup;I)V", "bind", "", "statsType", "Lorg/wordpress/android/fluxc/store/StatsStore$StatsType;", "items", "", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem;", "setFullWidth", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class BaseStatsViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
    
    public BaseStatsViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, @androidx.annotation.LayoutRes()
    int layout) {
        super(null);
    }
    
    @androidx.annotation.CallSuper()
    public void bind(@org.jetbrains.annotations.Nullable()
    org.wordpress.android.fluxc.store.StatsStore.StatsType statsType, @org.jetbrains.annotations.NotNull()
    java.util.List<? extends org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem> items) {
    }
    
    private final void setFullWidth() {
    }
}