package org.wordpress.android.viewmodel.posts;

import androidx.lifecycle.LiveData;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.fluxc.store.ListStore;
import org.wordpress.android.fluxc.store.PostStore;
import org.wordpress.android.ui.reader.utils.ReaderUtilsWrapper;
import org.wordpress.android.ui.uploads.UploadStarter;
import org.wordpress.android.ui.uploads.UploadUtilsWrapper;
import org.wordpress.android.util.NetworkUtilsWrapper;
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
public final class PostListViewModel_Factory implements Factory<PostListViewModel> {
  private final Provider<Dispatcher> dispatcherProvider;

  private final Provider<ListStore> listStoreProvider;

  private final Provider<PostStore> postStoreProvider;

  private final Provider<AccountStore> accountStoreProvider;

  private final Provider<PostListItemUiStateHelper> listItemUiStateHelperProvider;

  private final Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider;

  private final Provider<UploadStarter> uploadStarterProvider;

  private final Provider<ReaderUtilsWrapper> readerUtilsWrapperProvider;

  private final Provider<UploadUtilsWrapper> uploadUtilsWrapperProvider;

  private final Provider<CoroutineDispatcher> uiDispatcherProvider;

  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  private final Provider<LiveData<ConnectionStatus>> connectionStatusProvider;

  public PostListViewModel_Factory(Provider<Dispatcher> dispatcherProvider,
      Provider<ListStore> listStoreProvider, Provider<PostStore> postStoreProvider,
      Provider<AccountStore> accountStoreProvider,
      Provider<PostListItemUiStateHelper> listItemUiStateHelperProvider,
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider,
      Provider<UploadStarter> uploadStarterProvider,
      Provider<ReaderUtilsWrapper> readerUtilsWrapperProvider,
      Provider<UploadUtilsWrapper> uploadUtilsWrapperProvider,
      Provider<CoroutineDispatcher> uiDispatcherProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider,
      Provider<LiveData<ConnectionStatus>> connectionStatusProvider) {
    this.dispatcherProvider = dispatcherProvider;
    this.listStoreProvider = listStoreProvider;
    this.postStoreProvider = postStoreProvider;
    this.accountStoreProvider = accountStoreProvider;
    this.listItemUiStateHelperProvider = listItemUiStateHelperProvider;
    this.networkUtilsWrapperProvider = networkUtilsWrapperProvider;
    this.uploadStarterProvider = uploadStarterProvider;
    this.readerUtilsWrapperProvider = readerUtilsWrapperProvider;
    this.uploadUtilsWrapperProvider = uploadUtilsWrapperProvider;
    this.uiDispatcherProvider = uiDispatcherProvider;
    this.bgDispatcherProvider = bgDispatcherProvider;
    this.connectionStatusProvider = connectionStatusProvider;
  }

  @Override
  public PostListViewModel get() {
    return newInstance(dispatcherProvider.get(), listStoreProvider.get(), postStoreProvider.get(), accountStoreProvider.get(), listItemUiStateHelperProvider.get(), networkUtilsWrapperProvider.get(), uploadStarterProvider.get(), readerUtilsWrapperProvider.get(), uploadUtilsWrapperProvider.get(), uiDispatcherProvider.get(), bgDispatcherProvider.get(), connectionStatusProvider.get());
  }

  public static PostListViewModel_Factory create(Provider<Dispatcher> dispatcherProvider,
      Provider<ListStore> listStoreProvider, Provider<PostStore> postStoreProvider,
      Provider<AccountStore> accountStoreProvider,
      Provider<PostListItemUiStateHelper> listItemUiStateHelperProvider,
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider,
      Provider<UploadStarter> uploadStarterProvider,
      Provider<ReaderUtilsWrapper> readerUtilsWrapperProvider,
      Provider<UploadUtilsWrapper> uploadUtilsWrapperProvider,
      Provider<CoroutineDispatcher> uiDispatcherProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider,
      Provider<LiveData<ConnectionStatus>> connectionStatusProvider) {
    return new PostListViewModel_Factory(dispatcherProvider, listStoreProvider, postStoreProvider, accountStoreProvider, listItemUiStateHelperProvider, networkUtilsWrapperProvider, uploadStarterProvider, readerUtilsWrapperProvider, uploadUtilsWrapperProvider, uiDispatcherProvider, bgDispatcherProvider, connectionStatusProvider);
  }

  public static PostListViewModel newInstance(Dispatcher dispatcher, ListStore listStore,
      PostStore postStore, AccountStore accountStore,
      PostListItemUiStateHelper listItemUiStateHelper, NetworkUtilsWrapper networkUtilsWrapper,
      UploadStarter uploadStarter, ReaderUtilsWrapper readerUtilsWrapper,
      UploadUtilsWrapper uploadUtilsWrapper, CoroutineDispatcher uiDispatcher,
      CoroutineDispatcher bgDispatcher, LiveData<ConnectionStatus> connectionStatus) {
    return new PostListViewModel(dispatcher, listStore, postStore, accountStore, listItemUiStateHelper, networkUtilsWrapper, uploadStarter, readerUtilsWrapper, uploadUtilsWrapper, uiDispatcher, bgDispatcher, connectionStatus);
  }
}
