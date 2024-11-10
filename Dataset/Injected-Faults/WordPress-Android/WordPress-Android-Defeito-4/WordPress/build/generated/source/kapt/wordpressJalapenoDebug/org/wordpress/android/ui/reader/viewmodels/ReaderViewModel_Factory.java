package org.wordpress.android.ui.reader.viewmodels;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.ui.mysite.SelectedSiteRepository;
import org.wordpress.android.ui.mysite.cards.quickstart.QuickStartRepository;
import org.wordpress.android.ui.prefs.AppPrefsWrapper;
import org.wordpress.android.ui.reader.tracker.ReaderTracker;
import org.wordpress.android.ui.reader.usecases.LoadReaderTabsUseCase;
import org.wordpress.android.ui.reader.utils.DateProvider;
import org.wordpress.android.util.SnackbarSequencer;

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
public final class ReaderViewModel_Factory implements Factory<ReaderViewModel> {
  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  private final Provider<AppPrefsWrapper> appPrefsWrapperProvider;

  private final Provider<DateProvider> dateProvider;

  private final Provider<LoadReaderTabsUseCase> loadReaderTabsUseCaseProvider;

  private final Provider<ReaderTracker> readerTrackerProvider;

  private final Provider<AccountStore> accountStoreProvider;

  private final Provider<QuickStartRepository> quickStartRepositoryProvider;

  private final Provider<SelectedSiteRepository> selectedSiteRepositoryProvider;

  private final Provider<SnackbarSequencer> snackbarSequencerProvider;

  public ReaderViewModel_Factory(Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider,
      Provider<AppPrefsWrapper> appPrefsWrapperProvider, Provider<DateProvider> dateProvider,
      Provider<LoadReaderTabsUseCase> loadReaderTabsUseCaseProvider,
      Provider<ReaderTracker> readerTrackerProvider, Provider<AccountStore> accountStoreProvider,
      Provider<QuickStartRepository> quickStartRepositoryProvider,
      Provider<SelectedSiteRepository> selectedSiteRepositoryProvider,
      Provider<SnackbarSequencer> snackbarSequencerProvider) {
    this.mainDispatcherProvider = mainDispatcherProvider;
    this.bgDispatcherProvider = bgDispatcherProvider;
    this.appPrefsWrapperProvider = appPrefsWrapperProvider;
    this.dateProvider = dateProvider;
    this.loadReaderTabsUseCaseProvider = loadReaderTabsUseCaseProvider;
    this.readerTrackerProvider = readerTrackerProvider;
    this.accountStoreProvider = accountStoreProvider;
    this.quickStartRepositoryProvider = quickStartRepositoryProvider;
    this.selectedSiteRepositoryProvider = selectedSiteRepositoryProvider;
    this.snackbarSequencerProvider = snackbarSequencerProvider;
  }

  @Override
  public ReaderViewModel get() {
    return newInstance(mainDispatcherProvider.get(), bgDispatcherProvider.get(), appPrefsWrapperProvider.get(), dateProvider.get(), loadReaderTabsUseCaseProvider.get(), readerTrackerProvider.get(), accountStoreProvider.get(), quickStartRepositoryProvider.get(), selectedSiteRepositoryProvider.get(), snackbarSequencerProvider.get());
  }

  public static ReaderViewModel_Factory create(Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider,
      Provider<AppPrefsWrapper> appPrefsWrapperProvider, Provider<DateProvider> dateProvider,
      Provider<LoadReaderTabsUseCase> loadReaderTabsUseCaseProvider,
      Provider<ReaderTracker> readerTrackerProvider, Provider<AccountStore> accountStoreProvider,
      Provider<QuickStartRepository> quickStartRepositoryProvider,
      Provider<SelectedSiteRepository> selectedSiteRepositoryProvider,
      Provider<SnackbarSequencer> snackbarSequencerProvider) {
    return new ReaderViewModel_Factory(mainDispatcherProvider, bgDispatcherProvider, appPrefsWrapperProvider, dateProvider, loadReaderTabsUseCaseProvider, readerTrackerProvider, accountStoreProvider, quickStartRepositoryProvider, selectedSiteRepositoryProvider, snackbarSequencerProvider);
  }

  public static ReaderViewModel newInstance(CoroutineDispatcher mainDispatcher,
      CoroutineDispatcher bgDispatcher, AppPrefsWrapper appPrefsWrapper, DateProvider dateProvider,
      LoadReaderTabsUseCase loadReaderTabsUseCase, ReaderTracker readerTracker,
      AccountStore accountStore, QuickStartRepository quickStartRepository,
      SelectedSiteRepository selectedSiteRepository, SnackbarSequencer snackbarSequencer) {
    return new ReaderViewModel(mainDispatcher, bgDispatcher, appPrefsWrapper, dateProvider, loadReaderTabsUseCase, readerTracker, accountStore, quickStartRepository, selectedSiteRepository, snackbarSequencer);
  }
}
