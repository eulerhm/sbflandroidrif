// Generated by Dagger (https://google.github.io/dagger).
package fr.free.nrw.commons.di;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import fr.free.nrw.commons.review.ReviewInterface;
import fr.free.nrw.commons.wikidata.CommonsServiceFactory;
import javax.inject.Provider;

public final class NetworkingModule_ProvideReviewInterfaceFactory implements Factory<ReviewInterface> {
  private final NetworkingModule module;

  private final Provider<CommonsServiceFactory> serviceFactoryProvider;

  public NetworkingModule_ProvideReviewInterfaceFactory(NetworkingModule module,
      Provider<CommonsServiceFactory> serviceFactoryProvider) {
    this.module = module;
    this.serviceFactoryProvider = serviceFactoryProvider;
  }

  @Override
  public ReviewInterface get() {
    return provideReviewInterface(module, serviceFactoryProvider.get());
  }

  public static NetworkingModule_ProvideReviewInterfaceFactory create(NetworkingModule module,
      Provider<CommonsServiceFactory> serviceFactoryProvider) {
    return new NetworkingModule_ProvideReviewInterfaceFactory(module, serviceFactoryProvider);
  }

  public static ReviewInterface provideReviewInterface(NetworkingModule instance,
      CommonsServiceFactory serviceFactory) {
    return Preconditions.checkNotNull(instance.provideReviewInterface(serviceFactory), "Cannot return null from a non-@Nullable @Provides method");
  }
}
