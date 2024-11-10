package org.wordpress.android.viewmodel.activitylog;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.store.ActivityLogStore;
import org.wordpress.android.ui.utils.HtmlMessageUtils;
import org.wordpress.android.viewmodel.ResourceProvider;

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
public final class ActivityLogDetailViewModel_Factory implements Factory<ActivityLogDetailViewModel> {
  private final Provider<Dispatcher> dispatcherProvider;

  private final Provider<ActivityLogStore> activityLogStoreProvider;

  private final Provider<ResourceProvider> resourceProvider;

  private final Provider<HtmlMessageUtils> htmlMessageUtilsProvider;

  public ActivityLogDetailViewModel_Factory(Provider<Dispatcher> dispatcherProvider,
      Provider<ActivityLogStore> activityLogStoreProvider,
      Provider<ResourceProvider> resourceProvider,
      Provider<HtmlMessageUtils> htmlMessageUtilsProvider) {
    this.dispatcherProvider = dispatcherProvider;
    this.activityLogStoreProvider = activityLogStoreProvider;
    this.resourceProvider = resourceProvider;
    this.htmlMessageUtilsProvider = htmlMessageUtilsProvider;
  }

  @Override
  public ActivityLogDetailViewModel get() {
    return newInstance(dispatcherProvider.get(), activityLogStoreProvider.get(), resourceProvider.get(), htmlMessageUtilsProvider.get());
  }

  public static ActivityLogDetailViewModel_Factory create(Provider<Dispatcher> dispatcherProvider,
      Provider<ActivityLogStore> activityLogStoreProvider,
      Provider<ResourceProvider> resourceProvider,
      Provider<HtmlMessageUtils> htmlMessageUtilsProvider) {
    return new ActivityLogDetailViewModel_Factory(dispatcherProvider, activityLogStoreProvider, resourceProvider, htmlMessageUtilsProvider);
  }

  public static ActivityLogDetailViewModel newInstance(Dispatcher dispatcher,
      ActivityLogStore activityLogStore, ResourceProvider resourceProvider,
      HtmlMessageUtils htmlMessageUtils) {
    return new ActivityLogDetailViewModel(dispatcher, activityLogStore, resourceProvider, htmlMessageUtils);
  }
}
