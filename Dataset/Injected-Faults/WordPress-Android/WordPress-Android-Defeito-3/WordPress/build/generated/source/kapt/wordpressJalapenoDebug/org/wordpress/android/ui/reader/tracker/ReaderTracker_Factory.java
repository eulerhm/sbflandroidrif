package org.wordpress.android.ui.reader.tracker;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.prefs.AppPrefsWrapper;
import org.wordpress.android.ui.reader.utils.DateProvider;
import org.wordpress.android.util.analytics.AnalyticsTrackerWrapper;
import org.wordpress.android.util.analytics.AnalyticsUtilsWrapper;

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
public final class ReaderTracker_Factory implements Factory<ReaderTracker> {
  private final Provider<DateProvider> dateProvider;

  private final Provider<AppPrefsWrapper> appPrefsWrapperProvider;

  private final Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider;

  private final Provider<AnalyticsUtilsWrapper> analyticsUtilsWrapperProvider;

  public ReaderTracker_Factory(Provider<DateProvider> dateProvider,
      Provider<AppPrefsWrapper> appPrefsWrapperProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider,
      Provider<AnalyticsUtilsWrapper> analyticsUtilsWrapperProvider) {
    this.dateProvider = dateProvider;
    this.appPrefsWrapperProvider = appPrefsWrapperProvider;
    this.analyticsTrackerWrapperProvider = analyticsTrackerWrapperProvider;
    this.analyticsUtilsWrapperProvider = analyticsUtilsWrapperProvider;
  }

  @Override
  public ReaderTracker get() {
    return newInstance(dateProvider.get(), appPrefsWrapperProvider.get(), analyticsTrackerWrapperProvider.get(), analyticsUtilsWrapperProvider.get());
  }

  public static ReaderTracker_Factory create(Provider<DateProvider> dateProvider,
      Provider<AppPrefsWrapper> appPrefsWrapperProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider,
      Provider<AnalyticsUtilsWrapper> analyticsUtilsWrapperProvider) {
    return new ReaderTracker_Factory(dateProvider, appPrefsWrapperProvider, analyticsTrackerWrapperProvider, analyticsUtilsWrapperProvider);
  }

  public static ReaderTracker newInstance(DateProvider dateProvider,
      AppPrefsWrapper appPrefsWrapper, AnalyticsTrackerWrapper analyticsTrackerWrapper,
      AnalyticsUtilsWrapper analyticsUtilsWrapper) {
    return new ReaderTracker(dateProvider, appPrefsWrapper, analyticsTrackerWrapper, analyticsUtilsWrapper);
  }
}
