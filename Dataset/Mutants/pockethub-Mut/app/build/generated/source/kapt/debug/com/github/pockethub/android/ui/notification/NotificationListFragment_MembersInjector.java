package com.github.pockethub.android.ui.notification;

import com.meisolsson.githubsdk.service.activity.NotificationService;
import dagger.MembersInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.DaggerFragment_MembersInjector;
import dagger.internal.InjectedFieldSignature;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class NotificationListFragment_MembersInjector implements MembersInjector<NotificationListFragment> {
  private final Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider;

  private final Provider<NotificationService> notificationServiceProvider;

  public NotificationListFragment_MembersInjector(
      Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider,
      Provider<NotificationService> notificationServiceProvider) {
    this.androidInjectorProvider = androidInjectorProvider;
    this.notificationServiceProvider = notificationServiceProvider;
  }

  public static MembersInjector<NotificationListFragment> create(
      Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider,
      Provider<NotificationService> notificationServiceProvider) {
    return new NotificationListFragment_MembersInjector(androidInjectorProvider, notificationServiceProvider);}

  @Override
  public void injectMembers(NotificationListFragment instance) {
    DaggerFragment_MembersInjector.injectAndroidInjector(instance, androidInjectorProvider.get());
    injectNotificationService(instance, notificationServiceProvider.get());
  }

  @InjectedFieldSignature("com.github.pockethub.android.ui.notification.NotificationListFragment.notificationService")
  public static void injectNotificationService(NotificationListFragment instance,
      NotificationService notificationService) {
    instance.notificationService = notificationService;
  }
}
