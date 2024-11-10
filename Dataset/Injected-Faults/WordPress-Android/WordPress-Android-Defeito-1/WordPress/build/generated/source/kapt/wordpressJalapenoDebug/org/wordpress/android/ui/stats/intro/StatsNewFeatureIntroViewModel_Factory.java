package org.wordpress.android.ui.stats.intro;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.ui.prefs.AppPrefsWrapper;
import org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider;
import org.wordpress.android.util.analytics.AnalyticsTrackerWrapper;

@ScopeMetadata
@QualifierMetadata("javax.inject.Named")
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class StatsNewFeatureIntroViewModel_Factory implements Factory<StatsNewFeatureIntroViewModel> {
  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  private final Provider<StatsSiteProvider> statsSiteProvider;

  private final Provider<AppPrefsWrapper> appPrefsWrapperProvider;

  private final Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider;

  public StatsNewFeatureIntroViewModel_Factory(Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<StatsSiteProvider> statsSiteProvider,
      Provider<AppPrefsWrapper> appPrefsWrapperProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider) {
    this.mainDispatcherProvider = mainDispatcherProvider;
    this.statsSiteProvider = statsSiteProvider;
    this.appPrefsWrapperProvider = appPrefsWrapperProvider;
    this.analyticsTrackerProvider = analyticsTrackerProvider;
  }

  @Override
  public StatsNewFeatureIntroViewModel get() {
    return newInstance(mainDispatcherProvider.get(), statsSiteProvider.get(), appPrefsWrapperProvider.get(), analyticsTrackerProvider.get());
  }

  public static StatsNewFeatureIntroViewModel_Factory create(
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<StatsSiteProvider> statsSiteProvider,
      Provider<AppPrefsWrapper> appPrefsWrapperProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider) {
    return new StatsNewFeatureIntroViewModel_Factory(mainDispatcherProvider, statsSiteProvider, appPrefsWrapperProvider, analyticsTrackerProvider);
  }

  public static StatsNewFeatureIntroViewModel newInstance(CoroutineDispatcher mainDispatcher,
      StatsSiteProvider statsSiteProvider, AppPrefsWrapper appPrefsWrapper,
      AnalyticsTrackerWrapper analyticsTracker) {
    return new StatsNewFeatureIntroViewModel(mainDispatcher, statsSiteProvider, appPrefsWrapper, analyticsTracker);
  }
}
