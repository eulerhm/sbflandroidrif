package org.wordpress.android.ui.stories;

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
public final class StoriesMediaPickerResultHandler_Factory implements Factory<StoriesMediaPickerResultHandler> {
  @Override
  public StoriesMediaPickerResultHandler get() {
    return newInstance();
  }

  public static StoriesMediaPickerResultHandler_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static StoriesMediaPickerResultHandler newInstance() {
    return new StoriesMediaPickerResultHandler();
  }

  private static final class InstanceHolder {
    private static final StoriesMediaPickerResultHandler_Factory INSTANCE = new StoriesMediaPickerResultHandler_Factory();
  }
}
