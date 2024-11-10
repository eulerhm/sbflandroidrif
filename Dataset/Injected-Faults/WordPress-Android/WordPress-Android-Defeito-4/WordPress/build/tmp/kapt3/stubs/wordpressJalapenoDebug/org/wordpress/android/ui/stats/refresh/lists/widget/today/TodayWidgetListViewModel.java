package org.wordpress.android.ui.stats.refresh.lists.widget.today;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001%B/\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0002\u0010\fJ\u001e\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u000eH\u0002J)\u0010\u001e\u001a\u00020\u001f2!\u0010 \u001a\u001d\u0012\u0013\u0012\u00110\u000e\u00a2\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u001f0!J\u001e\u0010$\u001a\u00020\u001f2\u0006\u0010\u0019\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\r\u001a\u00020\u000eR\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0012\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00140\u0018X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u0019\u001a\u0004\u0018\u00010\u000eX\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\u000fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006&"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/widget/today/TodayWidgetListViewModel;", "", "siteStore", "Lorg/wordpress/android/fluxc/store/SiteStore;", "todayInsightsStore", "Lorg/wordpress/android/fluxc/store/stats/insights/TodayInsightsStore;", "resourceProvider", "Lorg/wordpress/android/viewmodel/ResourceProvider;", "appPrefsWrapper", "Lorg/wordpress/android/ui/prefs/AppPrefsWrapper;", "statsUtils", "Lorg/wordpress/android/ui/stats/refresh/utils/StatsUtils;", "(Lorg/wordpress/android/fluxc/store/SiteStore;Lorg/wordpress/android/fluxc/store/stats/insights/TodayInsightsStore;Lorg/wordpress/android/viewmodel/ResourceProvider;Lorg/wordpress/android/ui/prefs/AppPrefsWrapper;Lorg/wordpress/android/ui/stats/refresh/utils/StatsUtils;)V", "appWidgetId", "", "Ljava/lang/Integer;", "colorMode", "Lorg/wordpress/android/ui/stats/refresh/lists/widget/configuration/StatsColorSelectionViewModel$Color;", "data", "", "Lorg/wordpress/android/ui/stats/refresh/lists/widget/today/TodayWidgetListViewModel$TodayItemUiModel;", "getData", "()Ljava/util/List;", "mutableData", "", "siteId", "buildListItemUiModel", "domainModel", "Lorg/wordpress/android/fluxc/model/stats/VisitsModel;", "localSiteId", "onDataSetChanged", "", "onError", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "start", "TodayItemUiModel", "org.wordpress.android_wordpressJalapenoDebug"})
public final class TodayWidgetListViewModel {
    private final org.wordpress.android.fluxc.store.SiteStore siteStore = null;
    private final org.wordpress.android.fluxc.store.stats.insights.TodayInsightsStore todayInsightsStore = null;
    private final org.wordpress.android.viewmodel.ResourceProvider resourceProvider = null;
    private final org.wordpress.android.ui.prefs.AppPrefsWrapper appPrefsWrapper = null;
    private final org.wordpress.android.ui.stats.refresh.utils.StatsUtils statsUtils = null;
    private java.lang.Integer siteId;
    private org.wordpress.android.ui.stats.refresh.lists.widget.configuration.StatsColorSelectionViewModel.Color colorMode = org.wordpress.android.ui.stats.refresh.lists.widget.configuration.StatsColorSelectionViewModel.Color.LIGHT;
    private java.lang.Integer appWidgetId;
    private final java.util.List<org.wordpress.android.ui.stats.refresh.lists.widget.today.TodayWidgetListViewModel.TodayItemUiModel> mutableData = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<org.wordpress.android.ui.stats.refresh.lists.widget.today.TodayWidgetListViewModel.TodayItemUiModel> data = null;
    
    @javax.inject.Inject()
    public TodayWidgetListViewModel(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.SiteStore siteStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.stats.insights.TodayInsightsStore todayInsightsStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.ResourceProvider resourceProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.prefs.AppPrefsWrapper appPrefsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.utils.StatsUtils statsUtils) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<org.wordpress.android.ui.stats.refresh.lists.widget.today.TodayWidgetListViewModel.TodayItemUiModel> getData() {
        return null;
    }
    
    public final void start(int siteId, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.widget.configuration.StatsColorSelectionViewModel.Color colorMode, int appWidgetId) {
    }
    
    public final void onDataSetChanged(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> onError) {
    }
    
    private final java.util.List<org.wordpress.android.ui.stats.refresh.lists.widget.today.TodayWidgetListViewModel.TodayItemUiModel> buildListItemUiModel(org.wordpress.android.fluxc.model.stats.VisitsModel domainModel, int localSiteId) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\'\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0010\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0011\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u0012\u001a\u00020\u0006H\u00c6\u0003J1\u0010\u0013\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0006H\u00c6\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0017\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u0018\u001a\u00020\u0006H\u00d6\u0001R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\n\u00a8\u0006\u0019"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/widget/today/TodayWidgetListViewModel$TodayItemUiModel;", "", "layout", "", "localSiteId", "key", "", "value", "(IILjava/lang/String;Ljava/lang/String;)V", "getKey", "()Ljava/lang/String;", "getLayout", "()I", "getLocalSiteId", "getValue", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class TodayItemUiModel {
        private final int layout = 0;
        private final int localSiteId = 0;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String key = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String value = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.stats.refresh.lists.widget.today.TodayWidgetListViewModel.TodayItemUiModel copy(@androidx.annotation.LayoutRes()
        int layout, int localSiteId, @org.jetbrains.annotations.NotNull()
        java.lang.String key, @org.jetbrains.annotations.NotNull()
        java.lang.String value) {
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
        
        public TodayItemUiModel(@androidx.annotation.LayoutRes()
        int layout, int localSiteId, @org.jetbrains.annotations.NotNull()
        java.lang.String key, @org.jetbrains.annotations.NotNull()
        java.lang.String value) {
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
        public final java.lang.String getKey() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component4() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getValue() {
            return null;
        }
    }
}