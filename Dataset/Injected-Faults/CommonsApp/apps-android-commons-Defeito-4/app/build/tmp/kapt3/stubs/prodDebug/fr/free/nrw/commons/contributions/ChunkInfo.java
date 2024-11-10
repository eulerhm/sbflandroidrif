package fr.free.nrw.commons.contributions;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0086\b\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004B\u001f\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u00a2\u0006\u0002\u0010\nJ\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003J\t\u0010\u0011\u001a\u00020\bH\u00c6\u0003J\t\u0010\u0012\u001a\u00020\bH\u00c6\u0003J)\u0010\u0013\u001a\u00020\u00002\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\bH\u00c6\u0001J\b\u0010\u0014\u001a\u00020\bH\u0016J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u00d6\u0003J\t\u0010\u0019\u001a\u00020\bH\u00d6\u0001J\t\u0010\u001a\u001a\u00020\u001bH\u00d6\u0001J\u0018\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u001e\u001a\u00020\bH\u0016R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\t\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006 "}, d2 = {"Lfr/free/nrw/commons/contributions/ChunkInfo;", "Landroid/os/Parcelable;", "parcel", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "uploadResult", "Lfr/free/nrw/commons/upload/UploadResult;", "indexOfNextChunkToUpload", "", "totalChunks", "(Lfr/free/nrw/commons/upload/UploadResult;II)V", "getIndexOfNextChunkToUpload", "()I", "getTotalChunks", "getUploadResult", "()Lfr/free/nrw/commons/upload/UploadResult;", "component1", "component2", "component3", "copy", "describeContents", "equals", "", "other", "", "hashCode", "toString", "", "writeToParcel", "", "flags", "CREATOR", "app-commons-v4.2.1-master_prodDebug"})
public final class ChunkInfo implements android.os.Parcelable {
    @org.jetbrains.annotations.Nullable
    private final fr.free.nrw.commons.upload.UploadResult uploadResult = null;
    private final int indexOfNextChunkToUpload = 0;
    private final int totalChunks = 0;
    @org.jetbrains.annotations.NotNull
    public static final fr.free.nrw.commons.contributions.ChunkInfo.CREATOR CREATOR = null;
    
    @org.jetbrains.annotations.NotNull
    public final fr.free.nrw.commons.contributions.ChunkInfo copy(@org.jetbrains.annotations.Nullable
    fr.free.nrw.commons.upload.UploadResult uploadResult, int indexOfNextChunkToUpload, int totalChunks) {
        return null;
    }
    
    @java.lang.Override
    public boolean equals(@org.jetbrains.annotations.Nullable
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override
    public int hashCode() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public java.lang.String toString() {
        return null;
    }
    
    public ChunkInfo(@org.jetbrains.annotations.Nullable
    fr.free.nrw.commons.upload.UploadResult uploadResult, int indexOfNextChunkToUpload, int totalChunks) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final fr.free.nrw.commons.upload.UploadResult component1() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final fr.free.nrw.commons.upload.UploadResult getUploadResult() {
        return null;
    }
    
    public final int component2() {
        return 0;
    }
    
    public final int getIndexOfNextChunkToUpload() {
        return 0;
    }
    
    public final int component3() {
        return 0;
    }
    
    public final int getTotalChunks() {
        return 0;
    }
    
    public ChunkInfo(@org.jetbrains.annotations.NotNull
    android.os.Parcel parcel) {
        super();
    }
    
    @java.lang.Override
    public void writeToParcel(@org.jetbrains.annotations.NotNull
    android.os.Parcel parcel, int flags) {
    }
    
    @java.lang.Override
    public int describeContents() {
        return 0;
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u001d\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016\u00a2\u0006\u0002\u0010\u000b\u00a8\u0006\f"}, d2 = {"Lfr/free/nrw/commons/contributions/ChunkInfo$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lfr/free/nrw/commons/contributions/ChunkInfo;", "()V", "createFromParcel", "parcel", "Landroid/os/Parcel;", "newArray", "", "size", "", "(I)[Lfr/free/nrw/commons/contributions/ChunkInfo;", "app-commons-v4.2.1-master_prodDebug"})
    public static final class CREATOR implements android.os.Parcelable.Creator<fr.free.nrw.commons.contributions.ChunkInfo> {
        
        private CREATOR() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        @java.lang.Override
        public fr.free.nrw.commons.contributions.ChunkInfo createFromParcel(@org.jetbrains.annotations.NotNull
        android.os.Parcel parcel) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        @java.lang.Override
        public fr.free.nrw.commons.contributions.ChunkInfo[] newArray(int size) {
            return null;
        }
    }
}