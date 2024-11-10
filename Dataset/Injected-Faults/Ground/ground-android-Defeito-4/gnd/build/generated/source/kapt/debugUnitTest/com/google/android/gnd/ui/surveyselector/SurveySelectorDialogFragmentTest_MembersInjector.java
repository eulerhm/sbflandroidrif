package com.google.android.gnd.ui.surveyselector;

import com.google.android.gnd.persistence.remote.FakeRemoteDataStore;
import com.google.android.gnd.repository.SurveyRepository;
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
public final class SurveySelectorDialogFragmentTest_MembersInjector implements MembersInjector<SurveySelectorDialogFragmentTest> {
  private final Provider<SurveyRepository> surveyRepositoryProvider;

  private final Provider<FakeRemoteDataStore> fakeRemoteDataStoreProvider;

  public SurveySelectorDialogFragmentTest_MembersInjector(
      Provider<SurveyRepository> surveyRepositoryProvider,
      Provider<FakeRemoteDataStore> fakeRemoteDataStoreProvider) {
    this.surveyRepositoryProvider = surveyRepositoryProvider;
    this.fakeRemoteDataStoreProvider = fakeRemoteDataStoreProvider;
  }

  public static MembersInjector<SurveySelectorDialogFragmentTest> create(
      Provider<SurveyRepository> surveyRepositoryProvider,
      Provider<FakeRemoteDataStore> fakeRemoteDataStoreProvider) {
    return new SurveySelectorDialogFragmentTest_MembersInjector(surveyRepositoryProvider, fakeRemoteDataStoreProvider);
  }

  @Override
  public void injectMembers(SurveySelectorDialogFragmentTest instance) {
    injectSurveyRepository(instance, surveyRepositoryProvider.get());
    injectFakeRemoteDataStore(instance, fakeRemoteDataStoreProvider.get());
  }

  @InjectedFieldSignature("com.google.android.gnd.ui.surveyselector.SurveySelectorDialogFragmentTest.surveyRepository")
  public static void injectSurveyRepository(SurveySelectorDialogFragmentTest instance,
      SurveyRepository surveyRepository) {
    instance.surveyRepository = surveyRepository;
  }

  @InjectedFieldSignature("com.google.android.gnd.ui.surveyselector.SurveySelectorDialogFragmentTest.fakeRemoteDataStore")
  public static void injectFakeRemoteDataStore(SurveySelectorDialogFragmentTest instance,
      FakeRemoteDataStore fakeRemoteDataStore) {
    instance.fakeRemoteDataStore = fakeRemoteDataStore;
  }
}
