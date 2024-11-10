package org.wordpress.android.ui.reader;

import androidx.lifecycle.ViewModelProvider;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.ui.reader.tracker.ReaderTracker;
import org.wordpress.android.ui.utils.UiHelpers;

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
public final class ReaderCommentListActivity_MembersInjector implements MembersInjector<ReaderCommentListActivity> {
  private final Provider<AccountStore> mAccountStoreProvider;

  private final Provider<UiHelpers> mUiHelpersProvider;

  private final Provider<ViewModelProvider.Factory> mViewModelFactoryProvider;

  private final Provider<ReaderTracker> mReaderTrackerProvider;

  private final Provider<SiteStore> mSiteStoreProvider;

  public ReaderCommentListActivity_MembersInjector(Provider<AccountStore> mAccountStoreProvider,
      Provider<UiHelpers> mUiHelpersProvider,
      Provider<ViewModelProvider.Factory> mViewModelFactoryProvider,
      Provider<ReaderTracker> mReaderTrackerProvider, Provider<SiteStore> mSiteStoreProvider) {
    this.mAccountStoreProvider = mAccountStoreProvider;
    this.mUiHelpersProvider = mUiHelpersProvider;
    this.mViewModelFactoryProvider = mViewModelFactoryProvider;
    this.mReaderTrackerProvider = mReaderTrackerProvider;
    this.mSiteStoreProvider = mSiteStoreProvider;
  }

  public static MembersInjector<ReaderCommentListActivity> create(
      Provider<AccountStore> mAccountStoreProvider, Provider<UiHelpers> mUiHelpersProvider,
      Provider<ViewModelProvider.Factory> mViewModelFactoryProvider,
      Provider<ReaderTracker> mReaderTrackerProvider, Provider<SiteStore> mSiteStoreProvider) {
    return new ReaderCommentListActivity_MembersInjector(mAccountStoreProvider, mUiHelpersProvider, mViewModelFactoryProvider, mReaderTrackerProvider, mSiteStoreProvider);
  }

  @Override
  public void injectMembers(ReaderCommentListActivity instance) {
    injectMAccountStore(instance, mAccountStoreProvider.get());
    injectMUiHelpers(instance, mUiHelpersProvider.get());
    injectMViewModelFactory(instance, mViewModelFactoryProvider.get());
    injectMReaderTracker(instance, mReaderTrackerProvider.get());
    injectMSiteStore(instance, mSiteStoreProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.ReaderCommentListActivity.mAccountStore")
  public static void injectMAccountStore(ReaderCommentListActivity instance,
      AccountStore mAccountStore) {
    instance.mAccountStore = mAccountStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.ReaderCommentListActivity.mUiHelpers")
  public static void injectMUiHelpers(ReaderCommentListActivity instance, UiHelpers mUiHelpers) {
    instance.mUiHelpers = mUiHelpers;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.ReaderCommentListActivity.mViewModelFactory")
  public static void injectMViewModelFactory(ReaderCommentListActivity instance,
      ViewModelProvider.Factory mViewModelFactory) {
    instance.mViewModelFactory = mViewModelFactory;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.ReaderCommentListActivity.mReaderTracker")
  public static void injectMReaderTracker(ReaderCommentListActivity instance,
      ReaderTracker mReaderTracker) {
    instance.mReaderTracker = mReaderTracker;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.ReaderCommentListActivity.mSiteStore")
  public static void injectMSiteStore(ReaderCommentListActivity instance, SiteStore mSiteStore) {
    instance.mSiteStore = mSiteStore;
  }
}
