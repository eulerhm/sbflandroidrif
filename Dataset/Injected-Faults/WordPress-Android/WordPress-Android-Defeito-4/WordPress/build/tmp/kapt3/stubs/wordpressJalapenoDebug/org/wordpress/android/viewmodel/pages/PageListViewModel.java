package org.wordpress.android.viewmodel.pages;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u00dc\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\t\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\u0018\u00002\u00020\u0001:\u0003defBQ\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\b\b\u0001\u0010\u0012\u001a\u00020\u0013\u00a2\u0006\u0002\u0010\u0014J\u0010\u0010>\u001a\u00020?2\u0006\u0010@\u001a\u000203H\u0002J\u0016\u0010A\u001a\u00020B2\f\u0010C\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017H\u0002J\u0012\u0010D\u001a\u0004\u0018\u00010\"2\u0006\u0010E\u001a\u00020!H\u0002J\u0012\u0010F\u001a\u00020\u001b2\b\u0010G\u001a\u0004\u0018\u000103H\u0002J\u0014\u0010H\u001a\u00020B2\n\u0010I\u001a\u00020J\"\u00020!H\u0002J\u0016\u0010K\u001a\u00020L2\f\u0010-\u001a\b\u0012\u0004\u0012\u0002030\u0017H\u0002J\b\u0010M\u001a\u00020BH\u0014J\u0006\u0010N\u001a\u00020BJ\u0016\u0010O\u001a\u00020B2\u0006\u0010P\u001a\u00020Q2\u0006\u0010R\u001a\u00020SJ\u0010\u0010T\u001a\u00020B2\u0006\u0010U\u001a\u00020VH\u0007J\u001e\u0010W\u001a\u00020$2\u0006\u0010X\u001a\u00020Y2\u0006\u0010P\u001a\u00020Q2\u0006\u0010R\u001a\u00020SJ\u000e\u0010Z\u001a\u00020B2\u0006\u0010[\u001a\u00020\u001bJ$\u0010\\\u001a\b\u0012\u0004\u0012\u00020\u00180\u00172\f\u0010-\u001a\b\u0012\u0004\u0012\u0002030\u00172\u0006\u0010]\u001a\u00020$H\u0002J$\u0010^\u001a\b\u0012\u0004\u0012\u00020\u00180\u00172\f\u0010-\u001a\b\u0012\u0004\u0012\u0002030\u00172\u0006\u0010]\u001a\u00020$H\u0002J$\u0010_\u001a\b\u0012\u0004\u0012\u00020\u00180\u00172\f\u0010-\u001a\b\u0012\u0004\u0012\u0002030\u00172\u0006\u0010]\u001a\u00020$H\u0002J$\u0010`\u001a\b\u0012\u0004\u0012\u00020\u00180\u00172\f\u0010-\u001a\b\u0012\u0004\u0012\u0002030\u00172\u0006\u0010]\u001a\u00020$H\u0002J\u0016\u0010a\u001a\u00020B2\u0006\u0010+\u001a\u00020,2\u0006\u00104\u001a\u000205J0\u0010b\u001a\b\u0012\u0004\u0012\u0002030\u00172\f\u0010-\u001a\b\u0012\u0004\u0012\u0002030\u00172\u0006\u0010+\u001a\u00020,2\n\b\u0002\u0010c\u001a\u0004\u0018\u000103H\u0002R\u001a\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u00170\u0016X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001aX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001dX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\"0 X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010#\u001a\u00020$8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b&\u0010\'\u001a\u0004\b#\u0010%R\u001b\u0010(\u001a\u00020$8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b)\u0010\'\u001a\u0004\b(\u0010%R\u000e\u0010*\u001a\u00020$X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020,X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R/\u0010-\u001a \u0012\u001c\u0012\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u0017\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020$0/0.\u00a2\u0006\b\n\u0000\u001a\u0004\b0\u00101R\u001a\u00102\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002030\u00170\u001dX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u00104\u001a\u000205X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u00106\u001a\u0004\u0018\u000107X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0017\u00108\u001a\b\u0012\u0004\u0012\u00020\u001b0.\u00a2\u0006\b\n\u0000\u001a\u0004\b9\u00101R\u001a\u0010:\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002070\u00170\u001dX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0018\u0010;\u001a\u00020$*\u0002038BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b;\u0010<R\u0018\u0010=\u001a\u00020$*\u0002038BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b=\u0010<\u00a8\u0006g"}, d2 = {"Lorg/wordpress/android/viewmodel/pages/PageListViewModel;", "Lorg/wordpress/android/viewmodel/ScopedViewModel;", "createPageListItemLabelsUseCase", "Lorg/wordpress/android/viewmodel/pages/CreatePageListItemLabelsUseCase;", "postModelUploadUiStateUseCase", "Lorg/wordpress/android/viewmodel/pages/PostModelUploadUiStateUseCase;", "pageListItemActionsUseCase", "Lorg/wordpress/android/viewmodel/pages/CreatePageListItemActionsUseCase;", "pageItemProgressUiStateUseCase", "Lorg/wordpress/android/viewmodel/pages/PageItemProgressUiStateUseCase;", "mediaStore", "Lorg/wordpress/android/fluxc/store/MediaStore;", "dispatcher", "Lorg/wordpress/android/fluxc/Dispatcher;", "localeManagerWrapper", "Lorg/wordpress/android/util/LocaleManagerWrapper;", "accountStore", "Lorg/wordpress/android/fluxc/store/AccountStore;", "coroutineDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Lorg/wordpress/android/viewmodel/pages/CreatePageListItemLabelsUseCase;Lorg/wordpress/android/viewmodel/pages/PostModelUploadUiStateUseCase;Lorg/wordpress/android/viewmodel/pages/CreatePageListItemActionsUseCase;Lorg/wordpress/android/viewmodel/pages/PageItemProgressUiStateUseCase;Lorg/wordpress/android/fluxc/store/MediaStore;Lorg/wordpress/android/fluxc/Dispatcher;Lorg/wordpress/android/util/LocaleManagerWrapper;Lorg/wordpress/android/fluxc/store/AccountStore;Lkotlinx/coroutines/CoroutineDispatcher;)V", "_pages", "Landroidx/lifecycle/MutableLiveData;", "", "Lorg/wordpress/android/ui/pages/PageItem;", "_scrollToPosition", "Lorg/wordpress/android/viewmodel/SingleLiveEvent;", "", "authorSelectionChangedObserver", "Landroidx/lifecycle/Observer;", "Lorg/wordpress/android/ui/posts/AuthorFilterSelection;", "featuredImageMap", "", "", "", "isSitePhotonCapable", "", "()Z", "isSitePhotonCapable$delegate", "Lkotlin/Lazy;", "isSitePrivateAt", "isSitePrivateAt$delegate", "isStarted", "listType", "Lorg/wordpress/android/viewmodel/pages/PageListViewModel$PageListType;", "pages", "Landroidx/lifecycle/LiveData;", "Lkotlin/Triple;", "getPages", "()Landroidx/lifecycle/LiveData;", "pagesObserver", "Lorg/wordpress/android/fluxc/model/page/PageModel;", "pagesViewModel", "Lorg/wordpress/android/viewmodel/pages/PagesViewModel;", "retryScrollToPage", "Lorg/wordpress/android/fluxc/model/LocalOrRemoteId$LocalId;", "scrollToPosition", "getScrollToPosition", "uploadStatusObserver", "isHomepage", "(Lorg/wordpress/android/fluxc/model/page/PageModel;)Z", "isPostsPage", "createItemUiStateData", "Lorg/wordpress/android/viewmodel/pages/PageListViewModel$ItemUiStateData;", "pageModel", "displayListItems", "", "newPages", "getFeaturedImageUrl", "featuredImageId", "getPageItemIndent", "page", "invalidateFeaturedMedia", "featuredImageIds", "", "loadPagesAsync", "Lkotlinx/coroutines/Job;", "onCleared", "onEmptyListNewPageButtonTapped", "onItemTapped", "pageItem", "Lorg/wordpress/android/ui/pages/PageItem$Page;", "context", "Landroid/content/Context;", "onMediaChanged", "event", "Lorg/wordpress/android/fluxc/store/MediaStore$OnMediaChanged;", "onMenuAction", "action", "Lorg/wordpress/android/ui/pages/PageItem$Action;", "onScrollToPageRequested", "localPageId", "prepareDraftPages", "actionsEnabled", "preparePublishedPages", "prepareScheduledPages", "prepareTrashedPages", "start", "topologicalSort", "parent", "ItemUiStateData", "PageListState", "PageListType", "org.wordpress.android_wordpressJalapenoDebug"})
public final class PageListViewModel extends org.wordpress.android.viewmodel.ScopedViewModel {
    private final org.wordpress.android.viewmodel.pages.CreatePageListItemLabelsUseCase createPageListItemLabelsUseCase = null;
    private final org.wordpress.android.viewmodel.pages.PostModelUploadUiStateUseCase postModelUploadUiStateUseCase = null;
    private final org.wordpress.android.viewmodel.pages.CreatePageListItemActionsUseCase pageListItemActionsUseCase = null;
    private final org.wordpress.android.viewmodel.pages.PageItemProgressUiStateUseCase pageItemProgressUiStateUseCase = null;
    private final org.wordpress.android.fluxc.store.MediaStore mediaStore = null;
    private final org.wordpress.android.fluxc.Dispatcher dispatcher = null;
    private final org.wordpress.android.util.LocaleManagerWrapper localeManagerWrapper = null;
    private final org.wordpress.android.fluxc.store.AccountStore accountStore = null;
    private final kotlinx.coroutines.CoroutineDispatcher coroutineDispatcher = null;
    private final androidx.lifecycle.MutableLiveData<java.util.List<org.wordpress.android.ui.pages.PageItem>> _pages = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<kotlin.Triple<java.util.List<org.wordpress.android.ui.pages.PageItem>, java.lang.Boolean, java.lang.Boolean>> pages = null;
    private final org.wordpress.android.viewmodel.SingleLiveEvent<java.lang.Integer> _scrollToPosition = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Integer> scrollToPosition = null;
    private org.wordpress.android.fluxc.model.LocalOrRemoteId.LocalId retryScrollToPage;
    private boolean isStarted = false;
    private org.wordpress.android.viewmodel.pages.PageListViewModel.PageListType listType;
    private org.wordpress.android.viewmodel.pages.PagesViewModel pagesViewModel;
    private final java.util.Map<java.lang.Long, java.lang.String> featuredImageMap = null;
    private final kotlin.Lazy isSitePhotonCapable$delegate = null;
    private final kotlin.Lazy isSitePrivateAt$delegate = null;
    private final androidx.lifecycle.Observer<java.util.List<org.wordpress.android.fluxc.model.page.PageModel>> pagesObserver = null;
    private final androidx.lifecycle.Observer<java.util.List<org.wordpress.android.fluxc.model.LocalOrRemoteId.LocalId>> uploadStatusObserver = null;
    private final androidx.lifecycle.Observer<org.wordpress.android.ui.posts.AuthorFilterSelection> authorSelectionChangedObserver = null;
    
