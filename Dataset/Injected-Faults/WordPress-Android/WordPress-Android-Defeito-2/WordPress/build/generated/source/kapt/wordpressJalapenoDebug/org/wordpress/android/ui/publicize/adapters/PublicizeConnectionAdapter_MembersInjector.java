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
public final class PublicizeConnectionAdapter_MembersInjector implements MembersInjector<PublicizeConnectionAdapter> {
  private final Provider<ImageManager> mImageManagerProvider;

  public PublicizeConnectionAdapter_MembersInjector(Provider<ImageManager> mImageManagerProvider) {
    this.mImageManagerProvider = mImageManagerProvider;
  }

  public static MembersInjector<PublicizeConnectionAdapter> create(
      Provider<ImageManager> mImageManagerProvider) {
    return new PublicizeConnectionAdapter_MembersInjector(mImageManagerProvider);
  }

  @Override
  public void injectMembers(PublicizeConnectionAdapter instance) {
    injectMImageManager(instance, mImageManagerProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.publicize.adapters.PublicizeConnectionAdapter.mImageManager")
  public static void injectMImageManager(PublicizeConnectionAdapter instance,
      ImageManager mImageManager) {
    instance.mImageManager = mImageManager;
  }
}
