package fr.free.nrw.commons.customselector.ui.selector;

import java.lang.System;

/**
 * Custom Selector view model.
 */
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0006\u0010\u001e\u001a\u00020\u001fJ\b\u0010 \u001a\u00020\u001fH\u0014R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001f\u0010\u000f\u001a\u0010\u0012\f\u0012\n \u0012*\u0004\u0018\u00010\u00110\u00110\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004\u00a2\u0006\u0002\n\u0000R0\u0010\u0017\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u00190\u0018j\b\u0012\u0004\u0012\u00020\u0019`\u001a0\u0010X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0014\"\u0004\b\u001c\u0010\u001d\u00a8\u0006!"}, d2 = {"Lfr/free/nrw/commons/customselector/ui/selector/CustomSelectorViewModel;", "Landroidx/lifecycle/ViewModel;", "context", "Landroid/content/Context;", "imageFileLoader", "Lfr/free/nrw/commons/customselector/ui/selector/ImageFileLoader;", "(Landroid/content/Context;Lfr/free/nrw/commons/customselector/ui/selector/ImageFileLoader;)V", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "getImageFileLoader", "()Lfr/free/nrw/commons/customselector/ui/selector/ImageFileLoader;", "setImageFileLoader", "(Lfr/free/nrw/commons/customselector/ui/selector/ImageFileLoader;)V", "result", "Landroidx/lifecycle/MutableLiveData;", "Lfr/free/nrw/commons/customselector/model/Result;", "kotlin.jvm.PlatformType", "getResult", "()Landroidx/lifecycle/MutableLiveData;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "selectedImages", "Ljava/util/ArrayList;", "Lfr/free/nrw/commons/customselector/model/Image;", "Lkotlin/collections/ArrayList;", "getSelectedImages", "setSelectedImages", "(Landroidx/lifecycle/MutableLiveData;)V", "fetchImages", "", "onCleared", "app-commons-v4.2.1-main_betaDebug"})
public final class CustomSelectorViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull
    private android.content.Context context;
    @org.jetbrains.annotations.NotNull
    private fr.free.nrw.commons.customselector.ui.selector.ImageFileLoader imageFileLoader;
    
    /**
     * Scope for coroutine task (image fetch).
     */
    private final kotlinx.coroutines.CoroutineScope scope = null;
    
    /**
     * Stores selected images.
     */
    @org.jetbrains.annotations.NotNull
    private androidx.lifecycle.MutableLiveData<java.util.ArrayList<fr.free.nrw.commons.customselector.model.Image>> selectedImages;
    
    /**
     * Result Live Data.
     */
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<fr.free.nrw.commons.customselector.model.Result> result = null;
    
    public CustomSelectorViewModel(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.customselector.ui.selector.ImageFileLoader imageFileLoader) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final android.content.Context getContext() {
        return null;
    }
    
    public final void setContext(@org.jetbrains.annotations.NotNull
    android.content.Context p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final fr.free.nrw.commons.customselector.ui.selector.ImageFileLoader getImageFileLoader() {
        return null;
    }
    
    public final void setImageFileLoader(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.customselector.ui.selector.ImageFileLoader p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.MutableLiveData<java.util.ArrayList<fr.free.nrw.commons.customselector.model.Image>> getSelectedImages() {
        return null;
    }
    
    public final void setSelectedImages(@org.jetbrains.annotations.NotNull
    androidx.lifecycle.MutableLiveData<java.util.ArrayList<fr.free.nrw.commons.customselector.model.Image>> p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.MutableLiveData<fr.free.nrw.commons.customselector.model.Result> getResult() {
        return null;
    }
    
    /**
     * Fetch Images and supply to result.
     */
    public final void fetchImages() {
    }
    
    /**
     * Clear the coroutine task linked with context.
     */
    @java.lang.Override
    protected void onCleared() {
    }
}