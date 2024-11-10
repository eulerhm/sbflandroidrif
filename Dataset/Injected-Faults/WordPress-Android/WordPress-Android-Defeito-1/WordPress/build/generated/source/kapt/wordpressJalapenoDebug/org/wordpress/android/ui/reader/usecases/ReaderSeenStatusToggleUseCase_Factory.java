package org.wordpress.android.ui.reader.usecases;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.datasets.ReaderBlogTableWrapper;
import org.wordpress.android.datasets.wrappers.ReaderPostTableWrapper;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.ui.reader.tracker.ReaderTracker;
import org.wordpress.android.ui.reader.utils.PostSeenStatusApiCallsProvider;
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
public final class ReaderSeenStatusToggleUseCase_Factory implements Factory<ReaderSeenStatusToggleUseCase> {
  private final Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider;

  private final Provider<PostSeenStatusApiCallsProvider> apiCallsProvider;

  private final Provider<AccountStore> accountStoreProvider;

  private final Provider<ReaderTracker> readerTrackerProvider;

  private final Provider<ReaderPostTableWrapper> readerPostTableWrapperProvider;

  private final Provider<ReaderBlogTableWrapper> readerBlogTableWrapperProvider;

  public ReaderSeenStatusToggleUseCase_Factory(
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider,
      Provider<PostSeenStatusApiCallsProvider> apiCallsProvider,
      Provider<AccountStore> accountStoreProvider, Provider<ReaderTracker> readerTrackerProvider,
      Provider<ReaderPostTableWrapper> readerPostTableWrapperProvider,
      Provider<ReaderBlogTableWrapper> readerBlogTableWrapperProvider) {
    this.networkUtilsWrapperProvider = networkUtilsWrapperProvider;
    this.apiCallsProvider = apiCallsProvider;
    this.accountStoreProvider = accountStoreProvider;
    this.readerTrackerProvider = readerTrackerProvider;
    this.readerPostTableWrapperProvider = readerPostTableWrapperProvider;
    this.readerBlogTableWrapperProvider = readerBlogTableWrapperProvider;
  }

  @Override
  public ReaderSeenStatusToggleUseCase get() {
    return newInstance(networkUtilsWrapperProvider.get(), apiCallsProvider.get(), accountStoreProvider.get(), readerTrackerProvider.get(), readerPostTableWrapperProvider.get(), readerBlogTableWrapperProvider.get());
  }

  public static ReaderSeenStatusToggleUseCase_Factory create(
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider,
      Provider<PostSeenStatusApiCallsProvider> apiCallsProvider,
      Provider<AccountStore> accountStoreProvider, Provider<ReaderTracker> readerTrackerProvider,
      Provider<ReaderPostTableWrapper> readerPostTableWrapperProvider,
      Provider<ReaderBlogTableWrapper> readerBlogTableWrapperProvider) {
    return new ReaderSeenStatusToggleUseCase_Factory(networkUtilsWrapperProvider, apiCallsProvider, accountStoreProvider, readerTrackerProvider, readerPostTableWrapperProvider, readerBlogTableWrapperProvider);
  }

  public static ReaderSeenStatusToggleUseCase newInstance(NetworkUtilsWrapper networkUtilsWrapper,
      PostSeenStatusApiCallsProvider apiCallsProvider, AccountStore accountStore,
      ReaderTracker readerTracker, ReaderPostTableWrapper readerPostTableWrapper,
      ReaderBlogTableWrapper readerBlogTableWrapper) {
    return new ReaderSeenStatusToggleUseCase(networkUtilsWrapper, apiCallsProvider, accountStore, readerTracker, readerPostTableWrapper, readerBlogTableWrapper);
  }
}
