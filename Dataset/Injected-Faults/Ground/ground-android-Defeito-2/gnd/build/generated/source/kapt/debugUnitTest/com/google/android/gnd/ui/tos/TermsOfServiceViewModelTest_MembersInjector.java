package com.google.android.gnd.ui.tos;

import com.google.android.gnd.repository.TermsOfServiceRepository;
import com.google.android.gnd.ui.common.Navigator;
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
public final class TermsOfServiceViewModelTest_MembersInjector implements MembersInjector<TermsOfServiceViewModelTest> {
  private final Provider<Navigator> navigatorProvider;

  private final Provider<TermsOfServiceRepository> termsOfServiceRepositoryProvider;

  private final Provider<TermsOfServiceViewModel> viewModelProvider;

  public TermsOfServiceViewModelTest_MembersInjector(Provider<Navigator> navigatorProvider,
      Provider<TermsOfServiceRepository> termsOfServiceRepositoryProvider,
      Provider<TermsOfServiceViewModel> viewModelProvider) {
    this.navigatorProvider = navigatorProvider;
    this.termsOfServiceRepositoryProvider = termsOfServiceRepositoryProvider;
    this.viewModelProvider = viewModelProvider;
  }

  public static MembersInjector<TermsOfServiceViewModelTest> create(
      Provider<Navigator> navigatorProvider,
      Provider<TermsOfServiceRepository> termsOfServiceRepositoryProvider,
      Provider<TermsOfServiceViewModel> viewModelProvider) {
    return new TermsOfServiceViewModelTest_MembersInjector(navigatorProvider, termsOfServiceRepositoryProvider, viewModelProvider);
  }

  @Override
  public void injectMembers(TermsOfServiceViewModelTest instance) {
    injectNavigator(instance, navigatorProvider.get());
    injectTermsOfServiceRepository(instance, termsOfServiceRepositoryProvider.get());
    injectViewModel(instance, viewModelProvider.get());
  }

  @InjectedFieldSignature("com.google.android.gnd.ui.tos.TermsOfServiceViewModelTest.navigator")
  public static void injectNavigator(TermsOfServiceViewModelTest instance, Navigator navigator) {
    instance.navigator = navigator;
  }

  @InjectedFieldSignature("com.google.android.gnd.ui.tos.TermsOfServiceViewModelTest.termsOfServiceRepository")
  public static void injectTermsOfServiceRepository(TermsOfServiceViewModelTest instance,
      TermsOfServiceRepository termsOfServiceRepository) {
    instance.termsOfServiceRepository = termsOfServiceRepository;
  }

  @InjectedFieldSignature("com.google.android.gnd.ui.tos.TermsOfServiceViewModelTest.viewModel")
  public static void injectViewModel(TermsOfServiceViewModelTest instance,
      TermsOfServiceViewModel viewModel) {
    instance.viewModel = viewModel;
  }
}
