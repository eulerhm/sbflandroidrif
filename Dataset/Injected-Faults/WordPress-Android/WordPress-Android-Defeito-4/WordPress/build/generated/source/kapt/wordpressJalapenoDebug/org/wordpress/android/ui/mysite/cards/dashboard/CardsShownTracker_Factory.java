package org.wordpress.android.ui.mysite.cards.dashboard;

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
public final class CardsShownTracker_Factory implements Factory<CardsShownTracker> {
  private final Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider;

  public CardsShownTracker_Factory(
      Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider) {
    this.analyticsTrackerWrapperProvider = analyticsTrackerWrapperProvider;
  }

  @Override
  public CardsShownTracker get() {
    return newInstance(analyticsTrackerWrapperProvider.get());
  }

  public static CardsShownTracker_Factory create(
      Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider) {
    return new CardsShownTracker_Factory(analyticsTrackerWrapperProvider);
  }

  public static CardsShownTracker newInstance(AnalyticsTrackerWrapper analyticsTrackerWrapper) {
    return new CardsShownTracker(analyticsTrackerWrapper);
  }
}
