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
public final class DomainRegistrationDetailsFragment_MembersInjector implements MembersInjector<DomainRegistrationDetailsFragment> {
  private final Provider<ViewModelProvider.Factory> viewModelFactoryProvider;

  public DomainRegistrationDetailsFragment_MembersInjector(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider) {
    this.viewModelFactoryProvider = viewModelFactoryProvider;
  }

  public static MembersInjector<DomainRegistrationDetailsFragment> create(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider) {
    return new DomainRegistrationDetailsFragment_MembersInjector(viewModelFactoryProvider);
  }

  @Override
  public void injectMembers(DomainRegistrationDetailsFragment instance) {
    injectViewModelFactory(instance, viewModelFactoryProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.domains.DomainRegistrationDetailsFragment.viewModelFactory")
  public static void injectViewModelFactory(DomainRegistrationDetailsFragment instance,
      ViewModelProvider.Factory viewModelFactory) {
    instance.viewModelFactory = viewModelFactory;
  }
}
