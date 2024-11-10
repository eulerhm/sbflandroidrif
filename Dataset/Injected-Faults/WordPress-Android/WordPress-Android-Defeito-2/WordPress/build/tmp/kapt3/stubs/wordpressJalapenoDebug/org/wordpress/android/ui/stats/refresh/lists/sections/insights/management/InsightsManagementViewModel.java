package org.wordpress.android.ui.stats.refresh.lists.sections.insights.management;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u00010BE\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u00a2\u0006\u0002\u0010\u000fJ\b\u0010&\u001a\u00020\u0016H\u0002J\b\u0010\'\u001a\u00020\u0016H\u0002J\u0006\u0010(\u001a\u00020\u0016J\u0010\u0010)\u001a\u00020\u00162\u0006\u0010*\u001a\u00020\u001bH\u0002J\u0006\u0010+\u001a\u00020\u0016J\u0015\u0010,\u001a\u00020\u00162\b\u0010-\u001a\u0004\u0018\u00010.\u00a2\u0006\u0002\u0010/R\u001a\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001aX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120\u001d\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00160\u001d\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001fR\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u000e\u0010$\u001a\u00020\u0018X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0017\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00180\u001d\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u00061"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/management/InsightsManagementViewModel;", "Lorg/wordpress/android/viewmodel/ScopedViewModel;", "mainDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "defaultDispatcher", "insightsUseCase", "Lorg/wordpress/android/ui/stats/refresh/lists/BaseListUseCase;", "siteProvider", "Lorg/wordpress/android/ui/stats/refresh/utils/StatsSiteProvider;", "statsStore", "Lorg/wordpress/android/fluxc/store/StatsStore;", "insightsManagementMapper", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/management/InsightsManagementMapper;", "analyticsTrackerWrapper", "Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;", "(Lkotlinx/coroutines/CoroutineDispatcher;Lkotlinx/coroutines/CoroutineDispatcher;Lorg/wordpress/android/ui/stats/refresh/lists/BaseListUseCase;Lorg/wordpress/android/ui/stats/refresh/utils/StatsSiteProvider;Lorg/wordpress/android/fluxc/store/StatsStore;Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/management/InsightsManagementMapper;Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;)V", "_addedInsights", "Landroidx/lifecycle/MutableLiveData;", "", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/management/InsightsManagementViewModel$InsightListItem;", "_closeInsightsManagement", "Lorg/wordpress/android/viewmodel/SingleLiveEvent;", "", "_isMenuVisible", "", "addedInsightTypes", "", "Lorg/wordpress/android/fluxc/store/StatsStore$InsightType;", "addedInsights", "Landroidx/lifecycle/LiveData;", "getAddedInsights", "()Landroidx/lifecycle/LiveData;", "closeInsightsManagement", "getCloseInsightsManagement", "getInsightsUseCase", "()Lorg/wordpress/android/ui/stats/refresh/lists/BaseListUseCase;", "isInitialized", "isMenuVisible", "displayInsights", "loadInsights", "onBackPressed", "onItemButtonClicked", "insight", "onSaveInsights", "start", "localSiteId", "", "(Ljava/lang/Integer;)V", "InsightListItem", "org.wordpress.android_wordpressJalapenoDebug"})
public final class InsightsManagementViewModel extends org.wordpress.android.viewmodel.ScopedViewModel {
    private final kotlinx.coroutines.CoroutineDispatcher mainDispatcher = null;
    private final kotlinx.coroutines.CoroutineDispatcher defaultDispatcher = null;
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.ui.stats.refresh.lists.BaseListUseCase insightsUseCase = null;
    private final org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider siteProvider = null;
    private final org.wordpress.android.fluxc.store.StatsStore statsStore = null;
    private final org.wordpress.android.ui.stats.refresh.lists.sections.insights.management.InsightsManagementMapper insightsManagementMapper = null;
    private final org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTrackerWrapper = null;
    private final androidx.lifecycle.MutableLiveData<java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.insights.management.InsightsManagementViewModel.InsightListItem>> _addedInsights = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.insights.management.InsightsManagementViewModel.InsightListItem>> addedInsights = null;
    private final org.wordpress.android.viewmodel.SingleLiveEvent<kotlin.Unit> _closeInsightsManagement = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<kotlin.Unit> closeInsightsManagement = null;
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> _isMenuVisible = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Boolean> isMenuVisible = null;
    private final java.util.Set<org.wordpress.android.fluxc.store.StatsStore.InsightType> addedInsightTypes = null;
    private boolean isInitialized = false;
    
