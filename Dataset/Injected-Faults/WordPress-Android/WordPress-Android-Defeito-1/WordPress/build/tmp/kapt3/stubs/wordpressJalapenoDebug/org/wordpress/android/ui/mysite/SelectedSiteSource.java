package org.wordpress.android.ui.mysite;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\u001e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0006\u0010\u0014\u001a\u00020\u0015J\u0012\u0010\u0016\u001a\u00020\u00152\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0007J\b\u0010\b\u001a\u00020\u0015H\u0016J\u0006\u0010\u0019\u001a\u00020\u0015R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\"\u0010\b\u001a\u0010\u0012\f\u0012\n \u000b*\u0004\u0018\u00010\n0\n0\tX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"}, d2 = {"Lorg/wordpress/android/ui/mysite/SelectedSiteSource;", "Lorg/wordpress/android/ui/mysite/MySiteSource$MySiteRefreshSource;", "Lorg/wordpress/android/ui/mysite/MySiteUiState$PartialState$SelectedSite;", "selectedSiteRepository", "Lorg/wordpress/android/ui/mysite/SelectedSiteRepository;", "dispatcher", "Lorg/wordpress/android/fluxc/Dispatcher;", "(Lorg/wordpress/android/ui/mysite/SelectedSiteRepository;Lorg/wordpress/android/fluxc/Dispatcher;)V", "refresh", "Landroidx/lifecycle/MutableLiveData;", "", "kotlin.jvm.PlatformType", "getRefresh", "()Landroidx/lifecycle/MutableLiveData;", "build", "Landroidx/lifecycle/MediatorLiveData;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "siteLocalId", "", "clear", "", "onSiteChanged", "event", "Lorg/wordpress/android/fluxc/store/SiteStore$OnSiteChanged;", "updateSiteSettingsIfNecessary", "org.wordpress.android_wordpressJalapenoDebug"})
public final class SelectedSiteSource implements org.wordpress.android.ui.mysite.MySiteSource.MySiteRefreshSource<org.wordpress.android.ui.mysite.MySiteUiState.PartialState.SelectedSite> {
    private final org.wordpress.android.ui.mysite.SelectedSiteRepository selectedSiteRepository = null;
    private final org.wordpress.android.fluxc.Dispatcher dispatcher = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> refresh = null;
    
    @javax.inject.Inject()
    public SelectedSiteSource(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.SelectedSiteRepository selectedSiteRepository, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.Dispatcher dispatcher) {
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
    public androidx.lifecycle.MediatorLiveData<org.wordpress.android.ui.mysite.MySiteUiState.PartialState.SelectedSite> build(@org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.CoroutineScope coroutineScope, int siteLocalId) {
        return null;
    }
    
    @java.lang.Override()
    public void refresh() {
    }
    
    public final void updateSiteSettingsIfNecessary() {
    }
    
    @kotlin.Suppress(names = {"unused", "UNUSED_PARAMETER"})
    @org.greenrobot.eventbus.Subscribe(threadMode = org.greenrobot.eventbus.ThreadMode.MAIN)
    public final void onSiteChanged(@org.jetbrains.annotations.Nullable()
    org.wordpress.android.fluxc.store.SiteStore.OnSiteChanged event) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public org.wordpress.android.ui.mysite.MySiteUiState.PartialState.SelectedSite getState(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.MySiteUiState.PartialState.SelectedSite value) {
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
    androidx.lifecycle.MediatorLiveData<org.wordpress.android.ui.mysite.MySiteUiState.PartialState.SelectedSite> $this$postState, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.MySiteUiState.PartialState.SelectedSite value) {
    }
    
    public void setState(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.MediatorLiveData<org.wordpress.android.ui.mysite.MySiteUiState.PartialState.SelectedSite> $this$setState, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.MySiteUiState.PartialState.SelectedSite value) {
    }
}