package org.wordpress.android.ui.jetpack.restore;

import androidx.lifecycle.ViewModelProvider;
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
public final class RestoreFragment_MembersInjector implements MembersInjector<RestoreFragment> {
  private final Provider<ViewModelProvider.Factory> viewModelFactoryProvider;

  private final Provider<UiHelpers> uiHelpersProvider;

  private final Provider<ImageManager> imageManagerProvider;

  public RestoreFragment_MembersInjector(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<UiHelpers> uiHelpersProvider, Provider<ImageManager> imageManagerProvider) {
    this.viewModelFactoryProvider = viewModelFactoryProvider;
    this.uiHelpersProvider = uiHelpersProvider;
    this.imageManagerProvider = imageManagerProvider;
  }

  public static MembersInjector<RestoreFragment> create(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<UiHelpers> uiHelpersProvider, Provider<ImageManager> imageManagerProvider) {
    return new RestoreFragment_MembersInjector(viewModelFactoryProvider, uiHelpersProvider, imageManagerProvider);
  }

  @Override
  public void injectMembers(RestoreFragment instance) {
    injectViewModelFactory(instance, viewModelFactoryProvider.get());
    injectUiHelpers(instance, uiHelpersProvider.get());
    injectImageManager(instance, imageManagerProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.jetpack.restore.RestoreFragment.viewModelFactory")
  public static void injectViewModelFactory(RestoreFragment instance,
      ViewModelProvider.Factory viewModelFactory) {
    instance.viewModelFactory = viewModelFactory;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.jetpack.restore.RestoreFragment.uiHelpers")
  public static void injectUiHelpers(RestoreFragment instance, UiHelpers uiHelpers) {
    instance.uiHelpers = uiHelpers;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.jetpack.restore.RestoreFragment.imageManager")
  public static void injectImageManager(RestoreFragment instance, ImageManager imageManager) {
    instance.imageManager = imageManager;
  }
}
