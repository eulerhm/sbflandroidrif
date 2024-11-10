package org.wordpress.android.ui.sitecreation.usecases;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.store.VerticalStore;

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
public final class FetchSegmentsUseCase_Factory implements Factory<FetchSegmentsUseCase> {
  private final Provider<Dispatcher> dispatcherProvider;

  private final Provider<VerticalStore> verticalStoreProvider;

  public FetchSegmentsUseCase_Factory(Provider<Dispatcher> dispatcherProvider,
      Provider<VerticalStore> verticalStoreProvider) {
    this.dispatcherProvider = dispatcherProvider;
    this.verticalStoreProvider = verticalStoreProvider;
  }

  @Override
  public FetchSegmentsUseCase get() {
    return newInstance(dispatcherProvider.get(), verticalStoreProvider.get());
  }

  public static FetchSegmentsUseCase_Factory create(Provider<Dispatcher> dispatcherProvider,
      Provider<VerticalStore> verticalStoreProvider) {
    return new FetchSegmentsUseCase_Factory(dispatcherProvider, verticalStoreProvider);
  }

  public static FetchSegmentsUseCase newInstance(Dispatcher dispatcher,
      VerticalStore verticalStore) {
    return new FetchSegmentsUseCase(dispatcher, verticalStore);
  }
}
