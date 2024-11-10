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
public final class ExampleExperimentConfig_Factory implements Factory<ExampleExperimentConfig> {
  private final Provider<AppConfig> appConfigProvider;

  public ExampleExperimentConfig_Factory(Provider<AppConfig> appConfigProvider) {
    this.appConfigProvider = appConfigProvider;
  }

  @Override
  public ExampleExperimentConfig get() {
    return newInstance(appConfigProvider.get());
  }

  public static ExampleExperimentConfig_Factory create(Provider<AppConfig> appConfigProvider) {
    return new ExampleExperimentConfig_Factory(appConfigProvider);
  }

  public static ExampleExperimentConfig newInstance(AppConfig appConfig) {
    return new ExampleExperimentConfig(appConfig);
  }
}
