package org.wordpress.android.ui.jetpack.scan.history;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u00014B!\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u001c\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020 0\u001f2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\"0\u001fH\u0002J$\u0010#\u001a\b\u0012\u0004\u0012\u00020$0\u001f2\f\u0010%\u001a\b\u0012\u0004\u0012\u00020$0\u001f2\u0006\u0010&\u001a\u00020\'H\u0002J\u0016\u0010(\u001a\b\u0012\u0004\u0012\u00020)0\u001f2\u0006\u0010&\u001a\u00020\'H\u0002J\u001c\u0010*\u001a\b\u0012\u0004\u0012\u00020\"0\u001f2\f\u0010%\u001a\b\u0012\u0004\u0012\u00020$0\u001fH\u0002J\u0010\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020.H\u0002J\b\u0010/\u001a\u00020,H\u0002J\u001e\u00100\u001a\u00020,2\u0006\u0010&\u001a\u00020\'2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u00101\u001a\u000202J\u001e\u00103\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00132\u0006\u00101\u001a\u0002022\u0006\u0010&\u001a\u00020\'H\u0002R\u001a\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0016\u001a\u00020\u0017X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u0017\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0015\u00a8\u00065"}, d2 = {"Lorg/wordpress/android/ui/jetpack/scan/history/ScanHistoryListViewModel;", "Lorg/wordpress/android/viewmodel/ScopedViewModel;", "scanThreatItemBuilder", "Lorg/wordpress/android/ui/jetpack/scan/builders/ThreatItemBuilder;", "scanTracker", "Lorg/wordpress/android/util/analytics/ScanTracker;", "mainDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Lorg/wordpress/android/ui/jetpack/scan/builders/ThreatItemBuilder;Lorg/wordpress/android/util/analytics/ScanTracker;Lkotlinx/coroutines/CoroutineDispatcher;)V", "_navigation", "Landroidx/lifecycle/MutableLiveData;", "Lorg/wordpress/android/viewmodel/Event;", "Lorg/wordpress/android/ui/jetpack/scan/ScanNavigationEvents$ShowThreatDetails;", "_uiState", "Landroidx/lifecycle/MediatorLiveData;", "Lorg/wordpress/android/ui/jetpack/scan/history/ScanHistoryListViewModel$ScanHistoryUiState;", "isStarted", "", "navigation", "Landroidx/lifecycle/LiveData;", "getNavigation", "()Landroidx/lifecycle/LiveData;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "getSite", "()Lorg/wordpress/android/fluxc/model/SiteModel;", "setSite", "(Lorg/wordpress/android/fluxc/model/SiteModel;)V", "uiState", "getUiState", "addDateHeaders", "", "Lorg/wordpress/android/ui/jetpack/scan/ScanListItemState;", "threatItemList", "Lorg/wordpress/android/ui/jetpack/scan/ScanListItemState$ThreatItemState;", "filterByTabType", "Lorg/wordpress/android/fluxc/model/scan/threat/ThreatModel;", "threatList", "tabType", "Lorg/wordpress/android/ui/jetpack/scan/history/ScanHistoryViewModel$ScanHistoryTabType;", "mapTabTypeToThreatStatuses", "Lorg/wordpress/android/fluxc/model/scan/threat/ThreatModel$ThreatStatus;", "mapToThreatUiStateList", "onItemClicked", "", "threatId", "", "showLoadingState", "start", "parentViewModel", "Lorg/wordpress/android/ui/jetpack/scan/history/ScanHistoryViewModel;", "transformThreatsToUiState", "ScanHistoryUiState", "org.wordpress.android_wordpressJalapenoDebug"})
public final class ScanHistoryListViewModel extends org.wordpress.android.viewmodel.ScopedViewModel {
    private final org.wordpress.android.ui.jetpack.scan.builders.ThreatItemBuilder scanThreatItemBuilder = null;
    private final org.wordpress.android.util.analytics.ScanTracker scanTracker = null;
    private final kotlinx.coroutines.CoroutineDispatcher mainDispatcher = null;
    private boolean isStarted = false;
    private final androidx.lifecycle.MediatorLiveData<org.wordpress.android.ui.jetpack.scan.history.ScanHistoryListViewModel.ScanHistoryUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.ui.jetpack.scan.history.ScanHistoryListViewModel.ScanHistoryUiState> uiState = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.jetpack.scan.ScanNavigationEvents.ShowThreatDetails>> _navigation = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.jetpack.scan.ScanNavigationEvents.ShowThreatDetails>> navigation = null;
    public org.wordpress.android.fluxc.model.SiteModel site;
    
    @javax.inject.Inject()
    public ScanHistoryListViewModel(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.jetpack.scan.builders.ThreatItemBuilder scanThreatItemBuilder, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.ScanTracker scanTracker, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "UI_THREAD")
    kotlinx.coroutines.CoroutineDispatcher mainDispatcher) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.ui.jetpack.scan.history.ScanHistoryListViewModel.ScanHistoryUiState> getUiState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.jetpack.scan.ScanNavigationEvents.ShowThreatDetails>> getNavigation() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.fluxc.model.SiteModel getSite() {
        return null;
    }
    
    public final void setSite(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel p0) {
    }
    
    public final void start(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.jetpack.scan.history.ScanHistoryViewModel.ScanHistoryTabType tabType, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.jetpack.scan.history.ScanHistoryViewModel parentViewModel) {
    }
    
    private final void showLoadingState() {
    }
    
    private final androidx.lifecycle.LiveData<org.wordpress.android.ui.jetpack.scan.history.ScanHistoryListViewModel.ScanHistoryUiState> transformThreatsToUiState(org.wordpress.android.ui.jetpack.scan.history.ScanHistoryViewModel parentViewModel, org.wordpress.android.ui.jetpack.scan.history.ScanHistoryViewModel.ScanHistoryTabType tabType) {
        return null;
    }
    
    private final java.util.List<org.wordpress.android.fluxc.model.scan.threat.ThreatModel> filterByTabType(java.util.List<? extends org.wordpress.android.fluxc.model.scan.threat.ThreatModel> threatList, org.wordpress.android.ui.jetpack.scan.history.ScanHistoryViewModel.ScanHistoryTabType tabType) {
        return null;
    }
    
    private final java.util.List<org.wordpress.android.ui.jetpack.scan.ScanListItemState.ThreatItemState> mapToThreatUiStateList(java.util.List<? extends org.wordpress.android.fluxc.model.scan.threat.ThreatModel> threatList) {
        return null;
    }
    
    private final java.util.List<org.wordpress.android.ui.jetpack.scan.ScanListItemState> addDateHeaders(java.util.List<org.wordpress.android.ui.jetpack.scan.ScanListItemState.ThreatItemState> threatItemList) {
        return null;
    }
    
    private final void onItemClicked(long threatId) {
    }
    
    private final java.util.List<org.wordpress.android.fluxc.model.scan.threat.ThreatModel.ThreatStatus> mapTabTypeToThreatStatuses(org.wordpress.android.ui.jetpack.scan.history.ScanHistoryViewModel.ScanHistoryTabType tabType) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\t\nB\u001b\b\u0004\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0005R\u0014\u0010\u0004\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007\u0082\u0001\u0002\u000b\f\u00a8\u0006\r"}, d2 = {"Lorg/wordpress/android/ui/jetpack/scan/history/ScanHistoryListViewModel$ScanHistoryUiState;", "", "emptyVisibility", "", "contentVisibility", "(ZZ)V", "getContentVisibility", "()Z", "getEmptyVisibility", "ContentUiState", "EmptyUiState", "Lorg/wordpress/android/ui/jetpack/scan/history/ScanHistoryListViewModel$ScanHistoryUiState$EmptyUiState;", "Lorg/wordpress/android/ui/jetpack/scan/history/ScanHistoryListViewModel$ScanHistoryUiState$ContentUiState;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class ScanHistoryUiState {
        private final boolean emptyVisibility = false;
        private final boolean contentVisibility = false;
        
        private ScanHistoryUiState(boolean emptyVisibility, boolean contentVisibility) {
            super();
        }
        
        public boolean getEmptyVisibility() {
            return false;
        }
        
        public boolean getContentVisibility() {
            return false;
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0001\u0003B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0001\u0004\u00a8\u0006\u0005"}, d2 = {"Lorg/wordpress/android/ui/jetpack/scan/history/ScanHistoryListViewModel$ScanHistoryUiState$EmptyUiState;", "Lorg/wordpress/android/ui/jetpack/scan/history/ScanHistoryListViewModel$ScanHistoryUiState;", "()V", "EmptyHistory", "Lorg/wordpress/android/ui/jetpack/scan/history/ScanHistoryListViewModel$ScanHistoryUiState$EmptyUiState$EmptyHistory;", "org.wordpress.android_wordpressJalapenoDebug"})
        public static abstract class EmptyUiState extends org.wordpress.android.ui.jetpack.scan.history.ScanHistoryListViewModel.ScanHistoryUiState {
            
            private EmptyUiState() {
                super(false, false);
            }
            
            @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\u00020\u00048\u0006X\u0087D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u000b"}, d2 = {"Lorg/wordpress/android/ui/jetpack/scan/history/ScanHistoryListViewModel$ScanHistoryUiState$EmptyUiState$EmptyHistory;", "Lorg/wordpress/android/ui/jetpack/scan/history/ScanHistoryListViewModel$ScanHistoryUiState$EmptyUiState;", "()V", "img", "", "getImg", "()I", "label", "Lorg/wordpress/android/ui/utils/UiString;", "getLabel", "()Lorg/wordpress/android/ui/utils/UiString;", "org.wordpress.android_wordpressJalapenoDebug"})
            public static final class EmptyHistory extends org.wordpress.android.ui.jetpack.scan.history.ScanHistoryListViewModel.ScanHistoryUiState.EmptyUiState {
                @org.jetbrains.annotations.NotNull()
                public static final org.wordpress.android.ui.jetpack.scan.history.ScanHistoryListViewModel.ScanHistoryUiState.EmptyUiState.EmptyHistory INSTANCE = null;
                @org.jetbrains.annotations.NotNull()
                private static final org.wordpress.android.ui.utils.UiString label = null;
                @androidx.annotation.DrawableRes()
                private static final int img = org.wordpress.android.R.drawable.img_illustration_empty_results_216dp;
                
                private EmptyHistory() {
                    super();
                }
                
                @org.jetbrains.annotations.NotNull()
                public final org.wordpress.android.ui.utils.UiString getLabel() {
                    return null;
                }
                
                public final int getImg() {
                    return 0;
                }
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\u0005J\u000f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\u0019\u0010\t\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u00d6\u0003J\t\u0010\u000e\u001a\u00020\u000fH\u00d6\u0001J\t\u0010\u0010\u001a\u00020\u0011H\u00d6\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\u0012"}, d2 = {"Lorg/wordpress/android/ui/jetpack/scan/history/ScanHistoryListViewModel$ScanHistoryUiState$ContentUiState;", "Lorg/wordpress/android/ui/jetpack/scan/history/ScanHistoryListViewModel$ScanHistoryUiState;", "items", "", "Lorg/wordpress/android/ui/jetpack/scan/ScanListItemState;", "(Ljava/util/List;)V", "getItems", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class ContentUiState extends org.wordpress.android.ui.jetpack.scan.history.ScanHistoryListViewModel.ScanHistoryUiState {
            @org.jetbrains.annotations.NotNull()
            private final java.util.List<org.wordpress.android.ui.jetpack.scan.ScanListItemState> items = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.jetpack.scan.history.ScanHistoryListViewModel.ScanHistoryUiState.ContentUiState copy(@org.jetbrains.annotations.NotNull()
            java.util.List<? extends org.wordpress.android.ui.jetpack.scan.ScanListItemState> items) {
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
            
            public ContentUiState(@org.jetbrains.annotations.NotNull()
            java.util.List<? extends org.wordpress.android.ui.jetpack.scan.ScanListItemState> items) {
                super(false, false);
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.util.List<org.wordpress.android.ui.jetpack.scan.ScanListItemState> component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.util.List<org.wordpress.android.ui.jetpack.scan.ScanListItemState> getItems() {
                return null;
            }
        }
    }
}