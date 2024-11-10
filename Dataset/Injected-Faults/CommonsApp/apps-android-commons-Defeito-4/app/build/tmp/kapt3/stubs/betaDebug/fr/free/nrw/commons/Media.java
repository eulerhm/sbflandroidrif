package fr.free.nrw.commons;

import java.lang.System;

@kotlinx.android.parcel.Parcelize
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b0\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001BS\b\u0016\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0004\u00a2\u0006\u0002\u0010\u000bB\u00eb\u0001\u0012\b\b\u0002\u0010\f\u001a\u00020\u0004\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0004\u0012\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0014\u0012\u0014\b\u0002\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0014\b\u0002\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\b\u0002\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006\u0012\u0014\b\u0002\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00180\u0003\u00a2\u0006\u0002\u0010\u0019J\t\u0010R\u001a\u00020SH\u00d6\u0001J\u0019\u0010T\u001a\u00020U2\u0006\u0010V\u001a\u00020W2\u0006\u0010X\u001a\u00020SH\u00d6\u0001R6\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00062\u000e\u0010\u001a\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00068F@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001c\u0010\t\u001a\u0004\u0018\u00010\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R&\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010\'R\"\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u001d\"\u0004\b)\u0010\u001fR&\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00180\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b*\u0010%\"\u0004\b+\u0010\'R\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b0\u00101\"\u0004\b2\u00103R \u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b4\u0010\u001d\"\u0004\b5\u0010\u001fR&\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b6\u0010%\"\u0004\b7\u0010\'R\u0011\u00108\u001a\u00020\u00048F\u00a2\u0006\u0006\u001a\u0004\b9\u0010!R\u001c\u0010\b\u001a\u0004\u0018\u00010\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b:\u0010!\"\u0004\b;\u0010#R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b<\u0010!\"\u0004\b=\u0010#R\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b>\u0010!\"\u0004\b?\u0010#R\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b@\u0010!\"\u0004\bA\u0010#R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bB\u0010!\"\u0004\bC\u0010#R\u0011\u0010D\u001a\u00020\u00048F\u00a2\u0006\u0006\u001a\u0004\bE\u0010!R\u001a\u0010\f\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bF\u0010!\"\u0004\bG\u0010#R\u0011\u0010H\u001a\u00020I8F\u00a2\u0006\u0006\u001a\u0004\bJ\u0010KR\u001c\u0010\r\u001a\u0004\u0018\u00010\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bL\u0010!\"\u0004\bM\u0010#R\u001c\u0010\n\u001a\u0004\u0018\u00010\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bN\u0010!\"\u0004\bO\u0010#R\u0011\u0010P\u001a\u00020\u00048F\u00a2\u0006\u0006\u001a\u0004\bQ\u0010!\u00a8\u0006Y"}, d2 = {"Lfr/free/nrw/commons/Media;", "Landroid/os/Parcelable;", "captions", "", "", "categories", "", "filename", "fallbackDescription", "author", "user", "(Ljava/util/Map;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "pageId", "thumbUrl", "imageUrl", "dateUploaded", "Ljava/util/Date;", "license", "licenseUrl", "coordinates", "Lfr/free/nrw/commons/location/LatLng;", "descriptions", "depictionIds", "categoriesHiddenStatus", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Date;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Lfr/free/nrw/commons/location/LatLng;Ljava/util/Map;Ljava/util/Map;Ljava/util/List;Ljava/util/Map;)V", "value", "addedCategories", "getAddedCategories", "()Ljava/util/List;", "setAddedCategories", "(Ljava/util/List;)V", "getAuthor", "()Ljava/lang/String;", "setAuthor", "(Ljava/lang/String;)V", "getCaptions", "()Ljava/util/Map;", "setCaptions", "(Ljava/util/Map;)V", "getCategories", "setCategories", "getCategoriesHiddenStatus", "setCategoriesHiddenStatus", "getCoordinates", "()Lfr/free/nrw/commons/location/LatLng;", "setCoordinates", "(Lfr/free/nrw/commons/location/LatLng;)V", "getDateUploaded", "()Ljava/util/Date;", "setDateUploaded", "(Ljava/util/Date;)V", "getDepictionIds", "setDepictionIds", "getDescriptions", "setDescriptions", "displayTitle", "getDisplayTitle", "getFallbackDescription", "setFallbackDescription", "getFilename", "setFilename", "getImageUrl", "setImageUrl", "getLicense", "setLicense", "getLicenseUrl", "setLicenseUrl", "mostRelevantCaption", "getMostRelevantCaption", "getPageId", "setPageId", "pageTitle", "Lorg/wikipedia/page/PageTitle;", "getPageTitle", "()Lorg/wikipedia/page/PageTitle;", "getThumbUrl", "setThumbUrl", "getUser", "setUser", "wikiCode", "getWikiCode", "describeContents", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "app-commons-v4.2.1-main_betaDebug"})
public final class Media implements android.os.Parcelable {
    
