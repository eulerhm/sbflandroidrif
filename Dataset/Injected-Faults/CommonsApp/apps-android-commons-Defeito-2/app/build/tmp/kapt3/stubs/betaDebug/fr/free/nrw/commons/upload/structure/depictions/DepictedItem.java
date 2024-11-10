package fr.free.nrw.commons.upload.structure.depictions;

import java.lang.System;

/**
 * Model class for Depicted Item in Upload and Explore
 */
@androidx.room.Entity
@kotlinx.android.parcel.Parcelize
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0016\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0005B\u0017\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bB\u001f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\n\u00a2\u0006\u0002\u0010\fBM\u0012\u0006\u0010\t\u001a\u00020\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\n\u0012\b\u0010\r\u001a\u0004\u0018\u00010\n\u0012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\n0\u000f\u0012\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u000f\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00020\n\u00a2\u0006\u0002\u0010\u0015J\t\u0010!\u001a\u00020\nH\u00c6\u0003J\u000b\u0010\"\u001a\u0004\u0018\u00010\nH\u00c6\u0003J\u000b\u0010#\u001a\u0004\u0018\u00010\nH\u00c6\u0003J\u000f\u0010$\u001a\b\u0012\u0004\u0012\u00020\n0\u000fH\u00c6\u0003J\u000f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00110\u000fH\u00c6\u0003J\t\u0010&\u001a\u00020\u0013H\u00c6\u0003J\t\u0010\'\u001a\u00020\nH\u00c6\u0003J_\u0010(\u001a\u00020\u00002\b\b\u0002\u0010\t\u001a\u00020\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\n2\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\n0\u000f2\u000e\b\u0002\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u000f2\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\nH\u00c6\u0001J\t\u0010)\u001a\u00020*H\u00d6\u0001J\u0013\u0010+\u001a\u00020\u00132\b\u0010,\u001a\u0004\u0018\u00010-H\u0096\u0002J\b\u0010.\u001a\u00020*H\u0016J\t\u0010/\u001a\u00020\nH\u00d6\u0001J\u0019\u00100\u001a\u0002012\u0006\u00102\u001a\u0002032\u0006\u00104\u001a\u00020*H\u00d6\u0001R\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0016\u0010\u0014\u001a\u00020\n8\u0016X\u0097\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0019R\u0013\u0010\r\u001a\u0004\u0018\u00010\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0019R\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\n0\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0017R\u001a\u0010\u0012\u001a\u00020\u0013X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u0014\u0010\t\u001a\u00020\nX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u0019\u00a8\u00065"}, d2 = {"Lfr/free/nrw/commons/upload/structure/depictions/DepictedItem;", "Lfr/free/nrw/commons/upload/WikidataItem;", "Landroid/os/Parcelable;", "entity", "Lorg/wikipedia/wikidata/Entities$Entity;", "(Lorg/wikipedia/wikidata/Entities$Entity;)V", "place", "Lfr/free/nrw/commons/nearby/Place;", "(Lorg/wikipedia/wikidata/Entities$Entity;Lfr/free/nrw/commons/nearby/Place;)V", "name", "", "description", "(Lorg/wikipedia/wikidata/Entities$Entity;Ljava/lang/String;Ljava/lang/String;)V", "imageUrl", "instanceOfs", "", "commonsCategories", "Lfr/free/nrw/commons/category/CategoryItem;", "isSelected", "", "id", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/List;ZLjava/lang/String;)V", "getCommonsCategories", "()Ljava/util/List;", "getDescription", "()Ljava/lang/String;", "getId", "getImageUrl", "getInstanceOfs", "()Z", "setSelected", "(Z)V", "getName", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "describeContents", "", "equals", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "app-commons-v4.2.1-main_betaDebug"})
public final class DepictedItem implements fr.free.nrw.commons.upload.WikidataItem, android.os.Parcelable {
    @org.jetbrains.annotations.NotNull
    private final java.lang.String name = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String description = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String imageUrl = null;
    @org.jetbrains.annotations.NotNull
    private final java.util.List<java.lang.String> instanceOfs = null;
    @org.jetbrains.annotations.NotNull
    private final java.util.List<fr.free.nrw.commons.category.CategoryItem> commonsCategories = null;
    private boolean isSelected;
    @org.jetbrains.annotations.NotNull
    @androidx.room.PrimaryKey
    private final java.lang.String id = null;
    public static final android.os.Parcelable.Creator<fr.free.nrw.commons.upload.structure.depictions.DepictedItem> CREATOR = null;
    
