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
public final class EditorTracker_Factory implements Factory<EditorTracker> {
  private final Provider<Context> contextProvider;

  private final Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider;

  public EditorTracker_Factory(Provider<Context> contextProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider) {
    this.contextProvider = contextProvider;
    this.analyticsTrackerWrapperProvider = analyticsTrackerWrapperProvider;
  }

  @Override
  public EditorTracker get() {
    return newInstance(contextProvider.get(), analyticsTrackerWrapperProvider.get());
  }

  public static EditorTracker_Factory create(Provider<Context> contextProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider) {
    return new EditorTracker_Factory(contextProvider, analyticsTrackerWrapperProvider);
  }

  public static EditorTracker newInstance(Context context,
      AnalyticsTrackerWrapper analyticsTrackerWrapper) {
    return new EditorTracker(context, analyticsTrackerWrapper);
  }
}
