package org.wordpress.android.ui.media;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.store.MediaStore;
import org.wordpress.android.ui.utils.AuthenticationUtils;
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
public final class MediaSettingsActivity_MembersInjector implements MembersInjector<MediaSettingsActivity> {
  private final Provider<MediaStore> mMediaStoreProvider;

  private final Provider<Dispatcher> mDispatcherProvider;

  private final Provider<ImageManager> mImageManagerProvider;

  private final Provider<AuthenticationUtils> mAuthenticationUtilsProvider;

  public MediaSettingsActivity_MembersInjector(Provider<MediaStore> mMediaStoreProvider,
      Provider<Dispatcher> mDispatcherProvider, Provider<ImageManager> mImageManagerProvider,
      Provider<AuthenticationUtils> mAuthenticationUtilsProvider) {
    this.mMediaStoreProvider = mMediaStoreProvider;
    this.mDispatcherProvider = mDispatcherProvider;
    this.mImageManagerProvider = mImageManagerProvider;
    this.mAuthenticationUtilsProvider = mAuthenticationUtilsProvider;
  }

  public static MembersInjector<MediaSettingsActivity> create(
      Provider<MediaStore> mMediaStoreProvider, Provider<Dispatcher> mDispatcherProvider,
      Provider<ImageManager> mImageManagerProvider,
      Provider<AuthenticationUtils> mAuthenticationUtilsProvider) {
    return new MediaSettingsActivity_MembersInjector(mMediaStoreProvider, mDispatcherProvider, mImageManagerProvider, mAuthenticationUtilsProvider);
  }

  @Override
  public void injectMembers(MediaSettingsActivity instance) {
    injectMMediaStore(instance, mMediaStoreProvider.get());
    injectMDispatcher(instance, mDispatcherProvider.get());
    injectMImageManager(instance, mImageManagerProvider.get());
    injectMAuthenticationUtils(instance, mAuthenticationUtilsProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.media.MediaSettingsActivity.mMediaStore")
  public static void injectMMediaStore(MediaSettingsActivity instance, MediaStore mMediaStore) {
    instance.mMediaStore = mMediaStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.media.MediaSettingsActivity.mDispatcher")
  public static void injectMDispatcher(MediaSettingsActivity instance, Dispatcher mDispatcher) {
    instance.mDispatcher = mDispatcher;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.media.MediaSettingsActivity.mImageManager")
  public static void injectMImageManager(MediaSettingsActivity instance,
      ImageManager mImageManager) {
    instance.mImageManager = mImageManager;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.media.MediaSettingsActivity.mAuthenticationUtils")
  public static void injectMAuthenticationUtils(MediaSettingsActivity instance,
      AuthenticationUtils mAuthenticationUtils) {
    instance.mAuthenticationUtils = mAuthenticationUtils;
  }
}
