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
public final class LocaleProvider_Factory implements Factory<LocaleProvider> {
  @Override
  public LocaleProvider get() {
    return newInstance();
  }

  public static LocaleProvider_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static LocaleProvider newInstance() {
    return new LocaleProvider();
  }

  private static final class InstanceHolder {
    private static final LocaleProvider_Factory INSTANCE = new LocaleProvider_Factory();
  }
}
