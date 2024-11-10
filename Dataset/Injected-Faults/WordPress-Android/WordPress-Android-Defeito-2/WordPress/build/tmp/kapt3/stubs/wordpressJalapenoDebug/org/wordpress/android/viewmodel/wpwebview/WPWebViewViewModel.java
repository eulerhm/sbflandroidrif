package org.wordpress.android.viewmodel.wpwebview;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 R2\u00020\u00012\u00020\u0002:\u0004RSTUB-\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u00a2\u0006\u0002\u0010\fJ\u0006\u00109\u001a\u00020:J\u0010\u0010;\u001a\u00020<2\u0006\u0010/\u001a\u00020\u0018H\u0002J\u0006\u0010=\u001a\u00020\u000fJ\u0006\u0010>\u001a\u00020\u0014J\u0006\u0010)\u001a\u00020\u0014J\u0006\u0010+\u001a\u00020\u0014J\b\u0010?\u001a\u00020\u0014H\u0014J\u0010\u0010@\u001a\u00020\u00142\u0006\u0010A\u001a\u00020\u0018H\u0016J\u0006\u0010B\u001a\u00020\u0014J\u0006\u0010C\u001a\u00020\u0014J\u0006\u0010D\u001a\u00020\u0014J\u000e\u0010E\u001a\u00020\u00142\u0006\u0010F\u001a\u00020\u0018J\b\u0010F\u001a\u00020\u0018H\u0016J\u0006\u00103\u001a\u00020\u0014J\u000e\u0010G\u001a\u00020\u00142\u0006\u0010H\u001a\u000208J\u000e\u0010I\u001a\u00020\u00142\u0006\u0010J\u001a\u00020\u000fJ\u000e\u0010K\u001a\u00020\u00142\u0006\u0010J\u001a\u00020\u000fJ\u000e\u0010L\u001a\u00020\u00142\u0006\u0010M\u001a\u00020\u000fJ\u000e\u0010N\u001a\u00020\u00142\u0006\u0010O\u001a\u00020PJ\u0010\u0010Q\u001a\u00020\u00142\u0006\u00105\u001a\u00020\u001dH\u0002R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00140\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00140\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00140\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001d0\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u001e\u001a\u00020\u00188BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u001f\u0010 R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020#X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010$\u001a\b\u0012\u0004\u0012\u00020\u000f0\n\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0017\u0010\'\u001a\b\u0012\u0004\u0012\u00020\u00120\n\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010&R\u0017\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00140\n\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010&R\u0017\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00140\n\u00a2\u0006\b\n\u0000\u001a\u0004\b,\u0010&R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00140\n\u00a2\u0006\b\n\u0000\u001a\u0004\b.\u0010&R\u0017\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00180\n\u00a2\u0006\b\n\u0000\u001a\u0004\b0\u0010&R\u0017\u00101\u001a\b\u0012\u0004\u0012\u00020\u001a0\n\u00a2\u0006\b\n\u0000\u001a\u0004\b2\u0010&R\u0017\u00103\u001a\b\u0012\u0004\u0012\u00020\u00140\n\u00a2\u0006\b\n\u0000\u001a\u0004\b4\u0010&R\u0017\u00105\u001a\b\u0012\u0004\u0012\u00020\u001d0\n\u00a2\u0006\b\n\u0000\u001a\u0004\b6\u0010&R\u000e\u00107\u001a\u000208X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006V"}, d2 = {"Lorg/wordpress/android/viewmodel/wpwebview/WPWebViewViewModel;", "Landroidx/lifecycle/ViewModel;", "Lorg/wordpress/android/ui/PreviewModeHandler;", "displayUtils", "Lorg/wordpress/android/util/DisplayUtilsWrapper;", "networkUtils", "Lorg/wordpress/android/util/NetworkUtilsWrapper;", "analyticsTrackerWrapper", "Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;", "connectionStatus", "Landroidx/lifecycle/LiveData;", "Lorg/wordpress/android/viewmodel/helpers/ConnectionStatus;", "(Lorg/wordpress/android/util/DisplayUtilsWrapper;Lorg/wordpress/android/util/NetworkUtilsWrapper;Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;Landroidx/lifecycle/LiveData;)V", "_loadNeeded", "Lorg/wordpress/android/viewmodel/SingleLiveEvent;", "", "_navbarUiState", "Landroidx/lifecycle/MutableLiveData;", "Lorg/wordpress/android/viewmodel/wpwebview/WPWebViewViewModel$NavBarUiState;", "_navigateBack", "", "_navigateForward", "_openInExternalBrowser", "_previewMode", "Lorg/wordpress/android/ui/PreviewMode;", "_previewModeSelector", "Lorg/wordpress/android/viewmodel/wpwebview/WPWebViewViewModel$PreviewModeSelectorStatus;", "_share", "_uiState", "Lorg/wordpress/android/viewmodel/wpwebview/WPWebViewViewModel$WebPreviewUiState;", "defaultPreviewMode", "getDefaultPreviewMode", "()Lorg/wordpress/android/ui/PreviewMode;", "isStarted", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "loadNeeded", "getLoadNeeded", "()Landroidx/lifecycle/LiveData;", "navbarUiState", "getNavbarUiState", "navigateBack", "getNavigateBack", "navigateForward", "getNavigateForward", "openExternalBrowser", "getOpenExternalBrowser", "previewMode", "getPreviewMode", "previewModeSelector", "getPreviewModeSelector", "share", "getShare", "uiState", "getUiState", "wpWebViewUsageCategory", "Lorg/wordpress/android/ui/WPWebViewUsageCategory;", "getMenuUiState", "Lorg/wordpress/android/ui/WPWebViewMenuUiState;", "getPreviewHintResId", "", "isActionableDirectUsage", "loadIfNecessary", "onCleared", "onPreviewModeChanged", "mode", "onReceivedError", "onUrlLoaded", "openPageInExternalBrowser", "selectPreviewMode", "selectedPreviewMode", "start", "webViewUsageCategory", "toggleBackNavigation", "isEnabled", "toggleForwardNavigation", "togglePreviewModeSelectorVisibility", "isVisible", "track", "stat", "Lorg/wordpress/android/analytics/AnalyticsTracker$Stat;", "updateUiState", "Companion", "NavBarUiState", "PreviewModeSelectorStatus", "WebPreviewUiState", "org.wordpress.android_wordpressJalapenoDebug"})
public final class WPWebViewViewModel extends androidx.lifecycle.ViewModel implements org.wordpress.android.ui.PreviewModeHandler {
    private final org.wordpress.android.util.DisplayUtilsWrapper displayUtils = null;
    private final org.wordpress.android.util.NetworkUtilsWrapper networkUtils = null;
    private final org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTrackerWrapper = null;
    private boolean isStarted = false;
    private org.wordpress.android.ui.WPWebViewUsageCategory wpWebViewUsageCategory = org.wordpress.android.ui.WPWebViewUsageCategory.WEBVIEW_STANDARD;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.wpwebview.WPWebViewViewModel.WebPreviewUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.wpwebview.WPWebViewViewModel.WebPreviewUiState> uiState = null;
    private final org.wordpress.android.viewmodel.SingleLiveEvent<java.lang.Boolean> _loadNeeded = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Boolean> loadNeeded = null;
    private final org.wordpress.android.viewmodel.SingleLiveEvent<kotlin.Unit> _navigateBack = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<kotlin.Unit> navigateBack = null;
    private final org.wordpress.android.viewmodel.SingleLiveEvent<kotlin.Unit> _navigateForward = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<kotlin.Unit> navigateForward = null;
    private final org.wordpress.android.viewmodel.SingleLiveEvent<kotlin.Unit> _share = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<kotlin.Unit> share = null;
    private final org.wordpress.android.viewmodel.SingleLiveEvent<kotlin.Unit> _openInExternalBrowser = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<kotlin.Unit> openExternalBrowser = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.wpwebview.WPWebViewViewModel.PreviewModeSelectorStatus> _previewModeSelector = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.wpwebview.WPWebViewViewModel.PreviewModeSelectorStatus> previewModeSelector = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.wpwebview.WPWebViewViewModel.NavBarUiState> _navbarUiState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.wpwebview.WPWebViewViewModel.NavBarUiState> navbarUiState = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.ui.PreviewMode> _previewMode = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.ui.PreviewMode> previewMode = null;
    private final androidx.lifecycle.LifecycleOwner lifecycleOwner = null;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.viewmodel.wpwebview.WPWebViewViewModel.Companion Companion = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String TRACK_SELECTED_OPTION_NAME = "selected_option_name";
    
