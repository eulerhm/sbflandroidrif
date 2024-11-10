package org.wordpress.android.ui.prefs.categories.list;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.ui.posts.GetCategoriesUseCase;
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
public final class CategoriesListViewModel_Factory implements Factory<CategoriesListViewModel> {
  private final Provider<GetCategoriesUseCase> getCategoriesUseCaseProvider;

  private final Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider;

  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  private final Provider<Dispatcher> dispatcherProvider;

  public CategoriesListViewModel_Factory(
      Provider<GetCategoriesUseCase> getCategoriesUseCaseProvider,
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider, Provider<Dispatcher> dispatcherProvider) {
    this.getCategoriesUseCaseProvider = getCategoriesUseCaseProvider;
    this.networkUtilsWrapperProvider = networkUtilsWrapperProvider;
    this.bgDispatcherProvider = bgDispatcherProvider;
    this.dispatcherProvider = dispatcherProvider;
  }

  @Override
  public CategoriesListViewModel get() {
    return newInstance(getCategoriesUseCaseProvider.get(), networkUtilsWrapperProvider.get(), bgDispatcherProvider.get(), dispatcherProvider.get());
  }

  public static CategoriesListViewModel_Factory create(
      Provider<GetCategoriesUseCase> getCategoriesUseCaseProvider,
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider, Provider<Dispatcher> dispatcherProvider) {
    return new CategoriesListViewModel_Factory(getCategoriesUseCaseProvider, networkUtilsWrapperProvider, bgDispatcherProvider, dispatcherProvider);
  }

  public static CategoriesListViewModel newInstance(GetCategoriesUseCase getCategoriesUseCase,
      NetworkUtilsWrapper networkUtilsWrapper, CoroutineDispatcher bgDispatcher,
      Dispatcher dispatcher) {
    return new CategoriesListViewModel(getCategoriesUseCase, networkUtilsWrapper, bgDispatcher, dispatcher);
  }
}
