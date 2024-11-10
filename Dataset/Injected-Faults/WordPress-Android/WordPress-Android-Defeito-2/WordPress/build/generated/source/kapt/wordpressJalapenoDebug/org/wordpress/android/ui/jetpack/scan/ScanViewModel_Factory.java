package org.wordpress.android.ui.jetpack.scan;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.store.ScanStore;
import org.wordpress.android.ui.jetpack.scan.builders.ScanStateListItemsBuilder;
import org.wordpress.android.ui.jetpack.scan.usecases.FetchFixThreatsStatusUseCase;
import org.wordpress.android.ui.jetpack.scan.usecases.FetchScanStateUseCase;
import org.wordpress.android.ui.jetpack.scan.usecases.FixThreatsUseCase;
import org.wordpress.android.ui.jetpack.scan.usecases.StartScanUseCase;
import org.wordpress.android.ui.utils.HtmlMessageUtils;
import org.wordpress.android.util.analytics.ScanTracker;

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
public final class ScanViewModel_Factory implements Factory<ScanViewModel> {
  private final Provider<ScanStateListItemsBuilder> scanStateListItemsBuilderProvider;

  private final Provider<FetchScanStateUseCase> fetchScanStateUseCaseProvider;

  private final Provider<StartScanUseCase> startScanUseCaseProvider;

  private final Provider<FixThreatsUseCase> fixThreatsUseCaseProvider;

  private final Provider<FetchFixThreatsStatusUseCase> fetchFixThreatsStatusUseCaseProvider;

  private final Provider<ScanStore> scanStoreProvider;

  private final Provider<ScanTracker> scanTrackerProvider;

  private final Provider<HtmlMessageUtils> htmlMessageUtilsProvider;

  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  public ScanViewModel_Factory(
      Provider<ScanStateListItemsBuilder> scanStateListItemsBuilderProvider,
      Provider<FetchScanStateUseCase> fetchScanStateUseCaseProvider,
      Provider<StartScanUseCase> startScanUseCaseProvider,
      Provider<FixThreatsUseCase> fixThreatsUseCaseProvider,
      Provider<FetchFixThreatsStatusUseCase> fetchFixThreatsStatusUseCaseProvider,
      Provider<ScanStore> scanStoreProvider, Provider<ScanTracker> scanTrackerProvider,
      Provider<HtmlMessageUtils> htmlMessageUtilsProvider,
      Provider<CoroutineDispatcher> mainDispatcherProvider) {
    this.scanStateListItemsBuilderProvider = scanStateListItemsBuilderProvider;
    this.fetchScanStateUseCaseProvider = fetchScanStateUseCaseProvider;
    this.startScanUseCaseProvider = startScanUseCaseProvider;
    this.fixThreatsUseCaseProvider = fixThreatsUseCaseProvider;
    this.fetchFixThreatsStatusUseCaseProvider = fetchFixThreatsStatusUseCaseProvider;
    this.scanStoreProvider = scanStoreProvider;
    this.scanTrackerProvider = scanTrackerProvider;
    this.htmlMessageUtilsProvider = htmlMessageUtilsProvider;
    this.mainDispatcherProvider = mainDispatcherProvider;
  }

  @Override
  public ScanViewModel get() {
    return newInstance(scanStateListItemsBuilderProvider.get(), fetchScanStateUseCaseProvider.get(), startScanUseCaseProvider.get(), fixThreatsUseCaseProvider.get(), fetchFixThreatsStatusUseCaseProvider.get(), scanStoreProvider.get(), scanTrackerProvider.get(), htmlMessageUtilsProvider.get(), mainDispatcherProvider.get());
  }

  public static ScanViewModel_Factory create(
      Provider<ScanStateListItemsBuilder> scanStateListItemsBuilderProvider,
      Provider<FetchScanStateUseCase> fetchScanStateUseCaseProvider,
      Provider<StartScanUseCase> startScanUseCaseProvider,
      Provider<FixThreatsUseCase> fixThreatsUseCaseProvider,
      Provider<FetchFixThreatsStatusUseCase> fetchFixThreatsStatusUseCaseProvider,
      Provider<ScanStore> scanStoreProvider, Provider<ScanTracker> scanTrackerProvider,
      Provider<HtmlMessageUtils> htmlMessageUtilsProvider,
      Provider<CoroutineDispatcher> mainDispatcherProvider) {
    return new ScanViewModel_Factory(scanStateListItemsBuilderProvider, fetchScanStateUseCaseProvider, startScanUseCaseProvider, fixThreatsUseCaseProvider, fetchFixThreatsStatusUseCaseProvider, scanStoreProvider, scanTrackerProvider, htmlMessageUtilsProvider, mainDispatcherProvider);
  }

  public static ScanViewModel newInstance(ScanStateListItemsBuilder scanStateListItemsBuilder,
      FetchScanStateUseCase fetchScanStateUseCase, StartScanUseCase startScanUseCase,
      FixThreatsUseCase fixThreatsUseCase,
      FetchFixThreatsStatusUseCase fetchFixThreatsStatusUseCase, ScanStore scanStore,
      ScanTracker scanTracker, HtmlMessageUtils htmlMessageUtils,
      CoroutineDispatcher mainDispatcher) {
    return new ScanViewModel(scanStateListItemsBuilder, fetchScanStateUseCase, startScanUseCase, fixThreatsUseCase, fetchFixThreatsStatusUseCase, scanStore, scanTracker, htmlMessageUtils, mainDispatcher);
  }
}