    @javax.inject.Inject()
    public WPWebViewViewModel(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.DisplayUtilsWrapper displayUtils, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.NetworkUtilsWrapper networkUtils, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTrackerWrapper, @org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.helpers.ConnectionStatus> connectionStatus) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.wpwebview.WPWebViewViewModel.WebPreviewUiState> getUiState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Boolean> getLoadNeeded() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<kotlin.Unit> getNavigateBack() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<kotlin.Unit> getNavigateForward() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<kotlin.Unit> getShare() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<kotlin.Unit> getOpenExternalBrowser() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.wpwebview.WPWebViewViewModel.PreviewModeSelectorStatus> getPreviewModeSelector() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.wpwebview.WPWebViewViewModel.NavBarUiState> getNavbarUiState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.ui.PreviewMode> getPreviewMode() {
        return null;
    }
    
    private final org.wordpress.android.ui.PreviewMode getDefaultPreviewMode() {
        return null;
    }
    
    public final void start(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.WPWebViewUsageCategory webViewUsageCategory) {
    }
    
    @java.lang.Override()
    protected void onCleared() {
    }
    
    private final void updateUiState(org.wordpress.android.viewmodel.wpwebview.WPWebViewViewModel.WebPreviewUiState uiState) {
    }
    
    /**
     * Update the ui state if the Loading or Error screen is being shown.
     * In other words don't update it after a configuration change.
     */
    public final void onUrlLoaded() {
    }
    
