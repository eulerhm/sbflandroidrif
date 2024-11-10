package org.wordpress.android.ui.posts.adapters;

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
public final class AuthorSelectionAdapter_MembersInjector implements MembersInjector<AuthorSelectionAdapter> {
  private final Provider<ImageManager> imageManagerProvider;

  private final Provider<UiHelpers> uiHelpersProvider;

  public AuthorSelectionAdapter_MembersInjector(Provider<ImageManager> imageManagerProvider,
      Provider<UiHelpers> uiHelpersProvider) {
    this.imageManagerProvider = imageManagerProvider;
    this.uiHelpersProvider = uiHelpersProvider;
  }

  public static MembersInjector<AuthorSelectionAdapter> create(
      Provider<ImageManager> imageManagerProvider, Provider<UiHelpers> uiHelpersProvider) {
    return new AuthorSelectionAdapter_MembersInjector(imageManagerProvider, uiHelpersProvider);
  }

  @Override
  public void injectMembers(AuthorSelectionAdapter instance) {
    injectImageManager(instance, imageManagerProvider.get());
    injectUiHelpers(instance, uiHelpersProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.adapters.AuthorSelectionAdapter.imageManager")
  public static void injectImageManager(AuthorSelectionAdapter instance,
      ImageManager imageManager) {
    instance.imageManager = imageManager;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.adapters.AuthorSelectionAdapter.uiHelpers")
  public static void injectUiHelpers(AuthorSelectionAdapter instance, UiHelpers uiHelpers) {
    instance.uiHelpers = uiHelpers;
  }
}
