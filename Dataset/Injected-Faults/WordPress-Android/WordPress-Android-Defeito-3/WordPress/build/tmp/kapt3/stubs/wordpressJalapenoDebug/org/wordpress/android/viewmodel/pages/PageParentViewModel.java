package org.wordpress.android.viewmodel.pages;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u000e\u0018\u00002\u00020\u0001B+\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0001\u0010\b\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\tJ\b\u00103\u001a\u00020\u0018H\u0002J$\u00104\u001a\b\u0012\u0004\u0012\u00020(0\u00142\u0006\u0010\'\u001a\u00020(2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020(0\u0014H\u0002J\u001e\u00105\u001a\u00020\u000f2\u0006\u00106\u001a\u00020(2\f\u00107\u001a\b\u0012\u0004\u0012\u00020(0\u0014H\u0002J\u0010\u00108\u001a\u00020.2\u0006\u00109\u001a\u00020:H\u0002J\u000e\u0010;\u001a\u00020\u00182\u0006\u0010\'\u001a\u00020\u000bJ\u0006\u0010<\u001a\u00020\u0018J\u0018\u0010=\u001a\u00020\u00182\u0006\u0010>\u001a\u00020\u00122\b\b\u0002\u0010?\u001a\u00020:J\u0006\u0010@\u001a\u00020\u0018J\u000e\u0010A\u001a\u00020\u00182\u0006\u0010B\u001a\u00020\u000fJ\u001f\u0010C\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\u0006\u0010>\u001a\u00020\u0012H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010DJ\b\u0010E\u001a\u00020\u0018H\u0002J\u0016\u0010F\u001a\u00020\u00182\u0006\u00101\u001a\u0002022\u0006\u00109\u001a\u00020:J\b\u0010G\u001a\u00020\u0018H\u0002R\u000e\u0010\n\u001a\u00020\u000bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00140\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0019\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u00140\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u001a\u001a\u00020\u000b8F\u00a2\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u000e\u0010\b\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u001d\u001a\u00020\u000b8F\u00a2\u0006\u0006\u001a\u0004\b\u001e\u0010\u001cR\u0017\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u000f0 \u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010!R\u0017\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u000f0 \u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010!R\u000e\u0010#\u001a\u00020\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0011\u0010$\u001a\u00020\u00128F\u00a2\u0006\u0006\u001a\u0004\b%\u0010&R\u0010\u0010\'\u001a\u0004\u0018\u00010(X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010)\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00140 \u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010!R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00180 \u00a2\u0006\b\n\u0000\u001a\u0004\b,\u0010!R\u0010\u0010-\u001a\u0004\u0018\u00010.X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001f\u0010/\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u00140 \u00a2\u0006\b\n\u0000\u001a\u0004\b0\u0010!R\u000e\u00101\u001a\u000202X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006H"}, d2 = {"Lorg/wordpress/android/viewmodel/pages/PageParentViewModel;", "Lorg/wordpress/android/viewmodel/ScopedViewModel;", "pageStore", "Lorg/wordpress/android/fluxc/store/PageStore;", "resourceProvider", "Lorg/wordpress/android/viewmodel/ResourceProvider;", "uiDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "defaultDispatcher", "(Lorg/wordpress/android/fluxc/store/PageStore;Lorg/wordpress/android/viewmodel/ResourceProvider;Lkotlinx/coroutines/CoroutineDispatcher;Lkotlinx/coroutines/CoroutineDispatcher;)V", "_currentParent", "Lorg/wordpress/android/ui/pages/PageItem$ParentPage;", "_initialParent", "_isSaveButtonVisible", "Landroidx/lifecycle/MutableLiveData;", "", "_isSearchExpanded", "_lastSearchQuery", "", "_pages", "", "Lorg/wordpress/android/ui/pages/PageItem;", "_saveParent", "Lorg/wordpress/android/viewmodel/SingleLiveEvent;", "", "_searchPages", "currentParent", "getCurrentParent", "()Lorg/wordpress/android/ui/pages/PageItem$ParentPage;", "initialParent", "getInitialParent", "isSaveButtonVisible", "Landroidx/lifecycle/LiveData;", "()Landroidx/lifecycle/LiveData;", "isSearchExpanded", "isStarted", "lastSearchQuery", "getLastSearchQuery", "()Ljava/lang/String;", "page", "Lorg/wordpress/android/fluxc/model/page/PageModel;", "pages", "getPages", "saveParent", "getSaveParent", "searchJob", "Lkotlinx/coroutines/Job;", "searchPages", "getSearchPages", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "clearSearch", "getChildren", "isNotChild", "choice", "choices", "loadPages", "pageId", "", "onParentSelected", "onSaveButtonTapped", "onSearch", "searchQuery", "delay", "onSearchCollapsed", "onSearchExpanded", "restorePreviousSearch", "search", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setSaveButton", "start", "trackSaveEvent", "org.wordpress.android_wordpressJalapenoDebug"})
public final class PageParentViewModel extends org.wordpress.android.viewmodel.ScopedViewModel {
    private final org.wordpress.android.fluxc.store.PageStore pageStore = null;
    private final org.wordpress.android.viewmodel.ResourceProvider resourceProvider = null;
    private final kotlinx.coroutines.CoroutineDispatcher uiDispatcher = null;
    private final kotlinx.coroutines.CoroutineDispatcher defaultDispatcher = null;
    private final androidx.lifecycle.MutableLiveData<java.util.List<org.wordpress.android.ui.pages.PageItem>> _pages = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<org.wordpress.android.ui.pages.PageItem>> pages = null;
    private org.wordpress.android.ui.pages.PageItem.ParentPage _currentParent;
    private org.wordpress.android.ui.pages.PageItem.ParentPage _initialParent;
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> _isSaveButtonVisible = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Boolean> isSaveButtonVisible = null;
    private final org.wordpress.android.viewmodel.SingleLiveEvent<kotlin.Unit> _saveParent = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<kotlin.Unit> saveParent = null;
    private final androidx.lifecycle.MutableLiveData<java.util.List<org.wordpress.android.ui.pages.PageItem>> _searchPages = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<org.wordpress.android.ui.pages.PageItem>> searchPages = null;
    private java.lang.String _lastSearchQuery = "";
    private kotlinx.coroutines.Job searchJob;
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> _isSearchExpanded = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Boolean> isSearchExpanded = null;
    private org.wordpress.android.fluxc.model.SiteModel site;
    private boolean isStarted = false;
    private org.wordpress.android.fluxc.model.page.PageModel page;
    
    @javax.inject.Inject()
    public PageParentViewModel(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.PageStore pageStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.ResourceProvider resourceProvider, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "UI_THREAD")
    kotlinx.coroutines.CoroutineDispatcher uiDispatcher, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher defaultDispatcher) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<org.wordpress.android.ui.pages.PageItem>> getPages() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.pages.PageItem.ParentPage getCurrentParent() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.pages.PageItem.ParentPage getInitialParent() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Boolean> isSaveButtonVisible() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<kotlin.Unit> getSaveParent() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<org.wordpress.android.ui.pages.PageItem>> getSearchPages() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getLastSearchQuery() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Boolean> isSearchExpanded() {
        return null;
    }
    
    public final void start(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, long pageId) {
    }
    
    private final kotlinx.coroutines.Job loadPages(long pageId) {
        return null;
    }
    
    public final void onParentSelected(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.pages.PageItem.ParentPage page) {
    }
    
    public final void onSaveButtonTapped() {
    }
    
    private final void trackSaveEvent() {
    }
    
    private final boolean isNotChild(org.wordpress.android.fluxc.model.page.PageModel choice, java.util.List<org.wordpress.android.fluxc.model.page.PageModel> choices) {
        return false;
    }
    
    private final java.util.List<org.wordpress.android.fluxc.model.page.PageModel> getChildren(org.wordpress.android.fluxc.model.page.PageModel page, java.util.List<org.wordpress.android.fluxc.model.page.PageModel> pages) {
        return null;
    }
    
    public final void onSearchExpanded(boolean restorePreviousSearch) {
    }
    
    public final void onSearchCollapsed() {
    }
    
    private final void clearSearch() {
    }
    
    public final void onSearch(@org.jetbrains.annotations.NotNull()
    java.lang.String searchQuery, long delay) {
    }
    
    private final java.lang.Object search(java.lang.String searchQuery, kotlin.coroutines.Continuation<? super java.util.List<? extends org.wordpress.android.ui.pages.PageItem>> continuation) {
        return null;
    }
    
    private final void setSaveButton() {
    }
}