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
public final class BloggingRemindersFeatureConfig_Factory implements Factory<BloggingRemindersFeatureConfig> {
  private final Provider<AppConfig> appConfigProvider;

  public BloggingRemindersFeatureConfig_Factory(Provider<AppConfig> appConfigProvider) {
    this.appConfigProvider = appConfigProvider;
  }

  @Override
  public BloggingRemindersFeatureConfig get() {
    return newInstance(appConfigProvider.get());
  }

  public static BloggingRemindersFeatureConfig_Factory create(
      Provider<AppConfig> appConfigProvider) {
    return new BloggingRemindersFeatureConfig_Factory(appConfigProvider);
  }

  public static BloggingRemindersFeatureConfig newInstance(AppConfig appConfig) {
    return new BloggingRemindersFeatureConfig(appConfig);
  }
}
