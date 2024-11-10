package org.wordpress.android.ui.mysite.cards;

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
public final class DomainRegistrationCardShownTracker_Factory implements Factory<DomainRegistrationCardShownTracker> {
  private final Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider;

  public DomainRegistrationCardShownTracker_Factory(
      Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider) {
    this.analyticsTrackerWrapperProvider = analyticsTrackerWrapperProvider;
  }

  @Override
  public DomainRegistrationCardShownTracker get() {
    return newInstance(analyticsTrackerWrapperProvider.get());
  }

  public static DomainRegistrationCardShownTracker_Factory create(
      Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider) {
    return new DomainRegistrationCardShownTracker_Factory(analyticsTrackerWrapperProvider);
  }

  public static DomainRegistrationCardShownTracker newInstance(
      AnalyticsTrackerWrapper analyticsTrackerWrapper) {
    return new DomainRegistrationCardShownTracker(analyticsTrackerWrapper);
  }
}
