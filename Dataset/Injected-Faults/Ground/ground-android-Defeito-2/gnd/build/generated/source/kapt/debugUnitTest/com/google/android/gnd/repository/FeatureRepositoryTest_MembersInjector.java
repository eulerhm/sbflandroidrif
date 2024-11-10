package com.google.android.gnd.repository;

import com.google.android.gnd.persistence.remote.FakeRemoteDataStore;
import com.google.android.gnd.system.auth.FakeAuthenticationManager;
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
public final class FeatureRepositoryTest_MembersInjector implements MembersInjector<FeatureRepositoryTest> {
  private final Provider<FakeAuthenticationManager> fakeAuthenticationManagerProvider;

  private final Provider<FakeRemoteDataStore> fakeRemoteDataStoreProvider;

  private final Provider<FeatureRepository> featureRepositoryProvider;

  public FeatureRepositoryTest_MembersInjector(
      Provider<FakeAuthenticationManager> fakeAuthenticationManagerProvider,
      Provider<FakeRemoteDataStore> fakeRemoteDataStoreProvider,
      Provider<FeatureRepository> featureRepositoryProvider) {
    this.fakeAuthenticationManagerProvider = fakeAuthenticationManagerProvider;
    this.fakeRemoteDataStoreProvider = fakeRemoteDataStoreProvider;
    this.featureRepositoryProvider = featureRepositoryProvider;
  }

  public static MembersInjector<FeatureRepositoryTest> create(
      Provider<FakeAuthenticationManager> fakeAuthenticationManagerProvider,
      Provider<FakeRemoteDataStore> fakeRemoteDataStoreProvider,
      Provider<FeatureRepository> featureRepositoryProvider) {
    return new FeatureRepositoryTest_MembersInjector(fakeAuthenticationManagerProvider, fakeRemoteDataStoreProvider, featureRepositoryProvider);
  }

  @Override
  public void injectMembers(FeatureRepositoryTest instance) {
    injectFakeAuthenticationManager(instance, fakeAuthenticationManagerProvider.get());
    injectFakeRemoteDataStore(instance, fakeRemoteDataStoreProvider.get());
    injectFeatureRepository(instance, featureRepositoryProvider.get());
  }

  @InjectedFieldSignature("com.google.android.gnd.repository.FeatureRepositoryTest.fakeAuthenticationManager")
  public static void injectFakeAuthenticationManager(FeatureRepositoryTest instance,
      FakeAuthenticationManager fakeAuthenticationManager) {
    instance.fakeAuthenticationManager = fakeAuthenticationManager;
  }

  @InjectedFieldSignature("com.google.android.gnd.repository.FeatureRepositoryTest.fakeRemoteDataStore")
  public static void injectFakeRemoteDataStore(FeatureRepositoryTest instance,
      FakeRemoteDataStore fakeRemoteDataStore) {
    instance.fakeRemoteDataStore = fakeRemoteDataStore;
  }

  @InjectedFieldSignature("com.google.android.gnd.repository.FeatureRepositoryTest.featureRepository")
  public static void injectFeatureRepository(FeatureRepositoryTest instance,
      FeatureRepository featureRepository) {
    instance.featureRepository = featureRepository;
  }
}
