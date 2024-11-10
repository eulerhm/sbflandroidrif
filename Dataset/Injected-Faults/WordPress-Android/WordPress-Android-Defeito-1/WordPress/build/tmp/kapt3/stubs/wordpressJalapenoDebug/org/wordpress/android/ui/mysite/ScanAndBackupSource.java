package org.wordpress.android.ui.mysite;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B!\b\u0007\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\tJ\u001e\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0006\u0010\u0016\u001a\u00020\u0017J\"\u0010\u0018\u001a\u00020\u0017*\b\u0012\u0004\u0012\u00020\u00020\u00192\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J3\u0010\u0018\u001a\u00020\u0017*\b\u0012\u0004\u0012\u00020\u00020\u00192\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\fH\u0002\u00a2\u0006\u0002\u0010\u001bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\"\u0010\n\u001a\u0010\u0012\f\u0012\n \r*\u0004\u0018\u00010\f0\f0\u000bX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001c"}, d2 = {"Lorg/wordpress/android/ui/mysite/ScanAndBackupSource;", "Lorg/wordpress/android/ui/mysite/MySiteSource$MySiteRefreshSource;", "Lorg/wordpress/android/ui/mysite/MySiteUiState$PartialState$JetpackCapabilities;", "bgDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "selectedSiteRepository", "Lorg/wordpress/android/ui/mysite/SelectedSiteRepository;", "jetpackCapabilitiesUseCase", "Lorg/wordpress/android/ui/jetpack/JetpackCapabilitiesUseCase;", "(Lkotlinx/coroutines/CoroutineDispatcher;Lorg/wordpress/android/ui/mysite/SelectedSiteRepository;Lorg/wordpress/android/ui/jetpack/JetpackCapabilitiesUseCase;)V", "refresh", "Landroidx/lifecycle/MutableLiveData;", "", "kotlin.jvm.PlatformType", "getRefresh", "()Landroidx/lifecycle/MutableLiveData;", "build", "Landroidx/lifecycle/LiveData;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "siteLocalId", "", "clear", "", "refreshData", "Landroidx/lifecycle/MediatorLiveData;", "isRefresh", "(Landroidx/lifecycle/MediatorLiveData;Lkotlinx/coroutines/CoroutineScope;ILjava/lang/Boolean;)V", "org.wordpress.android_wordpressJalapenoDebug"})
public final class ScanAndBackupSource implements org.wordpress.android.ui.mysite.MySiteSource.MySiteRefreshSource<org.wordpress.android.ui.mysite.MySiteUiState.PartialState.JetpackCapabilities> {
    private final kotlinx.coroutines.CoroutineDispatcher bgDispatcher = null;
    private final org.wordpress.android.ui.mysite.SelectedSiteRepository selectedSiteRepository = null;
    private final org.wordpress.android.ui.jetpack.JetpackCapabilitiesUseCase jetpackCapabilitiesUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> refresh = null;
    
    @javax.inject.Inject()
    public ScanAndBackupSource(@org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher bgDispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.SelectedSiteRepository selectedSiteRepository, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.jetpack.JetpackCapabilitiesUseCase jetpackCapabilitiesUseCase) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public androidx.lifecycle.MutableLiveData<java.lang.Boolean> getRefresh() {
        return null;
    }
    
    public final void clear() {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public androidx.lifecycle.LiveData<org.wordpress.android.ui.mysite.MySiteUiState.PartialState.JetpackCapabilities> build(@org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.CoroutineScope coroutineScope, int siteLocalId) {
        return null;
    }
    
    private final void refreshData(androidx.lifecycle.MediatorLiveData<org.wordpress.android.ui.mysite.MySiteUiState.PartialState.JetpackCapabilities> $this$refreshData, kotlinx.coroutines.CoroutineScope coroutineScope, int siteLocalId, java.lang.Boolean isRefresh) {
    }
    
    private final void refreshData(androidx.lifecycle.MediatorLiveData<org.wordpress.android.ui.mysite.MySiteUiState.PartialState.JetpackCapabilities> $this$refreshData, kotlinx.coroutines.CoroutineScope coroutineScope, int siteLocalId) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public org.wordpress.android.ui.mysite.MySiteUiState.PartialState.JetpackCapabilities getState(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.MySiteUiState.PartialState.JetpackCapabilities value) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public java.lang.Boolean isRefreshing() {
        return null;
    }
    
    public void onRefreshedBackgroundThread() {
    }
    
    public void onRefreshedMainThread() {
    }
    
    public void postState(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.MediatorLiveData<org.wordpress.android.ui.mysite.MySiteUiState.PartialState.JetpackCapabilities> $this$postState, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.MySiteUiState.PartialState.JetpackCapabilities value) {
    }
    
    public void refresh() {
    }
    
    public void setState(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.MediatorLiveData<org.wordpress.android.ui.mysite.MySiteUiState.PartialState.JetpackCapabilities> $this$setState, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.MySiteUiState.PartialState.JetpackCapabilities value) {
    }
}