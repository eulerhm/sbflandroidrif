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
public final class MySiteDashboardTabsFeatureConfig_Factory implements Factory<MySiteDashboardTabsFeatureConfig> {
  private final Provider<AppConfig> appConfigProvider;

  public MySiteDashboardTabsFeatureConfig_Factory(Provider<AppConfig> appConfigProvider) {
    this.appConfigProvider = appConfigProvider;
  }

  @Override
  public MySiteDashboardTabsFeatureConfig get() {
    return newInstance(appConfigProvider.get());
  }

  public static MySiteDashboardTabsFeatureConfig_Factory create(
      Provider<AppConfig> appConfigProvider) {
    return new MySiteDashboardTabsFeatureConfig_Factory(appConfigProvider);
  }

  public static MySiteDashboardTabsFeatureConfig newInstance(AppConfig appConfig) {
    return new MySiteDashboardTabsFeatureConfig(appConfig);
  }
}
