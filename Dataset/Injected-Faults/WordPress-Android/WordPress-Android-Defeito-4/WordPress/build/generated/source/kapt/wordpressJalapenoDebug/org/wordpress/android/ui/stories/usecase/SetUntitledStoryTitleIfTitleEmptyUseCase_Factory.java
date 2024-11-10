package org.wordpress.android.ui.stories.usecase;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.stories.StoryRepositoryWrapper;

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
public final class SetUntitledStoryTitleIfTitleEmptyUseCase_Factory implements Factory<SetUntitledStoryTitleIfTitleEmptyUseCase> {
  private final Provider<StoryRepositoryWrapper> storyRepositoryWrapperProvider;

  private final Provider<UpdateStoryPostTitleUseCase> updateStoryPostTitleUseCaseProvider;

  private final Provider<Context> contextProvider;

  public SetUntitledStoryTitleIfTitleEmptyUseCase_Factory(
      Provider<StoryRepositoryWrapper> storyRepositoryWrapperProvider,
      Provider<UpdateStoryPostTitleUseCase> updateStoryPostTitleUseCaseProvider,
      Provider<Context> contextProvider) {
    this.storyRepositoryWrapperProvider = storyRepositoryWrapperProvider;
    this.updateStoryPostTitleUseCaseProvider = updateStoryPostTitleUseCaseProvider;
    this.contextProvider = contextProvider;
  }

  @Override
  public SetUntitledStoryTitleIfTitleEmptyUseCase get() {
    return newInstance(storyRepositoryWrapperProvider.get(), updateStoryPostTitleUseCaseProvider.get(), contextProvider.get());
  }

  public static SetUntitledStoryTitleIfTitleEmptyUseCase_Factory create(
      Provider<StoryRepositoryWrapper> storyRepositoryWrapperProvider,
      Provider<UpdateStoryPostTitleUseCase> updateStoryPostTitleUseCaseProvider,
      Provider<Context> contextProvider) {
    return new SetUntitledStoryTitleIfTitleEmptyUseCase_Factory(storyRepositoryWrapperProvider, updateStoryPostTitleUseCaseProvider, contextProvider);
  }

  public static SetUntitledStoryTitleIfTitleEmptyUseCase newInstance(
      StoryRepositoryWrapper storyRepositoryWrapper,
      UpdateStoryPostTitleUseCase updateStoryPostTitleUseCase, Context context) {
    return new SetUntitledStoryTitleIfTitleEmptyUseCase(storyRepositoryWrapper, updateStoryPostTitleUseCase, context);
  }
}
