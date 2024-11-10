package org.wordpress.android.ui.mysite.cards.dashboard.bloggingprompts;

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
public final class BloggingPromptsCardAnalyticsTracker_Factory implements Factory<BloggingPromptsCardAnalyticsTracker> {
  private final Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider;

  public BloggingPromptsCardAnalyticsTracker_Factory(
      Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider) {
    this.analyticsTrackerProvider = analyticsTrackerProvider;
  }

  @Override
  public BloggingPromptsCardAnalyticsTracker get() {
    return newInstance(analyticsTrackerProvider.get());
  }

  public static BloggingPromptsCardAnalyticsTracker_Factory create(
      Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider) {
    return new BloggingPromptsCardAnalyticsTracker_Factory(analyticsTrackerProvider);
  }

  public static BloggingPromptsCardAnalyticsTracker newInstance(
      AnalyticsTrackerWrapper analyticsTracker) {
    return new BloggingPromptsCardAnalyticsTracker(analyticsTracker);
  }
}
