package org.wordpress.android.ui.people;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.util.NetworkUtilsWrapper;
import org.wordpress.android.util.analytics.AnalyticsUtilsWrapper;

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
public final class InviteLinksUseCase_Factory implements Factory<InviteLinksUseCase> {
  private final Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider;

  private final Provider<InviteLinksApiCallsProvider> inviteLinksApiCallsProvider;

  private final Provider<AccountStore> accountStoreProvider;

  private final Provider<AnalyticsUtilsWrapper> analyticsUtilsWrapperProvider;

  private final Provider<SiteStore> siteStoreProvider;

  public InviteLinksUseCase_Factory(Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider,
      Provider<InviteLinksApiCallsProvider> inviteLinksApiCallsProvider,
      Provider<AccountStore> accountStoreProvider,
      Provider<AnalyticsUtilsWrapper> analyticsUtilsWrapperProvider,
      Provider<SiteStore> siteStoreProvider) {
    this.networkUtilsWrapperProvider = networkUtilsWrapperProvider;
    this.inviteLinksApiCallsProvider = inviteLinksApiCallsProvider;
    this.accountStoreProvider = accountStoreProvider;
    this.analyticsUtilsWrapperProvider = analyticsUtilsWrapperProvider;
    this.siteStoreProvider = siteStoreProvider;
  }

  @Override
  public InviteLinksUseCase get() {
    return newInstance(networkUtilsWrapperProvider.get(), inviteLinksApiCallsProvider.get(), accountStoreProvider.get(), analyticsUtilsWrapperProvider.get(), siteStoreProvider.get());
  }

  public static InviteLinksUseCase_Factory create(
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider,
      Provider<InviteLinksApiCallsProvider> inviteLinksApiCallsProvider,
      Provider<AccountStore> accountStoreProvider,
      Provider<AnalyticsUtilsWrapper> analyticsUtilsWrapperProvider,
      Provider<SiteStore> siteStoreProvider) {
    return new InviteLinksUseCase_Factory(networkUtilsWrapperProvider, inviteLinksApiCallsProvider, accountStoreProvider, analyticsUtilsWrapperProvider, siteStoreProvider);
  }

  public static InviteLinksUseCase newInstance(NetworkUtilsWrapper networkUtilsWrapper,
      InviteLinksApiCallsProvider inviteLinksApiCallsProvider, AccountStore accountStore,
      AnalyticsUtilsWrapper analyticsUtilsWrapper, SiteStore siteStore) {
    return new InviteLinksUseCase(networkUtilsWrapper, inviteLinksApiCallsProvider, accountStore, analyticsUtilsWrapper, siteStore);
  }
}
