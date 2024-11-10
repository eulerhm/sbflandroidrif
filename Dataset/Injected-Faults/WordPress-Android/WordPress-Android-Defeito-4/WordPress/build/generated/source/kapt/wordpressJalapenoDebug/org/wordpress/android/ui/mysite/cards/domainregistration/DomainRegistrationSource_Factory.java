package org.wordpress.android.ui.mysite.cards.domainregistration;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.utils.AppLogWrapper;
import org.wordpress.android.ui.mysite.SelectedSiteRepository;
import org.wordpress.android.util.SiteUtilsWrapper;

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
public final class DomainRegistrationSource_Factory implements Factory<DomainRegistrationSource> {
  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  private final Provider<Dispatcher> dispatcherProvider;

  private final Provider<SelectedSiteRepository> selectedSiteRepositoryProvider;

  private final Provider<AppLogWrapper> appLogWrapperProvider;

  private final Provider<SiteUtilsWrapper> siteUtilsProvider;

  public DomainRegistrationSource_Factory(Provider<CoroutineDispatcher> bgDispatcherProvider,
      Provider<Dispatcher> dispatcherProvider,
      Provider<SelectedSiteRepository> selectedSiteRepositoryProvider,
      Provider<AppLogWrapper> appLogWrapperProvider, Provider<SiteUtilsWrapper> siteUtilsProvider) {
    this.bgDispatcherProvider = bgDispatcherProvider;
    this.dispatcherProvider = dispatcherProvider;
    this.selectedSiteRepositoryProvider = selectedSiteRepositoryProvider;
    this.appLogWrapperProvider = appLogWrapperProvider;
    this.siteUtilsProvider = siteUtilsProvider;
  }

  @Override
  public DomainRegistrationSource get() {
    return newInstance(bgDispatcherProvider.get(), dispatcherProvider.get(), selectedSiteRepositoryProvider.get(), appLogWrapperProvider.get(), siteUtilsProvider.get());
  }

  public static DomainRegistrationSource_Factory create(
      Provider<CoroutineDispatcher> bgDispatcherProvider, Provider<Dispatcher> dispatcherProvider,
      Provider<SelectedSiteRepository> selectedSiteRepositoryProvider,
      Provider<AppLogWrapper> appLogWrapperProvider, Provider<SiteUtilsWrapper> siteUtilsProvider) {
    return new DomainRegistrationSource_Factory(bgDispatcherProvider, dispatcherProvider, selectedSiteRepositoryProvider, appLogWrapperProvider, siteUtilsProvider);
  }

  public static DomainRegistrationSource newInstance(CoroutineDispatcher bgDispatcher,
      Dispatcher dispatcher, SelectedSiteRepository selectedSiteRepository,
      AppLogWrapper appLogWrapper, SiteUtilsWrapper siteUtils) {
    return new DomainRegistrationSource(bgDispatcher, dispatcher, selectedSiteRepository, appLogWrapper, siteUtils);
  }
}
