package org.wordpress.android.ui.jetpack.scan;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u00a8\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u000f\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001[BQ\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\b\b\u0001\u0010\u0012\u001a\u00020\u0013\u00a2\u0006\u0002\u0010\u0014J)\u00105\u001a\u0002062\u0006\u00107\u001a\u00020*2\u000e\b\u0002\u00108\u001a\b\u0012\u0004\u0012\u00020 0\u001fH\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u00109J\'\u0010:\u001a\u00020$2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020 0\u001f2\u0006\u0010;\u001a\u00020$H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010<J/\u0010=\u001a\u00020>2\b\b\u0002\u0010?\u001a\u00020$2\b\b\u0002\u0010@\u001a\u00020$2\b\b\u0002\u0010A\u001a\u00020$H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010BJ\b\u0010C\u001a\u00020>H\u0002J\b\u0010D\u001a\u00020>H\u0002J\b\u0010E\u001a\u00020>H\u0002J\b\u0010F\u001a\u00020>H\u0002J\b\u0010G\u001a\u00020>H\u0002J\u000e\u0010H\u001a\u00020>2\u0006\u0010I\u001a\u00020 J\b\u0010J\u001a\u00020>H\u0002J\b\u0010K\u001a\u00020>H\u0002J\u0010\u0010L\u001a\u00020>2\b\b\u0001\u0010M\u001a\u00020NJ\u0010\u0010O\u001a\u00020>2\u0006\u0010I\u001a\u00020 H\u0002J\b\u0010P\u001a\u00020>H\u0002J\u0010\u0010Q\u001a\u00020>2\u0006\u0010)\u001a\u00020*H\u0002J\u000e\u0010R\u001a\u00020>2\u0006\u0010+\u001a\u00020,J\b\u0010S\u001a\u00020>H\u0002J\u0010\u0010T\u001a\u00020>2\u0006\u0010U\u001a\u00020\u0018H\u0002J\u0010\u0010V\u001a\u00020>2\u0006\u0010W\u001a\u00020XH\u0002J\u0010\u0010Y\u001a\u00020>2\u0006\u0010Z\u001a\u00020\u001dH\u0002R\u001a\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u00170\u0016X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\u00170\u0016X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020 0\u001f8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b!\u0010\"R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020$X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010%\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u00170&\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010(R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010)\u001a\u0004\u0018\u00010*X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010+\u001a\u00020,X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u001d\u00101\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\u00170&\u00a2\u0006\b\n\u0000\u001a\u0004\b2\u0010(R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u00103\u001a\b\u0012\u0004\u0012\u00020\u001d0&\u00a2\u0006\b\n\u0000\u001a\u0004\b4\u0010(\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\\"}, d2 = {"Lorg/wordpress/android/ui/jetpack/scan/ScanViewModel;", "Lorg/wordpress/android/viewmodel/ScopedViewModel;", "scanStateListItemsBuilder", "Lorg/wordpress/android/ui/jetpack/scan/builders/ScanStateListItemsBuilder;", "fetchScanStateUseCase", "Lorg/wordpress/android/ui/jetpack/scan/usecases/FetchScanStateUseCase;", "startScanUseCase", "Lorg/wordpress/android/ui/jetpack/scan/usecases/StartScanUseCase;", "fixThreatsUseCase", "Lorg/wordpress/android/ui/jetpack/scan/usecases/FixThreatsUseCase;", "fetchFixThreatsStatusUseCase", "Lorg/wordpress/android/ui/jetpack/scan/usecases/FetchFixThreatsStatusUseCase;", "scanStore", "Lorg/wordpress/android/fluxc/store/ScanStore;", "scanTracker", "Lorg/wordpress/android/util/analytics/ScanTracker;", "htmlMessageUtils", "Lorg/wordpress/android/ui/utils/HtmlMessageUtils;", "mainDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Lorg/wordpress/android/ui/jetpack/scan/builders/ScanStateListItemsBuilder;Lorg/wordpress/android/ui/jetpack/scan/usecases/FetchScanStateUseCase;Lorg/wordpress/android/ui/jetpack/scan/usecases/StartScanUseCase;Lorg/wordpress/android/ui/jetpack/scan/usecases/FixThreatsUseCase;Lorg/wordpress/android/ui/jetpack/scan/usecases/FetchFixThreatsStatusUseCase;Lorg/wordpress/android/fluxc/store/ScanStore;Lorg/wordpress/android/util/analytics/ScanTracker;Lorg/wordpress/android/ui/utils/HtmlMessageUtils;Lkotlinx/coroutines/CoroutineDispatcher;)V", "_navigationEvents", "Landroidx/lifecycle/MediatorLiveData;", "Lorg/wordpress/android/viewmodel/Event;", "Lorg/wordpress/android/ui/jetpack/scan/ScanNavigationEvents;", "_snackbarEvents", "Lorg/wordpress/android/ui/pages/SnackbarMessageHolder;", "_uiState", "Landroidx/lifecycle/MutableLiveData;", "Lorg/wordpress/android/ui/jetpack/scan/ScanViewModel$UiState;", "fixableThreatIds", "", "", "getFixableThreatIds", "()Ljava/util/List;", "isStarted", "", "navigationEvents", "Landroidx/lifecycle/LiveData;", "getNavigationEvents", "()Landroidx/lifecycle/LiveData;", "scanStateModel", "Lorg/wordpress/android/fluxc/model/scan/ScanStateModel;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "getSite", "()Lorg/wordpress/android/fluxc/model/SiteModel;", "setSite", "(Lorg/wordpress/android/fluxc/model/SiteModel;)V", "snackbarEvents", "getSnackbarEvents", "uiState", "getUiState", "buildContentUiState", "Lorg/wordpress/android/ui/jetpack/scan/ScanViewModel$UiState$ContentUiState;", "model", "fixingThreatIds", "(Lorg/wordpress/android/fluxc/model/scan/ScanStateModel;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetchFixThreatsStatus", "isInvokedByUser", "(Ljava/util/List;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetchScanState", "", "invokedByUser", "isRetry", "isInvokedFromInit", "(ZZZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fixAllThreats", "init", "onContactSupportClicked", "onEnterServerCredsIconClicked", "onFixAllButtonClicked", "onFixStateRequested", "threatId", "onRetryClicked", "onScanButtonClicked", "onScanStateRequestedWithMessage", "messageRes", "", "onThreatItemClicked", "onVisitVaultPressDashboardClicked", "showScanFinishedMessage", "start", "startScan", "updateNavigationEvent", "navigationEvent", "updateSnackbarMessageEvent", "message", "Lorg/wordpress/android/ui/utils/UiString;", "updateUiState", "state", "UiState", "org.wordpress.android_wordpressJalapenoDebug"})
public final class ScanViewModel extends org.wordpress.android.viewmodel.ScopedViewModel {
    private final org.wordpress.android.ui.jetpack.scan.builders.ScanStateListItemsBuilder scanStateListItemsBuilder = null;
    private final org.wordpress.android.ui.jetpack.scan.usecases.FetchScanStateUseCase fetchScanStateUseCase = null;
    private final org.wordpress.android.ui.jetpack.scan.usecases.StartScanUseCase startScanUseCase = null;
    private final org.wordpress.android.ui.jetpack.scan.usecases.FixThreatsUseCase fixThreatsUseCase = null;
    private final org.wordpress.android.ui.jetpack.scan.usecases.FetchFixThreatsStatusUseCase fetchFixThreatsStatusUseCase = null;
    private final org.wordpress.android.fluxc.store.ScanStore scanStore = null;
    private final org.wordpress.android.util.analytics.ScanTracker scanTracker = null;
    private final org.wordpress.android.ui.utils.HtmlMessageUtils htmlMessageUtils = null;
    private final kotlinx.coroutines.CoroutineDispatcher mainDispatcher = null;
    private boolean isStarted = false;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.ui.jetpack.scan.ScanViewModel.UiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.ui.jetpack.scan.ScanViewModel.UiState> uiState = null;
    private final androidx.lifecycle.MediatorLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.pages.SnackbarMessageHolder>> _snackbarEvents = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.pages.SnackbarMessageHolder>> snackbarEvents = null;
    private final androidx.lifecycle.MediatorLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.jetpack.scan.ScanNavigationEvents>> _navigationEvents = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.jetpack.scan.ScanNavigationEvents>> navigationEvents = null;
    private org.wordpress.android.fluxc.model.scan.ScanStateModel scanStateModel;
    public org.wordpress.android.fluxc.model.SiteModel site;
    
