package fr.free.nrw.commons.explore.categories;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b&\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001b\u0010\b\u001a\u00020\t8VX\u0096\u0084\u0002\u00a2\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u000e"}, d2 = {"Lfr/free/nrw/commons/explore/categories/PageableCategoryFragment;", "Lfr/free/nrw/commons/explore/paging/BasePagingFragment;", "", "()V", "errorTextId", "", "getErrorTextId", "()I", "pagedListAdapter", "Lfr/free/nrw/commons/explore/categories/PagedSearchCategoriesAdapter;", "getPagedListAdapter", "()Lfr/free/nrw/commons/explore/categories/PagedSearchCategoriesAdapter;", "pagedListAdapter$delegate", "Lkotlin/Lazy;", "app-commons-v4.2.1-main_betaDebug"})
public abstract class PageableCategoryFragment extends fr.free.nrw.commons.explore.paging.BasePagingFragment<java.lang.String> {
    private final int errorTextId = fr.free.nrw.commons.R.string.error_loading_categories;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy pagedListAdapter$delegate = null;
    private java.util.HashMap _$_findViewCache;
    
    public PageableCategoryFragment() {
        super();
    }
    
    @java.lang.Override
    public int getErrorTextId() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public fr.free.nrw.commons.explore.categories.PagedSearchCategoriesAdapter getPagedListAdapter() {
        return null;
    }
}