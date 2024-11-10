package org.wordpress.android.util.image;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.media.VideoLoader;

@ScopeMetadata("javax.inject.Singleton")
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
public final class ImageManager_Factory implements Factory<ImageManager> {
  private final Provider<ImagePlaceholderManager> placeholderManagerProvider;

  private final Provider<VideoLoader> videoLoaderProvider;

  public ImageManager_Factory(Provider<ImagePlaceholderManager> placeholderManagerProvider,
      Provider<VideoLoader> videoLoaderProvider) {
    this.placeholderManagerProvider = placeholderManagerProvider;
    this.videoLoaderProvider = videoLoaderProvider;
  }

  @Override
  public ImageManager get() {
    return newInstance(placeholderManagerProvider.get(), videoLoaderProvider.get());
  }

  public static ImageManager_Factory create(
      Provider<ImagePlaceholderManager> placeholderManagerProvider,
      Provider<VideoLoader> videoLoaderProvider) {
    return new ImageManager_Factory(placeholderManagerProvider, videoLoaderProvider);
  }

  public static ImageManager newInstance(ImagePlaceholderManager placeholderManager,
      VideoLoader videoLoader) {
    return new ImageManager(placeholderManager, videoLoader);
  }
}
