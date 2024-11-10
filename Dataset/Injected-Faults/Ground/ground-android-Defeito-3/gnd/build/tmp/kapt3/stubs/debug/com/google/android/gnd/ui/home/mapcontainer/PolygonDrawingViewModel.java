package com.google.android.gnd.ui.home.mapcontainer;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u00ae\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 M2\u00020\u0001:\u0002MNB\u001f\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0018\u00104\u001a\u0002052\u0006\u00106\u001a\u00020\n2\u0006\u00107\u001a\u00020\u001bH\u0002J\u000e\u00108\u001a\b\u0012\u0004\u0012\u00020#09H\u0002J\f\u0010:\u001a\b\u0012\u0004\u0012\u00020\u001b0\u0016J\b\u0010;\u001a\u00020\u001bH\u0002J\u000e\u0010<\u001a\u0002052\u0006\u0010=\u001a\u00020\nJ\u0006\u0010>\u001a\u000205J\u0006\u0010?\u001a\u000205J\u0006\u0010@\u001a\u000205J\b\u0010A\u001a\u000205H\u0002J\r\u0010B\u001a\u0004\u0018\u000105\u00a2\u0006\u0002\u0010CJ\u000e\u0010D\u001a\u0002052\u0006\u0010E\u001a\u00020\u001bJ\u0016\u0010F\u001a\u0002052\u0006\u0010,\u001a\u00020-2\u0006\u0010)\u001a\u00020+J\u0016\u0010G\u001a\u0002052\u0006\u0010=\u001a\u00020\n2\u0006\u0010H\u001a\u00020IJ\u0016\u0010J\u001a\u0002052\f\u0010K\u001a\b\u0012\u0004\u0012\u00020\n0LH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u000b\u001a\r\u0012\u0004\u0012\u00020\r0\f\u00a2\u0006\u0002\b\u000e8F\u00a2\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\n0\u00128F\u00a2\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u001c\u001a\r\u0012\u0004\u0012\u00020\u001b0\u0016\u00a2\u0006\u0002\b\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0019R\u0019\u0010\u001d\u001a\r\u0012\u0004\u0012\u00020\u001b0\u001e\u00a2\u0006\u0002\b\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R#\u0010\u001f\u001a\u0017\u0012\u0004\u0012\u00020\u001b0 \u00a2\u0006\f\b\u000e\u0012\b\b!\u0012\u0004\b\u0007\u0010\u0002X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\"\u001a\b\u0012\u0004\u0012\u00020#0\u0016X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R2\u0010$\u001a&\u0012\f\u0012\n &*\u0004\u0018\u00010%0% &*\u0012\u0012\f\u0012\n &*\u0004\u0018\u00010%0%\u0018\u00010\u00120\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001f\u0010\'\u001a\u0013\u0012\n\u0012\b\u0012\u0004\u0012\u00020%0\u00120\u001e\u00a2\u0006\u0002\b\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010(\u001a\r\u0012\u0004\u0012\u00020\r0\u001e\u00a2\u0006\u0002\b\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010)\u001a\u0010\u0012\f\u0012\n &*\u0004\u0018\u00010+0+0*X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010,\u001a\u0010\u0012\f\u0012\n &*\u0004\u0018\u00010-0-0*X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\"\u0010.\u001a\u0013\u0012\n\u0012\b\u0012\u0004\u0012\u0002000/0\u0016\u00a2\u0006\u0002\b\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b1\u0010\u0019R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u00102\u001a\b\u0012\u0004\u0012\u00020\n03X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006O"}, d2 = {"Lcom/google/android/gnd/ui/home/mapcontainer/PolygonDrawingViewModel;", "Lcom/google/android/gnd/ui/common/AbstractViewModel;", "locationManager", "Lcom/google/android/gnd/system/LocationManager;", "authManager", "Lcom/google/android/gnd/system/auth/AuthenticationManager;", "uuidGenerator", "Lcom/google/android/gnd/persistence/uuid/OfflineUuidGenerator;", "(Lcom/google/android/gnd/system/LocationManager;Lcom/google/android/gnd/system/auth/AuthenticationManager;Lcom/google/android/gnd/persistence/uuid/OfflineUuidGenerator;)V", "cameraTarget", "Lcom/google/android/gnd/model/feature/Point;", "drawingState", "Lio/reactivex/Observable;", "Lcom/google/android/gnd/ui/home/mapcontainer/PolygonDrawingViewModel$PolygonDrawingState;", "Lcom/google/android/gnd/rx/annotations/Hot;", "getDrawingState", "()Lio/reactivex/Observable;", "firstVertex", "Ljava8/util/Optional;", "getFirstVertex", "()Ljava8/util/Optional;", "iconTint", "Landroidx/lifecycle/LiveData;", "", "getIconTint", "()Landroidx/lifecycle/LiveData;", "isLastVertexNotSelectedByUser", "", "isPolygonCompleted", "locationLockChangeRequests", "Lio/reactivex/subjects/Subject;", "locationLockEnabled", "Landroidx/lifecycle/MutableLiveData;", "replays", "locationLockState", "Lcom/google/android/gnd/rx/BooleanOrError;", "mapPolygon", "Lcom/google/android/gnd/ui/map/MapPolygon;", "kotlin.jvm.PlatformType", "mapPolygonFlowable", "polygonDrawingState", "selectedJob", "Lio/reactivex/processors/BehaviorProcessor;", "Lcom/google/android/gnd/model/job/Job;", "selectedSurvey", "Lcom/google/android/gnd/model/Survey;", "unsavedMapFeatures", "Lcom/google/common/collect/ImmutableSet;", "Lcom/google/android/gnd/ui/map/MapFeature;", "getUnsavedMapFeatures", "vertices", "", "addVertex", "", "vertex", "isNotSelectedByUser", "createLocationLockStateFlowable", "Lio/reactivex/Flowable;", "getLocationLockEnabled", "isLocationLockEnabled", "onCameraMoved", "newTarget", "onCompletePolygonButtonClick", "onLocationLockClick", "removeLastVertex", "reset", "selectCurrentVertex", "()Lkotlin/Unit;", "setLocationLockEnabled", "enabled", "startDrawingFlow", "updateLastVertex", "distanceInPixels", "", "updateVertices", "newVertices", "Lcom/google/common/collect/ImmutableList;", "Companion", "PolygonDrawingState", "gnd_debug"})
@com.google.android.gnd.ui.common.SharedViewModel()
public final class PolygonDrawingViewModel extends com.google.android.gnd.ui.common.AbstractViewModel {
    private final com.google.android.gnd.system.LocationManager locationManager = null;
    private final com.google.android.gnd.system.auth.AuthenticationManager authManager = null;
    private final com.google.android.gnd.persistence.uuid.OfflineUuidGenerator uuidGenerator = null;
    private final io.reactivex.subjects.Subject<com.google.android.gnd.ui.home.mapcontainer.PolygonDrawingViewModel.PolygonDrawingState> polygonDrawingState = null;
    private final io.reactivex.subjects.Subject<java8.util.Optional<com.google.android.gnd.ui.map.MapPolygon>> mapPolygonFlowable = null;
    
