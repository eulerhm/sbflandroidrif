package org.wordpress.android.ui.stats.refresh.lists.viewholders;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J \u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/viewholders/LoadingViewHolder;", "Lorg/wordpress/android/ui/stats/refresh/lists/viewholders/BaseStatsViewHolder;", "parent", "Landroid/view/ViewGroup;", "imageManager", "Lorg/wordpress/android/util/image/ImageManager;", "(Landroid/view/ViewGroup;Lorg/wordpress/android/util/image/ImageManager;)V", "getImageManager", "()Lorg/wordpress/android/util/image/ImageManager;", "list", "Landroidx/recyclerview/widget/RecyclerView;", "bind", "", "statsType", "Lorg/wordpress/android/fluxc/store/StatsStore$StatsType;", "items", "", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem;", "org.wordpress.android_wordpressJalapenoDebug"})
public final class LoadingViewHolder extends org.wordpress.android.ui.stats.refresh.lists.viewholders.BaseStatsViewHolder {
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.util.image.ImageManager imageManager = null;
    private final androidx.recyclerview.widget.RecyclerView list = null;
    
    public LoadingViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.image.ImageManager imageManager) {
        super(null, 0);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.util.image.ImageManager getImageManager() {
        return null;
    }
    
    @java.lang.Override()
    public void bind(@org.jetbrains.annotations.Nullable()
    org.wordpress.android.fluxc.store.StatsStore.StatsType statsType, @org.jetbrains.annotations.NotNull()
    java.util.List<? extends org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem> items) {
    }
}