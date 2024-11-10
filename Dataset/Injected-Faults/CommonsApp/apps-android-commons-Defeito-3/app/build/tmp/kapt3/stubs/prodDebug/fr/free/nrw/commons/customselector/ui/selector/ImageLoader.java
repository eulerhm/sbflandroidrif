package fr.free.nrw.commons.customselector.ui.selector;

import java.lang.System;

/**
 * Image Loader class, loads images, depending on API results.
 */
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\b\b\u0018\u0000 L2\u00020\u0001:\u0002LMB7\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u00a2\u0006\u0002\u0010\u000eJ\u001b\u00101\u001a\u0004\u0018\u0001022\u0006\u00103\u001a\u00020\u001fH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u00104J\u000e\u00105\u001a\u00020\"2\u0006\u00106\u001a\u000202J!\u00107\u001a\u00020\u001f2\u0006\u00108\u001a\u00020\u001c2\u0006\u00109\u001a\u00020:H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010;J1\u0010<\u001a\u00020=2\u0006\u00103\u001a\u00020\u001f2\u0006\u0010>\u001a\u00020\u001f2\u0006\u0010?\u001a\u00020@2\u0006\u0010A\u001a\u00020@H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010BJ7\u0010C\u001a\u00020D2\f\u0010E\u001a\b\u0012\u0004\u0012\u00020\u001c0F2\u0006\u0010G\u001a\u00020:2\u0006\u00109\u001a\u00020:2\u0006\u0010H\u001a\u00020DH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010IJ&\u0010J\u001a\u00020=2\u0006\u0010K\u001a\u00020\u001b2\u0006\u00108\u001a\u00020\u001c2\u0006\u0010G\u001a\u00020:2\u0006\u00109\u001a\u00020:R\u0011\u0010\f\u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u001c0\u001aX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u001f0\u001aX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010 \u001a\u000e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u001f0\u001aX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\"0\u001aX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\'\u0010(\"\u0004\b)\u0010*R\u000e\u0010+\u001a\u00020,X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006N"}, d2 = {"Lfr/free/nrw/commons/customselector/ui/selector/ImageLoader;", "", "mediaClient", "Lfr/free/nrw/commons/media/MediaClient;", "fileProcessor", "Lfr/free/nrw/commons/upload/FileProcessor;", "fileUtilsWrapper", "Lfr/free/nrw/commons/upload/FileUtilsWrapper;", "uploadedStatusDao", "Lfr/free/nrw/commons/customselector/database/UploadedStatusDao;", "notForUploadStatusDao", "Lfr/free/nrw/commons/customselector/database/NotForUploadStatusDao;", "context", "Landroid/content/Context;", "(Lfr/free/nrw/commons/media/MediaClient;Lfr/free/nrw/commons/upload/FileProcessor;Lfr/free/nrw/commons/upload/FileUtilsWrapper;Lfr/free/nrw/commons/customselector/database/UploadedStatusDao;Lfr/free/nrw/commons/customselector/database/NotForUploadStatusDao;Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "getFileProcessor", "()Lfr/free/nrw/commons/upload/FileProcessor;", "setFileProcessor", "(Lfr/free/nrw/commons/upload/FileProcessor;)V", "getFileUtilsWrapper", "()Lfr/free/nrw/commons/upload/FileUtilsWrapper;", "setFileUtilsWrapper", "(Lfr/free/nrw/commons/upload/FileUtilsWrapper;)V", "mapHolderImage", "Ljava/util/HashMap;", "Lfr/free/nrw/commons/customselector/ui/adapter/ImageAdapter$ImageViewHolder;", "Lfr/free/nrw/commons/customselector/model/Image;", "mapImageSHA1", "Landroid/net/Uri;", "", "mapModifiedImageSHA1", "mapResult", "Lfr/free/nrw/commons/customselector/ui/selector/ImageLoader$Result;", "getMediaClient", "()Lfr/free/nrw/commons/media/MediaClient;", "setMediaClient", "(Lfr/free/nrw/commons/media/MediaClient;)V", "getNotForUploadStatusDao", "()Lfr/free/nrw/commons/customselector/database/NotForUploadStatusDao;", "setNotForUploadStatusDao", "(Lfr/free/nrw/commons/customselector/database/NotForUploadStatusDao;)V", "scope", "Lkotlinx/coroutines/CoroutineScope;", "getUploadedStatusDao", "()Lfr/free/nrw/commons/customselector/database/UploadedStatusDao;", "setUploadedStatusDao", "(Lfr/free/nrw/commons/customselector/database/UploadedStatusDao;)V", "getFromUploaded", "Lfr/free/nrw/commons/customselector/database/UploadedStatus;", "imageSha1", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getResultFromUploadedStatus", "uploadedStatus", "getSHA1", "image", "defaultDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Lfr/free/nrw/commons/customselector/model/Image;Lkotlinx/coroutines/CoroutineDispatcher;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertIntoUploaded", "", "modifiedImageSha1", "imageResult", "", "modifiedImageResult", "(Ljava/lang/String;Ljava/lang/String;ZZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "nextActionableImage", "", "allImages", "", "ioDispatcher", "nextImagePosition", "(Ljava/util/List;Lkotlinx/coroutines/CoroutineDispatcher;Lkotlinx/coroutines/CoroutineDispatcher;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "queryAndSetView", "holder", "Companion", "Result", "app-commons-v4.2.1-master_prodDebug"})
public final class ImageLoader {
    
