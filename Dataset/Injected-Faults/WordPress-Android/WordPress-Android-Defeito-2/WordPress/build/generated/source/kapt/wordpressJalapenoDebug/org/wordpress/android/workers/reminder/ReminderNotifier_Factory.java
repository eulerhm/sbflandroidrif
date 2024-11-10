package org.wordpress.android.workers.reminder;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.ui.bloggingreminders.BloggingRemindersAnalyticsTracker;
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
public final class ReminderNotifier_Factory implements Factory<ReminderNotifier> {
  private final Provider<ContextProvider> contextProvider;

  private final Provider<ResourceProvider> resourceProvider;

  private final Provider<SiteStore> siteStoreProvider;

  private final Provider<AccountStore> accountStoreProvider;

  private final Provider<ReminderNotificationManager> reminderNotificationManagerProvider;

  private final Provider<BloggingRemindersAnalyticsTracker> analyticsTrackerProvider;

  public ReminderNotifier_Factory(Provider<ContextProvider> contextProvider,
      Provider<ResourceProvider> resourceProvider, Provider<SiteStore> siteStoreProvider,
      Provider<AccountStore> accountStoreProvider,
      Provider<ReminderNotificationManager> reminderNotificationManagerProvider,
      Provider<BloggingRemindersAnalyticsTracker> analyticsTrackerProvider) {
    this.contextProvider = contextProvider;
    this.resourceProvider = resourceProvider;
    this.siteStoreProvider = siteStoreProvider;
    this.accountStoreProvider = accountStoreProvider;
    this.reminderNotificationManagerProvider = reminderNotificationManagerProvider;
    this.analyticsTrackerProvider = analyticsTrackerProvider;
  }

  @Override
  public ReminderNotifier get() {
    return newInstance(contextProvider.get(), resourceProvider.get(), siteStoreProvider.get(), accountStoreProvider.get(), reminderNotificationManagerProvider.get(), analyticsTrackerProvider.get());
  }

  public static ReminderNotifier_Factory create(Provider<ContextProvider> contextProvider,
      Provider<ResourceProvider> resourceProvider, Provider<SiteStore> siteStoreProvider,
      Provider<AccountStore> accountStoreProvider,
      Provider<ReminderNotificationManager> reminderNotificationManagerProvider,
      Provider<BloggingRemindersAnalyticsTracker> analyticsTrackerProvider) {
    return new ReminderNotifier_Factory(contextProvider, resourceProvider, siteStoreProvider, accountStoreProvider, reminderNotificationManagerProvider, analyticsTrackerProvider);
  }

  public static ReminderNotifier newInstance(ContextProvider contextProvider,
      ResourceProvider resourceProvider, SiteStore siteStore, AccountStore accountStore,
      ReminderNotificationManager reminderNotificationManager,
      BloggingRemindersAnalyticsTracker analyticsTracker) {
    return new ReminderNotifier(contextProvider, resourceProvider, siteStore, accountStore, reminderNotificationManager, analyticsTracker);
  }
}
