package org.wordpress.android.ui.media;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.store.MediaStore;

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
public final class MediaPreviewActivity_MembersInjector implements MembersInjector<MediaPreviewActivity> {
  private final Provider<MediaStore> mMediaStoreProvider;

  public MediaPreviewActivity_MembersInjector(Provider<MediaStore> mMediaStoreProvider) {
    this.mMediaStoreProvider = mMediaStoreProvider;
  }

  public static MembersInjector<MediaPreviewActivity> create(
      Provider<MediaStore> mMediaStoreProvider) {
    return new MediaPreviewActivity_MembersInjector(mMediaStoreProvider);
  }

  @Override
  public void injectMembers(MediaPreviewActivity instance) {
    injectMMediaStore(instance, mMediaStoreProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.media.MediaPreviewActivity.mMediaStore")
  public static void injectMMediaStore(MediaPreviewActivity instance, MediaStore mMediaStore) {
    instance.mMediaStore = mMediaStore;
  }
}
