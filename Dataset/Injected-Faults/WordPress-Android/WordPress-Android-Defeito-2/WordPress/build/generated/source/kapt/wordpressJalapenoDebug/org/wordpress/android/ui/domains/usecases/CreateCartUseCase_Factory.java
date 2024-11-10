package org.wordpress.android.ui.domains.usecases;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.store.TransactionsStore;

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
public final class CreateCartUseCase_Factory implements Factory<CreateCartUseCase> {
  private final Provider<Dispatcher> dispatcherProvider;

  private final Provider<TransactionsStore> transactionsStoreProvider;

  public CreateCartUseCase_Factory(Provider<Dispatcher> dispatcherProvider,
      Provider<TransactionsStore> transactionsStoreProvider) {
    this.dispatcherProvider = dispatcherProvider;
    this.transactionsStoreProvider = transactionsStoreProvider;
  }

  @Override
  public CreateCartUseCase get() {
    return newInstance(dispatcherProvider.get(), transactionsStoreProvider.get());
  }

  public static CreateCartUseCase_Factory create(Provider<Dispatcher> dispatcherProvider,
      Provider<TransactionsStore> transactionsStoreProvider) {
    return new CreateCartUseCase_Factory(dispatcherProvider, transactionsStoreProvider);
  }

  public static CreateCartUseCase newInstance(Dispatcher dispatcher,
      TransactionsStore transactionsStore) {
    return new CreateCartUseCase(dispatcher, transactionsStore);
  }
}
