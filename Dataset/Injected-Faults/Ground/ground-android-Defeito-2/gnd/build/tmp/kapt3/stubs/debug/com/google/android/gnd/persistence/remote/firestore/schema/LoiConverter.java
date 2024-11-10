package com.google.android.gnd.persistence.remote.firestore.schema;

import java.lang.System;

/**
 * Converts between Firestore documents and [Feature] instances.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J,\u0010\f\u001a\u00020\r2\n\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0014H\u0002J\u001c\u0010\u0018\u001a\u0006\u0012\u0002\b\u00030\u00192\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u001a\u001a\u00020\u001bH\u0007J \u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\""}, d2 = {"Lcom/google/android/gnd/persistence/remote/firestore/schema/LoiConverter;", "", "()V", "CREATED", "", "GEOMETRY", "GEOMETRY_COORDINATES", "GEOMETRY_TYPE", "JOB_ID", "LAST_MODIFIED", "LOCATION", "POLYGON_TYPE", "fillFeature", "", "builder", "Lcom/google/android/gnd/model/feature/Feature$Builder;", "survey", "Lcom/google/android/gnd/model/Survey;", "id", "loiDoc", "Lcom/google/android/gnd/persistence/remote/firestore/schema/LoiDocument;", "hasNonEmptyVertices", "", "loiDocument", "toLoi", "Lcom/google/android/gnd/model/feature/Feature;", "doc", "Lcom/google/firebase/firestore/DocumentSnapshot;", "toLoiFromGeometry", "Lcom/google/android/gnd/model/feature/PolygonFeature;", "toPoint", "Lcom/google/android/gnd/model/feature/Point;", "geoPoint", "Lcom/google/firebase/firestore/GeoPoint;", "gnd_debug"})
public final class LoiConverter {
    @org.jetbrains.annotations.NotNull()
    public static final com.google.android.gnd.persistence.remote.firestore.schema.LoiConverter INSTANCE = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String JOB_ID = "jobId";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String LOCATION = "location";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String CREATED = "created";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String LAST_MODIFIED = "lastModified";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GEOMETRY_TYPE = "type";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String POLYGON_TYPE = "Polygon";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GEOMETRY_COORDINATES = "coordinates";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GEOMETRY = "geometry";
    
    private LoiConverter() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @kotlin.jvm.Throws(exceptionClasses = {com.google.android.gnd.persistence.remote.DataStoreException.class})
    @kotlin.jvm.JvmStatic()
    public static final com.google.android.gnd.model.feature.Feature<?> toLoi(@org.jetbrains.annotations.NotNull()
    com.google.android.gnd.model.Survey survey, @org.jetbrains.annotations.NotNull()
    com.google.firebase.firestore.DocumentSnapshot doc) {
        return null;
    }
    
    private final boolean hasNonEmptyVertices(com.google.android.gnd.persistence.remote.firestore.schema.LoiDocument loiDocument) {
        return false;
    }
    
    private final com.google.android.gnd.model.feature.PolygonFeature toLoiFromGeometry(com.google.android.gnd.model.Survey survey, com.google.firebase.firestore.DocumentSnapshot doc, com.google.android.gnd.persistence.remote.firestore.schema.LoiDocument loiDoc) {
        return null;
    }
    
    private final void fillFeature(com.google.android.gnd.model.feature.Feature.Builder<?> builder, com.google.android.gnd.model.Survey survey, java.lang.String id, com.google.android.gnd.persistence.remote.firestore.schema.LoiDocument loiDoc) {
    }
    
    private final com.google.android.gnd.model.feature.Point toPoint(com.google.firebase.firestore.GeoPoint geoPoint) {
        return null;
    }
}