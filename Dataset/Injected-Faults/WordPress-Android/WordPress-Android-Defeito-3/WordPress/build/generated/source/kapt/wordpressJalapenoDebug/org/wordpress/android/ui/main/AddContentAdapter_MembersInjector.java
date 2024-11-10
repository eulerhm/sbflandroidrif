package org.wordpress.android.ui.main;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.utils.UiHelpers;
import org.wordpress.android.util.HtmlCompatWrapper;
import org.wordpress.android.util.image.ImageManager;

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
public final class AddContentAdapter_MembersInjector implements MembersInjector<AddContentAdapter> {
  private final Provider<ImageManager> imageManagerProvider;

  private final Provider<UiHelpers> uiHelpersProvider;

  private final Provider<HtmlCompatWrapper> htmlCompatWrapperProvider;

  public AddContentAdapter_MembersInjector(Provider<ImageManager> imageManagerProvider,
      Provider<UiHelpers> uiHelpersProvider,
      Provider<HtmlCompatWrapper> htmlCompatWrapperProvider) {
    this.imageManagerProvider = imageManagerProvider;
    this.uiHelpersProvider = uiHelpersProvider;
    this.htmlCompatWrapperProvider = htmlCompatWrapperProvider;
  }

  public static MembersInjector<AddContentAdapter> create(
      Provider<ImageManager> imageManagerProvider, Provider<UiHelpers> uiHelpersProvider,
      Provider<HtmlCompatWrapper> htmlCompatWrapperProvider) {
    return new AddContentAdapter_MembersInjector(imageManagerProvider, uiHelpersProvider, htmlCompatWrapperProvider);
  }

  @Override
  public void injectMembers(AddContentAdapter instance) {
    injectImageManager(instance, imageManagerProvider.get());
    injectUiHelpers(instance, uiHelpersProvider.get());
    injectHtmlCompatWrapper(instance, htmlCompatWrapperProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.main.AddContentAdapter.imageManager")
  public static void injectImageManager(AddContentAdapter instance, ImageManager imageManager) {
    instance.imageManager = imageManager;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.main.AddContentAdapter.uiHelpers")
  public static void injectUiHelpers(AddContentAdapter instance, UiHelpers uiHelpers) {
    instance.uiHelpers = uiHelpers;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.main.AddContentAdapter.htmlCompatWrapper")
  public static void injectHtmlCompatWrapper(AddContentAdapter instance,
      HtmlCompatWrapper htmlCompatWrapper) {
    instance.htmlCompatWrapper = htmlCompatWrapper;
  }
}
