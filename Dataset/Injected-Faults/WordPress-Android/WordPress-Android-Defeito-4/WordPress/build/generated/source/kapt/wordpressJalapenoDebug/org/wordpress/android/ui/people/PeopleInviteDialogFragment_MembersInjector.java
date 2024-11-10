package org.wordpress.android.ui.people;

import androidx.lifecycle.ViewModelProvider;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.viewmodel.ContextProvider;

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
public final class PeopleInviteDialogFragment_MembersInjector implements MembersInjector<PeopleInviteDialogFragment> {
  private final Provider<ContextProvider> contextProvider;

  private final Provider<ViewModelProvider.Factory> viewModelFactoryProvider;

  public PeopleInviteDialogFragment_MembersInjector(Provider<ContextProvider> contextProvider,
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider) {
    this.contextProvider = contextProvider;
    this.viewModelFactoryProvider = viewModelFactoryProvider;
  }

  public static MembersInjector<PeopleInviteDialogFragment> create(
      Provider<ContextProvider> contextProvider,
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider) {
    return new PeopleInviteDialogFragment_MembersInjector(contextProvider, viewModelFactoryProvider);
  }

  @Override
  public void injectMembers(PeopleInviteDialogFragment instance) {
    injectContextProvider(instance, contextProvider.get());
    injectViewModelFactory(instance, viewModelFactoryProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.people.PeopleInviteDialogFragment.contextProvider")
  public static void injectContextProvider(PeopleInviteDialogFragment instance,
      ContextProvider contextProvider) {
    instance.contextProvider = contextProvider;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.people.PeopleInviteDialogFragment.viewModelFactory")
  public static void injectViewModelFactory(PeopleInviteDialogFragment instance,
      ViewModelProvider.Factory viewModelFactory) {
    instance.viewModelFactory = viewModelFactory;
  }
}
