package org.wordpress.android.ui.qrcodeauth;

import java.lang.System;

@dagger.hilt.android.lifecycle.HiltViewModel()
@kotlin.Suppress(names = {"TooManyFunctions"})
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u00a6\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u0000 T2\u00020\u0001:\u0001TB/\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0002\u0010\fJ\u0018\u0010%\u001a\u00020&2\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001f\u001a\u00020\u0018H\u0002J\b\u0010\'\u001a\u00020&H\u0002J\u0012\u0010(\u001a\u00020&2\b\u0010)\u001a\u0004\u0018\u00010\u0018H\u0002J\u0012\u0010*\u001a\u00020&2\b\u0010+\u001a\u0004\u0018\u00010,H\u0002J\b\u0010-\u001a\u00020.H\u0002J\u0010\u0010/\u001a\u0002002\u0006\u00101\u001a\u000202H\u0002J\u0016\u00103\u001a\u0002042\f\u00105\u001a\b\u0012\u0004\u0012\u00020706H\u0002J\b\u00108\u001a\u00020&H\u0002J\b\u00109\u001a\u00020&H\u0002J\u0006\u0010:\u001a\u00020&J\b\u0010;\u001a\u00020&H\u0002J\u000e\u0010<\u001a\u00020&2\u0006\u0010=\u001a\u00020>J\b\u0010?\u001a\u00020&H\u0002J\b\u0010@\u001a\u00020&H\u0002J\u0006\u0010A\u001a\u00020&J\u0010\u0010B\u001a\u00020&2\b\u0010)\u001a\u0004\u0018\u00010\u0018J\u0010\u0010C\u001a\u00020&2\u0006\u0010D\u001a\u00020\u000fH\u0002J\u0010\u0010E\u001a\u00020&2\u0006\u0010F\u001a\u00020\u0012H\u0002J\u0012\u0010G\u001a\u00020&2\b\u0010H\u001a\u0004\u0018\u00010\u0018H\u0002J(\u0010I\u001a\u00020&2\n\b\u0002\u0010J\u001a\u0004\u0018\u00010\u00182\b\b\u0002\u0010K\u001a\u00020\u001b2\n\b\u0002\u0010+\u001a\u0004\u0018\u00010,J\b\u0010L\u001a\u00020&H\u0002J\u001a\u0010M\u001a\u00020&2\u0006\u0010N\u001a\u00020O2\n\b\u0002\u00101\u001a\u0004\u0018\u00010\u0018J\b\u0010P\u001a\u00020&H\u0002J\u0018\u0010Q\u001a\u00020&2\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001f\u001a\u00020\u0018H\u0002J\u000e\u0010R\u001a\u00020&2\u0006\u0010S\u001a\u00020,R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u0018X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u0018X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u0004\u0018\u00010\u0018X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010 \u001a\u0004\u0018\u00010\u0018X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0017\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00120\"\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006U"}, d2 = {"Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthViewModel;", "Landroidx/lifecycle/ViewModel;", "authStore", "Lorg/wordpress/android/fluxc/store/qrcodeauth/QRCodeAuthStore;", "uiStateMapper", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiStateMapper;", "networkUtilsWrapper", "Lorg/wordpress/android/util/NetworkUtilsWrapper;", "validator", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthValidator;", "analyticsTrackerWrapper", "Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;", "(Lorg/wordpress/android/fluxc/store/qrcodeauth/QRCodeAuthStore;Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiStateMapper;Lorg/wordpress/android/util/NetworkUtilsWrapper;Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthValidator;Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;)V", "_actionEvents", "Lkotlinx/coroutines/channels/Channel;", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthActionEvent;", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState;", "actionEvents", "Lkotlinx/coroutines/flow/Flow;", "getActionEvents", "()Lkotlinx/coroutines/flow/Flow;", "browser", "", "data", "isStarted", "", "lastState", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiStateType;", "location", "token", "trackingOrigin", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "authenticate", "", "clearProperties", "extractQueryParamsIfValid", "scannedValue", "extractSavedInstanceStateIfNeeded", "savedInstanceState", "Landroid/os/Bundle;", "mapAuthenticateSuccessToDoneState", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$Content$Done;", "mapScanErrorToErrorState", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$Error;", "error", "Lorg/wordpress/android/fluxc/network/rest/wpcom/qrcodeauth/QRCodeAuthError;", "mapScanSuccessToValidatedState", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$Content$Validated;", "result", "Lorg/wordpress/android/fluxc/store/qrcodeauth/QRCodeAuthStore$QRCodeAuthResult;", "Lorg/wordpress/android/fluxc/store/qrcodeauth/QRCodeAuthStore$QRCodeAuthValidateResult;", "onAuthenticateCancelClicked", "onAuthenticateClicked", "onBackPressed", "onCancelClicked", "onDialogInteraction", "interaction", "Lorg/wordpress/android/ui/posts/BasicDialogViewModel$DialogInteraction;", "onDismissClicked", "onScanAgainClicked", "onScanFailure", "onScanSuccess", "postActionEvent", "actionEvent", "postUiState", "state", "process", "input", "start", "uri", "isDeepLink", "startOrRestoreUiState", "track", "stat", "Lorg/wordpress/android/analytics/AnalyticsTracker$Stat;", "updateUiStateAndLaunchScanner", "validateScan", "writeToBundle", "outState", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public final class QRCodeAuthViewModel extends androidx.lifecycle.ViewModel {
    private final org.wordpress.android.fluxc.store.qrcodeauth.QRCodeAuthStore authStore = null;
    private final org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiStateMapper uiStateMapper = null;
    private final org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper = null;
    private final org.wordpress.android.ui.qrcodeauth.QRCodeAuthValidator validator = null;
    private final org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTrackerWrapper = null;
    private final kotlinx.coroutines.channels.Channel<org.wordpress.android.ui.qrcodeauth.QRCodeAuthActionEvent> _actionEvents = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<org.wordpress.android.ui.qrcodeauth.QRCodeAuthActionEvent> actionEvents = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState> uiState = null;
    private java.lang.String trackingOrigin;
    private java.lang.String data;
    private java.lang.String token;
    private java.lang.String location;
    private java.lang.String browser;
    private org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiStateType lastState;
    private boolean isStarted = false;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.qrcodeauth.QRCodeAuthViewModel.Companion Companion = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String TAG_DISMISS_DIALOG = "TAG_DISMISS_DIALOG";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String TOKEN_KEY = "token";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String DATA_KEY = "data";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String BROWSER_KEY = "browser";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String LOCATION_KEY = "location";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String LAST_STATE_KEY = "last_state";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String TRACKING_ORIGIN_KEY = "tracking_origin";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ORIGIN = "origin";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ORIGIN_MENU = "menu";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ORIGIN_DEEPLINK = "deep_link";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXPIRED_MESSAGE = "qr code data expired";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ERROR = "error";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String TRACK_INVALID_DATA = "invalid_data";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String TRACK_NO_INTERNET = "no_internet";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String TRACK_AUTHENTICATION_FAILED = "authentication_failed";
    
    @javax.inject.Inject()
    public QRCodeAuthViewModel(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.qrcodeauth.QRCodeAuthStore authStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiStateMapper uiStateMapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.qrcodeauth.QRCodeAuthValidator validator, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTrackerWrapper) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<org.wordpress.android.ui.qrcodeauth.QRCodeAuthActionEvent> getActionEvents() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState> getUiState() {
        return null;
    }
    
    public final void start(@org.jetbrains.annotations.Nullable()
    java.lang.String uri, boolean isDeepLink, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void extractSavedInstanceStateIfNeeded(android.os.Bundle savedInstanceState) {
    }
    
    private final void startOrRestoreUiState() {
    }
    
    public final void onScanSuccess(@org.jetbrains.annotations.Nullable()
    java.lang.String scannedValue) {
    }
    
    public final void onScanFailure() {
    }
    
    public final void onBackPressed() {
    }
    
    private final void onCancelClicked() {
    }
    
    private final void onScanAgainClicked() {
    }
    
    private final void onDismissClicked() {
    }
    
    private final void onAuthenticateClicked() {
    }
    
    private final void onAuthenticateCancelClicked() {
    }
    
    private final void process(java.lang.String input) {
    }
    
    private final void validateScan(java.lang.String data, java.lang.String token) {
    }
    
    private final org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.Content.Validated mapScanSuccessToValidatedState(org.wordpress.android.fluxc.store.qrcodeauth.QRCodeAuthStore.QRCodeAuthResult<org.wordpress.android.fluxc.store.qrcodeauth.QRCodeAuthStore.QRCodeAuthValidateResult> result) {
        return null;
    }
    
    private final org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.Error mapScanErrorToErrorState(org.wordpress.android.fluxc.network.rest.wpcom.qrcodeauth.QRCodeAuthError error) {
        return null;
    }
    
    private final void extractQueryParamsIfValid(java.lang.String scannedValue) {
    }
    
    private final void authenticate(java.lang.String data, java.lang.String token) {
    }
    
    private final org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.Content.Done mapAuthenticateSuccessToDoneState() {
        return null;
    }
    
    private final void updateUiStateAndLaunchScanner() {
    }
    
    private final void postUiState(org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState state) {
    }
    
    private final void postActionEvent(org.wordpress.android.ui.qrcodeauth.QRCodeAuthActionEvent actionEvent) {
    }
    
    public final void onDialogInteraction(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.BasicDialogViewModel.DialogInteraction interaction) {
    }
    
    private final void clearProperties() {
    }
    
    public final void writeToBundle(@org.jetbrains.annotations.NotNull()
    android.os.Bundle outState) {
    }
    
    public final void track(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.analytics.AnalyticsTracker.Stat stat, @org.jetbrains.annotations.Nullable()
    java.lang.String error) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000f\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthViewModel$Companion;", "", "()V", "BROWSER_KEY", "", "DATA_KEY", "ERROR", "EXPIRED_MESSAGE", "LAST_STATE_KEY", "LOCATION_KEY", "ORIGIN", "ORIGIN_DEEPLINK", "ORIGIN_MENU", "TAG_DISMISS_DIALOG", "TOKEN_KEY", "TRACKING_ORIGIN_KEY", "TRACK_AUTHENTICATION_FAILED", "TRACK_INVALID_DATA", "TRACK_NO_INTERNET", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}