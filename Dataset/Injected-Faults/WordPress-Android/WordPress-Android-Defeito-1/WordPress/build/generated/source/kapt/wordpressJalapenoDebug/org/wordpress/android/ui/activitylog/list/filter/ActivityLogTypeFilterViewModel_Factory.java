package org.wordpress.android.ui.activitylog.list.filter;

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
public final class ActivityLogTypeFilterViewModel_Factory implements Factory<ActivityLogTypeFilterViewModel> {
  private final Provider<ActivityLogStore> activityLogStoreProvider;

  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  public ActivityLogTypeFilterViewModel_Factory(Provider<ActivityLogStore> activityLogStoreProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider,
      Provider<CoroutineDispatcher> mainDispatcherProvider) {
    this.activityLogStoreProvider = activityLogStoreProvider;
    this.bgDispatcherProvider = bgDispatcherProvider;
    this.mainDispatcherProvider = mainDispatcherProvider;
  }

  @Override
  public ActivityLogTypeFilterViewModel get() {
    return newInstance(activityLogStoreProvider.get(), bgDispatcherProvider.get(), mainDispatcherProvider.get());
  }

  public static ActivityLogTypeFilterViewModel_Factory create(
      Provider<ActivityLogStore> activityLogStoreProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider,
      Provider<CoroutineDispatcher> mainDispatcherProvider) {
    return new ActivityLogTypeFilterViewModel_Factory(activityLogStoreProvider, bgDispatcherProvider, mainDispatcherProvider);
  }

  public static ActivityLogTypeFilterViewModel newInstance(ActivityLogStore activityLogStore,
      CoroutineDispatcher bgDispatcher, CoroutineDispatcher mainDispatcher) {
    return new ActivityLogTypeFilterViewModel(activityLogStore, bgDispatcher, mainDispatcher);
  }
}
