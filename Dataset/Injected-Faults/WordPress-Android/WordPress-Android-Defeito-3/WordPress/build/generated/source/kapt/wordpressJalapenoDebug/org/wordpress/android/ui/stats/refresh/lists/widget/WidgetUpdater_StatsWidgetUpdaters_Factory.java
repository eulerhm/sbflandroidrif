package org.wordpress.android.ui.stats.refresh.lists.widget;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.prefs.AppPrefsWrapper;
import org.wordpress.android.ui.stats.refresh.lists.widget.alltime.AllTimeWidgetUpdater;
import org.wordpress.android.ui.stats.refresh.lists.widget.minified.MinifiedWidgetUpdater;
import org.wordpress.android.ui.stats.refresh.lists.widget.today.TodayWidgetUpdater;
import org.wordpress.android.ui.stats.refresh.lists.widget.views.ViewsWidgetUpdater;

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
public final class WidgetUpdater_StatsWidgetUpdaters_Factory implements Factory<WidgetUpdater.StatsWidgetUpdaters> {
  private final Provider<ViewsWidgetUpdater> viewsWidgetUpdaterProvider;

  private final Provider<AllTimeWidgetUpdater> allTimeWidgetUpdaterProvider;

  private final Provider<TodayWidgetUpdater> todayWidgetUpdaterProvider;

  private final Provider<MinifiedWidgetUpdater> minifiedWidgetUpdaterProvider;

  private final Provider<AppPrefsWrapper> appPrefsWrapperProvider;

  private final Provider<Context> contextProvider;

  public WidgetUpdater_StatsWidgetUpdaters_Factory(
      Provider<ViewsWidgetUpdater> viewsWidgetUpdaterProvider,
      Provider<AllTimeWidgetUpdater> allTimeWidgetUpdaterProvider,
      Provider<TodayWidgetUpdater> todayWidgetUpdaterProvider,
      Provider<MinifiedWidgetUpdater> minifiedWidgetUpdaterProvider,
      Provider<AppPrefsWrapper> appPrefsWrapperProvider, Provider<Context> contextProvider) {
    this.viewsWidgetUpdaterProvider = viewsWidgetUpdaterProvider;
    this.allTimeWidgetUpdaterProvider = allTimeWidgetUpdaterProvider;
    this.todayWidgetUpdaterProvider = todayWidgetUpdaterProvider;
    this.minifiedWidgetUpdaterProvider = minifiedWidgetUpdaterProvider;
    this.appPrefsWrapperProvider = appPrefsWrapperProvider;
    this.contextProvider = contextProvider;
  }

  @Override
  public WidgetUpdater.StatsWidgetUpdaters get() {
    return newInstance(viewsWidgetUpdaterProvider.get(), allTimeWidgetUpdaterProvider.get(), todayWidgetUpdaterProvider.get(), minifiedWidgetUpdaterProvider.get(), appPrefsWrapperProvider.get(), contextProvider.get());
  }

  public static WidgetUpdater_StatsWidgetUpdaters_Factory create(
      Provider<ViewsWidgetUpdater> viewsWidgetUpdaterProvider,
      Provider<AllTimeWidgetUpdater> allTimeWidgetUpdaterProvider,
      Provider<TodayWidgetUpdater> todayWidgetUpdaterProvider,
      Provider<MinifiedWidgetUpdater> minifiedWidgetUpdaterProvider,
      Provider<AppPrefsWrapper> appPrefsWrapperProvider, Provider<Context> contextProvider) {
    return new WidgetUpdater_StatsWidgetUpdaters_Factory(viewsWidgetUpdaterProvider, allTimeWidgetUpdaterProvider, todayWidgetUpdaterProvider, minifiedWidgetUpdaterProvider, appPrefsWrapperProvider, contextProvider);
  }

  public static WidgetUpdater.StatsWidgetUpdaters newInstance(ViewsWidgetUpdater viewsWidgetUpdater,
      AllTimeWidgetUpdater allTimeWidgetUpdater, TodayWidgetUpdater todayWidgetUpdater,
      MinifiedWidgetUpdater minifiedWidgetUpdater, AppPrefsWrapper appPrefsWrapper,
      Context context) {
    return new WidgetUpdater.StatsWidgetUpdaters(viewsWidgetUpdater, allTimeWidgetUpdater, todayWidgetUpdater, minifiedWidgetUpdater, appPrefsWrapper, context);
  }
}
