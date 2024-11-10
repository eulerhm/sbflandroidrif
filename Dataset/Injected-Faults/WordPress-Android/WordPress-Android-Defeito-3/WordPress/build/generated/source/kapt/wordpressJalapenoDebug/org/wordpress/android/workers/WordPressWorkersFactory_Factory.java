package org.wordpress.android.workers;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.ui.uploads.UploadStarter;
import org.wordpress.android.workers.notification.local.LocalNotificationHandlerFactory;
import org.wordpress.android.workers.reminder.ReminderNotifier;
import org.wordpress.android.workers.reminder.ReminderScheduler;
import org.wordpress.android.workers.reminder.prompt.PromptReminderNotifier;
import org.wordpress.android.workers.weeklyroundup.WeeklyRoundupNotifier;

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
public final class WordPressWorkersFactory_Factory implements Factory<WordPressWorkersFactory> {
  private final Provider<UploadStarter> uploadStarterProvider;

  private final Provider<SiteStore> siteStoreProvider;

  private final Provider<LocalNotificationHandlerFactory> localNotificationHandlerFactoryProvider;

  private final Provider<ReminderScheduler> reminderSchedulerProvider;

  private final Provider<ReminderNotifier> reminderNotifierProvider;

  private final Provider<WeeklyRoundupNotifier> weeklyRoundupNotifierProvider;

  private final Provider<PromptReminderNotifier> promptReminderNotifierProvider;

  public WordPressWorkersFactory_Factory(Provider<UploadStarter> uploadStarterProvider,
      Provider<SiteStore> siteStoreProvider,
      Provider<LocalNotificationHandlerFactory> localNotificationHandlerFactoryProvider,
      Provider<ReminderScheduler> reminderSchedulerProvider,
      Provider<ReminderNotifier> reminderNotifierProvider,
      Provider<WeeklyRoundupNotifier> weeklyRoundupNotifierProvider,
      Provider<PromptReminderNotifier> promptReminderNotifierProvider) {
    this.uploadStarterProvider = uploadStarterProvider;
    this.siteStoreProvider = siteStoreProvider;
    this.localNotificationHandlerFactoryProvider = localNotificationHandlerFactoryProvider;
    this.reminderSchedulerProvider = reminderSchedulerProvider;
    this.reminderNotifierProvider = reminderNotifierProvider;
    this.weeklyRoundupNotifierProvider = weeklyRoundupNotifierProvider;
    this.promptReminderNotifierProvider = promptReminderNotifierProvider;
  }

  @Override
  public WordPressWorkersFactory get() {
    return newInstance(uploadStarterProvider.get(), siteStoreProvider.get(), localNotificationHandlerFactoryProvider.get(), reminderSchedulerProvider.get(), reminderNotifierProvider.get(), weeklyRoundupNotifierProvider.get(), promptReminderNotifierProvider.get());
  }

  public static WordPressWorkersFactory_Factory create(
      Provider<UploadStarter> uploadStarterProvider, Provider<SiteStore> siteStoreProvider,
      Provider<LocalNotificationHandlerFactory> localNotificationHandlerFactoryProvider,
      Provider<ReminderScheduler> reminderSchedulerProvider,
      Provider<ReminderNotifier> reminderNotifierProvider,
      Provider<WeeklyRoundupNotifier> weeklyRoundupNotifierProvider,
      Provider<PromptReminderNotifier> promptReminderNotifierProvider) {
    return new WordPressWorkersFactory_Factory(uploadStarterProvider, siteStoreProvider, localNotificationHandlerFactoryProvider, reminderSchedulerProvider, reminderNotifierProvider, weeklyRoundupNotifierProvider, promptReminderNotifierProvider);
  }

  public static WordPressWorkersFactory newInstance(UploadStarter uploadStarter,
      SiteStore siteStore, LocalNotificationHandlerFactory localNotificationHandlerFactory,
      ReminderScheduler reminderScheduler, ReminderNotifier reminderNotifier,
      WeeklyRoundupNotifier weeklyRoundupNotifier, PromptReminderNotifier promptReminderNotifier) {
    return new WordPressWorkersFactory(uploadStarter, siteStore, localNotificationHandlerFactory, reminderScheduler, reminderNotifier, weeklyRoundupNotifier, promptReminderNotifier);
  }
}
