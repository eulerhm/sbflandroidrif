package org.wordpress.android.ui.jetpack.restore.usecases;

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
public final class GetRestoreStatusUseCase_Factory implements Factory<GetRestoreStatusUseCase> {
  private final Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider;

  private final Provider<ActivityLogStore> activityLogStoreProvider;

  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  public GetRestoreStatusUseCase_Factory(Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider,
      Provider<ActivityLogStore> activityLogStoreProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider) {
    this.networkUtilsWrapperProvider = networkUtilsWrapperProvider;
    this.activityLogStoreProvider = activityLogStoreProvider;
    this.bgDispatcherProvider = bgDispatcherProvider;
  }

  @Override
  public GetRestoreStatusUseCase get() {
    return newInstance(networkUtilsWrapperProvider.get(), activityLogStoreProvider.get(), bgDispatcherProvider.get());
  }

  public static GetRestoreStatusUseCase_Factory create(
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider,
      Provider<ActivityLogStore> activityLogStoreProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider) {
    return new GetRestoreStatusUseCase_Factory(networkUtilsWrapperProvider, activityLogStoreProvider, bgDispatcherProvider);
  }

  public static GetRestoreStatusUseCase newInstance(NetworkUtilsWrapper networkUtilsWrapper,
      ActivityLogStore activityLogStore, CoroutineDispatcher bgDispatcher) {
    return new GetRestoreStatusUseCase(networkUtilsWrapper, activityLogStore, bgDispatcher);
  }
}
