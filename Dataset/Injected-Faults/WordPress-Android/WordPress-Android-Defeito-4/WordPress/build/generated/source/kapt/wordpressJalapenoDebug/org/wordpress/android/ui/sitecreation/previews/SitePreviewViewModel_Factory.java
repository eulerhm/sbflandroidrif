package org.wordpress.android.ui.sitecreation.previews;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.store.QuickStartStore;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.ui.sitecreation.misc.SiteCreationTracker;
import org.wordpress.android.ui.sitecreation.services.FetchWpComSiteUseCase;
import org.wordpress.android.util.NetworkUtilsWrapper;
import org.wordpress.android.util.UrlUtilsWrapper;

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
public final class SitePreviewViewModel_Factory implements Factory<SitePreviewViewModel> {
  private final Provider<Dispatcher> dispatcherProvider;

  private final Provider<SiteStore> siteStoreProvider;

  private final Provider<QuickStartStore> quickStartStoreProvider;

  private final Provider<FetchWpComSiteUseCase> fetchWpComSiteUseCaseProvider;

  private final Provider<NetworkUtilsWrapper> networkUtilsProvider;

  private final Provider<UrlUtilsWrapper> urlUtilsProvider;

  private final Provider<SiteCreationTracker> trackerProvider;

  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  public SitePreviewViewModel_Factory(Provider<Dispatcher> dispatcherProvider,
      Provider<SiteStore> siteStoreProvider, Provider<QuickStartStore> quickStartStoreProvider,
      Provider<FetchWpComSiteUseCase> fetchWpComSiteUseCaseProvider,
      Provider<NetworkUtilsWrapper> networkUtilsProvider,
      Provider<UrlUtilsWrapper> urlUtilsProvider, Provider<SiteCreationTracker> trackerProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider,
      Provider<CoroutineDispatcher> mainDispatcherProvider) {
    this.dispatcherProvider = dispatcherProvider;
    this.siteStoreProvider = siteStoreProvider;
    this.quickStartStoreProvider = quickStartStoreProvider;
    this.fetchWpComSiteUseCaseProvider = fetchWpComSiteUseCaseProvider;
    this.networkUtilsProvider = networkUtilsProvider;
    this.urlUtilsProvider = urlUtilsProvider;
    this.trackerProvider = trackerProvider;
    this.bgDispatcherProvider = bgDispatcherProvider;
    this.mainDispatcherProvider = mainDispatcherProvider;
  }

  @Override
  public SitePreviewViewModel get() {
    return newInstance(dispatcherProvider.get(), siteStoreProvider.get(), quickStartStoreProvider.get(), fetchWpComSiteUseCaseProvider.get(), networkUtilsProvider.get(), urlUtilsProvider.get(), trackerProvider.get(), bgDispatcherProvider.get(), mainDispatcherProvider.get());
  }

  public static SitePreviewViewModel_Factory create(Provider<Dispatcher> dispatcherProvider,
      Provider<SiteStore> siteStoreProvider, Provider<QuickStartStore> quickStartStoreProvider,
      Provider<FetchWpComSiteUseCase> fetchWpComSiteUseCaseProvider,
      Provider<NetworkUtilsWrapper> networkUtilsProvider,
      Provider<UrlUtilsWrapper> urlUtilsProvider, Provider<SiteCreationTracker> trackerProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider,
      Provider<CoroutineDispatcher> mainDispatcherProvider) {
    return new SitePreviewViewModel_Factory(dispatcherProvider, siteStoreProvider, quickStartStoreProvider, fetchWpComSiteUseCaseProvider, networkUtilsProvider, urlUtilsProvider, trackerProvider, bgDispatcherProvider, mainDispatcherProvider);
  }

  public static SitePreviewViewModel newInstance(Dispatcher dispatcher, SiteStore siteStore,
      QuickStartStore quickStartStore, FetchWpComSiteUseCase fetchWpComSiteUseCase,
      NetworkUtilsWrapper networkUtils, UrlUtilsWrapper urlUtils, SiteCreationTracker tracker,
      CoroutineDispatcher bgDispatcher, CoroutineDispatcher mainDispatcher) {
    return new SitePreviewViewModel(dispatcher, siteStore, quickStartStore, fetchWpComSiteUseCase, networkUtils, urlUtils, tracker, bgDispatcher, mainDispatcher);
  }
}
