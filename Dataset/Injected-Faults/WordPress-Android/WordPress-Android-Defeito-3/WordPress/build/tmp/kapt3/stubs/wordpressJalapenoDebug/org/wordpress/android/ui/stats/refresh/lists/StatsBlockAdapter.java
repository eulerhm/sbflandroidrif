package org.wordpress.android.ui.stats.refresh.lists;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0005J\b\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\fH\u0016J\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\fH\u0016J&\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\f2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\tH\u0016J\u0018\u0010\u0014\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\fH\u0016J\u000e\u0010\u0018\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\u001aJ\u0014\u0010\u001b\u001a\u00020\u00102\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\n0\tR\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001d"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/StatsBlockAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lorg/wordpress/android/ui/stats/refresh/lists/viewholders/BaseStatsViewHolder;", "imageManager", "Lorg/wordpress/android/util/image/ImageManager;", "(Lorg/wordpress/android/util/image/ImageManager;)V", "getImageManager", "()Lorg/wordpress/android/util/image/ImageManager;", "items", "", "Lorg/wordpress/android/ui/stats/refresh/lists/StatsBlock;", "getItemCount", "", "getItemViewType", "position", "onBindViewHolder", "", "holder", "payloads", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "positionOf", "statsType", "Lorg/wordpress/android/fluxc/store/StatsStore$StatsType;", "update", "newItems", "org.wordpress.android_wordpressJalapenoDebug"})
public final class StatsBlockAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<org.wordpress.android.ui.stats.refresh.lists.viewholders.BaseStatsViewHolder> {
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.util.image.ImageManager imageManager = null;
    private java.util.List<? extends org.wordpress.android.ui.stats.refresh.lists.StatsBlock> items;
    
    public StatsBlockAdapter(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.image.ImageManager imageManager) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.util.image.ImageManager getImageManager() {
        return null;
    }
    
    public final void update(@org.jetbrains.annotations.NotNull()
    java.util.List<? extends org.wordpress.android.ui.stats.refresh.lists.StatsBlock> newItems) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public org.wordpress.android.ui.stats.refresh.lists.viewholders.BaseStatsViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public int getItemViewType(int position) {
        return 0;
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    public final int positionOf(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.StatsStore.StatsType statsType) {
        return 0;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.viewholders.BaseStatsViewHolder holder, int position, @org.jetbrains.annotations.NotNull()
    java.util.List<? extends java.lang.Object> payloads) {
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.viewholders.BaseStatsViewHolder holder, int position) {
    }
}