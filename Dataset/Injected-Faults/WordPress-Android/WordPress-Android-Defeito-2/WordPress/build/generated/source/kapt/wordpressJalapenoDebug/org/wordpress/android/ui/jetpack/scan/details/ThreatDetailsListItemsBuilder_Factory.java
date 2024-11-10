package org.wordpress.android.ui.jetpack.scan.details;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.jetpack.scan.builders.ThreatItemBuilder;
import org.wordpress.android.ui.utils.HtmlMessageUtils;
import org.wordpress.android.util.DateFormatWrapper;

@ScopeMetadata("dagger.Reusable")
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
public final class ThreatDetailsListItemsBuilder_Factory implements Factory<ThreatDetailsListItemsBuilder> {
  private final Provider<HtmlMessageUtils> htmlMessageUtilsProvider;

  private final Provider<ThreatItemBuilder> threatItemBuilderProvider;

  private final Provider<DateFormatWrapper> dateFormatWrapperProvider;

  public ThreatDetailsListItemsBuilder_Factory(Provider<HtmlMessageUtils> htmlMessageUtilsProvider,
      Provider<ThreatItemBuilder> threatItemBuilderProvider,
      Provider<DateFormatWrapper> dateFormatWrapperProvider) {
    this.htmlMessageUtilsProvider = htmlMessageUtilsProvider;
    this.threatItemBuilderProvider = threatItemBuilderProvider;
    this.dateFormatWrapperProvider = dateFormatWrapperProvider;
  }

  @Override
  public ThreatDetailsListItemsBuilder get() {
    return newInstance(htmlMessageUtilsProvider.get(), threatItemBuilderProvider.get(), dateFormatWrapperProvider.get());
  }

  public static ThreatDetailsListItemsBuilder_Factory create(
      Provider<HtmlMessageUtils> htmlMessageUtilsProvider,
      Provider<ThreatItemBuilder> threatItemBuilderProvider,
      Provider<DateFormatWrapper> dateFormatWrapperProvider) {
    return new ThreatDetailsListItemsBuilder_Factory(htmlMessageUtilsProvider, threatItemBuilderProvider, dateFormatWrapperProvider);
  }

  public static ThreatDetailsListItemsBuilder newInstance(HtmlMessageUtils htmlMessageUtils,
      ThreatItemBuilder threatItemBuilder, DateFormatWrapper dateFormatWrapper) {
    return new ThreatDetailsListItemsBuilder(htmlMessageUtils, threatItemBuilder, dateFormatWrapper);
  }
}
