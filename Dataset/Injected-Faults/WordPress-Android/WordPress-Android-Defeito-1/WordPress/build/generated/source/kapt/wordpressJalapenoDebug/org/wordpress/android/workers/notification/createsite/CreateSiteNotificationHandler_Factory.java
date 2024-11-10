package org.wordpress.android.workers.notification.createsite;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.fluxc.store.SiteStore;
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
public final class CreateSiteNotificationHandler_Factory implements Factory<CreateSiteNotificationHandler> {
  private final Provider<AccountStore> accountStoreProvider;

  private final Provider<SiteStore> siteStoreProvider;

  private final Provider<SystemNotificationsTracker> notificationsTrackerProvider;

  public CreateSiteNotificationHandler_Factory(Provider<AccountStore> accountStoreProvider,
      Provider<SiteStore> siteStoreProvider,
      Provider<SystemNotificationsTracker> notificationsTrackerProvider) {
    this.accountStoreProvider = accountStoreProvider;
    this.siteStoreProvider = siteStoreProvider;
    this.notificationsTrackerProvider = notificationsTrackerProvider;
  }

  @Override
  public CreateSiteNotificationHandler get() {
    return newInstance(accountStoreProvider.get(), siteStoreProvider.get(), notificationsTrackerProvider.get());
  }

  public static CreateSiteNotificationHandler_Factory create(
      Provider<AccountStore> accountStoreProvider, Provider<SiteStore> siteStoreProvider,
      Provider<SystemNotificationsTracker> notificationsTrackerProvider) {
    return new CreateSiteNotificationHandler_Factory(accountStoreProvider, siteStoreProvider, notificationsTrackerProvider);
  }

  public static CreateSiteNotificationHandler newInstance(AccountStore accountStore,
      SiteStore siteStore, SystemNotificationsTracker notificationsTracker) {
    return new CreateSiteNotificationHandler(accountStore, siteStore, notificationsTracker);
  }
}
