package org.wordpress.android.ui.posts;

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
public final class PublishNotificationReceiver_MembersInjector implements MembersInjector<PublishNotificationReceiver> {
  private final Provider<PublishNotificationReceiverViewModel> publishNotificationReceiverViewModelProvider;

  private final Provider<SystemNotificationsTracker> systemNotificationsTrackerProvider;

  public PublishNotificationReceiver_MembersInjector(
      Provider<PublishNotificationReceiverViewModel> publishNotificationReceiverViewModelProvider,
      Provider<SystemNotificationsTracker> systemNotificationsTrackerProvider) {
    this.publishNotificationReceiverViewModelProvider = publishNotificationReceiverViewModelProvider;
    this.systemNotificationsTrackerProvider = systemNotificationsTrackerProvider;
  }

  public static MembersInjector<PublishNotificationReceiver> create(
      Provider<PublishNotificationReceiverViewModel> publishNotificationReceiverViewModelProvider,
      Provider<SystemNotificationsTracker> systemNotificationsTrackerProvider) {
    return new PublishNotificationReceiver_MembersInjector(publishNotificationReceiverViewModelProvider, systemNotificationsTrackerProvider);
  }

  @Override
  public void injectMembers(PublishNotificationReceiver instance) {
    injectPublishNotificationReceiverViewModel(instance, publishNotificationReceiverViewModelProvider.get());
    injectSystemNotificationsTracker(instance, systemNotificationsTrackerProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.PublishNotificationReceiver.publishNotificationReceiverViewModel")
  public static void injectPublishNotificationReceiverViewModel(
      PublishNotificationReceiver instance,
      PublishNotificationReceiverViewModel publishNotificationReceiverViewModel) {
    instance.publishNotificationReceiverViewModel = publishNotificationReceiverViewModel;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.PublishNotificationReceiver.systemNotificationsTracker")
  public static void injectSystemNotificationsTracker(PublishNotificationReceiver instance,
      SystemNotificationsTracker systemNotificationsTracker) {
    instance.systemNotificationsTracker = systemNotificationsTracker;
  }
}
