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
public final class SiteUtilsWrapper_Factory implements Factory<SiteUtilsWrapper> {
  @Override
  public SiteUtilsWrapper get() {
    return newInstance();
  }

  public static SiteUtilsWrapper_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static SiteUtilsWrapper newInstance() {
    return new SiteUtilsWrapper();
  }

  private static final class InstanceHolder {
    private static final SiteUtilsWrapper_Factory INSTANCE = new SiteUtilsWrapper_Factory();
  }
}
