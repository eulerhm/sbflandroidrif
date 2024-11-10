package org.wordpress.android.ui.stats.refresh.utils;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.stats.refresh.lists.sections.granular.SelectedDateProvider;

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
public final class StatsDateSelector_Factory_Factory implements Factory<StatsDateSelector.Factory> {
  private final Provider<SelectedDateProvider> selectedDateProvider;

  private final Provider<StatsSiteProvider> siteProvider;

  private final Provider<StatsDateFormatter> statsDateFormatterProvider;

  public StatsDateSelector_Factory_Factory(Provider<SelectedDateProvider> selectedDateProvider,
      Provider<StatsSiteProvider> siteProvider,
      Provider<StatsDateFormatter> statsDateFormatterProvider) {
    this.selectedDateProvider = selectedDateProvider;
    this.siteProvider = siteProvider;
    this.statsDateFormatterProvider = statsDateFormatterProvider;
  }

  @Override
  public StatsDateSelector.Factory get() {
    return newInstance(selectedDateProvider.get(), siteProvider.get(), statsDateFormatterProvider.get());
  }

  public static StatsDateSelector_Factory_Factory create(
      Provider<SelectedDateProvider> selectedDateProvider, Provider<StatsSiteProvider> siteProvider,
      Provider<StatsDateFormatter> statsDateFormatterProvider) {
    return new StatsDateSelector_Factory_Factory(selectedDateProvider, siteProvider, statsDateFormatterProvider);
  }

  public static StatsDateSelector.Factory newInstance(SelectedDateProvider selectedDateProvider,
      StatsSiteProvider siteProvider, StatsDateFormatter statsDateFormatter) {
    return new StatsDateSelector.Factory(selectedDateProvider, siteProvider, statsDateFormatter);
  }
}
