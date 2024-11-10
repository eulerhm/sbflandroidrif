package org.wordpress.android.viewmodel.history;

import java.lang.System;

@kotlin.Suppress(names = {"TooManyFunctions"})
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u009e\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0002BCBI\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\b\u0001\u0010\n\u001a\u00020\u000b\u0012\b\b\u0001\u0010\f\u001a\u00020\u000b\u0012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e\u00a2\u0006\u0002\u0010\u0010J\u0016\u0010.\u001a\u00020/2\u0006\u00100\u001a\u0002012\u0006\u0010,\u001a\u00020-J\u0016\u00102\u001a\u00020/2\f\u0010%\u001a\b\u0012\u0004\u0012\u0002030\u0017H\u0002J\u0016\u00104\u001a\u00020/2\f\u00105\u001a\b\u0012\u0004\u0012\u0002060\u0017H\u0002J\b\u00107\u001a\u00020/H\u0002J\u001c\u00108\u001a\b\u0012\u0004\u0012\u00020\u00180\u00172\f\u0010%\u001a\b\u0012\u0004\u0012\u0002030\u0017H\u0002J\b\u00109\u001a\u00020/H\u0014J\u000e\u0010:\u001a\u00020/2\u0006\u0010;\u001a\u00020\u0018J\u0006\u0010<\u001a\u00020/J\u0010\u0010=\u001a\u00020/2\u0006\u0010>\u001a\u00020?H\u0007J\u0010\u0010@\u001a\u00020/2\u0006\u0010#\u001a\u00020\u0015H\u0002J\u001e\u0010A\u001a\u00020/2\u0006\u0010#\u001a\u00020\u00152\f\u0010%\u001a\b\u0012\u0004\u0012\u0002030\u0017H\u0002R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0014\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00150\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u00170\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001aX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00130\u000e8F\u00a2\u0006\u0006\u001a\u0004\b!\u0010\"R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010#\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00150\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010\"R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010%\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u00170\u000e8F\u00a2\u0006\u0006\u001a\u0004\b&\u0010\"R\u0014\u0010\'\u001a\b\u0012\u0004\u0012\u00020)0(X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010*\u001a\b\u0012\u0004\u0012\u00020\u001b0\u000e8F\u00a2\u0006\u0006\u001a\u0004\b+\u0010\"R\u000e\u0010,\u001a\u00020-X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006D"}, d2 = {"Lorg/wordpress/android/viewmodel/history/HistoryViewModel;", "Lorg/wordpress/android/viewmodel/ScopedViewModel;", "dispatcher", "Lorg/wordpress/android/fluxc/Dispatcher;", "resourceProvider", "Lorg/wordpress/android/viewmodel/ResourceProvider;", "networkUtils", "Lorg/wordpress/android/util/NetworkUtilsWrapper;", "postStore", "Lorg/wordpress/android/fluxc/store/PostStore;", "uiDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "bgDispatcher", "connectionStatus", "Landroidx/lifecycle/LiveData;", "Lorg/wordpress/android/viewmodel/helpers/ConnectionStatus;", "(Lorg/wordpress/android/fluxc/Dispatcher;Lorg/wordpress/android/viewmodel/ResourceProvider;Lorg/wordpress/android/util/NetworkUtilsWrapper;Lorg/wordpress/android/fluxc/store/PostStore;Lkotlinx/coroutines/CoroutineDispatcher;Lkotlinx/coroutines/CoroutineDispatcher;Landroidx/lifecycle/LiveData;)V", "_listStatus", "Landroidx/lifecycle/MutableLiveData;", "Lorg/wordpress/android/viewmodel/history/HistoryViewModel$HistoryListStatus;", "_post", "Lorg/wordpress/android/fluxc/model/PostModel;", "_revisions", "", "Lorg/wordpress/android/ui/history/HistoryListItem;", "_showDialog", "Lorg/wordpress/android/viewmodel/SingleLiveEvent;", "Lorg/wordpress/android/viewmodel/history/HistoryViewModel$ShowDialogEvent;", "isStarted", "", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "listStatus", "getListStatus", "()Landroidx/lifecycle/LiveData;", "post", "getPost", "revisions", "getRevisions", "revisionsList", "", "Lorg/wordpress/android/ui/history/HistoryListItem$Revision;", "showDialog", "getShowDialog", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "create", "", "localPostId", "", "createRevisionsList", "Lorg/wordpress/android/fluxc/model/revisions/RevisionModel;", "fetchRevisionAuthorDetails", "authorsId", "", "fetchRevisions", "getHistoryListItemsFromRevisionModels", "onCleared", "onItemClicked", "item", "onPullToRefresh", "onRevisionsFetched", "event", "Lorg/wordpress/android/fluxc/store/PostStore$OnRevisionsFetched;", "removeRevisionsFromLocalDB", "saveRevisionsToLocalDB", "HistoryListStatus", "ShowDialogEvent", "org.wordpress.android_wordpressJalapenoDebug"})
public final class HistoryViewModel extends org.wordpress.android.viewmodel.ScopedViewModel {
    private final org.wordpress.android.fluxc.Dispatcher dispatcher = null;
    private final org.wordpress.android.viewmodel.ResourceProvider resourceProvider = null;
    private final org.wordpress.android.util.NetworkUtilsWrapper networkUtils = null;
    private final org.wordpress.android.fluxc.store.PostStore postStore = null;
    private final kotlinx.coroutines.CoroutineDispatcher bgDispatcher = null;
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.helpers.ConnectionStatus> connectionStatus = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.history.HistoryViewModel.HistoryListStatus> _listStatus = null;
    private final androidx.lifecycle.MutableLiveData<java.util.List<org.wordpress.android.ui.history.HistoryListItem>> _revisions = null;
    private final org.wordpress.android.viewmodel.SingleLiveEvent<org.wordpress.android.viewmodel.history.HistoryViewModel.ShowDialogEvent> _showDialog = null;
    private boolean isStarted = false;
    private final java.util.List<org.wordpress.android.ui.history.HistoryListItem.Revision> revisionsList = null;
    private org.wordpress.android.fluxc.model.SiteModel site;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.fluxc.model.PostModel> _post = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.fluxc.model.PostModel> post = null;
    private final androidx.lifecycle.LifecycleOwner lifecycleOwner = null;
    
