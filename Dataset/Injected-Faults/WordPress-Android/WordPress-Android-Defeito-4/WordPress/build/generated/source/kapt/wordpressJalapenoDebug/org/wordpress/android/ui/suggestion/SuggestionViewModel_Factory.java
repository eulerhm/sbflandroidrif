package org.wordpress.android.ui.suggestion;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.util.NetworkUtilsWrapper;
import org.wordpress.android.util.analytics.AnalyticsTrackerWrapper;
import org.wordpress.android.viewmodel.ResourceProvider;

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
public final class SuggestionViewModel_Factory implements Factory<SuggestionViewModel> {
  private final Provider<SuggestionSourceProvider> suggestionSourceProvider;

  private final Provider<ResourceProvider> resourceProvider;

  private final Provider<NetworkUtilsWrapper> networkUtilsProvider;

  private final Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider;

  public SuggestionViewModel_Factory(Provider<SuggestionSourceProvider> suggestionSourceProvider,
      Provider<ResourceProvider> resourceProvider,
      Provider<NetworkUtilsWrapper> networkUtilsProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider) {
    this.suggestionSourceProvider = suggestionSourceProvider;
    this.resourceProvider = resourceProvider;
    this.networkUtilsProvider = networkUtilsProvider;
    this.analyticsTrackerProvider = analyticsTrackerProvider;
  }

  @Override
  public SuggestionViewModel get() {
    return newInstance(suggestionSourceProvider.get(), resourceProvider.get(), networkUtilsProvider.get(), analyticsTrackerProvider.get());
  }

  public static SuggestionViewModel_Factory create(
      Provider<SuggestionSourceProvider> suggestionSourceProvider,
      Provider<ResourceProvider> resourceProvider,
      Provider<NetworkUtilsWrapper> networkUtilsProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider) {
    return new SuggestionViewModel_Factory(suggestionSourceProvider, resourceProvider, networkUtilsProvider, analyticsTrackerProvider);
  }

  public static SuggestionViewModel newInstance(SuggestionSourceProvider suggestionSourceProvider,
      ResourceProvider resourceProvider, NetworkUtilsWrapper networkUtils,
      AnalyticsTrackerWrapper analyticsTracker) {
    return new SuggestionViewModel(suggestionSourceProvider, resourceProvider, networkUtils, analyticsTracker);
  }
}
