package fr.free.nrw.commons.upload;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001BA\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\b\u0001\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u00a2\u0006\u0002\u0010\u0010J\u0006\u0010\u0015\u001a\u00020\u0016J\u0018\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001dJ\u000e\u0010\u001f\u001a\u00020\u00162\u0006\u0010 \u001a\u00020!J\"\u0010\"\u001a\u00020!2\u0006\u0010\u001a\u001a\u00020\u001b2\b\u0010#\u001a\u0004\u0018\u00010\u001e2\b\u0010$\u001a\u0004\u0018\u00010%J\u0012\u0010&\u001a\u0004\u0018\u00010!2\u0006\u0010\'\u001a\u00020\u0019H\u0002J\u001e\u0010(\u001a\u00020\u00162\b\u0010)\u001a\u0004\u0018\u00010*2\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001dJ\u001a\u0010,\u001a\u00020\u00162\b\u0010)\u001a\u0004\u0018\u00010*2\u0006\u0010-\u001a\u00020\u001eH\u0002J\u0012\u0010.\u001a\u00020\u00162\b\u0010)\u001a\u0004\u0018\u00010*H\u0002J\u0010\u0010/\u001a\u0002002\u0006\u0010 \u001a\u00020!H\u0002R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u00061"}, d2 = {"Lfr/free/nrw/commons/upload/FileProcessor;", "", "context", "Landroid/content/Context;", "contentResolver", "Landroid/content/ContentResolver;", "gpsCategoryModel", "Lfr/free/nrw/commons/upload/GpsCategoryModel;", "depictsModel", "Lfr/free/nrw/commons/upload/structure/depictions/DepictModel;", "defaultKvStore", "Lfr/free/nrw/commons/kvstore/JsonKvStore;", "apiCall", "Lfr/free/nrw/commons/mwapi/CategoryApi;", "okHttpJsonApiClient", "Lfr/free/nrw/commons/mwapi/OkHttpJsonApiClient;", "(Landroid/content/Context;Landroid/content/ContentResolver;Lfr/free/nrw/commons/upload/GpsCategoryModel;Lfr/free/nrw/commons/upload/structure/depictions/DepictModel;Lfr/free/nrw/commons/kvstore/JsonKvStore;Lfr/free/nrw/commons/mwapi/CategoryApi;Lfr/free/nrw/commons/mwapi/OkHttpJsonApiClient;)V", "compositeDisposable", "Lio/reactivex/disposables/CompositeDisposable;", "radiiProgressionInMetres", "Lkotlin/ranges/IntProgression;", "cleanup", "", "findOtherImages", "fileBeingProcessed", "Ljava/io/File;", "similarImageInterface", "Lfr/free/nrw/commons/upload/SimilarImageInterface;", "getExifTagsToRedact", "", "", "prePopulateCategoriesAndDepictionsBy", "imageCoordinates", "Lfr/free/nrw/commons/upload/ImageCoordinates;", "processFileCoordinates", "filePath", "inAppPictureLocation", "Lfr/free/nrw/commons/location/LatLng;", "readImageCoordinates", "file", "redactExifTags", "exifInterface", "Landroidx/exifinterface/media/ExifInterface;", "redactTags", "redactTag", "tag", "save", "suggestNearbyDepictions", "Lio/reactivex/disposables/Disposable;", "app-commons-v4.2.1-main_betaDebug"})
public final class FileProcessor {
    private final android.content.Context context = null;
    private final android.content.ContentResolver contentResolver = null;
    private final fr.free.nrw.commons.upload.GpsCategoryModel gpsCategoryModel = null;
    private final fr.free.nrw.commons.upload.structure.depictions.DepictModel depictsModel = null;
    private final fr.free.nrw.commons.kvstore.JsonKvStore defaultKvStore = null;
    private final fr.free.nrw.commons.mwapi.CategoryApi apiCall = null;
    private final fr.free.nrw.commons.mwapi.OkHttpJsonApiClient okHttpJsonApiClient = null;
    private final io.reactivex.disposables.CompositeDisposable compositeDisposable = null;
    private final kotlin.ranges.IntProgression radiiProgressionInMetres = null;
    
    @javax.inject.Inject
    public FileProcessor(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    android.content.ContentResolver contentResolver, @org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.upload.GpsCategoryModel gpsCategoryModel, @org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.upload.structure.depictions.DepictModel depictsModel, @org.jetbrains.annotations.NotNull
    @javax.inject.Named(value = "default_preferences")
    fr.free.nrw.commons.kvstore.JsonKvStore defaultKvStore, @org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.mwapi.CategoryApi apiCall, @org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.mwapi.OkHttpJsonApiClient okHttpJsonApiClient) {
        super();
    }
    
    public final void cleanup() {
    }
    
    /**
     * Processes filePath coordinates, either from EXIF data or user location
     */
    @org.jetbrains.annotations.NotNull
    public final fr.free.nrw.commons.upload.ImageCoordinates processFileCoordinates(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.upload.SimilarImageInterface similarImageInterface, @org.jetbrains.annotations.Nullable
    java.lang.String filePath, @org.jetbrains.annotations.Nullable
    fr.free.nrw.commons.location.LatLng inAppPictureLocation) {
        return null;
    }
    
    /**
     * Gets EXIF Tags from preferences to be redacted.
     *
     * @return tags to be redacted
     */
    @org.jetbrains.annotations.NotNull
    public final java.util.Set<java.lang.String> getExifTagsToRedact() {
        return null;
    }
    
    /**
     * Redacts EXIF metadata as indicated in preferences.
     *
     * @param exifInterface ExifInterface object
     * @param redactTags    tags to be redacted
     */
    public final void redactExifTags(@org.jetbrains.annotations.Nullable
    androidx.exifinterface.media.ExifInterface exifInterface, @org.jetbrains.annotations.NotNull
    java.util.Set<java.lang.String> redactTags) {
    }
    
    private final void save(androidx.exifinterface.media.ExifInterface exifInterface) {
    }
    
    private final void redactTag(androidx.exifinterface.media.ExifInterface exifInterface, java.lang.String tag) {
    }
    
    /**
     * Find other images around the same location that were taken within the last 20 sec
     *
     * @param originalImageCoordinates
     * @param fileBeingProcessed
     * @param similarImageInterface
     */
    private final void findOtherImages(java.io.File fileBeingProcessed, fr.free.nrw.commons.upload.SimilarImageInterface similarImageInterface) {
    }
    
    private final fr.free.nrw.commons.upload.ImageCoordinates readImageCoordinates(java.io.File file) {
        return null;
    }
    
    /**
     * Initiates retrieval of image coordinates or user coordinates, and caching of coordinates. Then
     * initiates the calls to MediaWiki API through an instance of CategoryApi.
     *
     * @param imageCoordinates
     */
    public final void prePopulateCategoriesAndDepictionsBy(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.upload.ImageCoordinates imageCoordinates) {
    }
    
    private final io.reactivex.disposables.Disposable suggestNearbyDepictions(fr.free.nrw.commons.upload.ImageCoordinates imageCoordinates) {
        return null;
    }
}