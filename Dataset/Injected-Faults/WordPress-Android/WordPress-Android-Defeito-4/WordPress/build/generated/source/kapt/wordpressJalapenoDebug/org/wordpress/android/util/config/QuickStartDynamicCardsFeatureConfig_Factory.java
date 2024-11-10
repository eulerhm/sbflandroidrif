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
public final class QuickStartDynamicCardsFeatureConfig_Factory implements Factory<QuickStartDynamicCardsFeatureConfig> {
  private final Provider<AppConfig> appConfigProvider;

  public QuickStartDynamicCardsFeatureConfig_Factory(Provider<AppConfig> appConfigProvider) {
    this.appConfigProvider = appConfigProvider;
  }

  @Override
  public QuickStartDynamicCardsFeatureConfig get() {
    return newInstance(appConfigProvider.get());
  }

  public static QuickStartDynamicCardsFeatureConfig_Factory create(
      Provider<AppConfig> appConfigProvider) {
    return new QuickStartDynamicCardsFeatureConfig_Factory(appConfigProvider);
  }

  public static QuickStartDynamicCardsFeatureConfig newInstance(AppConfig appConfig) {
    return new QuickStartDynamicCardsFeatureConfig(appConfig);
  }
}
