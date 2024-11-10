package org.wordpress.android.ui.sitecreation.domains;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.ui.sitecreation.misc.SiteCreationTracker;
import org.wordpress.android.ui.sitecreation.usecases.FetchDomainsUseCase;
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
public final class SiteCreationDomainsViewModel_Factory implements Factory<SiteCreationDomainsViewModel> {
  private final Provider<NetworkUtilsWrapper> networkUtilsProvider;

  private final Provider<Dispatcher> dispatcherProvider;

  private final Provider<SiteCreationDomainSanitizer> domainSanitizerProvider;

  private final Provider<FetchDomainsUseCase> fetchDomainsUseCaseProvider;

  private final Provider<SiteCreationTracker> trackerProvider;

  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  public SiteCreationDomainsViewModel_Factory(Provider<NetworkUtilsWrapper> networkUtilsProvider,
      Provider<Dispatcher> dispatcherProvider,
      Provider<SiteCreationDomainSanitizer> domainSanitizerProvider,
      Provider<FetchDomainsUseCase> fetchDomainsUseCaseProvider,
      Provider<SiteCreationTracker> trackerProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider,
      Provider<CoroutineDispatcher> mainDispatcherProvider) {
    this.networkUtilsProvider = networkUtilsProvider;
    this.dispatcherProvider = dispatcherProvider;
    this.domainSanitizerProvider = domainSanitizerProvider;
    this.fetchDomainsUseCaseProvider = fetchDomainsUseCaseProvider;
    this.trackerProvider = trackerProvider;
    this.bgDispatcherProvider = bgDispatcherProvider;
    this.mainDispatcherProvider = mainDispatcherProvider;
  }

  @Override
  public SiteCreationDomainsViewModel get() {
    return newInstance(networkUtilsProvider.get(), dispatcherProvider.get(), domainSanitizerProvider.get(), fetchDomainsUseCaseProvider.get(), trackerProvider.get(), bgDispatcherProvider.get(), mainDispatcherProvider.get());
  }

  public static SiteCreationDomainsViewModel_Factory create(
      Provider<NetworkUtilsWrapper> networkUtilsProvider, Provider<Dispatcher> dispatcherProvider,
      Provider<SiteCreationDomainSanitizer> domainSanitizerProvider,
      Provider<FetchDomainsUseCase> fetchDomainsUseCaseProvider,
      Provider<SiteCreationTracker> trackerProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider,
      Provider<CoroutineDispatcher> mainDispatcherProvider) {
    return new SiteCreationDomainsViewModel_Factory(networkUtilsProvider, dispatcherProvider, domainSanitizerProvider, fetchDomainsUseCaseProvider, trackerProvider, bgDispatcherProvider, mainDispatcherProvider);
  }

  public static SiteCreationDomainsViewModel newInstance(NetworkUtilsWrapper networkUtils,
      Dispatcher dispatcher, SiteCreationDomainSanitizer domainSanitizer,
      FetchDomainsUseCase fetchDomainsUseCase, SiteCreationTracker tracker,
      CoroutineDispatcher bgDispatcher, CoroutineDispatcher mainDispatcher) {
    return new SiteCreationDomainsViewModel(networkUtils, dispatcher, domainSanitizer, fetchDomainsUseCase, tracker, bgDispatcher, mainDispatcher);
  }
}
