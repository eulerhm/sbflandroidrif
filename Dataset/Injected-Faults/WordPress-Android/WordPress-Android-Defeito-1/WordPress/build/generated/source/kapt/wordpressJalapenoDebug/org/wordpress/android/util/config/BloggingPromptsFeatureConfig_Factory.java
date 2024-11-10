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
public final class BloggingPromptsFeatureConfig_Factory implements Factory<BloggingPromptsFeatureConfig> {
  private final Provider<AppConfig> appConfigProvider;

  public BloggingPromptsFeatureConfig_Factory(Provider<AppConfig> appConfigProvider) {
    this.appConfigProvider = appConfigProvider;
  }

  @Override
  public BloggingPromptsFeatureConfig get() {
    return newInstance(appConfigProvider.get());
  }

  public static BloggingPromptsFeatureConfig_Factory create(Provider<AppConfig> appConfigProvider) {
    return new BloggingPromptsFeatureConfig_Factory(appConfigProvider);
  }

  public static BloggingPromptsFeatureConfig newInstance(AppConfig appConfig) {
    return new BloggingPromptsFeatureConfig(appConfig);
  }
}
