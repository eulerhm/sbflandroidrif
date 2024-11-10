package org.wordpress.android.util.config;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.util.analytics.AnalyticsTrackerWrapper;

@ScopeMetadata("javax.inject.Singleton")
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
public final class AppConfig_Factory implements Factory<AppConfig> {
  private final Provider<RemoteConfig> remoteConfigProvider;

  private final Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider;

  private final Provider<ManualFeatureConfig> manualFeatureConfigProvider;

  public AppConfig_Factory(Provider<RemoteConfig> remoteConfigProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider,
      Provider<ManualFeatureConfig> manualFeatureConfigProvider) {
    this.remoteConfigProvider = remoteConfigProvider;
    this.analyticsTrackerProvider = analyticsTrackerProvider;
    this.manualFeatureConfigProvider = manualFeatureConfigProvider;
  }

  @Override
  public AppConfig get() {
    return newInstance(remoteConfigProvider.get(), analyticsTrackerProvider.get(), manualFeatureConfigProvider.get());
  }

  public static AppConfig_Factory create(Provider<RemoteConfig> remoteConfigProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider,
      Provider<ManualFeatureConfig> manualFeatureConfigProvider) {
    return new AppConfig_Factory(remoteConfigProvider, analyticsTrackerProvider, manualFeatureConfigProvider);
  }

  public static AppConfig newInstance(RemoteConfig remoteConfig,
      AnalyticsTrackerWrapper analyticsTracker, ManualFeatureConfig manualFeatureConfig) {
    return new AppConfig(remoteConfig, analyticsTracker, manualFeatureConfig);
  }
}
