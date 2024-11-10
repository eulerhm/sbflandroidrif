package org.wordpress.android.ui.domains;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.ui.domains.usecases.FetchPlansUseCase;
import org.wordpress.android.ui.utils.HtmlMessageUtils;
import org.wordpress.android.util.analytics.AnalyticsTrackerWrapper;

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
public final class DomainsDashboardViewModel_Factory implements Factory<DomainsDashboardViewModel> {
  private final Provider<SiteStore> siteStoreProvider;

  private final Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider;

  private final Provider<HtmlMessageUtils> htmlMessageUtilsProvider;

  private final Provider<FetchPlansUseCase> fetchPlansUseCaseProvider;

  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  public DomainsDashboardViewModel_Factory(Provider<SiteStore> siteStoreProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider,
      Provider<HtmlMessageUtils> htmlMessageUtilsProvider,
      Provider<FetchPlansUseCase> fetchPlansUseCaseProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider) {
    this.siteStoreProvider = siteStoreProvider;
    this.analyticsTrackerWrapperProvider = analyticsTrackerWrapperProvider;
    this.htmlMessageUtilsProvider = htmlMessageUtilsProvider;
    this.fetchPlansUseCaseProvider = fetchPlansUseCaseProvider;
    this.bgDispatcherProvider = bgDispatcherProvider;
  }

  @Override
  public DomainsDashboardViewModel get() {
    return newInstance(siteStoreProvider.get(), analyticsTrackerWrapperProvider.get(), htmlMessageUtilsProvider.get(), fetchPlansUseCaseProvider.get(), bgDispatcherProvider.get());
  }

  public static DomainsDashboardViewModel_Factory create(Provider<SiteStore> siteStoreProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider,
      Provider<HtmlMessageUtils> htmlMessageUtilsProvider,
      Provider<FetchPlansUseCase> fetchPlansUseCaseProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider) {
    return new DomainsDashboardViewModel_Factory(siteStoreProvider, analyticsTrackerWrapperProvider, htmlMessageUtilsProvider, fetchPlansUseCaseProvider, bgDispatcherProvider);
  }

  public static DomainsDashboardViewModel newInstance(SiteStore siteStore,
      AnalyticsTrackerWrapper analyticsTrackerWrapper, HtmlMessageUtils htmlMessageUtils,
      FetchPlansUseCase fetchPlansUseCase, CoroutineDispatcher bgDispatcher) {
    return new DomainsDashboardViewModel(siteStore, analyticsTrackerWrapper, htmlMessageUtils, fetchPlansUseCase, bgDispatcher);
  }
}
