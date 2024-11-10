package org.wordpress.android.ui.stats;

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
public final class StatsConnectJetpackActivity_MembersInjector implements MembersInjector<StatsConnectJetpackActivity> {
  private final Provider<AccountStore> mAccountStoreProvider;

  private final Provider<Dispatcher> mDispatcherProvider;

  public StatsConnectJetpackActivity_MembersInjector(Provider<AccountStore> mAccountStoreProvider,
      Provider<Dispatcher> mDispatcherProvider) {
    this.mAccountStoreProvider = mAccountStoreProvider;
    this.mDispatcherProvider = mDispatcherProvider;
  }

  public static MembersInjector<StatsConnectJetpackActivity> create(
      Provider<AccountStore> mAccountStoreProvider, Provider<Dispatcher> mDispatcherProvider) {
    return new StatsConnectJetpackActivity_MembersInjector(mAccountStoreProvider, mDispatcherProvider);
  }

  @Override
  public void injectMembers(StatsConnectJetpackActivity instance) {
    injectMAccountStore(instance, mAccountStoreProvider.get());
    injectMDispatcher(instance, mDispatcherProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.stats.StatsConnectJetpackActivity.mAccountStore")
  public static void injectMAccountStore(StatsConnectJetpackActivity instance,
      AccountStore mAccountStore) {
    instance.mAccountStore = mAccountStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.stats.StatsConnectJetpackActivity.mDispatcher")
  public static void injectMDispatcher(StatsConnectJetpackActivity instance,
      Dispatcher mDispatcher) {
    instance.mDispatcher = mDispatcher;
  }
}
