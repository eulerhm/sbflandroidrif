package org.wordpress.android.ui.stats.refresh.lists.detail;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
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
public final class PostDayViewsMapper_Factory implements Factory<PostDayViewsMapper> {
  private final Provider<ResourceProvider> resourceProvider;

  private final Provider<StatsUtils> statsUtilsProvider;

  private final Provider<StatsDateFormatter> statsDateFormatterProvider;

  public PostDayViewsMapper_Factory(Provider<ResourceProvider> resourceProvider,
      Provider<StatsUtils> statsUtilsProvider,
      Provider<StatsDateFormatter> statsDateFormatterProvider) {
    this.resourceProvider = resourceProvider;
    this.statsUtilsProvider = statsUtilsProvider;
    this.statsDateFormatterProvider = statsDateFormatterProvider;
  }

  @Override
  public PostDayViewsMapper get() {
    return newInstance(resourceProvider.get(), statsUtilsProvider.get(), statsDateFormatterProvider.get());
  }

  public static PostDayViewsMapper_Factory create(Provider<ResourceProvider> resourceProvider,
      Provider<StatsUtils> statsUtilsProvider,
      Provider<StatsDateFormatter> statsDateFormatterProvider) {
    return new PostDayViewsMapper_Factory(resourceProvider, statsUtilsProvider, statsDateFormatterProvider);
  }

  public static PostDayViewsMapper newInstance(ResourceProvider resourceProvider,
      StatsUtils statsUtils, StatsDateFormatter statsDateFormatter) {
    return new PostDayViewsMapper(resourceProvider, statsUtils, statsDateFormatter);
  }
}
