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
package com.google.android.gnd.ui.home;

import static com.google.android.gnd.rx.Nil.NIL;
import static com.google.android.gnd.rx.RxCompletable.toBooleanSingle;
import static com.google.android.gnd.util.ImmutableListCollector.toImmutableList;
import static java8.util.stream.StreamSupport.stream;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MutableLiveData;
import com.google.android.gnd.model.Survey;
import com.google.android.gnd.model.feature.Feature;
import com.google.android.gnd.model.feature.Point;
import com.google.android.gnd.model.feature.PolygonFeature;
import com.google.android.gnd.model.job.Job;
import com.google.android.gnd.model.mutation.FeatureMutation;
import com.google.android.gnd.model.mutation.Mutation.Type;
import com.google.android.gnd.model.task.Task;
import com.google.android.gnd.repository.FeatureRepository;
import com.google.android.gnd.repository.SurveyRepository;
import com.google.android.gnd.repository.UserRepository;
import com.google.android.gnd.rx.Loadable;
import com.google.android.gnd.rx.Nil;
import com.google.android.gnd.rx.annotations.Hot;
import com.google.android.gnd.ui.common.AbstractViewModel;
import com.google.android.gnd.ui.common.Navigator;
import com.google.android.gnd.ui.common.SharedViewModel;
import com.google.android.gnd.ui.map.MapFeature;
import com.google.android.gnd.ui.map.MapPin;
import com.google.common.collect.ImmutableList;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.processors.PublishProcessor;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;
import java.util.Date;
import java8.util.Objects;
import java8.util.Optional;
import javax.inject.Inject;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@SharedViewModel
public class HomeScreenViewModel extends AbstractViewModel {

    @Hot(replays = true)
    public final MutableLiveData<Boolean> isSubmissionButtonVisible = new MutableLiveData<>(false);

    private final SurveyRepository surveyRepository;

    private final Navigator navigator;

    private final FeatureRepository featureRepository;

    private final UserRepository userRepository;

    /**
     * The state and value of the currently active survey (loading, loaded, etc.).
     */
    private final LiveData<Loadable<Survey>> surveyLoadingState;

    // TODO(#719): Move into FeatureDetailsViewModel.
    @Hot
    private final FlowableProcessor<Nil> openDrawerRequests = PublishProcessor.create();

    @Hot(replays = true)
    private final MutableLiveData<BottomSheetState> bottomSheetState = new MutableLiveData<>();

    @Hot
    private final FlowableProcessor<FeatureMutation> addFeatureRequests = PublishProcessor.create();

    @Hot
    private final FlowableProcessor<FeatureMutation> updateFeatureRequests = PublishProcessor.create();

    @Hot
    private final FlowableProcessor<FeatureMutation> deleteFeatureRequests = PublishProcessor.create();

    @Hot
    private final Flowable<Feature> addFeatureResults;

    @Hot
    private final Flowable<Boolean> updateFeatureResults;

    @Hot
    private final Flowable<Boolean> deleteFeatureResults;

    @Hot
    private final FlowableProcessor<Throwable> errors = PublishProcessor.create();

    @Hot
    private final Subject<ImmutableList<Feature>> showFeatureSelectorRequests = PublishSubject.create();

    @Inject
    HomeScreenViewModel(SurveyRepository surveyRepository, FeatureRepository featureRepository, Navigator navigator, UserRepository userRepository) {
        this.surveyRepository = surveyRepository;
        this.featureRepository = featureRepository;
        this.navigator = navigator;
        this.userRepository = userRepository;
        surveyLoadingState = LiveDataReactiveStreams.fromPublisher(surveyRepository.getSurveyLoadingState());
        addFeatureResults = addFeatureRequests.switchMapSingle(mutation -> featureRepository.applyAndEnqueue(mutation).andThen(featureRepository.getFeature(mutation)).doOnError(errors::onNext).onErrorResumeNext(// Prevent from breaking upstream.
        Single.never()));
        deleteFeatureResults = deleteFeatureRequests.switchMapSingle(mutation -> toBooleanSingle(featureRepository.applyAndEnqueue(mutation), errors::onNext));
        updateFeatureResults = updateFeatureRequests.switchMapSingle(mutation -> toBooleanSingle(featureRepository.applyAndEnqueue(mutation), errors::onNext));
    }

    @Hot
    public Observable<ImmutableList<Feature>> getShowFeatureSelectorRequests() {
        return showFeatureSelectorRequests;
    }

    public Flowable<Feature> getAddFeatureResults() {
        return addFeatureResults;
    }

    public Flowable<Boolean> getUpdateFeatureResults() {
        return updateFeatureResults;
    }

    public Flowable<Boolean> getDeleteFeatureResults() {
        return deleteFeatureResults;
    }

    public Flowable<Throwable> getErrors() {
        return errors;
    }

    public void addFeature(Job job, Point point) {
        if (!ListenerUtil.mutListener.listen(1012)) {
            getActiveSurvey().map(Survey::getId).ifPresentOrElse(surveyId -> addFeatureRequests.onNext(featureRepository.newMutation(surveyId, job.getId(), point, new Date())), () -> {
                throw new IllegalStateException("Empty survey");
            });
        }
    }

    public void addPolygonFeature(PolygonFeature feature) {
        if (!ListenerUtil.mutListener.listen(1013)) {
            getActiveSurvey().map(Survey::getId).ifPresentOrElse(surveyId -> addFeatureRequests.onNext(featureRepository.newPolygonFeatureMutation(surveyId, feature.getJob().getId(), feature.getVertices(), new Date())), () -> {
                throw new IllegalStateException("Empty survey");
            });
        }
    }

