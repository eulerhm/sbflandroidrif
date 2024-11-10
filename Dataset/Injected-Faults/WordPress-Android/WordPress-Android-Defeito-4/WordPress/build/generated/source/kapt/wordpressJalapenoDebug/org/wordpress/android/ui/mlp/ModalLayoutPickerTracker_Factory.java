package org.wordpress.android.ui.mlp;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.util.analytics.AnalyticsTrackerWrapper;

@ScopeMetadata("javax.inject.Singleton")
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
public final class ModalLayoutPickerTracker_Factory implements Factory<ModalLayoutPickerTracker> {
  private final Provider<AnalyticsTrackerWrapper> trackerProvider;

  public ModalLayoutPickerTracker_Factory(Provider<AnalyticsTrackerWrapper> trackerProvider) {
    this.trackerProvider = trackerProvider;
  }

  @Override
  public ModalLayoutPickerTracker get() {
    return newInstance(trackerProvider.get());
  }

  public static ModalLayoutPickerTracker_Factory create(
      Provider<AnalyticsTrackerWrapper> trackerProvider) {
    return new ModalLayoutPickerTracker_Factory(trackerProvider);
  }

  public static ModalLayoutPickerTracker newInstance(AnalyticsTrackerWrapper tracker) {
    return new ModalLayoutPickerTracker(tracker);
  }
}
