package fr.free.nrw.commons.customselector.model;

import java.lang.System;

/**
 * Custom selector data class Folder.
 */
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0018\b\u0002\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0007j\b\u0012\u0004\u0012\u00020\b`\t\u00a2\u0006\u0002\u0010\nJ\t\u0010\u0017\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0018\u001a\u00020\u0005H\u00c6\u0003J\u0019\u0010\u0019\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0007j\b\u0012\u0004\u0012\u00020\b`\tH\u00c6\u0003J7\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0018\b\u0002\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0007j\b\u0012\u0004\u0012\u00020\b`\tH\u00c6\u0001J\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\t\u0010\u001e\u001a\u00020\u001fH\u00d6\u0001J\t\u0010 \u001a\u00020\u0005H\u00d6\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR*\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0007j\b\u0012\u0004\u0012\u00020\b`\tX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016\u00a8\u0006!"}, d2 = {"Lfr/free/nrw/commons/customselector/model/Folder;", "", "bucketId", "", "name", "", "images", "Ljava/util/ArrayList;", "Lfr/free/nrw/commons/customselector/model/Image;", "Lkotlin/collections/ArrayList;", "(JLjava/lang/String;Ljava/util/ArrayList;)V", "getBucketId", "()J", "setBucketId", "(J)V", "getImages", "()Ljava/util/ArrayList;", "setImages", "(Ljava/util/ArrayList;)V", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app-commons-v4.2.1-master_prodDebug"})
public final class Folder {
    
    /**
     * bucketId : Unique directory id, eg 540528482
     */
    private long bucketId;
    
    /**
     * name : bucket/folder name, eg Camera
     */
    @org.jetbrains.annotations.NotNull
    private java.lang.String name;
    
    /**
     * images : folder images, list of all images under this folder.
     */
    @org.jetbrains.annotations.NotNull
    private java.util.ArrayList<fr.free.nrw.commons.customselector.model.Image> images;
    
    /**
     * Custom selector data class Folder.
     */
    @org.jetbrains.annotations.NotNull
    public final fr.free.nrw.commons.customselector.model.Folder copy(long bucketId, @org.jetbrains.annotations.NotNull
    java.lang.String name, @org.jetbrains.annotations.NotNull
    java.util.ArrayList<fr.free.nrw.commons.customselector.model.Image> images) {
        return null;
    }
    
    /**
     * Custom selector data class Folder.
     */
    @java.lang.Override
    public int hashCode() {
        return 0;
    }
    
    /**
     * Custom selector data class Folder.
     */
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public java.lang.String toString() {
        return null;
    }
    
    public Folder(long bucketId, @org.jetbrains.annotations.NotNull
    java.lang.String name, @org.jetbrains.annotations.NotNull
    java.util.ArrayList<fr.free.nrw.commons.customselector.model.Image> images) {
        super();
    }
    
    /**
     * bucketId : Unique directory id, eg 540528482
     */
    public final long component1() {
        return 0L;
    }
    
    /**
     * bucketId : Unique directory id, eg 540528482
     */
    public final long getBucketId() {
        return 0L;
    }
    
    /**
     * bucketId : Unique directory id, eg 540528482
     */
    public final void setBucketId(long p0) {
    }
    
    /**
     * name : bucket/folder name, eg Camera
     */
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component2() {
        return null;
    }
    
    /**
     * name : bucket/folder name, eg Camera
     */
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getName() {
        return null;
    }
    
    /**
     * name : bucket/folder name, eg Camera
     */
    public final void setName(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
    
    /**
     * images : folder images, list of all images under this folder.
     */
    @org.jetbrains.annotations.NotNull
    public final java.util.ArrayList<fr.free.nrw.commons.customselector.model.Image> component3() {
        return null;
    }
    
    /**
     * images : folder images, list of all images under this folder.
     */
    @org.jetbrains.annotations.NotNull
    public final java.util.ArrayList<fr.free.nrw.commons.customselector.model.Image> getImages() {
        return null;
    }
    
    /**
     * images : folder images, list of all images under this folder.
     */
    public final void setImages(@org.jetbrains.annotations.NotNull
    java.util.ArrayList<fr.free.nrw.commons.customselector.model.Image> p0) {
    }
    
    /**
     * Indicates whether some other object is "equal to" this one.
     */
    @java.lang.Override
    public boolean equals(@org.jetbrains.annotations.Nullable
    java.lang.Object other) {
        return false;
    }
}