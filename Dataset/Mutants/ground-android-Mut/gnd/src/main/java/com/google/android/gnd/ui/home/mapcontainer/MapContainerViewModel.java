/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.android.gnd.ui.home.mapcontainer;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.google.android.gnd.util.ImmutableSetCollector.toImmutableSet;
import static java8.util.stream.StreamSupport.stream;
import android.content.res.Resources;
import android.location.Location;
import androidx.annotation.ColorRes;
import androidx.annotation.Dimension;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MutableLiveData;
import com.cocoahero.android.gmaps.addons.mapbox.MapBoxOfflineTileProvider;
import com.google.android.gnd.R;
import com.google.android.gnd.model.Survey;
import com.google.android.gnd.model.basemap.tile.TileSet;
import com.google.android.gnd.model.feature.Feature;
import com.google.android.gnd.model.feature.GeoJsonFeature;
import com.google.android.gnd.model.feature.Point;
import com.google.android.gnd.model.feature.PointFeature;
import com.google.android.gnd.model.feature.PolygonFeature;
import com.google.android.gnd.model.job.Style;
import com.google.android.gnd.repository.FeatureRepository;
import com.google.android.gnd.repository.OfflineAreaRepository;
import com.google.android.gnd.repository.SurveyRepository;
import com.google.android.gnd.rx.BooleanOrError;
import com.google.android.gnd.rx.Event;
import com.google.android.gnd.rx.Loadable;
import com.google.android.gnd.rx.Nil;
import com.google.android.gnd.rx.annotations.Hot;
import com.google.android.gnd.system.LocationManager;
import com.google.android.gnd.ui.common.AbstractViewModel;
import com.google.android.gnd.ui.common.SharedViewModel;
import com.google.android.gnd.ui.map.CameraPosition;
import com.google.android.gnd.ui.map.MapFeature;
import com.google.android.gnd.ui.map.MapGeoJson;
import com.google.android.gnd.ui.map.MapPin;
import com.google.android.gnd.ui.map.MapPolygon;
import com.google.common.collect.ImmutableSet;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.processors.BehaviorProcessor;
import io.reactivex.processors.PublishProcessor;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java8.util.Optional;
import javax.inject.Inject;
import org.json.JSONException;
import org.json.JSONObject;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@SharedViewModel
public class MapContainerViewModel extends AbstractViewModel {

    // Higher zoom levels means the map is more zoomed in. 0.0f is fully zoomed out.
    public static final float ZOOM_LEVEL_THRESHOLD = 16f;

    public static final float DEFAULT_FEATURE_ZOOM_LEVEL = 18.0f;

    private static final float DEFAULT_MAP_ZOOM_LEVEL = 0.0f;

    private static final Point DEFAULT_MAP_POINT = Point.newBuilder().setLatitude(0.0).setLongitude(0.0).build();

    private final LiveData<Loadable<Survey>> surveyLoadingState;

    private final LiveData<ImmutableSet<MapFeature>> mapFeatures;

    private final LiveData<BooleanOrError> locationLockState;

    private final LiveData<Event<CameraUpdate>> cameraUpdateRequests;

    @Hot(replays = true)
    private final MutableLiveData<CameraPosition> cameraPosition = new MutableLiveData<>(new CameraPosition(DEFAULT_MAP_POINT, DEFAULT_MAP_ZOOM_LEVEL));

    private final Resources resources;

    private final SurveyRepository surveyRepository;

    private final LocationManager locationManager;

    private final FeatureRepository featureRepository;

    @Hot
    private final Subject<Boolean> locationLockChangeRequests = PublishSubject.create();

    @Hot
    private final Subject<CameraUpdate> cameraUpdateSubject = PublishSubject.create();

    /**
     * Temporary set of {@link MapFeature} used for displaying on map during add/edit flows.
     */
    @Hot
    private final PublishProcessor<ImmutableSet<MapFeature>> unsavedMapFeatures = PublishProcessor.create();

    @Hot(replays = true)
    private final MutableLiveData<Integer> mapControlsVisibility = new MutableLiveData<>(VISIBLE);

    private final MutableLiveData<Integer> addPolygonVisibility = new MutableLiveData<>(GONE);

    @Hot(replays = true)
    private final MutableLiveData<Integer> moveFeaturesVisibility = new MutableLiveData<>(GONE);

    @Hot(replays = true)
    private final MutableLiveData<Boolean> locationLockEnabled = new MutableLiveData<>();

