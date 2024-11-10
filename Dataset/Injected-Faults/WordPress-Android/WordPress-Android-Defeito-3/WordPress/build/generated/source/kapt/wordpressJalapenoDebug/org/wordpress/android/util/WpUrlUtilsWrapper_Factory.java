package org.wordpress.android.util;

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
public final class WpUrlUtilsWrapper_Factory implements Factory<WpUrlUtilsWrapper> {
  @Override
  public WpUrlUtilsWrapper get() {
    return newInstance();
  }

  public static WpUrlUtilsWrapper_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static WpUrlUtilsWrapper newInstance() {
    return new WpUrlUtilsWrapper();
  }

  private static final class InstanceHolder {
    private static final WpUrlUtilsWrapper_Factory INSTANCE = new WpUrlUtilsWrapper_Factory();
  }
}
