package org.wordpress.android.ui.mediapicker.loader;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.store.StockMediaStore;
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
public final class StockMediaDataSource_Factory implements Factory<StockMediaDataSource> {
  private final Provider<StockMediaStore> stockMediaStoreProvider;

  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  private final Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider;

  public StockMediaDataSource_Factory(Provider<StockMediaStore> stockMediaStoreProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider,
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider) {
    this.stockMediaStoreProvider = stockMediaStoreProvider;
    this.bgDispatcherProvider = bgDispatcherProvider;
    this.networkUtilsWrapperProvider = networkUtilsWrapperProvider;
  }

  @Override
  public StockMediaDataSource get() {
    return newInstance(stockMediaStoreProvider.get(), bgDispatcherProvider.get(), networkUtilsWrapperProvider.get());
  }

  public static StockMediaDataSource_Factory create(
      Provider<StockMediaStore> stockMediaStoreProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider,
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider) {
    return new StockMediaDataSource_Factory(stockMediaStoreProvider, bgDispatcherProvider, networkUtilsWrapperProvider);
  }

  public static StockMediaDataSource newInstance(StockMediaStore stockMediaStore,
      CoroutineDispatcher bgDispatcher, NetworkUtilsWrapper networkUtilsWrapper) {
    return new StockMediaDataSource(stockMediaStore, bgDispatcher, networkUtilsWrapper);
  }
}
