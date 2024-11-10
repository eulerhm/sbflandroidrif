package com.google.android.gnd.repository;

import com.google.android.gnd.persistence.sync.DataSyncWorkManager;
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
public final class FeatureRepositoryTest_BindValueModule_ProvidesMockWorkManagerFactory implements Factory<DataSyncWorkManager> {
  private final Provider<FeatureRepositoryTest> testProvider;

  public FeatureRepositoryTest_BindValueModule_ProvidesMockWorkManagerFactory(
      Provider<FeatureRepositoryTest> testProvider) {
    this.testProvider = testProvider;
  }

  @Override
  public DataSyncWorkManager get() {
    return providesMockWorkManager(testProvider.get());
  }

  public static FeatureRepositoryTest_BindValueModule_ProvidesMockWorkManagerFactory create(
      Provider<FeatureRepositoryTest> testProvider) {
    return new FeatureRepositoryTest_BindValueModule_ProvidesMockWorkManagerFactory(testProvider);
  }

  public static DataSyncWorkManager providesMockWorkManager(FeatureRepositoryTest test) {
    return Preconditions.checkNotNullFromProvides(FeatureRepositoryTest_BindValueModule.providesMockWorkManager(test));
  }
}
