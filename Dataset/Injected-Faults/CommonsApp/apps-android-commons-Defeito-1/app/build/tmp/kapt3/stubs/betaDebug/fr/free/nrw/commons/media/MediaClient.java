package fr.free.nrw.commons.media;

import java.lang.System;

/**
 * Media Client to handle custom calls to Commons MediaWiki APIs
 */
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\'\b\u0007\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0002\u0010\fJ\u0016\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011J\u0016\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0011J\u0016\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0011J*\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00160\u000e2\u0006\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0019J\u0016\u0010\u001b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u000e2\u0006\u0010\u0013\u001a\u00020\u0011J\u001a\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001d0\u000e2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00110\u0016J\u0016\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00030\u000e2\b\u0010 \u001a\u0004\u0018\u00010\u0011J\u001c\u0010!\u001a\u0010\u0012\f\u0012\n \"*\u0004\u0018\u00010\u00030\u00030\u000e2\u0006\u0010#\u001a\u00020\u0011J\u001a\u0010$\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00160\u000e2\u0006\u0010%\u001a\u00020\u0011J\u001a\u0010&\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00160\u000e2\u0006\u0010\'\u001a\u00020\u0011J\u001c\u0010(\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00160\u000e2\b\u0010)\u001a\u0004\u0018\u00010\u0011J,\u0010*\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00160\u000e2\b\u0010+\u001a\u0004\u0018\u00010\u00112\u0006\u0010,\u001a\u00020\u00192\u0006\u0010-\u001a\u00020\u0019J\u0016\u0010.\u001a\b\u0012\u0004\u0012\u00020\u00110\u000e2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0011J\f\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00030\u000eJ\"\u00100\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00160\u000e2\f\u00101\u001a\b\u0012\u0004\u0012\u0002020\u0016H\u0002J\u000e\u00103\u001a\u0002042\u0006\u0010\'\u001a\u00020\u0011J\u000e\u00105\u001a\u0002042\u0006\u0010%\u001a\u00020\u0011J,\u00106\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00160\u000e2\f\u00107\u001a\b\u0012\u0004\u0012\u00020\u00020\u000e2\b\u00108\u001a\u0004\u0018\u00010\u0011H\u0016R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u00069"}, d2 = {"Lfr/free/nrw/commons/media/MediaClient;", "Lfr/free/nrw/commons/category/ContinuationClient;", "Lorg/wikipedia/dataclient/mwapi/MwQueryResponse;", "Lfr/free/nrw/commons/Media;", "mediaInterface", "Lfr/free/nrw/commons/media/MediaInterface;", "pageMediaInterface", "Lfr/free/nrw/commons/media/PageMediaInterface;", "mediaDetailInterface", "Lfr/free/nrw/commons/media/MediaDetailInterface;", "mediaConverter", "Lfr/free/nrw/commons/explore/media/MediaConverter;", "(Lfr/free/nrw/commons/media/MediaInterface;Lfr/free/nrw/commons/media/PageMediaInterface;Lfr/free/nrw/commons/media/MediaDetailInterface;Lfr/free/nrw/commons/explore/media/MediaConverter;)V", "checkFileExistsUsingSha", "Lio/reactivex/Single;", "", "fileSha", "", "checkPageExistsUsingTitle", "title", "doesPageContainMedia", "fetchImagesForDepictedItem", "", "query", "srlimit", "", "sroffset", "getCurrentWikiText", "getEntities", "Lorg/wikipedia/wikidata/Entities;", "entityIds", "getMedia", "titles", "getMediaById", "kotlin.jvm.PlatformType", "id", "getMediaListForUser", "userName", "getMediaListFromCategory", "category", "getMediaListFromGeoSearch", "coordinate", "getMediaListFromSearch", "keyword", "limit", "offset", "getPageHtml", "getPictureOfTheDay", "mediaFromPageAndEntity", "pages", "Lorg/wikipedia/dataclient/mwapi/MwQueryPage;", "resetCategoryContinuation", "", "resetUserNameContinuation", "responseMapper", "networkResult", "key", "app-commons-v4.2.1-main_betaDebug"})
@javax.inject.Singleton
public final class MediaClient extends fr.free.nrw.commons.category.ContinuationClient<org.wikipedia.dataclient.mwapi.MwQueryResponse, fr.free.nrw.commons.Media> {
    private final fr.free.nrw.commons.media.MediaInterface mediaInterface = null;
    private final fr.free.nrw.commons.media.PageMediaInterface pageMediaInterface = null;
    private final fr.free.nrw.commons.media.MediaDetailInterface mediaDetailInterface = null;
    private final fr.free.nrw.commons.explore.media.MediaConverter mediaConverter = null;
    
    @javax.inject.Inject
    public MediaClient(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.media.MediaInterface mediaInterface, @org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.media.PageMediaInterface pageMediaInterface, @org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.media.MediaDetailInterface mediaDetailInterface, @org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.explore.media.MediaConverter mediaConverter) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final io.reactivex.Single<fr.free.nrw.commons.Media> getMediaById(@org.jetbrains.annotations.NotNull
    java.lang.String id) {
        return null;
    }
    
