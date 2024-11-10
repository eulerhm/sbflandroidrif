package org.wordpress.android.ui.mysite.dynamiccards;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\u001e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J5\u0010\u0014\u001a\u00020\u00152\"\u0010\u0016\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\u0013\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u0018\u0012\u0006\u0012\u0004\u0018\u00010\u00190\u0017H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001aJ\u0019\u0010\u001b\u001a\u00020\u00152\u0006\u0010\u001c\u001a\u00020\u001dH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001eJ\u0019\u0010\u001f\u001a\u00020\u00152\u0006\u0010\u001c\u001a\u00020\u001dH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001eJ\u0019\u0010 \u001a\u00020\u00152\u0006\u0010\u001c\u001a\u00020\u001dH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001eJ\u0011\u0010!\u001a\u00020\u0015H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\"J\"\u0010#\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u00020$2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010%\u001a\u00020\u0013H\u0002J3\u0010#\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u00020$2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010%\u001a\u00020\u00132\n\b\u0002\u0010&\u001a\u0004\u0018\u00010\nH\u0002\u00a2\u0006\u0002\u0010\'R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\"\u0010\b\u001a\u0010\u0012\f\u0012\n \u000b*\u0004\u0018\u00010\n0\n0\tX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006("}, d2 = {"Lorg/wordpress/android/ui/mysite/dynamiccards/DynamicCardsSource;", "Lorg/wordpress/android/ui/mysite/MySiteSource$MySiteRefreshSource;", "Lorg/wordpress/android/ui/mysite/MySiteUiState$PartialState$DynamicCardsUpdate;", "dynamicCardStore", "Lorg/wordpress/android/fluxc/store/DynamicCardStore;", "selectedSiteRepository", "Lorg/wordpress/android/ui/mysite/SelectedSiteRepository;", "(Lorg/wordpress/android/fluxc/store/DynamicCardStore;Lorg/wordpress/android/ui/mysite/SelectedSiteRepository;)V", "refresh", "Landroidx/lifecycle/MutableLiveData;", "", "kotlin.jvm.PlatformType", "getRefresh", "()Landroidx/lifecycle/MutableLiveData;", "build", "Landroidx/lifecycle/LiveData;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "siteLocalId", "", "callWithSite", "", "function", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "(Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "hideItem", "dynamicCardType", "Lorg/wordpress/android/fluxc/model/DynamicCardType;", "(Lorg/wordpress/android/fluxc/model/DynamicCardType;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "pinItem", "removeItem", "unpinItem", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "refreshData", "Landroidx/lifecycle/MediatorLiveData;", "siteId", "isRefresh", "(Landroidx/lifecycle/MediatorLiveData;Lkotlinx/coroutines/CoroutineScope;ILjava/lang/Boolean;)V", "org.wordpress.android_wordpressJalapenoDebug"})
public final class DynamicCardsSource implements org.wordpress.android.ui.mysite.MySiteSource.MySiteRefreshSource<org.wordpress.android.ui.mysite.MySiteUiState.PartialState.DynamicCardsUpdate> {
    private final org.wordpress.android.fluxc.store.DynamicCardStore dynamicCardStore = null;
    private final org.wordpress.android.ui.mysite.SelectedSiteRepository selectedSiteRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> refresh = null;
    
    @javax.inject.Inject()
    public DynamicCardsSource(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.DynamicCardStore dynamicCardStore, @org.jetbrains.annotations.NotNull()
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
    public androidx.lifecycle.LiveData<org.wordpress.android.ui.mysite.MySiteUiState.PartialState.DynamicCardsUpdate> build(@org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.CoroutineScope coroutineScope, int siteLocalId) {
        return null;
    }
    
    private final void refreshData(androidx.lifecycle.MediatorLiveData<org.wordpress.android.ui.mysite.MySiteUiState.PartialState.DynamicCardsUpdate> $this$refreshData, kotlinx.coroutines.CoroutineScope coroutineScope, int siteId, java.lang.Boolean isRefresh) {
    }
    
    private final void refreshData(androidx.lifecycle.MediatorLiveData<org.wordpress.android.ui.mysite.MySiteUiState.PartialState.DynamicCardsUpdate> $this$refreshData, kotlinx.coroutines.CoroutineScope coroutineScope, int siteId) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object pinItem(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.DynamicCardType dynamicCardType, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object hideItem(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.DynamicCardType dynamicCardType, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object unpinItem(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object removeItem(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.DynamicCardType dynamicCardType, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    private final java.lang.Object callWithSite(kotlin.jvm.functions.Function2<? super java.lang.Integer, ? super kotlin.coroutines.Continuation<? super kotlin.Unit>, ? extends java.lang.Object> function, kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public org.wordpress.android.ui.mysite.MySiteUiState.PartialState.DynamicCardsUpdate getState(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.MySiteUiState.PartialState.DynamicCardsUpdate value) {
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
    androidx.lifecycle.MediatorLiveData<org.wordpress.android.ui.mysite.MySiteUiState.PartialState.DynamicCardsUpdate> $this$postState, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.MySiteUiState.PartialState.DynamicCardsUpdate value) {
    }
    
    public void refresh() {
    }
    
    public void setState(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.MediatorLiveData<org.wordpress.android.ui.mysite.MySiteUiState.PartialState.DynamicCardsUpdate> $this$setState, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.MySiteUiState.PartialState.DynamicCardsUpdate value) {
    }
}