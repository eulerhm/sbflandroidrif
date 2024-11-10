package org.wordpress.android.ui.notifications;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.push.GCMMessageHandler;

@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class NotificationsListFragmentPage_MembersInjector implements MembersInjector<NotificationsListFragmentPage> {
  private final Provider<AccountStore> accountStoreProvider;

  private final Provider<GCMMessageHandler> gcmMessageHandlerProvider;

  public NotificationsListFragmentPage_MembersInjector(Provider<AccountStore> accountStoreProvider,
      Provider<GCMMessageHandler> gcmMessageHandlerProvider) {
    this.accountStoreProvider = accountStoreProvider;
    this.gcmMessageHandlerProvider = gcmMessageHandlerProvider;
  }

  public static MembersInjector<NotificationsListFragmentPage> create(
      Provider<AccountStore> accountStoreProvider,
      Provider<GCMMessageHandler> gcmMessageHandlerProvider) {
    return new NotificationsListFragmentPage_MembersInjector(accountStoreProvider, gcmMessageHandlerProvider);
  }

  @Override
  public void injectMembers(NotificationsListFragmentPage instance) {
    injectAccountStore(instance, accountStoreProvider.get());
    injectGcmMessageHandler(instance, gcmMessageHandlerProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.notifications.NotificationsListFragmentPage.accountStore")
  public static void injectAccountStore(NotificationsListFragmentPage instance,
      AccountStore accountStore) {
    instance.accountStore = accountStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.notifications.NotificationsListFragmentPage.gcmMessageHandler")
  public static void injectGcmMessageHandler(NotificationsListFragmentPage instance,
      GCMMessageHandler gcmMessageHandler) {
    instance.gcmMessageHandler = gcmMessageHandler;
  }
}
