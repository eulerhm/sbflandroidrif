package org.wordpress.android.ui.jetpack.scan.details.usecases;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.store.ScanStore;

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
public final class GetThreatModelUseCase_Factory implements Factory<GetThreatModelUseCase> {
  private final Provider<CoroutineDispatcher> ioDispatcherProvider;

  private final Provider<ScanStore> scanStoreProvider;

  public GetThreatModelUseCase_Factory(Provider<CoroutineDispatcher> ioDispatcherProvider,
      Provider<ScanStore> scanStoreProvider) {
    this.ioDispatcherProvider = ioDispatcherProvider;
    this.scanStoreProvider = scanStoreProvider;
  }

  @Override
  public GetThreatModelUseCase get() {
    return newInstance(ioDispatcherProvider.get(), scanStoreProvider.get());
  }

  public static GetThreatModelUseCase_Factory create(
      Provider<CoroutineDispatcher> ioDispatcherProvider, Provider<ScanStore> scanStoreProvider) {
    return new GetThreatModelUseCase_Factory(ioDispatcherProvider, scanStoreProvider);
  }

  public static GetThreatModelUseCase newInstance(CoroutineDispatcher ioDispatcher,
      ScanStore scanStore) {
    return new GetThreatModelUseCase(ioDispatcher, scanStore);
  }
}
