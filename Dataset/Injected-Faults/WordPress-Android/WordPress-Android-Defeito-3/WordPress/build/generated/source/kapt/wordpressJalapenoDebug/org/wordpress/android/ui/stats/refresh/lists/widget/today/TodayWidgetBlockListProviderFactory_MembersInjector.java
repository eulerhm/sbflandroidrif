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
public final class TodayWidgetBlockListProviderFactory_MembersInjector implements MembersInjector<TodayWidgetBlockListProviderFactory> {
  private final Provider<TodayWidgetBlockListViewModel> viewModelProvider;

  public TodayWidgetBlockListProviderFactory_MembersInjector(
      Provider<TodayWidgetBlockListViewModel> viewModelProvider) {
    this.viewModelProvider = viewModelProvider;
  }

  public static MembersInjector<TodayWidgetBlockListProviderFactory> create(
      Provider<TodayWidgetBlockListViewModel> viewModelProvider) {
    return new TodayWidgetBlockListProviderFactory_MembersInjector(viewModelProvider);
  }

  @Override
  public void injectMembers(TodayWidgetBlockListProviderFactory instance) {
    injectViewModel(instance, viewModelProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.stats.refresh.lists.widget.today.TodayWidgetBlockListProviderFactory.viewModel")
  public static void injectViewModel(TodayWidgetBlockListProviderFactory instance,
      TodayWidgetBlockListViewModel viewModel) {
    instance.viewModel = viewModel;
  }
}
