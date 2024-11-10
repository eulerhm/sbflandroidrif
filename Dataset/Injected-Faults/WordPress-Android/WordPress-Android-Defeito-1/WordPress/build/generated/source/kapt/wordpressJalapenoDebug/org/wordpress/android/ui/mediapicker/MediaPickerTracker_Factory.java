package org.wordpress.android.ui.mediapicker;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.util.analytics.AnalyticsTrackerWrapper;
import org.wordpress.android.util.analytics.AnalyticsUtilsWrapper;

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
public final class MediaPickerTracker_Factory implements Factory<MediaPickerTracker> {
  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  private final Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider;

  private final Provider<AnalyticsUtilsWrapper> analyticsUtilsWrapperProvider;

  public MediaPickerTracker_Factory(Provider<CoroutineDispatcher> bgDispatcherProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider,
      Provider<AnalyticsUtilsWrapper> analyticsUtilsWrapperProvider) {
    this.bgDispatcherProvider = bgDispatcherProvider;
    this.analyticsTrackerWrapperProvider = analyticsTrackerWrapperProvider;
    this.analyticsUtilsWrapperProvider = analyticsUtilsWrapperProvider;
  }

  @Override
  public MediaPickerTracker get() {
    return newInstance(bgDispatcherProvider.get(), analyticsTrackerWrapperProvider.get(), analyticsUtilsWrapperProvider.get());
  }

  public static MediaPickerTracker_Factory create(
      Provider<CoroutineDispatcher> bgDispatcherProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider,
      Provider<AnalyticsUtilsWrapper> analyticsUtilsWrapperProvider) {
    return new MediaPickerTracker_Factory(bgDispatcherProvider, analyticsTrackerWrapperProvider, analyticsUtilsWrapperProvider);
  }

  public static MediaPickerTracker newInstance(CoroutineDispatcher bgDispatcher,
      AnalyticsTrackerWrapper analyticsTrackerWrapper,
      AnalyticsUtilsWrapper analyticsUtilsWrapper) {
    return new MediaPickerTracker(bgDispatcher, analyticsTrackerWrapper, analyticsUtilsWrapper);
  }
}
