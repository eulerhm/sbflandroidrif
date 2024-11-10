package org.wordpress.android.ui.domains;

import androidx.lifecycle.ViewModelProvider;
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
public final class DomainRegistrationResultFragment_MembersInjector implements MembersInjector<DomainRegistrationResultFragment> {
  private final Provider<ViewModelProvider.Factory> viewModelFactoryProvider;

  public DomainRegistrationResultFragment_MembersInjector(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider) {
    this.viewModelFactoryProvider = viewModelFactoryProvider;
  }

  public static MembersInjector<DomainRegistrationResultFragment> create(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider) {
    return new DomainRegistrationResultFragment_MembersInjector(viewModelFactoryProvider);
  }

  @Override
  public void injectMembers(DomainRegistrationResultFragment instance) {
    injectViewModelFactory(instance, viewModelFactoryProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.domains.DomainRegistrationResultFragment.viewModelFactory")
  public static void injectViewModelFactory(DomainRegistrationResultFragment instance,
      ViewModelProvider.Factory viewModelFactory) {
    instance.viewModelFactory = viewModelFactory;
  }
}
