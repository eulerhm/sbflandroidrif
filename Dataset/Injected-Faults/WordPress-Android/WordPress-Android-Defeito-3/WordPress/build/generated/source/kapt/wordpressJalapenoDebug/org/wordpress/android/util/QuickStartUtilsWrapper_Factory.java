package org.wordpress.android.util;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.store.QuickStartStore;

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
public final class QuickStartUtilsWrapper_Factory implements Factory<QuickStartUtilsWrapper> {
  private final Provider<QuickStartStore> quickStartStoreProvider;

  private final Provider<Dispatcher> dispatcherProvider;

  public QuickStartUtilsWrapper_Factory(Provider<QuickStartStore> quickStartStoreProvider,
      Provider<Dispatcher> dispatcherProvider) {
    this.quickStartStoreProvider = quickStartStoreProvider;
    this.dispatcherProvider = dispatcherProvider;
  }

  @Override
  public QuickStartUtilsWrapper get() {
    return newInstance(quickStartStoreProvider.get(), dispatcherProvider.get());
  }

  public static QuickStartUtilsWrapper_Factory create(
      Provider<QuickStartStore> quickStartStoreProvider, Provider<Dispatcher> dispatcherProvider) {
    return new QuickStartUtilsWrapper_Factory(quickStartStoreProvider, dispatcherProvider);
  }

  public static QuickStartUtilsWrapper newInstance(QuickStartStore quickStartStore,
      Dispatcher dispatcher) {
    return new QuickStartUtilsWrapper(quickStartStore, dispatcher);
  }
}
