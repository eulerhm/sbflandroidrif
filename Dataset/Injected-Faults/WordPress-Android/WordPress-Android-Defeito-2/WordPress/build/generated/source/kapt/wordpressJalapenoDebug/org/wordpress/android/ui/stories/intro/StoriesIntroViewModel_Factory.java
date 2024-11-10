package org.wordpress.android.ui.stories.intro;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.ui.prefs.AppPrefsWrapper;
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
public final class StoriesIntroViewModel_Factory implements Factory<StoriesIntroViewModel> {
  private final Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider;

  private final Provider<AppPrefsWrapper> appPrefsWrapperProvider;

  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  public StoriesIntroViewModel_Factory(
      Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider,
      Provider<AppPrefsWrapper> appPrefsWrapperProvider,
      Provider<CoroutineDispatcher> mainDispatcherProvider) {
    this.analyticsTrackerWrapperProvider = analyticsTrackerWrapperProvider;
    this.appPrefsWrapperProvider = appPrefsWrapperProvider;
    this.mainDispatcherProvider = mainDispatcherProvider;
  }

  @Override
  public StoriesIntroViewModel get() {
    return newInstance(analyticsTrackerWrapperProvider.get(), appPrefsWrapperProvider.get(), mainDispatcherProvider.get());
  }

  public static StoriesIntroViewModel_Factory create(
      Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider,
      Provider<AppPrefsWrapper> appPrefsWrapperProvider,
      Provider<CoroutineDispatcher> mainDispatcherProvider) {
    return new StoriesIntroViewModel_Factory(analyticsTrackerWrapperProvider, appPrefsWrapperProvider, mainDispatcherProvider);
  }

  public static StoriesIntroViewModel newInstance(AnalyticsTrackerWrapper analyticsTrackerWrapper,
      AppPrefsWrapper appPrefsWrapper, CoroutineDispatcher mainDispatcher) {
    return new StoriesIntroViewModel(analyticsTrackerWrapper, appPrefsWrapper, mainDispatcher);
  }
}