    @javax.inject.Inject()
    public ScanViewModel(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.jetpack.scan.builders.ScanStateListItemsBuilder scanStateListItemsBuilder, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.jetpack.scan.usecases.FetchScanStateUseCase fetchScanStateUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.jetpack.scan.usecases.StartScanUseCase startScanUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.jetpack.scan.usecases.FixThreatsUseCase fixThreatsUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.jetpack.scan.usecases.FetchFixThreatsStatusUseCase fetchFixThreatsStatusUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.ScanStore scanStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.ScanTracker scanTracker, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.utils.HtmlMessageUtils htmlMessageUtils, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "UI_THREAD")
    kotlinx.coroutines.CoroutineDispatcher mainDispatcher) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.ui.jetpack.scan.ScanViewModel.UiState> getUiState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.pages.SnackbarMessageHolder>> getSnackbarEvents() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.jetpack.scan.ScanNavigationEvents>> getNavigationEvents() {
        return null;
    }
    
    private final java.util.List<java.lang.Long> getFixableThreatIds() {
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
    
    private final void init() {
    }
    
    private final java.lang.Object fetchScanState(boolean invokedByUser, boolean isRetry, boolean isInvokedFromInit, kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    private final void showScanFinishedMessage(org.wordpress.android.fluxc.model.scan.ScanStateModel scanStateModel) {
    }
    
    private final void startScan() {
    }
    
    private final void fixAllThreats() {
    }
    
    private final java.lang.Object fetchFixThreatsStatus(java.util.List<java.lang.Long> fixableThreatIds, boolean isInvokedByUser, kotlin.coroutines.Continuation<? super java.lang.Boolean> continuation) {
        return null;
    }
    
    private final void onRetryClicked() {
    }
    
    private final void onVisitVaultPressDashboardClicked() {
    }
    
    private final void onContactSupportClicked() {
    }
    
    private final void onScanButtonClicked() {
    }
    
    private final void onFixAllButtonClicked() {
    }
    
    private final void onThreatItemClicked(long threatId) {
    }
    
    private final void onEnterServerCredsIconClicked() {
    }
    
    public final void onScanStateRequestedWithMessage(@androidx.annotation.StringRes()
    int messageRes) {
    }
    
    public final void onFixStateRequested(long threatId) {
    }
    
    private final void updateSnackbarMessageEvent(org.wordpress.android.ui.utils.UiString message) {
    }
    
    private final void updateNavigationEvent(org.wordpress.android.ui.jetpack.scan.ScanNavigationEvents navigationEvent) {
    }
    
    private final void updateUiState(org.wordpress.android.ui.jetpack.scan.ScanViewModel.UiState state) {
    }
    
    private final java.lang.Object buildContentUiState(org.wordpress.android.fluxc.model.scan.ScanStateModel model, java.util.List<java.lang.Long> fixingThreatIds, kotlin.coroutines.Continuation<? super org.wordpress.android.ui.jetpack.scan.ScanViewModel.UiState.ContentUiState> continuation) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u000b\f\rB%\b\u0004\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0006R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b\u0082\u0001\u0003\u000e\u000f\u0010\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/jetpack/scan/ScanViewModel$UiState;", "", "loadingVisible", "", "contentVisible", "errorVisible", "(ZZZ)V", "getContentVisible", "()Z", "getErrorVisible", "getLoadingVisible", "ContentUiState", "ErrorUiState", "FullScreenLoadingUiState", "Lorg/wordpress/android/ui/jetpack/scan/ScanViewModel$UiState$FullScreenLoadingUiState;", "Lorg/wordpress/android/ui/jetpack/scan/ScanViewModel$UiState$ContentUiState;", "Lorg/wordpress/android/ui/jetpack/scan/ScanViewModel$UiState$ErrorUiState;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class UiState {
        private final boolean loadingVisible = false;
        private final boolean contentVisible = false;
        private final boolean errorVisible = false;
        
        private UiState(boolean loadingVisible, boolean contentVisible, boolean errorVisible) {
            super();
        }
        
        public final boolean getLoadingVisible() {
            return false;
        }
        
        public final boolean getContentVisible() {
            return false;
        }
        
        public final boolean getErrorVisible() {
            return false;
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/jetpack/scan/ScanViewModel$UiState$FullScreenLoadingUiState;", "Lorg/wordpress/android/ui/jetpack/scan/ScanViewModel$UiState;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class FullScreenLoadingUiState extends org.wordpress.android.ui.jetpack.scan.ScanViewModel.UiState {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.ui.jetpack.scan.ScanViewModel.UiState.FullScreenLoadingUiState INSTANCE = null;
            
            private FullScreenLoadingUiState() {
                super(false, false, false);
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\u0005J\u000f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\u0019\u0010\t\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u00d6\u0003J\t\u0010\u000e\u001a\u00020\u000fH\u00d6\u0001J\t\u0010\u0010\u001a\u00020\u0011H\u00d6\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\u0012"}, d2 = {"Lorg/wordpress/android/ui/jetpack/scan/ScanViewModel$UiState$ContentUiState;", "Lorg/wordpress/android/ui/jetpack/scan/ScanViewModel$UiState;", "items", "", "Lorg/wordpress/android/ui/jetpack/common/JetpackListItemState;", "(Ljava/util/List;)V", "getItems", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class ContentUiState extends org.wordpress.android.ui.jetpack.scan.ScanViewModel.UiState {
            @org.jetbrains.annotations.NotNull()
            private final java.util.List<org.wordpress.android.ui.jetpack.common.JetpackListItemState> items = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.jetpack.scan.ScanViewModel.UiState.ContentUiState copy(@org.jetbrains.annotations.NotNull()
            java.util.List<? extends org.wordpress.android.ui.jetpack.common.JetpackListItemState> items) {
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
            java.util.List<? extends org.wordpress.android.ui.jetpack.common.JetpackListItemState> items) {
                super(false, false, false);
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.util.List<org.wordpress.android.ui.jetpack.common.JetpackListItemState> component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.util.List<org.wordpress.android.ui.jetpack.common.JetpackListItemState> getItems() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0005\u0018\u0019\u001a\u001b\u001cB\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0016\u0010\b\u001a\u0004\u0018\u00010\tX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0012\u0010\f\u001a\u00020\rX\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0018\u0010\u0010\u001a\u0004\u0018\u00010\rX\u0096\u0004\u00a2\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u0011\u0010\u0012R\u0012\u0010\u0014\u001a\u00020\tX\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0015\u0010\u000bR\u0012\u0010\u0016\u001a\u00020\tX\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0017\u0010\u000b\u0082\u0001\u0005\u001d\u001e\u001f !\u00a8\u0006\""}, d2 = {"Lorg/wordpress/android/ui/jetpack/scan/ScanViewModel$UiState$ErrorUiState;", "Lorg/wordpress/android/ui/jetpack/scan/ScanViewModel$UiState;", "()V", "action", "Lkotlin/Function0;", "", "getAction", "()Lkotlin/jvm/functions/Function0;", "buttonText", "Lorg/wordpress/android/ui/utils/UiString;", "getButtonText", "()Lorg/wordpress/android/ui/utils/UiString;", "image", "", "getImage", "()I", "imageColorResId", "getImageColorResId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "subtitle", "getSubtitle", "title", "getTitle", "GenericRequestFailed", "MultisiteNotSupported", "NoConnection", "ScanRequestFailed", "VaultPressActiveOnSite", "Lorg/wordpress/android/ui/jetpack/scan/ScanViewModel$UiState$ErrorUiState$NoConnection;", "Lorg/wordpress/android/ui/jetpack/scan/ScanViewModel$UiState$ErrorUiState$GenericRequestFailed;", "Lorg/wordpress/android/ui/jetpack/scan/ScanViewModel$UiState$ErrorUiState$ScanRequestFailed;", "Lorg/wordpress/android/ui/jetpack/scan/ScanViewModel$UiState$ErrorUiState$MultisiteNotSupported;", "Lorg/wordpress/android/ui/jetpack/scan/ScanViewModel$UiState$ErrorUiState$VaultPressActiveOnSite;", "org.wordpress.android_wordpressJalapenoDebug"})
        public static abstract class ErrorUiState extends org.wordpress.android.ui.jetpack.scan.ScanViewModel.UiState {
            @org.jetbrains.annotations.Nullable()
            private final java.lang.Integer imageColorResId = null;
            @org.jetbrains.annotations.Nullable()
            private final org.wordpress.android.ui.utils.UiString buttonText = null;
            @org.jetbrains.annotations.Nullable()
            private final kotlin.jvm.functions.Function0<kotlin.Unit> action = null;
            
            private ErrorUiState() {
                super(false, false, false);
            }
            
            public abstract int getImage();
            
            @org.jetbrains.annotations.Nullable()
            public java.lang.Integer getImageColorResId() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public abstract org.wordpress.android.ui.utils.UiString getTitle();
            
            @org.jetbrains.annotations.NotNull()
            public abstract org.wordpress.android.ui.utils.UiString getSubtitle();
            
            @org.jetbrains.annotations.Nullable()
            public org.wordpress.android.ui.utils.UiString getButtonText() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public kotlin.jvm.functions.Function0<kotlin.Unit> getAction() {
                return null;
            }
            
            @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\u0005J\u000f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\u0019\u0010\u0015\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u00d6\u0003J\t\u0010\u001a\u001a\u00020\rH\u00d6\u0001J\t\u0010\u001b\u001a\u00020\u001cH\u00d6\u0001R\u001a\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\f\u001a\u00020\r8\u0016X\u0097D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\tX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000bR\u0014\u0010\u0012\u001a\u00020\tX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000b\u00a8\u0006\u001d"}, d2 = {"Lorg/wordpress/android/ui/jetpack/scan/ScanViewModel$UiState$ErrorUiState$NoConnection;", "Lorg/wordpress/android/ui/jetpack/scan/ScanViewModel$UiState$ErrorUiState;", "action", "Lkotlin/Function0;", "", "(Lkotlin/jvm/functions/Function0;)V", "getAction", "()Lkotlin/jvm/functions/Function0;", "buttonText", "Lorg/wordpress/android/ui/utils/UiString$UiStringRes;", "getButtonText", "()Lorg/wordpress/android/ui/utils/UiString$UiStringRes;", "image", "", "getImage", "()I", "subtitle", "getSubtitle", "title", "getTitle", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
            public static final class NoConnection extends org.wordpress.android.ui.jetpack.scan.ScanViewModel.UiState.ErrorUiState {
                @org.jetbrains.annotations.NotNull()
                private final kotlin.jvm.functions.Function0<kotlin.Unit> action = null;
                @androidx.annotation.DrawableRes()
                private final int image = org.wordpress.android.R.drawable.img_illustration_cloud_off_152dp;
                @org.jetbrains.annotations.NotNull()
                private final org.wordpress.android.ui.utils.UiString.UiStringRes title = null;
                @org.jetbrains.annotations.NotNull()
                private final org.wordpress.android.ui.utils.UiString.UiStringRes subtitle = null;
                @org.jetbrains.annotations.NotNull()
                private final org.wordpress.android.ui.utils.UiString.UiStringRes buttonText = null;
                
                @org.jetbrains.annotations.NotNull()
                public final org.wordpress.android.ui.jetpack.scan.ScanViewModel.UiState.ErrorUiState.NoConnection copy(@org.jetbrains.annotations.NotNull()
                kotlin.jvm.functions.Function0<kotlin.Unit> action) {
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
                kotlin.jvm.functions.Function0<kotlin.Unit> action) {
                    super();
                }
                
                @org.jetbrains.annotations.NotNull()
                public final kotlin.jvm.functions.Function0<kotlin.Unit> component1() {
                    return null;
                }
                
                @org.jetbrains.annotations.NotNull()
                @java.lang.Override()
                public kotlin.jvm.functions.Function0<kotlin.Unit> getAction() {
                    return null;
                }
                
                @java.lang.Override()
                public int getImage() {
                    return 0;
                }
                
                @org.jetbrains.annotations.NotNull()
                @java.lang.Override()
                public org.wordpress.android.ui.utils.UiString.UiStringRes getTitle() {
                    return null;
                }
                
                @org.jetbrains.annotations.NotNull()
                @java.lang.Override()
                public org.wordpress.android.ui.utils.UiString.UiStringRes getSubtitle() {
                    return null;
                }
                
                @org.jetbrains.annotations.NotNull()
                @java.lang.Override()
                public org.wordpress.android.ui.utils.UiString.UiStringRes getButtonText() {
                    return null;
                }
            }
            
            @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\u0005J\u000f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\u0019\u0010\u0015\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u00d6\u0003J\t\u0010\u001a\u001a\u00020\rH\u00d6\u0001J\t\u0010\u001b\u001a\u00020\u001cH\u00d6\u0001R\u001a\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\f\u001a\u00020\r8\u0016X\u0097D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\tX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000bR\u0014\u0010\u0012\u001a\u00020\tX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000b\u00a8\u0006\u001d"}, d2 = {"Lorg/wordpress/android/ui/jetpack/scan/ScanViewModel$UiState$ErrorUiState$GenericRequestFailed;", "Lorg/wordpress/android/ui/jetpack/scan/ScanViewModel$UiState$ErrorUiState;", "action", "Lkotlin/Function0;", "", "(Lkotlin/jvm/functions/Function0;)V", "getAction", "()Lkotlin/jvm/functions/Function0;", "buttonText", "Lorg/wordpress/android/ui/utils/UiString$UiStringRes;", "getButtonText", "()Lorg/wordpress/android/ui/utils/UiString$UiStringRes;", "image", "", "getImage", "()I", "subtitle", "getSubtitle", "title", "getTitle", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
            public static final class GenericRequestFailed extends org.wordpress.android.ui.jetpack.scan.ScanViewModel.UiState.ErrorUiState {
                @org.jetbrains.annotations.NotNull()
                private final kotlin.jvm.functions.Function0<kotlin.Unit> action = null;
                @androidx.annotation.DrawableRes()
                private final int image = org.wordpress.android.R.drawable.img_illustration_cloud_off_152dp;
                @org.jetbrains.annotations.NotNull()
                private final org.wordpress.android.ui.utils.UiString.UiStringRes title = null;
                @org.jetbrains.annotations.NotNull()
                private final org.wordpress.android.ui.utils.UiString.UiStringRes subtitle = null;
                @org.jetbrains.annotations.NotNull()
                private final org.wordpress.android.ui.utils.UiString.UiStringRes buttonText = null;
                
                @org.jetbrains.annotations.NotNull()
                public final org.wordpress.android.ui.jetpack.scan.ScanViewModel.UiState.ErrorUiState.GenericRequestFailed copy(@org.jetbrains.annotations.NotNull()
                kotlin.jvm.functions.Function0<kotlin.Unit> action) {
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
                
                public GenericRequestFailed(@org.jetbrains.annotations.NotNull()
                kotlin.jvm.functions.Function0<kotlin.Unit> action) {
                    super();
                }
                
                @org.jetbrains.annotations.NotNull()
                public final kotlin.jvm.functions.Function0<kotlin.Unit> component1() {
                    return null;
                }
                
                @org.jetbrains.annotations.NotNull()
                @java.lang.Override()
                public kotlin.jvm.functions.Function0<kotlin.Unit> getAction() {
                    return null;
                }
                
                @java.lang.Override()
                public int getImage() {
                    return 0;
                }
                
                @org.jetbrains.annotations.NotNull()
                @java.lang.Override()
                public org.wordpress.android.ui.utils.UiString.UiStringRes getTitle() {
                    return null;
                }
                
                @org.jetbrains.annotations.NotNull()
                @java.lang.Override()
                public org.wordpress.android.ui.utils.UiString.UiStringRes getSubtitle() {
                    return null;
                }
                
                @org.jetbrains.annotations.NotNull()
                @java.lang.Override()
                public org.wordpress.android.ui.utils.UiString.UiStringRes getButtonText() {
                    return null;
                }
            }
            
            @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\u0005J\u000f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\u0019\u0010\u0015\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u00d6\u0003J\t\u0010\u001a\u001a\u00020\rH\u00d6\u0001J\t\u0010\u001b\u001a\u00020\u001cH\u00d6\u0001R\u001a\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\f\u001a\u00020\r8\u0016X\u0097D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\tX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000bR\u0014\u0010\u0012\u001a\u00020\tX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000b\u00a8\u0006\u001d"}, d2 = {"Lorg/wordpress/android/ui/jetpack/scan/ScanViewModel$UiState$ErrorUiState$ScanRequestFailed;", "Lorg/wordpress/android/ui/jetpack/scan/ScanViewModel$UiState$ErrorUiState;", "action", "Lkotlin/Function0;", "", "(Lkotlin/jvm/functions/Function0;)V", "getAction", "()Lkotlin/jvm/functions/Function0;", "buttonText", "Lorg/wordpress/android/ui/utils/UiString$UiStringRes;", "getButtonText", "()Lorg/wordpress/android/ui/utils/UiString$UiStringRes;", "image", "", "getImage", "()I", "subtitle", "getSubtitle", "title", "getTitle", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
            public static final class ScanRequestFailed extends org.wordpress.android.ui.jetpack.scan.ScanViewModel.UiState.ErrorUiState {
                @org.jetbrains.annotations.NotNull()
                private final kotlin.jvm.functions.Function0<kotlin.Unit> action = null;
                @androidx.annotation.DrawableRes()
                private final int image = org.wordpress.android.R.drawable.img_illustration_empty_results_216dp;
                @org.jetbrains.annotations.NotNull()
                private final org.wordpress.android.ui.utils.UiString.UiStringRes title = null;
                @org.jetbrains.annotations.NotNull()
                private final org.wordpress.android.ui.utils.UiString.UiStringRes subtitle = null;
                @org.jetbrains.annotations.NotNull()
                private final org.wordpress.android.ui.utils.UiString.UiStringRes buttonText = null;
                
                @org.jetbrains.annotations.NotNull()
                public final org.wordpress.android.ui.jetpack.scan.ScanViewModel.UiState.ErrorUiState.ScanRequestFailed copy(@org.jetbrains.annotations.NotNull()
                kotlin.jvm.functions.Function0<kotlin.Unit> action) {
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
                
                public ScanRequestFailed(@org.jetbrains.annotations.NotNull()
                kotlin.jvm.functions.Function0<kotlin.Unit> action) {
                    super();
                }
                
                @org.jetbrains.annotations.NotNull()
                public final kotlin.jvm.functions.Function0<kotlin.Unit> component1() {
                    return null;
                }
                
                @org.jetbrains.annotations.NotNull()
                @java.lang.Override()
                public kotlin.jvm.functions.Function0<kotlin.Unit> getAction() {
                    return null;
                }
                
                @java.lang.Override()
                public int getImage() {
                    return 0;
                }
                
                @org.jetbrains.annotations.NotNull()
                @java.lang.Override()
                public org.wordpress.android.ui.utils.UiString.UiStringRes getTitle() {
                    return null;
                }
                
                @org.jetbrains.annotations.NotNull()
                @java.lang.Override()
                public org.wordpress.android.ui.utils.UiString.UiStringRes getSubtitle() {
                    return null;
                }
                
                @org.jetbrains.annotations.NotNull()
                @java.lang.Override()
                public org.wordpress.android.ui.utils.UiString.UiStringRes getButtonText() {
                    return null;
                }
            }
            
            @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\u00020\u00048\u0016X\u0097D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0016\u0010\u0007\u001a\u00020\u00048\u0016X\u0097D\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u000bX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u000bX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\r\u00a8\u0006\u0010"}, d2 = {"Lorg/wordpress/android/ui/jetpack/scan/ScanViewModel$UiState$ErrorUiState$MultisiteNotSupported;", "Lorg/wordpress/android/ui/jetpack/scan/ScanViewModel$UiState$ErrorUiState;", "()V", "image", "", "getImage", "()I", "imageColorResId", "getImageColorResId", "()Ljava/lang/Integer;", "subtitle", "Lorg/wordpress/android/ui/utils/UiString$UiStringRes;", "getSubtitle", "()Lorg/wordpress/android/ui/utils/UiString$UiStringRes;", "title", "getTitle", "org.wordpress.android_wordpressJalapenoDebug"})
            public static final class MultisiteNotSupported extends org.wordpress.android.ui.jetpack.scan.ScanViewModel.UiState.ErrorUiState {
                @org.jetbrains.annotations.NotNull()
                public static final org.wordpress.android.ui.jetpack.scan.ScanViewModel.UiState.ErrorUiState.MultisiteNotSupported INSTANCE = null;
                @androidx.annotation.DrawableRes()
                private static final int image = org.wordpress.android.R.drawable.ic_baseline_security_white_24dp;
                @androidx.annotation.ColorRes()
                private static final int imageColorResId = org.wordpress.android.R.color.gray;
                @org.jetbrains.annotations.NotNull()
                private static final org.wordpress.android.ui.utils.UiString.UiStringRes title = null;
                @org.jetbrains.annotations.NotNull()
                private static final org.wordpress.android.ui.utils.UiString.UiStringRes subtitle = null;
                
                private MultisiteNotSupported() {
                    super();
                }
                
                @java.lang.Override()
                public int getImage() {
                    return 0;
                }
                
                @org.jetbrains.annotations.NotNull()
                @java.lang.Override()
                public java.lang.Integer getImageColorResId() {
                    return null;
                }
                
                @org.jetbrains.annotations.NotNull()
                @java.lang.Override()
                public org.wordpress.android.ui.utils.UiString.UiStringRes getTitle() {
                    return null;
                }
                
                @org.jetbrains.annotations.NotNull()
                @java.lang.Override()
                public org.wordpress.android.ui.utils.UiString.UiStringRes getSubtitle() {
                    return null;
                }
            }
            
            @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\u0005J\u000f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\u0019\u0010\u0018\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u00d6\u0003J\t\u0010\u001d\u001a\u00020\rH\u00d6\u0001J\t\u0010\u001e\u001a\u00020\u001fH\u00d6\u0001R\u001a\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\f\u001a\u00020\r8\u0016X\u0097D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0010\u001a\u00020\r8\u0016X\u0097D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\u00020\tX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000bR\u0014\u0010\u0015\u001a\u00020\tX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000b\u00a8\u0006 "}, d2 = {"Lorg/wordpress/android/ui/jetpack/scan/ScanViewModel$UiState$ErrorUiState$VaultPressActiveOnSite;", "Lorg/wordpress/android/ui/jetpack/scan/ScanViewModel$UiState$ErrorUiState;", "action", "Lkotlin/Function0;", "", "(Lkotlin/jvm/functions/Function0;)V", "getAction", "()Lkotlin/jvm/functions/Function0;", "buttonText", "Lorg/wordpress/android/ui/utils/UiString$UiStringRes;", "getButtonText", "()Lorg/wordpress/android/ui/utils/UiString$UiStringRes;", "image", "", "getImage", "()I", "imageColorResId", "getImageColorResId", "()Ljava/lang/Integer;", "subtitle", "getSubtitle", "title", "getTitle", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
            public static final class VaultPressActiveOnSite extends org.wordpress.android.ui.jetpack.scan.ScanViewModel.UiState.ErrorUiState {
                @org.jetbrains.annotations.NotNull()
                private final kotlin.jvm.functions.Function0<kotlin.Unit> action = null;
                @androidx.annotation.DrawableRes()
                private final int image = org.wordpress.android.R.drawable.ic_shield_warning_white;
                @androidx.annotation.ColorRes()
                private final int imageColorResId = org.wordpress.android.R.color.error_60;
                @org.jetbrains.annotations.NotNull()
                private final org.wordpress.android.ui.utils.UiString.UiStringRes title = null;
                @org.jetbrains.annotations.NotNull()
                private final org.wordpress.android.ui.utils.UiString.UiStringRes subtitle = null;
                @org.jetbrains.annotations.NotNull()
                private final org.wordpress.android.ui.utils.UiString.UiStringRes buttonText = null;
                
                @org.jetbrains.annotations.NotNull()
                public final org.wordpress.android.ui.jetpack.scan.ScanViewModel.UiState.ErrorUiState.VaultPressActiveOnSite copy(@org.jetbrains.annotations.NotNull()
                kotlin.jvm.functions.Function0<kotlin.Unit> action) {
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
                
                public VaultPressActiveOnSite(@org.jetbrains.annotations.NotNull()
                kotlin.jvm.functions.Function0<kotlin.Unit> action) {
                    super();
                }
                
                @org.jetbrains.annotations.NotNull()
                public final kotlin.jvm.functions.Function0<kotlin.Unit> component1() {
                    return null;
                }
                
                @org.jetbrains.annotations.NotNull()
                @java.lang.Override()
                public kotlin.jvm.functions.Function0<kotlin.Unit> getAction() {
                    return null;
                }
                
                @java.lang.Override()
                public int getImage() {
                    return 0;
                }
                
                @org.jetbrains.annotations.NotNull()
                @java.lang.Override()
                public java.lang.Integer getImageColorResId() {
                    return null;
                }
                
                @org.jetbrains.annotations.NotNull()
                @java.lang.Override()
                public org.wordpress.android.ui.utils.UiString.UiStringRes getTitle() {
                    return null;
                }
                
                @org.jetbrains.annotations.NotNull()
                @java.lang.Override()
                public org.wordpress.android.ui.utils.UiString.UiStringRes getSubtitle() {
                    return null;
                }
                
                @org.jetbrains.annotations.NotNull()
                @java.lang.Override()
                public org.wordpress.android.ui.utils.UiString.UiStringRes getButtonText() {
                    return null;
                }
            }
        }
    }
}