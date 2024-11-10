package fr.free.nrw.commons.profile.achievements;

import java.lang.System;

/**
 * Represents Featured Images on WikiMedia Commons platform
 * Used by Achievements and FeedbackResponse (objects) of the user
 */
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0005R\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007\u00a8\u0006\t"}, d2 = {"Lfr/free/nrw/commons/profile/achievements/FeaturedImages;", "", "qualityImages", "", "featuredPicturesOnWikimediaCommons", "(II)V", "getFeaturedPicturesOnWikimediaCommons", "()I", "getQualityImages", "app-commons-v4.2.1-main_betaDebug"})
public final class FeaturedImages {
    @com.google.gson.annotations.SerializedName(value = "Quality_images")
    private final int qualityImages = 0;
    @com.google.gson.annotations.SerializedName(value = "Featured_pictures_on_Wikimedia_Commons")
    private final int featuredPicturesOnWikimediaCommons = 0;
    
    public FeaturedImages(int qualityImages, int featuredPicturesOnWikimediaCommons) {
        super();
    }
    
    public final int getQualityImages() {
        return 0;
    }
    
    public final int getFeaturedPicturesOnWikimediaCommons() {
        return 0;
    }
}