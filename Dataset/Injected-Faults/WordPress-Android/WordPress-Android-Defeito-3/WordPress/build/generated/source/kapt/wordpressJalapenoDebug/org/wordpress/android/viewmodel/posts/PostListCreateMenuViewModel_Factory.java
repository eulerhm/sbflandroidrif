package org.wordpress.android.viewmodel.posts;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.prefs.AppPrefsWrapper;
import org.wordpress.android.util.analytics.AnalyticsTrackerWrapper;

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
public final class PostListCreateMenuViewModel_Factory implements Factory<PostListCreateMenuViewModel> {
  private final Provider<AppPrefsWrapper> appPrefsWrapperProvider;

  private final Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider;

  public PostListCreateMenuViewModel_Factory(Provider<AppPrefsWrapper> appPrefsWrapperProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider) {
    this.appPrefsWrapperProvider = appPrefsWrapperProvider;
    this.analyticsTrackerProvider = analyticsTrackerProvider;
  }

  @Override
  public PostListCreateMenuViewModel get() {
    return newInstance(appPrefsWrapperProvider.get(), analyticsTrackerProvider.get());
  }

  public static PostListCreateMenuViewModel_Factory create(
      Provider<AppPrefsWrapper> appPrefsWrapperProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider) {
    return new PostListCreateMenuViewModel_Factory(appPrefsWrapperProvider, analyticsTrackerProvider);
  }

  public static PostListCreateMenuViewModel newInstance(AppPrefsWrapper appPrefsWrapper,
      AnalyticsTrackerWrapper analyticsTracker) {
    return new PostListCreateMenuViewModel(appPrefsWrapper, analyticsTracker);
  }
}
