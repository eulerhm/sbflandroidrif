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
public final class SiteIntentQuestionFeatureConfig_Factory implements Factory<SiteIntentQuestionFeatureConfig> {
  private final Provider<AppConfig> appConfigProvider;

  public SiteIntentQuestionFeatureConfig_Factory(Provider<AppConfig> appConfigProvider) {
    this.appConfigProvider = appConfigProvider;
  }

  @Override
  public SiteIntentQuestionFeatureConfig get() {
    return newInstance(appConfigProvider.get());
  }

  public static SiteIntentQuestionFeatureConfig_Factory create(
      Provider<AppConfig> appConfigProvider) {
    return new SiteIntentQuestionFeatureConfig_Factory(appConfigProvider);
  }

  public static SiteIntentQuestionFeatureConfig newInstance(AppConfig appConfig) {
    return new SiteIntentQuestionFeatureConfig(appConfig);
  }
}
