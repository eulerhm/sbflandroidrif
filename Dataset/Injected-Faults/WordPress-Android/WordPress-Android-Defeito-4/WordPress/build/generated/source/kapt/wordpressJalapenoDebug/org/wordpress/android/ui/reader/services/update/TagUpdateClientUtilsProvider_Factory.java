package org.wordpress.android.ui.reader.services.update;

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
public final class TagUpdateClientUtilsProvider_Factory implements Factory<TagUpdateClientUtilsProvider> {
  @Override
  public TagUpdateClientUtilsProvider get() {
    return newInstance();
  }

  public static TagUpdateClientUtilsProvider_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static TagUpdateClientUtilsProvider newInstance() {
    return new TagUpdateClientUtilsProvider();
  }

  private static final class InstanceHolder {
    private static final TagUpdateClientUtilsProvider_Factory INSTANCE = new TagUpdateClientUtilsProvider_Factory();
  }
}
