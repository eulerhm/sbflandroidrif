package org.wordpress.android.ui.sitecreation.previews;

import java.lang.System;

@dagger.hilt.android.lifecycle.HiltViewModel()
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u00b0\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\f\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002:\u0004_`abBS\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\b\b\u0001\u0010\u0011\u001a\u00020\u0012\u0012\b\b\u0001\u0010\u0013\u001a\u00020\u0012\u00a2\u0006\u0002\u0010\u0014J\b\u0010F\u001a\u00020GH\u0002J\u0010\u0010H\u001a\u00020\u00192\u0006\u0010I\u001a\u00020JH\u0002J\u0010\u0010K\u001a\u00020\u00192\u0006\u0010L\u001a\u00020\u0017H\u0002J\u0006\u00100\u001a\u00020\u0019J\b\u0010M\u001a\u00020\u0019H\u0014J\u0006\u00104\u001a\u00020\u0019J\u0006\u00106\u001a\u00020\u0019J\u0010\u0010N\u001a\u00020\u00192\u0006\u0010O\u001a\u00020-H\u0007J\u0006\u0010P\u001a\u00020\u0019J\u0006\u0010Q\u001a\u00020\u0019J\u0006\u0010R\u001a\u00020\u0019J\b\u0010S\u001a\u00020\u0019H\u0002J\b\u0010T\u001a\u00020\u0019H\u0002J\u0018\u0010U\u001a\u00020\u00192\u0006\u0010=\u001a\u00020>2\b\u0010V\u001a\u0004\u0018\u00010WJ\u0014\u0010@\u001a\u00020\u00192\n\b\u0002\u0010X\u001a\u0004\u0018\u00010-H\u0002J\u0012\u0010Y\u001a\u00020\u00192\b\b\u0002\u0010Z\u001a\u00020)H\u0002J\u0010\u0010[\u001a\u00020\u00192\u0006\u0010B\u001a\u00020\"H\u0002J\u0010\u0010\\\u001a\u00020\u00192\u0006\u0010B\u001a\u00020\"H\u0002J\u000e\u0010]\u001a\u00020\u00192\u0006\u0010^\u001a\u00020WR\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\u0016X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001dX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020 0\u0016X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010!\u001a\b\u0012\u0004\u0012\u00020\"0\u001dX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010#\u001a\u00020$8VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b%\u0010&R\u000e\u0010\'\u001a\u00020\u0017X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020)X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020+X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010,\u001a\u0004\u0018\u00010-X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010.\u001a\u0004\u0018\u00010/X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u00100\u001a\b\u0012\u0004\u0012\u00020\u001701\u00a2\u0006\b\n\u0000\u001a\u0004\b2\u00103R\u0017\u00104\u001a\b\u0012\u0004\u0012\u00020\u001901\u00a2\u0006\b\n\u0000\u001a\u0004\b5\u00103R\u0017\u00106\u001a\b\u0012\u0004\u0012\u00020\u001701\u00a2\u0006\b\n\u0000\u001a\u0004\b7\u00103R\u0017\u00108\u001a\b\u0012\u0004\u0012\u00020\u001701\u00a2\u0006\b\n\u0000\u001a\u0004\b9\u00103R\u0017\u0010:\u001a\b\u0012\u0004\u0012\u00020\u001e01\u00a2\u0006\b\n\u0000\u001a\u0004\b;\u00103R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010<\u001a\u0004\u0018\u00010-X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010=\u001a\u00020>X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010?\u001a\u0004\u0018\u00010\u001eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0017\u0010@\u001a\b\u0012\u0004\u0012\u00020 01\u00a2\u0006\b\n\u0000\u001a\u0004\bA\u00103R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010B\u001a\b\u0012\u0004\u0012\u00020\"01\u00a2\u0006\b\n\u0000\u001a\u0004\bC\u00103R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010D\u001a\u0004\u0018\u00010\u001eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010E\u001a\u00020)X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006c"}, d2 = {"Lorg/wordpress/android/ui/sitecreation/previews/SitePreviewViewModel;", "Landroidx/lifecycle/ViewModel;", "Lkotlinx/coroutines/CoroutineScope;", "dispatcher", "Lorg/wordpress/android/fluxc/Dispatcher;", "siteStore", "Lorg/wordpress/android/fluxc/store/SiteStore;", "quickStartStore", "Lorg/wordpress/android/fluxc/store/QuickStartStore;", "fetchWpComSiteUseCase", "Lorg/wordpress/android/ui/sitecreation/services/FetchWpComSiteUseCase;", "networkUtils", "Lorg/wordpress/android/util/NetworkUtilsWrapper;", "urlUtils", "Lorg/wordpress/android/util/UrlUtilsWrapper;", "tracker", "Lorg/wordpress/android/ui/sitecreation/misc/SiteCreationTracker;", "bgDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "mainDispatcher", "(Lorg/wordpress/android/fluxc/Dispatcher;Lorg/wordpress/android/fluxc/store/SiteStore;Lorg/wordpress/android/fluxc/store/QuickStartStore;Lorg/wordpress/android/ui/sitecreation/services/FetchWpComSiteUseCase;Lorg/wordpress/android/util/NetworkUtilsWrapper;Lorg/wordpress/android/util/UrlUtilsWrapper;Lorg/wordpress/android/ui/sitecreation/misc/SiteCreationTracker;Lkotlinx/coroutines/CoroutineDispatcher;Lkotlinx/coroutines/CoroutineDispatcher;)V", "_onCancelWizardClicked", "Lorg/wordpress/android/viewmodel/SingleLiveEvent;", "Lorg/wordpress/android/ui/sitecreation/previews/SitePreviewViewModel$CreateSiteState;", "_onHelpClicked", "", "_onOkButtonClicked", "_onSiteCreationCompleted", "_preloadPreview", "Landroidx/lifecycle/MutableLiveData;", "", "_startCreateSiteService", "Lorg/wordpress/android/ui/sitecreation/previews/SitePreviewViewModel$SitePreviewStartServiceData;", "_uiState", "Lorg/wordpress/android/ui/sitecreation/previews/SitePreviewViewModel$SitePreviewUiState;", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "createSiteState", "isStarted", "", "job", "Lkotlinx/coroutines/CompletableJob;", "lastReceivedServiceState", "Lorg/wordpress/android/ui/sitecreation/services/SiteCreationServiceState;", "loadingAnimationJob", "Lkotlinx/coroutines/Job;", "onCancelWizardClicked", "Landroidx/lifecycle/LiveData;", "getOnCancelWizardClicked", "()Landroidx/lifecycle/LiveData;", "onHelpClicked", "getOnHelpClicked", "onOkButtonClicked", "getOnOkButtonClicked", "onSiteCreationCompleted", "getOnSiteCreationCompleted", "preloadPreview", "getPreloadPreview", "serviceStateForRetry", "siteCreationState", "Lorg/wordpress/android/ui/sitecreation/SiteCreationState;", "siteTitle", "startCreateSiteService", "getStartCreateSiteService", "uiState", "getUiState", "urlWithoutScheme", "webviewFullyLoadedTracked", "createSitePreviewData", "Lorg/wordpress/android/ui/sitecreation/previews/SitePreviewViewModel$SitePreviewData;", "fetchNewlyCreatedSiteModel", "remoteSiteId", "", "init", "state", "onCleared", "onSiteCreationServiceStateUpdated", "event", "onUrlLoaded", "onWebViewError", "retry", "showFullscreenErrorWithDelay", "showFullscreenProgress", "start", "savedState", "Landroid/os/Bundle;", "previousState", "startPreLoadingWebView", "skipDelay", "updateUiState", "updateUiStateAsync", "writeToBundle", "outState", "CreateSiteState", "SitePreviewData", "SitePreviewStartServiceData", "SitePreviewUiState", "org.wordpress.android_wordpressJalapenoDebug"})
public final class SitePreviewViewModel extends androidx.lifecycle.ViewModel implements kotlinx.coroutines.CoroutineScope {
    private final org.wordpress.android.fluxc.Dispatcher dispatcher = null;
    private final org.wordpress.android.fluxc.store.SiteStore siteStore = null;
    private final org.wordpress.android.fluxc.store.QuickStartStore quickStartStore = null;
    private final org.wordpress.android.ui.sitecreation.services.FetchWpComSiteUseCase fetchWpComSiteUseCase = null;
    private final org.wordpress.android.util.NetworkUtilsWrapper networkUtils = null;
    private final org.wordpress.android.util.UrlUtilsWrapper urlUtils = null;
    private final org.wordpress.android.ui.sitecreation.misc.SiteCreationTracker tracker = null;
    private final kotlinx.coroutines.CoroutineDispatcher bgDispatcher = null;
    private final kotlinx.coroutines.CoroutineDispatcher mainDispatcher = null;
    private final kotlinx.coroutines.CompletableJob job = null;
    private boolean isStarted = false;
    private boolean webviewFullyLoadedTracked = false;
    private kotlinx.coroutines.Job loadingAnimationJob;
    private org.wordpress.android.ui.sitecreation.SiteCreationState siteCreationState;
    private java.lang.String urlWithoutScheme;
    private java.lang.String siteTitle;
    private org.wordpress.android.ui.sitecreation.services.SiteCreationServiceState lastReceivedServiceState;
    private org.wordpress.android.ui.sitecreation.services.SiteCreationServiceState serviceStateForRetry;
    private org.wordpress.android.ui.sitecreation.previews.SitePreviewViewModel.CreateSiteState createSiteState;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.ui.sitecreation.previews.SitePreviewViewModel.SitePreviewUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.ui.sitecreation.previews.SitePreviewViewModel.SitePreviewUiState> uiState = null;
    private final androidx.lifecycle.MutableLiveData<java.lang.String> _preloadPreview = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.String> preloadPreview = null;
    private final org.wordpress.android.viewmodel.SingleLiveEvent<org.wordpress.android.ui.sitecreation.previews.SitePreviewViewModel.SitePreviewStartServiceData> _startCreateSiteService = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.ui.sitecreation.previews.SitePreviewViewModel.SitePreviewStartServiceData> startCreateSiteService = null;
    private final org.wordpress.android.viewmodel.SingleLiveEvent<kotlin.Unit> _onHelpClicked = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<kotlin.Unit> onHelpClicked = null;
    private final org.wordpress.android.viewmodel.SingleLiveEvent<org.wordpress.android.ui.sitecreation.previews.SitePreviewViewModel.CreateSiteState> _onCancelWizardClicked = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.ui.sitecreation.previews.SitePreviewViewModel.CreateSiteState> onCancelWizardClicked = null;
    private final org.wordpress.android.viewmodel.SingleLiveEvent<org.wordpress.android.ui.sitecreation.previews.SitePreviewViewModel.CreateSiteState> _onOkButtonClicked = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.ui.sitecreation.previews.SitePreviewViewModel.CreateSiteState> onOkButtonClicked = null;
    private final org.wordpress.android.viewmodel.SingleLiveEvent<org.wordpress.android.ui.sitecreation.previews.SitePreviewViewModel.CreateSiteState> _onSiteCreationCompleted = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.ui.sitecreation.previews.SitePreviewViewModel.CreateSiteState> onSiteCreationCompleted = null;
    
    @javax.inject.Inject()
    public SitePreviewViewModel(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.Dispatcher dispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.SiteStore siteStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.QuickStartStore quickStartStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.sitecreation.services.FetchWpComSiteUseCase fetchWpComSiteUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.NetworkUtilsWrapper networkUtils, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.UrlUtilsWrapper urlUtils, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.sitecreation.misc.SiteCreationTracker tracker, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher bgDispatcher, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "UI_THREAD")
    kotlinx.coroutines.CoroutineDispatcher mainDispatcher) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public kotlin.coroutines.CoroutineContext getCoroutineContext() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.ui.sitecreation.previews.SitePreviewViewModel.SitePreviewUiState> getUiState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.String> getPreloadPreview() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.ui.sitecreation.previews.SitePreviewViewModel.SitePreviewStartServiceData> getStartCreateSiteService() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<kotlin.Unit> getOnHelpClicked() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.ui.sitecreation.previews.SitePreviewViewModel.CreateSiteState> getOnCancelWizardClicked() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.ui.sitecreation.previews.SitePreviewViewModel.CreateSiteState> getOnOkButtonClicked() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.ui.sitecreation.previews.SitePreviewViewModel.CreateSiteState> getOnSiteCreationCompleted() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCleared() {
    }
    
    public final void writeToBundle(@org.jetbrains.annotations.NotNull()
    android.os.Bundle outState) {
    }
    
    public final void start(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.sitecreation.SiteCreationState siteCreationState, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedState) {
    }
    
    private final void init(org.wordpress.android.ui.sitecreation.previews.SitePreviewViewModel.CreateSiteState state) {
    }
    
    private final void startCreateSiteService(org.wordpress.android.ui.sitecreation.services.SiteCreationServiceState previousState) {
    }
    
    public final void retry() {
    }
    
    public final void onHelpClicked() {
    }
    
    public final void onCancelWizardClicked() {
    }
    
    public final void onOkButtonClicked() {
    }
    
    private final void showFullscreenErrorWithDelay() {
    }
    
    /**
     * The service automatically shows system notifications when site creation is in progress and the app is in
     * the background. We need to connect to the `AutoForeground` service from the View(Fragment), as only the View
     * knows when the app is in the background. Required parameter for `ServiceEventConnection` is also
     * the observer/listener of the `SiteCreationServiceState` (VM in our case), therefore we can't simply register
     * to the EventBus from the ViewModel and we have to use `sticky` events instead.
     */
    @kotlin.Suppress(names = {"unused"})
    @org.greenrobot.eventbus.Subscribe(threadMode = org.greenrobot.eventbus.ThreadMode.BACKGROUND, sticky = true)
    public final void onSiteCreationServiceStateUpdated(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.sitecreation.services.SiteCreationServiceState event) {
    }
    
    /**
     * Fetch newly created site model - supports retry with linear backoff.
     */
    private final void fetchNewlyCreatedSiteModel(long remoteSiteId) {
    }
    
    private final void startPreLoadingWebView(boolean skipDelay) {
    }
    
    public final void onUrlLoaded() {
    }
    
    public final void onWebViewError() {
    }
    
    private final org.wordpress.android.ui.sitecreation.previews.SitePreviewViewModel.SitePreviewData createSitePreviewData() {
        return null;
    }
    
    private final void showFullscreenProgress() {
    }
    
    private final void updateUiState(org.wordpress.android.ui.sitecreation.previews.SitePreviewViewModel.SitePreviewUiState uiState) {
    }
    
    private final void updateUiStateAsync(org.wordpress.android.ui.sitecreation.previews.SitePreviewViewModel.SitePreviewUiState uiState) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0005\u0011\u0012\u0013\u0014\u0015BC\b\u0004\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000b\u0082\u0001\u0005\u0016\u0017\u0018\u0019\u001a\u00a8\u0006\u001b"}, d2 = {"Lorg/wordpress/android/ui/sitecreation/previews/SitePreviewViewModel$SitePreviewUiState;", "", "fullscreenProgressLayoutVisibility", "", "contentLayoutVisibility", "webViewVisibility", "webViewErrorVisibility", "shimmerVisibility", "fullscreenErrorLayoutVisibility", "(ZZZZZZ)V", "getContentLayoutVisibility", "()Z", "getFullscreenErrorLayoutVisibility", "getFullscreenProgressLayoutVisibility", "getShimmerVisibility", "getWebViewErrorVisibility", "getWebViewVisibility", "SitePreviewContentUiState", "SitePreviewFullscreenErrorUiState", "SitePreviewFullscreenProgressUiState", "SitePreviewLoadingShimmerState", "SitePreviewWebErrorUiState", "Lorg/wordpress/android/ui/sitecreation/previews/SitePreviewViewModel$SitePreviewUiState$SitePreviewContentUiState;", "Lorg/wordpress/android/ui/sitecreation/previews/SitePreviewViewModel$SitePreviewUiState$SitePreviewWebErrorUiState;", "Lorg/wordpress/android/ui/sitecreation/previews/SitePreviewViewModel$SitePreviewUiState$SitePreviewLoadingShimmerState;", "Lorg/wordpress/android/ui/sitecreation/previews/SitePreviewViewModel$SitePreviewUiState$SitePreviewFullscreenProgressUiState;", "Lorg/wordpress/android/ui/sitecreation/previews/SitePreviewViewModel$SitePreviewUiState$SitePreviewFullscreenErrorUiState;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class SitePreviewUiState {
        private final boolean fullscreenProgressLayoutVisibility = false;
        private final boolean contentLayoutVisibility = false;
        private final boolean webViewVisibility = false;
        private final boolean webViewErrorVisibility = false;
        private final boolean shimmerVisibility = false;
        private final boolean fullscreenErrorLayoutVisibility = false;
        
        private SitePreviewUiState(boolean fullscreenProgressLayoutVisibility, boolean contentLayoutVisibility, boolean webViewVisibility, boolean webViewErrorVisibility, boolean shimmerVisibility, boolean fullscreenErrorLayoutVisibility) {
            super();
        }
        
        public final boolean getFullscreenProgressLayoutVisibility() {
            return false;
        }
        
        public final boolean getContentLayoutVisibility() {
            return false;
        }
        
        public final boolean getWebViewVisibility() {
            return false;
        }
        
        public final boolean getWebViewErrorVisibility() {
            return false;
        }
        
        public final boolean getShimmerVisibility() {
            return false;
        }
        
        public final boolean getFullscreenErrorLayoutVisibility() {
            return false;
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/sitecreation/previews/SitePreviewViewModel$SitePreviewUiState$SitePreviewContentUiState;", "Lorg/wordpress/android/ui/sitecreation/previews/SitePreviewViewModel$SitePreviewUiState;", "data", "Lorg/wordpress/android/ui/sitecreation/previews/SitePreviewViewModel$SitePreviewData;", "(Lorg/wordpress/android/ui/sitecreation/previews/SitePreviewViewModel$SitePreviewData;)V", "getData", "()Lorg/wordpress/android/ui/sitecreation/previews/SitePreviewViewModel$SitePreviewData;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class SitePreviewContentUiState extends org.wordpress.android.ui.sitecreation.previews.SitePreviewViewModel.SitePreviewUiState {
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.sitecreation.previews.SitePreviewViewModel.SitePreviewData data = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.sitecreation.previews.SitePreviewViewModel.SitePreviewUiState.SitePreviewContentUiState copy(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.sitecreation.previews.SitePreviewViewModel.SitePreviewData data) {
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
            
            public SitePreviewContentUiState(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.sitecreation.previews.SitePreviewViewModel.SitePreviewData data) {
                super(false, false, false, false, false, false);
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.sitecreation.previews.SitePreviewViewModel.SitePreviewData component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.sitecreation.previews.SitePreviewViewModel.SitePreviewData getData() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/sitecreation/previews/SitePreviewViewModel$SitePreviewUiState$SitePreviewWebErrorUiState;", "Lorg/wordpress/android/ui/sitecreation/previews/SitePreviewViewModel$SitePreviewUiState;", "data", "Lorg/wordpress/android/ui/sitecreation/previews/SitePreviewViewModel$SitePreviewData;", "(Lorg/wordpress/android/ui/sitecreation/previews/SitePreviewViewModel$SitePreviewData;)V", "getData", "()Lorg/wordpress/android/ui/sitecreation/previews/SitePreviewViewModel$SitePreviewData;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class SitePreviewWebErrorUiState extends org.wordpress.android.ui.sitecreation.previews.SitePreviewViewModel.SitePreviewUiState {
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.sitecreation.previews.SitePreviewViewModel.SitePreviewData data = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.sitecreation.previews.SitePreviewViewModel.SitePreviewUiState.SitePreviewWebErrorUiState copy(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.sitecreation.previews.SitePreviewViewModel.SitePreviewData data) {
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
            
            public SitePreviewWebErrorUiState(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.sitecreation.previews.SitePreviewViewModel.SitePreviewData data) {
                super(false, false, false, false, false, false);
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.sitecreation.previews.SitePreviewViewModel.SitePreviewData component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.sitecreation.previews.SitePreviewViewModel.SitePreviewData getData() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/sitecreation/previews/SitePreviewViewModel$SitePreviewUiState$SitePreviewLoadingShimmerState;", "Lorg/wordpress/android/ui/sitecreation/previews/SitePreviewViewModel$SitePreviewUiState;", "data", "Lorg/wordpress/android/ui/sitecreation/previews/SitePreviewViewModel$SitePreviewData;", "(Lorg/wordpress/android/ui/sitecreation/previews/SitePreviewViewModel$SitePreviewData;)V", "getData", "()Lorg/wordpress/android/ui/sitecreation/previews/SitePreviewViewModel$SitePreviewData;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class SitePreviewLoadingShimmerState extends org.wordpress.android.ui.sitecreation.previews.SitePreviewViewModel.SitePreviewUiState {
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.sitecreation.previews.SitePreviewViewModel.SitePreviewData data = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.sitecreation.previews.SitePreviewViewModel.SitePreviewUiState.SitePreviewLoadingShimmerState copy(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.sitecreation.previews.SitePreviewViewModel.SitePreviewData data) {
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
            
            public SitePreviewLoadingShimmerState(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.sitecreation.previews.SitePreviewViewModel.SitePreviewData data) {
                super(false, false, false, false, false, false);
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.sitecreation.previews.SitePreviewViewModel.SitePreviewData component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.sitecreation.previews.SitePreviewViewModel.SitePreviewData getData() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\f\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u000e\u001a\u00020\u00052\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u00d6\u0003J\t\u0010\u0011\u001a\u00020\u0012H\u00d6\u0001J\t\u0010\u0013\u001a\u00020\u0014H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0015"}, d2 = {"Lorg/wordpress/android/ui/sitecreation/previews/SitePreviewViewModel$SitePreviewUiState$SitePreviewFullscreenProgressUiState;", "Lorg/wordpress/android/ui/sitecreation/previews/SitePreviewViewModel$SitePreviewUiState;", "loadingTextResId", "Lorg/wordpress/android/ui/utils/UiString;", "animate", "", "(Lorg/wordpress/android/ui/utils/UiString;Z)V", "getAnimate", "()Z", "getLoadingTextResId", "()Lorg/wordpress/android/ui/utils/UiString;", "component1", "component2", "copy", "equals", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class SitePreviewFullscreenProgressUiState extends org.wordpress.android.ui.sitecreation.previews.SitePreviewViewModel.SitePreviewUiState {
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.utils.UiString loadingTextResId = null;
            private final boolean animate = false;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.sitecreation.previews.SitePreviewViewModel.SitePreviewUiState.SitePreviewFullscreenProgressUiState copy(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.utils.UiString loadingTextResId, boolean animate) {
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
            
            public SitePreviewFullscreenProgressUiState(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.utils.UiString loadingTextResId, boolean animate) {
                super(false, false, false, false, false, false);
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.utils.UiString component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.utils.UiString getLoadingTextResId() {
                return null;
            }
            
            public final boolean component2() {
                return false;
            }
            
            public final boolean getAnimate() {
                return false;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0011\u0012B/\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\bR\u0011\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010\u0082\u0001\u0002\u0013\u0014\u00a8\u0006\u0015"}, d2 = {"Lorg/wordpress/android/ui/sitecreation/previews/SitePreviewViewModel$SitePreviewUiState$SitePreviewFullscreenErrorUiState;", "Lorg/wordpress/android/ui/sitecreation/previews/SitePreviewViewModel$SitePreviewUiState;", "titleResId", "", "subtitleResId", "showContactSupport", "", "showCancelWizardButton", "(ILjava/lang/Integer;ZZ)V", "getShowCancelWizardButton", "()Z", "getShowContactSupport", "getSubtitleResId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getTitleResId", "()I", "SitePreviewConnectionErrorUiState", "SitePreviewGenericErrorUiState", "Lorg/wordpress/android/ui/sitecreation/previews/SitePreviewViewModel$SitePreviewUiState$SitePreviewFullscreenErrorUiState$SitePreviewGenericErrorUiState;", "Lorg/wordpress/android/ui/sitecreation/previews/SitePreviewViewModel$SitePreviewUiState$SitePreviewFullscreenErrorUiState$SitePreviewConnectionErrorUiState;", "org.wordpress.android_wordpressJalapenoDebug"})
        public static abstract class SitePreviewFullscreenErrorUiState extends org.wordpress.android.ui.sitecreation.previews.SitePreviewViewModel.SitePreviewUiState {
            private final int titleResId = 0;
            @org.jetbrains.annotations.Nullable()
            private final java.lang.Integer subtitleResId = null;
            private final boolean showContactSupport = false;
            private final boolean showCancelWizardButton = false;
            
            private SitePreviewFullscreenErrorUiState(int titleResId, java.lang.Integer subtitleResId, boolean showContactSupport, boolean showCancelWizardButton) {
                super(false, false, false, false, false, false);
            }
            
            public final int getTitleResId() {
                return 0;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final java.lang.Integer getSubtitleResId() {
                return null;
            }
            
            public final boolean getShowContactSupport() {
                return false;
            }
            
            public final boolean getShowCancelWizardButton() {
                return false;
            }
            
            @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/sitecreation/previews/SitePreviewViewModel$SitePreviewUiState$SitePreviewFullscreenErrorUiState$SitePreviewGenericErrorUiState;", "Lorg/wordpress/android/ui/sitecreation/previews/SitePreviewViewModel$SitePreviewUiState$SitePreviewFullscreenErrorUiState;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
            public static final class SitePreviewGenericErrorUiState extends org.wordpress.android.ui.sitecreation.previews.SitePreviewViewModel.SitePreviewUiState.SitePreviewFullscreenErrorUiState {
                @org.jetbrains.annotations.NotNull()
                public static final org.wordpress.android.ui.sitecreation.previews.SitePreviewViewModel.SitePreviewUiState.SitePreviewFullscreenErrorUiState.SitePreviewGenericErrorUiState INSTANCE = null;
                
                private SitePreviewGenericErrorUiState() {
                    super(0, null, false, false);
                }
            }
            
            @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/sitecreation/previews/SitePreviewViewModel$SitePreviewUiState$SitePreviewFullscreenErrorUiState$SitePreviewConnectionErrorUiState;", "Lorg/wordpress/android/ui/sitecreation/previews/SitePreviewViewModel$SitePreviewUiState$SitePreviewFullscreenErrorUiState;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
            public static final class SitePreviewConnectionErrorUiState extends org.wordpress.android.ui.sitecreation.previews.SitePreviewViewModel.SitePreviewUiState.SitePreviewFullscreenErrorUiState {
                @org.jetbrains.annotations.NotNull()
                public static final org.wordpress.android.ui.sitecreation.previews.SitePreviewViewModel.SitePreviewUiState.SitePreviewFullscreenErrorUiState.SitePreviewConnectionErrorUiState INSTANCE = null;
                
                private SitePreviewConnectionErrorUiState() {
                    super(0, null, false, false);
                }
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0006\u00a2\u0006\u0002\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0011\u001a\u00020\u0003H\u00c6\u0003J\u0015\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0006H\u00c6\u0003J\u0015\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0006H\u00c6\u0003JI\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u0014\b\u0002\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u00062\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0006H\u00c6\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0018\u001a\u00020\u0007H\u00d6\u0001J\t\u0010\u0019\u001a\u00020\u0003H\u00d6\u0001R\u001d\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u001d\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000b\u00a8\u0006\u001a"}, d2 = {"Lorg/wordpress/android/ui/sitecreation/previews/SitePreviewViewModel$SitePreviewData;", "", "fullUrl", "", "shortUrl", "domainIndices", "Lkotlin/Pair;", "", "subDomainIndices", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/Pair;Lkotlin/Pair;)V", "getDomainIndices", "()Lkotlin/Pair;", "getFullUrl", "()Ljava/lang/String;", "getShortUrl", "getSubDomainIndices", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class SitePreviewData {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String fullUrl = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String shortUrl = null;
        @org.jetbrains.annotations.NotNull()
        private final kotlin.Pair<java.lang.Integer, java.lang.Integer> domainIndices = null;
        @org.jetbrains.annotations.NotNull()
        private final kotlin.Pair<java.lang.Integer, java.lang.Integer> subDomainIndices = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.sitecreation.previews.SitePreviewViewModel.SitePreviewData copy(@org.jetbrains.annotations.NotNull()
        java.lang.String fullUrl, @org.jetbrains.annotations.NotNull()
        java.lang.String shortUrl, @org.jetbrains.annotations.NotNull()
        kotlin.Pair<java.lang.Integer, java.lang.Integer> domainIndices, @org.jetbrains.annotations.NotNull()
        kotlin.Pair<java.lang.Integer, java.lang.Integer> subDomainIndices) {
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
        
        public SitePreviewData(@org.jetbrains.annotations.NotNull()
        java.lang.String fullUrl, @org.jetbrains.annotations.NotNull()
        java.lang.String shortUrl, @org.jetbrains.annotations.NotNull()
        kotlin.Pair<java.lang.Integer, java.lang.Integer> domainIndices, @org.jetbrains.annotations.NotNull()
        kotlin.Pair<java.lang.Integer, java.lang.Integer> subDomainIndices) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getFullUrl() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getShortUrl() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.Pair<java.lang.Integer, java.lang.Integer> component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.Pair<java.lang.Integer, java.lang.Integer> getDomainIndices() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.Pair<java.lang.Integer, java.lang.Integer> component4() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.Pair<java.lang.Integer, java.lang.Integer> getSubDomainIndices() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u001f\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u00c6\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0011\u001a\u00020\u0012H\u00d6\u0001J\t\u0010\u0013\u001a\u00020\u0014H\u00d6\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0015"}, d2 = {"Lorg/wordpress/android/ui/sitecreation/previews/SitePreviewViewModel$SitePreviewStartServiceData;", "", "serviceData", "Lorg/wordpress/android/ui/sitecreation/services/SiteCreationServiceData;", "previousState", "Lorg/wordpress/android/ui/sitecreation/services/SiteCreationServiceState;", "(Lorg/wordpress/android/ui/sitecreation/services/SiteCreationServiceData;Lorg/wordpress/android/ui/sitecreation/services/SiteCreationServiceState;)V", "getPreviousState", "()Lorg/wordpress/android/ui/sitecreation/services/SiteCreationServiceState;", "getServiceData", "()Lorg/wordpress/android/ui/sitecreation/services/SiteCreationServiceData;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class SitePreviewStartServiceData {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.sitecreation.services.SiteCreationServiceData serviceData = null;
        @org.jetbrains.annotations.Nullable()
        private final org.wordpress.android.ui.sitecreation.services.SiteCreationServiceState previousState = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.sitecreation.previews.SitePreviewViewModel.SitePreviewStartServiceData copy(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.sitecreation.services.SiteCreationServiceData serviceData, @org.jetbrains.annotations.Nullable()
        org.wordpress.android.ui.sitecreation.services.SiteCreationServiceState previousState) {
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
        
        public SitePreviewStartServiceData(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.sitecreation.services.SiteCreationServiceData serviceData, @org.jetbrains.annotations.Nullable()
        org.wordpress.android.ui.sitecreation.services.SiteCreationServiceState previousState) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.sitecreation.services.SiteCreationServiceData component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.sitecreation.services.SiteCreationServiceData getServiceData() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final org.wordpress.android.ui.sitecreation.services.SiteCreationServiceState component2() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final org.wordpress.android.ui.sitecreation.services.SiteCreationServiceState getPreviousState() {
            return null;
        }
    }
    
    @android.annotation.SuppressLint(value = {"ParcelCreator"})
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u00002\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0003\u0006\u0007\b\u00a8\u0006\t"}, d2 = {"Lorg/wordpress/android/ui/sitecreation/previews/SitePreviewViewModel$CreateSiteState;", "Landroid/os/Parcelable;", "()V", "SiteCreationCompleted", "SiteNotCreated", "SiteNotInLocalDb", "Lorg/wordpress/android/ui/sitecreation/previews/SitePreviewViewModel$CreateSiteState$SiteNotCreated;", "Lorg/wordpress/android/ui/sitecreation/previews/SitePreviewViewModel$CreateSiteState$SiteNotInLocalDb;", "Lorg/wordpress/android/ui/sitecreation/previews/SitePreviewViewModel$CreateSiteState$SiteCreationCompleted;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class CreateSiteState implements android.os.Parcelable {
        
        private CreateSiteState() {
            super();
        }
        
        /**
         * CreateSite request haven't finished yet or failed.
         */
        @kotlinx.parcelize.Parcelize()
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\t\u0010\u0003\u001a\u00020\u0004H\u00d6\u0001J\u0019\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0004H\u00d6\u0001\u00a8\u0006\n"}, d2 = {"Lorg/wordpress/android/ui/sitecreation/previews/SitePreviewViewModel$CreateSiteState$SiteNotCreated;", "Lorg/wordpress/android/ui/sitecreation/previews/SitePreviewViewModel$CreateSiteState;", "()V", "describeContents", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class SiteNotCreated extends org.wordpress.android.ui.sitecreation.previews.SitePreviewViewModel.CreateSiteState {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.ui.sitecreation.previews.SitePreviewViewModel.CreateSiteState.SiteNotCreated INSTANCE = null;
            public static final android.os.Parcelable.Creator<org.wordpress.android.ui.sitecreation.previews.SitePreviewViewModel.CreateSiteState.SiteNotCreated> CREATOR = null;
            
            private SiteNotCreated() {
                super();
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
            public static final class Creator implements android.os.Parcelable.Creator<org.wordpress.android.ui.sitecreation.previews.SitePreviewViewModel.CreateSiteState.SiteNotCreated> {
                
                public Creator() {
                    super();
                }
                
                @org.jetbrains.annotations.NotNull()
                @java.lang.Override()
                public final org.wordpress.android.ui.sitecreation.previews.SitePreviewViewModel.CreateSiteState.SiteNotCreated createFromParcel(@org.jetbrains.annotations.NotNull()
                android.os.Parcel in) {
                    return null;
                }
                
                @org.jetbrains.annotations.NotNull()
                @java.lang.Override()
                public final org.wordpress.android.ui.sitecreation.previews.SitePreviewViewModel.CreateSiteState.SiteNotCreated[] newArray(int size) {
                    return null;
                }
            }
        }
        
        /**
         * FetchSite request haven't finished yet or failed.
         * Since we fetch the site without user awareness in background, the user may potentially leave the screen
         * before the request is finished.
         */
        @kotlinx.parcelize.Parcelize()
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\t\u0010\n\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000b\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\u0013\u0010\u000f\u001a\u00020\u00052\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u00d6\u0003J\t\u0010\u0012\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u0013\u001a\u00020\u0014H\u00d6\u0001J\u0019\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u000eH\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t\u00a8\u0006\u001a"}, d2 = {"Lorg/wordpress/android/ui/sitecreation/previews/SitePreviewViewModel$CreateSiteState$SiteNotInLocalDb;", "Lorg/wordpress/android/ui/sitecreation/previews/SitePreviewViewModel$CreateSiteState;", "remoteSiteId", "", "isSiteTitleTaskComplete", "", "(JZ)V", "()Z", "getRemoteSiteId", "()J", "component1", "component2", "copy", "describeContents", "", "equals", "other", "", "hashCode", "toString", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class SiteNotInLocalDb extends org.wordpress.android.ui.sitecreation.previews.SitePreviewViewModel.CreateSiteState {
            private final long remoteSiteId = 0L;
            private final boolean isSiteTitleTaskComplete = false;
            public static final android.os.Parcelable.Creator<org.wordpress.android.ui.sitecreation.previews.SitePreviewViewModel.CreateSiteState.SiteNotInLocalDb> CREATOR = null;
            
            /**
             * FetchSite request haven't finished yet or failed.
             * Since we fetch the site without user awareness in background, the user may potentially leave the screen
             * before the request is finished.
             */
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.sitecreation.previews.SitePreviewViewModel.CreateSiteState.SiteNotInLocalDb copy(long remoteSiteId, boolean isSiteTitleTaskComplete) {
                return null;
            }
            
            /**
             * FetchSite request haven't finished yet or failed.
             * Since we fetch the site without user awareness in background, the user may potentially leave the screen
             * before the request is finished.
             */
            @java.lang.Override()
            public boolean equals(@org.jetbrains.annotations.Nullable()
            java.lang.Object other) {
                return false;
            }
            
            /**
             * FetchSite request haven't finished yet or failed.
             * Since we fetch the site without user awareness in background, the user may potentially leave the screen
             * before the request is finished.
             */
            @java.lang.Override()
            public int hashCode() {
                return 0;
            }
            
            /**
             * FetchSite request haven't finished yet or failed.
             * Since we fetch the site without user awareness in background, the user may potentially leave the screen
             * before the request is finished.
             */
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public java.lang.String toString() {
                return null;
            }
            
            public SiteNotInLocalDb(long remoteSiteId, boolean isSiteTitleTaskComplete) {
                super();
            }
            
            public final long component1() {
                return 0L;
            }
            
            public final long getRemoteSiteId() {
                return 0L;
            }
            
            public final boolean component2() {
                return false;
            }
            
            public final boolean isSiteTitleTaskComplete() {
                return false;
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
            public static final class Creator implements android.os.Parcelable.Creator<org.wordpress.android.ui.sitecreation.previews.SitePreviewViewModel.CreateSiteState.SiteNotInLocalDb> {
                
                public Creator() {
                    super();
                }
                
                @org.jetbrains.annotations.NotNull()
                @java.lang.Override()
                public final org.wordpress.android.ui.sitecreation.previews.SitePreviewViewModel.CreateSiteState.SiteNotInLocalDb createFromParcel(@org.jetbrains.annotations.NotNull()
                android.os.Parcel in) {
                    return null;
                }
                
                @org.jetbrains.annotations.NotNull()
                @java.lang.Override()
                public final org.wordpress.android.ui.sitecreation.previews.SitePreviewViewModel.CreateSiteState.SiteNotInLocalDb[] newArray(int size) {
                    return null;
                }
            }
        }
        
        /**
         * The site has been successfully created and stored into local db.
         */
        @kotlinx.parcelize.Parcelize()
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\t\u0010\n\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000b\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\t\u0010\r\u001a\u00020\u0003H\u00d6\u0001J\u0013\u0010\u000e\u001a\u00020\u00052\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u00d6\u0003J\t\u0010\u0011\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u0012\u001a\u00020\u0013H\u00d6\u0001J\u0019\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t\u00a8\u0006\u0019"}, d2 = {"Lorg/wordpress/android/ui/sitecreation/previews/SitePreviewViewModel$CreateSiteState$SiteCreationCompleted;", "Lorg/wordpress/android/ui/sitecreation/previews/SitePreviewViewModel$CreateSiteState;", "localSiteId", "", "isSiteTitleTaskComplete", "", "(IZ)V", "()Z", "getLocalSiteId", "()I", "component1", "component2", "copy", "describeContents", "equals", "other", "", "hashCode", "toString", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class SiteCreationCompleted extends org.wordpress.android.ui.sitecreation.previews.SitePreviewViewModel.CreateSiteState {
            private final int localSiteId = 0;
            private final boolean isSiteTitleTaskComplete = false;
            public static final android.os.Parcelable.Creator<org.wordpress.android.ui.sitecreation.previews.SitePreviewViewModel.CreateSiteState.SiteCreationCompleted> CREATOR = null;
            
            /**
             * The site has been successfully created and stored into local db.
             */
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.sitecreation.previews.SitePreviewViewModel.CreateSiteState.SiteCreationCompleted copy(int localSiteId, boolean isSiteTitleTaskComplete) {
                return null;
            }
            
            /**
             * The site has been successfully created and stored into local db.
             */
            @java.lang.Override()
            public boolean equals(@org.jetbrains.annotations.Nullable()
            java.lang.Object other) {
                return false;
            }
            
            /**
             * The site has been successfully created and stored into local db.
             */
            @java.lang.Override()
            public int hashCode() {
                return 0;
            }
            
            /**
             * The site has been successfully created and stored into local db.
             */
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public java.lang.String toString() {
                return null;
            }
            
            public SiteCreationCompleted(int localSiteId, boolean isSiteTitleTaskComplete) {
                super();
            }
            
            public final int component1() {
                return 0;
            }
            
            public final int getLocalSiteId() {
                return 0;
            }
            
            public final boolean component2() {
                return false;
            }
            
            public final boolean isSiteTitleTaskComplete() {
                return false;
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
            public static final class Creator implements android.os.Parcelable.Creator<org.wordpress.android.ui.sitecreation.previews.SitePreviewViewModel.CreateSiteState.SiteCreationCompleted> {
                
                public Creator() {
                    super();
                }
                
                @org.jetbrains.annotations.NotNull()
                @java.lang.Override()
                public final org.wordpress.android.ui.sitecreation.previews.SitePreviewViewModel.CreateSiteState.SiteCreationCompleted createFromParcel(@org.jetbrains.annotations.NotNull()
                android.os.Parcel in) {
                    return null;
                }
                
                @org.jetbrains.annotations.NotNull()
                @java.lang.Override()
                public final org.wordpress.android.ui.sitecreation.previews.SitePreviewViewModel.CreateSiteState.SiteCreationCompleted[] newArray(int size) {
                    return null;
                }
            }
        }
    }
}