package org.wordpress.android.ui.plans;

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
public final class PlansListFragment_MembersInjector implements MembersInjector<PlansListFragment> {
  private final Provider<ViewModelProvider.Factory> viewModelFactoryProvider;

  public PlansListFragment_MembersInjector(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider) {
    this.viewModelFactoryProvider = viewModelFactoryProvider;
  }

  public static MembersInjector<PlansListFragment> create(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider) {
    return new PlansListFragment_MembersInjector(viewModelFactoryProvider);
  }

  @Override
  public void injectMembers(PlansListFragment instance) {
    injectViewModelFactory(instance, viewModelFactoryProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.plans.PlansListFragment.viewModelFactory")
  public static void injectViewModelFactory(PlansListFragment instance,
      ViewModelProvider.Factory viewModelFactory) {
    instance.viewModelFactory = viewModelFactory;
  }
}
