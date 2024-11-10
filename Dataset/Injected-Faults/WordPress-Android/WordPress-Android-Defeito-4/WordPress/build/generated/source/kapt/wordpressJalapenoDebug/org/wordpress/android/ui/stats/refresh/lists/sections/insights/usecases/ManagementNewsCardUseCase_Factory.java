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
public final class ManagementNewsCardUseCase_Factory implements Factory<ManagementNewsCardUseCase> {
  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  private final Provider<CoroutineDispatcher> backgroundDispatcherProvider;

  private final Provider<ResourceProvider> resourceProvider;

  private final Provider<NewsCardHandler> newsCardHandlerProvider;

  private final Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider;

  public ManagementNewsCardUseCase_Factory(Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> backgroundDispatcherProvider,
      Provider<ResourceProvider> resourceProvider,
      Provider<NewsCardHandler> newsCardHandlerProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider) {
    this.mainDispatcherProvider = mainDispatcherProvider;
    this.backgroundDispatcherProvider = backgroundDispatcherProvider;
    this.resourceProvider = resourceProvider;
    this.newsCardHandlerProvider = newsCardHandlerProvider;
    this.analyticsTrackerWrapperProvider = analyticsTrackerWrapperProvider;
  }

  @Override
  public ManagementNewsCardUseCase get() {
    return newInstance(mainDispatcherProvider.get(), backgroundDispatcherProvider.get(), resourceProvider.get(), newsCardHandlerProvider.get(), analyticsTrackerWrapperProvider.get());
  }

  public static ManagementNewsCardUseCase_Factory create(
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> backgroundDispatcherProvider,
      Provider<ResourceProvider> resourceProvider,
      Provider<NewsCardHandler> newsCardHandlerProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider) {
    return new ManagementNewsCardUseCase_Factory(mainDispatcherProvider, backgroundDispatcherProvider, resourceProvider, newsCardHandlerProvider, analyticsTrackerWrapperProvider);
  }

  public static ManagementNewsCardUseCase newInstance(CoroutineDispatcher mainDispatcher,
      CoroutineDispatcher backgroundDispatcher, ResourceProvider resourceProvider,
      NewsCardHandler newsCardHandler, AnalyticsTrackerWrapper analyticsTrackerWrapper) {
    return new ManagementNewsCardUseCase(mainDispatcher, backgroundDispatcher, resourceProvider, newsCardHandler, analyticsTrackerWrapper);
  }
}
