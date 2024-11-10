package fr.free.nrw.commons.utils;

import java.lang.System;

/**
 * Util Class for Custom Selector
 */
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0004"}, d2 = {"Lfr/free/nrw/commons/utils/CustomSelectorUtils;", "", "()V", "Companion", "app-commons-v4.2.1-main_betaDebug"})
public final class CustomSelectorUtils {
    @org.jetbrains.annotations.NotNull
    public static final fr.free.nrw.commons.utils.CustomSelectorUtils.Companion Companion = null;
    
    public CustomSelectorUtils() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J)\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bJ9\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0016J1\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u001a\u001a\u00020\u001bH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001c\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u001d"}, d2 = {"Lfr/free/nrw/commons/utils/CustomSelectorUtils$Companion;", "", "()V", "checkWhetherFileExistsOnCommonsUsingSHA1", "Lfr/free/nrw/commons/customselector/ui/selector/ImageLoader$Result;", "SHA1", "", "ioDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "mediaClient", "Lfr/free/nrw/commons/media/MediaClient;", "(Ljava/lang/String;Lkotlinx/coroutines/CoroutineDispatcher;Lfr/free/nrw/commons/media/MediaClient;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "generateModifiedSHA1", "image", "Lfr/free/nrw/commons/customselector/model/Image;", "defaultDispatcher", "context", "Landroid/content/Context;", "fileProcessor", "Lfr/free/nrw/commons/upload/FileProcessor;", "fileUtilsWrapper", "Lfr/free/nrw/commons/upload/FileUtilsWrapper;", "(Lfr/free/nrw/commons/customselector/model/Image;Lkotlinx/coroutines/CoroutineDispatcher;Landroid/content/Context;Lfr/free/nrw/commons/upload/FileProcessor;Lfr/free/nrw/commons/upload/FileUtilsWrapper;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getImageSHA1", "uri", "Landroid/net/Uri;", "contentResolver", "Landroid/content/ContentResolver;", "(Landroid/net/Uri;Lkotlinx/coroutines/CoroutineDispatcher;Lfr/free/nrw/commons/upload/FileUtilsWrapper;Landroid/content/ContentResolver;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app-commons-v4.2.1-main_betaDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        /**
         * Get image sha1 from uri, used to retrieve the original image sha1.
         */
        @org.jetbrains.annotations.Nullable
        public final java.lang.Object getImageSHA1(@org.jetbrains.annotations.NotNull
        android.net.Uri uri, @org.jetbrains.annotations.NotNull
        kotlinx.coroutines.CoroutineDispatcher ioDispatcher, @org.jetbrains.annotations.NotNull
        fr.free.nrw.commons.upload.FileUtilsWrapper fileUtilsWrapper, @org.jetbrains.annotations.NotNull
        android.content.ContentResolver contentResolver, @org.jetbrains.annotations.NotNull
        kotlin.coroutines.Continuation<? super java.lang.String> continuation) {
            return null;
        }
        
        /**
         * Generates modified SHA1 of an image
         */
        @org.jetbrains.annotations.Nullable
        public final java.lang.Object generateModifiedSHA1(@org.jetbrains.annotations.NotNull
        fr.free.nrw.commons.customselector.model.Image image, @org.jetbrains.annotations.NotNull
        kotlinx.coroutines.CoroutineDispatcher defaultDispatcher, @org.jetbrains.annotations.NotNull
        android.content.Context context, @org.jetbrains.annotations.NotNull
        fr.free.nrw.commons.upload.FileProcessor fileProcessor, @org.jetbrains.annotations.NotNull
        fr.free.nrw.commons.upload.FileUtilsWrapper fileUtilsWrapper, @org.jetbrains.annotations.NotNull
        kotlin.coroutines.Continuation<? super java.lang.String> continuation) {
            return null;
        }
        
        /**
         * Query SHA1, return result if previously queried, otherwise start a new query.
         *
         * @return true if the image exists on Commons, false otherwise.
         */
        @org.jetbrains.annotations.Nullable
        public final java.lang.Object checkWhetherFileExistsOnCommonsUsingSHA1(@org.jetbrains.annotations.NotNull
        java.lang.String SHA1, @org.jetbrains.annotations.NotNull
        kotlinx.coroutines.CoroutineDispatcher ioDispatcher, @org.jetbrains.annotations.NotNull
        fr.free.nrw.commons.media.MediaClient mediaClient, @org.jetbrains.annotations.NotNull
        kotlin.coroutines.Continuation<? super fr.free.nrw.commons.customselector.ui.selector.ImageLoader.Result> continuation) {
            return null;
        }
    }
}