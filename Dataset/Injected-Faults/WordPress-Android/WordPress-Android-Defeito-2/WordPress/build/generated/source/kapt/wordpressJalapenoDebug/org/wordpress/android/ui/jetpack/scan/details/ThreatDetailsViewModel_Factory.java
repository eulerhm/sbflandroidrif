package org.wordpress.android.ui.jetpack.scan.details;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.store.ScanStore;
import org.wordpress.android.ui.jetpack.scan.details.usecases.GetThreatModelUseCase;
import org.wordpress.android.ui.jetpack.scan.details.usecases.IgnoreThreatUseCase;
import org.wordpress.android.ui.jetpack.scan.usecases.FixThreatsUseCase;
import org.wordpress.android.ui.mysite.SelectedSiteRepository;
import org.wordpress.android.ui.utils.HtmlMessageUtils;
import org.wordpress.android.util.analytics.ScanTracker;
import org.wordpress.android.viewmodel.ResourceProvider;

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
public final class ThreatDetailsViewModel_Factory implements Factory<ThreatDetailsViewModel> {
  private final Provider<GetThreatModelUseCase> getThreatModelUseCaseProvider;

  private final Provider<IgnoreThreatUseCase> ignoreThreatUseCaseProvider;

  private final Provider<FixThreatsUseCase> fixThreatsUseCaseProvider;

  private final Provider<SelectedSiteRepository> selectedSiteRepositoryProvider;

  private final Provider<ScanStore> scanStoreProvider;

  private final Provider<ThreatDetailsListItemsBuilder> builderProvider;

  private final Provider<HtmlMessageUtils> htmlMessageUtilsProvider;

  private final Provider<ResourceProvider> resourceProvider;

  private final Provider<ScanTracker> scanTrackerProvider;

  public ThreatDetailsViewModel_Factory(
      Provider<GetThreatModelUseCase> getThreatModelUseCaseProvider,
      Provider<IgnoreThreatUseCase> ignoreThreatUseCaseProvider,
      Provider<FixThreatsUseCase> fixThreatsUseCaseProvider,
      Provider<SelectedSiteRepository> selectedSiteRepositoryProvider,
      Provider<ScanStore> scanStoreProvider,
      Provider<ThreatDetailsListItemsBuilder> builderProvider,
      Provider<HtmlMessageUtils> htmlMessageUtilsProvider,
      Provider<ResourceProvider> resourceProvider, Provider<ScanTracker> scanTrackerProvider) {
    this.getThreatModelUseCaseProvider = getThreatModelUseCaseProvider;
    this.ignoreThreatUseCaseProvider = ignoreThreatUseCaseProvider;
    this.fixThreatsUseCaseProvider = fixThreatsUseCaseProvider;
    this.selectedSiteRepositoryProvider = selectedSiteRepositoryProvider;
    this.scanStoreProvider = scanStoreProvider;
    this.builderProvider = builderProvider;
    this.htmlMessageUtilsProvider = htmlMessageUtilsProvider;
    this.resourceProvider = resourceProvider;
    this.scanTrackerProvider = scanTrackerProvider;
  }

  @Override
  public ThreatDetailsViewModel get() {
    return newInstance(getThreatModelUseCaseProvider.get(), ignoreThreatUseCaseProvider.get(), fixThreatsUseCaseProvider.get(), selectedSiteRepositoryProvider.get(), scanStoreProvider.get(), builderProvider.get(), htmlMessageUtilsProvider.get(), resourceProvider.get(), scanTrackerProvider.get());
  }

  public static ThreatDetailsViewModel_Factory create(
      Provider<GetThreatModelUseCase> getThreatModelUseCaseProvider,
      Provider<IgnoreThreatUseCase> ignoreThreatUseCaseProvider,
      Provider<FixThreatsUseCase> fixThreatsUseCaseProvider,
      Provider<SelectedSiteRepository> selectedSiteRepositoryProvider,
      Provider<ScanStore> scanStoreProvider,
      Provider<ThreatDetailsListItemsBuilder> builderProvider,
      Provider<HtmlMessageUtils> htmlMessageUtilsProvider,
      Provider<ResourceProvider> resourceProvider, Provider<ScanTracker> scanTrackerProvider) {
    return new ThreatDetailsViewModel_Factory(getThreatModelUseCaseProvider, ignoreThreatUseCaseProvider, fixThreatsUseCaseProvider, selectedSiteRepositoryProvider, scanStoreProvider, builderProvider, htmlMessageUtilsProvider, resourceProvider, scanTrackerProvider);
  }

  public static ThreatDetailsViewModel newInstance(GetThreatModelUseCase getThreatModelUseCase,
      IgnoreThreatUseCase ignoreThreatUseCase, FixThreatsUseCase fixThreatsUseCase,
      SelectedSiteRepository selectedSiteRepository, ScanStore scanStore,
      ThreatDetailsListItemsBuilder builder, HtmlMessageUtils htmlMessageUtils,
      ResourceProvider resourceProvider, ScanTracker scanTracker) {
    return new ThreatDetailsViewModel(getThreatModelUseCase, ignoreThreatUseCase, fixThreatsUseCase, selectedSiteRepository, scanStore, builder, htmlMessageUtils, resourceProvider, scanTracker);
  }
}