    /**
     * MediaClient for SHA1 query.
     */
    @org.jetbrains.annotations.NotNull
    private fr.free.nrw.commons.media.MediaClient mediaClient;
    
    /**
     * FileProcessor to pre-process the file.
     */
    @org.jetbrains.annotations.NotNull
    private fr.free.nrw.commons.upload.FileProcessor fileProcessor;
    
    /**
     * File Utils Wrapper for SHA1
     */
    @org.jetbrains.annotations.NotNull
    private fr.free.nrw.commons.upload.FileUtilsWrapper fileUtilsWrapper;
    
    /**
     * UploadedStatusDao for cache query.
     */
    @org.jetbrains.annotations.NotNull
    private fr.free.nrw.commons.customselector.database.UploadedStatusDao uploadedStatusDao;
    
    /**
     * NotForUploadDao for database operations
     */
    @org.jetbrains.annotations.NotNull
    private fr.free.nrw.commons.customselector.database.NotForUploadStatusDao notForUploadStatusDao;
    
    /**
     * Context for coroutine.
     */
    @org.jetbrains.annotations.NotNull
    private final android.content.Context context = null;
    
    /**
     * Maps to facilitate image query.
     */
    private java.util.HashMap<fr.free.nrw.commons.customselector.model.Image, java.lang.String> mapModifiedImageSHA1;
    private java.util.HashMap<fr.free.nrw.commons.customselector.ui.adapter.ImageAdapter.ImageViewHolder, fr.free.nrw.commons.customselector.model.Image> mapHolderImage;
    private java.util.HashMap<java.lang.String, fr.free.nrw.commons.customselector.ui.selector.ImageLoader.Result> mapResult;
    private java.util.HashMap<android.net.Uri, java.lang.String> mapImageSHA1;
    
    /**
     * Coroutine Scope.
     */
    private final kotlinx.coroutines.CoroutineScope scope = null;
    @org.jetbrains.annotations.NotNull
    public static final fr.free.nrw.commons.customselector.ui.selector.ImageLoader.Companion Companion = null;
    
    /**
     * Invalidate Day count.
     * False Database Entries are invalid after INVALIDATE_DAY_COUNT and need to be re-queried.
     */
    public static final long INVALIDATE_DAY_COUNT = 7L;
    
    @javax.inject.Inject
    public ImageLoader(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.media.MediaClient mediaClient, @org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.upload.FileProcessor fileProcessor, @org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.upload.FileUtilsWrapper fileUtilsWrapper, @org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.customselector.database.UploadedStatusDao uploadedStatusDao, @org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.customselector.database.NotForUploadStatusDao notForUploadStatusDao, @org.jetbrains.annotations.NotNull
    android.content.Context context) {
        super();
    }
    
    /**
     * MediaClient for SHA1 query.
     */
    @org.jetbrains.annotations.NotNull
    public final fr.free.nrw.commons.media.MediaClient getMediaClient() {
        return null;
    }
    
