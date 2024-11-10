package org.wordpress.android.ui.reader.discover;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.datasets.ReaderBlogTableWrapper;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.ui.prefs.AppPrefsWrapper;
import org.wordpress.android.ui.reader.reblog.ReblogUseCase;
import org.wordpress.android.ui.reader.repository.usecases.BlockBlogUseCase;
import org.wordpress.android.ui.reader.repository.usecases.PostLikeUseCase;
import org.wordpress.android.ui.reader.repository.usecases.UndoBlockBlogUseCase;
import org.wordpress.android.ui.reader.tracker.ReaderTracker;
import org.wordpress.android.ui.reader.usecases.ReaderFetchSiteUseCase;
import org.wordpress.android.ui.reader.usecases.ReaderPostBookmarkUseCase;
import org.wordpress.android.ui.reader.usecases.ReaderSeenStatusToggleUseCase;
import org.wordpress.android.ui.reader.usecases.ReaderSiteFollowUseCase;
import org.wordpress.android.ui.reader.usecases.ReaderSiteNotificationsUseCase;
import org.wordpress.android.ui.utils.HtmlMessageUtils;
import org.wordpress.android.viewmodel.ResourceProvider;
import org.wordpress.android.widgets.AppRatingDialogWrapper;

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
public final class ReaderPostCardActionsHandler_Factory implements Factory<ReaderPostCardActionsHandler> {
  private final Provider<ReaderTracker> readerTrackerProvider;

  private final Provider<ReblogUseCase> reblogUseCaseProvider;

  private final Provider<ReaderPostBookmarkUseCase> bookmarkUseCaseProvider;

  private final Provider<ReaderSiteFollowUseCase> followUseCaseProvider;

  private final Provider<BlockBlogUseCase> blockBlogUseCaseProvider;

  private final Provider<PostLikeUseCase> likeUseCaseProvider;

  private final Provider<ReaderSiteNotificationsUseCase> siteNotificationsUseCaseProvider;

  private final Provider<UndoBlockBlogUseCase> undoBlockBlogUseCaseProvider;

  private final Provider<ReaderFetchSiteUseCase> fetchSiteUseCaseProvider;

  private final Provider<AppPrefsWrapper> appPrefsWrapperProvider;

  private final Provider<Dispatcher> dispatcherProvider;

  private final Provider<ResourceProvider> resourceProvider;

  private final Provider<HtmlMessageUtils> htmlMessageUtilsProvider;

  private final Provider<AppRatingDialogWrapper> appRatingDialogWrapperProvider;

  private final Provider<ReaderSeenStatusToggleUseCase> seenStatusToggleUseCaseProvider;

  private final Provider<ReaderBlogTableWrapper> readerBlogTableWrapperProvider;

  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  public ReaderPostCardActionsHandler_Factory(Provider<ReaderTracker> readerTrackerProvider,
      Provider<ReblogUseCase> reblogUseCaseProvider,
      Provider<ReaderPostBookmarkUseCase> bookmarkUseCaseProvider,
      Provider<ReaderSiteFollowUseCase> followUseCaseProvider,
      Provider<BlockBlogUseCase> blockBlogUseCaseProvider,
      Provider<PostLikeUseCase> likeUseCaseProvider,
      Provider<ReaderSiteNotificationsUseCase> siteNotificationsUseCaseProvider,
      Provider<UndoBlockBlogUseCase> undoBlockBlogUseCaseProvider,
      Provider<ReaderFetchSiteUseCase> fetchSiteUseCaseProvider,
      Provider<AppPrefsWrapper> appPrefsWrapperProvider, Provider<Dispatcher> dispatcherProvider,
      Provider<ResourceProvider> resourceProvider,
      Provider<HtmlMessageUtils> htmlMessageUtilsProvider,
      Provider<AppRatingDialogWrapper> appRatingDialogWrapperProvider,
      Provider<ReaderSeenStatusToggleUseCase> seenStatusToggleUseCaseProvider,
      Provider<ReaderBlogTableWrapper> readerBlogTableWrapperProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider) {
    this.readerTrackerProvider = readerTrackerProvider;
    this.reblogUseCaseProvider = reblogUseCaseProvider;
    this.bookmarkUseCaseProvider = bookmarkUseCaseProvider;
    this.followUseCaseProvider = followUseCaseProvider;
    this.blockBlogUseCaseProvider = blockBlogUseCaseProvider;
    this.likeUseCaseProvider = likeUseCaseProvider;
    this.siteNotificationsUseCaseProvider = siteNotificationsUseCaseProvider;
    this.undoBlockBlogUseCaseProvider = undoBlockBlogUseCaseProvider;
    this.fetchSiteUseCaseProvider = fetchSiteUseCaseProvider;
    this.appPrefsWrapperProvider = appPrefsWrapperProvider;
    this.dispatcherProvider = dispatcherProvider;
    this.resourceProvider = resourceProvider;
    this.htmlMessageUtilsProvider = htmlMessageUtilsProvider;
    this.appRatingDialogWrapperProvider = appRatingDialogWrapperProvider;
    this.seenStatusToggleUseCaseProvider = seenStatusToggleUseCaseProvider;
    this.readerBlogTableWrapperProvider = readerBlogTableWrapperProvider;
    this.bgDispatcherProvider = bgDispatcherProvider;
  }

