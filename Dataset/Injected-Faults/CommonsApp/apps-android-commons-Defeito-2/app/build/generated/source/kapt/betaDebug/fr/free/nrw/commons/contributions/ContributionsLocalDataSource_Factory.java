// Generated by Dagger (https://google.github.io/dagger).
package fr.free.nrw.commons.contributions;

import dagger.internal.Factory;
import fr.free.nrw.commons.kvstore.JsonKvStore;
import javax.inject.Provider;

public final class ContributionsLocalDataSource_Factory implements Factory<ContributionsLocalDataSource> {
  private final Provider<JsonKvStore> defaultKVStoreProvider;

  private final Provider<ContributionDao> contributionDaoProvider;

  public ContributionsLocalDataSource_Factory(Provider<JsonKvStore> defaultKVStoreProvider,
      Provider<ContributionDao> contributionDaoProvider) {
    this.defaultKVStoreProvider = defaultKVStoreProvider;
    this.contributionDaoProvider = contributionDaoProvider;
  }

  @Override
  public ContributionsLocalDataSource get() {
    return new ContributionsLocalDataSource(defaultKVStoreProvider.get(), contributionDaoProvider.get());
  }

  public static ContributionsLocalDataSource_Factory create(
      Provider<JsonKvStore> defaultKVStoreProvider,
      Provider<ContributionDao> contributionDaoProvider) {
    return new ContributionsLocalDataSource_Factory(defaultKVStoreProvider, contributionDaoProvider);
  }

  public static ContributionsLocalDataSource newInstance(JsonKvStore defaultKVStore,
      ContributionDao contributionDao) {
    return new ContributionsLocalDataSource(defaultKVStore, contributionDao);
  }
}
