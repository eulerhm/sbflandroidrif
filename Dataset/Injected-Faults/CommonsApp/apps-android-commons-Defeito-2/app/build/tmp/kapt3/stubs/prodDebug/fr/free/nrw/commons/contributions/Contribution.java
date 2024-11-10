package fr.free.nrw.commons.contributions;

import java.lang.System;

@kotlinx.android.parcel.Parcelize
@androidx.room.Entity(tableName = "contribution")
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\bR\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0087\b\u0018\u0000 |2\u00020\u0001:\u0001|B;\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0007\u0012\u0006\u0010\u000b\u001a\u00020\n\u00a2\u0006\u0002\u0010\fB\u00e9\u0001\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\b\b\u0002\u0010\u000f\u001a\u00020\n\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0011\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0013\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0017\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0019\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001c\u0012\b\b\u0002\u0010\u001d\u001a\u00020\u0013\u0012\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f\u0012\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u001f\u0012\b\b\u0002\u0010\"\u001a\u00020\u0011\u0012\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u001c\u0012\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n\u0012\b\b\u0002\u0010%\u001a\u00020\u0011\u00a2\u0006\u0002\u0010&J\u000e\u0010Z\u001a\u00020\u00002\u0006\u0010\r\u001a\u00020\u000eJ\t\u0010[\u001a\u00020\u000eH\u00c6\u0003J\u000b\u0010\\\u001a\u0004\u0018\u00010\nH\u00c6\u0003J\u000b\u0010]\u001a\u0004\u0018\u00010\u001cH\u00c6\u0003J\t\u0010^\u001a\u00020\u0013H\u00c6\u0003J\u000b\u0010_\u001a\u0004\u0018\u00010\u001fH\u00c6\u0003J\u000b\u0010`\u001a\u0004\u0018\u00010\nH\u00c6\u0003J\u000b\u0010a\u001a\u0004\u0018\u00010\u001fH\u00c6\u0003J\t\u0010b\u001a\u00020\u0011H\u00c6\u0003J\u000b\u0010c\u001a\u0004\u0018\u00010\u001cH\u00c6\u0003J\u000b\u0010d\u001a\u0004\u0018\u00010\nH\u00c6\u0003J\u000b\u0010e\u001a\u0004\u0018\u00010\nH\u00c6\u0003J\t\u0010f\u001a\u00020\nH\u00c6\u0003J\t\u0010g\u001a\u00020\u0011H\u00c6\u0003J\t\u0010h\u001a\u00020\u0011H\u00c6\u0003J\t\u0010i\u001a\u00020\u0013H\u00c6\u0003J\u000b\u0010j\u001a\u0004\u0018\u00010\nH\u00c6\u0003J\u000b\u0010k\u001a\u0004\u0018\u00010\nH\u00c6\u0003J\u000b\u0010l\u001a\u0004\u0018\u00010\u0017H\u00c6\u0003J\u000b\u0010m\u001a\u0004\u0018\u00010\u0019H\u00c6\u0003J\u000f\u0010n\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u00c6\u0003J\u00ef\u0001\u0010o\u001a\u00020\u00002\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\n2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00172\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\b\b\u0002\u0010\u001d\u001a\u00020\u00132\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u001f2\b\b\u0002\u0010\"\u001a\u00020\u00112\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u001c2\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010%\u001a\u00020\u0011H\u00c6\u0001J\t\u0010p\u001a\u00020\u0011H\u00d6\u0001J\u0013\u0010q\u001a\u00020r2\b\u0010s\u001a\u0004\u0018\u00010tH\u00d6\u0003J\u0006\u0010\"\u001a\u00020rJ\t\u0010u\u001a\u00020\u0011H\u00d6\u0001J\u000e\u0010F\u001a\u00020v2\u0006\u0010\"\u001a\u00020rJ\t\u0010w\u001a\u00020\nH\u00d6\u0001J\u0019\u0010x\u001a\u00020v2\u0006\u0010y\u001a\u00020z2\u0006\u0010{\u001a\u00020\u0011H\u00d6\u0001R\u001c\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\'\u0010(\"\u0004\b)\u0010*R\u001c\u0010#\u001a\u0004\u0018\u00010\u001cX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R\u001c\u0010$\u001a\u0004\u0018\u00010\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b/\u00100\"\u0004\b1\u00102R\u001a\u0010\u001d\u001a\u00020\u0013X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b3\u00104\"\u0004\b5\u00106R\u001c\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b7\u00108\"\u0004\b9\u0010:R\u001c\u0010\u0015\u001a\u0004\u0018\u00010\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b;\u00100\"\u0004\b<\u00102R\u001c\u0010 \u001a\u0004\u0018\u00010\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b=\u00100\"\u0004\b>\u00102R\u001c\u0010!\u001a\u0004\u0018\u00010\u001fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b?\u00108\"\u0004\b@\u0010:R\u0013\u0010\u0014\u001a\u0004\u0018\u00010\n\u00a2\u0006\b\n\u0000\u001a\u0004\bA\u00100R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\bB\u0010CR\u001a\u0010\"\u001a\u00020\u0011X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bD\u0010E\"\u0004\bF\u0010GR\u001c\u0010\u000b\u001a\u0004\u0018\u00010\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bH\u00100\"\u0004\bI\u00102R\u0013\u0010\u001b\u001a\u0004\u0018\u00010\u001c\u00a2\u0006\b\n\u0000\u001a\u0004\bJ\u0010,R\u0016\u0010\r\u001a\u00020\u000e8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\bK\u0010LR\u001c\u0010\u001a\u001a\u0004\u0018\u00010\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bM\u00100\"\u0004\bN\u00102R\u0016\u0010\u000f\u001a\u00020\n8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\bO\u00100R\u001a\u0010%\u001a\u00020\u0011X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bP\u0010E\"\u0004\bQ\u0010GR\u001a\u0010\u0010\u001a\u00020\u0011X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bR\u0010E\"\u0004\bS\u0010GR\u001a\u0010\u0012\u001a\u00020\u0013X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bT\u00104\"\u0004\bU\u00106R\u001c\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bV\u0010W\"\u0004\bX\u0010Y\u00a8\u0006}"}, d2 = {"Lfr/free/nrw/commons/contributions/Contribution;", "Landroid/os/Parcelable;", "item", "Lfr/free/nrw/commons/upload/UploadItem;", "sessionManager", "Lfr/free/nrw/commons/auth/SessionManager;", "depictedItems", "", "Lfr/free/nrw/commons/upload/structure/depictions/DepictedItem;", "categories", "", "imageSHA1", "(Lfr/free/nrw/commons/upload/UploadItem;Lfr/free/nrw/commons/auth/SessionManager;Ljava/util/List;Ljava/util/List;Ljava/lang/String;)V", "media", "Lfr/free/nrw/commons/Media;", "pageId", "state", "", "transferred", "", "decimalCoords", "dateCreatedSource", "wikidataPlace", "Lfr/free/nrw/commons/upload/WikidataPlace;", "chunkInfo", "Lfr/free/nrw/commons/contributions/ChunkInfo;", "mimeType", "localUri", "Landroid/net/Uri;", "dataLength", "dateCreated", "Ljava/util/Date;", "dateCreatedString", "dateModified", "hasInvalidLocation", "contentUri", "countryCode", "retries", "(Lfr/free/nrw/commons/Media;Ljava/lang/String;IJLjava/lang/String;Ljava/lang/String;Lfr/free/nrw/commons/upload/WikidataPlace;Lfr/free/nrw/commons/contributions/ChunkInfo;Ljava/util/List;Ljava/lang/String;Landroid/net/Uri;JLjava/util/Date;Ljava/lang/String;Ljava/util/Date;ILandroid/net/Uri;Ljava/lang/String;Ljava/lang/String;I)V", "getChunkInfo", "()Lfr/free/nrw/commons/contributions/ChunkInfo;", "setChunkInfo", "(Lfr/free/nrw/commons/contributions/ChunkInfo;)V", "getContentUri", "()Landroid/net/Uri;", "setContentUri", "(Landroid/net/Uri;)V", "getCountryCode", "()Ljava/lang/String;", "setCountryCode", "(Ljava/lang/String;)V", "getDataLength", "()J", "setDataLength", "(J)V", "getDateCreated", "()Ljava/util/Date;", "setDateCreated", "(Ljava/util/Date;)V", "getDateCreatedSource", "setDateCreatedSource", "getDateCreatedString", "setDateCreatedString", "getDateModified", "setDateModified", "getDecimalCoords", "getDepictedItems", "()Ljava/util/List;", "getHasInvalidLocation", "()I", "setHasInvalidLocation", "(I)V", "getImageSHA1", "setImageSHA1", "getLocalUri", "getMedia", "()Lfr/free/nrw/commons/Media;", "getMimeType", "setMimeType", "getPageId", "getRetries", "setRetries", "getState", "setState", "getTransferred", "setTransferred", "getWikidataPlace", "()Lfr/free/nrw/commons/upload/WikidataPlace;", "setWikidataPlace", "(Lfr/free/nrw/commons/upload/WikidataPlace;)V", "completeWith", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "describeContents", "equals", "", "other", "", "hashCode", "", "toString", "writeToParcel", "parcel", "Landroid/os/Parcel;", "flags", "Companion", "app-commons-v4.2.1-master_prodDebug"})
public final class Contribution implements android.os.Parcelable {
    @org.jetbrains.annotations.NotNull
    @androidx.room.Embedded(prefix = "media_")
    private final fr.free.nrw.commons.Media media = null;
    @org.jetbrains.annotations.NotNull
    @androidx.room.PrimaryKey
    private final java.lang.String pageId = null;
    private int state;
    private long transferred;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String decimalCoords = null;
    @org.jetbrains.annotations.Nullable
    private java.lang.String dateCreatedSource;
    @org.jetbrains.annotations.Nullable
    private fr.free.nrw.commons.upload.WikidataPlace wikidataPlace;
    @org.jetbrains.annotations.Nullable
    private fr.free.nrw.commons.contributions.ChunkInfo chunkInfo;
    
