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
public final class FetchFixThreatsStatusUseCase_Factory implements Factory<FetchFixThreatsStatusUseCase> {
  private final Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider;

  private final Provider<ScanStore> scanStoreProvider;

  private final Provider<CoroutineDispatcher> ioDispatcherProvider;

  public FetchFixThreatsStatusUseCase_Factory(
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider,
      Provider<ScanStore> scanStoreProvider, Provider<CoroutineDispatcher> ioDispatcherProvider) {
    this.networkUtilsWrapperProvider = networkUtilsWrapperProvider;
    this.scanStoreProvider = scanStoreProvider;
    this.ioDispatcherProvider = ioDispatcherProvider;
  }

  @Override
  public FetchFixThreatsStatusUseCase get() {
    return newInstance(networkUtilsWrapperProvider.get(), scanStoreProvider.get(), ioDispatcherProvider.get());
  }

  public static FetchFixThreatsStatusUseCase_Factory create(
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider,
      Provider<ScanStore> scanStoreProvider, Provider<CoroutineDispatcher> ioDispatcherProvider) {
    return new FetchFixThreatsStatusUseCase_Factory(networkUtilsWrapperProvider, scanStoreProvider, ioDispatcherProvider);
  }

  public static FetchFixThreatsStatusUseCase newInstance(NetworkUtilsWrapper networkUtilsWrapper,
      ScanStore scanStore, CoroutineDispatcher ioDispatcher) {
    return new FetchFixThreatsStatusUseCase(networkUtilsWrapper, scanStore, ioDispatcher);
  }
}
