package com.google.android.gnd.repository;

import com.google.android.gnd.persistence.remote.FakeRemoteDataStore;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import javax.annotation.Generated;
import javax.inject.Provider;

@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class SurveyRepositoryTest_MembersInjector implements MembersInjector<SurveyRepositoryTest> {
  private final Provider<SurveyRepository> surveyRepositoryProvider;

  private final Provider<FakeRemoteDataStore> fakeRemoteDataStoreProvider;

  public SurveyRepositoryTest_MembersInjector(Provider<SurveyRepository> surveyRepositoryProvider,
      Provider<FakeRemoteDataStore> fakeRemoteDataStoreProvider) {
    this.surveyRepositoryProvider = surveyRepositoryProvider;
    this.fakeRemoteDataStoreProvider = fakeRemoteDataStoreProvider;
  }

  public static MembersInjector<SurveyRepositoryTest> create(
      Provider<SurveyRepository> surveyRepositoryProvider,
      Provider<FakeRemoteDataStore> fakeRemoteDataStoreProvider) {
    return new SurveyRepositoryTest_MembersInjector(surveyRepositoryProvider, fakeRemoteDataStoreProvider);
  }

  @Override
  public void injectMembers(SurveyRepositoryTest instance) {
    injectSurveyRepository(instance, surveyRepositoryProvider.get());
    injectFakeRemoteDataStore(instance, fakeRemoteDataStoreProvider.get());
  }

  @InjectedFieldSignature("com.google.android.gnd.repository.SurveyRepositoryTest.surveyRepository")
  public static void injectSurveyRepository(SurveyRepositoryTest instance,
      SurveyRepository surveyRepository) {
    instance.surveyRepository = surveyRepository;
  }

  @InjectedFieldSignature("com.google.android.gnd.repository.SurveyRepositoryTest.fakeRemoteDataStore")
  public static void injectFakeRemoteDataStore(SurveyRepositoryTest instance,
      FakeRemoteDataStore fakeRemoteDataStore) {
    instance.fakeRemoteDataStore = fakeRemoteDataStore;
  }
}
