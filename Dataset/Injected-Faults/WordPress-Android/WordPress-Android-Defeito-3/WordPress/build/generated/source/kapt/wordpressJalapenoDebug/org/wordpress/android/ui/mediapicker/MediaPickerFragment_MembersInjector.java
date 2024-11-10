package org.wordpress.android.ui.mediapicker;

import androidx.lifecycle.ViewModelProvider;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.utils.UiHelpers;
import org.wordpress.android.util.SnackbarSequencer;
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
public final class MediaPickerFragment_MembersInjector implements MembersInjector<MediaPickerFragment> {
  private final Provider<ImageManager> imageManagerProvider;

  private final Provider<ViewModelProvider.Factory> viewModelFactoryProvider;

  private final Provider<SnackbarSequencer> snackbarSequencerProvider;

  private final Provider<UiHelpers> uiHelpersProvider;

  public MediaPickerFragment_MembersInjector(Provider<ImageManager> imageManagerProvider,
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<SnackbarSequencer> snackbarSequencerProvider,
      Provider<UiHelpers> uiHelpersProvider) {
    this.imageManagerProvider = imageManagerProvider;
    this.viewModelFactoryProvider = viewModelFactoryProvider;
    this.snackbarSequencerProvider = snackbarSequencerProvider;
    this.uiHelpersProvider = uiHelpersProvider;
  }

  public static MembersInjector<MediaPickerFragment> create(
      Provider<ImageManager> imageManagerProvider,
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<SnackbarSequencer> snackbarSequencerProvider,
      Provider<UiHelpers> uiHelpersProvider) {
    return new MediaPickerFragment_MembersInjector(imageManagerProvider, viewModelFactoryProvider, snackbarSequencerProvider, uiHelpersProvider);
  }

  @Override
  public void injectMembers(MediaPickerFragment instance) {
    injectImageManager(instance, imageManagerProvider.get());
    injectViewModelFactory(instance, viewModelFactoryProvider.get());
    injectSnackbarSequencer(instance, snackbarSequencerProvider.get());
    injectUiHelpers(instance, uiHelpersProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.mediapicker.MediaPickerFragment.imageManager")
  public static void injectImageManager(MediaPickerFragment instance, ImageManager imageManager) {
    instance.imageManager = imageManager;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.mediapicker.MediaPickerFragment.viewModelFactory")
  public static void injectViewModelFactory(MediaPickerFragment instance,
      ViewModelProvider.Factory viewModelFactory) {
    instance.viewModelFactory = viewModelFactory;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.mediapicker.MediaPickerFragment.snackbarSequencer")
  public static void injectSnackbarSequencer(MediaPickerFragment instance,
      SnackbarSequencer snackbarSequencer) {
    instance.snackbarSequencer = snackbarSequencer;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.mediapicker.MediaPickerFragment.uiHelpers")
  public static void injectUiHelpers(MediaPickerFragment instance, UiHelpers uiHelpers) {
    instance.uiHelpers = uiHelpers;
  }
}
