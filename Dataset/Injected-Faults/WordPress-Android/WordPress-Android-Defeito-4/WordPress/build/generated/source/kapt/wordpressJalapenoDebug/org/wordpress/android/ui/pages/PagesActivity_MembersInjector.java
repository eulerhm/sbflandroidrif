package org.wordpress.android.ui.pages;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.notifications.SystemNotificationsTracker;

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
public final class PagesActivity_MembersInjector implements MembersInjector<PagesActivity> {
  private final Provider<SystemNotificationsTracker> systemNotificationTrackerProvider;

  public PagesActivity_MembersInjector(
      Provider<SystemNotificationsTracker> systemNotificationTrackerProvider) {
    this.systemNotificationTrackerProvider = systemNotificationTrackerProvider;
  }

  public static MembersInjector<PagesActivity> create(
      Provider<SystemNotificationsTracker> systemNotificationTrackerProvider) {
    return new PagesActivity_MembersInjector(systemNotificationTrackerProvider);
  }

  @Override
  public void injectMembers(PagesActivity instance) {
    injectSystemNotificationTracker(instance, systemNotificationTrackerProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.pages.PagesActivity.systemNotificationTracker")
  public static void injectSystemNotificationTracker(PagesActivity instance,
      SystemNotificationsTracker systemNotificationTracker) {
    instance.systemNotificationTracker = systemNotificationTracker;
  }
}
