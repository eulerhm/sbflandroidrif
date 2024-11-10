package org.wordpress.android.ui.prefs.language;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.util.LocaleProvider;
import org.wordpress.android.viewmodel.ResourceProvider;

@ScopeMetadata
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
public final class LocalePickerViewModel_Factory implements Factory<LocalePickerViewModel> {
  private final Provider<ResourceProvider> resourceProvider;

  private final Provider<LocaleProvider> localeProvider;

  public LocalePickerViewModel_Factory(Provider<ResourceProvider> resourceProvider,
      Provider<LocaleProvider> localeProvider) {
    this.resourceProvider = resourceProvider;
    this.localeProvider = localeProvider;
  }

  @Override
  public LocalePickerViewModel get() {
    return newInstance(resourceProvider.get(), localeProvider.get());
  }

  public static LocalePickerViewModel_Factory create(Provider<ResourceProvider> resourceProvider,
      Provider<LocaleProvider> localeProvider) {
    return new LocalePickerViewModel_Factory(resourceProvider, localeProvider);
  }

  public static LocalePickerViewModel newInstance(ResourceProvider resourceProvider,
      LocaleProvider localeProvider) {
    return new LocalePickerViewModel(resourceProvider, localeProvider);
  }
}
