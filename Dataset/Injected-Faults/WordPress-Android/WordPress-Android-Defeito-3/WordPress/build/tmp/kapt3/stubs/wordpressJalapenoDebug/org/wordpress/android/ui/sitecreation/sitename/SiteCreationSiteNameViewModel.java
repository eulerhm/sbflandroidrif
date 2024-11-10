package org.wordpress.android.ui.sitecreation.sitename;

import java.lang.System;

@dagger.hilt.android.lifecycle.HiltViewModel()
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002:\u0001(B\u0019\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\u0006\u0010#\u001a\u00020\nJ\u000e\u0010$\u001a\u00020\n2\u0006\u0010%\u001a\u00020\fJ\u0006\u0010\u001d\u001a\u00020\nJ\u0006\u0010&\u001a\u00020\nJ\u0006\u0010\'\u001a\u00020\nR\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\u00020\u00128VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\n0\u001a\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0017\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\f0\u001a\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001cR\u0017\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\n0\u001a\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u001cR\u0017\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00100\u001a\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001c\u00a8\u0006)"}, d2 = {"Lorg/wordpress/android/ui/sitecreation/sitename/SiteCreationSiteNameViewModel;", "Landroidx/lifecycle/ViewModel;", "Lkotlinx/coroutines/CoroutineScope;", "analyticsTracker", "Lorg/wordpress/android/ui/sitecreation/misc/SiteCreationTracker;", "bgDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Lorg/wordpress/android/ui/sitecreation/misc/SiteCreationTracker;Lkotlinx/coroutines/CoroutineDispatcher;)V", "_onBackButtonPressed", "Lorg/wordpress/android/viewmodel/SingleLiveEvent;", "", "_onSiteNameEntered", "", "_onSkipButtonPressed", "_uiState", "Landroidx/lifecycle/MutableLiveData;", "Lorg/wordpress/android/ui/sitecreation/sitename/SiteCreationSiteNameViewModel$SiteNameUiState;", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "isInitialized", "", "job", "Lkotlinx/coroutines/CompletableJob;", "onBackButtonPressed", "Landroidx/lifecycle/LiveData;", "getOnBackButtonPressed", "()Landroidx/lifecycle/LiveData;", "onSiteNameEntered", "getOnSiteNameEntered", "onSkipButtonPressed", "getOnSkipButtonPressed", "uiState", "getUiState", "onBackPressed", "onSiteNameChanged", "siteName", "onSkipPressed", "start", "SiteNameUiState", "org.wordpress.android_wordpressJalapenoDebug"})
public final class SiteCreationSiteNameViewModel extends androidx.lifecycle.ViewModel implements kotlinx.coroutines.CoroutineScope {
    private final org.wordpress.android.ui.sitecreation.misc.SiteCreationTracker analyticsTracker = null;
    private final kotlinx.coroutines.CoroutineDispatcher bgDispatcher = null;
    private final kotlinx.coroutines.CompletableJob job = null;
    private boolean isInitialized = false;
    private final org.wordpress.android.viewmodel.SingleLiveEvent<kotlin.Unit> _onSkipButtonPressed = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<kotlin.Unit> onSkipButtonPressed = null;
    private final org.wordpress.android.viewmodel.SingleLiveEvent<kotlin.Unit> _onBackButtonPressed = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<kotlin.Unit> onBackButtonPressed = null;
    private final org.wordpress.android.viewmodel.SingleLiveEvent<java.lang.String> _onSiteNameEntered = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.String> onSiteNameEntered = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.ui.sitecreation.sitename.SiteCreationSiteNameViewModel.SiteNameUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.ui.sitecreation.sitename.SiteCreationSiteNameViewModel.SiteNameUiState> uiState = null;
    
    @javax.inject.Inject()
    public SiteCreationSiteNameViewModel(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.sitecreation.misc.SiteCreationTracker analyticsTracker, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher bgDispatcher) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public kotlin.coroutines.CoroutineContext getCoroutineContext() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<kotlin.Unit> getOnSkipButtonPressed() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<kotlin.Unit> getOnBackButtonPressed() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.String> getOnSiteNameEntered() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.ui.sitecreation.sitename.SiteCreationSiteNameViewModel.SiteNameUiState> getUiState() {
        return null;
    }
    
    public final void start() {
    }
    
    public final void onSkipPressed() {
    }
    
    public final void onBackPressed() {
    }
    
    public final void onSiteNameEntered() {
    }
    
    public final void onSiteNameChanged(@org.jetbrains.annotations.NotNull()
    java.lang.String siteName) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\n\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\f\u001a\u00020\u00062\b\u0010\r\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u000e\u001a\u00020\u000fH\u00d6\u0001J\t\u0010\u0010\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0005\u001a\u00020\u00068F\u00a2\u0006\u0006\u001a\u0004\b\u0005\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/sitecreation/sitename/SiteCreationSiteNameViewModel$SiteNameUiState;", "", "siteName", "", "(Ljava/lang/String;)V", "isContinueButtonEnabled", "", "()Z", "getSiteName", "()Ljava/lang/String;", "component1", "copy", "equals", "other", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class SiteNameUiState {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String siteName = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.sitecreation.sitename.SiteCreationSiteNameViewModel.SiteNameUiState copy(@org.jetbrains.annotations.NotNull()
        java.lang.String siteName) {
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
        
        public SiteNameUiState(@org.jetbrains.annotations.NotNull()
        java.lang.String siteName) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getSiteName() {
            return null;
        }
        
        public final boolean isContinueButtonEnabled() {
            return false;
        }
    }
}