package org.wordpress.android.ui.stories;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.ui.notifications.SystemNotificationsTracker;
import org.wordpress.android.ui.posts.PostEditorAnalyticsSessionWrapper;
import org.wordpress.android.ui.posts.SavePostToDbUseCase;
import org.wordpress.android.ui.stories.usecase.SetUntitledStoryTitleIfTitleEmptyUseCase;

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
public final class StoryComposerViewModel_Factory implements Factory<StoryComposerViewModel> {
  private final Provider<SystemNotificationsTracker> systemNotificationsTrackerProvider;

  private final Provider<SaveInitialPostUseCase> saveInitialPostUseCaseProvider;

  private final Provider<SavePostToDbUseCase> savePostToDbUseCaseProvider;

  private final Provider<SetUntitledStoryTitleIfTitleEmptyUseCase> setUntitledStoryTitleIfTitleEmptyUseCaseProvider;

  private final Provider<PostEditorAnalyticsSessionWrapper> postEditorAnalyticsSessionWrapperProvider;

  private final Provider<Dispatcher> dispatcherProvider;

  public StoryComposerViewModel_Factory(
      Provider<SystemNotificationsTracker> systemNotificationsTrackerProvider,
      Provider<SaveInitialPostUseCase> saveInitialPostUseCaseProvider,
      Provider<SavePostToDbUseCase> savePostToDbUseCaseProvider,
      Provider<SetUntitledStoryTitleIfTitleEmptyUseCase> setUntitledStoryTitleIfTitleEmptyUseCaseProvider,
      Provider<PostEditorAnalyticsSessionWrapper> postEditorAnalyticsSessionWrapperProvider,
      Provider<Dispatcher> dispatcherProvider) {
    this.systemNotificationsTrackerProvider = systemNotificationsTrackerProvider;
    this.saveInitialPostUseCaseProvider = saveInitialPostUseCaseProvider;
    this.savePostToDbUseCaseProvider = savePostToDbUseCaseProvider;
    this.setUntitledStoryTitleIfTitleEmptyUseCaseProvider = setUntitledStoryTitleIfTitleEmptyUseCaseProvider;
    this.postEditorAnalyticsSessionWrapperProvider = postEditorAnalyticsSessionWrapperProvider;
    this.dispatcherProvider = dispatcherProvider;
  }

  @Override
  public StoryComposerViewModel get() {
    return newInstance(systemNotificationsTrackerProvider.get(), saveInitialPostUseCaseProvider.get(), savePostToDbUseCaseProvider.get(), setUntitledStoryTitleIfTitleEmptyUseCaseProvider.get(), postEditorAnalyticsSessionWrapperProvider.get(), dispatcherProvider.get());
  }

  public static StoryComposerViewModel_Factory create(
      Provider<SystemNotificationsTracker> systemNotificationsTrackerProvider,
      Provider<SaveInitialPostUseCase> saveInitialPostUseCaseProvider,
      Provider<SavePostToDbUseCase> savePostToDbUseCaseProvider,
      Provider<SetUntitledStoryTitleIfTitleEmptyUseCase> setUntitledStoryTitleIfTitleEmptyUseCaseProvider,
      Provider<PostEditorAnalyticsSessionWrapper> postEditorAnalyticsSessionWrapperProvider,
      Provider<Dispatcher> dispatcherProvider) {
    return new StoryComposerViewModel_Factory(systemNotificationsTrackerProvider, saveInitialPostUseCaseProvider, savePostToDbUseCaseProvider, setUntitledStoryTitleIfTitleEmptyUseCaseProvider, postEditorAnalyticsSessionWrapperProvider, dispatcherProvider);
  }

  public static StoryComposerViewModel newInstance(
      SystemNotificationsTracker systemNotificationsTracker,
      SaveInitialPostUseCase saveInitialPostUseCase, SavePostToDbUseCase savePostToDbUseCase,
      SetUntitledStoryTitleIfTitleEmptyUseCase setUntitledStoryTitleIfTitleEmptyUseCase,
      PostEditorAnalyticsSessionWrapper postEditorAnalyticsSessionWrapper, Dispatcher dispatcher) {
    return new StoryComposerViewModel(systemNotificationsTracker, saveInitialPostUseCase, savePostToDbUseCase, setUntitledStoryTitleIfTitleEmptyUseCase, postEditorAnalyticsSessionWrapper, dispatcher);
  }
}
