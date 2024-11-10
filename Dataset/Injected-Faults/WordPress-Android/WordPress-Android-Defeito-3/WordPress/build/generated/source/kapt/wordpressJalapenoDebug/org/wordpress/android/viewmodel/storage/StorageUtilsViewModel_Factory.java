package org.wordpress.android.viewmodel.storage;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.util.StorageUtilsProvider;

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
public final class StorageUtilsViewModel_Factory implements Factory<StorageUtilsViewModel> {
  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  private final Provider<StorageUtilsProvider> storageUtilsProvider;

  public StorageUtilsViewModel_Factory(Provider<CoroutineDispatcher> bgDispatcherProvider,
      Provider<StorageUtilsProvider> storageUtilsProvider) {
    this.bgDispatcherProvider = bgDispatcherProvider;
    this.storageUtilsProvider = storageUtilsProvider;
  }

  @Override
  public StorageUtilsViewModel get() {
    return newInstance(bgDispatcherProvider.get(), storageUtilsProvider.get());
  }

  public static StorageUtilsViewModel_Factory create(
      Provider<CoroutineDispatcher> bgDispatcherProvider,
      Provider<StorageUtilsProvider> storageUtilsProvider) {
    return new StorageUtilsViewModel_Factory(bgDispatcherProvider, storageUtilsProvider);
  }

  public static StorageUtilsViewModel newInstance(CoroutineDispatcher bgDispatcher,
      StorageUtilsProvider storageUtilsProvider) {
    return new StorageUtilsViewModel(bgDispatcher, storageUtilsProvider);
  }
}
