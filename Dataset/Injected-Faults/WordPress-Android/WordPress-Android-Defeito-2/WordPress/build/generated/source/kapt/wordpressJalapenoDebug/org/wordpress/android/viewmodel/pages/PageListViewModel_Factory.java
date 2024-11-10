package org.wordpress.android.viewmodel.pages;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.fluxc.store.MediaStore;
import org.wordpress.android.util.LocaleManagerWrapper;

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
public final class PageListViewModel_Factory implements Factory<PageListViewModel> {
  private final Provider<CreatePageListItemLabelsUseCase> createPageListItemLabelsUseCaseProvider;

  private final Provider<PostModelUploadUiStateUseCase> postModelUploadUiStateUseCaseProvider;

  private final Provider<CreatePageListItemActionsUseCase> pageListItemActionsUseCaseProvider;

  private final Provider<PageItemProgressUiStateUseCase> pageItemProgressUiStateUseCaseProvider;

  private final Provider<MediaStore> mediaStoreProvider;

  private final Provider<Dispatcher> dispatcherProvider;

  private final Provider<LocaleManagerWrapper> localeManagerWrapperProvider;

  private final Provider<AccountStore> accountStoreProvider;

  private final Provider<CoroutineDispatcher> coroutineDispatcherProvider;

  public PageListViewModel_Factory(
      Provider<CreatePageListItemLabelsUseCase> createPageListItemLabelsUseCaseProvider,
      Provider<PostModelUploadUiStateUseCase> postModelUploadUiStateUseCaseProvider,
      Provider<CreatePageListItemActionsUseCase> pageListItemActionsUseCaseProvider,
      Provider<PageItemProgressUiStateUseCase> pageItemProgressUiStateUseCaseProvider,
      Provider<MediaStore> mediaStoreProvider, Provider<Dispatcher> dispatcherProvider,
      Provider<LocaleManagerWrapper> localeManagerWrapperProvider,
      Provider<AccountStore> accountStoreProvider,
      Provider<CoroutineDispatcher> coroutineDispatcherProvider) {
    this.createPageListItemLabelsUseCaseProvider = createPageListItemLabelsUseCaseProvider;
    this.postModelUploadUiStateUseCaseProvider = postModelUploadUiStateUseCaseProvider;
    this.pageListItemActionsUseCaseProvider = pageListItemActionsUseCaseProvider;
    this.pageItemProgressUiStateUseCaseProvider = pageItemProgressUiStateUseCaseProvider;
    this.mediaStoreProvider = mediaStoreProvider;
    this.dispatcherProvider = dispatcherProvider;
    this.localeManagerWrapperProvider = localeManagerWrapperProvider;
    this.accountStoreProvider = accountStoreProvider;
    this.coroutineDispatcherProvider = coroutineDispatcherProvider;
  }

  @Override
  public PageListViewModel get() {
    return newInstance(createPageListItemLabelsUseCaseProvider.get(), postModelUploadUiStateUseCaseProvider.get(), pageListItemActionsUseCaseProvider.get(), pageItemProgressUiStateUseCaseProvider.get(), mediaStoreProvider.get(), dispatcherProvider.get(), localeManagerWrapperProvider.get(), accountStoreProvider.get(), coroutineDispatcherProvider.get());
  }

  public static PageListViewModel_Factory create(
      Provider<CreatePageListItemLabelsUseCase> createPageListItemLabelsUseCaseProvider,
      Provider<PostModelUploadUiStateUseCase> postModelUploadUiStateUseCaseProvider,
      Provider<CreatePageListItemActionsUseCase> pageListItemActionsUseCaseProvider,
      Provider<PageItemProgressUiStateUseCase> pageItemProgressUiStateUseCaseProvider,
      Provider<MediaStore> mediaStoreProvider, Provider<Dispatcher> dispatcherProvider,
      Provider<LocaleManagerWrapper> localeManagerWrapperProvider,
      Provider<AccountStore> accountStoreProvider,
      Provider<CoroutineDispatcher> coroutineDispatcherProvider) {
    return new PageListViewModel_Factory(createPageListItemLabelsUseCaseProvider, postModelUploadUiStateUseCaseProvider, pageListItemActionsUseCaseProvider, pageItemProgressUiStateUseCaseProvider, mediaStoreProvider, dispatcherProvider, localeManagerWrapperProvider, accountStoreProvider, coroutineDispatcherProvider);
  }

  public static PageListViewModel newInstance(
      CreatePageListItemLabelsUseCase createPageListItemLabelsUseCase,
      PostModelUploadUiStateUseCase postModelUploadUiStateUseCase,
      CreatePageListItemActionsUseCase pageListItemActionsUseCase,
      PageItemProgressUiStateUseCase pageItemProgressUiStateUseCase, MediaStore mediaStore,
      Dispatcher dispatcher, LocaleManagerWrapper localeManagerWrapper, AccountStore accountStore,
      CoroutineDispatcher coroutineDispatcher) {
    return new PageListViewModel(createPageListItemLabelsUseCase, postModelUploadUiStateUseCase, pageListItemActionsUseCase, pageItemProgressUiStateUseCase, mediaStore, dispatcher, localeManagerWrapper, accountStore, coroutineDispatcher);
  }
}
