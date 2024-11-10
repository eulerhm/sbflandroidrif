package org.wordpress.android.ui.posts;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.ui.posts.prepublishing.home.usecases.GetButtonUiStateUseCase;
import org.wordpress.android.ui.stories.StoryRepositoryWrapper;
import org.wordpress.android.ui.stories.usecase.UpdateStoryPostTitleUseCase;
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
public final class PrepublishingHomeViewModel_Factory implements Factory<PrepublishingHomeViewModel> {
  private final Provider<GetPostTagsUseCase> getPostTagsUseCaseProvider;

  private final Provider<PostSettingsUtils> postSettingsUtilsProvider;

  private final Provider<GetButtonUiStateUseCase> getButtonUiStateUseCaseProvider;

  private final Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider;

  private final Provider<StoryRepositoryWrapper> storyRepositoryWrapperProvider;

  private final Provider<UpdateStoryPostTitleUseCase> updateStoryPostTitleUseCaseProvider;

  private final Provider<GetCategoriesUseCase> getCategoriesUseCaseProvider;

  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  public PrepublishingHomeViewModel_Factory(Provider<GetPostTagsUseCase> getPostTagsUseCaseProvider,
      Provider<PostSettingsUtils> postSettingsUtilsProvider,
      Provider<GetButtonUiStateUseCase> getButtonUiStateUseCaseProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider,
      Provider<StoryRepositoryWrapper> storyRepositoryWrapperProvider,
      Provider<UpdateStoryPostTitleUseCase> updateStoryPostTitleUseCaseProvider,
      Provider<GetCategoriesUseCase> getCategoriesUseCaseProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider) {
    this.getPostTagsUseCaseProvider = getPostTagsUseCaseProvider;
    this.postSettingsUtilsProvider = postSettingsUtilsProvider;
    this.getButtonUiStateUseCaseProvider = getButtonUiStateUseCaseProvider;
    this.analyticsTrackerWrapperProvider = analyticsTrackerWrapperProvider;
    this.storyRepositoryWrapperProvider = storyRepositoryWrapperProvider;
    this.updateStoryPostTitleUseCaseProvider = updateStoryPostTitleUseCaseProvider;
    this.getCategoriesUseCaseProvider = getCategoriesUseCaseProvider;
    this.bgDispatcherProvider = bgDispatcherProvider;
  }

  @Override
  public PrepublishingHomeViewModel get() {
    return newInstance(getPostTagsUseCaseProvider.get(), postSettingsUtilsProvider.get(), getButtonUiStateUseCaseProvider.get(), analyticsTrackerWrapperProvider.get(), storyRepositoryWrapperProvider.get(), updateStoryPostTitleUseCaseProvider.get(), getCategoriesUseCaseProvider.get(), bgDispatcherProvider.get());
  }

  public static PrepublishingHomeViewModel_Factory create(
      Provider<GetPostTagsUseCase> getPostTagsUseCaseProvider,
      Provider<PostSettingsUtils> postSettingsUtilsProvider,
      Provider<GetButtonUiStateUseCase> getButtonUiStateUseCaseProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider,
      Provider<StoryRepositoryWrapper> storyRepositoryWrapperProvider,
      Provider<UpdateStoryPostTitleUseCase> updateStoryPostTitleUseCaseProvider,
      Provider<GetCategoriesUseCase> getCategoriesUseCaseProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider) {
    return new PrepublishingHomeViewModel_Factory(getPostTagsUseCaseProvider, postSettingsUtilsProvider, getButtonUiStateUseCaseProvider, analyticsTrackerWrapperProvider, storyRepositoryWrapperProvider, updateStoryPostTitleUseCaseProvider, getCategoriesUseCaseProvider, bgDispatcherProvider);
  }

  public static PrepublishingHomeViewModel newInstance(GetPostTagsUseCase getPostTagsUseCase,
      PostSettingsUtils postSettingsUtils, GetButtonUiStateUseCase getButtonUiStateUseCase,
      AnalyticsTrackerWrapper analyticsTrackerWrapper,
      StoryRepositoryWrapper storyRepositoryWrapper,
      UpdateStoryPostTitleUseCase updateStoryPostTitleUseCase,
      GetCategoriesUseCase getCategoriesUseCase, CoroutineDispatcher bgDispatcher) {
    return new PrepublishingHomeViewModel(getPostTagsUseCase, postSettingsUtils, getButtonUiStateUseCase, analyticsTrackerWrapper, storyRepositoryWrapper, updateStoryPostTitleUseCase, getCategoriesUseCase, bgDispatcher);
  }
}
