package org.wordpress.android.util;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;

@ScopeMetadata("dagger.Reusable")
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
public final class FileProvider_Factory implements Factory<FileProvider> {
  @Override
  public FileProvider get() {
    return newInstance();
  }

  public static FileProvider_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static FileProvider newInstance() {
    return new FileProvider();
  }

  private static final class InstanceHolder {
    private static final FileProvider_Factory INSTANCE = new FileProvider_Factory();
  }
}
