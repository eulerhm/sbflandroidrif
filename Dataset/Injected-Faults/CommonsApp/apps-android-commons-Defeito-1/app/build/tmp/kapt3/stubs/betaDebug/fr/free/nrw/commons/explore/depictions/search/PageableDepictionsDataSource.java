package fr.free.nrw.commons.explore.depictions.search;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010 \n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR:\u0010\n\u001a(\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020\u0002 \u000e*\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\r0\r0\u000bX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010\u00a8\u0006\u0011"}, d2 = {"Lfr/free/nrw/commons/explore/depictions/search/PageableDepictionsDataSource;", "Lfr/free/nrw/commons/explore/paging/PageableBaseDataSource;", "Lfr/free/nrw/commons/upload/structure/depictions/DepictedItem;", "liveDataConverter", "Lfr/free/nrw/commons/explore/paging/LiveDataConverter;", "depictsClient", "Lfr/free/nrw/commons/explore/depictions/DepictsClient;", "(Lfr/free/nrw/commons/explore/paging/LiveDataConverter;Lfr/free/nrw/commons/explore/depictions/DepictsClient;)V", "getDepictsClient", "()Lfr/free/nrw/commons/explore/depictions/DepictsClient;", "loadFunction", "Lkotlin/Function2;", "", "", "kotlin.jvm.PlatformType", "getLoadFunction", "()Lkotlin/jvm/functions/Function2;", "app-commons-v4.2.1-main_betaDebug"})
public final class PageableDepictionsDataSource extends fr.free.nrw.commons.explore.paging.PageableBaseDataSource<fr.free.nrw.commons.upload.structure.depictions.DepictedItem> {
    @org.jetbrains.annotations.NotNull
    private final fr.free.nrw.commons.explore.depictions.DepictsClient depictsClient = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.jvm.functions.Function2<java.lang.Integer, java.lang.Integer, java.util.List<fr.free.nrw.commons.upload.structure.depictions.DepictedItem>> loadFunction = null;
    
    @javax.inject.Inject
    public PageableDepictionsDataSource(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.explore.paging.LiveDataConverter liveDataConverter, @org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.explore.depictions.DepictsClient depictsClient) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull
    public final fr.free.nrw.commons.explore.depictions.DepictsClient getDepictsClient() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public kotlin.jvm.functions.Function2<java.lang.Integer, java.lang.Integer, java.util.List<fr.free.nrw.commons.upload.structure.depictions.DepictedItem>> getLoadFunction() {
        return null;
    }
}