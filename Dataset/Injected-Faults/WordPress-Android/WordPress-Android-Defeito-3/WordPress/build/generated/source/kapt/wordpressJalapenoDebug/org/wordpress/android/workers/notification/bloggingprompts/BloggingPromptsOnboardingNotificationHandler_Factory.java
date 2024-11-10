package org.wordpress.android.workers.notification.bloggingprompts;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.ui.notifications.SystemNotificationsTracker;

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
public final class BloggingPromptsOnboardingNotificationHandler_Factory implements Factory<BloggingPromptsOnboardingNotificationHandler> {
  private final Provider<AccountStore> accountStoreProvider;

  private final Provider<SystemNotificationsTracker> notificationsTrackerProvider;

  public BloggingPromptsOnboardingNotificationHandler_Factory(
      Provider<AccountStore> accountStoreProvider,
      Provider<SystemNotificationsTracker> notificationsTrackerProvider) {
    this.accountStoreProvider = accountStoreProvider;
    this.notificationsTrackerProvider = notificationsTrackerProvider;
  }

  @Override
  public BloggingPromptsOnboardingNotificationHandler get() {
    return newInstance(accountStoreProvider.get(), notificationsTrackerProvider.get());
  }

  public static BloggingPromptsOnboardingNotificationHandler_Factory create(
      Provider<AccountStore> accountStoreProvider,
      Provider<SystemNotificationsTracker> notificationsTrackerProvider) {
    return new BloggingPromptsOnboardingNotificationHandler_Factory(accountStoreProvider, notificationsTrackerProvider);
  }

  public static BloggingPromptsOnboardingNotificationHandler newInstance(AccountStore accountStore,
      SystemNotificationsTracker notificationsTracker) {
    return new BloggingPromptsOnboardingNotificationHandler(accountStore, notificationsTracker);
  }
}
