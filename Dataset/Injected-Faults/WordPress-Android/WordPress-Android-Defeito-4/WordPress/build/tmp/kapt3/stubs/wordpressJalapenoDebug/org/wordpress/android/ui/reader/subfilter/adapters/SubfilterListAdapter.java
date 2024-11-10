package org.wordpress.android.ui.reader.subfilter.adapters;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\tJ\b\u0010\u0013\u001a\u00020\u0014H\u0016J\u0010\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0014H\u0016J\u0018\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00022\u0006\u0010\u0016\u001a\u00020\u0014H\u0016J\u0018\u0010\u001a\u001a\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0014H\u0016J\u0014\u0010\u001e\u001a\u00020\u00182\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\f0\u000bR\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012\u00a8\u0006 "}, d2 = {"Lorg/wordpress/android/ui/reader/subfilter/adapters/SubfilterListAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lorg/wordpress/android/ui/reader/subfilter/viewholders/SubfilterListItemViewHolder;", "uiHelpers", "Lorg/wordpress/android/ui/utils/UiHelpers;", "statsUtils", "Lorg/wordpress/android/ui/stats/refresh/utils/StatsUtils;", "seenUnseenWithCounterFeatureConfig", "Lorg/wordpress/android/util/config/SeenUnseenWithCounterFeatureConfig;", "(Lorg/wordpress/android/ui/utils/UiHelpers;Lorg/wordpress/android/ui/stats/refresh/utils/StatsUtils;Lorg/wordpress/android/util/config/SeenUnseenWithCounterFeatureConfig;)V", "items", "", "Lorg/wordpress/android/ui/reader/subfilter/SubfilterListItem;", "getSeenUnseenWithCounterFeatureConfig", "()Lorg/wordpress/android/util/config/SeenUnseenWithCounterFeatureConfig;", "getStatsUtils", "()Lorg/wordpress/android/ui/stats/refresh/utils/StatsUtils;", "getUiHelpers", "()Lorg/wordpress/android/ui/utils/UiHelpers;", "getItemCount", "", "getItemViewType", "position", "onBindViewHolder", "", "holder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "update", "newItems", "org.wordpress.android_wordpressJalapenoDebug"})
public final class SubfilterListAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<org.wordpress.android.ui.reader.subfilter.viewholders.SubfilterListItemViewHolder> {
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.ui.utils.UiHelpers uiHelpers = null;
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.ui.stats.refresh.utils.StatsUtils statsUtils = null;
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.util.config.SeenUnseenWithCounterFeatureConfig seenUnseenWithCounterFeatureConfig = null;
    private java.util.List<? extends org.wordpress.android.ui.reader.subfilter.SubfilterListItem> items;
    
    public SubfilterListAdapter(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.utils.UiHelpers uiHelpers, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.utils.StatsUtils statsUtils, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.config.SeenUnseenWithCounterFeatureConfig seenUnseenWithCounterFeatureConfig) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.utils.UiHelpers getUiHelpers() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.stats.refresh.utils.StatsUtils getStatsUtils() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.util.config.SeenUnseenWithCounterFeatureConfig getSeenUnseenWithCounterFeatureConfig() {
        return null;
    }
    
    public final void update(@org.jetbrains.annotations.NotNull()
    java.util.List<? extends org.wordpress.android.ui.reader.subfilter.SubfilterListItem> newItems) {
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.subfilter.viewholders.SubfilterListItemViewHolder holder, int position) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public org.wordpress.android.ui.reader.subfilter.viewholders.SubfilterListItemViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public int getItemViewType(int position) {
        return 0;
    }
}