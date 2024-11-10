package org.wordpress.android.util.image.getters;

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
public final class WPCustomImageGetter_MembersInjector implements MembersInjector<WPCustomImageGetter> {
  private final Provider<ImageManager> imageManagerProvider;

  public WPCustomImageGetter_MembersInjector(Provider<ImageManager> imageManagerProvider) {
    this.imageManagerProvider = imageManagerProvider;
  }

  public static MembersInjector<WPCustomImageGetter> create(
      Provider<ImageManager> imageManagerProvider) {
    return new WPCustomImageGetter_MembersInjector(imageManagerProvider);
  }

  @Override
  public void injectMembers(WPCustomImageGetter instance) {
    injectImageManager(instance, imageManagerProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.util.image.getters.WPCustomImageGetter.imageManager")
  public static void injectImageManager(WPCustomImageGetter instance, ImageManager imageManager) {
    instance.imageManager = imageManager;
  }
}
