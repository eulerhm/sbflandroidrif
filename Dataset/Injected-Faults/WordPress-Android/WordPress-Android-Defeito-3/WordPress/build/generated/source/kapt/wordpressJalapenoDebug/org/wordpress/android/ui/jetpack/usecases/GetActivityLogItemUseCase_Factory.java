package org.wordpress.android.ui.jetpack.usecases;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.store.ActivityLogStore;

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
public final class GetActivityLogItemUseCase_Factory implements Factory<GetActivityLogItemUseCase> {
  private final Provider<CoroutineDispatcher> ioDispatcherProvider;

  private final Provider<ActivityLogStore> activityLogStoreProvider;

  public GetActivityLogItemUseCase_Factory(Provider<CoroutineDispatcher> ioDispatcherProvider,
      Provider<ActivityLogStore> activityLogStoreProvider) {
    this.ioDispatcherProvider = ioDispatcherProvider;
    this.activityLogStoreProvider = activityLogStoreProvider;
  }

  @Override
  public GetActivityLogItemUseCase get() {
    return newInstance(ioDispatcherProvider.get(), activityLogStoreProvider.get());
  }

  public static GetActivityLogItemUseCase_Factory create(
      Provider<CoroutineDispatcher> ioDispatcherProvider,
      Provider<ActivityLogStore> activityLogStoreProvider) {
    return new GetActivityLogItemUseCase_Factory(ioDispatcherProvider, activityLogStoreProvider);
  }

  public static GetActivityLogItemUseCase newInstance(CoroutineDispatcher ioDispatcher,
      ActivityLogStore activityLogStore) {
    return new GetActivityLogItemUseCase(ioDispatcher, activityLogStore);
  }
}
