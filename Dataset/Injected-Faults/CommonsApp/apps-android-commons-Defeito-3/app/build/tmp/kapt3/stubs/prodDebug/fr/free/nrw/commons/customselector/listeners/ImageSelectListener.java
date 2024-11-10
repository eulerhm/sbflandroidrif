package fr.free.nrw.commons.customselector.listeners;

import java.lang.System;

/**
 * Custom selector Image select listener
 */
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J@\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0016\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0007j\b\u0012\u0004\u0012\u00020\b`\t2\u0016\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0007j\b\u0012\u0004\u0012\u00020\b`\tH&J(\u0010\u000b\u001a\u00020\u00032\u0016\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0007j\b\u0012\u0004\u0012\u00020\b`\t2\u0006\u0010\f\u001a\u00020\u0005H&\u00a8\u0006\r"}, d2 = {"Lfr/free/nrw/commons/customselector/listeners/ImageSelectListener;", "", "onLongPress", "", "position", "", "images", "Ljava/util/ArrayList;", "Lfr/free/nrw/commons/customselector/model/Image;", "Lkotlin/collections/ArrayList;", "selectedImages", "onSelectedImagesChanged", "selectedNotForUploadImages", "app-commons-v4.2.1-master_prodDebug"})
public abstract interface ImageSelectListener {
    
    /**
     * onSelectedImagesChanged
     * @param selectedImages : new selected images.
     * @param selectedNotForUploadImages : number of selected not for upload images
     */
    public abstract void onSelectedImagesChanged(@org.jetbrains.annotations.NotNull
    java.util.ArrayList<fr.free.nrw.commons.customselector.model.Image> selectedImages, int selectedNotForUploadImages);
    
    /**
     * onLongPress
     * @param imageUri : uri of image
     */
    public abstract void onLongPress(int position, @org.jetbrains.annotations.NotNull
    java.util.ArrayList<fr.free.nrw.commons.customselector.model.Image> images, @org.jetbrains.annotations.NotNull
    java.util.ArrayList<fr.free.nrw.commons.customselector.model.Image> selectedImages);
}