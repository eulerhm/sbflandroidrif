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
public final class MimeTypeMapUtilsWrapper_Factory implements Factory<MimeTypeMapUtilsWrapper> {
  @Override
  public MimeTypeMapUtilsWrapper get() {
    return newInstance();
  }

  public static MimeTypeMapUtilsWrapper_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static MimeTypeMapUtilsWrapper newInstance() {
    return new MimeTypeMapUtilsWrapper();
  }

  private static final class InstanceHolder {
    private static final MimeTypeMapUtilsWrapper_Factory INSTANCE = new MimeTypeMapUtilsWrapper_Factory();
  }
}
