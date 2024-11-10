package org.wordpress.android.ui.comments.unified;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.datasets.wrappers.ReaderCommentTableWrapper;
import org.wordpress.android.fluxc.store.CommentsStore;
import org.wordpress.android.models.usecases.LocalCommentCacheUpdateHandler;
import org.wordpress.android.ui.comments.unified.usecase.GetCommentUseCase;
import org.wordpress.android.ui.notifications.utils.NotificationsActionsWrapper;
import org.wordpress.android.util.NetworkUtilsWrapper;
import org.wordpress.android.util.analytics.AnalyticsUtilsWrapper;
import org.wordpress.android.viewmodel.ResourceProvider;

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
public final class UnifiedCommentsEditViewModel_Factory implements Factory<UnifiedCommentsEditViewModel> {
  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  private final Provider<CommentsStore> commentsStoreProvider;

  private final Provider<ResourceProvider> resourceProvider;

  private final Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider;

  private final Provider<LocalCommentCacheUpdateHandler> localCommentCacheUpdateHandlerProvider;

  private final Provider<GetCommentUseCase> getCommentUseCaseProvider;

  private final Provider<NotificationsActionsWrapper> notificationActionsWrapperProvider;

  private final Provider<ReaderCommentTableWrapper> readerCommentTableWrapperProvider;

  private final Provider<AnalyticsUtilsWrapper> analyticsUtilsWrapperProvider;

  public UnifiedCommentsEditViewModel_Factory(Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider,
      Provider<CommentsStore> commentsStoreProvider, Provider<ResourceProvider> resourceProvider,
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider,
      Provider<LocalCommentCacheUpdateHandler> localCommentCacheUpdateHandlerProvider,
      Provider<GetCommentUseCase> getCommentUseCaseProvider,
      Provider<NotificationsActionsWrapper> notificationActionsWrapperProvider,
      Provider<ReaderCommentTableWrapper> readerCommentTableWrapperProvider,
      Provider<AnalyticsUtilsWrapper> analyticsUtilsWrapperProvider) {
    this.mainDispatcherProvider = mainDispatcherProvider;
    this.bgDispatcherProvider = bgDispatcherProvider;
    this.commentsStoreProvider = commentsStoreProvider;
    this.resourceProvider = resourceProvider;
    this.networkUtilsWrapperProvider = networkUtilsWrapperProvider;
    this.localCommentCacheUpdateHandlerProvider = localCommentCacheUpdateHandlerProvider;
    this.getCommentUseCaseProvider = getCommentUseCaseProvider;
    this.notificationActionsWrapperProvider = notificationActionsWrapperProvider;
    this.readerCommentTableWrapperProvider = readerCommentTableWrapperProvider;
    this.analyticsUtilsWrapperProvider = analyticsUtilsWrapperProvider;
  }

  @Override
  public UnifiedCommentsEditViewModel get() {
    return newInstance(mainDispatcherProvider.get(), bgDispatcherProvider.get(), commentsStoreProvider.get(), resourceProvider.get(), networkUtilsWrapperProvider.get(), localCommentCacheUpdateHandlerProvider.get(), getCommentUseCaseProvider.get(), notificationActionsWrapperProvider.get(), readerCommentTableWrapperProvider.get(), analyticsUtilsWrapperProvider.get());
  }

  public static UnifiedCommentsEditViewModel_Factory create(
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider,
      Provider<CommentsStore> commentsStoreProvider, Provider<ResourceProvider> resourceProvider,
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider,
      Provider<LocalCommentCacheUpdateHandler> localCommentCacheUpdateHandlerProvider,
      Provider<GetCommentUseCase> getCommentUseCaseProvider,
      Provider<NotificationsActionsWrapper> notificationActionsWrapperProvider,
      Provider<ReaderCommentTableWrapper> readerCommentTableWrapperProvider,
      Provider<AnalyticsUtilsWrapper> analyticsUtilsWrapperProvider) {
    return new UnifiedCommentsEditViewModel_Factory(mainDispatcherProvider, bgDispatcherProvider, commentsStoreProvider, resourceProvider, networkUtilsWrapperProvider, localCommentCacheUpdateHandlerProvider, getCommentUseCaseProvider, notificationActionsWrapperProvider, readerCommentTableWrapperProvider, analyticsUtilsWrapperProvider);
  }

  public static UnifiedCommentsEditViewModel newInstance(CoroutineDispatcher mainDispatcher,
      CoroutineDispatcher bgDispatcher, CommentsStore commentsStore,
      ResourceProvider resourceProvider, NetworkUtilsWrapper networkUtilsWrapper,
      LocalCommentCacheUpdateHandler localCommentCacheUpdateHandler,
      GetCommentUseCase getCommentUseCase, NotificationsActionsWrapper notificationActionsWrapper,
      ReaderCommentTableWrapper readerCommentTableWrapper,
      AnalyticsUtilsWrapper analyticsUtilsWrapper) {
    return new UnifiedCommentsEditViewModel(mainDispatcher, bgDispatcher, commentsStore, resourceProvider, networkUtilsWrapper, localCommentCacheUpdateHandler, getCommentUseCase, notificationActionsWrapper, readerCommentTableWrapper, analyticsUtilsWrapper);
  }
}
