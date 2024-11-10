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
public final class StatsRevampV2FeatureConfig_Factory implements Factory<StatsRevampV2FeatureConfig> {
  private final Provider<AppConfig> appConfigProvider;

  public StatsRevampV2FeatureConfig_Factory(Provider<AppConfig> appConfigProvider) {
    this.appConfigProvider = appConfigProvider;
  }

  @Override
  public StatsRevampV2FeatureConfig get() {
    return newInstance(appConfigProvider.get());
  }

  public static StatsRevampV2FeatureConfig_Factory create(Provider<AppConfig> appConfigProvider) {
    return new StatsRevampV2FeatureConfig_Factory(appConfigProvider);
  }

  public static StatsRevampV2FeatureConfig newInstance(AppConfig appConfig) {
    return new StatsRevampV2FeatureConfig(appConfig);
  }
}