    /**
     * MediaClient for SHA1 query.
     */
    public final void setMediaClient(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.media.MediaClient p0) {
    }
    
    /**
     * FileProcessor to pre-process the file.
     */
    @org.jetbrains.annotations.NotNull
    public final fr.free.nrw.commons.upload.FileProcessor getFileProcessor() {
        return null;
    }
    
    /**
     * FileProcessor to pre-process the file.
     */
    public final void setFileProcessor(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.upload.FileProcessor p0) {
    }
    
    /**
     * File Utils Wrapper for SHA1
     */
    @org.jetbrains.annotations.NotNull
    public final fr.free.nrw.commons.upload.FileUtilsWrapper getFileUtilsWrapper() {
        return null;
    }
    
    /**
     * File Utils Wrapper for SHA1
     */
    public final void setFileUtilsWrapper(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.upload.FileUtilsWrapper p0) {
    }
    
    /**
     * UploadedStatusDao for cache query.
     */
    @org.jetbrains.annotations.NotNull
    public final fr.free.nrw.commons.customselector.database.UploadedStatusDao getUploadedStatusDao() {
        return null;
    }
    
    /**
     * UploadedStatusDao for cache query.
     */
    public final void setUploadedStatusDao(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.customselector.database.UploadedStatusDao p0) {
    }
    
    /**
     * NotForUploadDao for database operations
     */
    @org.jetbrains.annotations.NotNull
    public final fr.free.nrw.commons.customselector.database.NotForUploadStatusDao getNotForUploadStatusDao() {
        return null;
    }
    
    /**
     * NotForUploadDao for database operations
     */
    public final void setNotForUploadStatusDao(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.customselector.database.NotForUploadStatusDao p0) {
    }
    
    /**
     * Context for coroutine.
     */
    @org.jetbrains.annotations.NotNull
    public final android.content.Context getContext() {
        return null;
    }
    
    /**
     * Query image and setUp the view.
     */
    public final void queryAndSetView(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.customselector.ui.adapter.ImageAdapter.ImageViewHolder holder, @org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.customselector.model.Image image, @org.jetbrains.annotations.NotNull
    kotlinx.coroutines.CoroutineDispatcher ioDispatcher, @org.jetbrains.annotations.NotNull
    kotlinx.coroutines.CoroutineDispatcher defaultDispatcher) {
    }
    
    /**
     * Finds out the next actionable image position
     */
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object nextActionableImage(@org.jetbrains.annotations.NotNull
    java.util.List<fr.free.nrw.commons.customselector.model.Image> allImages, @org.jetbrains.annotations.NotNull
    kotlinx.coroutines.CoroutineDispatcher ioDispatcher, @org.jetbrains.annotations.NotNull
    kotlinx.coroutines.CoroutineDispatcher defaultDispatcher, int nextImagePosition, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Integer> continuation) {
        return null;
    }
    
    /**
     * Get SHA1, return SHA1 if available, otherwise generate and store the SHA1.
     *
     * @return sha1 of the image
     */
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getSHA1(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.customselector.model.Image image, @org.jetbrains.annotations.NotNull
    kotlinx.coroutines.CoroutineDispatcher defaultDispatcher, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.String> continuation) {
        return null;
    }
    
    /**
     * Get the uploaded status entry from the database.
     */
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getFromUploaded(@org.jetbrains.annotations.NotNull
    java.lang.String imageSha1, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super fr.free.nrw.commons.customselector.database.UploadedStatus> continuation) {
        return null;
    }
    
