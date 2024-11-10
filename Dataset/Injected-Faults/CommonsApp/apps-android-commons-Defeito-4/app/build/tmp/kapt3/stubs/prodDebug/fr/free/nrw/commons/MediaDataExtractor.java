package fr.free.nrw.commons;

import java.lang.System;

/**
 * Fetch additional media data from the network that we don't store locally.
 *
 *
 * This includes things like category lists and multilingual descriptions, which are not intrinsic
 * to the media and may change due to editing.
 */
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tJ(\u0010\n\u001a\u001c\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020\f \r*\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b0\u000b0\u00062\u0006\u0010\b\u001a\u00020\tJ\u001c\u0010\u000e\u001a\u0010\u0012\f\u0012\n \r*\u0004\u0018\u00010\u000f0\u000f0\u00062\u0006\u0010\b\u001a\u00020\tJ\u0016\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u00062\u0006\u0010\u0011\u001a\u00020\u000fJ\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00062\u0006\u0010\u0011\u001a\u00020\u000fJ\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\t0\u00062\u0006\u0010\b\u001a\u00020\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lfr/free/nrw/commons/MediaDataExtractor;", "", "mediaClient", "Lfr/free/nrw/commons/media/MediaClient;", "(Lfr/free/nrw/commons/media/MediaClient;)V", "checkDeletionRequestExists", "Lio/reactivex/Single;", "", "media", "Lfr/free/nrw/commons/Media;", "fetchDepictionIdsAndLabels", "", "Lfr/free/nrw/commons/media/IdAndCaptions;", "kotlin.jvm.PlatformType", "fetchDiscussion", "", "getCurrentWikiText", "title", "getHtmlOfPage", "refresh", "app-commons-v4.2.1-master_prodDebug"})
@javax.inject.Singleton
public final class MediaDataExtractor {
    private final fr.free.nrw.commons.media.MediaClient mediaClient = null;
    
    @javax.inject.Inject
    public MediaDataExtractor(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.media.MediaClient mediaClient) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final io.reactivex.Single<java.util.List<fr.free.nrw.commons.media.IdAndCaptions>> fetchDepictionIdsAndLabels(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.Media media) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final io.reactivex.Single<java.lang.Boolean> checkDeletionRequestExists(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.Media media) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final io.reactivex.Single<java.lang.String> fetchDiscussion(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.Media media) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final io.reactivex.Single<fr.free.nrw.commons.Media> refresh(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.Media media) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final io.reactivex.Single<java.lang.String> getHtmlOfPage(@org.jetbrains.annotations.NotNull
    java.lang.String title) {
        return null;
    }
    
    /**
     * Fetches wikitext from mediaClient
     */
    @org.jetbrains.annotations.NotNull
    public final io.reactivex.Single<java.lang.String> getCurrentWikiText(@org.jetbrains.annotations.NotNull
    java.lang.String title) {
        return null;
    }
}