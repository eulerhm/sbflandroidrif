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
public final class AllTimeWidgetListProvider_MembersInjector implements MembersInjector<AllTimeWidgetListProvider> {
  private final Provider<AllTimeWidgetListViewModel> viewModelProvider;

  private final Provider<AllTimeWidgetUpdater> widgetUpdatedProvider;

  public AllTimeWidgetListProvider_MembersInjector(
      Provider<AllTimeWidgetListViewModel> viewModelProvider,
      Provider<AllTimeWidgetUpdater> widgetUpdatedProvider) {
    this.viewModelProvider = viewModelProvider;
    this.widgetUpdatedProvider = widgetUpdatedProvider;
  }

  public static MembersInjector<AllTimeWidgetListProvider> create(
      Provider<AllTimeWidgetListViewModel> viewModelProvider,
      Provider<AllTimeWidgetUpdater> widgetUpdatedProvider) {
    return new AllTimeWidgetListProvider_MembersInjector(viewModelProvider, widgetUpdatedProvider);
  }

  @Override
  public void injectMembers(AllTimeWidgetListProvider instance) {
    injectViewModel(instance, viewModelProvider.get());
    injectWidgetUpdated(instance, widgetUpdatedProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.stats.refresh.lists.widget.alltime.AllTimeWidgetListProvider.viewModel")
  public static void injectViewModel(AllTimeWidgetListProvider instance,
      AllTimeWidgetListViewModel viewModel) {
    instance.viewModel = viewModel;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.stats.refresh.lists.widget.alltime.AllTimeWidgetListProvider.widgetUpdated")
  public static void injectWidgetUpdated(AllTimeWidgetListProvider instance,
      AllTimeWidgetUpdater widgetUpdated) {
    instance.widgetUpdated = widgetUpdated;
  }
}
