package org.wordpress.android.viewmodel.pages;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.viewmodel.ResourceProvider;

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
public final class SearchListViewModel_Factory implements Factory<SearchListViewModel> {
  private final Provider<CreatePageListItemLabelsUseCase> createPageListItemLabelsUseCaseProvider;

  private final Provider<PostModelUploadUiStateUseCase> postModelUploadUiStateUseCaseProvider;

  private final Provider<CreatePageListItemActionsUseCase> pageListItemActionsUseCaseProvider;

  private final Provider<PageItemProgressUiStateUseCase> pageItemProgressUiStateUseCaseProvider;

  private final Provider<ResourceProvider> resourceProvider;

  private final Provider<CoroutineDispatcher> uiDispatcherProvider;

  public SearchListViewModel_Factory(
      Provider<CreatePageListItemLabelsUseCase> createPageListItemLabelsUseCaseProvider,
      Provider<PostModelUploadUiStateUseCase> postModelUploadUiStateUseCaseProvider,
      Provider<CreatePageListItemActionsUseCase> pageListItemActionsUseCaseProvider,
      Provider<PageItemProgressUiStateUseCase> pageItemProgressUiStateUseCaseProvider,
      Provider<ResourceProvider> resourceProvider,
      Provider<CoroutineDispatcher> uiDispatcherProvider) {
    this.createPageListItemLabelsUseCaseProvider = createPageListItemLabelsUseCaseProvider;
    this.postModelUploadUiStateUseCaseProvider = postModelUploadUiStateUseCaseProvider;
    this.pageListItemActionsUseCaseProvider = pageListItemActionsUseCaseProvider;
    this.pageItemProgressUiStateUseCaseProvider = pageItemProgressUiStateUseCaseProvider;
    this.resourceProvider = resourceProvider;
    this.uiDispatcherProvider = uiDispatcherProvider;
  }

  @Override
  public SearchListViewModel get() {
    return newInstance(createPageListItemLabelsUseCaseProvider.get(), postModelUploadUiStateUseCaseProvider.get(), pageListItemActionsUseCaseProvider.get(), pageItemProgressUiStateUseCaseProvider.get(), resourceProvider.get(), uiDispatcherProvider.get());
  }

  public static SearchListViewModel_Factory create(
      Provider<CreatePageListItemLabelsUseCase> createPageListItemLabelsUseCaseProvider,
      Provider<PostModelUploadUiStateUseCase> postModelUploadUiStateUseCaseProvider,
      Provider<CreatePageListItemActionsUseCase> pageListItemActionsUseCaseProvider,
      Provider<PageItemProgressUiStateUseCase> pageItemProgressUiStateUseCaseProvider,
      Provider<ResourceProvider> resourceProvider,
      Provider<CoroutineDispatcher> uiDispatcherProvider) {
    return new SearchListViewModel_Factory(createPageListItemLabelsUseCaseProvider, postModelUploadUiStateUseCaseProvider, pageListItemActionsUseCaseProvider, pageItemProgressUiStateUseCaseProvider, resourceProvider, uiDispatcherProvider);
  }

  public static SearchListViewModel newInstance(
      CreatePageListItemLabelsUseCase createPageListItemLabelsUseCase,
      PostModelUploadUiStateUseCase postModelUploadUiStateUseCase,
      CreatePageListItemActionsUseCase pageListItemActionsUseCase,
      PageItemProgressUiStateUseCase pageItemProgressUiStateUseCase,
      ResourceProvider resourceProvider, CoroutineDispatcher uiDispatcher) {
    return new SearchListViewModel(createPageListItemLabelsUseCase, postModelUploadUiStateUseCase, pageListItemActionsUseCase, pageItemProgressUiStateUseCase, resourceProvider, uiDispatcher);
  }
}