    /**
     * Insert into uploaded status table.
     */
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object insertIntoUploaded(@org.jetbrains.annotations.NotNull
    java.lang.String imageSha1, @org.jetbrains.annotations.NotNull
    java.lang.String modifiedImageSha1, boolean imageResult, boolean modifiedImageResult, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    /**
     * Get result data from database.
     */
    @org.jetbrains.annotations.NotNull
    public final fr.free.nrw.commons.customselector.ui.selector.ImageLoader.Result getResultFromUploadedStatus(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.customselector.database.UploadedStatus uploadedStatus) {
        return null;
    }
    
    /**
     * Sealed Result class.
     */
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0005\u0003\u0004\u0005\u0006\u0007B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0005\b\t\n\u000b\f\u00a8\u0006\r"}, d2 = {"Lfr/free/nrw/commons/customselector/ui/selector/ImageLoader$Result;", "", "()V", "ERROR", "FALSE", "INVALID", "NOTFOUND", "TRUE", "Lfr/free/nrw/commons/customselector/ui/selector/ImageLoader$Result$ERROR;", "Lfr/free/nrw/commons/customselector/ui/selector/ImageLoader$Result$FALSE;", "Lfr/free/nrw/commons/customselector/ui/selector/ImageLoader$Result$INVALID;", "Lfr/free/nrw/commons/customselector/ui/selector/ImageLoader$Result$NOTFOUND;", "Lfr/free/nrw/commons/customselector/ui/selector/ImageLoader$Result$TRUE;", "app-commons-v4.2.1-master_prodDebug"})
    public static abstract class Result {
        
        private Result() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lfr/free/nrw/commons/customselector/ui/selector/ImageLoader$Result$TRUE;", "Lfr/free/nrw/commons/customselector/ui/selector/ImageLoader$Result;", "()V", "app-commons-v4.2.1-master_prodDebug"})
        public static final class TRUE extends fr.free.nrw.commons.customselector.ui.selector.ImageLoader.Result {
            @org.jetbrains.annotations.NotNull
            public static final fr.free.nrw.commons.customselector.ui.selector.ImageLoader.Result.TRUE INSTANCE = null;
            
            private TRUE() {
                super();
            }
        }
        
        @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lfr/free/nrw/commons/customselector/ui/selector/ImageLoader$Result$FALSE;", "Lfr/free/nrw/commons/customselector/ui/selector/ImageLoader$Result;", "()V", "app-commons-v4.2.1-master_prodDebug"})
        public static final class FALSE extends fr.free.nrw.commons.customselector.ui.selector.ImageLoader.Result {
            @org.jetbrains.annotations.NotNull
            public static final fr.free.nrw.commons.customselector.ui.selector.ImageLoader.Result.FALSE INSTANCE = null;
            
            private FALSE() {
                super();
            }
        }
        
        @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lfr/free/nrw/commons/customselector/ui/selector/ImageLoader$Result$INVALID;", "Lfr/free/nrw/commons/customselector/ui/selector/ImageLoader$Result;", "()V", "app-commons-v4.2.1-master_prodDebug"})
        public static final class INVALID extends fr.free.nrw.commons.customselector.ui.selector.ImageLoader.Result {
            @org.jetbrains.annotations.NotNull
            public static final fr.free.nrw.commons.customselector.ui.selector.ImageLoader.Result.INVALID INSTANCE = null;
            
            private INVALID() {
                super();
            }
        }
        
        @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lfr/free/nrw/commons/customselector/ui/selector/ImageLoader$Result$NOTFOUND;", "Lfr/free/nrw/commons/customselector/ui/selector/ImageLoader$Result;", "()V", "app-commons-v4.2.1-master_prodDebug"})
        public static final class NOTFOUND extends fr.free.nrw.commons.customselector.ui.selector.ImageLoader.Result {
            @org.jetbrains.annotations.NotNull
            public static final fr.free.nrw.commons.customselector.ui.selector.ImageLoader.Result.NOTFOUND INSTANCE = null;
            
            private NOTFOUND() {
                super();
            }
        }
        
        @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lfr/free/nrw/commons/customselector/ui/selector/ImageLoader$Result$ERROR;", "Lfr/free/nrw/commons/customselector/ui/selector/ImageLoader$Result;", "()V", "app-commons-v4.2.1-master_prodDebug"})
        public static final class ERROR extends fr.free.nrw.commons.customselector.ui.selector.ImageLoader.Result {
            @org.jetbrains.annotations.NotNull
            public static final fr.free.nrw.commons.customselector.ui.selector.ImageLoader.Result.ERROR INSTANCE = null;
            
            private ERROR() {
                super();
            }
        }
    }
    
    /**
     * Companion Object
     */
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lfr/free/nrw/commons/customselector/ui/selector/ImageLoader$Companion;", "", "()V", "INVALIDATE_DAY_COUNT", "", "app-commons-v4.2.1-master_prodDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}