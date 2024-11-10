package fr.free.nrw.commons.customselector.listeners;

import java.lang.System;

/**
 * Custom Selector Image Loader Listener
 * responds to the device image query.
 */
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J \u0010\u0006\u001a\u00020\u00032\u0016\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\nH&\u00a8\u0006\u000b"}, d2 = {"Lfr/free/nrw/commons/customselector/listeners/ImageLoaderListener;", "", "onFailed", "", "throwable", "", "onImageLoaded", "images", "Ljava/util/ArrayList;", "Lfr/free/nrw/commons/customselector/model/Image;", "Lkotlin/collections/ArrayList;", "app-commons-v4.2.1-master_prodDebug"})
public abstract interface ImageLoaderListener {
    
    /**
     * On image loaded
     * @param images : queried device images.
     */
    public abstract void onImageLoaded(@org.jetbrains.annotations.NotNull
    java.util.ArrayList<fr.free.nrw.commons.customselector.model.Image> images);
    
    /**
     * On failed
     * @param throwable : throwable exception on failure.
     */
    public abstract void onFailed(@org.jetbrains.annotations.NotNull
    java.lang.Throwable throwable);
}