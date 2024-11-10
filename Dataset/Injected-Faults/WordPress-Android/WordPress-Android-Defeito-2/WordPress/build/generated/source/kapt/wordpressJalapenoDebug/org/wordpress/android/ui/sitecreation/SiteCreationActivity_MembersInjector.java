package org.wordpress.android.ui.sitecreation;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.utils.UiHelpers;
import org.wordpress.android.util.config.SiteNameFeatureConfig;

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
public final class SiteCreationActivity_MembersInjector implements MembersInjector<SiteCreationActivity> {
  private final Provider<UiHelpers> uiHelpersProvider;

  private final Provider<SiteNameFeatureConfig> siteNameFeatureConfigProvider;

  public SiteCreationActivity_MembersInjector(Provider<UiHelpers> uiHelpersProvider,
      Provider<SiteNameFeatureConfig> siteNameFeatureConfigProvider) {
    this.uiHelpersProvider = uiHelpersProvider;
    this.siteNameFeatureConfigProvider = siteNameFeatureConfigProvider;
  }

  public static MembersInjector<SiteCreationActivity> create(Provider<UiHelpers> uiHelpersProvider,
      Provider<SiteNameFeatureConfig> siteNameFeatureConfigProvider) {
    return new SiteCreationActivity_MembersInjector(uiHelpersProvider, siteNameFeatureConfigProvider);
  }

  @Override
  public void injectMembers(SiteCreationActivity instance) {
    injectUiHelpers(instance, uiHelpersProvider.get());
    injectSiteNameFeatureConfig(instance, siteNameFeatureConfigProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.sitecreation.SiteCreationActivity.uiHelpers")
  public static void injectUiHelpers(SiteCreationActivity instance, UiHelpers uiHelpers) {
    instance.uiHelpers = uiHelpers;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.sitecreation.SiteCreationActivity.siteNameFeatureConfig")
  public static void injectSiteNameFeatureConfig(SiteCreationActivity instance,
      SiteNameFeatureConfig siteNameFeatureConfig) {
    instance.siteNameFeatureConfig = siteNameFeatureConfig;
  }
}
