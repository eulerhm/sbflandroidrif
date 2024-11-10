package org.wordpress.android.ui.mysite.cards.siteinfo;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.mysite.cards.quickstart.QuickStartRepository;
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
public final class SiteInfoHeaderCardBuilder_Factory implements Factory<SiteInfoHeaderCardBuilder> {
  private final Provider<ResourceProvider> resourceProvider;

  private final Provider<QuickStartRepository> quickStartRepositoryProvider;

  public SiteInfoHeaderCardBuilder_Factory(Provider<ResourceProvider> resourceProvider,
      Provider<QuickStartRepository> quickStartRepositoryProvider) {
    this.resourceProvider = resourceProvider;
    this.quickStartRepositoryProvider = quickStartRepositoryProvider;
  }

  @Override
  public SiteInfoHeaderCardBuilder get() {
    return newInstance(resourceProvider.get(), quickStartRepositoryProvider.get());
  }

  public static SiteInfoHeaderCardBuilder_Factory create(
      Provider<ResourceProvider> resourceProvider,
      Provider<QuickStartRepository> quickStartRepositoryProvider) {
    return new SiteInfoHeaderCardBuilder_Factory(resourceProvider, quickStartRepositoryProvider);
  }

  public static SiteInfoHeaderCardBuilder newInstance(ResourceProvider resourceProvider,
      QuickStartRepository quickStartRepository) {
    return new SiteInfoHeaderCardBuilder(resourceProvider, quickStartRepository);
  }
}
