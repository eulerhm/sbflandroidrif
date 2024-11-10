package org.wordpress.android.ui.prefs.language;

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
public final class LocalePickerBottomSheet_MembersInjector implements MembersInjector<LocalePickerBottomSheet> {
  private final Provider<ViewModelProvider.Factory> viewModelFactoryProvider;

  public LocalePickerBottomSheet_MembersInjector(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider) {
    this.viewModelFactoryProvider = viewModelFactoryProvider;
  }

  public static MembersInjector<LocalePickerBottomSheet> create(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider) {
    return new LocalePickerBottomSheet_MembersInjector(viewModelFactoryProvider);
  }

  @Override
  public void injectMembers(LocalePickerBottomSheet instance) {
    injectViewModelFactory(instance, viewModelFactoryProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.prefs.language.LocalePickerBottomSheet.viewModelFactory")
  public static void injectViewModelFactory(LocalePickerBottomSheet instance,
      ViewModelProvider.Factory viewModelFactory) {
    instance.viewModelFactory = viewModelFactory;
  }
}
