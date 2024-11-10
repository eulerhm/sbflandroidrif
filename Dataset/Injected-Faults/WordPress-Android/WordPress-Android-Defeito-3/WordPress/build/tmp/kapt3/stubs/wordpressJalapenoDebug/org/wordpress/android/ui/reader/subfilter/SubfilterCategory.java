package org.wordpress.android.ui.reader.subfilter;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0001\u0018\u0000 \u00142\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002:\u0001\u0014B\u0019\b\u0002\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\b\u0010\f\u001a\u00020\u0004H\u0016J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0004H\u0016R\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bj\u0002\b\u0012j\u0002\b\u0013\u00a8\u0006\u0015"}, d2 = {"Lorg/wordpress/android/ui/reader/subfilter/SubfilterCategory;", "", "Landroid/os/Parcelable;", "titleRes", "", "type", "Lorg/wordpress/android/ui/reader/subfilter/SubfilterListItem$ItemType;", "(Ljava/lang/String;IILorg/wordpress/android/ui/reader/subfilter/SubfilterListItem$ItemType;)V", "getTitleRes", "()I", "getType", "()Lorg/wordpress/android/ui/reader/subfilter/SubfilterListItem$ItemType;", "describeContents", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "SITES", "TAGS", "CREATOR", "org.wordpress.android_wordpressJalapenoDebug"})
public enum SubfilterCategory implements android.os.Parcelable {
    /*public static final*/ SITES /* = new SITES(0, null) */,
    /*public static final*/ TAGS /* = new TAGS(0, null) */;
    private final int titleRes = 0;
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.ui.reader.subfilter.SubfilterListItem.ItemType type = null;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.reader.subfilter.SubfilterCategory.CREATOR CREATOR = null;
    
    SubfilterCategory(@androidx.annotation.StringRes()
    int titleRes, org.wordpress.android.ui.reader.subfilter.SubfilterListItem.ItemType type) {
    }
    
    public final int getTitleRes() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.reader.subfilter.SubfilterListItem.ItemType getType() {
        return null;
    }
    
    @java.lang.Override()
    public void writeToParcel(@org.jetbrains.annotations.NotNull()
    android.os.Parcel parcel, int flags) {
    }
    
    @java.lang.Override()
    public int describeContents() {
        return 0;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u001d\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016\u00a2\u0006\u0002\u0010\u000b\u00a8\u0006\f"}, d2 = {"Lorg/wordpress/android/ui/reader/subfilter/SubfilterCategory$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lorg/wordpress/android/ui/reader/subfilter/SubfilterCategory;", "()V", "createFromParcel", "parcel", "Landroid/os/Parcel;", "newArray", "", "size", "", "(I)[Lorg/wordpress/android/ui/reader/subfilter/SubfilterCategory;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class CREATOR implements android.os.Parcelable.Creator<org.wordpress.android.ui.reader.subfilter.SubfilterCategory> {
        
        private CREATOR() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public org.wordpress.android.ui.reader.subfilter.SubfilterCategory createFromParcel(@org.jetbrains.annotations.NotNull()
        android.os.Parcel parcel) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public org.wordpress.android.ui.reader.subfilter.SubfilterCategory[] newArray(int size) {
            return null;
        }
    }
}