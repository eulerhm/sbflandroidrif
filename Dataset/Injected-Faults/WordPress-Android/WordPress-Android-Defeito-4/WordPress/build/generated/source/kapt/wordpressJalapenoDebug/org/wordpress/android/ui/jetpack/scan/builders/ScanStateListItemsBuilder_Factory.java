package org.wordpress.android.ui.jetpack.scan.builders;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.store.ScanStore;
import org.wordpress.android.ui.jetpack.scan.details.ThreatDetailsListItemsBuilder;
import org.wordpress.android.ui.reader.utils.DateProvider;
import org.wordpress.android.ui.utils.HtmlMessageUtils;
import org.wordpress.android.viewmodel.ResourceProvider;

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
public final class ScanStateListItemsBuilder_Factory implements Factory<ScanStateListItemsBuilder> {
  private final Provider<DateProvider> dateProvider;

  private final Provider<HtmlMessageUtils> htmlMessageUtilsProvider;

  private final Provider<ResourceProvider> resourceProvider;

  private final Provider<ThreatItemBuilder> threatItemBuilderProvider;

  private final Provider<ThreatDetailsListItemsBuilder> threatDetailsListItemsBuilderProvider;

  private final Provider<ScanStore> scanStoreProvider;

  public ScanStateListItemsBuilder_Factory(Provider<DateProvider> dateProvider,
      Provider<HtmlMessageUtils> htmlMessageUtilsProvider,
      Provider<ResourceProvider> resourceProvider,
      Provider<ThreatItemBuilder> threatItemBuilderProvider,
      Provider<ThreatDetailsListItemsBuilder> threatDetailsListItemsBuilderProvider,
      Provider<ScanStore> scanStoreProvider) {
    this.dateProvider = dateProvider;
    this.htmlMessageUtilsProvider = htmlMessageUtilsProvider;
    this.resourceProvider = resourceProvider;
    this.threatItemBuilderProvider = threatItemBuilderProvider;
    this.threatDetailsListItemsBuilderProvider = threatDetailsListItemsBuilderProvider;
    this.scanStoreProvider = scanStoreProvider;
  }

  @Override
  public ScanStateListItemsBuilder get() {
    return newInstance(dateProvider.get(), htmlMessageUtilsProvider.get(), resourceProvider.get(), threatItemBuilderProvider.get(), threatDetailsListItemsBuilderProvider.get(), scanStoreProvider.get());
  }

  public static ScanStateListItemsBuilder_Factory create(Provider<DateProvider> dateProvider,
      Provider<HtmlMessageUtils> htmlMessageUtilsProvider,
      Provider<ResourceProvider> resourceProvider,
      Provider<ThreatItemBuilder> threatItemBuilderProvider,
      Provider<ThreatDetailsListItemsBuilder> threatDetailsListItemsBuilderProvider,
      Provider<ScanStore> scanStoreProvider) {
    return new ScanStateListItemsBuilder_Factory(dateProvider, htmlMessageUtilsProvider, resourceProvider, threatItemBuilderProvider, threatDetailsListItemsBuilderProvider, scanStoreProvider);
  }

  public static ScanStateListItemsBuilder newInstance(DateProvider dateProvider,
      HtmlMessageUtils htmlMessageUtils, ResourceProvider resourceProvider,
      ThreatItemBuilder threatItemBuilder,
      ThreatDetailsListItemsBuilder threatDetailsListItemsBuilder, ScanStore scanStore) {
    return new ScanStateListItemsBuilder(dateProvider, htmlMessageUtils, resourceProvider, threatItemBuilder, threatDetailsListItemsBuilder, scanStore);
  }
}
