package org.wordpress.android.ui.prefs.homepage;

import androidx.lifecycle.ViewModelProvider;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
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
public final class HomepageSettingsDialog_MembersInjector implements MembersInjector<HomepageSettingsDialog> {
  private final Provider<ViewModelProvider.Factory> viewModelFactoryProvider;

  private final Provider<UiHelpers> uiHelpersProvider;

  public HomepageSettingsDialog_MembersInjector(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<UiHelpers> uiHelpersProvider) {
    this.viewModelFactoryProvider = viewModelFactoryProvider;
    this.uiHelpersProvider = uiHelpersProvider;
  }

  public static MembersInjector<HomepageSettingsDialog> create(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<UiHelpers> uiHelpersProvider) {
    return new HomepageSettingsDialog_MembersInjector(viewModelFactoryProvider, uiHelpersProvider);
  }

  @Override
  public void injectMembers(HomepageSettingsDialog instance) {
    injectViewModelFactory(instance, viewModelFactoryProvider.get());
    injectUiHelpers(instance, uiHelpersProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.prefs.homepage.HomepageSettingsDialog.viewModelFactory")
  public static void injectViewModelFactory(HomepageSettingsDialog instance,
      ViewModelProvider.Factory viewModelFactory) {
    instance.viewModelFactory = viewModelFactory;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.prefs.homepage.HomepageSettingsDialog.uiHelpers")
  public static void injectUiHelpers(HomepageSettingsDialog instance, UiHelpers uiHelpers) {
    instance.uiHelpers = uiHelpers;
  }
}
