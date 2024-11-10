package fr.free.nrw.commons.upload.depicts;

import java.lang.System;

/**
 * Dao class for DepictsRoomDataBase
 */
@androidx.room.Dao
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\b\'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0019\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0010J\u000e\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u0005J\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004J\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0014J\u001f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0016\u001a\u00020\u0017H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0018J\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u001a\u001a\u00020\u0017J\u0019\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0010J\u000e\u0010\u001d\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u0005J\u0014\u0010\u001e\u001a\u00020\u000e2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020 0\u0004R \u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR \u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\u0007\"\u0004\b\f\u0010\t\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006!"}, d2 = {"Lfr/free/nrw/commons/upload/depicts/DepictsDao;", "", "()V", "allDepict", "", "Lfr/free/nrw/commons/upload/depicts/Depicts;", "getAllDepict", "()Ljava/util/List;", "setAllDepict", "(Ljava/util/List;)V", "listOfDelete", "getListOfDelete", "setListOfDelete", "delete", "", "depicts", "(Lfr/free/nrw/commons/upload/depicts/Depicts;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteDepicts", "depictes", "depictsList", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getItemToDelete", "n", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getItemTodelete", "number", "insert", "depictedItem", "insertDepict", "savingDepictsInRoomDataBase", "listDepictedItem", "Lfr/free/nrw/commons/upload/structure/depictions/DepictedItem;", "app-commons-v4.2.1-master_prodDebug"})
public abstract class DepictsDao {
    public java.util.List<fr.free.nrw.commons.upload.depicts.Depicts> allDepict;
    public java.util.List<fr.free.nrw.commons.upload.depicts.Depicts> listOfDelete;
    
    public DepictsDao() {
        super();
    }
    
    /**
     * insert Depicts in DepictsRoomDataBase
     */
    @org.jetbrains.annotations.Nullable
    @androidx.room.Insert(onConflict = 1)
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.upload.depicts.Depicts depictedItem, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    /**
     * get all Depicts from roomdatabase
     */
    @org.jetbrains.annotations.Nullable
    @androidx.room.Query(value = "Select * From depicts_table order by lastUsed DESC")
    public abstract java.lang.Object getAllDepict(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<fr.free.nrw.commons.upload.depicts.Depicts>> continuation);
    
    /**
     * get all Depicts which need to delete  from roomdatabase
     */
    @org.jetbrains.annotations.Nullable
    @androidx.room.Query(value = "Select * From depicts_table order by lastUsed DESC LIMIT :n OFFSET 10")
    public abstract java.lang.Object getItemToDelete(int n, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<fr.free.nrw.commons.upload.depicts.Depicts>> continuation);
    
    /**
     * Delete Depicts from roomdatabase
     */
    @org.jetbrains.annotations.Nullable
    @androidx.room.Delete
    public abstract java.lang.Object delete(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.upload.depicts.Depicts depicts, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<fr.free.nrw.commons.upload.depicts.Depicts> getAllDepict() {
        return null;
    }
    
    public final void setAllDepict(@org.jetbrains.annotations.NotNull
    java.util.List<fr.free.nrw.commons.upload.depicts.Depicts> p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<fr.free.nrw.commons.upload.depicts.Depicts> getListOfDelete() {
        return null;
    }
    
    public final void setListOfDelete(@org.jetbrains.annotations.NotNull
    java.util.List<fr.free.nrw.commons.upload.depicts.Depicts> p0) {
    }
    
    /**
     * get all depicts from DepictsRoomDatabase
     */
    @org.jetbrains.annotations.NotNull
    public final java.util.List<fr.free.nrw.commons.upload.depicts.Depicts> depictsList() {
        return null;
    }
    
    /**
     * insert Depicts  in DepictsRoomDataBase
     */
    public final void insertDepict(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.upload.depicts.Depicts depictes) {
    }
    
    /**
     * get all Depicts item which need to delete
     */
    @org.jetbrains.annotations.NotNull
    public final java.util.List<fr.free.nrw.commons.upload.depicts.Depicts> getItemTodelete(int number) {
        return null;
    }
    
    /**
     * delete Depicts  in DepictsRoomDataBase
     */
    public final void deleteDepicts(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.upload.depicts.Depicts depictes) {
    }
    
    /**
     * save Depicts in DepictsRoomDataBase
     */
    public final void savingDepictsInRoomDataBase(@org.jetbrains.annotations.NotNull
    java.util.List<fr.free.nrw.commons.upload.structure.depictions.DepictedItem> listDepictedItem) {
    }
}