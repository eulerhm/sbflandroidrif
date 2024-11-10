package org.wordpress.android.ui.stats.refresh;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.store.JetpackStore;
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
public final class StatsModuleActivateUseCase_Factory implements Factory<StatsModuleActivateUseCase> {
  private final Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider;

  private final Provider<JetpackStore> jetpackStoreProvider;

  private final Provider<CoroutineDispatcher> ioDispatcherProvider;

  public StatsModuleActivateUseCase_Factory(
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider,
      Provider<JetpackStore> jetpackStoreProvider,
      Provider<CoroutineDispatcher> ioDispatcherProvider) {
    this.networkUtilsWrapperProvider = networkUtilsWrapperProvider;
    this.jetpackStoreProvider = jetpackStoreProvider;
    this.ioDispatcherProvider = ioDispatcherProvider;
  }

  @Override
  public StatsModuleActivateUseCase get() {
    return newInstance(networkUtilsWrapperProvider.get(), jetpackStoreProvider.get(), ioDispatcherProvider.get());
  }

  public static StatsModuleActivateUseCase_Factory create(
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider,
      Provider<JetpackStore> jetpackStoreProvider,
      Provider<CoroutineDispatcher> ioDispatcherProvider) {
    return new StatsModuleActivateUseCase_Factory(networkUtilsWrapperProvider, jetpackStoreProvider, ioDispatcherProvider);
  }

  public static StatsModuleActivateUseCase newInstance(NetworkUtilsWrapper networkUtilsWrapper,
      JetpackStore jetpackStore, CoroutineDispatcher ioDispatcher) {
    return new StatsModuleActivateUseCase(networkUtilsWrapper, jetpackStore, ioDispatcher);
  }
}
