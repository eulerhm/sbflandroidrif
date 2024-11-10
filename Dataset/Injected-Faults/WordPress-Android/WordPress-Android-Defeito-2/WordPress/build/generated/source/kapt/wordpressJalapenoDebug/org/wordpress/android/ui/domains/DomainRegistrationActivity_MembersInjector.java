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
public final class DomainRegistrationActivity_MembersInjector implements MembersInjector<DomainRegistrationActivity> {
  private final Provider<ViewModelProvider.Factory> viewModelFactoryProvider;

  public DomainRegistrationActivity_MembersInjector(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider) {
    this.viewModelFactoryProvider = viewModelFactoryProvider;
  }

  public static MembersInjector<DomainRegistrationActivity> create(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider) {
    return new DomainRegistrationActivity_MembersInjector(viewModelFactoryProvider);
  }

  @Override
  public void injectMembers(DomainRegistrationActivity instance) {
    injectViewModelFactory(instance, viewModelFactoryProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.domains.DomainRegistrationActivity.viewModelFactory")
  public static void injectViewModelFactory(DomainRegistrationActivity instance,
      ViewModelProvider.Factory viewModelFactory) {
    instance.viewModelFactory = viewModelFactory;
  }
}
