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
public final class BlogPreferencesActivity_MembersInjector implements MembersInjector<BlogPreferencesActivity> {
  private final Provider<AccountStore> mAccountStoreProvider;

  private final Provider<SiteStore> mSiteStoreProvider;

  private final Provider<Dispatcher> mDispatcherProvider;

  public BlogPreferencesActivity_MembersInjector(Provider<AccountStore> mAccountStoreProvider,
      Provider<SiteStore> mSiteStoreProvider, Provider<Dispatcher> mDispatcherProvider) {
    this.mAccountStoreProvider = mAccountStoreProvider;
    this.mSiteStoreProvider = mSiteStoreProvider;
    this.mDispatcherProvider = mDispatcherProvider;
  }

  public static MembersInjector<BlogPreferencesActivity> create(
      Provider<AccountStore> mAccountStoreProvider, Provider<SiteStore> mSiteStoreProvider,
      Provider<Dispatcher> mDispatcherProvider) {
    return new BlogPreferencesActivity_MembersInjector(mAccountStoreProvider, mSiteStoreProvider, mDispatcherProvider);
  }

  @Override
  public void injectMembers(BlogPreferencesActivity instance) {
    injectMAccountStore(instance, mAccountStoreProvider.get());
    injectMSiteStore(instance, mSiteStoreProvider.get());
    injectMDispatcher(instance, mDispatcherProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.prefs.BlogPreferencesActivity.mAccountStore")
  public static void injectMAccountStore(BlogPreferencesActivity instance,
      AccountStore mAccountStore) {
    instance.mAccountStore = mAccountStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.prefs.BlogPreferencesActivity.mSiteStore")
  public static void injectMSiteStore(BlogPreferencesActivity instance, SiteStore mSiteStore) {
    instance.mSiteStore = mSiteStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.prefs.BlogPreferencesActivity.mDispatcher")
  public static void injectMDispatcher(BlogPreferencesActivity instance, Dispatcher mDispatcher) {
    instance.mDispatcher = mDispatcher;
  }
}