    /**
     * Model class for Depicted Item in Upload and Explore
     */
    @org.jetbrains.annotations.NotNull
    public final fr.free.nrw.commons.upload.structure.depictions.DepictedItem copy(@org.jetbrains.annotations.NotNull
    java.lang.String name, @org.jetbrains.annotations.Nullable
    java.lang.String description, @org.jetbrains.annotations.Nullable
    java.lang.String imageUrl, @org.jetbrains.annotations.NotNull
    java.util.List<java.lang.String> instanceOfs, @org.jetbrains.annotations.NotNull
    java.util.List<fr.free.nrw.commons.category.CategoryItem> commonsCategories, boolean isSelected, @org.jetbrains.annotations.NotNull
    java.lang.String id) {
        return null;
    }
    
    /**
     * Model class for Depicted Item in Upload and Explore
     */
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public java.lang.String toString() {
        return null;
    }
    
    public DepictedItem(@org.jetbrains.annotations.NotNull
    java.lang.String name, @org.jetbrains.annotations.Nullable
    java.lang.String description, @org.jetbrains.annotations.Nullable
    java.lang.String imageUrl, @org.jetbrains.annotations.NotNull
    java.util.List<java.lang.String> instanceOfs, @org.jetbrains.annotations.NotNull
    java.util.List<fr.free.nrw.commons.category.CategoryItem> commonsCategories, boolean isSelected, @org.jetbrains.annotations.NotNull
    java.lang.String id) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public java.lang.String getName() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getDescription() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getImageUrl() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<java.lang.String> component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<java.lang.String> getInstanceOfs() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<fr.free.nrw.commons.category.CategoryItem> component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<fr.free.nrw.commons.category.CategoryItem> getCommonsCategories() {
        return null;
    }
    
    public final boolean component6() {
        return false;
    }
    
    public final boolean isSelected() {
        return false;
    }
    
    public final void setSelected(boolean p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public java.lang.String getId() {
        return null;
    }
    
    public DepictedItem(@org.jetbrains.annotations.NotNull
    org.wikipedia.wikidata.Entities.Entity entity) {
        super();
    }
    
    public DepictedItem(@org.jetbrains.annotations.NotNull
    org.wikipedia.wikidata.Entities.Entity entity, @org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.nearby.Place place) {
        super();
    }
    
    public DepictedItem(@org.jetbrains.annotations.NotNull
    org.wikipedia.wikidata.Entities.Entity entity, @org.jetbrains.annotations.NotNull
    java.lang.String name, @org.jetbrains.annotations.NotNull
    java.lang.String description) {
        super();
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
    
    @java.lang.Override
    public int describeContents() {
        return 0;
    }
    
    @java.lang.Override
    public void writeToParcel(@org.jetbrains.annotations.NotNull
    android.os.Parcel parcel, int flags) {
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 3)
    public static final class Creator implements android.os.Parcelable.Creator<fr.free.nrw.commons.upload.structure.depictions.DepictedItem> {
        
        public Creator() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        @java.lang.Override
        public final fr.free.nrw.commons.upload.structure.depictions.DepictedItem createFromParcel(@org.jetbrains.annotations.NotNull
        android.os.Parcel in) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        @java.lang.Override
        public final fr.free.nrw.commons.upload.structure.depictions.DepictedItem[] newArray(int size) {
            return null;
        }
    }
}