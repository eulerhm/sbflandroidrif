package org.wordpress.android.ui;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.util.image.ImageManager;

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
public final class ShareIntentReceiverFragment_MembersInjector implements MembersInjector<ShareIntentReceiverFragment> {
  private final Provider<AccountStore> mAccountStoreProvider;

  private final Provider<ImageManager> mImageManagerProvider;

  public ShareIntentReceiverFragment_MembersInjector(Provider<AccountStore> mAccountStoreProvider,
      Provider<ImageManager> mImageManagerProvider) {
    this.mAccountStoreProvider = mAccountStoreProvider;
    this.mImageManagerProvider = mImageManagerProvider;
  }

  public static MembersInjector<ShareIntentReceiverFragment> create(
      Provider<AccountStore> mAccountStoreProvider, Provider<ImageManager> mImageManagerProvider) {
    return new ShareIntentReceiverFragment_MembersInjector(mAccountStoreProvider, mImageManagerProvider);
  }

  @Override
  public void injectMembers(ShareIntentReceiverFragment instance) {
    injectMAccountStore(instance, mAccountStoreProvider.get());
    injectMImageManager(instance, mImageManagerProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.ShareIntentReceiverFragment.mAccountStore")
  public static void injectMAccountStore(ShareIntentReceiverFragment instance,
      AccountStore mAccountStore) {
    instance.mAccountStore = mAccountStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.ShareIntentReceiverFragment.mImageManager")
  public static void injectMImageManager(ShareIntentReceiverFragment instance,
      ImageManager mImageManager) {
    instance.mImageManager = mImageManager;
  }
}
