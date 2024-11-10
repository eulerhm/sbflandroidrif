package org.wordpress.android.ui.photopicker;

import androidx.lifecycle.ViewModelProvider;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
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
public final class PhotoPickerFragment_MembersInjector implements MembersInjector<PhotoPickerFragment> {
  private final Provider<ImageManager> imageManagerProvider;

  private final Provider<ViewModelProvider.Factory> viewModelFactoryProvider;

  public PhotoPickerFragment_MembersInjector(Provider<ImageManager> imageManagerProvider,
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider) {
    this.imageManagerProvider = imageManagerProvider;
    this.viewModelFactoryProvider = viewModelFactoryProvider;
  }

  public static MembersInjector<PhotoPickerFragment> create(
      Provider<ImageManager> imageManagerProvider,
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider) {
    return new PhotoPickerFragment_MembersInjector(imageManagerProvider, viewModelFactoryProvider);
  }

  @Override
  public void injectMembers(PhotoPickerFragment instance) {
    injectImageManager(instance, imageManagerProvider.get());
    injectViewModelFactory(instance, viewModelFactoryProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.photopicker.PhotoPickerFragment.imageManager")
  public static void injectImageManager(PhotoPickerFragment instance, ImageManager imageManager) {
    instance.imageManager = imageManager;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.photopicker.PhotoPickerFragment.viewModelFactory")
  public static void injectViewModelFactory(PhotoPickerFragment instance,
      ViewModelProvider.Factory viewModelFactory) {
    instance.viewModelFactory = viewModelFactory;
  }
}
