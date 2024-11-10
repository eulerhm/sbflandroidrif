package com.google.android.gnd.repository;

import android.content.Context;
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
public final class SurveyRepositoryTest_BindValueModule_ProvidesSurveyRepositoryTestFactory implements Factory<SurveyRepositoryTest> {
  private final Provider<Context> contextProvider;

  public SurveyRepositoryTest_BindValueModule_ProvidesSurveyRepositoryTestFactory(
      Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public SurveyRepositoryTest get() {
    return providesSurveyRepositoryTest(contextProvider.get());
  }

  public static SurveyRepositoryTest_BindValueModule_ProvidesSurveyRepositoryTestFactory create(
      Provider<Context> contextProvider) {
    return new SurveyRepositoryTest_BindValueModule_ProvidesSurveyRepositoryTestFactory(contextProvider);
  }

  public static SurveyRepositoryTest providesSurveyRepositoryTest(Context context) {
    return Preconditions.checkNotNullFromProvides(SurveyRepositoryTest_BindValueModule.providesSurveyRepositoryTest(context));
  }
}
