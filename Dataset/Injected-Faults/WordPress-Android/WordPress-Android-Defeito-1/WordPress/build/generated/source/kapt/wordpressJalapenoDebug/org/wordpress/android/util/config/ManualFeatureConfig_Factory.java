package org.wordpress.android.util.config;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.prefs.AppPrefsWrapper;
import org.wordpress.android.util.BuildConfigWrapper;

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
public final class ManualFeatureConfig_Factory implements Factory<ManualFeatureConfig> {
  private final Provider<AppPrefsWrapper> appPrefsWrapperProvider;

  private final Provider<BuildConfigWrapper> buildConfigWrapperProvider;

  public ManualFeatureConfig_Factory(Provider<AppPrefsWrapper> appPrefsWrapperProvider,
      Provider<BuildConfigWrapper> buildConfigWrapperProvider) {
    this.appPrefsWrapperProvider = appPrefsWrapperProvider;
    this.buildConfigWrapperProvider = buildConfigWrapperProvider;
  }

  @Override
  public ManualFeatureConfig get() {
    return newInstance(appPrefsWrapperProvider.get(), buildConfigWrapperProvider.get());
  }

  public static ManualFeatureConfig_Factory create(
      Provider<AppPrefsWrapper> appPrefsWrapperProvider,
      Provider<BuildConfigWrapper> buildConfigWrapperProvider) {
    return new ManualFeatureConfig_Factory(appPrefsWrapperProvider, buildConfigWrapperProvider);
  }

  public static ManualFeatureConfig newInstance(AppPrefsWrapper appPrefsWrapper,
      BuildConfigWrapper buildConfigWrapper) {
    return new ManualFeatureConfig(appPrefsWrapper, buildConfigWrapper);
  }
}
