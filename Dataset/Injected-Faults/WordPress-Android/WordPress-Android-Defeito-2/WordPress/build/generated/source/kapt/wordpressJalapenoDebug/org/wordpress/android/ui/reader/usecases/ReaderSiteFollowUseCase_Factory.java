package org.wordpress.android.ui.reader.usecases;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.datasets.ReaderBlogTableWrapper;
import org.wordpress.android.ui.reader.actions.ReaderBlogActionsWrapper;
import org.wordpress.android.ui.reader.tracker.ReaderTracker;
import org.wordpress.android.ui.reader.utils.ReaderUtilsWrapper;
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
public final class ReaderSiteFollowUseCase_Factory implements Factory<ReaderSiteFollowUseCase> {
  private final Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider;

  private final Provider<ReaderBlogActionsWrapper> readerBlogActionsWrapperProvider;

  private final Provider<ReaderBlogTableWrapper> readerBlogTableWrapperProvider;

  private final Provider<ReaderUtilsWrapper> readerUtilsWrapperProvider;

  private final Provider<ReaderTracker> readerTrackerProvider;

  public ReaderSiteFollowUseCase_Factory(Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider,
      Provider<ReaderBlogActionsWrapper> readerBlogActionsWrapperProvider,
      Provider<ReaderBlogTableWrapper> readerBlogTableWrapperProvider,
      Provider<ReaderUtilsWrapper> readerUtilsWrapperProvider,
      Provider<ReaderTracker> readerTrackerProvider) {
    this.networkUtilsWrapperProvider = networkUtilsWrapperProvider;
    this.readerBlogActionsWrapperProvider = readerBlogActionsWrapperProvider;
    this.readerBlogTableWrapperProvider = readerBlogTableWrapperProvider;
    this.readerUtilsWrapperProvider = readerUtilsWrapperProvider;
    this.readerTrackerProvider = readerTrackerProvider;
  }

  @Override
  public ReaderSiteFollowUseCase get() {
    return newInstance(networkUtilsWrapperProvider.get(), readerBlogActionsWrapperProvider.get(), readerBlogTableWrapperProvider.get(), readerUtilsWrapperProvider.get(), readerTrackerProvider.get());
  }

  public static ReaderSiteFollowUseCase_Factory create(
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider,
      Provider<ReaderBlogActionsWrapper> readerBlogActionsWrapperProvider,
      Provider<ReaderBlogTableWrapper> readerBlogTableWrapperProvider,
      Provider<ReaderUtilsWrapper> readerUtilsWrapperProvider,
      Provider<ReaderTracker> readerTrackerProvider) {
    return new ReaderSiteFollowUseCase_Factory(networkUtilsWrapperProvider, readerBlogActionsWrapperProvider, readerBlogTableWrapperProvider, readerUtilsWrapperProvider, readerTrackerProvider);
  }

  public static ReaderSiteFollowUseCase newInstance(NetworkUtilsWrapper networkUtilsWrapper,
      ReaderBlogActionsWrapper readerBlogActionsWrapper,
      ReaderBlogTableWrapper readerBlogTableWrapper, ReaderUtilsWrapper readerUtilsWrapper,
      ReaderTracker readerTracker) {
    return new ReaderSiteFollowUseCase(networkUtilsWrapper, readerBlogActionsWrapper, readerBlogTableWrapper, readerUtilsWrapper, readerTracker);
  }
}
