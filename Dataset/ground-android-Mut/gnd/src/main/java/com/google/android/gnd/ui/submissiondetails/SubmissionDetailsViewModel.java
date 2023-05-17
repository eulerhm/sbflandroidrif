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
package com.google.android.gnd.ui.submissiondetails;

import android.view.View;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import com.google.android.gnd.model.feature.Feature;
import com.google.android.gnd.model.submission.Submission;
import com.google.android.gnd.repository.SubmissionRepository;
import com.google.android.gnd.rx.Loadable;
import com.google.android.gnd.rx.annotations.Hot;
import com.google.android.gnd.ui.common.AbstractViewModel;
import com.google.android.gnd.ui.common.FeatureHelper;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.processors.BehaviorProcessor;
import io.reactivex.processors.FlowableProcessor;
import java8.util.Optional;
import javax.inject.Inject;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class SubmissionDetailsViewModel extends AbstractViewModel {

    @Hot(replays = true)
    public final LiveData<Loadable<Submission>> submission;

    @Hot(replays = true)
    public final LiveData<Integer> progressBarVisibility;

    @Hot(replays = true)
    public final LiveData<String> title;

    @Hot(replays = true)
    public final LiveData<String> subtitle;

    private final SubmissionRepository submissionRepository;

    @Hot(replays = true)
    private final FlowableProcessor<SubmissionDetailsFragmentArgs> argsProcessor = BehaviorProcessor.create();

    @Inject
    SubmissionDetailsViewModel(SubmissionRepository submissionRepository, FeatureHelper featureHelper) {
        this.submissionRepository = submissionRepository;
        Flowable<Loadable<Submission>> submissionStream = argsProcessor.switchMapSingle(args -> submissionRepository.getSubmission(args.getSurveyId(), args.getFeatureId(), args.getSubmissionId()).map(Loadable::loaded).onErrorReturn(Loadable::error));
        // TODO: Refactor to expose the fetched submission directly.
        this.submission = LiveDataReactiveStreams.fromPublisher(submissionStream);
        this.progressBarVisibility = LiveDataReactiveStreams.fromPublisher(submissionStream.map(SubmissionDetailsViewModel::getProgressBarVisibility));
        this.title = LiveDataReactiveStreams.fromPublisher(submissionStream.map(SubmissionDetailsViewModel::getFeature).map(featureHelper::getLabel));
        this.subtitle = LiveDataReactiveStreams.fromPublisher(submissionStream.map(SubmissionDetailsViewModel::getFeature).map(featureHelper::getCreatedBy));
    }

    private static Integer getProgressBarVisibility(Loadable<Submission> submission) {
        return submission.isLoaded() ? View.GONE : View.VISIBLE;
    }

    private static Optional<Feature> getFeature(Loadable<Submission> submission) {
        return submission.value().map(Submission::getFeature);
    }

    public void loadSubmissionDetails(SubmissionDetailsFragmentArgs args) {
        if (!ListenerUtil.mutListener.listen(606)) {
            this.argsProcessor.onNext(args);
        }
    }

    /**
     * Creates an {@link com.google.android.gnd.model.mutation.SubmissionMutation}, marks the locally
     * stored {@link Submission} as DELETED and enqueues a worker to remove the submission from remote
     * {@link com.google.android.gnd.persistence.remote.firestore.FirestoreDataStore}.
     */
    @Hot
    public Completable deleteCurrentSubmission(String surveyId, String featureId, String submissionId) {
        return submissionRepository.getSubmission(surveyId, featureId, submissionId).flatMapCompletable(submissionRepository::deleteSubmission);
    }
}
