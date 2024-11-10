package org.wordpress.android.ui.domains;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.fluxc.store.TransactionsStore;
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
public final class DomainRegistrationDetailsViewModel_Factory implements Factory<DomainRegistrationDetailsViewModel> {
  private final Provider<Dispatcher> dispatcherProvider;

  private final Provider<TransactionsStore> transactionsStoreProvider;

  private final Provider<SiteStore> siteStoreProvider;

  private final Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider;

  private final Provider<CoroutineDispatcher> uiDispatcherProvider;

  public DomainRegistrationDetailsViewModel_Factory(Provider<Dispatcher> dispatcherProvider,
      Provider<TransactionsStore> transactionsStoreProvider, Provider<SiteStore> siteStoreProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider,
      Provider<CoroutineDispatcher> uiDispatcherProvider) {
    this.dispatcherProvider = dispatcherProvider;
    this.transactionsStoreProvider = transactionsStoreProvider;
    this.siteStoreProvider = siteStoreProvider;
    this.analyticsTrackerProvider = analyticsTrackerProvider;
    this.uiDispatcherProvider = uiDispatcherProvider;
  }

  @Override
  public DomainRegistrationDetailsViewModel get() {
    return newInstance(dispatcherProvider.get(), transactionsStoreProvider.get(), siteStoreProvider.get(), analyticsTrackerProvider.get(), uiDispatcherProvider.get());
  }

  public static DomainRegistrationDetailsViewModel_Factory create(
      Provider<Dispatcher> dispatcherProvider,
      Provider<TransactionsStore> transactionsStoreProvider, Provider<SiteStore> siteStoreProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider,
      Provider<CoroutineDispatcher> uiDispatcherProvider) {
    return new DomainRegistrationDetailsViewModel_Factory(dispatcherProvider, transactionsStoreProvider, siteStoreProvider, analyticsTrackerProvider, uiDispatcherProvider);
  }

  public static DomainRegistrationDetailsViewModel newInstance(Dispatcher dispatcher,
      TransactionsStore transactionsStore, SiteStore siteStore,
      AnalyticsTrackerWrapper analyticsTracker, CoroutineDispatcher uiDispatcher) {
    return new DomainRegistrationDetailsViewModel(dispatcher, transactionsStore, siteStore, analyticsTracker, uiDispatcher);
  }
}
