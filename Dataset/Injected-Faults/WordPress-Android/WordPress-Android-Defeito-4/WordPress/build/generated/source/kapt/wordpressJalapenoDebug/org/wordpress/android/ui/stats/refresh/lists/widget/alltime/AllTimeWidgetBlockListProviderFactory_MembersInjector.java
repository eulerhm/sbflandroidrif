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
public final class AllTimeWidgetBlockListProviderFactory_MembersInjector implements MembersInjector<AllTimeWidgetBlockListProviderFactory> {
  private final Provider<AllTimeWidgetBlockListViewModel> viewModelProvider;

  public AllTimeWidgetBlockListProviderFactory_MembersInjector(
      Provider<AllTimeWidgetBlockListViewModel> viewModelProvider) {
    this.viewModelProvider = viewModelProvider;
  }

  public static MembersInjector<AllTimeWidgetBlockListProviderFactory> create(
      Provider<AllTimeWidgetBlockListViewModel> viewModelProvider) {
    return new AllTimeWidgetBlockListProviderFactory_MembersInjector(viewModelProvider);
  }

  @Override
  public void injectMembers(AllTimeWidgetBlockListProviderFactory instance) {
    injectViewModel(instance, viewModelProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.stats.refresh.lists.widget.alltime.AllTimeWidgetBlockListProviderFactory.viewModel")
  public static void injectViewModel(AllTimeWidgetBlockListProviderFactory instance,
      AllTimeWidgetBlockListViewModel viewModel) {
    instance.viewModel = viewModel;
  }
}
