package com.google.android.gnd.ui.home.mapcontainer;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import javax.annotation.Generated;
import javax.inject.Provider;

@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class PolygonDrawingViewModelTest_MembersInjector implements MembersInjector<PolygonDrawingViewModelTest> {
  private final Provider<PolygonDrawingViewModel> viewModelProvider;

  public PolygonDrawingViewModelTest_MembersInjector(
      Provider<PolygonDrawingViewModel> viewModelProvider) {
    this.viewModelProvider = viewModelProvider;
  }

  public static MembersInjector<PolygonDrawingViewModelTest> create(
      Provider<PolygonDrawingViewModel> viewModelProvider) {
    return new PolygonDrawingViewModelTest_MembersInjector(viewModelProvider);
  }

  @Override
  public void injectMembers(PolygonDrawingViewModelTest instance) {
    injectViewModel(instance, viewModelProvider.get());
  }

  @InjectedFieldSignature("com.google.android.gnd.ui.home.mapcontainer.PolygonDrawingViewModelTest.viewModel")
  public static void injectViewModel(PolygonDrawingViewModelTest instance,
      PolygonDrawingViewModel viewModel) {
    instance.viewModel = viewModel;
  }
}
