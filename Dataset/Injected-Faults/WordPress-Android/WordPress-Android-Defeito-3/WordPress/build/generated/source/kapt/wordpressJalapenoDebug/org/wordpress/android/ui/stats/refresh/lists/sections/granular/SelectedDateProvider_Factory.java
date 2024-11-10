package org.wordpress.android.ui.stats.refresh.lists.sections.granular;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.stats.refresh.utils.StatsDateFormatter;
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
public final class SelectedDateProvider_Factory implements Factory<SelectedDateProvider> {
  private final Provider<StatsDateFormatter> statsDateFormatterProvider;

  private final Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider;

  public SelectedDateProvider_Factory(Provider<StatsDateFormatter> statsDateFormatterProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider) {
    this.statsDateFormatterProvider = statsDateFormatterProvider;
    this.analyticsTrackerWrapperProvider = analyticsTrackerWrapperProvider;
  }

  @Override
  public SelectedDateProvider get() {
    return newInstance(statsDateFormatterProvider.get(), analyticsTrackerWrapperProvider.get());
  }

  public static SelectedDateProvider_Factory create(
      Provider<StatsDateFormatter> statsDateFormatterProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider) {
    return new SelectedDateProvider_Factory(statsDateFormatterProvider, analyticsTrackerWrapperProvider);
  }

  public static SelectedDateProvider newInstance(StatsDateFormatter statsDateFormatter,
      AnalyticsTrackerWrapper analyticsTrackerWrapper) {
    return new SelectedDateProvider(statsDateFormatter, analyticsTrackerWrapper);
  }
}
