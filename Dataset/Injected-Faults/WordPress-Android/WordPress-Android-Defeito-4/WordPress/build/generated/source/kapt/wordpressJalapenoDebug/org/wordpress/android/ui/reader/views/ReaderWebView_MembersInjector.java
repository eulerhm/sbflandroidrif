package org.wordpress.android.ui.reader.views;

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
public final class ReaderWebView_MembersInjector implements MembersInjector<ReaderWebView> {
  private final Provider<AccountStore> mAccountStoreProvider;

  public ReaderWebView_MembersInjector(Provider<AccountStore> mAccountStoreProvider) {
    this.mAccountStoreProvider = mAccountStoreProvider;
  }

  public static MembersInjector<ReaderWebView> create(
      Provider<AccountStore> mAccountStoreProvider) {
    return new ReaderWebView_MembersInjector(mAccountStoreProvider);
  }

  @Override
  public void injectMembers(ReaderWebView instance) {
    injectMAccountStore(instance, mAccountStoreProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.views.ReaderWebView.mAccountStore")
  public static void injectMAccountStore(ReaderWebView instance, AccountStore mAccountStore) {
    instance.mAccountStore = mAccountStore;
  }
}
