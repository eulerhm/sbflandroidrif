package fr.free.nrw.commons.wikidata.model;

import java.lang.System;

/**
 * Tag class used when adding wikidata edit tag
 */
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u000b\u0018\u00002\u00020\u0001B9\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\b\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00010\b\u00a2\u0006\u0002\u0010\nR\u0016\u0010\u0006\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001c\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\b8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001c\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00010\b8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012\u00a8\u0006\u0013"}, d2 = {"Lfr/free/nrw/commons/wikidata/model/EditTag;", "", "revid", "", "status", "", "actionlogid", "added", "", "removed", "(ILjava/lang/String;ILjava/util/List;Ljava/util/List;)V", "getActionlogid", "()I", "getAdded", "()Ljava/util/List;", "getRemoved", "getRevid", "getStatus", "()Ljava/lang/String;", "app-commons-v4.2.1-master_prodDebug"})
public final class EditTag {
    @com.google.gson.annotations.SerializedName(value = "revid")
    @com.google.gson.annotations.Expose
    private final int revid = 0;
    @org.jetbrains.annotations.NotNull
    @com.google.gson.annotations.SerializedName(value = "status")
    @com.google.gson.annotations.Expose
    private final java.lang.String status = null;
    @com.google.gson.annotations.SerializedName(value = "actionlogid")
    @com.google.gson.annotations.Expose
    private final int actionlogid = 0;
    @org.jetbrains.annotations.NotNull
    @com.google.gson.annotations.SerializedName(value = "added")
    @com.google.gson.annotations.Expose
    private final java.util.List<java.lang.String> added = null;
    @org.jetbrains.annotations.NotNull
    @com.google.gson.annotations.SerializedName(value = "removed")
    @com.google.gson.annotations.Expose
    private final java.util.List<java.lang.Object> removed = null;
    
    public EditTag(int revid, @org.jetbrains.annotations.NotNull
    java.lang.String status, int actionlogid, @org.jetbrains.annotations.NotNull
    java.util.List<java.lang.String> added, @org.jetbrains.annotations.NotNull
    java.util.List<? extends java.lang.Object> removed) {
        super();
    }
    
    public final int getRevid() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getStatus() {
        return null;
    }
    
    public final int getActionlogid() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<java.lang.String> getAdded() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<java.lang.Object> getRemoved() {
        return null;
    }
}