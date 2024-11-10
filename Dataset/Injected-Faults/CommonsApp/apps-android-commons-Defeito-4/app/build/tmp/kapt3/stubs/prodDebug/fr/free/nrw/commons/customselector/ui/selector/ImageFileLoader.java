package fr.free.nrw.commons.customselector.ui.selector;

import java.lang.System;

/**
 * Custom Selector Image File Loader.
 * Loads device images.
 */
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u000f\u001a\u00020\u0010J\u0010\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u000e\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0013R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0016\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u000e\u00a8\u0006\u0015"}, d2 = {"Lfr/free/nrw/commons/customselector/ui/selector/ImageFileLoader;", "Lkotlinx/coroutines/CoroutineScope;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "projection", "", "", "[Ljava/lang/String;", "abortLoadImage", "", "getImages", "listener", "Lfr/free/nrw/commons/customselector/listeners/ImageLoaderListener;", "loadDeviceImages", "app-commons-v4.2.1-master_prodDebug"})
public final class ImageFileLoader implements kotlinx.coroutines.CoroutineScope {
    @org.jetbrains.annotations.NotNull
    private final android.content.Context context = null;
    
    /**
     * Coroutine context for fetching images.
     */
    @org.jetbrains.annotations.NotNull
    private final kotlin.coroutines.CoroutineContext coroutineContext = null;
    
    /**
     * Media paramerters required.
     */
    private final java.lang.String[] projection = {"_id", "_display_name", "_data", "bucket_id", "bucket_display_name", "date_added"};
    
    public ImageFileLoader(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final android.content.Context getContext() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public kotlin.coroutines.CoroutineContext getCoroutineContext() {
        return null;
    }
    
    /**
     * Load Device Images under coroutine.
     */
    public final void loadDeviceImages(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.customselector.listeners.ImageLoaderListener listener) {
    }
    
    /**
     * Load Device images using cursor
     */
    private final void getImages(fr.free.nrw.commons.customselector.listeners.ImageLoaderListener listener) {
    }
    
    /**
     * Abort loading images.
     */
    public final void abortLoadImage() {
    }
}