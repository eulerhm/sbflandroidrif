package org.wordpress.android.ui.reader.repository.usecases;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.ui.reader.actions.ReaderPostActionsWrapper;
import org.wordpress.android.ui.reader.tracker.ReaderTracker;
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
public final class PostLikeUseCase_Factory implements Factory<PostLikeUseCase> {
  private final Provider<ReaderPostActionsWrapper> readerPostActionsWrapperProvider;

  private final Provider<ReaderTracker> readerTrackerProvider;

  private final Provider<AccountStore> accountStoreProvider;

  private final Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider;

  public PostLikeUseCase_Factory(
      Provider<ReaderPostActionsWrapper> readerPostActionsWrapperProvider,
      Provider<ReaderTracker> readerTrackerProvider, Provider<AccountStore> accountStoreProvider,
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider) {
    this.readerPostActionsWrapperProvider = readerPostActionsWrapperProvider;
    this.readerTrackerProvider = readerTrackerProvider;
    this.accountStoreProvider = accountStoreProvider;
    this.networkUtilsWrapperProvider = networkUtilsWrapperProvider;
  }

  @Override
  public PostLikeUseCase get() {
    return newInstance(readerPostActionsWrapperProvider.get(), readerTrackerProvider.get(), accountStoreProvider.get(), networkUtilsWrapperProvider.get());
  }

  public static PostLikeUseCase_Factory create(
      Provider<ReaderPostActionsWrapper> readerPostActionsWrapperProvider,
      Provider<ReaderTracker> readerTrackerProvider, Provider<AccountStore> accountStoreProvider,
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider) {
    return new PostLikeUseCase_Factory(readerPostActionsWrapperProvider, readerTrackerProvider, accountStoreProvider, networkUtilsWrapperProvider);
  }

  public static PostLikeUseCase newInstance(ReaderPostActionsWrapper readerPostActionsWrapper,
      ReaderTracker readerTracker, AccountStore accountStore,
      NetworkUtilsWrapper networkUtilsWrapper) {
    return new PostLikeUseCase(readerPostActionsWrapper, readerTracker, accountStore, networkUtilsWrapper);
  }
}
