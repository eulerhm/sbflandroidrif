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
public final class FeatureRepositoryTest_BindValueModule_ProvidesFeatureRepositoryTestFactory implements Factory<FeatureRepositoryTest> {
  private final Provider<Context> contextProvider;

  public FeatureRepositoryTest_BindValueModule_ProvidesFeatureRepositoryTestFactory(
      Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public FeatureRepositoryTest get() {
    return providesFeatureRepositoryTest(contextProvider.get());
  }

  public static FeatureRepositoryTest_BindValueModule_ProvidesFeatureRepositoryTestFactory create(
      Provider<Context> contextProvider) {
    return new FeatureRepositoryTest_BindValueModule_ProvidesFeatureRepositoryTestFactory(contextProvider);
  }

  public static FeatureRepositoryTest providesFeatureRepositoryTest(Context context) {
    return Preconditions.checkNotNullFromProvides(FeatureRepositoryTest_BindValueModule.providesFeatureRepositoryTest(context));
  }
}
