package org.wordpress.android.ui.photopicker;

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
public final class PhotoPickerActivity_MembersInjector implements MembersInjector<PhotoPickerActivity> {
  private final Provider<Dispatcher> mDispatcherProvider;

  private final Provider<MediaStore> mMediaStoreProvider;

  private final Provider<FeaturedImageHelper> mFeaturedImageHelperProvider;

  private final Provider<ImageEditorTracker> mImageEditorTrackerProvider;

  public PhotoPickerActivity_MembersInjector(Provider<Dispatcher> mDispatcherProvider,
      Provider<MediaStore> mMediaStoreProvider,
      Provider<FeaturedImageHelper> mFeaturedImageHelperProvider,
      Provider<ImageEditorTracker> mImageEditorTrackerProvider) {
    this.mDispatcherProvider = mDispatcherProvider;
    this.mMediaStoreProvider = mMediaStoreProvider;
    this.mFeaturedImageHelperProvider = mFeaturedImageHelperProvider;
    this.mImageEditorTrackerProvider = mImageEditorTrackerProvider;
  }

  public static MembersInjector<PhotoPickerActivity> create(
      Provider<Dispatcher> mDispatcherProvider, Provider<MediaStore> mMediaStoreProvider,
      Provider<FeaturedImageHelper> mFeaturedImageHelperProvider,
      Provider<ImageEditorTracker> mImageEditorTrackerProvider) {
    return new PhotoPickerActivity_MembersInjector(mDispatcherProvider, mMediaStoreProvider, mFeaturedImageHelperProvider, mImageEditorTrackerProvider);
  }

  @Override
  public void injectMembers(PhotoPickerActivity instance) {
    injectMDispatcher(instance, mDispatcherProvider.get());
    injectMMediaStore(instance, mMediaStoreProvider.get());
    injectMFeaturedImageHelper(instance, mFeaturedImageHelperProvider.get());
    injectMImageEditorTracker(instance, mImageEditorTrackerProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.photopicker.PhotoPickerActivity.mDispatcher")
  public static void injectMDispatcher(PhotoPickerActivity instance, Dispatcher mDispatcher) {
    instance.mDispatcher = mDispatcher;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.photopicker.PhotoPickerActivity.mMediaStore")
  public static void injectMMediaStore(PhotoPickerActivity instance, MediaStore mMediaStore) {
    instance.mMediaStore = mMediaStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.photopicker.PhotoPickerActivity.mFeaturedImageHelper")
  public static void injectMFeaturedImageHelper(PhotoPickerActivity instance,
      FeaturedImageHelper mFeaturedImageHelper) {
    instance.mFeaturedImageHelper = mFeaturedImageHelper;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.photopicker.PhotoPickerActivity.mImageEditorTracker")
  public static void injectMImageEditorTracker(PhotoPickerActivity instance,
      ImageEditorTracker mImageEditorTracker) {
    instance.mImageEditorTracker = mImageEditorTracker;
  }
}
