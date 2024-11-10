package org.wordpress.android.workers.weeklyroundup;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.ui.notifications.SystemNotificationsTracker;
import org.wordpress.android.ui.prefs.AppPrefsWrapper;
import org.wordpress.android.util.SiteUtilsWrapper;
import org.wordpress.android.viewmodel.ContextProvider;
import org.wordpress.android.viewmodel.ResourceProvider;

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
public final class WeeklyRoundupNotifier_Factory implements Factory<WeeklyRoundupNotifier> {
  private final Provider<AccountStore> accountStoreProvider;

  private final Provider<SiteStore> siteStoreProvider;

  private final Provider<ContextProvider> contextProvider;

  private final Provider<ResourceProvider> resourceProvider;

  private final Provider<WeeklyRoundupScheduler> weeklyRoundupSchedulerProvider;

  private final Provider<SystemNotificationsTracker> notificationsTrackerProvider;

  private final Provider<SiteUtilsWrapper> siteUtilsProvider;

  private final Provider<WeeklyRoundupRepository> weeklyRoundupRepositoryProvider;

  private final Provider<AppPrefsWrapper> appPrefsProvider;

  public WeeklyRoundupNotifier_Factory(Provider<AccountStore> accountStoreProvider,
      Provider<SiteStore> siteStoreProvider, Provider<ContextProvider> contextProvider,
      Provider<ResourceProvider> resourceProvider,
      Provider<WeeklyRoundupScheduler> weeklyRoundupSchedulerProvider,
      Provider<SystemNotificationsTracker> notificationsTrackerProvider,
      Provider<SiteUtilsWrapper> siteUtilsProvider,
      Provider<WeeklyRoundupRepository> weeklyRoundupRepositoryProvider,
      Provider<AppPrefsWrapper> appPrefsProvider) {
    this.accountStoreProvider = accountStoreProvider;
    this.siteStoreProvider = siteStoreProvider;
    this.contextProvider = contextProvider;
    this.resourceProvider = resourceProvider;
    this.weeklyRoundupSchedulerProvider = weeklyRoundupSchedulerProvider;
    this.notificationsTrackerProvider = notificationsTrackerProvider;
    this.siteUtilsProvider = siteUtilsProvider;
    this.weeklyRoundupRepositoryProvider = weeklyRoundupRepositoryProvider;
    this.appPrefsProvider = appPrefsProvider;
  }

  @Override
  public WeeklyRoundupNotifier get() {
    return newInstance(accountStoreProvider.get(), siteStoreProvider.get(), contextProvider.get(), resourceProvider.get(), weeklyRoundupSchedulerProvider.get(), notificationsTrackerProvider.get(), siteUtilsProvider.get(), weeklyRoundupRepositoryProvider.get(), appPrefsProvider.get());
  }

  public static WeeklyRoundupNotifier_Factory create(Provider<AccountStore> accountStoreProvider,
      Provider<SiteStore> siteStoreProvider, Provider<ContextProvider> contextProvider,
      Provider<ResourceProvider> resourceProvider,
      Provider<WeeklyRoundupScheduler> weeklyRoundupSchedulerProvider,
      Provider<SystemNotificationsTracker> notificationsTrackerProvider,
      Provider<SiteUtilsWrapper> siteUtilsProvider,
      Provider<WeeklyRoundupRepository> weeklyRoundupRepositoryProvider,
      Provider<AppPrefsWrapper> appPrefsProvider) {
    return new WeeklyRoundupNotifier_Factory(accountStoreProvider, siteStoreProvider, contextProvider, resourceProvider, weeklyRoundupSchedulerProvider, notificationsTrackerProvider, siteUtilsProvider, weeklyRoundupRepositoryProvider, appPrefsProvider);
  }

  public static WeeklyRoundupNotifier newInstance(AccountStore accountStore, SiteStore siteStore,
      ContextProvider contextProvider, ResourceProvider resourceProvider,
      WeeklyRoundupScheduler weeklyRoundupScheduler,
      SystemNotificationsTracker notificationsTracker, SiteUtilsWrapper siteUtils,
      WeeklyRoundupRepository weeklyRoundupRepository, AppPrefsWrapper appPrefs) {
    return new WeeklyRoundupNotifier(accountStore, siteStore, contextProvider, resourceProvider, weeklyRoundupScheduler, notificationsTracker, siteUtils, weeklyRoundupRepository, appPrefs);
  }
}
