package org.wordpress.android.util;

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
public final class UrlUtilsWrapper_Factory implements Factory<UrlUtilsWrapper> {
  @Override
  public UrlUtilsWrapper get() {
    return newInstance();
  }

  public static UrlUtilsWrapper_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static UrlUtilsWrapper newInstance() {
    return new UrlUtilsWrapper();
  }

  private static final class InstanceHolder {
    private static final UrlUtilsWrapper_Factory INSTANCE = new UrlUtilsWrapper_Factory();
  }
}
