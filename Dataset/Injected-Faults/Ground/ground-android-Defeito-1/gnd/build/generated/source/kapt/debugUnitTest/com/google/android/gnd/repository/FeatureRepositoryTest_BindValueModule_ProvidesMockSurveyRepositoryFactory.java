package com.google.android.gnd.repository;

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
public final class FeatureRepositoryTest_BindValueModule_ProvidesMockSurveyRepositoryFactory implements Factory<SurveyRepository> {
  private final Provider<FeatureRepositoryTest> testProvider;

  public FeatureRepositoryTest_BindValueModule_ProvidesMockSurveyRepositoryFactory(
      Provider<FeatureRepositoryTest> testProvider) {
    this.testProvider = testProvider;
  }

  @Override
  public SurveyRepository get() {
    return providesMockSurveyRepository(testProvider.get());
  }

  public static FeatureRepositoryTest_BindValueModule_ProvidesMockSurveyRepositoryFactory create(
      Provider<FeatureRepositoryTest> testProvider) {
    return new FeatureRepositoryTest_BindValueModule_ProvidesMockSurveyRepositoryFactory(testProvider);
  }

  public static SurveyRepository providesMockSurveyRepository(FeatureRepositoryTest test) {
    return Preconditions.checkNotNullFromProvides(FeatureRepositoryTest_BindValueModule.providesMockSurveyRepository(test));
  }
}
