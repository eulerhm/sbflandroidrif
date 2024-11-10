package org.wordpress.android.ui.stats.refresh.lists.widget.today;

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
public final class StatsTodayWidget_MembersInjector implements MembersInjector<StatsTodayWidget> {
  private final Provider<TodayWidgetUpdater> todayWidgetUpdaterProvider;

  public StatsTodayWidget_MembersInjector(Provider<TodayWidgetUpdater> todayWidgetUpdaterProvider) {
    this.todayWidgetUpdaterProvider = todayWidgetUpdaterProvider;
  }

  public static MembersInjector<StatsTodayWidget> create(
      Provider<TodayWidgetUpdater> todayWidgetUpdaterProvider) {
    return new StatsTodayWidget_MembersInjector(todayWidgetUpdaterProvider);
  }

  @Override
  public void injectMembers(StatsTodayWidget instance) {
    injectTodayWidgetUpdater(instance, todayWidgetUpdaterProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.stats.refresh.lists.widget.today.StatsTodayWidget.todayWidgetUpdater")
  public static void injectTodayWidgetUpdater(StatsTodayWidget instance,
      TodayWidgetUpdater todayWidgetUpdater) {
    instance.todayWidgetUpdater = todayWidgetUpdater;
  }
}
