package fr.free.nrw.commons.explore.paging;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J4\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\u00050\u0004\"\u0004\b\u0000\u0010\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00060\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u00a8\u0006\f"}, d2 = {"Lfr/free/nrw/commons/explore/paging/LiveDataConverter;", "", "()V", "convert", "Landroidx/lifecycle/LiveData;", "Landroidx/paging/PagedList;", "T", "dataSourceFactory", "Lfr/free/nrw/commons/explore/paging/PagingDataSourceFactory;", "zeroItemsLoadedFunction", "Lkotlin/Function0;", "", "app-commons-v4.2.1-main_betaDebug"})
public final class LiveDataConverter {
    
    @javax.inject.Inject
    public LiveDataConverter() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final <T extends java.lang.Object>androidx.lifecycle.LiveData<androidx.paging.PagedList<T>> convert(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.explore.paging.PagingDataSourceFactory<T> dataSourceFactory, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> zeroItemsLoadedFunction) {
        return null;
    }
}