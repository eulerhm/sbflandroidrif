package org.wordpress.android.ui.sitecreation;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.util.config.SiteIntentQuestionFeatureConfig;
import org.wordpress.android.util.config.SiteNameFeatureConfig;

@ScopeMetadata("javax.inject.Singleton")
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
public final class SiteCreationStepsProvider_Factory implements Factory<SiteCreationStepsProvider> {
  private final Provider<SiteIntentQuestionFeatureConfig> siteIntentQuestionFeatureConfigProvider;

  private final Provider<SiteNameFeatureConfig> siteNameFeatureConfigProvider;

  public SiteCreationStepsProvider_Factory(
      Provider<SiteIntentQuestionFeatureConfig> siteIntentQuestionFeatureConfigProvider,
      Provider<SiteNameFeatureConfig> siteNameFeatureConfigProvider) {
    this.siteIntentQuestionFeatureConfigProvider = siteIntentQuestionFeatureConfigProvider;
    this.siteNameFeatureConfigProvider = siteNameFeatureConfigProvider;
  }

  @Override
  public SiteCreationStepsProvider get() {
    return newInstance(siteIntentQuestionFeatureConfigProvider.get(), siteNameFeatureConfigProvider.get());
  }

  public static SiteCreationStepsProvider_Factory create(
      Provider<SiteIntentQuestionFeatureConfig> siteIntentQuestionFeatureConfigProvider,
      Provider<SiteNameFeatureConfig> siteNameFeatureConfigProvider) {
    return new SiteCreationStepsProvider_Factory(siteIntentQuestionFeatureConfigProvider, siteNameFeatureConfigProvider);
  }

  public static SiteCreationStepsProvider newInstance(
      SiteIntentQuestionFeatureConfig siteIntentQuestionFeatureConfig,
      SiteNameFeatureConfig siteNameFeatureConfig) {
    return new SiteCreationStepsProvider(siteIntentQuestionFeatureConfig, siteNameFeatureConfig);
  }
}
