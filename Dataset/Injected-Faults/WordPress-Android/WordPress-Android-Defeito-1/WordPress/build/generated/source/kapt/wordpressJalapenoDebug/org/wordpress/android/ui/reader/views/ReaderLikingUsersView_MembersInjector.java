package org.wordpress.android.ui.reader.views;

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
public final class ReaderLikingUsersView_MembersInjector implements MembersInjector<ReaderLikingUsersView> {
  private final Provider<ImageManager> mImageManagerProvider;

  public ReaderLikingUsersView_MembersInjector(Provider<ImageManager> mImageManagerProvider) {
    this.mImageManagerProvider = mImageManagerProvider;
  }

  public static MembersInjector<ReaderLikingUsersView> create(
      Provider<ImageManager> mImageManagerProvider) {
    return new ReaderLikingUsersView_MembersInjector(mImageManagerProvider);
  }

  @Override
  public void injectMembers(ReaderLikingUsersView instance) {
    injectMImageManager(instance, mImageManagerProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.views.ReaderLikingUsersView.mImageManager")
  public static void injectMImageManager(ReaderLikingUsersView instance,
      ImageManager mImageManager) {
    instance.mImageManager = mImageManager;
  }
}
