package org.wordpress.android.ui.plans;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.store.PlanOffersStore;
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
public final class PlansViewModel_Factory implements Factory<PlansViewModel> {
  private final Provider<Dispatcher> dispatcherProvider;

  private final Provider<PlanOffersStore> plansStoreProvider;

  private final Provider<CoroutineDispatcher> uiDispatcherProvider;

  private final Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider;

  public PlansViewModel_Factory(Provider<Dispatcher> dispatcherProvider,
      Provider<PlanOffersStore> plansStoreProvider,
      Provider<CoroutineDispatcher> uiDispatcherProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider) {
    this.dispatcherProvider = dispatcherProvider;
    this.plansStoreProvider = plansStoreProvider;
    this.uiDispatcherProvider = uiDispatcherProvider;
    this.analyticsTrackerWrapperProvider = analyticsTrackerWrapperProvider;
  }

  @Override
  public PlansViewModel get() {
    return newInstance(dispatcherProvider.get(), plansStoreProvider.get(), uiDispatcherProvider.get(), analyticsTrackerWrapperProvider.get());
  }

  public static PlansViewModel_Factory create(Provider<Dispatcher> dispatcherProvider,
      Provider<PlanOffersStore> plansStoreProvider,
      Provider<CoroutineDispatcher> uiDispatcherProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider) {
    return new PlansViewModel_Factory(dispatcherProvider, plansStoreProvider, uiDispatcherProvider, analyticsTrackerWrapperProvider);
  }

  public static PlansViewModel newInstance(Dispatcher dispatcher, PlanOffersStore plansStore,
      CoroutineDispatcher uiDispatcher, AnalyticsTrackerWrapper analyticsTrackerWrapper) {
    return new PlansViewModel(dispatcher, plansStore, uiDispatcher, analyticsTrackerWrapper);
  }
}
