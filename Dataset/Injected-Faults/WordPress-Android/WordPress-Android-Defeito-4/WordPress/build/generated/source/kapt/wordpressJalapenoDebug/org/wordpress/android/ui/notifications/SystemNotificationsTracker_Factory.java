package org.wordpress.android.ui.notifications;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.prefs.AppPrefsWrapper;
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
public final class SystemNotificationsTracker_Factory implements Factory<SystemNotificationsTracker> {
  private final Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider;

  private final Provider<AppPrefsWrapper> appPrefsProvider;

  private final Provider<NotificationManagerWrapper> notificationManagerProvider;

  public SystemNotificationsTracker_Factory(
      Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider,
      Provider<AppPrefsWrapper> appPrefsProvider,
      Provider<NotificationManagerWrapper> notificationManagerProvider) {
    this.analyticsTrackerProvider = analyticsTrackerProvider;
    this.appPrefsProvider = appPrefsProvider;
    this.notificationManagerProvider = notificationManagerProvider;
  }

  @Override
  public SystemNotificationsTracker get() {
    return newInstance(analyticsTrackerProvider.get(), appPrefsProvider.get(), notificationManagerProvider.get());
  }

  public static SystemNotificationsTracker_Factory create(
      Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider,
      Provider<AppPrefsWrapper> appPrefsProvider,
      Provider<NotificationManagerWrapper> notificationManagerProvider) {
    return new SystemNotificationsTracker_Factory(analyticsTrackerProvider, appPrefsProvider, notificationManagerProvider);
  }

  public static SystemNotificationsTracker newInstance(AnalyticsTrackerWrapper analyticsTracker,
      AppPrefsWrapper appPrefs, NotificationManagerWrapper notificationManager) {
    return new SystemNotificationsTracker(analyticsTracker, appPrefs, notificationManager);
  }
}
