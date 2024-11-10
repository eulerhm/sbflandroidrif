package org.wordpress.android.ui.posts;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.store.TaxonomyStore;
import org.wordpress.android.fluxc.utils.AppLogWrapper;
import org.wordpress.android.models.wrappers.CategoryNodeWrapper;

@ScopeMetadata("dagger.Reusable")
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
public final class GetCategoriesUseCase_Factory implements Factory<GetCategoriesUseCase> {
  private final Provider<TaxonomyStore> taxonomyStoreProvider;

  private final Provider<Dispatcher> dispatcherProvider;

  private final Provider<AppLogWrapper> appLogWrapperProvider;

  private final Provider<CategoryNodeWrapper> categoryNodeWrapperProvider;

  public GetCategoriesUseCase_Factory(Provider<TaxonomyStore> taxonomyStoreProvider,
      Provider<Dispatcher> dispatcherProvider, Provider<AppLogWrapper> appLogWrapperProvider,
      Provider<CategoryNodeWrapper> categoryNodeWrapperProvider) {
    this.taxonomyStoreProvider = taxonomyStoreProvider;
    this.dispatcherProvider = dispatcherProvider;
    this.appLogWrapperProvider = appLogWrapperProvider;
    this.categoryNodeWrapperProvider = categoryNodeWrapperProvider;
  }

  @Override
  public GetCategoriesUseCase get() {
    return newInstance(taxonomyStoreProvider.get(), dispatcherProvider.get(), appLogWrapperProvider.get(), categoryNodeWrapperProvider.get());
  }

  public static GetCategoriesUseCase_Factory create(Provider<TaxonomyStore> taxonomyStoreProvider,
      Provider<Dispatcher> dispatcherProvider, Provider<AppLogWrapper> appLogWrapperProvider,
      Provider<CategoryNodeWrapper> categoryNodeWrapperProvider) {
    return new GetCategoriesUseCase_Factory(taxonomyStoreProvider, dispatcherProvider, appLogWrapperProvider, categoryNodeWrapperProvider);
  }

  public static GetCategoriesUseCase newInstance(TaxonomyStore taxonomyStore, Dispatcher dispatcher,
      AppLogWrapper appLogWrapper, CategoryNodeWrapper categoryNodeWrapper) {
    return new GetCategoriesUseCase(taxonomyStore, dispatcher, appLogWrapper, categoryNodeWrapper);
  }
}
