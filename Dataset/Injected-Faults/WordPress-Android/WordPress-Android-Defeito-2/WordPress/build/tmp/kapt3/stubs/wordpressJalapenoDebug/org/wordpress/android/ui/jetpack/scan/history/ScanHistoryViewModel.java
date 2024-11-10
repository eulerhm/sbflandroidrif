package org.wordpress.android.ui.jetpack.scan.history;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0003%&\'B!\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0012\u0010\u001d\u001a\u00020\u001e2\b\b\u0002\u0010\u001f\u001a\u00020\u0010H\u0002J\b\u0010 \u001a\u00020\u001eH\u0002J\u000e\u0010!\u001a\u00020\u001e2\u0006\u0010\"\u001a\u00020#J\u000e\u0010$\u001a\u00020\u001e2\u0006\u0010\u0011\u001a\u00020\u0012R\u001a\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0011\u001a\u00020\u0012X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001d\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0017\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001a\u00a8\u0006("}, d2 = {"Lorg/wordpress/android/ui/jetpack/scan/history/ScanHistoryViewModel;", "Lorg/wordpress/android/viewmodel/ScopedViewModel;", "scanTracker", "Lorg/wordpress/android/util/analytics/ScanTracker;", "fetchScanHistoryUseCase", "Lorg/wordpress/android/ui/jetpack/scan/usecases/FetchScanHistoryUseCase;", "mainDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Lorg/wordpress/android/util/analytics/ScanTracker;Lorg/wordpress/android/ui/jetpack/scan/usecases/FetchScanHistoryUseCase;Lkotlinx/coroutines/CoroutineDispatcher;)V", "_threats", "Landroidx/lifecycle/MutableLiveData;", "", "Lorg/wordpress/android/fluxc/model/scan/threat/ThreatModel;", "_uiState", "Lorg/wordpress/android/ui/jetpack/scan/history/ScanHistoryViewModel$UiState;", "isStarted", "", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "getSite", "()Lorg/wordpress/android/fluxc/model/SiteModel;", "setSite", "(Lorg/wordpress/android/fluxc/model/SiteModel;)V", "threats", "Landroidx/lifecycle/LiveData;", "getThreats", "()Landroidx/lifecycle/LiveData;", "uiState", "getUiState", "fetchScanHistory", "", "isRetry", "onRetryClicked", "onTabSelected", "position", "", "start", "ScanHistoryTabType", "TabUiState", "UiState", "org.wordpress.android_wordpressJalapenoDebug"})
public final class ScanHistoryViewModel extends org.wordpress.android.viewmodel.ScopedViewModel {
    private final org.wordpress.android.util.analytics.ScanTracker scanTracker = null;
    private final org.wordpress.android.ui.jetpack.scan.usecases.FetchScanHistoryUseCase fetchScanHistoryUseCase = null;
    private final kotlinx.coroutines.CoroutineDispatcher mainDispatcher = null;
    private boolean isStarted = false;
    private final androidx.lifecycle.MutableLiveData<java.util.List<org.wordpress.android.fluxc.model.scan.threat.ThreatModel>> _threats = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<org.wordpress.android.fluxc.model.scan.threat.ThreatModel>> threats = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.ui.jetpack.scan.history.ScanHistoryViewModel.UiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.ui.jetpack.scan.history.ScanHistoryViewModel.UiState> uiState = null;
    public org.wordpress.android.fluxc.model.SiteModel site;
    
