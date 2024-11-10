package org.wordpress.android.ui.mysite;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.util.EventBusWrapper;
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
public final class SiteIconUploadHandler_Factory implements Factory<SiteIconUploadHandler> {
  private final Provider<SelectedSiteRepository> selectedSiteRepositoryProvider;

  private final Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider;

  private final Provider<EventBusWrapper> eventBusWrapperProvider;

  public SiteIconUploadHandler_Factory(
      Provider<SelectedSiteRepository> selectedSiteRepositoryProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider,
      Provider<EventBusWrapper> eventBusWrapperProvider) {
    this.selectedSiteRepositoryProvider = selectedSiteRepositoryProvider;
    this.analyticsTrackerWrapperProvider = analyticsTrackerWrapperProvider;
    this.eventBusWrapperProvider = eventBusWrapperProvider;
  }

  @Override
  public SiteIconUploadHandler get() {
    return newInstance(selectedSiteRepositoryProvider.get(), analyticsTrackerWrapperProvider.get(), eventBusWrapperProvider.get());
  }

  public static SiteIconUploadHandler_Factory create(
      Provider<SelectedSiteRepository> selectedSiteRepositoryProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider,
      Provider<EventBusWrapper> eventBusWrapperProvider) {
    return new SiteIconUploadHandler_Factory(selectedSiteRepositoryProvider, analyticsTrackerWrapperProvider, eventBusWrapperProvider);
  }

  public static SiteIconUploadHandler newInstance(SelectedSiteRepository selectedSiteRepository,
      AnalyticsTrackerWrapper analyticsTrackerWrapper, EventBusWrapper eventBusWrapper) {
    return new SiteIconUploadHandler(selectedSiteRepository, analyticsTrackerWrapper, eventBusWrapper);
  }
}
