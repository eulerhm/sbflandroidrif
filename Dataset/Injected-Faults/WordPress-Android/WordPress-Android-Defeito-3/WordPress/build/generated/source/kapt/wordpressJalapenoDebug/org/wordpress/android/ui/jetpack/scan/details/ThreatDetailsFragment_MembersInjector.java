package org.wordpress.android.ui.jetpack.scan.details;

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
public final class ThreatDetailsFragment_MembersInjector implements MembersInjector<ThreatDetailsFragment> {
  private final Provider<ImageManager> imageManagerProvider;

  private final Provider<UiHelpers> uiHelpersProvider;

  private final Provider<ViewModelProvider.Factory> viewModelFactoryProvider;

  public ThreatDetailsFragment_MembersInjector(Provider<ImageManager> imageManagerProvider,
      Provider<UiHelpers> uiHelpersProvider,
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider) {
    this.imageManagerProvider = imageManagerProvider;
    this.uiHelpersProvider = uiHelpersProvider;
    this.viewModelFactoryProvider = viewModelFactoryProvider;
  }

  public static MembersInjector<ThreatDetailsFragment> create(
      Provider<ImageManager> imageManagerProvider, Provider<UiHelpers> uiHelpersProvider,
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider) {
    return new ThreatDetailsFragment_MembersInjector(imageManagerProvider, uiHelpersProvider, viewModelFactoryProvider);
  }

  @Override
  public void injectMembers(ThreatDetailsFragment instance) {
    injectImageManager(instance, imageManagerProvider.get());
    injectUiHelpers(instance, uiHelpersProvider.get());
    injectViewModelFactory(instance, viewModelFactoryProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.jetpack.scan.details.ThreatDetailsFragment.imageManager")
  public static void injectImageManager(ThreatDetailsFragment instance, ImageManager imageManager) {
    instance.imageManager = imageManager;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.jetpack.scan.details.ThreatDetailsFragment.uiHelpers")
  public static void injectUiHelpers(ThreatDetailsFragment instance, UiHelpers uiHelpers) {
    instance.uiHelpers = uiHelpers;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.jetpack.scan.details.ThreatDetailsFragment.viewModelFactory")
  public static void injectViewModelFactory(ThreatDetailsFragment instance,
      ViewModelProvider.Factory viewModelFactory) {
    instance.viewModelFactory = viewModelFactory;
  }
}
