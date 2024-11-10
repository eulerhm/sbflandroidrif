package org.wordpress.android.ui.mysite.tabs;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.prefs.AppPrefsWrapper;
import org.wordpress.android.util.analytics.AnalyticsTrackerWrapper;
import org.wordpress.android.util.config.MySiteDashboardTabsFeatureConfig;
import org.wordpress.android.util.config.MySiteDefaultTabExperimentFeatureConfig;
import org.wordpress.android.util.config.MySiteDefaultTabExperimentVariationDashboardFeatureConfig;

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
public final class MySiteDefaultTabExperiment_Factory implements Factory<MySiteDefaultTabExperiment> {
  private final Provider<MySiteDefaultTabExperimentFeatureConfig> mySiteDefaultTabExperimentFeatureConfigProvider;

  private final Provider<MySiteDefaultTabExperimentVariationDashboardFeatureConfig> mySiteDefaultTabExperimentVariationDashboardFeatureConfigProvider;

  private final Provider<MySiteDashboardTabsFeatureConfig> mySiteDashboardTabsFeatureConfigProvider;

  private final Provider<AppPrefsWrapper> appPrefsWrapperProvider;

  private final Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider;

  public MySiteDefaultTabExperiment_Factory(
      Provider<MySiteDefaultTabExperimentFeatureConfig> mySiteDefaultTabExperimentFeatureConfigProvider,
      Provider<MySiteDefaultTabExperimentVariationDashboardFeatureConfig> mySiteDefaultTabExperimentVariationDashboardFeatureConfigProvider,
      Provider<MySiteDashboardTabsFeatureConfig> mySiteDashboardTabsFeatureConfigProvider,
      Provider<AppPrefsWrapper> appPrefsWrapperProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider) {
    this.mySiteDefaultTabExperimentFeatureConfigProvider = mySiteDefaultTabExperimentFeatureConfigProvider;
    this.mySiteDefaultTabExperimentVariationDashboardFeatureConfigProvider = mySiteDefaultTabExperimentVariationDashboardFeatureConfigProvider;
    this.mySiteDashboardTabsFeatureConfigProvider = mySiteDashboardTabsFeatureConfigProvider;
    this.appPrefsWrapperProvider = appPrefsWrapperProvider;
    this.analyticsTrackerWrapperProvider = analyticsTrackerWrapperProvider;
  }

  @Override
  public MySiteDefaultTabExperiment get() {
    return newInstance(mySiteDefaultTabExperimentFeatureConfigProvider.get(), mySiteDefaultTabExperimentVariationDashboardFeatureConfigProvider.get(), mySiteDashboardTabsFeatureConfigProvider.get(), appPrefsWrapperProvider.get(), analyticsTrackerWrapperProvider.get());
  }

  public static MySiteDefaultTabExperiment_Factory create(
      Provider<MySiteDefaultTabExperimentFeatureConfig> mySiteDefaultTabExperimentFeatureConfigProvider,
      Provider<MySiteDefaultTabExperimentVariationDashboardFeatureConfig> mySiteDefaultTabExperimentVariationDashboardFeatureConfigProvider,
      Provider<MySiteDashboardTabsFeatureConfig> mySiteDashboardTabsFeatureConfigProvider,
      Provider<AppPrefsWrapper> appPrefsWrapperProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider) {
    return new MySiteDefaultTabExperiment_Factory(mySiteDefaultTabExperimentFeatureConfigProvider, mySiteDefaultTabExperimentVariationDashboardFeatureConfigProvider, mySiteDashboardTabsFeatureConfigProvider, appPrefsWrapperProvider, analyticsTrackerWrapperProvider);
  }

  public static MySiteDefaultTabExperiment newInstance(
      MySiteDefaultTabExperimentFeatureConfig mySiteDefaultTabExperimentFeatureConfig,
      MySiteDefaultTabExperimentVariationDashboardFeatureConfig mySiteDefaultTabExperimentVariationDashboardFeatureConfig,
      MySiteDashboardTabsFeatureConfig mySiteDashboardTabsFeatureConfig,
      AppPrefsWrapper appPrefsWrapper, AnalyticsTrackerWrapper analyticsTrackerWrapper) {
    return new MySiteDefaultTabExperiment(mySiteDefaultTabExperimentFeatureConfig, mySiteDefaultTabExperimentVariationDashboardFeatureConfig, mySiteDashboardTabsFeatureConfig, appPrefsWrapper, analyticsTrackerWrapper);
  }
}
