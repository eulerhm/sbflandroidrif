package fr.free.nrw.commons.customselector.database;

import java.lang.System;

/**
 * UploadedStatusDao for Custom Selector.
 */
@androidx.room.Dao
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\b\'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0019\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0007J!\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eJ!\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\rH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eJ\u001b\u0010\u0012\u001a\u0004\u0018\u00010\u00062\u0006\u0010\n\u001a\u00020\u000bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0013J\u001b\u0010\u0014\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0010\u001a\u00020\u000bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0013J\u001b\u0010\u0015\u001a\u0004\u0018\u00010\u00062\u0006\u0010\n\u001a\u00020\u000bH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0013J\u0019\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0007J\u0019\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0007J\u0019\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0007\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0019"}, d2 = {"Lfr/free/nrw/commons/customselector/database/UploadedStatusDao;", "", "()V", "delete", "", "uploadedStatus", "Lfr/free/nrw/commons/customselector/database/UploadedStatus;", "(Lfr/free/nrw/commons/customselector/database/UploadedStatus;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "findByImageSHA1", "", "imageSHA1", "", "imageResult", "", "(Ljava/lang/String;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "findByModifiedImageSHA1", "modifiedImageSHA1", "modifiedImageResult", "getFromImageSHA1", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getFromModifiedImageSHA1", "getUploadedFromImageSHA1", "insert", "insertUploaded", "update", "app-commons-v4.2.1-master_prodDebug"})
public abstract class UploadedStatusDao {
    
    public UploadedStatusDao() {
        super();
    }
    
    /**
     * Insert into uploaded status.
     */
    @org.jetbrains.annotations.Nullable
    @androidx.room.Insert(onConflict = 1)
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.customselector.database.UploadedStatus uploadedStatus, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    /**
     * Update uploaded status entry.
     */
    @org.jetbrains.annotations.Nullable
    @androidx.room.Update
    public abstract java.lang.Object update(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.customselector.database.UploadedStatus uploadedStatus, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    /**
     * Delete uploaded status entry.
     */
    @org.jetbrains.annotations.Nullable
    @androidx.room.Delete
    public abstract java.lang.Object delete(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.customselector.database.UploadedStatus uploadedStatus, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    /**
     * Query uploaded status with image sha1.
     */
    @org.jetbrains.annotations.Nullable
    @androidx.room.Query(value = "SELECT * FROM uploaded_table WHERE imageSHA1 = (:imageSHA1) ")
    public abstract java.lang.Object getFromImageSHA1(@org.jetbrains.annotations.NotNull
    java.lang.String imageSHA1, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super fr.free.nrw.commons.customselector.database.UploadedStatus> continuation);
    
    /**
     * Query uploaded status with modified image sha1.
     */
    @org.jetbrains.annotations.Nullable
    @androidx.room.Query(value = "SELECT * FROM uploaded_table WHERE modifiedImageSHA1 = (:modifiedImageSHA1) ")
    public abstract java.lang.Object getFromModifiedImageSHA1(@org.jetbrains.annotations.NotNull
    java.lang.String modifiedImageSHA1, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super fr.free.nrw.commons.customselector.database.UploadedStatus> continuation);
    
    /**
     * Asynchronous insert into uploaded status table.
     */
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object insertUploaded(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.customselector.database.UploadedStatus uploadedStatus, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    /**
     * Check whether the imageSHA1 is present in database
     */
    @org.jetbrains.annotations.Nullable
    @androidx.room.Query(value = "SELECT COUNT() FROM uploaded_table WHERE imageSHA1 = (:imageSHA1) AND imageResult = (:imageResult) ")
    public abstract java.lang.Object findByImageSHA1(@org.jetbrains.annotations.NotNull
    java.lang.String imageSHA1, boolean imageResult, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Integer> continuation);
    
    /**
     * Check whether the modifiedImageSHA1 is present in database
     */
    @org.jetbrains.annotations.Nullable
    @androidx.room.Query(value = "SELECT COUNT() FROM uploaded_table WHERE modifiedImageSHA1 = (:modifiedImageSHA1) AND modifiedImageResult = (:modifiedImageResult) ")
    public abstract java.lang.Object findByModifiedImageSHA1(@org.jetbrains.annotations.NotNull
    java.lang.String modifiedImageSHA1, boolean modifiedImageResult, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Integer> continuation);
    
    /**
     * Asynchronous image sha1 query.
     */
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getUploadedFromImageSHA1(@org.jetbrains.annotations.NotNull
    java.lang.String imageSHA1, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super fr.free.nrw.commons.customselector.database.UploadedStatus> continuation) {
        return null;
    }
}