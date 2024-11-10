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
public final class ExampleRemoteFeature_Factory implements Factory<ExampleRemoteFeature> {
  private final Provider<AppConfig> appConfigProvider;

  public ExampleRemoteFeature_Factory(Provider<AppConfig> appConfigProvider) {
    this.appConfigProvider = appConfigProvider;
  }

  @Override
  public ExampleRemoteFeature get() {
    return newInstance(appConfigProvider.get());
  }

  public static ExampleRemoteFeature_Factory create(Provider<AppConfig> appConfigProvider) {
    return new ExampleRemoteFeature_Factory(appConfigProvider);
  }

  public static ExampleRemoteFeature newInstance(AppConfig appConfig) {
    return new ExampleRemoteFeature(appConfig);
  }
}
