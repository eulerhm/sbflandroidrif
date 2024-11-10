package fr.free.nrw.commons.explore.depictions.parent;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010!\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007RJ\u0010\b\u001a8\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n\u0012(\u0012&\u0012\f\u0012\n \f*\u0004\u0018\u00010\u00020\u0002 \f*\u0012\u0012\f\u0012\n \f*\u0004\u0018\u00010\u00020\u0002\u0018\u00010\r0\u000b0\tX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lfr/free/nrw/commons/explore/depictions/parent/PageableParentDepictionsDataSource;", "Lfr/free/nrw/commons/explore/paging/PageableBaseDataSource;", "Lfr/free/nrw/commons/upload/structure/depictions/DepictedItem;", "liveDataConverter", "Lfr/free/nrw/commons/explore/paging/LiveDataConverter;", "okHttpJsonApiClient", "Lfr/free/nrw/commons/mwapi/OkHttpJsonApiClient;", "(Lfr/free/nrw/commons/explore/paging/LiveDataConverter;Lfr/free/nrw/commons/mwapi/OkHttpJsonApiClient;)V", "loadFunction", "Lkotlin/Function2;", "", "", "kotlin.jvm.PlatformType", "", "getLoadFunction", "()Lkotlin/jvm/functions/Function2;", "app-commons-v4.2.1-master_prodDebug"})
public final class PageableParentDepictionsDataSource extends fr.free.nrw.commons.explore.paging.PageableBaseDataSource<fr.free.nrw.commons.upload.structure.depictions.DepictedItem> {
    private final fr.free.nrw.commons.mwapi.OkHttpJsonApiClient okHttpJsonApiClient = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.jvm.functions.Function2<java.lang.Integer, java.lang.Integer, java.util.List<fr.free.nrw.commons.upload.structure.depictions.DepictedItem>> loadFunction = null;
    
    @javax.inject.Inject
    public PageableParentDepictionsDataSource(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.explore.paging.LiveDataConverter liveDataConverter, @org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.mwapi.OkHttpJsonApiClient okHttpJsonApiClient) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public kotlin.jvm.functions.Function2<java.lang.Integer, java.lang.Integer, java.util.List<fr.free.nrw.commons.upload.structure.depictions.DepictedItem>> getLoadFunction() {
        return null;
    }
}