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
public final class ViewsWidgetListProvider_MembersInjector implements MembersInjector<ViewsWidgetListProvider> {
  private final Provider<ViewsWidgetListViewModel> viewModelProvider;

  private final Provider<ViewsWidgetUpdater> viewsWidgetUpdaterProvider;

  public ViewsWidgetListProvider_MembersInjector(
      Provider<ViewsWidgetListViewModel> viewModelProvider,
      Provider<ViewsWidgetUpdater> viewsWidgetUpdaterProvider) {
    this.viewModelProvider = viewModelProvider;
    this.viewsWidgetUpdaterProvider = viewsWidgetUpdaterProvider;
  }

  public static MembersInjector<ViewsWidgetListProvider> create(
      Provider<ViewsWidgetListViewModel> viewModelProvider,
      Provider<ViewsWidgetUpdater> viewsWidgetUpdaterProvider) {
    return new ViewsWidgetListProvider_MembersInjector(viewModelProvider, viewsWidgetUpdaterProvider);
  }

  @Override
  public void injectMembers(ViewsWidgetListProvider instance) {
    injectViewModel(instance, viewModelProvider.get());
    injectViewsWidgetUpdater(instance, viewsWidgetUpdaterProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.stats.refresh.lists.widget.views.ViewsWidgetListProvider.viewModel")
  public static void injectViewModel(ViewsWidgetListProvider instance,
      ViewsWidgetListViewModel viewModel) {
    instance.viewModel = viewModel;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.stats.refresh.lists.widget.views.ViewsWidgetListProvider.viewsWidgetUpdater")
  public static void injectViewsWidgetUpdater(ViewsWidgetListProvider instance,
      ViewsWidgetUpdater viewsWidgetUpdater) {
    instance.viewsWidgetUpdater = viewsWidgetUpdater;
  }
}
