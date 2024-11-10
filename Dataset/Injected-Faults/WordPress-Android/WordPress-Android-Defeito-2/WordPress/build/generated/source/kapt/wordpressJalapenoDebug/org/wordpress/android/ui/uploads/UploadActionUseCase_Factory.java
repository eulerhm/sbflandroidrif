package org.wordpress.android.ui.uploads;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.store.UploadStore;
import org.wordpress.android.ui.posts.PostUtilsWrapper;

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
public final class UploadActionUseCase_Factory implements Factory<UploadActionUseCase> {
  private final Provider<UploadStore> uploadStoreProvider;

  private final Provider<PostUtilsWrapper> postUtilsWrapperProvider;

  private final Provider<UploadServiceFacade> uploadServiceFacadeProvider;

  public UploadActionUseCase_Factory(Provider<UploadStore> uploadStoreProvider,
      Provider<PostUtilsWrapper> postUtilsWrapperProvider,
      Provider<UploadServiceFacade> uploadServiceFacadeProvider) {
    this.uploadStoreProvider = uploadStoreProvider;
    this.postUtilsWrapperProvider = postUtilsWrapperProvider;
    this.uploadServiceFacadeProvider = uploadServiceFacadeProvider;
  }

  @Override
  public UploadActionUseCase get() {
    return newInstance(uploadStoreProvider.get(), postUtilsWrapperProvider.get(), uploadServiceFacadeProvider.get());
  }

  public static UploadActionUseCase_Factory create(Provider<UploadStore> uploadStoreProvider,
      Provider<PostUtilsWrapper> postUtilsWrapperProvider,
      Provider<UploadServiceFacade> uploadServiceFacadeProvider) {
    return new UploadActionUseCase_Factory(uploadStoreProvider, postUtilsWrapperProvider, uploadServiceFacadeProvider);
  }

  public static UploadActionUseCase newInstance(UploadStore uploadStore,
      PostUtilsWrapper postUtilsWrapper, UploadServiceFacade uploadServiceFacade) {
    return new UploadActionUseCase(uploadStore, postUtilsWrapper, uploadServiceFacade);
  }
}
