package org.wordpress.android.ui.uploads;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.stories.SaveStoryGutenbergBlockUseCase;

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
public final class MediaUploadReadyProcessor_Factory implements Factory<MediaUploadReadyProcessor> {
  private final Provider<SaveStoryGutenbergBlockUseCase> mSaveStoryGutenbergBlockUseCaseProvider;

  public MediaUploadReadyProcessor_Factory(
      Provider<SaveStoryGutenbergBlockUseCase> mSaveStoryGutenbergBlockUseCaseProvider) {
    this.mSaveStoryGutenbergBlockUseCaseProvider = mSaveStoryGutenbergBlockUseCaseProvider;
  }

  @Override
  public MediaUploadReadyProcessor get() {
    MediaUploadReadyProcessor instance = newInstance();
    MediaUploadReadyProcessor_MembersInjector.injectMSaveStoryGutenbergBlockUseCase(instance, mSaveStoryGutenbergBlockUseCaseProvider.get());
    return instance;
  }

  public static MediaUploadReadyProcessor_Factory create(
      Provider<SaveStoryGutenbergBlockUseCase> mSaveStoryGutenbergBlockUseCaseProvider) {
    return new MediaUploadReadyProcessor_Factory(mSaveStoryGutenbergBlockUseCaseProvider);
  }

  public static MediaUploadReadyProcessor newInstance() {
    return new MediaUploadReadyProcessor();
  }
}
