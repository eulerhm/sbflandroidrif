package org.wordpress.android.util.image;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;

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
public final class ImagePlaceholderManager_Factory implements Factory<ImagePlaceholderManager> {
  @Override
  public ImagePlaceholderManager get() {
    return newInstance();
  }

  public static ImagePlaceholderManager_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static ImagePlaceholderManager newInstance() {
    return new ImagePlaceholderManager();
  }

  private static final class InstanceHolder {
    private static final ImagePlaceholderManager_Factory INSTANCE = new ImagePlaceholderManager_Factory();
  }
}