    /**
     * Denotes whether the drawn polygon is complete or not. This is different from drawing state.
     */
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Boolean> isPolygonCompleted = null;
    
    /**
     * Features drawn by the user but not yet saved.
     */
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.google.common.collect.ImmutableSet<com.google.android.gnd.ui.map.MapFeature>> unsavedMapFeatures = null;
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> locationLockEnabled = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Integer> iconTint = null;
    private final io.reactivex.subjects.Subject<java.lang.Boolean> locationLockChangeRequests = null;
    private final androidx.lifecycle.LiveData<com.google.android.gnd.rx.BooleanOrError> locationLockState = null;
    private final java.util.List<com.google.android.gnd.model.feature.Point> vertices = null;
    
    /**
     * The currently selected job and survey for the polygon drawing.
     */
    private final io.reactivex.processors.BehaviorProcessor<com.google.android.gnd.model.job.Job> selectedJob = null;
    private final io.reactivex.processors.BehaviorProcessor<com.google.android.gnd.model.Survey> selectedSurvey = null;
    private com.google.android.gnd.model.feature.Point cameraTarget;
    
    /**
     * If true, then it means that the last vertex is added automatically and should be removed before
     * adding any permanent vertex. Used for rendering a line between last added point and current
     * camera target.
     */
    private boolean isLastVertexNotSelectedByUser = false;
    private java8.util.Optional<com.google.android.gnd.ui.map.MapPolygon> mapPolygon;
    @org.jetbrains.annotations.NotNull()
    public static final com.google.android.gnd.ui.home.mapcontainer.PolygonDrawingViewModel.Companion Companion = null;
    
