package org.wordpress.android.ui.posts.editor.media;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.uploads.UploadServiceFacade;

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
public final class UploadMediaUseCase_Factory implements Factory<UploadMediaUseCase> {
  private final Provider<UploadServiceFacade> uploadServiceFacadeProvider;

  public UploadMediaUseCase_Factory(Provider<UploadServiceFacade> uploadServiceFacadeProvider) {
    this.uploadServiceFacadeProvider = uploadServiceFacadeProvider;
  }

  @Override
  public UploadMediaUseCase get() {
    return newInstance(uploadServiceFacadeProvider.get());
  }

  public static UploadMediaUseCase_Factory create(
      Provider<UploadServiceFacade> uploadServiceFacadeProvider) {
    return new UploadMediaUseCase_Factory(uploadServiceFacadeProvider);
  }

  public static UploadMediaUseCase newInstance(UploadServiceFacade uploadServiceFacade) {
    return new UploadMediaUseCase(uploadServiceFacade);
  }
}
