package org.wordpress.android.ui.quickstart;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.mysite.SelectedSiteRepository;
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
public final class QuickStartTracker_Factory implements Factory<QuickStartTracker> {
  private final Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider;

  private final Provider<AppPrefsWrapper> appPrefsWrapperProvider;

  private final Provider<SelectedSiteRepository> selectedSiteRepositoryProvider;

  public QuickStartTracker_Factory(
      Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider,
      Provider<AppPrefsWrapper> appPrefsWrapperProvider,
      Provider<SelectedSiteRepository> selectedSiteRepositoryProvider) {
    this.analyticsTrackerWrapperProvider = analyticsTrackerWrapperProvider;
    this.appPrefsWrapperProvider = appPrefsWrapperProvider;
    this.selectedSiteRepositoryProvider = selectedSiteRepositoryProvider;
  }

  @Override
  public QuickStartTracker get() {
    return newInstance(analyticsTrackerWrapperProvider.get(), appPrefsWrapperProvider.get(), selectedSiteRepositoryProvider.get());
  }

  public static QuickStartTracker_Factory create(
      Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider,
      Provider<AppPrefsWrapper> appPrefsWrapperProvider,
      Provider<SelectedSiteRepository> selectedSiteRepositoryProvider) {
    return new QuickStartTracker_Factory(analyticsTrackerWrapperProvider, appPrefsWrapperProvider, selectedSiteRepositoryProvider);
  }

  public static QuickStartTracker newInstance(AnalyticsTrackerWrapper analyticsTrackerWrapper,
      AppPrefsWrapper appPrefsWrapper, SelectedSiteRepository selectedSiteRepository) {
    return new QuickStartTracker(analyticsTrackerWrapper, appPrefsWrapper, selectedSiteRepository);
  }
}
