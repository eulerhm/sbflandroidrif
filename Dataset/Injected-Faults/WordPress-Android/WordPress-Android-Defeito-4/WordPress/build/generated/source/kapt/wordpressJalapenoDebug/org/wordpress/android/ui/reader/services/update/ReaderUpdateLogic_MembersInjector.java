package org.wordpress.android.ui.reader.services.update;

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
public final class ReaderUpdateLogic_MembersInjector implements MembersInjector<ReaderUpdateLogic> {
  private final Provider<AccountStore> mAccountStoreProvider;

  private final Provider<TagUpdateClientUtilsProvider> mClientUtilsProvider;

  public ReaderUpdateLogic_MembersInjector(Provider<AccountStore> mAccountStoreProvider,
      Provider<TagUpdateClientUtilsProvider> mClientUtilsProvider) {
    this.mAccountStoreProvider = mAccountStoreProvider;
    this.mClientUtilsProvider = mClientUtilsProvider;
  }

  public static MembersInjector<ReaderUpdateLogic> create(
      Provider<AccountStore> mAccountStoreProvider,
      Provider<TagUpdateClientUtilsProvider> mClientUtilsProvider) {
    return new ReaderUpdateLogic_MembersInjector(mAccountStoreProvider, mClientUtilsProvider);
  }

  @Override
  public void injectMembers(ReaderUpdateLogic instance) {
    injectMAccountStore(instance, mAccountStoreProvider.get());
    injectMClientUtilsProvider(instance, mClientUtilsProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.services.update.ReaderUpdateLogic.mAccountStore")
  public static void injectMAccountStore(ReaderUpdateLogic instance, AccountStore mAccountStore) {
    instance.mAccountStore = mAccountStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.services.update.ReaderUpdateLogic.mClientUtilsProvider")
  public static void injectMClientUtilsProvider(ReaderUpdateLogic instance,
      TagUpdateClientUtilsProvider mClientUtilsProvider) {
    instance.mClientUtilsProvider = mClientUtilsProvider;
  }
}
