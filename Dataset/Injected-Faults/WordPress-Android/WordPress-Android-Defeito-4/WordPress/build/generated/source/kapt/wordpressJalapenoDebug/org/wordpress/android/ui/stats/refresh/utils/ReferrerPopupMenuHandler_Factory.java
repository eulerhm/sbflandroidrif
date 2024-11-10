package org.wordpress.android.ui.stats.refresh.utils;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.util.analytics.AnalyticsTrackerWrapper;

@ScopeMetadata("javax.inject.Singleton")
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
public final class ReferrerPopupMenuHandler_Factory implements Factory<ReferrerPopupMenuHandler> {
  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  private final Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider;

  public ReferrerPopupMenuHandler_Factory(Provider<CoroutineDispatcher> bgDispatcherProvider,
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider) {
    this.bgDispatcherProvider = bgDispatcherProvider;
    this.mainDispatcherProvider = mainDispatcherProvider;
    this.analyticsTrackerWrapperProvider = analyticsTrackerWrapperProvider;
  }

  @Override
  public ReferrerPopupMenuHandler get() {
    return newInstance(bgDispatcherProvider.get(), mainDispatcherProvider.get(), analyticsTrackerWrapperProvider.get());
  }

  public static ReferrerPopupMenuHandler_Factory create(
      Provider<CoroutineDispatcher> bgDispatcherProvider,
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider) {
    return new ReferrerPopupMenuHandler_Factory(bgDispatcherProvider, mainDispatcherProvider, analyticsTrackerWrapperProvider);
  }

  public static ReferrerPopupMenuHandler newInstance(CoroutineDispatcher bgDispatcher,
      CoroutineDispatcher mainDispatcher, AnalyticsTrackerWrapper analyticsTrackerWrapper) {
    return new ReferrerPopupMenuHandler(bgDispatcher, mainDispatcher, analyticsTrackerWrapper);
  }
}
