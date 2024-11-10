package org.wordpress.android.ui.jetpack.scan.history;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.ui.jetpack.scan.builders.ThreatItemBuilder;
import org.wordpress.android.util.analytics.ScanTracker;

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
public final class ScanHistoryListViewModel_Factory implements Factory<ScanHistoryListViewModel> {
  private final Provider<ThreatItemBuilder> scanThreatItemBuilderProvider;

  private final Provider<ScanTracker> scanTrackerProvider;

  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  public ScanHistoryListViewModel_Factory(Provider<ThreatItemBuilder> scanThreatItemBuilderProvider,
      Provider<ScanTracker> scanTrackerProvider,
      Provider<CoroutineDispatcher> mainDispatcherProvider) {
    this.scanThreatItemBuilderProvider = scanThreatItemBuilderProvider;
    this.scanTrackerProvider = scanTrackerProvider;
    this.mainDispatcherProvider = mainDispatcherProvider;
  }

  @Override
  public ScanHistoryListViewModel get() {
    return newInstance(scanThreatItemBuilderProvider.get(), scanTrackerProvider.get(), mainDispatcherProvider.get());
  }

  public static ScanHistoryListViewModel_Factory create(
      Provider<ThreatItemBuilder> scanThreatItemBuilderProvider,
      Provider<ScanTracker> scanTrackerProvider,
      Provider<CoroutineDispatcher> mainDispatcherProvider) {
    return new ScanHistoryListViewModel_Factory(scanThreatItemBuilderProvider, scanTrackerProvider, mainDispatcherProvider);
  }

  public static ScanHistoryListViewModel newInstance(ThreatItemBuilder scanThreatItemBuilder,
      ScanTracker scanTracker, CoroutineDispatcher mainDispatcher) {
    return new ScanHistoryListViewModel(scanThreatItemBuilder, scanTracker, mainDispatcher);
  }
}
