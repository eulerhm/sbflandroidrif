package org.wordpress.android.ui.jetpack.scan.history;

import androidx.lifecycle.ViewModelProvider;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.utils.UiHelpers;
import org.wordpress.android.util.LocaleManagerWrapper;

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
public final class ScanHistoryFragment_MembersInjector implements MembersInjector<ScanHistoryFragment> {
  private final Provider<ViewModelProvider.Factory> viewModelFactoryProvider;

  private final Provider<UiHelpers> uiHelpersProvider;

  private final Provider<LocaleManagerWrapper> localeManagerWrapperProvider;

  public ScanHistoryFragment_MembersInjector(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<UiHelpers> uiHelpersProvider,
      Provider<LocaleManagerWrapper> localeManagerWrapperProvider) {
    this.viewModelFactoryProvider = viewModelFactoryProvider;
    this.uiHelpersProvider = uiHelpersProvider;
    this.localeManagerWrapperProvider = localeManagerWrapperProvider;
  }

  public static MembersInjector<ScanHistoryFragment> create(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<UiHelpers> uiHelpersProvider,
      Provider<LocaleManagerWrapper> localeManagerWrapperProvider) {
    return new ScanHistoryFragment_MembersInjector(viewModelFactoryProvider, uiHelpersProvider, localeManagerWrapperProvider);
  }

  @Override
  public void injectMembers(ScanHistoryFragment instance) {
    injectViewModelFactory(instance, viewModelFactoryProvider.get());
    injectUiHelpers(instance, uiHelpersProvider.get());
    injectLocaleManagerWrapper(instance, localeManagerWrapperProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.jetpack.scan.history.ScanHistoryFragment.viewModelFactory")
  public static void injectViewModelFactory(ScanHistoryFragment instance,
      ViewModelProvider.Factory viewModelFactory) {
    instance.viewModelFactory = viewModelFactory;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.jetpack.scan.history.ScanHistoryFragment.uiHelpers")
  public static void injectUiHelpers(ScanHistoryFragment instance, UiHelpers uiHelpers) {
    instance.uiHelpers = uiHelpers;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.jetpack.scan.history.ScanHistoryFragment.localeManagerWrapper")
  public static void injectLocaleManagerWrapper(ScanHistoryFragment instance,
      LocaleManagerWrapper localeManagerWrapper) {
    instance.localeManagerWrapper = localeManagerWrapper;
  }
}
