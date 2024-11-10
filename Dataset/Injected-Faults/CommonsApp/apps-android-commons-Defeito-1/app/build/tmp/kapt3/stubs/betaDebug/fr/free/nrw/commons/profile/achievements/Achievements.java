package fr.free.nrw.commons.profile.achievements;

import java.lang.System;

/**
 * Represents Achievements class and stores all the parameters
 */
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0017\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\u0007\b\u0016\u00a2\u0006\u0002\u0010\u0002B?\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0004\u0012\u0006\u0010\b\u001a\u00020\u0004\u0012\u0006\u0010\t\u001a\u00020\u0004\u0012\u0006\u0010\n\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u000bR\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\t\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\r\"\u0004\b\u0011\u0010\u000fR\u0011\u0010\u0012\u001a\u00020\u00048F\u00a2\u0006\u0006\u001a\u0004\b\u0013\u0010\rR\u001a\u0010\b\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\r\"\u0004\b\u0015\u0010\u000fR\u000e\u0010\n\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\r\"\u0004\b\u0017\u0010\u000fR\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\r\"\u0004\b\u0019\u0010\u000f\u00a8\u0006\u001b"}, d2 = {"Lfr/free/nrw/commons/profile/achievements/Achievements;", "", "()V", "uniqueUsedImages", "", "articlesUsingImages", "thanksReceived", "featuredImages", "qualityImages", "imagesUploaded", "revertCount", "(IIIIIII)V", "getFeaturedImages", "()I", "setFeaturedImages", "(I)V", "getImagesUploaded", "setImagesUploaded", "notRevertPercentage", "getNotRevertPercentage", "getQualityImages", "setQualityImages", "getThanksReceived", "setThanksReceived", "getUniqueUsedImages", "setUniqueUsedImages", "Companion", "app-commons-v4.2.1-main_betaDebug"})
public final class Achievements {
    
    /**
     * setter function to set count of uniques images used by wiki
     * @param uniqueUsedImages
     */
    private int uniqueUsedImages = 0;
    private int articlesUsingImages = 0;
    
    /**
     * setter function to set count of thanks received
     * @param thanksReceived
     */
    private int thanksReceived = 0;
    
    /**
     * setter function to set count of featured images
     * @param featuredImages
     */
    private int featuredImages = 0;
    
    /**
     * setter function to set count of featured images
     * @param featuredImages
     */
    private int qualityImages = 0;
    
    /**
     * setter function to count of images uploaded
     * @param imagesUploaded
     */
    private int imagesUploaded = 0;
    private int revertCount = 0;
    @org.jetbrains.annotations.NotNull
    public static final fr.free.nrw.commons.profile.achievements.Achievements.Companion Companion = null;
    
    public final int getUniqueUsedImages() {
        return 0;
    }
    
    public final void setUniqueUsedImages(int p0) {
    }
    
    public final int getThanksReceived() {
        return 0;
    }
    
    public final void setThanksReceived(int p0) {
    }
    
    public final int getFeaturedImages() {
        return 0;
    }
    
    public final void setFeaturedImages(int p0) {
    }
    
    public final int getQualityImages() {
        return 0;
    }
    
    public final void setQualityImages(int p0) {
    }
    
    public final int getImagesUploaded() {
        return 0;
    }
    
    public final void setImagesUploaded(int p0) {
    }
    
    public Achievements() {
        super();
    }
    
    /**
     * constructor for achievements class to set its data members
     * @param uniqueUsedImages
     * @param articlesUsingImages
     * @param thanksReceived
     * @param featuredImages
     * @param imagesUploaded
     * @param revertCount
     */
    public Achievements(int uniqueUsedImages, int articlesUsingImages, int thanksReceived, int featuredImages, int qualityImages, int imagesUploaded, int revertCount) {
        super();
    }
    
    public final int getNotRevertPercentage() {
        return 0;
    }
    
    /**
     * Get Achievements object from FeedbackResponse
     *
     * @param response
     * @return
     */
    @org.jetbrains.annotations.NotNull
    @kotlin.jvm.JvmStatic
    public static final fr.free.nrw.commons.profile.achievements.Achievements from(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.profile.achievements.FeedbackResponse response) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007\u00a8\u0006\u0007"}, d2 = {"Lfr/free/nrw/commons/profile/achievements/Achievements$Companion;", "", "()V", "from", "Lfr/free/nrw/commons/profile/achievements/Achievements;", "response", "Lfr/free/nrw/commons/profile/achievements/FeedbackResponse;", "app-commons-v4.2.1-main_betaDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        /**
         * Get Achievements object from FeedbackResponse
         *
         * @param response
         * @return
         */
        @org.jetbrains.annotations.NotNull
        @kotlin.jvm.JvmStatic
        public final fr.free.nrw.commons.profile.achievements.Achievements from(@org.jetbrains.annotations.NotNull
        fr.free.nrw.commons.profile.achievements.FeedbackResponse response) {
            return null;
        }
    }
}