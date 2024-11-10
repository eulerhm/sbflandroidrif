package org.wordpress.android.ui.mediapicker;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.store.MediaStore;
import org.wordpress.android.ui.posts.FeaturedImageHelper;
import org.wordpress.android.ui.posts.editor.ImageEditorTracker;
import org.wordpress.android.ui.utils.UiHelpers;

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
public final class MediaPickerActivity_MembersInjector implements MembersInjector<MediaPickerActivity> {
  private final Provider<Dispatcher> dispatcherProvider;

  private final Provider<MediaStore> mediaStoreProvider;

  private final Provider<FeaturedImageHelper> featuredImageHelperProvider;

  private final Provider<ImageEditorTracker> imageEditorTrackerProvider;

  private final Provider<UiHelpers> uiHelpersProvider;

  public MediaPickerActivity_MembersInjector(Provider<Dispatcher> dispatcherProvider,
      Provider<MediaStore> mediaStoreProvider,
      Provider<FeaturedImageHelper> featuredImageHelperProvider,
      Provider<ImageEditorTracker> imageEditorTrackerProvider,
      Provider<UiHelpers> uiHelpersProvider) {
    this.dispatcherProvider = dispatcherProvider;
    this.mediaStoreProvider = mediaStoreProvider;
    this.featuredImageHelperProvider = featuredImageHelperProvider;
    this.imageEditorTrackerProvider = imageEditorTrackerProvider;
    this.uiHelpersProvider = uiHelpersProvider;
  }

  public static MembersInjector<MediaPickerActivity> create(Provider<Dispatcher> dispatcherProvider,
      Provider<MediaStore> mediaStoreProvider,
      Provider<FeaturedImageHelper> featuredImageHelperProvider,
      Provider<ImageEditorTracker> imageEditorTrackerProvider,
      Provider<UiHelpers> uiHelpersProvider) {
    return new MediaPickerActivity_MembersInjector(dispatcherProvider, mediaStoreProvider, featuredImageHelperProvider, imageEditorTrackerProvider, uiHelpersProvider);
  }

  @Override
  public void injectMembers(MediaPickerActivity instance) {
    injectDispatcher(instance, dispatcherProvider.get());
    injectMediaStore(instance, mediaStoreProvider.get());
    injectFeaturedImageHelper(instance, featuredImageHelperProvider.get());
    injectImageEditorTracker(instance, imageEditorTrackerProvider.get());
    injectUiHelpers(instance, uiHelpersProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.mediapicker.MediaPickerActivity.dispatcher")
  public static void injectDispatcher(MediaPickerActivity instance, Dispatcher dispatcher) {
    instance.dispatcher = dispatcher;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.mediapicker.MediaPickerActivity.mediaStore")
  public static void injectMediaStore(MediaPickerActivity instance, MediaStore mediaStore) {
    instance.mediaStore = mediaStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.mediapicker.MediaPickerActivity.featuredImageHelper")
  public static void injectFeaturedImageHelper(MediaPickerActivity instance,
      FeaturedImageHelper featuredImageHelper) {
    instance.featuredImageHelper = featuredImageHelper;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.mediapicker.MediaPickerActivity.imageEditorTracker")
  public static void injectImageEditorTracker(MediaPickerActivity instance,
      ImageEditorTracker imageEditorTracker) {
    instance.imageEditorTracker = imageEditorTracker;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.mediapicker.MediaPickerActivity.uiHelpers")
  public static void injectUiHelpers(MediaPickerActivity instance, UiHelpers uiHelpers) {
    instance.uiHelpers = uiHelpers;
  }
}