    /**
     * Checks if a page exists on Commons
     * The same method can be used to check for file or talk page
     *
     * @param title File:Test.jpg or Commons:Deletion_requests/File:Test1.jpeg
     */
    @org.jetbrains.annotations.NotNull
    public final io.reactivex.Single<java.lang.Boolean> checkPageExistsUsingTitle(@org.jetbrains.annotations.Nullable
    java.lang.String title) {
        return null;
    }
    
    /**
     * Take the fileSha and returns whether a file with a matching SHA exists or not
     *
     * @param fileSha SHA of the file to be checked
     */
    @org.jetbrains.annotations.NotNull
    public final io.reactivex.Single<java.lang.Boolean> checkFileExistsUsingSha(@org.jetbrains.annotations.Nullable
    java.lang.String fileSha) {
        return null;
    }
    
    /**
     * This method takes the category as input and returns a list of  Media objects filtered using image generator query
     * It uses the generator query API to get the images searched using a query, 10 at a time.
     *
     * @param category the search category. Must start with "Category:"
     * @return
     */
    @org.jetbrains.annotations.NotNull
    public final io.reactivex.Single<java.util.List<fr.free.nrw.commons.Media>> getMediaListFromCategory(@org.jetbrains.annotations.NotNull
    java.lang.String category) {
        return null;
    }
    
    /**
     * This method takes the userName as input and returns a list of  Media objects filtered using
     * allimages query It uses the allimages query API to get the images contributed by the userName,
     * 10 at a time.
     *
     * @param userName the username
     * @return
     */
    @org.jetbrains.annotations.NotNull
    public final io.reactivex.Single<java.util.List<fr.free.nrw.commons.Media>> getMediaListForUser(@org.jetbrains.annotations.NotNull
    java.lang.String userName) {
        return null;
    }
    
    /**
     * This method takes a keyword as input and returns a list of  Media objects filtered using image generator query
     * It uses the generator query API to get the images searched using a query, 10 at a time.
     *
     * @param keyword the search keyword
     * @param limit
     * @param offset
     * @return
     */
    @org.jetbrains.annotations.NotNull
    public final io.reactivex.Single<java.util.List<fr.free.nrw.commons.Media>> getMediaListFromSearch(@org.jetbrains.annotations.Nullable
    java.lang.String keyword, int limit, int offset) {
        return null;
    }
    
    /**
     * This method takes coordinate as input and returns a list of  Media objects.
     * It uses the generator query API to get the images searched using a query.
     *
     * @param coordinate coordinate
     * @return
     */
    @org.jetbrains.annotations.NotNull
    public final io.reactivex.Single<java.util.List<fr.free.nrw.commons.Media>> getMediaListFromGeoSearch(@org.jetbrains.annotations.Nullable
    java.lang.String coordinate) {
        return null;
    }
    
    /**
     * @return list of images for a particular depict entity
     */
    @org.jetbrains.annotations.NotNull
    public final io.reactivex.Single<java.util.List<fr.free.nrw.commons.Media>> fetchImagesForDepictedItem(@org.jetbrains.annotations.NotNull
    java.lang.String query, int srlimit, int sroffset) {
        return null;
    }
    
    /**
     * Fetches Media object from the imageInfo API
     *
     * @param titles the tiles to be searched for. Can be filename or template name
     * @return
     */
    @org.jetbrains.annotations.NotNull
    public final io.reactivex.Single<fr.free.nrw.commons.Media> getMedia(@org.jetbrains.annotations.Nullable
    java.lang.String titles) {
        return null;
    }
    
    /**
     * The method returns the picture of the day
     *
     * @return Media object corresponding to the picture of the day
     */
    @org.jetbrains.annotations.NotNull
    public final io.reactivex.Single<fr.free.nrw.commons.Media> getPictureOfTheDay() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final io.reactivex.Single<java.lang.String> getPageHtml(@org.jetbrains.annotations.Nullable
    java.lang.String title) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final io.reactivex.Single<org.wikipedia.wikidata.Entities> getEntities(@org.jetbrains.annotations.NotNull
    java.util.List<java.lang.String> entityIds) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final io.reactivex.Single<java.lang.Boolean> doesPageContainMedia(@org.jetbrains.annotations.Nullable
    java.lang.String title) {
        return null;
    }
    
    public final void resetCategoryContinuation(@org.jetbrains.annotations.NotNull
    java.lang.String category) {
    }
    
    /**
     * Call the resetUserContinuation method
     *
     * @param userName the username
     */
    public final void resetUserNameContinuation(@org.jetbrains.annotations.NotNull
    java.lang.String userName) {
    }
    
    /**
     * Get whole WikiText of required file
     * @param title : Name of the file
     * @return Observable<MwQueryResult>
     */
    @org.jetbrains.annotations.NotNull
    public final io.reactivex.Single<java.lang.String> getCurrentWikiText(@org.jetbrains.annotations.NotNull
    java.lang.String title) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public io.reactivex.Single<java.util.List<fr.free.nrw.commons.Media>> responseMapper(@org.jetbrains.annotations.NotNull
    io.reactivex.Single<org.wikipedia.dataclient.mwapi.MwQueryResponse> networkResult, @org.jetbrains.annotations.Nullable
    java.lang.String key) {
        return null;
    }
    
    private final io.reactivex.Single<java.util.List<fr.free.nrw.commons.Media>> mediaFromPageAndEntity(java.util.List<? extends org.wikipedia.dataclient.mwapi.MwQueryPage> pages) {
        return null;
    }
}