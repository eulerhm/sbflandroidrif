package com.google.android.gnd.repository;

import java.lang.System;

/**
 * Coordinates persistence and retrieval of [Survey] instances from remote, local, and in
 * memory data stores. For more details on this pattern and overall architecture, see
 * https://developer.android.com/jetpack/docs/guide.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0094\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001B\'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u000e\u0010&\u001a\u00020\'2\u0006\u0010(\u001a\u00020\u0014J\u0010\u0010)\u001a\u00020\u000e2\u0006\u0010*\u001a\u00020\u000eH\u0002J\u0006\u0010+\u001a\u00020\'J\u0016\u0010,\u001a\b\u0012\u0004\u0012\u00020-0\u001c2\u0006\u0010.\u001a\u00020/H\u0002J\u0014\u00100\u001a\b\u0012\u0004\u0012\u0002010\r2\u0006\u0010(\u001a\u00020\u0014J\u0014\u00102\u001a\b\u0012\u0004\u0012\u0002030\u001c2\u0006\u0010*\u001a\u00020\u000eJ)\u00104\u001a\u001d\u0012\n\u0012\b\u0012\u0004\u0012\u0002050\u001c0\f\u00a2\u0006\f\b\u001d\u0012\b\b6\u0012\u0004\b\u0007\u0010\u00002\u0006\u0010*\u001a\u00020\u000eJ\u0019\u00107\u001a\r\u0012\u0004\u0012\u00020\u000e0\u001b\u00a2\u0006\u0002\b\u001d2\u0006\u0010(\u001a\u00020\u0014J%\u00108\u001a\u0019\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e090#0\f\u00a2\u0006\u0002\b\u001d2\u0006\u0010:\u001a\u00020;J\u0006\u0010<\u001a\u00020\'J!\u0010=\u001a\u0013\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e090\u001b\u00a2\u0006\u0002\b\u001d2\u0006\u0010:\u001a\u00020;H\u0002J!\u0010>\u001a\u0013\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0#0\f\u00a2\u0006\u0002\b\u001d2\u0006\u0010(\u001a\u00020\u0014H\u0002J\u0016\u0010?\u001a\u00020\'2\u0006\u0010(\u001a\u00020\u00142\u0006\u0010@\u001a\u000201J\u001b\u0010A\u001a\r\u0012\u0004\u0012\u00020\u000e0\u001b\u00a2\u0006\u0002\b\u001d2\u0006\u0010B\u001a\u00020\u0014H\u0002R,\u0010\u000b\u001a\u001d\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\f\u00a2\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\u0007\u0010\u00028F\u00a2\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R$\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\u00148F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\"\u0010\u001a\u001a\u0013\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u001c0\u001b\u00a2\u0006\u0002\b\u001d8F\u00a2\u0006\u0006\u001a\u0004\b\u001e\u0010\u001fR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010 \u001a\r\u0012\u0004\u0012\u00020\u00140!\u00a2\u0006\u0002\b\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R,\u0010\"\u001a\u001d\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0#0!\u00a2\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\u0007\u0010\u0002\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006C"}, d2 = {"Lcom/google/android/gnd/repository/SurveyRepository;", "", "userRepository", "Lcom/google/android/gnd/repository/UserRepository;", "localDataStore", "Lcom/google/android/gnd/persistence/local/LocalDataStore;", "remoteDataStore", "Lcom/google/android/gnd/persistence/remote/RemoteDataStore;", "localValueStore", "Lcom/google/android/gnd/persistence/local/LocalValueStore;", "(Lcom/google/android/gnd/repository/UserRepository;Lcom/google/android/gnd/persistence/local/LocalDataStore;Lcom/google/android/gnd/persistence/remote/RemoteDataStore;Lcom/google/android/gnd/persistence/local/LocalValueStore;)V", "activeSurvey", "Lio/reactivex/Flowable;", "Ljava8/util/Optional;", "Lcom/google/android/gnd/model/Survey;", "Lcom/google/android/gnd/rx/annotations/Hot;", "replays", "getActiveSurvey", "()Lio/reactivex/Flowable;", "value", "", "lastActiveSurveyId", "getLastActiveSurveyId", "()Ljava/lang/String;", "setLastActiveSurveyId", "(Ljava/lang/String;)V", "offlineSurveys", "Lio/reactivex/Single;", "Lcom/google/common/collect/ImmutableList;", "Lcom/google/android/gnd/rx/annotations/Cold;", "getOfflineSurveys", "()Lio/reactivex/Single;", "selectSurveyEvent", "Lio/reactivex/processors/FlowableProcessor;", "surveyLoadingState", "Lcom/google/android/gnd/rx/Loadable;", "getSurveyLoadingState", "()Lio/reactivex/processors/FlowableProcessor;", "activateSurvey", "", "surveyId", "attachJobPermissions", "survey", "clearActiveSurvey", "getAddableFeatureTypes", "Lcom/google/android/gnd/model/feature/FeatureType;", "userRole", "Lcom/google/android/gnd/model/Role;", "getLastCameraPosition", "Lcom/google/android/gnd/ui/map/CameraPosition;", "getModifiableJobs", "Lcom/google/android/gnd/model/job/Job;", "getMutationsOnceAndStream", "Lcom/google/android/gnd/model/mutation/Mutation;", "terminates", "getSurvey", "getSurveySummaries", "", "user", "Lcom/google/android/gnd/model/User;", "loadLastActiveSurvey", "loadSurveySummariesFromRemote", "selectSurvey", "setCameraPosition", "cameraPosition", "syncSurveyWithRemote", "id", "gnd_debug"})
@javax.inject.Singleton()
public final class SurveyRepository {
    private final com.google.android.gnd.repository.UserRepository userRepository = null;
    private final com.google.android.gnd.persistence.local.LocalDataStore localDataStore = null;
    private final com.google.android.gnd.persistence.remote.RemoteDataStore remoteDataStore = null;
    private final com.google.android.gnd.persistence.local.LocalValueStore localValueStore = null;
    
    /**
     * Emits a survey id on {@see #activateSurvey} and empty on {@see #clearActiveSurvey}.
     */
    private final io.reactivex.processors.FlowableProcessor<java.lang.String> selectSurveyEvent = null;
    
    /**
     * Emits the latest loading state of the current survey on subscribe and on change.
     */
    @org.jetbrains.annotations.NotNull()
    private final io.reactivex.processors.FlowableProcessor<com.google.android.gnd.rx.Loadable<com.google.android.gnd.model.Survey>> surveyLoadingState = null;
    
    @javax.inject.Inject()
    public SurveyRepository(@org.jetbrains.annotations.NotNull()
    com.google.android.gnd.repository.UserRepository userRepository, @org.jetbrains.annotations.NotNull()
    com.google.android.gnd.persistence.local.LocalDataStore localDataStore, @org.jetbrains.annotations.NotNull()
    com.google.android.gnd.persistence.remote.RemoteDataStore remoteDataStore, @org.jetbrains.annotations.NotNull()
    com.google.android.gnd.persistence.local.LocalValueStore localValueStore) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final io.reactivex.processors.FlowableProcessor<com.google.android.gnd.rx.Loadable<com.google.android.gnd.model.Survey>> getSurveyLoadingState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getLastActiveSurveyId() {
        return null;
    }
    
    public final void setLastActiveSurveyId(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final io.reactivex.Flowable<java8.util.Optional<com.google.android.gnd.model.Survey>> getActiveSurvey() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final io.reactivex.Single<com.google.common.collect.ImmutableList<com.google.android.gnd.model.Survey>> getOfflineSurveys() {
        return null;
    }
    
    private final io.reactivex.Flowable<com.google.android.gnd.rx.Loadable<com.google.android.gnd.model.Survey>> selectSurvey(java.lang.String surveyId) {
        return null;
    }
    
    private final com.google.android.gnd.model.Survey attachJobPermissions(com.google.android.gnd.model.Survey survey) {
        return null;
    }
    
    private final com.google.common.collect.ImmutableList<com.google.android.gnd.model.feature.FeatureType> getAddableFeatureTypes(com.google.android.gnd.model.Role userRole) {
        return null;
    }
    
    /**
     * This only works if the survey is already cached to local db.
     */
    @org.jetbrains.annotations.NotNull()
    public final io.reactivex.Single<com.google.android.gnd.model.Survey> getSurvey(@org.jetbrains.annotations.NotNull()
    java.lang.String surveyId) {
        return null;
    }
    
    private final io.reactivex.Single<com.google.android.gnd.model.Survey> syncSurveyWithRemote(java.lang.String id) {
        return null;
    }
    
    public final void loadLastActiveSurvey() {
    }
    
    public final void activateSurvey(@org.jetbrains.annotations.NotNull()
    java.lang.String surveyId) {
    }
    
    public final void clearActiveSurvey() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final io.reactivex.Flowable<com.google.android.gnd.rx.Loadable<java.util.List<com.google.android.gnd.model.Survey>>> getSurveySummaries(@org.jetbrains.annotations.NotNull()
    com.google.android.gnd.model.User user) {
        return null;
    }
    
    private final io.reactivex.Single<java.util.List<com.google.android.gnd.model.Survey>> loadSurveySummariesFromRemote(com.google.android.gnd.model.User user) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.google.common.collect.ImmutableList<com.google.android.gnd.model.job.Job> getModifiableJobs(@org.jetbrains.annotations.NotNull()
    com.google.android.gnd.model.Survey survey) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final io.reactivex.Flowable<com.google.common.collect.ImmutableList<com.google.android.gnd.model.mutation.Mutation>> getMutationsOnceAndStream(@org.jetbrains.annotations.NotNull()
    com.google.android.gnd.model.Survey survey) {
        return null;
    }
    
    public final void setCameraPosition(@org.jetbrains.annotations.NotNull()
    java.lang.String surveyId, @org.jetbrains.annotations.NotNull()
    com.google.android.gnd.ui.map.CameraPosition cameraPosition) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java8.util.Optional<com.google.android.gnd.ui.map.CameraPosition> getLastCameraPosition(@org.jetbrains.annotations.NotNull()
    java.lang.String surveyId) {
        return null;
    }
}