package org.wordpress.android.push;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.store.AccountStore;
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
public final class GCMRegistrationIntentService_MembersInjector implements MembersInjector<GCMRegistrationIntentService> {
  private final Provider<AccountStore> mAccountStoreProvider;

  private final Provider<ZendeskHelper> mZendeskHelperProvider;

  public GCMRegistrationIntentService_MembersInjector(Provider<AccountStore> mAccountStoreProvider,
      Provider<ZendeskHelper> mZendeskHelperProvider) {
    this.mAccountStoreProvider = mAccountStoreProvider;
    this.mZendeskHelperProvider = mZendeskHelperProvider;
  }

  public static MembersInjector<GCMRegistrationIntentService> create(
      Provider<AccountStore> mAccountStoreProvider,
      Provider<ZendeskHelper> mZendeskHelperProvider) {
    return new GCMRegistrationIntentService_MembersInjector(mAccountStoreProvider, mZendeskHelperProvider);
  }

  @Override
  public void injectMembers(GCMRegistrationIntentService instance) {
    injectMAccountStore(instance, mAccountStoreProvider.get());
    injectMZendeskHelper(instance, mZendeskHelperProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.push.GCMRegistrationIntentService.mAccountStore")
  public static void injectMAccountStore(GCMRegistrationIntentService instance,
      AccountStore mAccountStore) {
    instance.mAccountStore = mAccountStore;
  }

  @InjectedFieldSignature("org.wordpress.android.push.GCMRegistrationIntentService.mZendeskHelper")
  public static void injectMZendeskHelper(GCMRegistrationIntentService instance,
      ZendeskHelper mZendeskHelper) {
    instance.mZendeskHelper = mZendeskHelper;
  }
}
