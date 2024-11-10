package org.wordpress.android.ui.notifications;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.store.AccountStore;

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
public final class NotificationsListFragment_MembersInjector implements MembersInjector<NotificationsListFragment> {
  private final Provider<AccountStore> accountStoreProvider;

  public NotificationsListFragment_MembersInjector(Provider<AccountStore> accountStoreProvider) {
    this.accountStoreProvider = accountStoreProvider;
  }

  public static MembersInjector<NotificationsListFragment> create(
      Provider<AccountStore> accountStoreProvider) {
    return new NotificationsListFragment_MembersInjector(accountStoreProvider);
  }

  @Override
  public void injectMembers(NotificationsListFragment instance) {
    injectAccountStore(instance, accountStoreProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.notifications.NotificationsListFragment.accountStore")
  public static void injectAccountStore(NotificationsListFragment instance,
      AccountStore accountStore) {
    instance.accountStore = accountStore;
  }
}
