package org.wordpress.android.ui.engagement;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.fluxc.store.CommentStore;
import org.wordpress.android.fluxc.store.PostStore;
import org.wordpress.android.util.NetworkUtilsWrapper;

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
public final class GetLikesUseCase_Factory implements Factory<GetLikesUseCase> {
  private final Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider;

  private final Provider<Dispatcher> dispatcherProvider;

  private final Provider<CommentStore> commentStoreProvider;

  private final Provider<PostStore> postStoreProvider;

  private final Provider<AccountStore> accountStoreProvider;

  public GetLikesUseCase_Factory(Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider,
      Provider<Dispatcher> dispatcherProvider, Provider<CommentStore> commentStoreProvider,
      Provider<PostStore> postStoreProvider, Provider<AccountStore> accountStoreProvider) {
    this.networkUtilsWrapperProvider = networkUtilsWrapperProvider;
    this.dispatcherProvider = dispatcherProvider;
    this.commentStoreProvider = commentStoreProvider;
    this.postStoreProvider = postStoreProvider;
    this.accountStoreProvider = accountStoreProvider;
  }

  @Override
  public GetLikesUseCase get() {
    return newInstance(networkUtilsWrapperProvider.get(), dispatcherProvider.get(), commentStoreProvider.get(), postStoreProvider.get(), accountStoreProvider.get());
  }

  public static GetLikesUseCase_Factory create(
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider,
      Provider<Dispatcher> dispatcherProvider, Provider<CommentStore> commentStoreProvider,
      Provider<PostStore> postStoreProvider, Provider<AccountStore> accountStoreProvider) {
    return new GetLikesUseCase_Factory(networkUtilsWrapperProvider, dispatcherProvider, commentStoreProvider, postStoreProvider, accountStoreProvider);
  }

  public static GetLikesUseCase newInstance(NetworkUtilsWrapper networkUtilsWrapper,
      Dispatcher dispatcher, CommentStore commentStore, PostStore postStore,
      AccountStore accountStore) {
    return new GetLikesUseCase(networkUtilsWrapper, dispatcher, commentStore, postStore, accountStore);
  }
}