    @javax.inject.Inject()
    public PageListViewModel(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.pages.CreatePageListItemLabelsUseCase createPageListItemLabelsUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.pages.PostModelUploadUiStateUseCase postModelUploadUiStateUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.pages.CreatePageListItemActionsUseCase pageListItemActionsUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.pages.PageItemProgressUiStateUseCase pageItemProgressUiStateUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.MediaStore mediaStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.Dispatcher dispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.LocaleManagerWrapper localeManagerWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.AccountStore accountStore, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher coroutineDispatcher) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<kotlin.Triple<java.util.List<org.wordpress.android.ui.pages.PageItem>, java.lang.Boolean, java.lang.Boolean>> getPages() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Integer> getScrollToPosition() {
        return null;
    }
    
    private final boolean isHomepage(org.wordpress.android.fluxc.model.page.PageModel $this$isHomepage) {
        return false;
    }
    
    private final boolean isPostsPage(org.wordpress.android.fluxc.model.page.PageModel $this$isPostsPage) {
        return false;
    }
    
    private final boolean isSitePhotonCapable() {
        return false;
    }
    
    private final boolean isSitePrivateAt() {
        return false;
    }
    
    public final void start(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.pages.PageListViewModel.PageListType listType, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.pages.PagesViewModel pagesViewModel) {
    }
    
