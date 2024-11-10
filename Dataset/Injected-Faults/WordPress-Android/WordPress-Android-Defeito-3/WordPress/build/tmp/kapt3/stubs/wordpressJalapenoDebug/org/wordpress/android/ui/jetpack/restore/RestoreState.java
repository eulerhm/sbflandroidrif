package org.wordpress.android.ui.jetpack.restore;

import java.lang.System;

@android.annotation.SuppressLint(value = {"ParcelCreator"})
@kotlinx.parcelize.Parcelize()
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001f\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u00012\u00020\u0002Bg\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\u001c\b\u0002\u0010\u0005\u001a\u0016\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007\u0018\u00010\u0006\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\b\u0012\b\b\u0002\u0010\u000f\u001a\u00020\t\u0012\b\b\u0002\u0010\u0010\u001a\u00020\t\u00a2\u0006\u0002\u0010\u0011J\u000b\u0010!\u001a\u0004\u0018\u00010\u0004H\u00c6\u0003J\u001d\u0010\"\u001a\u0016\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007\u0018\u00010\u0006H\u00c6\u0003J\u0010\u0010#\u001a\u0004\u0018\u00010\u000bH\u00c6\u0003\u00a2\u0006\u0002\u0010\u001aJ\u000b\u0010$\u001a\u0004\u0018\u00010\rH\u00c6\u0003J\u0010\u0010%\u001a\u0004\u0018\u00010\bH\u00c6\u0003\u00a2\u0006\u0002\u0010\u0013J\t\u0010&\u001a\u00020\tH\u00c6\u0003J\t\u0010\'\u001a\u00020\tH\u00c6\u0003Jp\u0010(\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u001c\b\u0002\u0010\u0005\u001a\u0016\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007\u0018\u00010\u00062\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\b2\b\b\u0002\u0010\u000f\u001a\u00020\t2\b\b\u0002\u0010\u0010\u001a\u00020\tH\u00c6\u0001\u00a2\u0006\u0002\u0010)J\t\u0010*\u001a\u00020\bH\u00d6\u0001J\u0013\u0010+\u001a\u00020\t2\b\u0010,\u001a\u0004\u0018\u00010-H\u00d6\u0003J\t\u0010.\u001a\u00020\bH\u00d6\u0001J\t\u0010/\u001a\u00020\u0004H\u00d6\u0001J\u0019\u00100\u001a\u0002012\u0006\u00102\u001a\u0002032\u0006\u00104\u001a\u00020\bH\u00d6\u0001R\u0015\u0010\u000e\u001a\u0004\u0018\u00010\b\u00a2\u0006\n\n\u0002\u0010\u0014\u001a\u0004\b\u0012\u0010\u0013R%\u0010\u0005\u001a\u0016\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007\u0018\u00010\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0013\u0010\f\u001a\u0004\u0018\u00010\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0015\u0010\n\u001a\u0004\u0018\u00010\u000b\u00a2\u0006\n\n\u0002\u0010\u001b\u001a\u0004\b\u0019\u0010\u001aR\u0013\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\u0010\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010\u000f\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u001f\u00a8\u00065"}, d2 = {"Lorg/wordpress/android/ui/jetpack/restore/RestoreState;", "Lorg/wordpress/android/util/wizard/WizardState;", "Landroid/os/Parcelable;", "rewindId", "", "optionsSelected", "", "Lkotlin/Pair;", "", "", "restoreId", "", "published", "Ljava/util/Date;", "errorType", "shouldInitProgress", "shouldInitDetails", "(Ljava/lang/String;Ljava/util/List;Ljava/lang/Long;Ljava/util/Date;Ljava/lang/Integer;ZZ)V", "getErrorType", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getOptionsSelected", "()Ljava/util/List;", "getPublished", "()Ljava/util/Date;", "getRestoreId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getRewindId", "()Ljava/lang/String;", "getShouldInitDetails", "()Z", "getShouldInitProgress", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "(Ljava/lang/String;Ljava/util/List;Ljava/lang/Long;Ljava/util/Date;Ljava/lang/Integer;ZZ)Lorg/wordpress/android/ui/jetpack/restore/RestoreState;", "describeContents", "equals", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "org.wordpress.android_wordpressJalapenoDebug"})
public final class RestoreState implements org.wordpress.android.util.wizard.WizardState, android.os.Parcelable {
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String rewindId = null;
    @org.jetbrains.annotations.Nullable()
    private final java.util.List<kotlin.Pair<java.lang.Integer, java.lang.Boolean>> optionsSelected = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Long restoreId = null;
    @org.jetbrains.annotations.Nullable()
    private final java.util.Date published = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Integer errorType = null;
    private final boolean shouldInitProgress = false;
    private final boolean shouldInitDetails = false;
    public static final android.os.Parcelable.Creator<org.wordpress.android.ui.jetpack.restore.RestoreState> CREATOR = null;
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.jetpack.restore.RestoreState copy(@org.jetbrains.annotations.Nullable()
    java.lang.String rewindId, @org.jetbrains.annotations.Nullable()
    java.util.List<kotlin.Pair<java.lang.Integer, java.lang.Boolean>> optionsSelected, @org.jetbrains.annotations.Nullable()
    java.lang.Long restoreId, @org.jetbrains.annotations.Nullable()
    java.util.Date published, @org.jetbrains.annotations.Nullable()
    java.lang.Integer errorType, boolean shouldInitProgress, boolean shouldInitDetails) {
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
    
    public RestoreState() {
        super();
    }
    
    public RestoreState(@org.jetbrains.annotations.Nullable()
    java.lang.String rewindId, @org.jetbrains.annotations.Nullable()
    java.util.List<kotlin.Pair<java.lang.Integer, java.lang.Boolean>> optionsSelected, @org.jetbrains.annotations.Nullable()
    java.lang.Long restoreId, @org.jetbrains.annotations.Nullable()
    java.util.Date published, @org.jetbrains.annotations.Nullable()
    java.lang.Integer errorType, boolean shouldInitProgress, boolean shouldInitDetails) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getRewindId() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.List<kotlin.Pair<java.lang.Integer, java.lang.Boolean>> component2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.List<kotlin.Pair<java.lang.Integer, java.lang.Boolean>> getOptionsSelected() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long component3() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long getRestoreId() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.Date component4() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.Date getPublished() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer component5() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getErrorType() {
        return null;
    }
    
    public final boolean component6() {
        return false;
    }
    
    public final boolean getShouldInitProgress() {
        return false;
    }
    
    public final boolean component7() {
        return false;
    }
    
    public final boolean getShouldInitDetails() {
        return false;
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
    public static final class Creator implements android.os.Parcelable.Creator<org.wordpress.android.ui.jetpack.restore.RestoreState> {
        
        public Creator() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public final org.wordpress.android.ui.jetpack.restore.RestoreState createFromParcel(@org.jetbrains.annotations.NotNull()
        android.os.Parcel in) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public final org.wordpress.android.ui.jetpack.restore.RestoreState[] newArray(int size) {
            return null;
        }
    }
}