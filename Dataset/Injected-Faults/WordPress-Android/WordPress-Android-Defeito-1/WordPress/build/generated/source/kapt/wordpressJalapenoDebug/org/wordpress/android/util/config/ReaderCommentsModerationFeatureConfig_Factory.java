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
public final class ReaderCommentsModerationFeatureConfig_Factory implements Factory<ReaderCommentsModerationFeatureConfig> {
  private final Provider<AppConfig> appConfigProvider;

  public ReaderCommentsModerationFeatureConfig_Factory(Provider<AppConfig> appConfigProvider) {
    this.appConfigProvider = appConfigProvider;
  }

  @Override
  public ReaderCommentsModerationFeatureConfig get() {
    return newInstance(appConfigProvider.get());
  }

  public static ReaderCommentsModerationFeatureConfig_Factory create(
      Provider<AppConfig> appConfigProvider) {
    return new ReaderCommentsModerationFeatureConfig_Factory(appConfigProvider);
  }

  public static ReaderCommentsModerationFeatureConfig newInstance(AppConfig appConfig) {
    return new ReaderCommentsModerationFeatureConfig(appConfig);
  }
}
