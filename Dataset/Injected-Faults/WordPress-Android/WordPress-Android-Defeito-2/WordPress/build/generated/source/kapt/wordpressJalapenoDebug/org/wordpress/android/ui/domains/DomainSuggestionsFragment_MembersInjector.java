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
public final class DomainSuggestionsFragment_MembersInjector implements MembersInjector<DomainSuggestionsFragment> {
  private final Provider<ViewModelProvider.Factory> viewModelFactoryProvider;

  public DomainSuggestionsFragment_MembersInjector(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider) {
    this.viewModelFactoryProvider = viewModelFactoryProvider;
  }

  public static MembersInjector<DomainSuggestionsFragment> create(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider) {
    return new DomainSuggestionsFragment_MembersInjector(viewModelFactoryProvider);
  }

  @Override
  public void injectMembers(DomainSuggestionsFragment instance) {
    injectViewModelFactory(instance, viewModelFactoryProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.domains.DomainSuggestionsFragment.viewModelFactory")
  public static void injectViewModelFactory(DomainSuggestionsFragment instance,
      ViewModelProvider.Factory viewModelFactory) {
    instance.viewModelFactory = viewModelFactory;
  }
}
