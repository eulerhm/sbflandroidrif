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
public final class SurveyRepositoryTest_BindValueModule_ProvidesUserRepositoryFactory implements Factory<UserRepository> {
  private final Provider<SurveyRepositoryTest> testProvider;

  public SurveyRepositoryTest_BindValueModule_ProvidesUserRepositoryFactory(
      Provider<SurveyRepositoryTest> testProvider) {
    this.testProvider = testProvider;
  }

  @Override
  public UserRepository get() {
    return providesUserRepository(testProvider.get());
  }

  public static SurveyRepositoryTest_BindValueModule_ProvidesUserRepositoryFactory create(
      Provider<SurveyRepositoryTest> testProvider) {
    return new SurveyRepositoryTest_BindValueModule_ProvidesUserRepositoryFactory(testProvider);
  }

  public static UserRepository providesUserRepository(SurveyRepositoryTest test) {
    return Preconditions.checkNotNullFromProvides(SurveyRepositoryTest_BindValueModule.providesUserRepository(test));
  }
}
