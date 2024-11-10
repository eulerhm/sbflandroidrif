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
public final class RecommendTheAppFeatureConfig_Factory implements Factory<RecommendTheAppFeatureConfig> {
  private final Provider<AppConfig> appConfigProvider;

  public RecommendTheAppFeatureConfig_Factory(Provider<AppConfig> appConfigProvider) {
    this.appConfigProvider = appConfigProvider;
  }

  @Override
  public RecommendTheAppFeatureConfig get() {
    return newInstance(appConfigProvider.get());
  }

  public static RecommendTheAppFeatureConfig_Factory create(Provider<AppConfig> appConfigProvider) {
    return new RecommendTheAppFeatureConfig_Factory(appConfigProvider);
  }

  public static RecommendTheAppFeatureConfig newInstance(AppConfig appConfig) {
    return new RecommendTheAppFeatureConfig(appConfig);
  }
}
