package org.wordpress.android.ui.media;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
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
public final class MediaPreviewFragment_MembersInjector implements MembersInjector<MediaPreviewFragment> {
  private final Provider<MediaStore> mMediaStoreProvider;

  private final Provider<ImageManager> mImageManagerProvider;

  private final Provider<AuthenticationUtils> mAuthenticationUtilsProvider;

  private final Provider<ExoPlayerUtils> mExoPlayerUtilsProvider;

  public MediaPreviewFragment_MembersInjector(Provider<MediaStore> mMediaStoreProvider,
      Provider<ImageManager> mImageManagerProvider,
      Provider<AuthenticationUtils> mAuthenticationUtilsProvider,
      Provider<ExoPlayerUtils> mExoPlayerUtilsProvider) {
    this.mMediaStoreProvider = mMediaStoreProvider;
    this.mImageManagerProvider = mImageManagerProvider;
    this.mAuthenticationUtilsProvider = mAuthenticationUtilsProvider;
    this.mExoPlayerUtilsProvider = mExoPlayerUtilsProvider;
  }

  public static MembersInjector<MediaPreviewFragment> create(
      Provider<MediaStore> mMediaStoreProvider, Provider<ImageManager> mImageManagerProvider,
      Provider<AuthenticationUtils> mAuthenticationUtilsProvider,
      Provider<ExoPlayerUtils> mExoPlayerUtilsProvider) {
    return new MediaPreviewFragment_MembersInjector(mMediaStoreProvider, mImageManagerProvider, mAuthenticationUtilsProvider, mExoPlayerUtilsProvider);
  }

  @Override
  public void injectMembers(MediaPreviewFragment instance) {
    injectMMediaStore(instance, mMediaStoreProvider.get());
    injectMImageManager(instance, mImageManagerProvider.get());
    injectMAuthenticationUtils(instance, mAuthenticationUtilsProvider.get());
    injectMExoPlayerUtils(instance, mExoPlayerUtilsProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.media.MediaPreviewFragment.mMediaStore")
  public static void injectMMediaStore(MediaPreviewFragment instance, MediaStore mMediaStore) {
    instance.mMediaStore = mMediaStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.media.MediaPreviewFragment.mImageManager")
  public static void injectMImageManager(MediaPreviewFragment instance,
      ImageManager mImageManager) {
    instance.mImageManager = mImageManager;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.media.MediaPreviewFragment.mAuthenticationUtils")
  public static void injectMAuthenticationUtils(MediaPreviewFragment instance,
      AuthenticationUtils mAuthenticationUtils) {
    instance.mAuthenticationUtils = mAuthenticationUtils;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.media.MediaPreviewFragment.mExoPlayerUtils")
  public static void injectMExoPlayerUtils(MediaPreviewFragment instance,
      ExoPlayerUtils mExoPlayerUtils) {
    instance.mExoPlayerUtils = mExoPlayerUtils;
  }
}
