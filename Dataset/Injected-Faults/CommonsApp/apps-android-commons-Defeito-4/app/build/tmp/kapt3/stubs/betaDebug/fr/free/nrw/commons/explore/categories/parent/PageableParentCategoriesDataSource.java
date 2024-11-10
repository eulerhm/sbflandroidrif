package fr.free.nrw.commons.explore.categories.parent;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010 \n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR,\u0010\n\u001a\u001a\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\r0\u000bX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006\u0010"}, d2 = {"Lfr/free/nrw/commons/explore/categories/parent/PageableParentCategoriesDataSource;", "Lfr/free/nrw/commons/explore/paging/PageableBaseDataSource;", "", "liveDataConverter", "Lfr/free/nrw/commons/explore/paging/LiveDataConverter;", "categoryClient", "Lfr/free/nrw/commons/category/CategoryClient;", "(Lfr/free/nrw/commons/explore/paging/LiveDataConverter;Lfr/free/nrw/commons/category/CategoryClient;)V", "getCategoryClient", "()Lfr/free/nrw/commons/category/CategoryClient;", "loadFunction", "Lkotlin/Function2;", "", "", "getLoadFunction", "()Lkotlin/jvm/functions/Function2;", "app-commons-v4.2.1-main_betaDebug"})
public final class PageableParentCategoriesDataSource extends fr.free.nrw.commons.explore.paging.PageableBaseDataSource<java.lang.String> {
    @org.jetbrains.annotations.NotNull
    private final fr.free.nrw.commons.category.CategoryClient categoryClient = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.jvm.functions.Function2<java.lang.Integer, java.lang.Integer, java.util.List<java.lang.String>> loadFunction = null;
    
    @javax.inject.Inject
    public PageableParentCategoriesDataSource(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.explore.paging.LiveDataConverter liveDataConverter, @org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.category.CategoryClient categoryClient) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull
    public final fr.free.nrw.commons.category.CategoryClient getCategoryClient() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public kotlin.jvm.functions.Function2<java.lang.Integer, java.lang.Integer, java.util.List<java.lang.String>> getLoadFunction() {
        return null;
    }
}