    /**
     * Each depiction loaded in depictions activity is associated with a wikidata entity id, this Id
     * is in turn used to upload depictions to wikibase
     */
    @org.jetbrains.annotations.NotNull
    private final java.util.List<fr.free.nrw.commons.upload.structure.depictions.DepictedItem> depictedItems = null;
    @org.jetbrains.annotations.Nullable
    private java.lang.String mimeType;
    @org.jetbrains.annotations.Nullable
    private final android.net.Uri localUri = null;
    private long dataLength;
    @org.jetbrains.annotations.Nullable
    private java.util.Date dateCreated;
    @org.jetbrains.annotations.Nullable
    private java.lang.String dateCreatedString;
    @org.jetbrains.annotations.Nullable
    private java.util.Date dateModified;
    private int hasInvalidLocation;
    @org.jetbrains.annotations.Nullable
    private android.net.Uri contentUri;
    @org.jetbrains.annotations.Nullable
    private java.lang.String countryCode;
    @org.jetbrains.annotations.Nullable
    private java.lang.String imageSHA1;
    
    /**
     * Number of times a contribution has been retried after a failure
     */
    private int retries;
    @org.jetbrains.annotations.NotNull
    public static final fr.free.nrw.commons.contributions.Contribution.Companion Companion = null;
    public static final int STATE_COMPLETED = -1;
    public static final int STATE_FAILED = 1;
    public static final int STATE_QUEUED = 2;
    public static final int STATE_IN_PROGRESS = 3;
    public static final int STATE_PAUSED = 4;
    public static final int STATE_QUEUED_LIMITED_CONNECTION_MODE = 5;
    public static final android.os.Parcelable.Creator<fr.free.nrw.commons.contributions.Contribution> CREATOR = null;
    
