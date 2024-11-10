package org.wordpress.android.ui;

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
public final class JetpackRemoteInstallFragment_MembersInjector implements MembersInjector<JetpackRemoteInstallFragment> {
  private final Provider<ViewModelProvider.Factory> viewModelFactoryProvider;

  public JetpackRemoteInstallFragment_MembersInjector(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider) {
    this.viewModelFactoryProvider = viewModelFactoryProvider;
  }

  public static MembersInjector<JetpackRemoteInstallFragment> create(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider) {
    return new JetpackRemoteInstallFragment_MembersInjector(viewModelFactoryProvider);
  }

  @Override
  public void injectMembers(JetpackRemoteInstallFragment instance) {
    injectViewModelFactory(instance, viewModelFactoryProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.JetpackRemoteInstallFragment.viewModelFactory")
  public static void injectViewModelFactory(JetpackRemoteInstallFragment instance,
      ViewModelProvider.Factory viewModelFactory) {
    instance.viewModelFactory = viewModelFactory;
  }
}