    @javax.inject.Inject()
    public HistoryViewModel(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.Dispatcher dispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.ResourceProvider resourceProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.NetworkUtilsWrapper networkUtils, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.PostStore postStore, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "UI_THREAD")
    kotlinx.coroutines.CoroutineDispatcher uiDispatcher, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher bgDispatcher, @org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.helpers.ConnectionStatus> connectionStatus) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.history.HistoryViewModel.HistoryListStatus> getListStatus() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<org.wordpress.android.ui.history.HistoryListItem>> getRevisions() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.history.HistoryViewModel.ShowDialogEvent> getShowDialog() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.fluxc.model.PostModel> getPost() {
        return null;
    }
    
    public final void create(int localPostId, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site) {
    }
    
    private final void createRevisionsList(java.util.List<org.wordpress.android.fluxc.model.revisions.RevisionModel> revisions) {
    }
    
    private final void fetchRevisionAuthorDetails(java.util.List<java.lang.String> authorsId) {
    }
    
    private final void fetchRevisions() {
    }
    
    private final java.util.List<org.wordpress.android.ui.history.HistoryListItem> getHistoryListItemsFromRevisionModels(java.util.List<org.wordpress.android.fluxc.model.revisions.RevisionModel> revisions) {
        return null;
    }
    
    @java.lang.Override()
    protected void onCleared() {
    }
    
    public final void onItemClicked(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.history.HistoryListItem item) {
    }
    
    public final void onPullToRefresh() {
    }
    
    private final void saveRevisionsToLocalDB(org.wordpress.android.fluxc.model.PostModel post, java.util.List<org.wordpress.android.fluxc.model.revisions.RevisionModel> revisions) {
    }
    
    private final void removeRevisionsFromLocalDB(org.wordpress.android.fluxc.model.PostModel post) {
    }
    
    @java.lang.SuppressWarnings(value = {"unused"})
    @org.greenrobot.eventbus.Subscribe(threadMode = org.greenrobot.eventbus.ThreadMode.MAIN)
    public final void onRevisionsFetched(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.PostStore.OnRevisionsFetched event) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/viewmodel/history/HistoryViewModel$HistoryListStatus;", "", "(Ljava/lang/String;I)V", "DONE", "ERROR", "NO_NETWORK", "FETCHING", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum HistoryListStatus {
        /*public static final*/ DONE /* = new DONE() */,
        /*public static final*/ ERROR /* = new ERROR() */,
        /*public static final*/ NO_NETWORK /* = new NO_NETWORK() */,
        /*public static final*/ FETCHING /* = new FETCHING() */;
        
        HistoryListStatus() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\u0002\u0010\u0007J\t\u0010\f\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0003J#\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0012\u001a\u00020\u0013H\u00d6\u0001J\t\u0010\u0014\u001a\u00020\u0015H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0016"}, d2 = {"Lorg/wordpress/android/viewmodel/history/HistoryViewModel$ShowDialogEvent;", "", "historyListItem", "Lorg/wordpress/android/ui/history/HistoryListItem;", "revisionsList", "", "Lorg/wordpress/android/ui/history/HistoryListItem$Revision;", "(Lorg/wordpress/android/ui/history/HistoryListItem;Ljava/util/List;)V", "getHistoryListItem", "()Lorg/wordpress/android/ui/history/HistoryListItem;", "getRevisionsList", "()Ljava/util/List;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class ShowDialogEvent {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.history.HistoryListItem historyListItem = null;
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<org.wordpress.android.ui.history.HistoryListItem.Revision> revisionsList = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.viewmodel.history.HistoryViewModel.ShowDialogEvent copy(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.history.HistoryListItem historyListItem, @org.jetbrains.annotations.NotNull()
        java.util.List<org.wordpress.android.ui.history.HistoryListItem.Revision> revisionsList) {
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
        
        public ShowDialogEvent(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.history.HistoryListItem historyListItem, @org.jetbrains.annotations.NotNull()
        java.util.List<org.wordpress.android.ui.history.HistoryListItem.Revision> revisionsList) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.history.HistoryListItem component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.history.HistoryListItem getHistoryListItem() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.ui.history.HistoryListItem.Revision> component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.ui.history.HistoryListItem.Revision> getRevisionsList() {
            return null;
        }
    }
}