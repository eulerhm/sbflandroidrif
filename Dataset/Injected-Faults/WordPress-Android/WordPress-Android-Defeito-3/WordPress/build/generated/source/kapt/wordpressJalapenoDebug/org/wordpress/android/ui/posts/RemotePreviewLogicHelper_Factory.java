package org.wordpress.android.ui.posts;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.ActivityLauncherWrapper;
import org.wordpress.android.ui.uploads.UploadActionUseCase;
import org.wordpress.android.util.NetworkUtilsWrapper;

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
public final class RemotePreviewLogicHelper_Factory implements Factory<RemotePreviewLogicHelper> {
  private final Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider;

  private final Provider<ActivityLauncherWrapper> activityLauncherWrapperProvider;

  private final Provider<PostUtilsWrapper> postUtilsWrapperProvider;

  private final Provider<UploadActionUseCase> uploadActionUseCaseProvider;

  public RemotePreviewLogicHelper_Factory(Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider,
      Provider<ActivityLauncherWrapper> activityLauncherWrapperProvider,
      Provider<PostUtilsWrapper> postUtilsWrapperProvider,
      Provider<UploadActionUseCase> uploadActionUseCaseProvider) {
    this.networkUtilsWrapperProvider = networkUtilsWrapperProvider;
    this.activityLauncherWrapperProvider = activityLauncherWrapperProvider;
    this.postUtilsWrapperProvider = postUtilsWrapperProvider;
    this.uploadActionUseCaseProvider = uploadActionUseCaseProvider;
  }

  @Override
  public RemotePreviewLogicHelper get() {
    return newInstance(networkUtilsWrapperProvider.get(), activityLauncherWrapperProvider.get(), postUtilsWrapperProvider.get(), uploadActionUseCaseProvider.get());
  }

  public static RemotePreviewLogicHelper_Factory create(
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider,
      Provider<ActivityLauncherWrapper> activityLauncherWrapperProvider,
      Provider<PostUtilsWrapper> postUtilsWrapperProvider,
      Provider<UploadActionUseCase> uploadActionUseCaseProvider) {
    return new RemotePreviewLogicHelper_Factory(networkUtilsWrapperProvider, activityLauncherWrapperProvider, postUtilsWrapperProvider, uploadActionUseCaseProvider);
  }

  public static RemotePreviewLogicHelper newInstance(NetworkUtilsWrapper networkUtilsWrapper,
      ActivityLauncherWrapper activityLauncherWrapper, PostUtilsWrapper postUtilsWrapper,
      UploadActionUseCase uploadActionUseCase) {
    return new RemotePreviewLogicHelper(networkUtilsWrapper, activityLauncherWrapper, postUtilsWrapper, uploadActionUseCase);
  }
}
