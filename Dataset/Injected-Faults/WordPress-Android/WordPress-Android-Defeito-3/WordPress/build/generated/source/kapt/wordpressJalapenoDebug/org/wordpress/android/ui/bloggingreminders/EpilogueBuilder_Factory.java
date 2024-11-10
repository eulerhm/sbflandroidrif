package org.wordpress.android.ui.bloggingreminders;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.utils.HtmlMessageUtils;
import org.wordpress.android.util.ListFormatterUtils;
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
public final class EpilogueBuilder_Factory implements Factory<EpilogueBuilder> {
  private final Provider<DayLabelUtils> dayLabelUtilsProvider;

  private final Provider<LocaleManagerWrapper> localeManagerWrapperProvider;

  private final Provider<ListFormatterUtils> listFormatterUtilsProvider;

  private final Provider<HtmlMessageUtils> htmlMessageUtilsProvider;

  public EpilogueBuilder_Factory(Provider<DayLabelUtils> dayLabelUtilsProvider,
      Provider<LocaleManagerWrapper> localeManagerWrapperProvider,
      Provider<ListFormatterUtils> listFormatterUtilsProvider,
      Provider<HtmlMessageUtils> htmlMessageUtilsProvider) {
    this.dayLabelUtilsProvider = dayLabelUtilsProvider;
    this.localeManagerWrapperProvider = localeManagerWrapperProvider;
    this.listFormatterUtilsProvider = listFormatterUtilsProvider;
    this.htmlMessageUtilsProvider = htmlMessageUtilsProvider;
  }

  @Override
  public EpilogueBuilder get() {
    return newInstance(dayLabelUtilsProvider.get(), localeManagerWrapperProvider.get(), listFormatterUtilsProvider.get(), htmlMessageUtilsProvider.get());
  }

  public static EpilogueBuilder_Factory create(Provider<DayLabelUtils> dayLabelUtilsProvider,
      Provider<LocaleManagerWrapper> localeManagerWrapperProvider,
      Provider<ListFormatterUtils> listFormatterUtilsProvider,
      Provider<HtmlMessageUtils> htmlMessageUtilsProvider) {
    return new EpilogueBuilder_Factory(dayLabelUtilsProvider, localeManagerWrapperProvider, listFormatterUtilsProvider, htmlMessageUtilsProvider);
  }

  public static EpilogueBuilder newInstance(DayLabelUtils dayLabelUtils,
      LocaleManagerWrapper localeManagerWrapper, ListFormatterUtils listFormatterUtils,
      HtmlMessageUtils htmlMessageUtils) {
    return new EpilogueBuilder(dayLabelUtils, localeManagerWrapper, listFormatterUtils, htmlMessageUtils);
  }
}
