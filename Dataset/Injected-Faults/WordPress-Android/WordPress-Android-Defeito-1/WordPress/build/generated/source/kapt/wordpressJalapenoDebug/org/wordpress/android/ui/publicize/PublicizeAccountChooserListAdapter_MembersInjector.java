package org.wordpress.android.ui.publicize;

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
public final class PublicizeAccountChooserListAdapter_MembersInjector implements MembersInjector<PublicizeAccountChooserListAdapter> {
  private final Provider<ImageManager> mImageManagerProvider;

  public PublicizeAccountChooserListAdapter_MembersInjector(
      Provider<ImageManager> mImageManagerProvider) {
    this.mImageManagerProvider = mImageManagerProvider;
  }

  public static MembersInjector<PublicizeAccountChooserListAdapter> create(
      Provider<ImageManager> mImageManagerProvider) {
    return new PublicizeAccountChooserListAdapter_MembersInjector(mImageManagerProvider);
  }

  @Override
  public void injectMembers(PublicizeAccountChooserListAdapter instance) {
    injectMImageManager(instance, mImageManagerProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.publicize.PublicizeAccountChooserListAdapter.mImageManager")
  public static void injectMImageManager(PublicizeAccountChooserListAdapter instance,
      ImageManager mImageManager) {
    instance.mImageManager = mImageManager;
  }
}
