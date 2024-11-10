package org.wordpress.android.util.config;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class MySiteDefaultTabExperimentFeatureConfig_Factory implements Factory<MySiteDefaultTabExperimentFeatureConfig> {
  private final Provider<AppConfig> appConfigProvider;

  public MySiteDefaultTabExperimentFeatureConfig_Factory(Provider<AppConfig> appConfigProvider) {
    this.appConfigProvider = appConfigProvider;
  }

  @Override
  public MySiteDefaultTabExperimentFeatureConfig get() {
    return newInstance(appConfigProvider.get());
  }

  public static MySiteDefaultTabExperimentFeatureConfig_Factory create(
      Provider<AppConfig> appConfigProvider) {
    return new MySiteDefaultTabExperimentFeatureConfig_Factory(appConfigProvider);
  }

  public static MySiteDefaultTabExperimentFeatureConfig newInstance(AppConfig appConfig) {
    return new MySiteDefaultTabExperimentFeatureConfig(appConfig);
  }
}
