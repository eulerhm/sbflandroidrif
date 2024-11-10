package org.wordpress.android.ui.stats.refresh.lists.detail;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.stats.refresh.utils.ContentDescriptionHelper;
import org.wordpress.android.ui.stats.refresh.utils.StatsDateFormatter;
import org.wordpress.android.ui.stats.refresh.utils.StatsUtils;
import org.wordpress.android.util.LocaleManagerWrapper;

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
public final class PostDetailMapper_Factory implements Factory<PostDetailMapper> {
  private final Provider<LocaleManagerWrapper> localeManagerWrapperProvider;

  private final Provider<StatsDateFormatter> statsDateFormatterProvider;

  private final Provider<StatsUtils> statsUtilsProvider;

  private final Provider<ContentDescriptionHelper> contentDescriptionHelperProvider;

  public PostDetailMapper_Factory(Provider<LocaleManagerWrapper> localeManagerWrapperProvider,
      Provider<StatsDateFormatter> statsDateFormatterProvider,
      Provider<StatsUtils> statsUtilsProvider,
      Provider<ContentDescriptionHelper> contentDescriptionHelperProvider) {
    this.localeManagerWrapperProvider = localeManagerWrapperProvider;
    this.statsDateFormatterProvider = statsDateFormatterProvider;
    this.statsUtilsProvider = statsUtilsProvider;
    this.contentDescriptionHelperProvider = contentDescriptionHelperProvider;
  }

  @Override
  public PostDetailMapper get() {
    return newInstance(localeManagerWrapperProvider.get(), statsDateFormatterProvider.get(), statsUtilsProvider.get(), contentDescriptionHelperProvider.get());
  }

  public static PostDetailMapper_Factory create(
      Provider<LocaleManagerWrapper> localeManagerWrapperProvider,
      Provider<StatsDateFormatter> statsDateFormatterProvider,
      Provider<StatsUtils> statsUtilsProvider,
      Provider<ContentDescriptionHelper> contentDescriptionHelperProvider) {
    return new PostDetailMapper_Factory(localeManagerWrapperProvider, statsDateFormatterProvider, statsUtilsProvider, contentDescriptionHelperProvider);
  }

  public static PostDetailMapper newInstance(LocaleManagerWrapper localeManagerWrapper,
      StatsDateFormatter statsDateFormatter, StatsUtils statsUtils,
      ContentDescriptionHelper contentDescriptionHelper) {
    return new PostDetailMapper(localeManagerWrapper, statsDateFormatter, statsUtils, contentDescriptionHelper);
  }
}
