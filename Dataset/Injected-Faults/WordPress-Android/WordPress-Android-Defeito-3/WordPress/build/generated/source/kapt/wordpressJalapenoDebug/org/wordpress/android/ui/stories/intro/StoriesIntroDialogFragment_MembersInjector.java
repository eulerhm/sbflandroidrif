package org.wordpress.android.ui.stories.intro;

import androidx.lifecycle.ViewModelProvider;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.photopicker.MediaPickerLauncher;

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
public final class StoriesIntroDialogFragment_MembersInjector implements MembersInjector<StoriesIntroDialogFragment> {
  private final Provider<ViewModelProvider.Factory> viewModelFactoryProvider;

  private final Provider<MediaPickerLauncher> mediaPickerLauncherProvider;

  public StoriesIntroDialogFragment_MembersInjector(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<MediaPickerLauncher> mediaPickerLauncherProvider) {
    this.viewModelFactoryProvider = viewModelFactoryProvider;
    this.mediaPickerLauncherProvider = mediaPickerLauncherProvider;
  }

  public static MembersInjector<StoriesIntroDialogFragment> create(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<MediaPickerLauncher> mediaPickerLauncherProvider) {
    return new StoriesIntroDialogFragment_MembersInjector(viewModelFactoryProvider, mediaPickerLauncherProvider);
  }

  @Override
  public void injectMembers(StoriesIntroDialogFragment instance) {
    injectViewModelFactory(instance, viewModelFactoryProvider.get());
    injectMediaPickerLauncher(instance, mediaPickerLauncherProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.stories.intro.StoriesIntroDialogFragment.viewModelFactory")
  public static void injectViewModelFactory(StoriesIntroDialogFragment instance,
      ViewModelProvider.Factory viewModelFactory) {
    instance.viewModelFactory = viewModelFactory;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.stories.intro.StoriesIntroDialogFragment.mediaPickerLauncher")
  public static void injectMediaPickerLauncher(StoriesIntroDialogFragment instance,
      MediaPickerLauncher mediaPickerLauncher) {
    instance.mediaPickerLauncher = mediaPickerLauncher;
  }
}
