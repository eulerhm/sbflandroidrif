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
public final class CommentsSnippetFeatureConfig_Factory implements Factory<CommentsSnippetFeatureConfig> {
  private final Provider<AppConfig> appConfigProvider;

  public CommentsSnippetFeatureConfig_Factory(Provider<AppConfig> appConfigProvider) {
    this.appConfigProvider = appConfigProvider;
  }

  @Override
  public CommentsSnippetFeatureConfig get() {
    return newInstance(appConfigProvider.get());
  }

  public static CommentsSnippetFeatureConfig_Factory create(Provider<AppConfig> appConfigProvider) {
    return new CommentsSnippetFeatureConfig_Factory(appConfigProvider);
  }

  public static CommentsSnippetFeatureConfig newInstance(AppConfig appConfig) {
    return new CommentsSnippetFeatureConfig(appConfig);
  }
}
