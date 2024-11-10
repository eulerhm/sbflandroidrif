package org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.ui.stats.refresh.utils.NewsCardHandler;
import org.wordpress.android.util.analytics.AnalyticsTrackerWrapper;
import org.wordpress.android.viewmodel.ResourceProvider;

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
public final class ManagementControlUseCase_Factory implements Factory<ManagementControlUseCase> {
  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  private final Provider<CoroutineDispatcher> backgroundDispatcherProvider;

  private final Provider<NewsCardHandler> newsCardHandlerProvider;

  private final Provider<ResourceProvider> resourceProvider;

  private final Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider;

  public ManagementControlUseCase_Factory(Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> backgroundDispatcherProvider,
      Provider<NewsCardHandler> newsCardHandlerProvider,
      Provider<ResourceProvider> resourceProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider) {
    this.mainDispatcherProvider = mainDispatcherProvider;
    this.backgroundDispatcherProvider = backgroundDispatcherProvider;
    this.newsCardHandlerProvider = newsCardHandlerProvider;
    this.resourceProvider = resourceProvider;
    this.analyticsTrackerWrapperProvider = analyticsTrackerWrapperProvider;
  }

  @Override
  public ManagementControlUseCase get() {
    return newInstance(mainDispatcherProvider.get(), backgroundDispatcherProvider.get(), newsCardHandlerProvider.get(), resourceProvider.get(), analyticsTrackerWrapperProvider.get());
  }

  public static ManagementControlUseCase_Factory create(
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> backgroundDispatcherProvider,
      Provider<NewsCardHandler> newsCardHandlerProvider,
      Provider<ResourceProvider> resourceProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider) {
    return new ManagementControlUseCase_Factory(mainDispatcherProvider, backgroundDispatcherProvider, newsCardHandlerProvider, resourceProvider, analyticsTrackerWrapperProvider);
  }

  public static ManagementControlUseCase newInstance(CoroutineDispatcher mainDispatcher,
      CoroutineDispatcher backgroundDispatcher, NewsCardHandler newsCardHandler,
      ResourceProvider resourceProvider, AnalyticsTrackerWrapper analyticsTrackerWrapper) {
    return new ManagementControlUseCase(mainDispatcher, backgroundDispatcher, newsCardHandler, resourceProvider, analyticsTrackerWrapper);
  }
}
