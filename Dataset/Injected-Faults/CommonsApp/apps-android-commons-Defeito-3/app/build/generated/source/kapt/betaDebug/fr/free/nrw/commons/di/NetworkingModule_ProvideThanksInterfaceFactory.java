// Generated by Dagger (https://google.github.io/dagger).
package fr.free.nrw.commons.di;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import fr.free.nrw.commons.actions.ThanksInterface;
import fr.free.nrw.commons.wikidata.CommonsServiceFactory;
import javax.inject.Provider;

public final class NetworkingModule_ProvideThanksInterfaceFactory implements Factory<ThanksInterface> {
  private final NetworkingModule module;

  private final Provider<CommonsServiceFactory> serviceFactoryProvider;

  public NetworkingModule_ProvideThanksInterfaceFactory(NetworkingModule module,
      Provider<CommonsServiceFactory> serviceFactoryProvider) {
    this.module = module;
    this.serviceFactoryProvider = serviceFactoryProvider;
  }

  @Override
  public ThanksInterface get() {
    return provideThanksInterface(module, serviceFactoryProvider.get());
  }

  public static NetworkingModule_ProvideThanksInterfaceFactory create(NetworkingModule module,
      Provider<CommonsServiceFactory> serviceFactoryProvider) {
    return new NetworkingModule_ProvideThanksInterfaceFactory(module, serviceFactoryProvider);
  }

  public static ThanksInterface provideThanksInterface(NetworkingModule instance,
      CommonsServiceFactory serviceFactory) {
    return Preconditions.checkNotNull(instance.provideThanksInterface(serviceFactory), "Cannot return null from a non-@Nullable @Provides method");
  }
}
