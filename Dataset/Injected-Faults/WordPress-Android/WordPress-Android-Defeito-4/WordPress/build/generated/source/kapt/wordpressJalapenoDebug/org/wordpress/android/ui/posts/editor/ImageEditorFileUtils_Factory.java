package org.wordpress.android.ui.posts.editor;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;

@ScopeMetadata
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
public final class ImageEditorFileUtils_Factory implements Factory<ImageEditorFileUtils> {
  @Override
  public ImageEditorFileUtils get() {
    return newInstance();
  }

  public static ImageEditorFileUtils_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static ImageEditorFileUtils newInstance() {
    return new ImageEditorFileUtils();
  }

  private static final class InstanceHolder {
    private static final ImageEditorFileUtils_Factory INSTANCE = new ImageEditorFileUtils_Factory();
  }
}
