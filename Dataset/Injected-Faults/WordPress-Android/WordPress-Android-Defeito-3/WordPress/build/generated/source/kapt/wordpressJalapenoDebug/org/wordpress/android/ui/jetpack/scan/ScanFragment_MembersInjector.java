package org.wordpress.android.ui.jetpack.scan;

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
public final class ScanFragment_MembersInjector implements MembersInjector<ScanFragment> {
  private final Provider<ImageManager> imageManagerProvider;

  private final Provider<UiHelpers> uiHelpersProvider;

  private final Provider<ViewModelProvider.Factory> viewModelFactoryProvider;

  public ScanFragment_MembersInjector(Provider<ImageManager> imageManagerProvider,
      Provider<UiHelpers> uiHelpersProvider,
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider) {
    this.imageManagerProvider = imageManagerProvider;
    this.uiHelpersProvider = uiHelpersProvider;
    this.viewModelFactoryProvider = viewModelFactoryProvider;
  }

  public static MembersInjector<ScanFragment> create(Provider<ImageManager> imageManagerProvider,
      Provider<UiHelpers> uiHelpersProvider,
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider) {
    return new ScanFragment_MembersInjector(imageManagerProvider, uiHelpersProvider, viewModelFactoryProvider);
  }

  @Override
  public void injectMembers(ScanFragment instance) {
    injectImageManager(instance, imageManagerProvider.get());
    injectUiHelpers(instance, uiHelpersProvider.get());
    injectViewModelFactory(instance, viewModelFactoryProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.jetpack.scan.ScanFragment.imageManager")
  public static void injectImageManager(ScanFragment instance, ImageManager imageManager) {
    instance.imageManager = imageManager;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.jetpack.scan.ScanFragment.uiHelpers")
  public static void injectUiHelpers(ScanFragment instance, UiHelpers uiHelpers) {
    instance.uiHelpers = uiHelpers;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.jetpack.scan.ScanFragment.viewModelFactory")
  public static void injectViewModelFactory(ScanFragment instance,
      ViewModelProvider.Factory viewModelFactory) {
    instance.viewModelFactory = viewModelFactory;
  }
}