    @Hot(replays = true)
    private final MutableLiveData<Integer> featureAddButtonBackgroundTint = new MutableLiveData<>(R.color.colorGrey500);

    private final LiveData<ImmutableSet<String>> mbtilesFilePaths;

    private final LiveData<Integer> iconTint;

    private final LiveData<Boolean> locationUpdatesEnabled;

    private final LiveData<String> locationAccuracy;

    private final List<MapBoxOfflineTileProvider> tileProviders = new ArrayList<>();

    @Dimension
    private final int defaultPolygonStrokeWidth;

    @Dimension
    private final int selectedPolygonStrokeWidth;

    /**
     * The currently selected feature on the map.
     */
    private final BehaviorProcessor<Optional<Feature>> selectedFeature = BehaviorProcessor.createDefault(Optional.empty());

    /* UI Clicks */
    @Hot
    private final Subject<Nil> selectMapTypeClicks = PublishSubject.create();

    @Hot
    private final Subject<Nil> zoomThresholdCrossed = PublishSubject.create();

    /**
     * Feature selected for repositioning.
     */
    private Optional<Feature> reposFeature = Optional.empty();

    @Inject
    MapContainerViewModel(Resources resources, SurveyRepository surveyRepository, FeatureRepository featureRepository, LocationManager locationManager, OfflineAreaRepository offlineAreaRepository) {
        // THIS SHOULD NOT BE CALLED ON CONFIG CHANGE
        this.resources = resources;
        this.surveyRepository = surveyRepository;
        this.featureRepository = featureRepository;
        this.locationManager = locationManager;
        this.defaultPolygonStrokeWidth = (int) resources.getDimension(R.dimen.polyline_stroke_width);
        this.selectedPolygonStrokeWidth = (int) resources.getDimension(R.dimen.selected_polyline_stroke_width);
        Flowable<BooleanOrError> locationLockStateFlowable = createLocationLockStateFlowable().share();
        this.locationLockState = LiveDataReactiveStreams.fromPublisher(locationLockStateFlowable.startWith(BooleanOrError.falseValue()));
        this.iconTint = LiveDataReactiveStreams.fromPublisher(locationLockStateFlowable.map(locked -> locked.isTrue() ? R.color.colorMapBlue : R.color.colorGrey800).startWith(R.color.colorGrey800));
        this.locationUpdatesEnabled = LiveDataReactiveStreams.fromPublisher(locationLockStateFlowable.map(BooleanOrError::isTrue).startWith(false));
        this.locationAccuracy = LiveDataReactiveStreams.fromPublisher(createLocationAccuracyFlowable(locationLockStateFlowable));
        this.cameraUpdateRequests = LiveDataReactiveStreams.fromPublisher(createCameraUpdateFlowable(locationLockStateFlowable));
        this.surveyLoadingState = LiveDataReactiveStreams.fromPublisher(surveyRepository.getSurveyLoadingState());
        // Features that are persisted to the local and remote dbs.
        Flowable<ImmutableSet<MapFeature>> savedMapFeatures = Flowable.combineLatest(surveyRepository.getActiveSurvey().switchMap(this::getFeaturesStream).map(this::toMapFeatures), selectedFeature, this::updateSelectedFeature);
        this.mapFeatures = LiveDataReactiveStreams.fromPublisher(Flowable.combineLatest(Arrays.asList(savedMapFeatures.startWith(ImmutableSet.<MapFeature>of()), unsavedMapFeatures.startWith(ImmutableSet.<MapFeature>of())), MapContainerViewModel::concatFeatureSets).distinctUntilChanged());
        this.mbtilesFilePaths = LiveDataReactiveStreams.fromPublisher(offlineAreaRepository.getDownloadedTileSetsOnceAndStream().map(set -> stream(set).map(TileSet::getPath).collect(toImmutableSet())));
        if (!ListenerUtil.mutListener.listen(736)) {
            disposeOnClear(surveyRepository.getActiveSurvey().subscribe(this::onSurveyChange));
        }
    }

    private static ImmutableSet<MapFeature> concatFeatureSets(Object[] objects) {
        return stream(Arrays.asList(objects)).flatMap(set -> stream((ImmutableSet<MapFeature>) set)).collect(toImmutableSet());
    }

