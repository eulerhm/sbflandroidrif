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
public final class UnifiedCommentsDetailFeatureConfig_Factory implements Factory<UnifiedCommentsDetailFeatureConfig> {
  private final Provider<AppConfig> appConfigProvider;

  public UnifiedCommentsDetailFeatureConfig_Factory(Provider<AppConfig> appConfigProvider) {
    this.appConfigProvider = appConfigProvider;
  }

  @Override
  public UnifiedCommentsDetailFeatureConfig get() {
    return newInstance(appConfigProvider.get());
  }

  public static UnifiedCommentsDetailFeatureConfig_Factory create(
      Provider<AppConfig> appConfigProvider) {
    return new UnifiedCommentsDetailFeatureConfig_Factory(appConfigProvider);
  }

  public static UnifiedCommentsDetailFeatureConfig newInstance(AppConfig appConfig) {
    return new UnifiedCommentsDetailFeatureConfig(appConfig);
  }
}
