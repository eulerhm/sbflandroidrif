package org.wordpress.android.ui.prefs.accountsettings.usecase;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.ui.utils.ContinuationWrapper;

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
public final class PushAccountSettingsUseCase_Factory implements Factory<PushAccountSettingsUseCase> {
  private final Provider<Dispatcher> dispatcherProvider;

  private final Provider<ContinuationWrapper<AccountStore.OnAccountChanged>> continuationWrapperProvider;

  private final Provider<CoroutineDispatcher> ioDispatcherProvider;

  public PushAccountSettingsUseCase_Factory(Provider<Dispatcher> dispatcherProvider,
      Provider<ContinuationWrapper<AccountStore.OnAccountChanged>> continuationWrapperProvider,
      Provider<CoroutineDispatcher> ioDispatcherProvider) {
    this.dispatcherProvider = dispatcherProvider;
    this.continuationWrapperProvider = continuationWrapperProvider;
    this.ioDispatcherProvider = ioDispatcherProvider;
  }

  @Override
  public PushAccountSettingsUseCase get() {
    return newInstance(dispatcherProvider.get(), continuationWrapperProvider.get(), ioDispatcherProvider.get());
  }

  public static PushAccountSettingsUseCase_Factory create(Provider<Dispatcher> dispatcherProvider,
      Provider<ContinuationWrapper<AccountStore.OnAccountChanged>> continuationWrapperProvider,
      Provider<CoroutineDispatcher> ioDispatcherProvider) {
    return new PushAccountSettingsUseCase_Factory(dispatcherProvider, continuationWrapperProvider, ioDispatcherProvider);
  }

  public static PushAccountSettingsUseCase newInstance(Dispatcher dispatcher,
      ContinuationWrapper<AccountStore.OnAccountChanged> continuationWrapper,
      CoroutineDispatcher ioDispatcher) {
    return new PushAccountSettingsUseCase(dispatcher, continuationWrapper, ioDispatcher);
  }
}
