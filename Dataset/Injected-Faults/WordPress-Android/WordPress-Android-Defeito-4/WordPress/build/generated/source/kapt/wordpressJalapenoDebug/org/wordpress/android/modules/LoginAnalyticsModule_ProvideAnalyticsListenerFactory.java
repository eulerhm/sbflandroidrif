package org.wordpress.android.modules;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.login.LoginAnalyticsListener;
import org.wordpress.android.ui.accounts.UnifiedLoginTracker;

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
public final class LoginAnalyticsModule_ProvideAnalyticsListenerFactory implements Factory<LoginAnalyticsListener> {
  private final LoginAnalyticsModule module;

  private final Provider<AccountStore> accountStoreProvider;

  private final Provider<SiteStore> siteStoreProvider;

  private final Provider<UnifiedLoginTracker> unifiedLoginTrackerProvider;

  public LoginAnalyticsModule_ProvideAnalyticsListenerFactory(LoginAnalyticsModule module,
      Provider<AccountStore> accountStoreProvider, Provider<SiteStore> siteStoreProvider,
      Provider<UnifiedLoginTracker> unifiedLoginTrackerProvider) {
    this.module = module;
    this.accountStoreProvider = accountStoreProvider;
    this.siteStoreProvider = siteStoreProvider;
    this.unifiedLoginTrackerProvider = unifiedLoginTrackerProvider;
  }

  @Override
  public LoginAnalyticsListener get() {
    return provideAnalyticsListener(module, accountStoreProvider.get(), siteStoreProvider.get(), unifiedLoginTrackerProvider.get());
  }

  public static LoginAnalyticsModule_ProvideAnalyticsListenerFactory create(
      LoginAnalyticsModule module, Provider<AccountStore> accountStoreProvider,
      Provider<SiteStore> siteStoreProvider,
      Provider<UnifiedLoginTracker> unifiedLoginTrackerProvider) {
    return new LoginAnalyticsModule_ProvideAnalyticsListenerFactory(module, accountStoreProvider, siteStoreProvider, unifiedLoginTrackerProvider);
  }

  public static LoginAnalyticsListener provideAnalyticsListener(LoginAnalyticsModule instance,
      AccountStore accountStore, SiteStore siteStore, UnifiedLoginTracker unifiedLoginTracker) {
    return Preconditions.checkNotNullFromProvides(instance.provideAnalyticsListener(accountStore, siteStore, unifiedLoginTracker));
  }
}
