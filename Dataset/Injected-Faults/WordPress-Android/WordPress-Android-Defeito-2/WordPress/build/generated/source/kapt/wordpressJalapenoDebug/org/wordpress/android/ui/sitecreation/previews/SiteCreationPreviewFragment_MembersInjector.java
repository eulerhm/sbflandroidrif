package org.wordpress.android.ui.sitecreation.previews;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.utils.UiHelpers;

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
public final class SiteCreationPreviewFragment_MembersInjector implements MembersInjector<SiteCreationPreviewFragment> {
  private final Provider<UiHelpers> uiHelpersProvider;

  public SiteCreationPreviewFragment_MembersInjector(Provider<UiHelpers> uiHelpersProvider) {
    this.uiHelpersProvider = uiHelpersProvider;
  }

  public static MembersInjector<SiteCreationPreviewFragment> create(
      Provider<UiHelpers> uiHelpersProvider) {
    return new SiteCreationPreviewFragment_MembersInjector(uiHelpersProvider);
  }

  @Override
  public void injectMembers(SiteCreationPreviewFragment instance) {
    injectUiHelpers(instance, uiHelpersProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.sitecreation.previews.SiteCreationPreviewFragment.uiHelpers")
  public static void injectUiHelpers(SiteCreationPreviewFragment instance, UiHelpers uiHelpers) {
    instance.uiHelpers = uiHelpers;
  }
}
