package org.wordpress.android.ui.accounts;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.support.SupportHelper;
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
public final class HelpActivity_MembersInjector implements MembersInjector<HelpActivity> {
  private final Provider<AccountStore> accountStoreProvider;

  private final Provider<SiteStore> siteStoreProvider;

  private final Provider<SupportHelper> supportHelperProvider;

  private final Provider<ZendeskHelper> zendeskHelperProvider;

  public HelpActivity_MembersInjector(Provider<AccountStore> accountStoreProvider,
      Provider<SiteStore> siteStoreProvider, Provider<SupportHelper> supportHelperProvider,
      Provider<ZendeskHelper> zendeskHelperProvider) {
    this.accountStoreProvider = accountStoreProvider;
    this.siteStoreProvider = siteStoreProvider;
    this.supportHelperProvider = supportHelperProvider;
    this.zendeskHelperProvider = zendeskHelperProvider;
  }

  public static MembersInjector<HelpActivity> create(Provider<AccountStore> accountStoreProvider,
      Provider<SiteStore> siteStoreProvider, Provider<SupportHelper> supportHelperProvider,
      Provider<ZendeskHelper> zendeskHelperProvider) {
    return new HelpActivity_MembersInjector(accountStoreProvider, siteStoreProvider, supportHelperProvider, zendeskHelperProvider);
  }

  @Override
  public void injectMembers(HelpActivity instance) {
    injectAccountStore(instance, accountStoreProvider.get());
    injectSiteStore(instance, siteStoreProvider.get());
    injectSupportHelper(instance, supportHelperProvider.get());
    injectZendeskHelper(instance, zendeskHelperProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.accounts.HelpActivity.accountStore")
  public static void injectAccountStore(HelpActivity instance, AccountStore accountStore) {
    instance.accountStore = accountStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.accounts.HelpActivity.siteStore")
  public static void injectSiteStore(HelpActivity instance, SiteStore siteStore) {
    instance.siteStore = siteStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.accounts.HelpActivity.supportHelper")
  public static void injectSupportHelper(HelpActivity instance, SupportHelper supportHelper) {
    instance.supportHelper = supportHelper;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.accounts.HelpActivity.zendeskHelper")
  public static void injectZendeskHelper(HelpActivity instance, ZendeskHelper zendeskHelper) {
    instance.zendeskHelper = zendeskHelper;
  }
}
