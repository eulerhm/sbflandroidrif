package org.wordpress.android.ui.jetpack.backup.download;

import java.lang.System;

@android.annotation.SuppressLint(value = {"ParcelCreator"})
@kotlinx.parcelize.Parcelize()
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0019\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u00012\u00020\u0002BY\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r\u00a2\u0006\u0002\u0010\u000eJ\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u0004H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0012J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003J\u0010\u0010\u001f\u001a\u0004\u0018\u00010\u0004H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0012J\u000b\u0010 \u001a\u0004\u0018\u00010\nH\u00c6\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003J\u0010\u0010\"\u001a\u0004\u0018\u00010\rH\u00c6\u0003\u00a2\u0006\u0002\u0010\u0015Jb\u0010#\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\rH\u00c6\u0001\u00a2\u0006\u0002\u0010$J\t\u0010%\u001a\u00020\rH\u00d6\u0001J\u0013\u0010&\u001a\u00020\'2\b\u0010(\u001a\u0004\u0018\u00010)H\u00d6\u0003J\t\u0010*\u001a\u00020\rH\u00d6\u0001J\t\u0010+\u001a\u00020\u0006H\u00d6\u0001J\u0019\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020/2\u0006\u00100\u001a\u00020\rH\u00d6\u0001R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0015\u0010\b\u001a\u0004\u0018\u00010\u0004\u00a2\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u0011\u0010\u0012R\u0015\u0010\f\u001a\u0004\u0018\u00010\r\u00a2\u0006\n\n\u0002\u0010\u0016\u001a\u0004\b\u0014\u0010\u0015R\u0013\u0010\t\u001a\u0004\u0018\u00010\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0010R\u0015\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u00a2\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u001a\u0010\u0012R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0010\u00a8\u00061"}, d2 = {"Lorg/wordpress/android/ui/jetpack/backup/download/BackupDownloadState;", "Lorg/wordpress/android/util/wizard/WizardState;", "Landroid/os/Parcelable;", "siteId", "", "activityId", "", "rewindId", "downloadId", "published", "Ljava/util/Date;", "url", "errorType", "", "(Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/util/Date;Ljava/lang/String;Ljava/lang/Integer;)V", "getActivityId", "()Ljava/lang/String;", "getDownloadId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getErrorType", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getPublished", "()Ljava/util/Date;", "getRewindId", "getSiteId", "getUrl", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "(Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/util/Date;Ljava/lang/String;Ljava/lang/Integer;)Lorg/wordpress/android/ui/jetpack/backup/download/BackupDownloadState;", "describeContents", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "org.wordpress.android_wordpressJalapenoDebug"})
public final class BackupDownloadState implements org.wordpress.android.util.wizard.WizardState, android.os.Parcelable {
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Long siteId = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String activityId = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String rewindId = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Long downloadId = null;
    @org.jetbrains.annotations.Nullable()
    private final java.util.Date published = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String url = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Integer errorType = null;
    public static final android.os.Parcelable.Creator<org.wordpress.android.ui.jetpack.backup.download.BackupDownloadState> CREATOR = null;
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.jetpack.backup.download.BackupDownloadState copy(@org.jetbrains.annotations.Nullable()
    java.lang.Long siteId, @org.jetbrains.annotations.Nullable()
    java.lang.String activityId, @org.jetbrains.annotations.Nullable()
    java.lang.String rewindId, @org.jetbrains.annotations.Nullable()
    java.lang.Long downloadId, @org.jetbrains.annotations.Nullable()
    java.util.Date published, @org.jetbrains.annotations.Nullable()
    java.lang.String url, @org.jetbrains.annotations.Nullable()
    java.lang.Integer errorType) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String toString() {
        return null;
    }
    
    public BackupDownloadState() {
        super();
    }
    
    public BackupDownloadState(@org.jetbrains.annotations.Nullable()
    java.lang.Long siteId, @org.jetbrains.annotations.Nullable()
    java.lang.String activityId, @org.jetbrains.annotations.Nullable()
    java.lang.String rewindId, @org.jetbrains.annotations.Nullable()
    java.lang.Long downloadId, @org.jetbrains.annotations.Nullable()
    java.util.Date published, @org.jetbrains.annotations.Nullable()
    java.lang.String url, @org.jetbrains.annotations.Nullable()
    java.lang.Integer errorType) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long component1() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long getSiteId() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getActivityId() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getRewindId() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long component4() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long getDownloadId() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.Date component5() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.Date getPublished() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getUrl() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer component7() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getErrorType() {
        return null;
    }
    
    @java.lang.Override()
    public int describeContents() {
        return 0;
    }
    
    @java.lang.Override()
    public void writeToParcel(@org.jetbrains.annotations.NotNull()
    android.os.Parcel parcel, int flags) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 3)
    public static final class Creator implements android.os.Parcelable.Creator<org.wordpress.android.ui.jetpack.backup.download.BackupDownloadState> {
        
        public Creator() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public final org.wordpress.android.ui.jetpack.backup.download.BackupDownloadState createFromParcel(@org.jetbrains.annotations.NotNull()
        android.os.Parcel in) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public final org.wordpress.android.ui.jetpack.backup.download.BackupDownloadState[] newArray(int size) {
            return null;
        }
    }
}