package fr.free.nrw.commons.upload.depicts;

import java.lang.System;

/**
 * entity class for DepictsRoomDateBase
 */
@androidx.room.Entity(tableName = "depicts_table")
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\f\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0011\u001a\u00020\u0012H\u00d6\u0001J\t\u0010\u0013\u001a\u00020\u0014H\u00d6\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0015"}, d2 = {"Lfr/free/nrw/commons/upload/depicts/Depicts;", "", "item", "Lfr/free/nrw/commons/upload/structure/depictions/DepictedItem;", "lastUsed", "Ljava/util/Date;", "(Lfr/free/nrw/commons/upload/structure/depictions/DepictedItem;Ljava/util/Date;)V", "getItem", "()Lfr/free/nrw/commons/upload/structure/depictions/DepictedItem;", "getLastUsed", "()Ljava/util/Date;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app-commons-v4.2.1-master_prodDebug"})
public final class Depicts {
    @org.jetbrains.annotations.NotNull
    @androidx.room.PrimaryKey
    private final fr.free.nrw.commons.upload.structure.depictions.DepictedItem item = null;
    @org.jetbrains.annotations.NotNull
    private final java.util.Date lastUsed = null;
    
    /**
     * entity class for DepictsRoomDateBase
     */
    @org.jetbrains.annotations.NotNull
    public final fr.free.nrw.commons.upload.depicts.Depicts copy(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.upload.structure.depictions.DepictedItem item, @org.jetbrains.annotations.NotNull
    java.util.Date lastUsed) {
        return null;
    }
    
    /**
     * entity class for DepictsRoomDateBase
     */
    @java.lang.Override
    public boolean equals(@org.jetbrains.annotations.Nullable
    java.lang.Object other) {
        return false;
    }
    
    /**
     * entity class for DepictsRoomDateBase
     */
    @java.lang.Override
    public int hashCode() {
        return 0;
    }
    
    /**
     * entity class for DepictsRoomDateBase
     */
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public java.lang.String toString() {
        return null;
    }
    
    public Depicts(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.upload.structure.depictions.DepictedItem item, @org.jetbrains.annotations.NotNull
    java.util.Date lastUsed) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final fr.free.nrw.commons.upload.structure.depictions.DepictedItem component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final fr.free.nrw.commons.upload.structure.depictions.DepictedItem getItem() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.Date component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.Date getLastUsed() {
        return null;
    }
}