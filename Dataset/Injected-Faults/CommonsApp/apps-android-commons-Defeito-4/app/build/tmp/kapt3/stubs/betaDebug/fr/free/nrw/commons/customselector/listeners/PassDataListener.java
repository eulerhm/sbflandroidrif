package fr.free.nrw.commons.customselector.listeners;

import java.lang.System;

/**
 * Interface to pass data between fragment and activity
 */
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J(\u0010\u0002\u001a\u00020\u00032\u0016\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u00072\u0006\u0010\b\u001a\u00020\tH&\u00a8\u0006\n"}, d2 = {"Lfr/free/nrw/commons/customselector/listeners/PassDataListener;", "", "passSelectedImages", "", "selectedImages", "Ljava/util/ArrayList;", "Lfr/free/nrw/commons/customselector/model/Image;", "Lkotlin/collections/ArrayList;", "shouldRefresh", "", "app-commons-v4.2.1-main_betaDebug"})
public abstract interface PassDataListener {
    
    public abstract void passSelectedImages(@org.jetbrains.annotations.NotNull
    java.util.ArrayList<fr.free.nrw.commons.customselector.model.Image> selectedImages, boolean shouldRefresh);
}