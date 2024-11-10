package fr.free.nrw.commons.customselector.database;

import java.lang.System;

/**
 * Entity class for Uploaded Status.
 */
@androidx.room.Entity(tableName = "uploaded_table", indices = {@androidx.room.Index(unique = true, value = {"modifiedImageSHA1"})})
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u00a2\u0006\u0002\u0010\nJ\t\u0010\u0018\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0019\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u001b\u001a\u00020\u0006H\u00c6\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\tH\u00c6\u0003J=\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\tH\u00c6\u0001J\u0013\u0010\u001e\u001a\u00020\u00062\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010 \u001a\u00020!H\u00d6\u0001J\t\u0010\"\u001a\u00020\u0003H\u00d6\u0001R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001c\u0010\b\u001a\u0004\u0018\u00010\tX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0007\u001a\u00020\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\f\"\u0004\b\u0016\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0010\u00a8\u0006#"}, d2 = {"Lfr/free/nrw/commons/customselector/database/UploadedStatus;", "", "imageSHA1", "", "modifiedImageSHA1", "imageResult", "", "modifiedImageResult", "lastUpdated", "Ljava/util/Date;", "(Ljava/lang/String;Ljava/lang/String;ZZLjava/util/Date;)V", "getImageResult", "()Z", "setImageResult", "(Z)V", "getImageSHA1", "()Ljava/lang/String;", "getLastUpdated", "()Ljava/util/Date;", "setLastUpdated", "(Ljava/util/Date;)V", "getModifiedImageResult", "setModifiedImageResult", "getModifiedImageSHA1", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "", "toString", "app-commons-v4.2.1-main_betaDebug"})
public final class UploadedStatus {
    
    /**
     * Original image sha1.
     */
    @org.jetbrains.annotations.NotNull
    @androidx.room.PrimaryKey
    private final java.lang.String imageSHA1 = null;
    
    /**
     * Modified image sha1 (after exif changes).
     */
    @org.jetbrains.annotations.NotNull
    private final java.lang.String modifiedImageSHA1 = null;
    
    /**
     * imageSHA1 query result from API.
     */
    private boolean imageResult;
    
    /**
     * modifiedImageSHA1 query result from API.
     */
    private boolean modifiedImageResult;
    
    /**
     * lastUpdated for data validation.
     */
    @org.jetbrains.annotations.Nullable
    private java.util.Date lastUpdated;
    
    /**
     * Entity class for Uploaded Status.
     */
    @org.jetbrains.annotations.NotNull
    public final fr.free.nrw.commons.customselector.database.UploadedStatus copy(@org.jetbrains.annotations.NotNull
    java.lang.String imageSHA1, @org.jetbrains.annotations.NotNull
    java.lang.String modifiedImageSHA1, boolean imageResult, boolean modifiedImageResult, @org.jetbrains.annotations.Nullable
    java.util.Date lastUpdated) {
        return null;
    }
    
    /**
     * Entity class for Uploaded Status.
     */
    @java.lang.Override
    public boolean equals(@org.jetbrains.annotations.Nullable
    java.lang.Object other) {
        return false;
    }
    
    /**
     * Entity class for Uploaded Status.
     */
    @java.lang.Override
    public int hashCode() {
        return 0;
    }
    
    /**
     * Entity class for Uploaded Status.
     */
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public java.lang.String toString() {
        return null;
    }
    
    public UploadedStatus(@org.jetbrains.annotations.NotNull
    java.lang.String imageSHA1, @org.jetbrains.annotations.NotNull
    java.lang.String modifiedImageSHA1, boolean imageResult, boolean modifiedImageResult, @org.jetbrains.annotations.Nullable
    java.util.Date lastUpdated) {
        super();
    }
    
    /**
     * Original image sha1.
     */
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component1() {
        return null;
    }
    
    /**
     * Original image sha1.
     */
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getImageSHA1() {
        return null;
    }
    
    /**
     * Modified image sha1 (after exif changes).
     */
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component2() {
        return null;
    }
    
    /**
     * Modified image sha1 (after exif changes).
     */
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getModifiedImageSHA1() {
        return null;
    }
    
    /**
     * imageSHA1 query result from API.
     */
    public final boolean component3() {
        return false;
    }
    
    /**
     * imageSHA1 query result from API.
     */
    public final boolean getImageResult() {
        return false;
    }
    
    /**
     * imageSHA1 query result from API.
     */
    public final void setImageResult(boolean p0) {
    }
    
    /**
     * modifiedImageSHA1 query result from API.
     */
    public final boolean component4() {
        return false;
    }
    
    /**
     * modifiedImageSHA1 query result from API.
     */
    public final boolean getModifiedImageResult() {
        return false;
    }
    
    /**
     * modifiedImageSHA1 query result from API.
     */
    public final void setModifiedImageResult(boolean p0) {
    }
    
    /**
     * lastUpdated for data validation.
     */
    @org.jetbrains.annotations.Nullable
    public final java.util.Date component5() {
        return null;
    }
    
    /**
     * lastUpdated for data validation.
     */
    @org.jetbrains.annotations.Nullable
    public final java.util.Date getLastUpdated() {
        return null;
    }
    
    /**
     * lastUpdated for data validation.
     */
    public final void setLastUpdated(@org.jetbrains.annotations.Nullable
    java.util.Date p0) {
    }
}