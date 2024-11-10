package org.wordpress.android.ui.stats.refresh.lists.widget.views;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010!\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001+B7\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u00a2\u0006\u0002\u0010\u000eJ.\u0010\u001e\u001a\u00020\u00162\u0006\u0010\u001f\u001a\u00020\u00102\u0006\u0010 \u001a\u00020!2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020!0\u00152\u0006\u0010#\u001a\u00020\u0010H\u0002J)\u0010$\u001a\u00020%2!\u0010&\u001a\u001d\u0012\u0013\u0012\u00110\u0010\u00a2\u0006\f\b(\u0012\b\b)\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020%0\'J&\u0010*\u001a\u00020%2\u0006\u0010\u001d\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u000f\u001a\u00020\u0010R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00160\u001cX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u001d\u001a\u0004\u0018\u00010\u0010X\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\u0011R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006,"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/widget/views/ViewsWidgetListViewModel;", "", "siteStore", "Lorg/wordpress/android/fluxc/store/SiteStore;", "visitsAndViewsStore", "Lorg/wordpress/android/fluxc/store/stats/time/VisitsAndViewsStore;", "overviewMapper", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/granular/usecases/OverviewMapper;", "resourceProvider", "Lorg/wordpress/android/viewmodel/ResourceProvider;", "statsDateFormatter", "Lorg/wordpress/android/ui/stats/refresh/utils/StatsDateFormatter;", "appPrefsWrapper", "Lorg/wordpress/android/ui/prefs/AppPrefsWrapper;", "(Lorg/wordpress/android/fluxc/store/SiteStore;Lorg/wordpress/android/fluxc/store/stats/time/VisitsAndViewsStore;Lorg/wordpress/android/ui/stats/refresh/lists/sections/granular/usecases/OverviewMapper;Lorg/wordpress/android/viewmodel/ResourceProvider;Lorg/wordpress/android/ui/stats/refresh/utils/StatsDateFormatter;Lorg/wordpress/android/ui/prefs/AppPrefsWrapper;)V", "appWidgetId", "", "Ljava/lang/Integer;", "colorMode", "Lorg/wordpress/android/ui/stats/refresh/lists/widget/configuration/StatsColorSelectionViewModel$Color;", "data", "", "Lorg/wordpress/android/ui/stats/refresh/lists/widget/views/ViewsWidgetListViewModel$ListItemUiModel;", "getData", "()Ljava/util/List;", "isWideView", "", "mutableData", "", "siteId", "buildListItemUiModel", "position", "selectedItem", "Lorg/wordpress/android/fluxc/model/stats/time/VisitsAndViewsModel$PeriodData;", "periods", "localSiteId", "onDataSetChanged", "", "onError", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "start", "ListItemUiModel", "org.wordpress.android_wordpressJalapenoDebug"})
public final class ViewsWidgetListViewModel {
    private final org.wordpress.android.fluxc.store.SiteStore siteStore = null;
    private final org.wordpress.android.fluxc.store.stats.time.VisitsAndViewsStore visitsAndViewsStore = null;
    private final org.wordpress.android.ui.stats.refresh.lists.sections.granular.usecases.OverviewMapper overviewMapper = null;
    private final org.wordpress.android.viewmodel.ResourceProvider resourceProvider = null;
    private final org.wordpress.android.ui.stats.refresh.utils.StatsDateFormatter statsDateFormatter = null;
    private final org.wordpress.android.ui.prefs.AppPrefsWrapper appPrefsWrapper = null;
    private java.lang.Integer siteId;
    private org.wordpress.android.ui.stats.refresh.lists.widget.configuration.StatsColorSelectionViewModel.Color colorMode = org.wordpress.android.ui.stats.refresh.lists.widget.configuration.StatsColorSelectionViewModel.Color.LIGHT;
    private boolean isWideView = true;
    private java.lang.Integer appWidgetId;
    private final java.util.List<org.wordpress.android.ui.stats.refresh.lists.widget.views.ViewsWidgetListViewModel.ListItemUiModel> mutableData = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<org.wordpress.android.ui.stats.refresh.lists.widget.views.ViewsWidgetListViewModel.ListItemUiModel> data = null;
    
    @javax.inject.Inject()
    public ViewsWidgetListViewModel(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.SiteStore siteStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.stats.time.VisitsAndViewsStore visitsAndViewsStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.sections.granular.usecases.OverviewMapper overviewMapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.ResourceProvider resourceProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.utils.StatsDateFormatter statsDateFormatter, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.prefs.AppPrefsWrapper appPrefsWrapper) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<org.wordpress.android.ui.stats.refresh.lists.widget.views.ViewsWidgetListViewModel.ListItemUiModel> getData() {
        return null;
    }
    
