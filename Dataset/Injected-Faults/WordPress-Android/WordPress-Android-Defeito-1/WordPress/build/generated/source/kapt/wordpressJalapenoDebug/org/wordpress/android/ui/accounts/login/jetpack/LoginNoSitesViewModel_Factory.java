package org.wordpress.android.ui.accounts.login.jetpack;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.ui.accounts.UnifiedLoginTracker;

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
public final class LoginNoSitesViewModel_Factory implements Factory<LoginNoSitesViewModel> {
  private final Provider<UnifiedLoginTracker> unifiedLoginTrackerProvider;

  private final Provider<AccountStore> accountStoreProvider;

  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  public LoginNoSitesViewModel_Factory(Provider<UnifiedLoginTracker> unifiedLoginTrackerProvider,
      Provider<AccountStore> accountStoreProvider,
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider) {
    this.unifiedLoginTrackerProvider = unifiedLoginTrackerProvider;
    this.accountStoreProvider = accountStoreProvider;
    this.mainDispatcherProvider = mainDispatcherProvider;
    this.bgDispatcherProvider = bgDispatcherProvider;
  }

  @Override
  public LoginNoSitesViewModel get() {
    return newInstance(unifiedLoginTrackerProvider.get(), accountStoreProvider.get(), mainDispatcherProvider.get(), bgDispatcherProvider.get());
  }

  public static LoginNoSitesViewModel_Factory create(
      Provider<UnifiedLoginTracker> unifiedLoginTrackerProvider,
      Provider<AccountStore> accountStoreProvider,
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider) {
    return new LoginNoSitesViewModel_Factory(unifiedLoginTrackerProvider, accountStoreProvider, mainDispatcherProvider, bgDispatcherProvider);
  }

  public static LoginNoSitesViewModel newInstance(UnifiedLoginTracker unifiedLoginTracker,
      AccountStore accountStore, CoroutineDispatcher mainDispatcher,
      CoroutineDispatcher bgDispatcher) {
    return new LoginNoSitesViewModel(unifiedLoginTracker, accountStore, mainDispatcher, bgDispatcher);
  }
}
