package org.wordpress.android.ui.reader.viewmodels;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.datasets.wrappers.ReaderCommentTableWrapper;
import org.wordpress.android.datasets.wrappers.ReaderPostTableWrapper;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.ui.engagement.EngagementUtils;
import org.wordpress.android.ui.engagement.GetLikesHandler;
import org.wordpress.android.ui.reader.ReaderPostDetailUiStateBuilder;
import org.wordpress.android.ui.reader.discover.ReaderPostCardActionsHandler;
import org.wordpress.android.ui.reader.discover.ReaderPostMoreButtonUiStateBuilder;
import org.wordpress.android.ui.reader.reblog.ReblogUseCase;
import org.wordpress.android.ui.reader.services.comment.wrapper.ReaderCommentServiceStarterWrapper;
import org.wordpress.android.ui.reader.tracker.ReaderTracker;
import org.wordpress.android.ui.reader.usecases.ReaderFetchPostUseCase;
import org.wordpress.android.ui.reader.usecases.ReaderFetchRelatedPostsUseCase;
import org.wordpress.android.ui.reader.usecases.ReaderGetPostUseCase;
import org.wordpress.android.ui.reader.utils.ReaderUtilsWrapper;
import org.wordpress.android.ui.utils.HtmlMessageUtils;
import org.wordpress.android.util.EventBusWrapper;
import org.wordpress.android.util.NetworkUtilsWrapper;
import org.wordpress.android.util.WpUrlUtilsWrapper;
import org.wordpress.android.util.config.CommentsSnippetFeatureConfig;
import org.wordpress.android.util.config.LikesEnhancementsFeatureConfig;
import org.wordpress.android.viewmodel.ContextProvider;

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
public final class ReaderPostDetailViewModel_Factory implements Factory<ReaderPostDetailViewModel> {
  private final Provider<ReaderPostCardActionsHandler> readerPostCardActionsHandlerProvider;

  private final Provider<ReaderUtilsWrapper> readerUtilsWrapperProvider;

  private final Provider<ReaderPostTableWrapper> readerPostTableWrapperProvider;

  private final Provider<ReaderPostMoreButtonUiStateBuilder> readerPostMoreButtonUiStateBuilderProvider;

  private final Provider<ReaderPostDetailUiStateBuilder> postDetailUiStateBuilderProvider;

  private final Provider<ReblogUseCase> reblogUseCaseProvider;

  private final Provider<ReaderFetchRelatedPostsUseCase> readerFetchRelatedPostsUseCaseProvider;

  private final Provider<ReaderGetPostUseCase> readerGetPostUseCaseProvider;

  private final Provider<ReaderFetchPostUseCase> readerFetchPostUseCaseProvider;

  private final Provider<SiteStore> siteStoreProvider;

  private final Provider<AccountStore> accountStoreProvider;

  private final Provider<ReaderTracker> readerTrackerProvider;

  private final Provider<EventBusWrapper> eventBusWrapperProvider;

  private final Provider<WpUrlUtilsWrapper> wpUrlUtilsWrapperProvider;

  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  private final Provider<CoroutineDispatcher> ioDispatcherProvider;

  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  private final Provider<GetLikesHandler> getLikesHandlerProvider;

  private final Provider<LikesEnhancementsFeatureConfig> likesEnhancementsFeatureConfigProvider;

  private final Provider<EngagementUtils> engagementUtilsProvider;

  private final Provider<HtmlMessageUtils> htmlMessageUtilsProvider;

  private final Provider<ContextProvider> contextProvider;

  private final Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider;

  private final Provider<CommentsSnippetFeatureConfig> commentsSnippetFeatureConfigProvider;

  private final Provider<ReaderCommentTableWrapper> readerCommentTableWrapperProvider;

  private final Provider<ReaderCommentServiceStarterWrapper> readerCommentServiceStarterWrapperProvider;

