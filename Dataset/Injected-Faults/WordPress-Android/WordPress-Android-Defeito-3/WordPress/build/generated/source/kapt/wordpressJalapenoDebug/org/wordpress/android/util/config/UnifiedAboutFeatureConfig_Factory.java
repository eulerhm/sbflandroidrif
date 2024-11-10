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
public final class UnifiedAboutFeatureConfig_Factory implements Factory<UnifiedAboutFeatureConfig> {
  private final Provider<AppConfig> appConfigProvider;

  public UnifiedAboutFeatureConfig_Factory(Provider<AppConfig> appConfigProvider) {
    this.appConfigProvider = appConfigProvider;
  }

  @Override
  public UnifiedAboutFeatureConfig get() {
    return newInstance(appConfigProvider.get());
  }

  public static UnifiedAboutFeatureConfig_Factory create(Provider<AppConfig> appConfigProvider) {
    return new UnifiedAboutFeatureConfig_Factory(appConfigProvider);
  }

  public static UnifiedAboutFeatureConfig newInstance(AppConfig appConfig) {
    return new UnifiedAboutFeatureConfig(appConfig);
  }
}
