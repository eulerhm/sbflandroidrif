package org.wordpress.android.ui.jetpack.scan.usecases;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.store.ScanStore;
import org.wordpress.android.util.NetworkUtilsWrapper;
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
public final class FetchScanHistoryUseCase_Factory implements Factory<FetchScanHistoryUseCase> {
  private final Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider;

  private final Provider<ScanStore> scanStoreProvider;

  private final Provider<ScanTracker> scanTrackerProvider;

  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  public FetchScanHistoryUseCase_Factory(Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider,
      Provider<ScanStore> scanStoreProvider, Provider<ScanTracker> scanTrackerProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider) {
    this.networkUtilsWrapperProvider = networkUtilsWrapperProvider;
    this.scanStoreProvider = scanStoreProvider;
    this.scanTrackerProvider = scanTrackerProvider;
    this.bgDispatcherProvider = bgDispatcherProvider;
  }

  @Override
  public FetchScanHistoryUseCase get() {
    return newInstance(networkUtilsWrapperProvider.get(), scanStoreProvider.get(), scanTrackerProvider.get(), bgDispatcherProvider.get());
  }

  public static FetchScanHistoryUseCase_Factory create(
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider,
      Provider<ScanStore> scanStoreProvider, Provider<ScanTracker> scanTrackerProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider) {
    return new FetchScanHistoryUseCase_Factory(networkUtilsWrapperProvider, scanStoreProvider, scanTrackerProvider, bgDispatcherProvider);
  }

  public static FetchScanHistoryUseCase newInstance(NetworkUtilsWrapper networkUtilsWrapper,
      ScanStore scanStore, ScanTracker scanTracker, CoroutineDispatcher bgDispatcher) {
    return new FetchScanHistoryUseCase(networkUtilsWrapper, scanStore, scanTracker, bgDispatcher);
  }
}