    @java.lang.Override()
    protected void onCleared() {
    }
    
    public final boolean onMenuAction(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.pages.PageItem.Action action, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.pages.PageItem.Page pageItem, @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return false;
    }
    
    public final void onItemTapped(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.pages.PageItem.Page pageItem, @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    public final void onEmptyListNewPageButtonTapped() {
    }
    
    public final void onScrollToPageRequested(int localPageId) {
    }
    
    private final kotlinx.coroutines.Job loadPagesAsync(java.util.List<org.wordpress.android.fluxc.model.page.PageModel> pages) {
        return null;
    }
    
    private final void displayListItems(java.util.List<? extends org.wordpress.android.ui.pages.PageItem> newPages) {
    }
    
    private final java.lang.String getFeaturedImageUrl(long featuredImageId) {
        return null;
    }
    
    private final java.util.List<org.wordpress.android.ui.pages.PageItem> preparePublishedPages(java.util.List<org.wordpress.android.fluxc.model.page.PageModel> pages, boolean actionsEnabled) {
        return null;
    }
    
    private final java.util.List<org.wordpress.android.ui.pages.PageItem> prepareScheduledPages(java.util.List<org.wordpress.android.fluxc.model.page.PageModel> pages, boolean actionsEnabled) {
        return null;
    }
    
    private final java.util.List<org.wordpress.android.ui.pages.PageItem> prepareDraftPages(java.util.List<org.wordpress.android.fluxc.model.page.PageModel> pages, boolean actionsEnabled) {
        return null;
    }
    
    private final java.util.List<org.wordpress.android.ui.pages.PageItem> prepareTrashedPages(java.util.List<org.wordpress.android.fluxc.model.page.PageModel> pages, boolean actionsEnabled) {
        return null;
    }
    
    private final java.util.List<org.wordpress.android.fluxc.model.page.PageModel> topologicalSort(java.util.List<org.wordpress.android.fluxc.model.page.PageModel> pages, org.wordpress.android.viewmodel.pages.PageListViewModel.PageListType listType, org.wordpress.android.fluxc.model.page.PageModel parent) {
        return null;
    }
    
    private final int getPageItemIndent(org.wordpress.android.fluxc.model.page.PageModel page) {
        return 0;
    }
    
    private final void invalidateFeaturedMedia(long... featuredImageIds) {
    }
    
    @java.lang.SuppressWarnings(value = {"unused"})
    @org.greenrobot.eventbus.Subscribe(threadMode = org.greenrobot.eventbus.ThreadMode.BACKGROUND)
    public final void onMediaChanged(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.MediaStore.OnMediaChanged event) {
    }
    
    private final org.wordpress.android.viewmodel.pages.PageListViewModel.ItemUiStateData createItemUiStateData(org.wordpress.android.fluxc.model.page.PageModel pageModel) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\b\b\u0086\u0001\u0018\u0000 \u00102\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0010B\u0015\b\u0002\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\u0005R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\t8F\u00a2\u0006\u0006\u001a\u0004\b\n\u0010\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000f\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/viewmodel/pages/PageListViewModel$PageListType;", "", "pageStatuses", "", "Lorg/wordpress/android/fluxc/model/page/PageStatus;", "(Ljava/lang/String;ILjava/util/List;)V", "getPageStatuses", "()Ljava/util/List;", "title", "", "getTitle", "()I", "PUBLISHED", "DRAFTS", "SCHEDULED", "TRASHED", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum PageListType {
        /*public static final*/ PUBLISHED /* = new PUBLISHED(null) */,
        /*public static final*/ DRAFTS /* = new DRAFTS(null) */,
        /*public static final*/ SCHEDULED /* = new SCHEDULED(null) */,
        /*public static final*/ TRASHED /* = new TRASHED(null) */;
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<org.wordpress.android.fluxc.model.page.PageStatus> pageStatuses = null;
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.viewmodel.pages.PageListViewModel.PageListType.Companion Companion = null;
        
        PageListType(java.util.List<? extends org.wordpress.android.fluxc.model.page.PageStatus> pageStatuses) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.fluxc.model.page.PageStatus> getPageStatuses() {
            return null;
        }
        
        public final int getTitle() {
            return 0;
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/viewmodel/pages/PageListViewModel$PageListType$Companion;", "", "()V", "fromPageStatus", "Lorg/wordpress/android/viewmodel/pages/PageListViewModel$PageListType;", "status", "Lorg/wordpress/android/fluxc/model/page/PageStatus;", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Companion {
            
            private Companion() {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.viewmodel.pages.PageListViewModel.PageListType fromPageStatus(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.fluxc.model.page.PageStatus status) {
                return null;
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/viewmodel/pages/PageListViewModel$PageListState;", "", "(Ljava/lang/String;I)V", "DONE", "ERROR", "REFRESHING", "FETCHING", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum PageListState {
        /*public static final*/ DONE /* = new DONE() */,
        /*public static final*/ ERROR /* = new ERROR() */,
        /*public static final*/ REFRESHING /* = new REFRESHING() */,
        /*public static final*/ FETCHING /* = new FETCHING() */;
        
        PageListState() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b \n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B_\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u0010\u0010\u001a\u00020\n\u00a2\u0006\u0002\u0010\u0011J\u000f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\u0010\u0010!\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0015J\t\u0010\"\u001a\u00020\bH\u00c6\u0003J\t\u0010#\u001a\u00020\nH\u00c6\u0003J\u000f\u0010$\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u00c6\u0003J\u0010\u0010%\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0015J\u0010\u0010&\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0015J\t\u0010\'\u001a\u00020\nH\u00c6\u0003Jp\u0010(\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\u0010\u001a\u00020\nH\u00c6\u0001\u00a2\u0006\u0002\u0010)J\u0013\u0010*\u001a\u00020\n2\b\u0010+\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010,\u001a\u00020\u0006H\u00d6\u0001J\t\u0010-\u001a\u00020.H\u00d6\u0001R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0015\u0010\u000f\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\n\n\u0002\u0010\u0016\u001a\u0004\b\u0014\u0010\u0015R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\n\n\u0002\u0010\u0016\u001a\u0004\b\u0019\u0010\u0015R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\u0010\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001dR\u0015\u0010\u000e\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\n\n\u0002\u0010\u0016\u001a\u0004\b\u001f\u0010\u0015\u00a8\u0006/"}, d2 = {"Lorg/wordpress/android/viewmodel/pages/PageListViewModel$ItemUiStateData;", "", "labels", "", "Lorg/wordpress/android/ui/utils/UiString;", "labelsColor", "", "progressBarUiState", "Lorg/wordpress/android/viewmodel/uistate/ProgressBarUiState;", "showOverlay", "", "actions", "", "Lorg/wordpress/android/ui/pages/PageItem$Action;", "subtitle", "icon", "showQuickStartFocusPoint", "(Ljava/util/List;Ljava/lang/Integer;Lorg/wordpress/android/viewmodel/uistate/ProgressBarUiState;ZLjava/util/Set;Ljava/lang/Integer;Ljava/lang/Integer;Z)V", "getActions", "()Ljava/util/Set;", "getIcon", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getLabels", "()Ljava/util/List;", "getLabelsColor", "getProgressBarUiState", "()Lorg/wordpress/android/viewmodel/uistate/ProgressBarUiState;", "getShowOverlay", "()Z", "getShowQuickStartFocusPoint", "getSubtitle", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "(Ljava/util/List;Ljava/lang/Integer;Lorg/wordpress/android/viewmodel/uistate/ProgressBarUiState;ZLjava/util/Set;Ljava/lang/Integer;Ljava/lang/Integer;Z)Lorg/wordpress/android/viewmodel/pages/PageListViewModel$ItemUiStateData;", "equals", "other", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    static final class ItemUiStateData {
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<org.wordpress.android.ui.utils.UiString> labels = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Integer labelsColor = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.viewmodel.uistate.ProgressBarUiState progressBarUiState = null;
        private final boolean showOverlay = false;
        @org.jetbrains.annotations.NotNull()
        private final java.util.Set<org.wordpress.android.ui.pages.PageItem.Action> actions = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Integer subtitle = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Integer icon = null;
        private final boolean showQuickStartFocusPoint = false;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.viewmodel.pages.PageListViewModel.ItemUiStateData copy(@org.jetbrains.annotations.NotNull()
        java.util.List<? extends org.wordpress.android.ui.utils.UiString> labels, @org.jetbrains.annotations.Nullable()
        @androidx.annotation.ColorRes()
        java.lang.Integer labelsColor, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.viewmodel.uistate.ProgressBarUiState progressBarUiState, boolean showOverlay, @org.jetbrains.annotations.NotNull()
        java.util.Set<? extends org.wordpress.android.ui.pages.PageItem.Action> actions, @org.jetbrains.annotations.Nullable()
        java.lang.Integer subtitle, @org.jetbrains.annotations.Nullable()
        java.lang.Integer icon, boolean showQuickStartFocusPoint) {
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
        
        public ItemUiStateData(@org.jetbrains.annotations.NotNull()
        java.util.List<? extends org.wordpress.android.ui.utils.UiString> labels, @org.jetbrains.annotations.Nullable()
        @androidx.annotation.ColorRes()
        java.lang.Integer labelsColor, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.viewmodel.uistate.ProgressBarUiState progressBarUiState, boolean showOverlay, @org.jetbrains.annotations.NotNull()
        java.util.Set<? extends org.wordpress.android.ui.pages.PageItem.Action> actions, @org.jetbrains.annotations.Nullable()
        java.lang.Integer subtitle, @org.jetbrains.annotations.Nullable()
        java.lang.Integer icon, boolean showQuickStartFocusPoint) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.ui.utils.UiString> component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.ui.utils.UiString> getLabels() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer component2() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer getLabelsColor() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.viewmodel.uistate.ProgressBarUiState component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.viewmodel.uistate.ProgressBarUiState getProgressBarUiState() {
            return null;
        }
        
        public final boolean component4() {
            return false;
        }
        
        public final boolean getShowOverlay() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.Set<org.wordpress.android.ui.pages.PageItem.Action> component5() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.Set<org.wordpress.android.ui.pages.PageItem.Action> getActions() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer component6() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer getSubtitle() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer component7() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer getIcon() {
            return null;
        }
        
        public final boolean component8() {
            return false;
        }
        
        public final boolean getShowQuickStartFocusPoint() {
            return false;
        }
    }
}