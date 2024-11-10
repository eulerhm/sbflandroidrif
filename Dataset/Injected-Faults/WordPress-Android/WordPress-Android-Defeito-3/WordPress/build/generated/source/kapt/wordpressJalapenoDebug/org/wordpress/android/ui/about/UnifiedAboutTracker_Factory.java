package org.wordpress.android.ui.about;

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
public final class UnifiedAboutTracker_Factory implements Factory<UnifiedAboutTracker> {
  private final Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider;

  public UnifiedAboutTracker_Factory(
      Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider) {
    this.analyticsTrackerWrapperProvider = analyticsTrackerWrapperProvider;
  }

  @Override
  public UnifiedAboutTracker get() {
    return newInstance(analyticsTrackerWrapperProvider.get());
  }

  public static UnifiedAboutTracker_Factory create(
      Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider) {
    return new UnifiedAboutTracker_Factory(analyticsTrackerWrapperProvider);
  }

  public static UnifiedAboutTracker newInstance(AnalyticsTrackerWrapper analyticsTrackerWrapper) {
    return new UnifiedAboutTracker(analyticsTrackerWrapper);
  }
}
