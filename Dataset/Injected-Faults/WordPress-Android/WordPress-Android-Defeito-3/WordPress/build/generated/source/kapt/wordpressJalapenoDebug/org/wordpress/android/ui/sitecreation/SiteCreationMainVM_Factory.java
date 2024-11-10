package org.wordpress.android.ui.sitecreation;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.ui.sitecreation.misc.SiteCreationTracker;
import org.wordpress.android.ui.sitecreation.usecases.FetchHomePageLayoutsUseCase;
import org.wordpress.android.util.NetworkUtilsWrapper;
import org.wordpress.android.util.image.ImageManager;
import org.wordpress.android.util.wizard.WizardManager;

@ScopeMetadata
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
public final class SiteCreationMainVM_Factory implements Factory<SiteCreationMainVM> {
  private final Provider<SiteCreationTracker> trackerProvider;

  private final Provider<WizardManager<SiteCreationStep>> wizardManagerProvider;

  private final Provider<NetworkUtilsWrapper> networkUtilsProvider;

  private final Provider<Dispatcher> dispatcherProvider;

  private final Provider<FetchHomePageLayoutsUseCase> fetchHomePageLayoutsUseCaseProvider;

  private final Provider<ImageManager> imageManagerProvider;

  public SiteCreationMainVM_Factory(Provider<SiteCreationTracker> trackerProvider,
      Provider<WizardManager<SiteCreationStep>> wizardManagerProvider,
      Provider<NetworkUtilsWrapper> networkUtilsProvider, Provider<Dispatcher> dispatcherProvider,
      Provider<FetchHomePageLayoutsUseCase> fetchHomePageLayoutsUseCaseProvider,
      Provider<ImageManager> imageManagerProvider) {
    this.trackerProvider = trackerProvider;
    this.wizardManagerProvider = wizardManagerProvider;
    this.networkUtilsProvider = networkUtilsProvider;
    this.dispatcherProvider = dispatcherProvider;
    this.fetchHomePageLayoutsUseCaseProvider = fetchHomePageLayoutsUseCaseProvider;
    this.imageManagerProvider = imageManagerProvider;
  }

  @Override
  public SiteCreationMainVM get() {
    return newInstance(trackerProvider.get(), wizardManagerProvider.get(), networkUtilsProvider.get(), dispatcherProvider.get(), fetchHomePageLayoutsUseCaseProvider.get(), imageManagerProvider.get());
  }

  public static SiteCreationMainVM_Factory create(Provider<SiteCreationTracker> trackerProvider,
      Provider<WizardManager<SiteCreationStep>> wizardManagerProvider,
      Provider<NetworkUtilsWrapper> networkUtilsProvider, Provider<Dispatcher> dispatcherProvider,
      Provider<FetchHomePageLayoutsUseCase> fetchHomePageLayoutsUseCaseProvider,
      Provider<ImageManager> imageManagerProvider) {
    return new SiteCreationMainVM_Factory(trackerProvider, wizardManagerProvider, networkUtilsProvider, dispatcherProvider, fetchHomePageLayoutsUseCaseProvider, imageManagerProvider);
  }

  public static SiteCreationMainVM newInstance(SiteCreationTracker tracker,
      WizardManager<SiteCreationStep> wizardManager, NetworkUtilsWrapper networkUtils,
      Dispatcher dispatcher, FetchHomePageLayoutsUseCase fetchHomePageLayoutsUseCase,
      ImageManager imageManager) {
    return new SiteCreationMainVM(tracker, wizardManager, networkUtils, dispatcher, fetchHomePageLayoutsUseCase, imageManager);
  }
}
