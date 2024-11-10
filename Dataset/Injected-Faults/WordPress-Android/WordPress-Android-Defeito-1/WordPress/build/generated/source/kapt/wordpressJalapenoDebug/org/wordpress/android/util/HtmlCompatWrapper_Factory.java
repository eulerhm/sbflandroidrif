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
public final class HtmlCompatWrapper_Factory implements Factory<HtmlCompatWrapper> {
  @Override
  public HtmlCompatWrapper get() {
    return newInstance();
  }

  public static HtmlCompatWrapper_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static HtmlCompatWrapper newInstance() {
    return new HtmlCompatWrapper();
  }

  private static final class InstanceHolder {
    private static final HtmlCompatWrapper_Factory INSTANCE = new HtmlCompatWrapper_Factory();
  }
}
