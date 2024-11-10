package org.wordpress.android.ui.featureintroduction;

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
public final class FeatureIntroductionViewModel_Factory implements Factory<FeatureIntroductionViewModel> {
  private final Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider;

  public FeatureIntroductionViewModel_Factory(
      Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider) {
    this.analyticsTrackerProvider = analyticsTrackerProvider;
  }

  @Override
  public FeatureIntroductionViewModel get() {
    return newInstance(analyticsTrackerProvider.get());
  }

  public static FeatureIntroductionViewModel_Factory create(
      Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider) {
    return new FeatureIntroductionViewModel_Factory(analyticsTrackerProvider);
  }

  public static FeatureIntroductionViewModel newInstance(AnalyticsTrackerWrapper analyticsTracker) {
    return new FeatureIntroductionViewModel(analyticsTracker);
  }
}
