package org.wordpress.android.ui.stats.refresh.utils;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.reader.tracker.ReaderTracker;
import org.wordpress.android.ui.stats.refresh.lists.sections.granular.SelectedDateProvider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class StatsNavigator_Factory implements Factory<StatsNavigator> {
  private final Provider<StatsSiteProvider> siteProvider;

  private final Provider<SelectedDateProvider> selectedDateProvider;

  private final Provider<ReaderTracker> readerTrackerProvider;

  public StatsNavigator_Factory(Provider<StatsSiteProvider> siteProvider,
      Provider<SelectedDateProvider> selectedDateProvider,
      Provider<ReaderTracker> readerTrackerProvider) {
    this.siteProvider = siteProvider;
    this.selectedDateProvider = selectedDateProvider;
    this.readerTrackerProvider = readerTrackerProvider;
  }

  @Override
  public StatsNavigator get() {
    return newInstance(siteProvider.get(), selectedDateProvider.get(), readerTrackerProvider.get());
  }

  public static StatsNavigator_Factory create(Provider<StatsSiteProvider> siteProvider,
      Provider<SelectedDateProvider> selectedDateProvider,
      Provider<ReaderTracker> readerTrackerProvider) {
    return new StatsNavigator_Factory(siteProvider, selectedDateProvider, readerTrackerProvider);
  }

  public static StatsNavigator newInstance(StatsSiteProvider siteProvider,
      SelectedDateProvider selectedDateProvider, ReaderTracker readerTracker) {
    return new StatsNavigator(siteProvider, selectedDateProvider, readerTracker);
  }
}
