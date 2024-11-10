package org.wordpress.android.ui.uploads;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.stories.SaveStoryGutenbergBlockUseCase;

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
public final class MediaUploadReadyProcessor_MembersInjector implements MembersInjector<MediaUploadReadyProcessor> {
  private final Provider<SaveStoryGutenbergBlockUseCase> mSaveStoryGutenbergBlockUseCaseProvider;

  public MediaUploadReadyProcessor_MembersInjector(
      Provider<SaveStoryGutenbergBlockUseCase> mSaveStoryGutenbergBlockUseCaseProvider) {
    this.mSaveStoryGutenbergBlockUseCaseProvider = mSaveStoryGutenbergBlockUseCaseProvider;
  }

  public static MembersInjector<MediaUploadReadyProcessor> create(
      Provider<SaveStoryGutenbergBlockUseCase> mSaveStoryGutenbergBlockUseCaseProvider) {
    return new MediaUploadReadyProcessor_MembersInjector(mSaveStoryGutenbergBlockUseCaseProvider);
  }

  @Override
  public void injectMembers(MediaUploadReadyProcessor instance) {
    injectMSaveStoryGutenbergBlockUseCase(instance, mSaveStoryGutenbergBlockUseCaseProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.uploads.MediaUploadReadyProcessor.mSaveStoryGutenbergBlockUseCase")
  public static void injectMSaveStoryGutenbergBlockUseCase(MediaUploadReadyProcessor instance,
      SaveStoryGutenbergBlockUseCase mSaveStoryGutenbergBlockUseCase) {
    instance.mSaveStoryGutenbergBlockUseCase = mSaveStoryGutenbergBlockUseCase;
  }
}
