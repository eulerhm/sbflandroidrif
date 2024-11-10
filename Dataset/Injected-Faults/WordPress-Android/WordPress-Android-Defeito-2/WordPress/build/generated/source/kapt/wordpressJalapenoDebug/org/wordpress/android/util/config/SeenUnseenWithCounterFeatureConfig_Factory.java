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
public final class SeenUnseenWithCounterFeatureConfig_Factory implements Factory<SeenUnseenWithCounterFeatureConfig> {
  private final Provider<AppConfig> appConfigProvider;

  public SeenUnseenWithCounterFeatureConfig_Factory(Provider<AppConfig> appConfigProvider) {
    this.appConfigProvider = appConfigProvider;
  }

  @Override
  public SeenUnseenWithCounterFeatureConfig get() {
    return newInstance(appConfigProvider.get());
  }

  public static SeenUnseenWithCounterFeatureConfig_Factory create(
      Provider<AppConfig> appConfigProvider) {
    return new SeenUnseenWithCounterFeatureConfig_Factory(appConfigProvider);
  }

  public static SeenUnseenWithCounterFeatureConfig newInstance(AppConfig appConfig) {
    return new SeenUnseenWithCounterFeatureConfig(appConfig);
  }
}
