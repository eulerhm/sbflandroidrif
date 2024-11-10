package fr.free.nrw.commons.upload;

import java.lang.System;

/**
 * Extracts geolocation to be passed to API for category suggestions. If a picture with geolocation
 * is uploaded, extract latitude and longitude from EXIF data of image.
 * Otherwise, if current user location is available while using the in-app camera,
 * use it to set image coordinates
 */
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u000f\u0018\u00002\u00020\u0001B\u0019\b\u0010\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\u0006B\u0019\b\u0010\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\tB\u001b\b\u0000\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\fJ\u0010\u0010\'\u001a\u00020\u000e2\u0006\u0010(\u001a\u00020\bH\u0002J\u0010\u0010)\u001a\u00020\u000e2\u0006\u0010*\u001a\u00020\bH\u0002R\u001a\u0010\r\u001a\u00020\u000eX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u000eX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0010\"\u0004\b\u0015\u0010\u0012R\u001c\u0010\u0016\u001a\u0004\u0018\u00010\bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001b\u001a\u00020\u001cX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u0013\u0010!\u001a\u0004\u0018\u00010\u00058F\u00a2\u0006\u0006\u001a\u0004\b\"\u0010#R\u001a\u0010$\u001a\u00020\u000eX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u0010\"\u0004\b&\u0010\u0012\u00a8\u0006+"}, d2 = {"Lfr/free/nrw/commons/upload/ImageCoordinates;", "", "stream", "Ljava/io/InputStream;", "inAppPictureLocation", "Lfr/free/nrw/commons/location/LatLng;", "(Ljava/io/InputStream;Lfr/free/nrw/commons/location/LatLng;)V", "path", "", "(Ljava/lang/String;Lfr/free/nrw/commons/location/LatLng;)V", "exif", "Landroidx/exifinterface/media/ExifInterface;", "(Landroidx/exifinterface/media/ExifInterface;Lfr/free/nrw/commons/location/LatLng;)V", "decLatitude", "", "getDecLatitude", "()D", "setDecLatitude", "(D)V", "decLongitude", "getDecLongitude", "setDecLongitude", "decimalCoords", "getDecimalCoords", "()Ljava/lang/String;", "setDecimalCoords", "(Ljava/lang/String;)V", "imageCoordsExists", "", "getImageCoordsExists", "()Z", "setImageCoordsExists", "(Z)V", "latLng", "getLatLng", "()Lfr/free/nrw/commons/location/LatLng;", "zoomLevel", "getZoomLevel", "setZoomLevel", "convertToDegree", "degreeMinuteSecondString", "evaluateExpression", "dm", "app-commons-v4.2.1-master_prodDebug"})
public final class ImageCoordinates {
    private double decLatitude = 0.0;
    private double decLongitude = 0.0;
    private boolean imageCoordsExists = false;
    
    /**
     * @return string of `"[decLatitude]|[decLongitude]"` or null if coordinates do not exist
     */
    @org.jetbrains.annotations.Nullable
    private java.lang.String decimalCoords;
    private double zoomLevel = 16.0;
    
    public ImageCoordinates(@org.jetbrains.annotations.Nullable
    androidx.exifinterface.media.ExifInterface exif, @org.jetbrains.annotations.Nullable
    fr.free.nrw.commons.location.LatLng inAppPictureLocation) {
        super();
    }
    
    public final double getDecLatitude() {
        return 0.0;
    }
    
    public final void setDecLatitude(double p0) {
    }
    
    public final double getDecLongitude() {
        return 0.0;
    }
    
    public final void setDecLongitude(double p0) {
    }
    
    public final boolean getImageCoordsExists() {
        return false;
    }
    
    public final void setImageCoordsExists(boolean p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getDecimalCoords() {
        return null;
    }
    
    public final void setDecimalCoords(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    public final double getZoomLevel() {
        return 0.0;
    }
    
    public final void setZoomLevel(double p0) {
    }
    
    /**
     * Construct from a stream.
     */
    public ImageCoordinates(@org.jetbrains.annotations.NotNull
    java.io.InputStream stream, @org.jetbrains.annotations.Nullable
    fr.free.nrw.commons.location.LatLng inAppPictureLocation) {
        super();
    }
    
    /**
     * Construct from the file path of the image.
     * @param path file path of the image
     */
    @kotlin.jvm.Throws(exceptionClasses = {java.io.IOException.class})
    public ImageCoordinates(@org.jetbrains.annotations.NotNull
    java.lang.String path, @org.jetbrains.annotations.Nullable
    fr.free.nrw.commons.location.LatLng inAppPictureLocation) throws java.io.IOException {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final fr.free.nrw.commons.location.LatLng getLatLng() {
        return null;
    }
    
    /**
     * Convert a string to an accurate Degree
     *
     * @param degreeMinuteSecondString - template string "a/b,c/d,e/f" where the letters represent numbers
     * @return the degree accurate to the second
     */
    private final double convertToDegree(java.lang.String degreeMinuteSecondString) {
        return 0.0;
    }
    
    private final double evaluateExpression(java.lang.String dm) {
        return 0.0;
    }
}