  public ReaderPostDetailViewModel_Factory(
      Provider<ReaderPostCardActionsHandler> readerPostCardActionsHandlerProvider,
      Provider<ReaderUtilsWrapper> readerUtilsWrapperProvider,
      Provider<ReaderPostTableWrapper> readerPostTableWrapperProvider,
      Provider<ReaderPostMoreButtonUiStateBuilder> readerPostMoreButtonUiStateBuilderProvider,
      Provider<ReaderPostDetailUiStateBuilder> postDetailUiStateBuilderProvider,
      Provider<ReblogUseCase> reblogUseCaseProvider,
      Provider<ReaderFetchRelatedPostsUseCase> readerFetchRelatedPostsUseCaseProvider,
      Provider<ReaderGetPostUseCase> readerGetPostUseCaseProvider,
      Provider<ReaderFetchPostUseCase> readerFetchPostUseCaseProvider,
      Provider<SiteStore> siteStoreProvider, Provider<AccountStore> accountStoreProvider,
      Provider<ReaderTracker> readerTrackerProvider,
      Provider<EventBusWrapper> eventBusWrapperProvider,
      Provider<WpUrlUtilsWrapper> wpUrlUtilsWrapperProvider,
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> ioDispatcherProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider,
      Provider<GetLikesHandler> getLikesHandlerProvider,
      Provider<LikesEnhancementsFeatureConfig> likesEnhancementsFeatureConfigProvider,
      Provider<EngagementUtils> engagementUtilsProvider,
      Provider<HtmlMessageUtils> htmlMessageUtilsProvider,
      Provider<ContextProvider> contextProvider,
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider,
      Provider<CommentsSnippetFeatureConfig> commentsSnippetFeatureConfigProvider,
      Provider<ReaderCommentTableWrapper> readerCommentTableWrapperProvider,
      Provider<ReaderCommentServiceStarterWrapper> readerCommentServiceStarterWrapperProvider) {
    this.readerPostCardActionsHandlerProvider = readerPostCardActionsHandlerProvider;
    this.readerUtilsWrapperProvider = readerUtilsWrapperProvider;
    this.readerPostTableWrapperProvider = readerPostTableWrapperProvider;
    this.readerPostMoreButtonUiStateBuilderProvider = readerPostMoreButtonUiStateBuilderProvider;
    this.postDetailUiStateBuilderProvider = postDetailUiStateBuilderProvider;
    this.reblogUseCaseProvider = reblogUseCaseProvider;
    this.readerFetchRelatedPostsUseCaseProvider = readerFetchRelatedPostsUseCaseProvider;
    this.readerGetPostUseCaseProvider = readerGetPostUseCaseProvider;
    this.readerFetchPostUseCaseProvider = readerFetchPostUseCaseProvider;
    this.siteStoreProvider = siteStoreProvider;
    this.accountStoreProvider = accountStoreProvider;
    this.readerTrackerProvider = readerTrackerProvider;
    this.eventBusWrapperProvider = eventBusWrapperProvider;
    this.wpUrlUtilsWrapperProvider = wpUrlUtilsWrapperProvider;
    this.mainDispatcherProvider = mainDispatcherProvider;
    this.ioDispatcherProvider = ioDispatcherProvider;
    this.bgDispatcherProvider = bgDispatcherProvider;
    this.getLikesHandlerProvider = getLikesHandlerProvider;
    this.likesEnhancementsFeatureConfigProvider = likesEnhancementsFeatureConfigProvider;
    this.engagementUtilsProvider = engagementUtilsProvider;
    this.htmlMessageUtilsProvider = htmlMessageUtilsProvider;
    this.contextProvider = contextProvider;
    this.networkUtilsWrapperProvider = networkUtilsWrapperProvider;
    this.commentsSnippetFeatureConfigProvider = commentsSnippetFeatureConfigProvider;
    this.readerCommentTableWrapperProvider = readerCommentTableWrapperProvider;
    this.readerCommentServiceStarterWrapperProvider = readerCommentServiceStarterWrapperProvider;
  }

  @Override
  public ReaderPostDetailViewModel get() {
    return newInstance(readerPostCardActionsHandlerProvider.get(), readerUtilsWrapperProvider.get(), readerPostTableWrapperProvider.get(), readerPostMoreButtonUiStateBuilderProvider.get(), postDetailUiStateBuilderProvider.get(), reblogUseCaseProvider.get(), readerFetchRelatedPostsUseCaseProvider.get(), readerGetPostUseCaseProvider.get(), readerFetchPostUseCaseProvider.get(), siteStoreProvider.get(), accountStoreProvider.get(), readerTrackerProvider.get(), eventBusWrapperProvider.get(), wpUrlUtilsWrapperProvider.get(), mainDispatcherProvider.get(), ioDispatcherProvider.get(), bgDispatcherProvider.get(), getLikesHandlerProvider.get(), likesEnhancementsFeatureConfigProvider.get(), engagementUtilsProvider.get(), htmlMessageUtilsProvider.get(), contextProvider.get(), networkUtilsWrapperProvider.get(), commentsSnippetFeatureConfigProvider.get(), readerCommentTableWrapperProvider.get(), readerCommentServiceStarterWrapperProvider.get());
  }

