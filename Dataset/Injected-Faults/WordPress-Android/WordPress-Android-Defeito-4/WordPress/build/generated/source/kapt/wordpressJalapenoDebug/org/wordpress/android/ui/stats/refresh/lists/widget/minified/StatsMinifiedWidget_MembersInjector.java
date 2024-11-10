package org.wordpress.android.ui.stats.refresh.lists.widget.minified;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;

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
public final class StatsMinifiedWidget_MembersInjector implements MembersInjector<StatsMinifiedWidget> {
  private final Provider<MinifiedWidgetUpdater> minifiedWidgetUpdaterProvider;

  public StatsMinifiedWidget_MembersInjector(
      Provider<MinifiedWidgetUpdater> minifiedWidgetUpdaterProvider) {
    this.minifiedWidgetUpdaterProvider = minifiedWidgetUpdaterProvider;
  }

  public static MembersInjector<StatsMinifiedWidget> create(
      Provider<MinifiedWidgetUpdater> minifiedWidgetUpdaterProvider) {
    return new StatsMinifiedWidget_MembersInjector(minifiedWidgetUpdaterProvider);
  }

  @Override
  public void injectMembers(StatsMinifiedWidget instance) {
    injectMinifiedWidgetUpdater(instance, minifiedWidgetUpdaterProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.stats.refresh.lists.widget.minified.StatsMinifiedWidget.minifiedWidgetUpdater")
  public static void injectMinifiedWidgetUpdater(StatsMinifiedWidget instance,
      MinifiedWidgetUpdater minifiedWidgetUpdater) {
    instance.minifiedWidgetUpdater = minifiedWidgetUpdater;
  }
}
