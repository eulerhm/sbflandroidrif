package org.wordpress.android.ui.posts;

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
public final class PrepublishingHomeFragment_MembersInjector implements MembersInjector<PrepublishingHomeFragment> {
  private final Provider<UiHelpers> uiHelpersProvider;

  private final Provider<ImageManager> imageManagerProvider;

  private final Provider<ViewModelProvider.Factory> viewModelFactoryProvider;

  public PrepublishingHomeFragment_MembersInjector(Provider<UiHelpers> uiHelpersProvider,
      Provider<ImageManager> imageManagerProvider,
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider) {
    this.uiHelpersProvider = uiHelpersProvider;
    this.imageManagerProvider = imageManagerProvider;
    this.viewModelFactoryProvider = viewModelFactoryProvider;
  }

  public static MembersInjector<PrepublishingHomeFragment> create(
      Provider<UiHelpers> uiHelpersProvider, Provider<ImageManager> imageManagerProvider,
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider) {
    return new PrepublishingHomeFragment_MembersInjector(uiHelpersProvider, imageManagerProvider, viewModelFactoryProvider);
  }

  @Override
  public void injectMembers(PrepublishingHomeFragment instance) {
    injectUiHelpers(instance, uiHelpersProvider.get());
    injectImageManager(instance, imageManagerProvider.get());
    injectViewModelFactory(instance, viewModelFactoryProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.PrepublishingHomeFragment.uiHelpers")
  public static void injectUiHelpers(PrepublishingHomeFragment instance, UiHelpers uiHelpers) {
    instance.uiHelpers = uiHelpers;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.PrepublishingHomeFragment.imageManager")
  public static void injectImageManager(PrepublishingHomeFragment instance,
      ImageManager imageManager) {
    instance.imageManager = imageManager;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.PrepublishingHomeFragment.viewModelFactory")
  public static void injectViewModelFactory(PrepublishingHomeFragment instance,
      ViewModelProvider.Factory viewModelFactory) {
    instance.viewModelFactory = viewModelFactory;
  }
}
