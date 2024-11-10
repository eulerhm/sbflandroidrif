package org.wordpress.android.ui.mysite.cards.quickstart;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\'\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u00a2\u0006\u0002\u0010\u000bJ\u001e\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u0018\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u001aH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\"\u0010\f\u001a\u0010\u0012\f\u0012\n \u000f*\u0004\u0018\u00010\u000e0\u000e0\rX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001b"}, d2 = {"Lorg/wordpress/android/ui/mysite/cards/quickstart/QuickStartCardSource;", "Lorg/wordpress/android/ui/mysite/MySiteSource$MySiteRefreshSource;", "Lorg/wordpress/android/ui/mysite/MySiteUiState$PartialState$QuickStartUpdate;", "quickStartRepository", "Lorg/wordpress/android/ui/mysite/cards/quickstart/QuickStartRepository;", "quickStartStore", "Lorg/wordpress/android/fluxc/store/QuickStartStore;", "quickStartUtilsWrapper", "Lorg/wordpress/android/util/QuickStartUtilsWrapper;", "selectedSiteRepository", "Lorg/wordpress/android/ui/mysite/SelectedSiteRepository;", "(Lorg/wordpress/android/ui/mysite/cards/quickstart/QuickStartRepository;Lorg/wordpress/android/fluxc/store/QuickStartStore;Lorg/wordpress/android/util/QuickStartUtilsWrapper;Lorg/wordpress/android/ui/mysite/SelectedSiteRepository;)V", "refresh", "Landroidx/lifecycle/MutableLiveData;", "", "kotlin.jvm.PlatformType", "getRefresh", "()Landroidx/lifecycle/MutableLiveData;", "build", "Landroidx/lifecycle/LiveData;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "siteLocalId", "", "isEmptyCategory", "taskType", "Lorg/wordpress/android/fluxc/store/QuickStartStore$QuickStartTaskType;", "org.wordpress.android_wordpressJalapenoDebug"})
public final class QuickStartCardSource implements org.wordpress.android.ui.mysite.MySiteSource.MySiteRefreshSource<org.wordpress.android.ui.mysite.MySiteUiState.PartialState.QuickStartUpdate> {
    private final org.wordpress.android.ui.mysite.cards.quickstart.QuickStartRepository quickStartRepository = null;
    private final org.wordpress.android.fluxc.store.QuickStartStore quickStartStore = null;
    private final org.wordpress.android.util.QuickStartUtilsWrapper quickStartUtilsWrapper = null;
    private final org.wordpress.android.ui.mysite.SelectedSiteRepository selectedSiteRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> refresh = null;
    
    @javax.inject.Inject()
    public QuickStartCardSource(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.cards.quickstart.QuickStartRepository quickStartRepository, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.QuickStartStore quickStartStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.QuickStartUtilsWrapper quickStartUtilsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.SelectedSiteRepository selectedSiteRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public androidx.lifecycle.MutableLiveData<java.lang.Boolean> getRefresh() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public androidx.lifecycle.LiveData<org.wordpress.android.ui.mysite.MySiteUiState.PartialState.QuickStartUpdate> build(@org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.CoroutineScope coroutineScope, int siteLocalId) {
        return null;
    }
    
    private final boolean isEmptyCategory(int siteLocalId, org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTaskType taskType) {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public org.wordpress.android.ui.mysite.MySiteUiState.PartialState.QuickStartUpdate getState(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.MySiteUiState.PartialState.QuickStartUpdate value) {
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
    androidx.lifecycle.MediatorLiveData<org.wordpress.android.ui.mysite.MySiteUiState.PartialState.QuickStartUpdate> $this$postState, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.MySiteUiState.PartialState.QuickStartUpdate value) {
    }
    
    public void refresh() {
    }
    
    public void setState(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.MediatorLiveData<org.wordpress.android.ui.mysite.MySiteUiState.PartialState.QuickStartUpdate> $this$setState, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.MySiteUiState.PartialState.QuickStartUpdate value) {
    }
}