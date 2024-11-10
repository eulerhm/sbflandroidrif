package org.wordpress.android.ui.reader.utils;

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
public final class DateProvider_Factory implements Factory<DateProvider> {
  @Override
  public DateProvider get() {
    return newInstance();
  }

  public static DateProvider_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static DateProvider newInstance() {
    return new DateProvider();
  }

  private static final class InstanceHolder {
    private static final DateProvider_Factory INSTANCE = new DateProvider_Factory();
  }
}
