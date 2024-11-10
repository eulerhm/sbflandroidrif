package org.wordpress.android.ui.publicize.adapters;

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
public final class PublicizeServiceAdapter_MembersInjector implements MembersInjector<PublicizeServiceAdapter> {
  private final Provider<ImageManager> mImageManagerProvider;

  public PublicizeServiceAdapter_MembersInjector(Provider<ImageManager> mImageManagerProvider) {
    this.mImageManagerProvider = mImageManagerProvider;
  }

  public static MembersInjector<PublicizeServiceAdapter> create(
      Provider<ImageManager> mImageManagerProvider) {
    return new PublicizeServiceAdapter_MembersInjector(mImageManagerProvider);
  }

  @Override
  public void injectMembers(PublicizeServiceAdapter instance) {
    injectMImageManager(instance, mImageManagerProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.publicize.adapters.PublicizeServiceAdapter.mImageManager")
  public static void injectMImageManager(PublicizeServiceAdapter instance,
      ImageManager mImageManager) {
    instance.mImageManager = mImageManager;
  }
}
