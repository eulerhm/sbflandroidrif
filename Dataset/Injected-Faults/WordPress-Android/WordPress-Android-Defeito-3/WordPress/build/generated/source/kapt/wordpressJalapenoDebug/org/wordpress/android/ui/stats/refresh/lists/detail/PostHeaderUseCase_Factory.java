package org.wordpress.android.ui.stats.refresh.lists.detail;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.ui.stats.refresh.utils.StatsPostProvider;
import org.wordpress.android.util.analytics.AnalyticsTrackerWrapper;

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
public final class PostHeaderUseCase_Factory implements Factory<PostHeaderUseCase> {
  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  private final Provider<CoroutineDispatcher> backgroundDispatcherProvider;

  private final Provider<StatsPostProvider> statsPostProvider;

  private final Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider;

  public PostHeaderUseCase_Factory(Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> backgroundDispatcherProvider,
      Provider<StatsPostProvider> statsPostProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider) {
    this.mainDispatcherProvider = mainDispatcherProvider;
    this.backgroundDispatcherProvider = backgroundDispatcherProvider;
    this.statsPostProvider = statsPostProvider;
    this.analyticsTrackerProvider = analyticsTrackerProvider;
  }

  @Override
  public PostHeaderUseCase get() {
    return newInstance(mainDispatcherProvider.get(), backgroundDispatcherProvider.get(), statsPostProvider.get(), analyticsTrackerProvider.get());
  }

  public static PostHeaderUseCase_Factory create(
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> backgroundDispatcherProvider,
      Provider<StatsPostProvider> statsPostProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider) {
    return new PostHeaderUseCase_Factory(mainDispatcherProvider, backgroundDispatcherProvider, statsPostProvider, analyticsTrackerProvider);
  }

  public static PostHeaderUseCase newInstance(CoroutineDispatcher mainDispatcher,
      CoroutineDispatcher backgroundDispatcher, StatsPostProvider statsPostProvider,
      AnalyticsTrackerWrapper analyticsTracker) {
    return new PostHeaderUseCase(mainDispatcher, backgroundDispatcher, statsPostProvider, analyticsTracker);
  }
}
