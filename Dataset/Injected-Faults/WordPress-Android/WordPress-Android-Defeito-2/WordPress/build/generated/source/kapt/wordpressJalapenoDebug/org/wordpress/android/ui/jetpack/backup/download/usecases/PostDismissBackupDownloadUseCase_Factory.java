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
public final class PostDismissBackupDownloadUseCase_Factory implements Factory<PostDismissBackupDownloadUseCase> {
  private final Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider;

  private final Provider<ActivityLogStore> activityLogStoreProvider;

  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  public PostDismissBackupDownloadUseCase_Factory(
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider,
      Provider<ActivityLogStore> activityLogStoreProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider) {
    this.networkUtilsWrapperProvider = networkUtilsWrapperProvider;
    this.activityLogStoreProvider = activityLogStoreProvider;
    this.bgDispatcherProvider = bgDispatcherProvider;
  }

  @Override
  public PostDismissBackupDownloadUseCase get() {
    return newInstance(networkUtilsWrapperProvider.get(), activityLogStoreProvider.get(), bgDispatcherProvider.get());
  }

  public static PostDismissBackupDownloadUseCase_Factory create(
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider,
      Provider<ActivityLogStore> activityLogStoreProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider) {
    return new PostDismissBackupDownloadUseCase_Factory(networkUtilsWrapperProvider, activityLogStoreProvider, bgDispatcherProvider);
  }

  public static PostDismissBackupDownloadUseCase newInstance(
      NetworkUtilsWrapper networkUtilsWrapper, ActivityLogStore activityLogStore,
      CoroutineDispatcher bgDispatcher) {
    return new PostDismissBackupDownloadUseCase(networkUtilsWrapper, activityLogStore, bgDispatcher);
  }
}
