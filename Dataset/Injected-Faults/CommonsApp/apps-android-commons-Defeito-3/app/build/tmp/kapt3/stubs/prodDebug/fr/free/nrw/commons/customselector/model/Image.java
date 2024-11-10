package fr.free.nrw.commons.customselector.model;

import java.lang.System;

/**
 * Custom selector data class Image.
 */
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b&\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0086\b\u0018\u0000 ;2\u00020\u0001:\u0001;B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004BM\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\b\u0012\b\b\u0002\u0010\f\u001a\u00020\u0006\u0012\b\b\u0002\u0010\r\u001a\u00020\b\u0012\b\b\u0002\u0010\u000e\u001a\u00020\b\u0012\b\b\u0002\u0010\u000f\u001a\u00020\b\u00a2\u0006\u0002\u0010\u0010J\t\u0010\'\u001a\u00020\u0006H\u00c6\u0003J\t\u0010(\u001a\u00020\bH\u00c6\u0003J\t\u0010)\u001a\u00020\nH\u00c6\u0003J\t\u0010*\u001a\u00020\bH\u00c6\u0003J\t\u0010+\u001a\u00020\u0006H\u00c6\u0003J\t\u0010,\u001a\u00020\bH\u00c6\u0003J\t\u0010-\u001a\u00020\bH\u00c6\u0003J\t\u0010.\u001a\u00020\bH\u00c6\u0003JY\u0010/\u001a\u00020\u00002\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\b2\b\b\u0002\u0010\f\u001a\u00020\u00062\b\b\u0002\u0010\r\u001a\u00020\b2\b\b\u0002\u0010\u000e\u001a\u00020\b2\b\b\u0002\u0010\u000f\u001a\u00020\bH\u00c6\u0001J\b\u00100\u001a\u000201H\u0016J\u0013\u00102\u001a\u0002032\b\u00104\u001a\u0004\u0018\u000105H\u0096\u0002J\t\u00106\u001a\u000201H\u00d6\u0001J\t\u00107\u001a\u00020\bH\u00d6\u0001J\u0018\u00108\u001a\u0002092\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010:\u001a\u000201H\u0016R\u001a\u0010\f\u001a\u00020\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\r\u001a\u00020\bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001a\u0010\u000f\u001a\u00020\bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0016\"\u0004\b\u001a\u0010\u0018R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0012\"\u0004\b\u001c\u0010\u0014R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0016\"\u0004\b\u001e\u0010\u0018R\u001a\u0010\u000b\u001a\u00020\bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0016\"\u0004\b \u0010\u0018R\u001a\u0010\u000e\u001a\u00020\bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u0016\"\u0004\b\"\u0010\u0018R\u001a\u0010\t\u001a\u00020\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&\u00a8\u0006<"}, d2 = {"Lfr/free/nrw/commons/customselector/model/Image;", "Landroid/os/Parcelable;", "parcel", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "id", "", "name", "", "uri", "Landroid/net/Uri;", "path", "bucketId", "bucketName", "sha1", "date", "(JLjava/lang/String;Landroid/net/Uri;Ljava/lang/String;JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getBucketId", "()J", "setBucketId", "(J)V", "getBucketName", "()Ljava/lang/String;", "setBucketName", "(Ljava/lang/String;)V", "getDate", "setDate", "getId", "setId", "getName", "setName", "getPath", "setPath", "getSha1", "setSha1", "getUri", "()Landroid/net/Uri;", "setUri", "(Landroid/net/Uri;)V", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "flags", "CREATOR", "app-commons-v4.2.1-master_prodDebug"})
public final class Image implements android.os.Parcelable {
    
    /**
     * id : Unique image id, primary key of image in device, eg 104950
     */
    private long id;
    
    /**
     * name : Name of the image with extension, eg CommonsLogo.jpeg
     */
    @org.jetbrains.annotations.NotNull
    private java.lang.String name;
    
    /**
     * uri : Uri of the image, points to image location or name, eg content://media/external/images/camera/10495 (Android 10)
     */
    @org.jetbrains.annotations.NotNull
    private android.net.Uri uri;
    
    /**
     * path : System path of the image, eg storage/emulated/0/camera/CommonsLogo.jpeg
     */
    @org.jetbrains.annotations.NotNull
    private java.lang.String path;
    
    /**
     * bucketId : bucketId of folder, eg 540528482
     */
    private long bucketId;
    
    /**
     * bucketName : name of folder, eg Camera
     */
    @org.jetbrains.annotations.NotNull
    private java.lang.String bucketName;
    
    /**
     * sha1 : sha1 of original image.
     */
    @org.jetbrains.annotations.NotNull
    private java.lang.String sha1;
    
    /**
     * date: Creation date of the image to show it inside the bubble during bubble scroll.
     */
    @org.jetbrains.annotations.NotNull
    private java.lang.String date;
    @org.jetbrains.annotations.NotNull
    public static final fr.free.nrw.commons.customselector.model.Image.CREATOR CREATOR = null;
    
    /**
     * Custom selector data class Image.
     */
    @org.jetbrains.annotations.NotNull
    public final fr.free.nrw.commons.customselector.model.Image copy(long id, @org.jetbrains.annotations.NotNull
    java.lang.String name, @org.jetbrains.annotations.NotNull
    android.net.Uri uri, @org.jetbrains.annotations.NotNull
    java.lang.String path, long bucketId, @org.jetbrains.annotations.NotNull
    java.lang.String bucketName, @org.jetbrains.annotations.NotNull
    java.lang.String sha1, @org.jetbrains.annotations.NotNull
    java.lang.String date) {
        return null;
    }
    
