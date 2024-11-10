package fr.free.nrw.commons.customselector.database;

import java.lang.System;

/**
 * Dao class for Not For Upload
 */
@androidx.room.Dao
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\b\'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0019\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0007J\u0019\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\nH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bJ\u0019\u0010\f\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\nH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bJ\u0019\u0010\r\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\nH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bJ\u001b\u0010\u000f\u001a\u0004\u0018\u00010\u00062\u0006\u0010\t\u001a\u00020\nH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bJ\u001b\u0010\u0010\u001a\u0004\u0018\u00010\u00062\u0006\u0010\t\u001a\u00020\nH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bJ\u0019\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0007\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0012"}, d2 = {"Lfr/free/nrw/commons/customselector/database/NotForUploadStatusDao;", "", "()V", "delete", "", "notForUploadStatus", "Lfr/free/nrw/commons/customselector/database/NotForUploadStatus;", "(Lfr/free/nrw/commons/customselector/database/NotForUploadStatus;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteNotForUploadWithImageSHA1", "imageSHA1", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteWithImageSHA1", "find", "", "getFromImageSHA1", "getNotForUploadFromImageSHA1", "insert", "app-commons-v4.2.1-master_prodDebug"})
public abstract class NotForUploadStatusDao {
    
    public NotForUploadStatusDao() {
        super();
    }
    
    /**
     * Insert into Not For Upload status.
     */
    @org.jetbrains.annotations.Nullable
    @androidx.room.Insert(onConflict = 1)
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.customselector.database.NotForUploadStatus notForUploadStatus, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    /**
     * Delete Not For Upload status entry.
     */
    @org.jetbrains.annotations.Nullable
    @androidx.room.Delete
    public abstract java.lang.Object delete(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.customselector.database.NotForUploadStatus notForUploadStatus, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    /**
     * Query Not For Upload status with image sha1.
     */
    @org.jetbrains.annotations.Nullable
    @androidx.room.Query(value = "SELECT * FROM images_not_for_upload_table WHERE imageSHA1 = (:imageSHA1) ")
    public abstract java.lang.Object getFromImageSHA1(@org.jetbrains.annotations.NotNull
    java.lang.String imageSHA1, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super fr.free.nrw.commons.customselector.database.NotForUploadStatus> continuation);
    
    /**
     * Asynchronous image sha1 query.
     */
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getNotForUploadFromImageSHA1(@org.jetbrains.annotations.NotNull
    java.lang.String imageSHA1, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super fr.free.nrw.commons.customselector.database.NotForUploadStatus> continuation) {
        return null;
    }
    
    /**
     * Deletion Not For Upload status with image sha1.
     */
    @org.jetbrains.annotations.Nullable
    @androidx.room.Query(value = "DELETE FROM images_not_for_upload_table WHERE imageSHA1 = (:imageSHA1) ")
    public abstract java.lang.Object deleteWithImageSHA1(@org.jetbrains.annotations.NotNull
    java.lang.String imageSHA1, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    /**
     * Asynchronous image sha1 deletion.
     */
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object deleteNotForUploadWithImageSHA1(@org.jetbrains.annotations.NotNull
    java.lang.String imageSHA1, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    /**
     * Check whether the imageSHA1 is present in database
     */
    @org.jetbrains.annotations.Nullable
    @androidx.room.Query(value = "SELECT COUNT() FROM images_not_for_upload_table WHERE imageSHA1 = (:imageSHA1) ")
    public abstract java.lang.Object find(@org.jetbrains.annotations.NotNull
    java.lang.String imageSHA1, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Integer> continuation);
}