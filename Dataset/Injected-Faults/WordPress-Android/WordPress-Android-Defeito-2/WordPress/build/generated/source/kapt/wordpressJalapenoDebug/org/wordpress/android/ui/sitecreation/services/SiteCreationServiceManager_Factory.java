package org.wordpress.android.ui.sitecreation.services;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.ui.sitecreation.misc.SiteCreationTracker;
import org.wordpress.android.ui.sitecreation.usecases.CreateSiteUseCase;

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
public final class SiteCreationServiceManager_Factory implements Factory<SiteCreationServiceManager> {
  private final Provider<CreateSiteUseCase> createSiteUseCaseProvider;

  private final Provider<Dispatcher> dispatcherProvider;

  private final Provider<SiteCreationTracker> trackerProvider;

  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  public SiteCreationServiceManager_Factory(Provider<CreateSiteUseCase> createSiteUseCaseProvider,
      Provider<Dispatcher> dispatcherProvider, Provider<SiteCreationTracker> trackerProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider) {
    this.createSiteUseCaseProvider = createSiteUseCaseProvider;
    this.dispatcherProvider = dispatcherProvider;
    this.trackerProvider = trackerProvider;
    this.bgDispatcherProvider = bgDispatcherProvider;
  }

  @Override
  public SiteCreationServiceManager get() {
    return newInstance(createSiteUseCaseProvider.get(), dispatcherProvider.get(), trackerProvider.get(), bgDispatcherProvider.get());
  }

  public static SiteCreationServiceManager_Factory create(
      Provider<CreateSiteUseCase> createSiteUseCaseProvider,
      Provider<Dispatcher> dispatcherProvider, Provider<SiteCreationTracker> trackerProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider) {
    return new SiteCreationServiceManager_Factory(createSiteUseCaseProvider, dispatcherProvider, trackerProvider, bgDispatcherProvider);
  }

  public static SiteCreationServiceManager newInstance(CreateSiteUseCase createSiteUseCase,
      Dispatcher dispatcher, SiteCreationTracker tracker, CoroutineDispatcher bgDispatcher) {
    return new SiteCreationServiceManager(createSiteUseCase, dispatcher, tracker, bgDispatcher);
  }
}
