package org.wordpress.android.ui.publicize;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
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
public final class PublicizeWebViewFragment_MembersInjector implements MembersInjector<PublicizeWebViewFragment> {
  private final Provider<AccountStore> mAccountStoreProvider;

  public PublicizeWebViewFragment_MembersInjector(Provider<AccountStore> mAccountStoreProvider) {
    this.mAccountStoreProvider = mAccountStoreProvider;
  }

  public static MembersInjector<PublicizeWebViewFragment> create(
      Provider<AccountStore> mAccountStoreProvider) {
    return new PublicizeWebViewFragment_MembersInjector(mAccountStoreProvider);
  }

  @Override
  public void injectMembers(PublicizeWebViewFragment instance) {
    injectMAccountStore(instance, mAccountStoreProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.publicize.PublicizeWebViewFragment.mAccountStore")
  public static void injectMAccountStore(PublicizeWebViewFragment instance,
      AccountStore mAccountStore) {
    instance.mAccountStore = mAccountStore;
  }
}
