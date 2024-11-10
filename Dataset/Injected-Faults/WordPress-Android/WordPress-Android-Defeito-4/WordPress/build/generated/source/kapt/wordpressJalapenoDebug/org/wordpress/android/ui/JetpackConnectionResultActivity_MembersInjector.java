package org.wordpress.android.ui;

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
public final class JetpackConnectionResultActivity_MembersInjector implements MembersInjector<JetpackConnectionResultActivity> {
  private final Provider<AccountStore> mAccountStoreProvider;

  private final Provider<SiteStore> mSiteStoreProvider;

  private final Provider<Dispatcher> mDispatcherProvider;

  public JetpackConnectionResultActivity_MembersInjector(
      Provider<AccountStore> mAccountStoreProvider, Provider<SiteStore> mSiteStoreProvider,
      Provider<Dispatcher> mDispatcherProvider) {
    this.mAccountStoreProvider = mAccountStoreProvider;
    this.mSiteStoreProvider = mSiteStoreProvider;
    this.mDispatcherProvider = mDispatcherProvider;
  }

  public static MembersInjector<JetpackConnectionResultActivity> create(
      Provider<AccountStore> mAccountStoreProvider, Provider<SiteStore> mSiteStoreProvider,
      Provider<Dispatcher> mDispatcherProvider) {
    return new JetpackConnectionResultActivity_MembersInjector(mAccountStoreProvider, mSiteStoreProvider, mDispatcherProvider);
  }

  @Override
  public void injectMembers(JetpackConnectionResultActivity instance) {
    injectMAccountStore(instance, mAccountStoreProvider.get());
    injectMSiteStore(instance, mSiteStoreProvider.get());
    injectMDispatcher(instance, mDispatcherProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.JetpackConnectionResultActivity.mAccountStore")
  public static void injectMAccountStore(JetpackConnectionResultActivity instance,
      AccountStore mAccountStore) {
    instance.mAccountStore = mAccountStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.JetpackConnectionResultActivity.mSiteStore")
  public static void injectMSiteStore(JetpackConnectionResultActivity instance,
      SiteStore mSiteStore) {
    instance.mSiteStore = mSiteStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.JetpackConnectionResultActivity.mDispatcher")
  public static void injectMDispatcher(JetpackConnectionResultActivity instance,
      Dispatcher mDispatcher) {
    instance.mDispatcher = mDispatcher;
  }
}
