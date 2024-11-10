package org.wordpress.android.ui.layoutpicker;

import java.lang.System;

@android.annotation.SuppressLint(value = {"ParcelCreator"})
@kotlinx.parcelize.Parcelize()
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B1\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\tJ\t\u0010\u0016\u001a\u00020\u0017H\u00d6\u0001J\u0019\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0017H\u00d6\u0001R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\n\u001a\u00020\u000b8F\u00a2\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0011\u0010\u000e\u001a\u00020\u000b8F\u00a2\u0006\u0006\u001a\u0004\b\u000f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0010R\u0011\u0010\b\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0011\u0010\u0012\u001a\u00020\u000b8F\u00a2\u0006\u0006\u001a\u0004\b\u0013\u0010\rR\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0014\u001a\u00020\u000b8F\u00a2\u0006\u0006\u001a\u0004\b\u0015\u0010\r\u00a8\u0006\u001d"}, d2 = {"Lorg/wordpress/android/ui/layoutpicker/LayoutCategoryModel;", "Landroid/os/Parcelable;", "starterDesignCategory", "Lorg/wordpress/android/fluxc/network/rest/wpcom/theme/StarterDesignCategory;", "blockLayoutCategory", "Lorg/wordpress/android/fluxc/network/rest/wpcom/site/GutenbergLayoutCategory;", "isRecommended", "", "randomizeOrder", "(Lorg/wordpress/android/fluxc/network/rest/wpcom/theme/StarterDesignCategory;Lorg/wordpress/android/fluxc/network/rest/wpcom/site/GutenbergLayoutCategory;ZZ)V", "description", "", "getDescription", "()Ljava/lang/String;", "emoji", "getEmoji", "()Z", "getRandomizeOrder", "slug", "getSlug", "title", "getTitle", "describeContents", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "org.wordpress.android_wordpressJalapenoDebug"})
public final class LayoutCategoryModel implements android.os.Parcelable {
    private final org.wordpress.android.fluxc.network.rest.wpcom.theme.StarterDesignCategory starterDesignCategory = null;
    private final org.wordpress.android.fluxc.network.rest.wpcom.site.GutenbergLayoutCategory blockLayoutCategory = null;
    private final boolean isRecommended = false;
    private final boolean randomizeOrder = false;
    public static final android.os.Parcelable.Creator<org.wordpress.android.ui.layoutpicker.LayoutCategoryModel> CREATOR = null;
    
    public LayoutCategoryModel() {
        super();
    }
    
    public LayoutCategoryModel(@org.jetbrains.annotations.Nullable()
    org.wordpress.android.fluxc.network.rest.wpcom.theme.StarterDesignCategory starterDesignCategory, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.fluxc.network.rest.wpcom.site.GutenbergLayoutCategory blockLayoutCategory, boolean isRecommended, boolean randomizeOrder) {
        super();
    }
    
    public final boolean isRecommended() {
        return false;
    }
    
    public final boolean getRandomizeOrder() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSlug() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTitle() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDescription() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getEmoji() {
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
    public static final class Creator implements android.os.Parcelable.Creator<org.wordpress.android.ui.layoutpicker.LayoutCategoryModel> {
        
        public Creator() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public final org.wordpress.android.ui.layoutpicker.LayoutCategoryModel createFromParcel(@org.jetbrains.annotations.NotNull()
        android.os.Parcel in) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public final org.wordpress.android.ui.layoutpicker.LayoutCategoryModel[] newArray(int size) {
            return null;
        }
    }
}