  @Override
  public ReaderPostCardActionsHandler get() {
    return newInstance(readerTrackerProvider.get(), reblogUseCaseProvider.get(), bookmarkUseCaseProvider.get(), followUseCaseProvider.get(), blockBlogUseCaseProvider.get(), likeUseCaseProvider.get(), siteNotificationsUseCaseProvider.get(), undoBlockBlogUseCaseProvider.get(), fetchSiteUseCaseProvider.get(), appPrefsWrapperProvider.get(), dispatcherProvider.get(), resourceProvider.get(), htmlMessageUtilsProvider.get(), appRatingDialogWrapperProvider.get(), seenStatusToggleUseCaseProvider.get(), readerBlogTableWrapperProvider.get(), bgDispatcherProvider.get());
  }

  public static ReaderPostCardActionsHandler_Factory create(
      Provider<ReaderTracker> readerTrackerProvider, Provider<ReblogUseCase> reblogUseCaseProvider,
      Provider<ReaderPostBookmarkUseCase> bookmarkUseCaseProvider,
      Provider<ReaderSiteFollowUseCase> followUseCaseProvider,
      Provider<BlockBlogUseCase> blockBlogUseCaseProvider,
      Provider<PostLikeUseCase> likeUseCaseProvider,
      Provider<ReaderSiteNotificationsUseCase> siteNotificationsUseCaseProvider,
      Provider<UndoBlockBlogUseCase> undoBlockBlogUseCaseProvider,
      Provider<ReaderFetchSiteUseCase> fetchSiteUseCaseProvider,
      Provider<AppPrefsWrapper> appPrefsWrapperProvider, Provider<Dispatcher> dispatcherProvider,
      Provider<ResourceProvider> resourceProvider,
      Provider<HtmlMessageUtils> htmlMessageUtilsProvider,
      Provider<AppRatingDialogWrapper> appRatingDialogWrapperProvider,
      Provider<ReaderSeenStatusToggleUseCase> seenStatusToggleUseCaseProvider,
      Provider<ReaderBlogTableWrapper> readerBlogTableWrapperProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider) {
    return new ReaderPostCardActionsHandler_Factory(readerTrackerProvider, reblogUseCaseProvider, bookmarkUseCaseProvider, followUseCaseProvider, blockBlogUseCaseProvider, likeUseCaseProvider, siteNotificationsUseCaseProvider, undoBlockBlogUseCaseProvider, fetchSiteUseCaseProvider, appPrefsWrapperProvider, dispatcherProvider, resourceProvider, htmlMessageUtilsProvider, appRatingDialogWrapperProvider, seenStatusToggleUseCaseProvider, readerBlogTableWrapperProvider, bgDispatcherProvider);
  }

  public static ReaderPostCardActionsHandler newInstance(ReaderTracker readerTracker,
      ReblogUseCase reblogUseCase, ReaderPostBookmarkUseCase bookmarkUseCase,
      ReaderSiteFollowUseCase followUseCase, BlockBlogUseCase blockBlogUseCase,
      PostLikeUseCase likeUseCase, ReaderSiteNotificationsUseCase siteNotificationsUseCase,
      UndoBlockBlogUseCase undoBlockBlogUseCase, ReaderFetchSiteUseCase fetchSiteUseCase,
      AppPrefsWrapper appPrefsWrapper, Dispatcher dispatcher, ResourceProvider resourceProvider,
      HtmlMessageUtils htmlMessageUtils, AppRatingDialogWrapper appRatingDialogWrapper,
      ReaderSeenStatusToggleUseCase seenStatusToggleUseCase,
      ReaderBlogTableWrapper readerBlogTableWrapper, CoroutineDispatcher bgDispatcher) {
    return new ReaderPostCardActionsHandler(readerTracker, reblogUseCase, bookmarkUseCase, followUseCase, blockBlogUseCase, likeUseCase, siteNotificationsUseCase, undoBlockBlogUseCase, fetchSiteUseCase, appPrefsWrapper, dispatcher, resourceProvider, htmlMessageUtils, appRatingDialogWrapper, seenStatusToggleUseCase, readerBlogTableWrapper, bgDispatcher);
  }
}
