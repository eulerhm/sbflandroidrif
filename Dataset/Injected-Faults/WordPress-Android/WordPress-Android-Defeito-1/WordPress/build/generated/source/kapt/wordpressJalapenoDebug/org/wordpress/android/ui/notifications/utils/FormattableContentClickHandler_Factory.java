package org.wordpress.android.ui.notifications.utils;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.ui.reader.tracker.ReaderTracker;

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
public final class FormattableContentClickHandler_Factory implements Factory<FormattableContentClickHandler> {
  private final Provider<SiteStore> siteStoreProvider;

  private final Provider<ReaderTracker> readerTrackerProvider;

  public FormattableContentClickHandler_Factory(Provider<SiteStore> siteStoreProvider,
      Provider<ReaderTracker> readerTrackerProvider) {
    this.siteStoreProvider = siteStoreProvider;
    this.readerTrackerProvider = readerTrackerProvider;
  }

  @Override
  public FormattableContentClickHandler get() {
    return newInstance(siteStoreProvider.get(), readerTrackerProvider.get());
  }

  public static FormattableContentClickHandler_Factory create(Provider<SiteStore> siteStoreProvider,
      Provider<ReaderTracker> readerTrackerProvider) {
    return new FormattableContentClickHandler_Factory(siteStoreProvider, readerTrackerProvider);
  }

  public static FormattableContentClickHandler newInstance(SiteStore siteStore,
      ReaderTracker readerTracker) {
    return new FormattableContentClickHandler(siteStore, readerTracker);
  }
}
