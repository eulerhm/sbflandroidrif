package org.wordpress.android.ui.stats.refresh.lists.widget.alltime;

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
public final class StatsAllTimeWidget_MembersInjector implements MembersInjector<StatsAllTimeWidget> {
  private final Provider<AllTimeWidgetUpdater> allTimeWidgetUpdaterProvider;

  public StatsAllTimeWidget_MembersInjector(
      Provider<AllTimeWidgetUpdater> allTimeWidgetUpdaterProvider) {
    this.allTimeWidgetUpdaterProvider = allTimeWidgetUpdaterProvider;
  }

  public static MembersInjector<StatsAllTimeWidget> create(
      Provider<AllTimeWidgetUpdater> allTimeWidgetUpdaterProvider) {
    return new StatsAllTimeWidget_MembersInjector(allTimeWidgetUpdaterProvider);
  }

  @Override
  public void injectMembers(StatsAllTimeWidget instance) {
    injectAllTimeWidgetUpdater(instance, allTimeWidgetUpdaterProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.stats.refresh.lists.widget.alltime.StatsAllTimeWidget.allTimeWidgetUpdater")
  public static void injectAllTimeWidgetUpdater(StatsAllTimeWidget instance,
      AllTimeWidgetUpdater allTimeWidgetUpdater) {
    instance.allTimeWidgetUpdater = allTimeWidgetUpdater;
  }
}
