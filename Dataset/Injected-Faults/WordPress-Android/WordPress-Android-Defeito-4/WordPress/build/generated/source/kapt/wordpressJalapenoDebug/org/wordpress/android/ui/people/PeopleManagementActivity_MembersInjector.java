package org.wordpress.android.ui.people;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.Dispatcher;
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
public final class PeopleManagementActivity_MembersInjector implements MembersInjector<PeopleManagementActivity> {
  private final Provider<Dispatcher> mDispatcherProvider;

  private final Provider<AccountStore> mAccountStoreProvider;

  public PeopleManagementActivity_MembersInjector(Provider<Dispatcher> mDispatcherProvider,
      Provider<AccountStore> mAccountStoreProvider) {
    this.mDispatcherProvider = mDispatcherProvider;
    this.mAccountStoreProvider = mAccountStoreProvider;
  }

  public static MembersInjector<PeopleManagementActivity> create(
      Provider<Dispatcher> mDispatcherProvider, Provider<AccountStore> mAccountStoreProvider) {
    return new PeopleManagementActivity_MembersInjector(mDispatcherProvider, mAccountStoreProvider);
  }

  @Override
  public void injectMembers(PeopleManagementActivity instance) {
    injectMDispatcher(instance, mDispatcherProvider.get());
    injectMAccountStore(instance, mAccountStoreProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.people.PeopleManagementActivity.mDispatcher")
  public static void injectMDispatcher(PeopleManagementActivity instance, Dispatcher mDispatcher) {
    instance.mDispatcher = mDispatcher;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.people.PeopleManagementActivity.mAccountStore")
  public static void injectMAccountStore(PeopleManagementActivity instance,
      AccountStore mAccountStore) {
    instance.mAccountStore = mAccountStore;
  }
}
