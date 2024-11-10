package org.wordpress.android.ui;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
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
public final class ShareIntentReceiverActivity_MembersInjector implements MembersInjector<ShareIntentReceiverActivity> {
  private final Provider<AccountStore> mAccountStoreProvider;

  private final Provider<SiteStore> mSiteStoreProvider;

  public ShareIntentReceiverActivity_MembersInjector(Provider<AccountStore> mAccountStoreProvider,
      Provider<SiteStore> mSiteStoreProvider) {
    this.mAccountStoreProvider = mAccountStoreProvider;
    this.mSiteStoreProvider = mSiteStoreProvider;
  }

  public static MembersInjector<ShareIntentReceiverActivity> create(
      Provider<AccountStore> mAccountStoreProvider, Provider<SiteStore> mSiteStoreProvider) {
    return new ShareIntentReceiverActivity_MembersInjector(mAccountStoreProvider, mSiteStoreProvider);
  }

  @Override
  public void injectMembers(ShareIntentReceiverActivity instance) {
    injectMAccountStore(instance, mAccountStoreProvider.get());
    injectMSiteStore(instance, mSiteStoreProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.ShareIntentReceiverActivity.mAccountStore")
  public static void injectMAccountStore(ShareIntentReceiverActivity instance,
      AccountStore mAccountStore) {
    instance.mAccountStore = mAccountStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.ShareIntentReceiverActivity.mSiteStore")
  public static void injectMSiteStore(ShareIntentReceiverActivity instance, SiteStore mSiteStore) {
    instance.mSiteStore = mSiteStore;
  }
}
