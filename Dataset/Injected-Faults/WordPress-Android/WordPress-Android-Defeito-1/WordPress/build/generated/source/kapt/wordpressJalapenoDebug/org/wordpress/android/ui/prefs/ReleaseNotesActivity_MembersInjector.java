package org.wordpress.android.ui.prefs;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.support.ZendeskHelper;

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
public final class ReleaseNotesActivity_MembersInjector implements MembersInjector<ReleaseNotesActivity> {
  private final Provider<AccountStore> mAccountStoreProvider;

  private final Provider<SiteStore> mSiteStoreProvider;

  private final Provider<ZendeskHelper> mZendeskHelperProvider;

  public ReleaseNotesActivity_MembersInjector(Provider<AccountStore> mAccountStoreProvider,
      Provider<SiteStore> mSiteStoreProvider, Provider<ZendeskHelper> mZendeskHelperProvider) {
    this.mAccountStoreProvider = mAccountStoreProvider;
    this.mSiteStoreProvider = mSiteStoreProvider;
    this.mZendeskHelperProvider = mZendeskHelperProvider;
  }

  public static MembersInjector<ReleaseNotesActivity> create(
      Provider<AccountStore> mAccountStoreProvider, Provider<SiteStore> mSiteStoreProvider,
      Provider<ZendeskHelper> mZendeskHelperProvider) {
    return new ReleaseNotesActivity_MembersInjector(mAccountStoreProvider, mSiteStoreProvider, mZendeskHelperProvider);
  }

  @Override
  public void injectMembers(ReleaseNotesActivity instance) {
    injectMAccountStore(instance, mAccountStoreProvider.get());
    injectMSiteStore(instance, mSiteStoreProvider.get());
    injectMZendeskHelper(instance, mZendeskHelperProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.prefs.ReleaseNotesActivity.mAccountStore")
  public static void injectMAccountStore(ReleaseNotesActivity instance,
      AccountStore mAccountStore) {
    instance.mAccountStore = mAccountStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.prefs.ReleaseNotesActivity.mSiteStore")
  public static void injectMSiteStore(ReleaseNotesActivity instance, SiteStore mSiteStore) {
    instance.mSiteStore = mSiteStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.prefs.ReleaseNotesActivity.mZendeskHelper")
  public static void injectMZendeskHelper(ReleaseNotesActivity instance,
      ZendeskHelper mZendeskHelper) {
    instance.mZendeskHelper = mZendeskHelper;
  }
}
