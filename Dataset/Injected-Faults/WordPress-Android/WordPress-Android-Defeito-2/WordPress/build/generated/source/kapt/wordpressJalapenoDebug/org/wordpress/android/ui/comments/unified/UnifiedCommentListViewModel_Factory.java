package org.wordpress.android.ui.comments.unified;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.models.usecases.LocalCommentCacheUpdateHandler;
import org.wordpress.android.models.usecases.UnifiedCommentsListHandler;
import org.wordpress.android.ui.mysite.SelectedSiteRepository;
import org.wordpress.android.util.NetworkUtilsWrapper;
import org.wordpress.android.util.analytics.AnalyticsTrackerWrapper;

@ScopeMetadata
@QualifierMetadata("javax.inject.Named")
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class UnifiedCommentListViewModel_Factory implements Factory<UnifiedCommentListViewModel> {
  private final Provider<CommentListUiModelHelper> commentListUiModelHelperProvider;

  private final Provider<SelectedSiteRepository> selectedSiteRepositoryProvider;

  private final Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider;

  private final Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider;

  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  private final Provider<UnifiedCommentsListHandler> unifiedCommentsListHandlerProvider;

  private final Provider<LocalCommentCacheUpdateHandler> localCommentCacheUpdateHandlerProvider;

  public UnifiedCommentListViewModel_Factory(
      Provider<CommentListUiModelHelper> commentListUiModelHelperProvider,
      Provider<SelectedSiteRepository> selectedSiteRepositoryProvider,
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider,
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<UnifiedCommentsListHandler> unifiedCommentsListHandlerProvider,
      Provider<LocalCommentCacheUpdateHandler> localCommentCacheUpdateHandlerProvider) {
    this.commentListUiModelHelperProvider = commentListUiModelHelperProvider;
    this.selectedSiteRepositoryProvider = selectedSiteRepositoryProvider;
    this.networkUtilsWrapperProvider = networkUtilsWrapperProvider;
    this.analyticsTrackerWrapperProvider = analyticsTrackerWrapperProvider;
    this.bgDispatcherProvider = bgDispatcherProvider;
    this.mainDispatcherProvider = mainDispatcherProvider;
    this.unifiedCommentsListHandlerProvider = unifiedCommentsListHandlerProvider;
    this.localCommentCacheUpdateHandlerProvider = localCommentCacheUpdateHandlerProvider;
  }

  @Override
  public UnifiedCommentListViewModel get() {
    return newInstance(commentListUiModelHelperProvider.get(), selectedSiteRepositoryProvider.get(), networkUtilsWrapperProvider.get(), analyticsTrackerWrapperProvider.get(), bgDispatcherProvider.get(), mainDispatcherProvider.get(), unifiedCommentsListHandlerProvider.get(), localCommentCacheUpdateHandlerProvider.get());
  }

  public static UnifiedCommentListViewModel_Factory create(
      Provider<CommentListUiModelHelper> commentListUiModelHelperProvider,
      Provider<SelectedSiteRepository> selectedSiteRepositoryProvider,
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider,
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<UnifiedCommentsListHandler> unifiedCommentsListHandlerProvider,
      Provider<LocalCommentCacheUpdateHandler> localCommentCacheUpdateHandlerProvider) {
    return new UnifiedCommentListViewModel_Factory(commentListUiModelHelperProvider, selectedSiteRepositoryProvider, networkUtilsWrapperProvider, analyticsTrackerWrapperProvider, bgDispatcherProvider, mainDispatcherProvider, unifiedCommentsListHandlerProvider, localCommentCacheUpdateHandlerProvider);
  }

  public static UnifiedCommentListViewModel newInstance(
      CommentListUiModelHelper commentListUiModelHelper,
      SelectedSiteRepository selectedSiteRepository, NetworkUtilsWrapper networkUtilsWrapper,
      AnalyticsTrackerWrapper analyticsTrackerWrapper, CoroutineDispatcher bgDispatcher,
      CoroutineDispatcher mainDispatcher, UnifiedCommentsListHandler unifiedCommentsListHandler,
      LocalCommentCacheUpdateHandler localCommentCacheUpdateHandler) {
    return new UnifiedCommentListViewModel(commentListUiModelHelper, selectedSiteRepository, networkUtilsWrapper, analyticsTrackerWrapper, bgDispatcher, mainDispatcher, unifiedCommentsListHandler, localCommentCacheUpdateHandler);
  }
}
