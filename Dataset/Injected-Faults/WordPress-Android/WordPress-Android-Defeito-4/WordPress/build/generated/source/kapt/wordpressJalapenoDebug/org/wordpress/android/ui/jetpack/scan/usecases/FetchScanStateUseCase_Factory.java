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
public final class FetchScanStateUseCase_Factory implements Factory<FetchScanStateUseCase> {
  private final Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider;

  private final Provider<ScanStore> scanStoreProvider;

  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  public FetchScanStateUseCase_Factory(Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider,
      Provider<ScanStore> scanStoreProvider, Provider<CoroutineDispatcher> bgDispatcherProvider) {
    this.networkUtilsWrapperProvider = networkUtilsWrapperProvider;
    this.scanStoreProvider = scanStoreProvider;
    this.bgDispatcherProvider = bgDispatcherProvider;
  }

  @Override
  public FetchScanStateUseCase get() {
    return newInstance(networkUtilsWrapperProvider.get(), scanStoreProvider.get(), bgDispatcherProvider.get());
  }

  public static FetchScanStateUseCase_Factory create(
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider,
      Provider<ScanStore> scanStoreProvider, Provider<CoroutineDispatcher> bgDispatcherProvider) {
    return new FetchScanStateUseCase_Factory(networkUtilsWrapperProvider, scanStoreProvider, bgDispatcherProvider);
  }

  public static FetchScanStateUseCase newInstance(NetworkUtilsWrapper networkUtilsWrapper,
      ScanStore scanStore, CoroutineDispatcher bgDispatcher) {
    return new FetchScanStateUseCase(networkUtilsWrapper, scanStore, bgDispatcher);
  }
}
