package org.wordpress.android.ui.domains;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.store.ProductsStore;
import org.wordpress.android.ui.domains.usecases.CreateCartUseCase;
import org.wordpress.android.util.analytics.AnalyticsTrackerWrapper;
import org.wordpress.android.util.config.SiteDomainsFeatureConfig;
import org.wordpress.android.util.helpers.Debouncer;

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
public final class DomainSuggestionsViewModel_Factory implements Factory<DomainSuggestionsViewModel> {
  private final Provider<ProductsStore> productsStoreProvider;

  private final Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider;

  private final Provider<Dispatcher> dispatcherProvider;

  private final Provider<Debouncer> debouncerProvider;

  private final Provider<SiteDomainsFeatureConfig> siteDomainsFeatureConfigProvider;

  private final Provider<CreateCartUseCase> createCartUseCaseProvider;

  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  public DomainSuggestionsViewModel_Factory(Provider<ProductsStore> productsStoreProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider,
      Provider<Dispatcher> dispatcherProvider, Provider<Debouncer> debouncerProvider,
      Provider<SiteDomainsFeatureConfig> siteDomainsFeatureConfigProvider,
      Provider<CreateCartUseCase> createCartUseCaseProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider) {
    this.productsStoreProvider = productsStoreProvider;
    this.analyticsTrackerProvider = analyticsTrackerProvider;
    this.dispatcherProvider = dispatcherProvider;
    this.debouncerProvider = debouncerProvider;
    this.siteDomainsFeatureConfigProvider = siteDomainsFeatureConfigProvider;
    this.createCartUseCaseProvider = createCartUseCaseProvider;
    this.bgDispatcherProvider = bgDispatcherProvider;
  }

  @Override
  public DomainSuggestionsViewModel get() {
    return newInstance(productsStoreProvider.get(), analyticsTrackerProvider.get(), dispatcherProvider.get(), debouncerProvider.get(), siteDomainsFeatureConfigProvider.get(), createCartUseCaseProvider.get(), bgDispatcherProvider.get());
  }

  public static DomainSuggestionsViewModel_Factory create(
      Provider<ProductsStore> productsStoreProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider,
      Provider<Dispatcher> dispatcherProvider, Provider<Debouncer> debouncerProvider,
      Provider<SiteDomainsFeatureConfig> siteDomainsFeatureConfigProvider,
      Provider<CreateCartUseCase> createCartUseCaseProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider) {
    return new DomainSuggestionsViewModel_Factory(productsStoreProvider, analyticsTrackerProvider, dispatcherProvider, debouncerProvider, siteDomainsFeatureConfigProvider, createCartUseCaseProvider, bgDispatcherProvider);
  }

  public static DomainSuggestionsViewModel newInstance(ProductsStore productsStore,
      AnalyticsTrackerWrapper analyticsTracker, Dispatcher dispatcher, Debouncer debouncer,
      SiteDomainsFeatureConfig siteDomainsFeatureConfig, CreateCartUseCase createCartUseCase,
      CoroutineDispatcher bgDispatcher) {
    return new DomainSuggestionsViewModel(productsStore, analyticsTracker, dispatcher, debouncer, siteDomainsFeatureConfig, createCartUseCase, bgDispatcher);
  }
}
