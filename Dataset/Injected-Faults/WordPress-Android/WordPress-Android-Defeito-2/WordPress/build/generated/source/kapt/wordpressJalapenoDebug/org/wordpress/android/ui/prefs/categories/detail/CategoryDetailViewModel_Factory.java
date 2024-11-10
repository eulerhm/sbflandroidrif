package org.wordpress.android.ui.prefs.categories.detail;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.ui.mysite.SelectedSiteRepository;
import org.wordpress.android.ui.posts.AddCategoryUseCase;
import org.wordpress.android.ui.posts.GetCategoriesUseCase;
import org.wordpress.android.util.NetworkUtilsWrapper;
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
public final class CategoryDetailViewModel_Factory implements Factory<CategoryDetailViewModel> {
  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  private final Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider;

  private final Provider<GetCategoriesUseCase> getCategoriesUseCaseProvider;

  private final Provider<AddCategoryUseCase> addCategoryUseCaseProvider;

  private final Provider<ResourceProvider> resourceProvider;

  private final Provider<Dispatcher> dispatcherProvider;

  private final Provider<SelectedSiteRepository> selectedSiteRepositoryProvider;

  public CategoryDetailViewModel_Factory(Provider<CoroutineDispatcher> bgDispatcherProvider,
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider,
      Provider<GetCategoriesUseCase> getCategoriesUseCaseProvider,
      Provider<AddCategoryUseCase> addCategoryUseCaseProvider,
      Provider<ResourceProvider> resourceProvider, Provider<Dispatcher> dispatcherProvider,
      Provider<SelectedSiteRepository> selectedSiteRepositoryProvider) {
    this.bgDispatcherProvider = bgDispatcherProvider;
    this.networkUtilsWrapperProvider = networkUtilsWrapperProvider;
    this.getCategoriesUseCaseProvider = getCategoriesUseCaseProvider;
    this.addCategoryUseCaseProvider = addCategoryUseCaseProvider;
    this.resourceProvider = resourceProvider;
    this.dispatcherProvider = dispatcherProvider;
    this.selectedSiteRepositoryProvider = selectedSiteRepositoryProvider;
  }

  @Override
  public CategoryDetailViewModel get() {
    return newInstance(bgDispatcherProvider.get(), networkUtilsWrapperProvider.get(), getCategoriesUseCaseProvider.get(), addCategoryUseCaseProvider.get(), resourceProvider.get(), dispatcherProvider.get(), selectedSiteRepositoryProvider.get());
  }

  public static CategoryDetailViewModel_Factory create(
      Provider<CoroutineDispatcher> bgDispatcherProvider,
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider,
      Provider<GetCategoriesUseCase> getCategoriesUseCaseProvider,
      Provider<AddCategoryUseCase> addCategoryUseCaseProvider,
      Provider<ResourceProvider> resourceProvider, Provider<Dispatcher> dispatcherProvider,
      Provider<SelectedSiteRepository> selectedSiteRepositoryProvider) {
    return new CategoryDetailViewModel_Factory(bgDispatcherProvider, networkUtilsWrapperProvider, getCategoriesUseCaseProvider, addCategoryUseCaseProvider, resourceProvider, dispatcherProvider, selectedSiteRepositoryProvider);
  }

  public static CategoryDetailViewModel newInstance(CoroutineDispatcher bgDispatcher,
      NetworkUtilsWrapper networkUtilsWrapper, GetCategoriesUseCase getCategoriesUseCase,
      AddCategoryUseCase addCategoryUseCase, ResourceProvider resourceProvider,
      Dispatcher dispatcher, SelectedSiteRepository selectedSiteRepository) {
    return new CategoryDetailViewModel(bgDispatcher, networkUtilsWrapper, getCategoriesUseCase, addCategoryUseCase, resourceProvider, dispatcher, selectedSiteRepository);
  }
}
