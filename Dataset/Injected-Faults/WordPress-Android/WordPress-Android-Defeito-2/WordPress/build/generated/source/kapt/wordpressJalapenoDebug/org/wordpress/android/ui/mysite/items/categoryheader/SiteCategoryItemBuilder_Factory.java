package org.wordpress.android.ui.mysite.items.categoryheader;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.themes.ThemeBrowserUtils;
import org.wordpress.android.util.SiteUtilsWrapper;

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
public final class SiteCategoryItemBuilder_Factory implements Factory<SiteCategoryItemBuilder> {
  private final Provider<ThemeBrowserUtils> themeBrowserUtilsProvider;

  private final Provider<SiteUtilsWrapper> siteUtilsWrapperProvider;

  public SiteCategoryItemBuilder_Factory(Provider<ThemeBrowserUtils> themeBrowserUtilsProvider,
      Provider<SiteUtilsWrapper> siteUtilsWrapperProvider) {
    this.themeBrowserUtilsProvider = themeBrowserUtilsProvider;
    this.siteUtilsWrapperProvider = siteUtilsWrapperProvider;
  }

  @Override
  public SiteCategoryItemBuilder get() {
    return newInstance(themeBrowserUtilsProvider.get(), siteUtilsWrapperProvider.get());
  }

  public static SiteCategoryItemBuilder_Factory create(
      Provider<ThemeBrowserUtils> themeBrowserUtilsProvider,
      Provider<SiteUtilsWrapper> siteUtilsWrapperProvider) {
    return new SiteCategoryItemBuilder_Factory(themeBrowserUtilsProvider, siteUtilsWrapperProvider);
  }

  public static SiteCategoryItemBuilder newInstance(ThemeBrowserUtils themeBrowserUtils,
      SiteUtilsWrapper siteUtilsWrapper) {
    return new SiteCategoryItemBuilder(themeBrowserUtils, siteUtilsWrapper);
  }
}
