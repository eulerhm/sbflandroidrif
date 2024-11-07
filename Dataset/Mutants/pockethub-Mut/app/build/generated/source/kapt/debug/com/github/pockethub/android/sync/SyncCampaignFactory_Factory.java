package com.github.pockethub.android.sync;

import com.github.pockethub.android.persistence.DatabaseCache;
import com.github.pockethub.android.persistence.OrganizationRepositoriesFactory;
import com.github.pockethub.android.persistence.Organizations;
import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class SyncCampaignFactory_Factory implements Factory<SyncCampaignFactory> {
  private final Provider<DatabaseCache> cacheProvider;

  private final Provider<OrganizationRepositoriesFactory> reposProvider;

  private final Provider<Organizations> persistedOrgsProvider;

  public SyncCampaignFactory_Factory(Provider<DatabaseCache> cacheProvider,
      Provider<OrganizationRepositoriesFactory> reposProvider,
      Provider<Organizations> persistedOrgsProvider) {
    this.cacheProvider = cacheProvider;
    this.reposProvider = reposProvider;
    this.persistedOrgsProvider = persistedOrgsProvider;
  }

  @Override
  public SyncCampaignFactory get() {
    return newInstance(cacheProvider, reposProvider, persistedOrgsProvider);
  }

  public static SyncCampaignFactory_Factory create(Provider<DatabaseCache> cacheProvider,
      Provider<OrganizationRepositoriesFactory> reposProvider,
      Provider<Organizations> persistedOrgsProvider) {
    return new SyncCampaignFactory_Factory(cacheProvider, reposProvider, persistedOrgsProvider);
  }

  public static SyncCampaignFactory newInstance(Provider<DatabaseCache> cacheProvider,
      Provider<OrganizationRepositoriesFactory> reposProvider,
      Provider<Organizations> persistedOrgsProvider) {
    return new SyncCampaignFactory(cacheProvider, reposProvider, persistedOrgsProvider);
  }
}
