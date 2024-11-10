package org.wordpress.android.workers.notification.createsite;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.prefs.AppPrefsWrapper;
import org.wordpress.android.util.analytics.AnalyticsTrackerWrapper;
import org.wordpress.android.workers.notification.local.LocalNotificationScheduler;

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
public final class CreateSiteNotificationScheduler_Factory implements Factory<CreateSiteNotificationScheduler> {
  private final Provider<LocalNotificationScheduler> localNotificationSchedulerProvider;

  private final Provider<CreateSiteNotificationHandler> createSiteNotificationHandlerProvider;

  private final Provider<AppPrefsWrapper> appsPrefsProvider;

  private final Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider;

  public CreateSiteNotificationScheduler_Factory(
      Provider<LocalNotificationScheduler> localNotificationSchedulerProvider,
      Provider<CreateSiteNotificationHandler> createSiteNotificationHandlerProvider,
      Provider<AppPrefsWrapper> appsPrefsProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider) {
    this.localNotificationSchedulerProvider = localNotificationSchedulerProvider;
    this.createSiteNotificationHandlerProvider = createSiteNotificationHandlerProvider;
    this.appsPrefsProvider = appsPrefsProvider;
    this.analyticsTrackerProvider = analyticsTrackerProvider;
  }

  @Override
  public CreateSiteNotificationScheduler get() {
    return newInstance(localNotificationSchedulerProvider.get(), createSiteNotificationHandlerProvider.get(), appsPrefsProvider.get(), analyticsTrackerProvider.get());
  }

  public static CreateSiteNotificationScheduler_Factory create(
      Provider<LocalNotificationScheduler> localNotificationSchedulerProvider,
      Provider<CreateSiteNotificationHandler> createSiteNotificationHandlerProvider,
      Provider<AppPrefsWrapper> appsPrefsProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider) {
    return new CreateSiteNotificationScheduler_Factory(localNotificationSchedulerProvider, createSiteNotificationHandlerProvider, appsPrefsProvider, analyticsTrackerProvider);
  }

  public static CreateSiteNotificationScheduler newInstance(
      LocalNotificationScheduler localNotificationScheduler,
      CreateSiteNotificationHandler createSiteNotificationHandler, AppPrefsWrapper appsPrefs,
      AnalyticsTrackerWrapper analyticsTracker) {
    return new CreateSiteNotificationScheduler(localNotificationScheduler, createSiteNotificationHandler, appsPrefs, analyticsTracker);
  }
}
