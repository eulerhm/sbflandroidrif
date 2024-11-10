package org.wordpress.android.ui.sitecreation.sitename;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.utils.HtmlMessageUtils;
import org.wordpress.android.ui.utils.UiHelpers;
import org.wordpress.android.util.DisplayUtilsWrapper;

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
public final class SiteCreationSiteNameFragment_MembersInjector implements MembersInjector<SiteCreationSiteNameFragment> {
  private final Provider<UiHelpers> uiHelperProvider;

  private final Provider<DisplayUtilsWrapper> displayUtilsProvider;

  private final Provider<HtmlMessageUtils> htmlMessageUtilsProvider;

  public SiteCreationSiteNameFragment_MembersInjector(Provider<UiHelpers> uiHelperProvider,
      Provider<DisplayUtilsWrapper> displayUtilsProvider,
      Provider<HtmlMessageUtils> htmlMessageUtilsProvider) {
    this.uiHelperProvider = uiHelperProvider;
    this.displayUtilsProvider = displayUtilsProvider;
    this.htmlMessageUtilsProvider = htmlMessageUtilsProvider;
  }

  public static MembersInjector<SiteCreationSiteNameFragment> create(
      Provider<UiHelpers> uiHelperProvider, Provider<DisplayUtilsWrapper> displayUtilsProvider,
      Provider<HtmlMessageUtils> htmlMessageUtilsProvider) {
    return new SiteCreationSiteNameFragment_MembersInjector(uiHelperProvider, displayUtilsProvider, htmlMessageUtilsProvider);
  }

  @Override
  public void injectMembers(SiteCreationSiteNameFragment instance) {
    injectUiHelper(instance, uiHelperProvider.get());
    injectDisplayUtils(instance, displayUtilsProvider.get());
    injectHtmlMessageUtils(instance, htmlMessageUtilsProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.sitecreation.sitename.SiteCreationSiteNameFragment.uiHelper")
  public static void injectUiHelper(SiteCreationSiteNameFragment instance, UiHelpers uiHelper) {
    instance.uiHelper = uiHelper;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.sitecreation.sitename.SiteCreationSiteNameFragment.displayUtils")
  public static void injectDisplayUtils(SiteCreationSiteNameFragment instance,
      DisplayUtilsWrapper displayUtils) {
    instance.displayUtils = displayUtils;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.sitecreation.sitename.SiteCreationSiteNameFragment.htmlMessageUtils")
  public static void injectHtmlMessageUtils(SiteCreationSiteNameFragment instance,
      HtmlMessageUtils htmlMessageUtils) {
    instance.htmlMessageUtils = htmlMessageUtils;
  }
}