    public void updateFeature(Feature feature) {
        if (!ListenerUtil.mutListener.listen(1014)) {
            updateFeatureRequests.onNext(feature.toMutation(Type.UPDATE, userRepository.getCurrentUser().getId()));
        }
    }

    public void deleteFeature(Feature feature) {
        if (!ListenerUtil.mutListener.listen(1015)) {
            deleteFeatureRequests.onNext(feature.toMutation(Type.DELETE, userRepository.getCurrentUser().getId()));
        }
    }

    public boolean shouldShowSurveySelectorOnStart() {
        return surveyRepository.getLastActiveSurveyId().isEmpty();
    }

    public Flowable<Nil> getOpenDrawerRequests() {
        return openDrawerRequests;
    }

    public void openNavDrawer() {
        if (!ListenerUtil.mutListener.listen(1016)) {
            openDrawerRequests.onNext(NIL);
        }
    }

    public LiveData<Loadable<Survey>> getSurveyLoadingState() {
        return surveyLoadingState;
    }

    public LiveData<BottomSheetState> getBottomSheetState() {
        return bottomSheetState;
    }

    public void onMarkerClick(MapPin marker) {
        if (!ListenerUtil.mutListener.listen(1018)) {
            if (marker.getFeature() != null) {
                if (!ListenerUtil.mutListener.listen(1017)) {
                    showBottomSheet(marker.getFeature());
                }
            }
        }
    }

    public void onFeatureSelected(Feature feature) {
        if (!ListenerUtil.mutListener.listen(1019)) {
            showBottomSheet(feature);
        }
    }

    private void showBottomSheet(Feature feature) {
        if (!ListenerUtil.mutListener.listen(1020)) {
            Timber.d("showing bottom sheet");
        }
        if (!ListenerUtil.mutListener.listen(1021)) {
            isSubmissionButtonVisible.setValue(true);
        }
        if (!ListenerUtil.mutListener.listen(1022)) {
            bottomSheetState.setValue(BottomSheetState.visible(feature));
        }
    }

    public void onBottomSheetHidden() {
        if (!ListenerUtil.mutListener.listen(1023)) {
            bottomSheetState.setValue(BottomSheetState.hidden());
        }
        if (!ListenerUtil.mutListener.listen(1024)) {
            isSubmissionButtonVisible.setValue(false);
        }
    }

    public void addSubmission() {
        BottomSheetState state = bottomSheetState.getValue();
        if (!ListenerUtil.mutListener.listen(1026)) {
            if (state == null) {
                if (!ListenerUtil.mutListener.listen(1025)) {
                    Timber.e("Missing bottomSheetState");
                }
                return;
            }
        }
        Optional<Feature> optionalFeature = state.getFeature();
        if (!ListenerUtil.mutListener.listen(1028)) {
            if (optionalFeature.isEmpty()) {
                if (!ListenerUtil.mutListener.listen(1027)) {
                    Timber.e("Missing feature");
                }
                return;
            }
        }
        Feature feature = optionalFeature.get();
        Optional<Task> form = feature.getJob().getTask();
        if (!ListenerUtil.mutListener.listen(1030)) {
            if (form.isEmpty()) {
                if (!ListenerUtil.mutListener.listen(1029)) {
                    // .TODO: Hide Add Submission button if no forms defined.
                    Timber.e("No tasks in job");
                }
                return;
            }
        }
        Survey survey = feature.getSurvey();
        if (!ListenerUtil.mutListener.listen(1032)) {
            if (survey == null) {
                if (!ListenerUtil.mutListener.listen(1031)) {
                    Timber.e("Missing survey");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(1033)) {
            navigator.navigate(HomeScreenFragmentDirections.addSubmission(survey.getId(), feature.getId(), form.get().getId()));
        }
    }

    public void init() {
        if (!ListenerUtil.mutListener.listen(1034)) {
            // Last active survey will be loaded once view subscribes to activeProject.
            surveyRepository.loadLastActiveSurvey();
        }
    }

    public void showOfflineAreas() {
        if (!ListenerUtil.mutListener.listen(1035)) {
            navigator.navigate(HomeScreenFragmentDirections.showOfflineAreas());
        }
    }

    public void showSettings() {
        if (!ListenerUtil.mutListener.listen(1036)) {
            navigator.navigate(HomeScreenFragmentDirections.actionHomeScreenFragmentToSettingsActivity());
        }
    }

    public void onFeatureClick(ImmutableList<MapFeature> mapFeatures) {
        ImmutableList<Feature> features = stream(mapFeatures).map(MapFeature::getFeature).filter(Objects::nonNull).collect(toImmutableList());
        if (!ListenerUtil.mutListener.listen(1038)) {
            if (features.isEmpty()) {
                if (!ListenerUtil.mutListener.listen(1037)) {
                    Timber.e("onFeatureClick called with empty or null map features");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(1040)) {
            if (features.size() == 1) {
                if (!ListenerUtil.mutListener.listen(1039)) {
                    onFeatureSelected(features.get(0));
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(1041)) {
            showFeatureSelectorRequests.onNext(features);
        }
    }

    public Optional<Survey> getActiveSurvey() {
        return Loadable.getValue(getSurveyLoadingState());
    }

    public void showSyncStatus() {
        if (!ListenerUtil.mutListener.listen(1042)) {
            navigator.navigate(HomeScreenFragmentDirections.showSyncStatus());
        }
    }
}
