package fr.free.nrw.commons.media;

import java.lang.System;

/**
 * Interface for MediaWiki Page REST APIs
 */
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\'\u00a8\u0006\u0007"}, d2 = {"Lfr/free/nrw/commons/media/PageMediaInterface;", "", "getMediaList", "Lio/reactivex/Single;", "Lfr/free/nrw/commons/media/model/PageMediaListResponse;", "title", "", "app-commons-v4.2.1-main_betaDebug"})
public abstract interface PageMediaInterface {
    
    /**
     * Get a list of media used on a page
     *
     * @param title the title of the page
     */
    @org.jetbrains.annotations.NotNull
    @retrofit2.http.GET(value = "api/rest_v1/page/media-list/{title}")
    public abstract io.reactivex.Single<fr.free.nrw.commons.media.model.PageMediaListResponse> getMediaList(@org.jetbrains.annotations.Nullable
    @retrofit2.http.Path(value = "title")
    java.lang.String title);
}