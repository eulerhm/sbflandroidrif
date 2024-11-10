package org.wordpress.android.ui.debug.cookies;

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
public final class DebugCookiesFragment_MembersInjector implements MembersInjector<DebugCookiesFragment> {
  private final Provider<ViewModelProvider.Factory> viewModelFactoryProvider;

  public DebugCookiesFragment_MembersInjector(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider) {
    this.viewModelFactoryProvider = viewModelFactoryProvider;
  }

  public static MembersInjector<DebugCookiesFragment> create(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider) {
    return new DebugCookiesFragment_MembersInjector(viewModelFactoryProvider);
  }

  @Override
  public void injectMembers(DebugCookiesFragment instance) {
    injectViewModelFactory(instance, viewModelFactoryProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.debug.cookies.DebugCookiesFragment.viewModelFactory")
  public static void injectViewModelFactory(DebugCookiesFragment instance,
      ViewModelProvider.Factory viewModelFactory) {
    instance.viewModelFactory = viewModelFactory;
  }
}
