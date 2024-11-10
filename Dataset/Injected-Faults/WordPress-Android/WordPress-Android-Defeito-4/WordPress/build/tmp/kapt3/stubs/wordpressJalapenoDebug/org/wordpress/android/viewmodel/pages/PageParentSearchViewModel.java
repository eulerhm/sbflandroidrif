package org.wordpress.android.viewmodel.pages;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0011\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0013\u001a\u00020\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0002J\b\u0010\u0016\u001a\u00020\u0017H\u0014J\u000e\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u001aJ\u000e\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u000b\u001a\u00020\fR\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u001c\u0010\r\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00070\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001c"}, d2 = {"Lorg/wordpress/android/viewmodel/pages/PageParentSearchViewModel;", "Lorg/wordpress/android/viewmodel/ScopedViewModel;", "uiDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Lkotlinx/coroutines/CoroutineDispatcher;)V", "_searchResult", "Landroidx/lifecycle/MutableLiveData;", "", "Lorg/wordpress/android/ui/pages/PageItem;", "isStarted", "", "pageParentViewModel", "Lorg/wordpress/android/viewmodel/pages/PageParentViewModel;", "searchObserver", "Landroidx/lifecycle/Observer;", "searchResult", "Landroidx/lifecycle/LiveData;", "getSearchResult", "()Landroidx/lifecycle/LiveData;", "loadFoundPages", "Lkotlinx/coroutines/Job;", "pageItems", "onCleared", "", "onParentSelected", "page", "Lorg/wordpress/android/ui/pages/PageItem$ParentPage;", "start", "org.wordpress.android_wordpressJalapenoDebug"})
public final class PageParentSearchViewModel extends org.wordpress.android.viewmodel.ScopedViewModel {
    private final kotlinx.coroutines.CoroutineDispatcher uiDispatcher = null;
    private final androidx.lifecycle.MutableLiveData<java.util.List<org.wordpress.android.ui.pages.PageItem>> _searchResult = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<org.wordpress.android.ui.pages.PageItem>> searchResult = null;
    private boolean isStarted = false;
    private org.wordpress.android.viewmodel.pages.PageParentViewModel pageParentViewModel;
    private final androidx.lifecycle.Observer<java.util.List<org.wordpress.android.ui.pages.PageItem>> searchObserver = null;
    
    @javax.inject.Inject()
    public PageParentSearchViewModel(@org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "UI_THREAD")
    kotlinx.coroutines.CoroutineDispatcher uiDispatcher) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<org.wordpress.android.ui.pages.PageItem>> getSearchResult() {
        return null;
    }
    
    public final void start(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.pages.PageParentViewModel pageParentViewModel) {
    }
    
    @java.lang.Override()
    protected void onCleared() {
    }
    
    public final void onParentSelected(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.pages.PageItem.ParentPage page) {
    }
    
    private final kotlinx.coroutines.Job loadFoundPages(java.util.List<? extends org.wordpress.android.ui.pages.PageItem> pageItems) {
        return null;
    }
}