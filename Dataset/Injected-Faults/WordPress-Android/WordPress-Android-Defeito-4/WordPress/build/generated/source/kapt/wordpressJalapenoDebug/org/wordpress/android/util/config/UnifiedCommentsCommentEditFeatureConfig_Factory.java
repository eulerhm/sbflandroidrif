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
public final class UnifiedCommentsCommentEditFeatureConfig_Factory implements Factory<UnifiedCommentsCommentEditFeatureConfig> {
  private final Provider<AppConfig> appConfigProvider;

  public UnifiedCommentsCommentEditFeatureConfig_Factory(Provider<AppConfig> appConfigProvider) {
    this.appConfigProvider = appConfigProvider;
  }

  @Override
  public UnifiedCommentsCommentEditFeatureConfig get() {
    return newInstance(appConfigProvider.get());
  }

  public static UnifiedCommentsCommentEditFeatureConfig_Factory create(
      Provider<AppConfig> appConfigProvider) {
    return new UnifiedCommentsCommentEditFeatureConfig_Factory(appConfigProvider);
  }

  public static UnifiedCommentsCommentEditFeatureConfig newInstance(AppConfig appConfig) {
    return new UnifiedCommentsCommentEditFeatureConfig(appConfig);
  }
}
