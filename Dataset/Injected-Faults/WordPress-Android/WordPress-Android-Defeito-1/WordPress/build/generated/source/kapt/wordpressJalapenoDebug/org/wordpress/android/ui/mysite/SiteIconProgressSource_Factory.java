package org.wordpress.android.ui.mysite;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;

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
public final class SiteIconProgressSource_Factory implements Factory<SiteIconProgressSource> {
  private final Provider<SelectedSiteRepository> selectedSiteRepositoryProvider;

  public SiteIconProgressSource_Factory(
      Provider<SelectedSiteRepository> selectedSiteRepositoryProvider) {
    this.selectedSiteRepositoryProvider = selectedSiteRepositoryProvider;
  }

  @Override
  public SiteIconProgressSource get() {
    return newInstance(selectedSiteRepositoryProvider.get());
  }

  public static SiteIconProgressSource_Factory create(
      Provider<SelectedSiteRepository> selectedSiteRepositoryProvider) {
    return new SiteIconProgressSource_Factory(selectedSiteRepositoryProvider);
  }

  public static SiteIconProgressSource newInstance(SelectedSiteRepository selectedSiteRepository) {
    return new SiteIconProgressSource(selectedSiteRepository);
  }
}