    /**
     * Custom selector data class Image.
     */
    @java.lang.Override
    public int hashCode() {
        return 0;
    }
    
    /**
     * Custom selector data class Image.
     */
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public java.lang.String toString() {
        return null;
    }
    
    public Image(long id, @org.jetbrains.annotations.NotNull
    java.lang.String name, @org.jetbrains.annotations.NotNull
    android.net.Uri uri, @org.jetbrains.annotations.NotNull
    java.lang.String path, long bucketId, @org.jetbrains.annotations.NotNull
    java.lang.String bucketName, @org.jetbrains.annotations.NotNull
    java.lang.String sha1, @org.jetbrains.annotations.NotNull
    java.lang.String date) {
        super();
    }
    
    /**
     * id : Unique image id, primary key of image in device, eg 104950
     */
    public final long component1() {
        return 0L;
    }
    
    /**
     * id : Unique image id, primary key of image in device, eg 104950
     */
    public final long getId() {
        return 0L;
    }
    
    /**
     * id : Unique image id, primary key of image in device, eg 104950
     */
    public final void setId(long p0) {
    }
    
    /**
     * name : Name of the image with extension, eg CommonsLogo.jpeg
     */
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component2() {
        return null;
    }
    
    /**
     * name : Name of the image with extension, eg CommonsLogo.jpeg
     */
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getName() {
        return null;
    }
    
    /**
     * name : Name of the image with extension, eg CommonsLogo.jpeg
     */
    public final void setName(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
    
    /**
     * uri : Uri of the image, points to image location or name, eg content://media/external/images/camera/10495 (Android 10)
     */
    @org.jetbrains.annotations.NotNull
    public final android.net.Uri component3() {
        return null;
    }
    
    /**
     * uri : Uri of the image, points to image location or name, eg content://media/external/images/camera/10495 (Android 10)
     */
    @org.jetbrains.annotations.NotNull
    public final android.net.Uri getUri() {
        return null;
    }
    
    /**
     * uri : Uri of the image, points to image location or name, eg content://media/external/images/camera/10495 (Android 10)
     */
    public final void setUri(@org.jetbrains.annotations.NotNull
    android.net.Uri p0) {
    }
    
    /**
     * path : System path of the image, eg storage/emulated/0/camera/CommonsLogo.jpeg
     */
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component4() {
        return null;
    }
    
    /**
     * path : System path of the image, eg storage/emulated/0/camera/CommonsLogo.jpeg
     */
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getPath() {
        return null;
    }
    
    /**
     * path : System path of the image, eg storage/emulated/0/camera/CommonsLogo.jpeg
     */
    public final void setPath(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
    
    /**
     * bucketId : bucketId of folder, eg 540528482
     */
    public final long component5() {
        return 0L;
    }
    
    /**
     * bucketId : bucketId of folder, eg 540528482
     */
    public final long getBucketId() {
        return 0L;
    }
    
    /**
     * bucketId : bucketId of folder, eg 540528482
     */
    public final void setBucketId(long p0) {
    }
    
    /**
     * bucketName : name of folder, eg Camera
     */
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component6() {
        return null;
    }
    
    /**
     * bucketName : name of folder, eg Camera
     */
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getBucketName() {
        return null;
    }
    
    /**
     * bucketName : name of folder, eg Camera
     */
    public final void setBucketName(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
    
    /**
     * sha1 : sha1 of original image.
     */
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component7() {
        return null;
    }
    
    /**
     * sha1 : sha1 of original image.
     */
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getSha1() {
        return null;
    }
    
    /**
     * sha1 : sha1 of original image.
     */
    public final void setSha1(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
    
    /**
     * date: Creation date of the image to show it inside the bubble during bubble scroll.
     */
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component8() {
        return null;
    }
    
    /**
     * date: Creation date of the image to show it inside the bubble during bubble scroll.
     */
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getDate() {
        return null;
    }
    
    /**
     * date: Creation date of the image to show it inside the bubble during bubble scroll.
     */
    public final void setDate(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
    
    /**
     * default parcelable constructor.
     */
    public Image(@org.jetbrains.annotations.NotNull
    android.os.Parcel parcel) {
        super();
    }
    
    /**
     * Write to parcel method.
     */
    @java.lang.Override
    public void writeToParcel(@org.jetbrains.annotations.NotNull
    android.os.Parcel parcel, int flags) {
    }
    
    /**
     * Describe the kinds of special objects contained in this Parcelable
     */
    @java.lang.Override
    public int describeContents() {
        return 0;
    }
    
    /**
     * Indicates whether some other object is "equal to" this one.
     */
    @java.lang.Override
    public boolean equals(@org.jetbrains.annotations.Nullable
    java.lang.Object other) {
        return false;
    }
    
    /**
     * Parcelable companion object
     */
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u001d\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016\u00a2\u0006\u0002\u0010\u000b\u00a8\u0006\f"}, d2 = {"Lfr/free/nrw/commons/customselector/model/Image$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lfr/free/nrw/commons/customselector/model/Image;", "()V", "createFromParcel", "parcel", "Landroid/os/Parcel;", "newArray", "", "size", "", "(I)[Lfr/free/nrw/commons/customselector/model/Image;", "app-commons-v4.2.1-master_prodDebug"})
    public static final class CREATOR implements android.os.Parcelable.Creator<fr.free.nrw.commons.customselector.model.Image> {
        
        private CREATOR() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        @java.lang.Override
        public fr.free.nrw.commons.customselector.model.Image createFromParcel(@org.jetbrains.annotations.NotNull
        android.os.Parcel parcel) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        @java.lang.Override
        public fr.free.nrw.commons.customselector.model.Image[] newArray(int size) {
            return null;
        }
    }
}