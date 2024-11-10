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
public final class UriUtilsWrapper_Factory implements Factory<UriUtilsWrapper> {
  @Override
  public UriUtilsWrapper get() {
    return newInstance();
  }

  public static UriUtilsWrapper_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static UriUtilsWrapper newInstance() {
    return new UriUtilsWrapper();
  }

  private static final class InstanceHolder {
    private static final UriUtilsWrapper_Factory INSTANCE = new UriUtilsWrapper_Factory();
  }
}
