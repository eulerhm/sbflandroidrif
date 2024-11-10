package org.wordpress.android.ui.posts;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.utils.UiHelpers;
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
public final class PrepublishingHomeAdapter_MembersInjector implements MembersInjector<PrepublishingHomeAdapter> {
  private final Provider<UiHelpers> uiHelpersProvider;

  private final Provider<ImageManager> imageManagerProvider;

  public PrepublishingHomeAdapter_MembersInjector(Provider<UiHelpers> uiHelpersProvider,
      Provider<ImageManager> imageManagerProvider) {
    this.uiHelpersProvider = uiHelpersProvider;
    this.imageManagerProvider = imageManagerProvider;
  }

  public static MembersInjector<PrepublishingHomeAdapter> create(
      Provider<UiHelpers> uiHelpersProvider, Provider<ImageManager> imageManagerProvider) {
    return new PrepublishingHomeAdapter_MembersInjector(uiHelpersProvider, imageManagerProvider);
  }

  @Override
  public void injectMembers(PrepublishingHomeAdapter instance) {
    injectUiHelpers(instance, uiHelpersProvider.get());
    injectImageManager(instance, imageManagerProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.PrepublishingHomeAdapter.uiHelpers")
  public static void injectUiHelpers(PrepublishingHomeAdapter instance, UiHelpers uiHelpers) {
    instance.uiHelpers = uiHelpers;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.PrepublishingHomeAdapter.imageManager")
  public static void injectImageManager(PrepublishingHomeAdapter instance,
      ImageManager imageManager) {
    instance.imageManager = imageManager;
  }
}
