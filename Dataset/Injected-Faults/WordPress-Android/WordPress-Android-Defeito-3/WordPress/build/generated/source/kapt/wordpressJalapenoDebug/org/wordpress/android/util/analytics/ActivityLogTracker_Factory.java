package org.wordpress.android.util.analytics;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.util.DateTimeUtilsWrapper;

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
public final class ActivityLogTracker_Factory implements Factory<ActivityLogTracker> {
  private final Provider<AnalyticsTrackerWrapper> trackerProvider;

  private final Provider<DateTimeUtilsWrapper> dateTimeUtilsWrapperProvider;

  public ActivityLogTracker_Factory(Provider<AnalyticsTrackerWrapper> trackerProvider,
      Provider<DateTimeUtilsWrapper> dateTimeUtilsWrapperProvider) {
    this.trackerProvider = trackerProvider;
    this.dateTimeUtilsWrapperProvider = dateTimeUtilsWrapperProvider;
  }

  @Override
  public ActivityLogTracker get() {
    return newInstance(trackerProvider.get(), dateTimeUtilsWrapperProvider.get());
  }

  public static ActivityLogTracker_Factory create(Provider<AnalyticsTrackerWrapper> trackerProvider,
      Provider<DateTimeUtilsWrapper> dateTimeUtilsWrapperProvider) {
    return new ActivityLogTracker_Factory(trackerProvider, dateTimeUtilsWrapperProvider);
  }

  public static ActivityLogTracker newInstance(AnalyticsTrackerWrapper tracker,
      DateTimeUtilsWrapper dateTimeUtilsWrapper) {
    return new ActivityLogTracker(tracker, dateTimeUtilsWrapper);
  }
}
