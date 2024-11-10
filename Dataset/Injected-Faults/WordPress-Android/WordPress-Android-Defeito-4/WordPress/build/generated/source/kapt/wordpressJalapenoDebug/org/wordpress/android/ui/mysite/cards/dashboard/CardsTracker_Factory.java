package org.wordpress.android.ui.mysite.cards.dashboard;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.quickstart.QuickStartTracker;
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
public final class CardsTracker_Factory implements Factory<CardsTracker> {
  private final Provider<CardsShownTracker> cardsShownTrackerProvider;

  private final Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider;

  private final Provider<QuickStartTracker> quickStartTrackerProvider;

  public CardsTracker_Factory(Provider<CardsShownTracker> cardsShownTrackerProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider,
      Provider<QuickStartTracker> quickStartTrackerProvider) {
    this.cardsShownTrackerProvider = cardsShownTrackerProvider;
    this.analyticsTrackerWrapperProvider = analyticsTrackerWrapperProvider;
    this.quickStartTrackerProvider = quickStartTrackerProvider;
  }

  @Override
  public CardsTracker get() {
    return newInstance(cardsShownTrackerProvider.get(), analyticsTrackerWrapperProvider.get(), quickStartTrackerProvider.get());
  }

  public static CardsTracker_Factory create(Provider<CardsShownTracker> cardsShownTrackerProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider,
      Provider<QuickStartTracker> quickStartTrackerProvider) {
    return new CardsTracker_Factory(cardsShownTrackerProvider, analyticsTrackerWrapperProvider, quickStartTrackerProvider);
  }

  public static CardsTracker newInstance(CardsShownTracker cardsShownTracker,
      AnalyticsTrackerWrapper analyticsTrackerWrapper, QuickStartTracker quickStartTracker) {
    return new CardsTracker(cardsShownTracker, analyticsTrackerWrapper, quickStartTracker);
  }
}
