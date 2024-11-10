package fr.free.nrw.commons.media;

import java.lang.System;

/**
 * Media Client to handle custom calls to Commons MediaWiki APIs of production server
 */
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ*\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\r0\f2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011J\u001a\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\f2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u000f0\rJ\"\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\r0\f2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\rH\u0002J,\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\r0\f2\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00020\f2\b\u0010\u001b\u001a\u0004\u0018\u00010\u000fH\u0016R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001c"}, d2 = {"Lfr/free/nrw/commons/media/WikidataMediaClient;", "Lfr/free/nrw/commons/category/ContinuationClient;", "Lorg/wikipedia/dataclient/mwapi/MwQueryResponse;", "Lfr/free/nrw/commons/Media;", "wikidataMediaInterface", "Lfr/free/nrw/commons/media/WikidataMediaInterface;", "mediaDetailInterface", "Lfr/free/nrw/commons/media/MediaDetailInterface;", "mediaConverter", "Lfr/free/nrw/commons/explore/media/MediaConverter;", "(Lfr/free/nrw/commons/media/WikidataMediaInterface;Lfr/free/nrw/commons/media/MediaDetailInterface;Lfr/free/nrw/commons/explore/media/MediaConverter;)V", "fetchImagesForDepictedItem", "Lio/reactivex/Single;", "", "query", "", "srlimit", "", "sroffset", "getEntities", "Lorg/wikipedia/wikidata/Entities;", "entityIds", "mediaFromPageAndEntity", "pages", "Lorg/wikipedia/dataclient/mwapi/MwQueryPage;", "responseMapper", "networkResult", "key", "app-commons-v4.2.1-main_betaDebug"})
@javax.inject.Singleton
public final class WikidataMediaClient extends fr.free.nrw.commons.category.ContinuationClient<org.wikipedia.dataclient.mwapi.MwQueryResponse, fr.free.nrw.commons.Media> {
    private final fr.free.nrw.commons.media.WikidataMediaInterface wikidataMediaInterface = null;
    private final fr.free.nrw.commons.media.MediaDetailInterface mediaDetailInterface = null;
    private final fr.free.nrw.commons.explore.media.MediaConverter mediaConverter = null;
    
    @javax.inject.Inject
    public WikidataMediaClient(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.media.WikidataMediaInterface wikidataMediaInterface, @org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.media.MediaDetailInterface mediaDetailInterface, @org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.explore.media.MediaConverter mediaConverter) {
        super();
    }
    
    /**
     * Fetch images for depict ID
     *
     * @param query depictionEntityId ex. "Q9394"
     * @param srlimit the number of items to fetch
     * @param sroffset number of depictions already fetched,
     *               this is useful in implementing pagination
     * @return list of images for a particular depict ID
     */
    @org.jetbrains.annotations.NotNull
    public final io.reactivex.Single<java.util.List<fr.free.nrw.commons.Media>> fetchImagesForDepictedItem(@org.jetbrains.annotations.NotNull
    java.lang.String query, int srlimit, int sroffset) {
        return null;
    }
    
    /**
     * Helps to map to the required data from the API response
     *
     * @param networkResult MwQueryResponse
     * @param key for handling continuation request, this is null in this case
     */
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public io.reactivex.Single<java.util.List<fr.free.nrw.commons.Media>> responseMapper(@org.jetbrains.annotations.NotNull
    io.reactivex.Single<org.wikipedia.dataclient.mwapi.MwQueryResponse> networkResult, @org.jetbrains.annotations.Nullable
    java.lang.String key) {
        return null;
    }
    
    /**
     * Gets list of Media from MwQueryPage
     */
    private final io.reactivex.Single<java.util.List<fr.free.nrw.commons.Media>> mediaFromPageAndEntity(java.util.List<? extends org.wikipedia.dataclient.mwapi.MwQueryPage> pages) {
        return null;
    }
    
    /**
     * Gets Entities from IDs
     *
     * @param entityIds list of IDs of pages/entities ex. {"M4254154", "M11413343"}
     */
    @org.jetbrains.annotations.NotNull
    public final io.reactivex.Single<org.wikipedia.wikidata.Entities> getEntities(@org.jetbrains.annotations.NotNull
    java.util.List<java.lang.String> entityIds) {
        return null;
    }
}