    /**
     * @return pageId for the current media object
     * Wikibase Identifier associated with media files
     */
    @org.jetbrains.annotations.NotNull
    private java.lang.String pageId;
    @org.jetbrains.annotations.Nullable
    private java.lang.String thumbUrl;
    
    /**
     * Gets image URL
     * @return Image URL
     */
    @org.jetbrains.annotations.Nullable
    private java.lang.String imageUrl;
    
    /**
     * Gets the name of the file.
     * @return file name as a string
     */
    @org.jetbrains.annotations.Nullable
    private java.lang.String filename;
    
    /**
     * Sets the file description.
     * @param fallbackDescription the new description of the file
     */
    @org.jetbrains.annotations.Nullable
    private java.lang.String fallbackDescription;
    
    /**
     * Gets the upload date of the file.
     * Can be null.
     * @return upload date as a Date
     */
    @org.jetbrains.annotations.Nullable
    private java.util.Date dateUploaded;
    
    /**
     * Sets the license name of the file.
     *
     * @param license license name as a String
     */
    @org.jetbrains.annotations.Nullable
    private java.lang.String license;
    @org.jetbrains.annotations.Nullable
    private java.lang.String licenseUrl;
    
    /**
     * Sets the author name of the file.
     * @param author creator name as a string
     */
    @org.jetbrains.annotations.Nullable
    private java.lang.String author;
    @org.jetbrains.annotations.Nullable
    private java.lang.String user;
    
    /**
     * Gets the categories the file falls under.
     * @return file categories as an ArrayList of Strings
     */
    @org.jetbrains.annotations.Nullable
    private java.util.List<java.lang.String> categories;
    
    /**
     * Gets the coordinates of where the file was created.
     * @return file coordinates as a LatLng
     */
    @org.jetbrains.annotations.Nullable
    private fr.free.nrw.commons.location.LatLng coordinates;
    @org.jetbrains.annotations.NotNull
    private java.util.Map<java.lang.String, java.lang.String> captions;
    @org.jetbrains.annotations.NotNull
    private java.util.Map<java.lang.String, java.lang.String> descriptions;
    @org.jetbrains.annotations.NotNull
    private java.util.List<java.lang.String> depictionIds;
    
    /**
     * This field was added to find non-hidden categories
     * Stores the mapping of category title to hidden attribute
     * Example: "Mountains" => false, "CC-BY-SA-2.0" => true
     */
    @org.jetbrains.annotations.NotNull
    private java.util.Map<java.lang.String, java.lang.Boolean> categoriesHiddenStatus;
    
    /**
     * Gets the categories the file falls under.
     * @return file categories as an ArrayList of Strings
     */
    @org.jetbrains.annotations.Nullable
    private java.util.List<java.lang.String> addedCategories;
    public static final android.os.Parcelable.Creator<fr.free.nrw.commons.Media> CREATOR = null;
    
    public Media() {
        super();
    }
    
    public Media(@org.jetbrains.annotations.NotNull
    java.lang.String pageId, @org.jetbrains.annotations.Nullable
    java.lang.String thumbUrl, @org.jetbrains.annotations.Nullable
    java.lang.String imageUrl, @org.jetbrains.annotations.Nullable
    java.lang.String filename, @org.jetbrains.annotations.Nullable
    java.lang.String fallbackDescription, @org.jetbrains.annotations.Nullable
    java.util.Date dateUploaded, @org.jetbrains.annotations.Nullable
    java.lang.String license, @org.jetbrains.annotations.Nullable
    java.lang.String licenseUrl, @org.jetbrains.annotations.Nullable
    java.lang.String author, @org.jetbrains.annotations.Nullable
    java.lang.String user, @org.jetbrains.annotations.Nullable
    java.util.List<java.lang.String> categories, @org.jetbrains.annotations.Nullable
    fr.free.nrw.commons.location.LatLng coordinates, @org.jetbrains.annotations.NotNull
    java.util.Map<java.lang.String, java.lang.String> captions, @org.jetbrains.annotations.NotNull
    java.util.Map<java.lang.String, java.lang.String> descriptions, @org.jetbrains.annotations.NotNull
    java.util.List<java.lang.String> depictionIds, @org.jetbrains.annotations.NotNull
    java.util.Map<java.lang.String, java.lang.Boolean> categoriesHiddenStatus) {
        super();
    }
    
    /**
     * @return pageId for the current media object
     * Wikibase Identifier associated with media files
     */
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getPageId() {
        return null;
    }
    
