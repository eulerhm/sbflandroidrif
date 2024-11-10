package org.wordpress.android.ui.sitecreation.sitename;

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
public final class SiteCreationSiteNameViewModel_Factory implements Factory<SiteCreationSiteNameViewModel> {
  private final Provider<SiteCreationTracker> analyticsTrackerProvider;

  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  public SiteCreationSiteNameViewModel_Factory(
      Provider<SiteCreationTracker> analyticsTrackerProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider) {
    this.analyticsTrackerProvider = analyticsTrackerProvider;
    this.bgDispatcherProvider = bgDispatcherProvider;
  }

  @Override
  public SiteCreationSiteNameViewModel get() {
    return newInstance(analyticsTrackerProvider.get(), bgDispatcherProvider.get());
  }

  public static SiteCreationSiteNameViewModel_Factory create(
      Provider<SiteCreationTracker> analyticsTrackerProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider) {
    return new SiteCreationSiteNameViewModel_Factory(analyticsTrackerProvider, bgDispatcherProvider);
  }

  public static SiteCreationSiteNameViewModel newInstance(SiteCreationTracker analyticsTracker,
      CoroutineDispatcher bgDispatcher) {
    return new SiteCreationSiteNameViewModel(analyticsTracker, bgDispatcher);
  }
}
