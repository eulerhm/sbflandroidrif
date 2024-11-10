package org.wordpress.android.ui.domains;

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
public final class DomainRegistrationMainViewModel_Factory implements Factory<DomainRegistrationMainViewModel> {
  private final Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider;

  public DomainRegistrationMainViewModel_Factory(
      Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider) {
    this.analyticsTrackerProvider = analyticsTrackerProvider;
  }

  @Override
  public DomainRegistrationMainViewModel get() {
    return newInstance(analyticsTrackerProvider.get());
  }

  public static DomainRegistrationMainViewModel_Factory create(
      Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider) {
    return new DomainRegistrationMainViewModel_Factory(analyticsTrackerProvider);
  }

  public static DomainRegistrationMainViewModel newInstance(
      AnalyticsTrackerWrapper analyticsTracker) {
    return new DomainRegistrationMainViewModel(analyticsTracker);
  }
}
