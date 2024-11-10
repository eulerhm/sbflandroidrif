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
public final class MySiteDashboardTodaysStatsCardFeatureConfig_Factory implements Factory<MySiteDashboardTodaysStatsCardFeatureConfig> {
  private final Provider<AppConfig> appConfigProvider;

  public MySiteDashboardTodaysStatsCardFeatureConfig_Factory(
      Provider<AppConfig> appConfigProvider) {
    this.appConfigProvider = appConfigProvider;
  }

  @Override
  public MySiteDashboardTodaysStatsCardFeatureConfig get() {
    return newInstance(appConfigProvider.get());
  }

  public static MySiteDashboardTodaysStatsCardFeatureConfig_Factory create(
      Provider<AppConfig> appConfigProvider) {
    return new MySiteDashboardTodaysStatsCardFeatureConfig_Factory(appConfigProvider);
  }

  public static MySiteDashboardTodaysStatsCardFeatureConfig newInstance(AppConfig appConfig) {
    return new MySiteDashboardTodaysStatsCardFeatureConfig(appConfig);
  }
}
