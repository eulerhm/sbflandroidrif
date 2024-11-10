package org.wordpress.android.ui.bloggingreminders;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.util.LocaleManagerWrapper;
import org.wordpress.android.util.config.BloggingPromptsFeatureConfig;

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
public final class DaySelectionBuilder_Factory implements Factory<DaySelectionBuilder> {
  private final Provider<DaysProvider> daysProvider;

  private final Provider<DayLabelUtils> dayLabelUtilsProvider;

  private final Provider<LocaleManagerWrapper> localeManagerWrapperProvider;

  private final Provider<BloggingPromptsFeatureConfig> bloggingPromptsFeatureConfigProvider;

  public DaySelectionBuilder_Factory(Provider<DaysProvider> daysProvider,
      Provider<DayLabelUtils> dayLabelUtilsProvider,
      Provider<LocaleManagerWrapper> localeManagerWrapperProvider,
      Provider<BloggingPromptsFeatureConfig> bloggingPromptsFeatureConfigProvider) {
    this.daysProvider = daysProvider;
    this.dayLabelUtilsProvider = dayLabelUtilsProvider;
    this.localeManagerWrapperProvider = localeManagerWrapperProvider;
    this.bloggingPromptsFeatureConfigProvider = bloggingPromptsFeatureConfigProvider;
  }

  @Override
  public DaySelectionBuilder get() {
    return newInstance(daysProvider.get(), dayLabelUtilsProvider.get(), localeManagerWrapperProvider.get(), bloggingPromptsFeatureConfigProvider.get());
  }

  public static DaySelectionBuilder_Factory create(Provider<DaysProvider> daysProvider,
      Provider<DayLabelUtils> dayLabelUtilsProvider,
      Provider<LocaleManagerWrapper> localeManagerWrapperProvider,
      Provider<BloggingPromptsFeatureConfig> bloggingPromptsFeatureConfigProvider) {
    return new DaySelectionBuilder_Factory(daysProvider, dayLabelUtilsProvider, localeManagerWrapperProvider, bloggingPromptsFeatureConfigProvider);
  }

  public static DaySelectionBuilder newInstance(DaysProvider daysProvider,
      DayLabelUtils dayLabelUtils, LocaleManagerWrapper localeManagerWrapper,
      BloggingPromptsFeatureConfig bloggingPromptsFeatureConfig) {
    return new DaySelectionBuilder(daysProvider, dayLabelUtils, localeManagerWrapper, bloggingPromptsFeatureConfig);
  }
}
