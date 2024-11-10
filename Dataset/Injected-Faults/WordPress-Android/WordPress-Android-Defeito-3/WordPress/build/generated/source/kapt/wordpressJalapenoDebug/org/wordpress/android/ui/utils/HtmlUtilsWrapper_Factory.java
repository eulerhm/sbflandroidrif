package org.wordpress.android.ui.utils;

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
public final class HtmlUtilsWrapper_Factory implements Factory<HtmlUtilsWrapper> {
  @Override
  public HtmlUtilsWrapper get() {
    return newInstance();
  }

  public static HtmlUtilsWrapper_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static HtmlUtilsWrapper newInstance() {
    return new HtmlUtilsWrapper();
  }

  private static final class InstanceHolder {
    private static final HtmlUtilsWrapper_Factory INSTANCE = new HtmlUtilsWrapper_Factory();
  }
}
