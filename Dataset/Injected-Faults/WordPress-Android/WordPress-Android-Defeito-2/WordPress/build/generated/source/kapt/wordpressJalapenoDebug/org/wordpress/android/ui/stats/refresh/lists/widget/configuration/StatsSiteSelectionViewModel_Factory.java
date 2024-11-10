package org.wordpress.android.ui.stats.refresh.lists.widget.configuration;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.fluxc.store.SiteStore;
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
public final class StatsSiteSelectionViewModel_Factory implements Factory<StatsSiteSelectionViewModel> {
  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  private final Provider<SiteStore> siteStoreProvider;

  private final Provider<AccountStore> accountStoreProvider;

  private final Provider<AppPrefsWrapper> appPrefsWrapperProvider;

  public StatsSiteSelectionViewModel_Factory(Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<SiteStore> siteStoreProvider, Provider<AccountStore> accountStoreProvider,
      Provider<AppPrefsWrapper> appPrefsWrapperProvider) {
    this.mainDispatcherProvider = mainDispatcherProvider;
    this.siteStoreProvider = siteStoreProvider;
    this.accountStoreProvider = accountStoreProvider;
    this.appPrefsWrapperProvider = appPrefsWrapperProvider;
  }

  @Override
  public StatsSiteSelectionViewModel get() {
    return newInstance(mainDispatcherProvider.get(), siteStoreProvider.get(), accountStoreProvider.get(), appPrefsWrapperProvider.get());
  }

  public static StatsSiteSelectionViewModel_Factory create(
      Provider<CoroutineDispatcher> mainDispatcherProvider, Provider<SiteStore> siteStoreProvider,
      Provider<AccountStore> accountStoreProvider,
      Provider<AppPrefsWrapper> appPrefsWrapperProvider) {
    return new StatsSiteSelectionViewModel_Factory(mainDispatcherProvider, siteStoreProvider, accountStoreProvider, appPrefsWrapperProvider);
  }

  public static StatsSiteSelectionViewModel newInstance(CoroutineDispatcher mainDispatcher,
      SiteStore siteStore, AccountStore accountStore, AppPrefsWrapper appPrefsWrapper) {
    return new StatsSiteSelectionViewModel(mainDispatcher, siteStore, accountStore, appPrefsWrapper);
  }
}
