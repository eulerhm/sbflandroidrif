package com.google.android.gnd.repository;

import java.lang.System;

/**
 * Provides access to user-provided media stored locally and remotely. This currently includes only
 * photos.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B!\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0016\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000eJ\u000e\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u000eJ\u0010\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u000eH\u0002J\u0019\u0010\u0014\u001a\r\u0012\u0004\u0012\u00020\u00160\u0015\u00a2\u0006\u0002\b\u00172\u0006\u0010\u0018\u001a\u00020\u000eJ\u0016\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00160\u00152\u0006\u0010\u001a\u001a\u00020\u000eH\u0002J\u000e\u0010\u001b\u001a\u00020\n2\u0006\u0010\u001a\u001a\u00020\u000eJ\u0016\u0010\u001c\u001a\u00020\n2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u0012\u001a\u00020\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\u0004\u0018\u00010\n8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001f"}, d2 = {"Lcom/google/android/gnd/repository/UserMediaRepository;", "", "context", "Landroid/content/Context;", "remoteStorageManager", "Lcom/google/android/gnd/persistence/remote/RemoteStorageManager;", "uuidGenerator", "Lcom/google/android/gnd/persistence/uuid/OfflineUuidGenerator;", "(Landroid/content/Context;Lcom/google/android/gnd/persistence/remote/RemoteStorageManager;Lcom/google/android/gnd/persistence/uuid/OfflineUuidGenerator;)V", "rootDir", "Ljava/io/File;", "getRootDir", "()Ljava/io/File;", "addImageToGallery", "", "filePath", "title", "createImageFile", "fieldId", "createImageFilename", "getDownloadUrl", "Lio/reactivex/Single;", "Landroid/net/Uri;", "Lcom/google/android/gnd/rx/annotations/Cold;", "path", "getFileUriFromRemotePath", "destinationPath", "getLocalFileFromRemotePath", "savePhoto", "bitmap", "Landroid/graphics/Bitmap;", "gnd_debug"})
@javax.inject.Singleton()
public final class UserMediaRepository {
    private final android.content.Context context = null;
    private final com.google.android.gnd.persistence.remote.RemoteStorageManager remoteStorageManager = null;
    private final com.google.android.gnd.persistence.uuid.OfflineUuidGenerator uuidGenerator = null;
    
    @javax.inject.Inject()
    public UserMediaRepository(@org.jetbrains.annotations.NotNull()
    @dagger.hilt.android.qualifiers.ApplicationContext()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    com.google.android.gnd.persistence.remote.RemoteStorageManager remoteStorageManager, @org.jetbrains.annotations.NotNull()
    com.google.android.gnd.persistence.uuid.OfflineUuidGenerator uuidGenerator) {
        super();
    }
    
    private final java.io.File getRootDir() {
        return null;
    }
    
    private final java.lang.String createImageFilename(java.lang.String fieldId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.io.File createImageFile(@org.jetbrains.annotations.NotNull()
    java.lang.String fieldId) {
        return null;
    }
    
    /**
     * Creates a new file from bitmap and saves under external app directory.
     *
     * @throws IOException If path is not accessible or error occurs while saving file
     */
    @org.jetbrains.annotations.NotNull()
    @kotlin.jvm.Throws(exceptionClasses = {java.io.IOException.class})
    public final java.io.File savePhoto(@org.jetbrains.annotations.NotNull()
    android.graphics.Bitmap bitmap, @org.jetbrains.annotations.NotNull()
    java.lang.String fieldId) throws java.io.IOException {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @kotlin.jvm.Throws(exceptionClasses = {java.io.FileNotFoundException.class})
    public final java.lang.String addImageToGallery(@org.jetbrains.annotations.NotNull()
    java.lang.String filePath, @org.jetbrains.annotations.NotNull()
    java.lang.String title) throws java.io.FileNotFoundException {
        return null;
    }
    
    /**
     * Attempts to load the file from local cache. Else attempts to fetch it from Firestore Storage.
     * Returns the uri of the file.
     *
     * @param path Final destination path of the uploaded file relative to Firestore
     */
    @org.jetbrains.annotations.NotNull()
    public final io.reactivex.Single<android.net.Uri> getDownloadUrl(@org.jetbrains.annotations.NotNull()
    java.lang.String path) {
        return null;
    }
    
    private final io.reactivex.Single<android.net.Uri> getFileUriFromRemotePath(java.lang.String destinationPath) {
        return null;
    }
    
    /**
     * Returns the path of the file saved in the sdcard used for uploading to the provided destination
     * path.
     */
    @org.jetbrains.annotations.NotNull()
    public final java.io.File getLocalFileFromRemotePath(@org.jetbrains.annotations.NotNull()
    java.lang.String destinationPath) {
        return null;
    }
}