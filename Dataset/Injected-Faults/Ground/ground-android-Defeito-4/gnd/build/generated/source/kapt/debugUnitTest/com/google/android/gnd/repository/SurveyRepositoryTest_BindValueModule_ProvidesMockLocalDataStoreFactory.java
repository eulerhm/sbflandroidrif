package com.google.android.gnd.repository;

import com.google.android.gnd.persistence.local.LocalDataStore;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class SurveyRepositoryTest_BindValueModule_ProvidesMockLocalDataStoreFactory implements Factory<LocalDataStore> {
  private final Provider<SurveyRepositoryTest> testProvider;

  public SurveyRepositoryTest_BindValueModule_ProvidesMockLocalDataStoreFactory(
      Provider<SurveyRepositoryTest> testProvider) {
    this.testProvider = testProvider;
  }

  @Override
  public LocalDataStore get() {
    return providesMockLocalDataStore(testProvider.get());
  }

  public static SurveyRepositoryTest_BindValueModule_ProvidesMockLocalDataStoreFactory create(
      Provider<SurveyRepositoryTest> testProvider) {
    return new SurveyRepositoryTest_BindValueModule_ProvidesMockLocalDataStoreFactory(testProvider);
  }

  public static LocalDataStore providesMockLocalDataStore(SurveyRepositoryTest test) {
    return Preconditions.checkNotNullFromProvides(SurveyRepositoryTest_BindValueModule.providesMockLocalDataStore(test));
  }
}
