package org.wordpress.android.ui.reader.viewmodels;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.ui.reader.discover.ReaderPostCardActionsHandler;
import org.wordpress.android.ui.reader.reblog.ReblogUseCase;
import org.wordpress.android.ui.reader.tracker.ReaderTracker;
import org.wordpress.android.ui.reader.usecases.ReaderSeenStatusToggleUseCase;

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
public final class ReaderPostListViewModel_Factory implements Factory<ReaderPostListViewModel> {
  private final Provider<ReaderPostCardActionsHandler> readerPostCardActionsHandlerProvider;

  private final Provider<ReblogUseCase> reblogUseCaseProvider;

  private final Provider<ReaderTracker> readerTrackerProvider;

  private final Provider<ReaderSeenStatusToggleUseCase> seenStatusToggleUseCaseProvider;

  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  public ReaderPostListViewModel_Factory(
      Provider<ReaderPostCardActionsHandler> readerPostCardActionsHandlerProvider,
      Provider<ReblogUseCase> reblogUseCaseProvider, Provider<ReaderTracker> readerTrackerProvider,
      Provider<ReaderSeenStatusToggleUseCase> seenStatusToggleUseCaseProvider,
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider) {
    this.readerPostCardActionsHandlerProvider = readerPostCardActionsHandlerProvider;
    this.reblogUseCaseProvider = reblogUseCaseProvider;
    this.readerTrackerProvider = readerTrackerProvider;
    this.seenStatusToggleUseCaseProvider = seenStatusToggleUseCaseProvider;
    this.mainDispatcherProvider = mainDispatcherProvider;
    this.bgDispatcherProvider = bgDispatcherProvider;
  }

  @Override
  public ReaderPostListViewModel get() {
    return newInstance(readerPostCardActionsHandlerProvider.get(), reblogUseCaseProvider.get(), readerTrackerProvider.get(), seenStatusToggleUseCaseProvider.get(), mainDispatcherProvider.get(), bgDispatcherProvider.get());
  }

  public static ReaderPostListViewModel_Factory create(
      Provider<ReaderPostCardActionsHandler> readerPostCardActionsHandlerProvider,
      Provider<ReblogUseCase> reblogUseCaseProvider, Provider<ReaderTracker> readerTrackerProvider,
      Provider<ReaderSeenStatusToggleUseCase> seenStatusToggleUseCaseProvider,
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider) {
    return new ReaderPostListViewModel_Factory(readerPostCardActionsHandlerProvider, reblogUseCaseProvider, readerTrackerProvider, seenStatusToggleUseCaseProvider, mainDispatcherProvider, bgDispatcherProvider);
  }

  public static ReaderPostListViewModel newInstance(
      ReaderPostCardActionsHandler readerPostCardActionsHandler, ReblogUseCase reblogUseCase,
      ReaderTracker readerTracker, ReaderSeenStatusToggleUseCase seenStatusToggleUseCase,
      CoroutineDispatcher mainDispatcher, CoroutineDispatcher bgDispatcher) {
    return new ReaderPostListViewModel(readerPostCardActionsHandler, reblogUseCase, readerTracker, seenStatusToggleUseCase, mainDispatcher, bgDispatcher);
  }
}
