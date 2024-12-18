// Generated by Dagger (https://google.github.io/dagger).
package fr.free.nrw.commons.di;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import fr.free.nrw.commons.actions.PageEditInterface;
import fr.free.nrw.commons.wikidata.CommonsServiceFactory;
import javax.inject.Provider;

public final class NetworkingModule_ProvidePageEditServiceFactory implements Factory<PageEditInterface> {
  private final NetworkingModule module;

  private final Provider<CommonsServiceFactory> serviceFactoryProvider;

  public NetworkingModule_ProvidePageEditServiceFactory(NetworkingModule module,
      Provider<CommonsServiceFactory> serviceFactoryProvider) {
    this.module = module;
    this.serviceFactoryProvider = serviceFactoryProvider;
  }

  @Override
  public PageEditInterface get() {
    return providePageEditService(module, serviceFactoryProvider.get());
  }

  public static NetworkingModule_ProvidePageEditServiceFactory create(NetworkingModule module,
      Provider<CommonsServiceFactory> serviceFactoryProvider) {
    return new NetworkingModule_ProvidePageEditServiceFactory(module, serviceFactoryProvider);
  }

  public static PageEditInterface providePageEditService(NetworkingModule instance,
      CommonsServiceFactory serviceFactory) {
    return Preconditions.checkNotNull(instance.providePageEditService(serviceFactory), "Cannot return null from a non-@Nullable @Provides method");
  }
}
