package org.wordpress.android.ui.photopicker;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
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
public final class MediaPickerLauncher_Factory implements Factory<MediaPickerLauncher> {
  private final Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider;

  public MediaPickerLauncher_Factory(
      Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider) {
    this.analyticsTrackerWrapperProvider = analyticsTrackerWrapperProvider;
  }

  @Override
  public MediaPickerLauncher get() {
    return newInstance(analyticsTrackerWrapperProvider.get());
  }

  public static MediaPickerLauncher_Factory create(
      Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider) {
    return new MediaPickerLauncher_Factory(analyticsTrackerWrapperProvider);
  }

  public static MediaPickerLauncher newInstance(AnalyticsTrackerWrapper analyticsTrackerWrapper) {
    return new MediaPickerLauncher(analyticsTrackerWrapper);
  }
}
