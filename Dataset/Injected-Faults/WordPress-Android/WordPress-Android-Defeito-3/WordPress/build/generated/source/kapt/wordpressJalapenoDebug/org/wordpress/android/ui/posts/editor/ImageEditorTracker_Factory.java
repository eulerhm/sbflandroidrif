package org.wordpress.android.ui.posts.editor;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.util.analytics.AnalyticsTrackerWrapper;

@ScopeMetadata("dagger.Reusable")
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
public final class ImageEditorTracker_Factory implements Factory<ImageEditorTracker> {
  private final Provider<Context> contextProvider;

  private final Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider;

  public ImageEditorTracker_Factory(Provider<Context> contextProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider) {
    this.contextProvider = contextProvider;
    this.analyticsTrackerWrapperProvider = analyticsTrackerWrapperProvider;
  }

  @Override
  public ImageEditorTracker get() {
    return newInstance(contextProvider.get(), analyticsTrackerWrapperProvider.get());
  }

  public static ImageEditorTracker_Factory create(Provider<Context> contextProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider) {
    return new ImageEditorTracker_Factory(contextProvider, analyticsTrackerWrapperProvider);
  }

  public static ImageEditorTracker newInstance(Context context,
      AnalyticsTrackerWrapper analyticsTrackerWrapper) {
    return new ImageEditorTracker(context, analyticsTrackerWrapper);
  }
}
