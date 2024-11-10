package com.google.android.gnd.repository;

import com.google.android.gnd.persistence.local.LocalDataStore;
import com.google.android.gnd.persistence.local.LocalValueStore;
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
public final class UserRepositoryTest_MembersInjector implements MembersInjector<UserRepositoryTest> {
  private final Provider<FakeAuthenticationManager> fakeAuthenticationManagerProvider;

  private final Provider<LocalDataStore> localDataStoreProvider;

  private final Provider<LocalValueStore> localValueStoreProvider;

  private final Provider<UserRepository> userRepositoryProvider;

  public UserRepositoryTest_MembersInjector(
      Provider<FakeAuthenticationManager> fakeAuthenticationManagerProvider,
      Provider<LocalDataStore> localDataStoreProvider,
      Provider<LocalValueStore> localValueStoreProvider,
      Provider<UserRepository> userRepositoryProvider) {
    this.fakeAuthenticationManagerProvider = fakeAuthenticationManagerProvider;
    this.localDataStoreProvider = localDataStoreProvider;
    this.localValueStoreProvider = localValueStoreProvider;
    this.userRepositoryProvider = userRepositoryProvider;
  }

  public static MembersInjector<UserRepositoryTest> create(
      Provider<FakeAuthenticationManager> fakeAuthenticationManagerProvider,
      Provider<LocalDataStore> localDataStoreProvider,
      Provider<LocalValueStore> localValueStoreProvider,
      Provider<UserRepository> userRepositoryProvider) {
    return new UserRepositoryTest_MembersInjector(fakeAuthenticationManagerProvider, localDataStoreProvider, localValueStoreProvider, userRepositoryProvider);
  }

  @Override
  public void injectMembers(UserRepositoryTest instance) {
    injectFakeAuthenticationManager(instance, fakeAuthenticationManagerProvider.get());
    injectLocalDataStore(instance, localDataStoreProvider.get());
    injectLocalValueStore(instance, localValueStoreProvider.get());
    injectUserRepository(instance, userRepositoryProvider.get());
  }

  @InjectedFieldSignature("com.google.android.gnd.repository.UserRepositoryTest.fakeAuthenticationManager")
  public static void injectFakeAuthenticationManager(UserRepositoryTest instance,
      FakeAuthenticationManager fakeAuthenticationManager) {
    instance.fakeAuthenticationManager = fakeAuthenticationManager;
  }

  @InjectedFieldSignature("com.google.android.gnd.repository.UserRepositoryTest.localDataStore")
  public static void injectLocalDataStore(UserRepositoryTest instance,
      LocalDataStore localDataStore) {
    instance.localDataStore = localDataStore;
  }

  @InjectedFieldSignature("com.google.android.gnd.repository.UserRepositoryTest.localValueStore")
  public static void injectLocalValueStore(UserRepositoryTest instance,
      LocalValueStore localValueStore) {
    instance.localValueStore = localValueStore;
  }

  @InjectedFieldSignature("com.google.android.gnd.repository.UserRepositoryTest.userRepository")
  public static void injectUserRepository(UserRepositoryTest instance,
      UserRepository userRepository) {
    instance.userRepository = userRepository;
  }
}
