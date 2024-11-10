package fr.free.nrw.commons.upload;

import java.lang.System;

/**
 * Holds a description of an item being uploaded by [UploadActivity]
 */
@kotlinx.android.parcel.Parcelize
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004B%\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\tJ\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003J\t\u0010\u001e\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u001f\u001a\u00020\u0006H\u00c6\u0003J)\u0010 \u001a\u00020\u00002\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u0006H\u00c6\u0001J\t\u0010!\u001a\u00020\u0018H\u00d6\u0001J\u0013\u0010\"\u001a\u00020\u00112\b\u0010#\u001a\u0004\u0018\u00010$H\u00d6\u0003J\t\u0010%\u001a\u00020\u0018H\u00d6\u0001J\u0006\u0010&\u001a\u00020\u0000J\t\u0010\'\u001a\u00020\u0006H\u00d6\u0001J\u0019\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020\u0018H\u00d6\u0001R\u001a\u0010\b\u001a\u00020\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u0007\u001a\u00020\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000b\"\u0004\b\u000f\u0010\rR\u001a\u0010\u0010\u001a\u00020\u0011X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u000b\"\u0004\b\u0016\u0010\rR\u001a\u0010\u0017\u001a\u00020\u0018X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001c\u00a8\u0006-"}, d2 = {"Lfr/free/nrw/commons/upload/UploadMediaDetail;", "Landroid/os/Parcelable;", "place", "Lfr/free/nrw/commons/nearby/Place;", "(Lfr/free/nrw/commons/nearby/Place;)V", "languageCode", "", "descriptionText", "captionText", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getCaptionText", "()Ljava/lang/String;", "setCaptionText", "(Ljava/lang/String;)V", "getDescriptionText", "setDescriptionText", "isManuallyAdded", "", "()Z", "setManuallyAdded", "(Z)V", "getLanguageCode", "setLanguageCode", "selectedLanguageIndex", "", "getSelectedLanguageIndex", "()I", "setSelectedLanguageIndex", "(I)V", "component1", "component2", "component3", "copy", "describeContents", "equals", "other", "", "hashCode", "javaCopy", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "app-commons-v4.2.1-master_prodDebug"})
public final class UploadMediaDetail implements android.os.Parcelable {
    
    /**
     * @param languageCode The language code ie. "en" or "fr"
     */
    @org.jetbrains.annotations.Nullable
    private java.lang.String languageCode;
    @org.jetbrains.annotations.NotNull
    private java.lang.String descriptionText;
    @org.jetbrains.annotations.NotNull
    private java.lang.String captionText;
    
    /**
     * @param selectedLanguageIndex the index of the language selected in a spinner with [SpinnerLanguagesAdapter]
     */
    private int selectedLanguageIndex = -1;
    
    /**
     * sets to true if the description was manually added by the user
     * @param manuallyAdded
     */
    private boolean isManuallyAdded = false;
    public static final android.os.Parcelable.Creator<fr.free.nrw.commons.upload.UploadMediaDetail> CREATOR = null;
    
    /**
     * Holds a description of an item being uploaded by [UploadActivity]
     */
    @org.jetbrains.annotations.NotNull
    public final fr.free.nrw.commons.upload.UploadMediaDetail copy(@org.jetbrains.annotations.Nullable
    java.lang.String languageCode, @org.jetbrains.annotations.NotNull
    java.lang.String descriptionText, @org.jetbrains.annotations.NotNull
    java.lang.String captionText) {
        return null;
    }
    
    /**
     * Holds a description of an item being uploaded by [UploadActivity]
     */
    @java.lang.Override
    public boolean equals(@org.jetbrains.annotations.Nullable
    java.lang.Object other) {
        return false;
    }
    
    /**
     * Holds a description of an item being uploaded by [UploadActivity]
     */
    @java.lang.Override
    public int hashCode() {
        return 0;
    }
    
    /**
     * Holds a description of an item being uploaded by [UploadActivity]
     */
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public java.lang.String toString() {
        return null;
    }
    
    public UploadMediaDetail() {
        super();
    }
    
    public UploadMediaDetail(@org.jetbrains.annotations.Nullable
    java.lang.String languageCode, @org.jetbrains.annotations.NotNull
    java.lang.String descriptionText, @org.jetbrains.annotations.NotNull
    java.lang.String captionText) {
        super();
    }
    
    /**
     * @param languageCode The language code ie. "en" or "fr"
     */
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component1() {
        return null;
    }
    
    /**
     * @param languageCode The language code ie. "en" or "fr"
     */
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getLanguageCode() {
        return null;
    }
    
    /**
     * @param languageCode The language code ie. "en" or "fr"
     */
    public final void setLanguageCode(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getDescriptionText() {
        return null;
    }
    
    public final void setDescriptionText(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getCaptionText() {
        return null;
    }
    
    public final void setCaptionText(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final fr.free.nrw.commons.upload.UploadMediaDetail javaCopy() {
        return null;
    }
    
    public UploadMediaDetail(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.nearby.Place place) {
        super();
    }
    
    public final int getSelectedLanguageIndex() {
        return 0;
    }
    
    public final void setSelectedLanguageIndex(int p0) {
    }
    
    public final boolean isManuallyAdded() {
        return false;
    }
    
    public final void setManuallyAdded(boolean p0) {
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
    public static final class Creator implements android.os.Parcelable.Creator<fr.free.nrw.commons.upload.UploadMediaDetail> {
        
        public Creator() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        @java.lang.Override
        public final fr.free.nrw.commons.upload.UploadMediaDetail createFromParcel(@org.jetbrains.annotations.NotNull
        android.os.Parcel in) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        @java.lang.Override
        public final fr.free.nrw.commons.upload.UploadMediaDetail[] newArray(int size) {
            return null;
        }
    }
}