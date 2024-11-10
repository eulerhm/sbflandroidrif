package org.wordpress.android.ui.prefs.categories.list;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B)\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u0006\u0010 \u001a\u00020!J\u001b\u0010\"\u001a\u00020!2\b\b\u0002\u0010#\u001a\u00020\u0012H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010$J\b\u0010%\u001a\u00020!H\u0002J\u000e\u0010&\u001a\u00020!2\u0006\u0010\'\u001a\u00020(J\b\u0010)\u001a\u00020!H\u0014J\b\u0010*\u001a\u00020!H\u0002J\u0010\u0010+\u001a\u00020!2\u0006\u0010,\u001a\u00020-H\u0007J\u0010\u0010.\u001a\u00020!2\u0006\u0010,\u001a\u00020/H\u0007J\u0010\u00100\u001a\u00020!2\u0006\u0010,\u001a\u00020-H\u0002J\u000e\u00101\u001a\u00020!2\u0006\u0010\u0016\u001a\u00020\u0017R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\r0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0016\u001a\u00020\u0017X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u0017\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00100\u001d\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001f\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u00062"}, d2 = {"Lorg/wordpress/android/ui/prefs/categories/list/CategoriesListViewModel;", "Lorg/wordpress/android/viewmodel/ScopedViewModel;", "getCategoriesUseCase", "Lorg/wordpress/android/ui/posts/GetCategoriesUseCase;", "networkUtilsWrapper", "Lorg/wordpress/android/util/NetworkUtilsWrapper;", "bgDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "dispatcher", "Lorg/wordpress/android/fluxc/Dispatcher;", "(Lorg/wordpress/android/ui/posts/GetCategoriesUseCase;Lorg/wordpress/android/util/NetworkUtilsWrapper;Lkotlinx/coroutines/CoroutineDispatcher;Lorg/wordpress/android/fluxc/Dispatcher;)V", "_navigation", "Lorg/wordpress/android/viewmodel/SingleLiveEvent;", "Lorg/wordpress/android/ui/prefs/categories/list/CategoryDetailNavigation;", "_uiState", "Landroidx/lifecycle/MutableLiveData;", "Lorg/wordpress/android/ui/prefs/categories/list/UiState;", "isStarted", "", "navigation", "getNavigation", "()Lorg/wordpress/android/viewmodel/SingleLiveEvent;", "siteModel", "Lorg/wordpress/android/fluxc/model/SiteModel;", "getSiteModel", "()Lorg/wordpress/android/fluxc/model/SiteModel;", "setSiteModel", "(Lorg/wordpress/android/fluxc/model/SiteModel;)V", "uiState", "Landroidx/lifecycle/LiveData;", "getUiState", "()Landroidx/lifecycle/LiveData;", "createCategory", "", "fetchCategoriesFromNetwork", "isInvokedFromInit", "(ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCategoriesFromDb", "onCategoryClicked", "categoryNode", "Lorg/wordpress/android/models/CategoryNode;", "onCleared", "onRetryClicked", "onTaxonomyChanged", "event", "Lorg/wordpress/android/fluxc/store/TaxonomyStore$OnTaxonomyChanged;", "onTermUploaded", "Lorg/wordpress/android/fluxc/store/TaxonomyStore$OnTermUploaded;", "processFetchCategoriesCallback", "start", "org.wordpress.android_wordpressJalapenoDebug"})
public final class CategoriesListViewModel extends org.wordpress.android.viewmodel.ScopedViewModel {
    private final org.wordpress.android.ui.posts.GetCategoriesUseCase getCategoriesUseCase = null;
    private final org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper = null;
    private final kotlinx.coroutines.CoroutineDispatcher bgDispatcher = null;
    private final org.wordpress.android.fluxc.Dispatcher dispatcher = null;
    private boolean isStarted = false;
    public org.wordpress.android.fluxc.model.SiteModel siteModel;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.ui.prefs.categories.list.UiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.ui.prefs.categories.list.UiState> uiState = null;
    private final org.wordpress.android.viewmodel.SingleLiveEvent<org.wordpress.android.ui.prefs.categories.list.CategoryDetailNavigation> _navigation = null;
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.viewmodel.SingleLiveEvent<org.wordpress.android.ui.prefs.categories.list.CategoryDetailNavigation> navigation = null;
    
    @javax.inject.Inject()
    public CategoriesListViewModel(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.GetCategoriesUseCase getCategoriesUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher bgDispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.Dispatcher dispatcher) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.fluxc.model.SiteModel getSiteModel() {
        return null;
    }
    
    public final void setSiteModel(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.ui.prefs.categories.list.UiState> getUiState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.viewmodel.SingleLiveEvent<org.wordpress.android.ui.prefs.categories.list.CategoryDetailNavigation> getNavigation() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCleared() {
    }
    
    public final void start(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel siteModel) {
    }
    
    private final void getCategoriesFromDb() {
    }
    
    private final void onRetryClicked() {
    }
    
    private final java.lang.Object fetchCategoriesFromNetwork(boolean isInvokedFromInit, kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @java.lang.SuppressWarnings(value = {"unused"})
    @org.greenrobot.eventbus.Subscribe(threadMode = org.greenrobot.eventbus.ThreadMode.MAIN)
    public final void onTaxonomyChanged(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.TaxonomyStore.OnTaxonomyChanged event) {
    }
    
    @java.lang.SuppressWarnings(value = {"unused"})
    @org.greenrobot.eventbus.Subscribe(threadMode = org.greenrobot.eventbus.ThreadMode.MAIN)
    public final void onTermUploaded(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.TaxonomyStore.OnTermUploaded event) {
    }
    
    public final void createCategory() {
    }
    
    @java.lang.SuppressWarnings(value = {"unused"})
    public final void onCategoryClicked(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.models.CategoryNode categoryNode) {
    }
    
    private final void processFetchCategoriesCallback(org.wordpress.android.fluxc.store.TaxonomyStore.OnTaxonomyChanged event) {
    }
}