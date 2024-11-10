package fr.free.nrw.commons.explore.media;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u0012\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\fH\u0002\u00a8\u0006\u0012"}, d2 = {"Lfr/free/nrw/commons/explore/media/MediaConverter;", "", "()V", "convert", "Lfr/free/nrw/commons/Media;", "page", "Lorg/wikipedia/dataclient/mwapi/MwQueryPage;", "entity", "Lorg/wikipedia/wikidata/Entities$Entity;", "imageInfo", "Lorg/wikipedia/gallery/ImageInfo;", "getAuthor", "", "metadata", "Lorg/wikipedia/gallery/ExtMetadata;", "safeParseDate", "Ljava/util/Date;", "dateStr", "app-commons-v4.2.1-main_betaDebug"})
public final class MediaConverter {
    
    @javax.inject.Inject
    public MediaConverter() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final fr.free.nrw.commons.Media convert(@org.jetbrains.annotations.NotNull
    org.wikipedia.dataclient.mwapi.MwQueryPage page, @org.jetbrains.annotations.NotNull
    org.wikipedia.wikidata.Entities.Entity entity, @org.jetbrains.annotations.NotNull
    org.wikipedia.gallery.ImageInfo imageInfo) {
        return null;
    }
    
    /**
     * Creating Media object from MWQueryPage.
     * Earlier only basic details were set for the media object but going forward,
     * a full media object(with categories, descriptions, coordinates etc) can be constructed using this method
     *
     * @param page response from the API
     * @return Media object
     */
    private final java.util.Date safeParseDate(java.lang.String dateStr) {
        return null;
    }
    
    /**
     * This method extracts the Commons Username from the artist HTML information
     * @param metadata
     * @return
     */
    private final java.lang.String getAuthor(org.wikipedia.gallery.ExtMetadata metadata) {
        return null;
    }
}