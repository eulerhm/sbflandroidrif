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
public final class ManageCategoriesFeatureConfig_Factory implements Factory<ManageCategoriesFeatureConfig> {
  private final Provider<AppConfig> appConfigProvider;

  public ManageCategoriesFeatureConfig_Factory(Provider<AppConfig> appConfigProvider) {
    this.appConfigProvider = appConfigProvider;
  }

  @Override
  public ManageCategoriesFeatureConfig get() {
    return newInstance(appConfigProvider.get());
  }

  public static ManageCategoriesFeatureConfig_Factory create(
      Provider<AppConfig> appConfigProvider) {
    return new ManageCategoriesFeatureConfig_Factory(appConfigProvider);
  }

  public static ManageCategoriesFeatureConfig newInstance(AppConfig appConfig) {
    return new ManageCategoriesFeatureConfig(appConfig);
  }
}
