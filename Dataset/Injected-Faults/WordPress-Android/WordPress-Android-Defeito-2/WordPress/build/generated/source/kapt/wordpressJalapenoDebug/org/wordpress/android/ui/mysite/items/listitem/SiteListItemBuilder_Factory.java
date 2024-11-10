package org.wordpress.android.ui.mysite.items.listitem;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.ui.plugins.PluginUtilsWrapper;
import org.wordpress.android.ui.themes.ThemeBrowserUtils;
import org.wordpress.android.util.BuildConfigWrapper;
import org.wordpress.android.util.SiteUtilsWrapper;
import org.wordpress.android.util.config.SiteDomainsFeatureConfig;

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
public final class SiteListItemBuilder_Factory implements Factory<SiteListItemBuilder> {
  private final Provider<AccountStore> accountStoreProvider;

  private final Provider<PluginUtilsWrapper> pluginUtilsWrapperProvider;

  private final Provider<SiteUtilsWrapper> siteUtilsWrapperProvider;

  private final Provider<BuildConfigWrapper> buildConfigWrapperProvider;

  private final Provider<ThemeBrowserUtils> themeBrowserUtilsProvider;

  private final Provider<SiteDomainsFeatureConfig> siteDomainsFeatureConfigProvider;

  public SiteListItemBuilder_Factory(Provider<AccountStore> accountStoreProvider,
      Provider<PluginUtilsWrapper> pluginUtilsWrapperProvider,
      Provider<SiteUtilsWrapper> siteUtilsWrapperProvider,
      Provider<BuildConfigWrapper> buildConfigWrapperProvider,
      Provider<ThemeBrowserUtils> themeBrowserUtilsProvider,
      Provider<SiteDomainsFeatureConfig> siteDomainsFeatureConfigProvider) {
    this.accountStoreProvider = accountStoreProvider;
    this.pluginUtilsWrapperProvider = pluginUtilsWrapperProvider;
    this.siteUtilsWrapperProvider = siteUtilsWrapperProvider;
    this.buildConfigWrapperProvider = buildConfigWrapperProvider;
    this.themeBrowserUtilsProvider = themeBrowserUtilsProvider;
    this.siteDomainsFeatureConfigProvider = siteDomainsFeatureConfigProvider;
  }

  @Override
  public SiteListItemBuilder get() {
    return newInstance(accountStoreProvider.get(), pluginUtilsWrapperProvider.get(), siteUtilsWrapperProvider.get(), buildConfigWrapperProvider.get(), themeBrowserUtilsProvider.get(), siteDomainsFeatureConfigProvider.get());
  }

  public static SiteListItemBuilder_Factory create(Provider<AccountStore> accountStoreProvider,
      Provider<PluginUtilsWrapper> pluginUtilsWrapperProvider,
      Provider<SiteUtilsWrapper> siteUtilsWrapperProvider,
      Provider<BuildConfigWrapper> buildConfigWrapperProvider,
      Provider<ThemeBrowserUtils> themeBrowserUtilsProvider,
      Provider<SiteDomainsFeatureConfig> siteDomainsFeatureConfigProvider) {
    return new SiteListItemBuilder_Factory(accountStoreProvider, pluginUtilsWrapperProvider, siteUtilsWrapperProvider, buildConfigWrapperProvider, themeBrowserUtilsProvider, siteDomainsFeatureConfigProvider);
  }

  public static SiteListItemBuilder newInstance(AccountStore accountStore,
      PluginUtilsWrapper pluginUtilsWrapper, SiteUtilsWrapper siteUtilsWrapper,
      BuildConfigWrapper buildConfigWrapper, ThemeBrowserUtils themeBrowserUtils,
      SiteDomainsFeatureConfig siteDomainsFeatureConfig) {
    return new SiteListItemBuilder(accountStore, pluginUtilsWrapper, siteUtilsWrapper, buildConfigWrapper, themeBrowserUtils, siteDomainsFeatureConfig);
  }
}
