package org.wordpress.android.ui.stats.refresh.lists.widget.configuration;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.ui.prefs.AppPrefsWrapper;

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
public final class StatsDataTypeSelectionViewModel_Factory implements Factory<StatsDataTypeSelectionViewModel> {
  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  private final Provider<AccountStore> accountStoreProvider;

  private final Provider<AppPrefsWrapper> appPrefsWrapperProvider;

  public StatsDataTypeSelectionViewModel_Factory(
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<AccountStore> accountStoreProvider,
      Provider<AppPrefsWrapper> appPrefsWrapperProvider) {
    this.mainDispatcherProvider = mainDispatcherProvider;
    this.accountStoreProvider = accountStoreProvider;
    this.appPrefsWrapperProvider = appPrefsWrapperProvider;
  }

  @Override
  public StatsDataTypeSelectionViewModel get() {
    return newInstance(mainDispatcherProvider.get(), accountStoreProvider.get(), appPrefsWrapperProvider.get());
  }

  public static StatsDataTypeSelectionViewModel_Factory create(
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<AccountStore> accountStoreProvider,
      Provider<AppPrefsWrapper> appPrefsWrapperProvider) {
    return new StatsDataTypeSelectionViewModel_Factory(mainDispatcherProvider, accountStoreProvider, appPrefsWrapperProvider);
  }

  public static StatsDataTypeSelectionViewModel newInstance(CoroutineDispatcher mainDispatcher,
      AccountStore accountStore, AppPrefsWrapper appPrefsWrapper) {
    return new StatsDataTypeSelectionViewModel(mainDispatcher, accountStore, appPrefsWrapper);
  }
}