    @org.jetbrains.annotations.NotNull
    public final fr.free.nrw.commons.contributions.Contribution copy(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.Media media, @org.jetbrains.annotations.NotNull
    java.lang.String pageId, int state, long transferred, @org.jetbrains.annotations.Nullable
    java.lang.String decimalCoords, @org.jetbrains.annotations.Nullable
    java.lang.String dateCreatedSource, @org.jetbrains.annotations.Nullable
    fr.free.nrw.commons.upload.WikidataPlace wikidataPlace, @org.jetbrains.annotations.Nullable
    fr.free.nrw.commons.contributions.ChunkInfo chunkInfo, @org.jetbrains.annotations.NotNull
    java.util.List<fr.free.nrw.commons.upload.structure.depictions.DepictedItem> depictedItems, @org.jetbrains.annotations.Nullable
    java.lang.String mimeType, @org.jetbrains.annotations.Nullable
    android.net.Uri localUri, long dataLength, @org.jetbrains.annotations.Nullable
    java.util.Date dateCreated, @org.jetbrains.annotations.Nullable
    java.lang.String dateCreatedString, @org.jetbrains.annotations.Nullable
    java.util.Date dateModified, int hasInvalidLocation, @org.jetbrains.annotations.Nullable
    android.net.Uri contentUri, @org.jetbrains.annotations.Nullable
    java.lang.String countryCode, @org.jetbrains.annotations.Nullable
    java.lang.String imageSHA1, int retries) {
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
    
    public Contribution(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.Media media, @org.jetbrains.annotations.NotNull
    java.lang.String pageId, int state, long transferred, @org.jetbrains.annotations.Nullable
    java.lang.String decimalCoords, @org.jetbrains.annotations.Nullable
    java.lang.String dateCreatedSource, @org.jetbrains.annotations.Nullable
    fr.free.nrw.commons.upload.WikidataPlace wikidataPlace, @org.jetbrains.annotations.Nullable
    fr.free.nrw.commons.contributions.ChunkInfo chunkInfo, @org.jetbrains.annotations.NotNull
    java.util.List<fr.free.nrw.commons.upload.structure.depictions.DepictedItem> depictedItems, @org.jetbrains.annotations.Nullable
    java.lang.String mimeType, @org.jetbrains.annotations.Nullable
    android.net.Uri localUri, long dataLength, @org.jetbrains.annotations.Nullable
    java.util.Date dateCreated, @org.jetbrains.annotations.Nullable
    java.lang.String dateCreatedString, @org.jetbrains.annotations.Nullable
    java.util.Date dateModified, int hasInvalidLocation, @org.jetbrains.annotations.Nullable
    android.net.Uri contentUri, @org.jetbrains.annotations.Nullable
    java.lang.String countryCode, @org.jetbrains.annotations.Nullable
    java.lang.String imageSHA1, int retries) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final fr.free.nrw.commons.Media component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final fr.free.nrw.commons.Media getMedia() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getPageId() {
        return null;
    }
    
    public final int component3() {
        return 0;
    }
    
    public final int getState() {
        return 0;
    }
    
    public final void setState(int p0) {
    }
    
    public final long component4() {
        return 0L;
    }
    
    public final long getTransferred() {
        return 0L;
    }
    
    public final void setTransferred(long p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getDecimalCoords() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getDateCreatedSource() {
        return null;
    }
    
    public final void setDateCreatedSource(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final fr.free.nrw.commons.upload.WikidataPlace component7() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final fr.free.nrw.commons.upload.WikidataPlace getWikidataPlace() {
        return null;
    }
    
    public final void setWikidataPlace(@org.jetbrains.annotations.Nullable
    fr.free.nrw.commons.upload.WikidataPlace p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final fr.free.nrw.commons.contributions.ChunkInfo component8() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final fr.free.nrw.commons.contributions.ChunkInfo getChunkInfo() {
        return null;
    }
    
    public final void setChunkInfo(@org.jetbrains.annotations.Nullable
    fr.free.nrw.commons.contributions.ChunkInfo p0) {
    }
    
    /**
     * Each depiction loaded in depictions activity is associated with a wikidata entity id, this Id
     * is in turn used to upload depictions to wikibase
     */
    @org.jetbrains.annotations.NotNull
    public final java.util.List<fr.free.nrw.commons.upload.structure.depictions.DepictedItem> component9() {
        return null;
    }
    
    /**
     * Each depiction loaded in depictions activity is associated with a wikidata entity id, this Id
     * is in turn used to upload depictions to wikibase
     */
    @org.jetbrains.annotations.NotNull
    public final java.util.List<fr.free.nrw.commons.upload.structure.depictions.DepictedItem> getDepictedItems() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component10() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getMimeType() {
        return null;
    }
    
    public final void setMimeType(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final android.net.Uri component11() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final android.net.Uri getLocalUri() {
        return null;
    }
    
    public final long component12() {
        return 0L;
    }
    
    public final long getDataLength() {
        return 0L;
    }
    
    public final void setDataLength(long p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.util.Date component13() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.util.Date getDateCreated() {
        return null;
    }
    
    public final void setDateCreated(@org.jetbrains.annotations.Nullable
    java.util.Date p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component14() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getDateCreatedString() {
        return null;
    }
    
    public final void setDateCreatedString(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.util.Date component15() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.util.Date getDateModified() {
        return null;
    }
    
    public final void setDateModified(@org.jetbrains.annotations.Nullable
    java.util.Date p0) {
    }
    
    public final int component16() {
        return 0;
    }
    
    public final int getHasInvalidLocation() {
        return 0;
    }
    
    public final void setHasInvalidLocation(int p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final android.net.Uri component17() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final android.net.Uri getContentUri() {
        return null;
    }
    
    public final void setContentUri(@org.jetbrains.annotations.Nullable
    android.net.Uri p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component18() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getCountryCode() {
        return null;
    }
    
    public final void setCountryCode(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component19() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getImageSHA1() {
        return null;
    }
    
    public final void setImageSHA1(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    /**
     * Number of times a contribution has been retried after a failure
     */
    public final int component20() {
        return 0;
    }
    
    /**
     * Number of times a contribution has been retried after a failure
     */
    public final int getRetries() {
        return 0;
    }
    
    /**
     * Number of times a contribution has been retried after a failure
     */
    public final void setRetries(int p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final fr.free.nrw.commons.contributions.Contribution completeWith(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.Media media) {
        return null;
    }
    
    public Contribution(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.upload.UploadItem item, @org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.auth.SessionManager sessionManager, @org.jetbrains.annotations.NotNull
    java.util.List<fr.free.nrw.commons.upload.structure.depictions.DepictedItem> depictedItems, @org.jetbrains.annotations.NotNull
    java.util.List<java.lang.String> categories, @org.jetbrains.annotations.NotNull
    java.lang.String imageSHA1) {
        super();
    }
    
    /**
     * Set this true when ImageProcessor has said that the location is invalid
     * @param hasInvalidLocation
     */
    public final void setHasInvalidLocation(boolean hasInvalidLocation) {
    }
    
    public final boolean hasInvalidLocation() {
        return false;
    }
    
    @java.lang.Override
    public int describeContents() {
        return 0;
    }
    
    @java.lang.Override
    public void writeToParcel(@org.jetbrains.annotations.NotNull
    android.os.Parcel parcel, int flags) {
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 3)
    public static final class Creator implements android.os.Parcelable.Creator<fr.free.nrw.commons.contributions.Contribution> {
        
        public Creator() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        @java.lang.Override
        public final fr.free.nrw.commons.contributions.Contribution createFromParcel(@org.jetbrains.annotations.NotNull
        android.os.Parcel in) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        @java.lang.Override
        public final fr.free.nrw.commons.contributions.Contribution[] newArray(int size) {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J \u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0\u000b2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eJ\u0014\u0010\u0010\u001a\u00020\f2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lfr/free/nrw/commons/contributions/Contribution$Companion;", "", "()V", "STATE_COMPLETED", "", "STATE_FAILED", "STATE_IN_PROGRESS", "STATE_PAUSED", "STATE_QUEUED", "STATE_QUEUED_LIMITED_CONNECTION_MODE", "formatCaptions", "", "", "uploadMediaDetails", "", "Lfr/free/nrw/commons/upload/UploadMediaDetail;", "formatDescriptions", "descriptions", "app-commons-v4.2.1-master_prodDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        /**
         * Formatting captions to the Wikibase format for sending labels
         * @param uploadMediaDetails list of media Details
         */
        @org.jetbrains.annotations.NotNull
        public final java.util.Map<java.lang.String, java.lang.String> formatCaptions(@org.jetbrains.annotations.NotNull
        java.util.List<fr.free.nrw.commons.upload.UploadMediaDetail> uploadMediaDetails) {
            return null;
        }
        
        /**
         * Formats the list of descriptions into the format Commons requires for uploads.
         *
         * @param descriptions the list of descriptions, description is ignored if text is null.
         * @return a string with the pattern of {{en|1=descriptionText}}
         */
        @org.jetbrains.annotations.NotNull
        public final java.lang.String formatDescriptions(@org.jetbrains.annotations.NotNull
        java.util.List<fr.free.nrw.commons.upload.UploadMediaDetail> descriptions) {
            return null;
        }
    }
}