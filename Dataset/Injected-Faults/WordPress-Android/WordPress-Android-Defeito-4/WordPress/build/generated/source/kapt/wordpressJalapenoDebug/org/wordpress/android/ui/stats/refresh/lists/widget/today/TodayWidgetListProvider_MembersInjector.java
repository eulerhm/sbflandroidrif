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
public final class TodayWidgetListProvider_MembersInjector implements MembersInjector<TodayWidgetListProvider> {
  private final Provider<TodayWidgetListViewModel> viewModelProvider;

  private final Provider<TodayWidgetUpdater> widgetUpdaterProvider;

  public TodayWidgetListProvider_MembersInjector(
      Provider<TodayWidgetListViewModel> viewModelProvider,
      Provider<TodayWidgetUpdater> widgetUpdaterProvider) {
    this.viewModelProvider = viewModelProvider;
    this.widgetUpdaterProvider = widgetUpdaterProvider;
  }

  public static MembersInjector<TodayWidgetListProvider> create(
      Provider<TodayWidgetListViewModel> viewModelProvider,
      Provider<TodayWidgetUpdater> widgetUpdaterProvider) {
    return new TodayWidgetListProvider_MembersInjector(viewModelProvider, widgetUpdaterProvider);
  }

  @Override
  public void injectMembers(TodayWidgetListProvider instance) {
    injectViewModel(instance, viewModelProvider.get());
    injectWidgetUpdater(instance, widgetUpdaterProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.stats.refresh.lists.widget.today.TodayWidgetListProvider.viewModel")
  public static void injectViewModel(TodayWidgetListProvider instance,
      TodayWidgetListViewModel viewModel) {
    instance.viewModel = viewModel;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.stats.refresh.lists.widget.today.TodayWidgetListProvider.widgetUpdater")
  public static void injectWidgetUpdater(TodayWidgetListProvider instance,
      TodayWidgetUpdater widgetUpdater) {
    instance.widgetUpdater = widgetUpdater;
  }
}
