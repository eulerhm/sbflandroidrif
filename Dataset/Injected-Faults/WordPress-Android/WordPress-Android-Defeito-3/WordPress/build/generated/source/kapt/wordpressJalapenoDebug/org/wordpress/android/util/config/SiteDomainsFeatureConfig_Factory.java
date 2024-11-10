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
public final class SiteDomainsFeatureConfig_Factory implements Factory<SiteDomainsFeatureConfig> {
  private final Provider<AppConfig> appConfigProvider;

  public SiteDomainsFeatureConfig_Factory(Provider<AppConfig> appConfigProvider) {
    this.appConfigProvider = appConfigProvider;
  }

  @Override
  public SiteDomainsFeatureConfig get() {
    return newInstance(appConfigProvider.get());
  }

  public static SiteDomainsFeatureConfig_Factory create(Provider<AppConfig> appConfigProvider) {
    return new SiteDomainsFeatureConfig_Factory(appConfigProvider);
  }

  public static SiteDomainsFeatureConfig newInstance(AppConfig appConfig) {
    return new SiteDomainsFeatureConfig(appConfig);
  }
}
