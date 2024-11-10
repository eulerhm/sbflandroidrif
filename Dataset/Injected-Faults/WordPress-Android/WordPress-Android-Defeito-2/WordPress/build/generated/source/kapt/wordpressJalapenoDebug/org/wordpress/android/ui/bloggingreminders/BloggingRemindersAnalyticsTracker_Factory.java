package org.wordpress.android.ui.bloggingreminders;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.store.SiteStore;
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
public final class BloggingRemindersAnalyticsTracker_Factory implements Factory<BloggingRemindersAnalyticsTracker> {
  private final Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider;

  private final Provider<SiteStore> siteStoreProvider;

  public BloggingRemindersAnalyticsTracker_Factory(
      Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider,
      Provider<SiteStore> siteStoreProvider) {
    this.analyticsTrackerProvider = analyticsTrackerProvider;
    this.siteStoreProvider = siteStoreProvider;
  }

  @Override
  public BloggingRemindersAnalyticsTracker get() {
    return newInstance(analyticsTrackerProvider.get(), siteStoreProvider.get());
  }

  public static BloggingRemindersAnalyticsTracker_Factory create(
      Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider,
      Provider<SiteStore> siteStoreProvider) {
    return new BloggingRemindersAnalyticsTracker_Factory(analyticsTrackerProvider, siteStoreProvider);
  }

  public static BloggingRemindersAnalyticsTracker newInstance(
      AnalyticsTrackerWrapper analyticsTracker, SiteStore siteStore) {
    return new BloggingRemindersAnalyticsTracker(analyticsTracker, siteStore);
  }
}
