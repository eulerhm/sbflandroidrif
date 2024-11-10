package org.wordpress.android.ui.stats.refresh.lists.widget;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0002 !B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\b\u0010\u0012\u001a\u00020\nH\u0016J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\nH\u0016J\n\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\u0010\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0015\u001a\u00020\nH\u0016J\b\u0010\u0019\u001a\u00020\nH\u0016J\b\u0010\u001a\u001a\u00020\u001bH\u0016J\b\u0010\u001c\u001a\u00020\u001dH\u0016J\b\u0010\u001e\u001a\u00020\u001dH\u0016J\b\u0010\u001f\u001a\u00020\u001dH\u0016R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011\u00a8\u0006\""}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/widget/WidgetBlockListProvider;", "Landroid/widget/RemoteViewsService$RemoteViewsFactory;", "context", "Landroid/content/Context;", "viewModel", "Lorg/wordpress/android/ui/stats/refresh/lists/widget/WidgetBlockListProvider$WidgetBlockListViewModel;", "intent", "Landroid/content/Intent;", "(Landroid/content/Context;Lorg/wordpress/android/ui/stats/refresh/lists/widget/WidgetBlockListProvider$WidgetBlockListViewModel;Landroid/content/Intent;)V", "appWidgetId", "", "colorMode", "Lorg/wordpress/android/ui/stats/refresh/lists/widget/configuration/StatsColorSelectionViewModel$Color;", "getContext", "()Landroid/content/Context;", "siteId", "getViewModel", "()Lorg/wordpress/android/ui/stats/refresh/lists/widget/WidgetBlockListProvider$WidgetBlockListViewModel;", "getCount", "getItemId", "", "position", "getLoadingView", "Landroid/widget/RemoteViews;", "getViewAt", "getViewTypeCount", "hasStableIds", "", "onCreate", "", "onDataSetChanged", "onDestroy", "BlockItemUiModel", "WidgetBlockListViewModel", "org.wordpress.android_wordpressJalapenoDebug"})
public final class WidgetBlockListProvider implements android.widget.RemoteViewsService.RemoteViewsFactory {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.ui.stats.refresh.lists.widget.WidgetBlockListProvider.WidgetBlockListViewModel viewModel = null;
    private final org.wordpress.android.ui.stats.refresh.lists.widget.configuration.StatsColorSelectionViewModel.Color colorMode = null;
    private final int siteId = 0;
    private final int appWidgetId = 0;
    
    public WidgetBlockListProvider(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.widget.WidgetBlockListProvider.WidgetBlockListViewModel viewModel, @org.jetbrains.annotations.NotNull()
    android.content.Intent intent) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.content.Context getContext() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.stats.refresh.lists.widget.WidgetBlockListProvider.WidgetBlockListViewModel getViewModel() {
        return null;
    }
    
    @java.lang.Override()
    public void onCreate() {
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public android.widget.RemoteViews getLoadingView() {
        return null;
    }
    
    @java.lang.Override()
    public void onDataSetChanged() {
    }
    
    @java.lang.Override()
    public boolean hasStableIds() {
        return false;
    }
    
    @java.lang.Override()
    public int getViewTypeCount() {
        return 0;
    }
    
    @java.lang.Override()
    public void onDestroy() {
    }
    
    @java.lang.Override()
    public int getCount() {
        return 0;
    }
    
    @java.lang.Override()
    public long getItemId(int position) {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public android.widget.RemoteViews getViewAt(int position) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001BA\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\u0006\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0002\u0010\fJ\t\u0010\u0017\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0018\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0019\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u001b\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u001c\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\u000bH\u00c6\u0003JO\u0010\u001e\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\u00062\b\b\u0002\u0010\n\u001a\u00020\u000bH\u00c6\u0001J\u0013\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\"\u001a\u00020\u0003H\u00d6\u0001J\t\u0010#\u001a\u00020\u0006H\u00d6\u0001R\u0011\u0010\b\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\t\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000eR\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016\u00a8\u0006$"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/widget/WidgetBlockListProvider$BlockItemUiModel;", "", "layout", "", "localSiteId", "startKey", "", "startValue", "endKey", "endValue", "targetTimeframe", "Lorg/wordpress/android/ui/stats/StatsTimeframe;", "(IILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lorg/wordpress/android/ui/stats/StatsTimeframe;)V", "getEndKey", "()Ljava/lang/String;", "getEndValue", "getLayout", "()I", "getLocalSiteId", "getStartKey", "getStartValue", "getTargetTimeframe", "()Lorg/wordpress/android/ui/stats/StatsTimeframe;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class BlockItemUiModel {
        private final int layout = 0;
        private final int localSiteId = 0;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String startKey = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String startValue = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String endKey = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String endValue = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.stats.StatsTimeframe targetTimeframe = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.stats.refresh.lists.widget.WidgetBlockListProvider.BlockItemUiModel copy(@androidx.annotation.LayoutRes()
        int layout, int localSiteId, @org.jetbrains.annotations.NotNull()
        java.lang.String startKey, @org.jetbrains.annotations.NotNull()
        java.lang.String startValue, @org.jetbrains.annotations.NotNull()
        java.lang.String endKey, @org.jetbrains.annotations.NotNull()
        java.lang.String endValue, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.stats.StatsTimeframe targetTimeframe) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.lang.String toString() {
            return null;
        }
        
        public BlockItemUiModel(@androidx.annotation.LayoutRes()
        int layout, int localSiteId, @org.jetbrains.annotations.NotNull()
        java.lang.String startKey, @org.jetbrains.annotations.NotNull()
        java.lang.String startValue, @org.jetbrains.annotations.NotNull()
        java.lang.String endKey, @org.jetbrains.annotations.NotNull()
        java.lang.String endValue, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.stats.StatsTimeframe targetTimeframe) {
            super();
        }
        
        public final int component1() {
            return 0;
        }
        
        public final int getLayout() {
            return 0;
        }
        
        public final int component2() {
            return 0;
        }
        
        public final int getLocalSiteId() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getStartKey() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component4() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getStartValue() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component5() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getEndKey() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component6() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getEndValue() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.stats.StatsTimeframe component7() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.stats.StatsTimeframe getTargetTimeframe() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH&J \u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\rH&R\u0018\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/widget/WidgetBlockListProvider$WidgetBlockListViewModel;", "", "data", "", "Lorg/wordpress/android/ui/stats/refresh/lists/widget/WidgetBlockListProvider$BlockItemUiModel;", "getData", "()Ljava/util/List;", "onDataSetChanged", "", "context", "Landroid/content/Context;", "start", "siteId", "", "colorMode", "Lorg/wordpress/android/ui/stats/refresh/lists/widget/configuration/StatsColorSelectionViewModel$Color;", "appWidgetId", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract interface WidgetBlockListViewModel {
        
        @org.jetbrains.annotations.NotNull()
        public abstract java.util.List<org.wordpress.android.ui.stats.refresh.lists.widget.WidgetBlockListProvider.BlockItemUiModel> getData();
        
        public abstract void start(int siteId, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.stats.refresh.lists.widget.configuration.StatsColorSelectionViewModel.Color colorMode, int appWidgetId);
        
        public abstract void onDataSetChanged(@org.jetbrains.annotations.NotNull()
        android.content.Context context);
    }
}