    @javax.inject.Inject()
    public ScanHistoryViewModel(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.ScanTracker scanTracker, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.jetpack.scan.usecases.FetchScanHistoryUseCase fetchScanHistoryUseCase, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "UI_THREAD")
    kotlinx.coroutines.CoroutineDispatcher mainDispatcher) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<org.wordpress.android.fluxc.model.scan.threat.ThreatModel>> getThreats() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.ui.jetpack.scan.history.ScanHistoryViewModel.UiState> getUiState() {
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
    org.wordpress.android.fluxc.model.SiteModel site) {
    }
    
    private final void fetchScanHistory(boolean isRetry) {
    }
    
    private final void onRetryClicked() {
    }
    
    public final void onTabSelected(int position) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\f\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0011\u001a\u00020\u0012H\u00d6\u0001J\t\u0010\u0013\u001a\u00020\u0014H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0015"}, d2 = {"Lorg/wordpress/android/ui/jetpack/scan/history/ScanHistoryViewModel$TabUiState;", "", "label", "Lorg/wordpress/android/ui/utils/UiString;", "type", "Lorg/wordpress/android/ui/jetpack/scan/history/ScanHistoryViewModel$ScanHistoryTabType;", "(Lorg/wordpress/android/ui/utils/UiString;Lorg/wordpress/android/ui/jetpack/scan/history/ScanHistoryViewModel$ScanHistoryTabType;)V", "getLabel", "()Lorg/wordpress/android/ui/utils/UiString;", "getType", "()Lorg/wordpress/android/ui/jetpack/scan/history/ScanHistoryViewModel$ScanHistoryTabType;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class TabUiState {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.utils.UiString label = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.jetpack.scan.history.ScanHistoryViewModel.ScanHistoryTabType type = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.jetpack.scan.history.ScanHistoryViewModel.TabUiState copy(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiString label, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.jetpack.scan.history.ScanHistoryViewModel.ScanHistoryTabType type) {
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
        
        public TabUiState(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiString label, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.jetpack.scan.history.ScanHistoryViewModel.ScanHistoryTabType type) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.UiString component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.UiString getLabel() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.jetpack.scan.history.ScanHistoryViewModel.ScanHistoryTabType component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.jetpack.scan.history.ScanHistoryViewModel.ScanHistoryTabType getType() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\t\nB\u001b\b\u0004\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007\u0082\u0001\u0002\u000b\f\u00a8\u0006\r"}, d2 = {"Lorg/wordpress/android/ui/jetpack/scan/history/ScanHistoryViewModel$UiState;", "", "contentVisible", "", "errorVisible", "(ZZ)V", "getContentVisible", "()Z", "getErrorVisible", "ContentUiState", "ErrorUiState", "Lorg/wordpress/android/ui/jetpack/scan/history/ScanHistoryViewModel$UiState$ContentUiState;", "Lorg/wordpress/android/ui/jetpack/scan/history/ScanHistoryViewModel$UiState$ErrorUiState;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class UiState {
        private final boolean contentVisible = false;
        private final boolean errorVisible = false;
        
        private UiState(boolean contentVisible, boolean errorVisible) {
            super();
        }
        
        public final boolean getContentVisible() {
            return false;
        }
        
        public final boolean getErrorVisible() {
            return false;
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\b"}, d2 = {"Lorg/wordpress/android/ui/jetpack/scan/history/ScanHistoryViewModel$UiState$ContentUiState;", "Lorg/wordpress/android/ui/jetpack/scan/history/ScanHistoryViewModel$UiState;", "()V", "tabs", "", "Lorg/wordpress/android/ui/jetpack/scan/history/ScanHistoryViewModel$TabUiState;", "getTabs", "()Ljava/util/List;", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class ContentUiState extends org.wordpress.android.ui.jetpack.scan.history.ScanHistoryViewModel.UiState {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.ui.jetpack.scan.history.ScanHistoryViewModel.UiState.ContentUiState INSTANCE = null;
            @org.jetbrains.annotations.NotNull()
            private static final java.util.List<org.wordpress.android.ui.jetpack.scan.history.ScanHistoryViewModel.TabUiState> tabs = null;
            
            private ContentUiState() {
                super(false, false);
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.util.List<org.wordpress.android.ui.jetpack.scan.history.ScanHistoryViewModel.TabUiState> getTabs() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0010\u0011B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002R\u0012\u0010\u0003\u001a\u00020\u0004X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0018\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0012\u0010\f\u001a\u00020\rX\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f\u0082\u0001\u0002\u0012\u0013\u00a8\u0006\u0014"}, d2 = {"Lorg/wordpress/android/ui/jetpack/scan/history/ScanHistoryViewModel$UiState$ErrorUiState;", "Lorg/wordpress/android/ui/jetpack/scan/history/ScanHistoryViewModel$UiState;", "()V", "img", "", "getImg", "()I", "retry", "Lkotlin/Function0;", "", "getRetry", "()Lkotlin/jvm/functions/Function0;", "title", "Lorg/wordpress/android/ui/utils/UiString;", "getTitle", "()Lorg/wordpress/android/ui/utils/UiString;", "NoConnection", "RequestFailed", "Lorg/wordpress/android/ui/jetpack/scan/history/ScanHistoryViewModel$UiState$ErrorUiState$NoConnection;", "Lorg/wordpress/android/ui/jetpack/scan/history/ScanHistoryViewModel$UiState$ErrorUiState$RequestFailed;", "org.wordpress.android_wordpressJalapenoDebug"})
        public static abstract class ErrorUiState extends org.wordpress.android.ui.jetpack.scan.history.ScanHistoryViewModel.UiState {
            
            private ErrorUiState() {
                super(false, false);
            }
            
            @org.jetbrains.annotations.NotNull()
            public abstract org.wordpress.android.ui.utils.UiString getTitle();
            
            public abstract int getImg();
            
            @org.jetbrains.annotations.NotNull()
            public abstract kotlin.jvm.functions.Function0<kotlin.Unit> getRetry();
            
            @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\u0005J\u000f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\u0019\u0010\u0011\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u00d6\u0003J\t\u0010\u0016\u001a\u00020\u0007H\u00d6\u0001J\t\u0010\u0017\u001a\u00020\u0018H\u00d6\u0001R\u0016\u0010\u0006\u001a\u00020\u00078\u0016X\u0097D\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001a\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\rX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006\u0019"}, d2 = {"Lorg/wordpress/android/ui/jetpack/scan/history/ScanHistoryViewModel$UiState$ErrorUiState$NoConnection;", "Lorg/wordpress/android/ui/jetpack/scan/history/ScanHistoryViewModel$UiState$ErrorUiState;", "retry", "Lkotlin/Function0;", "", "(Lkotlin/jvm/functions/Function0;)V", "img", "", "getImg", "()I", "getRetry", "()Lkotlin/jvm/functions/Function0;", "title", "Lorg/wordpress/android/ui/utils/UiString;", "getTitle", "()Lorg/wordpress/android/ui/utils/UiString;", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
            public static final class NoConnection extends org.wordpress.android.ui.jetpack.scan.history.ScanHistoryViewModel.UiState.ErrorUiState {
                @org.jetbrains.annotations.NotNull()
                private final kotlin.jvm.functions.Function0<kotlin.Unit> retry = null;
                @org.jetbrains.annotations.NotNull()
                private final org.wordpress.android.ui.utils.UiString title = null;
                @androidx.annotation.DrawableRes()
                private final int img = org.wordpress.android.R.drawable.img_illustration_cloud_off_152dp;
                
                @org.jetbrains.annotations.NotNull()
                public final org.wordpress.android.ui.jetpack.scan.history.ScanHistoryViewModel.UiState.ErrorUiState.NoConnection copy(@org.jetbrains.annotations.NotNull()
                kotlin.jvm.functions.Function0<kotlin.Unit> retry) {
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
                
                public NoConnection(@org.jetbrains.annotations.NotNull()
                kotlin.jvm.functions.Function0<kotlin.Unit> retry) {
                    super();
                }
                
                @org.jetbrains.annotations.NotNull()
                public final kotlin.jvm.functions.Function0<kotlin.Unit> component1() {
                    return null;
                }
                
                @org.jetbrains.annotations.NotNull()
                @java.lang.Override()
                public kotlin.jvm.functions.Function0<kotlin.Unit> getRetry() {
                    return null;
                }
                
                @org.jetbrains.annotations.NotNull()
                @java.lang.Override()
                public org.wordpress.android.ui.utils.UiString getTitle() {
                    return null;
                }
                
                @java.lang.Override()
                public int getImg() {
                    return 0;
                }
            }
            
            @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\u0005J\u000f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\u0019\u0010\u0011\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u00d6\u0003J\t\u0010\u0016\u001a\u00020\u0007H\u00d6\u0001J\t\u0010\u0017\u001a\u00020\u0018H\u00d6\u0001R\u0016\u0010\u0006\u001a\u00020\u00078\u0016X\u0097D\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001a\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\rX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006\u0019"}, d2 = {"Lorg/wordpress/android/ui/jetpack/scan/history/ScanHistoryViewModel$UiState$ErrorUiState$RequestFailed;", "Lorg/wordpress/android/ui/jetpack/scan/history/ScanHistoryViewModel$UiState$ErrorUiState;", "retry", "Lkotlin/Function0;", "", "(Lkotlin/jvm/functions/Function0;)V", "img", "", "getImg", "()I", "getRetry", "()Lkotlin/jvm/functions/Function0;", "title", "Lorg/wordpress/android/ui/utils/UiString;", "getTitle", "()Lorg/wordpress/android/ui/utils/UiString;", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
            public static final class RequestFailed extends org.wordpress.android.ui.jetpack.scan.history.ScanHistoryViewModel.UiState.ErrorUiState {
                @org.jetbrains.annotations.NotNull()
                private final kotlin.jvm.functions.Function0<kotlin.Unit> retry = null;
                @org.jetbrains.annotations.NotNull()
                private final org.wordpress.android.ui.utils.UiString title = null;
                @androidx.annotation.DrawableRes()
                private final int img = org.wordpress.android.R.drawable.img_illustration_cloud_off_152dp;
                
                @org.jetbrains.annotations.NotNull()
                public final org.wordpress.android.ui.jetpack.scan.history.ScanHistoryViewModel.UiState.ErrorUiState.RequestFailed copy(@org.jetbrains.annotations.NotNull()
                kotlin.jvm.functions.Function0<kotlin.Unit> retry) {
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
                
                public RequestFailed(@org.jetbrains.annotations.NotNull()
                kotlin.jvm.functions.Function0<kotlin.Unit> retry) {
                    super();
                }
                
                @org.jetbrains.annotations.NotNull()
                public final kotlin.jvm.functions.Function0<kotlin.Unit> component1() {
                    return null;
                }
                
                @org.jetbrains.annotations.NotNull()
                @java.lang.Override()
                public kotlin.jvm.functions.Function0<kotlin.Unit> getRetry() {
                    return null;
                }
                
                @org.jetbrains.annotations.NotNull()
                @java.lang.Override()
                public org.wordpress.android.ui.utils.UiString getTitle() {
                    return null;
                }
                
                @java.lang.Override()
                public int getImg() {
                    return 0;
                }
            }
        }
    }
    
    @android.annotation.SuppressLint(value = {"ParcelCreator"})
    @kotlinx.parcelize.Parcelize()
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0087\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0003J\t\u0010\u0004\u001a\u00020\u0005H\u00d6\u0001J\u0019\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0005H\u00d6\u0001j\u0002\b\u000bj\u0002\b\fj\u0002\b\r\u00a8\u0006\u000e"}, d2 = {"Lorg/wordpress/android/ui/jetpack/scan/history/ScanHistoryViewModel$ScanHistoryTabType;", "", "Landroid/os/Parcelable;", "(Ljava/lang/String;I)V", "describeContents", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "ALL", "FIXED", "IGNORED", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum ScanHistoryTabType implements android.os.Parcelable {
        /*public static final*/ ALL /* = new ALL() */,
        /*public static final*/ FIXED /* = new FIXED() */,
        /*public static final*/ IGNORED /* = new IGNORED() */;
        public static final android.os.Parcelable.Creator<org.wordpress.android.ui.jetpack.scan.history.ScanHistoryViewModel.ScanHistoryTabType> CREATOR = null;
        
        ScanHistoryTabType() {
        }
        
        @java.lang.Override()
        public int describeContents() {
            return 0;
        }
        
        @java.lang.Override()
        public void writeToParcel(@org.jetbrains.annotations.NotNull()
        android.os.Parcel parcel, int flags) {
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 3)
        public static final class Creator implements android.os.Parcelable.Creator<org.wordpress.android.ui.jetpack.scan.history.ScanHistoryViewModel.ScanHistoryTabType> {
            
            public Creator() {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public final org.wordpress.android.ui.jetpack.scan.history.ScanHistoryViewModel.ScanHistoryTabType createFromParcel(@org.jetbrains.annotations.NotNull()
            android.os.Parcel in) {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public final org.wordpress.android.ui.jetpack.scan.history.ScanHistoryViewModel.ScanHistoryTabType[] newArray(int size) {
                return null;
            }
        }
    }
}