    @javax.inject.Inject()
    public InsightsManagementViewModel(@org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "UI_THREAD")
    kotlinx.coroutines.CoroutineDispatcher mainDispatcher, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher defaultDispatcher, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "InsightsUseCase")
    org.wordpress.android.ui.stats.refresh.lists.BaseListUseCase insightsUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider siteProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.StatsStore statsStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.sections.insights.management.InsightsManagementMapper insightsManagementMapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTrackerWrapper) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.stats.refresh.lists.BaseListUseCase getInsightsUseCase() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.insights.management.InsightsManagementViewModel.InsightListItem>> getAddedInsights() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<kotlin.Unit> getCloseInsightsManagement() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Boolean> isMenuVisible() {
        return null;
    }
    
    public final void start(@org.jetbrains.annotations.Nullable()
    java.lang.Integer localSiteId) {
    }
    
    private final void loadInsights() {
    }
    
    private final void displayInsights() {
    }
    
    public final void onSaveInsights() {
    }
    
    private final void onItemButtonClicked(org.wordpress.android.fluxc.store.StatsStore.InsightType insight) {
    }
    
    public final void onBackPressed() {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0007\b\tB\u000f\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0002\n\u000b\u00a8\u0006\f"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/management/InsightsManagementViewModel$InsightListItem;", "", "type", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/management/InsightsManagementViewModel$InsightListItem$Type;", "(Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/management/InsightsManagementViewModel$InsightListItem$Type;)V", "getType", "()Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/management/InsightsManagementViewModel$InsightListItem$Type;", "Header", "InsightModel", "Type", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/management/InsightsManagementViewModel$InsightListItem$Header;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/management/InsightsManagementViewModel$InsightListItem$InsightModel;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class InsightListItem {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.stats.refresh.lists.sections.insights.management.InsightsManagementViewModel.InsightListItem.Type type = null;
        
        private InsightListItem(org.wordpress.android.ui.stats.refresh.lists.sections.insights.management.InsightsManagementViewModel.InsightListItem.Type type) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.stats.refresh.lists.sections.insights.management.InsightsManagementViewModel.InsightListItem.Type getType() {
            return null;
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004\u00a8\u0006\u0005"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/management/InsightsManagementViewModel$InsightListItem$Type;", "", "(Ljava/lang/String;I)V", "HEADER", "INSIGHT", "org.wordpress.android_wordpressJalapenoDebug"})
        public static enum Type {
            /*public static final*/ HEADER /* = new HEADER() */,
            /*public static final*/ INSIGHT /* = new INSIGHT() */;
            
            Type() {
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u000e\u001a\u00020\u000fH\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/management/InsightsManagementViewModel$InsightListItem$Header;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/management/InsightsManagementViewModel$InsightListItem;", "text", "", "(I)V", "getText", "()I", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Header extends org.wordpress.android.ui.stats.refresh.lists.sections.insights.management.InsightsManagementViewModel.InsightListItem {
            private final int text = 0;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.stats.refresh.lists.sections.insights.management.InsightsManagementViewModel.InsightListItem.Header copy(@androidx.annotation.StringRes()
            int text) {
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
            
            public Header(@androidx.annotation.StringRes()
            int text) {
                super(null);
            }
            
            public final int component1() {
                return 0;
            }
            
            public final int getText() {
                return 0;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001:\u0001!B\'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\t\u0010\u0014\u001a\u00020\u0003H\u00c6\u0003J\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0002\u0010\u000eJ\t\u0010\u0016\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\u0017\u001a\u00020\tH\u00c6\u0003J8\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tH\u00c6\u0001\u00a2\u0006\u0002\u0010\u0019J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u00d6\u0003J\t\u0010\u001e\u001a\u00020\u0005H\u00d6\u0001J\t\u0010\u001f\u001a\u00020 H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013\u00a8\u0006\""}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/management/InsightsManagementViewModel$InsightListItem$InsightModel;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/management/InsightsManagementViewModel$InsightListItem;", "insightType", "Lorg/wordpress/android/fluxc/store/StatsStore$InsightType;", "name", "", "status", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/management/InsightsManagementViewModel$InsightListItem$InsightModel$Status;", "onClick", "Lorg/wordpress/android/ui/utils/ListItemInteraction;", "(Lorg/wordpress/android/fluxc/store/StatsStore$InsightType;Ljava/lang/Integer;Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/management/InsightsManagementViewModel$InsightListItem$InsightModel$Status;Lorg/wordpress/android/ui/utils/ListItemInteraction;)V", "getInsightType", "()Lorg/wordpress/android/fluxc/store/StatsStore$InsightType;", "getName", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getOnClick", "()Lorg/wordpress/android/ui/utils/ListItemInteraction;", "getStatus", "()Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/management/InsightsManagementViewModel$InsightListItem$InsightModel$Status;", "component1", "component2", "component3", "component4", "copy", "(Lorg/wordpress/android/fluxc/store/StatsStore$InsightType;Ljava/lang/Integer;Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/management/InsightsManagementViewModel$InsightListItem$InsightModel$Status;Lorg/wordpress/android/ui/utils/ListItemInteraction;)Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/management/InsightsManagementViewModel$InsightListItem$InsightModel;", "equals", "", "other", "", "hashCode", "toString", "", "Status", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class InsightModel extends org.wordpress.android.ui.stats.refresh.lists.sections.insights.management.InsightsManagementViewModel.InsightListItem {
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.fluxc.store.StatsStore.InsightType insightType = null;
            @org.jetbrains.annotations.Nullable()
            private final java.lang.Integer name = null;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.stats.refresh.lists.sections.insights.management.InsightsManagementViewModel.InsightListItem.InsightModel.Status status = null;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.utils.ListItemInteraction onClick = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.stats.refresh.lists.sections.insights.management.InsightsManagementViewModel.InsightListItem.InsightModel copy(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.fluxc.store.StatsStore.InsightType insightType, @org.jetbrains.annotations.Nullable()
            java.lang.Integer name, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.stats.refresh.lists.sections.insights.management.InsightsManagementViewModel.InsightListItem.InsightModel.Status status, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.utils.ListItemInteraction onClick) {
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
            
            public InsightModel(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.fluxc.store.StatsStore.InsightType insightType, @org.jetbrains.annotations.Nullable()
            java.lang.Integer name, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.stats.refresh.lists.sections.insights.management.InsightsManagementViewModel.InsightListItem.InsightModel.Status status, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.utils.ListItemInteraction onClick) {
                super(null);
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.fluxc.store.StatsStore.InsightType component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.fluxc.store.StatsStore.InsightType getInsightType() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final java.lang.Integer component2() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final java.lang.Integer getName() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.stats.refresh.lists.sections.insights.management.InsightsManagementViewModel.InsightListItem.InsightModel.Status component3() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.stats.refresh.lists.sections.insights.management.InsightsManagementViewModel.InsightListItem.InsightModel.Status getStatus() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.utils.ListItemInteraction component4() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.utils.ListItemInteraction getOnClick() {
                return null;
            }
            
            @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004\u00a8\u0006\u0005"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/management/InsightsManagementViewModel$InsightListItem$InsightModel$Status;", "", "(Ljava/lang/String;I)V", "ADDED", "REMOVED", "org.wordpress.android_wordpressJalapenoDebug"})
            public static enum Status {
                /*public static final*/ ADDED /* = new ADDED() */,
                /*public static final*/ REMOVED /* = new REMOVED() */;
                
                Status() {
                }
            }
        }
    }
}