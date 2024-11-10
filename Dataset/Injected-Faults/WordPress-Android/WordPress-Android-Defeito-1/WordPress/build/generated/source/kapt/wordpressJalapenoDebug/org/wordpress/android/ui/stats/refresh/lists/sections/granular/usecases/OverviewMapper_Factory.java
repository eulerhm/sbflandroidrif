package org.wordpress.android.ui.stats.refresh.lists.sections.granular.usecases;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.stats.refresh.utils.ContentDescriptionHelper;
import org.wordpress.android.ui.stats.refresh.utils.StatsDateFormatter;
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
public final class OverviewMapper_Factory implements Factory<OverviewMapper> {
  private final Provider<StatsDateFormatter> statsDateFormatterProvider;

  private final Provider<ResourceProvider> resourceProvider;

  private final Provider<StatsUtils> statsUtilsProvider;

  private final Provider<ContentDescriptionHelper> contentDescriptionHelperProvider;

  public OverviewMapper_Factory(Provider<StatsDateFormatter> statsDateFormatterProvider,
      Provider<ResourceProvider> resourceProvider, Provider<StatsUtils> statsUtilsProvider,
      Provider<ContentDescriptionHelper> contentDescriptionHelperProvider) {
    this.statsDateFormatterProvider = statsDateFormatterProvider;
    this.resourceProvider = resourceProvider;
    this.statsUtilsProvider = statsUtilsProvider;
    this.contentDescriptionHelperProvider = contentDescriptionHelperProvider;
  }

  @Override
  public OverviewMapper get() {
    return newInstance(statsDateFormatterProvider.get(), resourceProvider.get(), statsUtilsProvider.get(), contentDescriptionHelperProvider.get());
  }

  public static OverviewMapper_Factory create(
      Provider<StatsDateFormatter> statsDateFormatterProvider,
      Provider<ResourceProvider> resourceProvider, Provider<StatsUtils> statsUtilsProvider,
      Provider<ContentDescriptionHelper> contentDescriptionHelperProvider) {
    return new OverviewMapper_Factory(statsDateFormatterProvider, resourceProvider, statsUtilsProvider, contentDescriptionHelperProvider);
  }

  public static OverviewMapper newInstance(StatsDateFormatter statsDateFormatter,
      ResourceProvider resourceProvider, StatsUtils statsUtils,
      ContentDescriptionHelper contentDescriptionHelper) {
    return new OverviewMapper(statsDateFormatter, resourceProvider, statsUtils, contentDescriptionHelper);
  }
}
