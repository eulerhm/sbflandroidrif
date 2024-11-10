package org.wordpress.android.ui.accounts.login.jetpack;

import androidx.lifecycle.ViewModelProvider;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.main.utils.MeGravatarLoader;
import org.wordpress.android.ui.utils.UiHelpers;

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
public final class LoginNoSitesFragment_MembersInjector implements MembersInjector<LoginNoSitesFragment> {
  private final Provider<ViewModelProvider.Factory> viewModelFactoryProvider;

  private final Provider<MeGravatarLoader> meGravatarLoaderProvider;

  private final Provider<UiHelpers> uiHelpersProvider;

  public LoginNoSitesFragment_MembersInjector(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<MeGravatarLoader> meGravatarLoaderProvider, Provider<UiHelpers> uiHelpersProvider) {
    this.viewModelFactoryProvider = viewModelFactoryProvider;
    this.meGravatarLoaderProvider = meGravatarLoaderProvider;
    this.uiHelpersProvider = uiHelpersProvider;
  }

  public static MembersInjector<LoginNoSitesFragment> create(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<MeGravatarLoader> meGravatarLoaderProvider, Provider<UiHelpers> uiHelpersProvider) {
    return new LoginNoSitesFragment_MembersInjector(viewModelFactoryProvider, meGravatarLoaderProvider, uiHelpersProvider);
  }

  @Override
  public void injectMembers(LoginNoSitesFragment instance) {
    injectViewModelFactory(instance, viewModelFactoryProvider.get());
    injectMeGravatarLoader(instance, meGravatarLoaderProvider.get());
    injectUiHelpers(instance, uiHelpersProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.accounts.login.jetpack.LoginNoSitesFragment.viewModelFactory")
  public static void injectViewModelFactory(LoginNoSitesFragment instance,
      ViewModelProvider.Factory viewModelFactory) {
    instance.viewModelFactory = viewModelFactory;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.accounts.login.jetpack.LoginNoSitesFragment.meGravatarLoader")
  public static void injectMeGravatarLoader(LoginNoSitesFragment instance,
      MeGravatarLoader meGravatarLoader) {
    instance.meGravatarLoader = meGravatarLoader;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.accounts.login.jetpack.LoginNoSitesFragment.uiHelpers")
  public static void injectUiHelpers(LoginNoSitesFragment instance, UiHelpers uiHelpers) {
    instance.uiHelpers = uiHelpers;
  }
}
