package org.wordpress.android.viewmodel.posts;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u00ea\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001Bq\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\b\b\u0001\u0010\u0014\u001a\u00020\u0015\u0012\b\b\u0001\u0010\u0016\u001a\u00020\u0015\u0012\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018\u00a2\u0006\u0002\u0010\u001aJ\b\u0010W\u001a\u00020XH\u0002J\u0014\u0010Y\u001a\u0004\u0018\u00010V2\b\u0010Z\u001a\u0004\u0018\u00010VH\u0002J\b\u0010[\u001a\u00020XH\u0002J)\u0010\\\u001a\u0004\u0018\u00010\'2\u0010\u0010]\u001a\f\u0012\u0004\u0012\u00020$0#j\u0002`>2\u0006\u0010^\u001a\u00020OH\u0002\u00a2\u0006\u0002\u0010_J\u0018\u0010`\u001a\u00020X2\u0006\u0010,\u001a\u00020-2\u0006\u0010a\u001a\u00020bH\u0002J\u0012\u0010c\u001a\u00020d2\b\u0010U\u001a\u0004\u0018\u00010VH\u0002J\b\u0010e\u001a\u00020 H\u0002J\b\u0010f\u001a\u00020 H\u0002J\u0016\u0010g\u001a\u00020X2\f\u0010@\u001a\b\u0012\u0004\u0012\u00020$0AH\u0002J\b\u0010h\u001a\u00020XH\u0014J\u001a\u0010i\u001a\u00020X2\u0010\u0010]\u001a\f\u0012\u0004\u0012\u00020$0#j\u0002`>H\u0002J\b\u0010j\u001a\u00020XH\u0002J\u000e\u0010k\u001a\u00020X2\u0006\u0010^\u001a\u00020OJ\u001a\u0010l\u001a\u00020X2\b\u0010m\u001a\u0004\u0018\u00010V2\b\b\u0002\u0010n\u001a\u00020oJ\b\u0010p\u001a\u00020XH\u0002J&\u0010q\u001a\u00020X2\u0006\u0010r\u001a\u00020+2\u0006\u0010s\u001a\u00020)2\u0006\u0010J\u001a\u00020\'2\u0006\u0010C\u001a\u00020\'J\u0006\u0010t\u001a\u00020XJ\u0010\u0010u\u001a\u00020v2\u0006\u0010w\u001a\u00020xH\u0002J\u000e\u0010y\u001a\u00020 2\u0006\u0010(\u001a\u00020)J\"\u0010z\u001a\u00020X2\u0010\u0010]\u001a\f\u0012\u0004\u0012\u00020$0#j\u0002`>2\u0006\u0010^\u001a\u00020OH\u0002R\u0014\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020 0\u001fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010!\u001a\b\u0012\u0004\u0012\u00020 0\u001fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\"\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020$0#0\u001fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010%\u001a\b\u0012\u0004\u0012\u00020\'0&X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020)X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0015X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020+X\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010,\u001a\u00020-8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b0\u00101\u001a\u0004\b.\u0010/R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u00102\u001a\b\u0012\u0004\u0012\u00020\u001d0\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\b3\u00104R\u0017\u00105\u001a\b\u0012\u0004\u0012\u00020 0\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\b5\u00104R\u0017\u00106\u001a\b\u0012\u0004\u0012\u00020 0\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\b6\u00104R\u000e\u00107\u001a\u00020 X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u00108\u001a\u00020 8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b:\u00101\u001a\u0004\b8\u00109R\u000e\u0010;\u001a\u00020<X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R!\u0010=\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020$0#j\u0002`>0\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\b?\u00104R\u0016\u0010@\u001a\n\u0012\u0004\u0012\u00020$\u0018\u00010AX\u0082\u000e\u00a2\u0006\u0002\n\u0000R+\u0010C\u001a\u00020\'2\u0006\u0010B\u001a\u00020\'8B@BX\u0082\u008e\u0002\u00a2\u0006\u0012\n\u0004\bH\u0010I\u001a\u0004\bD\u0010E\"\u0004\bF\u0010GR+\u0010J\u001a\u00020\'2\u0006\u0010B\u001a\u00020\'8B@BX\u0082\u008e\u0002\u00a2\u0006\u0012\n\u0004\bM\u0010I\u001a\u0004\bK\u0010E\"\u0004\bL\u0010GR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010N\u001a\u0004\u0018\u00010OX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0017\u0010P\u001a\b\u0012\u0004\u0012\u00020\'0\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\bQ\u00104R\u0010\u0010R\u001a\u0004\u0018\u00010SX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010T\u001a\u0004\u0018\u00010SX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010U\u001a\u0004\u0018\u00010VX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006{"}, d2 = {"Lorg/wordpress/android/viewmodel/posts/PostListViewModel;", "Lorg/wordpress/android/viewmodel/ScopedViewModel;", "dispatcher", "Lorg/wordpress/android/fluxc/Dispatcher;", "listStore", "Lorg/wordpress/android/fluxc/store/ListStore;", "postStore", "Lorg/wordpress/android/fluxc/store/PostStore;", "accountStore", "Lorg/wordpress/android/fluxc/store/AccountStore;", "listItemUiStateHelper", "Lorg/wordpress/android/viewmodel/posts/PostListItemUiStateHelper;", "networkUtilsWrapper", "Lorg/wordpress/android/util/NetworkUtilsWrapper;", "uploadStarter", "Lorg/wordpress/android/ui/uploads/UploadStarter;", "readerUtilsWrapper", "Lorg/wordpress/android/ui/reader/utils/ReaderUtilsWrapper;", "uploadUtilsWrapper", "Lorg/wordpress/android/ui/uploads/UploadUtilsWrapper;", "uiDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "bgDispatcher", "connectionStatus", "Landroidx/lifecycle/LiveData;", "Lorg/wordpress/android/viewmodel/helpers/ConnectionStatus;", "(Lorg/wordpress/android/fluxc/Dispatcher;Lorg/wordpress/android/fluxc/store/ListStore;Lorg/wordpress/android/fluxc/store/PostStore;Lorg/wordpress/android/fluxc/store/AccountStore;Lorg/wordpress/android/viewmodel/posts/PostListItemUiStateHelper;Lorg/wordpress/android/util/NetworkUtilsWrapper;Lorg/wordpress/android/ui/uploads/UploadStarter;Lorg/wordpress/android/ui/reader/utils/ReaderUtilsWrapper;Lorg/wordpress/android/ui/uploads/UploadUtilsWrapper;Lkotlinx/coroutines/CoroutineDispatcher;Lkotlinx/coroutines/CoroutineDispatcher;Landroidx/lifecycle/LiveData;)V", "_emptyViewState", "Lorg/wordpress/android/util/ThrottleLiveData;", "Lorg/wordpress/android/viewmodel/posts/PostListEmptyUiState;", "_isFetchingFirstPage", "Landroidx/lifecycle/MediatorLiveData;", "", "_isLoadingMore", "_pagedListData", "Landroidx/paging/PagedList;", "Lorg/wordpress/android/viewmodel/posts/PostListItemType;", "_scrollToPosition", "Lorg/wordpress/android/viewmodel/SingleLiveEvent;", "", "authorFilterSelection", "Lorg/wordpress/android/ui/posts/AuthorFilterSelection;", "connector", "Lorg/wordpress/android/viewmodel/posts/PostListViewModelConnector;", "dataSource", "Lorg/wordpress/android/viewmodel/posts/PostListItemDataSource;", "getDataSource", "()Lorg/wordpress/android/viewmodel/posts/PostListItemDataSource;", "dataSource$delegate", "Lkotlin/Lazy;", "emptyViewState", "getEmptyViewState", "()Landroidx/lifecycle/LiveData;", "isFetchingFirstPage", "isLoadingMore", "isStarted", "isStatsSupported", "()Z", "isStatsSupported$delegate", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "pagedListData", "Lorg/wordpress/android/viewmodel/posts/PagedPostList;", "getPagedListData", "pagedListWrapper", "Lorg/wordpress/android/fluxc/model/list/PagedListWrapper;", "<set-?>", "photonHeight", "getPhotonHeight", "()I", "setPhotonHeight", "(I)V", "photonHeight$delegate", "Lkotlin/properties/ReadWriteProperty;", "photonWidth", "getPhotonWidth", "setPhotonWidth", "photonWidth$delegate", "scrollToLocalPostId", "Lorg/wordpress/android/viewmodel/posts/PostListItemIdentifier$LocalPostId;", "scrollToPosition", "getScrollToPosition", "searchJob", "Lkotlinx/coroutines/Job;", "searchProgressJob", "searchQuery", "", "clearLiveDataSources", "", "convertToPhotonUrlIfPossible", "featuredImageUrl", "fetchFirstPage", "findItemListPosition", "data", "localPostId", "(Landroidx/paging/PagedList;Lorg/wordpress/android/viewmodel/posts/PostListItemIdentifier$LocalPostId;)Ljava/lang/Integer;", "initList", "lifecycle", "Landroidx/lifecycle/Lifecycle;", "initListDescriptor", "Lorg/wordpress/android/fluxc/model/list/PostListDescriptor;", "isEmptySearch", "isSearchResultDeliverable", "listenToEmptyViewStateLiveData", "onCleared", "onDataUpdated", "retryOnConnectionAvailableAfterRefreshError", "scrollToPost", "search", "query", "delay", "", "showEmptySearchPrompt", "start", "postListViewModelConnector", "value", "swipeToRefresh", "transformPostModelToPostListItemUiState", "Lorg/wordpress/android/viewmodel/posts/PostListItemType$PostListItemUiState;", "post", "Lorg/wordpress/android/fluxc/model/PostModel;", "updateAuthorFilterIfNotSearch", "updateScrollPosition", "org.wordpress.android_wordpressJalapenoDebug"})
public final class PostListViewModel extends org.wordpress.android.viewmodel.ScopedViewModel {
    private final org.wordpress.android.fluxc.Dispatcher dispatcher = null;
    private final org.wordpress.android.fluxc.store.ListStore listStore = null;
    private final org.wordpress.android.fluxc.store.PostStore postStore = null;
    private final org.wordpress.android.fluxc.store.AccountStore accountStore = null;
    private final org.wordpress.android.viewmodel.posts.PostListItemUiStateHelper listItemUiStateHelper = null;
    private final org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper = null;
    private final org.wordpress.android.ui.uploads.UploadStarter uploadStarter = null;
    private final org.wordpress.android.ui.reader.utils.ReaderUtilsWrapper readerUtilsWrapper = null;
    private final org.wordpress.android.ui.uploads.UploadUtilsWrapper uploadUtilsWrapper = null;
    private final kotlinx.coroutines.CoroutineDispatcher uiDispatcher = null;
    private final kotlinx.coroutines.CoroutineDispatcher bgDispatcher = null;
    private final kotlin.Lazy isStatsSupported$delegate = null;
    private boolean isStarted = false;
    private org.wordpress.android.viewmodel.posts.PostListViewModelConnector connector;
    private final kotlin.properties.ReadWriteProperty photonWidth$delegate = null;
    private final kotlin.properties.ReadWriteProperty photonHeight$delegate = null;
    private org.wordpress.android.viewmodel.posts.PostListItemIdentifier.LocalPostId scrollToLocalPostId;
    private final org.wordpress.android.viewmodel.SingleLiveEvent<java.lang.Integer> _scrollToPosition = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Integer> scrollToPosition = null;
    private final kotlin.Lazy dataSource$delegate = null;
    private org.wordpress.android.fluxc.model.list.PagedListWrapper<org.wordpress.android.viewmodel.posts.PostListItemType> pagedListWrapper;
    private final androidx.lifecycle.MediatorLiveData<androidx.paging.PagedList<org.wordpress.android.viewmodel.posts.PostListItemType>> _pagedListData = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<androidx.paging.PagedList<org.wordpress.android.viewmodel.posts.PostListItemType>> pagedListData = null;
    private final org.wordpress.android.util.ThrottleLiveData<org.wordpress.android.viewmodel.posts.PostListEmptyUiState> _emptyViewState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.posts.PostListEmptyUiState> emptyViewState = null;
    private final androidx.lifecycle.MediatorLiveData<java.lang.Boolean> _isLoadingMore = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Boolean> isLoadingMore = null;
    private final androidx.lifecycle.MediatorLiveData<java.lang.Boolean> _isFetchingFirstPage = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Boolean> isFetchingFirstPage = null;
    private java.lang.String searchQuery;
    private kotlinx.coroutines.Job searchJob;
    private kotlinx.coroutines.Job searchProgressJob;
    private org.wordpress.android.ui.posts.AuthorFilterSelection authorFilterSelection;
    private final androidx.lifecycle.LifecycleOwner lifecycleOwner = null;
    
