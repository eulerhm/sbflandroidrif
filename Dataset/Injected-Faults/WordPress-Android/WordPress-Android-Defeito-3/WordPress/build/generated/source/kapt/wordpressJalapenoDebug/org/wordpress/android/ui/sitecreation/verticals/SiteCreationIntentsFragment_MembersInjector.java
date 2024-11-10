package org.wordpress.android.ui.sitecreation.verticals;

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
public final class SiteCreationIntentsFragment_MembersInjector implements MembersInjector<SiteCreationIntentsFragment> {
  private final Provider<UiHelpers> uiHelperProvider;

  private final Provider<DisplayUtilsWrapper> displayUtilsProvider;

  public SiteCreationIntentsFragment_MembersInjector(Provider<UiHelpers> uiHelperProvider,
      Provider<DisplayUtilsWrapper> displayUtilsProvider) {
    this.uiHelperProvider = uiHelperProvider;
    this.displayUtilsProvider = displayUtilsProvider;
  }

  public static MembersInjector<SiteCreationIntentsFragment> create(
      Provider<UiHelpers> uiHelperProvider, Provider<DisplayUtilsWrapper> displayUtilsProvider) {
    return new SiteCreationIntentsFragment_MembersInjector(uiHelperProvider, displayUtilsProvider);
  }

  @Override
  public void injectMembers(SiteCreationIntentsFragment instance) {
    injectUiHelper(instance, uiHelperProvider.get());
    injectDisplayUtils(instance, displayUtilsProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.sitecreation.verticals.SiteCreationIntentsFragment.uiHelper")
  public static void injectUiHelper(SiteCreationIntentsFragment instance, UiHelpers uiHelper) {
    instance.uiHelper = uiHelper;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.sitecreation.verticals.SiteCreationIntentsFragment.displayUtils")
  public static void injectDisplayUtils(SiteCreationIntentsFragment instance,
      DisplayUtilsWrapper displayUtils) {
    instance.displayUtils = displayUtils;
  }
}
