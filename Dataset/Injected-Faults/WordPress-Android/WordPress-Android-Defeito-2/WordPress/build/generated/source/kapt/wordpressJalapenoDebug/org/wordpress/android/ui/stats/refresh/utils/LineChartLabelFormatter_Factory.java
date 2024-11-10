package org.wordpress.android.ui.stats.refresh.utils;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import java.util.List;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem;

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
public final class LineChartLabelFormatter_Factory implements Factory<LineChartLabelFormatter> {
  private final Provider<List<BlockListItem.LineChartItem.Line>> entriesProvider;

  public LineChartLabelFormatter_Factory(
      Provider<List<BlockListItem.LineChartItem.Line>> entriesProvider) {
    this.entriesProvider = entriesProvider;
  }

  @Override
  public LineChartLabelFormatter get() {
    return newInstance(entriesProvider.get());
  }

  public static LineChartLabelFormatter_Factory create(
      Provider<List<BlockListItem.LineChartItem.Line>> entriesProvider) {
    return new LineChartLabelFormatter_Factory(entriesProvider);
  }

  public static LineChartLabelFormatter newInstance(
      List<BlockListItem.LineChartItem.Line> entries) {
    return new LineChartLabelFormatter(entries);
  }
}
