package org.wordpress.android.ui.reader.usecases;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.datasets.wrappers.ReaderPostTableWrapper;
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
public final class ReaderPostBookmarkUseCase_Factory implements Factory<ReaderPostBookmarkUseCase> {
  private final Provider<ReaderTracker> readerTrackerProvider;

  private final Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider;

  private final Provider<ReaderPostActionsWrapper> readerPostActionsWrapperProvider;

  private final Provider<ReaderPostTableWrapper> readerPostTableWrapperProvider;

  public ReaderPostBookmarkUseCase_Factory(Provider<ReaderTracker> readerTrackerProvider,
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider,
      Provider<ReaderPostActionsWrapper> readerPostActionsWrapperProvider,
      Provider<ReaderPostTableWrapper> readerPostTableWrapperProvider) {
    this.readerTrackerProvider = readerTrackerProvider;
    this.networkUtilsWrapperProvider = networkUtilsWrapperProvider;
    this.readerPostActionsWrapperProvider = readerPostActionsWrapperProvider;
    this.readerPostTableWrapperProvider = readerPostTableWrapperProvider;
  }

  @Override
  public ReaderPostBookmarkUseCase get() {
    return newInstance(readerTrackerProvider.get(), networkUtilsWrapperProvider.get(), readerPostActionsWrapperProvider.get(), readerPostTableWrapperProvider.get());
  }

  public static ReaderPostBookmarkUseCase_Factory create(
      Provider<ReaderTracker> readerTrackerProvider,
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider,
      Provider<ReaderPostActionsWrapper> readerPostActionsWrapperProvider,
      Provider<ReaderPostTableWrapper> readerPostTableWrapperProvider) {
    return new ReaderPostBookmarkUseCase_Factory(readerTrackerProvider, networkUtilsWrapperProvider, readerPostActionsWrapperProvider, readerPostTableWrapperProvider);
  }

  public static ReaderPostBookmarkUseCase newInstance(ReaderTracker readerTracker,
      NetworkUtilsWrapper networkUtilsWrapper, ReaderPostActionsWrapper readerPostActionsWrapper,
      ReaderPostTableWrapper readerPostTableWrapper) {
    return new ReaderPostBookmarkUseCase(readerTracker, networkUtilsWrapper, readerPostActionsWrapper, readerPostTableWrapper);
  }
}
