package org.wordpress.android.ui.sitecreation.verticals;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.ui.sitecreation.misc.SiteCreationTracker;

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
public final class SiteCreationIntentsViewModel_Factory implements Factory<SiteCreationIntentsViewModel> {
  private final Provider<SiteCreationTracker> analyticsTrackerProvider;

  private final Provider<VerticalsSearchResultsProvider> searchResultsProvider;

  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  public SiteCreationIntentsViewModel_Factory(
      Provider<SiteCreationTracker> analyticsTrackerProvider,
      Provider<VerticalsSearchResultsProvider> searchResultsProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider) {
    this.analyticsTrackerProvider = analyticsTrackerProvider;
    this.searchResultsProvider = searchResultsProvider;
    this.bgDispatcherProvider = bgDispatcherProvider;
  }

  @Override
  public SiteCreationIntentsViewModel get() {
    return newInstance(analyticsTrackerProvider.get(), searchResultsProvider.get(), bgDispatcherProvider.get());
  }

  public static SiteCreationIntentsViewModel_Factory create(
      Provider<SiteCreationTracker> analyticsTrackerProvider,
      Provider<VerticalsSearchResultsProvider> searchResultsProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider) {
    return new SiteCreationIntentsViewModel_Factory(analyticsTrackerProvider, searchResultsProvider, bgDispatcherProvider);
  }

  public static SiteCreationIntentsViewModel newInstance(SiteCreationTracker analyticsTracker,
      VerticalsSearchResultsProvider searchResultsProvider, CoroutineDispatcher bgDispatcher) {
    return new SiteCreationIntentsViewModel(analyticsTracker, searchResultsProvider, bgDispatcher);
  }
}
