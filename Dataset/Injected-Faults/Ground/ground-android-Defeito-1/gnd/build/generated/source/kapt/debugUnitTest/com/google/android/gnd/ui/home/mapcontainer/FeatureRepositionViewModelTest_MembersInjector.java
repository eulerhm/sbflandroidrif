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
public final class FeatureRepositionViewModelTest_MembersInjector implements MembersInjector<FeatureRepositionViewModelTest> {
  private final Provider<FeatureRepositionViewModel> viewModelProvider;

  public FeatureRepositionViewModelTest_MembersInjector(
      Provider<FeatureRepositionViewModel> viewModelProvider) {
    this.viewModelProvider = viewModelProvider;
  }

  public static MembersInjector<FeatureRepositionViewModelTest> create(
      Provider<FeatureRepositionViewModel> viewModelProvider) {
    return new FeatureRepositionViewModelTest_MembersInjector(viewModelProvider);
  }

  @Override
  public void injectMembers(FeatureRepositionViewModelTest instance) {
    injectViewModel(instance, viewModelProvider.get());
  }

  @InjectedFieldSignature("com.google.android.gnd.ui.home.mapcontainer.FeatureRepositionViewModelTest.viewModel")
  public static void injectViewModel(FeatureRepositionViewModelTest instance,
      FeatureRepositionViewModel viewModel) {
    instance.viewModel = viewModel;
  }
}
