package org.wordpress.android.util.analytics;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.store.ScanStore;

@ScopeMetadata("dagger.Reusable")
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
public final class ScanTracker_Factory implements Factory<ScanTracker> {
  private final Provider<ScanStore> scanStoreProvider;

  private final Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider;

  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  public ScanTracker_Factory(Provider<ScanStore> scanStoreProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider) {
    this.scanStoreProvider = scanStoreProvider;
    this.analyticsTrackerWrapperProvider = analyticsTrackerWrapperProvider;
    this.bgDispatcherProvider = bgDispatcherProvider;
  }

  @Override
  public ScanTracker get() {
    return newInstance(scanStoreProvider.get(), analyticsTrackerWrapperProvider.get(), bgDispatcherProvider.get());
  }

  public static ScanTracker_Factory create(Provider<ScanStore> scanStoreProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider) {
    return new ScanTracker_Factory(scanStoreProvider, analyticsTrackerWrapperProvider, bgDispatcherProvider);
  }

  public static ScanTracker newInstance(ScanStore scanStore,
      AnalyticsTrackerWrapper analyticsTrackerWrapper, CoroutineDispatcher bgDispatcher) {
    return new ScanTracker(scanStore, analyticsTrackerWrapper, bgDispatcher);
  }
}
