package org.wordpress.android.ui.reader.views;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.ui.reader.tracker.ReaderTracker;
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
public final class ReaderSiteHeaderView_MembersInjector implements MembersInjector<ReaderSiteHeaderView> {
  private final Provider<AccountStore> mAccountStoreProvider;

  private final Provider<ImageManager> mImageManagerProvider;

  private final Provider<ReaderTracker> mReaderTrackerProvider;

  public ReaderSiteHeaderView_MembersInjector(Provider<AccountStore> mAccountStoreProvider,
      Provider<ImageManager> mImageManagerProvider,
      Provider<ReaderTracker> mReaderTrackerProvider) {
    this.mAccountStoreProvider = mAccountStoreProvider;
    this.mImageManagerProvider = mImageManagerProvider;
    this.mReaderTrackerProvider = mReaderTrackerProvider;
  }

  public static MembersInjector<ReaderSiteHeaderView> create(
      Provider<AccountStore> mAccountStoreProvider, Provider<ImageManager> mImageManagerProvider,
      Provider<ReaderTracker> mReaderTrackerProvider) {
    return new ReaderSiteHeaderView_MembersInjector(mAccountStoreProvider, mImageManagerProvider, mReaderTrackerProvider);
  }

  @Override
  public void injectMembers(ReaderSiteHeaderView instance) {
    injectMAccountStore(instance, mAccountStoreProvider.get());
    injectMImageManager(instance, mImageManagerProvider.get());
    injectMReaderTracker(instance, mReaderTrackerProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.views.ReaderSiteHeaderView.mAccountStore")
  public static void injectMAccountStore(ReaderSiteHeaderView instance,
      AccountStore mAccountStore) {
    instance.mAccountStore = mAccountStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.views.ReaderSiteHeaderView.mImageManager")
  public static void injectMImageManager(ReaderSiteHeaderView instance,
      ImageManager mImageManager) {
    instance.mImageManager = mImageManager;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.views.ReaderSiteHeaderView.mReaderTracker")
  public static void injectMReaderTracker(ReaderSiteHeaderView instance,
      ReaderTracker mReaderTracker) {
    instance.mReaderTracker = mReaderTracker;
  }
}
