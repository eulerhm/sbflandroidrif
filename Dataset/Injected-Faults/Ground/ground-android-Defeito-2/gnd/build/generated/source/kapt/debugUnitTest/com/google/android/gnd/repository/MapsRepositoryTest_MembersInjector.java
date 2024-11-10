package com.google.android.gnd.repository;

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
public final class MapsRepositoryTest_MembersInjector implements MembersInjector<MapsRepositoryTest> {
  private final Provider<MapsRepository> mapsRepositoryProvider;

  public MapsRepositoryTest_MembersInjector(Provider<MapsRepository> mapsRepositoryProvider) {
    this.mapsRepositoryProvider = mapsRepositoryProvider;
  }

  public static MembersInjector<MapsRepositoryTest> create(
      Provider<MapsRepository> mapsRepositoryProvider) {
    return new MapsRepositoryTest_MembersInjector(mapsRepositoryProvider);
  }

  @Override
  public void injectMembers(MapsRepositoryTest instance) {
    injectMapsRepository(instance, mapsRepositoryProvider.get());
  }

  @InjectedFieldSignature("com.google.android.gnd.repository.MapsRepositoryTest.mapsRepository")
  public static void injectMapsRepository(MapsRepositoryTest instance,
      MapsRepository mapsRepository) {
    instance.mapsRepository = mapsRepository;
  }
}
