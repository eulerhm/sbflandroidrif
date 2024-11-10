package org.wordpress.android.ui.reader.subfilter;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.ui.prefs.AppPrefsWrapper;
import org.wordpress.android.ui.reader.tracker.ReaderTracker;
import org.wordpress.android.util.EventBusWrapper;

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
public final class SubFilterViewModel_Factory implements Factory<SubFilterViewModel> {
  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  private final Provider<AppPrefsWrapper> appPrefsWrapperProvider;

  private final Provider<SubfilterListItemMapper> subfilterListItemMapperProvider;

  private final Provider<EventBusWrapper> eventBusWrapperProvider;

  private final Provider<AccountStore> accountStoreProvider;

  private final Provider<ReaderTracker> readerTrackerProvider;

  public SubFilterViewModel_Factory(Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider,
      Provider<AppPrefsWrapper> appPrefsWrapperProvider,
      Provider<SubfilterListItemMapper> subfilterListItemMapperProvider,
      Provider<EventBusWrapper> eventBusWrapperProvider,
      Provider<AccountStore> accountStoreProvider, Provider<ReaderTracker> readerTrackerProvider) {
    this.mainDispatcherProvider = mainDispatcherProvider;
    this.bgDispatcherProvider = bgDispatcherProvider;
    this.appPrefsWrapperProvider = appPrefsWrapperProvider;
    this.subfilterListItemMapperProvider = subfilterListItemMapperProvider;
    this.eventBusWrapperProvider = eventBusWrapperProvider;
    this.accountStoreProvider = accountStoreProvider;
    this.readerTrackerProvider = readerTrackerProvider;
  }

  @Override
  public SubFilterViewModel get() {
    return newInstance(mainDispatcherProvider.get(), bgDispatcherProvider.get(), appPrefsWrapperProvider.get(), subfilterListItemMapperProvider.get(), eventBusWrapperProvider.get(), accountStoreProvider.get(), readerTrackerProvider.get());
  }

  public static SubFilterViewModel_Factory create(
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider,
      Provider<AppPrefsWrapper> appPrefsWrapperProvider,
      Provider<SubfilterListItemMapper> subfilterListItemMapperProvider,
      Provider<EventBusWrapper> eventBusWrapperProvider,
      Provider<AccountStore> accountStoreProvider, Provider<ReaderTracker> readerTrackerProvider) {
    return new SubFilterViewModel_Factory(mainDispatcherProvider, bgDispatcherProvider, appPrefsWrapperProvider, subfilterListItemMapperProvider, eventBusWrapperProvider, accountStoreProvider, readerTrackerProvider);
  }

  public static SubFilterViewModel newInstance(CoroutineDispatcher mainDispatcher,
      CoroutineDispatcher bgDispatcher, AppPrefsWrapper appPrefsWrapper,
      SubfilterListItemMapper subfilterListItemMapper, EventBusWrapper eventBusWrapper,
      AccountStore accountStore, ReaderTracker readerTracker) {
    return new SubFilterViewModel(mainDispatcher, bgDispatcher, appPrefsWrapper, subfilterListItemMapper, eventBusWrapper, accountStore, readerTracker);
  }
}
