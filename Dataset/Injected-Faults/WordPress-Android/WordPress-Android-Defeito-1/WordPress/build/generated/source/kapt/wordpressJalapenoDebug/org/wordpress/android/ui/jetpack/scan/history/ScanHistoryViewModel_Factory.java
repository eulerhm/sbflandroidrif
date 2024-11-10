package org.wordpress.android.ui.jetpack.scan.history;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.ui.jetpack.scan.usecases.FetchScanHistoryUseCase;
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
public final class ScanHistoryViewModel_Factory implements Factory<ScanHistoryViewModel> {
  private final Provider<ScanTracker> scanTrackerProvider;

  private final Provider<FetchScanHistoryUseCase> fetchScanHistoryUseCaseProvider;

  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  public ScanHistoryViewModel_Factory(Provider<ScanTracker> scanTrackerProvider,
      Provider<FetchScanHistoryUseCase> fetchScanHistoryUseCaseProvider,
      Provider<CoroutineDispatcher> mainDispatcherProvider) {
    this.scanTrackerProvider = scanTrackerProvider;
    this.fetchScanHistoryUseCaseProvider = fetchScanHistoryUseCaseProvider;
    this.mainDispatcherProvider = mainDispatcherProvider;
  }

  @Override
  public ScanHistoryViewModel get() {
    return newInstance(scanTrackerProvider.get(), fetchScanHistoryUseCaseProvider.get(), mainDispatcherProvider.get());
  }

  public static ScanHistoryViewModel_Factory create(Provider<ScanTracker> scanTrackerProvider,
      Provider<FetchScanHistoryUseCase> fetchScanHistoryUseCaseProvider,
      Provider<CoroutineDispatcher> mainDispatcherProvider) {
    return new ScanHistoryViewModel_Factory(scanTrackerProvider, fetchScanHistoryUseCaseProvider, mainDispatcherProvider);
  }

  public static ScanHistoryViewModel newInstance(ScanTracker scanTracker,
      FetchScanHistoryUseCase fetchScanHistoryUseCase, CoroutineDispatcher mainDispatcher) {
    return new ScanHistoryViewModel(scanTracker, fetchScanHistoryUseCase, mainDispatcher);
  }
}
