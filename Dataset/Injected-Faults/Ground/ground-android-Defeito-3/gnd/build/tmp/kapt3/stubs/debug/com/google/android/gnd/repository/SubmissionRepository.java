package com.google.android.gnd.repository;

import java.lang.System;

/**
 * Coordinates persistence and retrieval of [Submission] instances from remote, local, and in
 * memory data stores. For more details on this pattern and overall architecture, see
 * https://developer.android.com/jetpack/docs/guide.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B7\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u00a2\u0006\u0002\u0010\u000eJ\u0015\u0010\u000f\u001a\u00070\u0010\u00a2\u0006\u0002\b\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J)\u0010\u0014\u001a\u00070\u0010\u00a2\u0006\u0002\b\u00112\u0006\u0010\u0015\u001a\u00020\u00162\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u00182\u0006\u0010\u001a\u001a\u00020\u001bJ)\u0010\u001c\u001a\r\u0012\u0004\u0012\u00020\u00160\u001d\u00a2\u0006\u0002\b\u00112\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001f2\u0006\u0010!\u001a\u00020\u001fJ\u0013\u0010\"\u001a\u00070\u0010\u00a2\u0006\u0002\b\u00112\u0006\u0010\u0015\u001a\u00020\u0016J\"\u0010#\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00180$2\u0006\u0010%\u001a\u00020&2\u0006\u0010 \u001a\u00020\u001fJ)\u0010\'\u001a\r\u0012\u0004\u0012\u00020\u00160\u001d\u00a2\u0006\u0002\b\u00112\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001f2\u0006\u0010(\u001a\u00020\u001fJ-\u0010)\u001a\u0013\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u00180\u001d\u00a2\u0006\u0002\b\u00112\n\u0010*\u001a\u0006\u0012\u0002\b\u00030+2\u0006\u0010!\u001a\u00020\u001fH\u0002J/\u0010)\u001a\u0013\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u00180\u001d\u00a2\u0006\u0002\b\u00112\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001f2\u0006\u0010!\u001a\u00020\u001fJ!\u0010,\u001a\u00070\u0010\u00a2\u0006\u0002\b\u00112\u0012\u0010-\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160.0\u0018H\u0002R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006/"}, d2 = {"Lcom/google/android/gnd/repository/SubmissionRepository;", "", "localDataStore", "Lcom/google/android/gnd/persistence/local/LocalDataStore;", "remoteDataStore", "Lcom/google/android/gnd/persistence/remote/RemoteDataStore;", "featureRepository", "Lcom/google/android/gnd/repository/FeatureRepository;", "dataSyncWorkManager", "Lcom/google/android/gnd/persistence/sync/DataSyncWorkManager;", "uuidGenerator", "Lcom/google/android/gnd/persistence/uuid/OfflineUuidGenerator;", "authManager", "Lcom/google/android/gnd/system/auth/AuthenticationManager;", "(Lcom/google/android/gnd/persistence/local/LocalDataStore;Lcom/google/android/gnd/persistence/remote/RemoteDataStore;Lcom/google/android/gnd/repository/FeatureRepository;Lcom/google/android/gnd/persistence/sync/DataSyncWorkManager;Lcom/google/android/gnd/persistence/uuid/OfflineUuidGenerator;Lcom/google/android/gnd/system/auth/AuthenticationManager;)V", "applyAndEnqueue", "Lio/reactivex/Completable;", "Lcom/google/android/gnd/rx/annotations/Cold;", "mutation", "Lcom/google/android/gnd/model/mutation/SubmissionMutation;", "createOrUpdateSubmission", "submission", "Lcom/google/android/gnd/model/submission/Submission;", "responseDeltas", "Lcom/google/common/collect/ImmutableList;", "Lcom/google/android/gnd/model/submission/ResponseDelta;", "isNew", "", "createSubmission", "Lio/reactivex/Single;", "surveyId", "", "featureId", "taskId", "deleteSubmission", "getIncompleteSubmissionMutationsOnceAndStream", "Lio/reactivex/Flowable;", "survey", "Lcom/google/android/gnd/model/Survey;", "getSubmission", "submissionId", "getSubmissions", "feature", "Lcom/google/android/gnd/model/feature/Feature;", "mergeRemoteSubmissions", "submissions", "Lcom/google/android/gnd/rx/ValueOrError;", "gnd_debug"})
public final class SubmissionRepository {
    private final com.google.android.gnd.persistence.local.LocalDataStore localDataStore = null;
    private final com.google.android.gnd.persistence.remote.RemoteDataStore remoteDataStore = null;
    private final com.google.android.gnd.repository.FeatureRepository featureRepository = null;
    private final com.google.android.gnd.persistence.sync.DataSyncWorkManager dataSyncWorkManager = null;
    private final com.google.android.gnd.persistence.uuid.OfflineUuidGenerator uuidGenerator = null;
    private final com.google.android.gnd.system.auth.AuthenticationManager authManager = null;
    
    @javax.inject.Inject()
    public SubmissionRepository(@org.jetbrains.annotations.NotNull()
    com.google.android.gnd.persistence.local.LocalDataStore localDataStore, @org.jetbrains.annotations.NotNull()
    com.google.android.gnd.persistence.remote.RemoteDataStore remoteDataStore, @org.jetbrains.annotations.NotNull()
    com.google.android.gnd.repository.FeatureRepository featureRepository, @org.jetbrains.annotations.NotNull()
    com.google.android.gnd.persistence.sync.DataSyncWorkManager dataSyncWorkManager, @org.jetbrains.annotations.NotNull()
    com.google.android.gnd.persistence.uuid.OfflineUuidGenerator uuidGenerator, @org.jetbrains.annotations.NotNull()
    com.google.android.gnd.system.auth.AuthenticationManager authManager) {
        super();
    }
    
    /**
     * Retrieves the submissions or the specified survey, feature, and task.
     *
     * <ol>
     *  <li>Attempt to sync remote submission changes to the local data store. If network is not
     *      available or operation times out, this step is skipped.
     *  <li>Relevant submissions are returned directly from the local data store.
     * </ol>
     */
    @org.jetbrains.annotations.NotNull()
    public final io.reactivex.Single<com.google.common.collect.ImmutableList<com.google.android.gnd.model.submission.Submission>> getSubmissions(@org.jetbrains.annotations.NotNull()
    java.lang.String surveyId, @org.jetbrains.annotations.NotNull()
    java.lang.String featureId, @org.jetbrains.annotations.NotNull()
    java.lang.String taskId) {
        return null;
    }
    
    private final io.reactivex.Single<com.google.common.collect.ImmutableList<com.google.android.gnd.model.submission.Submission>> getSubmissions(com.google.android.gnd.model.feature.Feature<?> feature, java.lang.String taskId) {
        return null;
    }
    
    private final io.reactivex.Completable mergeRemoteSubmissions(com.google.common.collect.ImmutableList<com.google.android.gnd.rx.ValueOrError<com.google.android.gnd.model.submission.Submission>> submissions) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final io.reactivex.Single<com.google.android.gnd.model.submission.Submission> getSubmission(@org.jetbrains.annotations.NotNull()
    java.lang.String surveyId, @org.jetbrains.annotations.NotNull()
    java.lang.String featureId, @org.jetbrains.annotations.NotNull()
    java.lang.String submissionId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final io.reactivex.Single<com.google.android.gnd.model.submission.Submission> createSubmission(@org.jetbrains.annotations.NotNull()
    java.lang.String surveyId, @org.jetbrains.annotations.NotNull()
    java.lang.String featureId, @org.jetbrains.annotations.NotNull()
    java.lang.String taskId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final io.reactivex.Completable deleteSubmission(@org.jetbrains.annotations.NotNull()
    com.google.android.gnd.model.submission.Submission submission) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final io.reactivex.Completable createOrUpdateSubmission(@org.jetbrains.annotations.NotNull()
    com.google.android.gnd.model.submission.Submission submission, @org.jetbrains.annotations.NotNull()
    com.google.common.collect.ImmutableList<com.google.android.gnd.model.submission.ResponseDelta> responseDeltas, boolean isNew) {
        return null;
    }
    
    private final io.reactivex.Completable applyAndEnqueue(com.google.android.gnd.model.mutation.SubmissionMutation mutation) {
        return null;
    }
    
    /**
     * Returns all [SubmissionMutation] instances for a given feature which have not yet been
     * marked as [SyncStatus.COMPLETED], including pending, in progress, and failed mutations. A
     * new list is emitted on each subsequent change.
     */
    @org.jetbrains.annotations.NotNull()
    public final io.reactivex.Flowable<com.google.common.collect.ImmutableList<com.google.android.gnd.model.mutation.SubmissionMutation>> getIncompleteSubmissionMutationsOnceAndStream(@org.jetbrains.annotations.NotNull()
    com.google.android.gnd.model.Survey survey, @org.jetbrains.annotations.NotNull()
    java.lang.String featureId) {
        return null;
    }
}