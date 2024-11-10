package org.wordpress.android.ui.posts.editor;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.ui.posts.PostUtilsWrapper;
import org.wordpress.android.ui.posts.SavePostToDbUseCase;
import org.wordpress.android.ui.uploads.UploadServiceFacade;
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
public final class StorePostViewModel_Factory implements Factory<StorePostViewModel> {
  private final Provider<CoroutineDispatcher> uiCoroutineDispatcherProvider;

  private final Provider<SiteStore> siteStoreProvider;

  private final Provider<PostUtilsWrapper> postUtilsProvider;

  private final Provider<UploadServiceFacade> uploadServiceProvider;

  private final Provider<SavePostToDbUseCase> savePostToDbUseCaseProvider;

  private final Provider<NetworkUtilsWrapper> networkUtilsProvider;

  private final Provider<Dispatcher> dispatcherProvider;

  public StorePostViewModel_Factory(Provider<CoroutineDispatcher> uiCoroutineDispatcherProvider,
      Provider<SiteStore> siteStoreProvider, Provider<PostUtilsWrapper> postUtilsProvider,
      Provider<UploadServiceFacade> uploadServiceProvider,
      Provider<SavePostToDbUseCase> savePostToDbUseCaseProvider,
      Provider<NetworkUtilsWrapper> networkUtilsProvider, Provider<Dispatcher> dispatcherProvider) {
    this.uiCoroutineDispatcherProvider = uiCoroutineDispatcherProvider;
    this.siteStoreProvider = siteStoreProvider;
    this.postUtilsProvider = postUtilsProvider;
    this.uploadServiceProvider = uploadServiceProvider;
    this.savePostToDbUseCaseProvider = savePostToDbUseCaseProvider;
    this.networkUtilsProvider = networkUtilsProvider;
    this.dispatcherProvider = dispatcherProvider;
  }

  @Override
  public StorePostViewModel get() {
    return newInstance(uiCoroutineDispatcherProvider.get(), siteStoreProvider.get(), postUtilsProvider.get(), uploadServiceProvider.get(), savePostToDbUseCaseProvider.get(), networkUtilsProvider.get(), dispatcherProvider.get());
  }

  public static StorePostViewModel_Factory create(
      Provider<CoroutineDispatcher> uiCoroutineDispatcherProvider,
      Provider<SiteStore> siteStoreProvider, Provider<PostUtilsWrapper> postUtilsProvider,
      Provider<UploadServiceFacade> uploadServiceProvider,
      Provider<SavePostToDbUseCase> savePostToDbUseCaseProvider,
      Provider<NetworkUtilsWrapper> networkUtilsProvider, Provider<Dispatcher> dispatcherProvider) {
    return new StorePostViewModel_Factory(uiCoroutineDispatcherProvider, siteStoreProvider, postUtilsProvider, uploadServiceProvider, savePostToDbUseCaseProvider, networkUtilsProvider, dispatcherProvider);
  }

  public static StorePostViewModel newInstance(CoroutineDispatcher uiCoroutineDispatcher,
      SiteStore siteStore, PostUtilsWrapper postUtils, UploadServiceFacade uploadService,
      SavePostToDbUseCase savePostToDbUseCase, NetworkUtilsWrapper networkUtils,
      Dispatcher dispatcher) {
    return new StorePostViewModel(uiCoroutineDispatcher, siteStore, postUtils, uploadService, savePostToDbUseCase, networkUtils, dispatcher);
  }
}
