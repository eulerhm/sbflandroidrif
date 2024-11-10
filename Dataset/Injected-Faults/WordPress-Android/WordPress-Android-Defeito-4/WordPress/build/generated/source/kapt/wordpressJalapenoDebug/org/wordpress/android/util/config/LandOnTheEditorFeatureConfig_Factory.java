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
public final class LandOnTheEditorFeatureConfig_Factory implements Factory<LandOnTheEditorFeatureConfig> {
  private final Provider<AppConfig> appConfigProvider;

  public LandOnTheEditorFeatureConfig_Factory(Provider<AppConfig> appConfigProvider) {
    this.appConfigProvider = appConfigProvider;
  }

  @Override
  public LandOnTheEditorFeatureConfig get() {
    return newInstance(appConfigProvider.get());
  }

  public static LandOnTheEditorFeatureConfig_Factory create(Provider<AppConfig> appConfigProvider) {
    return new LandOnTheEditorFeatureConfig_Factory(appConfigProvider);
  }

  public static LandOnTheEditorFeatureConfig newInstance(AppConfig appConfig) {
    return new LandOnTheEditorFeatureConfig(appConfig);
  }
}
