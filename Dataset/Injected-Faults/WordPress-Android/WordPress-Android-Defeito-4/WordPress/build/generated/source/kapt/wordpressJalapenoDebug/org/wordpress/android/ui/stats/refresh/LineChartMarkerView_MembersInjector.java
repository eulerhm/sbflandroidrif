package org.wordpress.android.ui.stats.refresh;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.stats.refresh.utils.StatsUtils;

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
public final class LineChartMarkerView_MembersInjector implements MembersInjector<LineChartMarkerView> {
  private final Provider<StatsUtils> statsUtilsProvider;

  public LineChartMarkerView_MembersInjector(Provider<StatsUtils> statsUtilsProvider) {
    this.statsUtilsProvider = statsUtilsProvider;
  }

  public static MembersInjector<LineChartMarkerView> create(
      Provider<StatsUtils> statsUtilsProvider) {
    return new LineChartMarkerView_MembersInjector(statsUtilsProvider);
  }

  @Override
  public void injectMembers(LineChartMarkerView instance) {
    injectStatsUtils(instance, statsUtilsProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.stats.refresh.LineChartMarkerView.statsUtils")
  public static void injectStatsUtils(LineChartMarkerView instance, StatsUtils statsUtils) {
    instance.statsUtils = statsUtils;
  }
}