    private static MapFeature toMapPin(PointFeature feature) {
        return MapPin.newBuilder().setId(feature.getId()).setPosition(feature.getPoint()).setStyle(Style.DEFAULT_MAP_STYLE).setFeature(feature).build();
    }

    private static MapFeature toMapPolygon(PolygonFeature feature) {
        return MapPolygon.newBuilder().setId(feature.getId()).setVertices(feature.getVertices()).setStyle(Style.DEFAULT_MAP_STYLE).setFeature(feature).build();
    }

    private void onSurveyChange(Optional<Survey> project) {
        if (!ListenerUtil.mutListener.listen(737)) {
            project.map(Survey::getId).flatMap(surveyRepository::getLastCameraPosition).ifPresent(this::panAndZoomCamera);
        }
    }

    public void setUnsavedMapFeatures(ImmutableSet<MapFeature> features) {
        if (!ListenerUtil.mutListener.listen(738)) {
            unsavedMapFeatures.onNext(features);
        }
    }

    private ImmutableSet<MapFeature> updateSelectedFeature(ImmutableSet<MapFeature> features, Optional<Feature> selectedFeature) {
        if (!ListenerUtil.mutListener.listen(739)) {
            Timber.v("Updating selected feature style");
        }
        if (!ListenerUtil.mutListener.listen(740)) {
            if (selectedFeature.isEmpty()) {
                return features;
            }
        }
        ImmutableSet.Builder updatedFeatures = ImmutableSet.builder();
        String selectedFeatureId = selectedFeature.get().getId();
        if (!ListenerUtil.mutListener.listen(746)) {
            {
                long _loopCounter22 = 0;
                for (MapFeature feature : features) {
                    ListenerUtil.loopListener.listen("_loopCounter22", ++_loopCounter22);
                    if (!ListenerUtil.mutListener.listen(744)) {
                        if (feature instanceof MapGeoJson) {
                            MapGeoJson geoJsonFeature = (MapGeoJson) feature;
                            String geoJsonFeatureId = geoJsonFeature.getFeature().getId();
                            if (!ListenerUtil.mutListener.listen(743)) {
                                if (geoJsonFeatureId.equals(selectedFeatureId)) {
                                    if (!ListenerUtil.mutListener.listen(741)) {
                                        Timber.v("Restyling selected GeoJSON feature " + selectedFeatureId);
                                    }
                                    if (!ListenerUtil.mutListener.listen(742)) {
                                        updatedFeatures.add(geoJsonFeature.toBuilder().setStrokeWidth(selectedPolygonStrokeWidth).build());
                                    }
                                    continue;
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(745)) {
                        updatedFeatures.add(feature);
                    }
                }
            }
        }
        return updatedFeatures.build();
    }

    private ImmutableSet<MapFeature> toMapFeatures(ImmutableSet<Feature> features) {
        ImmutableSet<MapFeature> mapPins = stream(features).filter(Feature::isPoint).map(PointFeature.class::cast).map(MapContainerViewModel::toMapPin).collect(toImmutableSet());
        ImmutableSet<MapFeature> mapGeoJson = stream(features).filter(Feature::isGeoJson).map(GeoJsonFeature.class::cast).map(this::toMapGeoJson).collect(toImmutableSet());
        ImmutableSet<MapFeature> mapPolygons = stream(features).filter(Feature::isPolygon).map(PolygonFeature.class::cast).map(MapContainerViewModel::toMapPolygon).collect(toImmutableSet());
        return ImmutableSet.<MapFeature>builder().addAll(mapPins).addAll(mapGeoJson).addAll(mapPolygons).build();
    }

    private MapGeoJson toMapGeoJson(GeoJsonFeature feature) {
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(feature.getGeoJsonString());
        } catch (JSONException e) {
            if (!ListenerUtil.mutListener.listen(747)) {
                Timber.e(e);
            }
            jsonObject = new JSONObject();
        }
        return MapGeoJson.newBuilder().setId(feature.getId()).setGeoJson(jsonObject).setStyle(Style.DEFAULT_MAP_STYLE).setStrokeWidth(defaultPolygonStrokeWidth).setFeature(feature).build();
    }

    private Flowable<String> createLocationAccuracyFlowable(Flowable<BooleanOrError> lockState) {
        return lockState.switchMap(booleanOrError -> booleanOrError.isTrue() ? locationManager.getLocationUpdates().map(Location::getAccuracy).map(accuracy -> resources.getString(R.string.location_accuracy, accuracy)) : Flowable.empty());
    }

    private Flowable<Event<CameraUpdate>> createCameraUpdateFlowable(Flowable<BooleanOrError> locationLockStateFlowable) {
        return cameraUpdateSubject.toFlowable(BackpressureStrategy.LATEST).mergeWith(locationLockStateFlowable.switchMap(this::createLocationLockCameraUpdateFlowable)).map(Event::create);
    }

    private Flowable<CameraUpdate> createLocationLockCameraUpdateFlowable(BooleanOrError lockState) {
        if (!ListenerUtil.mutListener.listen(748)) {
            if (!lockState.isTrue()) {
                return Flowable.empty();
            }
        }
        // only pan the map.
        Flowable<Point> locationUpdates = locationManager.getLocationUpdates().map(MapContainerViewModel::toPoint);
        return locationUpdates.take(1).map(CameraUpdate::panAndZoomIn).concatWith(locationUpdates.map(CameraUpdate::pan).skip(1));
    }

    private static Point toPoint(Location location) {
        return Point.newBuilder().setLatitude(location.getLatitude()).setLongitude(location.getLongitude()).build();
    }

    private Flowable<BooleanOrError> createLocationLockStateFlowable() {
        return locationLockChangeRequests.switchMapSingle(enabled -> enabled ? this.locationManager.enableLocationUpdates() : this.locationManager.disableLocationUpdates()).toFlowable(BackpressureStrategy.LATEST);
    }

    private Flowable<ImmutableSet<Feature>> getFeaturesStream(Optional<Survey> activeProject) {
        // subscribers.
        return activeProject.map(featureRepository::getFeaturesOnceAndStream).orElse(Flowable.just(ImmutableSet.of()));
    }

    public LiveData<Loadable<Survey>> getSurveyLoadingState() {
        return surveyLoadingState;
    }

    public LiveData<ImmutableSet<MapFeature>> getMapFeatures() {
        return mapFeatures;
    }

    public LiveData<ImmutableSet<String>> getMbtilesFilePaths() {
        return mbtilesFilePaths;
    }

    LiveData<Event<CameraUpdate>> getCameraUpdateRequests() {
        return cameraUpdateRequests;
    }

    public LiveData<CameraPosition> getCameraPosition() {
        if (!ListenerUtil.mutListener.listen(749)) {
            Timber.d("Current position is %s", cameraPosition.getValue().toString());
        }
        return cameraPosition;
    }

    public LiveData<BooleanOrError> getLocationLockState() {
        return locationLockState;
    }

    public LiveData<Boolean> isLocationUpdatesEnabled() {
        return locationUpdatesEnabled;
    }

    public LiveData<String> getLocationAccuracy() {
        return locationAccuracy;
    }

    public LiveData<Integer> getIconTint() {
        return iconTint;
    }

    private boolean isLocationLockEnabled() {
        return locationLockState.getValue().isTrue();
    }

    public void onCameraMove(CameraPosition newCameraPosition) {
        if (!ListenerUtil.mutListener.listen(750)) {
            Timber.d("Setting position to %s", newCameraPosition.toString());
        }
        if (!ListenerUtil.mutListener.listen(751)) {
            onZoomChange(cameraPosition.getValue().getZoomLevel(), newCameraPosition.getZoomLevel());
        }
        if (!ListenerUtil.mutListener.listen(752)) {
            cameraPosition.setValue(newCameraPosition);
        }
        if (!ListenerUtil.mutListener.listen(753)) {
            Loadable.getValue(surveyLoadingState).ifPresent(project -> surveyRepository.setCameraPosition(project.getId(), newCameraPosition));
        }
    }

    private void onZoomChange(float oldZoomLevel, float newZoomLevel) {
        boolean zoomThresholdCrossed = (ListenerUtil.mutListener.listen(776) ? ((ListenerUtil.mutListener.listen(764) ? ((ListenerUtil.mutListener.listen(758) ? (oldZoomLevel >= ZOOM_LEVEL_THRESHOLD) : (ListenerUtil.mutListener.listen(757) ? (oldZoomLevel <= ZOOM_LEVEL_THRESHOLD) : (ListenerUtil.mutListener.listen(756) ? (oldZoomLevel > ZOOM_LEVEL_THRESHOLD) : (ListenerUtil.mutListener.listen(755) ? (oldZoomLevel != ZOOM_LEVEL_THRESHOLD) : (ListenerUtil.mutListener.listen(754) ? (oldZoomLevel == ZOOM_LEVEL_THRESHOLD) : (oldZoomLevel < ZOOM_LEVEL_THRESHOLD)))))) || (ListenerUtil.mutListener.listen(763) ? (newZoomLevel <= ZOOM_LEVEL_THRESHOLD) : (ListenerUtil.mutListener.listen(762) ? (newZoomLevel > ZOOM_LEVEL_THRESHOLD) : (ListenerUtil.mutListener.listen(761) ? (newZoomLevel < ZOOM_LEVEL_THRESHOLD) : (ListenerUtil.mutListener.listen(760) ? (newZoomLevel != ZOOM_LEVEL_THRESHOLD) : (ListenerUtil.mutListener.listen(759) ? (newZoomLevel == ZOOM_LEVEL_THRESHOLD) : (newZoomLevel >= ZOOM_LEVEL_THRESHOLD))))))) : ((ListenerUtil.mutListener.listen(758) ? (oldZoomLevel >= ZOOM_LEVEL_THRESHOLD) : (ListenerUtil.mutListener.listen(757) ? (oldZoomLevel <= ZOOM_LEVEL_THRESHOLD) : (ListenerUtil.mutListener.listen(756) ? (oldZoomLevel > ZOOM_LEVEL_THRESHOLD) : (ListenerUtil.mutListener.listen(755) ? (oldZoomLevel != ZOOM_LEVEL_THRESHOLD) : (ListenerUtil.mutListener.listen(754) ? (oldZoomLevel == ZOOM_LEVEL_THRESHOLD) : (oldZoomLevel < ZOOM_LEVEL_THRESHOLD)))))) && (ListenerUtil.mutListener.listen(763) ? (newZoomLevel <= ZOOM_LEVEL_THRESHOLD) : (ListenerUtil.mutListener.listen(762) ? (newZoomLevel > ZOOM_LEVEL_THRESHOLD) : (ListenerUtil.mutListener.listen(761) ? (newZoomLevel < ZOOM_LEVEL_THRESHOLD) : (ListenerUtil.mutListener.listen(760) ? (newZoomLevel != ZOOM_LEVEL_THRESHOLD) : (ListenerUtil.mutListener.listen(759) ? (newZoomLevel == ZOOM_LEVEL_THRESHOLD) : (newZoomLevel >= ZOOM_LEVEL_THRESHOLD)))))))) && (ListenerUtil.mutListener.listen(775) ? ((ListenerUtil.mutListener.listen(769) ? (oldZoomLevel <= ZOOM_LEVEL_THRESHOLD) : (ListenerUtil.mutListener.listen(768) ? (oldZoomLevel > ZOOM_LEVEL_THRESHOLD) : (ListenerUtil.mutListener.listen(767) ? (oldZoomLevel < ZOOM_LEVEL_THRESHOLD) : (ListenerUtil.mutListener.listen(766) ? (oldZoomLevel != ZOOM_LEVEL_THRESHOLD) : (ListenerUtil.mutListener.listen(765) ? (oldZoomLevel == ZOOM_LEVEL_THRESHOLD) : (oldZoomLevel >= ZOOM_LEVEL_THRESHOLD)))))) || (ListenerUtil.mutListener.listen(774) ? (newZoomLevel >= ZOOM_LEVEL_THRESHOLD) : (ListenerUtil.mutListener.listen(773) ? (newZoomLevel <= ZOOM_LEVEL_THRESHOLD) : (ListenerUtil.mutListener.listen(772) ? (newZoomLevel > ZOOM_LEVEL_THRESHOLD) : (ListenerUtil.mutListener.listen(771) ? (newZoomLevel != ZOOM_LEVEL_THRESHOLD) : (ListenerUtil.mutListener.listen(770) ? (newZoomLevel == ZOOM_LEVEL_THRESHOLD) : (newZoomLevel < ZOOM_LEVEL_THRESHOLD))))))) : ((ListenerUtil.mutListener.listen(769) ? (oldZoomLevel <= ZOOM_LEVEL_THRESHOLD) : (ListenerUtil.mutListener.listen(768) ? (oldZoomLevel > ZOOM_LEVEL_THRESHOLD) : (ListenerUtil.mutListener.listen(767) ? (oldZoomLevel < ZOOM_LEVEL_THRESHOLD) : (ListenerUtil.mutListener.listen(766) ? (oldZoomLevel != ZOOM_LEVEL_THRESHOLD) : (ListenerUtil.mutListener.listen(765) ? (oldZoomLevel == ZOOM_LEVEL_THRESHOLD) : (oldZoomLevel >= ZOOM_LEVEL_THRESHOLD)))))) && (ListenerUtil.mutListener.listen(774) ? (newZoomLevel >= ZOOM_LEVEL_THRESHOLD) : (ListenerUtil.mutListener.listen(773) ? (newZoomLevel <= ZOOM_LEVEL_THRESHOLD) : (ListenerUtil.mutListener.listen(772) ? (newZoomLevel > ZOOM_LEVEL_THRESHOLD) : (ListenerUtil.mutListener.listen(771) ? (newZoomLevel != ZOOM_LEVEL_THRESHOLD) : (ListenerUtil.mutListener.listen(770) ? (newZoomLevel == ZOOM_LEVEL_THRESHOLD) : (newZoomLevel < ZOOM_LEVEL_THRESHOLD))))))))) : ((ListenerUtil.mutListener.listen(764) ? ((ListenerUtil.mutListener.listen(758) ? (oldZoomLevel >= ZOOM_LEVEL_THRESHOLD) : (ListenerUtil.mutListener.listen(757) ? (oldZoomLevel <= ZOOM_LEVEL_THRESHOLD) : (ListenerUtil.mutListener.listen(756) ? (oldZoomLevel > ZOOM_LEVEL_THRESHOLD) : (ListenerUtil.mutListener.listen(755) ? (oldZoomLevel != ZOOM_LEVEL_THRESHOLD) : (ListenerUtil.mutListener.listen(754) ? (oldZoomLevel == ZOOM_LEVEL_THRESHOLD) : (oldZoomLevel < ZOOM_LEVEL_THRESHOLD)))))) || (ListenerUtil.mutListener.listen(763) ? (newZoomLevel <= ZOOM_LEVEL_THRESHOLD) : (ListenerUtil.mutListener.listen(762) ? (newZoomLevel > ZOOM_LEVEL_THRESHOLD) : (ListenerUtil.mutListener.listen(761) ? (newZoomLevel < ZOOM_LEVEL_THRESHOLD) : (ListenerUtil.mutListener.listen(760) ? (newZoomLevel != ZOOM_LEVEL_THRESHOLD) : (ListenerUtil.mutListener.listen(759) ? (newZoomLevel == ZOOM_LEVEL_THRESHOLD) : (newZoomLevel >= ZOOM_LEVEL_THRESHOLD))))))) : ((ListenerUtil.mutListener.listen(758) ? (oldZoomLevel >= ZOOM_LEVEL_THRESHOLD) : (ListenerUtil.mutListener.listen(757) ? (oldZoomLevel <= ZOOM_LEVEL_THRESHOLD) : (ListenerUtil.mutListener.listen(756) ? (oldZoomLevel > ZOOM_LEVEL_THRESHOLD) : (ListenerUtil.mutListener.listen(755) ? (oldZoomLevel != ZOOM_LEVEL_THRESHOLD) : (ListenerUtil.mutListener.listen(754) ? (oldZoomLevel == ZOOM_LEVEL_THRESHOLD) : (oldZoomLevel < ZOOM_LEVEL_THRESHOLD)))))) && (ListenerUtil.mutListener.listen(763) ? (newZoomLevel <= ZOOM_LEVEL_THRESHOLD) : (ListenerUtil.mutListener.listen(762) ? (newZoomLevel > ZOOM_LEVEL_THRESHOLD) : (ListenerUtil.mutListener.listen(761) ? (newZoomLevel < ZOOM_LEVEL_THRESHOLD) : (ListenerUtil.mutListener.listen(760) ? (newZoomLevel != ZOOM_LEVEL_THRESHOLD) : (ListenerUtil.mutListener.listen(759) ? (newZoomLevel == ZOOM_LEVEL_THRESHOLD) : (newZoomLevel >= ZOOM_LEVEL_THRESHOLD)))))))) || (ListenerUtil.mutListener.listen(775) ? ((ListenerUtil.mutListener.listen(769) ? (oldZoomLevel <= ZOOM_LEVEL_THRESHOLD) : (ListenerUtil.mutListener.listen(768) ? (oldZoomLevel > ZOOM_LEVEL_THRESHOLD) : (ListenerUtil.mutListener.listen(767) ? (oldZoomLevel < ZOOM_LEVEL_THRESHOLD) : (ListenerUtil.mutListener.listen(766) ? (oldZoomLevel != ZOOM_LEVEL_THRESHOLD) : (ListenerUtil.mutListener.listen(765) ? (oldZoomLevel == ZOOM_LEVEL_THRESHOLD) : (oldZoomLevel >= ZOOM_LEVEL_THRESHOLD)))))) || (ListenerUtil.mutListener.listen(774) ? (newZoomLevel >= ZOOM_LEVEL_THRESHOLD) : (ListenerUtil.mutListener.listen(773) ? (newZoomLevel <= ZOOM_LEVEL_THRESHOLD) : (ListenerUtil.mutListener.listen(772) ? (newZoomLevel > ZOOM_LEVEL_THRESHOLD) : (ListenerUtil.mutListener.listen(771) ? (newZoomLevel != ZOOM_LEVEL_THRESHOLD) : (ListenerUtil.mutListener.listen(770) ? (newZoomLevel == ZOOM_LEVEL_THRESHOLD) : (newZoomLevel < ZOOM_LEVEL_THRESHOLD))))))) : ((ListenerUtil.mutListener.listen(769) ? (oldZoomLevel <= ZOOM_LEVEL_THRESHOLD) : (ListenerUtil.mutListener.listen(768) ? (oldZoomLevel > ZOOM_LEVEL_THRESHOLD) : (ListenerUtil.mutListener.listen(767) ? (oldZoomLevel < ZOOM_LEVEL_THRESHOLD) : (ListenerUtil.mutListener.listen(766) ? (oldZoomLevel != ZOOM_LEVEL_THRESHOLD) : (ListenerUtil.mutListener.listen(765) ? (oldZoomLevel == ZOOM_LEVEL_THRESHOLD) : (oldZoomLevel >= ZOOM_LEVEL_THRESHOLD)))))) && (ListenerUtil.mutListener.listen(774) ? (newZoomLevel >= ZOOM_LEVEL_THRESHOLD) : (ListenerUtil.mutListener.listen(773) ? (newZoomLevel <= ZOOM_LEVEL_THRESHOLD) : (ListenerUtil.mutListener.listen(772) ? (newZoomLevel > ZOOM_LEVEL_THRESHOLD) : (ListenerUtil.mutListener.listen(771) ? (newZoomLevel != ZOOM_LEVEL_THRESHOLD) : (ListenerUtil.mutListener.listen(770) ? (newZoomLevel == ZOOM_LEVEL_THRESHOLD) : (newZoomLevel < ZOOM_LEVEL_THRESHOLD))))))))));
        if (!ListenerUtil.mutListener.listen(778)) {
            if (zoomThresholdCrossed) {
                if (!ListenerUtil.mutListener.listen(777)) {
                    this.zoomThresholdCrossed.onNext(Nil.NIL);
                }
            }
        }
    }

    public void onMapDrag() {
        if (!ListenerUtil.mutListener.listen(781)) {
            if (isLocationLockEnabled()) {
                if (!ListenerUtil.mutListener.listen(779)) {
                    Timber.d("User dragged map. Disabling location lock");
                }
                if (!ListenerUtil.mutListener.listen(780)) {
                    locationLockChangeRequests.onNext(false);
                }
            }
        }
    }

    public void onMarkerClick(MapPin pin) {
        if (!ListenerUtil.mutListener.listen(782)) {
            panAndZoomCamera(pin.getPosition());
        }
    }

    public void panAndZoomCamera(CameraPosition cameraPosition) {
        if (!ListenerUtil.mutListener.listen(783)) {
            cameraUpdateSubject.onNext(CameraUpdate.panAndZoom(cameraPosition));
        }
    }

    public void panAndZoomCamera(Point position) {
        if (!ListenerUtil.mutListener.listen(784)) {
            cameraUpdateSubject.onNext(CameraUpdate.panAndZoomIn(position));
        }
    }

    public void onLocationLockClick() {
        if (!ListenerUtil.mutListener.listen(785)) {
            locationLockChangeRequests.onNext(!isLocationLockEnabled());
        }
    }

    // TODO(#691): Create our own wrapper/interface for MbTiles providers.
    public void queueTileProvider(MapBoxOfflineTileProvider tileProvider) {
        if (!ListenerUtil.mutListener.listen(786)) {
            this.tileProviders.add(tileProvider);
        }
    }

    public void closeProviders() {
        if (!ListenerUtil.mutListener.listen(787)) {
            stream(tileProviders).forEach(MapBoxOfflineTileProvider::close);
        }
    }

    public void setMode(Mode viewMode) {
        if (!ListenerUtil.mutListener.listen(788)) {
            mapControlsVisibility.postValue(viewMode == Mode.DEFAULT ? VISIBLE : GONE);
        }
        if (!ListenerUtil.mutListener.listen(789)) {
            moveFeaturesVisibility.postValue(viewMode == Mode.MOVE_POINT ? VISIBLE : GONE);
        }
        if (!ListenerUtil.mutListener.listen(790)) {
            addPolygonVisibility.postValue(viewMode == Mode.DRAW_POLYGON ? VISIBLE : GONE);
        }
        if (!ListenerUtil.mutListener.listen(792)) {
            if (viewMode == Mode.DEFAULT) {
                if (!ListenerUtil.mutListener.listen(791)) {
                    setReposFeature(Optional.empty());
                }
            }
        }
    }

    public void onMapTypeButtonClicked() {
        if (!ListenerUtil.mutListener.listen(793)) {
            selectMapTypeClicks.onNext(Nil.NIL);
        }
    }

    public Observable<Nil> getSelectMapTypeClicks() {
        return selectMapTypeClicks;
    }

    public Observable<Nil> getZoomThresholdCrossed() {
        return zoomThresholdCrossed;
    }

    public LiveData<Integer> getMapControlsVisibility() {
        return mapControlsVisibility;
    }

    public LiveData<Integer> getMoveFeatureVisibility() {
        return moveFeaturesVisibility;
    }

    public LiveData<Integer> getAddPolygonVisibility() {
        return addPolygonVisibility;
    }

    public Optional<Feature> getReposFeature() {
        return reposFeature;
    }

    public void setReposFeature(Optional<Feature> reposFeature) {
        if (!ListenerUtil.mutListener.listen(794)) {
            this.reposFeature = reposFeature;
        }
    }

    /**
     * Called when a feature is (de)selected.
     */
    public void setSelectedFeature(Optional<Feature> selectedFeature) {
        if (!ListenerUtil.mutListener.listen(795)) {
            this.selectedFeature.onNext(selectedFeature);
        }
    }

    public void setFeatureButtonBackgroundTint(@ColorRes int colorRes) {
        if (!ListenerUtil.mutListener.listen(796)) {
            featureAddButtonBackgroundTint.postValue(colorRes);
        }
    }

    public LiveData<Integer> getFeatureAddButtonBackgroundTint() {
        return featureAddButtonBackgroundTint;
    }

    public LiveData<Boolean> getLocationLockEnabled() {
        return locationLockEnabled;
    }

    public void setLocationLockEnabled(boolean enabled) {
        if (!ListenerUtil.mutListener.listen(797)) {
            locationLockEnabled.postValue(enabled);
        }
    }

    public enum Mode {

        DEFAULT, DRAW_POLYGON, MOVE_POINT
    }

    static class CameraUpdate {

        private final Point center;

        private final Optional<Float> zoomLevel;

        private final boolean allowZoomOut;

        public CameraUpdate(Point center, Optional<Float> zoomLevel, boolean allowZoomOut) {
            this.center = center;
            this.zoomLevel = zoomLevel;
            this.allowZoomOut = allowZoomOut;
        }

        private static CameraUpdate pan(Point center) {
            return new CameraUpdate(center, Optional.empty(), false);
        }

        private static CameraUpdate panAndZoomIn(Point center) {
            return new CameraUpdate(center, Optional.of(DEFAULT_FEATURE_ZOOM_LEVEL), false);
        }

        public static CameraUpdate panAndZoom(CameraPosition cameraPosition) {
            return new CameraUpdate(cameraPosition.getTarget(), Optional.of(cameraPosition.getZoomLevel()), true);
        }

        public Point getCenter() {
            return center;
        }

        public Optional<Float> getZoomLevel() {
            return zoomLevel;
        }

        public boolean isAllowZoomOut() {
            return allowZoomOut;
        }

        @NonNull
        @Override
        public String toString() {
            if (zoomLevel.isPresent()) {
                return "Pan + zoom";
            } else {
                return "Pan";
            }
        }
    }
}