  public static ReaderPostDetailViewModel_Factory create(
      Provider<ReaderPostCardActionsHandler> readerPostCardActionsHandlerProvider,
      Provider<ReaderUtilsWrapper> readerUtilsWrapperProvider,
      Provider<ReaderPostTableWrapper> readerPostTableWrapperProvider,
      Provider<ReaderPostMoreButtonUiStateBuilder> readerPostMoreButtonUiStateBuilderProvider,
      Provider<ReaderPostDetailUiStateBuilder> postDetailUiStateBuilderProvider,
      Provider<ReblogUseCase> reblogUseCaseProvider,
      Provider<ReaderFetchRelatedPostsUseCase> readerFetchRelatedPostsUseCaseProvider,
      Provider<ReaderGetPostUseCase> readerGetPostUseCaseProvider,
      Provider<ReaderFetchPostUseCase> readerFetchPostUseCaseProvider,
      Provider<SiteStore> siteStoreProvider, Provider<AccountStore> accountStoreProvider,
      Provider<ReaderTracker> readerTrackerProvider,
      Provider<EventBusWrapper> eventBusWrapperProvider,
      Provider<WpUrlUtilsWrapper> wpUrlUtilsWrapperProvider,
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> ioDispatcherProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider,
      Provider<GetLikesHandler> getLikesHandlerProvider,
      Provider<LikesEnhancementsFeatureConfig> likesEnhancementsFeatureConfigProvider,
      Provider<EngagementUtils> engagementUtilsProvider,
      Provider<HtmlMessageUtils> htmlMessageUtilsProvider,
      Provider<ContextProvider> contextProvider,
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider,
      Provider<CommentsSnippetFeatureConfig> commentsSnippetFeatureConfigProvider,
      Provider<ReaderCommentTableWrapper> readerCommentTableWrapperProvider,
      Provider<ReaderCommentServiceStarterWrapper> readerCommentServiceStarterWrapperProvider) {
    return new ReaderPostDetailViewModel_Factory(readerPostCardActionsHandlerProvider, readerUtilsWrapperProvider, readerPostTableWrapperProvider, readerPostMoreButtonUiStateBuilderProvider, postDetailUiStateBuilderProvider, reblogUseCaseProvider, readerFetchRelatedPostsUseCaseProvider, readerGetPostUseCaseProvider, readerFetchPostUseCaseProvider, siteStoreProvider, accountStoreProvider, readerTrackerProvider, eventBusWrapperProvider, wpUrlUtilsWrapperProvider, mainDispatcherProvider, ioDispatcherProvider, bgDispatcherProvider, getLikesHandlerProvider, likesEnhancementsFeatureConfigProvider, engagementUtilsProvider, htmlMessageUtilsProvider, contextProvider, networkUtilsWrapperProvider, commentsSnippetFeatureConfigProvider, readerCommentTableWrapperProvider, readerCommentServiceStarterWrapperProvider);
  }

  public static ReaderPostDetailViewModel newInstance(
      ReaderPostCardActionsHandler readerPostCardActionsHandler,
      ReaderUtilsWrapper readerUtilsWrapper, ReaderPostTableWrapper readerPostTableWrapper,
      ReaderPostMoreButtonUiStateBuilder readerPostMoreButtonUiStateBuilder,
      ReaderPostDetailUiStateBuilder postDetailUiStateBuilder, ReblogUseCase reblogUseCase,
      ReaderFetchRelatedPostsUseCase readerFetchRelatedPostsUseCase,
      ReaderGetPostUseCase readerGetPostUseCase, ReaderFetchPostUseCase readerFetchPostUseCase,
      SiteStore siteStore, AccountStore accountStore, ReaderTracker readerTracker,
      EventBusWrapper eventBusWrapper, WpUrlUtilsWrapper wpUrlUtilsWrapper,
      CoroutineDispatcher mainDispatcher, CoroutineDispatcher ioDispatcher,
      CoroutineDispatcher bgDispatcher, GetLikesHandler getLikesHandler,
      LikesEnhancementsFeatureConfig likesEnhancementsFeatureConfig,
      EngagementUtils engagementUtils, HtmlMessageUtils htmlMessageUtils,
      ContextProvider contextProvider, NetworkUtilsWrapper networkUtilsWrapper,
      CommentsSnippetFeatureConfig commentsSnippetFeatureConfig,
      ReaderCommentTableWrapper readerCommentTableWrapper,
      ReaderCommentServiceStarterWrapper readerCommentServiceStarterWrapper) {
    return new ReaderPostDetailViewModel(readerPostCardActionsHandler, readerUtilsWrapper, readerPostTableWrapper, readerPostMoreButtonUiStateBuilder, postDetailUiStateBuilder, reblogUseCase, readerFetchRelatedPostsUseCase, readerGetPostUseCase, readerFetchPostUseCase, siteStore, accountStore, readerTracker, eventBusWrapper, wpUrlUtilsWrapper, mainDispatcher, ioDispatcher, bgDispatcher, getLikesHandler, likesEnhancementsFeatureConfig, engagementUtils, htmlMessageUtils, contextProvider, networkUtilsWrapper, commentsSnippetFeatureConfig, readerCommentTableWrapper, readerCommentServiceStarterWrapper);
  }
}