    /**
     * @return pageId for the current media object
     * Wikibase Identifier associated with media files
     */
    public final void setPageId(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getThumbUrl() {
        return null;
    }
    
    public final void setThumbUrl(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    /**
     * Gets image URL
     * @return Image URL
     */
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getImageUrl() {
        return null;
    }
    
    /**
     * Gets image URL
     * @return Image URL
     */
    public final void setImageUrl(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    /**
     * Gets the name of the file.
     * @return file name as a string
     */
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getFilename() {
        return null;
    }
    
    /**
     * Gets the name of the file.
     * @return file name as a string
     */
    public final void setFilename(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    /**
     * Sets the file description.
     * @param fallbackDescription the new description of the file
     */
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getFallbackDescription() {
        return null;
    }
    
    /**
     * Sets the file description.
     * @param fallbackDescription the new description of the file
     */
    public final void setFallbackDescription(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    /**
     * Gets the upload date of the file.
     * Can be null.
     * @return upload date as a Date
     */
    @org.jetbrains.annotations.Nullable
    public final java.util.Date getDateUploaded() {
        return null;
    }
    
    /**
     * Gets the upload date of the file.
     * Can be null.
     * @return upload date as a Date
     */
    public final void setDateUploaded(@org.jetbrains.annotations.Nullable
    java.util.Date p0) {
    }
    
    /**
     * Sets the license name of the file.
     *
     * @param license license name as a String
     */
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getLicense() {
        return null;
    }
    
    /**
     * Sets the license name of the file.
     *
     * @param license license name as a String
     */
    public final void setLicense(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getLicenseUrl() {
        return null;
    }
    
    public final void setLicenseUrl(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    /**
     * Sets the author name of the file.
     * @param author creator name as a string
     */
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getAuthor() {
        return null;
    }
    
    /**
     * Sets the author name of the file.
     * @param author creator name as a string
     */
    public final void setAuthor(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getUser() {
        return null;
    }
    
    public final void setUser(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    /**
     * Gets the categories the file falls under.
     * @return file categories as an ArrayList of Strings
     */
    @org.jetbrains.annotations.Nullable
    public final java.util.List<java.lang.String> getCategories() {
        return null;
    }
    
    /**
     * Gets the categories the file falls under.
     * @return file categories as an ArrayList of Strings
     */
    public final void setCategories(@org.jetbrains.annotations.Nullable
    java.util.List<java.lang.String> p0) {
    }
    
    /**
     * Gets the coordinates of where the file was created.
     * @return file coordinates as a LatLng
     */
    @org.jetbrains.annotations.Nullable
    public final fr.free.nrw.commons.location.LatLng getCoordinates() {
        return null;
    }
    
    /**
     * Gets the coordinates of where the file was created.
     * @return file coordinates as a LatLng
     */
    public final void setCoordinates(@org.jetbrains.annotations.Nullable
    fr.free.nrw.commons.location.LatLng p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.Map<java.lang.String, java.lang.String> getCaptions() {
        return null;
    }
    
    public final void setCaptions(@org.jetbrains.annotations.NotNull
    java.util.Map<java.lang.String, java.lang.String> p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.Map<java.lang.String, java.lang.String> getDescriptions() {
        return null;
    }
    
    public final void setDescriptions(@org.jetbrains.annotations.NotNull
    java.util.Map<java.lang.String, java.lang.String> p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<java.lang.String> getDepictionIds() {
        return null;
    }
    
    public final void setDepictionIds(@org.jetbrains.annotations.NotNull
    java.util.List<java.lang.String> p0) {
    }
    
    /**
     * This field was added to find non-hidden categories
     * Stores the mapping of category title to hidden attribute
     * Example: "Mountains" => false, "CC-BY-SA-2.0" => true
     */
    @org.jetbrains.annotations.NotNull
    public final java.util.Map<java.lang.String, java.lang.Boolean> getCategoriesHiddenStatus() {
        return null;
    }
    
    /**
     * This field was added to find non-hidden categories
     * Stores the mapping of category title to hidden attribute
     * Example: "Mountains" => false, "CC-BY-SA-2.0" => true
     */
    public final void setCategoriesHiddenStatus(@org.jetbrains.annotations.NotNull
    java.util.Map<java.lang.String, java.lang.Boolean> p0) {
    }
    
    public Media(@org.jetbrains.annotations.NotNull
    java.util.Map<java.lang.String, java.lang.String> captions, @org.jetbrains.annotations.Nullable
    java.util.List<java.lang.String> categories, @org.jetbrains.annotations.Nullable
    java.lang.String filename, @org.jetbrains.annotations.Nullable
    java.lang.String fallbackDescription, @org.jetbrains.annotations.Nullable
    java.lang.String author, @org.jetbrains.annotations.Nullable
    java.lang.String user) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getDisplayTitle() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final org.wikipedia.page.PageTitle getPageTitle() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getWikiCode() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getMostRelevantCaption() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.util.List<java.lang.String> getAddedCategories() {
        return null;
    }
    
    public final void setAddedCategories(@org.jetbrains.annotations.Nullable
    java.util.List<java.lang.String> value) {
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
    public static final class Creator implements android.os.Parcelable.Creator<fr.free.nrw.commons.Media> {
        
        public Creator() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        @java.lang.Override
        public final fr.free.nrw.commons.Media createFromParcel(@org.jetbrains.annotations.NotNull
        android.os.Parcel in) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        @java.lang.Override
        public final fr.free.nrw.commons.Media[] newArray(int size) {
            return null;
        }
    }
}