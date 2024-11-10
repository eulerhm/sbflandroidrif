package org.wordpress.android.modules;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.analytics.Tracker;

@ScopeMetadata
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class TrackerModule_ProvideTrackerFactory implements Factory<Tracker> {
  private final TrackerModule module;

  private final Provider<Context> appContextProvider;

  public TrackerModule_ProvideTrackerFactory(TrackerModule module,
      Provider<Context> appContextProvider) {
    this.module = module;
    this.appContextProvider = appContextProvider;
  }

  @Override
  public Tracker get() {
    return provideTracker(module, appContextProvider.get());
  }

  public static TrackerModule_ProvideTrackerFactory create(TrackerModule module,
      Provider<Context> appContextProvider) {
    return new TrackerModule_ProvideTrackerFactory(module, appContextProvider);
  }

  public static Tracker provideTracker(TrackerModule instance, Context appContext) {
    return Preconditions.checkNotNullFromProvides(instance.provideTracker(appContext));
  }
}
