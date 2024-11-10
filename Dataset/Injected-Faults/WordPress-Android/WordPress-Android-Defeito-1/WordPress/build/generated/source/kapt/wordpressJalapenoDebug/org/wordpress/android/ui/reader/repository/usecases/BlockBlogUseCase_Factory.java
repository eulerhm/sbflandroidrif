package org.wordpress.android.ui.reader.repository.usecases;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.reader.actions.ReaderBlogActionsWrapper;
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
public final class BlockBlogUseCase_Factory implements Factory<BlockBlogUseCase> {
  private final Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider;

  private final Provider<ReaderTracker> readerTrackerProvider;

  private final Provider<ReaderBlogActionsWrapper> readerBlogActionsWrapperProvider;

  public BlockBlogUseCase_Factory(Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider,
      Provider<ReaderTracker> readerTrackerProvider,
      Provider<ReaderBlogActionsWrapper> readerBlogActionsWrapperProvider) {
    this.networkUtilsWrapperProvider = networkUtilsWrapperProvider;
    this.readerTrackerProvider = readerTrackerProvider;
    this.readerBlogActionsWrapperProvider = readerBlogActionsWrapperProvider;
  }

  @Override
  public BlockBlogUseCase get() {
    return newInstance(networkUtilsWrapperProvider.get(), readerTrackerProvider.get(), readerBlogActionsWrapperProvider.get());
  }

  public static BlockBlogUseCase_Factory create(
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider,
      Provider<ReaderTracker> readerTrackerProvider,
      Provider<ReaderBlogActionsWrapper> readerBlogActionsWrapperProvider) {
    return new BlockBlogUseCase_Factory(networkUtilsWrapperProvider, readerTrackerProvider, readerBlogActionsWrapperProvider);
  }

  public static BlockBlogUseCase newInstance(NetworkUtilsWrapper networkUtilsWrapper,
      ReaderTracker readerTracker, ReaderBlogActionsWrapper readerBlogActionsWrapper) {
    return new BlockBlogUseCase(networkUtilsWrapper, readerTracker, readerBlogActionsWrapper);
  }
}
