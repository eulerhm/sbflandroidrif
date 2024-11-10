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
public final class Mp4ComposerVideoOptimizationFeatureConfig_Factory implements Factory<Mp4ComposerVideoOptimizationFeatureConfig> {
  private final Provider<AppConfig> appConfigProvider;

  public Mp4ComposerVideoOptimizationFeatureConfig_Factory(Provider<AppConfig> appConfigProvider) {
    this.appConfigProvider = appConfigProvider;
  }

  @Override
  public Mp4ComposerVideoOptimizationFeatureConfig get() {
    return newInstance(appConfigProvider.get());
  }

  public static Mp4ComposerVideoOptimizationFeatureConfig_Factory create(
      Provider<AppConfig> appConfigProvider) {
    return new Mp4ComposerVideoOptimizationFeatureConfig_Factory(appConfigProvider);
  }

  public static Mp4ComposerVideoOptimizationFeatureConfig newInstance(AppConfig appConfig) {
    return new Mp4ComposerVideoOptimizationFeatureConfig(appConfig);
  }
}