    /**
     * Min. distance in dp between two points for them be considered as overlapping.
     */
    public static final int DISTANCE_THRESHOLD_DP = 24;
    
    @javax.inject.Inject()
    public PolygonDrawingViewModel(@org.jetbrains.annotations.NotNull()
    com.google.android.gnd.system.LocationManager locationManager, @org.jetbrains.annotations.NotNull()
    com.google.android.gnd.system.auth.AuthenticationManager authManager, @org.jetbrains.annotations.NotNull()
    com.google.android.gnd.persistence.uuid.OfflineUuidGenerator uuidGenerator) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Boolean> isPolygonCompleted() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.google.common.collect.ImmutableSet<com.google.android.gnd.ui.map.MapFeature>> getUnsavedMapFeatures() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Integer> getIconTint() {
        return null;
    }
    
    private final io.reactivex.Flowable<com.google.android.gnd.rx.BooleanOrError> createLocationLockStateFlowable() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final io.reactivex.Observable<com.google.android.gnd.ui.home.mapcontainer.PolygonDrawingViewModel.PolygonDrawingState> getDrawingState() {
        return null;
    }
    
    public final void onCameraMoved(@org.jetbrains.annotations.NotNull()
    com.google.android.gnd.model.feature.Point newTarget) {
    }
    
    /**
     * Adds another vertex at the given point if {@param distanceInPixels} is more than the configured
     * threshold. Otherwise, snaps to the first vertex.
     *
     * @param newTarget Position of the map camera.
     * @param distanceInPixels Distance between the last vertex and {@param newTarget}.
     */
    public final void updateLastVertex(@org.jetbrains.annotations.NotNull()
    com.google.android.gnd.model.feature.Point newTarget, double distanceInPixels) {
    }
    
    /**
     * Attempts to remove the last vertex of drawn polygon, if any.
     */
    public final void removeLastVertex() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final kotlin.Unit selectCurrentVertex() {
        return null;
    }
    
    public final void setLocationLockEnabled(boolean enabled) {
    }
    
    /**
     * Adds a new vertex.
     *
     * @param vertex new position
     * @param isNotSelectedByUser whether the vertex is not selected by the user
     */
    private final void addVertex(com.google.android.gnd.model.feature.Point vertex, boolean isNotSelectedByUser) {
    }
    
    private final void updateVertices(com.google.common.collect.ImmutableList<com.google.android.gnd.model.feature.Point> newVertices) {
    }
    
    public final void onCompletePolygonButtonClick() {
    }
    
    private final void reset() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java8.util.Optional<com.google.android.gnd.model.feature.Point> getFirstVertex() {
        return null;
    }
    
    public final void onLocationLockClick() {
    }
    
    private final boolean isLocationLockEnabled() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Boolean> getLocationLockEnabled() {
        return null;
    }
    
    public final void startDrawingFlow(@org.jetbrains.annotations.NotNull()
    com.google.android.gnd.model.Survey selectedSurvey, @org.jetbrains.annotations.NotNull()
    com.google.android.gnd.model.job.Job selectedJob) {
    }
    
    @com.google.auto.value.AutoValue()
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\'\u0018\u0000 \u00102\u00020\u0001:\u0002\u0010\u0011B\u0005\u00a2\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u00048F\u00a2\u0006\u0006\u001a\u0004\b\u0003\u0010\u0005R\u0011\u0010\u0006\u001a\u00020\u00048F\u00a2\u0006\u0006\u001a\u0004\b\u0006\u0010\u0005R\u0011\u0010\u0007\u001a\u00020\u00048F\u00a2\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005R\u0012\u0010\b\u001a\u00020\tX\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u0004\u0018\u00010\rX\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006\u0012"}, d2 = {"Lcom/google/android/gnd/ui/home/mapcontainer/PolygonDrawingViewModel$PolygonDrawingState;", "", "()V", "isCanceled", "", "()Z", "isCompleted", "isInProgress", "state", "Lcom/google/android/gnd/ui/home/mapcontainer/PolygonDrawingViewModel$PolygonDrawingState$State;", "getState", "()Lcom/google/android/gnd/ui/home/mapcontainer/PolygonDrawingViewModel$PolygonDrawingState$State;", "unsavedPolygonFeature", "Lcom/google/android/gnd/model/feature/PolygonFeature;", "getUnsavedPolygonFeature", "()Lcom/google/android/gnd/model/feature/PolygonFeature;", "Companion", "State", "gnd_debug"})
    public static abstract class PolygonDrawingState {
        @org.jetbrains.annotations.NotNull()
        public static final com.google.android.gnd.ui.home.mapcontainer.PolygonDrawingViewModel.PolygonDrawingState.Companion Companion = null;
        
