package org.wordpress.android.push;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.notifications.SystemNotificationsTracker;

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
public final class GCMMessageHandler_Factory implements Factory<GCMMessageHandler> {
  private final Provider<SystemNotificationsTracker> systemNotificationsTrackerProvider;

  public GCMMessageHandler_Factory(
      Provider<SystemNotificationsTracker> systemNotificationsTrackerProvider) {
    this.systemNotificationsTrackerProvider = systemNotificationsTrackerProvider;
  }

  @Override
  public GCMMessageHandler get() {
    return newInstance(systemNotificationsTrackerProvider.get());
  }

  public static GCMMessageHandler_Factory create(
      Provider<SystemNotificationsTracker> systemNotificationsTrackerProvider) {
    return new GCMMessageHandler_Factory(systemNotificationsTrackerProvider);
  }

  public static GCMMessageHandler newInstance(
      SystemNotificationsTracker systemNotificationsTracker) {
    return new GCMMessageHandler(systemNotificationsTracker);
  }
}
