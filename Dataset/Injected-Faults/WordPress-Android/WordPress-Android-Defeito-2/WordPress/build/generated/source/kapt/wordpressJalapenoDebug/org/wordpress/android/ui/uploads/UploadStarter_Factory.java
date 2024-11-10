package org.wordpress.android.ui.uploads;

import android.content.Context;
import androidx.lifecycle.LiveData;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.store.PageStore;
import org.wordpress.android.fluxc.store.PostStore;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.util.NetworkUtilsWrapper;
import org.wordpress.android.util.analytics.AnalyticsTrackerWrapper;
import org.wordpress.android.viewmodel.helpers.ConnectionStatus;

@ScopeMetadata("javax.inject.Singleton")
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
public final class UploadStarter_Factory implements Factory<UploadStarter> {
  private final Provider<Context> contextProvider;

  private final Provider<Dispatcher> dispatcherProvider;

  private final Provider<PostStore> postStoreProvider;

  private final Provider<PageStore> pageStoreProvider;

  private final Provider<SiteStore> siteStoreProvider;

  private final Provider<UploadActionUseCase> uploadActionUseCaseProvider;

  private final Provider<AnalyticsTrackerWrapper> trackerProvider;

  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  private final Provider<CoroutineDispatcher> ioDispatcherProvider;

  private final Provider<UploadServiceFacade> uploadServiceFacadeProvider;

  private final Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider;

  private final Provider<LiveData<ConnectionStatus>> connectionStatusProvider;

  public UploadStarter_Factory(Provider<Context> contextProvider,
      Provider<Dispatcher> dispatcherProvider, Provider<PostStore> postStoreProvider,
      Provider<PageStore> pageStoreProvider, Provider<SiteStore> siteStoreProvider,
      Provider<UploadActionUseCase> uploadActionUseCaseProvider,
      Provider<AnalyticsTrackerWrapper> trackerProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider,
      Provider<CoroutineDispatcher> ioDispatcherProvider,
      Provider<UploadServiceFacade> uploadServiceFacadeProvider,
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider,
      Provider<LiveData<ConnectionStatus>> connectionStatusProvider) {
    this.contextProvider = contextProvider;
    this.dispatcherProvider = dispatcherProvider;
    this.postStoreProvider = postStoreProvider;
    this.pageStoreProvider = pageStoreProvider;
    this.siteStoreProvider = siteStoreProvider;
    this.uploadActionUseCaseProvider = uploadActionUseCaseProvider;
    this.trackerProvider = trackerProvider;
    this.bgDispatcherProvider = bgDispatcherProvider;
    this.ioDispatcherProvider = ioDispatcherProvider;
    this.uploadServiceFacadeProvider = uploadServiceFacadeProvider;
    this.networkUtilsWrapperProvider = networkUtilsWrapperProvider;
    this.connectionStatusProvider = connectionStatusProvider;
  }

  @Override
  public UploadStarter get() {
    return newInstance(contextProvider.get(), dispatcherProvider.get(), postStoreProvider.get(), pageStoreProvider.get(), siteStoreProvider.get(), uploadActionUseCaseProvider.get(), trackerProvider.get(), bgDispatcherProvider.get(), ioDispatcherProvider.get(), uploadServiceFacadeProvider.get(), networkUtilsWrapperProvider.get(), connectionStatusProvider.get());
  }

  public static UploadStarter_Factory create(Provider<Context> contextProvider,
      Provider<Dispatcher> dispatcherProvider, Provider<PostStore> postStoreProvider,
      Provider<PageStore> pageStoreProvider, Provider<SiteStore> siteStoreProvider,
      Provider<UploadActionUseCase> uploadActionUseCaseProvider,
      Provider<AnalyticsTrackerWrapper> trackerProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider,
      Provider<CoroutineDispatcher> ioDispatcherProvider,
      Provider<UploadServiceFacade> uploadServiceFacadeProvider,
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider,
      Provider<LiveData<ConnectionStatus>> connectionStatusProvider) {
    return new UploadStarter_Factory(contextProvider, dispatcherProvider, postStoreProvider, pageStoreProvider, siteStoreProvider, uploadActionUseCaseProvider, trackerProvider, bgDispatcherProvider, ioDispatcherProvider, uploadServiceFacadeProvider, networkUtilsWrapperProvider, connectionStatusProvider);
  }

  public static UploadStarter newInstance(Context context, Dispatcher dispatcher,
      PostStore postStore, PageStore pageStore, SiteStore siteStore,
      UploadActionUseCase uploadActionUseCase, AnalyticsTrackerWrapper tracker,
      CoroutineDispatcher bgDispatcher, CoroutineDispatcher ioDispatcher,
      UploadServiceFacade uploadServiceFacade, NetworkUtilsWrapper networkUtilsWrapper,
      LiveData<ConnectionStatus> connectionStatus) {
    return new UploadStarter(context, dispatcher, postStore, pageStore, siteStore, uploadActionUseCase, tracker, bgDispatcher, ioDispatcher, uploadServiceFacade, networkUtilsWrapper, connectionStatus);
  }
}