        public PolygonDrawingState() {
            super();
        }
        
        public final boolean isCanceled() {
            return false;
        }
        
        public final boolean isInProgress() {
            return false;
        }
        
        public final boolean isCompleted() {
            return false;
        }
        
        /**
         * Current state of polygon drawing.
         */
        @org.jetbrains.annotations.NotNull()
        public abstract com.google.android.gnd.ui.home.mapcontainer.PolygonDrawingViewModel.PolygonDrawingState.State getState();
        
        /**
         * Final polygon feature.
         */
        @org.jetbrains.annotations.Nullable()
        public abstract com.google.android.gnd.model.feature.PolygonFeature getUnsavedPolygonFeature();
        
        /**
         * Represents state of PolygonDrawing action.
         */
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005\u00a8\u0006\u0006"}, d2 = {"Lcom/google/android/gnd/ui/home/mapcontainer/PolygonDrawingViewModel$PolygonDrawingState$State;", "", "(Ljava/lang/String;I)V", "IN_PROGRESS", "COMPLETED", "CANCELED", "gnd_debug"})
        public static enum State {
            /*public static final*/ IN_PROGRESS /* = new IN_PROGRESS() */,
            /*public static final*/ COMPLETED /* = new COMPLETED() */,
            /*public static final*/ CANCELED /* = new CANCELED() */;
            
            State() {
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004J\u0010\u0010\u0005\u001a\u00020\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007J\u001a\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\n2\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0002J\u0006\u0010\u000b\u001a\u00020\u0004\u00a8\u0006\f"}, d2 = {"Lcom/google/android/gnd/ui/home/mapcontainer/PolygonDrawingViewModel$PolygonDrawingState$Companion;", "", "()V", "canceled", "Lcom/google/android/gnd/ui/home/mapcontainer/PolygonDrawingViewModel$PolygonDrawingState;", "completed", "unsavedFeature", "Lcom/google/android/gnd/model/feature/PolygonFeature;", "createDrawingState", "state", "Lcom/google/android/gnd/ui/home/mapcontainer/PolygonDrawingViewModel$PolygonDrawingState$State;", "inProgress", "gnd_debug"})
        public static final class Companion {
            
            private Companion() {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final com.google.android.gnd.ui.home.mapcontainer.PolygonDrawingViewModel.PolygonDrawingState canceled() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final com.google.android.gnd.ui.home.mapcontainer.PolygonDrawingViewModel.PolygonDrawingState inProgress() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final com.google.android.gnd.ui.home.mapcontainer.PolygonDrawingViewModel.PolygonDrawingState completed(@org.jetbrains.annotations.Nullable()
            com.google.android.gnd.model.feature.PolygonFeature unsavedFeature) {
                return null;
            }
            
            private final com.google.android.gnd.ui.home.mapcontainer.PolygonDrawingViewModel.PolygonDrawingState createDrawingState(com.google.android.gnd.ui.home.mapcontainer.PolygonDrawingViewModel.PolygonDrawingState.State state, com.google.android.gnd.model.feature.PolygonFeature unsavedFeature) {
                return null;
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\n"}, d2 = {"Lcom/google/android/gnd/ui/home/mapcontainer/PolygonDrawingViewModel$Companion;", "", "()V", "DISTANCE_THRESHOLD_DP", "", "unsavedFeaturesFromPolygon", "Lcom/google/common/collect/ImmutableSet;", "Lcom/google/android/gnd/ui/map/MapFeature;", "mapPolygon", "Lcom/google/android/gnd/ui/map/MapPolygon;", "gnd_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        /**
         * Returns a set of [MapFeature] to be drawn on map for the given [MapPolygon].
         */
        private final com.google.common.collect.ImmutableSet<com.google.android.gnd.ui.map.MapFeature> unsavedFeaturesFromPolygon(com.google.android.gnd.ui.map.MapPolygon mapPolygon) {
            return null;
        }
    }
}