    public final void start(int siteId, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.widget.configuration.StatsColorSelectionViewModel.Color colorMode, boolean isWideView, int appWidgetId) {
    }
    
    public final void onDataSetChanged(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> onError) {
    }
    
    private final org.wordpress.android.ui.stats.refresh.lists.widget.views.ViewsWidgetListViewModel.ListItemUiModel buildListItemUiModel(int position, org.wordpress.android.fluxc.model.stats.time.VisitsAndViewsModel.PeriodData selectedItem, java.util.List<org.wordpress.android.fluxc.model.stats.time.VisitsAndViewsModel.PeriodData> periods, int localSiteId) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u001e\b\u0086\b\u0018\u00002\u00020\u0001BS\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\b\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\f\u001a\u00020\u0005\u0012\u0006\u0010\r\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u000eJ\t\u0010\u0018\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0019\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001b\u001a\u00020\bH\u00c6\u0003J\t\u0010\u001c\u001a\u00020\bH\u00c6\u0003J\t\u0010\u001d\u001a\u00020\bH\u00c6\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\t\u0010\u001f\u001a\u00020\u0005H\u00c6\u0003J\t\u0010 \u001a\u00020\u0003H\u00c6\u0003Je\u0010!\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\b2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\f\u001a\u00020\u00052\b\b\u0002\u0010\r\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\"\u001a\u00020\b2\b\u0010#\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010$\u001a\u00020\u0003H\u00d6\u0001J\t\u0010%\u001a\u00020\u0005H\u00d6\u0001R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\t\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0011R\u0011\u0010\n\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0011R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\r\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0011\u0010\f\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0010\u00a8\u0006&"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/widget/views/ViewsWidgetListViewModel$ListItemUiModel;", "", "layout", "", "key", "", "value", "isPositiveChangeVisible", "", "isNegativeChangeVisible", "isNeutralChangeVisible", "change", "period", "localSiteId", "(ILjava/lang/String;Ljava/lang/String;ZZZLjava/lang/String;Ljava/lang/String;I)V", "getChange", "()Ljava/lang/String;", "()Z", "getKey", "getLayout", "()I", "getLocalSiteId", "getPeriod", "getValue", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class ListItemUiModel {
        private final int layout = 0;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String key = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String value = null;
        private final boolean isPositiveChangeVisible = false;
        private final boolean isNegativeChangeVisible = false;
        private final boolean isNeutralChangeVisible = false;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String change = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String period = null;
        private final int localSiteId = 0;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.stats.refresh.lists.widget.views.ViewsWidgetListViewModel.ListItemUiModel copy(@androidx.annotation.LayoutRes()
        int layout, @org.jetbrains.annotations.NotNull()
        java.lang.String key, @org.jetbrains.annotations.NotNull()
        java.lang.String value, boolean isPositiveChangeVisible, boolean isNegativeChangeVisible, boolean isNeutralChangeVisible, @org.jetbrains.annotations.Nullable()
        java.lang.String change, @org.jetbrains.annotations.NotNull()
        java.lang.String period, int localSiteId) {
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
        
        public ListItemUiModel(@androidx.annotation.LayoutRes()
        int layout, @org.jetbrains.annotations.NotNull()
        java.lang.String key, @org.jetbrains.annotations.NotNull()
        java.lang.String value, boolean isPositiveChangeVisible, boolean isNegativeChangeVisible, boolean isNeutralChangeVisible, @org.jetbrains.annotations.Nullable()
        java.lang.String change, @org.jetbrains.annotations.NotNull()
        java.lang.String period, int localSiteId) {
            super();
        }
        
        public final int component1() {
            return 0;
        }
        
        public final int getLayout() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getKey() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getValue() {
            return null;
        }
        
        public final boolean component4() {
            return false;
        }
        
        public final boolean isPositiveChangeVisible() {
            return false;
        }
        
        public final boolean component5() {
            return false;
        }
        
        public final boolean isNegativeChangeVisible() {
            return false;
        }
        
        public final boolean component6() {
            return false;
        }
        
        public final boolean isNeutralChangeVisible() {
            return false;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component7() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getChange() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component8() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getPeriod() {
            return null;
        }
        
        public final int component9() {
            return 0;
        }
        
        public final int getLocalSiteId() {
            return 0;
        }
    }
}