package org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.stats.refresh.utils.StatsDateFormatter;
import org.wordpress.android.ui.stats.refresh.utils.StatsSinceLabelFormatter;
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
public final class LatestPostSummaryMapper_Factory implements Factory<LatestPostSummaryMapper> {
  private final Provider<StatsSinceLabelFormatter> statsSinceLabelFormatterProvider;

  private final Provider<ResourceProvider> resourceProvider;

  private final Provider<StatsDateFormatter> statsDateFormatterProvider;

  private final Provider<StatsUtils> statsUtilsProvider;

  public LatestPostSummaryMapper_Factory(
      Provider<StatsSinceLabelFormatter> statsSinceLabelFormatterProvider,
      Provider<ResourceProvider> resourceProvider,
      Provider<StatsDateFormatter> statsDateFormatterProvider,
      Provider<StatsUtils> statsUtilsProvider) {
    this.statsSinceLabelFormatterProvider = statsSinceLabelFormatterProvider;
    this.resourceProvider = resourceProvider;
    this.statsDateFormatterProvider = statsDateFormatterProvider;
    this.statsUtilsProvider = statsUtilsProvider;
  }

  @Override
  public LatestPostSummaryMapper get() {
    return newInstance(statsSinceLabelFormatterProvider.get(), resourceProvider.get(), statsDateFormatterProvider.get(), statsUtilsProvider.get());
  }

  public static LatestPostSummaryMapper_Factory create(
      Provider<StatsSinceLabelFormatter> statsSinceLabelFormatterProvider,
      Provider<ResourceProvider> resourceProvider,
      Provider<StatsDateFormatter> statsDateFormatterProvider,
      Provider<StatsUtils> statsUtilsProvider) {
    return new LatestPostSummaryMapper_Factory(statsSinceLabelFormatterProvider, resourceProvider, statsDateFormatterProvider, statsUtilsProvider);
  }

  public static LatestPostSummaryMapper newInstance(
      StatsSinceLabelFormatter statsSinceLabelFormatter, ResourceProvider resourceProvider,
      StatsDateFormatter statsDateFormatter, StatsUtils statsUtils) {
    return new LatestPostSummaryMapper(statsSinceLabelFormatter, resourceProvider, statsDateFormatter, statsUtils);
  }
}
