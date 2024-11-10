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
public final class PublicizeDetailFragment_MembersInjector implements MembersInjector<PublicizeDetailFragment> {
  private final Provider<AccountStore> mAccountStoreProvider;

  public PublicizeDetailFragment_MembersInjector(Provider<AccountStore> mAccountStoreProvider) {
    this.mAccountStoreProvider = mAccountStoreProvider;
  }

  public static MembersInjector<PublicizeDetailFragment> create(
      Provider<AccountStore> mAccountStoreProvider) {
    return new PublicizeDetailFragment_MembersInjector(mAccountStoreProvider);
  }

  @Override
  public void injectMembers(PublicizeDetailFragment instance) {
    injectMAccountStore(instance, mAccountStoreProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.publicize.PublicizeDetailFragment.mAccountStore")
  public static void injectMAccountStore(PublicizeDetailFragment instance,
      AccountStore mAccountStore) {
    instance.mAccountStore = mAccountStore;
  }
}
