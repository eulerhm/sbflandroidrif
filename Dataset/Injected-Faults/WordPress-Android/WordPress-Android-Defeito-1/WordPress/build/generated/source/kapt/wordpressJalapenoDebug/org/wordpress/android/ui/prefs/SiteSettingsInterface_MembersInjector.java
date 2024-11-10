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
import org.wordpress.android.viewmodel.ResourceProvider;

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
public final class SiteSettingsInterface_MembersInjector implements MembersInjector<SiteSettingsInterface> {
  private final Provider<SiteStore> mSiteStoreProvider;

  private final Provider<Dispatcher> mDispatcherProvider;

  private final Provider<AccountStore> mAccountStoreProvider;

  private final Provider<ResourceProvider> mResourceProvider;

  public SiteSettingsInterface_MembersInjector(Provider<SiteStore> mSiteStoreProvider,
      Provider<Dispatcher> mDispatcherProvider, Provider<AccountStore> mAccountStoreProvider,
      Provider<ResourceProvider> mResourceProvider) {
    this.mSiteStoreProvider = mSiteStoreProvider;
    this.mDispatcherProvider = mDispatcherProvider;
    this.mAccountStoreProvider = mAccountStoreProvider;
    this.mResourceProvider = mResourceProvider;
  }

  public static MembersInjector<SiteSettingsInterface> create(
      Provider<SiteStore> mSiteStoreProvider, Provider<Dispatcher> mDispatcherProvider,
      Provider<AccountStore> mAccountStoreProvider, Provider<ResourceProvider> mResourceProvider) {
    return new SiteSettingsInterface_MembersInjector(mSiteStoreProvider, mDispatcherProvider, mAccountStoreProvider, mResourceProvider);
  }

  @Override
  public void injectMembers(SiteSettingsInterface instance) {
    injectMSiteStore(instance, mSiteStoreProvider.get());
    injectMDispatcher(instance, mDispatcherProvider.get());
    injectMAccountStore(instance, mAccountStoreProvider.get());
    injectMResourceProvider(instance, mResourceProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.prefs.SiteSettingsInterface.mSiteStore")
  public static void injectMSiteStore(SiteSettingsInterface instance, SiteStore mSiteStore) {
    instance.mSiteStore = mSiteStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.prefs.SiteSettingsInterface.mDispatcher")
  public static void injectMDispatcher(SiteSettingsInterface instance, Dispatcher mDispatcher) {
    instance.mDispatcher = mDispatcher;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.prefs.SiteSettingsInterface.mAccountStore")
  public static void injectMAccountStore(SiteSettingsInterface instance,
      AccountStore mAccountStore) {
    instance.mAccountStore = mAccountStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.prefs.SiteSettingsInterface.mResourceProvider")
  public static void injectMResourceProvider(SiteSettingsInterface instance,
      ResourceProvider mResourceProvider) {
    instance.mResourceProvider = mResourceProvider;
  }
}
