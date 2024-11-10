package org.wordpress.android.ui.reader.adapters;

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
public final class ReaderTagAdapter_MembersInjector implements MembersInjector<ReaderTagAdapter> {
  private final Provider<AccountStore> mAccountStoreProvider;

  public ReaderTagAdapter_MembersInjector(Provider<AccountStore> mAccountStoreProvider) {
    this.mAccountStoreProvider = mAccountStoreProvider;
  }

  public static MembersInjector<ReaderTagAdapter> create(
      Provider<AccountStore> mAccountStoreProvider) {
    return new ReaderTagAdapter_MembersInjector(mAccountStoreProvider);
  }

  @Override
  public void injectMembers(ReaderTagAdapter instance) {
    injectMAccountStore(instance, mAccountStoreProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.adapters.ReaderTagAdapter.mAccountStore")
  public static void injectMAccountStore(ReaderTagAdapter instance, AccountStore mAccountStore) {
    instance.mAccountStore = mAccountStore;
  }
}
