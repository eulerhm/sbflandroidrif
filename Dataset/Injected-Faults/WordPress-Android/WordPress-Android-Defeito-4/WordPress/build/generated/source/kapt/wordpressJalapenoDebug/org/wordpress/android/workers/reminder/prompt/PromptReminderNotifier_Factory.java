package org.wordpress.android.workers.reminder.prompt;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.fluxc.store.BloggingRemindersStore;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.fluxc.store.bloggingprompts.BloggingPromptsStore;
import org.wordpress.android.ui.bloggingreminders.BloggingRemindersAnalyticsTracker;
import org.wordpress.android.util.HtmlCompatWrapper;
import org.wordpress.android.util.config.BloggingPromptsFeatureConfig;
import org.wordpress.android.viewmodel.ContextProvider;
import org.wordpress.android.viewmodel.ResourceProvider;
import org.wordpress.android.workers.reminder.ReminderNotificationManager;

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
public final class PromptReminderNotifier_Factory implements Factory<PromptReminderNotifier> {
  private final Provider<ContextProvider> contextProvider;

  private final Provider<ResourceProvider> resourceProvider;

  private final Provider<SiteStore> siteStoreProvider;

  private final Provider<AccountStore> accountStoreProvider;

  private final Provider<ReminderNotificationManager> reminderNotificationManagerProvider;

  private final Provider<BloggingPromptsFeatureConfig> bloggingPromptsFeatureConfigProvider;

  private final Provider<BloggingPromptsStore> bloggingPromptsStoreProvider;

  private final Provider<BloggingRemindersAnalyticsTracker> bloggingRemindersAnalyticsTrackerProvider;

  private final Provider<HtmlCompatWrapper> htmlCompatWrapperProvider;

  private final Provider<BloggingRemindersStore> bloggingRemindersStoreProvider;

  public PromptReminderNotifier_Factory(Provider<ContextProvider> contextProvider,
      Provider<ResourceProvider> resourceProvider, Provider<SiteStore> siteStoreProvider,
      Provider<AccountStore> accountStoreProvider,
      Provider<ReminderNotificationManager> reminderNotificationManagerProvider,
      Provider<BloggingPromptsFeatureConfig> bloggingPromptsFeatureConfigProvider,
      Provider<BloggingPromptsStore> bloggingPromptsStoreProvider,
      Provider<BloggingRemindersAnalyticsTracker> bloggingRemindersAnalyticsTrackerProvider,
      Provider<HtmlCompatWrapper> htmlCompatWrapperProvider,
      Provider<BloggingRemindersStore> bloggingRemindersStoreProvider) {
    this.contextProvider = contextProvider;
    this.resourceProvider = resourceProvider;
    this.siteStoreProvider = siteStoreProvider;
    this.accountStoreProvider = accountStoreProvider;
    this.reminderNotificationManagerProvider = reminderNotificationManagerProvider;
    this.bloggingPromptsFeatureConfigProvider = bloggingPromptsFeatureConfigProvider;
    this.bloggingPromptsStoreProvider = bloggingPromptsStoreProvider;
    this.bloggingRemindersAnalyticsTrackerProvider = bloggingRemindersAnalyticsTrackerProvider;
    this.htmlCompatWrapperProvider = htmlCompatWrapperProvider;
    this.bloggingRemindersStoreProvider = bloggingRemindersStoreProvider;
  }

  @Override
  public PromptReminderNotifier get() {
    return newInstance(contextProvider.get(), resourceProvider.get(), siteStoreProvider.get(), accountStoreProvider.get(), reminderNotificationManagerProvider.get(), bloggingPromptsFeatureConfigProvider.get(), bloggingPromptsStoreProvider.get(), bloggingRemindersAnalyticsTrackerProvider.get(), htmlCompatWrapperProvider.get(), bloggingRemindersStoreProvider.get());
  }

  public static PromptReminderNotifier_Factory create(Provider<ContextProvider> contextProvider,
      Provider<ResourceProvider> resourceProvider, Provider<SiteStore> siteStoreProvider,
      Provider<AccountStore> accountStoreProvider,
      Provider<ReminderNotificationManager> reminderNotificationManagerProvider,
      Provider<BloggingPromptsFeatureConfig> bloggingPromptsFeatureConfigProvider,
      Provider<BloggingPromptsStore> bloggingPromptsStoreProvider,
      Provider<BloggingRemindersAnalyticsTracker> bloggingRemindersAnalyticsTrackerProvider,
      Provider<HtmlCompatWrapper> htmlCompatWrapperProvider,
      Provider<BloggingRemindersStore> bloggingRemindersStoreProvider) {
    return new PromptReminderNotifier_Factory(contextProvider, resourceProvider, siteStoreProvider, accountStoreProvider, reminderNotificationManagerProvider, bloggingPromptsFeatureConfigProvider, bloggingPromptsStoreProvider, bloggingRemindersAnalyticsTrackerProvider, htmlCompatWrapperProvider, bloggingRemindersStoreProvider);
  }

  public static PromptReminderNotifier newInstance(ContextProvider contextProvider,
      ResourceProvider resourceProvider, SiteStore siteStore, AccountStore accountStore,
      ReminderNotificationManager reminderNotificationManager,
      BloggingPromptsFeatureConfig bloggingPromptsFeatureConfig,
      BloggingPromptsStore bloggingPromptsStore,
      BloggingRemindersAnalyticsTracker bloggingRemindersAnalyticsTracker,
      HtmlCompatWrapper htmlCompatWrapper, BloggingRemindersStore bloggingRemindersStore) {
    return new PromptReminderNotifier(contextProvider, resourceProvider, siteStore, accountStore, reminderNotificationManager, bloggingPromptsFeatureConfig, bloggingPromptsStore, bloggingRemindersAnalyticsTracker, htmlCompatWrapper, bloggingRemindersStore);
  }
}