    @javax.inject.Inject()
    public PostListViewModel(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.Dispatcher dispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.ListStore listStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.PostStore postStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.AccountStore accountStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.posts.PostListItemUiStateHelper listItemUiStateHelper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.uploads.UploadStarter uploadStarter, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.utils.ReaderUtilsWrapper readerUtilsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.uploads.UploadUtilsWrapper uploadUtilsWrapper, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "UI_THREAD")
    kotlinx.coroutines.CoroutineDispatcher uiDispatcher, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher bgDispatcher, @org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.helpers.ConnectionStatus> connectionStatus) {
        super(null);
    }
    
    private final boolean isStatsSupported() {
        return false;
    }
    
    private final int getPhotonWidth() {
        return 0;
    }
    
    private final void setPhotonWidth(int p0) {
    }
    
    private final int getPhotonHeight() {
        return 0;
    }
    
    private final void setPhotonHeight(int p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Integer> getScrollToPosition() {
        return null;
    }
    
    private final org.wordpress.android.viewmodel.posts.PostListItemDataSource getDataSource() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<androidx.paging.PagedList<org.wordpress.android.viewmodel.posts.PostListItemType>> getPagedListData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.posts.PostListEmptyUiState> getEmptyViewState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Boolean> isLoadingMore() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Boolean> isFetchingFirstPage() {
        return null;
    }
    
    public final void start(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.posts.PostListViewModelConnector postListViewModelConnector, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.AuthorFilterSelection value, int photonWidth, int photonHeight) {
    }
    
    private final void initList(org.wordpress.android.viewmodel.posts.PostListItemDataSource dataSource, androidx.lifecycle.Lifecycle lifecycle) {
    }
    
    private final void clearLiveDataSources() {
    }
    
    private final org.wordpress.android.fluxc.model.list.PostListDescriptor initListDescriptor(java.lang.String searchQuery) {
        return null;
    }
    
    private final void listenToEmptyViewStateLiveData(org.wordpress.android.fluxc.model.list.PagedListWrapper<org.wordpress.android.viewmodel.posts.PostListItemType> pagedListWrapper) {
    }
    
    private final boolean isSearchResultDeliverable() {
        return false;
    }
    
    private final boolean isEmptySearch() {
        return false;
    }
    
    public final void search(@org.jetbrains.annotations.Nullable()
    java.lang.String query, long delay) {
    }
    
    private final void showEmptySearchPrompt() {
    }
    
    @java.lang.Override()
    protected void onCleared() {
    }
    
    public final void swipeToRefresh() {
    }
    
    public final void scrollToPost(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.posts.PostListItemIdentifier.LocalPostId localPostId) {
    }
    
    private final void fetchFirstPage() {
    }
    
    private final void onDataUpdated(androidx.paging.PagedList<org.wordpress.android.viewmodel.posts.PostListItemType> data) {
    }
    
    private final void updateScrollPosition(androidx.paging.PagedList<org.wordpress.android.viewmodel.posts.PostListItemType> data, org.wordpress.android.viewmodel.posts.PostListItemIdentifier.LocalPostId localPostId) {
    }
    
    private final java.lang.Integer findItemListPosition(androidx.paging.PagedList<org.wordpress.android.viewmodel.posts.PostListItemType> data, org.wordpress.android.viewmodel.posts.PostListItemIdentifier.LocalPostId localPostId) {
        return null;
    }
    
    private final org.wordpress.android.viewmodel.posts.PostListItemType.PostListItemUiState transformPostModelToPostListItemUiState(org.wordpress.android.fluxc.model.PostModel post) {
        return null;
    }
    
    private final void retryOnConnectionAvailableAfterRefreshError() {
    }
    
    private final java.lang.String convertToPhotonUrlIfPossible(java.lang.String featuredImageUrl) {
        return null;
    }
    
    public final boolean updateAuthorFilterIfNotSearch(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.AuthorFilterSelection authorFilterSelection) {
        return false;
    }
}