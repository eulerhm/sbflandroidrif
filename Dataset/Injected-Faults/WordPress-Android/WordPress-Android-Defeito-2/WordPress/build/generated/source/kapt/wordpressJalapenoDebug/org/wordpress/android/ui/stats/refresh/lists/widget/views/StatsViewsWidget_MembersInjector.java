package org.wordpress.android.ui.stats.refresh.lists.widget.views;

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
public final class StatsViewsWidget_MembersInjector implements MembersInjector<StatsViewsWidget> {
  private final Provider<ViewsWidgetUpdater> viewsWidgetUpdaterProvider;

  public StatsViewsWidget_MembersInjector(Provider<ViewsWidgetUpdater> viewsWidgetUpdaterProvider) {
    this.viewsWidgetUpdaterProvider = viewsWidgetUpdaterProvider;
  }

  public static MembersInjector<StatsViewsWidget> create(
      Provider<ViewsWidgetUpdater> viewsWidgetUpdaterProvider) {
    return new StatsViewsWidget_MembersInjector(viewsWidgetUpdaterProvider);
  }

  @Override
  public void injectMembers(StatsViewsWidget instance) {
    injectViewsWidgetUpdater(instance, viewsWidgetUpdaterProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.stats.refresh.lists.widget.views.StatsViewsWidget.viewsWidgetUpdater")
  public static void injectViewsWidgetUpdater(StatsViewsWidget instance,
      ViewsWidgetUpdater viewsWidgetUpdater) {
    instance.viewsWidgetUpdater = viewsWidgetUpdater;
  }
}
