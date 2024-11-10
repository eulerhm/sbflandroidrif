package org.wordpress.android.ui.reader;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.store.AccountStore;
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
public final class ReaderSubsActivity_MembersInjector implements MembersInjector<ReaderSubsActivity> {
  private final Provider<AccountStore> mAccountStoreProvider;

  private final Provider<ReaderTracker> mReaderTrackerProvider;

  public ReaderSubsActivity_MembersInjector(Provider<AccountStore> mAccountStoreProvider,
      Provider<ReaderTracker> mReaderTrackerProvider) {
    this.mAccountStoreProvider = mAccountStoreProvider;
    this.mReaderTrackerProvider = mReaderTrackerProvider;
  }

  public static MembersInjector<ReaderSubsActivity> create(
      Provider<AccountStore> mAccountStoreProvider,
      Provider<ReaderTracker> mReaderTrackerProvider) {
    return new ReaderSubsActivity_MembersInjector(mAccountStoreProvider, mReaderTrackerProvider);
  }

  @Override
  public void injectMembers(ReaderSubsActivity instance) {
    injectMAccountStore(instance, mAccountStoreProvider.get());
    injectMReaderTracker(instance, mReaderTrackerProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.ReaderSubsActivity.mAccountStore")
  public static void injectMAccountStore(ReaderSubsActivity instance, AccountStore mAccountStore) {
    instance.mAccountStore = mAccountStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.ReaderSubsActivity.mReaderTracker")
  public static void injectMReaderTracker(ReaderSubsActivity instance,
      ReaderTracker mReaderTracker) {
    instance.mReaderTracker = mReaderTracker;
  }
}
