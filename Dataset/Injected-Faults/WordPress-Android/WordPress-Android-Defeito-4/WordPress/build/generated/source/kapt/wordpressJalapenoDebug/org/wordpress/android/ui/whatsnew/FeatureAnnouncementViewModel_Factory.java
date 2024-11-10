package org.wordpress.android.ui.whatsnew;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.ui.prefs.AppPrefsWrapper;
import org.wordpress.android.util.BuildConfigWrapper;
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
public final class FeatureAnnouncementViewModel_Factory implements Factory<FeatureAnnouncementViewModel> {
  private final Provider<FeatureAnnouncementProvider> featureAnnouncementProvider;

  private final Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider;

  private final Provider<BuildConfigWrapper> buildConfigWrapperProvider;

  private final Provider<AppPrefsWrapper> appPrefsWrapperProvider;

  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  public FeatureAnnouncementViewModel_Factory(
      Provider<FeatureAnnouncementProvider> featureAnnouncementProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider,
      Provider<BuildConfigWrapper> buildConfigWrapperProvider,
      Provider<AppPrefsWrapper> appPrefsWrapperProvider,
      Provider<CoroutineDispatcher> mainDispatcherProvider) {
    this.featureAnnouncementProvider = featureAnnouncementProvider;
    this.analyticsTrackerWrapperProvider = analyticsTrackerWrapperProvider;
    this.buildConfigWrapperProvider = buildConfigWrapperProvider;
    this.appPrefsWrapperProvider = appPrefsWrapperProvider;
    this.mainDispatcherProvider = mainDispatcherProvider;
  }

  @Override
  public FeatureAnnouncementViewModel get() {
    return newInstance(featureAnnouncementProvider.get(), analyticsTrackerWrapperProvider.get(), buildConfigWrapperProvider.get(), appPrefsWrapperProvider.get(), mainDispatcherProvider.get());
  }

  public static FeatureAnnouncementViewModel_Factory create(
      Provider<FeatureAnnouncementProvider> featureAnnouncementProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider,
      Provider<BuildConfigWrapper> buildConfigWrapperProvider,
      Provider<AppPrefsWrapper> appPrefsWrapperProvider,
      Provider<CoroutineDispatcher> mainDispatcherProvider) {
    return new FeatureAnnouncementViewModel_Factory(featureAnnouncementProvider, analyticsTrackerWrapperProvider, buildConfigWrapperProvider, appPrefsWrapperProvider, mainDispatcherProvider);
  }

  public static FeatureAnnouncementViewModel newInstance(
      FeatureAnnouncementProvider featureAnnouncementProvider,
      AnalyticsTrackerWrapper analyticsTrackerWrapper, BuildConfigWrapper buildConfigWrapper,
      AppPrefsWrapper appPrefsWrapper, CoroutineDispatcher mainDispatcher) {
    return new FeatureAnnouncementViewModel(featureAnnouncementProvider, analyticsTrackerWrapper, buildConfigWrapper, appPrefsWrapper, mainDispatcher);
  }
}
