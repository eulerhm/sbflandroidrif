package org.wordpress.android.ui.mysite;

import androidx.lifecycle.ViewModelProvider;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.main.utils.MeGravatarLoader;
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
public final class MySiteFragment_MembersInjector implements MembersInjector<MySiteFragment> {
  private final Provider<ViewModelProvider.Factory> viewModelFactoryProvider;

  private final Provider<UiHelpers> uiHelpersProvider;

  private final Provider<MeGravatarLoader> meGravatarLoaderProvider;

  private final Provider<ImageManager> imageManagerProvider;

  public MySiteFragment_MembersInjector(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<UiHelpers> uiHelpersProvider, Provider<MeGravatarLoader> meGravatarLoaderProvider,
      Provider<ImageManager> imageManagerProvider) {
    this.viewModelFactoryProvider = viewModelFactoryProvider;
    this.uiHelpersProvider = uiHelpersProvider;
    this.meGravatarLoaderProvider = meGravatarLoaderProvider;
    this.imageManagerProvider = imageManagerProvider;
  }

  public static MembersInjector<MySiteFragment> create(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<UiHelpers> uiHelpersProvider, Provider<MeGravatarLoader> meGravatarLoaderProvider,
      Provider<ImageManager> imageManagerProvider) {
    return new MySiteFragment_MembersInjector(viewModelFactoryProvider, uiHelpersProvider, meGravatarLoaderProvider, imageManagerProvider);
  }

  @Override
  public void injectMembers(MySiteFragment instance) {
    injectViewModelFactory(instance, viewModelFactoryProvider.get());
    injectUiHelpers(instance, uiHelpersProvider.get());
    injectMeGravatarLoader(instance, meGravatarLoaderProvider.get());
    injectImageManager(instance, imageManagerProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.mysite.MySiteFragment.viewModelFactory")
  public static void injectViewModelFactory(MySiteFragment instance,
      ViewModelProvider.Factory viewModelFactory) {
    instance.viewModelFactory = viewModelFactory;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.mysite.MySiteFragment.uiHelpers")
  public static void injectUiHelpers(MySiteFragment instance, UiHelpers uiHelpers) {
    instance.uiHelpers = uiHelpers;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.mysite.MySiteFragment.meGravatarLoader")
  public static void injectMeGravatarLoader(MySiteFragment instance,
      MeGravatarLoader meGravatarLoader) {
    instance.meGravatarLoader = meGravatarLoader;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.mysite.MySiteFragment.imageManager")
  public static void injectImageManager(MySiteFragment instance, ImageManager imageManager) {
    instance.imageManager = imageManager;
  }
}
