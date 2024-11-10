package org.wordpress.android.ui.layoutpicker;

import java.lang.System;

@android.annotation.SuppressLint(value = {"ParcelCreator"})
@kotlinx.parcelize.Parcelize()
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004B\u000f\b\u0016\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007BC\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\t\u0012\u0006\u0010\f\u001a\u00020\t\u0012\u0006\u0010\r\u001a\u00020\t\u0012\u0006\u0010\u000e\u001a\u00020\t\u0012\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010\u00a2\u0006\u0002\u0010\u0012J\t\u0010\u001c\u001a\u00020\tH\u00c6\u0003J\t\u0010\u001d\u001a\u00020\tH\u00c6\u0003J\t\u0010\u001e\u001a\u00020\tH\u00c6\u0003J\t\u0010\u001f\u001a\u00020\tH\u00c6\u0003J\t\u0010 \u001a\u00020\tH\u00c6\u0003J\t\u0010!\u001a\u00020\tH\u00c6\u0003J\u000f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u00c6\u0003JU\u0010#\u001a\u00020\u00002\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\t2\b\b\u0002\u0010\r\u001a\u00020\t2\b\b\u0002\u0010\u000e\u001a\u00020\t2\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u00c6\u0001J\t\u0010$\u001a\u00020%H\u00d6\u0001J\u0013\u0010&\u001a\u00020\'2\b\u0010(\u001a\u0004\u0018\u00010)H\u00d6\u0003J\t\u0010*\u001a\u00020%H\u00d6\u0001J\t\u0010+\u001a\u00020\tH\u00d6\u0001J\u0019\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020/2\u0006\u00100\u001a\u00020%H\u00d6\u0001R\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u000e\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u000b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0016R\u0011\u0010\r\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0016R\u0011\u0010\f\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0016R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0016R\u0011\u0010\n\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0016\u00a8\u00061"}, d2 = {"Lorg/wordpress/android/ui/layoutpicker/LayoutModel;", "Landroid/os/Parcelable;", "starterDesign", "Lorg/wordpress/android/fluxc/network/rest/wpcom/theme/StarterDesign;", "(Lorg/wordpress/android/fluxc/network/rest/wpcom/theme/StarterDesign;)V", "blockLayout", "Lorg/wordpress/android/fluxc/network/rest/wpcom/site/GutenbergLayout;", "(Lorg/wordpress/android/fluxc/network/rest/wpcom/site/GutenbergLayout;)V", "slug", "", "title", "preview", "previewTablet", "previewMobile", "demoUrl", "categories", "", "Lorg/wordpress/android/ui/layoutpicker/LayoutCategoryModel;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)V", "getCategories", "()Ljava/util/List;", "getDemoUrl", "()Ljava/lang/String;", "getPreview", "getPreviewMobile", "getPreviewTablet", "getSlug", "getTitle", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "org.wordpress.android_wordpressJalapenoDebug"})
public final class LayoutModel implements android.os.Parcelable {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String slug = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String title = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String preview = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String previewTablet = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String previewMobile = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String demoUrl = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<org.wordpress.android.ui.layoutpicker.LayoutCategoryModel> categories = null;
    public static final android.os.Parcelable.Creator<org.wordpress.android.ui.layoutpicker.LayoutModel> CREATOR = null;
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.layoutpicker.LayoutModel copy(@org.jetbrains.annotations.NotNull()
    java.lang.String slug, @org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String preview, @org.jetbrains.annotations.NotNull()
    java.lang.String previewTablet, @org.jetbrains.annotations.NotNull()
    java.lang.String previewMobile, @org.jetbrains.annotations.NotNull()
    java.lang.String demoUrl, @org.jetbrains.annotations.NotNull()
    java.util.List<org.wordpress.android.ui.layoutpicker.LayoutCategoryModel> categories) {
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
    
    public LayoutModel(@org.jetbrains.annotations.NotNull()
    java.lang.String slug, @org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String preview, @org.jetbrains.annotations.NotNull()
    java.lang.String previewTablet, @org.jetbrains.annotations.NotNull()
    java.lang.String previewMobile, @org.jetbrains.annotations.NotNull()
    java.lang.String demoUrl, @org.jetbrains.annotations.NotNull()
    java.util.List<org.wordpress.android.ui.layoutpicker.LayoutCategoryModel> categories) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSlug() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTitle() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPreview() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPreviewTablet() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPreviewMobile() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDemoUrl() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<org.wordpress.android.ui.layoutpicker.LayoutCategoryModel> component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<org.wordpress.android.ui.layoutpicker.LayoutCategoryModel> getCategories() {
        return null;
    }
    
    public LayoutModel(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.network.rest.wpcom.theme.StarterDesign starterDesign) {
        super();
    }
    
    public LayoutModel(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.network.rest.wpcom.site.GutenbergLayout blockLayout) {
        super();
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
    public static final class Creator implements android.os.Parcelable.Creator<org.wordpress.android.ui.layoutpicker.LayoutModel> {
        
        public Creator() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public final org.wordpress.android.ui.layoutpicker.LayoutModel createFromParcel(@org.jetbrains.annotations.NotNull()
        android.os.Parcel in) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public final org.wordpress.android.ui.layoutpicker.LayoutModel[] newArray(int size) {
            return null;
        }
    }
}