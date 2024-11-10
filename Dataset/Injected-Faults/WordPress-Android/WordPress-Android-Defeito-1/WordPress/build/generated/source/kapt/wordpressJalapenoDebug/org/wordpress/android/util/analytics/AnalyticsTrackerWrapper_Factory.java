package org.wordpress.android.util.analytics;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;

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
public final class AnalyticsTrackerWrapper_Factory implements Factory<AnalyticsTrackerWrapper> {
  @Override
  public AnalyticsTrackerWrapper get() {
    return newInstance();
  }

  public static AnalyticsTrackerWrapper_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static AnalyticsTrackerWrapper newInstance() {
    return new AnalyticsTrackerWrapper();
  }

  private static final class InstanceHolder {
    private static final AnalyticsTrackerWrapper_Factory INSTANCE = new AnalyticsTrackerWrapper_Factory();
  }
}
