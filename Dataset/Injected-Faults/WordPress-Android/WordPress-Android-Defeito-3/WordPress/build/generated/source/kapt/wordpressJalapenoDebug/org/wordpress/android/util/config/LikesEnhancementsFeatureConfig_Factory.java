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
public final class LikesEnhancementsFeatureConfig_Factory implements Factory<LikesEnhancementsFeatureConfig> {
  private final Provider<AppConfig> appConfigProvider;

  public LikesEnhancementsFeatureConfig_Factory(Provider<AppConfig> appConfigProvider) {
    this.appConfigProvider = appConfigProvider;
  }

  @Override
  public LikesEnhancementsFeatureConfig get() {
    return newInstance(appConfigProvider.get());
  }

  public static LikesEnhancementsFeatureConfig_Factory create(
      Provider<AppConfig> appConfigProvider) {
    return new LikesEnhancementsFeatureConfig_Factory(appConfigProvider);
  }

  public static LikesEnhancementsFeatureConfig newInstance(AppConfig appConfig) {
    return new LikesEnhancementsFeatureConfig(appConfig);
  }
}
