package org.wordpress.android.ui.posts;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
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
public final class PrepublishingAddCategoryViewModel_Factory implements Factory<PrepublishingAddCategoryViewModel> {
  private final Provider<GetCategoriesUseCase> getCategoriesUseCaseProvider;

  private final Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider;

  private final Provider<ResourceProvider> resourceProvider;

  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  public PrepublishingAddCategoryViewModel_Factory(
      Provider<GetCategoriesUseCase> getCategoriesUseCaseProvider,
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider,
      Provider<ResourceProvider> resourceProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider) {
    this.getCategoriesUseCaseProvider = getCategoriesUseCaseProvider;
    this.networkUtilsWrapperProvider = networkUtilsWrapperProvider;
    this.resourceProvider = resourceProvider;
    this.bgDispatcherProvider = bgDispatcherProvider;
  }

  @Override
  public PrepublishingAddCategoryViewModel get() {
    return newInstance(getCategoriesUseCaseProvider.get(), networkUtilsWrapperProvider.get(), resourceProvider.get(), bgDispatcherProvider.get());
  }

  public static PrepublishingAddCategoryViewModel_Factory create(
      Provider<GetCategoriesUseCase> getCategoriesUseCaseProvider,
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider,
      Provider<ResourceProvider> resourceProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider) {
    return new PrepublishingAddCategoryViewModel_Factory(getCategoriesUseCaseProvider, networkUtilsWrapperProvider, resourceProvider, bgDispatcherProvider);
  }

  public static PrepublishingAddCategoryViewModel newInstance(
      GetCategoriesUseCase getCategoriesUseCase, NetworkUtilsWrapper networkUtilsWrapper,
      ResourceProvider resourceProvider, CoroutineDispatcher bgDispatcher) {
    return new PrepublishingAddCategoryViewModel(getCategoriesUseCase, networkUtilsWrapper, resourceProvider, bgDispatcher);
  }
}
