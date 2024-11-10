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
public final class SiteNameFeatureConfig_Factory implements Factory<SiteNameFeatureConfig> {
  private final Provider<AppConfig> appConfigProvider;

  public SiteNameFeatureConfig_Factory(Provider<AppConfig> appConfigProvider) {
    this.appConfigProvider = appConfigProvider;
  }

  @Override
  public SiteNameFeatureConfig get() {
    return newInstance(appConfigProvider.get());
  }

  public static SiteNameFeatureConfig_Factory create(Provider<AppConfig> appConfigProvider) {
    return new SiteNameFeatureConfig_Factory(appConfigProvider);
  }

  public static SiteNameFeatureConfig newInstance(AppConfig appConfig) {
    return new SiteNameFeatureConfig(appConfig);
  }
}
