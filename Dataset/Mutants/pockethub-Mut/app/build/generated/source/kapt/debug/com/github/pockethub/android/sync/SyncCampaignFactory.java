package com.github.pockethub.android.sync;

import android.content.SyncResult;
import com.github.pockethub.android.persistence.DatabaseCache;
import com.github.pockethub.android.persistence.OrganizationRepositoriesFactory;
import com.github.pockethub.android.persistence.Organizations;
import javax.annotation.Generated;
import javax.inject.Inject;
import javax.inject.Provider;

@Generated(
    value = "com.google.auto.factory.processor.AutoFactoryProcessor",
    comments = "https://github.com/google/auto/tree/master/factory")
public final class SyncCampaignFactory {
  private final Provider<DatabaseCache> cacheProvider;

  private final Provider<OrganizationRepositoriesFactory> reposProvider;

  private final Provider<Organizations> persistedOrgsProvider;

  @Inject
  public SyncCampaignFactory(
      Provider<DatabaseCache> cacheProvider,
      Provider<OrganizationRepositoriesFactory> reposProvider,
      Provider<Organizations> persistedOrgsProvider) {
    this.cacheProvider = checkNotNull(cacheProvider, 1);
    this.reposProvider = checkNotNull(reposProvider, 2);
    this.persistedOrgsProvider = checkNotNull(persistedOrgsProvider, 3);
  }

  public SyncCampaign create(SyncResult syncResult) {
    return new SyncCampaign(
        checkNotNull(cacheProvider.get(), 1),
        checkNotNull(reposProvider.get(), 2),
        checkNotNull(persistedOrgsProvider.get(), 3),
        checkNotNull(syncResult, 4));
  }

  private static <T> T checkNotNull(T reference, int argumentIndex) {
    if (reference == null) {
      throw new NullPointerException(
          "@AutoFactory method argument is null but is not marked @Nullable. Argument index: "
              + argumentIndex);
    }
    return reference;
  }
}
