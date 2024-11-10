package fr.free.nrw.commons.customselector.helper;

import java.lang.System;

/**
 * Image Helper object, includes all the static functions and variables required by custom selector.
 */
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J=\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0007j\b\u0012\u0004\u0012\u00020\b`\t2\u0016\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0007j\b\u0012\u0004\u0012\u00020\b`\t2\b\u0010\u000b\u001a\u0004\u0018\u00010\f\u00a2\u0006\u0002\u0010\rJ$\u0010\u000e\u001a\u0012\u0012\u0004\u0012\u00020\u000f0\u0007j\b\u0012\u0004\u0012\u00020\u000f`\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\b0\u0010J&\u0010\u0011\u001a\u00020\u00122\u0016\u0010\u0013\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0007j\b\u0012\u0004\u0012\u00020\b`\t2\u0006\u0010\u0014\u001a\u00020\bJ&\u0010\u0015\u001a\u00020\u00122\u0016\u0010\u0013\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0007j\b\u0012\u0004\u0012\u00020\b`\t2\u0006\u0010\u0016\u001a\u00020\fJF\u0010\u0017\u001a\u0012\u0012\u0004\u0012\u00020\u00120\u0007j\b\u0012\u0004\u0012\u00020\u0012`\t2\u0016\u0010\u0013\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0007j\b\u0012\u0004\u0012\u00020\b`\t2\u0016\u0010\u0018\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0007j\b\u0012\u0004\u0012\u00020\b`\tR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0019"}, d2 = {"Lfr/free/nrw/commons/customselector/helper/ImageHelper;", "", "()V", "CUSTOM_SELECTOR_PREFERENCE_KEY", "", "SHOW_ALREADY_ACTIONED_IMAGES_PREFERENCE_KEY", "filterImages", "Ljava/util/ArrayList;", "Lfr/free/nrw/commons/customselector/model/Image;", "Lkotlin/collections/ArrayList;", "images", "bukketId", "", "(Ljava/util/ArrayList;Ljava/lang/Long;)Ljava/util/ArrayList;", "folderListFromImages", "Lfr/free/nrw/commons/customselector/model/Folder;", "", "getIndex", "", "list", "image", "getIndexFromId", "imageId", "getIndexList", "masterList", "app-commons-v4.2.1-main_betaDebug"})
public final class ImageHelper {
    @org.jetbrains.annotations.NotNull
    public static final fr.free.nrw.commons.customselector.helper.ImageHelper INSTANCE = null;
    
    /**
     * Custom selector preference key
     */
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String CUSTOM_SELECTOR_PREFERENCE_KEY = "custom_selector";
    
    /**
     * Switch state preference key
     */
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String SHOW_ALREADY_ACTIONED_IMAGES_PREFERENCE_KEY = "show_already_actioned_images";
    
    private ImageHelper() {
        super();
    }
    
    /**
     * Returns the list of folders from given image list.
     */
    @org.jetbrains.annotations.NotNull
    public final java.util.ArrayList<fr.free.nrw.commons.customselector.model.Folder> folderListFromImages(@org.jetbrains.annotations.NotNull
    java.util.List<fr.free.nrw.commons.customselector.model.Image> images) {
        return null;
    }
    
    /**
     * Filters the images based on the given bucketId (folder)
     */
    @org.jetbrains.annotations.NotNull
    public final java.util.ArrayList<fr.free.nrw.commons.customselector.model.Image> filterImages(@org.jetbrains.annotations.NotNull
    java.util.ArrayList<fr.free.nrw.commons.customselector.model.Image> images, @org.jetbrains.annotations.Nullable
    java.lang.Long bukketId) {
        return null;
    }
    
    /**
     * getIndex: Returns the index of image in given list.
     */
    public final int getIndex(@org.jetbrains.annotations.NotNull
    java.util.ArrayList<fr.free.nrw.commons.customselector.model.Image> list, @org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.customselector.model.Image image) {
        return 0;
    }
    
    /**
     * getIndex: Returns the index of image in given list.
     */
    public final int getIndexFromId(@org.jetbrains.annotations.NotNull
    java.util.ArrayList<fr.free.nrw.commons.customselector.model.Image> list, long imageId) {
        return 0;
    }
    
    /**
     * Gets the list of indices from the master list.
     */
    @org.jetbrains.annotations.NotNull
    public final java.util.ArrayList<java.lang.Integer> getIndexList(@org.jetbrains.annotations.NotNull
    java.util.ArrayList<fr.free.nrw.commons.customselector.model.Image> list, @org.jetbrains.annotations.NotNull
    java.util.ArrayList<fr.free.nrw.commons.customselector.model.Image> masterList) {
        return null;
    }
}