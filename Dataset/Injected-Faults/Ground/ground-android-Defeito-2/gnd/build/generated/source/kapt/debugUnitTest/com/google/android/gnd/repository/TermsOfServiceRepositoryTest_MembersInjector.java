package com.google.android.gnd.repository;

import com.google.android.gnd.persistence.remote.FakeRemoteDataStore;
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
public final class TermsOfServiceRepositoryTest_MembersInjector implements MembersInjector<TermsOfServiceRepositoryTest> {
  private final Provider<FakeRemoteDataStore> fakeRemoteDataStoreProvider;

  private final Provider<TermsOfServiceRepository> termsOfServiceRepositoryProvider;

  public TermsOfServiceRepositoryTest_MembersInjector(
      Provider<FakeRemoteDataStore> fakeRemoteDataStoreProvider,
      Provider<TermsOfServiceRepository> termsOfServiceRepositoryProvider) {
    this.fakeRemoteDataStoreProvider = fakeRemoteDataStoreProvider;
    this.termsOfServiceRepositoryProvider = termsOfServiceRepositoryProvider;
  }

  public static MembersInjector<TermsOfServiceRepositoryTest> create(
      Provider<FakeRemoteDataStore> fakeRemoteDataStoreProvider,
      Provider<TermsOfServiceRepository> termsOfServiceRepositoryProvider) {
    return new TermsOfServiceRepositoryTest_MembersInjector(fakeRemoteDataStoreProvider, termsOfServiceRepositoryProvider);
  }

  @Override
  public void injectMembers(TermsOfServiceRepositoryTest instance) {
    injectFakeRemoteDataStore(instance, fakeRemoteDataStoreProvider.get());
    injectTermsOfServiceRepository(instance, termsOfServiceRepositoryProvider.get());
  }

  @InjectedFieldSignature("com.google.android.gnd.repository.TermsOfServiceRepositoryTest.fakeRemoteDataStore")
  public static void injectFakeRemoteDataStore(TermsOfServiceRepositoryTest instance,
      FakeRemoteDataStore fakeRemoteDataStore) {
    instance.fakeRemoteDataStore = fakeRemoteDataStore;
  }

  @InjectedFieldSignature("com.google.android.gnd.repository.TermsOfServiceRepositoryTest.termsOfServiceRepository")
  public static void injectTermsOfServiceRepository(TermsOfServiceRepositoryTest instance,
      TermsOfServiceRepository termsOfServiceRepository) {
    instance.termsOfServiceRepository = termsOfServiceRepository;
  }
}
