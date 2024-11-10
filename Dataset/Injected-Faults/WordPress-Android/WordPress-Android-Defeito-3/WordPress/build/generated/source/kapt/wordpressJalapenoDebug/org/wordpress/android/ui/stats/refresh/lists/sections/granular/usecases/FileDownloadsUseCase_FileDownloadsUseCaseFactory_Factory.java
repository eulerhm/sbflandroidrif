package org.wordpress.android.ui.stats.refresh.lists.sections.granular.usecases;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.store.stats.time.FileDownloadsStore;
import org.wordpress.android.ui.stats.refresh.lists.sections.granular.SelectedDateProvider;
import org.wordpress.android.ui.stats.refresh.utils.ContentDescriptionHelper;
import org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider;
import org.wordpress.android.ui.stats.refresh.utils.StatsUtils;
import org.wordpress.android.util.LocaleManagerWrapper;
import org.wordpress.android.util.analytics.AnalyticsTrackerWrapper;

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
public final class FileDownloadsUseCase_FileDownloadsUseCaseFactory_Factory implements Factory<FileDownloadsUseCase.FileDownloadsUseCaseFactory> {
  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  private final Provider<CoroutineDispatcher> backgroundDispatcherProvider;

  private final Provider<FileDownloadsStore> storeProvider;

  private final Provider<SelectedDateProvider> selectedDateProvider;

  private final Provider<StatsSiteProvider> statsSiteProvider;

  private final Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider;

  private final Provider<ContentDescriptionHelper> contentDescriptionHelperProvider;

  private final Provider<StatsUtils> statsUtilsProvider;

  private final Provider<LocaleManagerWrapper> localeManagerWrapperProvider;

  public FileDownloadsUseCase_FileDownloadsUseCaseFactory_Factory(
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> backgroundDispatcherProvider,
      Provider<FileDownloadsStore> storeProvider,
      Provider<SelectedDateProvider> selectedDateProvider,
      Provider<StatsSiteProvider> statsSiteProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider,
      Provider<ContentDescriptionHelper> contentDescriptionHelperProvider,
      Provider<StatsUtils> statsUtilsProvider,
      Provider<LocaleManagerWrapper> localeManagerWrapperProvider) {
    this.mainDispatcherProvider = mainDispatcherProvider;
    this.backgroundDispatcherProvider = backgroundDispatcherProvider;
    this.storeProvider = storeProvider;
    this.selectedDateProvider = selectedDateProvider;
    this.statsSiteProvider = statsSiteProvider;
    this.analyticsTrackerProvider = analyticsTrackerProvider;
    this.contentDescriptionHelperProvider = contentDescriptionHelperProvider;
    this.statsUtilsProvider = statsUtilsProvider;
    this.localeManagerWrapperProvider = localeManagerWrapperProvider;
  }

  @Override
  public FileDownloadsUseCase.FileDownloadsUseCaseFactory get() {
    return newInstance(mainDispatcherProvider.get(), backgroundDispatcherProvider.get(), storeProvider.get(), selectedDateProvider.get(), statsSiteProvider.get(), analyticsTrackerProvider.get(), contentDescriptionHelperProvider.get(), statsUtilsProvider.get(), localeManagerWrapperProvider.get());
  }

  public static FileDownloadsUseCase_FileDownloadsUseCaseFactory_Factory create(
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> backgroundDispatcherProvider,
      Provider<FileDownloadsStore> storeProvider,
      Provider<SelectedDateProvider> selectedDateProvider,
      Provider<StatsSiteProvider> statsSiteProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider,
      Provider<ContentDescriptionHelper> contentDescriptionHelperProvider,
      Provider<StatsUtils> statsUtilsProvider,
      Provider<LocaleManagerWrapper> localeManagerWrapperProvider) {
    return new FileDownloadsUseCase_FileDownloadsUseCaseFactory_Factory(mainDispatcherProvider, backgroundDispatcherProvider, storeProvider, selectedDateProvider, statsSiteProvider, analyticsTrackerProvider, contentDescriptionHelperProvider, statsUtilsProvider, localeManagerWrapperProvider);
  }

  public static FileDownloadsUseCase.FileDownloadsUseCaseFactory newInstance(
      CoroutineDispatcher mainDispatcher, CoroutineDispatcher backgroundDispatcher,
      FileDownloadsStore store, SelectedDateProvider selectedDateProvider,
      StatsSiteProvider statsSiteProvider, AnalyticsTrackerWrapper analyticsTracker,
      ContentDescriptionHelper contentDescriptionHelper, StatsUtils statsUtils,
      LocaleManagerWrapper localeManagerWrapper) {
    return new FileDownloadsUseCase.FileDownloadsUseCaseFactory(mainDispatcher, backgroundDispatcher, store, selectedDateProvider, statsSiteProvider, analyticsTracker, contentDescriptionHelper, statsUtils, localeManagerWrapper);
  }
}
