package org.wordpress.android.ui.sitecreation.domains;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
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
public final class SiteCreationDomainsFragment_MembersInjector implements MembersInjector<SiteCreationDomainsFragment> {
  private final Provider<UiHelpers> uiHelpersProvider;

  private final Provider<DisplayUtilsWrapper> displayUtilsProvider;

  public SiteCreationDomainsFragment_MembersInjector(Provider<UiHelpers> uiHelpersProvider,
      Provider<DisplayUtilsWrapper> displayUtilsProvider) {
    this.uiHelpersProvider = uiHelpersProvider;
    this.displayUtilsProvider = displayUtilsProvider;
  }

  public static MembersInjector<SiteCreationDomainsFragment> create(
      Provider<UiHelpers> uiHelpersProvider, Provider<DisplayUtilsWrapper> displayUtilsProvider) {
    return new SiteCreationDomainsFragment_MembersInjector(uiHelpersProvider, displayUtilsProvider);
  }

  @Override
  public void injectMembers(SiteCreationDomainsFragment instance) {
    injectUiHelpers(instance, uiHelpersProvider.get());
    injectDisplayUtils(instance, displayUtilsProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.sitecreation.domains.SiteCreationDomainsFragment.uiHelpers")
  public static void injectUiHelpers(SiteCreationDomainsFragment instance, UiHelpers uiHelpers) {
    instance.uiHelpers = uiHelpers;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.sitecreation.domains.SiteCreationDomainsFragment.displayUtils")
  public static void injectDisplayUtils(SiteCreationDomainsFragment instance,
      DisplayUtilsWrapper displayUtils) {
    instance.displayUtils = displayUtils;
  }
}
