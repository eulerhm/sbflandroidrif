package org.wordpress.android.ui.posts.editor.media;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.util.MediaUtilsWrapper;

@ScopeMetadata("dagger.Reusable")
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
public final class AddLocalMediaToPostUseCase_Factory implements Factory<AddLocalMediaToPostUseCase> {
  private final Provider<CopyMediaToAppStorageUseCase> copyMediaToAppStorageUseCaseProvider;

  private final Provider<OptimizeMediaUseCase> optimizeMediaUseCaseProvider;

  private final Provider<GetMediaModelUseCase> getMediaModelUseCaseProvider;

  private final Provider<UpdateMediaModelUseCase> updateMediaModelUseCaseProvider;

  private final Provider<AppendMediaToEditorUseCase> appendMediaToEditorUseCaseProvider;

  private final Provider<UploadMediaUseCase> uploadMediaUseCaseProvider;

  private final Provider<MediaUtilsWrapper> mediaUtilsWrapperProvider;

  private final Provider<Context> contextProvider;

  public AddLocalMediaToPostUseCase_Factory(
      Provider<CopyMediaToAppStorageUseCase> copyMediaToAppStorageUseCaseProvider,
      Provider<OptimizeMediaUseCase> optimizeMediaUseCaseProvider,
      Provider<GetMediaModelUseCase> getMediaModelUseCaseProvider,
      Provider<UpdateMediaModelUseCase> updateMediaModelUseCaseProvider,
      Provider<AppendMediaToEditorUseCase> appendMediaToEditorUseCaseProvider,
      Provider<UploadMediaUseCase> uploadMediaUseCaseProvider,
      Provider<MediaUtilsWrapper> mediaUtilsWrapperProvider, Provider<Context> contextProvider) {
    this.copyMediaToAppStorageUseCaseProvider = copyMediaToAppStorageUseCaseProvider;
    this.optimizeMediaUseCaseProvider = optimizeMediaUseCaseProvider;
    this.getMediaModelUseCaseProvider = getMediaModelUseCaseProvider;
    this.updateMediaModelUseCaseProvider = updateMediaModelUseCaseProvider;
    this.appendMediaToEditorUseCaseProvider = appendMediaToEditorUseCaseProvider;
    this.uploadMediaUseCaseProvider = uploadMediaUseCaseProvider;
    this.mediaUtilsWrapperProvider = mediaUtilsWrapperProvider;
    this.contextProvider = contextProvider;
  }

  @Override
  public AddLocalMediaToPostUseCase get() {
    return newInstance(copyMediaToAppStorageUseCaseProvider.get(), optimizeMediaUseCaseProvider.get(), getMediaModelUseCaseProvider.get(), updateMediaModelUseCaseProvider.get(), appendMediaToEditorUseCaseProvider.get(), uploadMediaUseCaseProvider.get(), mediaUtilsWrapperProvider.get(), contextProvider.get());
  }

  public static AddLocalMediaToPostUseCase_Factory create(
      Provider<CopyMediaToAppStorageUseCase> copyMediaToAppStorageUseCaseProvider,
      Provider<OptimizeMediaUseCase> optimizeMediaUseCaseProvider,
      Provider<GetMediaModelUseCase> getMediaModelUseCaseProvider,
      Provider<UpdateMediaModelUseCase> updateMediaModelUseCaseProvider,
      Provider<AppendMediaToEditorUseCase> appendMediaToEditorUseCaseProvider,
      Provider<UploadMediaUseCase> uploadMediaUseCaseProvider,
      Provider<MediaUtilsWrapper> mediaUtilsWrapperProvider, Provider<Context> contextProvider) {
    return new AddLocalMediaToPostUseCase_Factory(copyMediaToAppStorageUseCaseProvider, optimizeMediaUseCaseProvider, getMediaModelUseCaseProvider, updateMediaModelUseCaseProvider, appendMediaToEditorUseCaseProvider, uploadMediaUseCaseProvider, mediaUtilsWrapperProvider, contextProvider);
  }

  public static AddLocalMediaToPostUseCase newInstance(
      CopyMediaToAppStorageUseCase copyMediaToAppStorageUseCase,
      OptimizeMediaUseCase optimizeMediaUseCase, GetMediaModelUseCase getMediaModelUseCase,
      UpdateMediaModelUseCase updateMediaModelUseCase,
      AppendMediaToEditorUseCase appendMediaToEditorUseCase, UploadMediaUseCase uploadMediaUseCase,
      MediaUtilsWrapper mediaUtilsWrapper, Context context) {
    return new AddLocalMediaToPostUseCase(copyMediaToAppStorageUseCase, optimizeMediaUseCase, getMediaModelUseCase, updateMediaModelUseCase, appendMediaToEditorUseCase, uploadMediaUseCase, mediaUtilsWrapper, context);
  }
}
