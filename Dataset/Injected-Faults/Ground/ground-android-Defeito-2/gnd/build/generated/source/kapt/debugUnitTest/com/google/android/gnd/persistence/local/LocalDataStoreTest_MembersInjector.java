package com.google.android.gnd.persistence.local;

import com.google.android.gnd.persistence.local.room.dao.FeatureDao;
import com.google.android.gnd.persistence.local.room.dao.SubmissionDao;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import javax.annotation.Generated;
import javax.inject.Provider;

@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class LocalDataStoreTest_MembersInjector implements MembersInjector<LocalDataStoreTest> {
  private final Provider<LocalDataStore> localDataStoreProvider;

  private final Provider<LocalValueStore> localValueStoreProvider;

  private final Provider<SubmissionDao> submissionDaoProvider;

  private final Provider<FeatureDao> featureDaoProvider;

  public LocalDataStoreTest_MembersInjector(Provider<LocalDataStore> localDataStoreProvider,
      Provider<LocalValueStore> localValueStoreProvider,
      Provider<SubmissionDao> submissionDaoProvider, Provider<FeatureDao> featureDaoProvider) {
    this.localDataStoreProvider = localDataStoreProvider;
    this.localValueStoreProvider = localValueStoreProvider;
    this.submissionDaoProvider = submissionDaoProvider;
    this.featureDaoProvider = featureDaoProvider;
  }

  public static MembersInjector<LocalDataStoreTest> create(
      Provider<LocalDataStore> localDataStoreProvider,
      Provider<LocalValueStore> localValueStoreProvider,
      Provider<SubmissionDao> submissionDaoProvider, Provider<FeatureDao> featureDaoProvider) {
    return new LocalDataStoreTest_MembersInjector(localDataStoreProvider, localValueStoreProvider, submissionDaoProvider, featureDaoProvider);
  }

  @Override
  public void injectMembers(LocalDataStoreTest instance) {
    injectLocalDataStore(instance, localDataStoreProvider.get());
    injectLocalValueStore(instance, localValueStoreProvider.get());
    injectSubmissionDao(instance, submissionDaoProvider.get());
    injectFeatureDao(instance, featureDaoProvider.get());
  }

  @InjectedFieldSignature("com.google.android.gnd.persistence.local.LocalDataStoreTest.localDataStore")
  public static void injectLocalDataStore(LocalDataStoreTest instance,
      LocalDataStore localDataStore) {
    instance.localDataStore = localDataStore;
  }

  @InjectedFieldSignature("com.google.android.gnd.persistence.local.LocalDataStoreTest.localValueStore")
  public static void injectLocalValueStore(LocalDataStoreTest instance,
      LocalValueStore localValueStore) {
    instance.localValueStore = localValueStore;
  }

  @InjectedFieldSignature("com.google.android.gnd.persistence.local.LocalDataStoreTest.submissionDao")
  public static void injectSubmissionDao(LocalDataStoreTest instance, SubmissionDao submissionDao) {
    instance.submissionDao = submissionDao;
  }

  @InjectedFieldSignature("com.google.android.gnd.persistence.local.LocalDataStoreTest.featureDao")
  public static void injectFeatureDao(LocalDataStoreTest instance, FeatureDao featureDao) {
    instance.featureDao = featureDao;
  }
}
