package org.wordpress.android.ui.accounts;

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
public final class UnifiedLoginTracker_Factory implements Factory<UnifiedLoginTracker> {
  private final Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider;

  public UnifiedLoginTracker_Factory(Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider) {
    this.analyticsTrackerProvider = analyticsTrackerProvider;
  }

  @Override
  public UnifiedLoginTracker get() {
    return newInstance(analyticsTrackerProvider.get());
  }

  public static UnifiedLoginTracker_Factory create(
      Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider) {
    return new UnifiedLoginTracker_Factory(analyticsTrackerProvider);
  }

  public static UnifiedLoginTracker newInstance(AnalyticsTrackerWrapper analyticsTracker) {
    return new UnifiedLoginTracker(analyticsTracker);
  }
}
