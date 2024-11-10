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
public final class BetaSiteDesignsFeatureConfig_Factory implements Factory<BetaSiteDesignsFeatureConfig> {
  private final Provider<AppConfig> appConfigProvider;

  public BetaSiteDesignsFeatureConfig_Factory(Provider<AppConfig> appConfigProvider) {
    this.appConfigProvider = appConfigProvider;
  }

  @Override
  public BetaSiteDesignsFeatureConfig get() {
    return newInstance(appConfigProvider.get());
  }

  public static BetaSiteDesignsFeatureConfig_Factory create(Provider<AppConfig> appConfigProvider) {
    return new BetaSiteDesignsFeatureConfig_Factory(appConfigProvider);
  }

  public static BetaSiteDesignsFeatureConfig newInstance(AppConfig appConfig) {
    return new BetaSiteDesignsFeatureConfig(appConfig);
  }
}
