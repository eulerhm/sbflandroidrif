// Generated by Dagger (https://google.github.io/dagger).
package fr.free.nrw.commons.di;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import fr.free.nrw.commons.notification.NotificationInterface;
import fr.free.nrw.commons.wikidata.CommonsServiceFactory;
import javax.inject.Provider;

public final class NetworkingModule_ProvideNotificationInterfaceFactory implements Factory<NotificationInterface> {
  private final NetworkingModule module;

  private final Provider<CommonsServiceFactory> serviceFactoryProvider;

  public NetworkingModule_ProvideNotificationInterfaceFactory(NetworkingModule module,
      Provider<CommonsServiceFactory> serviceFactoryProvider) {
    this.module = module;
    this.serviceFactoryProvider = serviceFactoryProvider;
  }

  @Override
  public NotificationInterface get() {
    return provideNotificationInterface(module, serviceFactoryProvider.get());
  }

  public static NetworkingModule_ProvideNotificationInterfaceFactory create(NetworkingModule module,
      Provider<CommonsServiceFactory> serviceFactoryProvider) {
    return new NetworkingModule_ProvideNotificationInterfaceFactory(module, serviceFactoryProvider);
  }

  public static NotificationInterface provideNotificationInterface(NetworkingModule instance,
      CommonsServiceFactory serviceFactory) {
    return Preconditions.checkNotNull(instance.provideNotificationInterface(serviceFactory), "Cannot return null from a non-@Nullable @Provides method");
  }
}
