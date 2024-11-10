package org.wordpress.android.ui.layoutpicker;

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
public final class LayoutsAdapter_MembersInjector implements MembersInjector<LayoutsAdapter> {
  private final Provider<ImageManager> imageManagerProvider;

  public LayoutsAdapter_MembersInjector(Provider<ImageManager> imageManagerProvider) {
    this.imageManagerProvider = imageManagerProvider;
  }

  public static MembersInjector<LayoutsAdapter> create(
      Provider<ImageManager> imageManagerProvider) {
    return new LayoutsAdapter_MembersInjector(imageManagerProvider);
  }

  @Override
  public void injectMembers(LayoutsAdapter instance) {
    injectImageManager(instance, imageManagerProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.layoutpicker.LayoutsAdapter.imageManager")
  public static void injectImageManager(LayoutsAdapter instance, ImageManager imageManager) {
    instance.imageManager = imageManager;
  }
}
