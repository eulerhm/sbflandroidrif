package fr.free.nrw.commons.explore.depictions.media;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007R6\u0010\b\u001a$\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u000b0\tj\b\u0012\u0004\u0012\u00020\u0002`\fX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lfr/free/nrw/commons/explore/depictions/media/PageableDepictedMediaDataSource;", "Lfr/free/nrw/commons/explore/paging/PageableBaseDataSource;", "Lfr/free/nrw/commons/Media;", "liveDataConverter", "Lfr/free/nrw/commons/explore/paging/LiveDataConverter;", "wikiMediaClient", "Lfr/free/nrw/commons/media/WikidataMediaClient;", "(Lfr/free/nrw/commons/explore/paging/LiveDataConverter;Lfr/free/nrw/commons/media/WikidataMediaClient;)V", "loadFunction", "Lkotlin/Function2;", "", "", "Lfr/free/nrw/commons/explore/depictions/search/LoadFunction;", "getLoadFunction", "()Lkotlin/jvm/functions/Function2;", "app-commons-v4.2.1-main_betaDebug"})
public final class PageableDepictedMediaDataSource extends fr.free.nrw.commons.explore.paging.PageableBaseDataSource<fr.free.nrw.commons.Media> {
    private final fr.free.nrw.commons.media.WikidataMediaClient wikiMediaClient = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.jvm.functions.Function2<java.lang.Integer, java.lang.Integer, java.util.List<fr.free.nrw.commons.Media>> loadFunction = null;
    
    @javax.inject.Inject
    public PageableDepictedMediaDataSource(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.explore.paging.LiveDataConverter liveDataConverter, @org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.media.WikidataMediaClient wikiMediaClient) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public kotlin.jvm.functions.Function2<java.lang.Integer, java.lang.Integer, java.util.List<fr.free.nrw.commons.Media>> getLoadFunction() {
        return null;
    }
}