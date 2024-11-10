package org.wordpress.android.ui.mysite.items;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.mysite.cards.quickstart.QuickStartRepository;
import org.wordpress.android.ui.mysite.items.categoryheader.SiteCategoryItemBuilder;
import org.wordpress.android.ui.mysite.items.listitem.SiteListItemBuilder;

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
public final class SiteItemsBuilder_Factory implements Factory<SiteItemsBuilder> {
  private final Provider<SiteCategoryItemBuilder> siteCategoryItemBuilderProvider;

  private final Provider<SiteListItemBuilder> siteListItemBuilderProvider;

  private final Provider<QuickStartRepository> quickStartRepositoryProvider;

  public SiteItemsBuilder_Factory(Provider<SiteCategoryItemBuilder> siteCategoryItemBuilderProvider,
      Provider<SiteListItemBuilder> siteListItemBuilderProvider,
      Provider<QuickStartRepository> quickStartRepositoryProvider) {
    this.siteCategoryItemBuilderProvider = siteCategoryItemBuilderProvider;
    this.siteListItemBuilderProvider = siteListItemBuilderProvider;
    this.quickStartRepositoryProvider = quickStartRepositoryProvider;
  }

  @Override
  public SiteItemsBuilder get() {
    return newInstance(siteCategoryItemBuilderProvider.get(), siteListItemBuilderProvider.get(), quickStartRepositoryProvider.get());
  }

  public static SiteItemsBuilder_Factory create(
      Provider<SiteCategoryItemBuilder> siteCategoryItemBuilderProvider,
      Provider<SiteListItemBuilder> siteListItemBuilderProvider,
      Provider<QuickStartRepository> quickStartRepositoryProvider) {
    return new SiteItemsBuilder_Factory(siteCategoryItemBuilderProvider, siteListItemBuilderProvider, quickStartRepositoryProvider);
  }

  public static SiteItemsBuilder newInstance(SiteCategoryItemBuilder siteCategoryItemBuilder,
      SiteListItemBuilder siteListItemBuilder, QuickStartRepository quickStartRepository) {
    return new SiteItemsBuilder(siteCategoryItemBuilder, siteListItemBuilder, quickStartRepository);
  }
}
