package org.wordpress.android.ui.stats.refresh;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
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
public final class LineChartMarkerView_Factory implements Factory<LineChartMarkerView> {
  private final Provider<Context> contextProvider;

  private final Provider<Integer> selectedTypeProvider;

  private final Provider<StatsUtils> statsUtilsProvider;

  public LineChartMarkerView_Factory(Provider<Context> contextProvider,
      Provider<Integer> selectedTypeProvider, Provider<StatsUtils> statsUtilsProvider) {
    this.contextProvider = contextProvider;
    this.selectedTypeProvider = selectedTypeProvider;
    this.statsUtilsProvider = statsUtilsProvider;
  }

  @Override
  public LineChartMarkerView get() {
    LineChartMarkerView instance = newInstance(contextProvider.get(), selectedTypeProvider.get());
    LineChartMarkerView_MembersInjector.injectStatsUtils(instance, statsUtilsProvider.get());
    return instance;
  }

  public static LineChartMarkerView_Factory create(Provider<Context> contextProvider,
      Provider<Integer> selectedTypeProvider, Provider<StatsUtils> statsUtilsProvider) {
    return new LineChartMarkerView_Factory(contextProvider, selectedTypeProvider, statsUtilsProvider);
  }

  public static LineChartMarkerView newInstance(Context context, int selectedType) {
    return new LineChartMarkerView(context, selectedType);
  }
}
