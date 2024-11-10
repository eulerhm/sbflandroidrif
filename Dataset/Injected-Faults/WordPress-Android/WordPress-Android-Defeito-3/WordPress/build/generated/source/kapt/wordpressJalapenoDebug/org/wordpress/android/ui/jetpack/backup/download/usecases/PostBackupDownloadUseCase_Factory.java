package org.wordpress.android.ui.jetpack.backup.download.usecases;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.store.ActivityLogStore;
import org.wordpress.android.util.NetworkUtilsWrapper;

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
public final class PostBackupDownloadUseCase_Factory implements Factory<PostBackupDownloadUseCase> {
  private final Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider;

  private final Provider<ActivityLogStore> activityLogStoreProvider;

  private final Provider<CoroutineDispatcher> ioDispatcherProvider;

  public PostBackupDownloadUseCase_Factory(
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider,
      Provider<ActivityLogStore> activityLogStoreProvider,
      Provider<CoroutineDispatcher> ioDispatcherProvider) {
    this.networkUtilsWrapperProvider = networkUtilsWrapperProvider;
    this.activityLogStoreProvider = activityLogStoreProvider;
    this.ioDispatcherProvider = ioDispatcherProvider;
  }

  @Override
  public PostBackupDownloadUseCase get() {
    return newInstance(networkUtilsWrapperProvider.get(), activityLogStoreProvider.get(), ioDispatcherProvider.get());
  }

  public static PostBackupDownloadUseCase_Factory create(
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider,
      Provider<ActivityLogStore> activityLogStoreProvider,
      Provider<CoroutineDispatcher> ioDispatcherProvider) {
    return new PostBackupDownloadUseCase_Factory(networkUtilsWrapperProvider, activityLogStoreProvider, ioDispatcherProvider);
  }

  public static PostBackupDownloadUseCase newInstance(NetworkUtilsWrapper networkUtilsWrapper,
      ActivityLogStore activityLogStore, CoroutineDispatcher ioDispatcher) {
    return new PostBackupDownloadUseCase(networkUtilsWrapper, activityLogStore, ioDispatcher);
  }
}
