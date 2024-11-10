package org.wordpress.android.viewmodel.mlp;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.ui.mlp.ModalLayoutPickerDimensionProvider;
import org.wordpress.android.ui.mlp.ModalLayoutPickerTracker;
import org.wordpress.android.ui.mlp.SupportedBlocksProvider;
import org.wordpress.android.ui.mysite.SelectedSiteRepository;
import org.wordpress.android.util.DisplayUtilsWrapper;
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
public final class ModalLayoutPickerViewModel_Factory implements Factory<ModalLayoutPickerViewModel> {
  private final Provider<Dispatcher> dispatcherProvider;

  private final Provider<SiteStore> siteStoreProvider;

  private final Provider<SelectedSiteRepository> selectedSiteRepositoryProvider;

  private final Provider<SupportedBlocksProvider> supportedBlocksProvider;

  private final Provider<ModalLayoutPickerDimensionProvider> thumbDimensionProvider;

  private final Provider<DisplayUtilsWrapper> displayUtilsWrapperProvider;

  private final Provider<NetworkUtilsWrapper> networkUtilsProvider;

  private final Provider<ModalLayoutPickerTracker> analyticsTrackerProvider;

  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  public ModalLayoutPickerViewModel_Factory(Provider<Dispatcher> dispatcherProvider,
      Provider<SiteStore> siteStoreProvider,
      Provider<SelectedSiteRepository> selectedSiteRepositoryProvider,
      Provider<SupportedBlocksProvider> supportedBlocksProvider,
      Provider<ModalLayoutPickerDimensionProvider> thumbDimensionProvider,
      Provider<DisplayUtilsWrapper> displayUtilsWrapperProvider,
      Provider<NetworkUtilsWrapper> networkUtilsProvider,
      Provider<ModalLayoutPickerTracker> analyticsTrackerProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider,
      Provider<CoroutineDispatcher> mainDispatcherProvider) {
    this.dispatcherProvider = dispatcherProvider;
    this.siteStoreProvider = siteStoreProvider;
    this.selectedSiteRepositoryProvider = selectedSiteRepositoryProvider;
    this.supportedBlocksProvider = supportedBlocksProvider;
    this.thumbDimensionProvider = thumbDimensionProvider;
    this.displayUtilsWrapperProvider = displayUtilsWrapperProvider;
    this.networkUtilsProvider = networkUtilsProvider;
    this.analyticsTrackerProvider = analyticsTrackerProvider;
    this.bgDispatcherProvider = bgDispatcherProvider;
    this.mainDispatcherProvider = mainDispatcherProvider;
  }

  @Override
  public ModalLayoutPickerViewModel get() {
    return newInstance(dispatcherProvider.get(), siteStoreProvider.get(), selectedSiteRepositoryProvider.get(), supportedBlocksProvider.get(), thumbDimensionProvider.get(), displayUtilsWrapperProvider.get(), networkUtilsProvider.get(), analyticsTrackerProvider.get(), bgDispatcherProvider.get(), mainDispatcherProvider.get());
  }

  public static ModalLayoutPickerViewModel_Factory create(Provider<Dispatcher> dispatcherProvider,
      Provider<SiteStore> siteStoreProvider,
      Provider<SelectedSiteRepository> selectedSiteRepositoryProvider,
      Provider<SupportedBlocksProvider> supportedBlocksProvider,
      Provider<ModalLayoutPickerDimensionProvider> thumbDimensionProvider,
      Provider<DisplayUtilsWrapper> displayUtilsWrapperProvider,
      Provider<NetworkUtilsWrapper> networkUtilsProvider,
      Provider<ModalLayoutPickerTracker> analyticsTrackerProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider,
      Provider<CoroutineDispatcher> mainDispatcherProvider) {
    return new ModalLayoutPickerViewModel_Factory(dispatcherProvider, siteStoreProvider, selectedSiteRepositoryProvider, supportedBlocksProvider, thumbDimensionProvider, displayUtilsWrapperProvider, networkUtilsProvider, analyticsTrackerProvider, bgDispatcherProvider, mainDispatcherProvider);
  }

  public static ModalLayoutPickerViewModel newInstance(Dispatcher dispatcher, SiteStore siteStore,
      SelectedSiteRepository selectedSiteRepository,
      SupportedBlocksProvider supportedBlocksProvider,
      ModalLayoutPickerDimensionProvider thumbDimensionProvider,
      DisplayUtilsWrapper displayUtilsWrapper, NetworkUtilsWrapper networkUtils,
      ModalLayoutPickerTracker analyticsTracker, CoroutineDispatcher bgDispatcher,
      CoroutineDispatcher mainDispatcher) {
    return new ModalLayoutPickerViewModel(dispatcher, siteStore, selectedSiteRepository, supportedBlocksProvider, thumbDimensionProvider, displayUtilsWrapper, networkUtils, analyticsTracker, bgDispatcher, mainDispatcher);
  }
}
