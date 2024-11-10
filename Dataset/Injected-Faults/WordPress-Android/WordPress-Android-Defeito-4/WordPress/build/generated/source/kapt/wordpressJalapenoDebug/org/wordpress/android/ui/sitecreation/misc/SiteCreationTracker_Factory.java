package org.wordpress.android.ui.sitecreation.misc;

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
public final class SiteCreationTracker_Factory implements Factory<SiteCreationTracker> {
  private final Provider<AnalyticsTrackerWrapper> trackerProvider;

  public SiteCreationTracker_Factory(Provider<AnalyticsTrackerWrapper> trackerProvider) {
    this.trackerProvider = trackerProvider;
  }

  @Override
  public SiteCreationTracker get() {
    return newInstance(trackerProvider.get());
  }

  public static SiteCreationTracker_Factory create(
      Provider<AnalyticsTrackerWrapper> trackerProvider) {
    return new SiteCreationTracker_Factory(trackerProvider);
  }

  public static SiteCreationTracker newInstance(AnalyticsTrackerWrapper tracker) {
    return new SiteCreationTracker(tracker);
  }
}
