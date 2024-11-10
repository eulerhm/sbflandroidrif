package org.wordpress.android.ui.reader.adapters;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.ui.reader.discover.ReaderPostMoreButtonUiStateBuilder;
import org.wordpress.android.ui.reader.discover.ReaderPostUiStateBuilder;
import org.wordpress.android.ui.reader.tracker.ReaderTracker;

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
public final class ReaderPostAdapter_MembersInjector implements MembersInjector<ReaderPostAdapter> {
  private final Provider<AccountStore> mAccountStoreProvider;

  private final Provider<SiteStore> mSiteStoreProvider;

  private final Provider<ReaderPostUiStateBuilder> mReaderPostUiStateBuilderProvider;

  private final Provider<ReaderPostMoreButtonUiStateBuilder> mReaderPostMoreButtonUiStateBuilderProvider;

  private final Provider<ReaderTracker> mReaderTrackerProvider;

  public ReaderPostAdapter_MembersInjector(Provider<AccountStore> mAccountStoreProvider,
      Provider<SiteStore> mSiteStoreProvider,
      Provider<ReaderPostUiStateBuilder> mReaderPostUiStateBuilderProvider,
      Provider<ReaderPostMoreButtonUiStateBuilder> mReaderPostMoreButtonUiStateBuilderProvider,
      Provider<ReaderTracker> mReaderTrackerProvider) {
    this.mAccountStoreProvider = mAccountStoreProvider;
    this.mSiteStoreProvider = mSiteStoreProvider;
    this.mReaderPostUiStateBuilderProvider = mReaderPostUiStateBuilderProvider;
    this.mReaderPostMoreButtonUiStateBuilderProvider = mReaderPostMoreButtonUiStateBuilderProvider;
    this.mReaderTrackerProvider = mReaderTrackerProvider;
  }

  public static MembersInjector<ReaderPostAdapter> create(
      Provider<AccountStore> mAccountStoreProvider, Provider<SiteStore> mSiteStoreProvider,
      Provider<ReaderPostUiStateBuilder> mReaderPostUiStateBuilderProvider,
      Provider<ReaderPostMoreButtonUiStateBuilder> mReaderPostMoreButtonUiStateBuilderProvider,
      Provider<ReaderTracker> mReaderTrackerProvider) {
    return new ReaderPostAdapter_MembersInjector(mAccountStoreProvider, mSiteStoreProvider, mReaderPostUiStateBuilderProvider, mReaderPostMoreButtonUiStateBuilderProvider, mReaderTrackerProvider);
  }

  @Override
  public void injectMembers(ReaderPostAdapter instance) {
    injectMAccountStore(instance, mAccountStoreProvider.get());
    injectMSiteStore(instance, mSiteStoreProvider.get());
    injectMReaderPostUiStateBuilder(instance, mReaderPostUiStateBuilderProvider.get());
    injectMReaderPostMoreButtonUiStateBuilder(instance, mReaderPostMoreButtonUiStateBuilderProvider.get());
    injectMReaderTracker(instance, mReaderTrackerProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.adapters.ReaderPostAdapter.mAccountStore")
  public static void injectMAccountStore(ReaderPostAdapter instance, AccountStore mAccountStore) {
    instance.mAccountStore = mAccountStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.adapters.ReaderPostAdapter.mSiteStore")
  public static void injectMSiteStore(ReaderPostAdapter instance, SiteStore mSiteStore) {
    instance.mSiteStore = mSiteStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.adapters.ReaderPostAdapter.mReaderPostUiStateBuilder")
  public static void injectMReaderPostUiStateBuilder(ReaderPostAdapter instance,
      ReaderPostUiStateBuilder mReaderPostUiStateBuilder) {
    instance.mReaderPostUiStateBuilder = mReaderPostUiStateBuilder;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.adapters.ReaderPostAdapter.mReaderPostMoreButtonUiStateBuilder")
  public static void injectMReaderPostMoreButtonUiStateBuilder(ReaderPostAdapter instance,
      ReaderPostMoreButtonUiStateBuilder mReaderPostMoreButtonUiStateBuilder) {
    instance.mReaderPostMoreButtonUiStateBuilder = mReaderPostMoreButtonUiStateBuilder;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.adapters.ReaderPostAdapter.mReaderTracker")
  public static void injectMReaderTracker(ReaderPostAdapter instance,
      ReaderTracker mReaderTracker) {
    instance.mReaderTracker = mReaderTracker;
  }
}
