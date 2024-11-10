package org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.stats.refresh.utils.StatsUtils;
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
public final class TotalStatsMapper_Factory implements Factory<TotalStatsMapper> {
  private final Provider<ResourceProvider> resourceProvider;

  private final Provider<StatsUtils> statsUtilsProvider;

  public TotalStatsMapper_Factory(Provider<ResourceProvider> resourceProvider,
      Provider<StatsUtils> statsUtilsProvider) {
    this.resourceProvider = resourceProvider;
    this.statsUtilsProvider = statsUtilsProvider;
  }

  @Override
  public TotalStatsMapper get() {
    return newInstance(resourceProvider.get(), statsUtilsProvider.get());
  }

  public static TotalStatsMapper_Factory create(Provider<ResourceProvider> resourceProvider,
      Provider<StatsUtils> statsUtilsProvider) {
    return new TotalStatsMapper_Factory(resourceProvider, statsUtilsProvider);
  }

  public static TotalStatsMapper newInstance(ResourceProvider resourceProvider,
      StatsUtils statsUtils) {
    return new TotalStatsMapper(resourceProvider, statsUtils);
  }
}
