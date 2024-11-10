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
public final class MySiteDefaultTabExperimentVariationDashboardFeatureConfig_Factory implements Factory<MySiteDefaultTabExperimentVariationDashboardFeatureConfig> {
  private final Provider<AppConfig> appConfigProvider;

  public MySiteDefaultTabExperimentVariationDashboardFeatureConfig_Factory(
      Provider<AppConfig> appConfigProvider) {
    this.appConfigProvider = appConfigProvider;
  }

  @Override
  public MySiteDefaultTabExperimentVariationDashboardFeatureConfig get() {
    return newInstance(appConfigProvider.get());
  }

  public static MySiteDefaultTabExperimentVariationDashboardFeatureConfig_Factory create(
      Provider<AppConfig> appConfigProvider) {
    return new MySiteDefaultTabExperimentVariationDashboardFeatureConfig_Factory(appConfigProvider);
  }

  public static MySiteDefaultTabExperimentVariationDashboardFeatureConfig newInstance(
      AppConfig appConfig) {
    return new MySiteDefaultTabExperimentVariationDashboardFeatureConfig(appConfig);
  }
}
