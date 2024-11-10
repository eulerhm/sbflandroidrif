package fr.free.nrw.commons.explore.paging;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001:\u0002\u0002\u0003\u00a8\u0006\u0004"}, d2 = {"Lfr/free/nrw/commons/explore/paging/PagingContract;", "", "Presenter", "View", "app-commons-v4.2.1-master_prodDebug"})
public abstract interface PagingContract {
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0004H&J\u001c\u0010\u0006\u001a\u00020\u00042\u0012\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\t0\bH&J\u0010\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\fH&J\b\u0010\r\u001a\u00020\u0004H&J\b\u0010\u000e\u001a\u00020\u0004H&\u00a8\u0006\u000f"}, d2 = {"Lfr/free/nrw/commons/explore/paging/PagingContract$View;", "T", "", "hideEmptyText", "", "hideInitialLoadProgress", "observePagingResults", "searchResults", "Landroidx/lifecycle/LiveData;", "Landroidx/paging/PagedList;", "showEmptyText", "query", "", "showInitialLoadInProgress", "showSnackbar", "app-commons-v4.2.1-master_prodDebug"})
    public static abstract interface View<T extends java.lang.Object> {
        
        public abstract void showSnackbar();
        
        public abstract void observePagingResults(@org.jetbrains.annotations.NotNull
        androidx.lifecycle.LiveData<androidx.paging.PagedList<T>> searchResults);
        
        public abstract void showInitialLoadInProgress();
        
        public abstract void hideInitialLoadProgress();
        
        public abstract void showEmptyText(@org.jetbrains.annotations.NotNull
        java.lang.String query);
        
        public abstract void hideEmptyText();
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00030\u0002J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH&J\b\u0010\u000e\u001a\u00020\u000bH&R\u001e\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u0005X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\b\u0010\t\u00a8\u0006\u000f"}, d2 = {"Lfr/free/nrw/commons/explore/paging/PagingContract$Presenter;", "T", "Lfr/free/nrw/commons/BasePresenter;", "Lfr/free/nrw/commons/explore/paging/PagingContract$View;", "listFooterData", "Landroidx/lifecycle/LiveData;", "", "Lfr/free/nrw/commons/explore/paging/FooterItem;", "getListFooterData", "()Landroidx/lifecycle/LiveData;", "onQueryUpdated", "", "query", "", "retryFailedRequest", "app-commons-v4.2.1-master_prodDebug"})
    public static abstract interface Presenter<T extends java.lang.Object> extends fr.free.nrw.commons.BasePresenter<fr.free.nrw.commons.explore.paging.PagingContract.View<T>> {
        
        @org.jetbrains.annotations.NotNull
        public abstract androidx.lifecycle.LiveData<java.util.List<fr.free.nrw.commons.explore.paging.FooterItem>> getListFooterData();
        
        public abstract void onQueryUpdated(@org.jetbrains.annotations.NotNull
        java.lang.String query);
        
        public abstract void retryFailedRequest();
    }
}