package org.wordpress.android.ui.posts;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.store.UploadStore;
import org.wordpress.android.ui.uploads.UploadActionUseCase;

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
public final class PostModelUploadStatusTracker_Factory implements Factory<PostModelUploadStatusTracker> {
  private final Provider<UploadStore> uploadStoreProvider;

  private final Provider<UploadActionUseCase> uploadActionUseCaseProvider;

  public PostModelUploadStatusTracker_Factory(Provider<UploadStore> uploadStoreProvider,
      Provider<UploadActionUseCase> uploadActionUseCaseProvider) {
    this.uploadStoreProvider = uploadStoreProvider;
    this.uploadActionUseCaseProvider = uploadActionUseCaseProvider;
  }

  @Override
  public PostModelUploadStatusTracker get() {
    return newInstance(uploadStoreProvider.get(), uploadActionUseCaseProvider.get());
  }

  public static PostModelUploadStatusTracker_Factory create(
      Provider<UploadStore> uploadStoreProvider,
      Provider<UploadActionUseCase> uploadActionUseCaseProvider) {
    return new PostModelUploadStatusTracker_Factory(uploadStoreProvider, uploadActionUseCaseProvider);
  }

  public static PostModelUploadStatusTracker newInstance(UploadStore uploadStore,
      UploadActionUseCase uploadActionUseCase) {
    return new PostModelUploadStatusTracker(uploadStore, uploadActionUseCase);
  }
}
