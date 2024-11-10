package org.wordpress.android.ui.reader.usecases;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.datasets.wrappers.ReaderPostTableWrapper;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.ui.reader.tracker.ReaderTracker;
import org.wordpress.android.ui.reader.utils.PostSubscribersApiCallsProvider;
import org.wordpress.android.util.NetworkUtilsWrapper;

@ScopeMetadata
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
public final class ReaderCommentsFollowUseCase_Factory implements Factory<ReaderCommentsFollowUseCase> {
  private final Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider;

  private final Provider<PostSubscribersApiCallsProvider> postSubscribersApiCallsProvider;

  private final Provider<AccountStore> accountStoreProvider;

  private final Provider<ReaderTracker> readerTrackerProvider;

  private final Provider<ReaderPostTableWrapper> readerPostTableWrapperProvider;

  public ReaderCommentsFollowUseCase_Factory(
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider,
      Provider<PostSubscribersApiCallsProvider> postSubscribersApiCallsProvider,
      Provider<AccountStore> accountStoreProvider, Provider<ReaderTracker> readerTrackerProvider,
      Provider<ReaderPostTableWrapper> readerPostTableWrapperProvider) {
    this.networkUtilsWrapperProvider = networkUtilsWrapperProvider;
    this.postSubscribersApiCallsProvider = postSubscribersApiCallsProvider;
    this.accountStoreProvider = accountStoreProvider;
    this.readerTrackerProvider = readerTrackerProvider;
    this.readerPostTableWrapperProvider = readerPostTableWrapperProvider;
  }

  @Override
  public ReaderCommentsFollowUseCase get() {
    return newInstance(networkUtilsWrapperProvider.get(), postSubscribersApiCallsProvider.get(), accountStoreProvider.get(), readerTrackerProvider.get(), readerPostTableWrapperProvider.get());
  }

  public static ReaderCommentsFollowUseCase_Factory create(
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider,
      Provider<PostSubscribersApiCallsProvider> postSubscribersApiCallsProvider,
      Provider<AccountStore> accountStoreProvider, Provider<ReaderTracker> readerTrackerProvider,
      Provider<ReaderPostTableWrapper> readerPostTableWrapperProvider) {
    return new ReaderCommentsFollowUseCase_Factory(networkUtilsWrapperProvider, postSubscribersApiCallsProvider, accountStoreProvider, readerTrackerProvider, readerPostTableWrapperProvider);
  }

  public static ReaderCommentsFollowUseCase newInstance(NetworkUtilsWrapper networkUtilsWrapper,
      PostSubscribersApiCallsProvider postSubscribersApiCallsProvider, AccountStore accountStore,
      ReaderTracker readerTracker, ReaderPostTableWrapper readerPostTableWrapper) {
    return new ReaderCommentsFollowUseCase(networkUtilsWrapper, postSubscribersApiCallsProvider, accountStore, readerTracker, readerPostTableWrapper);
  }
}
