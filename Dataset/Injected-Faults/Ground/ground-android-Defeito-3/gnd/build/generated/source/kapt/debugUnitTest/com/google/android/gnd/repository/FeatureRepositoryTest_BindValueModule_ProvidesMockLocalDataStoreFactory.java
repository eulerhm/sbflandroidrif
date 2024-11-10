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
public final class FeatureRepositoryTest_BindValueModule_ProvidesMockLocalDataStoreFactory implements Factory<LocalDataStore> {
  private final Provider<FeatureRepositoryTest> testProvider;

  public FeatureRepositoryTest_BindValueModule_ProvidesMockLocalDataStoreFactory(
      Provider<FeatureRepositoryTest> testProvider) {
    this.testProvider = testProvider;
  }

  @Override
  public LocalDataStore get() {
    return providesMockLocalDataStore(testProvider.get());
  }

  public static FeatureRepositoryTest_BindValueModule_ProvidesMockLocalDataStoreFactory create(
      Provider<FeatureRepositoryTest> testProvider) {
    return new FeatureRepositoryTest_BindValueModule_ProvidesMockLocalDataStoreFactory(testProvider);
  }

  public static LocalDataStore providesMockLocalDataStore(FeatureRepositoryTest test) {
    return Preconditions.checkNotNullFromProvides(FeatureRepositoryTest_BindValueModule.providesMockLocalDataStore(test));
  }
}
