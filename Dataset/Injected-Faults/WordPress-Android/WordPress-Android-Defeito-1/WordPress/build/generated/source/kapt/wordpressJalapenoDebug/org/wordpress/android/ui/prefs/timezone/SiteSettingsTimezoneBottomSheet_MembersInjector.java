package org.wordpress.android.ui.prefs.timezone;

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
public final class SiteSettingsTimezoneBottomSheet_MembersInjector implements MembersInjector<SiteSettingsTimezoneBottomSheet> {
  private final Provider<ViewModelProvider.Factory> viewModelFactoryProvider;

  public SiteSettingsTimezoneBottomSheet_MembersInjector(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider) {
    this.viewModelFactoryProvider = viewModelFactoryProvider;
  }

  public static MembersInjector<SiteSettingsTimezoneBottomSheet> create(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider) {
    return new SiteSettingsTimezoneBottomSheet_MembersInjector(viewModelFactoryProvider);
  }

  @Override
  public void injectMembers(SiteSettingsTimezoneBottomSheet instance) {
    injectViewModelFactory(instance, viewModelFactoryProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.prefs.timezone.SiteSettingsTimezoneBottomSheet.viewModelFactory")
  public static void injectViewModelFactory(SiteSettingsTimezoneBottomSheet instance,
      ViewModelProvider.Factory viewModelFactory) {
    instance.viewModelFactory = viewModelFactory;
  }
}
