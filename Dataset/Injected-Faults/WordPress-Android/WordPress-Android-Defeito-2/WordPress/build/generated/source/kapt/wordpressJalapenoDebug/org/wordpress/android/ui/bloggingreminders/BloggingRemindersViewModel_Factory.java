package org.wordpress.android.ui.bloggingreminders;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.store.BloggingRemindersStore;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.workers.reminder.ReminderScheduler;

@ScopeMetadata
@QualifierMetadata("javax.inject.Named")
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class BloggingRemindersViewModel_Factory implements Factory<BloggingRemindersViewModel> {
  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  private final Provider<BloggingRemindersManager> bloggingRemindersManagerProvider;

  private final Provider<BloggingRemindersStore> bloggingRemindersStoreProvider;

  private final Provider<PrologueBuilder> prologueBuilderProvider;

  private final Provider<DaySelectionBuilder> daySelectionBuilderProvider;

  private final Provider<EpilogueBuilder> epilogueBuilderProvider;

  private final Provider<DayLabelUtils> dayLabelUtilsProvider;

  private final Provider<BloggingRemindersAnalyticsTracker> analyticsTrackerProvider;

  private final Provider<ReminderScheduler> reminderSchedulerProvider;

  private final Provider<BloggingRemindersModelMapper> mapperProvider;

  private final Provider<SiteStore> siteStoreProvider;

  public BloggingRemindersViewModel_Factory(Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<BloggingRemindersManager> bloggingRemindersManagerProvider,
      Provider<BloggingRemindersStore> bloggingRemindersStoreProvider,
      Provider<PrologueBuilder> prologueBuilderProvider,
      Provider<DaySelectionBuilder> daySelectionBuilderProvider,
      Provider<EpilogueBuilder> epilogueBuilderProvider,
      Provider<DayLabelUtils> dayLabelUtilsProvider,
      Provider<BloggingRemindersAnalyticsTracker> analyticsTrackerProvider,
      Provider<ReminderScheduler> reminderSchedulerProvider,
      Provider<BloggingRemindersModelMapper> mapperProvider,
      Provider<SiteStore> siteStoreProvider) {
    this.mainDispatcherProvider = mainDispatcherProvider;
    this.bloggingRemindersManagerProvider = bloggingRemindersManagerProvider;
    this.bloggingRemindersStoreProvider = bloggingRemindersStoreProvider;
    this.prologueBuilderProvider = prologueBuilderProvider;
    this.daySelectionBuilderProvider = daySelectionBuilderProvider;
    this.epilogueBuilderProvider = epilogueBuilderProvider;
    this.dayLabelUtilsProvider = dayLabelUtilsProvider;
    this.analyticsTrackerProvider = analyticsTrackerProvider;
    this.reminderSchedulerProvider = reminderSchedulerProvider;
    this.mapperProvider = mapperProvider;
    this.siteStoreProvider = siteStoreProvider;
  }

  @Override
  public BloggingRemindersViewModel get() {
    return newInstance(mainDispatcherProvider.get(), bloggingRemindersManagerProvider.get(), bloggingRemindersStoreProvider.get(), prologueBuilderProvider.get(), daySelectionBuilderProvider.get(), epilogueBuilderProvider.get(), dayLabelUtilsProvider.get(), analyticsTrackerProvider.get(), reminderSchedulerProvider.get(), mapperProvider.get(), siteStoreProvider.get());
  }

  public static BloggingRemindersViewModel_Factory create(
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<BloggingRemindersManager> bloggingRemindersManagerProvider,
      Provider<BloggingRemindersStore> bloggingRemindersStoreProvider,
      Provider<PrologueBuilder> prologueBuilderProvider,
      Provider<DaySelectionBuilder> daySelectionBuilderProvider,
      Provider<EpilogueBuilder> epilogueBuilderProvider,
      Provider<DayLabelUtils> dayLabelUtilsProvider,
      Provider<BloggingRemindersAnalyticsTracker> analyticsTrackerProvider,
      Provider<ReminderScheduler> reminderSchedulerProvider,
      Provider<BloggingRemindersModelMapper> mapperProvider,
      Provider<SiteStore> siteStoreProvider) {
    return new BloggingRemindersViewModel_Factory(mainDispatcherProvider, bloggingRemindersManagerProvider, bloggingRemindersStoreProvider, prologueBuilderProvider, daySelectionBuilderProvider, epilogueBuilderProvider, dayLabelUtilsProvider, analyticsTrackerProvider, reminderSchedulerProvider, mapperProvider, siteStoreProvider);
  }

  public static BloggingRemindersViewModel newInstance(CoroutineDispatcher mainDispatcher,
      BloggingRemindersManager bloggingRemindersManager,
      BloggingRemindersStore bloggingRemindersStore, PrologueBuilder prologueBuilder,
      DaySelectionBuilder daySelectionBuilder, EpilogueBuilder epilogueBuilder,
      DayLabelUtils dayLabelUtils, BloggingRemindersAnalyticsTracker analyticsTracker,
      ReminderScheduler reminderScheduler, BloggingRemindersModelMapper mapper,
      SiteStore siteStore) {
    return new BloggingRemindersViewModel(mainDispatcher, bloggingRemindersManager, bloggingRemindersStore, prologueBuilder, daySelectionBuilder, epilogueBuilder, dayLabelUtils, analyticsTracker, reminderScheduler, mapper, siteStore);
  }
}
