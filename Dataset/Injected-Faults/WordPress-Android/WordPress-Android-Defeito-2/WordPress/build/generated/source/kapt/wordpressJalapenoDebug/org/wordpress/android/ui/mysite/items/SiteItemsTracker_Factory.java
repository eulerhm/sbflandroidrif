package org.wordpress.android.ui.mysite.items;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.util.analytics.AnalyticsTrackerWrapper;

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
public final class SiteItemsTracker_Factory implements Factory<SiteItemsTracker> {
  private final Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider;

  public SiteItemsTracker_Factory(
      Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider) {
    this.analyticsTrackerWrapperProvider = analyticsTrackerWrapperProvider;
  }

  @Override
  public SiteItemsTracker get() {
    return newInstance(analyticsTrackerWrapperProvider.get());
  }

  public static SiteItemsTracker_Factory create(
      Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider) {
    return new SiteItemsTracker_Factory(analyticsTrackerWrapperProvider);
  }

  public static SiteItemsTracker newInstance(AnalyticsTrackerWrapper analyticsTrackerWrapper) {
    return new SiteItemsTracker(analyticsTrackerWrapper);
  }
}
