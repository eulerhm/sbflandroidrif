package org.wordpress.android.ui.stats.refresh.utils;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;

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
public final class StatsPostProvider_Factory implements Factory<StatsPostProvider> {
  @Override
  public StatsPostProvider get() {
    return newInstance();
  }

  public static StatsPostProvider_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static StatsPostProvider newInstance() {
    return new StatsPostProvider();
  }

  private static final class InstanceHolder {
    private static final StatsPostProvider_Factory INSTANCE = new StatsPostProvider_Factory();
  }
}
