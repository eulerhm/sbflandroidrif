package org.wordpress.android.ui.mysite;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0005J\u0016\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J#\u0010\u0010\u001a\u00020\u0011*\b\u0012\u0004\u0012\u00020\u00020\u00122\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\bH\u0002\u00a2\u0006\u0002\u0010\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\"\u0010\u0006\u001a\u0010\u0012\f\u0012\n \t*\u0004\u0018\u00010\b0\b0\u0007X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0015"}, d2 = {"Lorg/wordpress/android/ui/mysite/CurrentAvatarSource;", "Lorg/wordpress/android/ui/mysite/MySiteSource$SiteIndependentSource;", "Lorg/wordpress/android/ui/mysite/MySiteUiState$PartialState$CurrentAvatarUrl;", "accountStore", "Lorg/wordpress/android/fluxc/store/AccountStore;", "(Lorg/wordpress/android/fluxc/store/AccountStore;)V", "refresh", "Landroidx/lifecycle/MutableLiveData;", "", "kotlin.jvm.PlatformType", "getRefresh", "()Landroidx/lifecycle/MutableLiveData;", "build", "Landroidx/lifecycle/LiveData;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "refreshData", "", "Landroidx/lifecycle/MediatorLiveData;", "isRefresh", "(Landroidx/lifecycle/MediatorLiveData;Ljava/lang/Boolean;)V", "org.wordpress.android_wordpressJalapenoDebug"})
public final class CurrentAvatarSource implements org.wordpress.android.ui.mysite.MySiteSource.SiteIndependentSource<org.wordpress.android.ui.mysite.MySiteUiState.PartialState.CurrentAvatarUrl> {
    private final org.wordpress.android.fluxc.store.AccountStore accountStore = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> refresh = null;
    
    @javax.inject.Inject()
    public CurrentAvatarSource(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.AccountStore accountStore) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public androidx.lifecycle.MutableLiveData<java.lang.Boolean> getRefresh() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public androidx.lifecycle.LiveData<org.wordpress.android.ui.mysite.MySiteUiState.PartialState.CurrentAvatarUrl> build(@org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.CoroutineScope coroutineScope) {
        return null;
    }
    
    private final void refreshData(androidx.lifecycle.MediatorLiveData<org.wordpress.android.ui.mysite.MySiteUiState.PartialState.CurrentAvatarUrl> $this$refreshData, java.lang.Boolean isRefresh) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public androidx.lifecycle.LiveData<org.wordpress.android.ui.mysite.MySiteUiState.PartialState.CurrentAvatarUrl> build(@org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.CoroutineScope coroutineScope, int siteLocalId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public org.wordpress.android.ui.mysite.MySiteUiState.PartialState.CurrentAvatarUrl getState(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.MySiteUiState.PartialState.CurrentAvatarUrl value) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Boolean isRefreshing() {
        return null;
    }
    
    @java.lang.Override()
    public void onRefreshedBackgroundThread() {
    }
    
    @java.lang.Override()
    public void onRefreshedMainThread() {
    }
    
    @java.lang.Override()
    public void postState(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.MediatorLiveData<org.wordpress.android.ui.mysite.MySiteUiState.PartialState.CurrentAvatarUrl> $this$postState, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.MySiteUiState.PartialState.CurrentAvatarUrl value) {
    }
    
    @java.lang.Override()
    public void refresh() {
    }
    
    @java.lang.Override()
    public void setState(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.MediatorLiveData<org.wordpress.android.ui.mysite.MySiteUiState.PartialState.CurrentAvatarUrl> $this$setState, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.MySiteUiState.PartialState.CurrentAvatarUrl value) {
    }
}