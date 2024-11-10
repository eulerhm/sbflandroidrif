package org.wordpress.android.ui.prefs.timezone;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
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
public final class SiteSettingsTimezoneViewModel_Factory implements Factory<SiteSettingsTimezoneViewModel> {
  private final Provider<ResourceProvider> resourceProvider;

  public SiteSettingsTimezoneViewModel_Factory(Provider<ResourceProvider> resourceProvider) {
    this.resourceProvider = resourceProvider;
  }

  @Override
  public SiteSettingsTimezoneViewModel get() {
    return newInstance(resourceProvider.get());
  }

  public static SiteSettingsTimezoneViewModel_Factory create(
      Provider<ResourceProvider> resourceProvider) {
    return new SiteSettingsTimezoneViewModel_Factory(resourceProvider);
  }

  public static SiteSettingsTimezoneViewModel newInstance(ResourceProvider resourceProvider) {
    return new SiteSettingsTimezoneViewModel(resourceProvider);
  }
}
