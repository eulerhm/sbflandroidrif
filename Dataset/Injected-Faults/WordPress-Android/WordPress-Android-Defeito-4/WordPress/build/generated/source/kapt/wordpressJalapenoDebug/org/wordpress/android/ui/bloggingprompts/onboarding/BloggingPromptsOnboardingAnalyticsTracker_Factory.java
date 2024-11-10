package org.wordpress.android.ui.bloggingprompts.onboarding;

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
public final class BloggingPromptsOnboardingAnalyticsTracker_Factory implements Factory<BloggingPromptsOnboardingAnalyticsTracker> {
  private final Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider;

  public BloggingPromptsOnboardingAnalyticsTracker_Factory(
      Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider) {
    this.analyticsTrackerProvider = analyticsTrackerProvider;
  }

  @Override
  public BloggingPromptsOnboardingAnalyticsTracker get() {
    return newInstance(analyticsTrackerProvider.get());
  }

  public static BloggingPromptsOnboardingAnalyticsTracker_Factory create(
      Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider) {
    return new BloggingPromptsOnboardingAnalyticsTracker_Factory(analyticsTrackerProvider);
  }

  public static BloggingPromptsOnboardingAnalyticsTracker newInstance(
      AnalyticsTrackerWrapper analyticsTracker) {
    return new BloggingPromptsOnboardingAnalyticsTracker(analyticsTracker);
  }
}
