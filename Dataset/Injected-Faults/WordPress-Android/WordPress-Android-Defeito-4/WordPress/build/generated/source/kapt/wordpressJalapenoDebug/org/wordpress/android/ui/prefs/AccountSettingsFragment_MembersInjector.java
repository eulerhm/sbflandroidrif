package org.wordpress.android.ui.prefs;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.fluxc.store.SiteStore;

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
public final class AccountSettingsFragment_MembersInjector implements MembersInjector<AccountSettingsFragment> {
  private final Provider<Dispatcher> mDispatcherProvider;

  private final Provider<AccountStore> mAccountStoreProvider;

  private final Provider<SiteStore> mSiteStoreProvider;

  public AccountSettingsFragment_MembersInjector(Provider<Dispatcher> mDispatcherProvider,
      Provider<AccountStore> mAccountStoreProvider, Provider<SiteStore> mSiteStoreProvider) {
    this.mDispatcherProvider = mDispatcherProvider;
    this.mAccountStoreProvider = mAccountStoreProvider;
    this.mSiteStoreProvider = mSiteStoreProvider;
  }

  public static MembersInjector<AccountSettingsFragment> create(
      Provider<Dispatcher> mDispatcherProvider, Provider<AccountStore> mAccountStoreProvider,
      Provider<SiteStore> mSiteStoreProvider) {
    return new AccountSettingsFragment_MembersInjector(mDispatcherProvider, mAccountStoreProvider, mSiteStoreProvider);
  }

  @Override
  public void injectMembers(AccountSettingsFragment instance) {
    injectMDispatcher(instance, mDispatcherProvider.get());
    injectMAccountStore(instance, mAccountStoreProvider.get());
    injectMSiteStore(instance, mSiteStoreProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.prefs.AccountSettingsFragment.mDispatcher")
  public static void injectMDispatcher(AccountSettingsFragment instance, Dispatcher mDispatcher) {
    instance.mDispatcher = mDispatcher;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.prefs.AccountSettingsFragment.mAccountStore")
  public static void injectMAccountStore(AccountSettingsFragment instance,
      AccountStore mAccountStore) {
    instance.mAccountStore = mAccountStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.prefs.AccountSettingsFragment.mSiteStore")
  public static void injectMSiteStore(AccountSettingsFragment instance, SiteStore mSiteStore) {
    instance.mSiteStore = mSiteStore;
  }
}
