package org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.stats.refresh.utils.ContentDescriptionHelper;
import org.wordpress.android.ui.stats.refresh.utils.StatsUtils;

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
public final class AnnualStatsMapper_Factory implements Factory<AnnualStatsMapper> {
  private final Provider<ContentDescriptionHelper> contentDescriptionHelperProvider;

  private final Provider<StatsUtils> statsUtilsProvider;

  public AnnualStatsMapper_Factory(
      Provider<ContentDescriptionHelper> contentDescriptionHelperProvider,
      Provider<StatsUtils> statsUtilsProvider) {
    this.contentDescriptionHelperProvider = contentDescriptionHelperProvider;
    this.statsUtilsProvider = statsUtilsProvider;
  }

  @Override
  public AnnualStatsMapper get() {
    return newInstance(contentDescriptionHelperProvider.get(), statsUtilsProvider.get());
  }

  public static AnnualStatsMapper_Factory create(
      Provider<ContentDescriptionHelper> contentDescriptionHelperProvider,
      Provider<StatsUtils> statsUtilsProvider) {
    return new AnnualStatsMapper_Factory(contentDescriptionHelperProvider, statsUtilsProvider);
  }

  public static AnnualStatsMapper newInstance(ContentDescriptionHelper contentDescriptionHelper,
      StatsUtils statsUtils) {
    return new AnnualStatsMapper(contentDescriptionHelper, statsUtils);
  }
}
