package org.wordpress.android.ui.stats.refresh.utils;

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
public final class ServiceMapper_Factory implements Factory<ServiceMapper> {
  private final Provider<ResourceProvider> resourceProvider;

  private final Provider<StatsUtils> statsUtilsProvider;

  private final Provider<ContentDescriptionHelper> contentDescriptionHelperProvider;

  public ServiceMapper_Factory(Provider<ResourceProvider> resourceProvider,
      Provider<StatsUtils> statsUtilsProvider,
      Provider<ContentDescriptionHelper> contentDescriptionHelperProvider) {
    this.resourceProvider = resourceProvider;
    this.statsUtilsProvider = statsUtilsProvider;
    this.contentDescriptionHelperProvider = contentDescriptionHelperProvider;
  }

  @Override
  public ServiceMapper get() {
    return newInstance(resourceProvider.get(), statsUtilsProvider.get(), contentDescriptionHelperProvider.get());
  }

  public static ServiceMapper_Factory create(Provider<ResourceProvider> resourceProvider,
      Provider<StatsUtils> statsUtilsProvider,
      Provider<ContentDescriptionHelper> contentDescriptionHelperProvider) {
    return new ServiceMapper_Factory(resourceProvider, statsUtilsProvider, contentDescriptionHelperProvider);
  }

  public static ServiceMapper newInstance(ResourceProvider resourceProvider, StatsUtils statsUtils,
      ContentDescriptionHelper contentDescriptionHelper) {
    return new ServiceMapper(resourceProvider, statsUtils, contentDescriptionHelper);
  }
}
