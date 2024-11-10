package org.wordpress.android.ui.mysite.cards.dashboard.todaysstats;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.utils.AppLogWrapper;
import org.wordpress.android.ui.stats.refresh.utils.StatsUtils;
import org.wordpress.android.ui.utils.HtmlMessageUtils;

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
public final class TodaysStatsCardBuilder_Factory implements Factory<TodaysStatsCardBuilder> {
  private final Provider<StatsUtils> statsUtilsProvider;

  private final Provider<AppLogWrapper> appLogWrapperProvider;

  private final Provider<HtmlMessageUtils> htmlMessageUtilsProvider;

  public TodaysStatsCardBuilder_Factory(Provider<StatsUtils> statsUtilsProvider,
      Provider<AppLogWrapper> appLogWrapperProvider,
      Provider<HtmlMessageUtils> htmlMessageUtilsProvider) {
    this.statsUtilsProvider = statsUtilsProvider;
    this.appLogWrapperProvider = appLogWrapperProvider;
    this.htmlMessageUtilsProvider = htmlMessageUtilsProvider;
  }

  @Override
  public TodaysStatsCardBuilder get() {
    return newInstance(statsUtilsProvider.get(), appLogWrapperProvider.get(), htmlMessageUtilsProvider.get());
  }

  public static TodaysStatsCardBuilder_Factory create(Provider<StatsUtils> statsUtilsProvider,
      Provider<AppLogWrapper> appLogWrapperProvider,
      Provider<HtmlMessageUtils> htmlMessageUtilsProvider) {
    return new TodaysStatsCardBuilder_Factory(statsUtilsProvider, appLogWrapperProvider, htmlMessageUtilsProvider);
  }

  public static TodaysStatsCardBuilder newInstance(StatsUtils statsUtils,
      AppLogWrapper appLogWrapper, HtmlMessageUtils htmlMessageUtils) {
    return new TodaysStatsCardBuilder(statsUtils, appLogWrapper, htmlMessageUtils);
  }
}
