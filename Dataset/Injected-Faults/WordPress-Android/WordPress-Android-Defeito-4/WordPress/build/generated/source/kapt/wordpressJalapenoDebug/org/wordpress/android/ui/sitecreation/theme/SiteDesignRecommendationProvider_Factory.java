package org.wordpress.android.ui.sitecreation.theme;

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
public final class SiteDesignRecommendationProvider_Factory implements Factory<SiteDesignRecommendationProvider> {
  private final Provider<ResourceProvider> resourceProvider;

  public SiteDesignRecommendationProvider_Factory(Provider<ResourceProvider> resourceProvider) {
    this.resourceProvider = resourceProvider;
  }

  @Override
  public SiteDesignRecommendationProvider get() {
    return newInstance(resourceProvider.get());
  }

  public static SiteDesignRecommendationProvider_Factory create(
      Provider<ResourceProvider> resourceProvider) {
    return new SiteDesignRecommendationProvider_Factory(resourceProvider);
  }

  public static SiteDesignRecommendationProvider newInstance(ResourceProvider resourceProvider) {
    return new SiteDesignRecommendationProvider(resourceProvider);
  }
}
