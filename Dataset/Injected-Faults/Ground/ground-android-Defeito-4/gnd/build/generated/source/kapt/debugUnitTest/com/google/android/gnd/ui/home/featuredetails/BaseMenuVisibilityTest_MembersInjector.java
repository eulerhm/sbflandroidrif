package com.google.android.gnd.ui.home.featuredetails;

import com.google.android.gnd.system.auth.FakeAuthenticationManager;
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
public final class BaseMenuVisibilityTest_MembersInjector implements MembersInjector<BaseMenuVisibilityTest> {
  private final Provider<FakeAuthenticationManager> fakeAuthenticationManagerProvider;

  private final Provider<FeatureDetailsViewModel> viewModelProvider;

  public BaseMenuVisibilityTest_MembersInjector(
      Provider<FakeAuthenticationManager> fakeAuthenticationManagerProvider,
      Provider<FeatureDetailsViewModel> viewModelProvider) {
    this.fakeAuthenticationManagerProvider = fakeAuthenticationManagerProvider;
    this.viewModelProvider = viewModelProvider;
  }

  public static MembersInjector<BaseMenuVisibilityTest> create(
      Provider<FakeAuthenticationManager> fakeAuthenticationManagerProvider,
      Provider<FeatureDetailsViewModel> viewModelProvider) {
    return new BaseMenuVisibilityTest_MembersInjector(fakeAuthenticationManagerProvider, viewModelProvider);
  }

  @Override
  public void injectMembers(BaseMenuVisibilityTest instance) {
    injectFakeAuthenticationManager(instance, fakeAuthenticationManagerProvider.get());
    injectViewModel(instance, viewModelProvider.get());
  }

  @InjectedFieldSignature("com.google.android.gnd.ui.home.featuredetails.BaseMenuVisibilityTest.fakeAuthenticationManager")
  public static void injectFakeAuthenticationManager(BaseMenuVisibilityTest instance,
      FakeAuthenticationManager fakeAuthenticationManager) {
    instance.fakeAuthenticationManager = fakeAuthenticationManager;
  }

  @InjectedFieldSignature("com.google.android.gnd.ui.home.featuredetails.BaseMenuVisibilityTest.viewModel")
  public static void injectViewModel(BaseMenuVisibilityTest instance,
      FeatureDetailsViewModel viewModel) {
    instance.viewModel = viewModel;
  }
}
