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
public final class QuickStartExistingUsersV2FeatureConfig_Factory implements Factory<QuickStartExistingUsersV2FeatureConfig> {
  private final Provider<AppConfig> appConfigProvider;

  public QuickStartExistingUsersV2FeatureConfig_Factory(Provider<AppConfig> appConfigProvider) {
    this.appConfigProvider = appConfigProvider;
  }

  @Override
  public QuickStartExistingUsersV2FeatureConfig get() {
    return newInstance(appConfigProvider.get());
  }

  public static QuickStartExistingUsersV2FeatureConfig_Factory create(
      Provider<AppConfig> appConfigProvider) {
    return new QuickStartExistingUsersV2FeatureConfig_Factory(appConfigProvider);
  }

  public static QuickStartExistingUsersV2FeatureConfig newInstance(AppConfig appConfig) {
    return new QuickStartExistingUsersV2FeatureConfig(appConfig);
  }
}
