package org.wordpress.android.ui.reader.discover;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.ui.prefs.AppPrefsWrapper;
import org.wordpress.android.ui.reader.reblog.ReblogUseCase;
import org.wordpress.android.ui.reader.repository.ReaderDiscoverDataProvider;
import org.wordpress.android.ui.reader.repository.usecases.tags.GetFollowedTagsUseCase;
import org.wordpress.android.ui.reader.tracker.ReaderTracker;
import org.wordpress.android.ui.reader.utils.ReaderUtilsWrapper;
import org.wordpress.android.util.DisplayUtilsWrapper;

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
public final class ReaderDiscoverViewModel_Factory implements Factory<ReaderDiscoverViewModel> {
  private final Provider<ReaderPostUiStateBuilder> postUiStateBuilderProvider;

  private final Provider<ReaderPostMoreButtonUiStateBuilder> readerPostMoreButtonUiStateBuilderProvider;

  private final Provider<ReaderPostCardActionsHandler> readerPostCardActionsHandlerProvider;

  private final Provider<ReaderDiscoverDataProvider> readerDiscoverDataProvider;

  private final Provider<ReblogUseCase> reblogUseCaseProvider;

  private final Provider<ReaderUtilsWrapper> readerUtilsWrapperProvider;

  private final Provider<AppPrefsWrapper> appPrefsWrapperProvider;

  private final Provider<ReaderTracker> readerTrackerProvider;

  private final Provider<DisplayUtilsWrapper> displayUtilsWrapperProvider;

  private final Provider<GetFollowedTagsUseCase> getFollowedTagsUseCaseProvider;

  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  private final Provider<CoroutineDispatcher> ioDispatcherProvider;

  public ReaderDiscoverViewModel_Factory(
      Provider<ReaderPostUiStateBuilder> postUiStateBuilderProvider,
      Provider<ReaderPostMoreButtonUiStateBuilder> readerPostMoreButtonUiStateBuilderProvider,
      Provider<ReaderPostCardActionsHandler> readerPostCardActionsHandlerProvider,
      Provider<ReaderDiscoverDataProvider> readerDiscoverDataProvider,
      Provider<ReblogUseCase> reblogUseCaseProvider,
      Provider<ReaderUtilsWrapper> readerUtilsWrapperProvider,
      Provider<AppPrefsWrapper> appPrefsWrapperProvider,
      Provider<ReaderTracker> readerTrackerProvider,
      Provider<DisplayUtilsWrapper> displayUtilsWrapperProvider,
      Provider<GetFollowedTagsUseCase> getFollowedTagsUseCaseProvider,
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> ioDispatcherProvider) {
    this.postUiStateBuilderProvider = postUiStateBuilderProvider;
    this.readerPostMoreButtonUiStateBuilderProvider = readerPostMoreButtonUiStateBuilderProvider;
    this.readerPostCardActionsHandlerProvider = readerPostCardActionsHandlerProvider;
    this.readerDiscoverDataProvider = readerDiscoverDataProvider;
    this.reblogUseCaseProvider = reblogUseCaseProvider;
    this.readerUtilsWrapperProvider = readerUtilsWrapperProvider;
    this.appPrefsWrapperProvider = appPrefsWrapperProvider;
    this.readerTrackerProvider = readerTrackerProvider;
    this.displayUtilsWrapperProvider = displayUtilsWrapperProvider;
    this.getFollowedTagsUseCaseProvider = getFollowedTagsUseCaseProvider;
    this.mainDispatcherProvider = mainDispatcherProvider;
    this.ioDispatcherProvider = ioDispatcherProvider;
  }

  @Override
  public ReaderDiscoverViewModel get() {
    return newInstance(postUiStateBuilderProvider.get(), readerPostMoreButtonUiStateBuilderProvider.get(), readerPostCardActionsHandlerProvider.get(), readerDiscoverDataProvider.get(), reblogUseCaseProvider.get(), readerUtilsWrapperProvider.get(), appPrefsWrapperProvider.get(), readerTrackerProvider.get(), displayUtilsWrapperProvider.get(), getFollowedTagsUseCaseProvider.get(), mainDispatcherProvider.get(), ioDispatcherProvider.get());
  }

  public static ReaderDiscoverViewModel_Factory create(
      Provider<ReaderPostUiStateBuilder> postUiStateBuilderProvider,
      Provider<ReaderPostMoreButtonUiStateBuilder> readerPostMoreButtonUiStateBuilderProvider,
      Provider<ReaderPostCardActionsHandler> readerPostCardActionsHandlerProvider,
      Provider<ReaderDiscoverDataProvider> readerDiscoverDataProvider,
      Provider<ReblogUseCase> reblogUseCaseProvider,
      Provider<ReaderUtilsWrapper> readerUtilsWrapperProvider,
      Provider<AppPrefsWrapper> appPrefsWrapperProvider,
      Provider<ReaderTracker> readerTrackerProvider,
      Provider<DisplayUtilsWrapper> displayUtilsWrapperProvider,
      Provider<GetFollowedTagsUseCase> getFollowedTagsUseCaseProvider,
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> ioDispatcherProvider) {
    return new ReaderDiscoverViewModel_Factory(postUiStateBuilderProvider, readerPostMoreButtonUiStateBuilderProvider, readerPostCardActionsHandlerProvider, readerDiscoverDataProvider, reblogUseCaseProvider, readerUtilsWrapperProvider, appPrefsWrapperProvider, readerTrackerProvider, displayUtilsWrapperProvider, getFollowedTagsUseCaseProvider, mainDispatcherProvider, ioDispatcherProvider);
  }

  public static ReaderDiscoverViewModel newInstance(ReaderPostUiStateBuilder postUiStateBuilder,
      ReaderPostMoreButtonUiStateBuilder readerPostMoreButtonUiStateBuilder,
      ReaderPostCardActionsHandler readerPostCardActionsHandler,
      ReaderDiscoverDataProvider readerDiscoverDataProvider, ReblogUseCase reblogUseCase,
      ReaderUtilsWrapper readerUtilsWrapper, AppPrefsWrapper appPrefsWrapper,
      ReaderTracker readerTracker, DisplayUtilsWrapper displayUtilsWrapper,
      GetFollowedTagsUseCase getFollowedTagsUseCase, CoroutineDispatcher mainDispatcher,
      CoroutineDispatcher ioDispatcher) {
    return new ReaderDiscoverViewModel(postUiStateBuilder, readerPostMoreButtonUiStateBuilder, readerPostCardActionsHandler, readerDiscoverDataProvider, reblogUseCase, readerUtilsWrapper, appPrefsWrapper, readerTracker, displayUtilsWrapper, getFollowedTagsUseCase, mainDispatcher, ioDispatcher);
  }
}
