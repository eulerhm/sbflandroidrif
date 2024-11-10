package org.wordpress.android.ui.posts.editor.media;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.uploads.UploadServiceFacade;

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
public final class ReattachUploadingMediaUseCase_Factory implements Factory<ReattachUploadingMediaUseCase> {
  private final Provider<UploadServiceFacade> uploadServiceFacadeProvider;

  public ReattachUploadingMediaUseCase_Factory(
      Provider<UploadServiceFacade> uploadServiceFacadeProvider) {
    this.uploadServiceFacadeProvider = uploadServiceFacadeProvider;
  }

  @Override
  public ReattachUploadingMediaUseCase get() {
    return newInstance(uploadServiceFacadeProvider.get());
  }

  public static ReattachUploadingMediaUseCase_Factory create(
      Provider<UploadServiceFacade> uploadServiceFacadeProvider) {
    return new ReattachUploadingMediaUseCase_Factory(uploadServiceFacadeProvider);
  }

  public static ReattachUploadingMediaUseCase newInstance(UploadServiceFacade uploadServiceFacade) {
    return new ReattachUploadingMediaUseCase(uploadServiceFacade);
  }
}
