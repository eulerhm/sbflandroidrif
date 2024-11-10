package org.wordpress.android.viewmodel.pages;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u008a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B9\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\b\b\u0001\u0010\f\u001a\u00020\r\u00a2\u0006\u0002\u0010\u000eJ\"\u0010 \u001a\u00020!2\u0018\u0010\"\u001a\u0014\u0012\u0004\u0012\u00020\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0\u00110\u0019H\u0002J\b\u0010#\u001a\u00020$H\u0014J\u000e\u0010%\u001a\u00020$2\u0006\u0010&\u001a\u00020\'J\"\u0010(\u001a\u00020\u00142\u0006\u0010)\u001a\u00020*2\u0006\u0010&\u001a\u00020\'2\n\b\u0002\u0010+\u001a\u0004\u0018\u00010,J\u000e\u0010-\u001a\u00020$2\u0006\u0010\u0015\u001a\u00020\u0016J\u0014\u0010.\u001a\u00020\u0012*\u00020\u001b2\u0006\u0010/\u001a\u00020\u0014H\u0002R\u001a\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00110\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R(\u0010\u0017\u001a\u001c\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0\u0011\u0018\u00010\u00190\u0018X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00110\u001d\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u00060"}, d2 = {"Lorg/wordpress/android/viewmodel/pages/SearchListViewModel;", "Lorg/wordpress/android/viewmodel/ScopedViewModel;", "createPageListItemLabelsUseCase", "Lorg/wordpress/android/viewmodel/pages/CreatePageListItemLabelsUseCase;", "postModelUploadUiStateUseCase", "Lorg/wordpress/android/viewmodel/pages/PostModelUploadUiStateUseCase;", "pageListItemActionsUseCase", "Lorg/wordpress/android/viewmodel/pages/CreatePageListItemActionsUseCase;", "pageItemProgressUiStateUseCase", "Lorg/wordpress/android/viewmodel/pages/PageItemProgressUiStateUseCase;", "resourceProvider", "Lorg/wordpress/android/viewmodel/ResourceProvider;", "uiDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Lorg/wordpress/android/viewmodel/pages/CreatePageListItemLabelsUseCase;Lorg/wordpress/android/viewmodel/pages/PostModelUploadUiStateUseCase;Lorg/wordpress/android/viewmodel/pages/CreatePageListItemActionsUseCase;Lorg/wordpress/android/viewmodel/pages/PageItemProgressUiStateUseCase;Lorg/wordpress/android/viewmodel/ResourceProvider;Lkotlinx/coroutines/CoroutineDispatcher;)V", "_searchResult", "Landroidx/lifecycle/MutableLiveData;", "", "Lorg/wordpress/android/ui/pages/PageItem;", "isStarted", "", "pagesViewModel", "Lorg/wordpress/android/viewmodel/pages/PagesViewModel;", "searchObserver", "Landroidx/lifecycle/Observer;", "Ljava/util/SortedMap;", "Lorg/wordpress/android/viewmodel/pages/PageListViewModel$PageListType;", "Lorg/wordpress/android/fluxc/model/page/PageModel;", "searchResult", "Landroidx/lifecycle/LiveData;", "getSearchResult", "()Landroidx/lifecycle/LiveData;", "loadFoundPages", "Lkotlinx/coroutines/Job;", "pages", "onCleared", "", "onItemTapped", "pageItem", "Lorg/wordpress/android/ui/pages/PageItem$Page;", "onMenuAction", "action", "Lorg/wordpress/android/ui/pages/PageItem$Action;", "context", "Landroid/content/Context;", "start", "toPageItem", "areActionsEnabled", "org.wordpress.android_wordpressJalapenoDebug"})
public final class SearchListViewModel extends org.wordpress.android.viewmodel.ScopedViewModel {
    private final org.wordpress.android.viewmodel.pages.CreatePageListItemLabelsUseCase createPageListItemLabelsUseCase = null;
    private final org.wordpress.android.viewmodel.pages.PostModelUploadUiStateUseCase postModelUploadUiStateUseCase = null;
    private final org.wordpress.android.viewmodel.pages.CreatePageListItemActionsUseCase pageListItemActionsUseCase = null;
    private final org.wordpress.android.viewmodel.pages.PageItemProgressUiStateUseCase pageItemProgressUiStateUseCase = null;
    private final org.wordpress.android.viewmodel.ResourceProvider resourceProvider = null;
    private final kotlinx.coroutines.CoroutineDispatcher uiDispatcher = null;
    private final androidx.lifecycle.MutableLiveData<java.util.List<org.wordpress.android.ui.pages.PageItem>> _searchResult = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<org.wordpress.android.ui.pages.PageItem>> searchResult = null;
    private boolean isStarted = false;
    private org.wordpress.android.viewmodel.pages.PagesViewModel pagesViewModel;
    private final androidx.lifecycle.Observer<java.util.SortedMap<org.wordpress.android.viewmodel.pages.PageListViewModel.PageListType, java.util.List<org.wordpress.android.fluxc.model.page.PageModel>>> searchObserver = null;
    
    @javax.inject.Inject()
    public SearchListViewModel(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.pages.CreatePageListItemLabelsUseCase createPageListItemLabelsUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.pages.PostModelUploadUiStateUseCase postModelUploadUiStateUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.pages.CreatePageListItemActionsUseCase pageListItemActionsUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.pages.PageItemProgressUiStateUseCase pageItemProgressUiStateUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.ResourceProvider resourceProvider, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "UI_THREAD")
    kotlinx.coroutines.CoroutineDispatcher uiDispatcher) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<org.wordpress.android.ui.pages.PageItem>> getSearchResult() {
        return null;
    }
    
    public final void start(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.pages.PagesViewModel pagesViewModel) {
    }
    
    @java.lang.Override()
    protected void onCleared() {
    }
    
    public final boolean onMenuAction(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.pages.PageItem.Action action, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.pages.PageItem.Page pageItem, @org.jetbrains.annotations.Nullable()
    android.content.Context context) {
        return false;
    }
    
    public final void onItemTapped(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.pages.PageItem.Page pageItem) {
    }
    
    private final kotlinx.coroutines.Job loadFoundPages(java.util.SortedMap<org.wordpress.android.viewmodel.pages.PageListViewModel.PageListType, java.util.List<org.wordpress.android.fluxc.model.page.PageModel>> pages) {
        return null;
    }
    
    private final org.wordpress.android.ui.pages.PageItem toPageItem(org.wordpress.android.fluxc.model.page.PageModel $this$toPageItem, boolean areActionsEnabled) {
        return null;
    }
}