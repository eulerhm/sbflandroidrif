package org.wordpress.android.viewmodel.history;

import androidx.lifecycle.LiveData;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.store.PostStore;
import org.wordpress.android.util.NetworkUtilsWrapper;
import org.wordpress.android.viewmodel.ResourceProvider;
import org.wordpress.android.viewmodel.helpers.ConnectionStatus;

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
public final class HistoryViewModel_Factory implements Factory<HistoryViewModel> {
  private final Provider<Dispatcher> dispatcherProvider;

  private final Provider<ResourceProvider> resourceProvider;

  private final Provider<NetworkUtilsWrapper> networkUtilsProvider;

  private final Provider<PostStore> postStoreProvider;

  private final Provider<CoroutineDispatcher> uiDispatcherProvider;

  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  private final Provider<LiveData<ConnectionStatus>> connectionStatusProvider;

  public HistoryViewModel_Factory(Provider<Dispatcher> dispatcherProvider,
      Provider<ResourceProvider> resourceProvider,
      Provider<NetworkUtilsWrapper> networkUtilsProvider, Provider<PostStore> postStoreProvider,
      Provider<CoroutineDispatcher> uiDispatcherProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider,
      Provider<LiveData<ConnectionStatus>> connectionStatusProvider) {
    this.dispatcherProvider = dispatcherProvider;
    this.resourceProvider = resourceProvider;
    this.networkUtilsProvider = networkUtilsProvider;
    this.postStoreProvider = postStoreProvider;
    this.uiDispatcherProvider = uiDispatcherProvider;
    this.bgDispatcherProvider = bgDispatcherProvider;
    this.connectionStatusProvider = connectionStatusProvider;
  }

  @Override
  public HistoryViewModel get() {
    return newInstance(dispatcherProvider.get(), resourceProvider.get(), networkUtilsProvider.get(), postStoreProvider.get(), uiDispatcherProvider.get(), bgDispatcherProvider.get(), connectionStatusProvider.get());
  }

  public static HistoryViewModel_Factory create(Provider<Dispatcher> dispatcherProvider,
      Provider<ResourceProvider> resourceProvider,
      Provider<NetworkUtilsWrapper> networkUtilsProvider, Provider<PostStore> postStoreProvider,
      Provider<CoroutineDispatcher> uiDispatcherProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider,
      Provider<LiveData<ConnectionStatus>> connectionStatusProvider) {
    return new HistoryViewModel_Factory(dispatcherProvider, resourceProvider, networkUtilsProvider, postStoreProvider, uiDispatcherProvider, bgDispatcherProvider, connectionStatusProvider);
  }

  public static HistoryViewModel newInstance(Dispatcher dispatcher,
      ResourceProvider resourceProvider, NetworkUtilsWrapper networkUtils, PostStore postStore,
      CoroutineDispatcher uiDispatcher, CoroutineDispatcher bgDispatcher,
      LiveData<ConnectionStatus> connectionStatus) {
    return new HistoryViewModel(dispatcher, resourceProvider, networkUtils, postStore, uiDispatcher, bgDispatcher, connectionStatus);
  }
}