    /**
     * Update the ui state if the Loading or Success screen is being shown.
     */
    public final void onReceivedError() {
    }
    
    public final void loadIfNecessary() {
    }
    
    public final boolean isActionableDirectUsage() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.WPWebViewMenuUiState getMenuUiState() {
        return null;
    }
    
    public final void navigateBack() {
    }
    
    public final void navigateForward() {
    }
    
    public final void toggleBackNavigation(boolean isEnabled) {
    }
    
    public final void toggleForwardNavigation(boolean isEnabled) {
    }
    
    public final void share() {
    }
    
    public final void openPageInExternalBrowser() {
    }
    
    public final void togglePreviewModeSelectorVisibility(boolean isVisible) {
    }
    
    public final void selectPreviewMode(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.PreviewMode selectedPreviewMode) {
    }
    
    public final void track(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.analytics.AnalyticsTracker.Stat stat) {
    }
    
    private final int getPreviewHintResId(org.wordpress.android.ui.PreviewMode previewMode) {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public org.wordpress.android.ui.PreviewMode selectedPreviewMode() {
        return null;
    }
    
    @java.lang.Override()
    public void onPreviewModeChanged(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.PreviewMode mode) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0010\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0010\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0011\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0012\u001a\u00020\u0007H\u00c6\u0003J1\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u00c6\u0001J\u0013\u0010\u0014\u001a\u00020\u00032\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0016\u001a\u00020\u0007H\u00d6\u0001J\t\u0010\u0017\u001a\u00020\u0018H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e\u00a8\u0006\u0019"}, d2 = {"Lorg/wordpress/android/viewmodel/wpwebview/WPWebViewViewModel$NavBarUiState;", "", "forwardNavigationEnabled", "", "backNavigationEnabled", "previewModeHintVisible", "reviewHintResId", "", "(ZZZI)V", "getBackNavigationEnabled", "()Z", "getForwardNavigationEnabled", "getPreviewModeHintVisible", "getReviewHintResId", "()I", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class NavBarUiState {
        private final boolean forwardNavigationEnabled = false;
        private final boolean backNavigationEnabled = false;
        private final boolean previewModeHintVisible = false;
        private final int reviewHintResId = 0;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.viewmodel.wpwebview.WPWebViewViewModel.NavBarUiState copy(boolean forwardNavigationEnabled, boolean backNavigationEnabled, boolean previewModeHintVisible, int reviewHintResId) {
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
        
        public NavBarUiState(boolean forwardNavigationEnabled, boolean backNavigationEnabled, boolean previewModeHintVisible, int reviewHintResId) {
            super();
        }
        
        public final boolean component1() {
            return false;
        }
        
        public final boolean getForwardNavigationEnabled() {
            return false;
        }
        
        public final boolean component2() {
            return false;
        }
        
        public final boolean getBackNavigationEnabled() {
            return false;
        }
        
        public final boolean component3() {
            return false;
        }
        
        public final boolean getPreviewModeHintVisible() {
            return false;
        }
        
        public final int component4() {
            return 0;
        }
        
        public final int getReviewHintResId() {
            return 0;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\t\u0010\u000b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\r\u001a\u00020\u0006H\u00c6\u0003J\'\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u00c6\u0001J\u0013\u0010\u000f\u001a\u00020\u00032\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0011\u001a\u00020\u0012H\u00d6\u0001J\t\u0010\u0013\u001a\u00020\u0014H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\bR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0015"}, d2 = {"Lorg/wordpress/android/viewmodel/wpwebview/WPWebViewViewModel$PreviewModeSelectorStatus;", "", "isVisible", "", "isEnabled", "selectedPreviewMode", "Lorg/wordpress/android/ui/PreviewMode;", "(ZZLorg/wordpress/android/ui/PreviewMode;)V", "()Z", "getSelectedPreviewMode", "()Lorg/wordpress/android/ui/PreviewMode;", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class PreviewModeSelectorStatus {
        private final boolean isVisible = false;
        private final boolean isEnabled = false;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.PreviewMode selectedPreviewMode = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.viewmodel.wpwebview.WPWebViewViewModel.PreviewModeSelectorStatus copy(boolean isVisible, boolean isEnabled, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.PreviewMode selectedPreviewMode) {
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
        
        public PreviewModeSelectorStatus(boolean isVisible, boolean isEnabled, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.PreviewMode selectedPreviewMode) {
            super();
        }
        
        public final boolean component1() {
            return false;
        }
        
        public final boolean isVisible() {
            return false;
        }
        
        public final boolean component2() {
            return false;
        }
        
        public final boolean isEnabled() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.PreviewMode component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.PreviewMode getSelectedPreviewMode() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\t\n\u000bB\u001b\b\u0004\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0005R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007\u0082\u0001\u0003\f\r\u000e\u00a8\u0006\u000f"}, d2 = {"Lorg/wordpress/android/viewmodel/wpwebview/WPWebViewViewModel$WebPreviewUiState;", "", "fullscreenProgressLayoutVisibility", "", "actionableEmptyView", "(ZZ)V", "getActionableEmptyView", "()Z", "getFullscreenProgressLayoutVisibility", "WebPreviewContentUiState", "WebPreviewFullscreenProgressUiState", "WebPreviewFullscreenUiState", "Lorg/wordpress/android/viewmodel/wpwebview/WPWebViewViewModel$WebPreviewUiState$WebPreviewContentUiState;", "Lorg/wordpress/android/viewmodel/wpwebview/WPWebViewViewModel$WebPreviewUiState$WebPreviewFullscreenProgressUiState;", "Lorg/wordpress/android/viewmodel/wpwebview/WPWebViewViewModel$WebPreviewUiState$WebPreviewFullscreenUiState;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class WebPreviewUiState {
        private final boolean fullscreenProgressLayoutVisibility = false;
        private final boolean actionableEmptyView = false;
        
        private WebPreviewUiState(boolean fullscreenProgressLayoutVisibility, boolean actionableEmptyView) {
            super();
        }
        
        public final boolean getFullscreenProgressLayoutVisibility() {
            return false;
        }
        
        public final boolean getActionableEmptyView() {
            return false;
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/viewmodel/wpwebview/WPWebViewViewModel$WebPreviewUiState$WebPreviewContentUiState;", "Lorg/wordpress/android/viewmodel/wpwebview/WPWebViewViewModel$WebPreviewUiState;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class WebPreviewContentUiState extends org.wordpress.android.viewmodel.wpwebview.WPWebViewViewModel.WebPreviewUiState {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.viewmodel.wpwebview.WPWebViewViewModel.WebPreviewUiState.WebPreviewContentUiState INSTANCE = null;
            
            private WebPreviewContentUiState() {
                super(false, false);
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/viewmodel/wpwebview/WPWebViewViewModel$WebPreviewUiState$WebPreviewFullscreenProgressUiState;", "Lorg/wordpress/android/viewmodel/wpwebview/WPWebViewViewModel$WebPreviewUiState;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class WebPreviewFullscreenProgressUiState extends org.wordpress.android.viewmodel.wpwebview.WPWebViewViewModel.WebPreviewUiState {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.viewmodel.wpwebview.WPWebViewViewModel.WebPreviewUiState.WebPreviewFullscreenProgressUiState INSTANCE = null;
            
            private WebPreviewFullscreenProgressUiState() {
                super(false, false);
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0011\u0012B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002R\u0012\u0010\u0003\u001a\u00020\u0004X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0012\u0010\u0007\u001a\u00020\bX\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u0004\u0018\u00010\fX\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u0004\u0018\u00010\fX\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0010\u0010\u000e\u0082\u0001\u0002\u0013\u0014\u00a8\u0006\u0015"}, d2 = {"Lorg/wordpress/android/viewmodel/wpwebview/WPWebViewViewModel$WebPreviewUiState$WebPreviewFullscreenUiState;", "Lorg/wordpress/android/viewmodel/wpwebview/WPWebViewViewModel$WebPreviewUiState;", "()V", "buttonVisibility", "", "getButtonVisibility", "()Z", "imageRes", "", "getImageRes", "()I", "subtitleText", "Lorg/wordpress/android/ui/utils/UiString$UiStringRes;", "getSubtitleText", "()Lorg/wordpress/android/ui/utils/UiString$UiStringRes;", "titleText", "getTitleText", "WebPreviewFullscreenErrorUiState", "WebPreviewFullscreenNotAvailableUiState", "Lorg/wordpress/android/viewmodel/wpwebview/WPWebViewViewModel$WebPreviewUiState$WebPreviewFullscreenUiState$WebPreviewFullscreenErrorUiState;", "Lorg/wordpress/android/viewmodel/wpwebview/WPWebViewViewModel$WebPreviewUiState$WebPreviewFullscreenUiState$WebPreviewFullscreenNotAvailableUiState;", "org.wordpress.android_wordpressJalapenoDebug"})
        public static abstract class WebPreviewFullscreenUiState extends org.wordpress.android.viewmodel.wpwebview.WPWebViewViewModel.WebPreviewUiState {
            
            private WebPreviewFullscreenUiState() {
                super(false, false);
            }
            
            public abstract int getImageRes();
            
            @org.jetbrains.annotations.Nullable()
            public abstract org.wordpress.android.ui.utils.UiString.UiStringRes getTitleText();
            
            @org.jetbrains.annotations.Nullable()
            public abstract org.wordpress.android.ui.utils.UiString.UiStringRes getSubtitleText();
            
            public abstract boolean getButtonVisibility();
            
            @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\b\b\u0003\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0012\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0013\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0014\u001a\u00020\bH\u00c6\u0003J1\u0010\u0015\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\bH\u00c6\u0001J\u0013\u0010\u0016\u001a\u00020\b2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u00d6\u0003J\t\u0010\u0019\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u001a\u001a\u00020\u001bH\u00d6\u0001R\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0006\u001a\u00020\u0005X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000f\u00a8\u0006\u001c"}, d2 = {"Lorg/wordpress/android/viewmodel/wpwebview/WPWebViewViewModel$WebPreviewUiState$WebPreviewFullscreenUiState$WebPreviewFullscreenErrorUiState;", "Lorg/wordpress/android/viewmodel/wpwebview/WPWebViewViewModel$WebPreviewUiState$WebPreviewFullscreenUiState;", "imageRes", "", "titleText", "Lorg/wordpress/android/ui/utils/UiString$UiStringRes;", "subtitleText", "buttonVisibility", "", "(ILorg/wordpress/android/ui/utils/UiString$UiStringRes;Lorg/wordpress/android/ui/utils/UiString$UiStringRes;Z)V", "getButtonVisibility", "()Z", "getImageRes", "()I", "getSubtitleText", "()Lorg/wordpress/android/ui/utils/UiString$UiStringRes;", "getTitleText", "component1", "component2", "component3", "component4", "copy", "equals", "other", "", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
            public static final class WebPreviewFullscreenErrorUiState extends org.wordpress.android.viewmodel.wpwebview.WPWebViewViewModel.WebPreviewUiState.WebPreviewFullscreenUiState {
                private final int imageRes = 0;
                @org.jetbrains.annotations.NotNull()
                private final org.wordpress.android.ui.utils.UiString.UiStringRes titleText = null;
                @org.jetbrains.annotations.NotNull()
                private final org.wordpress.android.ui.utils.UiString.UiStringRes subtitleText = null;
                private final boolean buttonVisibility = false;
                
                @org.jetbrains.annotations.NotNull()
                public final org.wordpress.android.viewmodel.wpwebview.WPWebViewViewModel.WebPreviewUiState.WebPreviewFullscreenUiState.WebPreviewFullscreenErrorUiState copy(@androidx.annotation.DrawableRes()
                int imageRes, @org.jetbrains.annotations.NotNull()
                org.wordpress.android.ui.utils.UiString.UiStringRes titleText, @org.jetbrains.annotations.NotNull()
                org.wordpress.android.ui.utils.UiString.UiStringRes subtitleText, boolean buttonVisibility) {
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
                
                public WebPreviewFullscreenErrorUiState() {
                    super();
                }
                
                public WebPreviewFullscreenErrorUiState(@androidx.annotation.DrawableRes()
                int imageRes, @org.jetbrains.annotations.NotNull()
                org.wordpress.android.ui.utils.UiString.UiStringRes titleText, @org.jetbrains.annotations.NotNull()
                org.wordpress.android.ui.utils.UiString.UiStringRes subtitleText, boolean buttonVisibility) {
                    super();
                }
                
                public final int component1() {
                    return 0;
                }
                
                @java.lang.Override()
                public int getImageRes() {
                    return 0;
                }
                
                @org.jetbrains.annotations.NotNull()
                public final org.wordpress.android.ui.utils.UiString.UiStringRes component2() {
                    return null;
                }
                
                @org.jetbrains.annotations.NotNull()
                @java.lang.Override()
                public org.wordpress.android.ui.utils.UiString.UiStringRes getTitleText() {
                    return null;
                }
                
                @org.jetbrains.annotations.NotNull()
                public final org.wordpress.android.ui.utils.UiString.UiStringRes component3() {
                    return null;
                }
                
                @org.jetbrains.annotations.NotNull()
                @java.lang.Override()
                public org.wordpress.android.ui.utils.UiString.UiStringRes getSubtitleText() {
                    return null;
                }
                
                public final boolean component4() {
                    return false;
                }
                
                @java.lang.Override()
                public boolean getButtonVisibility() {
                    return false;
                }
            }
            
            @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0016\u0010\u0007\u001a\u00020\b8\u0016X\u0097D\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0016\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\fX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000e\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/viewmodel/wpwebview/WPWebViewViewModel$WebPreviewUiState$WebPreviewFullscreenUiState$WebPreviewFullscreenNotAvailableUiState;", "Lorg/wordpress/android/viewmodel/wpwebview/WPWebViewViewModel$WebPreviewUiState$WebPreviewFullscreenUiState;", "()V", "buttonVisibility", "", "getButtonVisibility", "()Z", "imageRes", "", "getImageRes", "()I", "subtitleText", "Lorg/wordpress/android/ui/utils/UiString$UiStringRes;", "getSubtitleText", "()Lorg/wordpress/android/ui/utils/UiString$UiStringRes;", "titleText", "getTitleText", "org.wordpress.android_wordpressJalapenoDebug"})
            public static final class WebPreviewFullscreenNotAvailableUiState extends org.wordpress.android.viewmodel.wpwebview.WPWebViewViewModel.WebPreviewUiState.WebPreviewFullscreenUiState {
                @org.jetbrains.annotations.NotNull()
                public static final org.wordpress.android.viewmodel.wpwebview.WPWebViewViewModel.WebPreviewUiState.WebPreviewFullscreenUiState.WebPreviewFullscreenNotAvailableUiState INSTANCE = null;
                @androidx.annotation.DrawableRes()
                private static final int imageRes = org.wordpress.android.R.drawable.img_illustration_empty_results_216dp;
                @org.jetbrains.annotations.NotNull()
                private static final org.wordpress.android.ui.utils.UiString.UiStringRes titleText = null;
                @org.jetbrains.annotations.Nullable()
                private static final org.wordpress.android.ui.utils.UiString.UiStringRes subtitleText = null;
                private static final boolean buttonVisibility = false;
                
                private WebPreviewFullscreenNotAvailableUiState() {
                    super();
                }
                
                @java.lang.Override()
                public int getImageRes() {
                    return 0;
                }
                
                @org.jetbrains.annotations.NotNull()
                @java.lang.Override()
                public org.wordpress.android.ui.utils.UiString.UiStringRes getTitleText() {
                    return null;
                }
                
                @org.jetbrains.annotations.Nullable()
                @java.lang.Override()
                public org.wordpress.android.ui.utils.UiString.UiStringRes getSubtitleText() {
                    return null;
                }
                
                @java.lang.Override()
                public boolean getButtonVisibility() {
                    return false;
                }
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lorg/wordpress/android/viewmodel/wpwebview/WPWebViewViewModel$Companion;", "", "()V", "TRACK_SELECTED_OPTION_NAME", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}